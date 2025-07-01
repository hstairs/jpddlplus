package com.hstairs.ppmajal.pddl.heuristics.novelty;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubGoalingNoveltyHeuristic extends NoveltyHeuristic{

    final SearchHeuristic heuristic;
    Terminal[] subgoalConditions;
    Float[] subgoalHeuristic;
    List<Terminal> subgoalsList;
    float qbl1, qbu1;
    float h;

    public SubGoalingNoveltyHeuristic(PDDLProblem problem, int k, SearchHeuristic heuristic) {
        super(problem, k, NoveltyValue.WIDTH, NoveltyType.SUBGOAL);
        this.heuristic = heuristic;
        subgoalsList = problem.createSubgoals();
        subgoalConditions = subgoalsList.toArray(new Terminal[subgoalsList.size()]);
        subgoalHeuristic = new Float[subgoalConditions.length];
        for (int i = 0; i < subgoalConditions.length; i++) {
            subgoalHeuristic[i] = Float.POSITIVE_INFINITY;
        }
    }


    @Override
    public float computeEstimate(State stateInput) {
        h = computeHeuristic(heuristic, stateInput);
        qbl1 = qbu1 = nSubgoals;

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
