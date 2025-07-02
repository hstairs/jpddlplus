package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.*;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SearchEventLogger;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

public class PDDLPlanner {
    final String search;
    final String redundantConstraints;
    final boolean helpfulTransitions;
    final boolean helpfulActions;
    final float hWeigth;

    final public BigDecimal executionDelta;
    final public BigDecimal planningDelta;
    final String t;
    final private boolean saveSearchSpace;
    private final float boundG;
    private SearchEngine searchEngine;

    private SearchEventLogger eventLogger;
    private final boolean enableEventLogging;
    public SearchNode searchSpaceHandle;



    public PDDLPlanner() {
        this("wastar", "no", false,
                false, 1,
                new BigDecimal(1.0), new BigDecimal(1.0),
                "", false, Float.POSITIVE_INFINITY, false);
    }

    public PDDLPlanner(String search, String redundantConstraints,
                       boolean helpfulActionPruning, boolean helpfulTransitions,
                       float hWeigth, BigDecimal planningDelta, BigDecimal executionDelta, String t,
                       boolean saveSearchSpace, float depthLimit,
                       boolean enableEventLogging) {
        this.search = search;
        this.redundantConstraints = redundantConstraints;
        this.helpfulTransitions = helpfulTransitions;
        this.helpfulActions = helpfulActionPruning;
        this.hWeigth = hWeigth;
        this.executionDelta = executionDelta;
        this.planningDelta = planningDelta;
        this.t = t;
        this.saveSearchSpace = saveSearchSpace;
        this.boundG = depthLimit;
        this.enableEventLogging = enableEventLogging;

        if (enableEventLogging) {
            this.eventLogger = new SearchEventLogger();
        }
    }


    public PDDLSolution plan(PDDLProblem p, SearchHeuristic h) {
        TieBreaker tb;
        switch (t) {
            case "smaller_g":
                tb = new TieBreaker(SearchEngine.TieBreaking.LOWERG);
                break;
            case "larger_g":
                tb = new TieBreaker(SearchEngine.TieBreaking.HIGHERG);
                break;
            default:
                tb = new TieBreaker(SearchEngine.TieBreaking.ARBITRARY);
                break;
        }

        System.out.println("Motore di ricerca selezionato: " + search.toLowerCase());
        switch (search.toLowerCase()) {
            case "wastar":
                searchEngine = new WAStar(hWeigth, true, helpfulActions, tb, saveSearchSpace, enableEventLogging, boundG);
                break;
            case "gbfs":
                searchEngine = new WAStar(hWeigth, false, helpfulActions, tb, saveSearchSpace, enableEventLogging, boundG);
                break;
            case "ehs":
                searchEngine = new EHS(helpfulActions);
                break;
            case "ida":
                searchEngine = new IDAStar(helpfulActions, hWeigth, false, false, false, System.out);
            case "lazygbfs":
                searchEngine = new LazyWAStar(hWeigth, false, helpfulActions, saveSearchSpace, tb, boundG, enableEventLogging);
                break;
            case "lazywastar":
                searchEngine = new LazyWAStar(hWeigth, true, helpfulActions, saveSearchSpace, tb, boundG, enableEventLogging);
                break;
            default:
                searchEngine = new WAStar(hWeigth, false, helpfulActions, tb, saveSearchSpace, enableEventLogging, boundG);
                break;
        }

        if (eventLogger != null) {
            searchEngine.setEventLogger(eventLogger);
        }

        final SimpleSearchNode solutionHandle = searchEngine.search(p, h, System.out);

        if (solutionHandle == null)
            return new PDDLSolution(null, null, searchEngine.getStats(), -1);
        return new PDDLSolution(this.extractPlan(solutionHandle, p),
                (PDDLState) solutionHandle.s, searchEngine.getStats(), solutionHandle.gValue);
    }

    public SearchNode getSearchSpaceHandle() {
        return searchEngine.getSearchSpaceHandle();
    }

