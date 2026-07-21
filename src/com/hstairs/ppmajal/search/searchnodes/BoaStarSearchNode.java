package com.hstairs.ppmajal.search.searchnodes;

import java.util.ArrayList;
import java.util.List;
import com.hstairs.ppmajal.problem.State;

public class BoaStarSearchNode extends SimpleSearchNode {
    public final float g2;
    public final float f1;
    public final float f2;
    public long timeFound = -1;
    private List<BoaStarSearchNode> paretoFrontier = null;

    public BoaStarSearchNode(State state, Object action, BoaStarSearchNode father, float g1, float g2, float f1, float f2){
        super(state, action, father, g1, false);
        this.g2 = g2;
        this.f1 = f1;
        this.f2 = f2;
    }

    // crea un nodo per salvare l'intera frontiera di pareto.
    public BoaStarSearchNode(List<BoaStarSearchNode> frontier) {
        super(frontier.get(0).s, 
              frontier.get(0).transition, 
              (BoaStarSearchNode) frontier.get(0).father, 
              frontier.get(0).gValue, 
              false);
        this.paretoFrontier = frontier;
        this.g2 = frontier.get(0).g2;
        this.f1 = frontier.get(0).f1;
        this.f2 = frontier.get(0).f2;
    }

    // restituisce l'intera frontiera di pareto.
    public List<BoaStarSearchNode> getSolution() {
        return new ArrayList<>(this.paretoFrontier);
    }
}