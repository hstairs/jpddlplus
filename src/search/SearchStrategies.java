/**
 * *******************************************************************
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 ********************************************************************
 */
/**
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 *
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 *
 ********************************************************************
 */
package search;

import computation.NumericPlanningGraph;
import conditions.AndCond;
import conditions.Conditions;
import conditions.Predicate;
import expressions.NumEffect;
import expressions.NumFluent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import problem.EPddlProblem;
import problem.GroundAction;
import problem.GroundProcess;
import problem.PddlProblem;
import problem.State;

/**
 *
 * @author enrico
 */
public class SearchStrategies {

    private float hw = 4;
    public static int priority_queue_size;
    private boolean checking_visited = true;
    private Heuristics heuristic;
    private boolean decreasing_heuristic_pruning = false;
    private float gw;
    public static int states_evaluated;
    private boolean interactive_search_debug = false;
    public boolean json_rep_saving = false;
    public SearchNode search_space_handle;
    private boolean high_verbosity = false;
    public boolean preferred_operators_active = false;
    public boolean processes = false;
    public float delta;
    public int horizon = 10000000;
    public static int num_dead_end_detected;
    public static int number_duplicates;

    public boolean breakties_on_larger_g = false;
    public boolean breakties_on_smaller_g = false;

    public boolean bfs = true;
    static public int node_reopened;
    public boolean cost_optimal;
    public Conditions goal;
            HashMap<State, Float> g = new HashMap();

    private boolean can_reopen_nodes = true;

