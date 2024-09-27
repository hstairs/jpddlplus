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
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;

/**
 * @author enrico
 */
public class NotCond extends Terminal implements PostCondition {
    /**
     * The condition that is negated in this condition.
     */
    final private Condition son;

    public static HashMap<Condition,NotCond> notcondDB;
    public static NotCond createNotCond(Condition c){
        if (notcondDB == null){
            notcondDB = new HashMap();
        }
        NotCond nc = notcondDB.get(c);
        if (nc == null){
            nc = new NotCond(c);
            notcondDB.put(c,nc);
        }
        return nc;
    }
    
    private NotCond (Condition son) {
        super();
        this.son = son;
    }

    public static int getNumberOfNotCond() {
        if (NotCond.notcondDB == null){
            return 0;
        }else{
            return NotCond.notcondDB.values().size();
        }
    }


    public Condition getSon ( ) {
        return son;
    }

    @Override
    public String toString ( ) {
        String ret_val = "(NOT ";
        ret_val = ret_val.concat(son.toString());
        ret_val = ret_val.concat(")");

        return ret_val;
    }

    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        final Condition groundedSon = son.ground(substitution, po);
        NotCond ret = NotCond.createNotCond(groundedSon);
        return ret;
    }


    @Override
    public boolean eval (State s) {

        return !son.eval(s);

    }

    @Override
    public boolean isSatisfied (State s) {

        return !son.isSatisfied(s);

    }

    @Override
    public boolean canBeTrue (RelState s) {

        return son.canBeFalse(s);

    }

    @Override
    public Condition normalize ( ) {
        son.normalize();
        return this;
    }

//    @Override
//    public void changeVar (Map substitution) {
//        son.changeVar(substitution);
//    }

    @Override
    public Condition clone ( ) {
        return this;
    }


    @Override
    public String toSmtVariableString (int i) {
        return "(not " + son.toSmtVariableString(i) + ")";
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        Set<NumFluent> ret = new HashSet();

        if (son instanceof Condition) {
            Condition c = son;
            if (c.getInvolvedFluents() != null) {
                ret.addAll(c.getInvolvedFluents());
            }
        } else {
            System.out.println("Error in getting involved fluents");
        }

        return ret;
    }

    @Override
    public Condition weakEval (PDDLProblem s, Set invF) {

        Condition el = son;
        el = el.weakEval(s, invF);
        if (el == null) {
            this.setValid(true);
            this.setUnsatisfiable(false);
        } else if (el.isValid()) {
            this.setUnsatisfiable(true);
            this.setValid(false);
        } else if (el.isUnsatisfiable()) {
            this.setValid(true);
            this.setUnsatisfiable(false);
        }

        return this;
    }

    @Override
    public String toSmtVariableString (int i, TransitionGround gr, String var) {
        return "(not " + son.toSmtVariableString(i, gr, var) + ")";
    }

    @Override
    public Condition transformEquality ( ) {
        if (this.son == null) {
            return this;
        }
        final Condition transformedSon = son.transformEquality();
        NotCond ret = NotCond.createNotCond(transformedSon);
        //System.out.println(ret);
        return ret;
    }




    @Override
    public String pddlPrintWithExtraObject ( ) {
        String ret_val = "(not ";
        if (son instanceof Condition) {
            Condition c = son;
            ret_val = ret_val.concat(c.pddlPrintWithExtraObject());
        } else {
            System.out.println("Error in pddlPrint:" + this);
            System.exit(-1);
        }
        ret_val = ret_val.concat(")");
        return ret_val;
    }

    @Override
    public boolean canBeFalse (RelState s) {
        return son.canBeTrue(s);
    }



   

    @Override
    public HashMap apply (PDDLState s) {
        HashMap ret = new HashMap();
        apply(s, ret);
        return ret;
    }

    @Override
    public HashMap apply (RelState s) {
        HashMap ret = new HashMap();
        apply(s, ret);
        return ret;

    }

    @Override
    public void apply (State s, Map modifications) {
        if (son instanceof BoolPredicate) {
            BoolPredicate p = (BoolPredicate) son;
            modifications.put(p, Boolean.FALSE);
        } else {
            sonHasIncorrectType();
        }
    }

    @Override
    public void apply (RelState s, Map modifications) {
        if (son instanceof BoolPredicate) {
            BoolPredicate p = (BoolPredicate) son;
            if (s.possBollValues.get(p.getId()) == 1) {
                modifications.put(p, 2);
            }
        } else {
            sonHasIncorrectType();
        }
    }

    private void sonHasIncorrectType ( ) {
        System.out.println("Not " + son + " is not valid. Please revise your action model");
        System.exit(-1);
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(not ");
        son.pddlPrint(typeInformation, bui);
        bui.append(")");
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        for (NumFluent nf : this.getInvolvedFluents()) {
            for (final Object o : nf.getTerms()) {
                final Variable var = (Variable) o;
                vars.add(var);
            }
        }
        for (BoolPredicate p : this.getInvolvedPredicates()) {
            for (final Object o : p.getTerms()) {
                final Variable var = (Variable) o;
                vars.add(var);
            }
        }
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        if (!this.isTerminal()) {
            throw new RuntimeException("This should be a terminal!" + this);
        }
        LinkedHashSet ret = new LinkedHashSet();
        ret.add(this);
        return ret;
    }



    @Override
    public ComplexCondition and (Condition precondition) {
        Collection and = new HashSet();
        and.add(precondition);
        and.add(this);
        return new AndCond(and);
    }



    @Override
    public Condition pushNotToTerminals( ) {
        if (son instanceof BoolPredicate) {
            return this;
        } else if (son instanceof Comparison) {
            final Comparison c1 = (Comparison) son;
            final Condition c2 = c1.invertOperator();
            return c2;
        } else if (son instanceof AndCond) {
            final AndCond and = (AndCond) son;
            final OrCond or = and.push_negation_demorgan();
            final Condition c = or.pushNotToTerminals();
            return c;
        } else if (son instanceof OrCond) {
            final OrCond or = (OrCond) son;
            final AndCond and = or.push_negation_demorgan();
            final Condition c = and.pushNotToTerminals();
            return c;
        } else if (son instanceof NotCond) {
            final NotCond nc = (NotCond) son;
//            System.out.println("Pushing of the not:"+nc.son.push_not_to_terminals());
            return nc.son.pushNotToTerminals();
        } else if (son instanceof Existential) {
            final Existential nc = (Existential) son;
//            System.out.println("Pushing of the not:"+nc.son.push_not_to_terminals());
            return nc.pushNegationDemorgan().pushNotToTerminals();
        } else if (son instanceof ForAll) {
            throw new RuntimeException("NNF with negated forall not supported yet");
        } else {
            throw new RuntimeException("This condition is not supported:"+this);

        }
    }

    public boolean isTerminal ( ) {
        return this.son instanceof Terminal;
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<BoolPredicate> getInvolvedPredicates ( ) {
        Set ret = new LinkedHashSet();
        ret.addAll(this.son.getInvolvedPredicates());
        return ret;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition unifyVariablesReferences (PDDLProblem p) {
        NotCond nc = (NotCond) super.unifyVariablesReferences(p);
        //nc.son = nc.son.unifyVariablesReferences(p);
        return nc;
    }

}
