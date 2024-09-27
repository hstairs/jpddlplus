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
import com.hstairs.ppmajal.expressions.*;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author enrico
 */
public class Comparison extends Terminal {

    private String comparator;
    private Expression left;
    private Expression right;

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
    
    public static Comparison comparison(String comparator, Expression left, Expression right, boolean normalized) {
        if (comparisonDataBase == null) {
            comparisonDataBase = new HashMap();
        }
        Triple<String, String, String> t = Triple.of(comparator, left.toString(), right.toString());
        Comparison comp = comparisonDataBase.get(t);
        if (comp == null) {
            comp = new Comparison(t.getLeft(), left, right,normalized);
            comparisonDataBase.put(t, comp);
        }
        return comp;
    }

    private Comparison(String comparator, Expression left, Expression right, int id) {
        this(comparator, left, right,false);
    }
    
    private Comparison(String comparator, Expression left, Expression right,boolean normalized) {
        super();
        this.comparator = comparator;
        this.left = left;
        this.right = right;
        this.normalized = normalized;

    }

    public static int getNumberOfComparisons() {
        if (Comparison.getComparisonDataBase() == null){
            return 0;
        }else{
            return NotCond.notcondDB.values().size();
        }
    }



    @Override
    public String toString ( ) {

        if (string_representation == null) {
            string_representation = "("  + getLeft() + " "+ getComparator() + " " + getRight() + ")";
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
        Comparison ret = comparison(comparator, left.ground(substitution, po), right.ground(substitution, po),false);
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
        return isSatisfied(first, second);
    }

    public boolean isSatisfied(Double first, Double second){
        if ((first == null) || (second == null) ||
            first.isNaN() || second.isNaN()) {
            return false;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            return Utils.doubleComparator(first, second) < 0;
        } else if (this.getComparator().equals("<=")) {
            return Utils.doubleComparator(first, second) <= 0;
        } else if (this.getComparator().equals(">")) {
            return Utils.doubleComparator(first, second) > 0;
        } else if (this.getComparator().equals(">=")) {
            return Utils.doubleComparator(first, second) >= 0;
        } else if (this.getComparator().equals("=")) {
            return Utils.doubleComparator(first, second) == 0;
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
    public boolean canBeTrue (RelState s) {

        if (s.possNumValues.isEmpty()) {
            return false;
        }
        final HomeMadeRealInterval first = left.eval(s);
        final HomeMadeRealInterval second = right.eval(s);

        if ((first == null) || (second == null)) {
            return false;
        }
        if ((Double.isNaN(first.lo())) || (Double.isNaN(first.hi())) || (Double.isNaN(second.lo())) || (Double.isNaN(second.hi()))) {
            return false;//negation by failure.
        }
        if (this.getComparator().equals("<")) {
            return Utils.doubleComparator(first.lo(),second.hi()) < 0;
        } else if (this.getComparator().equals("<=")) {
           return Utils.doubleComparator(first.lo(),second.hi()) <= 0;
        } else if (this.getComparator().equals(">")) {
            return Utils.doubleComparator(first.hi(),second.lo()) > 0;
        } else if (this.getComparator().equals(">=")) {
            return Utils.doubleComparator(first.hi(),second.lo()) >= 0;
        } else if (this.getComparator().equals("=")) {
//            float ret = Math.max(first.lo()-second.hi(), second.lo()-first.hi());
//            if (ret>=0)
//                return false;
//            else
//                return true;
//            
//            return !((first.lo() > second.hi()) || (second.lo() > first.hi()));

            return !((Utils.doubleComparator(first.lo(),second.hi()) > 0) || ((Utils.doubleComparator(second.lo(),first.hi()) > 0)));
        } else {
            System.out.println(this.getComparator() + "  is not supported");
        }

        return false;
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
        String comp = comparator;

        //System.out.println("Instanceof left: "+ret.left.getClass());
        ExtendedNormExpression leftExpr = (ExtendedNormExpression) this.left.normalize();
        //System.out.println(leftExpr);
        ExtendedNormExpression rightExpr = (ExtendedNormExpression) this.right.normalize();
        if (leftExpr.isNumber() && rightExpr.isNumber()) {
            Double first;
            first = leftExpr.getNumber();
            Double second = rightExpr.getNumber();
            if (this.getComparator().equals("<")) {
                if (Utils.doubleComparator(first, second)< 0) {
                    this.setValid(true);
                }
            } else if (this.getComparator().equals("<=")) {
                if (Utils.doubleComparator(first, second)<= 0) {

                    this.setValid(true);
                }
            } else if (this.getComparator().equals(">")) {
                if (Utils.doubleComparator(first, second)> 0) {
                    this.setValid(true);
                }
            } else if (this.getComparator().equals(">=")) {
                if (Utils.doubleComparator(first, second)>= 0) {
                    this.setValid(true);
                }
            } else if (this.getComparator().equals("=")) {
                if (Utils.doubleComparator(first, second)== 0) {
                    this.setValid(true);
                }
            }else {
                //System.out.println(this.toString() + " will be never be satisfied");

                setUnsatisfiable(true);
            }//throw new Exception();

        }
        {
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
        //ret = comparison(comp,leftExpr,rightExpr,true);
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
                    num += Math.max(Math.abs(a.n * numericFleuntsBoundaries.functionInfValue(a.f)), Math.abs(a.n * numericFleuntsBoundaries.functionSupValue(a.f)));
                }
            }
            this.maxDist = num.floatValue();
        } else {
            System.out.println("Errore!!!");
            System.exit(-1);
        }

    }


    public boolean couldBePrevented (HashMap<NumFluent, HashSet<NumFluent>> computeFluentDependencePlanDependant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String toSmtVariableString (int k, TransitionGround gr, String var) {
//        if (!gr.mayInfluence(this)) {
//            return " true ";
//        }
//        ExtendedNormExpression norm = (ExtendedNormExpression) this.getLeft();
//        String ret_val = "";
//
//        HashMap<NumFluent, NumEffect> affector = new HashMap();
//
//        for (NumEffect neff : (Collection<NumEffect>) gr.getNumericEffects().sons) {
//            if (this.getInvolvedFluents().contains(neff.getFluentAffected())) {
//                affector.put(neff.getFluentAffected(), neff);
//            }
//        }
//        {
//            //System.out.println(summations);
//            ExtendedAddendum ad = norm.summations.get(0);
//            if (ad.bin != null) {
//                System.out.println("repetition cannot be activated for actions having non-linear constraints");
//                System.exit(-1);
//            }
//            if (ad.f == null) {
//                ret_val = " " + ad.n + " ";
//            } else {
//                NumEffect neff = affector.get(ad.f);
//                if (neff != null) {
//                    ret_val = neff.to_smtlib_with_repetition_for_the_right_part(k, var);
//                } else {
//                    ret_val = ad.f.toSmtVariableString(k);
//                }
//
//                ret_val = "(* " + ret_val + " " + ad.n + ")";
//            }
//        }
//        {
//            for (int i = 1; i < norm.summations.size(); i++) {
//                ExtendedAddendum ad = norm.summations.get(i);
//                if (ad.f == null) {
//                    ret_val = "(+ " + ret_val + " " + ad.n + " )";
//                } else {
//                    NumEffect neff = affector.get(ad.f);
//                    String temp = null;
//                    if (neff != null) {
//                        temp = neff.to_smtlib_with_repetition_for_the_right_part(k, var);
//                    } else {
//                        temp = ad.f.toSmtVariableString(k);
//                    }
//                    ret_val = "(+ " + ret_val + " " + "(* " + temp + " " + ad.n + "))";
//
////                    ret_val += "(* " + temp + " " + ad.n.pddlPrint(false) + ")";
//                }
//            }
//        }
//
//        return "( " + this.comparator + " " + ret_val + " " + this.getRight().toSmtVariableString(k) + " )";
        throw new UnsupportedOperationException();
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
    public Condition weakEval(PDDLProblem s, Set invF) {
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
        return comparison(comparator,lValue,rValue,false);
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



    @Override
    public Condition transformEquality ( ) {
        final Comparison comp = this;
        if (comp.getComparator().equals("=")) {
            final Collection ret = new HashSet();
            final Comparison dual = (Comparison) Comparison.comparison(">=", left, right,false).normalize();
            final Comparison dual2 = (Comparison) Comparison.comparison("<=", left,right,false).normalize();
            ret.add(dual);
            ret.add(dual2);
            return new AndCond(ret);
        } else {
            return this;
        }
    }



    @Override
    public String pddlPrintWithExtraObject ( ) {
        return "(" + getComparator() + " " + getLeft().pddlPrint(false) + " " + getRight().pddlPrint(false) + ")";
    }

    @Override
    public boolean canBeFalse (RelState s) {

        if (s.possNumValues.isEmpty()) {
            return true;
        }

        HomeMadeRealInterval first = left.eval(s);
        HomeMadeRealInterval second = right.eval(s);

        if ((first == null) || (second == null)) {
            return true;
        }
        if ((Double.isNaN(first.lo())) || (Double.isNaN(first.hi())) || (Double.isNaN(second.lo())) || (Double.isNaN(second.hi()))) {
            return true;//negation by failure.
        }
        
        
        if (this.getComparator().equals("<")) {
            return Utils.doubleComparator(first.hi(), second.lo())>= 0;
        } else if (this.getComparator().equals("<=")) {
            return Utils.doubleComparator(first.hi(), second.lo())> 0;
        } else if (this.getComparator().equals(">")) {
            return Utils.doubleComparator(first.lo(), second.hi())<= 0;
        } else if (this.getComparator().equals(">=")) {
            return Utils.doubleComparator(first.lo(), second.hi())< 0;
        } else if (this.getComparator().equals("=")) {
            return (Utils.doubleComparator(first.hi(), second.lo()) > 0 || Utils.doubleComparator(first.lo(), second.hi())< 0 );
//            return (first.hi() > second.lo() || (first.lo() < second.hi()));
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
    public Condition pushNotToTerminals( ) {
        return this;
    }

    Condition invertOperator ( ) {
        if (this.getComparator().equals("=")) {
            Collection a = new HashSet();
            a.add(Comparison.comparison("<", left, right,false));
            a.add(Comparison.comparison(">", left, right,false));
            return new OrCond(a);
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
            return Comparison.comparison(comp, left, right,false).normalize();
        }
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Collection<BoolPredicate> getInvolvedPredicates ( ) {
        return new HashSet();
    }

    @Override
    public Condition unifyVariablesReferences (PDDLProblem p) {
        if (this.isUnsatisfiable()){
            return this;
        }
        Expression newLeft = left.unifyVariablesReferences(p);
        Expression newRight = right.unifyVariablesReferences(p);
        return Comparison.comparison(comparator, newLeft, newRight,this.normalized);
    }

    @Override
    public Condition normalize() {
        try {
            return this.normalizeAndCopy();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("Something went wrong "+ex );      
        }
    }

    @Override
    public ComplexCondition and(Condition precondition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }






}
