package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SearchEventLogger;

import java.io.PrintStream;
import java.util.LinkedList;

public abstract class SearchEngine {
    protected final float G_DEFAULT = Float.NaN;
    protected int deadEndsDetected;
    protected int nodesExpanded;
    protected int nodesEvaluated;
    protected int duplicatedDetected;
    protected long totalTime;
    protected long heuristicTime;
    State lastState;
    final boolean helpfulActions;
    private SearchNode searchSpaceHandle;


    // 🔹 Aggiunto logger per eventi
    protected SearchEventLogger eventLogger;

    protected SearchEngine(boolean helpfulActionsPruning) {
        this.helpfulActions = helpfulActionsPruning;
    }

    // 🔹 Setter per permettere a PDDLPlanner di passare il logger
    public void setEventLogger(SearchEventLogger logger) {
        this.eventLogger = logger;
        SearchNode.setEventLogger(logger);  // in modo che anche i nodi possano accedervi
    }

    public abstract SearchStats getStats();

    protected void zeroCounters() {
        deadEndsDetected = 0;
        nodesExpanded = 0;
        nodesEvaluated = 0;
        duplicatedDetected = 0;
        heuristicTime = 0;
    }

    public void initHandle(SearchNode init){
        searchSpaceHandle = init;
    }

    public SearchNode getSearchSpaceHandle() {
        return searchSpaceHandle;
    }

    public abstract SimpleSearchNode search(SearchProblem p, SearchHeuristic h, PrintStream out);

    public LinkedList extractDecisions(SimpleSearchNode c) {
        LinkedList plan = new LinkedList<>();
        lastState = c.s;
        while (c.transition != null) {
            if (c.transition != null) {
                plan.addFirst(c.transition);
            }
            c = c.father;
        }
        return plan;
    }

    Object[] getActionsToSearch(SimpleSearchNode currentNode, SearchProblem problem, SearchHeuristic h) {
        if (helpfulActions && currentNode != null) {
            return ((SearchNode) currentNode).helpfulActions;
        }
        return h.getTransitions(false);
    }

    public enum TieBreaking {
        LOWERG,
        HIGHERG,
        ARBITRARY
    }

    public record SearchStats(int nodesExpanded, int nodesEvaluated, int deadEnds, int duplicates, long searchTime, long heuristicTime) {
    }
}
