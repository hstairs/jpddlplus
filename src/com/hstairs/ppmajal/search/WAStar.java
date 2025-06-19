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

public class WAStar extends SearchEngine {
    protected final boolean optimality;
    protected long previous;
    protected float hAtInit;
    final protected float hw;
    final protected TieBreaker tieBreaker;
    final protected boolean saveSearchSpace;
    final protected boolean enableEventLogging;
    final protected float gBound;

    enum retCode{inserted, deadend, duplicated}

    public WAStar(float hw, boolean optimality, boolean helpfulActionsPruning, TieBreaker tieBreaker, boolean saveSearchSpace, boolean enableEventLogging, float gBound){
        super(helpfulActionsPruning);
        this.optimality = optimality;
        this.hw = hw;
        this.tieBreaker = tieBreaker;
        this.saveSearchSpace = saveSearchSpace;
        this.gBound = gBound;
        this.enableEventLogging = enableEventLogging;
    }

    public SearchStats getStats(){
        return new SearchStats(nodesExpanded,nodesEvaluated,deadEndsDetected,duplicatedDetected,totalTime,heuristicTime);
    }

    protected float getPreviousCost(Object2FloatMap<State> gMap, State successorState) {
        return gMap.getOrDefault(successorState.getRepresentative(), G_DEFAULT);
    }

    

    protected retCode queueSuccessor(Object frontier, State successorState,
                                     SearchNode current_node, Object actionsBefore,
                                     float prev_cost, float gSuccessor, Object2FloatMap<State> g, SearchHeuristic h,
                                     float hw) {
        if (Objects.equals(prev_cost, this.G_DEFAULT) || gSuccessor < prev_cost) {
            final long start = System.currentTimeMillis();
            final float hValue = h.computeEstimate(successorState);
            heuristicTime += System.currentTimeMillis() - start;
            if (hValue != Float.MAX_VALUE) {
                final SearchNode node = !optimality ?
                        new SearchNode(successorState, actionsBefore,
                                current_node, gSuccessor, hValue * hw, hValue, saveSearchSpace)
                        : new SearchNode(successorState, actionsBefore,
                        current_node, gSuccessor, hValue * hw + gSuccessor, hValue, saveSearchSpace);
                if (this.helpfulActions) {
                    node.helpfulActions = h.getTransitions(helpfulActions);
                }
                if (saveSearchSpace) {
                    current_node.add_descendant(node);
                }

                // Log evento "generate"
                if (eventLogger != null && enableEventLogging) {
                    eventLogger.logGenerate(node, current_node);
                }

                addInFrontier(frontier, node);
                g.put(successorState.getRepresentative(), gSuccessor);
                return retCode.inserted;
            } else {
                return retCode.deadend;
            }
        }
        return retCode.duplicated;
    }

    protected void addInFrontier(Object frontier, SearchNode newNode) {
        if (frontier instanceof Queue) {
            ((Queue) frontier).add(newNode);
        } else if (frontier instanceof ObjectHeapPriorityQueue) {
            ((ObjectHeapPriorityQueue) frontier).enqueue(newNode);
        }
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
        hAtInit = h.computeEstimate(initState);
        heuristicTime += System.currentTimeMillis() - timeAtStart;
        if (hAtInit == Float.MAX_VALUE) {
            deadEndsDetected++;
            return null;
        } else {
            nodesEvaluated++;
        }

        SearchNode init = new SearchNode(initState.clone(), null, null, 0, hAtInit * hw, hAtInit, saveSearchSpace);
        if (this.helpfulActions) {
            init.helpfulActions = h.getTransitions(helpfulActions);
        }
        super.initHandle(init);
        frontier.enqueue(init);
        //generazione del nodo iniziale
        if (eventLogger != null && enableEventLogging) {
            eventLogger.logGenerate(init, null);
        }

        Object2FloatMap<State> gValueMap = new Object2FloatOpenHashMap<>();
        gValueMap.put(initState, 0f);

        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();

            float prev_cost = getPreviousCost(gValueMap, currentNode.s);
            if (currentNode.gValue != prev_cost) continue;

            // Log evento "expand"
            if (eventLogger != null && enableEventLogging) {
                eventLogger.logExpand(currentNode);
            }
            nodesExpanded++;

            final Boolean res = problem.goalSatisfied(currentNode.s);
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

                queueSuccessor(frontier, transition.getFirst(), currentNode,
                        transition.getSecond(), getPreviousCost(gValueMap, transition.getFirst()), gNew,
                        gValueMap, h, hw);
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
