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
import com.hstairs.ppmajal.expressions.*;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.*;

import java.util.*;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author enrico
 */
public class Comparison extends Terminal {

    private String comparator;
    private Expression left;
    private Expression right;
    public final int id;
    
    boolean normalized;
    //This needs to go away at some point
    public Comparison fatherFromRegression = null;
    public Float maxDist;
    private String string_representation;
    private boolean linear;
    
    private static HashMap<Triple<String, String, String>, Comparison> comparisonDataBase;

    public static HashMap<Triple<String, String, String>, Comparison> getComparisonDataBase() {
        return comparisonDataBase;
    }
    
    public static Comparison createComparison(String comparator, Expression left, Expression right, boolean normalized) {
        if (comparisonDataBase == null) {
            comparisonDataBase = new HashMap();
        }
        Triple<String, String, String> t = Triple.of(comparator, left.toString(), right.toString());
        Comparison comp = comparisonDataBase.get(t);
        if (comp == null) {
            comp = new Comparison(t.getLeft(), left, right,comparisonDataBase.entrySet().size(),normalized);
            comparisonDataBase.put(t, comp);
        }
        return comp;
    }

    private Comparison(String comparator, Expression left, Expression right, int id) {
        this(comparator, left, right, id, false);
                
    }
    
    private Comparison(String comparator, Expression left, Expression right, int id, boolean normalized) {
        this.comparator = comparator;
        this.left = left;
        this.right = right;
        this.id = id;
        this.normalized = normalized;
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
        final Comparison other = (Comparison) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
//    @Override
    public int getId() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String toString ( ) {

        if (string_representation == null) {
            string_representation = "(" + getComparator() + " (" + getLeft() + ") (" + getRight() + "))";
        }

        return string_representation;

        //return "(" + getLeft() + " " + getComparator() + " " + getRhs() + ")";
    }

    /**
     * @return the bin_comp
     */
    public String getComparator ( ) {
        return comparator;
    }


    /**
     * @return the one
     */
    public Expression getLeft ( ) {
        return left;
    }


    /**
     * @return the two
     */
    public Expression getRight ( ) {
        return right;
    }


    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        Comparison ret = createComparison(comparator, left.ground(substitution, po), right.ground(substitution, po),false);
        ret.grounded = true;
        return ret;
    }

    @Override
    public Condition ground (Map substitution, int c) {
        Condition ret = this.ground(substitution, null);
        ret.setHeuristicId(c);
        return ret;
    }

    public boolean eval_to_null (PDDLState s) {
        Double first = left.eval(s);
        Double second = right.eval(s);
        return (first == null) || (second == null);
    }

    @Override
    public boolean eval (State s) {
        Double first = left.eval(s);
        Double second = right.eval(s);
        if ((first == null) || (second == null)) {
            return false;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            return first < second;
        } else if (this.getComparator().equals("<=")) {
            return first <= second;
        } else if (this.getComparator().equals(">")) {
            return first > second;
        } else if (this.getComparator().equals(">=")) {
            return first >= second;
        } else if (this.getComparator().equals("=")) {
            return first == second;
        } else {
            System.out.println(this.getComparator() + "  does not supported");
        }

        return false;
    }

    public boolean isSatisfied(Double first, Double second){
        if ((first == null) || (second == null)) {
            return false;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            return first < second;
        } else if (this.getComparator().equals("<=")) {
            return first <= second;
        } else if (this.getComparator().equals(">")) {
            return first > second;
        } else if (this.getComparator().equals(">=")) {
            return first >= second;
        } else if (this.getComparator().equals("=")) {
            return first.equals(second);
        } else {
            System.out.println(this.getComparator() + "  is not supported");
        }

        return false;
    }
    
    @Override
    public boolean isSatisfied (State s) {
//        return s.satisfy(this);
        return this.isSatisfied(left.eval(s), right.eval(s));
    }

