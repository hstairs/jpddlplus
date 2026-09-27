package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.search.SearchEngine;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public record PDDLSolution(LinkedList<ImmutablePair<BigDecimal, TransitionGround>> rawPlan,
        List<LinkedList<ImmutablePair<BigDecimal, TransitionGround>>> paretoPlans,
        List<PDDLState> paretoLastStates,
        List<Pair<Float, Float>> paretoCosts,
        List<Long> paretoTimes,
        SimpleSearchNode lastNode,
        SearchEngine.SearchStats stats,
        float gValueAtTheEnd) {

    // costruttore originale.
    public PDDLSolution(LinkedList<ImmutablePair<BigDecimal, TransitionGround>> rawPlan,
            SimpleSearchNode lastNode,
            SearchEngine.SearchStats stats,
            float gValueAtTheEnd) {
        this(rawPlan,
                rawPlan == null ? List.of() : List.of(rawPlan),
                List.of(),
                List.of(),
                List.of(),
                lastNode,
                stats,
                gValueAtTheEnd);
    }

    // nuovo costruttore multi-obiettivo.
    public PDDLSolution {
        paretoPlans = paretoPlans == null ? List.of() : List.copyOf(paretoPlans);
        paretoLastStates = paretoLastStates == null ? List.of() : List.copyOf(paretoLastStates);
        paretoCosts = paretoCosts == null ? List.of() : List.copyOf(paretoCosts);
        paretoTimes = paretoTimes == null ? List.of() : List.copyOf(paretoTimes);
    }

    public PDDLState lastState() {
        return (PDDLState) lastNode.s;
    }
}
