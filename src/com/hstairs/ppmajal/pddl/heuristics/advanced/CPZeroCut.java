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
    private final float[] costsBeforeReduction;
    private final float[] thresholdBestDistance;
    private final PriorityQueue<QueuedCondition> thresholdQueue;
    private final BitSet actionsWithReducedCost;
    private final IntArrayList supportStack;
    private final BitSet visitedConditions;

    private record SupportEdge(int actionId, int[] supporterActions) {}

    private ArrayList<SupportEdge>[] supportEdgesByCondition;
    private final int[][] supportConditionsByAction;

    private final Long2FloatOpenHashMap crdMinima = new Long2FloatOpenHashMap();
    private final LongOpenHashSet crdUpdatedThisRound = new LongOpenHashSet();
    private LongArrayList[] crdPotentialPairsBySupporter;
    private LongArrayList[] crdActivePairsBySupporter;

    private record QueuedCondition(int conditionId, float distance) {}

    private record PreconditionSupport(float cost, int[] conditions) {}

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
        this.supportConditionsByAction = new int[cp.numActions()][];
        this.singletonConditionSets = new int[getTotNumberOfTerms()][];
        this.residualActionCosts = new float[cp.actionCost().length];
        this.costsBeforeReduction = new float[cp.actionCost().length];
        this.thresholdBestDistance = new float[getTotNumberOfTerms()];
        this.thresholdQueue = new PriorityQueue<>(
                Comparator.comparingDouble(QueuedCondition::distance)
        );
        this.actionsWithReducedCost = new BitSet(cp.numActions());
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

        while (true) {
            final float value = getActionHCost()[cp.goal()];
            if (value == 0f) {
                return estimate;
            }
            if (value == Float.MAX_VALUE) {
                return Float.MAX_VALUE;
            }

            estimate += value;
            actionsWithReducedCost.clear();

            final int[] goalConditions = supportConditionsByAction[cp.goal()];
            if (zeroingMode == ZeroingMode.BASE) {
                zeroCompleteSupportClosure(goalConditions);
            } else {
                reduceSupportCosts(value, goalConditions, state);
            }

            if (actionsWithReducedCost.isEmpty()) {
                throw new IllegalStateException(
                        "Positive hmax value without a positive-cost action in its support closure"
                );
            }
            propagateCostChanges(state);
        }
    }

    private float buildRelaxedSupportGraph(State state) {
        supportEdgesByCondition = new ArrayList[totNumberOfTerms];
        Arrays.fill(supportConditionsByAction, null);
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
            supportConditionsByAction[actionId] = EMPTY_INT_ARRAY;
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
            final PreconditionSupport preconditionSupport = selectPreconditionSupport(
                    cp.preconditionFunction()[actionId]
            );
            supportConditionsByAction[actionId] = preconditionSupport.conditions();
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

    private void propagateCostChanges(State state) {
        final FibonacciHeap heap = new FibonacciHeap();
        crdUpdatedThisRound.clear();
        Arrays.fill(getClosed(), false);
        Arrays.fill(nodeOf, null);
        for (int actionId = actionsWithReducedCost.nextSetBit(0); actionId >= 0;
             actionId = actionsWithReducedCost.nextSetBit(actionId + 1)) {
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

    @Override
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

    @Override
    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions == null) {
            return;
        }
        for (final int actionId : actions) {
            if (!closed[actionId] && !getActionInit()[actionId]) {
                final float value = computePreconditionCost(
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

    private float computePreconditionCost(final Condition condition) {
        if (condition instanceof AndCond and) {
            if (and.sons == null || and.sons.length == 0) {
                return 0f;
            }
            float best = computePreconditionCost((Condition) and.sons[0]);
            if (best == Float.MAX_VALUE) {
                return best;
            }
            for (int i = 1; i < and.sons.length; i++) {
                final float child = computePreconditionCost((Condition) and.sons[i]);
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
            for (final Object childCondition : or.sons) {
                best = Math.min(best, computePreconditionCost((Condition) childCondition));
            }
            return best;
        }
        if (condition instanceof Terminal terminal) {
            return getConditionCost()[terminal.getId()];
        }
        throw new RuntimeException("This is not supported:" + condition);
    }

    private PreconditionSupport selectPreconditionSupport(final Condition condition) {
        if (condition instanceof AndCond and) {
            if (and.sons == null || and.sons.length == 0) {
                return new PreconditionSupport(0f, EMPTY_INT_ARRAY);
            }

            PreconditionSupport best = null;
            for (final Object childCondition : and.sons) {
                final PreconditionSupport child = selectPreconditionSupport(
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
                return new PreconditionSupport(0f, EMPTY_INT_ARRAY);
            }
            float value = Float.POSITIVE_INFINITY;
            // Keep support conditions from every alternative, even though
            // the OR cost is the minimum of the alternative costs.
            final IntArraySet conditions = new IntArraySet();
            for (final Object childCondition : or.sons) {
                final PreconditionSupport child = selectPreconditionSupport(
                        (Condition) childCondition
                );
                value = Math.min(value, child.cost());
                for (final int conditionId : child.conditions()) {
                    conditions.add(conditionId);
                }
            }
            return new PreconditionSupport(value, conditions.toIntArray());
        }
        if (condition instanceof Terminal terminal) {
            final int id = terminal.getId();
            return new PreconditionSupport(
                    getConditionCost()[id],
                    singletonConditionSets[id]
            );
        }
        throw new RuntimeException("This is not supported:" + condition);
    }

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
        return computeNumericRepetitions(actionId, comparison, contribution, state)
                * getActionCost()[actionId];
    }

    private void zeroCompleteSupportClosure(int[] goalConditions) {
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
            for (final SupportEdge edge : conditionEdges) {
                final int actionId = edge.actionId();

                if (residualActionCosts[actionId] != 0f) {
                    residualActionCosts[actionId] = 0f;
                    actionsWithReducedCost.set(actionId);
                }

                for (final int source : edge.supporterActions()) {
                    final int[] sourceConditions = supportConditionsByAction[source];
                    if (sourceConditions == null || sourceConditions.length == 0) {
                        continue;
                    }
                    for (final int sourceCondition : sourceConditions) {
                        if (!visitedConditions.get(sourceCondition)) {
                            supportStack.add(sourceCondition);
                        }
                    }
                }
            }
        }
    }

    private void reduceSupportCosts(
            float threshold,
            int[] goalConditions,
            State state
    ) {
        resetThresholdTraversal();
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
            final float remainingBudget = threshold - distance;
            if (remainingBudget <= NUMERIC_PRECISION) {
                continue;
            }
            for (final SupportEdge edge : conditionEdges) {
                final int actionId = edge.actionId();

                final float actionCost = costsBeforeReduction[actionId];
                final float repetitions = repetitionsForCostReduction(
                        actionId,
                        conditionId,
                        state
                );
                final float edgeDistance = repetitions * actionCost;
                float candidateResidualCost = 0f;
                if (zeroingMode == ZeroingMode.NUM_FRACTIONAL) {
                    candidateResidualCost = Math.max(
                            0f, actionCost - remainingBudget / repetitions
                    );
                }
                lowerResidualActionCost(actionId, candidateResidualCost);

                for (final int supporter : edge.supporterActions()) {
                    final int[] sourceConditions = supportConditionsByAction[supporter];

                    if (sourceConditions == null) {
                        continue;
                    }
                    float preconditionDistance;
                    if (supporter == actionId) {
                        // Follow the activation floor: the action's own
                        // preconditions are preceded by one application.
                        preconditionDistance = distance + actionCost;
                    } else {
                        preconditionDistance = distance + edgeDistance;
                    }
                    if (zeroingMode == ZeroingMode.NUM_FRACTIONAL
                            && preconditionDistance + NUMERIC_PRECISION > threshold) {
                        continue;
                    }
                    for (final int sourceCondition : sourceConditions) {
                        if (preconditionDistance + NUMERIC_PRECISION
                                < bestDistance[sourceCondition]) {
                            bestDistance[sourceCondition] = preconditionDistance;
                            queue.add(new QueuedCondition(sourceCondition, preconditionDistance));
                        }
                    }
                }
            }
        }
    }

    private void lowerResidualActionCost(int actionId, float candidateCost) {
        if (candidateCost <= NUMERIC_PRECISION) {
            candidateCost = 0f;
        }
        // Keep the smallest candidate; repeated visits do not add reductions.
        if (candidateCost < residualActionCosts[actionId]) {
            residualActionCosts[actionId] = candidateCost;
            actionsWithReducedCost.set(actionId);
        }
    }

    private float repetitionsForCostReduction(
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
        return Math.max(1f, computeNumericRepetitions(actionId, comparison, contribution, state));
    }

    private void resetThresholdTraversal() {
        System.arraycopy(
                residualActionCosts,
                0,
                costsBeforeReduction,
                0,
                residualActionCosts.length
        );
        Arrays.fill(thresholdBestDistance, Float.POSITIVE_INFINITY);
        thresholdQueue.clear();
    }

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
}
