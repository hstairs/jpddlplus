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
package com.hstairs.ppmajal.heuristics;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.extraUtils.Pair;
import com.hstairs.ppmajal.heuristics.advanced.h1;
import com.hstairs.ppmajal.problem.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author enrico
 */
public abstract class Heuristic {

    protected Collection<GroundAction> reachable;
    public boolean additive_h = true;
    public int debug = 0;
    public Collection<GroundAction> A;
    public ComplexCondition G;
    public boolean why_not_active = false;
    protected Collection<GroundAction> helpful_actions;
    
    
    public int numberOfReachableConditions;
    public int horizon = Integer.MAX_VALUE;
    public ComplexCondition gC;
    public boolean helpful_actions_computation = false;
    public Boolean allAchieverActions = true;
    protected Collection<Condition> conditionUniverse;
    protected int numberOfAtoms;
    protected int numberOfComplexConditions;
    protected boolean sat_test_within_cost = true;
    protected int numberOfHardConditions;
    protected HashMap<String, Integer> fromConditionStringToUniqueInteger;
    protected EPddlProblem problem ;
    protected boolean ignoreCostInHeuristic;
    protected boolean reachability;

    public Heuristic(EPddlProblem problem) {
        this.problem = problem;
        ignoreCostInHeuristic  = false;
    }
    

    public Heuristic(ComplexCondition G, Collection<GroundAction> A, Collection<GroundProcess> P, Collection<GroundEvent> E, ComplexCondition GC, EPddlProblem problem) {
        super();
        this.G = G;
        this.A = new LinkedHashSet();
        this.A.addAll(A);
        if (P != null) {
            this.A.addAll(P);
        }
        if (E != null) {
            this.A.addAll(E);
        }
        reachable = new LinkedHashSet();
        conditionUniverse = new ArrayList();
        this.gC = GC;
        this.problem = problem;
    }

    //this initializer is mandatory for being executed before each invocation of the heuristic
    public abstract Float setup(State s_0);

    public void forceUniquenessInConditionsAndInternalActions() {
        conditionUniverse = new ArrayList<>();
        int counter_conditions = 0;

        fromConditionStringToUniqueInteger = new HashMap();
        ArrayList<GroundAction> actions_to_consider = new ArrayList(A);
        for (GroundAction a : actions_to_consider) {

            if (a.getPreconditions() != null) {
                for (Condition condition : a.getPreconditions().getTerminalConditions()) {
                    counter_conditions = establishHeuristicConditionId(condition, counter_conditions);
                }
            }
        }
        for (GroundAction a : actions_to_consider) {
            if (a.getAddList() != null && a.getAddList().sons != null) {
                for (Condition condition : (Set<Condition>) a.getAddList().sons) {
                    counter_conditions = establishHeuristicConditionId(condition, counter_conditions);
                }
            }
        }

        for (Condition c_1 : G.getTerminalConditions()) {
            counter_conditions = establishHeuristicConditionId(c_1, counter_conditions);
        }
        numberOfAtoms = counter_conditions;//index of the last atom
    }

    /**
     * @param s_0
     * @return
     */
    abstract public Float computeEstimate(State s_0);

   


    protected int establishHeuristicConditionId(Condition c_1, int counter) {

        if (fromConditionStringToUniqueInteger.get(c_1.toString()) == null) {
            fromConditionStringToUniqueInteger.put(c_1.toString(), counter);
            c_1.setHeuristicId(counter);
            conditionUniverse.add(c_1);
            counter++;
        } else {
            c_1.setHeuristicId(fromConditionStringToUniqueInteger.get(c_1.toString()));
        }
        return counter;

    }

    protected void simplify_actions(State init) {
        for (GroundAction gr : this.A) {
            try {
                if (gr.getNumericEffects() != null && !gr.getNumericEffects().sons.isEmpty()) {
                    int number_numericEffects = gr.getNumericEffects().sons.size();
                    for (Iterator it = gr.getNumericEffects().sons.iterator(); it.hasNext();) {
                        NumEffect neff = (NumEffect) it.next();
                        if (neff.getOperator().equals("assign")) {
                            ExtendedNormExpression right = (ExtendedNormExpression) neff.getRight();
                            try {

                                //The following has been disabled as it is not clear whether particular assignments can indeed be treated
                                //as pseudo increase effects which aren't state dependent.
//                                if (true) {
//
//                                } else {
//                                    if (right.isNumber() && neff.getFluentAffected().eval(init) != null && (number_numericEffects == 1 || risky)) {
//                                        //constant effect
//                                        //Utils.dbg_print(3,neff.toString());
//                                        //                            if (number_numericEffects == 1) {
//                                        System.out.println(neff);
//                                        neff.setOperator("increase");
//                                        neff.setRhs(new BinaryOp(neff.getRhs(), "-", neff.getFluentAffected(), true).normalize());
//                                        neff.setPseudo_num_effect(true);
//                                        //                            }
//                                    }
////                                }
                            } catch (Exception ex) {
                                Logger.getLogger(h1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                gr.normalize();
            } catch (Exception ex) {
                Logger.getLogger(h1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.G = (ComplexCondition) this.G.normalize();
    }

    public float gValue(State s, Object transition, State next, Float previousG) {
        GroundAction gr = (GroundAction) transition;
        if (gr == null) {
            return previousG;
        }
        return getTransitionCost(s, gr,previousG,false);
    }
    
    protected float getTransitionCost(State s, GroundAction gr, Float previousG, boolean ignoreCost){   
        if (ignoreCost){
            return previousG + 1;
        }
        if (problem.isAction_cost_from_metric()){
            return previousG + gr.getActionCost(s,problem.getMetric());
        }else{
            return previousG + 1;
        }  
    }

    public Collection<GroundAction> getReachableTransitions() {
        if (this.reachable == null){
            return this.A;
        }
        return this.reachable;
    }

    /**
     * @return the helpful_actions
     */
    public Collection<GroundAction> getHelpfulActions() {
        return this.reachable;
    }

}
