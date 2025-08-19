package com.hstairs.ppmajal.pddl.heuristics.novelty;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.Interval;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.NumericIntervalAssignment;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.*;

public class ISubGNegativeDistances extends NoveltyHeuristic{

    final SearchHeuristic heuristic;

    private final List<Interval> intervals;
    Condition[] subgoalConditions;
    Set<Terminal> subgoalsList = new HashSet<>();
    Set<Terminal> allSubgoalsList;

    Map<Integer,Float>[] n1NoveltyArray;
    HashMap<Pair<Integer, Integer>, Float>[][] n2NoveltyArray;
    Map<Integer, Float> b1NoveltyMap;
    Map<Pair<Integer, Integer>, Float> b2NoveltyMap;
    Map<Pair<Integer, NumericIntervalAssignment>, Float> bnNoveltyMap;

    private final List<NumericIntervalAssignment> tempNumIntAssignments;

    // variables to help compute heuristic
    float C1;
    float C2;
    float qbl1, qbu1;
    float qbl2, qbu2;
    List<Integer> stateBoolFluents;
    float h;

    public ISubGNegativeDistances(PDDLProblem problem, int k, SearchHeuristic heuristic) {
        super(problem, k, NoveltyValue.QUANTIFIED_BOTH, NoveltyType.INTERVAL);
        this.heuristic = heuristic;
        allSubgoalsList = problem.createSubgoals();
        subgoalConditions = allSubgoalsList.toArray(new Condition[subgoalsList.size()]);
        for(Condition c : subgoalConditions){
            subgoalsList.addAll(collectComparisonConditions(c));
        }
        subgoalConditions = subgoalsList.toArray(new Condition[subgoalsList.size()]);
        PDDLState s0 = (PDDLState) problem.getInit();
        List<Float> s0Distances = new ArrayList<>();
        intervals = new ArrayList<>();
        int nsubgoals = subgoalConditions.length;
        n1NoveltyArray = new Int2FloatOpenHashMap[nsubgoals];
        n2NoveltyArray = new HashMap[nsubgoals][nsubgoals];
        for(int i=0;i<nsubgoals;i++) {
            s0Distances.add(evalGoalDistance(subgoalConditions[i], s0));
            intervals.add(new Interval(s0Distances.get(i)));
            n1NoveltyArray[i] = new Int2FloatOpenHashMap();
            for(int j=i+1;j<nsubgoals;j++) {
                n2NoveltyArray[i][j] = new HashMap<>();
            }
        }
        b1NoveltyMap = new Int2FloatOpenHashMap();
        b2NoveltyMap = new HashMap<>();
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
            Float h1 = n1NoveltyArray[i].get(iInterval);
            if (h1 == null || h < h1) {
                qbl1--;
                n1NoveltyArray[i].put(iInterval, h);
            } else if (h > h1 && distance<=0f) {
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

        for(int i=0;i<subgoalConditions.length;i++) {
            float idistance = evalGoalDistance(subgoalConditions[i], state);
            int iInterval = intervals.get(i).getInterval(idistance);
            for (int j = i + 1; j < subgoalConditions.length;  j++) {
                float jdistance = evalGoalDistance(subgoalConditions[j], state);
                int jInterval = intervals.get(j).getInterval(jdistance);
                Pair<Integer, Integer> index = new Pair<>(iInterval, jInterval);
                Float h1 = n2NoveltyArray[i][j].get(index);
                if (h1 == null || h < h1) {
                    qbl2--;
                    n2NoveltyArray[i][j].put(index, h);
                } else if (h > h1 && (jdistance<=0f || idistance<=0f)) {
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
        if (h==Float.MAX_VALUE)
            return Float.MAX_VALUE;
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
        /*if(s.satisfy(goals)) {
            return 0f;
        }*/
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
            /*if (g.getComparator().equals(">"))
                return (float) (g.getLeft().eval(s)) + 0.01f;*/
            if(g.getComparator().equals("="))
                return (float) Math.abs(g.getLeft().eval(s));
            return (float) -(g.getLeft().eval(s));
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
