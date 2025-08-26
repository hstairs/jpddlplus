package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.*;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.lang3.tuple.ImmutablePair;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

public class PDDLPlanner {
    final String search;
    final String heuristic;
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
    private IExternalLogger extenalLogger;

    // ---------------- Static Maps ---------------- //
    private static final Map<String, SearchEngine.TieBreaking> TIE_BREAKERS = Map.of(
            "smaller_g", SearchEngine.TieBreaking.LOWERG,
            "larger_g", SearchEngine.TieBreaking.HIGHERG,
            "arbitrary", SearchEngine.TieBreaking.ARBITRARY
    );

    private static final Map<String, BiFunction<PDDLPlanner, TieBreaker, SearchEngine>> SEARCH_ENGINES = Map.ofEntries(
            Map.entry("wastar", (planner, tb) -> new WAStar(planner.hWeigth, true, planner.helpfulActions, tb, planner.saveSearchSpace, planner.boundG)),
            Map.entry("gbfs", (planner, tb) -> new WAStar(planner.hWeigth, false, planner.helpfulActions, tb, planner.saveSearchSpace, planner.boundG)),
            Map.entry("ehs", (planner, tb) -> new EHS(planner.helpfulActions)),
            Map.entry("ida", (planner, tb) -> new IDAStar(planner.helpfulActions, planner.hWeigth, false, false, false, System.out)),
            Map.entry("lazygbfs", (planner, tb) -> new LazyWAStar(planner.hWeigth, false, planner.helpfulActions, planner.saveSearchSpace, tb, planner.boundG)),
            Map.entry("lazywastar", (planner, tb) -> new LazyWAStar(planner.hWeigth, true, planner.helpfulActions, planner.saveSearchSpace, tb, planner.boundG))
    );

    public PDDLPlanner(String search, String heuristic, String redundantConstraints,
                    boolean helpfulActionPruning, boolean helpfulTransitions,
                    float hWeigth, BigDecimal planningDelta, BigDecimal executionDelta, String t,
                    boolean saveSearchSpace, float depthLimit, IExternalLogger extenalLogger) {
        this.search = search;
        this.heuristic = heuristic;
        this.redundantConstraints = redundantConstraints;
        this.helpfulTransitions = helpfulTransitions;
        this.helpfulActions = helpfulActionPruning;
        this.hWeigth = hWeigth;
        this.executionDelta = executionDelta;
        this.planningDelta = planningDelta;
        this.t = t;
        this.saveSearchSpace = saveSearchSpace;
        this.boundG = depthLimit;
        this.extenalLogger = extenalLogger;
    }

    public PDDLPlanner(String search, String heuristic, String redundantConstraints,
                       boolean helpfulActionPruning, boolean helpfulTransitions,
                       float hWeigth, BigDecimal planningDelta, BigDecimal executionDelta, String t,
                       boolean saveSearchSpace, float depthLimit) {
        this(search, heuristic, redundantConstraints, helpfulActionPruning, helpfulTransitions, hWeigth, planningDelta, executionDelta, t, saveSearchSpace, depthLimit, null);
    }
    public SearchNode searchSpaceHandle;
    public PDDLSolution plan(PDDLProblem p, SearchHeuristic h){
        TieBreaker tb = new TieBreaker(
                TIE_BREAKERS.getOrDefault(t, SearchEngine.TieBreaking.ARBITRARY)
        );

        searchEngine = SEARCH_ENGINES
                .getOrDefault(search.toLowerCase(), (pl, tie) -> new WAStar(pl.hWeigth, false, pl.helpfulActions, tie, pl.saveSearchSpace, pl.boundG))
                .apply(this, tb);

        searchEngine.setExtenalLogger(this.extenalLogger);

        searchEngine.beforeExecution();
        final SimpleSearchNode solutionHandle = searchEngine.search(p, h, System.out);
        searchEngine.afterExecution();
        if (solutionHandle == null)
            return new PDDLSolution(null,null,searchEngine.getStats(), -1);
        return new PDDLSolution(this.extractPlan(solutionHandle,p),
                solutionHandle, searchEngine.getStats(), solutionHandle.gValue);
    }

