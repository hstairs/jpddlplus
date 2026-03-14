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
import java.util.*;
import java.util.function.BiFunction;

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
    private final boolean tunnelling;
    private SearchEngine searchEngine;
    private IExternalLogger extenalLogger;
    private final long timeoutInMs;

    // ---------------- Static Maps ---------------- //
    private static final Map<String, SearchEngine.TieBreaking> TIE_BREAKERS = Map.of(
        "smaller_g", SearchEngine.TieBreaking.LOWERG,
        "larger_g", SearchEngine.TieBreaking.HIGHERG,
        "arbitrary", SearchEngine.TieBreaking.ARBITRARY
    );

    private static final String[][] SE_INFOS = {
        {"wastar", "WAStar", "Weighted A* Search"},
        {"gbfs", "GBFS", "Greedy Best-First Search"},
        {"ehs", "EHS", "Enhanced Heuristic Search"},
        {"ida", "IDA", "Iterative Deepening A*"},
        {"lazygbfs", "LazyGBFS", "Lazy Greedy Best-First Search"},
        {"lazywastar", "LazyWAStar", "Lazy Weighted A* Search"}
    };

    private static final Map<String, BiFunction<PDDLPlanner, TieBreaker, SearchEngine>> SEARCH_ENGINES = Map.ofEntries(
        Map.entry(SE_INFOS[0][0], (planner, tb) -> new WAStar(planner.hWeigth, true, planner.helpfulActions, tb, planner.saveSearchSpace, planner.boundG, planner.bucketBasedQueueSearch)),
        Map.entry(SE_INFOS[1][0], (planner, tb) -> new WAStar(planner.hWeigth, false, planner.helpfulActions, tb, planner.saveSearchSpace, planner.boundG, planner.bucketBasedQueueSearch)),
        Map.entry(SE_INFOS[2][0], (planner, __) -> new EHS(planner.helpfulActions)),
        Map.entry(SE_INFOS[3][0], (planner, __) -> new IDAStar(planner.helpfulActions, planner.hWeigth, false, false, false, System.out)),
        Map.entry(SE_INFOS[4][0], (planner, tb) -> new LazyWAStar(planner.hWeigth, false, planner.helpfulActions, planner.saveSearchSpace, tb, planner.boundG, false, planner.bucketBasedQueueSearch)),
        Map.entry(SE_INFOS[5][0], (planner, tb) -> new LazyWAStar(planner.hWeigth, true, planner.helpfulActions, planner.saveSearchSpace, tb, planner.boundG))
    );

    final private boolean bucketBasedQueueSearch;

    public PDDLPlanner() {
        this("wastar", "no", false,
                false, 1,
                new BigDecimal(1.0), new BigDecimal(1.0),
                "", false, Float.POSITIVE_INFINITY,false, false,
                null, Long.MAX_VALUE);
    }

    public PDDLPlanner(String search, String redundantConstraints,
                       boolean helpfulActionPruning, boolean helpfulTransitions,
                       float hWeigth, BigDecimal planningDelta, BigDecimal executionDelta, String t,
                       boolean saveSearchSpace, float depthLimit, boolean bucketBasedQueueSearch, boolean tunnelling, IExternalLogger extenalLogger) {
        this(search, redundantConstraints, helpfulActionPruning, helpfulTransitions, hWeigth, planningDelta, executionDelta, t,
                saveSearchSpace, depthLimit, bucketBasedQueueSearch, tunnelling, extenalLogger, Long.MAX_VALUE);
    }

    public PDDLPlanner(String search, String redundantConstraints,
                       boolean helpfulActionPruning, boolean helpfulTransitions,
                       float hWeigth, BigDecimal planningDelta, BigDecimal executionDelta, String t,
                       boolean saveSearchSpace, float depthLimit, boolean bucketBasedQueueSearch, boolean tunnelling,
                       IExternalLogger extenalLogger, long timeoutInMs) {
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
        this.bucketBasedQueueSearch = bucketBasedQueueSearch;
        this.extenalLogger = extenalLogger;
        this.tunnelling = tunnelling;
        this.timeoutInMs = timeoutInMs <= 0 ? Long.MAX_VALUE : timeoutInMs;
    }

    public SearchNode searchSpaceHandle;
    public PDDLSolution plan(PDDLProblem p, SearchHeuristic h){
        TieBreaker tb = new TieBreaker(
                TIE_BREAKERS.getOrDefault(t, SearchEngine.TieBreaking.ARBITRARY)
        );
        p.tunnelling = tunnelling;

        searchEngine = SEARCH_ENGINES
                .getOrDefault(search.toLowerCase(), (pl, tie) -> new WAStar(pl.hWeigth, false, pl.helpfulActions, tie, pl.saveSearchSpace, pl.boundG, pl.bucketBasedQueueSearch))
                .apply(this, tb);

        searchEngine.setExtenalLogger(this.extenalLogger);
        searchEngine.setTimeoutInMs(timeoutInMs);

        searchEngine.beforeExecution();
        final SimpleSearchNode solutionHandle = searchEngine.search(p, h, System.out);
        searchEngine.afterExecution();
        if (solutionHandle == null)
            return new PDDLSolution(null, null, searchEngine.getStats(), -1);
        return new PDDLSolution(this.extractPlan(solutionHandle, p),
                solutionHandle, searchEngine.getStats(), solutionHandle.gValue);
    }

    public LinkedList<ImmutablePair<BigDecimal, TransitionGround>> extractPlan(SimpleSearchNode input, PDDLProblem p) {

        final LinkedList<ImmutablePair<BigDecimal, TransitionGround>> plan = new LinkedList<>();
        State lastState = input.s;
        int nTun = 0;
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
                if (c.transition != null) {//this is an action
                    if (c.transition instanceof ImmutablePair) {
                        final ImmutablePair<TransitionGround, Integer> t = (ImmutablePair<TransitionGround, Integer>) c.transition;
                        for (int i = 0; i < t.right; i++) {
                            plan.addFirst(ImmutablePair.of(time, t.left));
                        }
                        System.out.println("JUMP for " + t.left + ":" + t.right);
                    } else if (c.transition instanceof TransitionGround){
                        plan.addFirst(ImmutablePair.of(time, (TransitionGround) c.transition));
                    } else if (c.transition instanceof List){
                        ArrayList<TransitionGround> transition = (ArrayList<TransitionGround>) c.transition;
                        nTun+=transition.size();
                        for (int k = ((ArrayList<?>) c.transition).size()-1; k >=0; k--){
                            plan.addFirst(ImmutablePair.of(time, transition.get(k)));
                        }
                    }else{
                        throw new RuntimeException("This can't be");
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
                    // This is an action
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
        if (tunnelling)
                System.out.println("Cumulative Size of Tunnels:"+nTun);
        return plan;
    }

    public SearchNode getSearchSpaceHandle(){
        return searchEngine.getSearchSpaceHandle();
    }

    public static String[][] getAvailableSearchEngines() {
        return SE_INFOS;
    }

    public static Collection<String> getAvailableTieBreakers() {
        return TIE_BREAKERS.keySet();
    }

    public static String getHelpString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Available Search Engines:\n");
        for (String[] seInfo : SE_INFOS) {
            sb.append(" - ").append(seInfo[0]).append(": ").append(seInfo[1]).append("\n");
        }
        return sb.toString();
    }
}
