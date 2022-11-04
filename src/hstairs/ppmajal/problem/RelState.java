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
package com.hstairs.ppmajal.problem;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author enrico
 */
public class RelState extends Object {

    final public Int2IntArrayMap possBollValues;//0 is negative, 1 positive, 2 both
    final public Int2ObjectArrayMap<HomeMadeRealInterval> possNumValues;

    public RelState (Int2IntArrayMap a, Int2ObjectArrayMap<HomeMadeRealInterval> b ) {
        super();
        possBollValues = a;
        possNumValues = b;
    }
    public RelState ( ) {
        super();
        possBollValues = new Int2IntArrayMap();
        possNumValues = new Int2ObjectArrayMap();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (int i: possNumValues.keySet()){
            NumFluent fluent = NumFluent.fromIdToNumFluents.get(i);
            str.append(fluent).append("=").append(possNumValues.get(i)).append("");
        }
        str.append("\n");
        for (BoolPredicate fluent : PDDLProblem.booleanFluents){
            str.append(fluent).append("=").append(this.canBeTrue(fluent)).append(" ");
            str.append(fluent).append("=").append(this.canBeFalse(fluent)).append(" ");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return ((RelState)obj).toString().equals(this.toString()); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public RelState clone ( ) {
        final RelState ret_val = new RelState(this.possBollValues.clone(),this.possNumValues.clone());
        return ret_val;
    }


    public double functionInfValue (NumFluent f) {
        final HomeMadeRealInterval n = this.possNumValues.get(f.getId());
        if (n != null) {
            return n.lo();
        }
        return Double.NaN;
    }

    public HomeMadeRealInterval functionValues (NumFluent f) {

        if (!this.possNumValues.isEmpty()) {
            final HomeMadeRealInterval a = this.possNumValues.get(f.getId());
            if (a != null) {
                return a;
            } else {
                return null;
            }
        }
        return null;

    }

    public double functionSupValue (NumFluent f) {
        final HomeMadeRealInterval a = this.possNumValues.get(f.getId());
        if (a != null) {
            return a.hi();
        }
        return Double.NaN;
    }

    public void makePositive (BoolPredicate p) {
        Integer inter = possBollValues.get(p.getId());
        if (inter == null) {//if was negative by default
            possBollValues.put(p.getId(), 2);
        } else if (inter == 0) {//if was said to be negative
            possBollValues.put(p.getId(), 2);
        }//otherwise it was already fine
    }


    public boolean canBeTrue (BoolPredicate p) {

        int o = this.possBollValues.getOrDefault(p.getId(), -1);
        if (o == -1) {
            return false;
        }
        return o >= 1;
    }

    public boolean canBeFalse (BoolPredicate p) {

        int o = this.possBollValues.getOrDefault(p.getId(), -1);
        if (o == -1) {
            return true;
        }
        return o == 0 || o == 2;
    }



    public void makeNegative (BoolPredicate p) {
        int inter = possBollValues.getOrDefault(p.getId(), -1);
        if (inter == -1) {//if was negative by default
        } else if (inter == 1) {//if was said to be positive it will also be negative
            possBollValues.put(p.getId(), 2);
        }//otherwise all good (inter == 2)
    }

    public boolean satisfy (Condition con) {

        if (con == null) {
            throw new RuntimeException("The testing condition appears to be null, and it shouldn't");
        }
        return con.canBeTrue(this);

    }

    public void setFunctionValues (NumFluent f, HomeMadeRealInterval after) {
        this.possNumValues.put(f.getId(), after);

    }



    public void update_values (HashMap subst) {
        for (final Object o : subst.keySet()) {
            if (o instanceof NumFluent) {
                NumFluent nf = (NumFluent) o;
                if (nf.has_to_be_tracked()) {
                    this.setFunctionValues(nf, (HomeMadeRealInterval) subst.get(o));
                }
            } else {
                this.possBollValues.put(((BoolPredicate) o).getId(), (int) subst.get(o));
            }
        }
    }
//
//    public RelState apply_with_generalized_interval_based_relaxation (TransitionGround gr) {
//        HashMap subst = new HashMap();
//        AndCond del = gr.getDelList();
//        if (del != null) {
//            subst.putAll(del.apply(this));
//        }
//        AndCond add = gr.getAddList();
//        if (add != null) {
//            subst.putAll(add.apply(this));
//        }
//
//        AndCond c = gr.getNumericEffects();
////        System.out.println("GroundAction:"+this);
//        subst.putAll(c.apply(this));
//
//        if (gr.conditionalEffects != null) {
//            AndCond c_eff = gr.conditionalEffects;
//            subst.putAll(c_eff.apply(this));
//        }
//
//        for (final Object o : subst.keySet()) {
//            if (o instanceof NumFluent) {
//                NumFluent nf = (NumFluent) o;
//                if (nf.has_to_be_tracked()) {
//
//                    this.setFunctionValues(nf, (Interval) subst.get(o));
//
//                }
//            } else {
//                this.possBollValues.put(((Predicate) o).getId(), (int) subst.get(o));
//            }
//        }
//        return this;
//    }
    public void apply(PostCondition effect, RelState prev) {
        if (effect instanceof AndCond){
            for (PostCondition c: (PostCondition[])((AndCond) effect).sons){
                this.apply((PostCondition)c, prev);
            }

        }else if (effect instanceof Collection){
            ((Collection) effect).forEach(o -> this.apply((PostCondition) o,prev));
        }else if (effect instanceof NotCond) {
            final NotCond nc = (NotCond) effect;
            final BoolPredicate p = (BoolPredicate) nc.getSon();
            if (this.possBollValues.get(p.getId()) == 1) {
                this.possBollValues.put(p.getId(), 2);
            }
        } else if (effect instanceof BoolPredicate) {
            if (this.possBollValues.get(((BoolPredicate) effect).getId()) == 0) {
                this.possBollValues.put(((BoolPredicate) effect).getId(), 2);
            }
        } else if (effect instanceof NumEffect) {
            ((NumEffect) effect).apply(this,prev);
        } else if (effect instanceof ConditionalEffect) {
            ConditionalEffect cond = (ConditionalEffect) effect;
            if (this.satisfy(cond.activation_condition)) {
                this.apply(cond.effect,prev);
            }
        }
    }



    public void apply(Collection<Terminal> effect, RelState prev){
        ((Collection) effect).forEach(o -> this.apply((PostCondition) o,prev));
    }


    public void apply (TransitionGround gr, RelState prev) {
        final Set<ConditionalEffects> effs = Set.of(gr.getConditionalPropositionalEffects(), gr.getConditionalNumericEffects());
        for (final ConditionalEffects<PostCondition> eff: effs) {
            for (final Map.Entry<Condition, Collection<PostCondition>> entry : eff.getActualConditionalEffects().entrySet()) {
                if (entry.getKey().isSatisfied(this)) {
                    for (final PostCondition n : entry.getValue()) {
                        this.apply(n, prev);
                    }
                }
            }
            for (final PostCondition n : eff.getUnconditionalEffect()) {
                this.apply(n, prev);
            }
        }
    }

}
