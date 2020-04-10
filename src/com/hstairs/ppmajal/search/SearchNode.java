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
package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author enrico
 */
public class SearchNode extends SimpleSearchNode {

    public float h_n;
    public JSONObject json_rep;
    public int reacheable_condition;
    public float wg;
    public float wh;
    public float f;
    public ArrayList<Object> list_of_actions;
    public Collection<GroundAction> helpfulActions;
    private boolean bfs = true;

    public SearchNode (State s1, Object action, SearchNode father, float action_cost_to_get_here, float goal_distance) {
        super(s1, action, father, action_cost_to_get_here);
        this.h_n = goal_distance;
        json_rep = null;
        reacheable_condition = 0;
        wh = 1f;
        wg = 1f;
    }

    public SearchNode (State s1, Object action, SearchNode father, float action_cost_to_get_here, float goal_distance, boolean saving_json, float wg, float wh) {
        super(s1, action, father, action_cost_to_get_here);
        this.h_n = goal_distance;
        reacheable_condition = 0;

        this.wh = wh;
        this.wg = wg;
        f = this.h_n * this.wh + this.gValue * this.wg;
        //System.out.println("F:"+f);
        if (saving_json) {
            json_rep = new JSONObject();
            if (action == null) {
                json_rep.put("action", "init_state");
            } else {
                if (action instanceof GroundAction){
                    json_rep.put("action", ((GroundAction) action).toPDDL());
                }else{
                    json_rep.put("action", "<waiting>");
                }
            }
            json_rep.put("distance", goal_distance);
            json_rep.put("action_cost_to_get_here", action_cost_to_get_here);
            if (father == null) {
                json_rep.put("ancestor", "init_state");
            } else {
                json_rep.put("ancestor", father.json_rep.get("visited_step"));
            }
            json_rep.put("visited", false);
            json_rep.put("visit_step", -1);
            json_rep.put("descendants", new JSONArray());
            JSONParser parser = new JSONParser();
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(s1.toString());
                json_rep.put("state", json);
            } catch (ParseException ex) {
                json_rep.put("state", s1.toString());
            }

        } else {
            json_rep = null;
        }

    }

    public SearchNode (State s1, float action_cost_to_get_here, float goal_distance, boolean saving_json, float wg, float wh) {
        super(s1, null, null, action_cost_to_get_here);
        this.h_n = goal_distance;
        reacheable_condition = 0;

        this.wh = wh;
        this.wg = wg;
        f = this.h_n * this.wh + this.gValue * this.wg;
        //System.out.println("F:"+f);
        if (saving_json) {
            json_rep = new JSONObject();
            if (transition == null) {
                json_rep.put("action", "init_state");
            } else {
                json_rep.put("action", transition.toString());
            }
            json_rep.put("distance", goal_distance);
            json_rep.put("action_cost_to_get_here", action_cost_to_get_here);
            if (father == null) {
                json_rep.put("ancestor", "init_state");
            } else {
                json_rep.put("ancestor", ((SearchNode) father).json_rep.get("visited_step"));
            }
            json_rep.put("visited", false);
            json_rep.put("visit_step", -1);
            json_rep.put("descendants", new JSONArray());
            JSONParser parser = new JSONParser();
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(s1.toString());
                json_rep.put("state", json);
            } catch (ParseException ex) {
                json_rep.put("state", s1.toString());
            }

        } else {
            json_rep = null;
        }

    }

    public SearchNode (State s1, ArrayList<Object> list, SearchNode father, float action_cost_to_get_here, float goal_distance, boolean saving_json, float wg, float wh) {
        super(s1, null, father, action_cost_to_get_here);
        this.h_n = goal_distance;
        this.list_of_actions = list;
        reacheable_condition = 0;

        this.wh = wh;
        this.wg = wg;
        f = this.h_n * this.wh + this.gValue * this.wg;
        //System.out.println("F:"+f);
        if (saving_json) {
            json_rep = new JSONObject();
            if (transition == null && this.list_of_actions == null) {
                json_rep.put("action", "init_state");
            } else if (transition == null) {
                json_rep.put("list_of_actions", "waiting");
            } else {
                json_rep.put("action", transition.toString());
            }

            json_rep.put("distance", goal_distance);
            json_rep.put("action_cost_to_get_here", action_cost_to_get_here);
            if (father == null) {
                json_rep.put("ancestor", "init_state");
            } else {
                json_rep.put("ancestor", father.json_rep.get("visited_step"));
            }
            json_rep.put("visited", false);
            json_rep.put("visit_step", -1);
            json_rep.put("descendants", new JSONArray());
            JSONParser parser = new JSONParser();
            JSONObject json;
            try {
                json = (JSONObject) parser.parse(s1.toString());
                json_rep.put("state", json);
            } catch (ParseException ex) {
                json_rep.put("state", s1.toString());
            }


        } else {
            json_rep = null;
        }

    }

    public SearchNode (State s1, Object action, SearchNode father, float action_cost_to_get_here, int goal_distance, boolean saving_json, int reacheable_conditions) {
        super(s1, action, father, action_cost_to_get_here);
        this.h_n = goal_distance;

        if (saving_json) {
            json_rep = new JSONObject();
            if (action == null) {
                json_rep.put("action", "init_state");
            } else {
                json_rep.put("action", action.toString());
            }
            json_rep.put("distance", goal_distance);
            json_rep.put("action_cost_to_get_here", action_cost_to_get_here);
            if (father == null) {
                json_rep.put("ancestor", "init_state");
            } else {
                json_rep.put("ancestor", father.json_rep.get("visited_step"));
            }
            json_rep.put("visited", false);
            json_rep.put("visit_step", -1);
            json_rep.put("descendants", new JSONArray());
        } else {
            json_rep = null;
        }

    }

    public void add_descendant (SearchNode desc) {
        JSONArray descendants = (JSONArray) json_rep.get("descendants");
        descendants.add(desc.json_rep);
    }

    public void set_visited (int visit_step) {
        json_rep.put("visited", true);
        json_rep.put("visit_step", visit_step);
    }

    public void print_json (String file_name) {
        FileWriter file = null;
        try {
            file = new FileWriter(file_name);
            //System.out.println(this.json_rep.toJSONString());
            file.write(this.json_rep.toJSONString());
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(SearchNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Successfully Copied JSON Object to File...");
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SearchNode other = (SearchNode) obj;

        return this.s.equals(other.s);
    }

    @Override
    public int hashCode ( ) {
        int hash = 5;
        hash = 29 * hash + (this.s != null ? this.s.hashCode() : 0);
//        hash = 29 * hash + (this.action != null ? this.action.hashCode() : 0);
//        hash = 29 * hash + (int) this.h_n;
//        hash = 29 * hash + (this.father != null ? this.father.hashCode() : 0);
//        hash = 29 * hash + (int) this.gValue;
        return hash;
    }

    @Override
    public String toString ( ) {
        return "SearchNode{" + "s=" + s + ", action=" + transition + ", h_n=" + h_n + ", gValue=" + gValue + '}';
    }

}
