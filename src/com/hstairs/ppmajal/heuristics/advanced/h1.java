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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in233 the editor.
 */
package com.hstairs.ppmajal.heuristics.advanced;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.heuristics.Aibr;
import com.hstairs.ppmajal.heuristics.Heuristic;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.GroundEvent;
import com.hstairs.ppmajal.problem.GroundProcess;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import org.jgrapht.util.FibonacciHeap;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;
import static java.util.Collections.nCopies;
import org.apache.commons.lang3.tuple.Pair;
import org.jgrapht.util.FibonacciHeapNode;

/**
 * @author enrico
 */
public class h1 extends Heuristic {

    public boolean useRedundantConstraints = false;
    public boolean extractRelaxedPlan = false;
    public boolean conservativehmax = true;
    public boolean ibrDisabled = false;
    public boolean integer_actions = false;
    public boolean only_mutual_exclusion_processes = false;

    protected boolean reachabilityRun;
    protected Collection<Terminal>[] achieve;
    protected Collection<GroundAction>[] invertedAchievers;
    protected Collection<Comparison>[] possibleAchievers;
    protected Collection<GroundAction>[] invertedPossibleAchievers;
    protected Collection<GroundAction>[] precondition_mapping;
    protected GroundAction pseudoGoal;
    protected AndCond[] extraActionPrecondition;
    protected Collection<GroundAction>[] conditionToAction;
    private float[] cost;
    private float[] actionHCost;
    private GroundAction[] establishedAchiever;
    private AchieverSet[] achieversSet;
    private Set<GroundAction>[] allAchievers;
    private boolean[] closed;
    private float minimumActionCost;
    private List<Pair<GroundAction, Float>> relaxedPlan;
    private float[] establishedLocalCost;
    private Object2FloatMap action_comp_number_execution;
    private GroundAction[] extraTrigger;
    private GroundAction[] heuristicActionsToProblemActions;
    private HashMap redundant_constraints;
    
    protected ArrayList<Boolean> is_complex;
    protected HashMap<Condition, Boolean> new_condition;
    protected Collection<Comparison> complex_condition_set;
    private GroundAction[] fromIdToAction;
    private Set<GroundAction> masterProblemReachableTransitions;
    private HashMap<Pair<Integer, Integer>, Comparison> directAssignmentConditionHandle;
    public boolean aggressiveRelaxedPlan;
    private final int totActions;

    public h1(EPddlProblem problem) {
        this(problem, false);
    }

    public h1(EPddlProblem problem, boolean ignoreCostHeuristic) {
        super(problem.getGoals(), problem.actions, problem.getProcessesSet(), problem.getEventsSet(), null, problem);
        this.problem = problem;
        this.ignoreCostInHeuristic = ignoreCostHeuristic;
        this.totActions = problem.getTotActions()+1;
    }

    protected void dataStructureConstruction(){
    
        if (useRedundantConstraints) {
            this.addRedundantConstraints();
        }
        if (additive_h) {
            minimumActionCost = 0.001f;
        } else {
            minimumActionCost = 0.0f;
        }
        forceUniquenessInConditionsAndInternalActions();
        generateAchieversDataStructures();
        generateConditionToActionMap();
        identifyComplexConditions(A);
    
    }
    
    private Float reachabilityComputeEstimate(State s){
        reachabilityRun = true;
        float ret = computeEstimate(s);
        reachabilityRun = false;
        sat_test_within_cost = false; //don't need to recheck precondition sat for each state. It is done in the beginning for every possible condition
        out.println("Hard Conditions: " + this.numberOfComplexConditions);
        out.println("Simple Conditions: " + (this.conditionUniverse.size() - this.numberOfComplexConditions));
        return ret;
    }
    
    @Override
    public Float setup (State s) {
        this.dataStructureConstruction();
        return this.reachabilityComputeEstimate(s);
    }

    @Override
    public void forceUniquenessInConditionsAndInternalActions ( ) {
        ArrayList<GroundAction> internalActions = new ArrayList();
        heuristicActionsToProblemActions = new GroundAction[totActions+1];
        fromIdToAction = new GroundAction[totActions+1];
        pseudoGoal = new GroundAction("goal",internalActions.size());
        pseudoGoal.setPreconditions(G);
        internalActions.add(pseudoGoal);
        fromIdToAction[pseudoGoal.getId()] = pseudoGoal;
        
        conditionUniverse = new ArrayList<>();
        fromConditionStringToUniqueInteger = new HashMap();
        int conditionsCounter = 0;
        for (GroundAction b : A) {
//            if (!internalActions.contains(b)){
                GroundAction a = new GroundAction(b,internalActions.size());
                internalActions.add(a);
                heuristicActionsToProblemActions[a.getId()] = b;
                fromIdToAction[a.getId()] = a;
                if (a.getPreconditions() != null) {
                    for (Condition c_1 : a.getPreconditions().getTerminalConditions()) {
                        conditionsCounter = establishHeuristicConditionId(c_1, conditionsCounter);
                    }
                }
//            }
        }
        A = internalActions;
        
        for (GroundAction a : A) {
            if (a.getAddList() != null && a.getAddList().sons != null) {
                for (Condition c_1 : (Set<Condition>) a.getAddList().sons) {
                    conditionsCounter = establishHeuristicConditionId(c_1, conditionsCounter);
                }
            }
        }

        for (Condition c_1 : G.getTerminalConditions()) {
            conditionsCounter = establishHeuristicConditionId(c_1, conditionsCounter);
        }
        numberOfAtoms = conditionsCounter;//index of the last atom
    }

    
    public void light_setup (PDDLState s) {
        this.dataStructureConstruction();
    }

    protected void addTriggerCondition (Predicate dummyPredicate, GroundAction pseudoGoal) {
        getExtraTrigger()[dummyPredicate.getHeuristicId()] = pseudoGoal;
    }

