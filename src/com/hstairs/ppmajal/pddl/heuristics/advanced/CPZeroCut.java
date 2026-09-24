package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.jgrapht.alg.util.Pair;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Critical-path heuristic with complete or threshold-selective support zeroing.
 */
public class CPZeroCut extends LmCut {

    private final boolean selectiveZeroing;

    private record ActionDistance(int actionId, float distance) {
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
                false
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
                false
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
        return new CPZeroCut(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction,
                crdMode,
                useNumericActivationFloor,
                selectiveZeroing
        );
    }

    private CPZeroCut(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            CrdMode crdMode,
            boolean useNumericActivationFloor,
            boolean selectiveZeroing
    ) {
        super(problem, false, false, false, redundantConstraints,
                false, false, false, false, null, unitaryCost,
                linearEffectsAbstraction, crdMode,
                useNumericActivationFloor);
        if (selectiveZeroing && !useNumericActivationFloor) {
            throw new IllegalArgumentException(
                    "Selective zeroing requires the numeric activation floor"
            );
        }
        this.selectiveZeroing = selectiveZeroing;
    }

    @Override
    public float computeEstimate(State state) {
        float estimate = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        ensureCrdCausalAchievers(state);
        resetNumericAchieverCostCache();
        resetEvalComparison();

        final Pair<JGraph, Float> graphAndValue = constructJG(state);
        final JGraph justificationGraph = graphAndValue.getFirst();
        final float initialValue = graphAndValue.getSecond();
        if (initialValue == 0f || initialValue == Float.MAX_VALUE) {
            return initialValue;
        }

        final BitSet closed = new BitSet(cp.numActions());
        final BitSet changedActions = new BitSet(cp.numActions());
        final IntArrayList supportStack = new IntArrayList();

        while (true) {
            // pcf[goal] can change when updateJG selects a new critical
            // precondition, so resolve the goal node at every iteration.
            final int goalCondition = pcf[cp.goal()];
            final float value = getConditionCost()[goalCondition];
            if (value == 0f) {
                return estimate;
            }
            if (value == Float.MAX_VALUE) {
                return Float.MAX_VALUE;
            }

            estimate += value;
            closed.clear();
            changedActions.clear();
            if (selectiveZeroing) {
                setToZeroSelectively(
                        value,
                        justificationGraph,
                        closed,
                        changedActions
                );
            } else {
                setToZero(
                        goalCondition,
                        justificationGraph,
                        closed,
                        changedActions,
                        supportStack
                );
            }

            if (changedActions.isEmpty()) {
                throw new IllegalStateException(
                        "Positive hmax value without a positive-cost action in its support closure"
                );
            }
            updateJG(justificationGraph, state, changedActions);
        }
    }

    @Override
    float computeSupporterCost(
            int conditionId,
            int actionId,
            State state,
            boolean includeHeuristicCost
    ) {
        if (!includeHeuristicCost) {
            return super.computeSupporterCost(conditionId, actionId, state, false);
        }

        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return super.computeSupporterCost(conditionId, actionId, state, true);
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            return super.computeSupporterCost(conditionId, actionId, state, true);
        }

        final float repetitions = computeRepetitions(comparison, contribution, state);
        return computeNumericAchieverCost(
                conditionId,
                actionId,
                repetitions * getActionCost()[actionId],
                state
        );
    }

    private void setToZero(
            int conditionId,
            JGraph justificationGraph,
            BitSet closed,
            BitSet changedActions,
            IntArrayList supportStack
    ) {
        supportStack.clear();
        supportStack.add(conditionId);
        while (!supportStack.isEmpty()) {
            final int currentCondition = supportStack.removeInt(supportStack.size() - 1);
            for (final int actionId : justificationGraph.ERev()[currentCondition]) {
                if (closed.get(actionId)) {
                    continue;
                }

                closed.set(actionId);
                if (reducedCosts[actionId] != 0f) {
                    reducedCosts[actionId] = 0f;
                    changedActions.set(actionId);
                }

                final int preferredCondition = pcf[actionId];
                if (preferredCondition != root) {
                    supportStack.add(preferredCondition);
                }
            }
        }
    }

    private void setToZeroSelectively(
            float threshold,
            JGraph justificationGraph,
            BitSet closed,
            BitSet changedActions
    ) {
        final float[] iterationCosts = Arrays.copyOf(reducedCosts, reducedCosts.length);
        final float[] bestDistance = new float[cp.numActions()];
        Arrays.fill(bestDistance, Float.POSITIVE_INFINITY);

        final PriorityQueue<ActionDistance> queue = new PriorityQueue<>(
                Comparator.comparingDouble(ActionDistance::distance)
        );
        bestDistance[cp.goal()] = 0f;
        queue.add(new ActionDistance(cp.goal(), 0f));

        while (!queue.isEmpty()) {
            final ActionDistance current = queue.poll();
            final int actionId = current.actionId();
            final float distance = current.distance();

            if (closed.get(actionId)
                    || distance > bestDistance[actionId] + NUMERIC_PRECISION) {
                continue;
            }

            closed.set(actionId);
            if (reducedCosts[actionId] != 0f) {
                reducedCosts[actionId] = 0f;
                changedActions.set(actionId);
            }
            if (distance + NUMERIC_PRECISION >= threshold) {
                continue;
            }

            final int preferredCondition = pcf[actionId];
            if (preferredCondition == root) {
                continue;
            }

            for (final int achieverId : justificationGraph.ERev()[preferredCondition]) {
                if (closed.get(achieverId)) {
                    continue;
                }

                final float candidateDistance = distance + iterationCosts[achieverId];
                if (candidateDistance + NUMERIC_PRECISION < bestDistance[achieverId]) {
                    bestDistance[achieverId] = candidateDistance;
                    queue.add(new ActionDistance(achieverId, candidateDistance));
                }
            }
        }
    }
}
