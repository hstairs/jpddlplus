/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.pddl.heuristics.advanced.Aibr;
import com.hstairs.ppmajal.pddl.heuristics.advanced.GoalCounting;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.pddl.heuristics.advanced.HGen;
import com.hstairs.ppmajal.pddl.heuristics.advanced.LM;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.search.SearchHeuristic;
import hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Fix;
import hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Res;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author enrico
 */
public class PDDLHeuristic {

    public static SearchHeuristic getHeuristic(String heuristic, PDDLProblem heuristicProblem, String redundantConstraints,
            boolean helpfulActionsPruning, boolean helpfulTransitions) {
        Map<AndCond, Collection<IntArraySet>> redConstraint = null;
        if ("smart".equals(redundantConstraints)) {
            final H1 h1 = new H1(heuristicProblem, true, true, false, "smart", false, true, false, false);
            h1.computeEstimate(heuristicProblem.getInit());
        }
        switch (heuristic) {
            case "gc": {
                return new GoalCounting(heuristicProblem);
            }
            case "hadd": {
                return new H1(heuristicProblem, true, false, false, redundantConstraints, helpfulActionsPruning, false, helpfulTransitions, false, redConstraint);

            }
            case "hradd": {
                return new H1(heuristicProblem, true, false, false, "brute", false, false, false, false);

            }

            case "hrmax": {
                return new H1(heuristicProblem, false, false, false, "brute", false, false, false, false);

            }
            case "h1res": {
                return new H1Res(heuristicProblem, redundantConstraints, false, false);

            }
            case "h1res2": {
                return new H1Res(heuristicProblem, redundantConstraints, true, false);

            }
            case "h1res3": {
                return new H1Res(heuristicProblem, redundantConstraints, true, true);

            }
            case "h1res4": {
                return new H1Res(heuristicProblem, redundantConstraints, false, true);

            }
            case "hmax": {
                return new H1(heuristicProblem, false, false, false, redundantConstraints, false, false, false, false, redConstraint);

            }
            case "hmrp": {
                return new H1(heuristicProblem, true, true, false, redundantConstraints, helpfulActionsPruning, false, helpfulTransitions, true, redConstraint);

            }
            case "hmrp_fix": {
                return new H1Fix(heuristicProblem, false, false, redundantConstraints, helpfulActionsPruning, false, false, true, false);

            }
            case "hmrp_easy_fix": {
                return new H1Fix(heuristicProblem, true, true, redundantConstraints, helpfulActionsPruning, false, false, false, false);

            }
            case "hmrp_fix_tran": {
                return new H1Fix(heuristicProblem, false, false, redundantConstraints, helpfulActionsPruning, false, false, false, true);

            }

            case "blind": {
                return new BlindHeuristic(heuristicProblem);

            }
            case "01blind": {
                return new GoalSensitiveHeuristic(heuristicProblem);

            }
            case "aibr": {
                System.out.println("AIBR selected");
                return new Aibr(heuristicProblem);

            }
            case "hlm-count": {
                System.out.println("HLM selected");
                return new LM(heuristicProblem);

            }
            case "hlm-lp": {
                System.out.println("HLM selected");
                System.out.println(redundantConstraints);
                return new LM(heuristicProblem, "lp", redundantConstraints, "cplex");

            }
            case "hlm-lp-gurobi": {
                System.out.println("HLM selected");
                System.out.println(redundantConstraints);
                return new LM(heuristicProblem, "lp", redundantConstraints, "gurobi");

            }
            case "hgen":{
                System.out.println("HGEN selected");
                System.out.println(redundantConstraints);
                return new HGen(heuristicProblem);

            }
            default:
                if (heuristic != null) {
                    System.out.println("Folding back to 1-0 heuristic. Input heuristic is not supported");
                }
                return new GoalSensitiveHeuristic(heuristicProblem);

        }
    }

}
