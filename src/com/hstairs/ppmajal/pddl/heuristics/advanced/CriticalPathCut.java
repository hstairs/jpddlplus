package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.jgrapht.alg.util.Pair;

import java.util.Arrays;

public class CriticalPathCut extends LmCut {

    public static CriticalPathCut create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        return new CriticalPathCut(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction
        );
    }

    private CriticalPathCut(PDDLProblem problem, String redConstraints, boolean unitaryCost, int linearEffectsAbstraction) {
        super(problem, false, false, false, redConstraints,
                false, false, false, false, unitaryCost, linearEffectsAbstraction);
    }

    @Override
    public float computeEstimate(State state) {
        float cost = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        final boolean[] expandedActions = new boolean[cp.numActions()];
        final boolean[] changedAction = new boolean[cp.numActions()];
        final IntArrayList changedActions = new IntArrayList();

        final Pair<JGraph, Float> jGraphAndValue = constructJG(state);
        final JGraph justificationGraph = jGraphAndValue.getFirst();
        final float goalValue = jGraphAndValue.getSecond();

        if (goalValue == 0f || goalValue == Float.MAX_VALUE) {
            return goalValue;
        }
        while (true) {

            cost += actionHCost[cp.goal()];
            Arrays.fill(expandedActions, false);
            for (final int actionId : changedActions) {
                changedAction[actionId] = false;
            }
            changedActions.clear();

            collectProportionalCuts(
                    pcf[cp.goal()],
                    reducedCosts.clone(),
                    justificationGraph,
                    expandedActions,
                    actionHCost[cp.goal()],
                    state,
                    changedActions,
                    changedAction
            );
            final Float res = updateJG(justificationGraph, state, changedActions);
            if (res == 0f) {
                return cost;
            }

        }
    }

    private void collectProportionalCuts(
            int conditionId,
            float[] previousReducedCosts,
            JGraph justificationGraph,
            boolean[] expandedActions,
            float pendingCostShare,
            State state,
            IntArrayList changedActions,
            boolean[] changedAction
    ) {
        if (pendingCostShare == 0f) {
            return;
        }
        for (final int actionId : justificationGraph.ERev()[conditionId]) {
            if (reducedCosts[actionId] > 0f) {
                final float previousCost = previousReducedCosts[actionId];
                final float supporterApplications = computeRequiredSupporterApplications(conditionId, actionId, state);
                final float localShare = pendingCostShare / supporterApplications;
                final float updatedReducedCost = Math.min(
                        reducedCosts[actionId],
                        Math.max(previousCost - localShare, 0f)
                );
                if (updatedReducedCost != reducedCosts[actionId]) {
                    reducedCosts[actionId] = updatedReducedCost;
                    if (!changedAction[actionId]) {
                        changedAction[actionId] = true;
                        changedActions.add(actionId);
                    }
                }
                final float residualCostShare = pendingCostShare
                        - (previousCost - reducedCosts[actionId]) * supporterApplications;
                if (residualCostShare > 0f && !expandedActions[actionId]) {
                    expandedActions[actionId] = true;
                    collectProportionalCuts(
                            pcf[actionId],
                            previousReducedCosts,
                            justificationGraph,
                            expandedActions,
                            residualCostShare,
                            state,
                            changedActions,
                            changedAction
                    );
                }
            } else if (pendingCostShare > 0f && !expandedActions[actionId]) {
                expandedActions[actionId] = true;
                collectProportionalCuts(
                        pcf[actionId],
                        previousReducedCosts,
                        justificationGraph,
                        expandedActions,
                        pendingCostShare,
                        state,
                        changedActions,
                        changedAction
                );
            }
        }
    }

    private float computeRequiredSupporterApplications(int conditionId, int actionId, State state) {
        final Terminal terminal = Terminal.getTerminal(conditionId);
        if (!(terminal instanceof Comparison comparison)) {
            return 1f;
        }
        final float contribution = numericContribution(actionId, comparison);
        if (contribution <= 0f) {
            throw new IllegalStateException("Invalid supporter repetition in justification graph");
        }
        if (contribution == UNKNOWNEFFECT) {
            return 1f;
        }
        return computeRepetitions(comparison, contribution, state);
    }
}
