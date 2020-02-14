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

import com.carrotsearch.hppc.DoubleArrayList;
import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.ConditionalEffect;
import com.hstairs.ppmajal.conditions.NotCond;
import com.hstairs.ppmajal.conditions.PostCondition;
import com.hstairs.ppmajal.conditions.Predicate;
import com.hstairs.ppmajal.expressions.Interval;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * @author enrico
 */
public class PDDLState extends State {

    protected DoubleArrayList numFluents;
//    private Int2DoubleArrayMap numFluents;
    private static int[] fromProblemNFId2StateNFId;
    private static int[] fromStateNFId2ProblemNFId;
    protected BitSet boolFluents;
    public double time;
    private PDDLState prev;
    
    private PDDLState(DoubleArrayList numFluents, BitSet boolFluents) {
        this.numFluents = numFluents.clone();
        this.boolFluents = (BitSet) boolFluents.clone();
    }

    private PDDLState(DoubleArrayList numFluents) {
        this.numFluents = numFluents.clone();
    }
    
    @Override
    public List getNumFluents() {
        return Arrays.asList(numFluents);
    }

    @Override
    public int getBoolFluentsSize() {
        return boolFluents.cardinality();
    }

    public PDDLState ( ) {
        super();
    }

    public PDDLState (HashMap<Integer,Double> inputNumFluents, BitSet otherBoolFluents) {
        this.numFluents = new DoubleArrayList();
        if (NumFluent.numFluentsBank != null) {
            fromProblemNFId2StateNFId = new int[NumFluent.numFluentsBank.values().size()];
            fromStateNFId2ProblemNFId = new int[inputNumFluents.entrySet().size()];
            Arrays.fill(fromProblemNFId2StateNFId, -1);
            Arrays.fill(fromStateNFId2ProblemNFId, -1);
            this.numFluents.resize(inputNumFluents.entrySet().size());
            int i = 0;
            for (Entry<Integer, Double> ele : inputNumFluents.entrySet()) {
                fromProblemNFId2StateNFId[ele.getKey()] = i;
                fromStateNFId2ProblemNFId[i] = ele.getKey();
                this.numFluents.set(i,ele.getValue());
                i++;
            }
        }
        this.boolFluents = (BitSet) otherBoolFluents.clone();
        time = -1;
    }
    

    @Override
    public String toString ( ) {
        StringBuilder str = new StringBuilder("");
        for (int i=0; i < numFluents.size(); i++){
            int idNFProblem = PDDLState.fromStateNFId2ProblemNFId[i];
            NumFluent fluent = NumFluent.fromIdToNumFluents.get(idNFProblem);
            str.append(fluent).append("=").append(numFluents.get(i)).append(" ");
        }
        str.append("\n");
        for (Predicate fluent : EPddlProblem.booleanFluents){
            str.append(fluent).append("=").append(this.holds(fluent)).append(" ");
        }
        return str.toString();
    }

    @Override
    public PDDLState clone ( ) {
        PDDLState ret_val = new PDDLState(this.numFluents, this.boolFluents);
        ret_val.time = this.time;
        return ret_val;
    }

