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

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.PDDLProblem.PDDLObjects;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;

/**
 * @author enrico
 */
public class BoolPredicate extends Terminal implements PostCondition {

    public static BoolPredicate getPredicate(String a) {
        return getPredicate(a, null);
    }

    final private String name;
    final private List variables;
    final private static BoolPredicate truePredicate = new BoolPredicate(0);
    final private static BoolPredicate falsePredicate = new BoolPredicate(1);

    private BoolPredicate (String name, List variables) {
        super();
        this.name = name;
        this.variables = variables;
    }

    private static HashMap<Pair<String,ArrayList>,BoolPredicate> predicates;

    
    public static BoolPredicate BoolFluent(String name){
        return getPredicate(name, Collections.EMPTY_LIST);
    }
    
    public static BoolPredicate getPredicate(String name, List vars){
        if (predicates == null){
            predicates = new HashMap();
        }
        Pair pair = new Pair(name,vars);
        BoolPredicate predicate = predicates.get(pair);
        if (predicate == null){
            predicate = new BoolPredicate(name,vars);
            predicates.put(pair,predicate);
        }
        return predicate;
    }
    
    
    public static BoolPredicate getPredicate(trueFalse input){
        if (input == trueFalse.TRUE) {
            return truePredicate;
        } else {
            return falsePredicate;
        }
    }

    //This is a mere convention
    private BoolPredicate(int id) {
        super();
        if (id == 0){
            this.name = "TRUE";
            this.setValid(true);
        }else{
            this.name = "FALSE";
            this.setUnsatisfiable(true);
        }
        variables = null;
    }

    public static Map<Pair<String,ArrayList>,BoolPredicate>  getPredicatesDB() {
        if (predicates == null) {
            return Collections.EMPTY_MAP;
        }
        return predicates;
    }

    
    

    @Override
    public String pddlPrintWithExtraObject ( ) {
        String ret = "";
        ret = ret.concat("  (" + this.getName());
        for (Object o1 : this.getTerms()) {
            if (o1 instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o1;
                ret = ret.concat(" " + obj.getName());
            } else {
                Variable obj = (Variable) o1;
                ret = ret.concat(" " + obj.getName());

            }

        }
        ret = ret.concat(" ?interpr)");
        return ret;
    }

    public String pddlPrintWithTypedExtraObject ( ) {
        String ret = "";
        ret = ret.concat("  (" + this.getName());
        for (Object o1 : this.getTerms()) {
            if (o1 == null) {
                System.out.println("There is some problem here with predicate:" + this);
            }
            if (o1 instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o1;
                ret = ret.concat(" " + obj.getName());
            } else {
                Variable obj = (Variable) o1;
//                System.out.println("DEBUG: obj"+obj+"In the context:"+this);
                ret = ret.concat(" " + obj.getName() + obj.getType());

            }

        }
        ret = ret.concat(" ?x - interpretation)");
        return ret;
    }

    @Override
    public boolean canBeFalse (RelState s) {
        if (s.possNumValues.isEmpty())
            return true;
        if (this.isValid())
            return false;
        if (this.isUnsatisfiable())
            return true;

        Integer i = s.possBollValues.get(this.getId());
        return (i == null) || (i == 0) || (i == 2);
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        return Sets.newHashSet(this);
    }


    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<BoolPredicate> getInvolvedPredicates ( ) {
        LinkedHashSet ret = new LinkedHashSet();
        ret.add(this);
        return ret;
    }


    public String getName ( ) {
        return name;
    }



    @Override
    public String toString ( ) {
        
        String ret = "";
        ret += "(" + this.name;
        if (this.getTerms() != null) {
            for (Object o1 : this.getTerms()) {
                if (o1 == null) {
                    throw new RuntimeException("Null object found at the level "
                            + "of " + this.name);
                }

                if (o1 instanceof PDDLObject) {
                    PDDLObject obj = (PDDLObject) o1;
                    ret = ret.concat(" " + obj.getName());
                } else {
                    Variable obj = (Variable) o1;
                    ret = ret.concat(" " + obj.getName() + obj.getType());

                }

            }
        }
        ret = ret.concat(")");
        return ret;
    }

