package com.hstairs.ppmajal.search.searchnodes;

import com.hstairs.ppmajal.problem.State;

public class IdaStarSearchNode extends SimpleSearchNode {

    public int numberOfSons;
    public float minSoFar;

    public IdaStarSearchNode (State s, Object transition, IdaStarSearchNode father, float g, boolean trackingUniqueness) {
        super(s, transition, father, g, trackingUniqueness);
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