package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.jgrapht.alg.util.Pair;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

public class CriticalPathCutSsnp extends LmCut {

    private final Collection<Integer>[] numericAchieversByCondition;
    private final BitSet[] relaxedCausalAncestors;

    public static CriticalPathCutSsnp create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        return new CriticalPathCutSsnp(
                problem,
                redundantConstraints,
                unitaryCost,
                linearEffectsAbstraction
        );
    }

    private CriticalPathCutSsnp(
            PDDLProblem problem,
            String redConstraints,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        super(problem, false, false, false, redConstraints,
                false, false, false, false, unitaryCost, linearEffectsAbstraction);
        numericAchieversByCondition = buildNumericAchieversByCondition();
        relaxedCausalAncestors = buildRelaxedCausalAncestors();
    }

    @Override
    public float computeEstimate(State state) {

        float cost = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        final boolean[] expandedActions = new boolean[cp.numActions()];
        final boolean[] changedAction = new boolean[cp.numActions()];
        final IntArrayList changedActions = new IntArrayList();
        resetEvalComparison(); //This is for caching evaluations of comparisons, since here we need to use it multiple times
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



    @Override
    float computeSupporterCost(int conditionId, int actionId, State state, boolean includeHeuristicCost) {
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
        if (!hasCausalInference(conditionId, actionId)) {
            final float repetitions = computeRepetitions(comparison, contribution, state);
            return heuristicCost + Math.max(1f, repetitions) * getActionCost()[actionId];
        }

        final float minCostPerProgress = computeMinCostPerProgress(conditionId, actionId);
        if (minCostPerProgress == 0f) {
            return heuristicCost;
        }
        final float bestProgressPerCost = 1f / minCostPerProgress;
        final float costToReachCondition = computeRepetitions(comparison, bestProgressPerCost, state);
        return heuristicCost + costToReachCondition;
    }

    private Collection<Integer>[] buildNumericAchieversByCondition() {
        final IntArrayList[] achieversByCondition = new IntArrayList[getTotNumberOfTerms()];

        for (final int conditionId : getAllComparisons()) {
            if (!(Terminal.getTerminal(conditionId) instanceof Comparison comparison)) {
                continue;
            }
            final IntArrayList achievers = new IntArrayList();
            for (final int actionId : allActions) {
                final float contribution = numericContribution(actionId, comparison);
                if (contribution > 0f) {
                    achievers.add(actionId);
                }
            }

            if (!achievers.isEmpty()) {
                achieversByCondition[conditionId] = achievers;
            }
        }
        return achieversByCondition;
    }

    private BitSet[] buildRelaxedCausalAncestors() {
        final BitSet[] ancestors = new BitSet[cp.numActions()];
        for (int actionId = 0; actionId < ancestors.length; actionId++) {
            ancestors[actionId] = new BitSet(cp.numActions());
        }

        for (final int achieverId : allActions) {
            if (achieverId == cp.goal()) {
                continue;
            }
            for (final int conditionId : getConditionsAchievableById(achieverId)) {
                final IntArraySet consumers = getConditionToAction()[conditionId];
                if (consumers != null) {
                    for (final int actionId : consumers) {
                        ancestors[actionId].set(achieverId);
                    }
                }
            }
        }

        boolean changed;
        do {
            changed = false;
            for (final int actionId : allActions) {
                final BitSet expanded = (BitSet) ancestors[actionId].clone();
                for (int ancestorId = ancestors[actionId].nextSetBit(0);
                     ancestorId >= 0;
                     ancestorId = ancestors[actionId].nextSetBit(ancestorId + 1)) {
                    expanded.or(ancestors[ancestorId]);
                }
                if (!expanded.equals(ancestors[actionId])) {
                    ancestors[actionId] = expanded;
                    changed = true;
                }
            }
        } while (changed);

        return ancestors;
    }

    private boolean hasCausalInference(int conditionId, int actionId) {
        final Collection<Integer> achievers = numericAchieversByCondition[conditionId];
        if (achievers == null) {
            return false;
        }
        for (final int achieverId : achievers) {
            if (achieverId != actionId && relaxedCausalAncestors[achieverId].get(actionId)) {
                return true;
            }
        }
        return false;
    }

    private float computeMinCostPerProgress(int conditionId, int actionId) {
        final Comparison comparison = (Comparison) Terminal.getTerminal(conditionId);
        float minimum = Float.POSITIVE_INFINITY;
        for (final int achieverId : numericAchieversByCondition[conditionId]) {
            if (achieverId == actionId || relaxedCausalAncestors[achieverId].get(actionId)) {
                final float contribution = numericContribution(achieverId, comparison);
                minimum = Math.min(minimum, getActionCost()[achieverId] / contribution);
            }
        }
        return minimum;
    }

//    @Override
    protected IntSet getConditionsAchievableByIdRemake(int actionId) {
        final Collection<Integer>  achievableConditions = super.getConditionsAchievableById(actionId);
        final IntArraySet positiveAchievableConditions = new IntArraySet();
        for (final int conditionId : achievableConditions) {
            final Terminal terminal = Terminal.getTerminal(conditionId);
            if (!(terminal instanceof Comparison comparison)
                    || numericContribution(actionId, comparison) > 0f) {
                positiveAchievableConditions.add(conditionId);
            }
        }
        return positiveAchievableConditions;
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
        final float repetitions = computeRepetitions(comparison, contribution, state);
        return hasCausalInference(conditionId, actionId)
                ? repetitions
                : Math.max(1f, repetitions);
    }
}