    @Override
    public Float computeEstimate (State gs) {
        PDDLState s = (PDDLState) gs;

        if (this.reachabilityRun) {
            this.reachable = new ArrayList();
        }
        if (s.satisfy(pseudoGoal.getPreconditions()) && extraActionPrecondition[pseudoGoal.getId()] == null) {
//            System.out.println("Goal satisfied??");
            return 0f;
        }

        if (this.extractRelaxedPlan || this.helpful_actions_computation) {
            establishedAchiever = new GroundAction[conditionUniverse.size() + 1];
            establishedLocalCost = new float[conditionUniverse.size() + 1];
            Arrays.fill(establishedLocalCost, Float.MAX_VALUE);
            achieversSet = new AchieverSet[this.totActions];
        }
        allAchievers = new ReferenceLinkedOpenHashSet[conditionUniverse.size() + 1];
        float estimate = Float.MAX_VALUE;
        FibonacciHeap<GroundAction> a_plus = new FibonacciHeap();
        FibonacciHeapNode[] actionToFibNode = new FibonacciHeapNode[this.totActions];
//        IntPriorityQueue a_plus = new it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue(new GroundActionComparator());
        cost = new float[conditionUniverse.size() + 1];
        Arrays.fill(cost, Float.MAX_VALUE);
        action_comp_number_execution = new Object2FloatOpenHashMap();
        for (int i = 0; i < conditionUniverse.size(); i++) {
            final Condition c = (Condition) ((ArrayList) conditionUniverse).get(i);
            if (s.satisfy(c)) {
                cost[c.getHeuristicId()] = 0f;
            } 
        }
        actionHCost = new float[totActions];
        Arrays.fill(actionHCost, Float.MAX_VALUE);
        closed = new boolean[totActions];
        Arrays.fill(closed, false);
        for (int i = 0 ; i < this.A.size(); i++){
            GroundAction gr = ((ArrayList<GroundAction>)this.A).get(i);
            if (this.estimateCost(gr.getPreconditions()) != Float.MAX_VALUE) {
                Condition extraPrecondition = extraActionPrecondition[gr.getId()];
                if ((extraPrecondition == null) || (this.estimateCost(extraPrecondition) != Float.MAX_VALUE)) {
                    FibonacciHeapNode node = new FibonacciHeapNode(gr);
                    actionToFibNode[gr.getId()] = node;
                    actionHCost[gr.getId()] = 0f;                    
                    a_plus.insert(node,0f);//add such an action
                    if (this.reachabilityRun) {
                        if (!this.reachable.contains(gr)){
                            this.reachable.add(gr);
                        }
                    }
                }
            }
        }
        while (!a_plus.isEmpty()) {//keep going till no action is in the list.
//            final GroundAction gr = fromIdToAction[a_plus.dequeueInt()];
            final GroundAction gr = a_plus.removeMin().getData();
            closed[gr.getId()] = true;
            if (gr == pseudoGoal) {
                estimate = actionHCost[gr.getId()];
                if (!this.reachabilityRun && (this.extractRelaxedPlan || this.additive_h || !conservativehmax)) {
//                    System.out.println(this.additive_h);
//                    System.out.println(this.conservativehmax);
                    extract_helpful_actions_or_relaxed_plan();
                    if (estimate == 0f) {
                        return 0.1f;
                    }
                    if (this.extractRelaxedPlan) {
                        return this.extract_tot_cost(relaxedPlan);
                    }
                    return estimate;
                }
            }
            update_reachable_conditions_actions(s, gr, a_plus, actionToFibNode);//this procedure updates
            //all the conditions that can be reached by using action gr.
            //This also changes the set a_plus whenever some new action becomes active becasue of gr
        }

//        System.out.println("Estimate: "+estimate);
        if (reachabilityRun || why_not_active) {
            if (estimate == Float.MAX_VALUE) {
                System.out.println("Goal unreachable from init state. Please revise model or instance file");
                findUnsatReasons(this.pseudoGoal);
                System.out.println("Actions Applicable till fix point:");
                for (GroundAction gr : reachable) {
                    System.out.println(gr.toEcoString());
                }
            }
        }

        if (this.reachabilityRun) {
            A = reachable;
        }

        if (estimate == Float.MAX_VALUE) {
            return estimate;
        }

        extract_helpful_actions_or_relaxed_plan();
        if (this.extractRelaxedPlan) {
            return this.extract_tot_cost(relaxedPlan);
        }

        //System.out.println("Conservative Setting");
        return estimate;
    }


