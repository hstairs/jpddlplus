package com.hstairs.ppmajal.pddl.heuristics.novelty;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.NoveltyIndexer;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

public class SubGoalingNoveltyHeuristic extends NoveltyHeuristic{

    final SearchHeuristic heuristic;
    Terminal[] subgoalConditions;
    //Float[] subgoalHeuristic;
    Set<Terminal> subgoalsList;
    NoveltyIndexer[] indexers;
    float[] upperBounds;
    float[] lowerBounds;
    float h;

    public SubGoalingNoveltyHeuristic(PDDLProblem problem, int k, SearchHeuristic heuristic) {
        super(problem, k, NoveltyValue.WIDTH, NoveltyType.SUBGOAL);
        this.heuristic = heuristic;
        subgoalsList = problem.createSubgoals();
        subgoalConditions = subgoalsList.toArray(new Terminal[subgoalsList.size()]);
        this.indexers = new NoveltyIndexer[k];
        for(int i = 0; i<k; i++){
            NoveltyIndexer indexer = new NoveltyIndexer(subgoalConditions.length, i+1);
            indexers[i] = indexer;
        }
        this.upperBounds = new float[k];
        this.lowerBounds = new float[k];
        /*subgoalHeuristic = new Float[subgoalConditions.length];
        for (int i = 0; i < subgoalConditions.length; i++) {
            subgoalHeuristic[i] = Float.POSITIVE_INFINITY;
        }*/
    }

    private void calcNovelty(int k, State s) {
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
                }
            } else {
                upperBounds[k-1]++;
            }

        });
    }



    @Override
    public float computeEstimate(State stateInput) {
        h = computeHeuristic(heuristic, stateInput);
        for(int i = 0; i<k; i++){
            upperBounds[i] = (int)indexers[i].size();
            lowerBounds[i] = (int)indexers[i].size();
        }

        for (int end = 0; end < k; end++) {
            calcNovelty(end+1, stateInput);
        }

        for(int i = 0; i<k; i++){
            if(lowerBounds[i]< indexers[i].size()){
                return lowerBounds[i] + totalNumberOfTuples(i+1, indexers);
            }
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
        return qbl1 < nSubgoals ? qbl1 : qbu1; */
        return upperBounds[k-1];
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


    @Override
    public Object[] getTransitions(boolean helpful) {
        return heuristic.getTransitions(helpful);
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() {
        return problem.getTransitions();
    }
}
