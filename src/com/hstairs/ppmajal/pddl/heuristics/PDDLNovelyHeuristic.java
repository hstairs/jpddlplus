package com.hstairs.ppmajal.pddl.heuristics;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.pddl.heuristics.advanced.*;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Fix;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Res;
import com.hstairs.ppmajal.pddl.heuristics.novelty.AtomQuantifiedBothHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.novelty.AtomWidthHeuristic;
import com.hstairs.ppmajal.search.SearchHeuristic;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.Collection;
import java.util.Map;

public class PDDLNovelyHeuristic {

    public static SearchHeuristic getNoveltyHeuristic(String novelty,
                                               PDDLProblem heuristicProblem, Integer k, SearchHeuristic searchHeuristic) {
        switch (novelty) {
            case "aqb": {
                return new AtomQuantifiedBothHeuristic(heuristicProblem, k, searchHeuristic);
            }
            case "aw": {
                return new AtomWidthHeuristic(heuristicProblem, k, searchHeuristic);
            }
            default:
                if (heuristic != null) {
                    System.out.println("Folding back to 1-0 heuristic. Input heuristic is not supported");
                }
                return new GoalSensitiveHeuristic(heuristicProblem);

        }
    }
}
