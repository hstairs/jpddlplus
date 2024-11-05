package com.hstairs.ppmajal.heuristics.novelty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class IntervalQuantifiedBothHeuristic extends NoveltyHeuristic {

  final SearchHeuristic heuristic;

  private final List<Interval> intervals;

  Map<Integer, Float> b1NoveltyMap;
  Map<NumericIntervalAssignment, Float> n1NoveltyMap;
  Map<Pair<Integer, Integer>, Float> b2NoveltyMap;
  Map<Pair<NumericIntervalAssignment, NumericIntervalAssignment>, Float> n2NoveltyMap;
  Map<Pair<Integer, NumericIntervalAssignment>, Float> bnNoveltyMap;

  // temporary interval assignment from a state
  private final List<NumericIntervalAssignment> tempNumIntAssignments;

  // variables to help compute heuristic
  float qbl1, qbu1;
  float qbl2, qbu2;
  List<Integer> stateBoolFluents;
  List<Double> stateNumFluents;
  float h;

  public IntervalQuantifiedBothHeuristic(PDDLProblem problem, int k, SearchHeuristic heuristic, boolean cachedHeuristic) {
    super(problem, k, NoveltyValue.QUANTIFIED_BOTH, NoveltyType.INTERVAL, cachedHeuristic);
    this.heuristic = heuristic;

    PDDLState s0 = (PDDLState) problem.getInit();
    List<Double> stateNumFluents = s0.getNumericalFluents();
    intervals = new ArrayList<>();
    for (int i = 0; i < nNumFluents; i++) {
      intervals.add(new Interval(stateNumFluents.get(i)));
    }

    b1NoveltyMap = new HashMap<>();
    n1NoveltyMap = new HashMap<>();
    b2NoveltyMap = new HashMap<>();
    n2NoveltyMap = new HashMap<>();
    bnNoveltyMap = new HashMap<>();

    tempNumIntAssignments = new ArrayList<>(Collections.nCopies(nNumFluents, null));
  }

  private void hqb1() {
    qbl1 = C1;  // = h_qn
    qbu1 = C1;  // = second case in Eqn. (3) in [Katz et al., ICAPS-17]

    // 1-subsets
    for (Integer a1 : stateBoolFluents) {
      if (!b1NoveltyMap.containsKey(a1) || h < b1NoveltyMap.get(a1)) {
        qbl1--;
        b1NoveltyMap.put(a1, h);
      } else if (h > b1NoveltyMap.get(a1)) {
        qbu1++;
      }
    }

    for (int var = 0; var < nNumFluents; var++) {
      int iInterval = intervals.get(var).getInterval(stateNumFluents.get(var));
      NumericIntervalAssignment a1 = new NumericIntervalAssignment(var, iInterval);
      tempNumIntAssignments.set(var, a1);
      if (!n1NoveltyMap.containsKey(a1) || h < n1NoveltyMap.get(a1)) {
        qbl1--;
        n1NoveltyMap.put(a1, h);
      } else if (h > n1NoveltyMap.get(a1)) {
        qbu1++;
      }
    }
  }

  private void hqb2() {
    qbl2 = C2;
    qbu2 = C2;

    // 2-subsets
    for (int i = 0; i < stateBoolFluents.size(); i++) {
      Integer iVal = stateBoolFluents.get(i);
      for (int j = i + 1; j < stateBoolFluents.size(); j++) {
        Integer jVal = stateBoolFluents.get(j);
        // iVal < jVal because collected from bitset
        Pair<Integer, Integer> a2 = new Pair<>(iVal, jVal);

        if (!b2NoveltyMap.containsKey(a2) || h < b2NoveltyMap.get(a2)) {
          qbl2--;
          b2NoveltyMap.put(a2, h);
        } else if (h > b2NoveltyMap.get(a2)) {
          qbu2++;
        }
      }
    }

    for (int iVar = 0; iVar < stateNumFluents.size(); iVar++) {
      NumericIntervalAssignment iVarVal = tempNumIntAssignments.get(iVar);
      for (int jVar = iVar + 1; jVar < stateNumFluents.size(); jVar++) {
        NumericIntervalAssignment jVarVal = tempNumIntAssignments.get(jVar);
        Pair<NumericIntervalAssignment, NumericIntervalAssignment> a2 = new Pair<>(
            iVarVal, jVarVal);

        if (!n2NoveltyMap.containsKey(a2) || h < n2NoveltyMap.get(a2)) {
          qbl2--;
          n2NoveltyMap.put(a2, h);
        } else if (h > n2NoveltyMap.get(a2)) {
          qbu2++;
        }
      }
    }

    for (int iVar = 0; iVar < stateNumFluents.size(); iVar++) {
      NumericIntervalAssignment iVarVal = tempNumIntAssignments.get(iVar);
      for (Integer jVal : stateBoolFluents) {
        Pair<Integer, NumericIntervalAssignment> a2 = new Pair<>(jVal, iVarVal);

        if (!bnNoveltyMap.containsKey(a2) || h < bnNoveltyMap.get(a2)) {
          qbl2--;
          bnNoveltyMap.put(a2, h);
        } else if (h > bnNoveltyMap.get(a2)) {
          qbu2++;
        }
      }
    }
  }

  @Override
  public float computeNoveltyEstimate(State stateInput) {
    h = computeHeuristic(heuristic, stateInput);
    final PDDLState s = (PDDLState) stateInput;
    stateBoolFluents = s.getBoolIds();
    stateNumFluents = s.getNumericalFluents();

    hqb1();

    if (k == 1) {
      return qbl1 < C1 ? qbl1 : qbu1;
    }

    // k = 2
    hqb2();
    if (qbl1 < C1) {
      return qbl1;
    } else if (qbl2 < C2) {
      return C1 + qbl2;
    } else {
      return C1 + qbu2;
    }
  }

  @Override
  public Object[] getTransitions(boolean helpful) {
    return heuristic.getTransitions(helpful);
  }

  @Override
  public Collection<TransitionGround> getAllTransitions() {
    return problem.getTransitions();
  }
}
