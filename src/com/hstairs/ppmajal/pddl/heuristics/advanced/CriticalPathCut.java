package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import org.jgrapht.alg.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

        final Pair<JGraph, Float> jGraphAndValue = constructJG(state);
        final JGraph justificationGraph = jGraphAndValue.getFirst();
        final float goalValue = jGraphAndValue.getSecond();

        if (goalValue == 0f || goalValue == Float.MAX_VALUE) {
            return goalValue;
        }
        while (true) {

            cost += actionHCost[cp.goal()];
            Arrays.fill(expandedActions, false);

            final Collection<Cut> cuts = collectProportionalCuts(
                    pcf[cp.goal()],
                    reducedCosts.clone(),
                    justificationGraph,
                    expandedActions,
                    actionHCost[cp.goal()],
                    state
            );
            final Float res = updateJG(justificationGraph, state, cuts);
            if (res == 0f) {
                return cost;
            }

        }
    }

    private List<Cut> collectProportionalCuts(
            int conditionId,
            float[] previousReducedCosts,
            JGraph justificationGraph,
            boolean[] expandedActions,
            float pendingCostShare,
            State state
    ) {
        if (pendingCostShare == 0f) {
            return Collections.emptyList();
        }
        final ArrayList<Cut> cuts = new ArrayList<>();
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
                    cuts.add(new Cut(actionId, conditionId));
                }
                final float residualCostShare = pendingCostShare
                        - (previousCost - reducedCosts[actionId]) * supporterApplications;
                if (residualCostShare > 0f && !expandedActions[actionId]) {
                    expandedActions[actionId] = true;
                    cuts.addAll(collectProportionalCuts(
                            pcf[actionId],
                            previousReducedCosts,
                            justificationGraph,
                            expandedActions,
                            residualCostShare,
                            state
                    ));
                }
            } else if (pendingCostShare > 0f && !expandedActions[actionId]) {
                expandedActions[actionId] = true;
                cuts.addAll(collectProportionalCuts(
                        pcf[actionId],
                        previousReducedCosts,
                        justificationGraph,
                        expandedActions,
                        pendingCostShare,
                        state
                ));
            }
        }
        return cuts;
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
