package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

public interface IterativeOptimizationSupport {

    boolean isSupported(SearchProblem problem);

    SearchHeuristic initialHeuristic(SearchHeuristic fallback);

    float evaluateObjective(SimpleSearchNode solutionNode);

    float tightenBound(float currentValue);

    boolean improves(float candidateValue, float incumbentValue);

    void applyTighterBound(float bound);

    default boolean usesGBound() {
        return false;
    }

    void restoreOriginalGoal();

    default SearchHeuristic refreshHeuristic(SearchHeuristic fallback) {
        return fallback;
    }
}
