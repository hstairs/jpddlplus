package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.*;

public class LmCut extends H1 {

    private final int NOBEST = -1;
    protected final int root;
    protected final int[] pcf;
    protected final BitSet[] pcf2Actions;
    protected float[] reducedCosts;
    protected double[] evalComparison;

    record Cut(int act, int cond) {
    }
    record CostJustifier(float cost, int conditionId) {
    }
    record JGraph(IntArrayList[] actionEdges, Collection<Integer>[] ERev) {
    }

    public LmCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                 String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                 boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost,
                 int linearEffectsAbstraction) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                null, unitaryCost, linearEffectsAbstraction, false);
    }

    public LmCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan,
                 boolean maxHelpfulTransitions, String redConstraints,
                 boolean helpfulActionsComputation, boolean reachability,
                 boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost,
                 int linearEffectsAbstraction, boolean useNumericActivationFloor) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions,
                redConstraints, helpfulActionsComputation, reachability,
                helpfulTransitions, conjunctionsMax, null, unitaryCost,
                linearEffectsAbstraction, CrdMode.NONE,
                useNumericActivationFloor);
    }

    protected LmCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                  String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                  boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond, Collection<IntArraySet>> redundantMap,
                  boolean unitaryCost, int compNumericStrategy, boolean crdEnabled) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                redundantMap, unitaryCost, compNumericStrategy,
                crdEnabled ? CrdMode.STATIC : CrdMode.NONE);
    }

    protected LmCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                  String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                  boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond, Collection<IntArraySet>> redundantMap,
                  boolean unitaryCost, int compNumericStrategy, CrdMode crdMode) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                redundantMap, unitaryCost, compNumericStrategy, crdMode, false);
    }

    protected LmCut(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                  String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                  boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond, Collection<IntArraySet>> redundantMap,
                  boolean unitaryCost, int compNumericStrategy, CrdMode crdMode,
                  boolean useNumericActivationFloor) {
        super(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                redundantMap, unitaryCost, compNumericStrategy, crdMode,
                useNumericActivationFloor);
        this.root = getTotNumberOfTerms();
        this.pcf = new int[cp.numActions()];
        Arrays.fill(this.pcf, -1);
        pcf2Actions = new BitSet[getTotNumberOfTerms()+1];
    }

    Float updateJG(JGraph jg, State gs, IntCollection changedActions) {

        final ArrayList<Cut> toExplore = new ArrayList<>();
        for (final int actionId : changedActions) {
            collectChangedActionEdges(jg, actionId, toExplore);
        }
        return updateJG(jg, gs, toExplore);
    }

    Float updateJG(JGraph jg, State gs, BitSet changedActions) {

        final ArrayList<Cut> toExplore = new ArrayList<>();
        for (int actionId = changedActions.nextSetBit(0);
             actionId >= 0;
             actionId = changedActions.nextSetBit(actionId + 1)) {
            collectChangedActionEdges(jg, actionId, toExplore);
        }
        return updateJG(jg, gs, toExplore);
    }

    private void collectChangedActionEdges(JGraph jg, int actionId, Collection<Cut> toExplore) {
        if (jg.actionEdges[actionId] != null) {
            for (var v : jg.actionEdges[actionId]) {
                toExplore.add(new Cut(actionId, v));
            }
        }
    }

    private Float updateJG(JGraph jg, State gs, Collection<Cut> toExplore) {

        final FibonacciHeap heap = new FibonacciHeap();
        Arrays.fill(getClosed(), false);
        nodeOf = new FibonacciHeapNode[cp.numActions()];
        for (final Cut cut : toExplore) {
            final int v = cut.cond;
            final float supporterCost = getActionCost()[cut.act] == 0f
                    ? actionHCost[cut.act]
                    : computeSupporterCost(v, cut.act, gs,true);
            if (updateIfNeeded(v, supporterCost)) {
                updateActions(v, heap);
            }
        }

        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            jg.actionEdges[actionId] = new IntArrayList();
            closed[actionId] = true;
            if (actionId != cp.goal()) {
                final Collection<Integer>  conditionsAchievableByAction = getConditionsAchievableById(actionId);
                for (final int conditionId : conditionsAchievableByAction) {
                    if (!getConditionInit()[conditionId]) {
                        addConditionToEdges(jg.actionEdges, actionId, conditionId);
                        final float supporterCost = getActionCost()[actionId] == 0f
                                ? actionHCost[actionId]
                                : computeSupporterCost(conditionId, actionId, gs,true);
                        if (updateIfNeeded(conditionId, supporterCost)) {
                            updateActions(conditionId, heap);
                        }
                    }
                }
            } else if (getActionHCost()[actionId] == 0f) {
                break;
            }

        }
        return getActionHCost()[cp.goal()];
    }

    Pair<JGraph, Float> constructJG(State gs) {
        final IntArrayList[] actionEdges = new IntArrayList[cp.numActions()];
        final IntArrayList[] reverseEdges = new IntArrayList[getTotNumberOfTerms() + 1];
        reverseEdges[root] = new IntArrayList();

        Arrays.fill(getActionHCost(), Float.MAX_VALUE);
        Arrays.fill(getConditionCost(), Float.MAX_VALUE);
        Arrays.fill(getClosed(), false);
        Arrays.fill(getConditionInit(), false);
        resetPCF();
//        allAchievers = new IntArrayList[getTotNumberOfTerms()];

        final FibonacciHeap heap = new FibonacciHeap();
        for (final int conditionId : allConditions) {
            if (gs.satisfy(Terminal.getTerminal(conditionId))) {
                conditionCost[conditionId] = 0f;
                conditionInit[conditionId] = true;
            }
            updateActions(conditionId, heap);
        }
        for (final int conditionId : getAllConditions()) {
            reverseEdges[conditionId] = new IntArrayList();
        }

        for (final int freePreconditionAction : freePreconditionActions) {
            getActionHCost()[freePreconditionAction] = 0f;
            addActionsInPriority(freePreconditionAction, heap, 0f);
            setPCF(root, freePreconditionAction);
        }

        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            if (!getClosed()[actionId]) {
                getClosed()[actionId] = true;
                nodeOf[actionId] = null;
                if (actionId != cp.goal()) {
                    final Collection<Integer>  conditionsAchievableByAction = getConditionsAchievableById(actionId);
                    for (final int conditionId : conditionsAchievableByAction) {
                        if (!getConditionInit()[conditionId]) {
                            addConditionToEdges(actionEdges, actionId, conditionId);
                            reverseEdges[conditionId].add(actionId);
                            final float supporterCost = getActionCost()[actionId] == 0f
                                    ? actionHCost[actionId]
                                    : computeSupporterCost(conditionId, actionId, gs, true);
                            if (updateIfNeeded(conditionId, supporterCost)) {
                                updateActions(conditionId, heap);
                            }
                        }
                    }
                } else if (getActionHCost()[actionId] == 0f) {
                    break;
                }
            }
        }
        return Pair.of(new JGraph(actionEdges, reverseEdges), getActionHCost()[cp.goal()]);
    }

    private void resetPCF() {
        for (int actionId = 0; actionId < pcf.length; actionId++) {
            final int conditionId = pcf[actionId];
            if (conditionId >= 0 && pcf2Actions[conditionId] != null) {
                pcf2Actions[conditionId].clear(actionId);
            }
            pcf[actionId] = -1;
        }
    }

    private void setPCF(int conditionId, int actionId) {
        final int previousConditionId = pcf[actionId];
        if (previousConditionId == conditionId) {
            return;
        }
        if (previousConditionId >= 0 && pcf2Actions[previousConditionId] != null) {
            pcf2Actions[previousConditionId].clear(actionId);
        }

        pcf[actionId] = conditionId;
        if (pcf2Actions[conditionId] == null){
            pcf2Actions[conditionId] = new BitSet(cp.numActions());
        }
        pcf2Actions[conditionId].set(actionId);
    }

    void addConditionToEdges(IntArrayList[] actionEdges, int actionId, int conditionId) {
        if (actionEdges[actionId] == null) {
            actionEdges[actionId] = new IntArrayList();
        }
        actionEdges[actionId].add(conditionId);

    }

    @Override
    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions != null) {
            for (final int actionId : actions) {
                if (!closed[actionId]) {
                    final CostJustifier justifier = estimateCostLMCUT(
                            cp.preconditionFunction()[actionId],
                            actionHCost[actionId]
                    );
                    final float value = justifier.cost();
                    final int justifierId = justifier.conditionId();
                    if (value < Float.MAX_VALUE && !getActionInit()[actionId]) {
                        if (value < getActionHCost()[actionId]) {
                            if (getActionHCost()[actionId] == Float.MAX_VALUE) {
                                getActionHCost()[actionId] = value;
                                addActionsInPriority(actionId, heap, value);
                            } else {
                                getActionHCost()[actionId] = value;
                                if (getNodeOf()[actionId] == null){
                                    addActionsInPriority(actionId, heap, value);
                                }else {
                                    heap.decreaseKey(getNodeOf()[actionId], value);
                                }
                            }
                        }
                        if (value == 0f || justifierId == NOBEST) {
                            if (pcf[actionId] == -1) {
                                setPCF(root, actionId);
                            }
                        }else{
                            if (pcf[actionId] != justifierId) {
                                setPCF(justifierId, actionId);
                            }
                        }
                    }
                }
            }

        }
    }

    protected void resetEvalComparison(){
        evalComparison = new double[getTotNumberOfTerms()];
        Arrays.fill(evalComparison, Double.NEGATIVE_INFINITY);
    }

    @Override
    public float computeEstimate(State gs) {
        float cost = 0f;
        boolean firstTime = true;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        resetEvalComparison();
        final boolean[] actionInCut = new boolean[cp.numActions()];
        final IntArrayList actionsInCut = new IntArrayList();
        JGraph justificationGraph = null;
        while (true) {
            final boolean[] goalZone = new boolean[getTotNumberOfTerms() + 1];

            if (firstTime) {
                final Pair<JGraph, Float> jGraphAndValue = constructJG(gs);
                justificationGraph = jGraphAndValue.getFirst();
                final float goalValue = jGraphAndValue.getSecond();

                if (goalValue == 0f || goalValue == Float.MAX_VALUE) {
                    return firstTime ? goalValue : cost;
                }
            }

            firstTime = false;

            markGoalZone(justificationGraph, pcf[cp.goal()], goalZone);
            final Collection<Cut> cuts = computeCuts(justificationGraph, goalZone);
            if (cuts.isEmpty()) {
                return cost;
            }

            float min = Float.POSITIVE_INFINITY;
            final float[] actionOut = new float[cp.numActions()];
            Arrays.fill(actionOut, Float.POSITIVE_INFINITY);
            actionsInCut.clear();
            for (final var cut : cuts) {
                if (!actionInCut[cut.act]) {
                    actionInCut[cut.act] = true;
                    actionsInCut.add(cut.act);
                }
                final float supporterCost = computeSupporterCost(cut.cond, cut.act, gs,false);
                if (supporterCost < min) {
                    min = supporterCost;
                }
                actionOut[cut.act] = Math.min(actionOut[cut.act], supporterCost/getActionCost()[cut.act]);
            }

            if (min < NUMERIC_PRECISION) {
                return cost;
            }
            for (final int actionId : actionsInCut) {
                getActionCost()[actionId] = Math.max(
                        getActionCost()[actionId] - min / actionOut[actionId],
                        0f
                );
                actionInCut[actionId] = false;
            }
            cost += min;
            Float res = updateJG(justificationGraph,gs,actionsInCut);
            if (res == 0f){
                return cost;
            }
        }
    }

    @Override
    public float[] getActionCost() {
        return reducedCosts;
    }

    private void markGoalZone(JGraph graph, int starting, boolean[] goalZone) {
        final IntArrayList stack = new IntArrayList();
        goalZone[starting] = true;
        stack.add(starting);

        while (!stack.isEmpty()) {
            final int current = stack.removeInt(stack.size() - 1);
            for (final int actionId : graph.ERev[current]) {
                if (getActionCost()[actionId] <= 0f) {
                    final int predecessor = pcf[actionId];
                    if (!goalZone[predecessor]) {
                        goalZone[predecessor] = true;
                        stack.add(predecessor);
                    }
                }
            }
        }
    }

    private Collection<Cut> computeCuts(JGraph graph, boolean[] goalZone) {
        final Collection<Cut> cuts = new ArrayList<>();
        final IntArrayFIFOQueue queue = new IntArrayFIFOQueue();
        final boolean[] visitedCondition = new boolean[totNumberOfTerms + 1];
        visitedCondition[root] = true;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            final int current = queue.dequeueInt();
            if (pcf2Actions[current] != null) {
                for (int act = pcf2Actions[current].nextSetBit(0);
                     act >= 0;
                     act = pcf2Actions[current].nextSetBit(act + 1)) {
                    if (graph.actionEdges[act] != null) {
                        for (var cond : graph.actionEdges[act]) {
                            if (goalZone[cond]) {
                                cuts.add(new Cut(act, cond));
                            } else if (!visitedCondition[cond]) {
                                visitedCondition[cond] = true;
                                queue.enqueue(cond);
                            }
                        }
                    }
                }
            }
        }
        return cuts;
    }

    private CostJustifier estimateCostLMCUT(final Condition c, float previous) {
        if (c instanceof AndCond and) {
            if (and.sons == null) {
                return new CostJustifier(0f, NOBEST);
            }
            float ret = 0f;
            int best = NOBEST;
            for (final var son : and.sons) {
                final CostJustifier child = estimateCostLMCUT((Condition) son, previous);
                if (child.cost() == Float.MAX_VALUE) {
                    return new CostJustifier(Float.MAX_VALUE, NOBEST);
                }
                if (ret < child.cost()) {
                    ret = child.cost();
                    best = child.conditionId();
                }
            }
            return new CostJustifier(ret, best);
        } else if (c instanceof OrCond or) {
            if (or.sons == null) {
                return new CostJustifier(0f, NOBEST);
            }
            float ret = Float.POSITIVE_INFINITY;
            int best = NOBEST;
            for (final var son : or.sons) {
                final CostJustifier child = estimateCostLMCUT((Condition) son, previous);
                if (ret > child.cost()) {
                    ret = child.cost();
                    best = child.conditionId();
                    if (ret == 0f){
                        return new CostJustifier(0f, NOBEST);
                    }
                }
            }
            return new CostJustifier(ret, best);
        } else if (c instanceof Terminal t) {
            return new CostJustifier(getConditionCost()[t.getId()], t.getId());
        }
        throw new RuntimeException("This is not supported:" + c);
    }

    float computeSupporterCost(int conditionId, int actionId, State gs, boolean includeHeuristicCost) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        float actionCost = getActionCost()[actionId];
        final float heuristicCost = includeHeuristicCost ? getActionHCost()[actionId] : 0f;

        if (!(terminal instanceof Comparison comparison)) {
            return heuristicCost + actionCost;
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution == UNKNOWNEFFECT) {
            actionCost = 0f;
        }else if (contribution == 0f) {
            return -1f;
        }

        final float repetitions = computeRepetitions(comparison, contribution, gs);
        return heuristicCost + repetitions * actionCost;
    }

    protected float computeRepetitions(Comparison comparison, float contribution, State state) {
        if (evalComparison[comparison.getId()] == Double.NEGATIVE_INFINITY){
            evalComparison[comparison.getId()] = comparison.getLeft().eval(state);
        }
        return computeNumericRepetitions(
                comparison,
                contribution,
                evalComparison[comparison.getId()]
        );
    }
}
