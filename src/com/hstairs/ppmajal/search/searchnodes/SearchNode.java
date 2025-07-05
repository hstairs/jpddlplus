/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.search.searchnodes;

import com.hstairs.ppmajal.problem.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchNode extends SimpleSearchNode {

    public final JSONObject jsonRepresentation;
    public float f;
    public float hValue;
    public int waitingPoints;
    public Object[] helpfulActions;

    private static int nextId = 0; //only way to have a global ID for all nodes
    private final int id;
    private final Integer parentId;

    // Costruttuctor without JSON savings
    public SearchNode(State s1, Object action, SearchNode father, float g, float h) {
        super(s1, action, father, g);
        this.f = h; 
        this.jsonRepresentation = null;
        this.id = nextId++;
        this.parentId = (father != null) ? father.getId() : null;
    }

    // Constrructor with JSON savings
    public SearchNode(State s1, Object action, SearchNode father, float gValue, float fExt, float hValue, boolean jsonSaving) {
        super(s1, action, father, gValue);
        this.f = fExt;
        this.hValue = hValue;
        this.parentId = (father != null) ? father.getId() : null;
        this.id = nextId++;

        if (action instanceof Integer act) {
            this.waitingPoints = act;
        } else {
            this.waitingPoints = 0;
        }

        if (jsonSaving) {
            jsonRepresentation = new JSONObject();

            if (action == null || (transition == null && waitingPoints == 0)) {
                jsonRepresentation.put("action", "init_state");
            } else if (transition == null) {
                jsonRepresentation.put("list_of_actions", "waiting");
            } else {
                jsonRepresentation.put("action", transition.toString());
            }

            jsonRepresentation.put("distance", hValue);
            jsonRepresentation.put("action_cost_to_get_here", gValue);
            jsonRepresentation.put("ancestor", (father == null) ? "init_state" : father.jsonRepresentation.get("visited_step"));
            jsonRepresentation.put("visited", false);
            jsonRepresentation.put("visit_step", -1);
            jsonRepresentation.put("descendants", new JSONArray());

            try {
                JSONObject parsedState = (JSONObject) new JSONParser().parse(s1.toString());
                jsonRepresentation.put("state", parsedState);
            } catch (ParseException ex) {
                jsonRepresentation.put("state", s1.toString());
            }
        } else {
            jsonRepresentation = null;
        }
    }

    // Constructor only used for init-state
    public SearchNode(State s1, float gValue, float fExt, float hValue, boolean savingJson) {
        super(s1, 0, null, gValue);
        this.f = fExt;
        this.id = 0;
        this.parentId = null;

        if (savingJson) {
            jsonRepresentation = new JSONObject();
            jsonRepresentation.put("action", (transition == null) ? "init_state" : transition.toString());
            jsonRepresentation.put("distance", hValue);
            jsonRepresentation.put("action_cost_to_get_here", gValue);
            jsonRepresentation.put("ancestor", "init_state");
            jsonRepresentation.put("visited", false);
            jsonRepresentation.put("visit_step", -1);
            jsonRepresentation.put("descendants", new JSONArray());

            try {
                JSONObject parsedState = (JSONObject) new JSONParser().parse(s1.toString());
                jsonRepresentation.put("state", parsedState);
            } catch (ParseException ex) {
                jsonRepresentation.put("state", s1.toString());
            }
        } else {
            jsonRepresentation = null;
        }
    }

    public int getId() {
        return id;
    }

    public float getHValue() {
        return hValue;
    }

    public Integer getParentId() {
        return parentId;
    }

    
    public Object getTransition() {
        return transition;
    }

    

    public void add_descendant(SearchNode desc) {
        if (jsonRepresentation != null) {
            JSONArray descendants = (JSONArray) jsonRepresentation.get("descendants");
            descendants.add(desc.jsonRepresentation);
        }
    }

    public void set_visited(int visit_step) {
        if (jsonRepresentation != null) {
            jsonRepresentation.put("visited", true);
            jsonRepresentation.put("visit_step", visit_step);
        }
    }


    public void printJson(String file_name) {
        try (FileWriter file = new FileWriter(file_name)) {
            file.write(this.jsonRepresentation.toJSONString());
        } catch (IOException ex) {
            Logger.getLogger(SearchNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Successfully Copied JSON Object to File...");
    }

    public float getF() {
        return f;
    }

    public float getG() {
        return gValue;
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

    @Override
    public String toString() {
        return "SearchNode{" + "s=" + s + ", action=" + transition + ", gValue=" + gValue + '}';
    }
}
