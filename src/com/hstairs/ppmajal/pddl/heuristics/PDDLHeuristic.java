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
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Fix;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Res;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author enrico
 */
public class PDDLHeuristic {

        @FunctionalInterface
        interface HeuristicFactory {
                SearchHeuristic create(PDDLProblem problem, String redundantConstraints,
                                boolean helpfulActionsPruning, boolean helpfulTransitions,
                                boolean toOneTransformation);
        }

        private static final Map<String, HeuristicFactory> HEURISTICS = Map.ofEntries(
                        Map.entry("gc", (p, rc, ha, ht, to1) -> new GoalCounting(p)),

                        Map.entry("hadd",
                                        (p, rc, ha, ht, to1) -> new H1(p, true, false, false, rc, ha, false, ht, false,
                                                        null, to1)),

                        Map.entry("hradd",
                                        (p, rc, ha, ht, to1) -> new H1(p, true, false, false, "brute", false, false,
                                                        false, false, false)),

                        Map.entry("hrmax",
                                        (p, rc, ha, ht, to1) -> new H1(p, false, false, false, "brute", false, false,
                                                        false, false, false)),

                        Map.entry("h1res", (p, rc, ha, ht, to1) -> new H1Res(p, rc, false, false)),
                        Map.entry("h1res2", (p, rc, ha, ht, to1) -> new H1Res(p, rc, true, false)),
                        Map.entry("h1res3", (p, rc, ha, ht, to1) -> new H1Res(p, rc, true, true)),
                        Map.entry("h1res4", (p, rc, ha, ht, to1) -> new H1Res(p, rc, false, true)),

                        Map.entry("hmax",
                                        (p, rc, ha, ht, to1) -> new H1(p, false, false, false, rc, false, false, false,
                                                        false, null,
                                                        false)),

                        Map.entry("hmrp",
                                        (p, rc, ha, ht, to1) -> new H1(p, true, true, false, rc, ha, false, ht, true,
                                                        null, to1)),

                        Map.entry("hmrp_fix",
                                        (p, rc, ha, ht, to1) -> new H1Fix(p, false, false, rc, ha, false, false, true,
                                                        false)),

                        Map.entry("hmrp_easy_fix",
                                        (p, rc, ha, ht, to1) -> new H1Fix(p, true, true, rc, ha, false, false, false,
                                                        false)),

                        Map.entry("hmrp_fix_tran",
                                        (p, rc, ha, ht, to1) -> new H1Fix(p, false, false, rc, ha, false, false, false,
                                                        true)),

                        Map.entry("blind", (p, rc, ha, ht, to1) -> new BlindHeuristic(p)),
                        Map.entry("01blind", (p, rc, ha, ht, to1) -> new GoalSensitiveHeuristic(p)),

                        Map.entry("aibr", (p, rc, ha, ht, to1) -> {
                                System.out.println("AIBR selected");
                                return new Aibr(p);
                        }),

                        Map.entry("hlm-count", (p, rc, ha, ht, to1) -> {
                                System.out.println("HLM selected");
                                return new LM(p);
                        }),

                        Map.entry("hlm-lp", (p, rc, ha, ht, to1) -> {
                                System.out.println("HLM selected");
                                System.out.println(rc);
                                return new LM(p, "lp", rc, "cplex");
                        }),

                        Map.entry("hlm-lp-gurobi", (p, rc, ha, ht, to1) -> {
                                System.out.println("HLM selected");
                                System.out.println(rc);
                                return new LM(p, "lp", rc, "gurobi");
                        }),

                        Map.entry("hgen", (p, rc, ha, ht, to1) -> {
                                System.out.println("HGEN selected");
                                System.out.println(rc);
                                return new HGen(p);
                        }));

        public static SearchHeuristic getHeuristic(String heuristic,
                        PDDLProblem heuristicProblem,
                        String redundantConstraints,
                        boolean helpfulActionsPruning,
                        boolean helpfulTransitions,
                        boolean toOneTransformation) {
                // Special case "smart"
                if ("smart".equals(redundantConstraints)) {
                        final H1 h1 = new H1(heuristicProblem, true, true, false,
                                        "smart", false, true, false, false, false);
                        h1.computeEstimate(heuristicProblem.getInit());
                }

                HeuristicFactory factory = HEURISTICS.get(heuristic);
                if (factory != null) {
                        return factory.create(heuristicProblem, redundantConstraints,
                                        helpfulActionsPruning, helpfulTransitions, toOneTransformation);
                }

                if (heuristic != null) {
                        System.out.println("Folding back to 1-0 heuristic. Input heuristic is not supported");
                }
                return new GoalSensitiveHeuristic(heuristicProblem);
        }

        public static Collection<String> getAvailableHeuristics() {
                return HEURISTICS.keySet();
        }
}
