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
package com.hstairs.ppmajal.heuristics.utils;

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Predicate;
import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.PddlProblem;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

import java.util.*;

/**
 * @author enrico
 */
public final class ojalgo_interface extends LpInterface {

    public HashMap<Integer, Collection<GroundAction>> affectors_of;
    public HashMap<Integer, Collection<GroundAction>> affectors_of_temp;

    public HashMap<Integer, ExpressionsBasedModel> cond_lp_formulation;
    public HashMap<Condition, Collection<GroundAction>> pos_affectors_of;
    public HashMap<Integer, Variable> action_to_variable;
    public ArrayList<Boolean> first_time;
    public HashMap<Variable, Collection<Constraint>> var_to_expr;

    public ExpressionsBasedModel lp;

    public ojalgo_interface (PddlProblem problem, ComplexCondition cond, ComplexCondition global_constraint) {
        super(cond, global_constraint,problem);
        n_invocations = 0;
        integer_variables = false;
        additive_h = false;
        lp = new ExpressionsBasedModel();
//        ExpressionsBasedModel.addIntegration(SolverCPLEX.INTEGRATION);

    }

    @Override
    public void initialize (Collection<GroundAction> actions, PDDLState s_0) {
        this.cond_lp_formulation = new HashMap();
        this.var_to_expr = new HashMap();
        this.affectors_of = new HashMap();
        pos_affectors_of = new HashMap();

        //first_time.set(c.getCounter(),true);
        this.init_condition(actions, s_0);

    }

    @Override
    public void update_conditions_bound_plus_reset_variables (PDDLState s_0) {
        this.update_local_global_conditions(s_0);
        for (Variable v : lp.getVariables()) {
            v.upper(0);
        }
        affectors_of_temp = new HashMap();
        for (Integer i : this.affectors_of.keySet()) {
            affectors_of_temp.put(i, new LinkedHashSet());
            affectors_of_temp.get(i).addAll(this.affectors_of.get(i));
        }
        return;
    }

    @Override
    protected void update_local_global_conditions (PDDLState s_0) {

        update_condition(s_0, c);
        if (this.gc != null) {
            update_condition(s_0, gc);
        }

    }

    @Override
    public float update_cost (PDDLState s_0, ArrayList<Boolean> active_actions, ArrayList<Float> h) {

        Collection<GroundAction> affectors = this.affectors_of_temp.get(c.getHeuristicId());
        Iterator<GroundAction> it = affectors.iterator();
        while (it.hasNext()) {
            GroundAction gr = it.next();
            if (active_actions.get(gr.getId())) {
                Variable v = this.action_to_variable.get(gr.getId());
                v.upper(Integer.MAX_VALUE);
                it.remove();

            }
        }

        Float precondition_contribution = Float.MAX_VALUE;
        if (this.additive_h) {
            precondition_contribution = 0f;
        }
        for (Condition c_0 : (Collection<Condition>) c.sons) {
            Float local_min = Float.MAX_VALUE;
            if (s_0.satisfy(c_0)) {
                if (!this.additive_h) {
                    precondition_contribution = 0f;
                    break;
                }
                local_min = 0f;
            } else {
                for (GroundAction gr : this.pos_affectors_of.get(c_0)) {
                    local_min = Math.min(h.get(gr.getPreconditions().getHeuristicId()), local_min);
                }
            }
            if (this.additive_h) {
                precondition_contribution += local_min;
            } else {
                precondition_contribution = Math.min(precondition_contribution, local_min);
            }
        }
        if (precondition_contribution == Float.MAX_VALUE) {
            precondition_contribution = 0f;
        }
        n_invocations++;

//        System.out.println(lp);
        Optimisation.Result tmpResult = lp.copy().minimise();
//        System.out.println(lp);

        if (tmpResult.getState().isFeasible()) {
            return (float) tmpResult.getValue() + precondition_contribution;
        }

        return Float.MAX_VALUE;
    }

