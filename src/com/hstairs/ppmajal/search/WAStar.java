package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
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
    final protected float gBound;

    public WAStar(float hw, boolean optimality, boolean helpfulActionsPruning, TieBreaker tieBreaker, boolean saveSearchSpace, float gBound){
        super(helpfulActionsPruning);
        this.optimality = optimality;
        this.hw = hw;
        this.tieBreaker = tieBreaker;
        this.saveSearchSpace = saveSearchSpace;
        this.gBound = gBound;
    }
    public SearchStats getStats(){
        return new SearchStats(nodesExpanded,nodesEvaluated,deadEndsDetected,duplicatedDetected,totalTime,heuristicTime);
    }
    protected float getPreviousCost(Object2FloatMap<State> gMap, State successorState) {
        return gMap.getOrDefault(successorState.getRepresentative(), G_DEFAULT);
    }
    enum retCode{inserted, deadend, duplicated};
    protected retCode queueSuccessor(Object frontier, State successorState,
                                     SearchNode current_node, Object actionsBefore,
                                     float prev_cost, float gSuccessor, Object2FloatMap<State> g, SearchHeuristic h,
                                     float hw) {
        if (Objects.equals(prev_cost, this.G_DEFAULT) || gSuccessor < prev_cost) {
            final long start = System.currentTimeMillis();
            final float hValue = h.computeEstimate(successorState);
            heuristicTime += System.currentTimeMillis() - start;
            if (hValue != Float.MAX_VALUE) {// && (d + succ_g) < this.depthLimit) {
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
        out.println("h(I):"+hAtInit);

        heuristicTime += System.currentTimeMillis() - timeAtStart;
        if (hAtInit == Float.MAX_VALUE) {
            deadEndsDetected++;
            return null;
        }else{
            nodesEvaluated++;
        }
        SearchNode init = new SearchNode(initState.clone(),
                0,hw*hAtInit,hAtInit, saveSearchSpace);
        if (this.helpfulActions) {
            init.helpfulActions = h.getTransitions(helpfulActions);
        }

        super.initHandle(init); //This is to inspect the search space if needed

        frontier.enqueue(init);

        Object2FloatMap<State> gValue = new Object2FloatOpenHashMap<>();
        gValue.put(initState, 0f);//The initial state is at 0 distance, of course.
        float bestf = 0;
        previous = 0;
        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();
            if (currentNode.gValue == getPreviousCost(gValue, currentNode.s)){
                nodesExpanded++;
                long fromTheBeginning = (System.currentTimeMillis() - timeAtStart);
                final Boolean res = problem.goalSatisfied(currentNode.s);
                if (res == null) {//this means it is a dead-end
                    deadEndsDetected++;
                    continue;
                }else if (res) {
                    totalTime = (System.currentTimeMillis() - timeAtStart);
                    return currentNode;
                }
                bestf = printInfoDuringSearch(timeAtStart,out,bestf,fromTheBeginning,
                        nodesExpanded,nodesEvaluated,frontier,currentNode);
                final Object[] actionsToSearch = getActionsToSearch(currentNode, problem, h);
                for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s,actionsToSearch); it.hasNext();) {
                    final Pair<State, Object> next = it.next();
                    final State successorState = next.getFirst();
                    final Object act = next.getSecond();
                    final float successorG = problem.gValue(currentNode.s, act, successorState, currentNode.gValue);
                    if (successorG < gBound) {
                        if (Objects.equals(successorG, this.G_DEFAULT)) {
                            deadEndsDetected++;
                            continue;
                        }
                        switch (this.queueSuccessor(frontier, successorState, currentNode, act,
                                getPreviousCost(gValue, successorState), successorG, gValue, h, hw)) {
                            case inserted -> nodesEvaluated++;
                            case deadend -> deadEndsDetected++;
                            case duplicated -> duplicatedDetected++;
                        }
                    }
                }
            }
        }
        return null;
    }



    protected float printInfoDuringSearch(long timeAtStart, PrintStream out, float bestf, long fromTheBeginning,
                                        int nodesExpanded,
                                        int nodesEvaluated, ObjectHeapPriorityQueue<SearchNode> frontier,
                                        SearchNode currentNode) {
        if (fromTheBeginning >= previous + 10000) {
            final float speed = nodesExpanded / (fromTheBeginning / 1000);
            out.println("-------------Time: " + fromTheBeginning / 1000
                    + "s ; Expanded Nodes: " + nodesExpanded +
                    " (Avg-Speed " + speed + " n/s); Evaluated States: " + nodesEvaluated);
            previous = fromTheBeginning;
        }
        final float hValueInCurrentNode = optimality ? currentNode.f-currentNode.gValue : currentNode.f;
        if (optimality && (bestf < currentNode.f)) {//this is the debugLevel for when the planner is run in optimality modality
            bestf = currentNode.gValue + hValueInCurrentNode;
            out.println("f(n) = " + currentNode.f + " (Expanded Nodes: " + nodesExpanded
                    + ", Evaluated States: " + nodesEvaluated + ", Time: " +
                    (float) ((System.currentTimeMillis() - timeAtStart)) / 1000.0 + ")"+
                    " Frontier Size: "+frontier.size());
        }else if (!optimality && hAtInit > (hValueInCurrentNode)) {
            out.println(" g(n)= " + currentNode.gValue + " h(n)=" + (hValueInCurrentNode));
            hAtInit = hValueInCurrentNode;
        }
        return bestf;
    }
}
