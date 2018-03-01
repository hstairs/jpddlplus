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
package conditions;

import domain.Variable;
import expressions.NumFluent;
import heuristics.utils.achiever_set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import problem.PDDLGroundAction;
import problem.PDDLObjects;
import problem.PDDLProblemComponent;
import problem.RelState;
import problem.PDDLState;
import problem.PddlProblem;

/**
 *
 * @author enrico
 */
public abstract class Condition extends PDDLProblemComponent {

    public boolean grounded;
    // TODO: Make a ConditionsWithSons class that sits between AndCond/OrCond/OneOf and Conditions
    protected boolean freeVarSemantic = false;
    private boolean unsatisfiable = false;
    private boolean valid = false;
    protected int heuristicId;

    public Condition() {
        //son = new HashSet();
        grounded = false;
        unsatisfiable = false;
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    /**
     *
     * @param p
     * @param s
     * @param invF
     * @return
     */
    public abstract Condition weakEval(PddlProblem p, HashMap invF);
    //public abstract void addConditions(Conditions o);

    /*
        The following regression operation operates under the following assumptions:
            1) Action do not have conflicting effects. 
                a) Conditional effects will never conflict
                b) Add and Delete List will never conflict
            2) Any literals (positive or negative) caused by an action 
               takes part just in one of the conditional effects of the action.
               Add and Del list can be seen as conditional effects with empty
               activation conditions.
    
     */
    public abstract Condition regress(PDDLGroundAction gr);

    /**
     * Substitutes the variables in this conditions with the PDDLObjects
     * associated to each variable in the specified table. The returned object
     * is therefore grounded. This method may fail if there is no substitution
     * for some variable.
     *
     * @param substitution the map that indicates what object should replace the
     * specified variable.
     * @param objects These are the objects of the problem
     * @return a copy of this conditions where each variable is replaced to the
     * object according to the specified mapping.
     */
    public abstract Condition ground(Map<Variable, PDDLObject> substitution, PDDLObjects objects);

    public abstract Condition ground(Map substitution, int c);

    public abstract boolean eval(PDDLState s);

    public abstract String toSmtVariableString(int k, PDDLGroundAction gr, String var);

    public abstract boolean isSatisfied(PDDLState s);

    public abstract void changeVar(Map substitution);

    public abstract String pddlPrintWithExtraObject();

    @Override
    public abstract Condition clone();

    /**
     *
     * @param aThis
     * @return
     */
    public abstract boolean can_be_true(RelState aThis);

    public abstract boolean can_be_false(RelState aThis);

    public abstract void normalize();

    public abstract Condition unGround(Map asbstractionOf);

    public abstract boolean isUngroundVersionOf(Condition conditions);

    public abstract String toSmtVariableString(int i);//just for and condition

    public abstract Set<NumFluent> getInvolvedFluents();

    /**
     * @return the freeVarSemantic
     */
    public boolean isFreeVarSemantic() {
        return freeVarSemantic;
    }

    /**
     * @param freeVarSemantic the freeVarSemantic to set
     */
    public void setFreeVarSemantic(boolean freeVarSemantic) {
        this.freeVarSemantic = freeVarSemantic;
    }

    /**
     * @return the unsatisfiable
     */
    public boolean isUnsatisfiable() {
        return unsatisfiable;
    }

    /**
     * @param unsatisfiable the unsatisfiable to set
     */
    public void setUnsatisfiable(boolean unsatisfiable) {
        this.unsatisfiable = unsatisfiable;
    }

    /**
     * @return the counter
     */
    public int getHeuristicId() {
        return heuristicId;
    }

    /**
     * @param counter the counter to set
     */
    public void setHeuristicId(int counter) {
//        System.out.println(this+ "Setting counter to"+counter);
        this.heuristicId = counter;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public abstract Condition transform_equality();

    public abstract boolean is_affected_by(PDDLGroundAction gr);

    /**
     *
     * @return
     */
    public abstract Collection<Predicate> getInvolvedPredicates();

    /**
     * Returns the list of variables involved in this conditions.
     *
     * @return the list of variables in this conditions.
     */
    public final ArrayList<Variable> getInvolvedVariables() {
        // TODO: not sure whether the result should be a list or a set.
        final ArrayList<Variable> result = new ArrayList<>();
        storeInvolvedVariables(result);
        return result;
    }

    /**
     * Stores in the specified collection the list of variables involved in this
     * conditions.
     *
     * @param vars the list where the variables involved in this condition are
     * to be stored.
     */
    public abstract void storeInvolvedVariables(Collection<Variable> vars);

    /**
     * Returns a string representation of this condition in PDDL format.
     *
     * @param typeInformation <tt>true</tt>
     * if the type of the object should be printed as well.
     * @return a string representation in PDDL format of this condition.
     */
    public final String pddlPrint(boolean typeInformation) {
        final StringBuilder bui = new StringBuilder();
        pddlPrint(typeInformation, bui);
        return bui.toString();
    }

    /**
     * Prints this condition in PDDL format in the specified string builder.
     *
     * @param typeInformation <tt>true</tt>
     * if the type of the object should be printed as well.
     * @param bui the string builder where this condition is printed.
     */
    public abstract void pddlPrint(boolean typeInformation, StringBuilder bui);

    public abstract Set<Condition> getTerminalConditions();

    public abstract Float estimate_cost(ArrayList<Float> cond_dist, boolean additive_h);

    public abstract ComplexCondition and(Condition precondition);

    public abstract achiever_set estimate_cost(ArrayList<Float> cond_dist, boolean additive_h, ArrayList<PDDLGroundAction> established_achiever);

    public abstract Condition push_not_to_terminals();

    public boolean isSatisfied(RelState rs) {
        return rs.satisfy(this);
    }

    public abstract boolean isSatisfied(RelState rs, ArrayList<Integer> dist, int i);

//    public abstract Conditionss unify_num_fluent(State init);
    public abstract Condition introduce_red_constraints();

    public boolean mutual_exclusion_guaranteed(Condition preconditions) {
        if (preconditions instanceof AndCond && this instanceof AndCond) {
            AndCond a = (AndCond) preconditions;
            AndCond b = (AndCond) this;
            ArrayList<Condition> c1 = new ArrayList(a.getTerminalConditions());
            ArrayList<Condition> c2 = new ArrayList(b.getTerminalConditions());
            for (int i = 0; i < c1.size(); i++) {
                for (int j = i + 1; j < c2.size(); j++) {
                    Condition a_1 = c1.get(i);
                    Condition a_2 = c2.get(j);
                    if (a_1 instanceof NotCond && a_2 instanceof Predicate) {
                        NotCond nc = (NotCond) a_1;
                        if (nc.getSon().equals(a_2)) {
                            return true;
                        }
                    } else if (a_2 instanceof NotCond && a_1 instanceof Predicate) {
                        NotCond nc = (NotCond) a_2;
                        if (nc.getSon().equals(a_1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public  abstract void extendTerms(Variable v);
    

}
