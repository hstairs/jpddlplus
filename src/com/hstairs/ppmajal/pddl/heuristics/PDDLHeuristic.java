package com.hstairs.ppmajal.pddl.heuristics;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.pddl.heuristics.advanced.*;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Fix;
import com.hstairs.ppmajal.pddl.heuristics.advanced.experimental.H1Res;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.*;

/**
 * Factory and registry for PDDL heuristics.
 * Provides access to all implemented heuristics and help descriptions.
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

    private static class HeuristicInfo {
        final String id;
        final String name;
        final String description;

        HeuristicInfo(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }

    public static SearchHeuristic getHeuristic(
            String heuristic,
            PDDLProblem heuristicProblem,
            String redundantConstraints,
            boolean helpfulActionsPruning,
            boolean helpfulTransitions,
            boolean toOneTransformation,
            int linearEffectsAbstraction,
            boolean aibrDebugging
    ) {
        if (redundantConstraints == null) {
            redundantConstraints = "";
        }

        Map<AndCond, Collection<IntArraySet>> redConstraint = null;

        if ("smart".equals(redundantConstraints)) {
            final H1 h1 = new H1(heuristicProblem, true, true, false,
                    "smart", false, true, false, false, false, linearEffectsAbstraction);
            h1.computeEstimate(heuristicProblem.getInit());
        }

        switch (heuristic) {
            case "gc":
                return new GoalCounting(heuristicProblem);
            case "hadd":
                return new H1(heuristicProblem, true, false, false, redundantConstraints, helpfulActionsPruning,
                        false, helpfulTransitions, false, redConstraint, toOneTransformation, linearEffectsAbstraction);
            case "haddb":
                return new H1WithBucketEXP(heuristicProblem, true, false, false, redundantConstraints,
                        helpfulActionsPruning, false, helpfulTransitions, false, redConstraint, toOneTransformation, linearEffectsAbstraction);
            case "ngc":
                return new NumericGoalCounting(heuristicProblem);
            case "agnosticngc":
                return new StructureSensitiveNumericGoalCounting(heuristicProblem);
            case "mgc":
                return new ManhattanHeuristic(heuristicProblem);
            case "hradd":
                return new H1(heuristicProblem, true, false, false, "brute", false, false, false, false, toOneTransformation, linearEffectsAbstraction);
           case "hrmax":
                return new H1(heuristicProblem, false, false, false, "brute", false, false, false, false, toOneTransformation, linearEffectsAbstraction);
            case "hrmaxssnp":
                return new H1(heuristicProblem, false, false, false, "brute", false, false, false, false, redConstraint, toOneTransformation, linearEffectsAbstraction, true);
            case "hrmaxssnphybrid":
                return new H1Hybrid(
                        heuristicProblem,
                        "brute",
                        redConstraint,
                        toOneTransformation,
                        linearEffectsAbstraction
                );
            case "hmaxssnp":
                return new H1(heuristicProblem, false, false, false, redundantConstraints, false, false, false, false, redConstraint, toOneTransformation, linearEffectsAbstraction, true);
            case "lmcut_old":
                return new lmcutOld(heuristicProblem, false, false, false, redundantConstraints, false, false, false, false, toOneTransformation, linearEffectsAbstraction);
            case "lmcut":
                return new LmCut(heuristicProblem, false, false, false, redundantConstraints, false, false, false, false, toOneTransformation, linearEffectsAbstraction);
            case "cpcut":
                return CriticalPathCut.create(
                        heuristicProblem,
                        redundantConstraints,
                        toOneTransformation,
                        linearEffectsAbstraction
                );
            case "cpcutssnp":
                return CriticalPathCutSsnp.create(
                        heuristicProblem,
                        redundantConstraints,
                        toOneTransformation,
                        linearEffectsAbstraction
                );
            case "hrmaxb":
                return new H1WithBucketEXP(heuristicProblem, false, false, false, "brute", false, false, false, false, toOneTransformation, linearEffectsAbstraction);
            case "h1res":
                return new H1Res(heuristicProblem, redundantConstraints, false, false);
            case "h1res2":
                return new H1Res(heuristicProblem, redundantConstraints, true, false);
            case "h1res3":
                return new H1Res(heuristicProblem, redundantConstraints, true, true);
            case "h1res4":
                return new H1Res(heuristicProblem, redundantConstraints, false, true);
            case "hmax":
                return new H1(heuristicProblem, false, false, false, redundantConstraints, false, false, false, false, redConstraint, false, linearEffectsAbstraction);
            case "hmrp":
                return new H1(heuristicProblem, true, true, false, redundantConstraints, helpfulActionsPruning, false, helpfulTransitions, true, redConstraint, toOneTransformation, linearEffectsAbstraction);
            case "hmrpb":
                return new H1WithBucketEXP(heuristicProblem, true, true, false, redundantConstraints, helpfulActionsPruning, false, helpfulTransitions, true, redConstraint, toOneTransformation, linearEffectsAbstraction);
            case "hmrp_fix":
                return new H1Fix(heuristicProblem, false, false, redundantConstraints, helpfulActionsPruning, false, false, true, false);
            case "hmrp_easy_fix":
                return new H1Fix(heuristicProblem, true, true, redundantConstraints, helpfulActionsPruning, false, false, false, false);
            case "hmrp_fix_tran":
                return new H1Fix(heuristicProblem, false, false, redundantConstraints, helpfulActionsPruning, false, false, false, true);
            case "blind":
                return new BlindHeuristic(heuristicProblem);
            case "01blind":
                return new GoalSensitiveHeuristic(heuristicProblem);
            case "aibr":
                return new Aibr(heuristicProblem, false, aibrDebugging);
            case "hlm-count":
                return new LM(heuristicProblem);
            case "hlm-lp":
                return new LM(heuristicProblem, "lp", redundantConstraints, "cplex");
            case "hlm-lp-gurobi":
                return new LM(heuristicProblem, "lp", redundantConstraints, "gurobi");
            case "hgen":
                return new HGen(heuristicProblem);
            default:
                return new GoalSensitiveHeuristic(heuristicProblem);
        }
    }

    public static List<HeuristicInfo> getAvailableHeuristics() {
        return List.of(
                new HeuristicInfo("gc", "Goal Counting", "Counts the number of unsatisfied goals as heuristic value."),
                new HeuristicInfo("hadd", "HAdd", "Additive version of subgoaling heuristic."),
                new HeuristicInfo("haddb", "HAdd-Bucket", "Additive heuristic with bucket expansion."),
                new HeuristicInfo("ngc", "NGC", "Structure-sensitive numeric goal counting."),
                new HeuristicInfo("agnosticngc", "AgnosticNGC", "Numeric goal counting ignoring structure."),
                new HeuristicInfo("mgc", "MGC", "Manhattan heuristic for numeric goals."),
                new HeuristicInfo("hradd", "HRAdd", "Additive version of subgoaling heuristic plus redundant constraints."),
                new HeuristicInfo("hrmax", "HRMax", "Hmax for Numeric Planning with redundant constraints."),
                new HeuristicInfo("hrmaxssnp", "HRMaxSSNP", "HRMax with SSNP-aware additive twist enabled."),
                new HeuristicInfo("hrmaxssnphybrid", "HRMaxSSNPHybrid", "SSNP-aware HRMax with a witness-local activation floor."),
                new HeuristicInfo("cpcutssnp", "CPCutSSNP", "Critical path cost partitioning using the minimum action-cost-to-numeric-progress ratio."),
                new HeuristicInfo("hrmaxb", "HRMax-Bucket", "Hmax with bucket expansion and redundant constraints."),
                new HeuristicInfo("lmcut_new", "LMCutNew", "LM-Cut variant implemented as an H1 extension."),
                new HeuristicInfo("h1res", "H1Res", "Resolution-based heuristic without optimizations."),
                new HeuristicInfo("h1res2", "H1Res2", "Resolution-based heuristic with relaxed operator relevance pruning."),
                new HeuristicInfo("h1res3", "H1Res3", "Resolution-based heuristic with both relevance and transition pruning."),
                new HeuristicInfo("h1res4", "H1Res4", "Resolution-based heuristic with transition pruning only."),
                new HeuristicInfo("hmax", "HMax", "Hmax for Numeric Planning."),
                new HeuristicInfo("hmrp", "HMRP", "Heuristic based on MRP extraction."),
                new HeuristicInfo("hmrpb", "HMRP-Bucket", "HMRP with bucket expansion."),
                new HeuristicInfo("hmrp_fix", "HMRPFix", "Fixed variant of HMRP with adjusted mutex handling."),
                new HeuristicInfo("hmrp_easy_fix", "HMRPEasyFix", "Simplified fixed variant of HMRP for efficiency."),
                new HeuristicInfo("hmrp_fix_tran", "HMRPFixTran", "Fixed variant of HMRP including transition-based handling."),
                new HeuristicInfo("blind", "Blind", "Blind heuristic always returning 0 (uninformed)."),
                new HeuristicInfo("01blind", "01Blind", "Goal-sensitive blind heuristic returning 0 or 1 depending on state."),
                new HeuristicInfo("aibr", "AIBR", "Additive Interval Based relaxation heuristic."),
                new HeuristicInfo("hlm-count", "HLMCount", "Landmark-count heuristic estimating distance by number of unsatisfied landmarks."),
                new HeuristicInfo("hlm-lp", "HLM-LP", "Landmark heuristic using linear programming (LP) with CPLEX."),
                new HeuristicInfo("hlm-lp-gurobi", "HLM-LP-Gurobi", "Landmark heuristic using LP solved with Gurobi."),
                new HeuristicInfo("hgen", "HGen", "Generalised hmax for handling conjunctions directly.")
        );
    }

    public static String getHelpString() {
        StringBuilder sb = new StringBuilder("Available Heuristics:\n");
        for (HeuristicInfo info : getAvailableHeuristics()) {
            sb.append(" - ").append(info.id)
                    .append(": ").append(info.description)
                    .append("\n");
        }
        return sb.toString();
    }
}
