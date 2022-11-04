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
package com.hstairs.ppmajal.transition;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.PDDLState;
import java.util.*;


public abstract class Transition {
    public static int totNumberOfTransitions = 0;
    final private int id;
    private static ArrayList<Transition> id2transition= new ArrayList();

    public static ArrayList<Transition> getId2transition() {
        return id2transition;
    }

    public static void setId2transition(ArrayList<Transition> id2transition) {
        Transition.id2transition = id2transition;
    }

    public static Transition getTransition(int actionId) {
        if (actionId < id2transition.size()){
            return id2transition.get(actionId);
        }
        throw new RuntimeException(actionId+ " does not refer to a valid transition");
    }

    public static TransitionGround getTransition(String actionName, List<String> objects) {

        for (Transition transition : id2transition) {
            if (transition instanceof TransitionGround){
                if (transition.getName().equals(actionName)){
                    if ((((TransitionGround) transition).parameters.size() == objects.size())) {
                        boolean found = true;
                        for (int i = 0; i < ((TransitionGround) transition).parameters.size(); i++) {
                            if (!((TransitionGround) transition).parameters.get(i).getName().equals(objects.get(i))) {
                                found = false;
                                break;
                            }
                        }
                        if (found){
                            return (TransitionGround) transition;
                        }
                    }
                }
            }
        }
        return null;
    }
    private Collection<Terminal> achievableLiterals;

    public enum Semantics{ACTION, PROCESS, EVENT}

    final protected String name;
    final protected ConditionalEffects conditionalPropositionalEffects;
    final protected ConditionalEffects<NumEffect> conditionalNumericEffects;
    final protected Condition preconditions;
    final protected Semantics semantics;
    protected Transition(String name, ConditionalEffects conditionalPropositionalEffects, ConditionalEffects conditionalNumericEffects, Condition preconditions, Semantics semantcis) {
        this.name = name;
        if (conditionalPropositionalEffects == null){
            conditionalPropositionalEffects = new ConditionalEffects();
        }
        if (conditionalNumericEffects == null){
            conditionalNumericEffects = new ConditionalEffects();
        }
        this.conditionalPropositionalEffects = conditionalPropositionalEffects;
        this.conditionalNumericEffects = conditionalNumericEffects;
        this.preconditions = preconditions;
        this.semantics = semantcis;
        id = this.totNumberOfTransitions;
        getId2transition().add(this);
        totNumberOfTransitions++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Condition getPreconditions() {
        if (preconditions == null){
            return new AndCond(Collections.EMPTY_SET);
        }
        return preconditions;
    }

    public ConditionalEffects<NumEffect> getConditionalNumericEffects() {
        return conditionalNumericEffects;
    }

    public ConditionalEffects getConditionalPropositionalEffects() {
        return conditionalPropositionalEffects;
    }
    public Semantics getSemantics() {
        return semantics;
    }

    public Iterable<? extends BoolPredicate> getPropositionAffected() {
        return this.conditionalPropositionalEffects.getAllAffectedVariables();
    }

    public Collection<NumEffect> getAllNumericEffects() {
        if (conditionalNumericEffects == null){
            return Collections.EMPTY_SET;
        }
        return conditionalNumericEffects.getAllAffectedVariables();
    }
    public Collection<NumFluent> getAllNumericAffected() {
        Collection res = new HashSet();
        for (NumEffect numEffect : conditionalNumericEffects.getAllAffectedVariables()) {
            res.add(numEffect.getFluentAffected());
        }
        return res;
    }

    public boolean affect(NumFluent f) {
        return this.getAllNumericAffected().contains(f);
    }

    // TODO: 20/09/19 This function needs to be cached
    public Double getCoefficientAffected(NumFluent f) {
        HashMap<NumFluent,Double> coefficientAffected  = new HashMap<>();
        for (Object c : this.getConditionalNumericEffects().getAllEffects()) {
            NumEffect nEff = (NumEffect) c;
            ExtendedNormExpression right = (ExtendedNormExpression) nEff.getRight();
            if (nEff.getOperator().equals("increase") || nEff.getOperator().equals("decrease")) {
                coefficientAffected.put(nEff.getFluentAffected(), 1 + right.getCoefficient(nEff.getFluentAffected()));
            } else {
                coefficientAffected.put(nEff.getFluentAffected(), right.getCoefficient(nEff.getFluentAffected()));
            }
        }
        return coefficientAffected.get(f);
    }

    public float getValueOfRightExpApartFromAffected(NumFluent f, PDDLState s_0) {
        for (Object c : this.getConditionalNumericEffects().getAllEffects()) {
            NumEffect nEff = (NumEffect) c;
            if (nEff.getFluentAffected().equals(f)) {
                ExtendedNormExpression right = (ExtendedNormExpression) nEff.getRight();
                if (nEff.getOperator().equals("increase")) {
                    return right.eval_apart_from_f(f, s_0);
                } else if (nEff.getOperator().equals("decrease")) {
                    return right.eval_apart_from_f(f, s_0) * -1.0f;

                }
                return right.eval_apart_from_f(f, s_0);
            }

        }
        return 0;
    }

    public void updateInvariantFluents(Set actualFluents) {
        for (NumEffect nf : this.getAllNumericEffects()) {
            if (nf == null){
                throw new RuntimeException("This cannot happen: "+this);
            }
            actualFluents.add(nf.getFluentAffected());
        }
        for (BoolPredicate p : this.getPropositionAffected()) {
            actualFluents.add(p);
        }
    }

    public Collection<? extends NumFluent> getNumFluentsNecessaryForExecution() {
        Collection<NumFluent> res = new ArrayList<>();
        for (NumEffect e: (Collection<NumEffect>)this.getConditionalNumericEffects().getAllEffects()){
            res.addAll(e.getRight().getInvolvedNumericFluents());
        }
        return res;
    }
    //todo: cache this
    public Collection<Terminal> getAllAchievableLiterals() {
        if (achievableLiterals == null) {
            achievableLiterals = new HashSet<Terminal>();
            final Set<Map.Entry<Condition, Collection>> set = this.conditionalPropositionalEffects.getActualConditionalEffects().entrySet();
            for (Map.Entry<Condition, Collection> conditionCollectionEntry : set) {
                achievableLiterals.addAll(conditionCollectionEntry.getValue());
            }
            achievableLiterals.addAll(this.conditionalPropositionalEffects.getEffects());
            achievableLiterals = new ArrayList<>(achievableLiterals);
        }
        return achievableLiterals;
    }
}