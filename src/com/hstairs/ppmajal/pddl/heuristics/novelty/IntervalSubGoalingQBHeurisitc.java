package com.hstairs.ppmajal.pddl.heuristics.novelty;
import java.util.*;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.pddl.heuristics.advanced.ManhattanHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.Interval;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.NumericIntervalAssignment;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;

public class IntervalSubGoalingQBHeurisitc extends NoveltyHeuristic{

    final SearchHeuristic heuristic;

    private final List<Interval> intervals;
    Condition[] subgoalConditions;
    Set<Terminal> subgoalsList = new HashSet<>();

    Map<Integer, Float> b1NoveltyMap;
    Map<NumericIntervalAssignment, Float> n1NoveltyMap;
    //Map<Pair<Integer, Integer>, Float> b2NoveltyMap;
    Map<Pair<NumericIntervalAssignment, NumericIntervalAssignment>, Float> n2NoveltyMap;
    //Map<Pair<Integer, NumericIntervalAssignment>, Float> bnNoveltyMap;

    // temporary interval assignment from a state
    //private final List<NumericIntervalAssignment> tempNumIntAssignments;

    // variables to help compute heuristic
    float qbl1, qbu1;
    float qbl2, qbu2;
    List<Integer> stateBoolFluents;
    List<Double> stateNumFluents;
    float h;

    public IntervalSubGoalingQBHeurisitc(PDDLProblem problem, int k, SearchHeuristic heuristic) {
        super(problem, k, NoveltyValue.QUANTIFIED_BOTH, NoveltyType.INTERVAL);
        this.heuristic = heuristic;
        if(heuristic instanceof H1){
            subgoalConditions=((H1) heuristic).cp.preconditionFunction();
            for(Condition c : subgoalConditions){
                subgoalsList.addAll(collectComparisonConditions(c));
            }
        }
        subgoalConditions = subgoalsList.toArray(new Condition[subgoalsList.size()]);
        PDDLState s0 = (PDDLState) problem.getInit();
        List<Float> s0Distances = new ArrayList<>();
        intervals = new ArrayList<>();
        for(int i=0;i<subgoalConditions.length;i++) {
            s0Distances.add(evalGoalDistance(subgoalConditions[i], s0));
            intervals.add(new Interval(s0Distances.get(i)));
        }
        b1NoveltyMap = new HashMap<>();
        n1NoveltyMap = new HashMap<>();
        //b2NoveltyMap = new HashMap<>();
        //n2NoveltyMap = new HashMap<>();
        //bnNoveltyMap = new HashMap<>();

        //tempNumIntAssignments = new ArrayList<>(Collections.nCopies(nNumFluents, null));
    }


    private void hqb1(State state) {
        qbl1 = subgoalConditions.length+nBoolFluents;
        qbu1 = subgoalConditions.length+nBoolFluents;

        for (Integer a1 : stateBoolFluents) {
            Float h1 = b1NoveltyMap.get(a1);
            if (h1 == null || h < h1) {
                qbl1--;
                b1NoveltyMap.put(a1, h);
            } else if (h > h1) {
                qbu1++;
            }
        }
        for(int i=0;i<subgoalConditions.length;i++) {
            float distance = evalGoalDistance(subgoalConditions[i], state);
            int iInterval = intervals.get(i).getInterval(distance);
            NumericIntervalAssignment a1 = new NumericIntervalAssignment(i, iInterval);
            Float h1 = n1NoveltyMap.get(a1);
            if (h1 == null || h < h1 || h==0.0f) {
                qbl1--;
                n1NoveltyMap.put(a1, h);
            } else if (h > h1) {
                qbu1++;
            }
        }
        /*for (int var = 0; var < nNumFluents; var++) {
            int iInterval = intervals.get(var).getInterval(stateNumFluents.get(var));
            NumericIntervalAssignment a1 = new NumericIntervalAssignment(var, iInterval);
            //tempNumIntAssignments.set(var, a1);
            if (!n1NoveltyMap.containsKey(a1) || h < n1NoveltyMap.get(a1)) {
                qbl1--;
                n1NoveltyMap.put(a1, h);
            } else if (h > n1NoveltyMap.get(a1)) {
                qbu1++;
            }
        }*/
    }
/*
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

        for (int iVar = 0; iVar < stateNumFluents.\size(); iVar++) {
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
    }*/

    @Override
    public float computeEstimate(State stateInput) {
        final PDDLState s = (PDDLState) stateInput;
        stateBoolFluents = s.getBoolIds();
        h = computeHeuristic(heuristic, stateInput);
        hqb1(stateInput);

        return qbl1 < subgoalConditions.length+nBoolFluents ? qbl1 : qbu1;

    }

    @Override
    public Object[] getTransitions(boolean helpful) {
        return heuristic.getTransitions(helpful);
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() {
        return problem.getTransitions();
    }

    private float evalGoalDistance(Condition goals, State s) {
        if (s.satisfy(goals)) {
            return 0f;
        }
        if (goals instanceof AndCond ac) {
            float res = 0f;
            for (var son : ac.sons) {
                final float v = evalGoalDistance((Condition) son, s);
                if (v == Float.MAX_VALUE)
                    return Float.MAX_VALUE;
                res += v;
            }
            return res;
        } else if (goals instanceof OrCond oc) {
            float min = Float.POSITIVE_INFINITY;
            for (var son : oc.sons) {
                final float v = evalGoalDistance((Condition) son, s);
                if (v < min) {
                    min = v;
                }
                if (v == 0f) {
                    return v;
                }
            }
            return min;
        } else if (goals instanceof Comparison g) {
            //if (g.getComparator().equals(">"))
                //return (float) Math.abs(g.getLeft().eval(s)) + 0.01f;
            return (float) Math.abs(g.getLeft().eval(s));
        } else if (goals instanceof BoolPredicate g) {
            return 1;
        }
        return 0f;
    }

    private void getComparisonFromConditions(Condition c, Set<Comparison> results){
        if(c instanceof Comparison){
            results.add((Comparison) c);
        } else if (c instanceof ComplexCondition){
            for (var child : ((ComplexCondition) c).sons){
                if (child instanceof Condition){
                    getComparisonFromConditions((Condition) child, results);
                }
            }
        }
    }

    private Set<Comparison> collectComparisonConditions(Condition c)
    {
        Set<Comparison> result = new HashSet<>();
        getComparisonFromConditions(c, result);
        return result;
    }
}
