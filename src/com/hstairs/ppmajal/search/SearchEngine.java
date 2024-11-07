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

import com.hstairs.ppmajal.heuristics.advanced.H1;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.objects.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * @author enrico
 */
public class SearchEngine {

  protected final SearchProblem problem;
  final boolean verboseHeuristic;
  // definition
  final private float G_DEFAULT = Float.NaN;
  final private SearchHeuristic heuristic;
  final private List<SearchHeuristic> heuristics;
  private final PrintStream out;
  private final List<Boolean> helpfulActionsPruningList;
  // externalise internal state of things
  public float currentG;
  public int constraintsViolations;
  protected State lastState;
  int numQueues;
  int numHeuristics;
  long timeSinceBeginning;
  long previousTime;
  long previousTimeShort;

  // configuration
  private float depthLimit;
  private boolean helpfulActionsPruning;
  private TieBreaking tbRule;

  // stats
  private int nodesReopened;
  private int nodesExpanded;
  private long heuristicCpuTime;
  private long overallSearchTime;
  private int numberOfEvaluatedStates;
  private int deadEndsDetected;
  private int duplicatesNumber;
  private long startGlobal;

  // search config
  private float wh;
  private boolean optimality;
  private boolean gbfs;
  private boolean multiQueue;
  private boolean lazySearch;
  private boolean completeSearch = false;

  // search variables
  private State initState;
  private Object2FloatMap<State> gMap;
  private ObjectHeapPriorityQueue<SearchNode> frontier;
  private List<ObjectHeapPriorityQueue<SearchNode>> queues;
  private Set<State> poppedFromIncompleteQueue;
  private Set<State> poppedFromCompleteQueue;
  private int startIterQueueCount;
  private int curQueueCount;
  private int curHeuristicIndex;
  private boolean deadEnd;
  private List<Float> hValues;

  public SearchEngine(
          PrintStream out,
          List<SearchHeuristic> heuristics,
          SearchProblem problem,
          boolean verboseHeuristic
  ) {
    wh = 1;
    depthLimit = Long.MAX_VALUE;
    optimality = true;
    heuristic = heuristics.get(0);
    heuristic.setPrintStream(out);
    this.out = out;
    this.heuristics = heuristics;
    this.problem = problem;
    this.verboseHeuristic = verboseHeuristic;
    helpfulActionsPruningList = new ArrayList<>();
    for (SearchHeuristic searchHeuristic : heuristics) {
      searchHeuristic.setPrintStream(out);
      if (searchHeuristic.getClass() == H1.class) {
        helpfulActionsPruningList.add(((H1) searchHeuristic).helpfulActionsComputation);
      } else {
        helpfulActionsPruningList.add(false);
      }
    }
  }

  private void initSearch() throws RuntimeException {
    nodesExpanded = 0;
    nodesReopened = 0;
    numberOfEvaluatedStates = 0;
    deadEndsDetected = 0;
    duplicatesNumber = 0;
    constraintsViolations = 0;
    heuristicCpuTime = 0;
    currentG = 0f;
    initState = problem.getInit();
    gMap = new Object2FloatLinkedOpenHashMap<>();
    numHeuristics = heuristics.size();

    if (multiQueue) {
      numQueues = numHeuristics;
      queues = new LinkedList<>();
      for (int i = 0; i < numQueues; i++) {
        queues.add(new ObjectHeapPriorityQueue<>(new TieBreaker(this.tbRule, i)));
      }
      poppedFromIncompleteQueue = new HashSet<>();
      poppedFromCompleteQueue = new HashSet<>();
      startIterQueueCount = 0;
      curQueueCount = 0;
    } else {
      frontier = new ObjectHeapPriorityQueue<>(new TieBreaker(this.tbRule, 0));
    }

    startGlobal = System.currentTimeMillis();
  }

