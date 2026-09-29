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

    float computeSupporterCost(
            int conditionId,
            int actionId,
            State state,
            boolean includeHeuristicCost
    ) {
        if (!includeHeuristicCost) {
            return computeBaseSupporterCost(conditionId, actionId, state, false);
        }

        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return computeBaseSupporterCost(conditionId, actionId, state, true);
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            return computeBaseSupporterCost(conditionId, actionId, state, true);
        }

        final float repetitions = computeRepetitions(
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
        return heuristicCost + computeRepetitions(
                actionId, comparison, contribution, state) * actionCost;
    }

    private float computeRepetitions(
            int actionId,
            Comparison comparison,
            float contribution,
            State state
    ) {
        return computeNumericRepetitions(actionId, comparison, contribution, state);
    }

    private void setToZero(
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

            final ArrayList<Edge> conditionEdges = incomingEdges[currentCondition];
            if (conditionEdges == null) {
                continue;
            }
            for (final Edge e : conditionEdges) {
                final int actionId = e.actionId();

                if (reducedCosts[actionId] != 0f) {
                    reducedCosts[actionId] = 0f;
                    changedActions.set(actionId);
                }

                for (final int source : e.supporters()) {
                    final int[] sourcePcf = pcf[source];
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
//
//    private void setToZeroSelectively(
//            float threshold,
//            JGraph justificationGraph,
//            State state,
//            BitSet closed,
//            BitSet changedActions
//    ) {
//        final float[] iterationCosts = Arrays.copyOf(reducedCosts, reducedCosts.length);
//        final float[] bestDistance = new float[justificationGraph.size()];
//        Arrays.fill(bestDistance, Float.POSITIVE_INFINITY);
//        final BitSet closedEdges = new BitSet(justificationGraph.size());
//
//        final PriorityQueue<EdgeDistance> queue = new PriorityQueue<>(
//                Comparator.comparingDouble(EdgeDistance::distance)
//        );
//        final int[] goalConditions = pcf[cp.goal()];
//        for (final int conditionId : goalConditions) {
//            enqueueIncomingEdges(
//                    conditionId,
//                    0f,
//                    justificationGraph,
//                    state,
//                    iterationCosts,
//                    bestDistance,
//                    queue
//            );
//        }
//
//        while (!queue.isEmpty()) {
//            final EdgeDistance current = queue.poll();
//            final int edgeId = current.edgeId();
//            final float distance = current.distance();
//
//            if (closedEdges.get(edgeId)
//                    || distance > bestDistance[edgeId] + NUMERIC_PRECISION) {
//                continue;
//            }
//            closedEdges.set(edgeId);
//
//            final HyperEdge edge = justificationGraph.edge(edgeId);
//            final int actionId = edge.actionId();
//            if (!closed.get(actionId)) {
//                closed.set(actionId);
//                if (reducedCosts[actionId] != 0f) {
//                    reducedCosts[actionId] = 0f;
//                    changedActions.set(actionId);
//                }
//            }
//            if (distance + NUMERIC_PRECISION >= threshold) {
//                continue;
//            }
//
//            for (final int source : edge.sources()) {
//                enqueueIncomingEdges(
//                        source,
//                        distance,
//                        justificationGraph,
//                        state,
//                        iterationCosts,
//                        bestDistance,
//                        queue
//                );
//            }
//        }
//    }
//
//    private void enqueueIncomingEdges(
//            int conditionId,
//            float distance,
//            JGraph graph,
//            State state,
//            float[] iterationCosts,
//            float[] bestDistance,
//            PriorityQueue<EdgeDistance> queue
//    ) {
//        final IntArrayList incomingEdges = graph.incomingEdges(conditionId);
//        if (incomingEdges == null) {
//            return;
//        }
//        for (final int edgeId : incomingEdges) {
//            final HyperEdge edge = graph.edge(edgeId);
//            final int actionId = edge.actionId();
//            final float candidateDistance = distance
//                    + zeroingMultiplier(actionId, conditionId, state)
//                    * iterationCosts[actionId];
//            if (candidateDistance + NUMERIC_PRECISION < bestDistance[edgeId]) {
//                bestDistance[edgeId] = candidateDistance;
//                queue.add(new EdgeDistance(edgeId, candidateDistance));
//            }
//        }
//    }
//
//    private float zeroingMultiplier(
//            int actionId,
//            int conditionId,
//            State state
//    ) {
//        if (zeroingMode != ZeroingMode.NUM) {
//            return 1f;
//        }
//        final Terminal terminal = Terminal.getTerminal(conditionId);
//        if (!(terminal instanceof Comparison comparison)) {
//            return 1f;
//        }
//        final float contribution = numericContribution(actionId, comparison);
//        // Repetitions are not compositional when supporting the action already
//        // contributes to this condition.
//        if (contribution <= 0f
//                || !isKnownInterferenceFree(actionId, conditionId, state)) {
//            return 1f;
//        }
//        return Math.max(
//                1f,
//                computeRepetitions(actionId, comparison, contribution, state)
//        );
//    }

    public enum ZeroingMode {
        BASE,
        FLOOR,
        NUM
    }

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private final ZeroingMode zeroingMode;
    private final CrdMode crdMode;
    private final int[][] terminalConditions;
    private float[] reducedCosts;

    private record Edge(int actionId, int[] supporters) {}

    private ArrayList<Edge>[] incomingEdges;
    private final int[][] pcf;
//    private final int[][] allSupportersByCondition;
//    private final int[][] selfSupporterByAction;

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
        this.crdMode = crdMode;
        this.pcf = new int[cp.numActions()][];
//        this.allSupportersByCondition = buildAllSupportersByCondition();
//        this.selfSupporterByAction = new int[cp.numActions()][];
//        for (int actionId = 0; actionId < cp.numActions(); actionId++) {
//            selfSupporterByAction[actionId] = new int[] { actionId };
//        }
        this.terminalConditions = new int[getTotNumberOfTerms()][];
        for (int i = 0; i < getTotNumberOfTerms(); i++) {
            this.terminalConditions[i] = new int[] { i };
        }
        if (zeroingMode != ZeroingMode.BASE && !useNumericActivationFloor) {
            throw new IllegalArgumentException(
                    "Selective zeroing requires the numeric activation floor"
            );
        }
        this.zeroingMode = zeroingMode;
    }

    private int[][] buildAllSupportersByCondition() {
        final IntArrayList[] supporters = new IntArrayList[getTotNumberOfTerms()];
        for (final int actionId : allActions) {
            if (actionId == cp.goal()) {
                continue;
            }
            for (final int conditionId : getConditionsAchievableById(actionId)) {
                IntArrayList conditionSupporters = supporters[conditionId];
                if (conditionSupporters == null) {
                    conditionSupporters = new IntArrayList();
                    supporters[conditionId] = conditionSupporters;
                }
                conditionSupporters.add(actionId);
            }
        }

        final int[][] result = new int[getTotNumberOfTerms()][];
        for (int conditionId = 0; conditionId < supporters.length; conditionId++) {
            final IntArrayList conditionSupporters = supporters[conditionId];
            result[conditionId] = conditionSupporters == null
                    ? EMPTY_INT_ARRAY
                    : conditionSupporters.toIntArray();
        }
        return result;
    }

    private Float constructJG(State state) {
        incomingEdges = new ArrayList[totNumberOfTerms];
        Arrays.fill(pcf, null);
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
            pcf[actionId] = EMPTY_INT_ARRAY;
            addActionsInPriority(actionId, heap, 0f);
        }

        propagate(state, heap, false);
        return getActionHCost()[cp.goal()];
    }

    private void propagate(State state, FibonacciHeap heap, boolean updating) {
        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            if (getClosed()[actionId]) {
                continue;
            }
            getClosed()[actionId] = true;
            nodeOf[actionId] = null;
            final PreferredConditions preferredConditions = estimateCostCPZeroCut(
                    cp.preconditionFunction()[actionId]
            );
            pcf[actionId] = preferredConditions.conditions();
            if (actionId == cp.goal()) {
                if (getActionHCost()[actionId] == 0f) {
                    break;
                }
                continue;
            }
            for (final int conditionId : getConditionsAchievableById(actionId)) {
                if (!getConditionInit()[conditionId]) {
                    if (!updating)
                        putHyperEdge(actionId, conditionId, state);
                    updateCondition(conditionId, actionId, state, heap);
                }
            }
        }
    }

    private void updateJG(State state, BitSet changedActions) {
        final FibonacciHeap heap = new FibonacciHeap();
        Arrays.fill(getClosed(), false);
        nodeOf = new FibonacciHeapNode[cp.numActions()];
        for (int actionId = changedActions.nextSetBit(0); actionId >= 0;
             actionId = changedActions.nextSetBit(actionId + 1)) {
            for (final int conditionId : getConditionsAchievableById(actionId)) {
                if (!getConditionInit()[conditionId]) {
                    updateCondition(conditionId, actionId, state, heap);
                }
            }
        }
        propagate(state, heap, true);
    }

    private void updateCondition(
            int conditionId,
            int actionId,
            State state,
            FibonacciHeap heap
    ) {
        final float supporterCost = getActionCost()[actionId] == 0f
                ? getActionHCost()[actionId]
                : computeSupporterCost(conditionId, actionId, state, true);
        if (updateIfNeeded(conditionId, supporterCost)) {
            updateActions(conditionId, heap);
        }
    }

    private void putHyperEdge(
            int actionId,
            int conditionId,
            State state
    ) {
        ArrayList<Edge> conditionEdges = incomingEdges[conditionId];
        if (conditionEdges == null) {
            conditionEdges = new ArrayList<>();
            incomingEdges[conditionId] = conditionEdges;
        }
        conditionEdges.add(new Edge(
                actionId,
                supportersFor(actionId, conditionId, state)
        ));
    }

    protected void updateAchievers(int conditionId, int actionId) {
            getOrCreateAchievers(conditionId).add(actionId);
    }

    private int[] supportersFor(
            int actionId,
            int conditionId,
            State state
    ) {
        if (!(Terminal.getTerminal(conditionId) instanceof Comparison)) {
            return new int[] { actionId };
        }
        if (crdMode == CrdMode.NONE) {
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

//    private void putHyperEdge(
//            JGraph graph,
//            int actionId,
//            int conditionId,
//            State state
//    ) {
//        final BitSet supportActions = new BitSet(cp.numActions());
//        supportActions.set(actionId);
//        if (crdMode != CrdMode.NONE) {
//            supportActions.or(interferingAchievers(actionId, conditionId, state));
//        }
//        graph.putEdge(
//                actionId,
//                conditionId,
//                supportActions,
//                collectSources(supportActions)
//        );
//    }



    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions == null) {
            return;
        }
        for (final int actionId : actions) {
            //optimise here later
            final PreferredConditions preferred = estimateCostCPZeroCut(
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

    private record PreferredConditions(float cost, int[] conditions) {}

    private PreferredConditions estimateCostCPZeroCut(final Condition condition) {
        if (condition instanceof AndCond and) {
            if (and.sons == null || and.sons.length == 0) {
                return new PreferredConditions(0f, EMPTY_INT_ARRAY);
            }

            PreferredConditions best = null;
            for (final var childCondition : and.sons) {
                final PreferredConditions child = estimateCostCPZeroCut(
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
                return new PreferredConditions(0f, EMPTY_INT_ARRAY);
            }
            float value = Float.POSITIVE_INFINITY;
            final IntArraySet conditions = new IntArraySet();
            for (final var childCondition : or.sons) {
                final PreferredConditions child = estimateCostCPZeroCut(
                        (Condition) childCondition
                );
                value = Math.min(value, child.cost);
                for (final int conditionId : child.conditions()) {
                    conditions.add(conditionId);
                }
            }
            return new PreferredConditions(value, conditions.toIntArray());
        }
        if (condition instanceof Terminal terminal) {
            final int id = terminal.getId();
            return new PreferredConditions(
                    getConditionCost()[id],
                    terminalConditions[id]
            );
        }
        throw new RuntimeException("This is not supported:" + condition);
    }

    @Override
    public float[] getActionCost() {
        return reducedCosts;
    }

    @Override
    public float computeEstimate(State state) {
        float estimate = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        ensureCrdCausalAchievers(state);
        resetNumericAchieverCostCache();
        resetNumericRepetitionCache();

        final float initialValue = constructJG(state);
        if (initialValue == 0f || initialValue == Float.MAX_VALUE) {
            return initialValue;
        }

        final BitSet closed = new BitSet(cp.numActions());
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
                closed.clear();
                changedActions.clear();

                setToZero(
                        pcf[cp.goal()],
                        changedActions,
                        supportStack,
                        visitedConditions
                );


                if (changedActions.isEmpty()) {
                    throw new IllegalStateException(
                            "Positive hmax value without a positive-cost action in its support closure"
                    );
                }
                updateJG(state, changedActions);
            }
        }
    }
