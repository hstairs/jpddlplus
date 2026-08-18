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
import java.util.Collection;
import java.util.List;

public class CriticalPathCutSsnp extends LmCut {

    private final Collection<Integer>[] numericAchieversByCondition;
    private final float[] initialMinCostPerProgress;
    private final float[] minCostPerProgress;
    private final Collection<Integer> nonSuperSimpleComparisons;

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
        final NumericProgressIndex numericProgressIndex = buildNumeriProgressionInfo();
        numericAchieversByCondition = numericProgressIndex.achieversByCondition();
        nonSuperSimpleComparisons = new IntArrayList(numericProgressIndex.nonSuperSimpleComparisons());
        initialMinCostPerProgress = numericProgressIndex.initialMinCostPerProgress();
        minCostPerProgress = initialMinCostPerProgress.clone();
    }

    @Override
    public float computeEstimate(State state) {

        float cost = 0f;
        reducedCosts = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        final boolean[] expandedActions = new boolean[cp.numActions()];
        final boolean[] changedAction = new boolean[cp.numActions()];
        final IntArrayList changedActions = new IntArrayList();
        resetEvalComparison(); //This is for caching evaluations of comparisons, since here we need to use it multiple times
        prepareFirstRunMinCostPerProgress();
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
            updateMinCostPerProgressAfterCut();
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
        if (contribution > 0f && initialMinCostPerProgress[conditionId] == Float.POSITIVE_INFINITY ) {
            final float repetitions = computeRepetitions(comparison, contribution, state);
            final float supporterApplications = applyDirectSsnpActivationFloor(
                    conditionId,
                    repetitions
            );
            return heuristicCost + supporterApplications * getActionCost()[actionId];
        }
        if (contribution == UNKNOWNEFFECT) {
            return heuristicCost;
        }
        if (contribution == 0f || minCostPerProgress[conditionId] == Float.POSITIVE_INFINITY) {
            return Float.MAX_VALUE;
        }

        final float bestProgressPerCost = 1f / minCostPerProgress[conditionId];
        final float costToReachCondition = computeRepetitions(comparison, bestProgressPerCost, state);
        return heuristicCost + costToReachCondition;
    }

    private NumericProgressIndex buildNumeriProgressionInfo() {
        final IntArrayList[] achieversByCondition = new IntArrayList[getTotNumberOfTerms()];
        final IntArraySet nonSuperSimple = new IntArraySet();
        final float[] initialMinima = new float[getTotNumberOfTerms()];
        Arrays.fill(initialMinima, Float.POSITIVE_INFINITY);

        for (final int conditionId : getAllComparisons()) {
            if (!(Terminal.getTerminal(conditionId) instanceof Comparison comparison)) {
                continue;
            }
            final IntArraySet achievers = new IntArraySet();
            for (final int actionId : allActions) {
                final float contribution = numericContribution(actionId, comparison);
                if (contribution > 0f || contribution == UNKNOWNEFFECT) {
                    achievers.add(actionId);
                }
            }

            if (!achievers.isEmpty()) {
                achieversByCondition[conditionId] = new IntArrayList(achievers);
            }
            if (achievers.size() > 1) {
                nonSuperSimple.add(conditionId);
                initialMinima[conditionId] = computeMinCostPerProgress(
                        conditionId,
                        achievers,
                        cp.actionCost()
                );
            }
        }
        return new NumericProgressIndex(
                achieversByCondition,
                nonSuperSimple,
                initialMinima
        );
    }

    private void prepareFirstRunMinCostPerProgress() {
        System.arraycopy(
                initialMinCostPerProgress,
                0,
                minCostPerProgress,
                0,
                initialMinCostPerProgress.length
        );
    }

    private void updateMinCostPerProgressAfterCut() {
        for (final int conditionId : nonSuperSimpleComparisons) {
            final Collection<Integer> achievers = numericAchieversByCondition[conditionId];
            minCostPerProgress[conditionId] = computeMinCostPerProgress(
                    conditionId,
                    achievers,
                    getActionCost()
            );
        }
    }

    private float computeMinCostPerProgress(
            int conditionId,
            Collection<Integer> achievers,
            float[] actionCosts
    ) {
        final Comparison comparison = (Comparison) Terminal.getTerminal(conditionId);
        float minimum = Float.POSITIVE_INFINITY;
        for (final int actionId : achievers) {
            final float contribution = numericContribution(actionId, comparison);
            minimum = Math.min(minimum, actionCosts[actionId] / contribution);
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
        return applyDirectSsnpActivationFloor(
                conditionId,
                computeRepetitions(comparison, contribution, state)
        );
    }

    private float applyDirectSsnpActivationFloor(int conditionId, float repetitions) {
        return initialMinCostPerProgress[conditionId] == Float.POSITIVE_INFINITY
                ? Math.max(1f, repetitions)
                : repetitions;
    }

    private record NumericProgressIndex(
            Collection<Integer>[] achieversByCondition,
            Collection<Integer> nonSuperSimpleComparisons,
            float[] initialMinCostPerProgress
    ) {
    }
}
