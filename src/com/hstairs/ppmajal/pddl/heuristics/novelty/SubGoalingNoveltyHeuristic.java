package com.hstairs.ppmajal.pddl.heuristics.novelty;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.NoveltyIndexer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class SubGoalingNoveltyHeuristic extends NoveltyHeuristic{

    final SearchHeuristic heuristic;
    Condition[] subgoalConditions;
    Set<Terminal> subgoalsList = new HashSet<>();;
    Set<Terminal> allSubgoalsList;
    Float[] s1NoveltyArray;
    Float[][] s2NoveltyArray;
    float h;
    float C1, C2;
    float qbl1, qbu1;
    float qbl2, qbu2;

    public SubGoalingNoveltyHeuristic(PDDLProblem problem, int k, SearchHeuristic heuristic) {
        super(problem, k, NoveltyValue.WIDTH, NoveltyType.SUBGOAL);
        this.heuristic = heuristic;
        allSubgoalsList = problem.createSubgoals();
        subgoalConditions = allSubgoalsList.toArray(new Condition[subgoalsList.size()]);
        for(Condition c : subgoalConditions){
            subgoalsList.addAll(collectComparisonConditions(c));
        }
        subgoalConditions = subgoalsList.toArray(new Condition[subgoalsList.size()]);
        s1NoveltyArray=new Float[subgoalsList.size()];
        s2NoveltyArray=new Float[subgoalsList.size()][subgoalsList.size()];
        for (int i=0; i<subgoalsList.size(); i++) {
            s1NoveltyArray[i]= Float.POSITIVE_INFINITY;
            for (int j=i+1; j<subgoalsList.size(); j++) {
                s2NoveltyArray[i][j] = Float.POSITIVE_INFINITY;
            }
        }

        C1 = subgoalConditions.length;
        C2 = (C1* (C1-1))/2;

        /*subgoalHeuristic = new Float[subgoalConditions.length];
        for (int i = 0; i < subgoalConditions.length; i++) {
            subgoalHeuristic[i] = Float.POSITIVE_INFINITY;
        }*/
    }

    /*private void calcNovelty(int k, State s) {
        int n = subgoalConditions.length;

        iterateCombinations(k, n, combination -> {
            boolean allSatisfied = true;
            for (int index : combination) {
                if (!subgoalConditions[index].isSatisfied(s)) {
                    allSatisfied = false;
                    break;
                }
            }

            if (allSatisfied) {
                if (h < indexers[k-1].get(combination)) {
                    indexers[k-1].set(combination, h);
                    lowerBounds[k-1]--;
                } else if (h > indexers[k-1].get(combination)) {
                    upperBounds[k-1]++;
                }
            }

        });
    }*/

    private void hqb1(State state){
        qbl1=C1;
        qbu1=C2;

        for(int i=0;i<subgoalConditions.length;i++){
            if(subgoalConditions[i].isSatisfied(state)){
                float h1 = s1NoveltyArray[i];
                if(h<h1){
                    qbl1--;
                    s1NoveltyArray[i]=h;
                } else if(h>h1){
                    qbu1++;
                }
            }
        }
    }

    private void hqb2(State state){
        qbl2=C2;
        qbu2=C1;

        for(int i=0;i<subgoalConditions.length;i++){
            if(subgoalConditions[i].isSatisfied(state)){
                for (int j=i+1; j<subgoalConditions.length; j++) {
                    if(subgoalConditions[j].isSatisfied(state)){
                        float h2 = s2NoveltyArray[i][j];
                        if(h<h2){
                            qbl2--;
                            s2NoveltyArray[i][j]=h;
                        }
                        else if(h>h2){
                            qbu2++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public float computeEstimate(State stateInput) {
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
/*
        for (int i=0; i<subgoalConditions.length; i++) {
            if (subgoalConditions[i].isSatisfied(stateInput)){
                if(h<subgoalHeuristic[i]){
                    qbl1--;
                    subgoalHeuristic[i]=h;
                }
            }
            else qbu1++;
        }
        return qbl1 < nSubgoals ? qbl1 : qbu1;
        return upperBounds[k-1];*/
    }

    private int totalNumberOfTuples(int k, NoveltyIndexer[] indexers) {        int total=0;
        if(k==1)
            return total;
        for(int i = 0; i<k; i++){
            total = total + (int)indexers[i].size();
        }
        return total;
    }

    private void iterateCombinations(int k, int n, Consumer<int[]> callback) {
        int[] indices = new int[k];
        generateCombinations(indices, 0, 0, k, n, callback);
    }

    private void generateCombinations(int[] indices, int depth, int start, int k, int n, Consumer<int[]> callback) {
        if (depth == k) {
            callback.accept(indices.clone());
            return;
        }
        for (int i = start; i < n; i++) {
            indices[depth] = i;
            generateCombinations(indices, depth + 1, i + 1, k, n, callback);
        }
    }

    private Set<Comparison> collectComparisonConditions(Condition c)
    {
        Set<Comparison> result = new HashSet<>();
        getComparisonFromConditions(c, result);
        return result;
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


    @Override
    public Object[] getTransitions(boolean helpful) {
        return heuristic.getTransitions(helpful);
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() {
        return problem.getTransitions();
    }
}
