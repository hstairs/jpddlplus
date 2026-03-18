package com.hstairs.ppmajal.search.searchnodes;

import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import java.util.Map;
import com.hstairs.ppmajal.problem.State;


public class DPEXSearchNode extends SearchNode  {
    // Angel
    public Map<String, Double> sampledInputs;
    private final java.util.Set<String> appliedActions = new java.util.HashSet<>();
    public float rectification = 0.0f;
    
    public DPEXSearchNode(State s1, Object action, SearchNode father, float g, float h, boolean trackingUniqueness) {
        super(s1, action, father, g, h, trackingUniqueness);
        this.sampledInputs = null;
    }

    public DPEXSearchNode(State s1, Object action, SearchNode father, float gValue, float fExt, float hValue,
            boolean jsonSaving, boolean trackingUniqueness, Map<String, Double> sampledInputs) {
        super(s1, action, father, gValue, fExt, hValue, jsonSaving, trackingUniqueness);
        this.sampledInputs = sampledInputs;
    }

    public DPEXSearchNode(State s1, float action_cost_to_get_here, float fExt, float hValue, boolean saving_json, boolean trackingUniqueness) {
        super(s1, action_cost_to_get_here, fExt, hValue, saving_json, trackingUniqueness);
        this.sampledInputs = null;
    }

    public boolean isActionAlreadyApplied(String action) {
        return appliedActions.contains(action);
    }

    public boolean markActionAsApplied(String action) {
        return appliedActions.add(action);
    }


}