    @Override
    public int hashCode ( ) {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.numFluents);
        hash = 53 * hash + this.boolFluents.hashCode();
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PDDLState other = (PDDLState) obj;
        if (!Objects.equals(this.numFluents, other.numFluents)) {
            return false;
        }
//                for (int i = 0 ; i< this.numFluents.elementsCount; i++){
//            final NumFluent nf = NumFluent.fromIdToNumFluents.get(fromStateId2Nf[i]);
//            if (nf.has_to_be_tracked()){
//                if (this.numFluents.get(i) != other.numFluents.get(i)){
//                    return false;
//                }
//            }else{
//                System.out.println(nf);
//            }
//        }
        if (!this.boolFluents.equals(other.boolFluents)) {
            return false;
        }
        return true;
    }


    public double fluentValue (NumFluent f) {
        if (f.getId() == -1) {
            return Double.NaN;
        }
        return this.numFluents.get(fromProblemNFId2StateNFId[f.getId()]);
    }


    public boolean holds (Predicate p) {
        return (p.getId() != -1 && (this.boolFluents.get(p.getId())));
    }

    public void setNumFluent (NumFluent f, Double after) {
        if (f.getId() == -1) {
            throw new RuntimeException("This shouldn't happen and is a bug. Numeric fluent wasn't on the table");
//            f.getId() = this.numFluents.size(); //This should handle the case where numFluent wasn't initialised
//            this.numFluents.add(after);
        } else {
            this.numFluents.set(fromProblemNFId2StateNFId[f.getId()], after);
        }
    }

    public void setPropFluent (Predicate f, Boolean after) {
        if (f.getId() == -1) {
            throw new RuntimeException("This shouldn't happen and is a bug. Predicate fluent wasn't on the table");
//            f.getId() = this.numFluents.size(); //This should handle the case where propFluent wasn't initialised
//            this.boolFluents.add(after);
        } else {
            this.boolFluents.set(f.getId(), after);
        }
    }


    public boolean satisfyNumerically (AndCond con) {

        for (Object o : con.sons) {

            if (o instanceof Comparison) {
                Comparison c = (Comparison) o;
                if (!c.isSatisfied(this)) {
                    return false;
                }

            }

        }
        return true;

    }

    @Override
    public void apply (GroundAction gr, State prev) {
        if (gr.getDelList() != null) {
            this.apply(gr.getDelList(), prev);
        }
        if (gr.getAddList() != null) {
            this.apply(gr.getAddList(), prev);
        }
        
        if (gr.cond_effects != null && !gr.cond_effects.sons.isEmpty()) {
            this.apply(gr.cond_effects, prev);
        }
        if (gr.getNumericEffects() != null && !gr.getNumericEffects().sons.isEmpty()) {
            this.apply(gr.getNumericEffects(), prev);
        }
        prev = null;

    }


    @Override
    public boolean satisfy (Condition input) {
        
        return input.isSatisfied(this);

    }

    public boolean whatIsNotsatisfied (AndCond con) {

        boolean ret = true;

        for (Object o : con.sons) {

            if (o instanceof Comparison) {
                Comparison c = (Comparison) o;
                if (!c.isSatisfied(this)) {
                    System.out.println(c + "is not satisfied ");
                    ret = false;
                }

            } else if (o instanceof Predicate) {
                if (!this.holds((Predicate) o)) {
                    System.out.println(o + "is not satisfied");
                    ret = false;
                }

            }

        }
        return ret;

    }

    public RelState relaxState ( ) {
        RelState ret_val = new RelState();
        for (int i = 0; i < this.numFluents.size(); i++) {
            double n = this.numFluents.get(i);
            if (n == Double.NaN) {
                ret_val.possNumValues.put(fromStateNFId2ProblemNFId[i], new Interval(Float.NaN));
            } else
                ret_val.possNumValues.put(fromStateNFId2ProblemNFId[i], new Interval(new Float(n)));

        }

        for (int i = 0; i < this.boolFluents.length(); i++) {
            if (this.boolFluents.get(i))
                ret_val.possBollValues.put(i, 1);
            else
                ret_val.possBollValues.put(i, 0);
        }

        return ret_val;

    }

    public void updateValues (HashSet<NumFluent> toUpdate, PDDLState temp) {
        for (NumFluent n : toUpdate) {
            this.setNumFluent(n, temp.fluentValue(n));
        }
    }

    void increase_time_by_epsilon ( ) {
        time += 0.1f;
    }

    private void apply(PostCondition effect, State prev) {        
        if (effect instanceof AndCond){
            for (PostCondition c: (Collection<PostCondition>)((AndCond) effect).sons){
                this.apply((PostCondition)c, prev);
            }
        }else if (effect instanceof NotCond) {
            final NotCond nc = (NotCond) effect;
            this.setPropFluent((Predicate) nc.getSon(), false);
        } else if (effect instanceof Predicate) {
            this.setPropFluent((Predicate) effect, true);
        } else if (effect instanceof NumEffect) {
            final NumEffect nf = (NumEffect) effect;
            if (nf.getFluentAffected().has_to_be_tracked()) {
                if (nf.getOperator().equals("increase")) {
                    final double currentValue = this.fluentValue(nf.getFluentAffected());
                    if (currentValue != Double.NaN) {
                        this.setNumFluent(nf.getFluentAffected(), currentValue + nf.getRight().eval(prev));
                    }
                } else if (nf.getOperator().equals("decrease")) {
                    final double currentValue = this.fluentValue(nf.getFluentAffected());
                    if (currentValue != Double.NaN) {
                        this.setNumFluent(nf.getFluentAffected(), currentValue - nf.getRight().eval(prev));
                    }
                } else if (nf.getOperator().equals("assign")) {
                    this.setNumFluent(nf.getFluentAffected(),  nf.getRight().eval(prev));
                }
            }
        } else if (effect instanceof ConditionalEffect) {
            ConditionalEffect cond = (ConditionalEffect) effect;
            if (this.satisfy(cond.activation_condition)) {
                this.apply(cond.effect,prev);
            }
        }
    }




}
