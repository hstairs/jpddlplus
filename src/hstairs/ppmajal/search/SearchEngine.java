/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.objects.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeoutException;
import org.jgrapht.alg.util.Pair;

/**
 * @author enrico
 */
public class SearchEngine {

    //definition
    final private float G_DEFAULT = Float.NaN;
    public int deadEndsDetected;
    public int duplicatesNumber;
    //debug
    public boolean saveSearchTreeAsJson;
    public SearchNode searchSpaceHandle;
    public int debugLevel;
    //configuration
    public float depthLimit;
    public boolean bfsTieBreaking;
    public boolean helpfulActionsPruning;
    public boolean forgettingEhc;
    public TieBreaking tbRule;
    //externalise internal state of things
    public float currentG;
    public boolean processes = false;

    public int constraintsViolations;
    protected State lastState;
    //stats
    private int nodesReopened;
    private int nodesExpanded;
    private long heuristicCpuTime;
    private long overallSearchTime;
    private int priorityQueueSize;
    private int numberOfEvaluatedStates;
    private float hw;
    final private SearchHeuristic heuristic;
    private boolean optimality;
    private long beginningTime;
    //dealing with continuous change

    private boolean incremental;
    private long previousTime;
    private int causalDeadEnds;
    private Object2FloatMap<State> idaStar;
    private PrintStream out;
    protected final SearchProblem problem;
    private boolean printPrefixAtEachStep = false;
    private boolean gbfs;

    public SearchEngine(SearchHeuristic h, SearchProblem problem) {
        this(System.out, h, problem);
    }

    public SearchEngine(PrintStream out, SearchHeuristic h, SearchProblem problem) {
        setNodesExpanded(0);
        setNodesReopened(0);
        setNumberOfEvaluatedStates(0);
        duplicatesNumber = 0;
        hw = 1;
        saveSearchTreeAsJson = false;
        depthLimit = Long.MAX_VALUE;
        bfsTieBreaking = true;
        optimality = true;
        forgettingEhc = false;
        bfsTieBreaking = true;
        this.out = out;
        heuristic = h;
        this.problem = problem;
    }

    private Object getMap(Explorator explorator) {
        Object visited = null;
        if (explorator.equals(Explorator.BRFS)) {
            visited = new Object2BooleanLinkedOpenHashMap();
        } else {
            visited = new Object2FloatLinkedOpenHashMap();
        }
        return visited;
    }


    /*
    Very Important and Experimental. In this case the successor is a list of waiting action. This is needed so as to retrieve it afterwards
     */
    protected SearchNode queueSuccessor(Object frontier, State successorState, SearchNode current_node, Object actionsBefore, float prev_cost, float succ_g, Object2FloatMap<State> g, boolean treeSearch) {

        if (Objects.equals(prev_cost, this.G_DEFAULT) || succ_g < prev_cost) {
            setEvaluatedStates(getEvaluatedStates() + 1);
            final long start = System.currentTimeMillis();
            final float hValue = getHeuristic().computeEstimate(successorState);
            setHeuristicCpuTime(getHeuristicCpuTime() + System.currentTimeMillis() - start);
            if (hValue != Float.MAX_VALUE) {// && (d + succ_g) < this.depthLimit) {
                final SearchNode node = gbfs ? new SearchNode(successorState, actionsBefore, current_node, succ_g, hValue * this.getHw(), hValue, this.saveSearchTreeAsJson)
                        : new SearchNode(successorState, actionsBefore, current_node, succ_g, hValue * this.getHw() + succ_g, hValue, this.saveSearchTreeAsJson);
                if (this.helpfulActionsPruning) {
                    node.helpfulActions = getHeuristic().getTransitions(helpfulActionsPruning);
                }
                if (saveSearchTreeAsJson) {
                    current_node.add_descendant(node);
                }
                addInFrontier(frontier, node);
                setGValue(successorState, g, succ_g, treeSearch);
                return node;
            } else {
                deadEndsDetected++;
                return null;
            }
        } else {
            duplicatesNumber++;
            return null;
        }
    }