    private void update_reachable_conditions_actions (PDDLState s_0, GroundAction gr, FibonacciHeap<GroundAction> a_plus, FibonacciHeapNode[] actionToFibNode) {
        float c_a = Math.max(this.getTransitionCost(s_0,gr,0f,ignoreCostInHeuristic), minimumActionCost);
        for (final Condition comp : this.predicatesProduction(gr)) {//This is the set of all predicates reachable because of gr
            if (cost[comp.getHeuristicId()] != 0f) {

                float cond_dist_comp = c_a + this.actionHCost[gr.getId()];
                if (cond_dist_comp != Float.MAX_VALUE) {
                    if (this.extractRelaxedPlan || this.helpful_actions_computation) {
                        update_achiever(comp, gr);
                    }
                    if (cond_dist_comp < cost[comp.getHeuristicId()]) {//if this isn't in the init state yet
                        cost[comp.getHeuristicId()] = cond_dist_comp;//update distance. Meant only to keep track of whether condition reachead or not, and "how"
                        if (this.extractRelaxedPlan || this.helpful_actions_computation) {
                            establishedAchiever[comp.getHeuristicId()] = gr;
                            establishedLocalCost[comp.getHeuristicId()] = c_a;
                        }
                        updateReachableActions(gr, comp, a_plus, actionToFibNode);

                    }
                }
            }
        }
        for (final Condition comp : this.numericProduction(gr)) {
//            Utils.dbg_print(debugLevel - 10, "Condition under analysis:" + comp + "\n");
            if (!this.is_complex.get(comp.getHeuristicId())) {
                float current_distance = cost[comp.getHeuristicId()];
                if (current_distance != 0f) {
                    float rep_needed = -1;//this.action_comp_number_execution.getOrDefault(new Pair(gr.getId(), comp.getHeuristicId()), -1f);
                    if (rep_needed == -1) {
                        if (gr.infinite_constant_effect) {
                            rep_needed = 1f * c_a;
                        }
                        if (this.invertedPossibleAchievers[comp.getHeuristicId()].size() == 1 || this.integer_actions) {
                            Comparison get = directAssignmentConditionHandle.get(Pair.of(gr.getId(),comp.getHeuristicId()));
                            if (get != null){
                                if (s_0.satisfy(get)){
                                    rep_needed = 1f * c_a;
                                }
                            }else{
                                rep_needed = gr.getNumberOfExecutionInt(s_0, (Comparison) comp) * c_a;
                            }
                        } else {
                            rep_needed = gr.getNumberOfExecution(s_0, (Comparison) comp) * c_a;
                        }
                        //this.action_comp_number_execution.put(new Pair(gr.getId(), comp.getHeuristicId()), rep_needed);
                    } else {
//                        System.out.println("Cache funziona");
                    }

                    if (rep_needed != Float.MAX_VALUE) {
                        if (this.extractRelaxedPlan || this.helpful_actions_computation || !this.additive_h) {
                            update_achiever(comp, gr);
                        }
                        float new_distance = rep_needed;
                        if (this.additive_h) {
                            new_distance += this.actionHCost[gr.getId()];
                        } else {

                            //rep_needed += this.action_dist.get(gr.counter);
                            new_distance += min_over_possible_achievers(comp);
                            /*
                            This preserves admissibility.
                             */
                        }

                        if (new_distance < current_distance) {
                            cost[comp.getHeuristicId()] = new_distance;
                            if (this.extractRelaxedPlan || this.helpful_actions_computation) {
                                establishedAchiever[comp.getHeuristicId()] = gr;
                                establishedLocalCost[comp.getHeuristicId()] = rep_needed;
                            }
                            updateReachableActions(gr, comp, a_plus, actionToFibNode);
                        }

                    }

                }
            } else {
//                System.out.println("Complex condition reasoning");
                float current_distance = cost[comp.getHeuristicId()];
                if (current_distance == 0f) {
                    continue;
                }
                float new_distance = Float.MAX_VALUE;
                if (!this.additive_h || ibrDisabled ) {
                    new_distance = actionHCost[gr.getId()] + c_a;//this is a very conservative measure.
                } else {
                    //This can be cached with a map, so that supporters are kept, and only the new ones are added


//                    LinkedHashSet<GroundAction> invertedPossibleAchiever = this.invertedPossibleAchievers[comp.getHeuristicId()];
//                    if (invertedPossibleAchiever.size() == 1){
//                        //Extend here a reasoning for the case where you only have one achiever and this achiever is a constant assignment. Formalize it also would be nice
//                    }


                    AndCond temp = new AndCond();
                    temp.addConditions(comp);

//                    System.out.println("let's see how it goes for"+ temp);
//                    System.out.println("With: "+ reachableActions);
                    //aibr_handle
                    EPddlProblem tempProblem = new EPddlProblem(temp,reachable);
                    Aibr aibr_handle = new Aibr(tempProblem);
                    aibr_handle.set(false, true);

                    aibr_handle.light_setup(s_0, this);

                    if (comp.getHeuristicId() > conditionUniverse.size()) {
                        System.out.println("Condition not intercepted!" + comp + "\n");
                        System.out.println("Identifier:" + comp.getHeuristicId());
                        System.exit(-1);
                    }
                    new_distance = aibr_handle.computeEstimate(s_0);
                }
                if (new_distance != Float.MAX_VALUE) {
                    //new_distance += this.action_dist.get(gr.counter);
                    if (this.extractRelaxedPlan || this.helpful_actions_computation) {
                        update_achiever(comp, gr);
                    }
                    if (new_distance < current_distance) {
                        cost[comp.getHeuristicId()] = new_distance;
                        if (this.extractRelaxedPlan || this.helpful_actions_computation) {
                            establishedAchiever[comp.getHeuristicId()] = gr;
                            establishedLocalCost[comp.getHeuristicId()] = new_distance;//this does not really work!!
                        }
                        updateReachableActions(gr, comp, a_plus, actionToFibNode);
                    }

                }
//                Utils.dbg_print(debugLevel - 10, "(Complex) Action under consideration and number of needed execution:" + gr + " " + current_distance + "\n");

            }
        }

    }


