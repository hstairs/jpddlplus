package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class IterativeMetricSearch extends SearchEngine {

    private static final int DEFAULT_MAX_ITERATIONS = 100;

    private final SearchEngine search;
    private final IterativeOptimizationSupport optimizationSupport;
    private final int maxIterations;
    private IterativeMetricSummary iterativeMetricSummary;

    public IterativeMetricSearch(SearchEngine search, IterativeOptimizationSupport optimizationSupport) {
        this(search, optimizationSupport, DEFAULT_MAX_ITERATIONS);
    }

    public IterativeMetricSearch(SearchEngine search, IterativeOptimizationSupport optimizationSupport, int maxIterations) {
        super(false);
        this.search = search;
        this.optimizationSupport = optimizationSupport;
        this.maxIterations = maxIterations;
    }

    @Override
    public SearchStats getStats() {
        return new SearchStats(
                nodesExpanded,
                nodesEvaluated,
                deadEndsDetected,
                duplicatedDetected,
                totalTime,
                heuristicTime,
                iterativeMetricSummary
        );
    }

    @Override
    public com.hstairs.ppmajal.search.searchnodes.SearchNode getSearchSpaceHandle() {
        return search.getSearchSpaceHandle();
    }

    @Override
    public void setTimeoutInMs(long timeoutInMs) {
        super.setTimeoutInMs(timeoutInMs);
        search.setTimeoutInMs(timeoutInMs);
    }

    @Override
    public void setExtenalLogger(com.hstairs.ppmajal.extraUtils.IExternalLogger extenalLogger) {
        super.setExtenalLogger(extenalLogger);
        search.setExtenalLogger(extenalLogger);
    }

    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        zeroCounters();
        iterativeMetricSummary = null;
        final long overallStart = System.currentTimeMillis();

        if (optimizationSupport == null || !optimizationSupport.isSupported(problem)) {
            return runBaseSearch(problem, h, out, overallStart);
        }
        SimpleSearchNode bestSolution = null;
        float bestValue = Float.NaN;
        int iterationsAttempted = 0;
        int successfulIterations = 0;
        float minObservedValue = Float.POSITIVE_INFINITY;
        float maxObservedValue = Float.NEGATIVE_INFINITY;
        String stopReason = "completed";

        try {
            SearchHeuristic currentHeuristic = optimizationSupport.initialHeuristic(h);
            SimpleSearchNode currentSolution = runBaseSearch(problem, currentHeuristic, out, overallStart);
            if (currentSolution == null) {
                stopReason = "initial_search_failed";
                return null;
            }
            bestSolution = currentSolution;
            bestValue = optimizationSupport.evaluateObjective(currentSolution);
            minObservedValue = Math.min(minObservedValue, bestValue);
            maxObservedValue = Math.max(maxObservedValue, bestValue);
            out.println("Iterative metric search: value=" + bestValue);

            while (!shouldStop(overallStart) && iterationsAttempted < maxIterations) {
                iterationsAttempted++;
                float tighterBound = optimizationSupport.tightenBound(bestValue);
                if (optimizationSupport.usesGBound()) {
                    search.setGBound(tighterBound);
                } else {
                    optimizationSupport.applyTighterBound(tighterBound);
                }
                currentHeuristic = optimizationSupport.refreshHeuristic(currentHeuristic);

                long remaining = remainingBudget(overallStart);
                search.setTimeoutInMs(remaining);
                SimpleSearchNode candidate = search.search(problem, currentHeuristic, new PrintStream(new OutputStream() {
                    @Override
                    public void write(int b) throws IOException {
                    }
                }));
                accumulateStats(search.getStats());

                if (candidate == null) {
                    out.println("Iterative metric search: stop at bound=" + tighterBound);
                    stopReason = "bound_infeasible";
                    break;
                }

                float candidateValue = optimizationSupport.evaluateObjective(candidate);
                minObservedValue = Math.min(minObservedValue, candidateValue);
                maxObservedValue = Math.max(maxObservedValue, candidateValue);
                if (!optimizationSupport.improves(candidateValue, bestValue)) {
                    out.println("Iterative metric search: no further improvement at iteration " + iterationsAttempted);
                    stopReason = "no_improvement";
                    break;
                }

                bestSolution = candidate;
                bestValue = candidateValue;
                successfulIterations++;
                out.println("Iterative metric search: improved value=" + bestValue);
            }
            if (shouldStop(overallStart)) {
                stopReason = "timeout";
            } else if (iterationsAttempted >= maxIterations) {
                stopReason = "max_iterations";
            }
        } finally {
            optimizationSupport.restoreOriginalGoal();
            iterativeMetricSummary = new IterativeMetricSummary(
                    maxIterations,
                    iterationsAttempted,
                    successfulIterations,
                    bestValue,
                    minObservedValue,
                    maxObservedValue,
                    stopReason
            );
        }

        totalTime = System.currentTimeMillis() - overallStart;
        return bestSolution;
    }

    private SimpleSearchNode runBaseSearch(SearchProblem problem, SearchHeuristic h, PrintStream out, long overallStart) {
        long remaining = remainingBudget(overallStart);
        search.setTimeoutInMs(remaining);
        SimpleSearchNode result = search.search(problem, h, out);
        accumulateStats(search.getStats());
        return result;
    }

    private long remainingBudget(long overallStart) {
        if (timeoutInMs == Long.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        long elapsed = System.currentTimeMillis() - overallStart;
        return Math.max(1L, timeoutInMs - elapsed);
    }

    private void accumulateStats(SearchStats stats) {
        if (stats == null) {
            return;
        }
        nodesExpanded += stats.nodesExpanded();
        nodesEvaluated += stats.nodesEvaluated();
        deadEndsDetected += stats.deadEnds();
        duplicatedDetected += stats.duplicates();
        heuristicTime += stats.heuristicTime();
    }

}
