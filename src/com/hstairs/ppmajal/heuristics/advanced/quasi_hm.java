/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.heuristics.advanced;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.BinaryOp;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.heuristics.Aibr;
import com.hstairs.ppmajal.heuristics.Heuristic;
import com.hstairs.ppmajal.heuristics.utils.LpInterface;
import com.hstairs.ppmajal.heuristics.utils.cplex_interface;
import com.hstairs.ppmajal.heuristics.utils.ojalgo_interface;
import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import ilog.concert.IloException;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Float.MAX_VALUE;
import static java.lang.System.out;
import static java.util.Collections.nCopies;



/**
 * @author enrico
 */
public class quasi_hm extends Heuristic {

    public HashMap<Integer, LpInterface> lps;
    protected HashMap<Integer, ArrayList<Condition>> poss_achiever;
    protected ArrayList<Integer> dist;
    private boolean reacheability_setting;
    private boolean cplex = true;
    private HashMap<Integer, Collection<GroundAction>> cond_to_actions;
    private boolean risky = true;
    
    public int n_lp_invocations;
    public boolean greedy = false;


    private Condition Gc;


    public quasi_hm (EPddlProblem problem) {
        super(problem.getGoals(),problem.actions,problem.getProcessesSet(),problem.getEventsSet(),problem.globalConstraints,problem);
    }

    @Override
    public Float setup (State gs) {

        PDDLState s = (PDDLState) gs;
        Aibr first_reachH = new Aibr(problem);
        first_reachH.setup(s);
        first_reachH.set(true, true);
        Float ret = first_reachH.computeEstimate(s);
        if (ret == Float.MAX_VALUE) {
            return ret;
        }
        A = first_reachH.getReachableTransitions();
        this.simplify_actions(s);
        this.cond_to_actions = new HashMap();
        forceUniquenessInConditionsAndInternalActions();
        //identify_complex_conditions(all_conditions, A);
        generate_achievers(s);
        try {
            this.generate_linear_programs(A, s);
        } catch (IloException ex) {
            Logger.getLogger(quasi_hm.class.getName()).log(Level.SEVERE, null, ex);
        }
        n_lp_invocations = 0;
        reacheability_setting = true;
        Utils.dbg_print(debug, "Reachability Analysis Started");
        Float ret2 = computeEstimate(s);
        Utils.dbg_print(debug, "Reachability Analysis Terminated");
        reacheability_setting = false;
        sat_test_within_cost = false; //don't need to recheck precondition sat for each state. It is done in the beginning for every possible condition
        out.println("Hard Conditions: " + this.numberOfHardConditions);
        out.println("Simple Conditions: " + (this.conditionUniverse.size() - this.numberOfHardConditions));
        return ret2;
    }

    @Override
    public void forceUniquenessInConditionsAndInternalActions ( ) {
        int counter2 = 0;
        conditionUniverse = new LinkedHashSet();
        int counter_actions = 0;
        G.setHeuristicId(counter2++);
        conditionUniverse.add(G);
        this.fromConditionStringToUniqueInteger = new HashMap();
        ArrayList<GroundAction> actions_to_consider = new ArrayList(A);
        for (GroundAction a : actions_to_consider) {
//            if (a.getPreconditions() != null) {
            if (a.getPreconditions() != null && a.getPreconditions().sons != null && !a.getPreconditions().sons.isEmpty()) {
                counter2 = this.establishHeuristicConditionId(a.getPreconditions(), counter2);
                update_mapping(a.getPreconditions(), a);
            } else {//this creates a fake precondition for the action. It is needed to trigger the very first set of actions that can be applied
                a.setPreconditions(new AndCond());
                counter2 = this.establishHeuristicConditionId(a.getPreconditions(), counter2);
                update_mapping(a.getPreconditions(), a);
            }
//            }
        }

        this.gC.setHeuristicId(counter2);
        //System.out.println(conditions);;

    }