    private void computeAchievers (GroundAction gr) {
        Collection<Comparison> comparisons = new ReferenceLinkedOpenHashSet<>();
        Collection<Terminal> predicatesAchieved = new ReferenceLinkedOpenHashSet<>();
        for (Condition c : this.conditionUniverse) {

            if (precondition_mapping[c.getHeuristicId()] == null) {
                precondition_mapping[c.getHeuristicId()] = new LinkedHashSet();
            }
            LinkedHashSet<GroundAction> action_list = new LinkedHashSet();
            if (c instanceof Comparison) {
//                    System.out.println("Condition under analysis:"+c);
//                    System.out.println("Counter is:"+c.getCounter());
                Comparison comp = (Comparison) c;
                if (comp.involve(gr.getNumericFluentAffected())) {
                    comparisons.add(comp);

                    if (this.is_complex.get(comp.getHeuristicId())) {
                        action_list.add(gr);
                    } else if (gr.is_possible_achiever_of(comp)) {
                        action_list.add(gr);
                    }
                }
                if (this.invertedPossibleAchievers[comp.getHeuristicId()] == null) {
                    this.invertedPossibleAchievers[comp.getHeuristicId()] = action_list;
                } else {
                    Collection<GroundAction> temp = this.invertedPossibleAchievers[comp.getHeuristicId()];
                    temp.addAll(action_list);
                    this.invertedPossibleAchievers[comp.getHeuristicId()] = temp;
                }
            } else if (c instanceof Predicate) {
                Predicate p = (Predicate) c;
                if (gr.weakAchiever(p)) {
                    predicatesAchieved.add(p);
                    action_list.add(gr);
                }
                if (this.invertedAchievers[p.getHeuristicId()] == null) {
                    this.invertedAchievers[p.getHeuristicId()] = action_list;
                } else {
                    Collection<GroundAction> temp = this.invertedAchievers[p.getHeuristicId()];
                    temp.addAll(action_list);
                    this.invertedAchievers[p.getHeuristicId()] = temp;
                }

            } else if (c instanceof NotCond) {
                NotCond c1 = (NotCond) c;
                if (!c1.isTerminal()) {
                    System.out.println("Not formula has to be used as a terminal, and it is not:" + c1);
                    System.exit(-1);
                }
                Predicate p = (Predicate) c1.getSon();
                if (gr.delete(p)) {
                    predicatesAchieved.add(c1);
                    action_list.add(gr);
                }
                if (this.invertedAchievers[c1.getHeuristicId()] == null) {
                    this.invertedAchievers[c1.getHeuristicId()] = action_list;
                } else {
                    Collection<GroundAction> temp = this.invertedAchievers[c1.getHeuristicId()];
                    temp.addAll(action_list);
                    this.invertedAchievers[c1.getHeuristicId()] = temp;
                }

            }
            this.achieve[gr.getId()] = predicatesAchieved;
            this.possibleAchievers[gr.getId()] = comparisons;

        }
    }

    private Iterable<? extends Condition> numericProduction (GroundAction gr) {
        Collection<Comparison> comparisons = this.possibleAchievers[gr.getId()];
        if (comparisons == null) {
            this.computeAchievers(gr);
        }
        return this.possibleAchievers[gr.getId()];
    }

    private Iterable<? extends Condition> predicatesProduction (GroundAction gr) {
        Collection<Terminal> terminals = this.achieve[gr.getId()];
        if (terminals == null) {
            this.computeAchievers(gr);
        }
        return this.achieve[gr.getId()];
    }

    private void updateReachableActions (GroundAction gr, Condition comp, FibonacciHeap<GroundAction> a_plus, FibonacciHeapNode[] action_to_fib_node) {
        //this procedure shrink landmarks for condition comp using action gr
//        System.out.println(changed);
        Collection<GroundAction> triggeredActions = null;
        if (comp.getHeuristicId() < conditionToAction.length) {
            triggeredActions = conditionToAction[comp.getHeuristicId()];
        }
        if (this.extraTrigger != null) {
            GroundAction groundAction = this.extraTrigger[comp.getHeuristicId()];
            if (triggeredActions == null && groundAction != null) {
                triggeredActions = Collections.singleton(groundAction);
            } else if (groundAction != null) {
                triggeredActions.add(groundAction);
            }

        }
        if (triggeredActions == null) {
            return;
        }
        //this mapping contains action that need to be triggered becasue of condition comp
        for (final GroundAction gr2 : triggeredActions) {

            if (closed[gr2.getId()]) {
                if (this.additive_h || !this.conservativehmax || this.extractRelaxedPlan) {
                    continue;
                }
            }
            final float c = checkConditions(gr2);
            if (this.reachabilityRun && (actionHCost[gr2.getId()] == Float.MAX_VALUE && c < Float.MAX_VALUE)) {
                this.reachable.add(gr2);
            }

            if (c < actionHCost[gr2.getId()]) {//are conditions all reached, and is this a better path?
                actionHCost[gr2.getId()] = c;
                if (action_to_fib_node[gr2.getId()] == null) {
                    FibonacciHeapNode n = new FibonacciHeapNode(gr2);
                    a_plus.insert(n, c);//push in the set of actions to consider.
                    action_to_fib_node[gr2.getId()] = n;
                } else if (closed[gr2.getId()]) {
                    a_plus.insert(action_to_fib_node[gr2.getId()], c);   
                    closed[gr2.getId()] = false;
                } else {
                   a_plus.decreaseKey(action_to_fib_node[gr2.getId()], c);//push in the set of actions to consider.
                 }

                //Need to understand whether is worth to do check on the list to see if action already is there.
            }

        }
    }

    float estimateCost (final Condition c) {
        if (c instanceof AndCond) {
            final AndCond and = (AndCond) c;
            if (and.sons == null) {
                return 0f;
            }
            float ret = 0f;
            for (final Condition son : (Collection<Condition>) and.sons) {
                final float estimate = estimateCost(son);
                if (estimate == Float.MAX_VALUE) {
                    return Float.MAX_VALUE;
                }
                if (additive_h){// && !this.extractRelaxedPlan) {
                    ret += estimate;
                } else {
                    ret = (estimate > ret) ? estimate : ret;
                }
            }
            return ret;

        } else if (c instanceof OrCond) {
            final OrCond and = (OrCond) c;
            if (and.sons == null) {
                return 0f;
            }
            float ret = Float.MAX_VALUE;
            for (final Condition son : (Collection<Condition>) and.sons) {
                final float estimate = estimateCost(son);
                if (estimate != Float.MAX_VALUE) {
                    ret = (estimate < ret) ? estimate : ret;
                }
            }
            return ret;
        } else if (c instanceof Predicate) {
            return cost[c.getHeuristicId()];
        } else if (c instanceof NotCond) {
            return cost[c.getHeuristicId()];
        } else if (c instanceof Comparison) {
            return cost[c.getHeuristicId()];
        } else {
            return 0f;
        }
    }

