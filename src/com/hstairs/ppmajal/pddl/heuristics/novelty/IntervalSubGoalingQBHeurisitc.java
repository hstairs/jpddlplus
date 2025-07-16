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
    Map<Pair<Integer, Integer>, Float> b2NoveltyMap;
    Map<Pair<NumericIntervalAssignment, NumericIntervalAssignment>, Float> n2NoveltyMap;
    Map<Pair<Integer, NumericIntervalAssignment>, Float> bnNoveltyMap;

    // temporary interval assignment from a state
    private final List<NumericIntervalAssignment> tempNumIntAssignments;

    // variables to help compute heuristic
    float C1;
    float C2;
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
            s0Distances.add(Float.POSITIVE_INFINITY);
            intervals.add(new Interval(s0Distances.get(i)));
        }
        b1NoveltyMap = new HashMap<>();
        n1NoveltyMap = new HashMap<>();
        b2NoveltyMap = new HashMap<>();
        n2NoveltyMap = new HashMap<>();
        bnNoveltyMap = new HashMap<>();

        tempNumIntAssignments = new ArrayList<>(Collections.nCopies(subgoalConditions.length, null));

        C1 = subgoalConditions.length+nBoolFluents;
        C2 = (C1 * (C1-1))/2;
    }


    private void hqb1(State state) {
        qbl1 = C1;
        qbu1 = C1;

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
            if (h1 == null || h < h1) {
                qbl1--;
                n1NoveltyMap.put(a1, h);
            } else if (h > h1) {
                qbu1++;
            }
        }
    }

    private void hqb2(State state) {
        qbl2 = C2;
        qbu2 = C2;

        for (int i = 0; i < stateBoolFluents.size(); i++) {
            Integer iVal = stateBoolFluents.get(i);
            for (int j = i + 1; j < stateBoolFluents.size(); j++) {
                Integer jVal = stateBoolFluents.get(j);
                Pair<Integer, Integer> a2 = new Pair<>(iVal, jVal);
                if (!b2NoveltyMap.containsKey(a2) || h < b2NoveltyMap.get(a2)) {
                    qbl2--;
                    b2NoveltyMap.put(a2, h);
                } else if (h > b2NoveltyMap.get(a2)) {
                    qbu2++;
                }
            }
        }

        for (int i= 0; i< subgoalConditions.length; i++) {
            float idistance = evalGoalDistance(subgoalConditions[i], state);
            int iInterval = intervals.get(i).getInterval(idistance);
            NumericIntervalAssignment i1 = new NumericIntervalAssignment(i, iInterval);
            for (int j = i + 1; j < subgoalConditions.length;  j++) {
                float jdistance = evalGoalDistance(subgoalConditions[i], state);
                int jInterval = intervals.get(i).getInterval(jdistance);
                NumericIntervalAssignment j1 = new NumericIntervalAssignment(j, jInterval);
                Pair<NumericIntervalAssignment, NumericIntervalAssignment> a2 = new Pair<>(
                        i1, j1);
                Float h2=n2NoveltyMap.get(a2);
                if (h2 == null || h < h2) {
                    qbl2--;
                    n2NoveltyMap.put(a2, h);
                } else if (h > h2) {
                    qbu2++;
                }
            }
        }

        for (int i= 0; i< subgoalConditions.length; i++) {
            float idistance = evalGoalDistance(subgoalConditions[i], state);
            int iInterval = intervals.get(i).getInterval(idistance);
            NumericIntervalAssignment i1 = new NumericIntervalAssignment(i, iInterval);
            for (Integer jVal : stateBoolFluents) {
                Pair<Integer, NumericIntervalAssignment> a2 = new Pair<>(jVal, i1);
                Float h2 = bnNoveltyMap.get(a2);
                if (h2 == null || h < h2) {
                    qbl2--;
                    bnNoveltyMap.put(a2, h);
                } else if (h > h2) {
                    qbu2++;
                }
            }
        }
    }

    @Override
    public float computeEstimate(State stateInput) {
        final PDDLState s = (PDDLState) stateInput;
        stateBoolFluents = s.getBoolIds();
        h = computeHeuristic(heuristic, stateInput);
        hqb1(stateInput);

        if (k == 1) {
            return qbl1 < C1 ? qbl1 : qbu1;
        }

        hqb2(stateInput);
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
