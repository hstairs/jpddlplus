package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import org.jgrapht.alg.util.Pair;

import java.util.*;

public class CriticalPathCut extends LmCut {

    private final boolean useBottomCostUpdate;
    private int[] bestAchiever;

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
    protected void updateBestAchiever(int cond, int act){
//        if (bestAchiever == null){
//            bestAchiever = new int[getTotNumberOfTerms()];
//            Arrays.fill(bestAchiever, -1);
//        }
//        bestAchiever[cond] = act;
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
            firstTime = false;

            cost += actionHCost[cp.goal()];
            Arrays.fill(close, Boolean.FALSE);
//            printActionName();
//            printConditionNames();
            int current = pcf[cp.goal()];
            float[] reducedCostsPrev = Arrays.copyOf(reducedCosts, cp.actionCost().length);

//            while (current != getTotNumberOfTerms() && bestAchiever != null){
//                final int action = bestAchiever[current];
//                if (action == -1) {
//                    break;
//                }
//                reducedCosts[action] = 0f;
//                cuts.add(new Cut(action,current));
//                close[action] = true;
//                current = pcf[action];
//            }
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

            final Collection<Cut> cuts = reduceCosts(pcf[cp.goal()],reducedCostsPrev, justificationGraph, close, actionHCost[cp.goal()], gs);
            //cuts.addAll(changeCost(pcf[cp.goal()], justificationGraph, close, actionHCost[cp.goal()], gs,useBottomCostUpdate));
            final Float res = updateJG(justificationGraph,gs,cuts);
            if (res == 0f){
                return cost;
            }

        }
    }

    private List<Cut> reduceCosts(int i, float[] reducedCostsPrev, JGraph justificationGraph, boolean[] close, float cost, State gs) {
        if (cost == 0f){
            return Collections.emptyList();
        }
        ArrayList<Cut> cuts = new ArrayList<Cut>();
        for (final var act : justificationGraph.ERev()[i]){
            if (reducedCosts[act] > 0f ) {
                final float prev = reducedCostsPrev[act];
                final float v = computeSupporterRepetition(i, act, gs);
                final float residual = cost / v;
                final float redCostTemp = Math.min(reducedCosts[act], Math.max(prev - residual, 0f));
                if (redCostTemp != reducedCosts[act]) {
                    reducedCosts[act] = redCostTemp;
                    cuts.add(new Cut(act, i));
                }
                final float newCost = cost - (prev  - reducedCosts[act]) * v;
                if (newCost > 0f && !close[act]) {
                    close[act] = true;
                    cuts.addAll(reduceCosts(pcf[act],reducedCostsPrev, justificationGraph, close, newCost, gs));
                }
            }else{
                if (cost > 0f && !close[act]) {
                    close[act] = true;
                    cuts.addAll(reduceCosts(pcf[act],reducedCostsPrev, justificationGraph, close, cost, gs));
                }
            }

        }
        return cuts;

    }


    private void printConditionNames() {
        for (var v=0; v<totNumberOfTerms;v++){
            System.out.println("ID:"+v+" -> Condition"+Terminal.getTerminal(v));
        }
    }

    private void printActionName() {
        for (var v=0; v<cp.numActions();v++){
            System.out.println("ID:"+v+" -> Action"+TransitionGround.getTransition(cp.cpTr2TrMap()[v]).getName());
        }
    }

//
//    Float updateJG(JGraph jg, State gs, Collection<Cut> cuts) {
//
//        final FibonacciHeap heap = new FibonacciHeap();
//        Arrays.fill(getClosed(), false);
//        nodeOf = new FibonacciHeapNode[cp.numActions()];
//        final ArrayList<Cut> toExplore = new ArrayList<>();
//        for (Cut cut : cuts) {
////            System.out.println("pcf: "+ pcf[cut.act]+ "root:"+root);
//            if (jg.E()[pcf[cut.act()]][cut.act()] != null) { // This shouldn't happen. There are cuts which are generated by reversed edged which are outdated.
//                for (var v : jg.E()[pcf[cut.act()]][cut.act()]) {
//                    toExplore.add(new Cut(cut.act(), v));
//                }
//            }
//        }
//        for (final Cut cut : toExplore) {
//            final int v = cut.cond();
//            final float supporterCost = getActionCost()[cut.act()] == 0f
//                    ? actionHCost[cut.act()]
//                    : computeSupporterCost(v, cut.act(), gs,true);
//            if (updateIfNeeded(v, supporterCost)) {
//                updateActions(v, heap);
//            }
//        }
//
//        while (!heap.isEmpty()) {
//            final int actionId = (int) heap.removeMin().getData();
//            jg.V().add(pcf[actionId]);
//            jg.E()[pcf[actionId]][actionId] = new IntArrayList();
//            closed[actionId] = true;
//            if (actionId != cp.goal()) {
//                final IntSet conditionsAchievableByAction = getConditionsAchievableById(actionId);
//                for (final int conditionId : conditionsAchievableByAction) {
//                    if (!getConditionInit()[conditionId]) {
//                        addConditionToEdges(jg.E(),pcf[actionId],actionId,conditionId);
////                        jg.ERev()[actionId].add(conditionId);
//                        final float supporterCost = getActionCost()[actionId] == 0f
//                                ? actionHCost[actionId]
//                                : computeSupporterCost(conditionId, actionId, gs,true);
//                        if (updateIfNeeded(conditionId, supporterCost)) {
//                            updateActions(conditionId, heap);
//                            updateBestAchiever(conditionId,actionId);
//                        }
//                    }
//                }
//            } else if (getActionHCost()[actionId] == 0f) {
//                break;
//            }
//
//        }
//        return getActionHCost()[cp.goal()];
//    }


    private List<Cut> changeCost(int i, JGraph justificationGraph, boolean[] close, float cost, State gs, boolean bottom) {
        if (cost == 0f){
            return Collections.EMPTY_LIST;
        }
        ArrayList<Cut> cuts = new ArrayList<Cut>();
        for (final var act : justificationGraph.ERev()[i]){
            if (!close[act]) {
                close[act] = true;
                final float prev = getActionCost()[act];
                final float v = computeSupporterRepetition(i, act, gs);
                if ((!bottom || actionHCost[act] < cost) ) {
                    final float residual = getActionCost()[act] * v + actionHCost[act] - cost;
                    assert residual >= 0;
                    getActionCost()[act] = Math.min(getActionCost()[act], residual / v);
                }else{
                    getActionCost()[act] = 0f;
                }
                if (prev > getActionCost()[act]) {
                    cuts.add(new Cut(act,i));
                }
//                System.out.println("Changing cost for "+ TransitionGround.getTransition(cp.cpTr2TrMap()[act]));
//                System.out.println("from "+prev);
//                System.out.println("to "+getActionCost()[act]);
//                System.out.println("Residual:"+(cost-(getActionCost()[act]*v)));
                final float newCost = cost - (prev  - (getActionCost()[act])) * v;
                if (newCost > 0f)
                    cuts.addAll(changeCost(pcf[act], justificationGraph, close, newCost, gs, bottom));
            }
        }
        return cuts;
    }

    private String getActionName(Integer act) {
        return TransitionGround.getTransition(cp.cpTr2TrMap()[act]).getName();
    }

    private float computeSupporterRepetition(int conditionId, int actionId, State gs) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return  1;
        }
        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            throw new IllegalStateException("Invalid supporter repetition in justification graph");
        }
        if (contribution == UNKNOWNEFFECT){
            return 1;
        }
        final float repetitions = computeRepetitions(comparison, contribution, gs);

        return repetitions;
    }


}
