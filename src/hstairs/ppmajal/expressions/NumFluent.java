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

import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.ActionParameter;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.problem.*;
import java.util.*;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Enrico Scala
 */
public class NumFluent extends Expression {

    Integer cachedHashCode;
    final private String name;
    final private List<ActionParameter> terms;
    public static ArrayList<NumFluent> fromIdToNumFluents;
    private Boolean has_to_be_tracked;
    private String terms_as_string;
    final private int id;

    public static HashMap<Pair<String,ArrayList>,NumFluent> numFluentsBank;
    public static NumFluent getNumFluent(String name, List variables){
        return createNumFluent(name,variables,false);
    }
    public static NumFluent numericFluent(String name){
        return createNumFluent(name,Collections.EMPTY_LIST,false);
    }
    public static NumFluent createNumFluent(String name, List variables, boolean groundAlready){
        if (numFluentsBank == null){
            numFluentsBank = new HashMap();
            fromIdToNumFluents = new ArrayList();
        }
        Pair pair = Pair.of(name,variables);
        NumFluent ret = numFluentsBank.get(pair);
        if (ret == null){
            ret = new NumFluent(name,variables,numFluentsBank.entrySet().size());
            fromIdToNumFluents.add(ret.getId(),ret);
            ret.grounded = groundAlready;
            numFluentsBank.put(pair, ret);            


        }
        return ret;
    }
            
    
    private NumFluent (String name, List variables, int id) {
        super();
        this.name = name;
        terms = variables;
        this.id = id;
        has_to_be_tracked = false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
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
        final NumFluent other = (NumFluent) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

    public void addVariable (Variable variable) {
        terms.add(variable);
    }

    @Override
    public String toString ( ) {
        String ret = "";
        ret += "(" + this.name;
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
        ret = ret.concat(")");
        return ret;
    }

    @Override
    public NumFluent ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        ArrayList variables = new ArrayList();
        for (final ActionParameter param : terms) {
            variables.add(param.ground(substitution));
        }
        NumFluent ret;
        ret = getNumFluent(name, variables);
        ret.grounded = true;
        terms_as_string = this.terms.toString();
        return ret;
    }

    @Override
    public Expression unGround (Map substitution) {
        ArrayList variables = new ArrayList();

        for (Object o : terms) {
            if (o instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o;
                Variable t = (Variable) substitution.get(obj.getName());
                if (t == null) {
                    System.out.println("Substitution Failed for " + o.toString());
                    System.exit(-1);
                } else {
                    variables.add(t);
                }
            } else {
                variables.add(o);
            }
        }
        NumFluent ret  = getNumFluent(name,variables);
        ret.grounded = false;
        return ret;
    }

    /**
     * @return the terms
     */
    public List<ActionParameter> getTerms ( ) {
        return terms;
    }


    public void addTerms (PDDLObject el) {
        terms.add(el);
    }

    /**
     * @return the name
     */
    public String getName ( ) {
        return name;
    }

    //    public void setName(String name) {
//        this.name = name;
//    }
    @Override
    public double eval (State s) {
        if (s == null) {
            throw new RuntimeException("State " + s + " is null at this stage. Bug");
        }
        final double d = ((PDDLState) s).fluentValue(this);
        if (d == Double.NaN) {
            return Double.NaN;
        }
        return d;
    }

    @Override
    public HomeMadeRealInterval eval (RelState s) {
        if (Double.isNaN(s.functionInfValue(this))) {
            return null;
        }
        HomeMadeRealInterval ret = new HomeMadeRealInterval(s.functionInfValue(this),s.functionSupValue(this));


        return ret;
    }

    @Override
    public ExtendedNormExpression normalize ( ) {
        ExtendedAddendum a = new ExtendedAddendum();
        a.n = 1d;
        a.f = this;
        ExtendedNormExpression ret = new ExtendedNormExpression();
        ret.summations.add(a);
        return ret;

    }

 

    @Override
    public Expression weakEval (PDDLProblem problem, Set invF) {

        if (this.name.equals("#t")) {
            //return this;
            return problem.getNumFluentInitialValue(this);
        }

        if ((!invF.contains(this))) {//this means that the fluent cannot be in principle assigned
            PDDLNumber o = problem.getNumFluentInitialValue(this);
            if (o == null && this.freeVarSemantic) {
                return this;
            }
            return problem.getNumFluentInitialValue(this);
        } else {
            if ( invF.contains(this)) {
                return this;
            } else {
                return problem.getNumFluentInitialValue(this);
            }
        }
    }