    @Override
    public Float computeEstimate (State gs) {
        PDDLState s = (PDDLState) gs;
        //PriorityQueue<ConditionsNode> q = new PriorityQueue();
        if (s.satisfy(G)) {
            return 0f;
        }
        FibonacciHeap<Condition> q = new FibonacciHeap();
//        if (this.reacheability_setting)
//            generate_lp(A,s);
//        else
//            generate_lp(this.reachable,s);
//        relaxed_plan_actions = new LinkedHashSet();
        //setting up the initial values
        ArrayList<Boolean> closed = new ArrayList<>(nCopies(conditionUniverse.size() + 1, false));
        ArrayList<Float> distance = new ArrayList<>(nCopies(conditionUniverse.size() + 1, MAX_VALUE));
        ArrayList<Boolean> open_list = new ArrayList<>(nCopies(conditionUniverse.size() + 1, false));
        HashMap<Integer, FibonacciHeapNode> cond_to_entry = new HashMap();
        for (Condition c : conditionUniverse) {
            if (s.satisfy(c)) {
                FibonacciHeapNode node = new FibonacciHeapNode(c);
                q.insert(node, 0);
                cond_to_entry.put(c.getHeuristicId(), node);
                distance.set(c.getHeuristicId(), 0f);
                open_list.set(c.getHeuristicId(), true);
                closed.set(c.getHeuristicId(), true);
            } else {
                //lps.get(c.getCounter()).initialize(A, s);

                lps.get(c.getHeuristicId()).update_conditions_bound_plus_reset_variables(s);

            }
        }

        numberOfReachableConditions = 0;

        ArrayList<Boolean> active_actions = new ArrayList<>(nCopies(A.size() + 1, false));
//        for (GroundAction gr: temp_a){
//            if (gr.isApplicable(s))
//                //active_actions.add(gr);
//        }
        Collection<Condition> temp_conditions = null;
//        System.out.println(all_conditions.size());
        temp_conditions = new ArrayList();
        while (!q.isEmpty()) {
            //if (!first) {
//            System.out.println(++iteration);
            Float first = null;

//            System.out.println("New Iteration");
            while (!q.isEmpty()) {//take all the elements with equal distance from the initial state

                if (this.additive_h && !this.reacheability_setting) {
                    if (distance.get(G.getHeuristicId()) != Float.MAX_VALUE) {
                        return distance.get(G.getHeuristicId());
                    }
                }

                Condition cn = q.removeMin().getData();
                if (distance.get(cn.getHeuristicId()) == Float.MAX_VALUE) {
                    System.out.println("Anomaly!!!");//This shouldn't happen as only reachable conditions are put in the list
                    break;
                }
                if (first == null) {
                    first = distance.get(cn.getHeuristicId());
                } else if (first < distance.get(cn.getHeuristicId())) {//put back and stop
                    distance.set(cn.getHeuristicId(), distance.get(cn.getHeuristicId()));
                    open_list.set(cn.getHeuristicId(), true);
                    FibonacciHeapNode node = new FibonacciHeapNode(cn);
                    q.insert(node, distance.get(cn.getHeuristicId()));
                    cond_to_entry.put(cn.getHeuristicId(), node);
                    first = null;

                    break;//exit from this inner loop and compute cost for new conditons that can be achieved
                    //looking at the active actions activated by this step.
                }

                closed.set(cn.getHeuristicId(), true);//this is the best cost so far; no need to reopen this condition again

                if (cn.getHeuristicId() == G.getHeuristicId() && !this.reacheability_setting) {
                    if (distance.get(cn.getHeuristicId()) == Float.MAX_VALUE) {
                        System.out.println("Anomaly");

                    }
                    return Math.max(distance.get(cn.getHeuristicId()), 1f);
                }
                Collection<GroundAction> actions = this.cond_to_actions.get(cn.getHeuristicId());
//                System.out.println("Action activated:"+gr);
                if (actions != null) {
                    for (GroundAction gr : actions) {
                        if (gr != null) {
                            //System.out.println("Action identified as reachable:"+gr);
                            active_actions.set(gr.getId(), Boolean.TRUE);
                            if (this.reacheability_setting) {
                                this.reachable.add(gr);
                            }
                            temp_conditions.addAll(poss_achiever.get(gr.getId()));

                        }
                    }
                }

            }

            for (Condition cond : temp_conditions) {
//                System.out.println("Condition checking:"+cond);
//                System.out.println("Action that is connected:"+this.cond_action.get(cond.getCounter()));
                if (!closed.get(cond.getHeuristicId())) {
//                    System.out.println("Condition under analysis:"+cond);

                    Float current_cost = null;
                    current_cost = lps.get(cond.getHeuristicId()).update_cost(s, active_actions, distance);
                    n_lp_invocations++;
                    if (current_cost != Float.MAX_VALUE) {
                        if (this.greedy && cond.getHeuristicId() == G.getHeuristicId() && !this.reacheability_setting) {
                            //System.out.println("Eary Exit");
                            return Math.max(current_cost, 1f);
                        }
                        update_cost_if_necessary(open_list, distance, cond, q, cond_to_entry, current_cost);
                    }
                } else {

                }

            }
        }

        //System.out.println("Current Estimate to the goal:"+this.compute_float_cost(s, G, dist));
//        System.out.println(distance.get(G.getCounter()));
//        System.exit(-1);
//        if (distance.get(G.getCounter())==Float.MAX_VALUE){
////            System.out.println("Dead-End in:"+s.pddlPrint());
//        }
//        System.out.println("Reachable actions:"+this.reachable);
        return Math.max(distance.get(G.getHeuristicId()), 1f);
    }

