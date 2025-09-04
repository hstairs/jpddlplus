package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.search.SearchEngine;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.math.BigDecimal;
import java.util.LinkedList;

public record PDDLSolution(LinkedList<ImmutablePair<BigDecimal, TransitionGround>> rawPlan, PDDLState lastState,
                           SearchEngine.SearchStats stats, float gValueAtTheEnd){
}
