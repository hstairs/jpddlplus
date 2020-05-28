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

import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.problem.*;
import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author enrico
 */
public class BinaryOp extends Expression {

    protected String operator;
    private Expression lhs;
    private Expression rhs;

    public BinaryOp ( ) {
        super();

    }

    public BinaryOp (Expression lhs, String string, Expression rhs, boolean grounded) {
        this.operator = string;
        this.lhs = lhs;
        this.rhs = rhs;
        this.grounded = grounded;

    }

    @Override
    public String toString ( ) {
        if (getRhs() != null) {
            return "(" + getOperator() + " " + getLhs() + " " + getRhs() + ")";
        }
        return "(" + getOperator() + " (" + getLhs() + "))";
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
     * @return the one
     */
    public Expression getLhs ( ) {
        return lhs;
    }

    /**
     * @param one the one to set
     */
    public void setLhs (Expression one) {
        this.lhs = one;
    }

    /**
     * @return the right element of the binary operation
     */
    public Expression getRhs ( ) {
        return rhs;
    }

    /**
     * @param right the two to set
     */
    public void setRhs (Expression right) {
        this.rhs = right;
    }

    @Override
    public Expression ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        BinaryOp ret = new BinaryOp();

        ret.operator = this.operator;
        ret.lhs = lhs.ground(substitution, po);
        ret.rhs = rhs.ground(substitution, po);

        ret.grounded = true;

        return ret;
    }

    @Override
    public double eval (State s) {
        double ret_val = Double.NaN;
        double first = this.lhs.eval(s);
        double second = this.rhs.eval(s);
        if ((Double.isNaN(first)) || (Double.isNaN(first))) {
            return Double.NaN;//negation by failure.
        }
        switch (this.getOperator()) {
            case "+":
                ret_val = first + second;
                break;
            case "-":
                ret_val = first - second;
                break;
            case "*":
                ret_val = first * second;
                break;
            case "/":
                //System.out.println("divisione: " + new Float(first.getNumber()) / new Float(second.getNumber()));
                ret_val = first / second;
                break;
            case "min":
                //System.out.println("min: " + Math.min(first.getNumber(), second.getNumber()));
                ret_val = Math.min(first, second);
                break;
            case "^":
                //System.out.println("min: " + Math.min(first.getNumber(), second.getNumber()));
                ret_val = Math.pow(first, second);
                break;
            default:
                System.out.println(this.operator + " not supported");
                break;
        }
        return ret_val;
    }

