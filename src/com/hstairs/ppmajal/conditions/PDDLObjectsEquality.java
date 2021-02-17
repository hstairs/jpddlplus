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
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.heuristics.utils.AchieverSet;
import com.hstairs.ppmajal.problem.*;

import java.util.*;

/**
 * @author enrico
 */
public class PDDLObjectsEquality extends Terminal {

    private PDDLObject left;
    private PDDLObject right;
    private Variable leftV;
    private Variable rightV;

    public PDDLObjectsEquality ( ) {
        grounded = false;
    }

    public PDDLObjectsEquality (Variable leftV, Variable rightV) {
        this.leftV = leftV;
        this.rightV = rightV;
        grounded = false;
    }

    private PDDLObjectsEquality (PDDLObject t1, PDDLObject t2) {
        left = t1;
        right = t2;
        grounded = true;
    }

    /**
     * @return a grounded copy of the condition
     */
    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        // TODO: Couldn't one of the objects be already grounded?

        PDDLObjectsEquality ret = null;

        PDDLObject t1 = substitution.get(getLeftV());
        PDDLObject t2 = substitution.get(getRightV());
        if ((t1 == null) || (t2 == null)) {
            System.out.println("Error in substitution  for " + getLeftV() + " or " + getRightV());
            System.exit(-1);
        } else {
            ret = new PDDLObjectsEquality(t1, t2);
        }

        return ret;

    }

    @Override
    public Condition ground (Map substitution, int c) {
        Condition ret = this.ground(substitution, null);
        ret.setHeuristicId(c);
        return ret;
    }

    @Override
    public boolean eval (State s) {
        //it is actually independent from the state s. A state does not change the objects at hand.
        if (!grounded) {
            System.out.println(this + "  condition should be grounded..exit!");
            System.exit(-1);
        }

        return getLeft().equals(getRight());
    }

    @Override
    public boolean isSatisfied (State s) {
        //it is actually independent from the state s. A state does not change the objects at hand.
        if (!grounded) {
            System.out.println(this + "  condition should be grounded..exit!");
            System.exit(-1);
        }

        return getLeft().equals(getRight());
    }

