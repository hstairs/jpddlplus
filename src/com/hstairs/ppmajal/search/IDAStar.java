package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SearchEventLogger;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

public class IDAStar extends SearchEngine {

    final protected float hw;
    final protected boolean saveSearchSpace;
    final protected boolean enableEventLogging;

    public IDAStar(float hw, boolean helpfulActionsPruning, boolean saveSearchSpace, boolean enableEventLogging){
        super(helpfulActionsPruning);
        this.hw = hw;
        this.saveSearchSpace = saveSearchSpace;
        this.enableEventLogging = enableEventLogging;
    }

    @Override
    public SearchStats getStats(){
        return new SearchStats(nodesExpanded, nodesEvaluated, deadEndsDetected, duplicatedDetected, totalTime, heuristicTime);
    }

    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        zeroCounters();
        long timeAtStart = System.currentTimeMillis();

        final State initState = problem.getInit();
        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }

        float hInit = h.computeEstimate(initState);
        heuristicTime += System.currentTimeMillis() - timeAtStart;

        if (hInit == Float.MAX_VALUE) {
            deadEndsDetected++;
            return null;
        }
        nodesEvaluated++;

        SearchNode init = new SearchNode(initState.clone(), null, null, 0, hInit * hw, hInit, saveSearchSpace);
        if (this.helpfulActions) {
            init.helpfulActions = h.getTransitions(helpfulActions);
        }
        // Log evento "generate" init
        if (eventLogger != null && enableEventLogging) {
            eventLogger.logGenerate(init, null);
        }
        super.initHandle(init);

        float bound = hInit * hw;
        while (true) {
            float nextBound = Float.MAX_VALUE;
            Stack<SearchNode> frontier = new Stack<>();
            frontier.push(init);

            while (!frontier.isEmpty()) {
                final SearchNode currentNode = frontier.pop();

                // Log evento "expand"
                if (eventLogger != null && enableEventLogging) {
                    eventLogger.logExpand(currentNode);
                }

                nodesExpanded++;

                if (problem.goalSatisfied(currentNode.s)) {
                    totalTime = System.currentTimeMillis() - timeAtStart;

                    // Log evento "close"
                    if (eventLogger != null && enableEventLogging) {
                        eventLogger.logClose(currentNode);
                    }

                    return currentNode;
                }

                for (Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s, getActionsToSearch(currentNode, problem, h)); it.hasNext(); ) {
                    final Pair<State, Object> transition = it.next();
                    float gNew = problem.gValue(currentNode.s, transition.getSecond(), transition.getFirst(), currentNode.gValue);
                    float hNew = h.computeEstimate(transition.getFirst());
                    heuristicTime += System.currentTimeMillis() - timeAtStart;

                    float fNew = gNew + hw * hNew;

                    if (fNew <= bound) {
                        SearchNode child = new SearchNode(transition.getFirst(), transition.getSecond(), currentNode,
                                gNew, fNew, hNew, saveSearchSpace);

                        if (helpfulActions) {
                            child.helpfulActions = h.getTransitions(helpfulActions);
                        }
                        if (saveSearchSpace) {
                            currentNode.add_descendant(child);
                        }

                        // Log evento "generate"
                        if (eventLogger != null && enableEventLogging) {
                            eventLogger.logGenerate(child, currentNode);
                        }

                        frontier.push(child);
                        nodesEvaluated++;
                    } else {
                        nextBound = Math.min(nextBound, fNew);
                        deadEndsDetected++;
                    }
                }

                // Log evento "close"
                if (eventLogger != null && enableEventLogging) {
                    eventLogger.logClose(currentNode);
                }
            }

            if (nextBound == Float.MAX_VALUE) {
                totalTime = System.currentTimeMillis() - timeAtStart;
                return null;
            }

            bound = nextBound;
        }
    }
}
