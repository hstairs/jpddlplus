package com.hstairs.ppmajal.search.searchnodes;

import java.util.UUID;

import com.hstairs.ppmajal.problem.State;

public class SimpleSearchNode {
    final public State s;
    final public Object transition;
    final public SimpleSearchNode father;
    public float gValue;
    public final UUID id;

    public SimpleSearchNode(State s, Object transition, SimpleSearchNode father, float g, boolean trackingUniqueness) {
        this.s = s;
        if (!(transition instanceof Integer)) {
            this.transition = transition;
        } else {
            this.transition = null;
        }
        this.father = father;
        this.gValue = g;
        if (trackingUniqueness)
            this.id = UUID.randomUUID();
        else
            this.id = null;
    }
}