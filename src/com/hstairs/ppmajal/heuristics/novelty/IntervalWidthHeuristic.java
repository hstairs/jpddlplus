package com.hstairs.ppmajal.heuristics.novelty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.heuristics.novelty.objects.Interval;
import com.hstairs.ppmajal.heuristics.novelty.objects.NumericIntervalAssignment;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;

/**
 * @author Dillon
 */
public class IntervalWidthHeuristic extends NoveltyHeuristic {

  final int numHeuristics;
  final SearchHeuristic[] heuristics;

  private final Map<List<Float>, UnionNumericState> heuristicTuplesToUnionState;

  public IntervalWidthHeuristic(PDDLProblem problem, int k, SearchHeuristic[] heuristics, boolean cachedHeuristic) {
    super(problem, k, NoveltyValue.WIDTH, NoveltyType.INTERVAL, cachedHeuristic);
    this.heuristics = heuristics;
    this.numHeuristics = heuristics.length;
    this.heuristicTuplesToUnionState = new HashMap<>();
  }

  @Override
  public float computeNoveltyEstimate(State s0) {
    final PDDLState s = (PDDLState) s0;

    // compute heuristics
    List<Float> heuristicTuple = new ArrayList<>();
    for (int i = 0; i < numHeuristics; i++) {
      heuristicTuple.add(computeHeuristic(heuristics[i], s0));
    }

    // compute novelty
    int h;
    UnionNumericState unionNumericState;
    if (!heuristicTuplesToUnionState.containsKey(heuristicTuple)) {
      unionNumericState = new UnionNumericState(problem, k);
      heuristicTuplesToUnionState.put(heuristicTuple, unionNumericState);
      h = 0;  // new heuristic tuple
    } else {
      unionNumericState = heuristicTuplesToUnionState.get(heuristicTuple);
      if (k >= 1 && unionNumericState.has_k1_novelty(s)) {
        h = 1;
      } else if (k >= 2 && unionNumericState.has_k2_novelty(s)) {
        h = 2;
      } else {
        h = 3;
      }
    }

    return h;
  }

  @Override
  public Object[] getTransitions(boolean helpful) {
    return heuristics[0].getTransitions(helpful);
  }

  @Override
  public Collection<TransitionGround> getAllTransitions() {
    return problem.getTransitions();
  }

  static class UnionNumericState {

    final int k;
    final int nNumFluents;
    final int nBoolFluents;

    private final List<Interval> intervals;

    private final Set<Integer> b1Seen;
    private final Set<NumericIntervalAssignment> n1Seen;
    private Set<Pair<Integer, Integer>> b2Seen;
    private Set<Pair<NumericIntervalAssignment, NumericIntervalAssignment>> n2Seen;
    private Set<Pair<Integer, NumericIntervalAssignment>> bnSeen;

    // temporary interval assignment from a state
    private final List<NumericIntervalAssignment> tempNumIntAssignments;

    public UnionNumericState(PDDLProblem problem, int k) {
      this.k = k;
      nNumFluents = problem.getTotNumberOfNumVariables();
      nBoolFluents = problem.getTotNumberOfBoolVariables();

      PDDLState s0 = (PDDLState) problem.getInit();
      List<Double> stateNumFluents = s0.getNumericalFluents();
      intervals = new ArrayList<>();
      for (int i = 0; i < nNumFluents; i++) {
        intervals.add(new Interval(stateNumFluents.get(i)));
      }

      b1Seen = new HashSet<>();
      n1Seen = new HashSet<>();
      bnSeen = new HashSet<>();
      b2Seen = new HashSet<>();
      n2Seen = new HashSet<>();

      tempNumIntAssignments = new ArrayList<>(Collections.nCopies(nNumFluents, null));
    }

    boolean has_k1_novelty(PDDLState s) {
      boolean novel = false;
      List<Integer> stateBoolFluents = s.getBoolIds();
      List<Double> stateNumFluents = s.getNumericalFluents();

      // check for new propositions
      for (Integer stateBoolFluent : stateBoolFluents) {
        novel = novel || b1Seen.add(stateBoolFluent);
      }

      // check for new numerical variable assignments and update
      for (int i = 0; i < stateNumFluents.size(); i++) {
        int iInterval = intervals.get(i).getInterval(stateNumFluents.get(i));
        NumericIntervalAssignment a1 = new NumericIntervalAssignment(i, iInterval);
        tempNumIntAssignments.set(i, a1);
        novel = novel || n1Seen.add(a1);
      }

      return novel;
    }

    boolean has_k2_novelty(PDDLState s) {
      boolean novel = false;
      List<Integer> stateBoolFluents = s.getBoolIds();
      List<Double> stateNumFluents = s.getNumericalFluents();

      // check for new proposition pairs
      for (int i = 0; i < stateBoolFluents.size(); i++) {
        Integer iVal = stateBoolFluents.get(i);
        for (int j = i + 1; j < stateBoolFluents.size(); j++) {
          Integer jVal = stateBoolFluents.get(j);
          // iVal < jVal because collected from bitset
          Pair<Integer, Integer> a2 = new Pair<>(iVal, jVal);

          novel = novel || b2Seen.add(a2);
        }
      }

      // check for new numerical variable pair assignments and update
      for (int iVar = 0; iVar < stateNumFluents.size(); iVar++) {
        NumericIntervalAssignment iVarVal = tempNumIntAssignments.get(iVar);
        for (int jVar = iVar + 1; jVar < stateNumFluents.size(); jVar++) {
          NumericIntervalAssignment jVarVal = tempNumIntAssignments.get(jVar);
          Pair<NumericIntervalAssignment, NumericIntervalAssignment> a2 = new Pair<>(
              iVarVal, jVarVal);

          novel = novel || n2Seen.add(a2);
        }
      }

      // check for new propositional-numerical variable pair assignments and update
      for (int iVar = 0; iVar < stateNumFluents.size(); iVar++) {
        NumericIntervalAssignment iVarVal = tempNumIntAssignments.get(iVar);
        for (Integer jVal : stateBoolFluents) {
          Pair<Integer, NumericIntervalAssignment> a2 = new Pair<>(jVal, iVarVal);

          novel = novel || bnSeen.add(a2);
        }
      }

      return novel;
    }
  }
}
