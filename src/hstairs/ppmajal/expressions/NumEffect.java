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
import com.hstairs.ppmajal.conditions.PostCondition;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.problem.*;
import java.util.*;

/**
 * @author Enrico Scala
 */
public class NumEffect extends Expression implements PostCondition {

    public String operator;
    public boolean additive_relaxation = true;
    private NumFluent fluentAffected;
    private Expression right;
    private boolean pseudo_num_effect;

    public static NumEffect easyNumEffect(String operator, NumFluent fluent, float right){
        return new NumEffect(operator,fluent,new PDDLNumber(right));
    }
    /**
     * @param operator
     */
    public NumEffect (String operator) {
        super();
        this.operator = operator;
        this.pseudo_num_effect = false;
    }
    
    public NumEffect(String operator, NumFluent f, Expression right){
        this(operator);
        fluentAffected = f;
        this.right = right;
    }

    /**
     * @return
     */
    @Override
    public String toString ( ) {

        return "(" + getOperator() + " " + getFluentAffected() + " " + getRight() + ")";
    }

    /**
     * @return the operator
     */
    public String getOperator ( ) {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator (String operator) {
        this.operator = operator;
    }

    /**
     * @return the fluentAffected
     */
    public NumFluent getFluentAffected ( ) {
        return fluentAffected;
    }

    /**
     * @param fluentAffected the fluentAffected to set
     */
    public void setFluentAffected (NumFluent fluentAffected) {
        this.fluentAffected = fluentAffected;
    }

    /**
     * @return the right element of the operation
     */
    public Expression getRight ( ) {
        return right;
    }

    /**
     * @param right the right element of the operation to set
     */
    public void setRight (Expression right) {
        this.right = right;
    }

    /**
     * @param substitution a substitution for variables. It is a mapping of
     *                     object to variable
     * @return a new Grounded NumEffect object
     */
    @Override
    public Expression ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        NumEffect ret = new NumEffect(this.operator);
        ret.fluentAffected = this.fluentAffected.ground(substitution, po);
        ret.right = this.right.ground(substitution, po);
        ret.grounded = true;
        return ret;
    }

    @Override
    public Expression unGround (Map substitution) {
        NumEffect ret = new NumEffect(this.operator);
        ret.fluentAffected = (NumFluent) this.fluentAffected.unGround(substitution);
        ret.right = this.right.unGround(substitution);
        ret.grounded = false;
        return ret;
    }

    /**
     * @param state the state in which the expression is to be evaluated
     * @return a PDDLNumber or Null in case the operation is not supported
     */
    @Override
    public double eval (State state) {
        final double first = this.fluentAffected.eval(state);
        final double second = this.right.eval(state);
        if (this.getOperator().equals("increase")) {
            return first + second;
        } else if (this.getOperator().equals("decrease")) {
            return first - second;
        } else if (this.getOperator().equals("assign")) {
            return second;
        } else {
            System.out.println(this.getOperator() + " does not supported");
        }
        return Double.NaN;

    }

    /**
     * @return
     */
    public PDDLState applyAndCreateNew (PDDLState state) {
        Double after = null;
        if (this.operator.equals("increase")) {
            Double current = state.fluentValue(fluentAffected);
            Double eval = this.getRight().eval(state);
            after = current + eval;
        } else if (this.operator.equals("decrease")) {
            Double current = state.fluentValue(fluentAffected);
            Double eval = this.getRight().eval(state);
            after = current - eval;
        } else if (this.operator.equals("assign")) {
            Double eval = this.getRight().eval(state);
            after = eval;
        }
        PDDLState ret = state.clone();

        if (after != null) {
            ret.setNumFluent(fluentAffected, after);
        }

        return ret;

    }