    @Override
    public boolean can_be_true (RelState s) {

        if (s.possNumValues.isEmpty()) {
            return false;
        }

        Interval first = left.eval(s);
        Interval second = right.eval(s);

        if ((first == null) || (second == null) || first.is_not_a_number || second.is_not_a_number) {
            return false;
        }
        if ((first.getInf() == null) || (first.getSup() == null) || (second.getInf() == null) || (second.getSup() == null)) {
            return false;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            return first.getInf().getNumber() < second.getSup().getNumber();
        } else if (this.getComparator().equals("<=")) {
            return first.getInf().getNumber() <= second.getSup().getNumber();
        } else if (this.getComparator().equals(">")) {
            return first.getSup().getNumber() > second.getInf().getNumber();
        } else if (this.getComparator().equals(">=")) {
            return first.getSup().getNumber() >= second.getInf().getNumber();
        } else if (this.getComparator().equals("=")) {
//            float ret = Math.max(first.getInf().getNumber()-second.getSup().getNumber(), second.getInf().getNumber()-first.getSup().getNumber());
//            if (ret>=0)
//                return false;
//            else
//                return true;
//            
            return !((first.getInf().getNumber() > second.getSup().getNumber()) || (second.getInf().getNumber() > first.getSup().getNumber()));
        } else {
            System.out.println(this.getComparator() + "  is not supported");
        }

        return false;
    }

    public Float satisfactionDistance (RelState s) {
        Float ret = new Float(0);

        Interval first = left.eval(s);
        Interval second = right.eval(s);
        if ((first == null) || (second == null)) {
            return Float.MAX_VALUE;
        }
        if ((first.getInf() == null) || (first.getSup() == null) || (second.getInf() == null) || (second.getSup() == null)) {
            return Float.MAX_VALUE;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            Float t = second.getSup().getNumber() - first.getInf().getNumber();
            return (t - 0.00001f) * -1;

        } else if (this.getComparator().equals("<=")) {
            Float t = second.getSup().getNumber() - first.getInf().getNumber();
            return t * -1;
        } else if (this.getComparator().equals(">")) {
            Float t = first.getSup().getNumber() - second.getInf().getNumber();
            return (t - 0.00001f) * -1;
        } else if (this.getComparator().equals(">=")) {
            Float t = first.getSup().getNumber() - second.getInf().getNumber();
            return t * (-1);
        } else if (this.getComparator().equals("=")) {
            ret = Math.max(first.getInf().getNumber() - second.getSup().getNumber(), second.getInf().getNumber() - first.getSup().getNumber());
            return (ret + 0.00001f) * -1;

        } else {
            System.out.println(this.getComparator() + "  is not supported");
        }

        return ret;
    }

//    @Override
//    public void changeVar (Map substitution) {
//
//        this.left.changeVar(substitution);
//        this.right.changeVar(substitution);
//
//    }

    @Deprecated //actually this function does not copy anything
    private Comparison normalizeAndCopy ( ) throws CloneNotSupportedException  {
        if (normalized) {
            this.linear = ((ExtendedNormExpression)this.left).linear && ((ExtendedNormExpression)this.right).linear;   
            return this;
        }
        Comparison ret;
        String comp = comparator;

        String leftToRemove = left.toString();
        String rightToRemove = left.toString();

        //System.out.println("Instanceof left: "+ret.left.getClass());
        ExtendedNormExpression leftExpr = (ExtendedNormExpression) this.left.normalize();
        //System.out.println(leftExpr);
        ExtendedNormExpression rightExpr = (ExtendedNormExpression) this.right.normalize();
        if (leftExpr.isNumber() && rightExpr.isNumber()) {
            Double first;
            first = leftExpr.getNumber();
            Double second = rightExpr.getNumber();
            if (this.getComparator().equals("<")) {
                if ((first < second)) {
                    return null;
                }
            } else if (this.getComparator().equals("<=")) {
                if ((first <= second)) {
                    return null;
                }
            } else if (this.getComparator().equals(">")) {
                if ((first > second)) {
                    return null;
                }
            } else if (this.getComparator().equals(">=")) {
                if ((first >= second)) {
                    return null;
                }
            } else if (this.getComparator().equals("=")) {
                Float res = new Float(Math.abs(first - second));
                if (res < Double.MIN_VALUE) {
                    return null;
                }
            }
            //System.out.println(this.toString() + " will be never be satisfied");

            setUnsatisfiable(true);
            //throw new Exception();

        } else {
            //System.out.println("DEBUG");
            if (this.comparator.equals("<") || this.comparator.equals("<=") || this.comparator.equals("=")) {
                leftExpr = rightExpr.minus(leftExpr);
                rightExpr = new ExtendedNormExpression(0d);

            } else {
                leftExpr = leftExpr.minus(rightExpr);
                rightExpr = new ExtendedNormExpression(0d);
            }

            if (!this.comparator.equals("=")) {
                if (this.comparator.contains("=")) {
                    comp = ">=";
                } else {
                    comp = ">";
                }
            }
        }

//        cancelComparison(comparator,leftToRemove,rightToRemove);
        //ret = createComparison(comp,leftExpr,rightExpr,true);
        //ret.linear = leftExpr.linear && rightExpr.linear;    
        left = leftExpr;
        right = rightExpr;
        comparator = comp;
        linear = leftExpr.linear && rightExpr.linear;
        normalized = true;
        
        return this;
    }

