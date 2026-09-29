package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.*;

/**
 * Critical-path heuristic with complete or threshold-selective support zeroing.
 */
public class CPZeroCut extends H1 {

    public enum ZeroingMode {
        BASE,
        FLOOR,
        NUM
    }

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private final ZeroingMode zeroingMode;
    private final CrdMode crdMode;
    private final int[][] pcf;
    private final int[][] terminalConditions;
    private float[] reducedCosts;
    private JGraph currentGraph;

    private record EdgeDistance(int edgeId, float distance) {
    }

    private record PreferredConditions(float cost, int[] conditions) {
    }

    /** A justification hyperedge (a, c, S), with V equal to the terminals T. */
    private record HyperEdge(
            int actionId,
            int conditionId,
            int[] sources
    ) {
    }

    private static final class JGraph {
        private final ArrayList<HyperEdge> edges = new ArrayList<>();
        private final IntArrayList[] incomingEdges;
        private final IntArrayList[] actionEdges;
        private final IntArrayList[] pcfDependents;
        private final ArrayList<BitSet> supportActionsByEdge = new ArrayList<>();
        private final Map<Long, Integer> edgeIds = new HashMap<>();

        private JGraph(int numberOfConditions, int numberOfActions) {
            incomingEdges = new IntArrayList[numberOfConditions];
            actionEdges = new IntArrayList[numberOfActions];
            pcfDependents = new IntArrayList[numberOfActions];
        }

        private record edge(int actionId, int conditionId, Collection<Integer> supporters){};
        private record hyperGraph(Collection<Integer> vertexes, Collection<edge> edges){};

        private int putEdge(
                int actionId,
                int conditionId,
                BitSet supportActions,
                int[] sources
        ) {
            final long key = edgeKey(actionId, conditionId);
            final Integer existingId = edgeIds.get(key);
            if (existingId != null) {
                edges.set(existingId, new HyperEdge(
                        actionId,
                        conditionId,
                        sources
                ));
                return existingId;
            }

            final int edgeId = edges.size();
            edges.add(new HyperEdge(
                    actionId,
                    conditionId,
                    sources
            ));
            supportActionsByEdge.add(supportActions);
            edgeIds.put(key, edgeId);
            if (incomingEdges[conditionId] == null) {
                incomingEdges[conditionId] = new IntArrayList();
            }
            incomingEdges[conditionId].add(edgeId);
            if (actionEdges[actionId] == null) {
                actionEdges[actionId] = new IntArrayList();
            }
            actionEdges[actionId].add(edgeId);
            for (int supportAction = supportActions.nextSetBit(0);
                 supportAction >= 0;
                 supportAction = supportActions.nextSetBit(supportAction + 1)) {
                if (pcfDependents[supportAction] == null) {
                    pcfDependents[supportAction] = new IntArrayList();
                }
                pcfDependents[supportAction].add(edgeId);
            }
            return edgeId;
        }

        private HyperEdge edge(int edgeId) {
            return edges.get(edgeId);
        }

        private IntArrayList incomingEdges(int conditionId) {
            return incomingEdges[conditionId];
        }

        private IntArrayList actionEdges(int actionId) {
            return actionEdges[actionId];
        }

        private IntArrayList pcfDependents(int actionId) {
            return pcfDependents[actionId];
        }

        private BitSet supportActions(int edgeId) {
            return supportActionsByEdge.get(edgeId);
        }

        private void setSources(int edgeId, int[] sources) {
            final HyperEdge edge = edges.get(edgeId);
            edges.set(edgeId, new HyperEdge(
                    edge.actionId(),
                    edge.conditionId(),
                    sources
            ));
        }

        private int size() {
            return edges.size();
        }

        private static long edgeKey(int actionId, int conditionId) {
            return ((long) actionId << 32) | (conditionId & 0xffffffffL);
        }
    }