//    @Override
//    public void changeVar (Map substitution) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//
//    }

    @Override
    public Condition clone ( ) {
        PDDLObjectsEquality ret = new PDDLObjectsEquality();
        ret.grounded = this.grounded;
        if (grounded) {
            ret.setLeft(this.getLeft());
            ret.setRight(this.getRight());
        } else {
            ret.setLeftV(this.getLeftV());
            ret.setRightV(this.getRightV());
        }
        return ret;
    }

    @Override
    public boolean can_be_true (RelState aThis) {
        //it is actually independent from the state s. A state does not change the objects at hand.
        //actions are not allowed to change objects..
        if (!grounded) {
            System.out.println(this + "  condition should be grounded..exit!");
            System.exit(-1);
        }

        return getLeft().equals(getRight());

    }

    @Override
    public Condition normalize ( ) {
        return this;
    }

    /**
     * @return the left
     */
    public PDDLObject getLeft ( ) {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft (PDDLObject left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public PDDLObject getRight ( ) {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight (PDDLObject right) {
        this.right = right;
    }

    /**
     * @return the leftV
     */
    public Variable getLeftV ( ) {
        return leftV;
    }

    /**
     * @param leftV the leftV to set
     */
    public void setLeftV (Variable leftV) {
        this.leftV = leftV;
    }

    /**
     * @return the rightV
     */
    public Variable getRightV ( ) {
        return rightV;
    }

    /**
     * @param rightV the rightV to set
     */
    public void setRightV (Variable rightV) {
        this.rightV = rightV;
    }

    @Override
    public Condition unGround (Map asbstractionOf) {
        PDDLObjectsEquality ret = null;

        Variable t1 = (Variable) asbstractionOf.get(getLeft().getName());
        Variable t2 = (Variable) asbstractionOf.get(getRight().getName());
        if ((t1 == null) || (t2 == null)) {
            System.out.println("Error in substitution  for " + getLeftV() + " or " + getRightV());
            System.exit(-1);
        } else {
            ret = new PDDLObjectsEquality(t1, t2);
        }
        ret.grounded = false;

        return ret;

    }

    @Override
    public boolean isUngroundVersionOf (Condition conditions) {
        if (conditions instanceof PDDLObjectsEquality) {
            PDDLObjectsEquality c = (PDDLObjectsEquality) conditions;
            Variable v1 = this.getLeftV();
            Variable v2 = this.getRightV();
            PDDLObject obj1 = c.getLeft();
            PDDLObject obj2 = c.getRight();
            //System.out.print("Matching Types between: "+ v.getType() + obj.getType());
            if (!v1.getType().equals(obj1.getType())) {
                if (!v1.getType().isAncestorOf(obj1.getType())) {
                    return false;
                }
            }
            if (!v2.getType().equals(obj2.getType())) {
                return v2.getType().isAncestorOf(obj2.getType());
            }
            return true;
        }
        return false;
    }

    @Override
    public String toSmtVariableString (int i) {
        if (this.left.equals(right)) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public String toString ( ) {
        return "PDDLObjectsEquality{" +
                "left=" + left +
                ", right=" + right +
                ", leftV=" + leftV +
                ", rightV=" + rightV +
                '}';
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        return new HashSet();
    }

    @Override
    public Condition weakEval (PddlProblem s, HashMap invF) {
        if (this.left.equals(this.right)) {
            this.setValid(true);
            this.setUnsatisfiable(false);
        } else {
            this.setUnsatisfiable(true);
            this.setValid(false);
        }
        return this;
    }

    @Override
    public String toSmtVariableString (int k, GroundAction gr, String var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition transformEquality ( ) {
        return this;
    }

    @Override
    public boolean is_affected_by (GroundAction gr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition regress (GroundAction gr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String pddlPrintWithExtraObject ( ) {
        String ret = "";
//        if (grounded) {
//            ret += "( = " + this.getLeft().pddlPrintWithExtraObject() + " " + this.getRhs().pddlPrintWithExtraObject() + ")";
//        } else {
        ret += "( = " + this.getLeftV().pddlPrint(false) + " " + this.getRightV().pddlPrint(false) + ")";

//        }
        return ret;
    }

    @Override
    public boolean can_be_false (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode ( ) {
        final int pddlObjectsHash, variablesHash;
        {
            final int h1, h2;
            if (left == null) {
                h1 = 0;
            } else {
                h1 = left.hashCode();
            }
            if (right == null) {
                h2 = 0;
            } else {
                h2 = right.hashCode();
            }
            pddlObjectsHash = (h1 * h2) + h1;
        }
        {
            final int h1, h2;
            if (leftV == null) {
                h1 = 0;
            } else {
                h1 = leftV.hashCode();
            }
            if (rightV == null) {
                h2 = 0;
            } else {
                h2 = rightV.hashCode();
            }
            variablesHash = (h1 * h2) + h1;
        }

        final int result = (pddlObjectsHash * variablesHash) + variablesHash;
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof PDDLObjectsEquality)) {
            return false;
        }

        final PDDLObjectsEquality other = (PDDLObjectsEquality) obj;

        if (left != right) { // they should differ from null
            if (!left.equals(right)) {
                return false;
            }
        }
        if (leftV != rightV) { // they should differ from null
            return leftV.equals(rightV);
        }

        return true;
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        if (grounded) {
            bui.append("( = ");
            getLeft().pddlPrint(typeInformation, bui);
            bui.append(" ");
            getRight().pddlPrint(typeInformation, bui);
            bui.append(")");
        } else {
            bui.append("( = ");
            getLeftV().pddlPrint(typeInformation, bui);
            bui.append(" ");
            getRightV().pddlPrint(typeInformation, bui);
            bui.append(")");
        }
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        vars.add(this.leftV);
        vars.add(this.rightV);
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float estimate_cost (ArrayList<Float> cond_dist, boolean additive_h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComplexCondition and (Condition precondition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AchieverSet estimate_cost (ArrayList<Float> cond_dist, boolean additive_h, ArrayList<GroundAction> established_achiever) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition push_not_to_terminals ( ) {
        return this;
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Predicate> getInvolvedPredicates ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition unifyVariablesReferences (EPddlProblem p) {
        return this;

    }
}
