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
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.heuristics.Aibr;
import com.hstairs.ppmajal.problem.*;
import ilog.concert.*;
import ilog.cplex.IloCplex;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;
import static java.util.Collections.nCopies;

/**
 * @author enrico
 */
public class hlm extends h1 {

    public ArrayList<Integer> dplus;//this is the minimum number of actions needed to weakAchiever a given condition
    //    public ArrayList<Set<repetition_landmark>> reachable_poss_achievers;
    public boolean lp_cost_partinioning;
    public boolean red_constraints = false;
    public boolean smart_intersection = false;
    public boolean mip;
    public boolean debug_landmarks_counting = false;
    private ArrayList<Float> cond_dist;
    //    private IloCplex lp;
    private IloCplex lp_global;
    private IloLinearNumExpr objective_function;
    private ArrayList<IloNumVar> action_to_variable;
    private ArrayList<IloRange> condition_to_cplex_constraint;
    private ArrayList<Set<GroundAction>> reach_achievers;
    private HashMap<Integer, Boolean> has_state_dependent_achievers;
    private boolean needs_checking_state_dependent_constraints;

    public hlm (EPddlProblem problem) {
        super(problem);

    }


    @Override
    public Float setup (State gs) {

        this.additive_h = false;

        super.dataStructureConstruction();
        Float reachabilityComputeEstimate = reachabilityComputeEstimate(gs);
        
        needs_checking_state_dependent_constraints = false;

        if (smart_intersection) {
            smart_intersection = check_if_smark_intersection_needed();
        }
        System.out.println("Smart Metric Intersection: " + smart_intersection);
        init_lp((PDDLState) gs);
        
        sat_test_within_cost = false; //don't need to recheck precondition sat for each state. It is done in the beginning for every possible condition
        out.println("Hard Conditions: " + this.numberOfComplexConditions);
        out.println("Simple Conditions: " + (this.conditionUniverse.size() - this.numberOfComplexConditions));

//        redo construction of integer to avoid spurious actions
//        build_integer_representation();
//        identify_complex_conditions( A);
//        this.generate_link_precondition_action();
//        try {
//            generateAchievers();
//        } catch (Exception ex) {
//            Logger.getLogger(Uniform_cost_search_H1.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return reachabilityComputeEstimate;
    }

