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
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;

/**
 * @author enrico
 */
public class ConditionalEffectAsACondition extends Condition implements PostCondition {

    public Condition activation_condition;
    public PostCondition effect;



    public ConditionalEffectAsACondition (Condition lhs, PostCondition rhs) {
        this.activation_condition = lhs;
        Collection res = new HashSet();
        if (rhs instanceof AndCond) {
            for (Object o: ((AndCond) rhs).sons){
                res.add(o);
            }
            
        } else {
            res.add(rhs);
        }
        effect = new AndCond(res);

    }

    public Condition clone ( ) {
        return new ConditionalEffectAsACondition(activation_condition.clone(), (PostCondition) effect.clone());
    }

    public String toString ( ) {
        if (this.activation_condition != null) {
            return "(when " + this.activation_condition.pddlPrint(true) + " " + this.effect.pddlPrint(true) + ")";
        }
        return null;
    }

    @Override
    public Condition weakEval (PDDLProblem s, Set invF) {
        this.activation_condition = this.activation_condition.weakEval(s, invF);
        if (this.activation_condition.isValid()){
            this.activation_condition = BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE);
        }
        if (this.effect instanceof Condition) {
            Condition con = (Condition) this.effect;
            this.effect = (PostCondition) con.weakEval(s, invF);
            if (this.effect == null){
                return null;
            }
        } else if (this.effect instanceof ConditionalEffectAsACondition) {
            ConditionalEffectAsACondition sub = (ConditionalEffectAsACondition) this.effect;
            this.effect = (PostCondition) sub.weakEval(s, invF);

        } else if (this.effect instanceof NumEffect) {
            NumEffect ne = (NumEffect) this.effect;
            this.effect = (PostCondition) ne.weakEval(s, invF);
        }
        return this;
    }

    public ConditionalEffectAsACondition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
//        if (ret.activation_condition!=null){
        Condition activation_condition = this.activation_condition.ground(substitution, po);
        PostCondition effect = null;
        if (this.effect instanceof Condition) {
            Condition con = (Condition) this.effect;
            effect = (PostCondition) con.ground(substitution, po);
        } else if (this.effect instanceof ConditionalEffectAsACondition) {
            ConditionalEffectAsACondition sub = (ConditionalEffectAsACondition) this.effect;
            effect = sub.ground(substitution, po);
        } else if (this.effect instanceof NumEffect) {
            NumEffect ne = (NumEffect) this.effect;
            effect = (NumEffect) ne.ground(substitution, po);
        }
//        }
        return new ConditionalEffectAsACondition(activation_condition,effect);
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
        return s.satisfy(activation_condition);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void changeVar (Map substitution) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean canBeTrue (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition normalize ( ) {
        this.activation_condition.normalize();
        if (this.effect instanceof Condition) {
            Condition con = (Condition) this.effect;
            this.effect = (PostCondition) con.normalize();
        } else if (this.effect instanceof ConditionalEffectAsACondition) {
            ConditionalEffectAsACondition sub = (ConditionalEffectAsACondition) this.effect;
            this.effect = (PostCondition) sub.normalize();
        } else if (this.effect instanceof NumEffect) {
            ((NumEffect) this.effect).getRight().normalize();
        }
        return this;
    }



    @Override
    public String toSmtVariableString (int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition transformEquality ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String pddlPrintWithExtraObject ( ) {
        return "(when " + this.activation_condition.pddlPrintWithExtraObject() + " " + this.effect.pddlPrintWithExtraObject() + ")";
    }

    @Override
    public boolean canBeFalse (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode ( ) {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.activation_condition);
        hash = 89 * hash + Objects.hashCode(this.effect);
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConditionalEffectAsACondition other = (ConditionalEffectAsACondition) obj;
        if (!Objects.equals(this.activation_condition, other.activation_condition)) {
            return false;
        }
        return Objects.equals(this.effect, other.effect);
    }


    @Override
    public HashMap<Object, Object> apply (RelState s) {
        final HashMap<Object, Object> ret = new HashMap<>();
        apply(s, ret);
        return ret;
    }

    @Override
    public HashMap<Object, Object> apply (PDDLState s) {
        final HashMap<Object, Object> ret = new HashMap<>();
        apply(s, ret);
        return ret;
    }

    @Override
    public void apply (State s, Map modifications) {
        if (s.satisfy(activation_condition)) {
            effect.apply(s, modifications);
        }
    }

    @Override
    public void apply (RelState s, Map modifications) {
        if (s.satisfy(activation_condition)) {
            effect.apply(s, modifications);
        }
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(when ");
        activation_condition.pddlPrint(typeInformation, bui);
        bui.append(" ");
        effect.pddlPrint(typeInformation, bui);
        bui.append(")");
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        LinkedHashSet ret = new LinkedHashSet();

        if (effect == null || this.activation_condition == null)
            return ret;
        ret.addAll(((Condition) effect).getTerminalConditions());
        ret.addAll(activation_condition.getTerminalConditions());


        return ret;
    }

    @Override
    public List<Condition> getTerminalConditionsInArray ( ) {
        return this.activation_condition.getTerminalConditionsInArray();
    }


    @Override
    public ComplexCondition and (Condition precondition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Condition pushNotToTerminals( ) {
        if (this.activation_condition != null)
            this.activation_condition = this.activation_condition.pushNotToTerminals();
        return this;
        //To change body of generated methods, choose Tools | Templates.
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
    public boolean involve (Condition c) {
        return this.activation_condition.involve(c);
    }

    @Override
    public Condition unifyVariablesReferences (PDDLProblem p) {
        LinkedHashSet ret = new LinkedHashSet();
        this.activation_condition = this.activation_condition.unifyVariablesReferences(p);
        this.effect = (PostCondition) ((Condition) this.effect).unifyVariablesReferences(p);
        return this;
    }

    @Override
    public Collection<BoolPredicate> getInvolvedPredicates ( ) {
        Set<BoolPredicate> ret = new LinkedHashSet();
        ret.addAll(this.activation_condition.getInvolvedPredicates());
        ret.addAll(((Condition) this.effect).getInvolvedPredicates());
        return ret;
    }
}
