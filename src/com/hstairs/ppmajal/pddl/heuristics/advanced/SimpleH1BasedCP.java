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
 * Simple h1-based critical-path heuristic that sets the whole support closure
 * of the current goal condition to zero at every iteration.
 */
public class SimpleH1BasedCP extends LmCut {

    public static SimpleH1BasedCP create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        return new SimpleH1BasedCP(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction
        );
    }

    private SimpleH1BasedCP(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        super(problem, false, false, false, redundantConstraints,
                false, false, false, false, null, unitaryCost,
                linearEffectsAbstraction, SsnpCausalMode.STATE_BASED);
    }

    @Override
    public float computeEstimate(State state) {
        float estimate = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        ensureSsnpCausalAchievers(state);
        resetSsnpEvaluationCache();
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

    /**
     * Uses the CPCutSSNP cost-per-progress calculation with H1's state-based
     * SSNP interference relation.  The current action remains a candidate and
     * the numeric part uses the cheapest reduced-cost/progress ratio among it
     * and its interfering achievers.
     */
    @Override
    float computeSupporterCost(
            int conditionId,
            int actionId,
            State state,
            boolean includeHeuristicCost
    ) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        final float heuristicCost = includeHeuristicCost ? getActionHCost()[actionId] : 0f;
        if (!(terminal instanceof Comparison comparison)) {
            return heuristicCost + getActionCost()[actionId];
        }

        final float contribution = numericContribution(actionId, comparison);
        if (contribution == UNKNOWNEFFECT) {
            return heuristicCost;
        }
        if (contribution <= 0f) {
            return Float.MAX_VALUE;
        }

        final BitSet activeAncestors = activeCausalAncestors(actionId, state);
        final BitSet positiveAchievers = positiveNumericAchievers(conditionId);
        float minCostPerProgress = getActionCost()[actionId] / contribution;
        boolean hasPositiveInterferingAchiever = false;
        if (positiveAchievers != null && !activeAncestors.isEmpty()) {
            final boolean scanPositiveAchievers = positiveAchievers.cardinality()
                    <= activeAncestors.cardinality();
            final BitSet actionsToScan = scanPositiveAchievers
                    ? positiveAchievers
                    : activeAncestors;
            final BitSet requiredMembership = scanPositiveAchievers
                    ? activeAncestors
                    : positiveAchievers;
            for (int interferingActionId = actionsToScan.nextSetBit(0);
                 interferingActionId >= 0;
                 interferingActionId = actionsToScan.nextSetBit(interferingActionId + 1)) {
                if (interferingActionId == actionId
                        || !requiredMembership.get(interferingActionId)) {
                    continue;
                }
                hasPositiveInterferingAchiever = true;
                if (getActionCost()[interferingActionId] == 0f) {
                    return heuristicCost;
                }
                final float interferingContribution = numericContribution(
                        interferingActionId,
                        comparison
                );
                minCostPerProgress = Math.min(
                        minCostPerProgress,
                        getActionCost()[interferingActionId] / interferingContribution
                );
            }
        }

        if (!hasPositiveInterferingAchiever) {
            final float repetitions = computeRepetitions(comparison, contribution, state);
            return heuristicCost + Math.max(1f, repetitions) * getActionCost()[actionId];
        }
        if (minCostPerProgress == 0f) {
            return heuristicCost;
        }
        final float bestProgressPerCost = 1f / minCostPerProgress;
        return heuristicCost + computeRepetitions(comparison, bestProgressPerCost, state);
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