    public static CPZeroCut create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        return create(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction,
                CrdMode.NONE,
                false,
                ZeroingMode.BASE
        );
    }

    public static CPZeroCut create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            CrdMode crdMode,
            boolean useNumericActivationFloor
    ) {
        return create(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction,
                crdMode,
                useNumericActivationFloor,
                ZeroingMode.BASE
        );
    }

    public static CPZeroCut create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            CrdMode crdMode,
            boolean useNumericActivationFloor,
            boolean selectiveZeroing
    ) {
        return create(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction,
                crdMode,
                useNumericActivationFloor,
                selectiveZeroing ? ZeroingMode.FLOOR : ZeroingMode.BASE
        );
    }

    public static CPZeroCut create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            CrdMode crdMode,
            boolean useNumericActivationFloor,
            ZeroingMode zeroingMode
    ) {
        return new CPZeroCut(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction,
                crdMode,
                useNumericActivationFloor,
                zeroingMode
        );
    }

    private CPZeroCut(
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

    private Pair<JGraph, Float> constructJG(State state) {
        final JGraph graph = new JGraph(getTotNumberOfTerms(), cp.numActions());
        this.currentGraph = graph;

        Arrays.fill(getActionHCost(), Float.MAX_VALUE);
        Arrays.fill(getConditionCost(), Float.MAX_VALUE);
        Arrays.fill(getClosed(), false);
        Arrays.fill(getConditionInit(), false);
        Arrays.fill(pcf, null);
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

        propagate(graph, state, heap);
        return Pair.of(graph, getActionHCost()[cp.goal()]);
    }

    private void propagate(JGraph graph, State state, FibonacciHeap heap) {
        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            if (getClosed()[actionId]) {
                continue;
            }

            getClosed()[actionId] = true;
            nodeOf[actionId] = null;
            if (actionId == cp.goal()) {
                if (getActionHCost()[actionId] == 0f) {
                    break;
                }
                continue;
            }

            for (final int conditionId : getConditionsAchievableById(actionId)) {
                if (!getConditionInit()[conditionId]) {
                    putHyperEdge(graph, actionId, conditionId, state);
                    updateCondition(conditionId, actionId, state, heap);
                }
            }
        }
    }

    private void updateJG(JGraph graph, State state, BitSet changedActions) {
        this.currentGraph = graph;
        final FibonacciHeap heap = new FibonacciHeap();
        Arrays.fill(getClosed(), false);
        nodeOf = new FibonacciHeapNode[cp.numActions()];
        for (int actionId = changedActions.nextSetBit(0); actionId >= 0;
             actionId = changedActions.nextSetBit(actionId + 1)) {
            final IntArrayList edgeIds = graph.actionEdges(actionId);
            if (edgeIds == null) {
                continue;
            }
            for (final int edgeId : edgeIds) {
                final HyperEdge edge = graph.edge(edgeId);
                updateCondition(edge.conditionId(), actionId, state, heap);
            }
        }
        propagate(graph, state, heap);
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
            JGraph graph,
            int actionId,
            int conditionId,
            State state
    ) {
        final BitSet supportActions = new BitSet(cp.numActions());
        supportActions.set(actionId);
        if (crdMode != CrdMode.NONE) {
            supportActions.or(interferingAchievers(actionId, conditionId, state));
        }
        graph.putEdge(
                actionId,
                conditionId,
                supportActions,
                collectSources(supportActions)
        );
    }

    private int[] collectSources(BitSet supportActions) {
        final IntArraySet sources = new IntArraySet();
        for (int actionId = supportActions.nextSetBit(0); actionId >= 0;
             actionId = supportActions.nextSetBit(actionId + 1)) {
            final int[] conditions = pcf[actionId];
            if (conditions != null) {
                for (final int conditionId : conditions) {
                    sources.add(conditionId);
                }
            }
        }
        return sources.toIntArray();
    }

    private void updatePCF(int actionId, int[] conditions, JGraph graph) {
        if (Arrays.equals(pcf[actionId], conditions)) {
            return;
        }
        pcf[actionId] = conditions;
        if (graph != null) {
            final IntArrayList dependentEdges = graph.pcfDependents(actionId);
            if (dependentEdges != null) {
                for (final int edgeId : dependentEdges) {
                    graph.setSources(
                            edgeId,
                            collectSources(graph.supportActions(edgeId))
                    );
                }
            }
        }
    }

    @Override
    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions == null) {
            return;
        }
        for (final int actionId : actions) {
            final PreferredConditions preferred = estimateCostCPZeroCut(
                    cp.preconditionFunction()[actionId]
            );
            final float value = preferred.cost();
            if (!closed[actionId] && !getActionInit()[actionId]) {
                if (value < getActionHCost()[actionId]) {
                    getActionHCost()[actionId] = value;
                    updatePCF(actionId, preferred.conditions(), currentGraph);
                    if (getNodeOf()[actionId] == null) {
                        addActionsInPriority(actionId, heap, value);
                    } else {
                        heap.decreaseKey(getNodeOf()[actionId], value);
                    }
                } else if (value == getActionHCost()[actionId]
                        && pcf[actionId] != null
                        && !Arrays.equals(pcf[actionId], preferred.conditions())
                        && getNodeOf()[actionId] == null) {
                    updatePCF(actionId, preferred.conditions(), currentGraph);
                    addActionsInPriority(actionId, heap, value);
                }
            }
            if (value < Float.MAX_VALUE) {
            }
        }
    }

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
                value = Math.min(value, child.cost());
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
    public float computeEstimate(State state) {
        float estimate = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        ensureCrdCausalAchievers(state);
        resetNumericAchieverCostCache();
        resetNumericRepetitionCache();

        final Pair<JGraph, Float> graphAndValue = constructJG(state);
        final JGraph justificationGraph = graphAndValue.getFirst();
        final float initialValue = graphAndValue.getSecond();
        if (initialValue == 0f || initialValue == Float.MAX_VALUE) {
            currentGraph = null;
            return initialValue;
        }

        final BitSet closed = new BitSet(cp.numActions());
        final BitSet changedActions = new BitSet(cp.numActions());
        final IntArrayList supportStack = new IntArrayList();
        final BitSet visitedConditions = new BitSet(getTotNumberOfTerms());

        try {
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
                if (zeroingMode != ZeroingMode.BASE) {
                    setToZeroSelectively(
                            value,
                            justificationGraph,
                            state,
                            closed,
                            changedActions
                    );
                } else {
                    setToZero(
                            pcf[cp.goal()],
                            justificationGraph,
                            changedActions,
                            supportStack,
                            visitedConditions
                    );
                }

                if (changedActions.isEmpty()) {
                    throw new IllegalStateException(
                            "Positive hmax value without a positive-cost action in its support closure"
                    );
                }
                updateJG(justificationGraph, state, changedActions);
            }
        } finally {
            currentGraph = null;
        }
    }

    @Override
    public float[] getActionCost() {
        return reducedCosts;
    }

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
            JGraph justificationGraph,
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

            final IntArrayList incomingEdges = justificationGraph.incomingEdges(
                    currentCondition
            );
            if (incomingEdges == null) {
                continue;
            }
            for (final int edgeId : incomingEdges) {
                final HyperEdge edge = justificationGraph.edge(edgeId);
                final int actionId = edge.actionId();

                if (reducedCosts[actionId] != 0f) {
                    reducedCosts[actionId] = 0f;
                    changedActions.set(actionId);
                }

                for (final int source : edge.sources()) {
                    if (!visitedConditions.get(source)) {
                        supportStack.add(source);
                    }
                }
            }
        }
    }

    private void setToZeroSelectively(
            float threshold,
            JGraph justificationGraph,
            State state,
            BitSet closed,
            BitSet changedActions
    ) {
        final float[] iterationCosts = Arrays.copyOf(reducedCosts, reducedCosts.length);
        final float[] bestDistance = new float[justificationGraph.size()];
        Arrays.fill(bestDistance, Float.POSITIVE_INFINITY);
        final BitSet closedEdges = new BitSet(justificationGraph.size());

        final PriorityQueue<EdgeDistance> queue = new PriorityQueue<>(
                Comparator.comparingDouble(EdgeDistance::distance)
        );
        final int[] goalConditions = pcf[cp.goal()];
        for (final int conditionId : goalConditions) {
            enqueueIncomingEdges(
                    conditionId,
                    0f,
                    justificationGraph,
                    state,
                    iterationCosts,
                    bestDistance,
                    queue
            );
        }

        while (!queue.isEmpty()) {
            final EdgeDistance current = queue.poll();
            final int edgeId = current.edgeId();
            final float distance = current.distance();

            if (closedEdges.get(edgeId)
                    || distance > bestDistance[edgeId] + NUMERIC_PRECISION) {
                continue;
            }
            closedEdges.set(edgeId);

            final HyperEdge edge = justificationGraph.edge(edgeId);
            final int actionId = edge.actionId();
            if (!closed.get(actionId)) {
                closed.set(actionId);
                if (reducedCosts[actionId] != 0f) {
                    reducedCosts[actionId] = 0f;
                    changedActions.set(actionId);
                }
            }
            if (distance + NUMERIC_PRECISION >= threshold) {
                continue;
            }

            for (final int source : edge.sources()) {
                enqueueIncomingEdges(
                        source,
                        distance,
                        justificationGraph,
                        state,
                        iterationCosts,
                        bestDistance,
                        queue
                );
            }
        }
    }

    private void enqueueIncomingEdges(
            int conditionId,
            float distance,
            JGraph graph,
            State state,
            float[] iterationCosts,
            float[] bestDistance,
            PriorityQueue<EdgeDistance> queue
    ) {
        final IntArrayList incomingEdges = graph.incomingEdges(conditionId);
        if (incomingEdges == null) {
            return;
        }
        for (final int edgeId : incomingEdges) {
            final HyperEdge edge = graph.edge(edgeId);
            final int actionId = edge.actionId();
            final float candidateDistance = distance
                    + zeroingMultiplier(actionId, conditionId, state)
                    * iterationCosts[actionId];
            if (candidateDistance + NUMERIC_PRECISION < bestDistance[edgeId]) {
                bestDistance[edgeId] = candidateDistance;
                queue.add(new EdgeDistance(edgeId, candidateDistance));
            }
        }
    }

    private float zeroingMultiplier(
            int actionId,
            int conditionId,
            State state
    ) {
        if (zeroingMode != ZeroingMode.NUM) {
            return 1f;
        }
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return 1f;
        }
        final float contribution = numericContribution(actionId, comparison);
        // Repetitions are not compositional when supporting the action already
        // contributes to this condition.
        if (contribution <= 0f
                || !isKnownInterferenceFree(actionId, conditionId, state)) {
            return 1f;
        }
        return Math.max(
                1f,
                computeRepetitions(actionId, comparison, contribution, state)
        );
    }
}
