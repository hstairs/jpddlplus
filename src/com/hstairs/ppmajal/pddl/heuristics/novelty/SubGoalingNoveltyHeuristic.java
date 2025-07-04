package com.hstairs.ppmajal.pddl.heuristics.novelty;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.NoveltyIndexer;

import java.util.Collection;
import java.util.Set;

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

    private void calcNovelty(int k, float[] upperBounds, float[] lowerBounds, NoveltyIndexer[] indexers){

    }


    @Override
    public float computeEstimate(State stateInput) {
        h = computeHeuristic(heuristic, stateInput);
        for(int i = 0; i<k; i++){
            upperBounds[i] = (int)indexers[i].size();
            lowerBounds[i] = (int)indexers[i].size();
        }

        for (int end = 0; end < k; end++) {
            calcNovelty(k, upperBounds, lowerBounds, indexers);
            if(lowerBounds[end]< indexers[end].size()){
                return lowerBounds[end] + totalNumberOfTuples(end, indexers);
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
        return 0;
    }

    private int totalNumberOfTuples(int k, NoveltyIndexer[] indexers) {
        int total=0;
        if(k==1)
            return total;
        for(int i = 0; i<k; i++){
            total = total + (int)indexers[i].size();
        }
        return total;
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
