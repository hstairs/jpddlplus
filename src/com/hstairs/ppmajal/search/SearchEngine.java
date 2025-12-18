package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class SearchEngine {
    protected final float G_DEFAULT = Float.NaN;
    protected int deadEndsDetected;
    protected int nodesExpanded;
    protected int nodesEvaluated;
    protected int duplicatedDetected;
    //Timing information
    protected long totalTime;
    protected long heuristicTime;
    State lastState;
    final boolean helpfulActions;
    private SearchNode searchSpaceHandle;
    protected IExternalLogger extenalLogger;

    protected SearchEngine(boolean helpfulActionsPruning) {
        this.helpfulActions = helpfulActionsPruning;
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
            searchSpaceHandle = init;//this needs to have an handle on the initial state for saving it into a json file

    }

    public abstract SimpleSearchNode search(SearchProblem p, SearchHeuristic h, PrintStream out);
    public LinkedList extractDecisions(SimpleSearchNode c) {
        LinkedList plan = new LinkedList<>();
        lastState = c.s;
        while (c.transition != null) {
            if (c.transition != null) {//this is an action
                plan.addFirst(c.transition);
            }
            c = c.father;
        }
        return plan;
    }
    Object[] getActionsToSearch(SimpleSearchNode currentNode, SearchProblem problem, SearchHeuristic h) {
        if (helpfulActions && currentNode != null) {
            if (problem instanceof PDDLProblem && !((PDDLProblem) problem).getProcessesSet().isEmpty()) {
                ArrayList ret = new ArrayList();
                for (var v: ((SearchNode)currentNode).helpfulActions){
                    if (v instanceof TransitionGround){
                        TransitionGround transition = (TransitionGround) v;
                        if (transition.getSemantics().equals(Transition.Semantics.ACTION))
                            ret.add(v);
                    }

                }
                return ret.toArray();
            }else{
                return ((SearchNode)currentNode).helpfulActions;
            }
        }
        return h.getTransitions(false);
    }

    public SearchNode getSearchSpaceHandle() {
        return searchSpaceHandle;
    }

    public enum TieBreaking {
        LOWERG,
        HIGHERG,
        ARBITRARY
    }

    public record SearchStats(int nodesExpanded, int nodesEvaluated, int deadEnds, int duplicates, long searchTime, long heuristicTime) {
    }

    public void beforeExecution() {
        if(this.extenalLogger == null) return;
        this.extenalLogger.beforeExecution();
    }

    public void afterExecution() {
        if(this.extenalLogger == null) return;
        this.extenalLogger.afterExecution();
    }

    public void setExtenalLogger(IExternalLogger extenalLogger) {
        this.extenalLogger = extenalLogger;
    }

    public void tryLog(SimpleSearchNode node, ExternalLoggerLogType logType) {
        if(this.extenalLogger == null) return;
        this.extenalLogger.log(node, logType);
    }
}
