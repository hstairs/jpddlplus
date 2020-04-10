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
package com.hstairs.ppmajal.heuristics;

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.ConditionalEffect;
import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.expressions.BinaryOp;
import com.hstairs.ppmajal.expressions.Expression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.heuristics.advanced.h1;
import com.hstairs.ppmajal.problem.*;

import java.util.*;

/**
 * @author enrico
 */
public class Aibr extends Heuristic {

    public boolean conservative = false;
    //    protected ArrayList<Integer> dist;
    public boolean layers_counter;
    private boolean counting_layers = true;
    private boolean greedy_relaxed_plan = false;
    private boolean reversing = false;
    private HashMap<GroundAction, GroundAction> supp_to_action;
    private boolean cost_oriented = true;

    private EPddlProblem subProblem;
    private Collection<GroundAction> supporters;
    
        private RelState reacheable_state;


    public Aibr (EPddlProblem problem) {
        super(problem.getGoals(),problem.actions,problem.getProcessesSet(),problem.getEventsSet(),null,problem);
        this.supp_to_action = new HashMap();
        subProblem = new EPddlProblem();
        supporters = subProblem.actions;
        generate_supporters(A);
        this.problem = problem;
    }


    public void set (boolean counting_layers_active, boolean greedy_relaxed_plan_active) {
        this.counting_layers = counting_layers_active;
        this.greedy_relaxed_plan = greedy_relaxed_plan_active;
    }

    @Override
    public Float setup (State s_0) {
        reachability = true;
        Float ret = computeEstimate(s_0);
        reachability = false;
        return ret;
    }

    public void light_setup (PDDLState s_0, h1 aThis) {
        this.conditionUniverse = aThis.conditionUniverse;

        reachability = false;
        this.reachable = A;

    }

    private LinkedHashSet<GroundAction> filterApplicable (Collection<GroundAction> actions, RelState rs) {
        LinkedHashSet<GroundAction> res = new LinkedHashSet<>();
        for (GroundAction ga : actions) {
            if (ga.isApplicable(rs)) {
                res.add(ga);
            }
        }
        return res;
    }

    @Override
    public Float computeEstimate (State gs) {
        PDDLState s = (PDDLState) gs;
        RelState rs = s.relaxState();
        Collection<GroundAction> temp_supporters = new LinkedHashSet(supporters);//making a copy of the supporters so as not to delete the source
        int supporters_counter = 0;

        int i = 0;
        boolean exit = false;
        while (!exit) {//until  the goal is not satisfied || the procedure has been called in reacheability setting
//            Collection<PDDLGroundAction> S = temp_supporters.stream().filter(p -> p.isApplicable(rs)).collect(Collectors.toSet());//lambda function, Take the applicable action
//            Utils.dbg_print(1, "Relaxed PDDLState:" + rs + "\n");

            if (check_goal_condition(G, rs) && !reachability) {
                break;
            }
            LinkedHashSet<GroundAction> S = get_applicable_supporters(temp_supporters, rs);
//            Utils.dbg_print(1, "Applicable Supporter:" + S + "\n");
            if (S.isEmpty()) {//if there are no applicable actions then finish!
                if (!rs.satisfy(G)) {
                    if (reachability) {
                        reacheable_state = rs.clone();
                        this.reachable = filterApplicable(A, rs);
                    }
//                    get_applicable_supporters(temp_supporters, rs, i);
                    return Float.MAX_VALUE;
                } else {
                    reacheable_state = rs.clone();
                    //this.reachable = new LinkedHashSet(A.stream().filter(p -> p.isApplicable(rs)).collect(Collectors.toList()));
                    break;
                }
            }

            if (reachability) {

//            if (true){
                for (final GroundAction gr : S) {
                    gr.apply(rs);
                }
                //S.stream().forEach((GroundAction a) -> a.apply(rs));
                supporters_counter += S.size();
            } else {
                for (final GroundAction gr : S) {
                    supporters_counter += S.size();
                    gr.apply(rs);
                    if (check_goal_condition(G, rs)) {
                        exit = true;
                        break;
                    }
                }
            }

            i++;
        }

        if (reachability) {
            this.reachable = filterApplicable(A, rs);
            this.supporters = filterApplicable(supporters, rs);
            return (float) i;
        }
        if (layers_counter) {
            return (float) i;
        }
        if (conservative) {
            return (float) supporters_counter;
        }
        if (supporters_counter == 0) {
            return 0f;
        }

        RelState rs2 = s.relaxState();

        return fix_point_computation(s, rs2);

    }

