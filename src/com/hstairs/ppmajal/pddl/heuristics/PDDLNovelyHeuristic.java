package com.hstairs.ppmajal.pddl.heuristics;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.pddl.heuristics.advanced.*;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Fix;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Res;
import com.hstairs.ppmajal.pddl.heuristics.novelty.*;
import com.hstairs.ppmajal.search.SearchHeuristic;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.Collection;
import java.util.Map;

public class PDDLNovelyHeuristic {

    public static SearchHeuristic getNoveltyHeuristic(String novelty,
                                               PDDLProblem heuristicProblem, Integer k, SearchHeuristic searchHeuristic) {
        SearchHeuristic h = searchHeuristic;
        switch (novelty) {
            case "aqb": {
                switch (k){
                    case 1: return new AtomQuantifiedBothHeuristic(heuristicProblem, 1, h);
                    case 2: return new AtomQuantifiedBothHeuristic(heuristicProblem, 2, h);
                }
            }
            case "aw": {
                switch (k){
                    case 1: return new AtomWidthHeuristic(heuristicProblem, 1, new SearchHeuristic[]{h});
                    case 2: return new AtomWidthHeuristic(heuristicProblem, 2, new SearchHeuristic[]{h});
                }

            }
            case "iqb": {
                switch (k){
                    case 1: return new IntervalQuantifiedBothHeuristic(heuristicProblem, 1, h);
                    case 2: return new IntervalQuantifiedBothHeuristic(heuristicProblem, 2, h);
                }
            }
            case "iw": {
                switch (k){
                    case 1: return new IntervalWidthHeuristic(heuristicProblem, 1, new SearchHeuristic[]{h});
                    case 2: return new IntervalWidthHeuristic(heuristicProblem, 2, new SearchHeuristic[]{h});
                }
            }
            case "sqb": {
                switch (k){
                    case 1: return new SubGoalingNoveltyHeuristic(heuristicProblem, 1, h);
                    case 2: return new SubGoalingNoveltyHeuristic(heuristicProblem, 2, h);
                }
            }
            case "dsqb":{
                switch (k){
                    case 1: return new IntervalSubGoalingQBHeurisitc(heuristicProblem, 1, h);
                    case 2: return new IntervalSubGoalingQBHeurisitc(heuristicProblem, 2, h);
                }
            }

            case "ndsqb1": {
                switch (k) {
                    case 1: return new ISubGNegativeDistances(heuristicProblem, 1, h, 1);
                    case 2: return new ISubGNegativeDistances(heuristicProblem, 2, h, 1);
                }
            }

            case "ndsqb2": {
                switch (k) {
                    case 1: return new ISubGNegativeDistances(heuristicProblem, 1, h, 2);
                    case 2: return new ISubGNegativeDistances(heuristicProblem, 2, h, 2);
                }
            }
            default:
                System.out.println("Input heuristic is not supported. Interval quantified both novelty is used.");
                return new IntervalQuantifiedBothHeuristic(heuristicProblem, 1, h);

        }
    }
}
