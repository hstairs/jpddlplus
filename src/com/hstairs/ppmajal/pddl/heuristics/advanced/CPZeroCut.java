package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.jgrapht.alg.util.Pair;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Critical-path heuristic that sets the whole support closure of the current
 * goal condition to zero at every iteration.
 */
public class CPZeroCut extends LmCut {

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
                SsnpCausalMode.NONE,
                false
        );
    }

    public static CPZeroCut create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            SsnpCausalMode ssnpCausalMode,
            boolean useNumericActivationFloor
    ) {
        return new CPZeroCut(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction,
                ssnpCausalMode,
                useNumericActivationFloor
        );
    }

    private CPZeroCut(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction,
            SsnpCausalMode ssnpCausalMode,
            boolean useNumericActivationFloor
    ) {
        super(problem, false, false, false, redundantConstraints,
                false, false, false, false, null, unitaryCost,
                linearEffectsAbstraction, ssnpCausalMode,
                useNumericActivationFloor);
    }

    @Override
    public float computeEstimate(State state) {
        float estimate = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        ensureSsnpCausalAchievers(state);
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
            setToZero(
                    goalCondition,
                    justificationGraph,
                    closed,
                    changedActions,
                    supportStack
            );

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
}
