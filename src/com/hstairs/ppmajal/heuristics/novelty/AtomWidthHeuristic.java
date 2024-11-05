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
public class AtomWidthHeuristic extends NoveltyHeuristic {

  final int numHeuristics;
  final SearchHeuristic[] heuristics;

  private final Map<List<Float>, UnionNumericState> heuristicTuplesToUnionState;

  public AtomWidthHeuristic(PDDLProblem problem, int k, SearchHeuristic[] heuristics, boolean cachedHeuristic) {
    super(problem, k, NoveltyValue.WIDTH, NoveltyType.ATOM, cachedHeuristic);
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

    // we do not use a bitset which is much faster because bool ids do not actually fill up
    // 0...numBoolFluents-1 (see PDDLProblem actualFluents); it is possible to optimise this
    // but would require changing some code in PDDLProblem
    private final Set<Integer> b1Seen;
    private final Set<NumericAtomAssignment> n1Seen;
    private Set<Pair<Integer, Integer>> b2Seen;
    private Set<Pair<NumericAtomAssignment, NumericAtomAssignment>> n2Seen;  // can be optimised
    private Set<Pair<Integer, NumericAtomAssignment>> bnSeen;

    // temporary interval assignment from a state
    private final List<NumericAtomAssignment> tempNumAtomAssignments;

    public UnionNumericState(PDDLProblem problem, int k) {
      this.k = k;
      nNumFluents = problem.getTotNumberOfNumVariables();
      nBoolFluents = problem.getTotNumberOfBoolVariables();

      b1Seen = new HashSet<>();
      n1Seen = new HashSet<>();
      bnSeen = new HashSet<>();
      b2Seen = new HashSet<>();
      n2Seen = new HashSet<>();

      tempNumAtomAssignments = new ArrayList<>(Collections.nCopies(nNumFluents, null));
    }

    static int pair_to_index_map(int n, int i, int j) {
      // map pair where 0 <= i < j < n to vec index
      return j - i - 1 + (i * n) - (i * (i + 1)) / 2;
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
        NumericAtomAssignment a1 = new NumericAtomAssignment(i, stateNumFluents.get(i));
        tempNumAtomAssignments.set(i, a1);
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
        NumericAtomAssignment iVarVal = tempNumAtomAssignments.get(iVar);
        for (int jVar = iVar + 1; jVar < stateNumFluents.size(); jVar++) {
          NumericAtomAssignment jVarVal = tempNumAtomAssignments.get(jVar);
          Pair<NumericAtomAssignment, NumericAtomAssignment> a2 = new Pair<>(iVarVal, jVarVal);

          novel = novel || n2Seen.add(a2);
        }
      }

      // check for new propositional-numerical variable pair assignments and update
      for (int iVar = 0; iVar < stateNumFluents.size(); iVar++) {
        NumericAtomAssignment iVarVal = tempNumAtomAssignments.get(iVar);
        for (Integer jVal : stateBoolFluents) {
          Pair<Integer, NumericAtomAssignment> a2 = new Pair<>(jVal, iVarVal);

          novel = novel || bnSeen.add(a2);
        }
      }

      return novel;
    }
  }
}
