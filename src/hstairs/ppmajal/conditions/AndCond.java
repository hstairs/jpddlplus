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

import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.RelState;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.TransitionGround;
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
     * @param sons
     */
    public AndCond (Collection sons ) {
        super(sons);
    }
    

    /**
     * @return a string representation of the and tree
     */
    @Override
    public String toString ( ) {
        String ret_val = "(AND ";
        for (final Object o : sons) {
            ret_val = ret_val.concat(o.toString());
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
        for (var o : this.sons) {
            if (o instanceof Condition c) {
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
    public boolean canBeTrue (RelState s) {
        for (final Object o : this.sons) {
            if (o instanceof Condition) {
                final Condition c = (Condition) o;
                if (!c.canBeTrue(s)) {
                    //System.out.println(c.pddlPrint() + " is not satisfied in " + s);
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public String pddlPrintWithExtraObject ( ) {
        String ret_val = "(and ";
        for (Object o : sons) {
            if (o instanceof Condition c) {
                ret_val = ret_val.concat(c.pddlPrintWithExtraObject());
            } else if (o instanceof Comparison comp) {
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
        AndCond ret = new AndCond(cloneSons());
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
                if (!c.canBeTrue(s)) {
                    //System.out.println(c.pddlPrint() + " is not satisfied in " + s);
                    return c;
                }
            }
        }
        return null;
    }

  




    public PDDLState transformInStateIfPossible ( ) {
        PDDLState ret = new PDDLState();
        for (Object o : this.sons) {
            if (o instanceof BoolPredicate) {
                ret.setPropFluent((BoolPredicate) o, true);
            } else {
                System.out.println("This AndCond cannot be transformed into a State");
                return null;
            }
        }
        return ret;
    }





    @Override
    public String toSmtVariableString (int i) {

        String ret = "";
        if (this.sons != null) {
            if (this.sons.length > 1) {
                ret += "(and";
            }

            for (Object o : this.sons) {
                if (o instanceof BoolPredicate) {
                    BoolPredicate p = (BoolPredicate) o;
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
            if (this.sons.length > 1) {
                ret += ")";
            }
        }
        return ret;
    }


    @Override
    public Condition weakEval (PDDLProblem s, Set invF) {
        HashSet to_keep = new LinkedHashSet();

        if (this.sons != null) {
            Collection ret = Arrays.asList(this.sons);
            Iterator it = ret.iterator();
            while (it.hasNext()) {
                Object o2 = it.next();
                if (o2 instanceof Condition) {
                    Condition c = (Condition) o2;
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

        return new AndCond(to_keep);
    }


    @Override
    public String toSmtVariableString (int i, TransitionGround gr, String var) {
        String ret = "";
        if (this.sons != null) {
            if (this.sons.length > 1) {
                ret += "(and";
            }

            for (Object o : this.sons) {
                if (o instanceof BoolPredicate) {
                    BoolPredicate p = (BoolPredicate) o;
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
            if (this.sons.length > 1) {
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
        Collection newSons = new HashSet();
        
        for (Object c1 :  this.sons) {
            if (c1 instanceof Condition){
                Condition res = ((Condition) c1).transformEquality();
                if (res instanceof AndCond) {
                    for (Object o: ((AndCond)res).sons){
                        newSons.add(o);
                    }
                } else {
                    newSons.add(c1);
                }
            }

        }
        return new AndCond(newSons);
    }



    @Override
    public boolean canBeFalse (RelState s) {
        for (Object o : this.sons) {
            if (o instanceof Condition) {
                Condition c = (Condition) o;
                if (c.canBeFalse(s)) {
                    //System.out.println(c.pddlPrint() + " is not satisfied in " + s);
                    return true;
                }
            }
        }
        return false;
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
    public ComplexCondition and (Condition precondition) {
        final Collection res = new HashSet();
        res.add(precondition);
        for (Object o: this.sons){
            res.add(o);
        }
        return new AndCond(res);
    }

    @Override
    public Condition pushNotToTerminals( ) {
        if (this.sons == null) {
            return this;
        }
        Collection res = new HashSet();
        for (Object c : this.sons) {
            if (c instanceof Condition){
                final Condition c1 = ((Condition)c).pushNotToTerminals();
                if (c1 instanceof AndCond) {
                    addAll(res, ((AndCond)c1).sons);
                } else {
                    res.add(c1);
                }
            }
        }
        return new AndCond(res);
    }

    OrCond push_negation_demorgan ( ) {
        
        Collection res = new HashSet();
        for (final Object c : this.sons) {
            NotCond nc = NotCond.createNotCond((Condition)c);
            res.add(nc);
        }
        return new OrCond(res);
    }

    @Override
    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        if (this.sons == null) {
            return true;
        }
        boolean ret = true;
        for (final Object c : this.sons) {
            if (!((Condition)c).isSatisfied(rs, dist, i)) {
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
//        AndCond ret = new AndCond();
        Collection ret = new HashSet();
        for (final Object c : this.sons) {
            if (c instanceof Comparison) {
                to_mix.add((Comparison) c);
            }
            ret.add(((Condition)c).introduce_red_constraints());
        }
        for (int i = 0; i < to_mix.size(); i++) {
            for (int j = i + 1; j < to_mix.size(); j++) {
                try {
                    final Comparison a1 = to_mix.get(i);
                    final Comparison a2 = to_mix.get(j);
                    final Comparison newC = generateRedConstraints(a1, a2);
                    if (newC != null) {
                        ret.add(newC);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AndCond.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return new AndCond(ret);
    }


    @Override
    public Condition normalize() {
        final HashSet sons1 = new LinkedHashSet();
        for (final Object cond : sons) {
            if (cond instanceof Condition) {
                Condition condInternal = ((Condition) cond).normalize();
                if (condInternal == null) {
                    //this.setUnsatisfiable(true);
                } else {
                    if (condInternal.isUnsatisfiable()) {
                        this.setUnsatisfiable(true);
                        sons1.add(condInternal);
                    } else if (!condInternal.isValid()) {
                        if (condInternal instanceof AndCond) {
                            addAll(sons1,sons);
                        } else {
                            sons1.add(condInternal);
                        }
                    }
                }
            }else if (cond instanceof NumEffect){
                ((NumEffect) cond).setRight(((NumEffect)cond).getRight().normalize());
                sons1.add(cond);
            }
        }
        return new AndCond(sons1);
    }

    public static Comparison generateRedConstraints(Comparison a1, Comparison a2) {
        ExtendedNormExpression lhs_a1 = (ExtendedNormExpression) a1.getLeft();
        ExtendedNormExpression lhs_a2 = (ExtendedNormExpression) a2.getLeft();
        ExtendedNormExpression expr = lhs_a1.sum(lhs_a2);
        String new_comparator = ">=";
        if (!a1.getComparator().equals(a2.getComparator())) {
            new_comparator = ">=";
        } else {
            new_comparator = a1.getComparator();
        }

        Comparison newC = (Comparison) Comparison.comparison(new_comparator, expr.normalize(), new ExtendedNormExpression(0d), false).normalize();
        if (newC == null) {
            return null;
        }
        ExtendedNormExpression tempLeft = (ExtendedNormExpression) newC.getLeft();

        if (tempLeft.summations.size() < 2) {
            return null;
        }
        return newC;
    }


    @Override
    public Condition unifyVariablesReferences(PDDLProblem p) {
        return new AndCond(this.__unifyVariablesReferences(p));
    }


}
