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

import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.*;

import java.util.*;

/**
 * @author enrico
 */
public class NotCond extends Terminal implements PostCondition {
    /**
     * The condition that is negated in this condition.
     */
    final private Condition son;
    final int id;

    public static HashMap<Condition,NotCond> notcondDB;
    public static NotCond createNotCond(Condition c){
        if (notcondDB == null){
            notcondDB = new HashMap();
        }
        NotCond nc = notcondDB.get(c);
        if (nc == null){
            nc = new NotCond(c,notcondDB.values().size());
            notcondDB.put(c,nc);
        }
        return nc;
    }
    
    private NotCond (Condition son, int id) {
        super();
        this.son = son;
        this.id = id;
    }

    public int getId() {
        return id;
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
        ret.grounded = true;
        return ret;
    }

    @Override
    public Condition ground (Map substitution, int c) {
        Condition ret = this.ground(substitution, null);
        ret.setHeuristicId(c);
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
    public boolean can_be_true (RelState s) {

        return son.can_be_false(s);

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
    public boolean isUngroundVersionOf (Condition con) {
        if (con instanceof NotCond) {
            NotCond nc = (NotCond) con;
            Condition c1 = this.son;
            Condition c2 = nc.son;
            return c1.isUngroundVersionOf(c2);

        }
        return false;
    }

    @Override
    public Condition unGround (Map substitution) {
        final Condition ungroundSon = son.unGround(substitution);
        NotCond ret = NotCond.createNotCond(ungroundSon);
        ret.grounded = false;
        return ret;
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
    public Condition weakEval (PddlProblem s, HashMap invF) {

        Condition el = son;
        el.setFreeVarSemantic(freeVarSemantic);
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
    public String toSmtVariableString (int i, GroundAction gr, String var) {
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
    public boolean is_affected_by (GroundAction gr) {

        return son.is_affected_by(gr);

    }

    @Override
    public Condition regress (GroundAction gr) {

        Condition temp = son.regress(gr);
        if (temp.isValid()) {
//            NotCond ret = new NotCond();
//            ret.setUnsatisfiable(true);
            return Predicate.createPredicate(Predicate.trueFalse.FALSE);
        }
        if (temp.isUnsatisfiable()) {
            return Predicate.createPredicate(Predicate.trueFalse.TRUE);
        }
        NotCond not = NotCond.createNotCond(temp);

        return not;
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
    public boolean can_be_false (RelState s) {
        return son.can_be_true(s);
    }

    @Override
    public Condition achieve (Predicate p) {
        return null;
    }

    @Override
    public Condition delete (Predicate p) {
        if (son.equals(p)) {
            return Predicate.createPredicate(Predicate.trueFalse.TRUE);
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NotCond other = (NotCond) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
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
        if (son instanceof Predicate) {
            Predicate p = (Predicate) son;
            modifications.put(p, Boolean.FALSE);
        } else {
            sonHasIncorrectType();
        }
    }

    @Override
    public void apply (RelState s, Map modifications) {
        if (son instanceof Predicate) {
            Predicate p = (Predicate) son;
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
        for (Predicate p : this.getInvolvedPredicates()) {
            for (final Object o : p.getTerms()) {
                final Variable var = (Variable) o;
                vars.add(var);
            }
        }
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        if (!this.isTerminal()) {
            System.out.println("This should be a terminal!" + this);
            System.exit(-1);
        }
        LinkedHashSet ret = new LinkedHashSet();
        ret.add(this);
        return ret;
    }

    @Override
    public Float estimate_cost (ArrayList<Float> cond_dist, boolean additive_h) {
        return cond_dist.get(this.getHeuristicId());
    }

    @Override
    public ComplexCondition and (Condition precondition) {
        AndCond and = new AndCond();
        and.addConditions(precondition);
        and.addConditions(this);
        return and;
    }

    @Override
    public AchieverSet estimate_cost (ArrayList<Float> cond_dist, boolean additive_h, ArrayList<GroundAction> established_achiever) {
        AchieverSet s = new AchieverSet();
        s.setCost(cond_dist.get(this.getHeuristicId()));
        s.getActions().add(established_achiever.get(this.getHeuristicId()));
        s.getTargetCond().add(this);
        return s;
    }

    @Override
    public Condition push_not_to_terminals ( ) {
        if (son instanceof Predicate) {
            return this;
        } else if (son instanceof Comparison) {
            Comparison c1 = (Comparison) son;
            Condition c2 = c1.invertOperator();
            return c2;
        } else if (son instanceof AndCond) {
            AndCond and = (AndCond) son;
            OrCond or = and.push_negation_demorgan();
            Condition c = or.push_not_to_terminals();
            return c;
        } else if (son instanceof OrCond) {
            OrCond or = (OrCond) son;
            AndCond and = or.push_negation_demorgan();
            Condition c = and.push_not_to_terminals();
            return c;
        } else if (son instanceof PDDLObjectsEquality) {
            return this;
        } else if (son instanceof NotCond) {
            NotCond nc = (NotCond) son;
//            System.out.println("Pushing of the not:"+nc.son.push_not_to_terminals());
            return nc.son.push_not_to_terminals();
        } else if (son instanceof Existential) {
            Existential nc = (Existential) son;
//            System.out.println("Pushing of the not:"+nc.son.push_not_to_terminals());
            return nc.pushNegationDemorgan().push_not_to_terminals();
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
    public Set<NumFluent> affectedNumericFluents ( ) {
        return new HashSet();
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Predicate> getInvolvedPredicates ( ) {
        Set ret = new LinkedHashSet();
        ret.addAll(this.son.getInvolvedPredicates());
        return ret;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition unifyVariablesReferences (EPddlProblem p) {
        NotCond nc = (NotCond) super.unifyVariablesReferences(p);
        //nc.son = nc.son.unifyVariablesReferences(p);
        return nc;
    }

}
