package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.sql.Array;
import java.util.*;

public class LazyWAStar extends WAStar {


    final private boolean helpfulActionsWithPruning;

    public LazyWAStar(float hw, boolean optimality, boolean helpfulActions, boolean saveSearchSpace, TieBreaker tb, float boundG,boolean helpfulActionsWithPruning) {
        super(hw, optimality, helpfulActions, tb, saveSearchSpace, boundG);
        this.helpfulActionsWithPruning = helpfulActionsWithPruning;
    }
    public LazyWAStar(float hw, boolean optimality, boolean helpfulActions, boolean saveSearchSpace, TieBreaker tb, float boundG) {
        this(hw, optimality, helpfulActions, saveSearchSpace,tb, boundG,false);
    }

    Object[] getActionsToSearch(List helpful, SearchHeuristic h) {

        ArrayList res = new ArrayList();
        res.addAll(h.getAllTransitions());
        if (helpful!= null){
            for (var v: helpful) {
                if (!(v instanceof TransitionGround)) {
                    res.add(v);
                }
            }
        }

        return res.toArray();
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
        SearchNode init = new SearchNode(initState.clone(),
                0, hw * hAtInit, hAtInit, saveSearchSpace);
        if (this.helpfulActions) {
            init.helpfulActions = h.getTransitions(helpfulActions);
        }
        super.initHandle(init); //This is to inspect the search space if needed
        frontier.enqueue(init);
        Object2FloatMap<State> gValueMap = new Object2FloatOpenHashMap<>();
        gValueMap.put(initState, 0f);//The initial state is at 0 distance, of course.
        float bestf = 0;
        previous = 0;
        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();
            if (currentNode.gValue == getPreviousCost(gValueMap, currentNode.s)) {
                nodesExpanded++;
                long fromTheBeginning = (System.currentTimeMillis() - timeAtStart);
                final Boolean res = problem.goalSatisfied(currentNode.s);
                if (res == null) {//this means it is a dead-end
                    deadEndsDetected++;
                    continue;
                } else if (res) {
                    totalTime = (System.currentTimeMillis() - timeAtStart);
                    return currentNode;
                }
                final long start = System.currentTimeMillis();
                final float hExpanded = h.computeEstimate(currentNode.s);
                if (hExpanded != Float.MAX_VALUE) {
                    final List helpful = helpfulActions ? List.of(h.getTransitions(true)) : null;

                    heuristicTime += System.currentTimeMillis() - start;
                    bestf = printInfoDuringSearch(timeAtStart, out, bestf, fromTheBeginning,
                            nodesExpanded, nodesEvaluated, frontier, currentNode);

                    Object[] actionsToSearch = getActionsToSearch(helpful,h);
                    for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s,actionsToSearch
                            ); it.hasNext(); ) {
                        final Pair<State, Object> next = it.next();
                        final State successorState = next.getFirst();
                        final Object act = next.getSecond();
                        final float successorG = problem.gValue(currentNode.s, act, successorState, currentNode.gValue);
                        if (successorG < gBound) {
                            if (Objects.equals(successorG, this.G_DEFAULT)) {
                                deadEndsDetected++;
                            } else {
                                float previousCost = getPreviousCost(gValueMap, successorState);
                                if (Objects.equals(previousCost, this.G_DEFAULT) || (optimality && successorG < previousCost)) { //Otherwise already seen
                                    float hValue = hExpanded;
                                    Object t;
                                    boolean helpT = false;
                                    if (act instanceof ImmutablePair tr) {
                                        t = tr.getLeft();
                                        helpT = true;
                                    } else {
                                        t = act;
                                    }
                                    //if (!helpfulActions || helpfulActionsWithPruning || helpful.contains(t)){
                                    if (helpfulActions && helpful.contains(t) ) {
                                        if (helpT) {
                                            //hValue = h.computeEstimate(successorState);
                                            hValue -= (successorG - currentNode.gValue);///(int)((ImmutablePair)act).getRight();
                                        }else{
                                            hValue -= (successorG - currentNode.gValue);
                                        }
                                        hValue = Math.max(0, hValue);
                                    }
                                    nodesEvaluated++;
                                    //if (hValue != Float.MAX_VALUE && (!helpT || hValue <hExpanded)) {
                                        final SearchNode toExplore = new SearchNode(successorState, act,
                                                currentNode, successorG, !optimality
                                                ? hValue : successorG + hValue * hw, hValue, saveSearchSpace);
                                        if (saveSearchSpace) {
                                            currentNode.add_descendant(toExplore);
                                        }
                                        addInFrontier(frontier, toExplore);
                                        gValueMap.put(successorState.getRepresentative(), successorG);
                                    //}
                                } else {
                                    duplicatedDetected++;
                                }
                            }
                        }
                    }
                }else{
                    deadEndsDetected++;
                }
            }
        }

        return null;
    }
}