    @Override
    public ExtendedNormExpression normalize ( ) {
        ExtendedNormExpression ret = new ExtendedNormExpression();
        this.setLhs(this.getLhs().normalize());
        this.setRhs(this.getRhs().normalize());

        ExtendedNormExpression l = (ExtendedNormExpression) this.getLhs();
        ExtendedNormExpression r = (ExtendedNormExpression) this.getRhs();

        try {
            if ((!r.isNumber() && this.getOperator().equals("/")) || ((!l.isNumber() && (this.getOperator().equals("^"))) || (!l.isNumber() && !r.isNumber() && ((this.getOperator().equals("*") || this.getOperator().equals("/")))))) {
                BinaryOp bin = new BinaryOp();
                bin.setOperator(this.getOperator());
                bin.setLhs(l);
                bin.setRhs(r);

                ret = new ExtendedNormExpression(bin);
                ret.linear = false;
                //System.out.println("Addendum:"+ret);
            } else {
                try {
                    switch (this.getOperator()) {
                        case "+":
                            ret = l.sum(r);
                            break;
                        case "-":
                            ret = l.minus(r);
                            break;
                        case "*":
                            //            System.out.println("DEBUG: left:"+left+" right:"+right);
                            ret = l.mult(r);
                            break;
                        case "/":
                            ret = l.div(r);
                            break;
                        case "^":
                            ret = l.pow(r);
                            break;
                        default:
                            System.out.println(this.operator + " not supported");
                            break;
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ExtendedNormExpression.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(BinaryOp.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println(this);
//        System.out.println("Left: "+l);
//        System.out.println("Right: "+r);
//        System.out.println("Result: "+ ret);
//        
        return ret;

    }



    @Override
    public Expression weakEval (PddlProblem s, Set invF) {
        BinaryOp ret = new BinaryOp();

        ret.operator = this.operator;
        lhs.freeVarSemantic = freeVarSemantic;
        rhs.freeVarSemantic = freeVarSemantic;
        final Expression left = lhs.weakEval(s, invF);
        final Expression right = rhs.weakEval(s, invF);

        if (left == null || right == null) {
            return null;
        }else{
            ret.lhs = left;
            ret.rhs = right;
        }
        if (ret.lhs instanceof PDDLNumber && ret.rhs instanceof PDDLNumber) {
            PDDLNumber first = (PDDLNumber) ret.lhs;
            PDDLNumber second = (PDDLNumber) ret.rhs;
            PDDLNumber ret_val = null;
            switch (this.getOperator()) {
                case "+":
                    ret_val = new PDDLNumber(first.getNumber() + second.getNumber());
                    break;
                case "-":
                    ret_val = new PDDLNumber(first.getNumber() - second.getNumber());
                    break;
                case "*":
                    ret_val = new PDDLNumber(first.getNumber() * second.getNumber());
                    break;
                case "/":
                    //System.out.println("divisione: " + new Float(first.getNumber()) / new Float(second.getNumber()));
                    ret_val = new PDDLNumber(first.getNumber() / second.getNumber());
                    break;
                case "min":
                    //System.out.println("min: " + Math.min(first.getNumber(), second.getNumber()));
                    ret_val = new PDDLNumber(Math.min(first.getNumber(), second.getNumber()));
                    break;
                case "^":
                    //System.out.println("min: " + Math.min(first.getNumber(), second.getNumber()));
                    ret_val = new PDDLNumber((float) Math.pow(first.getNumber(), second.getNumber()));
                    break;
                default:
                    System.out.println(this.operator + " not supported");
                    break;
            }
            return ret_val;
        }

        return ret;

    }

    @Override
    public Expression clone ( ) {
        BinaryOp ret = new BinaryOp();

        ret.operator = this.operator;
        ret.lhs = lhs.clone();
        ret.rhs = rhs.clone();

        ret.grounded = this.grounded;

        return ret;
    }

    @Override
    public RealInterval eval (RelState s) {
        RealInterval ret_val = null;
        final RealInterval first = this.lhs.eval(s);
        final RealInterval second = this.rhs.eval(s);

//        System.out.println(this);
        if ((first == null) || (second == null)) {
            return null;
        }
        if (Double.isNaN(first.lo()) || (Double.isNaN(first.hi())) || (Double.isNaN(second.lo())) || (Double.isNaN(second.hi()))) {
            return null;//negation by failure.
        }
        if (this.getOperator().equals("+")) {
            ret_val = IAMath.add(first,second);
//            ret_val.inf = new PDDLNumber(new Float(first.inf.getNumber()) + new Float(second.hi()));
//            ret_val.sup = new PDDLNumber(new Float(first.sup.getNumber()) + new Float(second.sup.getNumber()));
        } else if (this.getOperator().equals("-")) {
//            ret_val = first.subtract(second);
            ret_val = IAMath.sub(first,second);

//            ret_val.inf = new PDDLNumber(new Float(first.inf.getNumber()) - new Float(second.sup.getNumber()));
//            ret_val.sup = new PDDLNumber(new Float(first.sup.getNumber()) - new Float(second.hi()));
        } else if (this.getOperator().equals("*")) {
//            ret_val = first.mult(second);
            ret_val = IAMath.mul(first,second);

//            Float ac = new Float(first.inf.getNumber()) * new Float(second.hi());
//            Float ad = new Float(first.inf.getNumber()) * new Float(second.sup.getNumber());
//            Float bc = new Float(first.sup.getNumber()) * new Float(second.hi());
//            Float bd = new Float(first.sup.getNumber()) * new Float(second.sup.getNumber());
//            ret_val.inf = new PDDLNumber(Math.min(ac, Math.min(ad, Math.min(bc,bd))));
//            ret_val.sup = new PDDLNumber(Math.max(ac, Math.max(ad, Math.max(bc,bd))));
        } else if (this.getOperator().equals("/")) {
//            ret_val = first.div(second);
            ret_val = IAMath.div(first,second);

//            Float ac = new Float(first.inf.getNumber()) / new Float(second.hi());
//            Float ad = new Float(first.inf.getNumber()) / new Float(second.sup.getNumber());
//            Float bc = new Float(first.sup.getNumber()) / new Float(second.hi());
//            Float bd = new Float(first.sup.getNumber()) / new Float(second.sup.getNumber());
//            ret_val.inf = new PDDLNumber(Math.min(ac, Math.min(ad, Math.min(bc,bd))));
//            ret_val.sup = new PDDLNumber(Math.max(ac, Math.max(ad, Math.max(bc,bd))));
            //System.out.println("divisione: " + new Float(first.getNumber()) / new Float(second.getNumber()));
//            ret_val = new PDDLNumber(new Float(first.getNumber()) / new Float(second.getNumber()));
        } else if (this.getOperator().equals("^")) {
            ret_val = pow(first,second);


        }
        return ret_val;
    }

    public RealInterval pow (RealInterval first, RealInterval second) {
        if (first.lo() < 0){//This can be optimised
            return RealInterval.fullInterval();
        }else{
            return IAMath.power(first, second);
        }
        
    }

    @Override
    public boolean involve (Collection<NumFluent> arrayList) {
        if (this.lhs.involve(arrayList)) {
            return true;
        } else {
            return this.rhs.involve(arrayList);
        }
    }

    @Override
    public Expression subst (Condition numeric) {
        BinaryOp ret = (BinaryOp) this.clone();
        ret.lhs = ret.lhs.subst(numeric);
        ret.rhs = ret.rhs.subst(numeric);
        return ret;
    }

    @Override
    public Set getInvolvedNumericFluents ( ) {
        Set ret = new HashSet();
        ret.addAll(this.lhs.getInvolvedNumericFluents());
        ret.addAll(this.rhs.getInvolvedNumericFluents());
        return ret;
    }

    @Override
    public Expression unGround (Map substitution) {
        BinaryOp ret = new BinaryOp();

        ret.operator = this.operator;
        ret.lhs = lhs.unGround(substitution);
        ret.rhs = rhs.unGround(substitution);

        ret.grounded = false;

        return ret;
    }

    @Override
    public boolean isUngroundVersionOf (Expression expr) {
        if (expr instanceof BinaryOp) {
            BinaryOp bin = (BinaryOp) expr;
            if (bin.getOperator().equals(this.getOperator())) {
                return this.getLhs().isUngroundVersionOf(bin.getLhs()) && this.getRhs().isUngroundVersionOf(bin.getRhs());
            }
        }
        return false;
    }

    @Override
    public Expression susbtFluentsWithTheirInvariants (int j) {
        this.lhs = this.lhs.susbtFluentsWithTheirInvariants(j);
        this.rhs = this.rhs.susbtFluentsWithTheirInvariants(++j);
        return this;

    }


    @Override
    public String toSmtVariableString (int i) {
        return "(" + this.operator + " " + this.getLhs().toSmtVariableString(i) + " " + this.getRhs().toSmtVariableString(i) + ")";
    }

    @Override
    public boolean involve (NumFluent a) {
        if (this.lhs.involve(a)) {
            return true;
        } else {
            return this.rhs.involve(a);
        }
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(");
        bui.append(getOperator());
        bui.append(" ");
        getLhs().pddlPrint(typeInformation, bui);
        bui.append(" ");
        getRhs().pddlPrint(typeInformation, bui);
        bui.append(")");
    }

    @Override
    public Expression unifyVariablesReferences (EPddlProblem p) {
        if (this.lhs != null)
            this.lhs = this.lhs.unifyVariablesReferences(p);
        if (this.rhs != null)
            this.rhs = this.rhs.unifyVariablesReferences(p);
        return this;
    }
}
