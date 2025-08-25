package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import it.unimi.dsi.fastutil.objects.Object2BooleanLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import org.apache.commons.lang3.tuple.Pair;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class EHS extends SearchEngine {


    public EHS(boolean helpfulActionsPruning) {
        super(helpfulActionsPruning);
    }

    @Override
    public SearchStats getStats() {
        return new SearchStats(nodesExpanded,nodesEvaluated,deadEndsDetected,duplicatedDetected,
                totalTime,heuristicTime);
    }

    @Override
    public SimpleSearchNode search(SearchProblem p, SearchHeuristic h, PrintStream out) {
        zeroCounters();
        totalTime = 0;
        heuristicTime = 0;
        long startTime = System.currentTimeMillis();
        if (!p.satisfyGlobalConstraints(p.getInit())) {
            out.println("Initial State is not valid");
            return null;
        }
        SearchNode current = new SearchNode(p.getInit(),null,null,0,0);
        LinkedList<Pair<BigDecimal, Object>> plan = new LinkedList<>();
        Object visited = null;
        visited = new Object2BooleanLinkedOpenHashMap();
        ((Object2BooleanMap<State>) visited).put(current.s, true);
        while (true) {
            final Boolean b = p.goalSatisfied(current.s);
            if (b == null) {
                break;
            }else if (b) {
                    totalTime = System.currentTimeMillis()-startTime;
                    return current;
                }
            final SearchNode succ = oldBreathFirstSearchImplementation(current, p,h,
                    (Object2BooleanMap<State>) visited, System.out);

            if (succ == null) {
                out.println("No plan exists with EHC");
                break;
            }else {
                current = succ;
            }
        }
        totalTime = System.currentTimeMillis()-startTime;
        this.extenalLogger.afterExecution();
        return null;
    }    

    private SearchNode oldBreathFirstSearchImplementation(SearchNode init, SearchProblem problem,
                                                          SearchHeuristic heuristic,
                                                          Object2BooleanMap<State> visited,
                                                          PrintStream out) {
        //out.println("Visited size:"+visited.size());

        Queue<SearchNode> frontier = new LinkedList<>();
        float currentValue = heuristic.computeEstimate(init.s);
        frontier.add(init);
        this.tryLog(init, ExternalLoggerLogType.Generating);
        if (this.helpfulActions) {
            //throw new UnsupportedOperationException();
            init.helpfulActions = heuristic.getTransitions(true);
        }
//        out.println(init.relaxed_plan_from_heuristic);
        out.println("h(n):" + currentValue + " ");
        float current_gn = 0;
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.poll();
            this.tryLog(node, ExternalLoggerLogType.Expanding);
            nodesExpanded++;
            if (node.gValue > current_gn) {
                out.println(" " + node.gValue);
                current_gn = node.gValue;
            }
            for (Iterator<org.jgrapht.alg.util.Pair<State, Object>> it = problem.getSuccessors(node.s,
                    getActionsToSearch(node, problem,heuristic)); it.hasNext();) {
                final org.jgrapht.alg.util.Pair<State, Object> next = (org.jgrapht.alg.util.Pair<State, Object>) it.next();

                final Object act = next.getSecond();
                State temp = next.getFirst();
                if (!problem.satisfyGlobalConstraints(temp)) {
                    continue;
                }
                boolean visitedTemp = visited.getOrDefault(temp, false);
                if (!visitedTemp) {
                    visited.put(temp, true);
                    final Float newG;
                    newG = problem.gValue(node.s, act, temp, node.gValue);
                    if (newG == null) {
                        continue;
                    }
                    long start = System.currentTimeMillis();
                    final Float d = heuristic.computeEstimate(temp);
                    heuristicTime += System.currentTimeMillis() - start;
                    //out.println("try");
                    if (d != Float.MAX_VALUE) {// && d <= current_value) {
                        nodesEvaluated++;
                        SearchNode newNode = new SearchNode(temp, act, node, newG, 0);
                        this.tryLog(newNode, ExternalLoggerLogType.Generating);
                        frontier.add(newNode);
                        if (this.helpfulActions) {
                            newNode.helpfulActions = heuristic.getTransitions(true);
                        }
                        if (problem.milestoneReached(d, currentValue, temp)) {
                            out.println("h(n):" + d);
                            return newNode;
                        }
                    } else {
                        deadEndsDetected++;
                    }
                }else{
                    duplicatedDetected++;
                }

            }

            this.tryLog(node, ExternalLoggerLogType.Closing);
        }
        return null;

    }
}
