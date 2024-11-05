/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.heuristics;

import java.util.function.Supplier;
import com.hstairs.ppmajal.heuristics.advanced.Aibr;
import com.hstairs.ppmajal.heuristics.advanced.GoalCounting;
import com.hstairs.ppmajal.heuristics.advanced.GoalDifferenceCounting;
import com.hstairs.ppmajal.heuristics.advanced.H1;
import com.hstairs.ppmajal.heuristics.novelty.AtomWidthHeuristic;
import com.hstairs.ppmajal.heuristics.novelty.AtomQuantifiedBothHeuristic;
import com.hstairs.ppmajal.heuristics.novelty.IntervalQuantifiedBothHeuristic;
import com.hstairs.ppmajal.heuristics.novelty.IntervalWidthHeuristic;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.search.SearchHeuristic;

/**
 * @author enrico
 */
public class HeuristicFactory {

  public static SearchHeuristic getHeuristic(
      String heuristic,
      PDDLProblem problem,
      String redundantConstraints,
      boolean helpfulActionsPruning,
      boolean helpfulTransitions,
      SearchHeuristic helperHeuristic
  ) {
    SearchHeuristic h = helperHeuristic;
    if (heuristic.startsWith("hw1")) {
      return new AtomWidthHeuristic(problem, 1, new SearchHeuristic[]{h}, true);
    } else if (heuristic.startsWith("hw2")) {
      return new AtomWidthHeuristic(problem, 2, new SearchHeuristic[]{h}, true);
    } else if (heuristic.startsWith("hqb1")) {
      return new AtomQuantifiedBothHeuristic(problem, 1, h, true);
    } else if (heuristic.startsWith("hqb2")) {
      return new AtomQuantifiedBothHeuristic(problem, 2, h, true);
    } else if (heuristic.startsWith("hiw1")) {
      return new IntervalWidthHeuristic(problem, 1, new SearchHeuristic[]{h}, true);
    } else if (heuristic.startsWith("hiw2")) {
      return new IntervalWidthHeuristic(problem, 2, new SearchHeuristic[]{h}, true);
    } else if (heuristic.startsWith("hiqb1")) {
      return new IntervalQuantifiedBothHeuristic(problem, 1, h, true);
    } else if (heuristic.startsWith("hiqb2")) {
      return new IntervalQuantifiedBothHeuristic(problem, 2, h, true);
    } else {
      throw new UnsupportedOperationException("Error: Unknown heuristic configuration " + heuristic
          + " which requires a base heuristic");
    }
  }

  public static SearchHeuristic getHeuristic(
      String heuristic,
      PDDLProblem problem,
      String redundantConstraints,
      boolean helpfulActionsPruning,
      boolean helpfulTransitions
  ) {
    if ("smart".equals(redundantConstraints)) {
      final H1 h1 = new H1(problem, true, true, false, "smart", false, true, false, false);
      h1.computeEstimate(problem.getInit());
    }

    // lambda functions
    Supplier<SearchHeuristic> hadd = () -> new H1(problem, true, false, false, redundantConstraints,
        helpfulActionsPruning, false, helpfulTransitions, false, null
    );

    Supplier<SearchHeuristic> hmrp = () -> new H1(problem, true, true, false, redundantConstraints,
        helpfulActionsPruning, false, helpfulTransitions, true, null
    );

    HeuristicInterface getH = (baseHeuristic) -> getHeuristic(baseHeuristic, problem,
        redundantConstraints, helpfulActionsPruning, helpfulTransitions
    );

    // programmatically parse some heuristics
    if (heuristic.startsWith("hw1")) {
      SearchHeuristic h = getH.operation(heuristic.replace("w1", ""));
      return new AtomWidthHeuristic(problem, 1, new SearchHeuristic[]{h}, false);
    } else if (heuristic.startsWith("hw2")) {
      SearchHeuristic h = getH.operation(heuristic.replace("w2", ""));
      return new AtomWidthHeuristic(problem, 2, new SearchHeuristic[]{h}, false);
    } else if (heuristic.startsWith("hqb1")) {
      SearchHeuristic h = getH.operation(heuristic.replace("qb1", ""));
      return new AtomQuantifiedBothHeuristic(problem, 1, h, false);
    } else if (heuristic.startsWith("hqb2")) {
      SearchHeuristic h = getH.operation(heuristic.replace("qb2", ""));
      return new AtomQuantifiedBothHeuristic(problem, 2, h, false);
    }

    if (heuristic.startsWith("hiw1")) {
      SearchHeuristic h = getH.operation(heuristic.replace("iw1", ""));
      return new IntervalWidthHeuristic(problem, 1, new SearchHeuristic[]{h}, false);
    } else if (heuristic.startsWith("hiw2")) {
      SearchHeuristic h = getH.operation(heuristic.replace("iw2", ""));
      return new IntervalWidthHeuristic(problem, 2, new SearchHeuristic[]{h}, false);
    } else if (heuristic.startsWith("hiqb1")) {
      SearchHeuristic h = getH.operation(heuristic.replace("iqb1", ""));
      return new IntervalQuantifiedBothHeuristic(problem, 1, h, false);
    } else if (heuristic.startsWith("hiqb2")) {
      SearchHeuristic h = getH.operation(heuristic.replace("iqb2", ""));
      return new IntervalQuantifiedBothHeuristic(problem, 2, h, false);
    }

    return switch (heuristic) {
      case "gc", "hgc" -> new GoalCounting(problem);
      case "gdc", "hgdc" -> new GoalDifferenceCounting(problem);
      case "hmrp" -> hmrp.get();
      case "hadd" -> hadd.get();
      case "hradd" -> new H1(problem, true, false, false, "brute", false, false, false, false);
      case "hmax" -> new H1(problem, false, false, false, redundantConstraints, false, false, false,
          false, null
      );
      case "hrmax" -> new H1(problem, false, false, false, "brute", false, false, false, false);
      case "blind" -> new GoalSensitiveHeuristic(problem);
      case "aibr" -> {
        System.out.println("AIBR selected");
        yield new Aibr(problem);
      }
      default -> throw new UnsupportedOperationException(
          "Error: Unknown heuristic configuration " + heuristic);
    };
  }

  // use an interface for defining lambda functions
  interface HeuristicInterface {

    SearchHeuristic operation(String heuristic);
  }
}
