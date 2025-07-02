package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.IdaStarSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.TimeoutException;

public class IDAStar extends SearchEngine {
    final private float hWeigth;
    final private boolean idaStarWithMemory;
    private Object2FloatMap<State> idaStar;
    final private boolean checkAlongPrefix;
    final private boolean showExpansion;
    private int causalDeadEnds;
    final private PrintStream out;
    private long startTime;
    private long previousTime;
    private long timeout = Long.MAX_VALUE;

    public IDAStar(boolean helpfulActionsPruning, float hWeigth, boolean idaStarWithMemory, boolean checkAlongPrefix, boolean showExpansion, PrintStream out) {
        super(helpfulActionsPruning);
        this.hWeigth = hWeigth;
        this.idaStarWithMemory = idaStarWithMemory;
        this.checkAlongPrefix = checkAlongPrefix;
        this.showExpansion = showExpansion;
        this.out = out;
    }

    @Override
    public SearchStats getStats(){
        return new SearchStats(nodesExpanded,nodesEvaluated,deadEndsDetected,duplicatedDetected,totalTime,heuristicTime);
    }

    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        zeroCounters();
        State initState = problem.getInit();
        startTime = System.currentTimeMillis();
        previousTime = startTime;
        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }
        System.out.println(hWeigth);
        deadEndsDetected = 0;
        long start = System.currentTimeMillis();
        Float hAtInit = h.computeEstimate(initState);
        heuristicTime += System.currentTimeMillis() - start;
        out.println("h(n = s_0)=" + hAtInit);//debugging information

        if (hAtInit == Float.MAX_VALUE) {//this shouldn't happen here.
            deadEndsDetected++;
            return null;
        }
        if (idaStarWithMemory) {
            idaStar = new Object2FloatOpenHashMap<>();
        }
        float bound = hAtInit * hWeigth;
        long startSearch = System.currentTimeMillis();
        Pair<IdaStarSearchNode, Float> res = null;
        for (;;) {
            try {
                res = boundedDepthFirstSearch(problem,h, bound,
                        false, checkAlongPrefix, showExpansion, idaStarWithMemory, timeout);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
            if (res == null || res.getFirst() != null) {
                break;
            }
            bound = res.getSecond();
        }
        totalTime = System.currentTimeMillis() - startTime;
        if (res != null) {
            return res.getFirst();
        }
        return null;
    }
    private Pair<IdaStarSearchNode, Float> boundedDepthFirstSearch(SearchProblem problem,
                                                                   SearchHeuristic heuristic, float bound, boolean anytime,
                                                                   boolean checkAlongPrefix, boolean showExpansion,
                                                                   boolean idastarWithMemory, long timeout) throws TimeoutException {
        final Stack<IdaStarSearchNode> frontier = new Stack();

        IdaStarSearchNode init = new IdaStarSearchNode(problem.getInit().clone(), null, null, 0);
        causalDeadEnds = 0;
        frontier.push(init);
        float newBound = Float.POSITIVE_INFINITY;
        out.println("f(n): " + bound + "(Expanded Nodes: " + nodesExpanded + ")");
        out.println("-------------------(Dead-Ends: " + deadEndsDetected + ")");
        IdaStarSearchNode bestSol = null;
        while (!frontier.isEmpty()) {
            final IdaStarSearchNode node = frontier.pop();
            long now = System.currentTimeMillis();
            if (now - this.startTime > timeout) {
                throw new TimeoutException("");
            }
            if ((now - previousTime) > 10000) {
                out.println("-------------Time: " + (now - this.startTime) / 1000 + "s ; Expanded Nodes: " + nodesExpanded);
                out.println("-------------------(Dead-Ends: " + deadEndsDetected + ")");
                this.previousTime = now;
            }
            final float g;
            if (node.transition != null) {
                g = problem.gValue(node.father.s, node.transition, node.s, node.gValue);
            } else {
                g = 0;
            }
            node.gValue = g;
            if (showExpansion) {
                out.println("Expansion:" + node.transition);
            }
//            out.println("------------");
            long start = System.currentTimeMillis();
            Float h = null;
            if (idastarWithMemory) {
                h = idaStar.get(node.s.getRepresentative());
            }
            if (h == null) {
                h = heuristic.computeEstimate(node.s);
            }

            heuristicTime += (System.currentTimeMillis() - start);
            nodesExpanded++;
        if (Objects.equals(g, this.G_DEFAULT) || h == null || h == Float.MAX_VALUE || h == this.G_DEFAULT) {
                this.deadEndsDetected++;
                if (idastarWithMemory) {
                    updateTable((IdaStarSearchNode) node, h);
                }
            } else {
                float f = g + h * hWeigth;
                if ((f > bound && !anytime) || (f >= bound && anytime)) {
                    if (!anytime && idastarWithMemory) {
                        updateTable((IdaStarSearchNode) node, h);
                    }
                    if (f < newBound && !anytime) {
                        if (problem.goalSatisfied(node.s) != null) {
                            newBound = f;
                        }
                    }
                } else {
                    final Boolean goalSatisfied = problem.goalSatisfied(node.s);
                    if (goalSatisfied != null) {
                        if (goalSatisfied) {
                            if (anytime) {
                                bound = g;
                                out.println("Found solution of cost:" + bound);
                                bestSol = node;
                            } else {
                                return new Pair(node, newBound);
                            }
                        } else {

                            boolean atLeastOne = false;

                            for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(node.s, getActionsToSearch(null, problem,heuristic)); it.hasNext();) {
                                final Pair<State, Object> next = it.next();
                                boolean push = true;
                                if (checkAlongPrefix) {
                                    push = !onThePath(next.getFirst(), node.father);
                                }
                                if (push) {
                                    node.numberOfSons++;
                                    atLeastOne = true;
                                    frontier.push(new IdaStarSearchNode(next.getFirst(), next.getSecond(), node, g));
                                }
                            }
                            if (!atLeastOne) {
                                if (idastarWithMemory) {
                                    updateTable((IdaStarSearchNode) node, h);
                                }
                                causalDeadEnds++;
                            }
                        }
                    } else {
                        if (idastarWithMemory) {
                            updateTable((IdaStarSearchNode) node, null);
                        }
                        this.deadEndsDetected++;
                    }
                }
            }
        }
        if ((newBound == Float.POSITIVE_INFINITY && !anytime) || (bestSol == null && anytime)) {
            return null;
        }
        return new Pair(bestSol, newBound);

    }
    private boolean onThePath(State successorState, SimpleSearchNode father) {
        while (father != null) {
            if (father.s.equals(successorState)) {
                return true;
            }
            father = father.father;
        }
        return false;
    }
    private void updateTable(IdaStarSearchNode s, Float h) {
        IdaStarSearchNode temp = s;
        Float bound = null;
        while (temp.father != null) {
            if (bound == null) {
                if (h == null || temp.gValue == this.G_DEFAULT || h == this.G_DEFAULT || h == Float.MAX_VALUE) {
                    bound = Float.MAX_VALUE;
                } else {
                    bound = h;
                }
            }

            IdaStarSearchNode father = (IdaStarSearchNode) temp.father;
            if (bound != Float.MAX_VALUE) {
                bound += temp.gValue - father.gValue;
            }
            father.numberOfSons = father.numberOfSons - 1;
            float previousBound = father.minSoFar;
            if (bound >= previousBound) {
                bound = previousBound; //keep the minimum
            } else {
                father.minSoFar = bound;
            }
            if (father.numberOfSons == 0) {
                idaStar.put(father.s.getRepresentative(), bound);
                temp = (IdaStarSearchNode) father;
            } else {
                break;
            }
        }
    }
}