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

import com.hstairs.ppmajal.conditions.Predicate.trueFalse;
import com.hstairs.ppmajal.expressions.Expression;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Enrico Scala
 */
public class AndCond extends ComplexCondition implements PostCondition {

    private boolean specialAndForExpression;

    /**
     * Standard Constructor for the AndCond.
     */
    public AndCond ( ) {
        super();
    }

    /**
     * @return a string representation of the and tree
     */
    @Override
    public String toString ( ) {
        String ret_val = "(AND ";
        for (Object o : sons) {
            if (o != null){
                ret_val = ret_val.concat(o.toString());
            }
        }
        ret_val = ret_val.concat(")");
        return ret_val;
    }


    /**
     * @param s
     * @return
     */
    @Override
    public boolean eval (State s) {
        for (Object o : this.sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                if (!c.eval(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return the specialAndForExpression
     */
    public boolean isSpecialAndForExpression ( ) {
        return specialAndForExpression;
    }

    /**
     * @param specialAndForExpression the specialAndForExpression to set
     */
    public void setSpecialAndForExpression (boolean specialAndForExpression) {
        this.specialAndForExpression = specialAndForExpression;
    }

    /**
     * @param e when used with numeric effect
     */
    public void addExpression (Expression e) {
        this.sons.add(e);
    }

    /**
     * @param s
     * @return
     */
    @Override
    public boolean isSatisfied (State s) {
        for (final Object o : this.sons) {
            if (o instanceof Condition) {
                final Condition c = (Condition) o;
                if (!c.isSatisfied(s)) {
                    //System.out.println(c.toString() + " is not satisfied in " + s);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public boolean can_be_true (RelState s) {
        for (final Object o : this.sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                if (!c.can_be_true(s)) {
                    //System.out.println(c.pddlPrint() + " is not satisfied in " + s);
                    return false;
                }
            }
        }
        return true;
    }


    public String pddlPrintWithExtraObject ( ) {
        String ret_val = "(and ";
        for (Object o : sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                ret_val = ret_val.concat(c.pddlPrintWithExtraObject());
            } else if (o instanceof Comparison) {
                Comparison comp = (Comparison) o;
                ret_val = ret_val.concat(comp.pddlPrintWithExtraObject());
            } else if (o instanceof NumEffect) {
                System.out.println("Error in pddlPrint:" + this);
                System.exit(-1);
            } else{
                throw new RuntimeException("This is not supported in this condition"+o);
            }
        }
        ret_val = ret_val.concat(")");
        return ret_val;
    }

    /**
     * @return
     */
    @Override
    public Condition clone ( ) {
        AndCond ret = new AndCond();
        ret.grounded = this.grounded;
        //ret.sons = (HashSet)this.sons.clone();
        ret.sons = new LinkedHashSet();

        for (Object o : this.sons) {
            if (o instanceof AndCond) {
                AndCond a = (AndCond) o;
                ret.sons.add(a.clone());
            } else if (o instanceof NotCond) {
                NotCond a = (NotCond) o;
                ret.sons.add(a.clone());
            } else if (o instanceof OrCond) {
                OrCond a = (OrCond) o;
                ret.sons.add(a.clone());
            } else if (o instanceof Predicate) {
                Predicate a = (Predicate) o;
                ret.sons.add(a.clone());
            } else if (o instanceof Comparison) {
                Comparison a = (Comparison) o;
                ret.sons.add(a.clone());
            } else if (o instanceof NumEffect) {
                NumEffect a = (NumEffect) o;
                ret.sons.add(a.clone());
            } else if (o instanceof ConditionalEffect) {
                ConditionalEffect a = (ConditionalEffect) o;
                ret.sons.add(a.clone());
            } else if (o instanceof ForAll) {
                ForAll a = (ForAll) o;
                ret.sons.add(a.clone());
            }
        }

        ret.specialAndForExpression = this.specialAndForExpression;
        return ret;

    }

    /**
     * @param s
     * @return
     */
    public Condition whatisnotSatisfied (RelState s) {
        for (Object o : this.sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                if (!c.can_be_true(s)) {
                    //System.out.println(c.pddlPrint() + " is not satisfied in " + s);
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * @param delList
     */
    public void subtractElements (AndCond delList) {
        if (delList == null) {
            return;
        }
        Iterator it2 = delList.sons.iterator();
        while (it2.hasNext()) {
            NotCond nc = (NotCond) it2.next();
            Iterator it = this.sons.iterator();
            while (it.hasNext()) {
                //NotCond nc = (NotCond)it.next();
                //System.out.println("Confronto : " + o.getClass());
                if (it.next().equals(nc.getSon())) {
                    it.remove();
                }
            }
        }
    }

    /**
     * @param addList
     */
    public void subtractNegation (AndCond addList) {
        if (addList == null) {
            return;
        }
        Iterator it2 = addList.sons.iterator();
        while (it2.hasNext()) {
            Object o = it2.next();
            Iterator it = this.sons.iterator();

            while (it.hasNext()) {
                NotCond nc = (NotCond) it.next();
                //System.out.println("Confronto : " + o.getClass());
                if (o.equals(nc.getSon())) {
                    it.remove();
                }
            }
        }
    }


    public PDDLState transformInStateIfPossible ( ) {
        PDDLState ret = new PDDLState();
        for (Object o : this.sons) {
            if (o instanceof Predicate) {
                ret.setPropFluent((Predicate) o, true);
            } else {
                System.out.println("This AndCond cannot be transformed into a State");
                return null;
            }
        }
        return ret;
    }


    public Condition requireAnInstanceOf (Condition con) {

        for (Object o : this.sons) {
            //System.out.println("testing "+con+" with"+o);
            if (con.isUngroundVersionOf((Condition) o)) {
                //System.out.println("ok");
                return (Condition) o;
            }
        }
        return null;

    }

    public Predicate requireAnInstanceOfAndWhichis (Condition con) {

        for (Object o : this.sons) {
            //System.out.println("testing "+con+" with"+o);
            if (con.isUngroundVersionOf((Condition) o)) {
                //System.out.println("ok");
                return (Predicate) o;
            }
        }
        return null;

    }


    @Override
    public String toSmtVariableString (int i) {

        String ret = "";
        if (this.sons != null) {
            if (this.sons.size() > 1) {
                ret += "(and";
            }

            for (Object o : this.sons) {
                if (o instanceof Predicate) {
                    Predicate p = (Predicate) o;
                    ret += " " + p.toSmtVariableString(i);
                } else if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    ret += " " + nc.toSmtVariableString(i);
                } else if (o instanceof Condition) {
                    Condition c = (Condition) o;
                    if (c.toSmtVariableString(i) != null) {
                        ret += c.toSmtVariableString(i);
                    }

                } else if (o instanceof NumEffect) {
                    NumEffect neff = (NumEffect) o;
                    ret += neff.toSmtVariableString(i);
                }
            }
            if (this.sons.size() > 1) {
                ret += ")";
            }
        }
        return ret;
    }


    @Override
    public Condition weakEval (PddlProblem s, HashMap invF) {
        HashSet to_keep = new LinkedHashSet();

        if (this.sons != null) {
            Iterator it = this.sons.iterator();
            while (it.hasNext()) {
                Object o2 = it.next();
                if (o2 instanceof Condition) {
                    Condition c = (Condition) o2;
                    c.setFreeVarSemantic(this.freeVarSemantic);
                    c = c.weakEval(s, invF);
                    if (c != null && this instanceof PostCondition){
                        if (c.isValid()) {
                            //not keeping as will always be sat)
                        } else if (c.isUnsatisfiable()) {
                            this.setUnsatisfiable(true);
                            this.setValid(false);
                            return this;
                        } else {
                            to_keep.add(c);
                        }
                    }
                } else if (o2 instanceof NumEffect) {
                    NumEffect ne = (NumEffect) o2;
                    ne.setFreeVarSemantic(freeVarSemantic);
                    ne = (NumEffect) ne.weakEval(s, invF);
                    if (ne == null) {
                        return null;
                    }
                    to_keep.add(o2);
                } else {
                    System.out.println("Unsupported operation for :" + o2.getClass());
                }
            }

        }
        this.sons = to_keep;

        return this;
    }


    @Override
    public String toSmtVariableString (int i, GroundAction gr, String var) {
        String ret = "";
        if (this.sons != null) {
            if (this.sons.size() > 1) {
                ret += "(and";
            }

            for (Object o : this.sons) {
                if (o instanceof Predicate) {
                    Predicate p = (Predicate) o;
                    ret += " " + p.toSmtVariableString(i, gr, var);
                } else if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    ret += " " + nc.toSmtVariableString(i, gr, var);
                } else if (o instanceof Condition) {
                    Condition c = (Condition) o;
                    if (c.toSmtVariableString(i, gr, var) != null) {
                        ret += c.toSmtVariableString(i, gr, var);
                    }

                } else {
                    throw new UnsupportedOperationException("Num effect not supported for repetition.." + this);
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
        AndCond ret = new AndCond();
        for (Condition c1 : (Collection<Condition>) this.sons) {
            Condition res = c1.transformEquality();
            if (res instanceof AndCond) {

                ret.sons.addAll(((AndCond) res).sons);
            } else {
                ret.addConditions(res);
            }

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
        AndCond con = new AndCond();
        for (Object o : this.sons) {
            if (o instanceof Condition) {
                Condition t = (Condition) o;
                Condition temp = t.regress(gr);
//                System.out.println(temp);
                if (!temp.isValid()) {//needs to be satisfied
                    if (temp.isUnsatisfiable()) {
                        return Predicate.createPredicate(Predicate.trueFalse.FALSE);
                    } else if (temp instanceof AndCond) {
                        con.sons.addAll(((AndCond) temp).sons);
                    } else {
                        con.sons.add(temp);
                    }
                }//else do not add the condition at all
//                System.out.println("DEBUG: Condition before regression"+t);
//                System.out.println("DEBUG: Condition after regression"+temp);

            } else {
                System.out.println("AndCond: Condition " + o + " cannot be regressed");
                System.exit(-1);
            }
        }
//        System.out.println(this);

        if (con.sons.isEmpty()) {
//            System.out.println("Always valid");
            return Predicate.createPredicate(Predicate.trueFalse.TRUE);
        }
        return con;
    }

    @Override
    public boolean can_be_false (RelState s) {
        for (Object o : this.sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                if (c.can_be_false(s)) {
                    //System.out.println(c.pddlPrint() + " is not satisfied in " + s);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Condition achieve (Predicate p) {
        for (Object p1 : this.sons) {
            if (p1 instanceof Predicate) {
                if (p1.equals(p)) {
                    return Predicate.createPredicate(Predicate.trueFalse.TRUE);
                }
            }
        }
        return null;

    }

    @Override
    public Condition delete (Predicate p) {
        for (Object p1 : this.sons) {
            if (p1 instanceof NotCond) {
                NotCond nc = (NotCond) p1;
                Predicate p2 = (Predicate) nc.getSon();
                if (p2.equals(p)) {
                    return Predicate.createPredicate(Predicate.trueFalse.TRUE);
                }
            }
        }
        return null;
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
        for (Object o : this.sons) {
//            if ((o instanceof AndCond) 
//                    || (o instanceof Predicate)
//                    || (o instanceof NotCond)
//                    || (o instanceof NumEffect)
//                    || (o instanceof ConditionalEffect)
//                    ) {
            if (o instanceof PostCondition) {
                final PostCondition pc = (PostCondition) o;
                pc.apply(s, modifications);
            } else {
                sonHasIncorrectType(o);
            }
        }
    }

    @Override
    public void apply (RelState s, Map modifications) {
        for (Object o : this.sons) {
//            if ((o instanceof AndCond) 
//                    || (o instanceof Predicate)
//                    || (o instanceof NotCond)
//                    || (o instanceof NumEffect)
//                    || (o instanceof ConditionalEffect)
//                    ) {
            if (o instanceof PostCondition) {
                final PostCondition pc = (PostCondition) o;
                pc.apply(s, modifications);
            } else {
                sonHasIncorrectType(o);
            }
        }
    }


    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(and ");
        for (Object o : sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                c.pddlPrint(typeInformation, bui);
//            } else if (o instanceof Comparison) { // ??? a Comparison is already a Conditions
//                Comparison comp = (Comparison) o;
//                ret_val = ret_val.concat(comp.pddlPrint(typeInformation));
            } else if (o instanceof NumEffect) {
                ((NumEffect) o).pddlPrint(typeInformation,bui);
            } else{
                throw new RuntimeException("This is not supported in this condition: "+o);
            }
        }
        bui.append(")");
    }


    @Override
    public Float estimate_cost (ArrayList<Float> cond_dist, boolean additive_h) {
        if (this.sons == null) {
            return 0f;
        }
        Float ret = 0f;
        for (Condition c : (Collection<Condition>) this.sons) {
            Float estimate = c.estimate_cost(cond_dist, additive_h);
            if (estimate == Float.MAX_VALUE) {
                return Float.MAX_VALUE;
            }
            if (additive_h) {
                ret += estimate;
            } else {
                ret = Math.max(estimate, ret);
            }
        }
        return ret;
    }

    @Override
    public ComplexCondition and (Condition precondition) {
        AndCond and = new AndCond();
        and.addConditions(precondition);
        and.sons.addAll(this.sons);
        return and;
    }

    @Override
    public AchieverSet estimate_cost (ArrayList<Float> cond_dist, boolean additive_h, ArrayList<GroundAction> established_achiever) {
        AchieverSet s = new AchieverSet();
        if (this.sons == null) {
            s.setCost(0f);
        } else {
            s.setCost(0f);
            for (Condition c : (Collection<Condition>) this.sons) {
                AchieverSet s1 = c.estimate_cost(cond_dist, additive_h, established_achiever);
                if (s1.getCost() == Float.MAX_VALUE) {
                    s.setCost(Float.MAX_VALUE);
                    return s;
                }
                if (additive_h) {
                    s.setCost(s.getCost() + s1.getCost());

                    s.getActions().addAll(s1.getActions());
                    s.getTargetCond().addAll(s1.getTargetCond());
                } else {
                    s.setCost(Math.max(s1.getCost(), s.getCost()));
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
        AndCond res = new AndCond();
        for (Condition c : (Collection<Condition>) this.sons) {
            Condition c1 = c.push_not_to_terminals();
            if (c1 instanceof AndCond) {
                res.sons.addAll(((AndCond) c1).sons);
            } else {
                res.addConditions(c1);
            }
        }
        return res;
    }

    OrCond push_negation_demorgan ( ) {
        OrCond res = new OrCond();
        for (Condition c : (Collection<Condition>) this.sons) {
            NotCond nc = NotCond.createNotCond(c);
            res.addConditions(nc);
        }
        return res;
    }

    @Override
    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        if (this.sons == null) {
            return true;
        }
        boolean ret = true;
        for (Condition c : (Collection<Condition>) this.sons) {
            if (!c.isSatisfied(rs, dist, i)) {
                ret = false;
            }
        }
        return ret;
    }

    //    @Override
//    public Conditions unify_num_fluent(PDDLState init) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public Condition introduce_red_constraints ( ) {
        ArrayList<Comparison> to_mix = new ArrayList();
        AndCond ret = new AndCond();
        for (Condition c : (Collection<Condition>) this.sons) {
            if (c instanceof Comparison) {
                to_mix.add((Comparison) c);
            }
            ret.addConditions(c.introduce_red_constraints());
        }
        for (int i = 0; i < to_mix.size(); i++) {
            for (int j = i + 1; j < to_mix.size(); j++) {
                try {
                    Comparison a1 = to_mix.get(i);
                    Comparison a2 = to_mix.get(j);
                    ExtendedNormExpression lhs_a1 = (ExtendedNormExpression) a1.getLeft();
                    ExtendedNormExpression lhs_a2 = (ExtendedNormExpression) a2.getLeft();
                    ExtendedNormExpression expr = lhs_a1.sum(lhs_a2);
                    String new_comparator = ">=";
                    if (!a1.getComparator().equals(a2.getComparator())) {
                        new_comparator = ">=";
                    } else {
                        new_comparator = a1.getComparator();
                    }

                   
                    Comparison newC = (Comparison) Comparison.createComparison(new_comparator,expr.normalize(),new ExtendedNormExpression(0d),false).normalize();
                    

                    ExtendedNormExpression tempLeft = (ExtendedNormExpression) newC.getLeft();

                    if (tempLeft.summations.size() < 2) {
                        continue;
                    }
                    ret.addConditions(newC);

                } catch (Exception ex) {
                    Logger.getLogger(AndCond.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ret;
    }


    @Override
    public Set<NumFluent> affectedNumericFluents ( ) {
        Set<NumFluent> ret = new LinkedHashSet();
        if (this.sons.isEmpty()) {
            return ret;
        } else {

            for (Object con : this.sons) {
                if (con instanceof NumEffect) {
                    NumEffect temp = (NumEffect) con;
                    ret.add(temp.getFluentAffected());
                }
            }
        }
        return ret;
    }


    @Override
    public boolean isUngroundVersionOf (Condition conditions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition normalize() {
        HashSet sons1 = new LinkedHashSet();
        for (final Object cond : (Collection<Object>)sons ){
            if (cond instanceof Condition){
                Condition condInternal = ((Condition)cond).normalize();
                if (condInternal != null){
                    if (condInternal.isUnsatisfiable()){
                        this.setUnsatisfiable(true);
                        sons1.add(condInternal);
                    }else if (!condInternal.isValid()){
                        if (condInternal instanceof AndCond){
                            sons1.addAll(((AndCond) condInternal).sons);
                        }else{
                            sons1.add(condInternal);
                        }
                    }
                }
            }else if (cond instanceof NumEffect){
                ((NumEffect) cond).setRight(((NumEffect)cond).getRight().normalize());
                sons1.add(cond);
            }
        }
        sons = sons1;
        return this;
    }
}
