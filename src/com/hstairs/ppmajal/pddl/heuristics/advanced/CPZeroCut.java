package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import org.jgrapht.util.FibonacciHeap;

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
        final float preconditionCost = getActionHCost()[actionId];
        if (!(terminal instanceof Comparison comparison)) {
            return preconditionCost + getActionCost()[actionId];
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution > 0f) {
            return computeNumericAchieverCost(
                    conditionId,
                    actionId,
                    numericEffectCost(actionId, comparison, contribution, state),
                    state
            );
        }
        if (contribution == UNKNOWNEFFECT) {
            return preconditionCost;
        }
        if (contribution == 0f) {
            return -1f;
        }
        return preconditionCost + numericEffectCost(
                actionId, comparison, contribution, state
        );
    }

    private float numericEffectCost(
            int actionId, Comparison comparison, float contribution, State state
    ) {
        return numericRepetitions(actionId, comparison, contribution, state)
                * getActionCost()[actionId];
    }

    private float numericRepetitions(
            int actionId, Comparison comparison, float contribution, State state
    ) {
        return computeNumericRepetitions(actionId, comparison, contribution, state);
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

    private enum ThresholdReduction {
        FULL,
        FRACTIONAL
    }

    private void zeroSupportClosureUpToThreshold(
            float threshold,
            int[] goalConditions,
            BitSet changedActions,
            BitSet visitedConditions,
            State state,
            ThresholdReduction reduction
    ) {
        resetThresholdTraversal();
        final float[] iterationCosts = iterationActionCosts;
        final float[] bestDistance = thresholdBestDistance;
        final PriorityQueue<QueuedCondition> queue = thresholdQueue;
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
            final float availableDistance = threshold - distance;
            if (availableDistance <= NUMERIC_PRECISION) {
                continue;
            }
            for (final SupportEdge edge : conditionEdges) {
                final int actionId = edge.actionId();

                final float actionCost = iterationCosts[actionId];
                final float repetitions = zeroingRepetitions(
                        actionId,
                        conditionId,
                        state
                );
                final float edgeDistance = repetitions * actionCost;
                final float retainedCost = reduction == ThresholdReduction.FULL
                        ? 0f
                        : Math.max(0f, actionCost - availableDistance);
                final float normalizedRetainedCost = retainedCost
                        <= NUMERIC_PRECISION ? 0f : retainedCost;
                if (normalizedRetainedCost < residualActionCosts[actionId]) {
                    residualActionCosts[actionId] = normalizedRetainedCost;
                    changedActions.set(actionId);
                }

                for (final int supporter : edge.supporterActions()) {
                    final int[] sourcePcf = preferredConditionsByAction[supporter];

                    if (sourcePcf == null) {
                        continue;
                    }
                    final float toPush = supporter == actionId
                            ? distance + actionCost
                            : distance + edgeDistance;
                    if (reduction == ThresholdReduction.FRACTIONAL
                            && toPush + NUMERIC_PRECISION > threshold) {
                        continue;
                    }
                    for (final int sourceCondition : sourcePcf) {
                        if (toPush + NUMERIC_PRECISION
                                < bestDistance[sourceCondition]) {
                            bestDistance[sourceCondition] = toPush;
                            queue.add(new QueuedCondition(sourceCondition, toPush));
                        }
                    }
                }
            }
        }
    }

    private float zeroingRepetitions(
            int actionId,
            int conditionId,
            State state
    ) {
        if (zeroingMode != ZeroingMode.NUM
                && zeroingMode != ZeroingMode.NUM_FRACTIONAL) {
            return 1f;
        }
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return 1f;
        }
        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            return 1f;
        }
        return Math.max(1f, numericRepetitions(actionId, comparison, contribution, state));
    }

    private void resetThresholdTraversal() {
        System.arraycopy(
                residualActionCosts,
                0,
                iterationActionCosts,
                0,
                residualActionCosts.length
        );
        Arrays.fill(thresholdBestDistance, Float.POSITIVE_INFINITY);
        thresholdQueue.clear();
    }

    public enum ZeroingMode {
        BASE,
        FLOOR,
        NUM,
        NUM_FRACTIONAL
    }

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private final ZeroingMode zeroingMode;
    private final CrdMode causalReasoningMode;
    private final int[][] singletonConditionSets;
    private final float[] residualActionCosts;
    private final float[] iterationActionCosts;
    private final float[] thresholdBestDistance;
    private final PriorityQueue<QueuedCondition> thresholdQueue;
    private final BitSet changedActions;
    private final IntArrayList supportStack;
    private final BitSet visitedConditions;

    private record SupportEdge(int actionId, int[] supporterActions) {}

    private ArrayList<SupportEdge>[] supportEdgesByCondition;
    private final int[][] preferredConditionsByAction;

    private final Long2FloatOpenHashMap crdMinima = new Long2FloatOpenHashMap();
    private final LongOpenHashSet crdUpdatedThisRound = new LongOpenHashSet();
    private LongArrayList[] crdPotentialPairsBySupporter;
    private LongArrayList[] crdActivePairsBySupporter;

    private static long supportKey(int actionId, int conditionId) {
        return ((long) actionId << 32) | (conditionId & 0xffffffffL);
    }

    private void updateCrdMinimaOnExpansion(int supporter, State state) {
        if (causalReasoningMode == CrdMode.NONE) {
            return;
        }
        final float value = getActionHCost()[supporter];
        LongArrayList potential = crdPotentialPairsBySupporter[supporter];
        if (potential == null) {
            potential = new LongArrayList();
            crdPotentialPairsBySupporter[supporter] = potential;
            cacheCrdPotentialPairs(supporter, potential);
        }
        LongArrayList pairs = crdActivePairsBySupporter[supporter];
        if (pairs == null) {
            pairs = new LongArrayList();
            crdActivePairsBySupporter[supporter] = pairs;
            cacheCrdActivePairs(supporter, state, potential, pairs);
        }
        for (final long pair : pairs) {
            final int actionId = (int) (pair >> 32);
            if (!getClosed()[actionId] && crdUpdatedThisRound.add(pair)) {
                crdMinima.put(pair, Math.min(crdMinima.getOrDefault(pair, Float.MAX_VALUE), value));
            }
        }
    }

    private void cacheCrdPotentialPairs(int supporter, LongArrayList pairs) {
        for (final int actionId : reachableTransitions) {
            for (final int conditionId : getConditionsAchievableById(actionId)) {
                if (!(Terminal.getTerminal(conditionId) instanceof Comparison)) {
                    continue;
                }
                if (potentialInterferingAchievers(actionId, conditionId).get(supporter)) {
                    pairs.add(supportKey(actionId, conditionId));
                }
            }
        }
    }

    private void cacheCrdActivePairs(
            int supporter, State state, LongArrayList potential, LongArrayList active
    ) {
        if (causalReasoningMode == CrdMode.STATIC) {
            active.addAll(potential);
            return;
        }
        for (final long pair : potential) {
            final int actionId = (int) (pair >> 32);
            final int conditionId = (int) pair;
            if (interferingAchievers(actionId, conditionId, state).get(supporter)) {
                active.add(pair);
            }
        }
    }

    @Override
    protected float computeCausalPreconditionCost(
            int conditionId, int actionId, float numericEffectCost, State state
    ) {
        return Math.min(getActionHCost()[actionId],
                crdMinima.getOrDefault(supportKey(actionId, conditionId), Float.MAX_VALUE));
    }

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
        this.residualActionCosts = new float[cp.actionCost().length];
        this.iterationActionCosts = new float[cp.actionCost().length];
        this.thresholdBestDistance = new float[getTotNumberOfTerms()];
        this.thresholdQueue = new PriorityQueue<>(
                Comparator.comparingDouble(QueuedCondition::distance)
        );
        this.changedActions = new BitSet(cp.numActions());
        this.supportStack = new IntArrayList();
        this.visitedConditions = new BitSet(getTotNumberOfTerms());
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
        Arrays.fill(nodeOf, null);
        crdMinima.clear();
        crdUpdatedThisRound.clear();
        if (crdPotentialPairsBySupporter == null) {
            crdPotentialPairsBySupporter = new LongArrayList[cp.numActions()];
        }
        crdActivePairsBySupporter = new LongArrayList[cp.numActions()];

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
            updateCrdMinimaOnExpansion(actionId, state);
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
        crdUpdatedThisRound.clear();
        Arrays.fill(getClosed(), false);
        Arrays.fill(nodeOf, null);
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

        return interferingSupporterActions(actionId, conditionId, state);
    }

    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions == null) {
            return;
        }
        for (final int actionId : actions) {
            if (!closed[actionId] && !getActionInit()[actionId]) {
                final float value = computePcfCost(
                        cp.preconditionFunction()[actionId]
                );
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

    private float computePcfCost(final Condition condition) {
        if (condition instanceof AndCond and) {
            if (and.sons == null || and.sons.length == 0) {
                return 0f;
            }
            float best = computePcfCost((Condition) and.sons[0]);
            if (best == Float.MAX_VALUE) {
                return best;
            }
            for (int i = 1; i < and.sons.length; i++) {
                final float child = computePcfCost((Condition) and.sons[i]);
                if (child == Float.MAX_VALUE) {
                    return child;
                }
                if (child > best) {
                    best = child;
                }
            }
            return best;
        }
        if (condition instanceof OrCond or) {
            if (or.sons == null || or.sons.length == 0) {
                return 0f;
            }
            float best = Float.POSITIVE_INFINITY;
            for (final var childCondition : or.sons) {
                best = Math.min(best, computePcfCost((Condition) childCondition));
            }
            return best;
        }
        if (condition instanceof Terminal terminal) {
            return getConditionCost()[terminal.getId()];
        }
        throw new RuntimeException("This is not supported:" + condition);
    }

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
        System.arraycopy(cp.actionCost(), 0, residualActionCosts, 0, residualActionCosts.length);
        ensureCrdCausalAchievers(state);
        resetNumericAchieverCostCache();
        resetNumericRepetitionCache();

        final float initialValue = buildRelaxedSupportGraph(state);
        if (initialValue == 0f || initialValue == Float.MAX_VALUE) {
            return initialValue;
        }

        changedActions.clear();
        supportStack.clear();
        visitedConditions.clear();
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
            } else if (zeroingMode == ZeroingMode.NUM_FRACTIONAL) {
                zeroSupportClosureUpToThreshold(
                        value,
                        preferredConditionsByAction[cp.goal()],
                        changedActions,
                        visitedConditions,
                        state,
                        ThresholdReduction.FRACTIONAL
                );
            } else {
                zeroSupportClosureUpToThreshold(
                        value,
                        preferredConditionsByAction[cp.goal()],
                        changedActions,
                        visitedConditions,
                        state,
                        ThresholdReduction.FULL
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