    @Override
    public Expression clone ( ) {
        return this;
    }

    @Override
    public boolean involve (Collection<NumFluent> map) {
        return map.contains(this);
    }

    @Override
    public boolean involve (NumFluent nf) {
        return nf.equals(this);
    }

    @Override
    public Expression subst (Condition input) {

        if (input == null) {
            return this;
        }
        if (!(input instanceof ComplexCondition))
            return this;

        ComplexCondition numeric = (ComplexCondition) input;

        for (Object o : numeric.sons) {
            if (o instanceof NumEffect) {

                NumEffect as = (NumEffect) o;

                if (as.getFluentAffected().equals(this)) {
                    if (as.getOperator().equals("increase")) {
                        BinaryOp bin = new BinaryOp();
                        bin.setOperator("+");
                        bin.setLhs(this);
                        bin.setRhs(as.getRight());
                        return bin;
                    } else if (as.getOperator().equals("decrease")) {
                        BinaryOp bin = new BinaryOp();
                        bin.setOperator("-");
                        bin.setLhs(this);
                        bin.setRhs(as.getRight());
                        return bin;
                    } else if (as.getOperator().equals("assign")) {
                        return as.getRight();

                    } else {
                        System.err.println("Operation is not supported ( " + as.getOperator() + ")");
                    }
                }

            }

        }
        return this;
    }

    @Override
    public Set getInvolvedNumericFluents ( ) {
        Set ret = new HashSet();
        ret.add(this);
        return ret;
    }

    @Override
    public boolean isUngroundVersionOf (Expression expr) {
        if (expr instanceof NumFluent) {
            NumFluent nf = (NumFluent) expr;
            if (this.getName().equals(nf.getName())) {
                if (this.getTerms().size() == nf.getTerms().size()) {
                    for (int i = 0; i < this.getTerms().size(); i++) {
                        Variable v = (Variable) this.getTerms().get(i);
                        PDDLObject obj = (PDDLObject) nf.getTerms().get(i);
                        if (!v.getType().equals(obj.getType())) {
                            if (!v.getType().isAncestorOf(obj.getType())) {
                                return false;
                            }
                        }

                    }
                    return true;
                }

            }

        }

        return false;
    }

    @Override
    public Expression susbtFluentsWithTheirInvariants (int j) {
        return getNumFluent(name+j,terms);
    }



    @Override
    public String toSmtVariableString (int i) {
        String ret = "";
        ret = ret.concat(this.getName());
        for (Object o1 : this.getTerms()) {
            if (o1 instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o1;
                ret = ret.concat(" " + obj.getName());
            } else {
                Variable obj = (Variable) o1;
                ret = ret.concat(" " + obj.getName());

            }

        }
        ret += "-" + i;
        //ret = ret.concat(")");
        return ret.replaceAll("\\s+", "");
    }

    public boolean has_to_be_tracked ( ) {
        if (has_to_be_tracked == null) {
            if (this.getName().equals("total-cost")) {
                needsTrackingInState(Boolean.FALSE);
            } else {
                needsTrackingInState(Boolean.TRUE);
            }
        }
        return has_to_be_tracked;
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("  (");
        bui.append(this.name);
        for (Object o1 : this.getTerms()) {
            if (o1 instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o1;
                bui.append(" ");
                bui.append(obj.getName());
            } else {
                Variable obj = (Variable) o1;
                bui.append(" ");
                bui.append(obj.getName());
                if (typeInformation) {
                    bui.append(obj.getType());
                }
            }
        }
        bui.append(")");
    }

    private String getTermsAsString ( ) {
        if (terms_as_string == null) {
            terms_as_string = this.terms.toString(); //To change body of generated methods, choose Tools | Templates.
        }
        return terms_as_string;
    }

    /**
     * @return the id
     */
    public int getId ( ) {
        return id;
    }


    /**
     * @param has_to_be_tracked the has_to_be_tracked to set
     */
    public void needsTrackingInState (Boolean has_to_be_tracked) {
        this.has_to_be_tracked = has_to_be_tracked;
    }

    @Override
    public Expression unifyVariablesReferences (PDDLProblem p) {
        p.putNumFluentReference(this);
        return this;
    }
    
    public boolean isGrounded() {
        for (var v: this.terms){
            if (v instanceof Variable){
                return false;
            }
        }
        return true;
    }
}