    protected void init_condition (Collection<GroundAction> pool, PDDLState s_0) {

        action_to_variable = new HashMap();
        Collection<Condition> conditions_to_evaluate = new LinkedHashSet();
        conditions_to_evaluate.addAll(c.sons);
        if (gc != null) {
//            System.out.println("considering Global Constraints:"+gC.sons);
            conditions_to_evaluate.addAll(gc.sons);
        }
        this.affectors_of.put(c.getHeuristicId(), new LinkedHashSet());

        for (Condition cond : conditions_to_evaluate) {
            Expression condition = lp.addExpression(cond.toString());
            pos_affectors_of.put(cond, new LinkedHashSet());
            if (cond instanceof Comparison) {
                Comparison comp = (Comparison) cond;

                ExtendedNormExpression left = (ExtendedNormExpression) comp.getLeft();
                for (ExtendedAddendum ad : left.summations) {
                    if (ad.f != null) {
                        for (GroundAction gr : pool) {
//                                                        System.out.println(gr);

                            if (gr.getNumericFluentAffected().contains(ad.f)) {
                                for (NumEffect neff : gr.getNumericEffectsAsCollection()) {
                                    if (!neff.getFluentAffected().equals(ad.f)) {
                                        continue;
                                    }
                                    //                                    System.out.println(neff);

//                                    gr.set_unit_cost(s_0);
                                    Float action_cost = getActionCost(s_0,gr);
                                    if (action_cost.isNaN()) {
                                        continue;
                                    }
                                    affectors_of.get(c.getHeuristicId()).add(gr);//add the actions to the affectors list

                                    Variable action;
                                    if (action_to_variable.get(gr.getId()) != null) {
                                        action = action_to_variable.get(gr.getId());
                                        if (integer_variables) {
                                            action.integer(true);
                                        }
                                    } else {
                                        action = Variable.make(gr.toEcoString()).lower(0).upper(0).weight(action_cost);
                                        this.var_to_expr.put(action, new LinkedHashSet());
                                        //action.lower(0);
                                        lp.addVariable(action);
                                        action_to_variable.put(gr.getId(), action);
                                    }

//                                    Float cost_of_prec = h.get(gr.getPreconditions().getCounter()) * 10.0F;
                                    //opt.Add(ctx.mkImplies(ctx.mkGt(var, ctx.mkInt(0)), ctx.mkEq(prec_cost, ctx.mkReal(cost_of_prec.intValue(), 10))));
                                    //opt.Add(ctx.mkImplies(ctx.mkEq(var, ctx.mkInt(0)), ctx.mkEq(prec_cost, ctx.mkReal(0))));
                                    Double right = null;
                                    switch (neff.getOperator()) {
                                        case "increase":
                                            right = neff.getRight().eval(s_0) * ad.n;
                                            right = condition.get(action).floatValue() + right;
                                            //var_to_expr.get(action).add(new Constraint(condition,right));
                                            condition.set(action, right);

//                                            System.out.println("DEBUG:"+condition);
                                            break;
                                        case "decrease":
                                            right = neff.getRight().eval(s_0) * ad.n;
                                            right = condition.get(action).floatValue() - right;
                                            //var_to_expr.get(action).add(new Constraint(condition,-right));
                                            condition.set(action, right);
                                            break;
                                        case "assign":
                                            continue;
                                    }
//                                    System.out.println("DEBUG:"+condition+" right:"+right);

                                }
                            }
                            if (gr.getNumberOfExecution(s_0, comp) != Float.MAX_VALUE) {
                                pos_affectors_of.get(comp).add(gr);
                            }
                        }
                    }
                }

//                System.out.println(condition);
            } else if (cond instanceof Predicate) {
                condition.lower(1);
                Predicate p = (Predicate) cond;
                for (GroundAction gr : pool) {
                    if (gr.weakAchiever(p)) {
                        pos_affectors_of.get(cond).add(gr);
                        affectors_of.get(c.getHeuristicId()).add(gr);//add the actions to the affectors list
//                        gr.set_unit_cost(s_0);
                        Float action_cost = getActionCost(s_0,gr);
                        if (action_cost.isNaN()) {
                            continue;
                        }
                        Variable action;
                        if (action_to_variable.get(gr.getId()) != null) {
                            action = action_to_variable.get(gr.getId());
                        } else {
                            action = Variable.make(gr.toEcoString()).lower(0).weight(action_cost);
                            lp.addVariable(action);
                            action_to_variable.put(gr.getId(), action);
                            if (integer_variables) {
                                action.integer(true);
                            }
                        }
                        condition.set(action, 1);

                    }
                }
            }

        }

        lp.setMinimisation();
//        System.out.println("DEBUG: Condition: " + c + " LP Created:" + lp + " LP Obj function:" + lp.getObjectiveExpression());

    }

    @Override
    protected void update_condition (PDDLState s_0, ComplexCondition temp) {
        for (Condition c_0 : (Collection<Condition>) temp.sons) {
            Expression lp_cond = lp.getExpression(c_0.toString());
            if (c_0 instanceof Comparison) {
                Comparison comp = (Comparison) c_0;
                Double eval = comp.getLeft().eval(s_0);
                if (eval == null) {
                    lp_cond.lower(5).upper(4);
                } else {
                    Double number = eval;
                    //                System.out.println("DEBUG: expression before:" + lp.getExpression(lp_cond.getName()));
                    if (comp.getComparator().equals(">") || comp.getComparator().equals(">=")) {
                        lp_cond.lower(-number);
                    } else if (comp.getComparator().equals("=")) {
                        lp_cond.lower(number).upper(number);
                    } else {
                        lp_cond.lower(number);
                    }
                }
//                System.out.println("DEBUG: expression after:" + lp.getExpression(lp_cond.getName()));

            } else if (c_0 instanceof Predicate) {
                if (s_0.satisfy(c_0)) {
                    lp_cond.lower(0);
                } else {
                    lp_cond.lower(1);
                }
            }
        }

    }

    private static class Constraint {

        public Expression expr;
        public Float contr;

        public Constraint (Expression e, Float n) {
            super();
            expr = e;
            contr = n;
        }
    }

}
