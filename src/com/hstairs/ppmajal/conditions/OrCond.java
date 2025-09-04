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
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import java.util.*;

/**
 * @author enrico
 */
public class OrCond extends ComplexCondition {


    public OrCond (Collection sons ) {
        super(sons);
    }
    
    private OrCond(Object[] input){
        super(input);
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
        for (final var c : sons ){
            Condition condInternal = ((Condition)c).normalize();
            if (condInternal.isValid()){
                this.setValid(true);
                return BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE);
            }else if (!condInternal.isUnsatisfiable()){
                if (condInternal instanceof OrCond){
                    addAll(sons1,((OrCond) condInternal).sons);
                }else{
                    sons1.add(condInternal);
                }
            }
        }
        
        return new OrCond(sons1);
    }

    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        Collection ret = new HashSet();

        for (Object o : sons) {
            Condition el = (Condition) o;
            ret.add(el.ground(substitution, po));
        }
        return new OrCond(ret);
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
    public boolean canBeTrue (RelState s) {
        for (Object o : sons) {
            Condition c = (Condition) o;
            if (c.canBeTrue(s)) {
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
        Collection ret = new HashSet();

//        ret.sons = new HashSet();
        addAll(ret,this.sons);

        return new OrCond(ret);
    }


    @Override
    public String toSmtVariableString (int i) {
        String ret = "";

        //System.out.println(this);
        if (this.sons != null) {
            if (this.sons.length > 1) {
                ret += "(or";
            }
            for (Object o : this.sons) {
                if (o instanceof BoolPredicate) {
                    BoolPredicate p = (BoolPredicate) o;
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
            if (this.sons.length > 1) {
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
                    if (nc.getSon() instanceof BoolPredicate) {
                        Condition p = nc.getSon();
                        //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                        return possibleState.satisfy(p);
                    }
                } else if (o instanceof BoolPredicate) {
                    BoolPredicate p = (BoolPredicate) o;
                    //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                    return possibleState.satisfy(p);
                }
            }
        }
        return false;
    }

    public boolean involveReacheablePredicates (Collection<BoolPredicate> possibleState) {

        if (this.sons != null) {
            for (Object o : this.sons) {
//                System.out.println("Testing with:"+o);
                if (o instanceof NotCond) {
                    NotCond nc = (NotCond) o;
                    if (nc.getSon() instanceof BoolPredicate) {
                        Condition p = nc.getSon();
                        //System.out.println(p+" satisfiable");
//System.out.println(p);
//System.out.println(";Predicate "+p+" is not possible");
                        return possibleState.contains(p);
                    }
                } else if (o instanceof BoolPredicate) {
                    BoolPredicate p = (BoolPredicate) o;
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
    public Condition weakEval (PDDLProblem s, Set invF) {
        ReferenceLinkedOpenHashSet to_keep = new ReferenceLinkedOpenHashSet();
        if (this.sons != null) {
            Iterator it = Arrays.asList(this.sons).iterator();
            while (it.hasNext()) {
                Object o2 = it.next();
                if (o2 instanceof Condition) {
                    Condition c = (Condition) o2;
                    c = c.weakEval(s, invF);
                    if (c.isValid()) {
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
        if (to_keep.isEmpty()){
            this.setValid(false);
            this.setUnsatisfiable(true);
        }

        return new OrCond(to_keep);
    }

    @Override
    public String toSmtVariableString (int k, TransitionGround gr, String var) {
        String ret = "";

        //System.out.println(this);
        if (this.sons != null) {
            if (this.sons.length > 1) {
                ret += "(or";
            }
            for (Object o : this.sons) {
                if (o instanceof BoolPredicate) {
                    BoolPredicate p = (BoolPredicate) o;
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
        Collection ret = new HashSet();
        for (final var c :  this.sons) {
            ret.add(((Condition)c).transformEquality());
        }
        return new OrCond(ret);
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
    public boolean canBeFalse (RelState s) {
        for (Object o : sons) {
            Condition c = (Condition) o;
            if (!c.canBeFalse(s)) {
                return false;
            }
        }

        return true;
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
        for (final var  c : this.sons) {
            ret.addAll(((Condition)c).getTerminalConditions());
        }
        return ret;
    }



    @Override
    public Condition pushNotToTerminals( ) {
        if (this.sons == null) {
            return this;
        }
        Collection res = new HashSet();
        for (var c : sons) {
            res.add(((Condition)c).pushNotToTerminals());
        }

        return new OrCond(res);
    }

    @Override
    public ComplexCondition and (Condition precondition) {
        Collection and = new HashSet();
        and.add(precondition);
        and.add(this);
        return new AndCond(and);
    }

    AndCond push_negation_demorgan ( ) {
        Collection res = new HashSet();
        for (final var  c :  this.sons) {
            NotCond nc = NotCond.createNotCond((Condition) c);
            res.add(nc);
        }
        return new AndCond(res);
    }

    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        if (this.sons == null) {
            return true;
        }
        boolean ret = false;
        for (final var c : sons) {
            if (((Condition)c).isSatisfied(rs, dist, i)) {
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
        Collection ret = new HashSet();
        for (final var c : this.sons) {
            ret.add(((Condition)c).introduce_red_constraints());
        }
        return new OrCond(ret);
    }

    @Override
    public void extendTerms (Variable v) {
        if (this.sons == null)
            return;
        for (final var c : sons) {
            ((Condition) c).extendTerms(v);
        }
    }

    @Override
    public Condition unifyVariablesReferences(PDDLProblem p) {
        return new OrCond(this.__unifyVariablesReferences(p));
    }

}