    public SearchEventLogger getEventLogger() {
        return eventLogger;
    }


    public LinkedList<ImmutablePair<BigDecimal, TransitionGround>> extractPlan(SimpleSearchNode input, PDDLProblem p) {
        final LinkedList<ImmutablePair<BigDecimal, TransitionGround>> plan = new LinkedList<>();
        State lastState = input.s;

        if (!(input instanceof SearchNode c)) {
            SimpleSearchNode temp = input;
            while (temp.transition != null) {
                Double time = null;
                plan.addFirst(ImmutablePair.of(BigDecimal.ZERO, (TransitionGround) temp.transition));
                temp = temp.father;
            }
            return plan;
        }

        if (p.getProcessesSet().isEmpty()) {
            while ((c.transition != null || c.waitingPoints > 0)) {
                BigDecimal time = null;
                if (c.father != null && c.father.s instanceof PDDLState) {
                    time = ((PDDLState) c.father.s).time;
                }

                if (c.transition != null) {
                    if (c.transition instanceof ImmutablePair) {
                        final ImmutablePair<TransitionGround, Integer> t = (ImmutablePair<TransitionGround, Integer>) c.transition;
                        for (int i = 0; i < t.right; i++) {
                            plan.addFirst(ImmutablePair.of(time, t.left));
                        }
                        System.out.println("JUMP for " + t.left + ":" + t.right);
                    } else {
                        plan.addFirst(ImmutablePair.of(time, (TransitionGround) c.transition));
                    }
                }
                c = (SearchNode) c.father;
            }
        } else {
            System.out.println("Extracting plan with execution delta: " + executionDelta);
            BigDecimal time = ((PDDLState) c.s).time;
            TransitionGround waiting = TransitionGround.waitingAction();
            State current = null;
            while (c != null) {
                if (c.transition != null) {
                    plan.addFirst(ImmutablePair.of(((PDDLState) c.s).time, (TransitionGround) c.transition));
                } else { //This is when I am waiting
                    for (int i = 0; i < c.waitingPoints; i++) {
                        time = time.subtract(executionDelta);
                        plan.addFirst(ImmutablePair.of(time, waiting));
                    }
                }
                current = c.s;
                c = (SearchNode) c.father;
            }
            final LinkedList<ImmutablePair<BigDecimal, TransitionGround>> finalPlan = new LinkedList<>();
            BigDecimal currentTime = new BigDecimal(0);
            for (org.apache.commons.lang3.tuple.Pair<BigDecimal, TransitionGround> ele : plan) {
                TransitionGround right = ele.getRight();
                if (right.getSemantics().equals(Transition.Semantics.PROCESS)) {
                    ArrayList<TransitionGround> sponteneousTransitions = new ArrayList();
                    final ImmutablePair<State, Integer> stateCollectionPair
                            = p.simulation(current, executionDelta,
                            executionDelta, false, null, sponteneousTransitions);
                    if (stateCollectionPair == null) {
                        throw new RuntimeException("This can't be possible");
                    } else {
                        if (sponteneousTransitions.isEmpty()) {
                            System.out.println("something fishy just happened");
                        }
                        for (var v : sponteneousTransitions) {
                            finalPlan.add(ImmutablePair.
                                    of(currentTime, v));
                            if (v.getSemantics().equals(Transition.Semantics.PROCESS)) {
                                currentTime = currentTime.add(executionDelta);
                            }
                        }
                    }
                    current = stateCollectionPair.getLeft();
                } else {
                    if (ele.getRight() != null && right.getSemantics().equals(Transition.Semantics.ACTION)) {
                        current.apply(right, current.clone());
                        finalPlan.add(ImmutablePair.
                                of(currentTime, right));
                    } else {
                        throw new RuntimeException("We can't have something different from actions or processes. Instead I got:" + right);
                    }
                }
            }

            return finalPlan;
        }
        return plan;
    }
}
