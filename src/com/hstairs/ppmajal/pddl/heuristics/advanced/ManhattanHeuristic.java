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

public class ManhattanHeuristic extends GoalCounting {

    public ManhattanHeuristic(PDDLProblem problem) {
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
            return (float) Math.abs(g.getLeft().eval(s));
        } else if (goals instanceof BoolPredicate g) {
            return 1;
        }
        return 0f;
    }

}
