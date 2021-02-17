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

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.PddlProblem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author enrico
 */
public abstract class LpInterface {

    public int n_invocations;
    public boolean integer_variables;
    public boolean additive_h;
    public ComplexCondition gc;
    public ComplexCondition c;
    final protected PddlProblem problem;

    //this is for set of conditions
    public LpInterface (Set<ComplexCondition> cond, ComplexCondition global_constraint, PddlProblem inputProblem) {
        super();
        AndCond c = new AndCond();
        c.sons.addAll(cond);
        problem = inputProblem;
    }

    public LpInterface (ComplexCondition cond, ComplexCondition global_constraint, PddlProblem inputProblem) {
        super();
        c = cond;
        gc = global_constraint;
        problem = inputProblem;
    }

    public abstract void initialize (Collection<GroundAction> actions, PDDLState s_0);

    public abstract void update_conditions_bound_plus_reset_variables (PDDLState s_0);

    protected abstract void update_local_global_conditions(PDDLState s_0);

    public abstract float update_cost(PDDLState s_0, ArrayList<Boolean> active_actions, ArrayList<Float> h);

    protected abstract void init_condition(Collection<GroundAction> pool, PDDLState s_0);

    protected abstract void update_condition(PDDLState s_0, ComplexCondition temp);

    protected Float getActionCost(PDDLState s_0, GroundAction gr) {
        if (problem.isAction_cost_from_metric()) {
            return gr.getActionCost(s_0, problem.getMetric());
        }
        return 1f;
    }

}
