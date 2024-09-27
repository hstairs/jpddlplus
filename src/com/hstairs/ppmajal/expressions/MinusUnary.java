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
package com.hstairs.ppmajal.expressions;

import com.hstairs.ppmajal.PDDLProblem.PDDLObjects;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.problem.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author enrico
 */
public class MinusUnary extends Expression {

    Expression element;

    public MinusUnary ( ) {
        super();
    }

    public MinusUnary (Expression expr) {
        super();
        element = expr;
    }

    @Override
    public Expression ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        MinusUnary ret = new MinusUnary();

        ret.element = element.ground(substitution, po);
        ret.grounded = true;
        return ret;
    }

    @Override
    public Expression unGround (Map substitution) {
        MinusUnary ret = new MinusUnary();

        ret.element = element.unGround(substitution);
        ret.grounded = false;
        return ret;
    }

    @Override
    public double eval (State s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExtendedNormExpression normalize ( ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public Expression weakEval (PDDLProblem s, Set invF) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Expression clone ( ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HomeMadeRealInterval eval (RelState s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean involve (Collection<NumFluent> arrayList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Expression subst (Condition numeric) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set getInvolvedNumericFluents ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUngroundVersionOf (Expression expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expression susbtFluentsWithTheirInvariants (int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String toSmtVariableString (int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean involve (NumFluent a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Expression unifyVariablesReferences (PDDLProblem p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