    private void generate_supporters (Collection<GroundAction> actions) {

        Collection<GroundAction> actions_plus_action_for_supporters = new LinkedHashSet();
        for (GroundAction gr : actions) {
            if (gr.cond_effects != null) {
//                System.out.println(gr);
                actions_plus_action_for_supporters.addAll(generate_actions_for_cond_effects(gr.getName(), gr.getParameters(), gr.cond_effects));
            }
        }
        //System.out.println(actions_plus_action_for_supporters);
        actions_plus_action_for_supporters.addAll(actions);
        for (GroundAction gr : actions_plus_action_for_supporters) {
            if (gr.getNumericEffects() != null && !gr.getNumericEffects().sons.isEmpty()) {
                for (NumEffect effect : (Collection<NumEffect>) gr.getNumericEffects().sons) {
                    effect.additive_relaxation = true;
                    if (effect.getOperator().equals("assign") && effect.getRight().getInvolvedNumericFluents().isEmpty()) {
                        supporters.add(generate_constant_supporter(effect, gr.toFileCompliant() + effect.getFluentAffected(), gr.getPreconditions(), gr));
                    } else {
                        supporters.add(generate_plus_inf_supporter(effect, gr.toFileCompliant() + effect.getFluentAffected(), gr.getPreconditions(), gr));
                        supporters.add(generate_minus_inf_supporter(effect, gr.toFileCompliant() + effect.getFluentAffected(), gr.getPreconditions(), gr));
                    }
                }
            }
            if ((gr.getAddList() != null && !gr.getAddList().sons.isEmpty()) || (gr.getDelList() != null && !gr.getDelList().sons.isEmpty())) {
                supporters.add(generate_propositional_action(gr.toFileCompliant() + "prop", gr.getPreconditions(), gr));
            }

        }
//        System.out.println("_");

    }

    private GroundAction generate_constant_supporter (NumEffect effect, String name, Condition precondition, GroundAction gr) {
        GroundAction ret = new GroundAction(name + "constantassign",subProblem.getFreshActionId());
        NumEffect assign = new NumEffect("assign");
        assign.setFluentAffected(effect.getFluentAffected());
        assign.setRight(effect.getRight());
        ret.getNumericEffects().sons.add(assign);
        ret.setPreconditions(ret.getPreconditions().and(precondition));
        this.supp_to_action.put(ret, gr);
        return ret;
    }

    private GroundAction generate_plus_inf_supporter (NumEffect effect, String name, Condition precondition, GroundAction gr) {
        String disequality = "";
        Float asymptote = Float.MAX_VALUE;
        switch (effect.getOperator()) {
            case "increase":
                disequality = ">";
                break;
            case "decrease":
                disequality = "<";
                break;
            case "assign":
                disequality = ">";
                break;
        }
        return generate_supporter(effect, disequality, asymptote, name + "plusinf", precondition, gr);
    }

    private GroundAction generate_supporter (NumEffect effect, String inequality, Float asymptote, String name, Condition precondition, GroundAction gr) {
        GroundAction ret = new GroundAction(name,subProblem.getFreshActionId());
        Comparison indirect_precondition;
        Expression left;
        if (effect.getOperator().equals("assign")) {
            left = new BinaryOp(effect.getRight(), "-", effect.getFluentAffected(), true);
        } else {
            left = effect.getRight();
        }
        
        indirect_precondition = (Comparison) Comparison.createComparison(inequality, left, new PDDLNumber(0),false).normalize();
        ret.getPreconditions().sons.add(indirect_precondition);
        NumEffect eff = new NumEffect("assign");
        eff.setFluentAffected(effect.getFluentAffected());
        eff.setRight(new PDDLNumber(asymptote));
        ret.setPreconditions(ret.getPreconditions().and(precondition));
        ret.getNumericEffects().sons.add(eff);
        this.supp_to_action.put(ret, gr);
        return ret;
    }

    private GroundAction generate_minus_inf_supporter (NumEffect effect, String name, Condition precondition, GroundAction gr) {
        String disequality = "";
        Float asymptote = -Float.MAX_VALUE;
        switch (effect.getOperator()) {
            case "increase":
                disequality = "<";
                break;
            case "decrease":
                disequality = ">";
                break;
            case "assign":
                disequality = "<";
                break;

        }
        return generate_supporter(effect, disequality, asymptote, name + "minusinf", precondition, gr);
    }

