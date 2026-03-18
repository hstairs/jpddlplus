package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.search.SearchEngine;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;


public record PDDLSolution(LinkedList<ImmutablePair<BigDecimal, ImmutablePair<TransitionGround, Map<String,Double>>>> rawPlan, SimpleSearchNode lastNode,
                           SearchEngine.SearchStats stats, float gValueAtTheEnd){

    public PDDLState lastState() {
        return (PDDLState) lastNode.s;
    }
}

/*
public record PDDLSolution(LinkedList<ImmutablePair<BigDecimal, TransitionGround>> rawPlan, SimpleSearchNode lastNode,
                           SearchEngine.SearchStats stats, float gValueAtTheEnd){

    public PDDLState lastState() {
        return (PDDLState) lastNode.s;
    }
}
*/
