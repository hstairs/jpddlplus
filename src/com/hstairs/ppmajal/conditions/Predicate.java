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
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.*;

import java.util.*;

/**
 * @author enrico
 */
public class Predicate extends Terminal implements PostCondition {

    final private String name;
    final private ArrayList variables;
    final private static Predicate truePredicate = new Predicate(true);
    final private static Predicate falsePredicate = new Predicate(false);
    final private int id;
    
    private Predicate (String name, ArrayList variables, int id) {
        this.name = name;
        this.variables = variables;
        this.id = id;
    }
    
    
    
    public static HashMap<Pair<String,ArrayList>,Predicate> predicates;
    public static HashMap<Integer,Predicate> idToPredicate;
    
    public static Predicate createPredicate(String name, ArrayList variables){
        if (predicates == null){
            predicates = new HashMap();
            idToPredicate = new HashMap();
        }
        Pair pair = new Pair(name,variables);
        Predicate predicate = predicates.get(pair);
        if (predicate == null){
            int idN = predicates.entrySet().size()+2;
            predicate = new Predicate(name,variables,idN);
            idToPredicate.put(idN,predicate);
            predicates.put(pair,predicate);
        }
        return predicate;
    }
    
    
    public static Predicate createPredicate(trueFalse input){
        if (input == trueFalse.TRUE) {
            return truePredicate;
        } else {
            return falsePredicate;
        }
    }

    private Predicate(boolean b) {
        if (b){
            this.name = "TRUE";
            this.setValid(true);
            this.id = 0;
        }else{
            this.name = "FALSE";
            this.setUnsatisfiable(true);
            this.id = 1;
        }
        variables = null;
    }

//    @Override
    public int getId() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }

    
    

    @Override
    public String pddlPrintWithExtraObject ( ) {
        String ret = "";
        ret = ret.concat("  (" + this.getPredicateName());
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
        ret = ret.concat("  (" + this.getPredicateName());
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
    public boolean can_be_false (RelState s) {
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
    public Condition achieve (Predicate p) {
        if (this.equals(p)) {
            return Predicate.createPredicate(Predicate.trueFalse.TRUE);
        }
        return null;
    }

    @Override
    public Condition delete (Predicate p) {
        return null;
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        return Sets.newHashSet(this);
    }

    @Override
    public Float estimate_cost (ArrayList<Float> cond_dist, boolean additive_h) {
        return cond_dist.get(this.getHeuristicId());
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
        LinkedHashSet ret = new LinkedHashSet();
        ret.add(this);
        return ret;
    }


    public String getPredicateName ( ) {
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
    public ArrayList getTerms ( ) {
        return variables;
    }


    /**
     * @return the grounded
     */
    public boolean isGrounded ( ) {
        return grounded;
    }

    /**
     * @param grounded the grounded to set
     */
    public void setGrounded (boolean grounded) {
        this.grounded = grounded;
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
        Predicate createPredicate = Predicate.createPredicate(name, localVariables);
//        System.out.println(createPredicate);
        createPredicate.grounded = true;        
        return createPredicate;
    }

    @Override
    public Condition ground (Map substitution, int c) {
        Condition ret = this.ground(substitution, null);
        ret.setHeuristicId(c);
        return ret;
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
    public boolean can_be_true (RelState s) {
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

    @Override
    public int hashCode() {
        return id;
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
        final Predicate other = (Predicate) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
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
    public Condition unGround (Map substitution) {
        ArrayList localVariables = new ArrayList();
        //System.out.println(this);
        for (Object o : this.getTerms()) {
            if (o instanceof PDDLObject) {
                PDDLObject obj = (PDDLObject) o;
                Variable t = (Variable) substitution.get(obj.getName());

                if (t == null) {
                    System.out.println("Error in substitution  for " + o);
                    System.exit(-1);
                } else {
                    localVariables.add(t);
                }
            }
        }
        Predicate ret = Predicate.createPredicate(name, localVariables);
        ret.grounded = false;
        return ret;
    }

    @Override
    public boolean isUngroundVersionOf (Condition con) {
        if (con instanceof Predicate) {
            Predicate p = (Predicate) con;
            if (this.getPredicateName().equals(p.getPredicateName())) {
                if (this.getTerms().size() == p.getTerms().size()) {
                    for (int i = 0; i < this.getTerms().size(); i++) {
                        Variable v = (Variable) this.getTerms().get(i);
                        PDDLObject obj = (PDDLObject) p.getTerms().get(i);
                        //System.out.print("Matching Types between: "+ v.getType() + obj.getType());
                        if (!v.getType().equals(obj.getType())) {
                            if (!v.getType().isAncestorOf(obj.getType())) {
                                //System.out.println("Sottotipo di:"+obj.getType().getSubTypeOf());
                                //System.out.println(" FAILURE!!");
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
    public String toSmtVariableString (int i) {

        if (this.isValid()) {
            return "true";
        }
        if (this.isUnsatisfiable()) {
            return "false";
        }

        String ret = "";
        ret = ret.concat(this.getPredicateName());
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
    public Condition weakEval (PddlProblem problem, HashMap invF) {
        //if it is a static predicate (not invariant) and is satisfied in the init state,
        //then remove it in the upper level since it is valid for any state

        if (this.isValid()){
            return this;
        }
        if (this.isUnsatisfiable()){
            return this;
        }
        
        if (invF.get(this) == null) {//this means it is a static predicate
            if (problem.getInitBoolFluentValue(this)) {
                this.setValid(true);
                this.setUnsatisfiable(false);
            } else {
                this.setValid(false);
                this.setUnsatisfiable(true);
            }
            return this;
        } else {//this is meant to couple all the predicates in a unique representation
            return this;//problem.getPredicate(this);
        }
    }

    @Override
    public String toSmtVariableString (int k, GroundAction gr, String var) {
        return " true ";
    }

    @Override
    public Condition transformEquality ( ) {
        return this;
    }

    @Override
    public boolean is_affected_by (GroundAction gr) {
        return gr.weakAchiever(this) || gr.delete(this);
    }

    @Override
    public Condition regress (GroundAction gr) {

        OrCond achievers = gr.getAdders(this);
        OrCond deleters = gr.getDels(this);
        OrCond or = new OrCond();
        AndCond effect_reasons = new AndCond();
        AndCond frame_axiom = new AndCond();
        NotCond no_del = NotCond.createNotCond(deleters);

        effect_reasons.addConditions(achievers);
        effect_reasons.addConditions(no_del);
        frame_axiom.addConditions(no_del);
        frame_axiom.addConditions(this);
        or.addConditions(effect_reasons);
        or.addConditions(frame_axiom);
        return or;
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
        bui.append(getPredicateName());
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
        return this;
    }

    public enum trueFalse {
        TRUE, FALSE
    }

}