    public class FrontierOrder implements Comparator<SearchNode> {
        Heuristics h;
        public FrontierOrder(Heuristics h){
            super();
            this.h = h;
        }
        public FrontierOrder(){
            super();
            this.h = null;
        }
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            final SearchNode other = (SearchNode) o2;
            final SearchNode a = o1;
            if (a.f == other.f) {
//                if (h!=null){
//                    
//                    this.h.quasi_integer_actions = true;
////                    this.h.additive_h = true;
////                    this.h.greedy =true;
//                    Float sec_a = this.h.compute_estimate(a.s)*a.wh+a.g_n*a.wg;
//                    Float sec_b = this.h.compute_estimate(other.s)*a.wh+other.g_n*a.wg;
//                    this.h.quasi_integer_actions = false;
////                    this.h.greedy = false;                   
//                    
//
//                    if (sec_a < sec_b){
////                        System.out.println("Weight configuration: g_n"+a.g_n + " gw:"+a.wg );
////                        System.out.println("tie broken");
//                        return -1;
//                    }
//                    if (sec_a > sec_b){
////                        System.out.println("tie broken");
//                        return 1;
//                    }
//                }
                if (breakties_on_larger_g) {
//                System.out.println(this.g_n);
                    if (a.g_n < other.g_n)//goal is farer
                    {
                        return +1;
                    } else if (a.g_n > other.g_n) //goal is closer
                    {
                        return -1;
                    } else {
                        return 0;
                    }
                } else if (breakties_on_smaller_g) {
                    if (a.g_n < other.g_n)//goal is farer
                    {
                        return -1;
                    } else if (a.g_n > other.g_n) //goal is closer
                    {
                        return +1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            }
            if (bfs) {
                if (a.f <= other.f) {
                    return -1;
                } else {
                    return 1;
                }
            } else//dfs
            {
                if (a.f <= other.f) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

    }

    public void setup_heuristic(Heuristics input) {
        this.setHeuristic(input);
        setGw(0);
        setHw(1);
        //horizon = Float.MAX_VALUE;
        setDecreasing_heuristic_pruning(false);
    }

    public LinkedList enforced_hill_climbing(PddlProblem problem) throws Exception {
        long start_global = System.currentTimeMillis();
        LinkedHashSet rel_actions;

        getHeuristic().setup(problem.getInit());
        getHeuristic().preferred_operators = this.preferred_operators_active;

        State current = problem.getInit();

        LinkedList plan = new LinkedList();
        //a = new LinkedHashSet(np.compute_relevant_actions(problem.getInit(), problem.getActions()));
        //System.out.println("Goals:"+problem.getGoals());
        rel_actions = getHeuristic().reachable;
        System.out.println("All Actions:" + rel_actions.size());
        states_evaluated = 0;
        while (!current.satisfy(problem.getGoals())) {
            SearchNode succ = breadth_first_search(current, problem.getGoals(), rel_actions, getHeuristic(), (EPddlProblem) problem);
            if (succ == null) {
                System.out.println("No plan exists with EHC");
                return null;
            }
            //System.out.println("");
            current = succ.s;
            plan.addAll(add_actions(succ));
            //System.out.println(plan);
        }
        overall_search_time = System.currentTimeMillis() - start_global;
        return plan;

    }

    public static int nodes_expanded = 0;

    public SearchNode breadth_first_search(State current, Conditions goals, HashSet actions, Heuristics heuristic, EPddlProblem problem) throws Exception {
        HashMap<State, Boolean> visited = new HashMap();
        //System.out.println("Visited size:"+visited.size());
        
        PriorityQueue<SearchNode> frontier = new PriorityQueue(new FrontierOrder());
        boolean strong_relaxation = false;
        //int current_value = Heuristics.h1_recursion_memoization(current, goals,  actions, new HashMap(), 0, false,null,predAchievers);
        Float current_value = heuristic.compute_estimate(current);

        LinkedHashSet<GroundAction> branching_actions;
        if (this.preferred_operators_active) {
            branching_actions = heuristic.relaxed_plan_actions;
        } else {
            branching_actions = (LinkedHashSet<GroundAction>) actions;
        }

        setStates_evaluated(getStates_evaluated() + 1);

        SearchNode init = new SearchNode(current, null, null, 0, current_value);
        frontier.add(init);

        while (!frontier.isEmpty()) {
            SearchNode node = frontier.poll();
            nodes_expanded++;
            visited.put(node.s, true);
            if (processes) {
                advance_time(frontier, node, (EPddlProblem) problem);
            }
            for (GroundAction act : (LinkedHashSet<GroundAction>) branching_actions) {
                if (act instanceof GroundProcess)
                    continue;
                if (act.isApplicable(node.s)) {
                    State temp = act.apply(node.s.clone());
                    //act.normalize();
                    if (!temp.satisfy(problem.globalConstraints)) {
                        continue;
                    }
                    if (visited.get(temp) == null) {
                        long start = System.currentTimeMillis();
                        Float d = heuristic.compute_estimate(temp);
                        heuristic_time += System.currentTimeMillis() - start;
                        //System.out.println("try");
                        setStates_evaluated(getStates_evaluated() + 1);
                        if (d != Float.MAX_VALUE) {// && d <= current_value) {
                            SearchNode new_node = new SearchNode(temp, act, node, node.g_n + 1, 0);
                            frontier.add(new_node);
                            if (d < current_value) {
                                nodes_expanded++;
                                System.out.println("Distance:" + d);
                                return new_node;
                            }
                        } else {
                            num_dead_end_detected++;
                        }
                    }
                }
            }
        }
        return null;

    }

    public static long heuristic_time;
    public static long overall_search_time;

    public LinkedList wa_star(EPddlProblem problem) throws Exception {
        num_dead_end_detected = 0;

        long start_global = System.currentTimeMillis();
        PriorityQueue<SearchNode> frontier = new PriorityQueue(new FrontierOrder());
        State current = (State) problem.getInit();
        //problem.generateActions();

        LinkedHashSet rel_actions = new LinkedHashSet(problem.getActions());
        //LinkedHashSet a = new LinkedHashSet(np.compute_relevant_actions(problem.getInit().clone(), problem.getActions()));

        getHeuristic().setup(current);
        System.out.println("After Reacheability Actions:" + getHeuristic().reachable.size());
        Float current_value = getHeuristic().compute_estimate(current);
        Float init_estimate = current_value;
        //int current_value = Heuristics.h1_recursion_memoization(current, problem.getGoals(),  problem.getActions(), new HashMap(), 0, false,null,null)*hw;
        System.out.println("H(s_0,G)=:" + current_value);
        if (current_value == Float.MAX_VALUE) {
            num_dead_end_detected++;
            return null;
        }
        SearchNode init = new SearchNode((State) problem.getInit().clone(), null, null, 0, current_value, this.json_rep_saving, this.gw, this.hw);
        if (json_rep_saving) {
            search_space_handle = init;
        }
        frontier.add(init);
        HashMap<State, Boolean> visited = new HashMap();
        heuristic_time = 0;
        number_duplicates = 0;
        long previous_check = 0;
        node_reopened = 0;
        float current_g = 0f;
        g.put(problem.getInit(), 0f);
        while (!frontier.isEmpty()) {
            SearchNode current_node = frontier.poll();
            if (current_node.g_n >= horizon) {
                overall_search_time = System.currentTimeMillis() - start_global;
                continue;
            }
            if (json_rep_saving) {
                current_node.set_visited(nodes_expanded);
            }

            if (current_node.g_n <= g.get(current_node.s)) {

                nodes_expanded++;
                priority_queue_size = frontier.size();
                //System.out.println("Current Distance:"+current_node.g_n);
                //System.out.println("Current Cost:"+current_node.g_n);
                if (current_value > current_node.h_n || current_g < current_node.g_n) {
                    System.out.println(" g(n)= " + current_node.g_n + " h(n):" + current_node.h_n);
                    current_value = current_node.h_n;
                    current_g = current_node.g_n;
//                if (current_value == 1){
//                    System.out.println(current_node.s);
//                }
                }
                if (nodes_expanded % 10000 == 0 || ((System.currentTimeMillis() - start_global) - previous_check) > 10000) {
                    System.out.println("Stats so far. Expanded nodes:" + nodes_expanded + " States Evaluated:" + this.getStates_evaluated());
                    previous_check = (System.currentTimeMillis() - start_global);
                }
//            if (current_node.action!= null)
//                System.out.println("Action:"+current_node.action);
                if (current_node.s.satisfy(problem.getGoals())) {
                    overall_search_time = System.currentTimeMillis() - start_global;
                    return extract_plan(current_node);
                }
                if (processes) {
                    advance_time(frontier, current_node, problem);
                }

                visited.put(current_node.s, Boolean.TRUE);
                for (GroundAction act : getHeuristic().reachable) {
                    if (act instanceof GroundProcess) {
                        continue;
                    }
                    if (act.isApplicable(current_node.s)) {
                        State temp = act.apply(current_node.s.clone());
                        //act.normalize();
                        act.setAction_cost(temp);

                        if (!temp.satisfy(problem.globalConstraints)) {
                            continue;
                        }
                        boolean to_visit = true;
                        if (visited.get(temp) != null) {
                            if (!can_reopen_nodes) {
                                to_visit = false;
                            } else if (g.get(temp) != null) {
                                if (g.get(temp) <= current_node.g_n + act.getAction_cost()) {

                                    to_visit = false;
                                } else {
                                    node_reopened++;
                                    //                                System.out.println("Previous Value:"+g.get(temp));
                                    //                                System.out.println("New Value:"+(current_node.g_n+act.getAction_cost()));

                                }
                            }
                        }

                        if (to_visit) {
                            g.put(temp, current_node.g_n + act.getAction_cost());
                            setStates_evaluated(getStates_evaluated() + 1);

                            long start = System.currentTimeMillis();
                            Float d;
//                        if (temp.satisfy(problem.getGoals())){
//                            d = 0f;
////                            System.out.println("goal found at depth:"+(current_node.g_n+1));
////                            if (current_node.g_n+1 <= init_estimate){
////                                System.out.println("This is provably the best path");
////                                overall_search_time = System.currentTimeMillis() - start_global;
////                                return extract_plan(current_node);
////                            }
//                        }else
                            d = getHeuristic().compute_estimate(temp);
                            heuristic_time += System.currentTimeMillis() - start;
                            //System.out.print(d+" ");
                            if (d != Float.MAX_VALUE && (!this.isDecreasing_heuristic_pruning() || d <= current_value)) {
//                        if (d!=Float.MAX_VALUE && ( d <= current_value ) ){
                                SearchNode new_node = new SearchNode(temp, act, current_node, current_node.g_n + act.getAction_cost(), d, this.json_rep_saving, this.gw, this.hw);
                                //SearchNode new_node = new SearchNode(temp,act,current_node,1,d*hw);
                                if (json_rep_saving) {
                                    current_node.add_descendant(new_node);
                                }
                                frontier.add(new_node);
//                            if (new_node.s.satisfy(problem.getGoals()) && )
//                              return extract_plan(new_node);
                            } else {
                                num_dead_end_detected++;
                            }
                        } else {
                            number_duplicates++;
                        }
                    }
                }
            }
        }

        return null;
    }

    public LinkedList blindSearch(EPddlProblem problem) throws Exception {

        long start_global = System.currentTimeMillis();
        PriorityQueue<SearchNode> frontier = new PriorityQueue(new FrontierOrder());
        State current = (State) problem.getInit();
        //problem.generateActions();
        //LinkedHashSet a = new LinkedHashSet(np.compute_relevant_actions(problem.getInit().clone(), problem.getActions()));

        getHeuristic().setup(current);
        System.out.println("After Reacheability Actions:" + getHeuristic().reachable.size());
        Float current_value = 0f;
        SearchNode init = new SearchNode((State) problem.getInit().clone(), null, null, 0, current_value, this.json_rep_saving, this.gw, this.hw);
        if (json_rep_saving) {
            search_space_handle = init;
        }
        frontier.add(init);
        HashMap<State, Boolean> visited = new HashMap();
        HashMap<State, Float> cost = new HashMap();
        heuristic_time = 0;
        while (!frontier.isEmpty()) {
            SearchNode current_node = frontier.poll();
            if (json_rep_saving) {
                current_node.set_visited(nodes_expanded);
            }

            nodes_expanded++;
            priority_queue_size = frontier.size();
            //System.out.println("Current Distance:"+current_node.g_n);
            //System.out.println("Current Cost:"+current_node.g_n);
            if (current_value > current_node.h_n) {
                System.out.println("Current Distance:" + current_node.h_n);

                current_value = current_node.h_n;
            }
//            if (current_node.action!= null)
//                System.out.println("Action:"+current_node.action);
            if (current_node.s.satisfy(problem.getGoals())) {
                overall_search_time = System.currentTimeMillis() - start_global;
                return extract_plan(current_node);
            }

            if (current_node.g_n >= horizon) {
                overall_search_time = System.currentTimeMillis() - start_global;
                continue;
            }

            visited.put(current_node.s, Boolean.TRUE);
            if (processes) {
                advance_time(frontier, current_node, problem);
            }
            for (GroundAction act : getHeuristic().reachable) {
                if (act instanceof GroundProcess) {
                } else if (act.isApplicable(current_node.s)) {
                    State temp = act.apply(current_node.s.clone());
                    //act.normalize();
                    if (!temp.satisfy(problem.globalConstraints)) {
                        continue;
                    }

                    if (visited.get(temp) != null) {
                        if (cost.get(temp) != null) {
                            if (!(cost.get(temp) > current_node.g_n + 1)) {
                                continue;
                            }
                        }
                    }

                    cost.put(temp, current_node.g_n + 1);
                    setStates_evaluated(getStates_evaluated() + 1);

                    act.setAction_cost(temp);
                    SearchNode new_node = new SearchNode(temp, act, current_node, current_node.g_n + act.getAction_cost(), 0, this.json_rep_saving, this.gw, 0);
                    //SearchNode new_node = new SearchNode(temp,act,current_node,1,d*hw);
                    if (json_rep_saving) {
                        current_node.add_descendant(new_node);
                    }
                    frontier.add(new_node);
//                            if (new_node.s.satisfy(problem.getGoals()))
//                              return extract_plan(new_node);

                }
            }
        }

        return null;
    }

    public LinkedList greedy_best_first_search2(EPddlProblem problem) throws Exception {
        this.can_reopen_nodes = false;
        return this.wa_star(problem);
    }
    
    public LinkedList greedy_best_first_search(EPddlProblem problem) throws Exception {
         long start_global = System.currentTimeMillis();

        num_dead_end_detected = 0;
        //Frontier
        PriorityQueue<SearchNode> frontier = new PriorityQueue(new FrontierOrder());
        State current = (State) problem.getInit();
        if (!current.satisfy(problem.globalConstraints)) {
            return null;
        }
        getHeuristic().setup(current);
        //getHeuristic().horizon = this.horizon;
        System.out.println("|A U P| (After Relevance Analysis):" + getHeuristic().reachable.size());
        if (this.high_verbosity) {
            System.out.println("Ground Actions:" + getHeuristic().reachable);
        }

//        rel_actions = (LinkedHashSet) heuristic.compute_relevant_actions(current, problem.getGoals(), rel_actions);
        //heuristic.build_integer_representation(rel_actions, problem.getGoals());
        System.out.println("Computing Heuristic...");
        long start = System.currentTimeMillis();
        Float current_value = getHeuristic().compute_estimate(current);
        setStates_evaluated(0);
        System.out.println("Time(H):" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("H(s_0,G):" + current_value);

        if (current_value == Float.MAX_VALUE) {
            overall_search_time = System.currentTimeMillis() - start_global;
            num_dead_end_detected++;
            return null;
        }
        SearchNode init = new SearchNode((State) problem.getInit(), null, null, 0, current_value, json_rep_saving, this.gw, this.hw);
        if (json_rep_saving) {
            search_space_handle = init;
        }
        frontier.add(init);

        HashMap<State, Boolean> visited = new HashMap();
//        HashMap<State,Integer> cost = new HashMap();
        long previous_check = 0;
        heuristic_time = 0;
        int time = 0;
        System.out.println("Starting GBFS");
        while (!frontier.isEmpty()) {
            SearchNode current_node = frontier.poll();
            if (json_rep_saving) {
                current_node.set_visited(nodes_expanded);
            }

            nodes_expanded++;
            priority_queue_size = frontier.size();
            if (current_node.action != null && interactive_search_debug) {
                System.out.println("Action:" + current_node.action);
                System.out.println("Current Distance:" + current_node.h_n);
                System.out.println("Current Frontier:");
//                for (SearchNode sn : frontier) {
//                    System.out.println("Node:" + sn.action + " Distance to Goal:" + sn.h_n);
//                }
                System.in.read();
            }
            if (current_value > current_node.h_n) {
                System.out.println("Current Distance:" + current_node.h_n + " G(n)= " + current_node.g_n);
                current_value = current_node.h_n;
            }
            if (current_node.s.satisfy(problem.getGoals())) {
                overall_search_time = System.currentTimeMillis() - start_global;
                return extract_plan(current_node);
            }

            if (nodes_expanded % 10000 == 0 || ((System.currentTimeMillis() - start_global) - previous_check) > 10000) {
                System.out.println("Stats so far. Expanded nodes:" + nodes_expanded + " States Evaluated:" + this.getStates_evaluated());
                previous_check = (System.currentTimeMillis() - start_global);
            }
            if (current_node.g_n >= horizon) {
                overall_search_time = System.currentTimeMillis() - start_global;
                continue;
            }

            if (checking_visited) {
                visited.put(current_node.s, Boolean.TRUE);
            }
            if (processes) {
                advance_time(frontier, current_node, problem);
            }
            for (GroundAction act : getHeuristic().reachable) {
                if (act instanceof GroundProcess) {
                } else if (act.isApplicable(current_node.s)) {
                    State temp = act.apply(current_node.s.clone());
                    //if (!checking_visited || visited.get(temp) == null){
                    if (visited.get(temp) == null && temp.satisfy(problem.globalConstraints)) {
                        setStates_evaluated(getStates_evaluated() + 1);
                        start = System.currentTimeMillis();
                        Float d = getHeuristic().compute_estimate(temp);
                        heuristic_time += System.currentTimeMillis() - start;
                        //System.out.print("Reacheable Conditions:"+reacheable_conditions);
                        act.setAction_cost(temp);

                        if (d != Float.MAX_VALUE && (current_node.g_n + act.getAction_cost() + d <= horizon)) {  // && (!this.isDecreasing_heuristic_pruning() || d <= current_value)) {

                            SearchNode new_node = new SearchNode(temp, act, current_node, (current_node.g_n + act.getAction_cost()), d, json_rep_saving, this.gw, this.hw);
                            frontier.add(new_node);
                            //frontier.add(new_node);  //this can be substituted by looking whether the element was already present. In that case its weight has to be updated
                            if (json_rep_saving) {
                                current_node.add_descendant(new_node);
                            }

                        } else {
                            num_dead_end_detected++;
                        }
                    }
                }
            }
        }

        return null;
    
    }

    private static LinkedList add_actions(SearchNode c) throws CloneNotSupportedException {
        LinkedList temp = new LinkedList();
        while (c.father != null) {
            GroundAction gr = null;

            if (c.action instanceof GroundProcess) {
                gr = (GroundProcess) c.action.clone();
            } else {
                gr = (GroundAction) c.action.clone();
            }
//
            if (c.father.s.functionValue(new NumFluent("time_elapsed")) != null) {
                gr.time = c.father.s.functionValue(new NumFluent("time_elapsed")).getNumber();
            } else {
                gr.time = 0f;
            }
            if (c.action instanceof GroundProcess) {
                gr.setName("--------->waiting");
            }//else{
            temp.addFirst(gr);
            //}
            c = c.father;
        }
        return temp;
    }

    private static LinkedList extract_plan(SearchNode c) {
        LinkedList plan = new LinkedList();
        while (c.action != null) {
            try {

                GroundAction gr = null;

                if (c.action instanceof GroundProcess) {
                    gr = (GroundProcess) c.action.clone();
                } else {
                    gr = (GroundAction) c.action.clone();
                }

                if (c.father.s.functionValue(new NumFluent("time_elapsed")) != null) {
                    gr.time = c.father.s.functionValue(new NumFluent("time_elapsed")).getNumber();
                } else {
                    gr.time = 0f;
                }
                if (c.action instanceof GroundProcess) {
                    gr.setName("--------->waiting");
                }//else{
                plan.addFirst(gr);
                //}
                c = c.father;
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(SearchStrategies.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return plan;
    }

    /**
     * @return the gw
     */
    public float getGw() {
        return gw;
    }

    /**
     * @param gw the gw to set
     */
    public void setGw(float gw) {
        this.gw = gw;
    }

    /**
     * @return the hw
     */
    public float getHw() {
        return hw;
    }

    /**
     * @param hw the hw to set
     */
    public void setHw(float hw) {
        this.hw = hw;
    }

    /**
     * @return the decreasing_heuristic_pruning
     */
    public boolean isDecreasing_heuristic_pruning() {
        return decreasing_heuristic_pruning;
    }

    /**
     * @param decreasing_heuristic_pruning the decreasing_heuristic_pruning to
     * set
     */
    public void setDecreasing_heuristic_pruning(boolean decreasing_heuristic_pruning) {
        this.decreasing_heuristic_pruning = decreasing_heuristic_pruning;
    }

    /**
     * @return the heuristic
     */
    public Heuristics getHeuristic() {
        return heuristic;
    }

    /**
     * @param heuristic the heuristic to set
     */
    public void setHeuristic(Heuristics heuristic) {
        this.heuristic = heuristic;
    }

    /**
     * @return the states_evaluated
     */
    public int getStates_evaluated() {
        return states_evaluated;
    }

    /**
     * @param states_evaluated the states_evaluated to set
     */
    public void setStates_evaluated(int states_evaluated) {
        this.states_evaluated = states_evaluated;
    }

    private void advance_time(PriorityQueue<SearchNode> frontier, SearchNode current_node, EPddlProblem problem) throws Exception {
        try {
            GroundProcess waiting = new GroundProcess("waiting");
            waiting.setNumericEffects(new AndCond());
            waiting.setPreconditions(new AndCond());
            waiting.add_time_effects(delta);
            for (GroundAction act : getHeuristic().reachable) {

                if (act instanceof GroundProcess) {
                    GroundProcess gp = (GroundProcess) act;

                    if (gp.isActive(current_node.s)) {
                        AndCond precondition = (AndCond) waiting.getPreconditions();
                        precondition.addConditions(gp.getPreconditions());
                        for (NumEffect eff : gp.getNumericEffectsAsCollection()) {
                            waiting.add_numeric_effect(eff);
                        }
                        waiting.setPreconditions(precondition);
                    }
                }
            }

            State temp = waiting.apply(current_node.s.clone());
            if (temp.satisfy(problem.globalConstraints)) {
                g.put(temp, current_node.g_n + 1);

                setStates_evaluated(getStates_evaluated() + 1);
                long start = System.currentTimeMillis();
                Float d = 0f;
                if (this.getHw() != 0) {
                    d = getHeuristic().compute_estimate(temp);
                }
                heuristic_time += System.currentTimeMillis() - start;
                //System.out.print("Reacheable Conditions:"+reacheable_conditions);
                if (d != Float.MAX_VALUE) {
                    waiting.setAction_cost(temp);
                    SearchNode new_node = new SearchNode(temp, waiting, current_node, (current_node.g_n + waiting.getAction_cost()), d, this.json_rep_saving, this.gw, this.hw);
                    //SearchNode new_node = new SearchNode(temp, waiting, current_node, 0, d * getHw(), this.json_rep_saving);

                    frontier.add(new_node);
                    //frontier.add(new_node);  //this can be substituted by looking whether the element was already present. In that case its weight has to be updated
                    if (json_rep_saving) {
                        current_node.add_descendant(new_node);
                    }

                } else {
                    num_dead_end_detected++;
                }
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SearchStrategies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private float get_cost(float g_n, GroundAction act) {
        if (cost_optimal) {
            return g_n + act.getAction_cost();
        }
        return g_n + 1;
    }

}