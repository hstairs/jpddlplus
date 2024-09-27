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
package com.hstairs.ppmajal.PDDLProblem;

import com.carrotsearch.hppc.DoubleArrayList;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.FastTransitionTable;
import com.hstairs.ppmajal.problem.RelState;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author enrico
 */
public class PDDLState extends State {

protected DoubleArrayList numFluents;
//    private Int2DoubleArrayMap numFluents;
    private static int[] fromProblemNFId2StateNFId;
    private static int[] fromStateNFId2ProblemNFId;
    protected BitSet boolFluents;
    public BigDecimal time;

    private PDDLProblem p;
    
    private PDDLState(DoubleArrayList numFluents, BitSet boolFluents, PDDLProblem prob) {
        this.numFluents = numFluents.clone();
        this.boolFluents = (BitSet) boolFluents.clone();
        p = prob;

    }

    public static int[] getFromStateNFId2ProblemNFId() {
        return fromStateNFId2ProblemNFId;
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

    public PDDLState (HashMap<Integer,Double> inputNumFluents, BitSet otherBoolFluents, PDDLProblem p) {
        this.numFluents = new DoubleArrayList();
        if (NumFluent.numFluentsBank != null) {
            fromProblemNFId2StateNFId = new int[NumFluent.numFluentsBank.entrySet().size()];
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
        time = null;
        this.p = p;
    }
    

    @Override
    public String toString ( ) {
        StringBuilder str = new StringBuilder("");
        for (int i=0; i < numFluents.size(); i++){
            int idNFProblem = PDDLState.fromStateNFId2ProblemNFId[i];
            NumFluent fluent = NumFluent.fromIdToNumFluents.get(idNFProblem);
            str.append(fluent).append("=").append(numFluents.get(i)).append(" ");
        }
        if (time != null){
            str.append("(time)").append("=").append(time).append(" ");
        }
        str.append("\n");
        for (BoolPredicate fluent : PDDLProblem.booleanFluents){
            str.append(fluent).append("=").append(this.holds(fluent)).append(" ");
        }
        return str.toString();
    }

    @Override
    public PDDLState clone ( ) {
        PDDLState ret_val = new PDDLState(this.numFluents, this.boolFluents, p);
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
        if (f.getId() == -1 || fromProblemNFId2StateNFId[f.getId()] == -1) {
            return Double.NaN;
        }
//        System.out.println(f);
//        System.out.println(fromProblemNFId2StateNFId[f.getId()]);
        return this.numFluents.get(fromProblemNFId2StateNFId[f.getId()]);

    }


    public boolean holds (BoolPredicate p) {
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

    public void setPropFluent (BoolPredicate f, Boolean after) {
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
                    //System.out.println(c + "is not satisfied in " +this);
                    return false;
                }

            }

        }
        return true;

    }

    public static FastTransitionTable fastTransitionTable;
    public static boolean optimised = true;
    
    @Override
    public void apply (TransitionGround gr, State prev) {
        if (optimised){
            if (fastTransitionTable == null){
                fastTransitionTable = new FastTransitionTable(TransitionGround.totNumberOfTransitions);
            }
            if (!fastTransitionTable.done(gr.getId())) {
                final Collection<Pair<Condition, Collection<PostCondition>>> temp = new HashSet();
                final Set<ConditionalEffects> effs = Set.of(gr.getConditionalPropositionalEffects(), gr.getConditionalNumericEffects());
                for (ConditionalEffects<PostCondition> eff : effs) {
                    if (!eff.getActualConditionalEffects().isEmpty()){
                        for (final Entry<Condition, Collection<PostCondition>> entry : eff.getActualConditionalEffects().entrySet()) {
                            temp.add(Pair.of(entry.getKey(), entry.getValue()));
                        }
                    }
                    if (!eff.getUnconditionalEffect().isEmpty()){
                        temp.add(Pair.of(BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE),eff.getUnconditionalEffect()));
                    }
                    //eff = null;
                }
                fastTransitionTable.addEffect(gr.getId(), temp);
                
            }
            for (final var v : fastTransitionTable.getEffects(gr.getId())) {
                if (v.getKey().isSatisfied(prev)) {
                    for (final PostCondition n : v.getValue()) {
                        this.apply(n, prev);
                    }
                }

            }
        }else{
            final Set<ConditionalEffects> effs = Set.of(gr.getConditionalPropositionalEffects(), gr.getConditionalNumericEffects());
            for (final ConditionalEffects<PostCondition> eff: effs) {
                for (final Entry<Condition, Collection<PostCondition>> entry : eff.getActualConditionalEffects().entrySet()) {
                    if (entry.getKey().isSatisfied(prev)) {
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


    @Override
    public boolean satisfy (final Condition input) {
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

            } else if (o instanceof BoolPredicate) {
                if (!this.holds((BoolPredicate) o)) {
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
            if (Double.isNaN(n)) {
                ret_val.possNumValues.put(fromStateNFId2ProblemNFId[i], null);
            } else {
                ret_val.possNumValues.put(fromStateNFId2ProblemNFId[i], new HomeMadeRealInterval(n));
            }
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


    public void apply(PostCondition effect, State prev) {
        if (effect instanceof AndCond){
            for (PostCondition c: (PostCondition[])((AndCond) effect).sons){
                this.apply((PostCondition)c, prev);
            }
        }else if (effect instanceof NotCond) {
            final NotCond nc = (NotCond) effect;
            this.setPropFluent((BoolPredicate) nc.getSon(), false);
        } else if (effect instanceof BoolPredicate) {
            this.setPropFluent((BoolPredicate) effect, true);
        } else if (effect instanceof NumEffect) {
            final NumEffect nf = (NumEffect) effect;
            if (p.isSubgoalsRelevant(nf.getFluentAffected())) {
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
        } 
    }




}