    @Override
    public Condition clone ( ) {
        return this;
//        throw new RuntimeException("Do not clone Comparison!");
    }

    public boolean involve (Collection<NumFluent> input) {
        if (this.left.involve(input)) {
            return true;
        } else {
            return this.right.involve(input);
        }
    }



    /**
     * @return the normalized
     */
    public boolean isNormalized ( ) {
        return normalized;
    }



    public void computeMaxDist (RelState numericFleuntsBoundaries) {

        if ((this.getRight() instanceof ExtendedNormExpression) && (this.getLeft() instanceof ExtendedNormExpression)) {
            ExtendedNormExpression lExpr = (ExtendedNormExpression) this.getLeft();

            Double num = 0d;

            for (ExtendedAddendum a : lExpr.summations) {
                if (a.f == null) {
                    num += Math.abs(a.n);
                } else {
                    num += Math.max(Math.abs(a.n * numericFleuntsBoundaries.functionInfValue(a.f).getNumber()), Math.abs(a.n * numericFleuntsBoundaries.functionSupValue(a.f).getNumber()));
                }
            }
            this.maxDist = num.floatValue();
        } else {
            System.out.println("Errore!!!");
            System.exit(-1);
        }

    }

    @Override
    public Condition unGround (Map asbstractionOf) {
        Comparison ret = Comparison.createComparison(comparator,left.unGround(asbstractionOf),right.unGround(asbstractionOf),false);
        ret.grounded = false;
        return ret;
    }

    public boolean isDirectlyOrIndirectlyAffected (HashMap<NumFluent, HashSet<NumFluent>> dependsOn, GroundAction get) {

        if (!get.mayInfluence(this)) {
            //System.out.println("Action does not affect");
            return false;
        }

        //If the action affects one of the fluent the comparison depends on, then the comparison can be prevented
        return get.influence(dependsOn);

    }

    public boolean couldBePrevented (HashMap<NumFluent, HashSet<NumFluent>> dependsOn, GroundAction get) throws CloneNotSupportedException {

//        if (!get.mayInfluence(this)) {
//            //System.out.println("Action does not affect");
//            return false;
//        }
        //If the action affects one of the fluent the comparison depends on, then the comparison can be prevented
        if (get.influence(dependsOn)) {
            //System.out.println("Ordering for indirect influence");
            return true;
        }
        //todo add the == case
        //if the action does not threaten the dependant fluents, then let see if it is a proper weakThreat for c.
        Comparison c = (Comparison) get.regress(this.clone());

        if ((this.getRight() instanceof ExtendedNormExpression) && (this.getLeft() instanceof ExtendedNormExpression)) {
            ExtendedNormExpression lExpr = (ExtendedNormExpression) this.getLeft();
            ExtendedNormExpression lExprNew = (ExtendedNormExpression) c.getLeft();
//            System.out.println(lExpr);
//            System.out.println(lExprNew);
//            System.out.println(this.getRhs());

            ExtendedNormExpression toTest = lExprNew.minus((ExtendedNormExpression) lExpr.clone());
            Double total = 0d;
            for (ExtendedAddendum add : toTest.summations) {
                if (add.f != null) {
                    return true;
                } else {
                    total += add.n;
                }
            }
            if (this.getComparator().equals(">=")) {
                return total < 0;

            } else if (this.getComparator().equals(">")) {
                return total <= 0;
            } else if (this.getComparator().equals("<=")) {
                return total > 0;
            } else if (this.getComparator().equals("<")) {
                return total >= 0;
            } else if (this.getComparator().equals("=")) {
                return total != 0;
            }
        } else {
            System.out.println("Non valutata");

        }
        //System.out.println("Acion may prevent...");
        return true;

    }

