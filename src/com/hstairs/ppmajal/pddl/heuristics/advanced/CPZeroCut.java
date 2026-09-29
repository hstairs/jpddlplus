package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.*;

/**
 * Critical-path heuristic with complete or threshold-selective support zeroing.
 */
public class CPZeroCut extends H1 {

    float computeRelaxedSupporterCost(
            int conditionId,
            int actionId,
            State state
    ) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return computeBaseSupporterCost(conditionId, actionId, state, true);
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            return computeBaseSupporterCost(conditionId, actionId, state, true);
        }

        final float repetitions = computeNumericRepetitions(
                actionId,
                comparison,
                contribution,
                state
        );
        return computeNumericAchieverCost(
                conditionId,
                actionId,
                repetitions * getActionCost()[actionId],
                state
        );
    }

    private float computeBaseSupporterCost(
            int conditionId,
            int actionId,
            State state,
            boolean includeHeuristicCost
    ) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        float actionCost = getActionCost()[actionId];
        final float heuristicCost = includeHeuristicCost ? getActionHCost()[actionId] : 0f;
        if (!(terminal instanceof Comparison comparison)) {
            return heuristicCost + actionCost;
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution == UNKNOWNEFFECT) {
            actionCost = 0f;
        } else if (contribution == 0f) {
            return -1f;
        }
        return heuristicCost + computeNumericRepetitions(
                actionId, comparison, contribution, state) * actionCost;
    }

    private void zeroCompleteSupportClosure(
            int[] goalConditions,
            BitSet changedActions,
            IntArrayList supportStack,
            BitSet visitedConditions
    ) {
        supportStack.clear();
        visitedConditions.clear();
        for (final int conditionId : goalConditions) {
            supportStack.add(conditionId);
        }
        while (!supportStack.isEmpty()) {
            final int currentCondition = supportStack.removeInt(supportStack.size() - 1);
            if (visitedConditions.get(currentCondition)) {
                continue;
            }
            visitedConditions.set(currentCondition);

            final ArrayList<SupportEdge> conditionEdges = supportEdgesByCondition[currentCondition];
            if (conditionEdges == null) {
                continue;
            }
            for (final SupportEdge e : conditionEdges) {
                final int actionId = e.actionId();

                if (residualActionCosts[actionId] != 0f) {
                    residualActionCosts[actionId] = 0f;
                    changedActions.set(actionId);
                }

                for (final int source : e.supporterActions()) {
                    final int[] sourcePcf = preferredConditionsByAction[source];
                    if (sourcePcf == null || sourcePcf.length == 0) {
                        continue;
                    }
                    for (final int c : sourcePcf) {
                        if (!visitedConditions.get(c)) {
                            supportStack.add(c);
                        }
                    }
                }
            }
        }
    }

    private record QueuedCondition(int conditionId, float distance) {}

    private void zeroSupportClosureUpToThreshold(
            float threshold,
            int[] goalConditions,
            BitSet changedActions,
            BitSet visitedConditions,
            State state
    ) {
        final float[] iterationCosts = Arrays.copyOf(residualActionCosts, residualActionCosts.length);
        final float[] bestDistance = new float[totNumberOfTerms];
        Arrays.fill(bestDistance, Float.POSITIVE_INFINITY);
        final PriorityQueue<QueuedCondition> queue = new PriorityQueue<>(
                Comparator.comparingDouble(QueuedCondition::distance)
        );
        visitedConditions.clear();
        for (final int conditionId : goalConditions) {
            bestDistance[conditionId] = 0f;
            queue.add(new QueuedCondition(conditionId, 0f));
        }

        while (!queue.isEmpty()) {
            final QueuedCondition current = queue.poll();
            final int conditionId = current.conditionId();
            final float distance = current.distance();
            if (visitedConditions.get(conditionId)
                    || distance > bestDistance[conditionId] + NUMERIC_PRECISION) {
                continue;
            }
            visitedConditions.set(conditionId);

            final ArrayList<SupportEdge> conditionEdges = supportEdgesByCondition[conditionId];
            if (conditionEdges == null) {
                continue;
            }
            if (distance + NUMERIC_PRECISION >= threshold) {
                continue;
            }
            for (final SupportEdge edge : conditionEdges) {
                final int actionId = edge.actionId();

                if (residualActionCosts[actionId] != 0f) {
                    residualActionCosts[actionId] = 0f;
                    changedActions.set(actionId);
                }

                final float nextDistance = distance
                        + numericDistanceMultiplier(actionId, conditionId, state)
                        * iterationCosts[actionId];
                for (final int supporter : edge.supporterActions()) {
                    final int[] sourcePcf = preferredConditionsByAction[supporter];
                    if (sourcePcf == null) {
                        continue;
                    }
                    for (final int sourceCondition : sourcePcf) {
                        if (nextDistance + NUMERIC_PRECISION
                                < bestDistance[sourceCondition]) {
                            bestDistance[sourceCondition] = nextDistance;
                            queue.add(new QueuedCondition(sourceCondition, nextDistance));
                        }
                    }
                }
            }
        }
    }

    private float numericDistanceMultiplier(int actionId, int conditionId, State state) {
        if (zeroingMode != ZeroingMode.NUM) {
            return 1f;
        }
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return 1f;
        }
        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f || !isKnownInterferenceFree(actionId, conditionId, state)) {
            return 1f;
        }
        return Math.max(1f, computeNumericRepetitions(actionId, comparison, contribution, state));
    }
    public enum ZeroingMode {
        BASE,
        FLOOR,
        NUM
    }

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private final ZeroingMode zeroingMode;
    private final CrdMode causalReasoningMode;
    private final int[][] singletonConditionSets;
    private float[] residualActionCosts;

    private record SupportEdge(int actionId, int[] supporterActions) {}

    private ArrayList<SupportEdge>[] supportEdgesByCondition;
    private final int[][] preferredConditionsByAction;

    public CPZeroCut(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            CrdMode crdMode,
            boolean useNumericActivationFloor,
            ZeroingMode zeroingMode
    ) {
        super(problem, false, false, false, redundantConstraints,
                false, false, false, false, null, unitaryCost,
                linearEffectsAbstraction, crdMode,
                useNumericActivationFloor);
        this.causalReasoningMode = crdMode;
        this.preferredConditionsByAction = new int[cp.numActions()][];
        this.singletonConditionSets = new int[getTotNumberOfTerms()][];
        for (int i = 0; i < getTotNumberOfTerms(); i++) {
            this.singletonConditionSets[i] = new int[] { i };
        }
        if (zeroingMode != ZeroingMode.BASE && !useNumericActivationFloor) {
            throw new IllegalArgumentException(
                    "Selective zeroing requires the numeric activation floor"
            );
        }
        this.zeroingMode = zeroingMode;
    }

    private Float buildRelaxedSupportGraph(State state) {
        supportEdgesByCondition = new ArrayList[totNumberOfTerms];
        Arrays.fill(preferredConditionsByAction, null);
        Arrays.fill(getActionHCost(), Float.MAX_VALUE);
        Arrays.fill(getConditionCost(), Float.MAX_VALUE);
        Arrays.fill(getClosed(), false);
        Arrays.fill(getConditionInit(), false);
        nodeOf = new FibonacciHeapNode[cp.numActions()];

        final FibonacciHeap heap = new FibonacciHeap();
        for (final int conditionId : allConditions) {
            if (state.satisfy(Terminal.getTerminal(conditionId))) {
                conditionCost[conditionId] = 0f;
                conditionInit[conditionId] = true;
            }
            updateActions(conditionId, heap);
        }
        for (final int actionId : freePreconditionActions) {
            getActionHCost()[actionId] = 0f;
            preferredConditionsByAction[actionId] = EMPTY_INT_ARRAY;
            addActionsInPriority(actionId, heap, 0f);
        }

        propagateRelaxedCosts(state, heap, false);
        return getActionHCost()[cp.goal()];
    }

    private void propagateRelaxedCosts(State state, FibonacciHeap heap, boolean updating) {
        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            if (getClosed()[actionId]) {
                continue;
            }
            getClosed()[actionId] = true;
            nodeOf[actionId] = null;
            final PcfChoice preferredConditions = computePcfChoice(
                    cp.preconditionFunction()[actionId]
            );
            preferredConditionsByAction[actionId] = preferredConditions.conditions();
            if (actionId == cp.goal()) {
                if (getActionHCost()[actionId] == 0f) {
                    break;
                }
                continue;
            }
            for (final int conditionId : getConditionsAchievableById(actionId)) {
                if (!getConditionInit()[conditionId]) {
                    if (!updating) {
                        addSupportEdge(actionId, conditionId, state);
                    }
                    updateConditionCost(conditionId, actionId, state, heap);
                }
            }
        }
    }

    private void propagateCostChanges(State state, BitSet changedActions) {
        final FibonacciHeap heap = new FibonacciHeap();
        Arrays.fill(getClosed(), false);
        nodeOf = new FibonacciHeapNode[cp.numActions()];
        for (int actionId = changedActions.nextSetBit(0); actionId >= 0;
             actionId = changedActions.nextSetBit(actionId + 1)) {
            for (final int conditionId : getConditionsAchievableById(actionId)) {
                if (!getConditionInit()[conditionId]) {
                    updateConditionCost(conditionId, actionId, state, heap);
                }
            }
        }
        propagateRelaxedCosts(state, heap, true);
    }

    private void updateConditionCost(
            int conditionId,
            int actionId,
            State state,
            FibonacciHeap heap
    ) {
        final float supporterCost = getActionCost()[actionId] == 0f
                ? getActionHCost()[actionId]
                : computeRelaxedSupporterCost(conditionId, actionId, state);
        if (updateIfNeeded(conditionId, supporterCost)) {
            updateActions(conditionId, heap);
        }
    }

    private void addSupportEdge(
            int actionId,
            int conditionId,
            State state
    ) {
        ArrayList<SupportEdge> conditionEdges = supportEdgesByCondition[conditionId];
        if (conditionEdges == null) {
            conditionEdges = new ArrayList<>();
            supportEdgesByCondition[conditionId] = conditionEdges;
        }
        conditionEdges.add(new SupportEdge(
                actionId,
                selectSupporterActions(actionId, conditionId, state)
        ));
    }

    protected void updateAchievers(int conditionId, int actionId) {
        getOrCreateAchievers(conditionId).add(actionId);
    }

    private int[] selectSupporterActions(
            int actionId,
            int conditionId,
            State state
    ) {
        if (!(Terminal.getTerminal(conditionId) instanceof Comparison)) {
            return new int[] { actionId };
        }
        if (causalReasoningMode == CrdMode.NONE) {
            return getOrCreateAchievers(conditionId).toIntArray();
        }

        final BitSet filtered = interferingAchievers(
                actionId,
                conditionId,
                state
        );
        final boolean containsAction = filtered.get(actionId);
        final int[] supporters = new int[
                filtered.cardinality() + (containsAction ? 0 : 1)
        ];
        int index = 0;
        if (!containsAction) {
            supporters[index++] = actionId;
        }
        for (int supporter = filtered.nextSetBit(0); supporter >= 0;
             supporter = filtered.nextSetBit(supporter + 1)) {
            supporters[index++] = supporter;
        }
        return supporters;
    }

    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions == null) {
            return;
        }
        for (final int actionId : actions) {
            final PcfChoice preferred = computePcfChoice(
                    cp.preconditionFunction()[actionId]
            );
            final float value = preferred.cost();
            if (!closed[actionId] && !getActionInit()[actionId]) {
                if (value <= getActionHCost()[actionId]) {
                    getActionHCost()[actionId] = value;
                    if (getNodeOf()[actionId] == null) {
                        addActionsInPriority(actionId, heap, value);
                    } else {
                        heap.decreaseKey(getNodeOf()[actionId], value);
                    }
                }
            }
        }
    }

    private record PcfChoice(float cost, int[] conditions) {}

    private PcfChoice computePcfChoice(final Condition condition) {
        if (condition instanceof AndCond and) {
            if (and.sons == null || and.sons.length == 0) {
                return new PcfChoice(0f, EMPTY_INT_ARRAY);
            }

            PcfChoice best = null;
            for (final var childCondition : and.sons) {
                final PcfChoice child = computePcfChoice(
                        (Condition) childCondition
                );
                if (best == null || child.cost() > best.cost()) {
                    best = child;
                }
            }
            return best;
        }
        if (condition instanceof OrCond or) {
            if (or.sons == null || or.sons.length == 0) {
                return new PcfChoice(0f, EMPTY_INT_ARRAY);
            }
            float value = Float.POSITIVE_INFINITY;
            final IntArraySet conditions = new IntArraySet();
            for (final var childCondition : or.sons) {
                final PcfChoice child = computePcfChoice(
                        (Condition) childCondition
                );
                value = Math.min(value, child.cost);
                for (final int conditionId : child.conditions()) {
                    conditions.add(conditionId);
                }
            }
            return new PcfChoice(value, conditions.toIntArray());
        }
        if (condition instanceof Terminal terminal) {
            final int id = terminal.getId();
            return new PcfChoice(
                    getConditionCost()[id],
                    singletonConditionSets[id]
            );
        }
        throw new RuntimeException("This is not supported:" + condition);
    }

    @Override
    public float[] getActionCost() {
        return residualActionCosts;
    }

    @Override
    public float computeEstimate(State state) {
        float estimate = 0f;
        residualActionCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        ensureCrdCausalAchievers(state);
        resetNumericAchieverCostCache();
        resetNumericRepetitionCache();

        final float initialValue = buildRelaxedSupportGraph(state);
        if (initialValue == 0f || initialValue == Float.MAX_VALUE) {
            return initialValue;
        }

        final BitSet changedActions = new BitSet(cp.numActions());
        final IntArrayList supportStack = new IntArrayList();
        final BitSet visitedConditions = new BitSet(getTotNumberOfTerms());
        while (true) {
            final float value = getActionHCost()[cp.goal()];
            if (value == 0f) {
                return estimate;
            }
            if (value == Float.MAX_VALUE) {
                return Float.MAX_VALUE;
            }

            estimate += value;
            changedActions.clear();

            if (zeroingMode == ZeroingMode.BASE) {
                zeroCompleteSupportClosure(
                        preferredConditionsByAction[cp.goal()],
                        changedActions,
                        supportStack,
                        visitedConditions
                );
            } else {
                zeroSupportClosureUpToThreshold(
                        value,
                        preferredConditionsByAction[cp.goal()],
                        changedActions,
                        visitedConditions,
                        state
                );
            }


            if (changedActions.isEmpty()) {
                throw new IllegalStateException(
                        "Positive hmax value without a positive-cost action in its support closure"
                );
            }
            propagateCostChanges(state, changedActions);
        }
    }
}
