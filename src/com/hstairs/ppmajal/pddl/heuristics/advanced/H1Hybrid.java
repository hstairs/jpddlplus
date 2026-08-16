package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.AndCond;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.Collection;
import java.util.Map;

/**
 * SSNP-aware H1 variant using a witness-local activation floor.
 *
 * <p>For a non-interfering achiever {@code a}, the candidate is
 * {@code P(a) + max(gamma(a), E(a))}. For an interfering achiever it is
 * {@code max(P(a) + gamma(a), minP(c) + E(a))}.</p>
 */
public final class H1Hybrid extends H1 {

    public H1Hybrid(
            PDDLProblem problem,
            String redundantConstraints,
            Map<AndCond, Collection<IntArraySet>> redundantMap,
            boolean unitaryCost,
            int linearEffectsAbstraction
    ) {
        super(
                problem,
                false,
                false,
                false,
                redundantConstraints,
                false,
                false,
                false,
                false,
                redundantMap,
                unitaryCost,
                linearEffectsAbstraction,
                true
        );
    }

    @Override
    protected float computeNumericAchieverCost(
            int conditionId,
            int actionId,
            float numericEffectCost
    ) {
        final float preconditionCost = getActionHCost()[actionId];
        final float actionCost = getActionCost()[actionId];
        minAchieverPreconditionCost[conditionId] = Math.min(
                minAchieverPreconditionCost[conditionId],
                preconditionCost
        );

        if (isAdditive(actionId, conditionId)) {
            return preconditionCost + Math.max(actionCost, numericEffectCost);
        }

        return Math.max(
                preconditionCost + actionCost,
                minAchieverPreconditionCost[conditionId] + numericEffectCost
        );
    }
}
