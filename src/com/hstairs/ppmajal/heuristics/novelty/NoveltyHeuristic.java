package com.hstairs.ppmajal.heuristics.novelty;

import java.io.PrintStream;
import java.util.Map;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.heuristics.novelty.objects.NumericIntervalAssignment;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;

public abstract class NoveltyHeuristic extends SearchHeuristic {

  protected final int k;
  protected final NoveltyValue noveltyValue;
  protected final NoveltyType noveltyType;
  protected final int C1;
  protected final int C2;
  protected final int nNumFluents;
  protected final int nBoolFluents;
  protected final int nFluents;
  protected final PDDLProblem problem;
  protected final boolean cachedHeuristic;
  int level0Novel = 0;
  int level1Novel = 0;
  int level2Novel = 0;
  int notNovel = 0;

  protected NoveltyHeuristic(
      PDDLProblem problem, int k, NoveltyValue noveltyHeuristic, NoveltyType noveltyType, boolean cachedHeuristic
  ) {
    this.problem = problem;
    this.k = k;
    this.noveltyValue = noveltyHeuristic;
    this.noveltyType = noveltyType;
    this.cachedHeuristic = cachedHeuristic;

    nBoolFluents = problem.getTotNumberOfBoolVariables();
    nNumFluents = problem.getTotNumberOfNumVariables();
    nFluents = nBoolFluents + nNumFluents;

    // technically we can have negative QB heuristics, but ensuring positivity is nice
    C1 = nFluents;
    C2 = (nFluents * (nFluents - 1)) / 2;
  }

  protected float computeHeuristic(SearchHeuristic h, State s) {
    return h.computeEstimate(s, cachedHeuristic);
  }

  abstract float computeNoveltyEstimate(State s);

  void logNoveltyInformation(float h) {
    switch (noveltyValue) {
      case WIDTH -> logWidthNoveltyInformation(h);
      case QUANTIFIED_BOTH -> logQuantifiedBothNoveltyInformation(h);
    }
  }

  @Override
  protected float computeEstimateForStore(State s) {
    float h = computeNoveltyEstimate(s);
    if (verboseHeuristic) {
      logNoveltyInformation(h);
    }
    return h;
  }

  @Override
  public void dumpHeuristicStats() {
    if (!verboseHeuristic) {
      return;
    }

    if (noveltyValue == NoveltyValue.WIDTH) {
      out.println("0-novel: " + level0Novel);
    }
    out.println("1-novel: " + level1Novel);
    if (k >= 2) {
      out.println("2-novel: " + level2Novel);
    }
    out.println("non-novel: " + notNovel);
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
    ATOM, INTERVAL,
  }
}
