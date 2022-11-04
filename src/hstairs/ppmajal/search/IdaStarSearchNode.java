package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;

public class IdaStarSearchNode extends SimpleSearchNode {

    public int numberOfSons;
    public float minSoFar;

    public IdaStarSearchNode (State s, Object transition, IdaStarSearchNode father, float g) {
        super(s, transition, father, g);
        numberOfSons = 0;
        minSoFar = Float.MAX_VALUE;
    }

    @Override
    public String toString ( ) {
        return "IdaStarSearchNode{" +
                "s=" + s +
                ", transition=" + transition +
                ", gValue=" + gValue +
                '}';
    }
}