    private void setGValue(State successorState, Object2FloatMap<State> g, float succ_g, boolean treeSearch) {
        if (!treeSearch) {
            g.put(successorState.getRepresentative(), succ_g);
        }
    }

    protected SearchNode queueSuccessor(Object frontier, State successor_state, SearchNode current_node, Object action_s, Object2FloatMap<State> g) {
        return queueSuccessor(frontier, successor_state, current_node, action_s, g, false);
    }

    private SearchNode queueSuccessor(Object frontier, State successor_state, SearchNode current_node, Object action_s, Object2FloatMap<State> g, boolean treeSearch) {
        float succ_g = current_node.gValue + 1;
        float prev_cost = getPreviousCost(g, successor_state, treeSearch);
        //The node is put in the priority queue whenever one of the following holds
        //if prev_cost == null, then I have never seen this state before
        // if the new cost is better (which can happen in case of inconsistent heuristics or new state evaluation from some other paths
        return this.queueSuccessor(frontier, successor_state, current_node, action_s, prev_cost, succ_g, g, treeSearch);
    }



    private void addInFrontier(Object frontier, SearchNode newNode) {

        if (frontier instanceof Queue) {
            ((Queue) frontier).add(newNode);
        } else if (frontier instanceof ObjectHeapPriorityQueue) {
            ((ObjectHeapPriorityQueue) frontier).enqueue(newNode);
        }

    }

