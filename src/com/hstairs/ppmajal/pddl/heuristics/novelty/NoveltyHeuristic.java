package com.hstairs.ppmajal.pddl.heuristics.novelty;

import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.pddl.heuristics.novelty.objects.NumericIntervalAssignment;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;

import java.util.HashSet;
import java.util.List;

public abstract class NoveltyHeuristic implements SearchHeuristic {

  protected final int k;
  protected final NoveltyValue noveltyValue;
  protected final NoveltyType noveltyType;
  protected final int C1;
  protected final int C2;
  protected final int nNumFluents;
  protected final int nBoolFluents;
  protected final int nFluents;
  protected final int nSubgoals;
  protected final PDDLProblem problem;
  int level0Novel = 0;
  int level1Novel = 0;
  int level2Novel = 0;
  int notNovel = 0;

  protected NoveltyHeuristic(
      PDDLProblem problem, int k, NoveltyValue noveltyHeuristic, NoveltyType noveltyType
  ) {
    this.problem = problem;
    this.k = k;
    this.noveltyValue = noveltyHeuristic;
    this.noveltyType = noveltyType;

    nBoolFluents = problem.getTotNumberOfBoolVariables();
    nNumFluents = problem.getTotNumberOfNumVariables();
    nFluents = nBoolFluents + nNumFluents;
    // technically we can have negative QB heuristics, but ensuring positivity is nice
    C1 = nFluents;
    C2 = (nFluents * (nFluents - 1)) / 2;

    // For subgoalHeursitic
    nSubgoals = problem.createSubgoals().size();
  }

  protected float computeHeuristic(SearchHeuristic h, State s) {
    return h.computeEstimate(s);
  }


  void logNoveltyInformation(float h) {
    switch (noveltyValue) {
      case WIDTH -> logWidthNoveltyInformation(h);
      case QUANTIFIED_BOTH -> logQuantifiedBothNoveltyInformation(h);
    }
  }


  private void logWidthNoveltyInformation(float h) {
    if (h == 0) {
      level0Novel++;
    } else if (h == 1) {
      level1Novel++;
    } else if (h == 2 && k >= 2) {
      level2Novel++;
    } else {
      notNovel++;
    }
  }

  private void logQuantifiedBothNoveltyInformation(float h) {
    if (h < C1) {
      level1Novel++;
    } else if ((h < C1 + C2) && k >= 2) {
      level2Novel++;
    } else {
      notNovel++;
    }
  }

  public enum NoveltyValue {
    WIDTH, QUANTIFIED_BOTH,
  }

  public enum NoveltyType {
    ATOM, INTERVAL, SUBGOAL
  }

}
