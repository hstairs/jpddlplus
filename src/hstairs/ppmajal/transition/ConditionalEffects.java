package com.hstairs.ppmajal.transition;

import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.NotCond;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.problem.PDDLProblem;
import java.util.*;

public class ConditionalEffects<T> {
    private Map<Condition, Collection<T>> actualConditionalEffects;
    private Collection<T> unconditionalEffect;

    public static ConditionalEffects<Terminal> stripsEffects(Terminal... input){
        final ConditionalEffects<Terminal> conditionalEffects = new ConditionalEffects<>();
        for (var v: input){
           conditionalEffects.add(v);
        }
        return conditionalEffects;
    }
    public static ConditionalEffects<NumEffect> numEffects(NumEffect... input){
        final ConditionalEffects<NumEffect> conditionalEffects = new ConditionalEffects<>();
        for (var v: input){
           conditionalEffects.add(v);
        }
        return conditionalEffects;
    }
    
    public ConditionalEffects weakEval(PDDLProblem ePddlProblem, Set invariantFluents) {
        ConditionalEffects res = new ConditionalEffects();
        if (actualConditionalEffects != null) {
            for (Map.Entry<Condition, Collection<T>> entry : actualConditionalEffects.entrySet()) {
                Collection<T> toAdd = new ArrayList();
                for (T e : entry.getValue()) {
                    if (e instanceof Condition) {
                        T name = (T) ((Condition) e).weakEval(ePddlProblem, invariantFluents);
                        if (!toAdd.contains(name)){
                            toAdd.add((T) ((Condition) e).weakEval(ePddlProblem, invariantFluents));
                        }
                    } else if (e instanceof NumEffect) {
                        toAdd.add((T) ((NumEffect) e).weakEval(ePddlProblem, invariantFluents));
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                final Condition condition = entry.getKey().weakEval(ePddlProblem, invariantFluents);
                toAdd.forEach(t1 -> res.add(condition,t1));
            }
        }
        if (unconditionalEffect != null) {
            for (T e : unconditionalEffect) {
                if (e instanceof Condition) {
                    res.add((T) ((Condition) e).weakEval(ePddlProblem, invariantFluents));
                } else if (e instanceof NumEffect) {
                    res.add((T) ((NumEffect) e).weakEval(ePddlProblem, invariantFluents));
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        }
        return res;
    }

    public void addRepetition(T element) {
        if (unconditionalEffect == null){
            unconditionalEffect = new ArrayList<>();
        }
        unconditionalEffect.add(element);
    }
    public void forceUnconditionalEffect(Collection<T> elements) {
        unconditionalEffect = elements;
    }


    public void add(T element){
        if (unconditionalEffect == null){
            unconditionalEffect = new HashSet<>();
        }
        unconditionalEffect.add(element);
    }
    public void add(Condition c, T element){
        if (actualConditionalEffects == null){
            actualConditionalEffects = new HashMap<>();
        }
        final Collection<T> ts = actualConditionalEffects.get(c);
        if (ts == null){
            final Collection<T> terminals = new ArrayList();
            terminals.add(element);
            actualConditionalEffects.put(c,terminals);
        }else{
            ts.add(element);
        }
    }

    public Collection<T> getUnconditionalEffect() {
        if (unconditionalEffect == null){
            return Collections.EMPTY_SET;
        }
        return unconditionalEffect;
    }

    public Map<Condition, Collection<T>> getActualConditionalEffects() {
        if (actualConditionalEffects == null){
            return Collections.emptyMap();
        }
        return actualConditionalEffects;
    }

    public Collection<T> getEffects(){
        if (unconditionalEffect == null){
            return Collections.EMPTY_SET;
        }
        return unconditionalEffect;
    }
    public Collection<T> getEffects(Condition c){
        if (actualConditionalEffects == null){
            return Collections.EMPTY_SET;
        }
        return actualConditionalEffects.get(c);
    }
    private Collection<T> getVariables(Collection<T> effects){
        Collection<T> res = new ArrayList<>();
        for (T e: effects){
            if (e instanceof NotCond){
                final Condition son = ((NotCond) e).getSon();
                res.add((T) son);
            }else if (e instanceof NumEffect) {
                res.add((T) e);
            }else {
                res.add(e);
            }
        }
        return res;
    }


    public Collection<T> getAllAffectedVariables() {
        final Collection<T> res = new ArrayList<>();
        for (Collection<T> ele : this.getActualConditionalEffects().values()) {
            res.addAll(getVariables(ele));
        }

        res.addAll(getVariables(this.getUnconditionalEffect()));

        return res;
    }


    public Collection<T> getAllEffects() {
        final Collection<T> res = new ArrayList<>();
        final Set<Map.Entry<Condition, Collection<T>>> ts = this.getActualConditionalEffects().entrySet();
        for (Map.Entry<Condition, Collection<T>> entry : ts) {
            res.addAll(entry.getValue());
        }
        res.addAll(this.getUnconditionalEffect());
        return res;
    }
    public ConditionalEffects<T> ground(Map<Variable, PDDLObject> substitution, PDDLObjects po){
        ConditionalEffects res = new ConditionalEffects();
        final Set<Map.Entry<Condition, Collection<T>>> entries = this.getActualConditionalEffects().entrySet();
        for (Map.Entry<Condition, Collection<T>> entry : entries) {
            for (T e : entry.getValue()) {
                if (e instanceof NumEffect) {
                    res.add(entry.getKey().ground(substitution, po), ((NumEffect) e).ground(substitution, po));
                } else {
                    res.add(entry.getKey().ground(substitution, po), ((Condition) e).ground(substitution, po));
                }
            }
        }
        for (T e : this.getUnconditionalEffect()) {
            if (e instanceof NumEffect) {
                res.add(((NumEffect) e).ground(substitution, po));
            } else {
                res.add(((Condition) e).ground(substitution, po));
            }
        }
        return res;
    }
    
    public Map<Condition,Collection> getAllConditionalEffects(){
        Map<Condition,Collection> ret = new HashMap();
        if (unconditionalEffect != null){
            if (!unconditionalEffect.isEmpty()){
                ret.put(BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE), unconditionalEffect);
            }        
        }
        if (actualConditionalEffects != null) {
            for (Map.Entry<Condition, Collection<T>> ent : actualConditionalEffects.entrySet()) {
                ret.put(ent.getKey(), ent.getValue());
            }
        }
        return ret;
    }
    public enum VariableType {PROPEFFECT, NUMEFFECT}

 
    
    
}