  /**
   * The method implements WA*-like search algorithms systematically.
   * <p>
   * Solve the problem by using WA*, A* or GBFS depending on the evaluation function f the frontier
   * is prioritized by, where f = wg * g(n) + wh * h(n) wg = 1.
   * <p>
   * For GBFS: wg = 0, wh = 1.
   * <p>
   * For A*: wg = 1, wh = 1.
   * <p>
   * For WA*: wg = 1, wh > 1.
   * <p>
   * The weights wg and wh should be set by SearchStrategies.setWG() and SearchStrategies.setWH()
   * before the method is called. Heuristics function should also be setup.
   *
   * @param problem the task planning problem to be solved.
   * @param timeout in milliseconds
   * @return null if the problem is unsolvable, SearchNode of goal state otherwise.
   */
  private SearchNode WAStarAlg(SearchProblem problem, long timeout) throws TimeoutException {
    initSearch();

    if (!problem.satisfyGlobalConstraints(initState)) {
      out.println("Initial State is not valid");
      return null;
    }

    // initial node
    float hAtInit = heuristic.computeEstimate(initState);
    out.println("h(s_0)=" + hAtInit);
    SearchNode init = new SearchNode(initState.clone(), 0, wh * hAtInit);
    if (this.helpfulActionsPruning) {
      init.helpfulActions = heuristic.getTransitions(helpfulActionsPruning);
    }
    frontier.enqueue(init);
    gMap.put(initState, 0f);

    // Main search loop
    float bestf = 0;
    previousTime = 0;
    previousTimeShort = 0;
    while (!frontier.isEmpty()) {
      timeSinceBeginning = (System.currentTimeMillis() - startGlobal);
      if (timeSinceBeginning >= timeout) {
        throw new TimeoutException("Timeout has been reached: bailing out");
      }

      final SearchNode currentNode = frontier.dequeue();

      if (currentNode.gValue >= depthLimit) {
        setOverallSearchTime(System.currentTimeMillis() - startGlobal);
        continue;
      }

      final float previousG = getPreviousCost(currentNode.s);
      final float g_node = currentNode.gValue;

      if (g_node == previousG) {

        float currentF;
        if (lazySearch) {
          numberOfEvaluatedStates++;
          // not implemented for WA*
          currentF = heuristic.computeEstimate(currentNode.s);
        } else {
          currentF = currentNode.f.get(0);
        }
        final float hValueInCurrentNode = !gbfs ? currentF - currentNode.gValue : currentF;

        onlineLogging();

        if (optimality && (bestf < currentF)) {
          // this is the debugLevel for when the planner is run in optimality modality
          bestf = currentNode.gValue + hValueInCurrentNode;
          out.println(
                  "f(n) = " + bestf + " (Expanded Nodes: " + getNodesExpanded() + ", Evaluated States: "
                          + getNumberOfEvaluatedStates() + ", Time: "
                          + (float) ((System.currentTimeMillis() - startGlobal)) / 1000.0 + ")"
                          + " Frontier Size: " + frontier.size());
        }
        if (!optimality && hAtInit > (hValueInCurrentNode)) {
          out.println(
                  " h(n)=" + (hValueInCurrentNode) + ", g(n)=" + currentNode.gValue + ", evaluated "
                          + numberOfEvaluatedStates + ", expanded " + nodesExpanded);
          hAtInit = hValueInCurrentNode;
          currentG = currentNode.gValue;
        }

        final Boolean res = problem.goalSatisfied(currentNode.s);
        if (res == null) {
          // this means it is a dead-end
          deadEndsDetected++;
          continue;
        }
        nodesExpanded++;
        if (res) {
          setOverallSearchTime(System.currentTimeMillis() - startGlobal);
          currentG = currentNode.gValue;
          return currentNode;
        }

        // successors
        for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s,
                getActionsToSearch(currentNode)
        ); it.hasNext(); ) {
          final Pair<State, Object> next = it.next();
          final State successorState = next.getFirst();
          final Object action = next.getSecond();

          // skip this if violates global constraints
          final float successorG = problem.gValue(currentNode.s, action, successorState,
                  currentNode.gValue
          );
          if (Objects.equals(successorG, this.G_DEFAULT)) {
            this.deadEndsDetected++;
            continue;
          }

          final float previousCost = getPreviousCost(successorState);

          if (Objects.equals(previousCost, this.G_DEFAULT) || successorG < previousCost) {
            setEvaluatedStates(getEvaluatedStates() + 1);
            float hValue;

            if (lazySearch) {
              hValue = hValueInCurrentNode;
            } else {
              final long start = System.currentTimeMillis();
              hValue = heuristic.computeEstimate(successorState);
              setHeuristicCpuTime(getHeuristicCpuTime() + System.currentTimeMillis() - start);

              if (hValue == Float.MAX_VALUE) {
                deadEndsDetected++;
                continue;
              }
            }

            final float fVal = hValue * wh + (gbfs ? 0 : successorG);
            final SearchNode node = new SearchNode(successorState, action, currentNode, successorG,
                    fVal
            );
            if (this.helpfulActionsPruning) {
              node.helpfulActions = heuristic.getTransitions(helpfulActionsPruning);
            }
            frontier.enqueue(node);
            setGValue(successorState, successorG);
          } else {
            duplicatesNumber++;
          }
        }
      }
    }

    System.out.println("Search space exhausted!");
    return null;
  }

  /**
   * The method implements multi-queue GBFS.
   *
   * @param problem the task planning problem to be solved.
   * @param timeout in milliseconds
   * @return null if the problem is unsolvable, SearchNode of goal state otherwise.
   */
  private SearchNode AltGBFSAlg(SearchProblem problem, long timeout) throws TimeoutException {
    initSearch();
    assert (!optimality && gbfs);

    if (!problem.satisfyGlobalConstraints(initState)) {
      out.println("Initial State is not valid");
      return null;
    }

    // initial node
    List<Float> initHValues = computeMultiHeuristic(initState);
    if (deadEnd) {
      deadEndsDetected++;
      out.println("Initial State is a deadend");
      return null;
    }
    for (int i = 0; i < numHeuristics; i++) {
      out.println("h_" + i + "(s_0)=" + initHValues.get(i));
    }
    SearchNode initNode = new SearchNode(initState.clone(), 0, null, 0, initHValues);
    computeHelpfulActions(initNode);
    for (int i = 0; i < numQueues; i++) {
      queues.get(i).enqueue(initNode);
    }
    gMap.put(initState, 0f);

    if (completeSearch) {
      throw new UnsupportedOperationException("Complete search not implemented");
    }
    if (lazySearch) {
      throw new UnsupportedOperationException("Lazy search not implemented");
    }

    // Main search loop
    List<Float> bestH = initHValues;
    previousTime = 0;
    previousTimeShort = 0;
    while (true) {
      timeSinceBeginning = (System.currentTimeMillis() - startGlobal);
      if (timeSinceBeginning >= timeout) {
        throw new TimeoutException("Timeout has been reached: bailing out");
      }

      // pop from non-empty multi queue, and check if node is already popped from another queue
      // if we selected the complete search option
      curQueueCount = startIterQueueCount;
      int tmpCount = 0;
      while (queues.get(curQueueCount).isEmpty() && tmpCount < numQueues) {
        curQueueCount = (curQueueCount + 1) % numQueues;
        tmpCount++;
      }
      startIterQueueCount = (curQueueCount + 1) % numQueues;
      if (tmpCount == numQueues) {
        System.out.println("all queues empty!");
        break;
      }
      final SearchNode curNode = queues.get(curQueueCount).dequeue();
      final State curState = curNode.s;
      curHeuristicIndex = curQueueCount;
      if (poppedFromCompleteQueue.contains(curState)) {
        continue;
      }
      poppedFromCompleteQueue.add(curState);

      // continue search logic after popping node
      float currentQueueH = curNode.f.get(curHeuristicIndex);
      currentG = curNode.gValue;

      onlineLogging();

      if (currentQueueH < bestH.get(curHeuristicIndex)) {
        out.println(" h_" + curHeuristicIndex + "(n)=" + (currentQueueH) + ", g(n)=" + currentG
                + ", evaluated " + numberOfEvaluatedStates + ", expanded " + nodesExpanded);
        bestH.set(curHeuristicIndex, currentQueueH);
      }

      final Boolean res = problem.goalSatisfied(curState);
      if (res == null) {
        deadEndsDetected++;
        continue;
      }
      if (res) {
        setOverallSearchTime(System.currentTimeMillis() - startGlobal);
        return curNode;
      }
      nodesExpanded++;

      // successors
      Object[] successorActions = getActionsToSearch(curNode, curHeuristicIndex);
      for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(
              curState, successorActions); it.hasNext(); ) {
        final Pair<State, Object> next = it.next();
        final State succState = next.getFirst();
        final Object action = next.getSecond();

        if (gMap.containsKey(succState)) {
          duplicatesNumber++;
          continue;
        }

        // EAGER heuristic computation
        hValues = computeMultiHeuristic(succState);
        if (deadEnd) {
          continue;
        }

        // init node and add to multiple queues
        final float succG = problem.gValue(curState, action, succState, currentG);
        setGValue(succState, succG);
        final SearchNode node = new SearchNode(succState, action, curNode, succG, hValues);
        computeHelpfulActions(node);
        for (int i = 0; i < numQueues; i++) {
          queues.get(i).enqueue(node);
        }
      }
    }

    System.out.println("Search space exhausted!");
    return null;
  }

  private List<Float> computeMultiHeuristic(State state) {
    numberOfEvaluatedStates++;
    deadEnd = false;
    List<Float> hValues = new LinkedList<>();
    final long start = System.currentTimeMillis();
    for (int i = 0; i < numHeuristics; i++) {
      float hValue = heuristics.get(i).computeEstimate(state);
      if (hValue == Float.MAX_VALUE) {
        deadEnd = true;
        deadEndsDetected++;
        return null;
      }
      hValues.add(hValue);  // f = h in GBFS
    }
    setHeuristicCpuTime(getHeuristicCpuTime() + System.currentTimeMillis() - start);
    return hValues;
  }

  private void computeHelpfulActions(SearchNode node) {
    for (int i = 0; i < numHeuristics; i++) {
      if (helpfulActionsPruningList.get(i)) {
        node.helpfulActionsList.set(i,
                heuristics.get(i).getTransitions(helpfulActionsPruningList.get(i))
        );
      }
    }
  }

  private void onlineLogging() {
    if (timeSinceBeginning >= previousTime + 10000) {
      final float speed = (float) getNodesExpanded() / ((float) timeSinceBeginning / 1000);
      out.println(
              "---Time: " + timeSinceBeginning / 1000 + "s; " + "Expanded Nodes: " + getNodesExpanded()
                      + " (Avg-Speed " + speed + " n/s); " + "Evaluated States: "
                      + getNumberOfEvaluatedStates());
      previousTime = timeSinceBeginning;
    }

    if (verboseHeuristic && timeSinceBeginning >= previousTimeShort + 1000) {
      out.println("___Time: " + timeSinceBeginning / 1000 + "s; ");
      for (int i = 0; i < heuristics.size(); i++) {
        out.println("h_" + i);
        heuristics.get(i).dumpHeuristicStats();
      }
      previousTimeShort = timeSinceBeginning;
      out.println("___");
    }
  }

  private void setGValue(State successorState, float succG) {
    gMap.put(successorState.getRepresentative(), succG);
  }

  private float getPreviousCost(State successorState) {
    return gMap.getOrDefault(successorState.getRepresentative(), G_DEFAULT);
  }

  public State getLastState() {
    return this.lastState;
  }

  public LinkedList WAStar(SearchProblem problem, long timeout) throws TimeoutException {
    this.multiQueue = false;
    SearchNode end = this.WAStarAlg(problem, timeout);
    return this.extractPlan(end);
  }

  public LinkedList GBFS(SearchProblem problem, long timeout) throws TimeoutException {
    this.optimality = false;
    this.gbfs = true;
    return this.WAStar(problem, timeout);
  }

  public LinkedList AltGBFS(SearchProblem problem, long timeout) throws TimeoutException {
    this.optimality = false;
    this.gbfs = true;
    this.multiQueue = true;
    SearchNode end = this.AltGBFSAlg(problem, timeout);
    return this.extractPlan(end);
  }

  public LinkedList extractPlan(SimpleSearchNode node) {
    LinkedList plan = new LinkedList<>();
    lastState = node.s;
    while (node.transition != null) {
      plan.addFirst(node.transition);
      node = node.father;
    }
    return plan;
  }

  Object[] getActionsToSearch(SimpleSearchNode currentNode) {
    if (helpfulActionsPruning && currentNode != null) {
      return ((SearchNode) currentNode).helpfulActions;
    }
    return heuristic.getTransitions(false);
  }

  Object[] getActionsToSearch(SimpleSearchNode currentNode, int i) {
    if (helpfulActionsPruningList.get(i) && currentNode != null) {
      return ((SearchNode) currentNode).helpfulActionsList.get(i);
    }
    return heuristic.getTransitions(false);
  }

  public int getEvaluatedStates() {
    return getNumberOfEvaluatedStates();
  }

  protected void setEvaluatedStates(int states_evaluated) {
    this.setNumberOfEvaluatedStates(states_evaluated);
  }

  public int getNodesReopened() {
    return nodesReopened;
  }

  protected void setNodesReopened(int nodesReopened) {
    this.nodesReopened = nodesReopened;
  }

  public int getNodesExpanded() {
    return nodesExpanded;
  }

  protected void setNodesExpanded(int nodesExpanded) {
    this.nodesExpanded = nodesExpanded;
  }

  public long getHeuristicCpuTime() {
    return heuristicCpuTime;
  }

  protected void setHeuristicCpuTime(long heuristicCpuTime) {
    this.heuristicCpuTime = heuristicCpuTime;
  }

  public long getOverallSearchTime() {
    return overallSearchTime;
  }

  protected void setOverallSearchTime(long overallSearchTime) {
    this.overallSearchTime = overallSearchTime;
  }

  public int getNumberOfEvaluatedStates() {
    return numberOfEvaluatedStates;
  }

  protected void setNumberOfEvaluatedStates(int numberOfEvaluatedStates) {
    this.numberOfEvaluatedStates = numberOfEvaluatedStates;
  }

  public int getDeadEndsDetected() {
    return deadEndsDetected;
  }

  public int getDuplicatesDetected() {
    return duplicatesNumber;
  }

  /* Config setting functions */

  public void setDepthLimit(float depthLimit) {
    out.println("Setting horizon to: " + depthLimit);
    this.depthLimit = depthLimit;
  }

  public void setWH(float wh) {
    this.wh = wh;
  }

  public void setTieBreaking(TieBreaking tieBreaking) {
    System.out.println("Tie breaking: " + tieBreaking);
    this.tbRule = tieBreaking;
  }

  public void setHelpfulActionsPruning(boolean helpfulActionsPruning) {
    this.helpfulActionsPruning = helpfulActionsPruning;
  }

  public void setLazySearch(boolean lazySearch) {
    out.println("Lazy search: " + lazySearch);
    this.lazySearch = lazySearch;
  }

  public void setCompleteSearch(boolean completeSearch) {
    this.completeSearch = completeSearch;
  }

  public enum TieBreaking {
    LOWERG, HIGHERG, ARBITRARY
  }

  public static class TieBreaker implements Comparator<SearchNode> {

    final TieBreaking tb;
    final int i;

    public TieBreaker(TieBreaking tb, int i) {
      super();
      this.tb = tb;
      this.i = i;
    }

    @Override
    public int compare(SearchNode o1, SearchNode o2) {
      Float h1 = o1.f.get(i);
      Float h2 = o2.f.get(i);
      if (Objects.equals(h1, h2)) {
        return switch (tb) {
          case HIGHERG -> Float.compare(o2.gValue, o1.gValue);
          case LOWERG -> Float.compare(o1.gValue, o2.gValue);
          case ARBITRARY -> 0;
        };
      }
      return h1.compareTo(h2);
    }
  }
}