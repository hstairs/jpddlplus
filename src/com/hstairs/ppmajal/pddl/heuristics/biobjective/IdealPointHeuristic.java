package com.hstairs.ppmajal.pddl.heuristics.biobjective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;

public class IdealPointHeuristic implements SearchHeuristic {
    private final SearchHeuristic h1;
    private final SearchHeuristic h2;

    public IdealPointHeuristic(SearchHeuristic h1, SearchHeuristic h2){
        this.h1 = h1;
        this.h2 = h2;
    }

    @Override
    public List<float[]> computeBiObjectiveEstimate(State s) {
    List<float[]> result = new ArrayList<>();
    float val1 = h1.computeEstimate(s);
    float val2 = h2.computeEstimate(s);
    float[] idealPoint = new float[]{val1, val2};
    result.add(idealPoint);
    return result;
    }

    @Override
    public Object[] getTransitions(boolean onlyHelpful) { // otteniamo tutte le azioni utili.
        Object[] t1 = h1.getTransitions(onlyHelpful);
        Object[] t2 = h2.getTransitions(onlyHelpful);
        Set<Object> combined = new LinkedHashSet<>();
        if (t1 != null) for (Object o : t1) combined.add(o);
        if (t2 != null) for (Object o : t2) combined.add(o);
        return combined.toArray();
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() { // otteniamo tutte le transizioni.
        Collection<TransitionGround> t1 = h1.getAllTransitions();
        Collection<TransitionGround> t2 = h2.getAllTransitions();
        Set<TransitionGround> combined = new LinkedHashSet<>();
        if (t1 != null) combined.addAll(t1);
        if (t2 != null) combined.addAll(t2);  
        return combined;
    }

    // Override obbligatorio
    @Override
    public float computeEstimate(State s) {
        throw new UnsupportedOperationException("IdealPointHeuristic requires computeBiObjectiveEstimate(s).");
    }

}