    /**
     * @return the terms
     */
    public List getTerms ( ) {
        return variables;
    }


    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        ArrayList localVariables = new ArrayList();
        //System.out.println(this);
        if (substitution.isEmpty()){
            return this;
        }
        for (Object o : variables) {
            if (o instanceof Variable) {
                final Variable v = (Variable) o;
                PDDLObject t = substitution.get(v);
                if (t == null) {
                    localVariables.add(v);
//                    System.out.println("Error in substitution  for " + o);
//                    System.exit(-1);
                } else {
                    localVariables.add(t);
                }
            } else {
                localVariables.add(o);
            }
        }
        BoolPredicate createPredicate = BoolPredicate.getPredicate(name, localVariables);
//        System.out.println(BoolFluent);
        return createPredicate;
    }


    @Override
    public boolean eval (State s) {
        return ((PDDLState) s).holds(this);
    }

    @Override
    public boolean isSatisfied (State s) {
        if (isValid()) {
            return true;
        }
        if (isUnsatisfiable()) {
            return false;
        }
        return ((PDDLState) s).holds(this);
    }

    @Override
    public boolean canBeTrue (RelState s) {
        if (this.isValid())
            return true;
        if (this.isUnsatisfiable())
            return false;
        if (!s.possBollValues.isEmpty()) {
            int i = s.possBollValues.getOrDefault(this.getId(), -1);
            if (i == -1) {
                return false;
            }
            return i >= 1;
        } else {
            return false;
        }
        //return (i == 1) || (i == 2);
    }

    

    public PDDLState remove (PDDLState s) {
        s.setPropFluent(this, false);
        return s;
    }


    @Override
    public Condition clone ( ) {
        return this;
    }

    public RelState make_positive (RelState s) {
        s.makePositive(this);
        return s;
    }

    RelState make_negative (RelState s) {
        s.makeNegative(this);
        return s;
    }

    @Override
    public Condition normalize ( ) {
        return this;
    }

    @Override
    public String toSmtVariableString (int i) {

        if (this.isValid()) {
            return "true";
        }
        if (this.isUnsatisfiable()) {
            return "false";
        }

        String ret = "";
        ret = ret.concat(this.getName());
        for (Object o1 : this.getTerms()) {
            if (o1 instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o1;
                ret = ret.concat("" + obj.getName());
            } else {
                Variable obj = (Variable) o1;
                ret = ret.concat("" + obj.getName());

            }

        }
        ret += "-" + i;
        //ret = ret.concat(")");
//        System.out.println(ret);
//        System.out.println(ret.replaceAll("\\s+",""));
        return ret;
//        return ret.replaceAll("\\s+","");
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        return new HashSet();
    }

    @Override
    public Condition weakEval (PDDLProblem problem, Set invF) {
        //if it is a static predicate (not invariant) and is satisfied in the init state,
        //then remove it in the upper level since it is valid for any state
        if (this.isValid()){
            return this;
        }
        if (this.isUnsatisfiable()){
            return this;
        }
        
        if (!invF.contains(this)) {//this means it is a static predicate
            if (problem.getInitBoolFluentValue(this)) {
                this.setValid(true);
                this.setUnsatisfiable(false);
            } else {
                this.setValid(false);
                this.setUnsatisfiable(true);
            }
            return this;
        } else {//this is meant to couple all the predicates in a unique representation
            return this;//problem.BoolFluent(this);
        }
    }

    @Override
    public String toSmtVariableString (int k, TransitionGround gr, String var) {
        return " true ";
    }

    @Override
    public Condition transformEquality ( ) {
        return this;
    }


    

    @Override
    public HashMap apply (PDDLState s) {
        HashMap ret = new HashMap();
        apply(s, ret);
        return ret;
    }

    @Override
    public HashMap<Object, Object> apply (RelState s) {
        HashMap ret = new HashMap();
        apply(s, ret);
        return ret;
    }

    @Override
    public void apply (State s, Map modifications) {
        modifications.put(this, Boolean.TRUE);
    }

    @Override
    public void apply (RelState s, Map modifications) {
        Integer inter = s.possBollValues.get(this.getId());
        if (inter == null || inter == 0) {
            modifications.put(this, 2);
        }
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append(" (");
        bui.append(getName());
        if (this.getTerms() != null) {
            for (Object o1 : this.getTerms()) {
                if (o1 instanceof PDDLObject) {
                    PDDLObject obj = (PDDLObject) o1;
                    bui.append(" ");
                    bui.append(obj.getName()); // TODO: Why not obj.pddlPrint(typeInformation,bui) ?
                } else {
                    Variable obj = (Variable) o1;
                    bui.append(" ");
                    bui.append(obj.getName());
                    if (typeInformation) {
                        bui.append(obj.getType()); // No space?
                    }
                }
            }
        }
        bui.append(")");
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        vars.addAll(this.variables);
    }

    @Override
    public ComplexCondition and (Condition precondition) {
        Collection and = new HashSet();
        and.add(precondition);
        and.add(this);
        return new AndCond(and);
    }


//    @Override
//    public AchieverSet estimate_cost (ArrayList<Float> cond_dist, boolean additive_h, ArrayList<TransitionGround> established_achiever) {
//        AchieverSet s = new AchieverSet();
//        s.setCost(cond_dist.get(this.id));
//        s.getActions().add(established_achiever.get(this.id));
//        s.getTargetCond().add(this);
//        return s;
//
//    }

    @Override
    public Condition pushNotToTerminals( ) {
        return this;
    }

    public boolean isGrounded() {
        for (var v: this.variables){
            if (v instanceof Variable){
                return false;
            }
        }
        return true;
    }

    public enum trueFalse {
        TRUE, FALSE
    }

}
