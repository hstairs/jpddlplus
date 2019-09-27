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
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.heuristics.advanced;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.heuristics.Heuristic;
import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.IntComparator;
import it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static com.google.common.collect.Sets.SetView;

/**
 * @author enrico
 */
public class h1 implements Heuristic {

    final private boolean useRedundantConstraints;
    final public boolean extractRelaxedPlan;

    final private int pseudoGoal;
    final private Condition[] preconditionFunction;
    final private Collection<Terminal>[] propEffectFunction;
    final private Collection<NumEffect>[] numericEffectFunction;
    final private int heuristicNumberOfActions;
    final private int totNumberOfTerms;
    final private EPddlProblem problem;
    private final Collection[] invertedAchievers;
    private final Collection[] achieve;
    private final Collection[] possibleAchievers;
    private final Collection[] invertedPossibleAchievers;
    private final Collection[] preconditionToAction;
    private final Collection[] conditionToAction;
    private final HashSet<Terminal> allConditions;

    private float[] actionCost;
    private float[] actionHCost;
    private float[] conditionCost;

    private final boolean additive;
    private boolean[] closed;

    public h1(EPddlProblem problem){
        this(problem,true);
    }
    public h1(EPddlProblem problem, boolean additive) {
        this.additive = additive;
        this.problem = problem;
        this.useRedundantConstraints = false;
        extractRelaxedPlan = true;
        pseudoGoal = Transition.totNumberOfTransitions;
        heuristicNumberOfActions = Transition.totNumberOfTransitions+1;
        totNumberOfTerms = Predicate.getPredicatesDB().values().size() + Comparison.getNumberOfComparisons() + NotCond.getNumberOfNotCond();

        preconditionFunction = new Condition[heuristicNumberOfActions];
        propEffectFunction = new Collection[heuristicNumberOfActions];
        numericEffectFunction = new Collection[heuristicNumberOfActions];

        actionCost = new float[heuristicNumberOfActions];
        Arrays.fill(actionCost,Float.MAX_VALUE);

        SetView<TransitionGround> transitions = Sets.union(Sets.union(new HashSet(problem.actions), problem.getEventsSet()),problem.getProcessesSet());
        for (TransitionGround b : transitions) {
            if (useRedundantConstraints) {
                preconditionFunction[b.getId()] = b.getPreconditions().introduce_red_constraints();
            }else{
                preconditionFunction[b.getId()] = b.getPreconditions();
            }
            propEffectFunction[b.getId()] = b.getAllAchievableLiterals();
            numericEffectFunction[b.getId()] = b.getConditionalNumericEffects().getAllEffects();
            actionCost[b.getId()] = b.getActionCost(problem.getInit(),problem.getMetric());
        }
        if (useRedundantConstraints){
            preconditionFunction[pseudoGoal] = problem.getGoals().introduce_red_constraints();
        }else {
            preconditionFunction[pseudoGoal] = problem.getGoals();
        }
        achieve = new Collection[heuristicNumberOfActions];

        invertedAchievers = new Collection[totNumberOfTerms];
        possibleAchievers = new Collection[heuristicNumberOfActions];
        invertedPossibleAchievers = new Collection[totNumberOfTerms];
        preconditionToAction = new Collection[totNumberOfTerms];
        conditionToAction = new Collection[totNumberOfTerms];
        allConditions = new HashSet();

        for (TransitionGround b : transitions) {
            int i = b.getId();
            Collection<Condition> terminalConditions = preconditionFunction[i].getTerminalConditionsInArray();
            updatePreconditionFunction(i);
        }
        updatePreconditionFunction(pseudoGoal);
    }

    void updatePreconditionFunction(int i){
        Collection<Condition> terminalConditions = preconditionFunction[i].getTerminalConditionsInArray();
        for (Condition c : terminalConditions) {
            if (c instanceof  Terminal ) {
                Terminal t = (Terminal)c;
                Collection<Integer> groundActions = conditionToAction[((Terminal) c).id];
                if (groundActions == null) {
                    groundActions = new ArrayList<>();

                }
                groundActions.add(i);
                conditionToAction[t.id] = groundActions;
                allConditions.add((Terminal) c);
            }
        }
    }


    void updateActions(final Terminal c,final IntHeapPriorityQueue p) {
        Collection<Integer> actions = conditionToAction[c.id];
        if (actions != null) {
            for (int i : actions) {
                if (!closed[i]) {
                    final float v = estimateCost(preconditionFunction[i]);
                    if (v < Float.MAX_VALUE) {
                        actionHCost[i] = Math.min(v, actionHCost[i]);
                        p.enqueue(i);
                    }
                }
            }
        }
    }

    @Override
    public float computeEstimate (State gs) {
        actionHCost = new float[heuristicNumberOfActions];
        Arrays.fill(actionHCost,Float.MAX_VALUE);
        conditionCost = new float[totNumberOfTerms];
        Arrays.fill(conditionCost,Float.MAX_VALUE);
        closed = new boolean[heuristicNumberOfActions];
        Arrays.fill(closed,false);

        IntHeapPriorityQueue p = new IntHeapPriorityQueue(new GroundActionComparator());

        for (Terminal c: allConditions){
            if (gs.satisfy(c)){
                conditionCost[c.id] = 0f;
                updateActions(c,p);
            }
        }
        while(!p.isEmpty()){
            final int actionId = p.dequeueInt();
            if (actionId == pseudoGoal){
                return actionHCost[pseudoGoal];
            }
            closed[actionId] = true;
            updateAchievable(actionId,p);
        }
        return 0f;

    }

    private void updateAchievable(int actionId, IntHeapPriorityQueue p) {

        Collection<Terminal> achiavable = possibleAchievers[actionId];
        if (achiavable == null){
            achiavable = new HashSet();
            achiavable.addAll(propEffectFunction[actionId]);
            possibleAchievers[actionId] = achiavable;
        }
        for (final Terminal t: achiavable){
            conditionCost[t.id] = Math.min(actionHCost[actionId]+actionCost[actionId],conditionCost[t.id]);
            updateActions(t,p);
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
                if (additive){// && !this.extractRelaxedPlan) {
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
        } else if (c instanceof Terminal) {
            Terminal t = (Terminal) c;
            return conditionCost[t.id];
        }else {
            return 0f;
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

}