    AchieverSet estimateAchievers (Condition c) {
        AchieverSet s = new AchieverSet();
        if (c instanceof AndCond) {
            AndCond and = (AndCond) c;

            if (and.sons == null) {
                s.setCost(0f);
            } else {
                s.setCost(0f);
                for (Condition son : (Collection<Condition>) and.sons) {
                    AchieverSet s1 = estimateAchievers(son);
                    if (s1.getCost() == Float.MAX_VALUE) {
                        s.setCost(Float.MAX_VALUE);
                        return s;
                    }
                    if (additive_h) {
                        s.setCost(s.getCost() + s1.getCost());
                        s.getActions().addAll(s1.getActions());
                        s.getTargetCond().addAll(s1.getTargetCond());
                    } else {
                        s.setCost(Math.max(s1.getCost(), s.getCost()));
                        if (this.extractRelaxedPlan || this.helpful_actions_computation){
                            s.getActions().addAll(s1.getActions());
                            s.getTargetCond().addAll(s1.getTargetCond());
                        }
                    }
                }
               
            }
        } else if (c instanceof OrCond) {
            OrCond and = (OrCond) c;
            s.setCost(Float.MAX_VALUE);
            if (and.sons == null) {
                s.setCost(0f);
            } else {
                for (Condition c1 : (Collection<Condition>) and.sons) {
                    AchieverSet s1 = estimateAchievers(c1);
                    if (s1.getCost() != Float.MAX_VALUE) {
                        if (s.getCost() > s1.getCost()) {
                            s.setActions(s1.getActions());
                            s.setCost(s1.getCost());
                            s.getTargetCond().addAll(s1.getTargetCond());
                        }
                    }
                }
            }
        } else {
            s.setCost(cost[c.getHeuristicId()]);
            s.getActions().add(establishedAchiever[c.getHeuristicId()]);
            s.getTargetCond().add(c);
        }
        return s;
    }

    private float checkConditions (GroundAction gr2) {
        
        Condition condition = this.extraActionPrecondition[gr2.getId()];
        Condition input = null;
        if (condition != null) {
            input = new AndCond();
            ((AndCond) input).addConditions(condition);
            ((AndCond) input).addConditions(gr2.getPreconditions());
        } else {
            input = gr2.getPreconditions();
        }

        return estimateCost(input);
        

    }

    private void generateConditionToActionMap ( ) {
        conditionToAction = new ArrayList[conditionUniverse.size() + 1];
        for (GroundAction gr : A) {
            Collection<Condition> terminalConditions = gr.getPreconditions().getTerminalConditionsInArray();
            for (Condition c : terminalConditions) {
                Collection<GroundAction> groundActions = conditionToAction[c.getHeuristicId()];
                if (groundActions == null) {
                    groundActions = new ArrayList<>();

                }
                groundActions.add(gr);
                conditionToAction[c.getHeuristicId()] = groundActions;
            }
        }
    }



    private void compute_helpful_actions ( ) {
        LinkedList<GroundAction> list = new LinkedList();
        helpful_actions = new LinkedHashSet();
        AchieverSet s = getAchieverSet(pseudoGoal);
        getHelpfulActions(list, s);
        while (!list.isEmpty()) {
            GroundAction gr2 = list.pollLast();
            s = getAchieverSet(gr2);
            if (s.getCost() > 0){
                getHelpfulActions(list, s);
            }
        }
//        Utils.dbg_print(debugLevel, "HelpfulActions: " + helpful_actions.toString() + "\n");
    }

    private void computeRelaxPlan ( ) {//this should be updated!

        LinkedList<Condition> list = new LinkedList();
        relaxedPlan = new ArrayList();

        helpful_actions = new ArrayList();
        AchieverSet s = this.getAchieverSet(pseudoGoal);
        
        boolean[] visited = new boolean[this.conditionUniverse.size()+1];
        Arrays.fill(visited, false);
        for (Condition c : s.getTargetCond()) {
            list.add(c);
            visited[c.getHeuristicId()] =  true;
        }
        while (!list.isEmpty()) {
            final Condition c = list.pollLast();
//          Only conditions not already satsified participate here
            if (this.cost[c.getHeuristicId()] != 0) {
//              This is what you get in forward analysis
                GroundAction gr = this.establishedAchiever[c.getHeuristicId()];
                this.updateRelaxPlan((ArrayList) relaxedPlan, gr, this.establishedLocalCost[c.getHeuristicId()], c instanceof Comparison);
                if (this.is_complex.get(c.getHeuristicId()) || allAchieverActions) {
                    final Set<GroundAction> allAchiever = this.allAchievers[c.getHeuristicId()];
                    if (allAchiever != null) {
                        for (final GroundAction gr2 : allAchiever) {
                            if (this.actionHCost[gr2.getId()] == 0) {
                                this.getHelpfulActions().add(this.heuristicActionsToProblemActions[gr2.getId()]);
                            }
                        }
                    }
                }
                if (this.actionHCost[gr.getId()] == 0) {
                    this.getHelpfulActions().add(this.heuristicActionsToProblemActions[gr.getId()]);
                } else {
                    AchieverSet ach_set = this.getAchieverSet(gr);
                    if (ach_set != null) {
                        for (final Condition c1 : ach_set.getTargetCond()) {
                            if (!visited[c1.getHeuristicId()]) {
                                list.add(c1);
                                visited[c1.getHeuristicId()] = true;
                            }
                        }
                    }
                }
            }
        }
    }