    private void generate_achievers (PDDLState s_0) {
        poss_achiever = new HashMap();
        //this should also include the indirect dependencies, otherwise does not work!!
        for (GroundAction gr : this.A) {
            poss_achiever.put(gr.getId(), new ArrayList());
            for (Condition c1 : this.conditionUniverse) {
                if (gr.getPreconditions().getHeuristicId() != c1.getHeuristicId()) {
                    ComplexCondition c = (ComplexCondition) c1;
                    for (Condition c_in : (Collection<Condition>) c.sons) {
                        if (c_in instanceof Comparison) {
                            for (NumFluent nf : gr.getNumericFluentAffected()) {
                                if (c_in.getInvolvedFluents().contains(nf)) {
                                    poss_achiever.get(gr.getId()).add(c);
                                    break;
                                }
                            }
//                            if (gr.getNumberOfExecution(s_0, (Comparison) c_in)!= Float.MAX_VALUE) {
//                                poss_achiever.get(gr.counter).add(c);
//                                break;
//                            }
                        } else if (c_in instanceof Predicate) {
                            if (gr.weakAchiever((Predicate) c_in)) {
                                poss_achiever.get(gr.getId()).add(c);
                                break;
                            }
                        }
                    }
                }

            }
        }
    }

    private void update_cost_if_necessary (ArrayList<Boolean> open_list, ArrayList<Float> dist, Condition p, FibonacciHeap<Condition> q, HashMap<Integer, FibonacciHeapNode> cond_to_entry, Float current_cost) {
        if (current_cost == Float.MAX_VALUE) {
            return;
        }
        if (open_list.get(p.getHeuristicId())) {
            if (dist.get(p.getHeuristicId()) > current_cost) {
                q.decreaseKey(cond_to_entry.get(p.getHeuristicId()), current_cost);
                dist.set(p.getHeuristicId(), current_cost);
            }
        } else {
            dist.set(p.getHeuristicId(), current_cost);
            open_list.set(p.getHeuristicId(), true);
            FibonacciHeapNode node = new FibonacciHeapNode(p);
            q.insert(node, current_cost);
            cond_to_entry.put(p.getHeuristicId(), node);
            numberOfReachableConditions++;
        }
    }

    private void generate_linear_programs (Collection<GroundAction> actions, PDDLState s_0) throws IloException {
        lps = new HashMap();
        for (Condition c : conditionUniverse) {
            LpInterface lp = null;
            if (cplex) {
//                System.out.println("DEBUG: Using CPLEX");
                lp = new cplex_interface(problem,(ComplexCondition) c, this.gC);
            } else {
//                System.out.println("DEBUG: Using OJALGO");

                lp = new ojalgo_interface(problem,(ComplexCondition) c, this.gC);
            }
            lp.additive_h = this.additive_h;
            lp.initialize(actions, s_0);
            lps.put(c.getHeuristicId(), lp);
        }
    }

    private void update_mapping (Condition preconditions, GroundAction a) {
        if (this.cond_to_actions.get(preconditions.getHeuristicId()) == null) {
            LinkedHashSet actions = new LinkedHashSet();
            actions.add(a);
            this.cond_to_actions.put(preconditions.getHeuristicId(), actions);
        } else {
            Collection actions = this.cond_to_actions.get(preconditions.getHeuristicId());
            actions.add(a);
            this.cond_to_actions.put(preconditions.getHeuristicId(), actions);

        }
    }

    protected void simplify_actions (PDDLState init) {
        for (GroundAction gr : this.A) {
            try {
                if (gr.getNumericEffects() != null && !gr.getNumericEffects().sons.isEmpty()) {
                    int number_numericEffects = gr.getNumericEffects().sons.size();
                    for (Iterator it = gr.getNumericEffects().sons.iterator(); it.hasNext(); ) {
                        NumEffect neff = (NumEffect) it.next();
                        if (neff.getOperator().equals("assign")) {
                            ExtendedNormExpression right = (ExtendedNormExpression) neff.getRight();
                            try {
                                if (right.isNumber() && neff.getFluentAffected().eval(init) != Double.NaN && (number_numericEffects == 1 || risky)) {//constant effect
                                    //Utils.dbg_print(3,neff.toString());
//                            if (number_numericEffects == 1) {
                                    neff.setOperator("increase");
                                    neff.setRight(new BinaryOp(neff.getRight(), "-", neff.getFluentAffected(), true).normalize());
                                    neff.setPseudo_num_effect(true);
//                            }
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(h1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }
                gr.normalize();
            } catch (Exception ex) {
                Logger.getLogger(h1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.G = (ComplexCondition) this.G.normalize();
        this.Gc = this.gC.normalize();
    }
}
