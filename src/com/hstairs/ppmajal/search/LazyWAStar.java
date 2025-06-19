package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SearchEventLogger;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

public class LazyWAStar extends SearchEngine {
    protected final boolean optimality;
    final protected float hw;
    final protected TieBreaker tieBreaker;
    final protected boolean saveSearchSpace;
    final protected boolean enableEventLogging;

    public LazyWAStar(float hw, boolean optimality, boolean helpfulActionsPruning, TieBreaker tieBreaker, boolean saveSearchSpace, boolean enableEventLogging){
        super(helpfulActionsPruning);
        this.optimality = optimality;
        this.hw = hw;
        this.tieBreaker = tieBreaker;
        this.saveSearchSpace = saveSearchSpace;
        this.enableEventLogging = enableEventLogging;
    }

    public SearchStats getStats(){
        return new SearchStats(nodesExpanded,nodesEvaluated,deadEndsDetected,duplicatedDetected,totalTime,heuristicTime);
    }

    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        zeroCounters();
        final State initState = problem.getInit();

        final ObjectHeapPriorityQueue<SearchNode> frontier =
                new ObjectHeapPriorityQueue<>(tieBreaker);

        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }

        long timeAtStart = System.currentTimeMillis();
        float hInit = h.computeEstimate(initState);
        heuristicTime += System.currentTimeMillis() - timeAtStart;

        if (hInit == Float.MAX_VALUE) {
            deadEndsDetected++;
            return null;
        } else {
            nodesEvaluated++;
        }

        SearchNode init = new SearchNode(initState.clone(), null, null, 0, hInit * hw, hInit, saveSearchSpace);
        if (this.helpfulActions) {
            init.helpfulActions = h.getTransitions(helpfulActions);
        }
        // Log evento "generate" init
        if (eventLogger != null && enableEventLogging) {
            eventLogger.logGenerate(init, null);
        }

        super.initHandle(init);
        frontier.enqueue(init);

        Object2FloatMap<State> gValueMap = new Object2FloatOpenHashMap<>();
        gValueMap.put(initState, 0f);

        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();   

            float prev_cost = gValueMap.getOrDefault(currentNode.s.getRepresentative(), Float.NaN);
            if (currentNode.gValue != prev_cost) continue;

            // Log evento "expand"
            if (eventLogger != null && enableEventLogging) {
                eventLogger.logExpand(currentNode);
            }

            nodesExpanded++;

            Boolean res = problem.goalSatisfied(currentNode.s);
            if (res == null) break;
            if (res) {
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
                float oldCost = gValueMap.getOrDefault(transition.getFirst().getRepresentative(), Float.NaN);
                if (Objects.equals(oldCost, Float.NaN) || gNew < oldCost) {
                    long start = System.currentTimeMillis();
                    float hValue = h.computeEstimate(transition.getFirst());
                    heuristicTime += System.currentTimeMillis() - start;

                    if (hValue != Float.MAX_VALUE) {
                        SearchNode node = new SearchNode(transition.getFirst(), transition.getSecond(), currentNode,
                                gNew, optimality ? gNew + hValue * hw : hValue * hw, hValue, saveSearchSpace);

                        if (this.helpfulActions) {
                            node.helpfulActions = h.getTransitions(helpfulActions);
                        }
                        if (saveSearchSpace) {
                            currentNode.add_descendant(node);
                        }

                        // Log evento "generate"
                        if (eventLogger != null && enableEventLogging) {
                            eventLogger.logGenerate(node, currentNode);
                        }

                        frontier.enqueue(node);
                        gValueMap.put(transition.getFirst().getRepresentative(), gNew);
                        nodesEvaluated++;
                    } else {
                        deadEndsDetected++;
                    }
                } else {
                    duplicatedDetected++;
                }
            }

            // Log evento "close"
            if (eventLogger != null && enableEventLogging) {
                eventLogger.logClose(currentNode);
            }
        }

        totalTime = System.currentTimeMillis() - timeAtStart;
        return null;
    }
}
