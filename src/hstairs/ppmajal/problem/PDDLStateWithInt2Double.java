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

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import com.hstairs.ppmajal.expressions.NumFluent;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import java.util.*;

/**
 * @author enrico
 */
public class PDDLStateWithInt2Double extends PDDLState {

//    private DoubleArrayList numFluents;
    private Int2DoubleOpenHashMap numFluentsViaDoubleArray;
    
    private BitSet boolFluents;
    public double time;

    private PDDLStateWithInt2Double(Int2DoubleOpenHashMap numFluents, BitSet boolFluents) {
        this.numFluentsViaDoubleArray = numFluents.clone();
        this.boolFluents = (BitSet) boolFluents.clone();
    }

    @Override
    public List getNumFluents() {
        return Arrays.asList(numFluentsViaDoubleArray.keySet());
    }


    
    
    public int getBoolFluentsSize() {
        return boolFluents.cardinality();
    }

    public PDDLStateWithInt2Double ( ) {
        super();

    }

    public PDDLStateWithInt2Double (HashMap<Integer,Double> numFluents, BitSet otherBoolFluents) {
            
        this.numFluentsViaDoubleArray = new Int2DoubleOpenHashMap();
        if (NumFluent.numFluentsBank != null){
            for (Map.Entry<Integer, Double> ele: numFluents.entrySet()){
                this.numFluentsViaDoubleArray.put(ele.getKey().intValue(), ele.getValue().doubleValue());
            }
        }
        this.boolFluents = (BitSet) otherBoolFluents.clone();
        time = -1;
    }
    

    @Override
    public String toString ( ) {
        return "PDDLState{" +
                "numFluents=" + numFluentsViaDoubleArray +
                ", boolFluents=" + boolFluents +
                ", time=" + time +
                '}';
    }

    @Override
    public PDDLStateWithInt2Double clone ( ) {
        PDDLStateWithInt2Double ret_val = new PDDLStateWithInt2Double(this.numFluentsViaDoubleArray, this.boolFluents);
        ret_val.time = this.time;
        return ret_val;
    }

    @Override
    public int hashCode ( ) {
        int hash = 3;
        hash = 53 * hash + this.numFluentsViaDoubleArray.hashCode();
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
        final PDDLStateWithInt2Double other = (PDDLStateWithInt2Double) obj;
        if (!this.numFluentsViaDoubleArray.equals(other.numFluentsViaDoubleArray)){
//        if (!Objects.equals(this.numFluentsViaDoubleArray, other.numFluentsViaDoubleArray)) {
            return false;
        }
        if (!this.boolFluents.equals(other.boolFluents)) {
            return false;
        }
        return true;
    }


    @Override
    public double fluentValue (NumFluent f) {
        if (f.getId() == -1) {
            return Double.NaN;
        }
        return this.numFluentsViaDoubleArray.get(f.getId());

    }


    @Override
    public boolean holds (BoolPredicate p) {
        return (p.getId() != -1 && (this.boolFluents.get(p.getId())));
    }

    @Override
    public void setNumFluent (NumFluent f, Double after) {
        if (f.getId() == -1) {
            throw new RuntimeException("This shouldn't happen and is a bug. Numeric fluent wasn't on the table");
//            f.getId() = this.numFluents.size(); //This should handle the case where numFluent wasn't initialised
//            this.numFluents.add(after);
        } else {
            this.numFluentsViaDoubleArray.put(f.getId(), after.doubleValue());
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

    public boolean satisfy (AndCond con) {
        return con.isSatisfied(this);
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
//        for (int i = 0; i < this.numFluentsViaDoubleArray.size(); i++) {
        for (var v: this.numFluentsViaDoubleArray.int2DoubleEntrySet()){
            int i = v.getIntKey();
            double ele = v.getDoubleValue();
            if (Double.isNaN(ele)) {
                ret_val.possNumValues.put(i, null);
            } else
                ret_val.possNumValues.put(i, new HomeMadeRealInterval(ele));

        }


        for (int i = 0; i < this.boolFluents.length(); i++) {
            if (this.boolFluents.get(i))
                ret_val.possBollValues.put(i, 1);
            else
                ret_val.possBollValues.put(i, 0);
        }

        return ret_val;

    }

    public void updateValues (HashSet<NumFluent> toUpdate, PDDLStateWithInt2Double temp) {
        for (NumFluent n : toUpdate) {
            this.setNumFluent(n, temp.fluentValue(n));
        }
    }

    void increase_time_by_epsilon ( ) {
        time += 0.1f;
    }

}