    private GroundAction generate_propositional_action (String name, ComplexCondition cond, GroundAction gr) {
        GroundAction ret = new GroundAction(name,subProblem.getFreshActionId());
        ret.setPreconditions(cond);
        ret.setAddList(gr.getAddList());
        ret.setDelList(gr.getDelList());
        this.supp_to_action.put(ret, gr);

        return ret;
    }

    private Float fix_point_computation (PDDLState s, RelState rs2) {
        Float counter = 0f;
        Float layer_counter = 0f;
        while (counter <= horizon) {
            if (debug > 10) {
                System.out.println("L(" + layer_counter + ")Relaxed State: " + rs2);
            }

            boolean fix_point = true;
            layer_counter++;
            for (final GroundAction gr : this.reachable) {
                if (gr.isApplicable(rs2)) {
                    gr.apply_with_generalized_interval_based_relaxation(rs2);
                    if (debug > 10) {
                        System.out.println("L(" + layer_counter + ")Action/Process: " + gr);
                    }
                    //counter++;//=
//                    if (cost_oriented) {
////                        gr.set_unit_cost(s);
//                        counter += gr.getActionCost();
//                    } else {
                        counter++;
//                    }
                    fix_point = false;
                    if (rs2.satisfy(G) && greedy_relaxed_plan) {
                        if (counting_layers) {
                            return layer_counter;
                        } else {
                            return counter;
                        }
                    }
                }
                //                    System.out.println("Reacheability Iteration: "+counter);
            }
            if (!greedy_relaxed_plan) {
                if (rs2.satisfy(G)) {
                    if (counting_layers) {
                        return layer_counter;
                    } else {
                        return counter;
                    }
                }
            }

            if (fix_point) {
                System.out.println("Anomaly!");
                //return Integer.MAX_VALUE;
            }
        }

        return counter;
    }

    //The following is too weak as it only reason qualitatively! Needs to define concept of regression in the interval case.
    private LinkedHashSet<GroundAction> get_applicable_supporters (Collection<GroundAction> temp_supporters, RelState rs) {
        LinkedHashSet<GroundAction> ret = new LinkedHashSet();
        Iterator<GroundAction> it = temp_supporters.iterator();
        while (it.hasNext()) {
            GroundAction gr = it.next();
            if (gr.getPreconditions().isSatisfied(rs)) {
                ret.add(gr);
                it.remove();
            }
        }
        return ret;

    }

    private boolean check_goal_condition (Condition G, RelState rs) {
        return G.isSatisfied(rs);
    }

    private boolean achiever (GroundAction gr, RelState rs2, Condition g) {
        RelState temp = rs2.clone();
        return gr.apply(temp).satisfy(g);

    }

    private Collection<? extends GroundAction> generate_actions_for_cond_effects (String name, ParametersAsTerms p, ComplexCondition cond_effects) {
        Set ret = new LinkedHashSet();
        Integer counter = 0;
        for (Object o : cond_effects.sons) {
            if (o instanceof ConditionalEffect) {
                ConditionalEffect cond = (ConditionalEffect) o;
                GroundAction a = new GroundAction(name + counter+p,subProblem.getFreshActionId());
                a.setParameters(p);
                a.setPreconditions(a.getPreconditions().and(cond.activation_condition));
                a.create_effects_by_cases(cond.effect);
                ret.add(a);
                counter++;
            } else {
                throw new RuntimeException("Error!!");
            }
        }
        return ret;

    }

    public ArrayList<RelState> get_relaxed_reachable_states (PDDLState s, RelState rs2) {
        ArrayList<RelState> ret = new ArrayList<>();
        ret.add(rs2.clone());

        while (true) {
            boolean fix_point = true;
            for (GroundAction gr : this.reachable) {
                if (gr.isApplicable(rs2)) {
                    gr.apply_with_generalized_interval_based_relaxation(rs2);
                    fix_point = false;
                    ret.add(rs2.clone());
                    if (rs2.satisfy(G)) {
                        return ret;
                    }
                }
            }

            if (fix_point) {
                System.out.println("Anomaly!");
            }
        }
    }

    public RelState get_relaxed_goal_in_reachability (PDDLState s) {
        RelState rs = s.relaxState();
        Collection<GroundAction> temp_supporters = new LinkedHashSet(supporters);//making a copy of the supporters so as not to delete the source

        while (true) {
            LinkedHashSet<GroundAction> S = get_applicable_supporters(temp_supporters, rs);
            if (S.isEmpty()) {
                return rs;
            }

            for (GroundAction gr : S) {
                gr.apply(rs);
            }
            //S.stream().forEach((GroundAction a) -> a.apply(rs));
        }
//        return rs;
    }
}