    private void update_achiever (Condition comp, GroundAction gr) {

        Set s = allAchievers[comp.getHeuristicId()];
        if (s == null) {
            s = new ReferenceLinkedOpenHashSet();
        }
        s.add(gr);
        allAchievers[comp.getHeuristicId()] = s;
    }

    private void extract_helpful_actions_or_relaxed_plan ( ) {

        if (this.extractRelaxedPlan ) {
            computeRelaxPlan();
        }else if (this.helpful_actions_computation){
            compute_helpful_actions();
        }
        

    }

    private void getHelpfulActions (LinkedList<GroundAction> list, AchieverSet s) {
        if (s != null) {
            
            for (Condition o : s.getTargetCond()) {
                if (cost[o.getHeuristicId()] == 0) {
                    continue;
                }
//                if (o != null) {
                //System.out.println("Helpful actions extraction");
                if (this.is_complex.get(o.getHeuristicId()) || allAchieverActions) {
                    if (this.allAchievers[o.getHeuristicId()] != null) {
                        //System.out.println("Getting all the predicatesProduction as helpful actions..");
                        for (GroundAction gr : this.allAchievers[o.getHeuristicId()]) {
                            if (this.actionHCost[gr.getId()] == 0) {
                                addToHelpfulIfPossible(gr);
                            }
                        }
                    }
                } else {//Add helpful action if this action is an established achiever of any condition in the target condition and is applicable
                    //in the initial state
                    GroundAction gr = this.establishedAchiever[o.getHeuristicId()];
//                        if (gr==null && cond_dist[o.getCounter())!=0]{
//                            System.out.println("this is bizzare");
//                            System.out.println(o);
//                            System.exit(-1);
//                        }
//                        if (this.action_dist.get(gr.counter)==null){
//                            System.out.println("Action not classified. Very strange:"+gr);
//                            System.out.println("Counter"+gr.counter);
//                            System.out.println("Max number of actions"+this.total_number_of_actions);
//                        }
                    if (this.actionHCost[gr.getId()] == 0) {
                            addToHelpfulIfPossible(gr);
                    }

                }
//                }
            }
            //this.checkAchievers(s);
            for (GroundAction gr : s.getActions()) {
                if (gr != null) {
                    list.addFirst(gr);
                }
            }
        }
    }

    private void findUnsatReasons (GroundAction goal) {

        //works only for conjunction
        for (Condition c : goal.getPreconditions().getTerminalConditions()) {
            if (cost[c.getHeuristicId()] == Float.MAX_VALUE) {
                System.out.println("Unreachable in the relaxation: " + c);
            }
        }

    }

    private float min_over_possible_achievers (Condition comp) {
        Set<GroundAction> set = this.allAchievers[comp.getHeuristicId()];
        float min = Float.MAX_VALUE;
        for (GroundAction gr : set) {//can be optimised
            min = Math.min(actionHCost[gr.getId()], min);
            if (min == 0) {
                return 0f;
            }
        }
        return min;
    }

    private void updateRelaxPlan (List<Pair<GroundAction, Float>> plan, GroundAction g, Float cost, boolean isComparison) {

        for (int i = plan.size() - 1; i >= 0; i--) {
            Pair<GroundAction, Float> gr_pair = plan.get(i);
            //if (gr_pair.getFirst().equals(g)) {
            if (gr_pair.getLeft().getId() == g.getId()) {
                if (aggressiveRelaxedPlan && isComparison){
                    plan.set(i, Pair.of(gr_pair.getLeft(), cost + gr_pair.getRight()));
                }else{
                    plan.set(i, Pair.of(gr_pair.getLeft(),Math.max(cost, gr_pair.getRight())));
                }
                return;
            }

            if (only_mutual_exclusion_processes) {//work from here to have hff sensible to process structure!
                if (g instanceof com.hstairs.ppmajal.problem.GroundProcess || g instanceof com.hstairs.ppmajal.problem.GroundEvent) {//very very conservative
                    if (gr_pair.getLeft() instanceof com.hstairs.ppmajal.problem.GroundProcess || gr_pair.getLeft() instanceof com.hstairs.ppmajal.problem.GroundEvent) {
                        if (!gr_pair.getLeft().getPreconditions().mutual_exclusion_guaranteed(g.getPreconditions())) {
                            plan.set(i, Pair.of(gr_pair.getLeft(),Math.max(cost, gr_pair.getRight())));
                            return;
                        }
                    }
                }
            }

        }
        plan.add(Pair.of(g, cost));
    }

    private float extract_tot_cost (List<Pair<GroundAction, Float>> plan) {
        float cost = 0f;
        for (final Pair<GroundAction, Float> p : plan) {
//            System.out.println("("+p.getLeft().toEcoString()+","+p.getRight()+")");
            cost += p.getRight();
        }
//        System.exit(-1);
        return cost;
    }


    protected void generateAchieversDataStructures ( ) {
        achieve = new Collection[this.totActions];
        
        invertedAchievers = new Collection[this.conditionUniverse.size() + 1];
        possibleAchievers = new Collection[this.totActions];
        this.invertedPossibleAchievers = new Collection[this.conditionUniverse.size() + 1];
        precondition_mapping = new Collection[this.conditionUniverse.size() + 1];
        //this should also include the indirect dependencies, otherwise does not work!!
        extraActionPrecondition = new AndCond[this.totActions];


    }

