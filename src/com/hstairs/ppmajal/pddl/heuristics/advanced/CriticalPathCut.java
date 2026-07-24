package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.*;

public class CriticalPathCut extends LmCut {

    private final boolean useBottomCostUpdate;

    public CriticalPathCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                           String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                           boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost,
                           int linearEffectsAbstraction) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                null, unitaryCost, linearEffectsAbstraction, false, false);
    }

    public CriticalPathCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                           String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                           boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost,
                           int linearEffectsAbstraction, boolean useBottomCostUpdate) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                null, unitaryCost, linearEffectsAbstraction, false, useBottomCostUpdate);
    }

    private CriticalPathCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                            String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                            boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond, Collection<IntArraySet>> redundantMap,
                            boolean unitaryCost, int compNumericStrategy, boolean ssnpAwareVersion,
                            boolean useBottomCostUpdate) {
        super(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                redundantMap, unitaryCost, compNumericStrategy, ssnpAwareVersion);
        this.useBottomCostUpdate = useBottomCostUpdate;
    }

    @Override
    public float computeEstimate(State gs) {
        float cost = 0f;
        boolean firstTime = true;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        JGraph justificationGraph = null;
        boolean[] close = new boolean[cp.numActions()];

        while (true) {
            if (firstTime) {
                final Pair<JGraph, Float> jGraphAndValue = constructJG(gs);
                justificationGraph = jGraphAndValue.getFirst();
                final float goalValue = jGraphAndValue.getSecond();

                if (goalValue == 0f || goalValue == Float.MAX_VALUE) {
                    return firstTime ? goalValue : cost;
                }
            }
            cost += actionHCost[cp.goal()];
            Arrays.fill(close, Boolean.FALSE);
//
//            for(var v: allActions){
//                Transition transition1 = TransitionGround.getTransition(cp.cpTr2TrMap()[v]);
//                if (transition1 instanceof TransitionGround) {
//                    TransitionGround transition = (TransitionGround) transition1;
//                    System.out.print("Action:" + transition.getName() + transition.getName() + transition.getParameters());
//                    System.out.print(" Cost:" + getActionCost()[v]);
//                    System.out.println(" ID azione è:" + v);
//                }else{
//                    System.out.println("What is this transition? "+transition1);
//                }
//            }

            Collection<Cut> cuts  = changeCost(pcf[cp.goal()], justificationGraph, close, cost, gs,useBottomCostUpdate);
            if (true){
                final Float res = updateJG(justificationGraph,gs,cuts);
                if (res == 0f){
                    return cost;
                }
            }else{
                final Pair<JGraph, Float> jGraphAndValue = constructJG(gs);
                justificationGraph = constructJG(gs).getFirst();
                if (jGraphAndValue.getSecond() == 0f){
                    return cost;
                }
            }
        }
    }


    private Collection<Cut> changeCost(int i, JGraph justificationGraph, boolean[] close, float cost, State gs, boolean bottom) {
        if (cost == 0f){
            return Collections.EMPTY_LIST;
        }
        ArrayList<Cut> cuts = new ArrayList<Cut>();
        for (final var act : justificationGraph.ERev()[i]){
            if (!close[act]) {
                close[act] = true;
                final float prev = getActionCost()[act];
                final float v = computeSupporterRepetition(i, act, gs);
                if (!bottom || (actionHCost[act] < cost)){
                    final float residual = getActionCost()[act] * v + actionHCost[act] - cost;
                    getActionCost()[act] = Math.min(getActionHCost()[act], residual / v);
                }else {
                    getActionCost()[act] = 0f;
                }
                if (prev > getActionCost()[act]) {
                    cuts.add(new Cut(act,i));
                }
//                System.out.println("Changing cost for "+ TransitionGround.getTransition(cp.cpTr2TrMap()[act]));
//                System.out.println("from "+prev);
//                System.out.println("to "+getActionCost()[act]);
//                System.out.println("Residual:"+(cost-(getActionCost()[act]*v)));
                final float newCost = cost - (prev * v - (getActionCost()[act] * v));
                if (newCost > 0f)
                    cuts.addAll(changeCost(pcf[act], justificationGraph, close, newCost, gs, bottom));
            }
        }
        return cuts;
    }

    private float computeSupporterRepetition(int conditionId, int actionId, State gs) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        float actionCost = getActionCost()[actionId];

        if (!(terminal instanceof Comparison comparison)) {
            return  1;
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            return -1f;
        }

        final float repetitions = computeRepetitions(comparison, contribution, gs);
        if (contribution == UNKNOWNEFFECT)
            actionCost = 0f;
        return repetitions;
    }


}
