package com.hstairs.ppmajal.search.searchnodes;

import com.hstairs.ppmajal.problem.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IdaStarSearchNode extends SimpleSearchNode {

    public int numberOfSons;
    public float minSoFar;
    private static int nextId = 0; //only way to have a global ID for all nodes
    private final int id;
    private final Integer parentId;

    public IdaStarSearchNode (State s, Object transition, IdaStarSearchNode father, float g) {
        super(s, transition, father, g);
        numberOfSons = 0;
        minSoFar = Float.MAX_VALUE;
        this.id = nextId++;
        this.parentId = (father != null) ? father.getId() : null;
    }

    @Override
    public String toString ( ) {
        return "IdaStarSearchNode{" +
                "s=" + s +
                ", transition=" + transition +
                ", gValue=" + gValue +
                '}';
    }

    public int getId() {
        return id;
    }
    
    public Integer getParentId() {
        return parentId;
    }

    public float getG() {
        return gValue;
    }

    public Object getTransition() {
        return transition;
    }

    public String getState() {
        return s.toString();
    }

    //Give the JSON representation of the state
    public Object getJsonState() {
        String stateStr = s.toString();
        try {
            return new JSONParser().parse(stateStr);
        } catch (ParseException ex) {
            return stateStr;
        }
    }

}