    public LinkedList<ImmutablePair<BigDecimal, TransitionGround>> extractPlan (SimpleSearchNode input, PDDLProblem p) {

        final LinkedList<ImmutablePair<BigDecimal,TransitionGround>> plan = new LinkedList<>();
        State lastState = input.s;
        if (!(input instanceof SearchNode c)) {
            SimpleSearchNode temp = input;
            while (temp.transition != null) {
                Double time = null;
                plan.addFirst(ImmutablePair.of(BigDecimal.ZERO,(TransitionGround)temp.transition));
                temp = temp.father;
            }
            return plan;
        }
        if (p.getProcessesSet().isEmpty()) {
            while ((c.transition != null || c.waitingPoints > 0 )) {
                BigDecimal time = null;
                if (c.father != null && c.father.s instanceof PDDLState) {
                    time = ((PDDLState) c.father.s).time;
                }
                if (c.transition != null) {//this is an action
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
        }else {
            System.out.println("Extracting plan with execution delta: " + executionDelta);
            BigDecimal time = ((PDDLState) c.s).time;
            TransitionGround waiting = TransitionGround.waitingAction();
            State current = null;
            while (c != null ) {
                if (c.transition != null) {
                    // This is an action
                    plan.addFirst(ImmutablePair.of(((PDDLState) c.s).time, (TransitionGround) c.transition));
                } else { //This is when I am waiting
                    for (int i = 0 ; i < c.waitingPoints; i++ ){
                        time = time.subtract(executionDelta);
                        plan.addFirst(ImmutablePair.of(time, waiting));
                    }
                }
                current = c.s;
                c = (SearchNode) c.father;
            }
            final LinkedList<ImmutablePair<BigDecimal,TransitionGround>> finalPlan = new LinkedList<>();
            BigDecimal currentTime = new BigDecimal(0);
            for (org.apache.commons.lang3.tuple.Pair<BigDecimal, TransitionGround> ele : plan) {
                TransitionGround right = ele.getRight();
                if (right.getSemantics().equals(Transition.Semantics.PROCESS)) {
                    ArrayList<TransitionGround> sponteneousTransitions = new ArrayList();
                    final ImmutablePair<State, Integer> stateCollectionPair
                            = p.simulation(current, executionDelta,
                            executionDelta, false, null,sponteneousTransitions);
                    if (stateCollectionPair == null) {
                        throw new RuntimeException("This can't be possible");
                    } else {
                        if (sponteneousTransitions.isEmpty()){
                            System.out.println("something fishy just happened");
                        }
                        for (var v: sponteneousTransitions){
                            finalPlan.add(ImmutablePair.
                                    of(currentTime, v));
                            if (v.getSemantics().equals(Transition.Semantics.PROCESS)){
                                currentTime = currentTime.add(executionDelta);
                            }
                        }
                    }
                    current = stateCollectionPair.getLeft();
                }else{
                    if (ele.getRight() != null && right.getSemantics().equals(Transition.Semantics.ACTION)) {
                        current.apply(right, current.clone());
                        finalPlan.add(ImmutablePair.
                                of(currentTime, right));
                    }else{
                        throw new RuntimeException("We can't have something different from actions or processes");
                    }
                }
            }

            return finalPlan;
        }
        return plan;
    }

    public SearchNode getSearchSpaceHandle(){
        return searchEngine.getSearchSpaceHandle();
    }

    public static ArrayList<String> getAvailableSearchEngines() {
        return new ArrayList<>(SEARCH_ENGINES.keySet());
    }

    public static ArrayList<String> getAvailableTieBreakers() {
        return new ArrayList<>(TIE_BREAKERS.keySet());
    }
}
