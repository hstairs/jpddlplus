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
public abstract class Expression extends Object {

    public boolean grounded;
    protected boolean freeVarSemantic = false;

    public Expression ( ) {
        grounded = false;
    }

    /**
     * Substitutes the variables in this expression with the PDDLObjects
     * associated to each variable in the specified table.The returned
 expression is therefore grounded. This method may fail if there is no
 substitution for some variable.
     *
     * @param substitution the map that indicates what object should replace the
     *                     specified variable.
     * @param po
     * @return a copy of this expression where each variable is replaced to the
     * object according to the specified mapping.
     */
    public abstract Expression ground (Map<Variable, PDDLObject> substitution, PDDLObjects po);

    public abstract Expression unGround (Map substitution);

    public abstract double eval (State s);

    //public abstract PDDLState apply(PDDLState s);
    public abstract ExtendedNormExpression normalize ( );


    /**
     * @param p
     * @param invF
     * @return
     */
    public abstract Expression weakEval (PDDLProblem p, Set invF);

    @Override
    public abstract Expression clone ( );

    public abstract HomeMadeRealInterval eval (RelState s);

    public abstract boolean involve (Collection<NumFluent> input);

    public abstract Expression subst (Condition numeric);

    public abstract Set<NumFluent> getInvolvedNumericFluents ( );

    public abstract boolean isUngroundVersionOf (Expression expr);

    public abstract Expression susbtFluentsWithTheirInvariants (int j);


    public abstract String toSmtVariableString (int i);

    /**
     * @return the freeVarSemantic
     */
    public boolean isFreeVarSemantic ( ) {
        return freeVarSemantic;
    }

    /**
     * @param freeVarSemantic the freeVarSemantic to set
     */
    public void setFreeVarSemantic (boolean freeVarSemantic) {
        this.freeVarSemantic = freeVarSemantic;
    }

    public abstract boolean involve (NumFluent a);

    /**
     * Returns a string representation of this expression in PDDL format.
     *
     * @param typeInformation <tt>true</tt>
     *                        if the type of the object should be printed as well.
     * @return a string representation in PDDL format of this condition.
     */
    public final String pddlPrint (boolean typeInformation) {
        final StringBuilder bui = new StringBuilder();
        pddlPrint(typeInformation, bui);
        return bui.toString();
    }

    /**
     * Prints this expression in PDDL format in the specified string builder.
     *
     * @param typeInformation <tt>true</tt>
     *                        if the type of the object should be printed as well.
     * @param bui             the string builder where this condition is printed.
     */
    public abstract void pddlPrint (boolean typeInformation, StringBuilder bui);

    public abstract Expression unifyVariablesReferences (PDDLProblem p);
}
