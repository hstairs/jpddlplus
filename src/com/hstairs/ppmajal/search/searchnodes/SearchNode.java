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
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author enrico
 */
public class SearchNode extends SimpleSearchNode {

    public final JSONObject jsonRepresentation;
    public float f;
    public int waitingPoints;
    public Object[] helpfulActions;

    public SearchNode (State s1, Object action, SearchNode father, float g, float h) {
        super(s1, action, father, g);
        jsonRepresentation = null;
    }

    public SearchNode (State s1, Object action, SearchNode father, float gValue, float fExt,float hValue, boolean jsonSaving) {
        super(s1, action, father, gValue);
        if (action instanceof  final Integer act){
            this.waitingPoints = act;
            //System.out.println(this.list_of_actions.size());
        }else{
            this.waitingPoints = 0;
        }
        f = fExt;
//        f = this.h * this.wh + this.gValue * this.wg;
        //System.out.println("F:"+f);
        if (jsonSaving) {
            jsonRepresentation = new JSONObject();
            if (action == null) {
                jsonRepresentation.put("action", "init_state");
            } else {
                if (transition == null && waitingPoints == 0) {
                    jsonRepresentation.put("action", "init_state");
                } else if (transition == null) {
                    jsonRepresentation.put("list_of_actions", "waiting");
                } else {
                    jsonRepresentation.put("action", transition.toString());
                }
            }
            jsonRepresentation.put("distance", hValue);
            jsonRepresentation.put("action_cost_to_get_here", gValue);
            if (father == null) {
                jsonRepresentation.put("ancestor", "init_state");
            } else {
                jsonRepresentation.put("ancestor", father.jsonRepresentation.get("visited_step"));
            }
            jsonRepresentation.put("visited", false);
            jsonRepresentation.put("visit_step", -1);
            jsonRepresentation.put("descendants", new JSONArray());
            JSONParser parser = new JSONParser();
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(s1.toString());
                jsonRepresentation.put("state", json);
            } catch (ParseException ex) {
                jsonRepresentation.put("state", s1.toString());
            }

        } else {
            jsonRepresentation = null;
        }

    }

    public SearchNode (State s1, float action_cost_to_get_here, float fExt,float hValue, boolean saving_json) {
        super(s1, 0, null, action_cost_to_get_here);

        f = fExt;
        //System.out.println("F:"+f);
        if (saving_json) {
            jsonRepresentation = new JSONObject();
            if (transition == null) {
                jsonRepresentation.put("action", "init_state");
            } else {
                jsonRepresentation.put("action", transition.toString());
            }
            jsonRepresentation.put("distance", hValue);
            jsonRepresentation.put("action_cost_to_get_here", action_cost_to_get_here);
            if (father == null) {
                jsonRepresentation.put("ancestor", "init_state");
            } else {
                jsonRepresentation.put("ancestor", ((SearchNode) father).jsonRepresentation.get("visited_step"));
            }
            jsonRepresentation.put("visited", false);
            jsonRepresentation.put("visit_step", -1);
            jsonRepresentation.put("descendants", new JSONArray());
            JSONParser parser = new JSONParser();
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(s1.toString());
                jsonRepresentation.put("state", json);
            } catch (ParseException ex) {
                jsonRepresentation.put("state", s1.toString());
            }

        } else {
            jsonRepresentation = null;
        }

    }
//
//    public SearchNode (State s1, ArrayList<Object> list, SearchNode father, float action_cost_to_get_here, float goal_distance, boolean saving_json, float wg, float wh) {
//        super(s1, null, father, action_cost_to_get_here);
//        this.h = goal_distance;
//        this.list_of_actions = list;
//
//        this.wh = wh;
//        this.wg = wg;
//        f = this.h * this.wh + this.gValue * this.wg;
//        //System.out.println("F:"+f);
//        if (saving_json) {
//            json_rep = new JSONObject();
//            if (transition == null && this.list_of_actions == null) {
//                json_rep.put("action", "init_state");
//            } else if (transition == null) {
//                json_rep.put("list_of_actions", "waiting");
//            } else {
//                json_rep.put("action", transition.toString());
//            }
//
//            json_rep.put("distance", goal_distance);
//            json_rep.put("action_cost_to_get_here", action_cost_to_get_here);
//            if (father == null) {
//                json_rep.put("ancestor", "init_state");
//            } else {
//                json_rep.put("ancestor", father.json_rep.get("visited_step"));
//            }
//            json_rep.put("visited", false);
//            json_rep.put("visit_step", -1);
//            json_rep.put("descendants", new JSONArray());
//            JSONParser parser = new JSONParser();
//            JSONObject json;
//            try {
//                json = (JSONObject) parser.parse(s1.toString());
//                json_rep.put("state", json);
//            } catch (ParseException ex) {
//                json_rep.put("state", s1.toString());
//            }
//
//
//        } else {
//            json_rep = null;
//        }
//
//    }


    public void add_descendant (SearchNode desc) {
        JSONArray descendants = (JSONArray) jsonRepresentation.get("descendants");
        descendants.add(desc.jsonRepresentation);
    }

    public void set_visited (int visit_step) {
        jsonRepresentation.put("visited", true);
        jsonRepresentation.put("visit_step", visit_step);
    }

    public void printJson(String file_name) {
        FileWriter file = null;
        try {
            file = new FileWriter(file_name);
            //System.out.println(this.json_rep.toJSONString());
            file.write(this.jsonRepresentation.toJSONString());
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(SearchNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Successfully Copied JSON Object to File...");
    }


    @Override
    public String toString ( ) {
        return "SearchNode{" + "s=" + s + ", action=" + transition  + ", gValue=" + gValue + '}';
    }

}
