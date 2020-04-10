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
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.*;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author enrico
 */
public class OrCond extends ComplexCondition {


    public OrCond ( ) {
        super();
    }

    @Override
    public String toString ( ) {
        String ret_val = "(OR ";
        for (Object o : sons) {
            ret_val = ret_val.concat(o.toString());
        }
        ret_val = ret_val.concat(")");

        return ret_val;
    }

    @Override
    public Condition normalize() {
        ReferenceLinkedOpenHashSet sons1 = new ReferenceLinkedOpenHashSet();
        for (final Condition cond : (Collection<Condition>)sons ){
            Condition condInternal = cond.normalize();
            if (condInternal.isValid()){
                this.setUnsatisfiable(true);
                return Predicate.createPredicate(Predicate.trueFalse.FALSE);
            }else if (!condInternal.isUnsatisfiable()){
                if (condInternal instanceof OrCond){
                    sons1.addAll(((AndCond) condInternal).sons);
                }else{
                    sons1.add(condInternal);
                }
            }
        }
        sons = sons1;
        return this;
    }

    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        OrCond ret = new OrCond();

        for (Object o : sons) {
            Condition el = (Condition) o;
            ret.sons.add(el.ground(substitution, po));
        }
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

        for (Object o : sons) {
            Condition c = (Condition) o;
            if (c.eval(s)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isSatisfied (State s) {
        for (Object o : sons) {
            Condition c = (Condition) o;
            if (c.isSatisfied(s)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean can_be_true (RelState s) {
        for (Object o : sons) {
            Condition c = (Condition) o;
            if (c.can_be_true(s)) {
                return true;
            }
        }

        return false;
    }

//    @Override
//    public void changeVar (Map substitution) {
//
//        for (Object o : sons) {
//            Condition el = (Condition) o;
//            el.changeVar(substitution);
//        }
//    }

    @Override
    public Condition clone ( ) {
        OrCond ret = new OrCond();

//        ret.sons = new HashSet();
        ret.sons = new ReferenceLinkedOpenHashSet();
        ret.sons.addAll(this.sons);
//        for(Object o: this.sons){
//            if (o instanceof AndCond){
//                AndCond a = (AndCond)o;
//                ret.sons.add(a.clone());
//            }else if(o instanceof NotCond){
//                NotCond a = (NotCond)o;
//                ret.sons.add(a.clone());
//            }else if(o instanceof OrCond){
//                OrCond a = (OrCond)o;
//                ret.sons.add(a.clone());
//            }else if(o instanceof Predicate){
//                Predicate a = (Predicate)o;
//                ret.sons.add(a.clone());            
//            }else if(o instanceof Comparison){
//                Comparison a = (Comparison)o;
//                ret.sons.add(a.clone());            
//            }else if(o instanceof Assigner){
//                Assigner a = (Assigner)o;
//                ret.sons.add(a.clone());            
//            }
//        }
        ret.grounded = this.grounded;
        return ret;
    }

    @Override
    public Condition unGround (Map substitution) {
        OrCond ret = new OrCond();

        for (Object o : sons) {
            Condition el = (Condition) o;
            ret.sons.add(el.unGround(substitution));
        }
        ret.grounded = false;
        return ret;
    }

    @Override
    public boolean isUngroundVersionOf (Condition conditions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toSmtVariableString (int i) {
        String ret = "";

        //System.out.println(this);
        if (this.sons != null) {
            if (this.sons.size() > 1) {
                ret += "(or";
            }
            for (Object o : this.sons) {
                if (o instanceof Predicate) {
                    Predicate p = (Predicate) o;
                    ret += " " + p.toSmtVariableString(i);
                } else if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    ret += " " + nc.toSmtVariableString(i);
                } else if (o instanceof Condition) {
                    //System.out.println(o.getClass());
                    Condition c = (Condition) o;
                    ret += " " + c.toSmtVariableString(i);
                } else {
                    System.err.println("Not Supported" + o.getClass());
                }
            }
            if (this.sons.size() > 1) {
                ret += ")";
            }
        }
        return ret;
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        Set<NumFluent> ret = new HashSet();

        //System.out.println("Testing with:"+this.sons);
        if (this.sons != null) {
            for (Object o : this.sons) {
//                System.out.println("Testing with:"+o);
                if (o instanceof NumFluent) {
                    ret.add((NumFluent) o);
                } else if (o instanceof Condition) {

                    Condition c = (Condition) o;
                    //System.out.println(c);
                    if (c.getInvolvedFluents() != null) {
                        ret.addAll(c.getInvolvedFluents());
                    }
                } else {
                    System.out.println("Error in getting involved fluents");
                }
            }
        }

        return ret;
    }

    public boolean involveReacheablePredicates (RelState possibleState) {

        if (this.sons != null) {
            for (Object o : this.sons) {
//                System.out.println("Testing with:"+o);
                if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    if (nc.getSon() instanceof Predicate) {
                        Condition p = nc.getSon();
                        //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                        return possibleState.satisfy(p);
                    }
                } else if (o instanceof Predicate) {
                    Predicate p = (Predicate) o;
                    //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                    return possibleState.satisfy(p);
                }
            }
        }
        return false;
    }

    public boolean involveReacheablePredicates (Collection<Predicate> possibleState) {

        if (this.sons != null) {
            for (Object o : this.sons) {
//                System.out.println("Testing with:"+o);
                if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    if (nc.getSon() instanceof Predicate) {
                        Condition p = nc.getSon();
                        //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                        return possibleState.contains(p);
                    }
                } else if (o instanceof Predicate) {
                    Predicate p = (Predicate) o;
                    //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                    return possibleState.contains(p);
                }
            }
        }
        return false;
    }

    @Override
    public Condition weakEval (PddlProblem s, HashMap invF) {
        ReferenceLinkedOpenHashSet to_keep = new ReferenceLinkedOpenHashSet();
        if (this.sons != null) {
            Iterator it = this.sons.iterator();
            while (it.hasNext()) {
                Object o2 = it.next();
                if (o2 instanceof Condition) {
                    Condition c = (Condition) o2;
                    c.setFreeVarSemantic(this.freeVarSemantic);
                    c = c.weakEval(s, invF);
                    if (o2 instanceof PDDLObjectsEquality) {
                        if (c.isValid()) {
                            this.setValid(true);
                            this.setUnsatisfiable(false);
                            return this;
                        }
                    } else if (c.isValid()) {
                        this.setValid(true);
                        this.setUnsatisfiable(false);
                        return this;
                    } else if (c.isUnsatisfiable()) {

                    } else {
                        to_keep.add(c);
                    }

                }
            }
        }
        this.sons = to_keep;

        return this;
    }

    @Override
    public String toSmtVariableString (int k, GroundAction gr, String var) {
        String ret = "";

        //System.out.println(this);
        if (this.sons != null) {
            if (this.sons.size() > 1) {
                ret += "(or";
            }
            for (Object o : this.sons) {
                if (o instanceof Predicate) {
                    Predicate p = (Predicate) o;
                    ret += " " + p.toSmtVariableString(k, gr, var);
                } else if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    ret += " " + nc.toSmtVariableString(k, gr, var);
                } else if (o instanceof Condition) {
                    //System.out.println(o.getClass());
                    Condition c = (Condition) o;
                    ret += " " + c.toSmtVariableString(k, gr, var);
                } else {
                    System.err.println("Not Supported" + o.getClass());
                }
            }
            if (this.sons.size() > 1) {
                ret += ")";
            }
        }
        return ret;
    }

    @Override
    public Condition transformEquality ( ) {
        if (this.sons == null) {
            return this;
        }
        OrCond ret = new OrCond();
        for (Condition c1 : (Collection<Condition>) this.sons) {
            ret.addConditions(c1.transformEquality());
        }
        return ret;
    }

    @Override
    public boolean is_affected_by (GroundAction gr) {
        if (this.sons != null && !this.sons.isEmpty()) {

            for (Condition c : (Collection<Condition>) this.sons) {
                if (c.is_affected_by(gr)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public Condition regress (GroundAction gr) {
        OrCond con = new OrCond();
        for (Object o : this.sons) {
            if (o instanceof Condition) {
                Condition t = (Condition) o;
                Condition temp = t.regress(gr);
                if (!temp.isValid()) {//needs to be satisfied
                    if (!temp.isUnsatisfiable()) {
                        if (temp instanceof OrCond) {
                            con.sons.addAll(((OrCond) temp).sons);
                        } else {
                            con.sons.add(temp);
                        }
                    }
                } else {
                    return Predicate.createPredicate(Predicate.trueFalse.TRUE);
                }
            } else {
                System.out.println("AndCond: Condition " + o + " cannot be regressed");
                System.exit(-1);
            }
        }
        return con;
    }

    @Override
    public String pddlPrintWithExtraObject ( ) {
        String ret_val = "(or ";
        for (Object o : sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                ret_val = ret_val.concat(c.pddlPrintWithExtraObject());
            } else if (o instanceof Comparison) {
                Comparison comp = (Comparison) o;
                ret_val = ret_val.concat(comp.pddlPrintWithExtraObject());
            } else {
                System.out.println("Error in pddlPrint:" + this);
                System.exit(-1);
            }
        }
        ret_val = ret_val.concat(")");
        return ret_val;
    }

    @Override
    public boolean can_be_false (RelState s) {
        for (Object o : sons) {
            Condition c = (Condition) o;
            if (!c.can_be_false(s)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode ( ) {
        final int sonHash = sons.hashCode();
        final int result = sonHash + 12;
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

        if (!(obj instanceof OrCond)) {
            return false;
        }

        final OrCond other = (OrCond) obj;

        return this.sons.equals(other.sons);
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(or ");
        for (Object o : sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                c.pddlPrint(typeInformation, bui);
            } else {
                System.out.println("Error in pddlPrint:" + this);
                System.exit(-1);
            }
        }
        bui.append(")");
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        if (this.sons != null) {
            for (Object o : this.sons) {
                if (o instanceof Condition) {
                    Condition c = (Condition) o;
                    c.storeInvolvedVariables(vars);
                } else if (o instanceof NumEffect) {
                    NumEffect c = (NumEffect) o;
                    c.storeInvolvedVariables(vars);
                } else {
                    System.out.println("Error in getting involved variables");
                }
            }

        }
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        LinkedHashSet ret = new LinkedHashSet();
        if (this.sons == null) {
            return new LinkedHashSet();
        }
        for (Condition c : (Collection<Condition>) this.sons) {
            ret.addAll(c.getTerminalConditions());
        }
        return ret;
    }

    @Override
    public Float estimate_cost (ArrayList<Float> cond_dist, boolean additive_h) {
        if (this.sons == null) {
            return 0f;
        }
        Float ret = Float.MAX_VALUE;
        for (Condition c : (Collection<Condition>) this.sons) {
            if (c.estimate_cost(cond_dist, additive_h) != Float.MAX_VALUE) {
                ret = Math.min(c.estimate_cost(cond_dist, additive_h), ret);
            }
        }
        return ret;
    }

    @Override
    public AchieverSet estimate_cost (ArrayList<Float> cond_dist, boolean additive_h, ArrayList<GroundAction> established_achiever) {
        AchieverSet s = new AchieverSet();
        s.setCost(Float.MAX_VALUE);
        if (this.sons == null) {
            s.setCost(0f);
        } else {
            for (Condition c : (Collection<Condition>) this.sons) {
                AchieverSet s1 = c.estimate_cost(cond_dist, additive_h, established_achiever);
                if (s1.getCost() != Float.MAX_VALUE) {
                    if (s.getCost() > s1.getCost()) {
                        s.setActions(s1.getActions());
                        s.setCost(s1.getCost());
                        s.getTargetCond().addAll(s1.getTargetCond());
                    }
                }
            }
        }
        return s;
    }

    @Override
    public Condition push_not_to_terminals ( ) {
        if (this.sons == null) {
            return this;
        }
        OrCond res = new OrCond();
        for (Condition c : (Collection<Condition>) this.sons) {
            c = c.push_not_to_terminals();
            res.addConditions(c);
        }

        return res;
    }

    @Override
    public ComplexCondition and (Condition precondition) {
        AndCond and = new AndCond();
        and.addConditions(precondition);
        and.addConditions(this);
        return and;
    }

    AndCond push_negation_demorgan ( ) {
        AndCond res = new AndCond();
        for (Condition c : (Collection<Condition>) this.sons) {
            NotCond nc = NotCond.createNotCond(c);
            res.addConditions(nc);
        }
        return res;
    }

    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        if (this.sons == null) {
            return true;
        }
        boolean ret = false;
        for (Condition c : (Collection<Condition>) this.sons) {
            if (c.isSatisfied(rs, dist, i)) {
                ret = true;
            }
        }
        return ret;
    }

    @Override
    public Condition introduce_red_constraints ( ) {
        if (this.sons == null) {
            return this;
        }
        OrCond ret = new OrCond();
        for (Condition c : (Collection<Condition>) this.sons) {
            ret.addConditions(c.introduce_red_constraints());
        }
        return ret;
    }

    @Override
    public void extendTerms (Variable v) {
        if (this.sons == null)
            return;
        for (Condition c : (Collection<Condition>) sons) {
            c.extendTerms(v);
        }
    }

}
