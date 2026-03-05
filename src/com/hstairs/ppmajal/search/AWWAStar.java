package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import it.unimi.dsi.fastutil.PriorityQueue;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.*;

public class AWWAStar extends SearchEngine {
    protected final boolean optimality;
    protected long previous;
    protected float hAtInit;
    final protected float hw;
    final protected TieBreaker tieBreaker;
    final protected boolean saveSearchSpace;
    final protected float gBound;

    final protected boolean bucketPriorityQueue;
    protected final int max_width;


    public AWWAStar(float hw, boolean optimality, boolean helpfulActionsPruning,
                    TieBreaker tieBreaker, boolean saveSearchSpace, float gBound, int max_width){
        this(hw,optimality,helpfulActionsPruning,tieBreaker,saveSearchSpace,gBound,false, max_width);
    }
    public AWWAStar(float hw, boolean optimality, boolean helpfulActionsPruning,
                    TieBreaker tieBreaker, boolean saveSearchSpace, float gBound, boolean bucketPriorityQueue, int max_width){
        super(helpfulActionsPruning);
        this.optimality = optimality;
        this.hw = hw;
        this.tieBreaker = tieBreaker;
        this.saveSearchSpace = saveSearchSpace;
        this.gBound = gBound;
        this.bucketPriorityQueue = bucketPriorityQueue;
        this.max_width = max_width;
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
        } else if (frontier instanceof BucketPriorityQueue){
            ((BucketPriorityQueue) frontier).enqueue(newNode);
        }

        this.tryLog(newNode, ExternalLoggerLogType.Generating);
    }
    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        zeroCounters();
        final State initState = problem.getInit();

        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }
                long timeAtStart = System.currentTimeMillis();
        hAtInit = h.computeEstimate(initState);
        final PriorityQueue<SearchNode> frontier = getPriorityQueue((int)hAtInit*10);

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
        this.tryLog(init, ExternalLoggerLogType.Generating);

        Object2FloatMap<State> gValue = new Object2FloatOpenHashMap<>();
        gValue.put(initState, 0f);//The initial state is at 0 distance, of course.
        float bestf = 0;
        float besth = Float.MAX_VALUE;
        previous = 0;
        int width = 1;
        int plateauSteps = 0;
        double alpha = 1.5;
        double dynamicPatienceTreshold = 1.5;
        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();
            this.tryLog(currentNode, ExternalLoggerLogType.Expanding);

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
                List<Iterator<Pair<State, Object>>> iteratorList = new ArrayList<>();
                List <SearchNode> nodes = new ArrayList<>();
                iteratorList.add(problem.getSuccessors(currentNode.s,actionsToSearch));
                nodes.add(currentNode);
                if (currentNode.f < besth) {
                    besth = currentNode.f;
                    plateauSteps = 0;
                    if (width > 1) {
                        width = 1;
                        dynamicPatienceTreshold = 1.5;
                        //System.out.println("width = " + width);
                        System.out.println("width restarted at 1");
                    }
                    //}else if (currentNode.f == besth){
                }else{
                    plateauSteps += 1;
                    if (plateauSteps > dynamicPatienceTreshold){
                        plateauSteps = 0;
                        width += 1;
                        dynamicPatienceTreshold = Math.pow(alpha,width);
                        System.out.println("width expanded to "+width + " to escape the plateau");
                }
                    //}else{
                    //} else if (width < this.max_width) {
                    //width += 1;
                    //plateauSteps = 0;
                    //dynamicPatienceTreshold = Math.pow(alpha,width);
                    //System.out.println("width = "+width);
                    //widthPenalty = width * 0.01;
                }
                if (width > 1){
                    for (int i = 0; i < width-1; i++) {
                        if (!frontier.isEmpty()){
                            SearchNode newNode  = frontier.dequeue();
                            this.tryLog(newNode, ExternalLoggerLogType.Expanding);

                            if (newNode.gValue == getPreviousCost(gValue, newNode.s)) {
                                nodesExpanded++;
                                Boolean newRes = problem.goalSatisfied(newNode.s);

                                if (newRes == null) {//this means it is a dead-end
                                    deadEndsDetected++;
                                } else if (newRes) {
                                    totalTime = (System.currentTimeMillis() - timeAtStart);
                                    return newNode;
                                }
                            }
                            Object[] newActionsToSearch = getActionsToSearch(newNode, problem, h);
                            iteratorList.add(problem.getSuccessors(newNode.s,newActionsToSearch));
                            nodes.add(newNode);
                        }else{
                            break;
                        }
                    }
                }
                for (int i = 0; i < iteratorList.size(); i++) {
                    Iterator<Pair<State, Object>> currentIt = iteratorList.get(i);
                    SearchNode parentNode = nodes.get(i);
                    while (currentIt.hasNext()) {
                        final Pair<State, Object> next = currentIt.next();
                        final State successorState = next.getFirst();
                        final Object act = next.getSecond();
                        final float successorG = problem.gValue(parentNode.s, act, successorState, parentNode.gValue);
                        if (successorG < gBound) {
                            if (Objects.equals(successorG, this.G_DEFAULT)) {
                                deadEndsDetected++;
                                continue;
                            }
                            switch (this.queueSuccessor(frontier, successorState, parentNode, act,
                                    getPreviousCost(gValue, successorState), successorG, gValue, h, hw)) {
                                case inserted -> nodesEvaluated++;
                                case deadend -> deadEndsDetected++;
                                case duplicated -> duplicatedDetected++;
                            }
                        }
                    }
                }
            }

            this.tryLog(currentNode, ExternalLoggerLogType.Closing);
        }
        return null;
    }

    protected PriorityQueue getPriorityQueue(int size) {
        if (bucketPriorityQueue){
            return new BucketPriorityQueue(size,0,1);
        }else{
            return new ObjectHeapPriorityQueue<>(tieBreaker);
        }
    }


    protected float printInfoDuringSearch(long timeAtStart, PrintStream out, float bestf, long fromTheBeginning,
                                        int nodesExpanded,
                                        int nodesEvaluated, Object frontier,
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
                    " Frontier Size: "+size(frontier));
        }else if (!optimality && hAtInit > (hValueInCurrentNode)) {
            out.println(" g(n)= " + currentNode.gValue + " h(n)=" + (hValueInCurrentNode));
            hAtInit = hValueInCurrentNode;
        }
        return bestf;
    }

    private int size(Object frontier) {
        if (frontier instanceof Queue) {
            return ((Queue) frontier).size();
        } else if (frontier instanceof ObjectHeapPriorityQueue) {
            return ((ObjectHeapPriorityQueue) frontier).size();
        } else if (frontier instanceof BucketPriorityQueue){
            return ((BucketPriorityQueue) frontier).size();
        }
        return -1;
    }
}
