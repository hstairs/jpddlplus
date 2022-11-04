package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import java.util.ArrayList;

public class SimpleSearchNode {
    final public State s;
    final public Object transition;
    final public SimpleSearchNode father;
    public float gValue;


    public SimpleSearchNode (State s, Object transition, SimpleSearchNode father, float g) {
        this.s = s;
        if (!(transition instanceof ArrayList))
            this.transition = transition;
        else{
            this.transition = null;
        }
        this.father = father;
        this.gValue = g;
    }
}