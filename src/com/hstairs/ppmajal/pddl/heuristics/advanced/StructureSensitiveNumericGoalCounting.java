package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.TransitionGround;

import java.util.Arrays;

public class StructureSensitiveNumericGoalCounting extends GoalCounting {

    public StructureSensitiveNumericGoalCounting(PDDLProblem problem) {
        super(problem);
    }

    @Override
    public float computeEstimate(State s) {
        if (s.satisfy(problem.getGoals())) {
            return 0f;
        }
        return evalGoalDistance(problem.getGoals(), s);
    }

    private float evalGoalDistance(Condition goals, State s) {
        if (s.satisfy(goals)) {
            return 0f;
        }
        if (goals instanceof AndCond ac) {
            float res = 0f;
            for (var son : ac.sons) {
                final float v = evalGoalDistance((Condition) son, s);
                if (v == Float.MAX_VALUE)
                    return Float.MAX_VALUE;
                res += v;
            }
            return res;
        } else if (goals instanceof OrCond oc) {
            float min = Float.POSITIVE_INFINITY;
            for (var son : oc.sons) {
                final float v = evalGoalDistance((Condition) son, s);
                if (v < min) {
                    min = v;
                }
                if (v == 0f) {
                    return v;
                }
            }
            return min;
        } else if (goals instanceof Comparison g) {
            Pair<Float, Float> bestContribution = getBestContribution(g);
            if (bestContribution.getFirst() == 0f) {
                return Float.MAX_VALUE;
            }
            return (float) (-1f * g.getLeft().eval(s) / bestContribution.getFirst())*bestContribution.getSecond();
        } else if (goals instanceof BoolPredicate g) {
            final Pair<Float, Float> bestContribution = getBestContribution(g);
            if (bestContribution.getFirst() == Float.MAX_VALUE)
                return Float.MAX_VALUE;
            return bestContribution.getSecond();
        }
        return 0f;
    }

    private Pair<Float, Float>[] contribution;

    private Pair<Float, Float> getBestContribution(Terminal g) {
        if (contribution == null) {
            contribution = new Pair[Terminal.getTotCounter()];
            Arrays.fill(contribution, new Pair(-1f,-1f));
        }
        if (contribution[g.getId()].getFirst() == -1f) {
            if (g instanceof Comparison) {
                contribution[g.getId()] = new Pair(0f,Float.MAX_VALUE);
                for (var t : problem.getTransitions()) {
                    Pair<Float, Float> contCost = numericContribution(t, (Comparison) g);
                    if (contribution[g.getId()].getFirst() < contCost.getFirst()) {
                        contribution[g.getId()] = contCost;
                    }
                }

            } else {
                contribution[g.getId()] = new Pair(Float.MAX_VALUE,Float.MAX_VALUE);
                for (var t : problem.getTransitions()) {
                    if (t.getAllAchievableLiterals().contains(g)) {
                        final float actionCost = t.getActionCost(problem.init, problem.getMetric());
                        if (actionCost < contribution[g.getId()].getSecond()) {
                            contribution[g.getId()] = new Pair(actionCost,actionCost);
                        }
                    }
                }
            }
        }
        return contribution[g.getId()];
    }

    final float unknownEffects = 1f;

    private Pair<Float, Float> numericContribution(TransitionGround t, Comparison comp) {

        float actionCost = t.getActionCost(problem.init, problem.getMetric());
        float positiveness = 0f;

        if (comp.getLeft() instanceof ExtendedNormExpression extendedNormExpression) {
            final ExtendedNormExpression left = extendedNormExpression;
            for (final ExtendedAddendum ad : left.summations) {
                if (ad.bin != null) {
                    for (final NumEffect ne : t.getAllNumericEffects()) {
                        NumFluent fluentAffected = ne.getFluentAffected();
                        if (ad.bin.getInvolvedNumericFluents().contains(fluentAffected)) {
                            return new Pair(unknownEffects, actionCost);
                        }
                    }
                }
                if (ad.f != null) {
                    for (final NumEffect ne : t.getAllNumericEffects()) {

                        if (!ne.getFluentAffected().equals(ad.f)) {
                            continue;
                        }

                        if (ne.getInvolvedNumericFluents().isEmpty()) {
                            final ExtendedNormExpression rhs = (ExtendedNormExpression) ne.getRight();
                            if (!rhs.linear || !rhs.isNumber() || ne.getOperator().equals("assign")) {
                                return new Pair(unknownEffects, actionCost);
                            }
                            if (ne.getOperator().equals("increase")) {
                                positiveness += rhs.getNumber().floatValue() * ad.n.floatValue();
                            } else if (ne.getOperator().equals("decrease")) {
                                positiveness += (-1) * rhs.getNumber().floatValue() * ad.n.floatValue();
                            }
                        } else {//The effect is state dependent.
                            return new Pair(unknownEffects, actionCost);
                        }
                    }
                }
            }
            return new Pair(positiveness, actionCost);
        }
        return new Pair(positiveness, actionCost);
    }

}