    public boolean couldBePrevented (HashMap<NumFluent, HashSet<NumFluent>> computeFluentDependencePlanDependant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUngroundVersionOf (Condition c) {
        if (c instanceof Comparison) {
            Comparison comp = (Comparison) c;
            if (comp.getComparator().equals(this.getComparator())) {

                return this.getLeft().isUngroundVersionOf(comp.getLeft()) && this.getRight().isUngroundVersionOf(comp.getRight());
            }
        }
        return false;
    }


    public ArrayList<NumFluent> susbtFluentsWithTheirInvariants (HashMap<Object, Boolean> invariantFluent, int j) {

        ArrayList ret = new ArrayList();
        ret.addAll(this.left.getInvolvedNumericFluents());
        ret.addAll(this.right.getInvolvedNumericFluents());
        return ret;
    }

    @Override
    public String toSmtVariableString (int i) {
        return "(" + this.comparator + " " + this.getLeft().toSmtVariableString(i) + " " + this.getRight().toSmtVariableString(i) + ")";
    }

    @Override
    public String toSmtVariableString (int k, GroundAction gr, String var) {
        if (!gr.mayInfluence(this)) {
            return " true ";
        }
        ExtendedNormExpression norm = (ExtendedNormExpression) this.getLeft();
        String ret_val = "";

        HashMap<NumFluent, NumEffect> affector = new HashMap();
        for (NumEffect neff : (Collection<NumEffect>) gr.getNumericEffects().sons) {
            if (this.getInvolvedFluents().contains(neff.getFluentAffected())) {
                affector.put(neff.getFluentAffected(), neff);
            }
        }
        {
            //System.out.println(summations);
            ExtendedAddendum ad = norm.summations.get(0);
            if (ad.bin != null) {
                System.out.println("repetition cannot be activated for actions having non-linear constraints");
                System.exit(-1);
            }
            if (ad.f == null) {
                ret_val = " " + ad.n + " ";
            } else {
                NumEffect neff = affector.get(ad.f);
                if (neff != null) {
                    ret_val = neff.to_smtlib_with_repetition_for_the_right_part(k, var);
                } else {
                    ret_val = ad.f.toSmtVariableString(k);
                }

                ret_val = "(* " + ret_val + " " + ad.n + ")";
            }
        }
        {
            for (int i = 1; i < norm.summations.size(); i++) {
                ExtendedAddendum ad = norm.summations.get(i);
                if (ad.f == null) {
                    ret_val = "(+ " + ret_val + " " + ad.n + " )";
                } else {
                    NumEffect neff = affector.get(ad.f);
                    String temp = null;
                    if (neff != null) {
                        temp = neff.to_smtlib_with_repetition_for_the_right_part(k, var);
                    } else {
                        temp = ad.f.toSmtVariableString(k);
                    }
                    ret_val = "(+ " + ret_val + " " + "(* " + temp + " " + ad.n + "))";

//                    ret_val += "(* " + temp + " " + ad.n.pddlPrint(false) + ")";
                }
            }
        }

        return "( " + this.comparator + " " + ret_val + " " + this.getRight().toSmtVariableString(k) + " )";
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        Set<NumFluent> ret = new HashSet();

        ret.addAll(this.getLeft().getInvolvedNumericFluents());
        ret.addAll(this.getRight().getInvolvedNumericFluents());
        //System.out.println("Here we are:"+this);
        return ret;
    }

    @Override
    public Condition weakEval(PddlProblem s, HashMap invF) {
        if (this.isUnsatisfiable())
            return this;
        Comparison comp = this;
        Expression lValue = comp.getLeft();
        Expression rValue = comp.getRight();
//        System.out.println(this.isValid());
//        System.out.println("lValue Before" + lValue  );
//        System.out.println("rValue Before" + rValue);
//        System.out.println("lvalueClass:" + lValue.getClass());
//        System.out.println("rvalueClass:" + rValue.getClass());
        lValue.setFreeVarSemantic(this.freeVarSemantic);
        rValue.setFreeVarSemantic(this.freeVarSemantic);
        lValue = lValue.weakEval(s, invF);
        rValue = rValue.weakEval(s, invF);
        if (lValue == null || rValue == null) {
            this.setUnsatisfiable(true);
            this.setValid(false);
            return this;
        }
//        System.out.println("lValue after" + lValue  );
//        System.out.println("rValue after" + rValue);
//        System.out.println("lvalueClass after" + lValue.getClass());
//        System.out.println("rvalueClass after" + rValue.getClass());

        if (lValue instanceof PDDLNumber && rValue instanceof PDDLNumber) {
            PDDLNumber left = (PDDLNumber) lValue;
            PDDLNumber right = (PDDLNumber) rValue;
            if (this.isSatisfied(left.getNumber().doubleValue(), right.getNumber().doubleValue())) {
                this.setValid(true);
                this.setUnsatisfiable(false);
            }else{
                this.setValid(false);
                this.setUnsatisfiable(true);
                
            }
            return this;
        }
//        System.out.println(lValue);
//        System.out.println(rValue);
        return createComparison(comparator,lValue,rValue,false);
    }

    public float eval_not_affected (PDDLState s_0, GroundAction aThis) {
        if (!this.normalized) {
            this.normalize();
            //throw new RuntimeException("At the moment support just for normalized comparisons"+this+". Current expressions are:"+this.getLeft().getClass()+" "+this.getRight().getClass());
        }
        ExtendedNormExpression exp = (ExtendedNormExpression) this.getLeft();
        return exp.eval_not_affected(s_0, aThis);
    }

    public float eval_affected (PDDLState s_0, GroundAction aThis) {
        if (!this.normalized) {
            throw new RuntimeException("At the moment support just for normalized comparisons");
        }
        ExtendedNormExpression exp = (ExtendedNormExpression) this.getLeft();
        return exp.eval_affected(s_0, aThis);
    }

    public boolean is_evaluable (PDDLState tempInit) {
        Collection<NumFluent> set = this.getInvolvedFluents();
        for (NumFluent f : set) {
            if (tempInit.fluentValue(f) == Double.NaN) {
                return false;
            }
        }
        return true;
    }

    public String regress_repeatedely (GroundAction action, int number_of_repetition, PDDLState s_0) {
        float a1;
        float b;

        if (!this.involve(action.getNumericFluentAffected())) {
            return this.getLeft().eval(s_0) + this.comparator + this.getRight().eval(s_0);
        }

        a1 = this.eval_not_affected(s_0, action);
        b = this.eval_affected(s_0, action);
        Float lhs = (b * number_of_repetition + a1);

        return lhs.toString() + ">=" + 0;

    }

    @Override
    public Condition transformEquality ( ) {
        AndCond ret = new AndCond();
        Comparison comp = this;
        if (comp.getComparator().equals("=")) {
            Comparison dual = (Comparison) Comparison.createComparison(">=", left, right,false).normalize();
            Comparison dual2 = (Comparison) Comparison.createComparison("<=", left,right,false).normalize();
            ret.addConditions(dual);
            ret.addConditions(dual2);
        } else {
            return this;
        }
        return ret;
    }

    @Override
    public boolean is_affected_by (GroundAction gr) {
        return this.getLeft().involve(gr.getNumericFluentAffected()) || this.getRight().involve(gr.getNumericFluentAffected());
    }

    @Override
    public Condition regress (GroundAction gr) {
            return Comparison.createComparison(comparator, this.getLeft().subst(gr.getNumericEffects()), this.getRight().subst(gr.getNumericEffects()),false);
        }    

    @Override
    public String pddlPrintWithExtraObject ( ) {
        return "(" + getComparator() + " " + getLeft().pddlPrint(false) + " " + getRight().pddlPrint(false) + ")";
    }

    @Override
    public boolean can_be_false (RelState s) {

        if (s.possNumValues.isEmpty()) {
            return true;
        }

        Interval first = left.eval(s);
        Interval second = right.eval(s);

        if ((first == null) || (second == null) || first.is_not_a_number || second.is_not_a_number) {
            return true;
        }
        if ((first.getInf() == null) || (first.getSup() == null) || (second.getInf() == null) || (second.getSup() == null)) {
            return true;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            return first.getSup().getNumber() >= second.getInf().getNumber();
        } else if (this.getComparator().equals("<=")) {
            return first.getSup().getNumber() > second.getInf().getNumber();
        } else if (this.getComparator().equals(">")) {
            return first.getInf().getNumber() <= second.getSup().getNumber();
        } else if (this.getComparator().equals(">=")) {
            return first.getInf().getNumber() < second.getSup().getNumber();
        } else if (this.getComparator().equals("=")) {
            return (first.getSup().getNumber() > second.getInf().getNumber() || (first.getInf().getNumber() < second.getSup().getNumber()));
        } else {
            System.out.println(this.getComparator() + "  is not supported");
        }

        return false;
    }

    //<<<<<<< HEAD
    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(").append(getComparator()).append(" ");
        getLeft().pddlPrint(typeInformation, bui);
        bui.append(" ");
        getRight().pddlPrint(typeInformation, bui);
        bui.append(")");
    }

    //=======
    //This function computes a domination analysis between the source (a) comparison and the objective one (b).
    //If the satisfaction of a implies the satisfaction of b, then b is dominated by a.
    public boolean dominate (Comparison other) {
        ExtendedNormExpression e1 = (ExtendedNormExpression) this.getLeft();
        ExtendedNormExpression e2 = (ExtendedNormExpression) other.getLeft();

        ArrayList<ExtendedAddendum> sumC1 = e1.summations;
        ArrayList<ExtendedAddendum> sumC2 = e2.summations;

        int sizeOfSumC1 = sumC1.size();
        int sizeOfSumC2 = sumC2.size();

        if (Math.abs(sizeOfSumC1 - sizeOfSumC2) >= 2) {
            return false;
        }

        if (sizeOfSumC1 > sizeOfSumC2) {
            for (ExtendedAddendum ead1 : sumC1) {

                if (ead1.f != null) {
                    if (!sumC2.contains(ead1)) {
                        return false;
                    }
                } else if (ead1.n > 0) {
                    return false;
                }
            }
            return true;

        } else if (sumC1.size() < sumC2.size()) {
            for (ExtendedAddendum ead2 : sumC2) {
                if (ead2.f != null) {
                    if (!sumC1.contains(ead2)) {
                        return false;
                    }
                } else if (ead2.n > 0) {
                    return false;
                }
            }
            return true;

        } else {
            //they have same size
            for (ExtendedAddendum ead1 : sumC1) {
                if (ead1.f != null) {
                    if (!sumC2.contains(ead1)) {
                        return false;
                    }
                } else {
                    double constC1 = ead1.n;
                    for (ExtendedAddendum ead2 : sumC2) {
                        if (ead2.f == null) {
                            if (constC1 > ead2.n) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

    }

//>>>>>>> daan

    /**
     * @return the linear
     */
    public boolean isLinear ( ) {
        this.linear = ((ExtendedNormExpression)this.left).linear && ((ExtendedNormExpression)this.right).linear;   
        return linear;
    }


    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        for (NumFluent nf : this.getInvolvedFluents()) {
            for (final Object o : nf.getTerms()) {
                final Variable var = (Variable) o;
                vars.add(var);
            }
        }

    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        Set ret = new LinkedHashSet();
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
        return this;
    }

    Condition invertOperator ( ) {
        if (this.getComparator().equals("=")) {
            OrCond a = new OrCond();
            a.addConditions(Comparison.createComparison("<", left, right,false));
            a.addConditions(Comparison.createComparison(">", left, right,false));
            return a;
        } else {
            String comp = null;
            switch (this.getComparator()) {
                case "<":
                    comp = ">=";
                    break;
                case "<=":
                    comp = ">";
                    break;
                case ">=":
                    comp = "<";
                    break;
                case ">":
                    comp = "<=";
                    break;
            }
            return Comparison.createComparison(comp, left, right,false).normalize();
        }
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Collection<Predicate> getInvolvedPredicates ( ) {
        return new HashSet();
    }

    @Override
    public Condition unifyVariablesReferences (EPddlProblem p) {
        if (this.isUnsatisfiable()){
            return this;
        }
        Expression newLeft = left.unifyVariablesReferences(p);
        Expression newRight = right.unifyVariablesReferences(p);
        return Comparison.createComparison(comparator, newLeft, newRight,this.normalized);
    }

    @Override
    public Condition normalize() {
        try {
            return this.normalizeAndCopy();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("Something went wrong "+ex );      
        }
    }

    public boolean interactWith(GroundAction aThis) {
        if (Utils.interactsWith == null){
            Utils.interactsWith = new HashMap();
        }
        
        
        Pair<Integer, Integer> p = Pair.of(this.getId(), aThis.getId());
        Boolean interact = (Boolean) Utils.interactsWith.get(p);
        if (interact == null){
            if (this.involve(aThis.getNumericFluentAffected())) {
                interact = true;
            }else{
                interact = false;
            }
            Utils.interactsWith.put(p,interact);
        }
        return interact;
    }





}