    /**
     * @param problem
     * @param invF
     * @return
     */
    @Override
    public Expression weakEval (PDDLProblem problem, Set invF) {
        //System.out.println(this.fluentAffected);
        //this.setFluentAffected((NumFluent) this.fluentAffected.weakEval(s, invF));
        this.right.setFreeVarSemantic(freeVarSemantic);
        this.setRight(this.right.weakEval(problem, invF));
        if (this.right == null) {
            //System.out.println(this);
            return null;
        } else if (this.right instanceof PDDLNumber) {
            PDDLNumber rhs_number = (PDDLNumber) this.right;
            if (rhs_number.getNumber().isNaN()) {
                return null;
            }
        }
        return this;
    }

    /**
     * @return
     */
    @Override
    public Expression clone ( ) {
        NumEffect ret = new NumEffect(this.operator);
        ret.fluentAffected = (NumFluent) this.fluentAffected.clone();
        ret.right = this.right.clone();
        ret.grounded = this.grounded;
        return ret;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public HomeMadeRealInterval eval (RelState s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param arrayList
     * @return
     */
    @Override
    public boolean involve (Collection<NumFluent> arrayList) {
        return this.getRight().involve(arrayList);
    }

    /**
     * @return
     */
    @Override
    public Expression subst (Condition input) {

        //NumEffect ret = (NumEffect)this.clone();
        NumEffect ret = (NumEffect) this.clone();
        ret.right = ret.right.subst(input);

        if (input == null) {
            return ret;
        }
        if (!(input instanceof ComplexCondition))
            return ret;

        ComplexCondition numeric = (ComplexCondition) input;

        if (ret.getOperator().equals("assign")) {
            return ret;
        }
        for (Object o : numeric.sons) {
            NumEffect ef = (NumEffect) o;
            if (ef.fluentAffected.equals(ret.fluentAffected)) {

                if (ef.getOperator().equals("assign")) {
                    ret.setRight(new BinaryOp(ret.right, "-", new BinaryOp(ef.right, "-", ret.fluentAffected, true), true));
                } else if (ef.getOperator().equals(ret.getOperator())) {
                    ret.setRight(new BinaryOp(ret.right, "+", ef.right, true));
                } else {
                    ret.setRight(new BinaryOp(ret.right, "-", ef.right, true));
                }

            }
        }

        return ret;
    }

    @Override
    public Set getInvolvedNumericFluents ( ) {
        return this.right.getInvolvedNumericFluents();
    }

    @Override
    public boolean isUngroundVersionOf (Expression expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expression susbtFluentsWithTheirInvariants (int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String toSmtVariableString (int i) {
        BinaryOp op = new BinaryOp();
        if (this.operator.equals("increase")) {
            op.setOperator("+");
            op.setRhs(this.getRight());
            op.setLhs(this.getFluentAffected());
            return "(= " + this.getFluentAffected().toSmtVariableString(i + 1) + " " + op.toSmtVariableString(i) + ")";

        } else if (this.operator.equals("decrease")) {
            op.setOperator("-");
            op.setRhs(this.getRight());
            op.setLhs(this.getFluentAffected());
            return "(= " + this.getFluentAffected().toSmtVariableString(i + 1) + " " + op.toSmtVariableString(i) + ")";

        } else if (this.operator.equals("assign")) {
            return "(= " + this.getFluentAffected().toSmtVariableString(i + 1) + " " + this.getRight().toSmtVariableString(i) + ")";
        }

        return null;
    }

    public String to_smtlib_with_repetition (int i, String var) {

        //here there is the assumption that increase and decrease are internal cycle free, which makes a lot of sense. Formally
        //rhs(e) \cap lhs(e) = \emptyset
        return "(= " + this.getFluentAffected().toSmtVariableString(i + 1) + " " + this.to_smtlib_with_repetition_for_the_right_part(i, var) + ")";
    }

    public String to_smtlib_with_repetition_for_the_right_part (int i, String var) {

        //here there is the assumption that increase and decrease are internal cycle free, which makes a lot of sense. Formally
        //rhs(e) \cap lhs(e) = \emptyset
        if (this.operator.equals("increase")) {
            return "(+ " + this.getFluentAffected().toSmtVariableString(i) + " (* " + var + " " + this.getRight().toSmtVariableString(i) + " ))";
        } else if (this.operator.equals("decrease")) {
            return "(- " + this.getFluentAffected().toSmtVariableString(i) + " (* " + var + " " + this.getRight().toSmtVariableString(i) + " ))";
        } else if (this.operator.equals("assign")) {
            ExtendedNormExpression r = (ExtendedNormExpression) this.getRight();
            ExtendedNormExpression new_right = new ExtendedNormExpression(0d);
            Double alpha = null;
            for (ExtendedAddendum ad : r.summations) {
                if (ad.bin != null) {
                    System.out.println("Error: Trying to roll up an unrollable action");
                    System.exit(-1);
                }

                if (ad.f != null && ad.f.equals(this.getFluentAffected())) {
                    if (ad.n > 0f || ad.n == -1f) {
                        ///setting the alpha...
                        alpha = ad.n;
                    } else {
                        System.out.println("Error: Trying to roll up an unrollable action");
                        System.exit(-1);
                    }
                } else {
                    new_right.summations.add(ad);
                }
            }
            if (alpha == null) {
                return new_right.toSmtVariableString(i);
            } else if (alpha == -1f) {
                return "(- " + this.getFluentAffected().toSmtVariableString(i) + " (* " + var + " " + new_right.toSmtVariableString(i) + " ))";
            } else if (alpha == 1f) {
                return "(+ " + this.getFluentAffected().toSmtVariableString(i) + " (* " + var + " " + new_right.toSmtVariableString(i) + " ))";
            } else {
                String exp_variable = "(/ (^ " + alpha + " (+ " + var + " 1) )(- " + alpha + " 1) )";
                return "(+ (* (^ " + alpha + " " + var + " ) " + this.getFluentAffected().toSmtVariableString(i) + " )" + " "
                        + "(* " + exp_variable + " " + new_right.toSmtVariableString(i) + " ))";
            }
        }

        return null;
    }

    public Set<NumFluent> getInvolvedFluents ( ) {
        Set<NumFluent> ret = new LinkedHashSet();
        ret.add(this.getFluentAffected());
        ret.addAll(this.getRight().getInvolvedNumericFluents());
        return ret;
    }

    public NumEffect generate_m_times_extension (NumFluent m) throws CloneNotSupportedException {
        NumEffect ret = new NumEffect(this.operator);
        ret.setFluentAffected(fluentAffected);
        ExtendedNormExpression temp = (ExtendedNormExpression) this.getRight();

        //this applies for the case in which the effects of the action are increase decrease or assign without cycles.
        ret.setRight(temp.mult(m.normalize()));
        return ret;
    }

    @Override
    public boolean involve (NumFluent a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean internal_cycle ( ) {
//        boolean ret = false;
//        switch (this.getOperator()) {
//            case "increase":
//                ret = this.getRhs().involve(this.fluentAffected);
//                break;
//            case "decrease":
//                ret = this.getRhs().involve(this.fluentAffected);
//                break;
//            case "assign":
//                ret = this.getRhs().involve(this.fluentAffected);
//                break;
//        }
        return this.getRight().involve(this.fluentAffected);
    }

    @Override
    public String pddlPrintWithExtraObject ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the pseudo_num_effect
     */
    public boolean isPseudo_num_effect ( ) {
        return pseudo_num_effect;
    }

    /**
     * @param pseudo_num_effect the pseudo_num_effect to set
     */
    public void setPseudo_num_effect (boolean pseudo_num_effect) {
        this.pseudo_num_effect = pseudo_num_effect;
    }

    @Override
    public HashMap apply (PDDLState state) {
        HashMap ret = new HashMap();
        apply(state, ret);
        return ret;
    }

    @Override
    public HashMap apply (RelState s) {//This follows as for Convex union
        HashMap ret = new HashMap();
        apply(s, ret);
        return ret;
    }

    @Override
    public void apply (State s, Map modifications) {
        if (!fluentAffected.has_to_be_tracked()) {
            return;
        }

        Double after = null;
        Double eval = this.getRight().eval(s);
        if (eval == null) {
            System.out.print("State:" + s);
            System.out.println("Applying a not applicable effect!:" + this);
            System.exit(-1);
        }

        if (this.operator.equals("increase")) {
            Double current = ((PDDLState) s).fluentValue(fluentAffected);
            if (current == null) {
                //System.out.println("PDDLState:"+s);
                //System.out.println("This effect cannot be applied!:" + this);
                //System.exit(-1);
            } else {
                after = current + eval;
            }
        } else if (this.operator.equals("decrease")) {
            Double current = ((PDDLState) s).fluentValue(fluentAffected);
            if (current == null) {
                //System.out.println("This effect cannot be applied!:" + this);
                //System.exit(-1);
            } else {
                after = current - eval;
            }
        } else if (this.operator.equals("assign")) {
            after = eval;
        }

        modifications.put(this.fluentAffected, after);
    }

    @Override
    public void apply (RelState s, Map modifications) {
        if (!fluentAffected.has_to_be_tracked()) {
            return;
        }
        HomeMadeRealInterval after = null;
        final HomeMadeRealInterval current = s.functionValues(fluentAffected);

        final HomeMadeRealInterval eval = this.getRight().eval(s);

        if (this.getOperator().equals("increase")) {
            if (current!= null ) {
                after = new HomeMadeRealInterval(Math.min(current.sum(eval).lo(),current.lo()),
                                         Math.max(current.sum(eval).hi(),current.hi()));
            }
        } else if (getOperator().equals("decrease")) {
            if (current!= null) {
                final HomeMadeRealInterval sub = current.subtract(eval);
                after = new HomeMadeRealInterval(Math.min(sub.lo(),current.lo()),
                        Math.max(sub.hi(),current.hi()));
            }
        } else if (getOperator().equals("assign")) {
            if (additive_relaxation) {
                if (this.getRight().getInvolvedNumericFluents().isEmpty() ||
                        ((Double.isNaN(current.lo())) && (Double.isNaN(current.hi())))) {
                    if (current == null ||((Double.isNaN(current.lo())) && (Double.isNaN(current.hi())))) {
                        after = new HomeMadeRealInterval(eval.lo(),eval.hi());
                    } else {
                        after = new HomeMadeRealInterval(Math.min(eval.lo(),current.lo()),
                                Math.max(eval.hi(),current.hi()));

                    }
                } else {//this allows us to give a monotonic semantic also for the assignment operation by exploiting the fact that x=f(x) \equiv x = f(x)+x-x
                    //the equivalence does hold in the master theory of arithmetic, but not in the interval based relaxation! That's where we introduce the
                    //monotonicity. Look at the report on generalize interval based relaxation.
                    BinaryOp bin = new BinaryOp(this.getRight(), "-", this.getFluentAffected(), true);
                    HomeMadeRealInterval monotonic_eval = bin.eval(s);
                    final HomeMadeRealInterval add = current.sum(monotonic_eval);
                    after = new HomeMadeRealInterval(
                            Math.min(add.lo(),current.lo()),
                            Math.max(add.hi(),current.hi())
                    );
                }
            } else if (current == null || ((Double.isNaN(current.lo())) && (Double.isNaN(current.hi())))) {
                after = new HomeMadeRealInterval(eval.lo(),eval.hi());
            } else {
                after = new HomeMadeRealInterval(
                        Math.min(eval.lo(),current.lo()),
                        Math.max(eval.hi(),current.hi())
                        );
            }

        }

        modifications.put(fluentAffected, after);
    }


    public RelState apply (RelState s, RelState prev) {
        if (!fluentAffected.has_to_be_tracked()) {
            return s;
        }
        HomeMadeRealInterval after = null;
        final HomeMadeRealInterval current = prev.functionValues(fluentAffected);

        final HomeMadeRealInterval eval = this.getRight().eval(s);
        
        if (eval == null){
            throw new RuntimeException("RHS is nan: "+this.getRight());
        }

        if (this.getOperator().equals("increase")) {
            if (current!= null) {
                final HomeMadeRealInterval add = current.sum(eval);
                after = new HomeMadeRealInterval(Math.min(add.lo(),current.lo()),
                        Math.max(add.hi(),current.hi()));
            }
        } else if (getOperator().equals("decrease")) {
            if (current!= null) {
                final HomeMadeRealInterval sub = current.subtract(eval);
                after = new HomeMadeRealInterval(Math.min(sub.lo(),current.lo()),
                        Math.max(sub.hi(),current.hi()));
            }
        } else if (getOperator().equals("assign")) {
            if (additive_relaxation) {
                if (this.getRight().getInvolvedNumericFluents().isEmpty() || current == null ||
                        ((Double.isNaN(current.lo())) && (Double.isNaN(current.hi())))) {
                    if (current == null ||((Double.isNaN(current.lo())) && (Double.isNaN(current.hi())))) {
                        after = new HomeMadeRealInterval(eval.lo(),eval.hi());
                    } else {
                        after = new HomeMadeRealInterval(Math.min(eval.lo(),current.lo()),
                                Math.max(eval.hi(),current.hi()));

                    }
                } else {//this allows us to give a monotonic semantic also for the assignment operation by exploiting the fact that x=f(x) \equiv x = f(x)+x-x
                    //the equivalence does hold in the master theory of arithmetic, but not in the interval based relaxation! That's where we introduce the
                    //monotonicity. Look at the report on generalize interval based relaxation.
                    final BinaryOp bin = new BinaryOp(this.getRight(), "-", this.getFluentAffected(), true);
                    HomeMadeRealInterval monotonic_eval = bin.eval(s);
                    final HomeMadeRealInterval add = current.sum( monotonic_eval);
                    after = new HomeMadeRealInterval(
                            Math.min(add.lo(),current.lo()),
                            Math.max(add.hi(),current.hi())
                    );
                }
            } else if (current == null || ((Double.isNaN(current.lo())) && (Double.isNaN(current.hi())))) {
                after = new HomeMadeRealInterval(eval.lo(),eval.hi());
            } else {
                after = new HomeMadeRealInterval(
                        Math.min(eval.lo(),current.lo()),
                        Math.max(eval.hi(),current.hi())
                );
            }

        }
        s.possNumValues.put(fluentAffected.getId(),after);
        return s;
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(");
        bui.append(getOperator());
        bui.append(" ");
        getFluentAffected().pddlPrint(typeInformation, bui);
        bui.append(" ");
        getRight().pddlPrint(typeInformation, bui);
        bui.append(")");
    }

    public ArrayList<Variable> getInvolvedVariables ( ) {
        // It is assumed that this method will be called only when the terms are ungrounded.  
        // Here be dragon.  
        final List list = this.fluentAffected.getTerms();
        final ArrayList<Variable> result = (ArrayList<Variable>) list;
        return result;
    }

    public void storeInvolvedVariables (Collection<Variable> vars) {
        // It is assumed that this method will be called only when the terms are ungrounded.  
        // Here be dragon.  
        for (final Object o : this.fluentAffected.getTerms()) {
            final Variable var = (Variable) o;
            vars.add(var);
        }
    }

    public Set<NumFluent> affectedNumericFluents ( ) {
        Set<NumFluent> ret = new HashSet();
        ret.add(fluentAffected);
        return ret;
    }

    @Override
    public Expression unifyVariablesReferences (PDDLProblem p) {
        this.fluentAffected = (NumFluent) this.fluentAffected.unifyVariablesReferences(p);
//        System.out.println("right:"+right);
        this.right = right.unifyVariablesReferences(p);
        return this;
    }

    @Override
    public ExtendedNormExpression normalize() {
        right = this.getRight().normalize();
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.operator);
        hash = 53 * hash + Objects.hashCode(this.fluentAffected);
        hash = 53 * hash + Objects.hashCode(this.right);
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
        final NumEffect other = (NumEffect) obj;
        if (!Objects.equals(this.operator, other.operator)) {
            return false;
        }
        if (!Objects.equals(this.fluentAffected, other.fluentAffected)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return true;
    }
    
    
}