    @Override
    public Float computeEstimate (State gs) {
        PDDLState s = (PDDLState) gs;
        if (s.satisfy(G)) {
            return 0f;
        }
        Stack<GroundAction> a_plus = new Stack();//actions executable. Progressively updated
        ArrayList<Set<Condition>> lm = new ArrayList<>(nCopies(conditionUniverse.size() + 1, null));//mapping between condition and landmarks
        ArrayList<Boolean> never_active = new ArrayList<>(nCopies(A.size() + 1, true));//mapping between action and boolean. True if action has not been activated yet
        // HashMap<GroundAction, IloNumVar> action_to_variable = new HashMap();//mapping between action representation and integer variable in cplex
        reach_achievers = new ArrayList<>(nCopies(conditionUniverse.size() + 1, null));

        cond_dist = new ArrayList<>(nCopies(conditionUniverse.size() + 1, Float.MAX_VALUE));//keep track of conditions that have been reachead yet
        ArrayList<Float> target_value = new ArrayList<>(nCopies(conditionUniverse.size() + 1, 0f));//keep track of conditions that have been reachead yet
        try {
            for (Condition c : conditionUniverse) {//update with a value of 0 to say that condition is sat in init state
                IloRange ilo = condition_to_cplex_constraint.get(c.getHeuristicId());
                //Initially, all the conditions are assumed achieved. I am going to change only the ones that really need to be satisfied
                if (ilo != null) {
                    ilo.setLB(0f);
                }
                //System.out.println(c);
                if (c.isSatisfied(s)) {
                    cond_dist.set(c.getHeuristicId(), 0f);
                } else if (c instanceof Predicate) {
                    target_value.set(c.getHeuristicId(), 1f);
                } else if (c instanceof NotCond) {
                    target_value.set(c.getHeuristicId(), 1f);
                } else {
                    Comparison comp = (Comparison) c;
                    double number = comp.getLeft().eval(s);
                    if (number == Double.NaN) {
                    } else {
                        double t = comp.getLeft().eval(s);
                        target_value.set(c.getHeuristicId(), (float)-t);
                    }

                }
                lm.set(c.getHeuristicId(), null);//this condition has no landmark yet. This structure is updated on the way
                //reachable_poss_achievers.set(c.getCounter(), new LinkedHashSet());//this is a mapping between condition and its possible (reachable) achievers
            }
            //Iterator it = this.A.iterator();
            for (GroundAction gr : this.A) {//see which actions are executable in the current state
                IloNumVar var = this.action_to_variable.get(gr.getId());
                if (var == null) {
                    never_active.set(gr.getId(), null);
                    //System.out.println("Useless Action detected");
                    continue;
                }
                var.setUB(0f); //Initially no action is useful;
                if (this.check_conditions(gr)) {
                    a_plus.add(gr);//add such an action
                    never_active.set(gr.getId(), false);
                    if (!this.reachable.contains(gr)) {
                        this.reachable.add(gr);
                    }
                }
            }
            while (!a_plus.isEmpty()) {//keep going till no action is in the list. Look that here actions can be re-added
                GroundAction gr = a_plus.pop();
                if (never_active.get(gr.getId()) == null) {
                    continue;
                }
                update_actions_conditions(s, gr, a_plus, never_active, lm);//this procedure updates
                //all the conditions that can be reached by using action gr. 
                //This also changes the set a_plus whenever some new action becomes active becasue of gr
            }

            if (this.reachabilityRun) {
                A = reachable;
            }

            int goal_not_true_in_init = 0;
            Set<Condition> goal_landmark = new LinkedHashSet();
            for (Condition c : (Collection<Condition>) this.G.sons) {
                Float distance = cond_dist.get(c.getHeuristicId());
                if (distance == Float.MAX_VALUE) {
                    return Float.MAX_VALUE;
                } else if (distance != 0f) {
                    goal_not_true_in_init++;
                    goal_landmark.addAll(lm.get(c.getHeuristicId()));
                }
            }
            if (this.reachabilityRun) {
                System.out.println("Landmarks:" + goal_landmark.size());
                System.out.println("Not Trivial Landmarks:" + (goal_landmark.size() - goal_not_true_in_init));

            }

            if (lp_cost_partinioning) {
                int lm_considered = 0;
                for (Condition c : goal_landmark) {
                    if (cond_dist.get(c.getHeuristicId()) != 0f) {
                        lm_considered++;
                        //                        System.out.println("DEBUG: condition:"+c);
                        IloRange ilo = condition_to_cplex_constraint.get(c.getHeuristicId());
                        if (ilo == null) {
                            return Float.MAX_VALUE;
                        }
                        ilo.setLB(target_value.get(c.getHeuristicId()));
                        Set<GroundAction> set = reach_achievers.get(c.getHeuristicId());
                        boolean revise_terms = false;

                        //the following ask whether the condition depends on some action whose positiveness of the effects depend on the current state
                        //this happens for the special case of pseudo increase effects that are simulating the assignment operation
                        if (this.needs_checking_state_dependent_constraints) {
                            //                            System.out.println("DEBUG: There is need to check...");
                            if (this.has_state_dependent_achievers.get(c.getHeuristicId())) {
                                //                                System.out.println("this one is one of them...");
                                revise_terms = true;
                            }
                        }
                        for (GroundAction gr : set) {
                            free_variable_modify_contribution_if_needed(s, c, revise_terms, gr);

                        }
                    }
                }
//                System.out.println("LM to Satisfy:"+lm_considered);
                if (this.debug_landmarks_counting) {
                    System.exit(-1);
                }
                if (debug == 5) {
                    System.out.println(lp_global);
                }
                if (this.lp_global.solve()) {
//                        System.out.println(lp_global);
                    return (float) this.lp_global.getObjValue();

                } else {
                    return Float.MAX_VALUE;
                }

            } else {
                float estimate = 0f;
                for (Condition c : goal_landmark) {//these are the landmarks for the planning task
                    estimate += dplus.get(c.getHeuristicId());
                }
                return estimate;

            }
        } catch (IloException ex) {
            Logger.getLogger(hlm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Float.MAX_VALUE;
    }

    private boolean update_lm (Condition p, GroundAction gr, ArrayList<Set<Condition>> lm) {

        Set<Condition> previous = lm.get(p.getHeuristicId());

        if (previous == null) {
            if (debug > 10) {
                System.out.println("First LM for:" + p);
            }
            previous = new LinkedHashSet();
            for (Condition c : (Set<Condition>) gr.getPreconditions().sons) {
                if (this.cond_dist.get(c.getHeuristicId()) != 0f) {
                    previous.addAll(lm.get(c.getHeuristicId()));
                    previous.add(c);
                }
            }
            //experimental
            previous.add(p);//adding itself This is now done at the end, so as to minimise number of intersection operation
            lm.set(p.getHeuristicId(), previous);
            return true;
        } else {
            if (debug > 10) {
                System.out.println("Revise LM for:" + p);
            }
//            if (p.toString().contains("poured")){
//                System.out.println("Condition:"+p);
//            }

            int previous_size = previous.size();
            if (previous_size == 0) {
                return false;
            }

            if (smart_intersection) {
                Set<Condition> temp = new LinkedHashSet();
                for (Condition c : (Set<Condition>) gr.getPreconditions().sons) {
                    if (this.cond_dist.get(c.getHeuristicId()) != 0f) {
                        temp.addAll(lm.get(c.getHeuristicId()));
                        temp.add(c);//the lm as implemented in this code does not contain itself
                    }
                }

                previous = metric_sensitive_intersection(previous, temp);
//                previous.retainAll(temp);

            } else {
                if (debug > 10) {
                    System.out.println("Previous" + previous);
                }
                Iterator<Condition> it = previous.iterator();
                while (it.hasNext()) {
                    boolean found = false;
                    Condition prev = it.next();
                    for (Condition c : (Set<Condition>) gr.getPreconditions().sons) {
                        if (this.cond_dist.get(c.getHeuristicId()) != 0f) {
                            if (prev.getHeuristicId() == c.getHeuristicId()) {
                                found = true;
                                break;
                            }
                            for (Condition c1 : lm.get(c.getHeuristicId())) {
                                if (prev.getHeuristicId() == c1.getHeuristicId()) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                break;
                            }
                        }
                    }
                    if (!found) {
                        it.remove();
                    }
                }

                if (debug > 10) {
                    System.out.println("after" + previous);
                }
            }
            //experimental
            previous.add(p);//adding itself again (the intersection may have removed this...see above for reasons
            lm.set(p.getHeuristicId(), previous);
            return previous_size != previous.size();
        }

    }

    private void update_actions_conditions (PDDLState s_0, GroundAction gr, Stack<GroundAction> a_plus, ArrayList<Boolean> never_active, ArrayList<Set<Condition>> lm) {
        for (Condition comp : this.achieve[gr.getId()]) {//This is the set of all predicates reachable because of gr
            // Float rep_needed = 1f;
            if (cond_dist.get(comp.getHeuristicId()) != 0f) {//if this isn't in the init state yet
//                if (lm.get(comp.getCounter())!= null && lm.get(comp.getCounter()).isEmpty()){
////                    System.out.println("happen!");
//                    continue;
//                }
                cond_dist.set(comp.getHeuristicId(), 1f);//update distance. Meant only to keep track of whether condition reachead or not, and "how"
                update_action_condition(gr, comp, lm, never_active, a_plus);
                //for this specific condition check implications of having it reached.
            }
        }
        for (Condition comp : this.possibleAchievers[gr.getId()]) {
            if (cond_dist.get(comp.getHeuristicId()) != 0f) {
//                if (lm.get(comp.getCounter())!= null && lm.get(comp.getCounter()).isEmpty()){
//                    continue;
//                }
                Float contribution = gr.getContribution(s_0, (Comparison) comp);
                if (contribution != Float.MAX_VALUE) {
                    cond_dist.set(comp.getHeuristicId(), 1f);
                    update_action_condition(gr, comp, lm, never_active, a_plus);
                }
            }
        }
    }

    private void update_action_condition (GroundAction gr, Condition comp, ArrayList<Set<Condition>> lm, ArrayList<Boolean> never_active, Stack<GroundAction> a_plus) {
        boolean changed = update_lm(comp, gr, lm);//update set of landmarks for this condition.
        //this procedure shrink landmarks for condition comp using action gr
//        System.out.println(changed);
        Collection<GroundAction> set = conditionToAction[comp.getHeuristicId()];
        //this mapping contains action that need to be triggered becasue of condition comp
        if (set != null){
            for (GroundAction gr2 : set) {
                if (gr2.getId() == gr.getId()) {//avoids self-loop. Thanks god I have integer mapping here.
                    continue;
                }
                if (never_active.get(gr2.getId()) == null)//this is for useless actions
                {
                    continue;
                }

    //            System.out.println(never_active);
    //            if (!A.contains(gr2))
    //                continue;
                if (never_active.get(gr2.getId())) {//if this action has never been used before..
                    if (check_conditions(gr2)) {//are conditions all reached?
    //                    if (!a_plus.contains(gr2))
                        a_plus.push(gr2);//push in the set of actions to consider. 
                        //Need to understand whether is worth to do check on the list to see if action already is there.
                        never_active.set(gr2.getId(), false);//now is not never active anymore (just pushed in the a_plus)_
                        if (this.reachabilityRun) {
                            this.reachable.add(gr2);
                        }
                    }
                } else if (changed) {//if the lm of the condition has changed,
                    //we need to reconsider all the possible paths using this condition. Meaning all the possible actions
    //                if (!a_plus.contains(gr2)) 
    //                  if (!a_plus.contains(gr2))
                    a_plus.push(gr2);//see above for the eventual checking
    //                }
                }
            }
        }
        Set<GroundAction> set2 = this.reach_achievers.get(comp.getHeuristicId());
        if (set2 == null) {
            set2 = new HashSet();
        }
        set2.add(gr);
        this.reach_achievers.set(comp.getHeuristicId(), set2);

    }

    private boolean check_conditions (GroundAction gr2) {

        for (Condition c : (Collection<Condition>) gr2.getPreconditions().sons) {
            if (cond_dist.get(c.getHeuristicId()) == Float.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

//    private void generate_link_precondition_action ( ) {
//        condition_to_action = new ArrayList<>(nCopies(all_conditions.size() + 1, null));
//        for (Condition c : all_conditions) {
//            LinkedHashSet<GroundAction> set = new LinkedHashSet();
//            for (GroundAction gr : A) {
//                if (gr.getPreconditions().sons.contains(c)) {
//                    set.add(gr);
//                }
//            }
//            condition_to_action.set(c.getHeuristicId(), set);
//
//        }
//    }

    //TO-DO do the sensitive intersection to the metric
    private Set<Condition> metric_sensitive_intersection (Set<Condition> previous, Set<Condition> temp) {
        Set<Condition> newset = new LinkedHashSet();
        if (debug > 0) {
            System.out.println("Previous:" + previous);
            System.out.println("Intersecting with:" + temp);
        }
        for (Condition c : temp) {
            if (c instanceof Predicate) {
                if (previous.contains(c)) {
                    newset.add(c);
                }
            } else if (c instanceof Comparison) {
                for (Condition c1 : previous) {
                    if (c1 instanceof Comparison) {
                        Comparison comp_c = (Comparison) c;
                        Comparison comp_c1 = (Comparison) c1;
                        if (comp_c.dominate(comp_c1)) {
                            newset.add(comp_c1);
                        } else if (comp_c1.dominate(comp_c)) {
                            newset.add(comp_c);
                        }
                    }

                }
            }
        }
        if (debug > 0) {
            System.out.println("after:" + newset);
        }
        return newset;
    }

    private String print_ordering (ArrayList<Set<Condition>> lm, Condition c) {

        if (lm.get(c.getHeuristicId()) == null || lm.get(c.getHeuristicId()).isEmpty()) {
            return "(" + c.toString() + "," + c.getHeuristicId() + ")";
        } else {
            String temp = "";
            for (Condition c1 : lm.get(c.getHeuristicId())) {
                temp += "(" + c.toString() + "," + c.getHeuristicId() + ")" + "<-" + print_ordering(lm, c1) + "\n";
            }
            return temp;
        }

    }

    private void init_lp (PDDLState s_0) {
        try {
            lp_global = new IloCplex();
            lp_global.setOut(null);
            objective_function = lp_global.linearNumExpr();
            action_to_variable = new ArrayList<>(nCopies(A.size() + 1, null));
            condition_to_cplex_constraint = new ArrayList<>(nCopies(conditionUniverse.size() + 1, null));
            has_state_dependent_achievers = new HashMap();
            for (Condition c : conditionUniverse) {
                has_state_dependent_achievers.put(c.getHeuristicId(), false);
//                System.out.println("Condition under analysis" + c);
                IloLinearNumExpr expr = lp_global.linearNumExpr();
                Collection<GroundAction> set = null;
                if (c instanceof Predicate) {
                    set = this.invertedAchievers[c.getHeuristicId()];
                } else if (c instanceof Comparison) {
                    set = this.invertedPossibleAchievers[c.getHeuristicId()];
                }
                if (set != null) {
//                    System.out.println("Possible achievers...");
                    for (GroundAction gr : set) {

                        IloNumVar action;
//                        gr.set_unit_cost(s_0);
                        Float action_cost = this.getTransitionCost(s_0,gr,0f,ignoreCostInHeuristic);
                        if (action_cost.isNaN()) {
//                            System.out.println("Action: " + gr);
                            continue;
                        }
                        if (action_to_variable.get(gr.getId()) != null) {
                            action = action_to_variable.get(gr.getId());
                        } else {
                            if (mip) {
                                action = lp_global.numVar(0.0, Integer.MAX_VALUE, IloNumVarType.Int);
                            } else {
                                action = lp_global.numVar(0.0, 0.0, IloNumVarType.Float);
                            }
                            action_to_variable.set(gr.getId(), action);
                            objective_function.addTerm(action, action_cost);
                        }
//                        System.out.println("Condition under evaluation:" + c);

                        //Conservative: if at least one effect is pseudo numeric effect, the
                        //constraint has to be built on the fly: if this condition becomes a lm
                        if (gr.has_pseudo_numeric_effect_on(c)) {
                            has_state_dependent_achievers.put(c.getHeuristicId(), true);
                            needs_checking_state_dependent_constraints = true;
                        }
                        expr.addTerm(action, gr.getStaticContribution(s_0, c));
//                        System.out.println("ACtion Contribution:" + gr.getContribution(s_0, c));
                    }
                }
                double trg_value = 0f;
                if (c.isSatisfied(s_0)) {

                } else if (c instanceof Predicate) {
                    trg_value = 1f;
                } else if (c instanceof NotCond) {//assumption that only propositional predicates can be negated 
                    trg_value = 1f;
                } else {
                    Comparison comp = (Comparison) c;
                    double number = comp.getLeft().eval(s_0);
                    if (number == Double.NaN) {
                        System.out.println("Condition that cannot be evaluated are not supported:" + c);
                        System.exit(-1);
                    } else {
                        double t = comp.getLeft().eval(s_0);
                        trg_value = -1d * t;
                    }

                }
                if (set != null) {
                    IloRange ilo = lp_global.addGe(expr, trg_value);
                    if (ilo == null) {
                        System.out.println("something seriously wrong");
                    }
                    //System.out.println(c + " " + " Mapped into " + ilo);
                    condition_to_cplex_constraint.set(c.getHeuristicId(), ilo);
                }
            }
            lp_global.addMinimize(objective_function);
//            System.out.println(lp_global);
        } catch (IloException ex) {
            Logger.getLogger(hlm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void free_variable_modify_contribution_if_needed (PDDLState s_0, Condition c, boolean revise_terms, GroundAction gr) throws IloException {

        IloNumVar action_var = this.action_to_variable.get(gr.getId());
        action_var.setUB(Float.MAX_VALUE);//add only useful actions
        if (revise_terms) {
            IloRange ilo2 = condition_to_cplex_constraint.get(c.getHeuristicId());
            IloLinearNumExpr expr = (IloLinearNumExpr) ilo2.getExpr();
            IloLinearNumExprIterator it = expr.linearIterator();
            while (it.hasNext()) {
                IloNumVar var = it.nextNumVar();
                if (var.equals(action_var)) {
                    it.setValue(gr.getContribution(s_0, (Comparison) c));
                }
            }
            ilo2.setExpr(expr);
        }
    }

    private boolean check_if_smark_intersection_needed ( ) {
        for (int i = 0; i < conditionUniverse.toArray().length; i++) {
            for (int j = i + 1; j < conditionUniverse.toArray().length; j++) {
                Condition c1 = (Condition) conditionUniverse.toArray()[i];
                Condition c2 = (Condition) conditionUniverse.toArray()[j];
                if ((c1 instanceof Comparison) && (c2 instanceof Comparison)) {
                    Comparison comp_c1 = (Comparison) c1;
                    Comparison comp_c2 = (Comparison) c2;
                    if (comp_c1.getInvolvedFluents().equals(comp_c2.getInvolvedFluents())) {
//                        System.out.println(comp_c1+" "+comp_c2);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    private Float reachabilityComputeEstimate(State gs) {
        reachabilityRun = true;
        float ret = super.computeEstimate(gs);
        reachabilityRun = false;
        sat_test_within_cost = false; //don't need to recheck precondition sat for each state. It is done in the beginning for every possible condition
        out.println("Hard Conditions: " + this.numberOfComplexConditions);
        out.println("Simple Conditions: " + (this.conditionUniverse.size() - this.numberOfComplexConditions));
        return ret;
    }


    
    

}
