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
package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.NotCond;
import com.hstairs.ppmajal.conditions.OrCond;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.domain.SchemaGlobalConstraint;
import com.hstairs.ppmajal.domain.SchemaParameters;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.RelState;

import java.util.Set;

/**
 * @author enrico
 */
public class GlobalConstraint extends SchemaGlobalConstraint {

    public ParametersAsTerms grn_parameters;
    private boolean normalized;
    private boolean reachable;

    public GlobalConstraint (String name) {
        super(name);
    }

    @Override
    public Object clone ( ) {
        GlobalConstraint cloned = new GlobalConstraint(this.name);
        cloned.normalized = this.normalized;
        cloned.reachable = this.reachable;
        cloned.condition = this.condition.clone();
        cloned.grn_parameters = (ParametersAsTerms) this.grn_parameters.clone();
        cloned.parameters = (SchemaParameters) this.parameters.clone();
        return cloned;
    }

    boolean simplifyModelWithControllableVariablesSem (PDDLDomain domain, PDDLProblem problem) {

        Set invariantFluents = problem.getActualFluents();
        //add invariantFluents because free variable
        for (NumFluent nf : domain.get_derived_variables()) {
            invariantFluents.add(nf.getName());
        }

        GlobalConstraint constr = this;
        //a.normalizeAndCopy();

        Condition con = constr.condition;
        //System.out.println(con);
        //con.normalize();
        //System.out.println(con);

        //This needs to be fixed. The semantics of global constraints is different
        //from that of the actions. 
        con = con.weakEval(problem, invariantFluents);

        this.condition = con;

        if (con.isValid()) {
            return false;
        }

        //if (con == null || con.isUnsatisfiable()){
        //    return false;
        //}
        //System.out.println(a.toPDDL());
//        System.out.println(constr);
        constr.normalize();
//        System.out.println(constr);
//        if (a.isUnsatisfiable())
//            return false;
        //System.out.println(a.toPDDL());
        return true;
    }

    private void normalize ( ) {
        if (normalized) {
            return;
        }
        this.condition = this.condition.normalize();
        if (this.condition.isUnsatisfiable()) {
            this.setReacheable(false);
        }
        this.normalized = true;
    }

    /**
     * @return the reacheable
     */
    public boolean isReacheable ( ) {
        return reachable;
    }

    /**
     * @param reacheable the reacheable to set
     */
    public void setReacheable (boolean reacheable) {
        this.reachable = reacheable;
    }

    public boolean isTautology (PDDLState reacheableState) {

        //for now tautology is checked for disjunction in which one of the element is always true
        if (this.condition instanceof OrCond) {
            OrCond or = (OrCond) this.condition;
            for (final var c :  or.sons) {
                if (c instanceof NotCond) {
                    NotCond nc = (NotCond) c;
                    Object o = nc.getSon();
                    if (o instanceof BoolPredicate) {
                        BoolPredicate p = (BoolPredicate) o;
                        if (!p.isSatisfied(reacheableState)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean isTautology (RelState reacheableState) {

        //for now tautology is checked for disjunction in which one of the element is always true
        if (this.condition instanceof OrCond) {
            OrCond or = (OrCond) this.condition;
            for (final var c :  or.sons) {
                if (c instanceof NotCond) {
                    NotCond nc = (NotCond) c;
                    Object o = nc.getSon();
                    if (o instanceof BoolPredicate) {
                        BoolPredicate p = (BoolPredicate) o;
                        if (!p.canBeTrue(reacheableState)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

}
