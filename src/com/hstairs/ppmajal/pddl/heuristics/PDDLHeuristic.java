/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.pddl.heuristics.advanced.*;
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
        SearchHeuristic create(
            PDDLProblem problem,
            String redundantConstraints,
            boolean helpfulActionsPruning,
            boolean helpfulTransitions,
            boolean toOneTransformation,
            int linearEffectsAbstraction,
            boolean aibrDebugging,
            Map<AndCond, Collection<IntArraySet>> redConstraint
        );
    }

    private static final String[][] HEURISTIC_INFOS = {
        // CHECK if description is correct
        { "gc", "Goal Counting", "Counts the number of unsatisfied goals as heuristic value." },
        { "hadd", "HAdd", "Additive version of subgoaling heuristic." },
        { "haddb", "HAdd-Bucket", "Additive heuristic with bucket expansion." },
        { "ngc", "NGC", "Structure-sensitive numeric goal counting." },
        { "agnosticngc", "AgnosticNGC", "Numeric goal counting ignoring structure." },
        { "mgc", "MGC", "Manhattan heuristic for numeric goals." },
        { "hradd", "HRAdd", "Additive version of subgoaling heuristic plus redundant constraints." },
        { "hrmax", "HRMax", "Hmax for Numeric Planning with redundant constraints." },
        { "hrmaxb", "HRMax-Bucket", "Hmax with bucket expansion and redundant constraints." },
        { "h1res", "H1Res", "Resolution-based heuristic without optimizations." },
        { "h1res2", "H1Res2", "Resolution-based heuristic with relaxed operator relevance pruning." },
        { "h1res3", "H1Res3", "Resolution-based heuristic with both relevance and transition pruning." },
        { "h1res4", "H1Res4", "Resolution-based heuristic with transition pruning only." },
        { "hmax", "HMax", "Hmax for Numeric Planning." },
        { "hmrp", "HMRP", "Heuristic based on MRP extraction." },
        { "hmrpb", "HMRP-Bucket", "HMRP with bucket expansion." },
        { "hmrp_fix", "HMRPFix", "Fixed variant of HMRP with adjusted mutex handling." },
        { "hmrp_easy_fix", "HMRPEasyFix", "Simplified fixed variant of HMRP for efficiency." },
        { "hmrp_fix_tran", "HMRPFixTran", "Fixed variant of HMRP including transition-based handling." },
        { "blind", "Blind", "Blind heuristic always returning 0 (uninformed)." },
        { "01blind", "01Blind", "Goal-sensitive blind heuristic returning 0 or 1 depending on state." },
        { "aibr", "AIBR", "Additive Interval Based relaxation heuristic." },
        { "hlm-count", "HLMCount", "Landmark-count heuristic estimating distance by number of unsatisfied landmarks." },
        { "hlm-lp", "HLM-LP", "Landmark heuristic using linear programming (LP) with CPLEX." },
        { "hlm-lp-gurobi", "HLM-LP-Gurobi", "Landmark heuristic using LP solved with Gurobi." },
        { "hgen", "HGen", "Generic heuristic generator (experimental baseline heuristic)." },
        { "gnn_ts", "GNN TorchScript", "Neural heuristic loaded from TorchScript (.pt)." },
    };

    private static final Map<String, HeuristicFactory> HEURISTICS = Map.ofEntries(
            Map.entry(HEURISTIC_INFOS[0][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new GoalCounting(p)),
            Map.entry(HEURISTIC_INFOS[1][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1(p, true, false, false, rc, ha, false, ht, false, redC, to1, lea)),
            Map.entry(HEURISTIC_INFOS[2][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1WithBucketEXP(p, true, false, false, rc, ha, false, ht, false, redC, to1, lea)),
            Map.entry(HEURISTIC_INFOS[3][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new StructureSensitiveNumericGoalCounting(p)),
            Map.entry(HEURISTIC_INFOS[4][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new NumericGoalCounting(p)),
            Map.entry(HEURISTIC_INFOS[5][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new ManhattanHeuristic(p)),
            Map.entry(HEURISTIC_INFOS[6][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1(p, true, false, false, "brute", false, false, false, false, to1, lea)),
            Map.entry(HEURISTIC_INFOS[7][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1(p, false, false, false, "brute", false, false, false, false, to1, lea)),
            Map.entry(HEURISTIC_INFOS[8][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1WithBucketEXP(p, false, false, false, "brute", false, false, false, false, to1, lea)),
            Map.entry(HEURISTIC_INFOS[9][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Res(p, rc, false, false)),
            Map.entry(HEURISTIC_INFOS[10][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Res(p, rc, true, false)),
            Map.entry(HEURISTIC_INFOS[11][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Res(p, rc, true, true)),
            Map.entry(HEURISTIC_INFOS[12][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Res(p, rc, false, true)),
            Map.entry(HEURISTIC_INFOS[13][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1(p, false, false, false, rc, false, false, false, false, redC, false, lea)),
            Map.entry(HEURISTIC_INFOS[14][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1(p, true, true, false, rc, ha, false, ht, true, redC, to1, lea)),
            Map.entry(HEURISTIC_INFOS[15][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1WithBucketEXP(p, true, true, false, rc, ha, false, ht, true, redC, to1, lea)),
            Map.entry(HEURISTIC_INFOS[16][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Fix(p, false, false, rc, ha, false, false, true, false)),
            Map.entry(HEURISTIC_INFOS[17][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Fix(p, true, true, rc, ha, false, false, false, false)),
            Map.entry(HEURISTIC_INFOS[18][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new H1Fix(p, false, false, rc, ha, false, false, false, true)),
            Map.entry(HEURISTIC_INFOS[19][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new BlindHeuristic(p)),
            Map.entry(HEURISTIC_INFOS[20][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> new GoalSensitiveHeuristic(p)),
            Map.entry(HEURISTIC_INFOS[21][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> {
                System.out.println("AIBR selected");
                return new Aibr(p, false, dbg);
            }),
            Map.entry(HEURISTIC_INFOS[22][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> {
                System.out.println("HLM selected");
                return new LM(p);
            }),
            Map.entry(HEURISTIC_INFOS[23][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> {
                System.out.println("HLM-LP selected");
                return new LM(p, "lp", rc, "cplex");
            }),
            Map.entry(HEURISTIC_INFOS[24][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> {
                System.out.println("HLM selected");
                System.out.println(rc);
                return new LM(p, "lp", rc, "gurobi");
            }),
            Map.entry(HEURISTIC_INFOS[25][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> {
                System.out.println("HGEN selected");
                System.out.println(rc);
                return new HGen(p);
            }),
            Map.entry(HEURISTIC_INFOS[26][0], (p, rc, ha, ht, to1, lea, dbg, redC) -> {
                System.out.println("GNN TorchScript selected");
                //PDDLProblem toPass = PDDLProblem.gnnTsHeuristicProblem != null ? PDDLProblem.gnnTsHeuristicProblem : p;
                //return new GnnValTSHeuristic(toPass);

                return new GnnValTSHeuristic(p,PDDLProblem.gnnTsHeuristicProblem);


            })
    );

    public static SearchHeuristic getHeuristic(String heuristic,
        PDDLProblem heuristicProblem,
        String redundantConstraints,
        boolean helpfulActionsPruning,
        boolean helpfulTransitions,
        boolean toOneTransformation,
        int linearEffectsAbstraction,
        boolean aibrDebugging) {
        if (redundantConstraints == null) {
            redundantConstraints = "";
        }

        // Always create the redConstraint variable (null initially)
        Map<AndCond, Collection<IntArraySet>> redConstraint = null;

        // Special case "smart"
        if ("smart".equals(redundantConstraints)) {
            final H1 h1 = new H1(heuristicProblem, true, true, false,
                    "smart", false, true, false, false, false, linearEffectsAbstraction);
            h1.computeEstimate(heuristicProblem.getInit());
        }

        HeuristicFactory factory = HEURISTICS.get(heuristic);
        if (factory != null) {
            return factory.create(heuristicProblem,
                                redundantConstraints,
                                helpfulActionsPruning,
                                helpfulTransitions,
                                toOneTransformation,
                                linearEffectsAbstraction,
                                aibrDebugging,
                                redConstraint);  // always passed, even if null
        }

        if (heuristic != null) {
            System.out.println("Folding back to 1-0 heuristic. Input heuristic is not supported");
        }
        return new GoalSensitiveHeuristic(heuristicProblem);
    }

    public static String[][] getAvailableHeuristics() {
        return HEURISTIC_INFOS;
    }

    public static String getHelpString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Available Heuristics:\n");
        for (String[] info: HEURISTIC_INFOS) {
            sb.append(" - ").append(info[0]).append(": ").append(info[2]).append("\n");
        }
        return sb.toString();
    }
}
