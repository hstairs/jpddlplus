package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.expressions.Expression;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.search.IterativeOptimizationSupport;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.search.SearchProblem;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

import java.util.ArrayList;
import java.util.function.Function;

public class PDDLIterativeOptimizationSupport implements IterativeOptimizationSupport {

    private static final float IMPROVEMENT_EPSILON = 0.0001f;

    private final PDDLProblem problem;
    private final Function<PDDLProblem, SearchHeuristic> heuristicBuilder;
    private final Condition originalGoals;
    private final Expression metricExpression;
    private final Comparison.Comparator comparator;

    public PDDLIterativeOptimizationSupport(PDDLProblem problem, Function<PDDLProblem, SearchHeuristic> heuristicBuilder) {
        this.problem = problem;
        this.heuristicBuilder = heuristicBuilder;
        this.originalGoals = problem.getGoals();
        Metric metric = problem.getMetric();
        this.metricExpression = metric == null ? null : metric.getMetExpr();
        this.comparator = metric == null || metric.getMetExpr() == null || metric.getOptimization() == null
                ? Comparison.Comparator.LT
                : "minimize".equalsIgnoreCase(metric.getOptimization())
                ? Comparison.Comparator.LT
                : Comparison.Comparator.GT;
    }

    @Override
    public boolean isSupported(SearchProblem searchProblem) {
        if (!(searchProblem instanceof PDDLProblem pddlProblem)) {
            return false;
        }
        Metric metric = pddlProblem.getMetric();
        return pddlProblem == problem
                && (metric == null
                || metric.getMetExpr() == null
                || metric.getOptimization() == null
                || "minimize".equalsIgnoreCase(metric.getOptimization())
                || "maximize".equalsIgnoreCase(metric.getOptimization()));
    }

    @Override
    public SearchHeuristic initialHeuristic(SearchHeuristic fallback) {
        return refreshHeuristic(fallback);
    }

    @Override
    public float evaluateObjective(SimpleSearchNode solutionNode) {
        if (metricExpression == null) {
            return solutionNode.gValue;
        }
        return (float) metricExpression.eval(solutionNode.s);
    }

    @Override
    public float tightenBound(float currentValue) {
        return comparator == Comparison.Comparator.LT
                ? currentValue - IMPROVEMENT_EPSILON
                : currentValue + IMPROVEMENT_EPSILON;
    }

    @Override
    public boolean improves(float candidateValue, float incumbentValue) {
        return comparator == Comparison.Comparator.LT
                ? candidateValue < incumbentValue - IMPROVEMENT_EPSILON
                : candidateValue > incumbentValue + IMPROVEMENT_EPSILON;
    }

    @Override
    public void applyTighterBound(float bound) {
        if (metricExpression == null) {
            return;
        }
        ArrayList<Condition> conditions = new ArrayList<>();
        conditions.add(originalGoals);
        conditions.add(Comparison.comparison(
                comparator,
                metricExpression,
                new PDDLNumber(bound),
                false
        ));
        problem.setGroundGoalsForSearch(new AndCond(conditions));
    }

    @Override
    public void restoreOriginalGoal() {
        problem.setGroundGoalsForSearch(originalGoals);
    }

    @Override
    public boolean usesGBound() {
        return metricExpression == null;
    }

    @Override
    public SearchHeuristic refreshHeuristic(SearchHeuristic fallback) {
        if (heuristicBuilder == null) {
            return fallback;
        }
        return heuristicBuilder.apply(problem);
    }
}