    private void computeComplexAchievers (Comparison comp) {
        HashSet<NumEffect> num_effects = new LinkedHashSet();
        HashMap<NumEffect, Boolean> temp_mark = new HashMap();
        HashMap<NumEffect, Boolean> per_mark = new HashMap();
        LinkedList<NumEffect> sorted_nodes;
        sorted_nodes = new LinkedList();
        for (GroundAction gr : A) {
            for (NumEffect nf : gr.getNumericEffectsAsCollection()) {
                temp_mark.put(nf, false);
                per_mark.put(nf, false);
                num_effects.add(nf);
            }
        }
        for (NumEffect a : num_effects) {
            if ((!per_mark.get(a)) && (comp.getLeft().involve(a.getFluentAffected()))) {
                visit(a, num_effects, temp_mark, per_mark, sorted_nodes);
            }
        }
        ReferenceLinkedOpenHashSet<GroundAction> action_list = new ReferenceLinkedOpenHashSet();
        for (GroundAction gr : A) {
            for (NumEffect neff : gr.getNumericEffectsAsCollection()) {

                if (sorted_nodes.contains(neff)) {
                    if (possibleAchievers[gr.getId()] == null) {
                        possibleAchievers[gr.getId()] = new LinkedHashSet<>();
                    }
                    possibleAchievers[gr.getId()].add(comp);
                    action_list.add(gr);
                }

            }
        }
        if (this.invertedPossibleAchievers[comp.getHeuristicId()] == null) {
            this.invertedPossibleAchievers[comp.getHeuristicId()] = action_list;
        } else {
            Collection<GroundAction> temp = this.invertedPossibleAchievers[comp.getHeuristicId()];
            temp.addAll(action_list);
            this.invertedPossibleAchievers[comp.getHeuristicId()] = temp;
        }
        if (debug == 1) {
            System.out.println("Comparison:" + comp);
            System.out.println("Achievers Set" + this.invertedPossibleAchievers[comp.getHeuristicId()]);
        }
    }

    private void computeComplexAchievers ( ) {
        for (Comparison comp : this.complex_condition_set) {
            this.computeComplexAchievers(comp);
        }
        Utils.dbg_print(debug, "Complex predicatesProduction identified");
    }


    //incremental use of the model

    protected void addAchieved (GroundAction gr, Predicate dummyPredicate) {
        Collection<Terminal> terminals = this.achieve[gr.getId()];
        if (terminals == null) {
            terminals = new ReferenceLinkedOpenHashSet<>();
        }
        terminals.add(dummyPredicate);
        this.achieve[gr.getId()] = terminals;
    }

    protected void addRequired (GroundAction action, Predicate dummyPredicate) {
        AndCond condition = this.extraActionPrecondition[action.getId()];
        if (condition == null) {
            condition = new AndCond();
        }
        condition.addConditions(dummyPredicate);
        this.extraActionPrecondition[action.getId()] = condition;
        getExtraTrigger()[dummyPredicate.getHeuristicId()] = action;
    }

    private GroundAction[] getExtraTrigger ( ) {
        if (this.extraTrigger == null) {
            this.extraTrigger = new GroundAction[this.conditionUniverse.size()+1];
        }
        return this.extraTrigger;
    }

    protected void addNewCondition (Predicate dummyPredicate) {
        dummyPredicate.setHeuristicId(conditionUniverse.size());
        conditionUniverse.add(dummyPredicate);
        this.is_complex.add(false);
    }

    protected void addRedundantConstraints ( ) {
        redundant_constraints = new HashMap();

        for (GroundAction a : A) {
            if (a.getPreconditions() != null) {
                compute_redundant_constraint((Set<ComplexCondition>) a.getPreconditions().sons);
            }
            //System.out.println(a.toPDDL());
        }

        compute_redundant_constraint((Set<ComplexCondition>) G.sons);
    }
    protected void compute_redundant_constraint (Set<ComplexCondition> set) {
        LinkedHashSet temp = new LinkedHashSet();
        ArrayList<Condition> set_as_array = new ArrayList(set);
        int counter = 0;
        for (int i = 0; i < set_as_array.size(); i++) {
            for (int j = i + 1; j < set_as_array.size(); j++) {
                Condition c_1 = set_as_array.get(i);
                Condition c_2 = set_as_array.get(j);
                if ((c_1 instanceof Comparison) && (c_2 instanceof Comparison)) {
                    counter++;
                    Comparison a1 = (Comparison) c_1;
                    Comparison a2 = (Comparison) c_2;
                    ExtendedNormExpression lhs_a1 = (ExtendedNormExpression) a1.getLeft();
                    ExtendedNormExpression lhs_a2 = (ExtendedNormExpression) a2.getLeft();
                    ExtendedNormExpression expr = lhs_a1.sum(lhs_a2);
                    String new_comparator = ">=";
                    if (a1.getComparator().equals(">") && a2.getComparator().equals(">")) {
                        new_comparator = ">";
                    }
                    Comparison newC = (Comparison) Comparison.createComparison(new_comparator, expr, new ExtendedNormExpression(0d), false).normalize();
                    if (newC != null) {

                        newC.normalize();
                        ExtendedNormExpression tempLeft = (ExtendedNormExpression) newC.getLeft();

                        if (tempLeft.summations.size() < 2) {
                            continue;
                        }
                        redundant_constraints.put(newC, true);
                        temp.add(newC);
                    }

                }
            }
        }
//        System.out.println("New conditions now:"+counter);
//        System.out.println("Set before:"+set.size());
        set.addAll(temp);
//        System.out.println("Set after:"+set.size());
    }

    
    @Override
    final public Set<GroundAction> getReachableTransitions() {
        if (masterProblemReachableTransitions != null){
            return masterProblemReachableTransitions;
        }
        masterProblemReachableTransitions = new LinkedHashSet();
        for (GroundAction gr: this.A){
            if (gr != pseudoGoal){
                masterProblemReachableTransitions.add(this.heuristicActionsToProblemActions[gr.getId()]);
            }
        }
        if (problem.getProcessesSet().isEmpty() && problem.getEventsSet().isEmpty()){
            problem.actions = masterProblemReachableTransitions;
        }
        return masterProblemReachableTransitions;
    }
    
    
    protected void identifyComplexConditions(Collection<GroundAction> A) {
        //For each condition, identify whether there is at least an action whose effects are not simple. This condition
        // will be considered complex in that checking its satisfaction is hard
        is_complex = new ArrayList<>(nCopies(conditionUniverse.size() + 1, false));

        new_condition = new HashMap();
        complex_condition_set = new LinkedHashSet();
        directAssignmentConditionHandle = new HashMap();
        for (Condition c:conditionUniverse){
            c.normalize();
//            System.out.println("This is condition number:"+c.getCounter());
            if (c instanceof Comparison) {
                Comparison comp = (Comparison) c;
                
                new_condition.put(comp, false);
                is_complex.set(comp.getHeuristicId(), false);
                if (!comp.isLinear()) {
                    is_complex.set(comp.getHeuristicId(), true);
                    complex_condition_set.add((Comparison) c);
                }
                boolean oneFound = false;
                boolean oneAssignFound = false;

                for (GroundAction gr : A) {
                    if (gr.getNumericEffects() != null) {
                        AndCond effects = gr.getNumericEffects();
                        
                        for (NumEffect ne : (Collection<NumEffect>) effects.sons) {
                            if (comp.getInvolvedFluents().contains(ne.getFluentAffected())) {
                                if (conditionToAction[c.getHeuristicId()] != null && conditionToAction[c.getHeuristicId()].size() == 1 && !conditionToAction[c.getHeuristicId()].isEmpty()) {
                                    GroundAction next = conditionToAction[c.getHeuristicId()].iterator().next();
                                    if (next.equals(gr)) {
                                        break;
                                    }
                                }
                                if (!ne.getInvolvedNumericFluents().isEmpty() || 
                                        (gr.getNumericEffectsAsCollection().size() > 1) 
                                        && (ne.getOperator().equals("assign")) ||
                                        (oneFound) 
                                        && (ne.getOperator().equals("assign")) && gr.is_possible_achiever_of(comp) ||
                                        oneAssignFound && gr.is_possible_achiever_of(comp)
                                    ) {
                                    
                                    is_complex.set(comp.getHeuristicId(), true);
                                    complex_condition_set.add((Comparison) c);
                                    oneAssignFound = true;
                                }
                                if (gr.is_possible_achiever_of(comp)){
                                    if (ne.getOperator().equals("assign")){
                                        Condition regressEdComparison = comp.regress(gr);
                                        directAssignmentConditionHandle.put(Pair.of(gr.getId(),comp.getHeuristicId()), (Comparison) regressEdComparison);
                                    }
                                    oneFound = true;
                                }
                                
                            }
                        }
                    }
                }
                if (is_complex.get(comp.getHeuristicId())) {
                    numberOfComplexConditions++;
                }
            }
        }
        Utils.dbg_print(debug, "Identify complex predicatesProduction");
        if (!this.ibrDisabled) {
            this.computeComplexAchievers();
        }
    }
    