    private LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> a_star(SearchProblem problem) throws Exception {
        this.hw = 1f;
        return this.WAStar();
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> enforceHillClimbing(SearchProblem problem) throws Exception {
        return this.enforcedHillClimbing(problem, Explorator.BRFS);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> enforcedHillClimbing(SearchProblem problem, Explorator explorator) throws Exception {
        long start_global = System.currentTimeMillis();
        setEvaluatedStates(getEvaluatedStates() + 1);

        State current = problem.getInit();

        LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> plan = new LinkedList<>();
//        rel_actions = getHeuristic().reachable;
        setNumberOfEvaluatedStates(0);
        Object visited = null;
        visited = getMap(explorator);
        incremental = true;
        currentG = 0;

        while (true) {
            final Boolean b = problem.goalSatisfied(current);

            if (b == null) {
                return null;
            }
            if (b) {
                break;
            }

            final SearchNode succ;
            System.gc();
            if (explorator.equals(Explorator.BRFS)) {
                succ = oldBreathFirstSearchImplementation(current, problem, (Object2BooleanMap<State>) visited);
            } else {
                succ = WAStar(problem, current, true, (Object2FloatMap<State>) visited, false, Long.MAX_VALUE);
            }

            if (succ == null) {
                out.println("No plan exists with EHC");

                return null;
            }

            current = succ.s;
            currentG = succ.gValue;

            plan.addAll(extractPlan(succ));
            if (forgettingEhc) {
                visited = getMap(explorator);
            }
        }
        setOverallSearchTime(System.currentTimeMillis() - start_global);
        return plan;

    }
    
    private SearchNode oldBreathFirstSearchImplementation(State current, SearchProblem problem, Object2BooleanMap<State> visited) throws Exception {
        //out.println("Visited size:"+visited.size());

        Queue<SearchNode> frontier = new LinkedList<>();
        float currentValue = heuristic.computeEstimate(current);

        SearchNode init = new SearchNode(current, null, null, 0, currentValue);
        frontier.add(init);
        visited.put(init.s, true);
        if (this.helpfulActionsPruning) {
            throw new UnsupportedOperationException();
//            init.helpfulActions = getHeuristic().getHelpfulActions();
        }
//        out.println(init.relaxed_plan_from_heuristic);
        out.println("h(n):" + currentValue + " ");
        float current_gn = 0;
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.poll();
            setNodesExpanded(getNodesExpanded() + 1);
            if (node.gValue > current_gn) {
                out.println(" " + node.gValue);
                current_gn = node.gValue;

            }
            for (Iterator<Pair<State, Object>> it = problem.getSuccessors(node.s, getActionsToSearch(node, problem)); it.hasNext();) {
                final Pair<State, Object> next = (Pair<State, Object>) it.next();

                final Object act = next.getSecond();
                State temp = next.getFirst();
                if (!problem.satisfyGlobalConstraints(temp)) {
                    continue;
                }
                boolean visitedTemp = visited.getOrDefault(temp, false);
                if (!visitedTemp) {
                    visited.put(temp, true);
                    Float newG;
                    newG = problem.gValue(node.s, act, temp, node.gValue);
                    if (newG == null) {
                        continue;
                    }
                    long start = System.currentTimeMillis();
                    final Float d = heuristic.computeEstimate(temp);
                    setHeuristicCpuTime(getHeuristicCpuTime() + System.currentTimeMillis() - start);
                    //out.println("try");
                    setEvaluatedStates(getEvaluatedStates() + 1);
                    if (d != Float.MAX_VALUE) {// && d <= current_value) {

                        SearchNode new_node = new SearchNode(temp, act, node, newG, 0);
                        frontier.add(new_node);
                        if (this.helpfulActionsPruning) {
                            throw new UnsupportedOperationException();
//                            new_node.helpfulActions = heuristic.getHelpfulActions();
                        }
                        if (problem.milestoneReached(d, currentValue, temp)) {
//                            if (d < current_value && problem.isSafeState(temp)) {
                            setNodesExpanded(getNodesExpanded() + 1);
                            out.println("h(n):" + d);
                            return new_node;
                        }
                    } else {
//                            out.println("Dead End");
                        deadEndsDetected++;
                    }
                }

            }
        }
        return null;

    }

    
    
    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> UCS(SearchProblem problem) {
        final ObjectHeapPriorityQueue<SearchNode> frontier = new ObjectHeapPriorityQueue<>(new TieBreaker(this.tbRule));
        final State init = problem.getInit();
        frontier.enqueue(new SearchNode(init, 0,0, 0, false));
        final Object2BooleanOpenHashMap closed = new Object2BooleanOpenHashMap();
        final Object2FloatLinkedOpenHashMap gNode = new Object2FloatLinkedOpenHashMap();
        long start = System.currentTimeMillis();
        nodesExpanded = 0;
        if (this.tbRule == null) {
            tbRule = TieBreaking.LOWERG;
        }
        float maxF = Float.NEGATIVE_INFINITY;
        gNode.put(init, 0);
        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();
            Boolean goalSatisfied = problem.goalSatisfied(currentNode.s);
            if (gNode.getFloat(currentNode.s) == currentNode.gValue) {
                nodesExpanded++;
                if (nodesExpanded % 10000 == 0) {
                    //System.out.println("--------------------------- Expansions:" + nodesExpanded);

                } else if (maxF < currentNode.f) {
                    System.out.println("f(n) " + currentNode.f + " Expansions:" + nodesExpanded + " Frontier:"+frontier.size());
                    maxF = currentNode.f;
                }

                if (goalSatisfied) {
                    return this.extractPlan(currentNode);
                } else {
                    closed.put(currentNode.s, true);
                    for (Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s, getActionsToSearch(currentNode, problem)); it.hasNext();) {
                        final Pair<State, Object> next = it.next();
                        if (!closed.getBoolean(next.getFirst())) {
                            final float gValue = problem.gValue(currentNode.s, next.getSecond(), next.getFirst(), currentNode.gValue);
                            float aFloat = gNode.getOrDefault(next.getFirst(),-1);
                            if (aFloat == -1 || aFloat > gValue){
                                final SearchNode searchNode = new SearchNode(next.getFirst(), next.getSecond(), currentNode, gValue, gValue, 0, false);
                                frontier.enqueue(searchNode);
                                gNode.put(next.getFirst(),gValue);
                            }
                        } else {
                            this.duplicatesNumber++;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private SearchNode _breathFirstSearch(SearchProblem problem){
        State init = problem.getInit();
        SearchNode sn = new SearchNode(init, null,null, 0, 0);
        if (problem.goalSatisfied(init)){
            return sn;
        }
        this.beginningTime = System.currentTimeMillis();
        ObjectOpenHashSet<State> reached = new ObjectOpenHashSet<>();
        reached.add(sn.s);
        ObjectArrayFIFOQueue<SearchNode> frontier = new ObjectArrayFIFOQueue();
        frontier.enqueue(sn);
        int level = 0;
        while(!frontier.isEmpty()){
            SearchNode currentNode = frontier.dequeue();
            nodesExpanded++;
            if (level != currentNode.gValue){
                out.println("d(n): "+ currentNode.gValue +" Expanded: "+nodesExpanded);
                level = (int) currentNode.gValue;
            }
            for (Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s, getActionsToSearch(currentNode, problem)); it.hasNext();) {
                Pair<State, Object> next = it.next();
                if (!reached.contains(next.getFirst())) {
                    this.numberOfEvaluatedStates++;
                    SearchNode newNode;
                    newNode = new SearchNode(next.getFirst(), next.getSecond(), currentNode, currentNode.gValue+1,currentNode.gValue+1, 0, this.saveSearchTreeAsJson);
                    if (problem.goalSatisfied(newNode.s)) {
                        this.overallSearchTime = System.currentTimeMillis()-this.beginningTime;
                        return newNode;
                    }else{
                        reached.add(newNode.s);
                        frontier.enqueue(newNode);
                    }
                }else{
                    
                }
            }
        }
        this.overallSearchTime = System.currentTimeMillis()-this.beginningTime;

        return null;
    }

    /**
     * The method implements weighted-A*-like search algorithms systematically.
     * <p>
     * Solve the problem by using weighted-A* or A* or Uniform Cost Search (UCS)
     * depending on the evaluation function f the frontier is prioritized by,
     * where f = wg * g(n) + wh * h(n) wg = 1, wh = 0, UCS, wg = 1, wh = 1, A*,
     * wg = 1, wh > 1, weighted-A*. The weights wg and wh should be set by
     * SearchStrategies.setWG() and SearchStrategies.setWH() before the method
     * is called. Heuristics function should also be setup.
     *
     * @param problem the problem to be solved.
     * @param extCurrent start from this current state if given as input
     * @param exitOnBestH exit on best h value found (in expansion)
     * @param treeSearch
     * @return null if the problem is unsolvable, a linked list of the plan
     * otherwise.
     * @throws Exception
     */
    private SearchNode WAStar(SearchProblem problem, State extCurrent, boolean exitOnBestH, Object2FloatMap<State> gMap, boolean treeSearch, long timeout) throws Exception {

        State initState = null;
        if (extCurrent == null) {
            initState = problem.getInit();
        } else {
            initState = extCurrent;
        }

        if (this.tbRule == null) {
            tbRule = TieBreaking.ARBITRARY;
        }
        final ObjectHeapPriorityQueue<SearchNode> frontier = new ObjectHeapPriorityQueue<>(new TieBreaker(this.tbRule));
        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }
//        Float hAtInit = getHeuristic().computeEstimate(initState);
        long start_global = System.currentTimeMillis();
        Float hAtInit = getHeuristic().computeEstimate(initState);
        if (incremental) {
            throw new UnsupportedOperationException();
        } else {
            deadEndsDetected = 0;
            constraintsViolations = 0;
//            if (getHeuristic().setup(initState) == Float.MAX_VALUE) {
//                out.println("h(n = s_0)=inf");
//                return null;
//            }
//            out.println("Reachable actions and processes: |A U P U E|:" + getHeuristic().getTransitions(problem).size());
//            setupReachableActionsProcesses(problem);//this maps actions in the heuristic with the action in the execution model
            setHeuristicCpuTime(0);
            duplicatesNumber = 0;
            setNodesReopened(0);
            currentG = 0f;
        }

//        getHeuristic().why_not_active = true;
        printInfo(out);
        out.println("h(n = s_0)=" + hAtInit);//debugging information

//        getHeuristic().why_not_active = false;
        SearchNode init = new SearchNode(initState.clone(), 0,hw*hAtInit,hAtInit, this.saveSearchTreeAsJson);
        if (this.helpfulActionsPruning) {
            init.helpfulActions = getHeuristic().getTransitions(helpfulActionsPruning);
        }

        if (saveSearchTreeAsJson) {
            searchSpaceHandle = init;//this needs to have an handle on the initial state for saving it into a json file
        }

        if (hAtInit == Float.MAX_VALUE) {//this shouldn't happen here.
            deadEndsDetected++;
            return null;
        }


        frontier.enqueue(init);
        gMap.put(initState, 0f);//The initial state is at 0 distance, of course.
        float bestf = 0;
        setNodesExpanded(0);
        this.setEvaluatedStates(0);
        long previous = 0;
        while (!frontier.isEmpty()) {
            final SearchNode currentNode = frontier.dequeue();

            if (currentNode.gValue >= depthLimit) {
                setOverallSearchTime(System.currentTimeMillis() - start_global);
                continue;
            }
            if (saveSearchTreeAsJson) {
                currentNode.set_visited(getNodesExpanded());
            }

            final float previousG = getPreviousCost(gMap, currentNode.s, treeSearch);
            final float g_node = currentNode.gValue;

            if (g_node == previousG || treeSearch) {
                long fromTheBeginning = (System.currentTimeMillis() - start_global);
                if (fromTheBeginning >= timeout) {
                    throw new TimeoutException("Timeout has been reached: bailing out");
                }
                if (fromTheBeginning >= previous + 10000) {
                    final float speed = getNodesExpanded() / (fromTheBeginning / 1000);
                    out.println("-------------Time: " + fromTheBeginning / 1000
                            + "s ; Expanded Nodes: " + getNodesExpanded() + " (Avg-Speed " + speed + " n/s); Evaluated States: " + getNumberOfEvaluatedStates());
                    previous = fromTheBeginning;

                }
                
                final float hValueInCurrentNode = !gbfs ? currentNode.f-currentNode.gValue : currentNode.f;
                
                if (optimality && (bestf < currentNode.f)) {//this is the debugLevel for when the planner is run in optimality modality
                    bestf = currentNode.gValue + hValueInCurrentNode;
                    out.println("f(n) = " + bestf + " (Expanded Nodes: " + getNodesExpanded()
                            + ", Evaluated States: " + getNumberOfEvaluatedStates() + ", Time: " + 
                            (float) ((System.currentTimeMillis() - start_global)) / 1000.0 + ")"+
                            " Frontier Size: "+frontier.size());

                }
                if (!optimality && hAtInit > (hValueInCurrentNode)) {
                    out.println(" g(n)= " + currentNode.gValue + " h(n)=" + (hValueInCurrentNode));
                    hAtInit = hValueInCurrentNode;
                    currentG = currentNode.gValue;
                }

                if (debugLevel == 20 && getNodesExpanded() % 5000 == 0) {
                    out.println("Expanded Nodes / sec:" + (new Float(getNodesExpanded()) * 1000.0 / ((System.currentTimeMillis() - start_global))));
                }

                setPriorityQueueSize(frontier.size());
                if (!checkExternalSolver(currentNode.s,problem)){
                    continue;
                }
                final Boolean res = problem.goalSatisfied(currentNode.s);
                if (res == null) {//this means it is a dead-end
                    deadEndsDetected++;
                    continue;
                }
                if (exitOnBestH && problem.milestoneReached(hValueInCurrentNode, hAtInit, currentNode.s)) {
                    out.println("***************Best H: " + hValueInCurrentNode);
                    return currentNode;
                }
                setNodesExpanded(getNodesExpanded() + 1);
                if (res) {
                    setOverallSearchTime(System.currentTimeMillis() - start_global);
                    currentG = currentNode.gValue;
                    return currentNode;
                }

                //if we have a pddl+ problem, we also branch on waiting.
                if (printPrefixAtEachStep){
                    System.out.println("Current Prefix");
                    SearchNode temp = currentNode;
                    while (temp != null){
                        System.out.println(temp.transition);
                        temp = (SearchNode) temp.father;
                    }
                }
                        
                //In case we use helpful actions pruning. This is highly experimental, though it seems to work pretty well...
                for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s, getActionsToSearch(currentNode, problem)); it.hasNext();) {
                    final Pair<State, Object> next = it.next();
                    final State successorState = next.getFirst();
//                    advanceEvents(frontier,currentNode)
                    final Object act = next.getSecond();
                    //skip this if violates global constraints
                    final float successorG = problem.gValue(currentNode.s, act, successorState, currentNode.gValue);
                    if (Objects.equals(successorG, this.G_DEFAULT)) {
                        this.deadEndsDetected++;
                        continue;
                    }
                    final float previousCost = getPreviousCost(gMap, successorState, treeSearch);
                    this.queueSuccessor(frontier, successorState, currentNode, act, previousCost, successorG, gMap, treeSearch);

                }
            }
        }

        return null;
    }

    private float getPreviousCost(Object2FloatMap<State> gMap, State successorState, boolean treeSearch) {
        if (treeSearch) {
            return G_DEFAULT;
        }
        return gMap.getOrDefault(successorState.getRepresentative(), G_DEFAULT);
    }

    public State getLastState() {
        return this.lastState;
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> WAStar() throws Exception {
        return WAStar(problem, false, Long.MAX_VALUE);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> WAStar(SearchProblem problem, long timeout) throws Exception {
        return WAStar(problem, false, timeout);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> WAStar(SearchProblem problem, boolean treeSearch, long timeout) throws Exception {
        SearchNode end = this.WAStar(problem, null, false, new Object2FloatLinkedOpenHashMap<State>(), treeSearch, timeout);
        if (end != null) {
            return this.extractPlan(end);
        } else {
            return null;
        }
    }
    
    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> breathFirstSearch(SearchProblem problem) throws Exception {
        SimpleSearchNode end = this._breathFirstSearch(problem);
        if (end != null) {
            return this.extractPlan(end);
        } else {
            return null;
        }
    }
    

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> gbfs(SearchProblem problem) throws Exception {

        return this.gbfs(problem, Long.MAX_VALUE);
    }

    private LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> gbfs(SearchProblem problem, boolean optimality) throws Exception {
        this.optimality = optimality;
        return this.WAStar();
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> gbfs(SearchProblem problem, long timeout) throws Exception {
        this.optimality = false;
        this.gbfs = true;
        //this.gw = (float) 0.0;//this is the actual GBFS setting. Otherwise is not gbfs
        return this.WAStar(problem, timeout);
    }

    public LinkedList extractPlan(SimpleSearchNode c) {
        LinkedList plan = new LinkedList<>();
        lastState = c.s;
        while (c.transition != null) {
            if (c.transition != null) {//this is an action
                plan.addFirst(c.transition);
            }
            c = c.father;
        }
        return plan;
    }



    /**
     * @return the hw
     */
    public float getHw() {
        return hw;
    }

    /**
     * @param hw the hw to set
     */
    public void setWH(float hw) {
        this.hw = hw;
    }

    /**
     * @return the heuristic
     */
    public SearchHeuristic getHeuristic() {
        return heuristic;
    }

    /**
     * @return the states_evaluated
     */
    public int getEvaluatedStates() {
        return getNumberOfEvaluatedStates();
    }

    /**
     * @param states_evaluated the states_evaluated to set
     */
    public void setEvaluatedStates(int states_evaluated) {
        this.setNumberOfEvaluatedStates(states_evaluated);
    }


   

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> idastar(SearchProblem problem, boolean checkAlongPrefix, boolean showExpansion, boolean idaStarWithMemory) throws Exception {
        return idastar(problem, checkAlongPrefix, showExpansion, idaStarWithMemory, Long.MAX_VALUE);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> idastar(SearchProblem problem, boolean checkAlongPrefix) throws Exception {
        return idastar(problem, checkAlongPrefix, false, false, Long.MAX_VALUE);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> idastar(SearchProblem problem, boolean checkAlongPrefix, long timeout) throws Exception {
        return idastar(problem, checkAlongPrefix, false, false, timeout);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> idastar(SearchProblem problem, boolean checkAlongPrefix, boolean showExpansion, boolean idaStarWithMemory, long timeout) throws Exception {
        IdaStarSearchNode idastarInt = this.idaStarImplementation(problem, checkAlongPrefix, showExpansion, idaStarWithMemory, timeout);
        return this.extractPlan(idastarInt); 
    }

    private IdaStarSearchNode idaStarImplementation(SearchProblem problem, boolean checkAlongPrefix, boolean showExpansion, boolean idaStarWithMemory, long timeout) throws Exception {
        State initState = problem.getInit();

        beginningTime = System.currentTimeMillis();
        previousTime = beginningTime;
        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }
        deadEndsDetected = 0;
        constraintsViolations = 0;
        printInfo(out);
//        setupReachableActionsProcesses(problem);//this maps actions in the heuristic with the action in the execution model
        setHeuristicCpuTime(0);
        setNodesReopened(0);
        setNodesExpanded(0);
        this.setEvaluatedStates(0);

        Float hAtInit = getHeuristic().computeEstimate(initState);
        out.println("h(n = s_0)=" + hAtInit);//debugging information

        if (hAtInit == Float.MAX_VALUE) {//this shouldn't happen here.
            deadEndsDetected++;
            return null;
        }

        if (idaStarWithMemory) {
            idaStar = new Object2FloatOpenHashMap<>();
        }

        float bound = hAtInit * this.hw;
        long startSearch = System.currentTimeMillis();
        Pair<IdaStarSearchNode, Float> res = null;
        for (;;) {
            res = boundedDepthFirstSearch(problem, bound, false, checkAlongPrefix, showExpansion, idaStarWithMemory, timeout);
            if (res == null || res.getFirst() != null) {
                break;
            }
            bound = res.getSecond();
        }
        setOverallSearchTime((System.currentTimeMillis() - startSearch));
        if (res != null) {
            return res.getFirst();
        }
        return null;

    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> dfsbnb(SearchProblem problem) throws Exception {
        return this.dfsbnb(problem, false);
    }

    public LinkedList<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> dfsbnb(SearchProblem problem, boolean memory) throws Exception {
        State initState = problem.getInit();
        beginningTime = System.currentTimeMillis();
        previousTime = beginningTime;
        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }
        deadEndsDetected = 0;
        constraintsViolations = 0;
        printInfo(out);
//        setupReachableActionsProcesses(problem);//this maps actions in the heuristic with the action in the execution model
        setHeuristicCpuTime(0);
        setNodesReopened(0);
        setNodesExpanded(0);
        this.setEvaluatedStates(0);
        if (memory) {
            idaStar = new Object2FloatOpenHashMap<>();
        }

        long startSearch = System.currentTimeMillis();
        if (Float.isNaN(depthLimit)){
            depthLimit = Float.MAX_VALUE;
        }

        final Pair<IdaStarSearchNode, Float> res = boundedDepthFirstSearch(problem, depthLimit, true, true, memory);
        setOverallSearchTime((System.currentTimeMillis() - startSearch));
        if (res != null) {
            return this.extractPlan(res.getFirst());
        }
        return null;

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

    private Pair<IdaStarSearchNode, Float> boundedDepthFirstSearch(SearchProblem problem, float bound, boolean anytime, boolean checkAlongPrefix, boolean memory) throws TimeoutException {
        return boundedDepthFirstSearch(problem, bound, anytime, checkAlongPrefix, false, memory, Long.MAX_VALUE);
    }

    private Pair<IdaStarSearchNode, Float> boundedDepthFirstSearch(SearchProblem problem, float bound, boolean anytime, boolean checkAlongPrefix, boolean showExpansion, boolean idastarWithMemory, long timeout) throws TimeoutException {
        final Stack<IdaStarSearchNode> frontier = new Stack();

        IdaStarSearchNode init = new IdaStarSearchNode(problem.getInit().clone(), null, null, 0);
        causalDeadEnds = 0;
        frontier.push(init);
        float newBound = Float.POSITIVE_INFINITY;
        out.println("f(n): " + bound + "(Expanded Nodes: " + getNodesExpanded() + ")");
        out.println("-------------------(Dead-Ends: " + deadEndsDetected + ")");
        IdaStarSearchNode bestSol = null;
        while (!frontier.isEmpty()) {
            final IdaStarSearchNode node = frontier.pop();
            long now = System.currentTimeMillis();
            if (now - this.beginningTime > timeout) {
                throw new TimeoutException("");
            }
            if ((now - previousTime) > 10000) {
                out.println("-------------Time: " + (now - this.beginningTime) / 1000 + "s ; Expanded Nodes: " + getNodesExpanded());
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

            setHeuristicCpuTime(getHeuristicCpuTime() + (System.currentTimeMillis() - start));
            setNodesExpanded(getNodesExpanded() + 1);
            if (Objects.equals(g, this.G_DEFAULT) || h == null || h == Float.MAX_VALUE || h == this.G_DEFAULT) {
                this.deadEndsDetected++;
                if (idastarWithMemory) {
                    updateTable((IdaStarSearchNode) node, h);
                }
            } else {
                float f = g + h * this.hw;
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

                            for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(node.s, getActionsToSearch(null, problem)); it.hasNext();) {
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

    
    
    Object[] getActionsToSearch(SimpleSearchNode currentNode, SearchProblem problem) {
        if (helpfulActionsPruning && currentNode != null) {
            return ((SearchNode)currentNode).helpfulActions;
        }
        return heuristic.getTransitions(false);
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

    public int getNodesReopened() {
        return nodesReopened;
    }

    public void setNodesReopened(int nodesReopened) {
        this.nodesReopened = nodesReopened;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(int nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public long getHeuristicCpuTime() {
        return heuristicCpuTime;
    }

    public void setHeuristicCpuTime(long heuristicCpuTime) {
        this.heuristicCpuTime = heuristicCpuTime;
    }

    public long getOverallSearchTime() {
        return overallSearchTime;
    }

    public void setOverallSearchTime(long overallSearchTime) {
        this.overallSearchTime = overallSearchTime;
    }

    public int getNumberOfEvaluatedStates() {
        return numberOfEvaluatedStates;
    }

    public void setNumberOfEvaluatedStates(int numberOfEvaluatedStates) {
        this.numberOfEvaluatedStates = numberOfEvaluatedStates;
    }

    public int getPriorityQueueSize() {
        return priorityQueueSize;
    }

    public void setPriorityQueueSize(int priorityQueueSize) {
        this.priorityQueueSize = priorityQueueSize;
    }

    public int getCausalDeadEnds() {
        return causalDeadEnds;
    }


    protected void printInfo(PrintStream out) {
    }

    protected boolean checkExternalSolver(State s, SearchProblem problem) {
        return true;
    }

    public enum TieBreaking {
        LOWERG,
        HIGHERG,
        ARBITRARY
    }

    public enum Explorator {
        WASTAR, BRFS
    }

    public class TieBreaker implements Comparator<SearchNode> {

        final SearchEngine.TieBreaking tb;
        public boolean bfs;

        public TieBreaker(SearchEngine.TieBreaking tb) {
            super();
            this.tb = tb;
            bfs = true;
        }

        public TieBreaker(SearchEngine.TieBreaking tieBreaking, boolean b) {
            this(tieBreaking);
            bfs = b;
        }

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            final SearchNode other = o2;
            final SearchNode a = o1;
            if (a.f == other.f) {
                switch (tb) {
                    case HIGHERG:
                        //                out.println(this.gValue);
                        if (a.gValue < other.gValue)//goal is farer
                        {
                            return +1;
                        } else if (a.gValue > other.gValue) //goal is closer
                        {
                            return -1;
                        } else {
                            return 0;
                        }
                    case LOWERG:
                        if (a.gValue < other.gValue)//goal is farer
                        {
                            return -1;
                        } else if (a.gValue > other.gValue) //goal is closer
                        {
                            return +1;
                        } else {
                            return 0;
                        }
                    case ARBITRARY:
                        return 0;
                    default:
                        throw new RuntimeException("This shouldn't happen. Wrong tie breaking");
                }
            }
            if (bfs) {
                if (a.f <= other.f) {
                    return -1;
                } else {
                    return 1;
                }
            } else//dfs
            {
                if (a.f <= other.f) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

    }
}
