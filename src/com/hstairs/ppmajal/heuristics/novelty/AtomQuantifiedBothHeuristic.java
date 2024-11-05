package jpddlplus.heuristics.novelty;

import jpddlplus.extraUtils.Pair;
import jpddlplus.heuristics.novelty.objects.NumericAtomAssignment;
import jpddlplus.heuristics.novelty.objects.NumericIntervalAssignment;
import jpddlplus.problem.PDDLProblem;
import jpddlplus.problem.PDDLState;
import jpddlplus.problem.State;
import jpddlplus.search.SearchHeuristic;
import jpddlplus.transition.TransitionGround;

import java.util.*;

/**
 * @author Dillon
 */
public class AtomQuantifiedBothHeuristic extends NoveltyHeuristic {

  final SearchHeuristic heuristic;

  Map<Integer, Float> b1NoveltyMap;
  Map<NumericAtomAssignment, Float> n1NoveltyMap;
  Map<Pair<Integer, Integer>, Float> b2NoveltyMap;
  Map<Pair<NumericAtomAssignment, NumericAtomAssignment>, Float> n2NoveltyMap;
  Map<Pair<Integer, NumericAtomAssignment>, Float> bnNoveltyMap;

  // temporary interval assignment from a state
  private final List<NumericAtomAssignment> tempNumAtomAssignments;

  // variables to help compute heuristic
  float qbl1, qbu1;
  float qbl2, qbu2;
  List<Integer> stateBoolFluents;
  List<Double> stateNumFluents;
  float h;

  public AtomQuantifiedBothHeuristic(PDDLProblem problem, int k, SearchHeuristic heuristic, boolean cachedHeuristic) {
    super(problem, k, NoveltyValue.QUANTIFIED_BOTH, NoveltyType.ATOM, cachedHeuristic);
    this.heuristic = heuristic;

    b1NoveltyMap = new HashMap<>();
    n1NoveltyMap = new HashMap<>();
    b2NoveltyMap = new HashMap<>();
    n2NoveltyMap = new HashMap<>();
    bnNoveltyMap = new HashMap<>();

    tempNumAtomAssignments = new ArrayList<>(Collections.nCopies(nNumFluents, null));
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
      NumericAtomAssignment a1 = new NumericAtomAssignment(var, stateNumFluents.get(var));
      tempNumAtomAssignments.set(var, a1);
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
      NumericAtomAssignment iVarVal = tempNumAtomAssignments.get(iVar);
      for (int jVar = iVar + 1; jVar < stateNumFluents.size(); jVar++) {
        NumericAtomAssignment jVarVal = tempNumAtomAssignments.get(jVar);
        Pair<NumericAtomAssignment, NumericAtomAssignment> a2 = new Pair<>(iVarVal, jVarVal);

        if (!n2NoveltyMap.containsKey(a2) || h < n2NoveltyMap.get(a2)) {
          qbl2--;
          n2NoveltyMap.put(a2, h);
        } else if (h > n2NoveltyMap.get(a2)) {
          qbu2++;
        }
      }
    }

    for (int iVar = 0; iVar < stateNumFluents.size(); iVar++) {
      NumericAtomAssignment iVarVal = tempNumAtomAssignments.get(iVar);
      for (Integer jVal : stateBoolFluents) {
        Pair<Integer, NumericAtomAssignment> a2 = new Pair<>(jVal, iVarVal);

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