     public boolean visit(NumEffect nf, Collection<NumEffect> col, HashMap<NumEffect, Boolean> temp_mark, HashMap<NumEffect, Boolean> per_mark, LinkedList<NumEffect> list) {

        if (temp_mark.get(nf)) {
            return true;
        }
        if (per_mark.get(nf)) {
            return false;
        }
        temp_mark.put(nf, true);
        boolean cyclic = false;
        for (NumEffect b : col) {
            if (!b.equals(nf)) {
                if (nf.getRight().involve(b.getFluentAffected())) {
                    //list.addLast(nf);
                    if (visit(b, col, temp_mark, per_mark, list)) {
                        per_mark.put(nf, true);
                        temp_mark.put(nf, false);
                        list.addLast(nf);
                        return true;//don't bother here; the task is cyclic anyway, though keep this numeric effect and try to reach the goal afterwards
                        //cyclic = true;
                    }
                }
            }
        }
        per_mark.put(nf, true);
        temp_mark.put(nf, false);
        list.addLast(nf);
        return cyclic;
    }

    private void checkAchievers(AchieverSet s) {
        boolean found = true;
        for (Condition c : s.getTargetCond()) {
            if (cost[c.getHeuristicId()] != 0) {
                found = false;
               
            }else{
                System.out.println("Sat"+c);
            }
        }
        if (found && !s.getActions().isEmpty()) {
            System.out.println("This is broken");
            for (GroundAction gr: this.A){
                if (s == this.getAchieverSet(gr)){
                    System.out.println("Action culprit:"+gr);
                    System.out.println("Action it depends on:"+s.getActions());
                    System.out.println("Cost:"+s.getCost());
                }
            }
        }
    }

    private AchieverSet getAchieverSet(GroundAction gr) {
        if (achieversSet[gr.getId()]== null){
            Condition condition = this.extraActionPrecondition[gr.getId()];
            Condition input = null;
            if (condition != null) {
                input = new AndCond();
                ((AndCond) input).addConditions(condition);
                ((AndCond) input).addConditions(gr.getPreconditions());
            } else {
                input = gr.getPreconditions();
            }
            AchieverSet s = this.estimateAchievers(input);
            achieversSet[gr.getId()] = s;
        }
        return achieversSet[gr.getId()];
       
    }

    private void addToHelpfulIfPossible(GroundAction gr) {
        GroundAction helpful = this.heuristicActionsToProblemActions[gr.getId()];
        if (!(helpful instanceof GroundProcess) && !(helpful instanceof GroundEvent)) {
            this.helpful_actions.add(helpful);
        }
    }

     public class GroundActionComparator implements IntComparator{

        @Override
        public int compare(int o1, int o2) {
            if (actionHCost[o1] < actionHCost[o2]){
                return -1;
            }else if (actionHCost[o1] > actionHCost[o2]){
                return 1;
            }else{
                return 0;
            }
        }
    
    }
     
    @Override
     public Collection<GroundAction> getHelpfulActions(){
         return this.helpful_actions;
     }

}
