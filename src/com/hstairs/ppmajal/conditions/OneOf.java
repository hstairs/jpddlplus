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
package com.hstairs.ppmajal.conditions;

import com.hstairs.ppmajal.PDDLProblem.PDDLObjects;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;

/**
 * @author enrico
 */
public class OneOf extends ComplexCondition {

    private String smt_representation;


    public OneOf(Collection oneOf) {
        super(oneOf);
    }

    @Override
    public Condition weakEval (PDDLProblem s, Set invF) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean eval (State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toSmtVariableString (int k, TransitionGround gr, String var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSatisfied (State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void changeVar (Map substitution) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public String pddlPrintWithExtraObject ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition clone ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBeTrue (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition normalize ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String toSmtVariableString (int i) {//does not work
        if (smt_representation == null) {
            smt_representation = "";
            Collection and = new HashSet();
            Collection or = new HashSet();
            for (final var p1 :  this.sons) {
                or.add(p1);
            }
            and.add(or);
            

            for (int k = 0; k < sons.length; k++) {
                for (int j = k + 1; j < sons.length; j++) {
                    Collection or2 = new HashSet();
                    NotCond a = NotCond.createNotCond((Condition) sons[k]);
                    NotCond b = NotCond.createNotCond((Condition) sons[j]);
                    or2.add(a);
                    or2.add(b);
                    and.add(or2);
                }
            }
            AndCond temp = new AndCond(and);
            smt_representation = temp.toSmtVariableString(i);
        }

        return smt_representation;
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        return new HashSet();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComplexCondition transformEquality ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean canBeFalse (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode ( ) {
        final int sonHash = sons.hashCode();
        final int result = sonHash + 2;
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof OneOf)) {
            return false;
        }

        final OneOf other = (OneOf) obj;

        return this.sons.equals(other.sons);
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public ComplexCondition and (Condition precondition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Condition pushNotToTerminals( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition introduce_red_constraints ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition unifyVariablesReferences(PDDLProblem p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
