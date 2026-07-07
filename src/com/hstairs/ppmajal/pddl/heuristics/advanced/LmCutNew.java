package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.NotCond;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LmCutNew extends H1 {

    private final int root;
    private final int[] pcf;
    private float[] reducedCosts;

    record Supp(int act, int cond) {
    }

    private record JGraph(Set<Integer> V, Set<Supp>[] E, Set<Integer>[] ERev) {
    }

    record Cut(int actionId, int conditionId) {
    }

    public LmCutNew(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                    String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                    boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost,
                    int linearEffectsAbstraction) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                null, unitaryCost, linearEffectsAbstraction, false);
    }

    private LmCutNew(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions,
                     String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                     boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond, Collection<IntArraySet>> redundantMap,
                     boolean unitaryCost, int compNumericStrategy, boolean ssnpAwareVersion) {
        super(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                redundantMap, unitaryCost, compNumericStrategy, ssnpAwareVersion);
        this.root = getTotNumberOfTerms();
        this.pcf = new int[cp.numActions()];
        Arrays.fill(this.pcf, -1);
    }

    Float updateJG(JGraph jg, State gs, Collection<Cut> cuts) {

        final FibonacciHeap heap = new FibonacciHeap();
        Arrays.fill(getClosed(), false);
        nodeOf = new FibonacciHeapNode[cp.numActions()];
        for (Cut cut : cuts) {
            for (var v : jg.E[pcf[cut.actionId]]) {
                if (v.act == cut.actionId) {
                    final float supporterCost = getActionCost()[cut.actionId] == 0f
                            ? actionHCost[cut.actionId]
                            : computeSupporterCost(v.cond, v.act, gs);
                    if (updateIfNeeded(v.cond, supporterCost)) {
                        updateActions(v.cond, heap);
                    }
                }
            }

        }

        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            jg.V.add(pcf[actionId]);
            closed[actionId] = true;
            if (actionId != cp.goal()) {
                final IntSet conditionsAchievableByAction = getConditionsAchievableById(actionId);
                for (final int conditionId : conditionsAchievableByAction) {
                    if (!getConditionInit()[conditionId]) {
                        jg.E[pcf[actionId]].add(new Supp(actionId, conditionId));
                        final float supporterCost = getActionCost()[actionId] == 0f
                                ? actionHCost[actionId]
                                : computeSupporterCost(conditionId, actionId, gs);
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
        final IntArraySet vertices = new IntArraySet();
        final Set<Supp>[] edges = new HashSet[getTotNumberOfTerms() + 1];
        final Set<Integer>[] reverseEdges = new HashSet[getTotNumberOfTerms() + 1];
        vertices.add(root);
        edges[root] = new HashSet<>();
        reverseEdges[root] = new HashSet<>();

        Arrays.fill(getActionHCost(), Float.MAX_VALUE);
        Arrays.fill(getConditionCost(), Float.MAX_VALUE);
        Arrays.fill(getClosed(), false);
        Arrays.fill(getActionInit(), false);
        Arrays.fill(getConditionInit(), false);
        Arrays.fill(pcf, -1);
        allAchievers = new IntArraySet[getTotNumberOfTerms()];

        final FibonacciHeap heap = new FibonacciHeap();
        RelaxationHeuristicUtils.seedInitialConditions(gs, getAllConditions(), getConditionInit(), getConditionCost());
        for (final int conditionId : getAllConditions()) {
            vertices.add(conditionId);
            edges[conditionId] = new HashSet<>();
            reverseEdges[conditionId] = new HashSet<>();
        }

        for (final int freePreconditionAction : freePreconditionActions) {
            getActionHCost()[freePreconditionAction] = 0f;
            getActionInit()[freePreconditionAction] = true;
            addActionsInPriority(freePreconditionAction, heap, 0f);
            vertices.add(root);
            pcf[freePreconditionAction] = root;
        }

        for (final int actionId : allActions) {
            final Condition condition = cp.preconditionFunction()[actionId];
            if (gs.satisfy(condition) && !getActionInit()[actionId]) {
                addActionsInPriority(actionId, heap, 0f);
                getActionHCost()[actionId] = 0f;
                getActionInit()[actionId] = true;
                pcf[actionId] = root;
            }
        }

        while (!heap.isEmpty()) {
            final int actionId = (int) heap.removeMin().getData();
            if (!getClosed()[actionId]) {
                getClosed()[actionId] = true;
                vertices.add(pcf[actionId]);
                nodeOf[actionId] = null;
                if (actionId != cp.goal()) {
                    final IntSet conditionsAchievableByAction = getConditionsAchievableById(actionId);
                    for (final int conditionId : conditionsAchievableByAction) {
                        if (!getConditionInit()[conditionId]) {
                            edges[pcf[actionId]].add(new Supp(actionId, conditionId));
                            reverseEdges[conditionId].add(actionId);

                            final float supporterCost = getActionCost()[actionId] == 0f
                                    ? actionHCost[actionId]
                                    : computeSupporterCost(conditionId, actionId, gs);
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
        return Pair.of(new JGraph(vertices, edges, reverseEdges), getActionHCost()[cp.goal()]);
    }

    @Override
    protected void updateActions(final int conditionId, final FibonacciHeap heap) {
        final IntArraySet actions = getConditionToAction()[conditionId];
        if (actions != null) {
            for (final int actionId : actions) {
                if (!closed[actionId]) {
                    final Pair<Float, Integer> justifier = estimateCost(cp.preconditionFunction()[actionId]);
                    final float value = justifier.getFirst();
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
                        pcf[actionId] = justifier.getSecond() == null ? root : justifier.getSecond();
                    }
                }
            }

        }
    }

    @Override
    public float computeEstimate(State gs) {
        float cost = 0f;
        boolean firstTime = true;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
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
            for (final var cut : cuts) {
                final float supporterCost = computeOnlySupporterCost(cut.conditionId, cut.actionId, gs);
                if (supporterCost < min) {
                    min = supporterCost;
                }
                actionOut[cut.actionId] = Math.min(actionOut[cut.actionId], supporterCost);
            }

            if (min <= 0.00001f) {
                return cost;
            }
            for (final var cut : cuts) {
                getActionCost()[cut.actionId] -= min / actionOut[cut.actionId];
            }
            cost += min;
            if (true){
                Float res = updateJG(justificationGraph,gs,cuts);
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

    @Override
    public float[] getActionCost() {
        return reducedCosts;
    }

    private void markGoalZone(JGraph graph, int starting, boolean[] goalZone) {
        goalZone[starting] = true;
        for (final int actionId : graph.ERev[starting]) {
            if (getActionCost()[actionId] <= 0f) {
                markGoalZone(graph, pcf[actionId], goalZone);
            }
        }
    }

    private Collection<Cut> computeCuts(JGraph graph, boolean[] goalZone) {
        final Collection<Cut> cuts = new ArrayList<>();
        final IntArrayFIFOQueue queue = new IntArrayFIFOQueue();
        final IntArraySet closedConditions = new IntArraySet();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            final int current = queue.dequeueInt();
            closedConditions.add(current);
            for (final var supporter : graph.E[current]) {
                if (goalZone[supporter.cond]) {
                    cuts.add(new Cut(supporter.act, supporter.cond));
                } else if (!closedConditions.contains(supporter.cond)) {
                    queue.enqueue(supporter.cond);
                }
            }
        }
        return cuts;
    }

    private Pair<Float, Integer> estimateCost(final Condition c) {
        if (c instanceof AndCond and) {
            if (and.sons == null) {
                return Pair.of(0f, null);
            }
            float ret = 0f;
            Integer best = null;
            for (final var son : and.sons) {
                final Pair<Float, Integer> child = estimateCost((Condition) son);
                if (ret < child.getFirst()) {
                    ret = child.getFirst();
                    best = child.getSecond();
                }
            }
            return Pair.of(ret, best);
        } else if (c instanceof com.hstairs.ppmajal.conditions.OrCond or) {
            if (or.sons == null) {
                return Pair.of(0f, null);
            }
            float ret = Float.POSITIVE_INFINITY;
            Integer best = null;
            for (final var son : or.sons) {
                final Pair<Float, Integer> child = estimateCost((Condition) son);
                if (ret > child.getFirst()) {
                    ret = child.getFirst();
                    best = child.getSecond();
                }
            }
            return Pair.of(ret, best);
        } else if (c instanceof Terminal t) {
            return Pair.of(getConditionCost()[t.getId()], t.getId());
        }
        throw new RuntimeException("This is not supported:" + c);
    }

    private float computeSupporterCost(int conditionId, int actionId, State gs) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (terminal instanceof BoolPredicate || terminal instanceof NotCond) {
            return getActionCost()[actionId] + getActionHCost()[actionId];
        }
        final double contribution = numericContribution(actionId, (Comparison) terminal);
        if (contribution > 0) {
            final float repetitions = computeRepetitions(terminal, contribution, gs);
            return getActionHCost()[actionId] + repetitions * getActionCost()[actionId];
        }
        return -1f;
    }

    private float computeOnlySupporterCost(int conditionId, int actionId, State gs) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (terminal instanceof BoolPredicate || terminal instanceof NotCond) {
            return getActionCost()[actionId];
        }
        final double contribution = numericContribution(actionId, (Comparison) terminal);
        if (contribution > 0) {
            final float repetitions = computeRepetitions(terminal, contribution, gs);
            return repetitions * getActionCost()[actionId];
        }
        return -1f;
    }

    private float computeRepetitions(Terminal terminal, double contribution, State state) {
        final double eval = ((Comparison) terminal).getLeft().eval(state);
        if (Double.isNaN(eval)) {
            return 1.0f;
        }
        if (((Comparison) terminal).isStrict && this.isAdditive()) {
            return (float) (-1f * eval / contribution) + Float.MIN_VALUE;
        }
        return (float) (-1f * eval / contribution);
    }
}
