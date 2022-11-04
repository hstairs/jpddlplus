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
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author enrico
 */
public class PDDLVectorState extends State {

    public DoubleArrayList numFluents;
    public boolean[] boolFluents;
    public double time;

    public PDDLVectorState ( ) {
        super();

    }

    public PDDLVectorState (ArrayList<PDDLNumber> initialNumFluents, ArrayList<Boolean> initialPropFluents) {
        this.numFluents = new DoubleArrayList(initialNumFluents.size());
        for (int i = 0; i < initialNumFluents.size(); i++) {
            if (initialNumFluents.get(i) == null) {
                this.numFluents.add(Double.NaN);
            } else
                this.numFluents.add(initialNumFluents.get(i).getNumber().doubleValue());
        }
        this.boolFluents = new boolean[initialPropFluents.size()];
        for (int i = 0; i < initialPropFluents.size(); i++) {
            if (initialPropFluents.get(i) == null) {
                this.boolFluents[i] = false;
            } else
                this.boolFluents[i] = initialPropFluents.get(i);
        }
        time = -1;

    }

    public PDDLVectorState (DoubleArrayList numFluents, boolean[] propFluents) {
        this.numFluents = numFluents.clone();
        this.boolFluents = Arrays.copyOf(propFluents, propFluents.length);
        time = -1;
    }


    @Override
    public String toString ( ) {
        return "State{" + "currNumFluentsValues=" + numFluents + ", currPredValues=" + boolFluents + ", time=" + time + '}';
    }


    @Override
    public PDDLVectorState clone ( ) {
        PDDLVectorState ret_val = new PDDLVectorState(this.numFluents, this.boolFluents);
        ret_val.time = this.time;
        return ret_val;
    }

    @Override
    public int hashCode ( ) {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.numFluents);
        hash = 53 * hash + Arrays.hashCode(this.boolFluents);
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
        final PDDLVectorState other = (PDDLVectorState) obj;
        if (!Objects.equals(this.numFluents, other.numFluents)) {
            return false;
        }
        if (!Arrays.equals(this.boolFluents, other.boolFluents)) {
            return false;
        }
        return true;
    }


    public double fluentValue (NumFluent f) {
        if (f.getId() == -1) {
            throw new RuntimeException("Numeric Fluent " + f + " hasn't been assigned with a unique id ");
        }
        return this.numFluents.get(f.getId());

    }


    public boolean holds (BoolPredicate p) {
        return (p.getId() != -1 && (this.boolFluents[p.getId()]));
    }

    public void setNumFluent (NumFluent f, Double after) {
        if (f.getId() == -1) {
            throw new RuntimeException("This shouldn't happen and is a bug. Numeric fluent wasn't on the table");
//            f.getId() = this.numFluents.size(); //This should handle the case where numFluent wasn't initialised
//            this.numFluents.add(after);
        } else {
            this.numFluents.set(f.getId(), after);
        }
    }

    public void setPropFluent (BoolPredicate f, Boolean after) {
        if (f.getId() == -1) {
            throw new RuntimeException("This shouldn't happen and is a bug. Predicate fluent wasn't on the table");
//            f.getId() = this.numFluents.size(); //This should handle the case where propFluent wasn't initialised
//            this.boolFluents.add(after);
        } else {
            this.boolFluents[f.getId()] = after;
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

    public void apply (TransitionGround gr, State prev) {
        throw new UnsupportedOperationException();
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
        for (int i = 0; i < this.numFluents.size(); i++) {
            Double n = this.numFluents.get(i);
            if (n == null) {
                ret_val.possNumValues.put(i, null);
            } else
                ret_val.possNumValues.put(i, new HomeMadeRealInterval(this.numFluents.get(i)));

        }
        for (int i = 0; i < this.boolFluents.length; i++) {
            if (this.boolFluents[i])
                ret_val.possBollValues.put(i, 1);
            else
                ret_val.possBollValues.put(i, 0);
        }

        return ret_val;

    }

    public void updateValues (HashSet<NumFluent> toUpdate, PDDLVectorState temp) {
        for (NumFluent n : toUpdate) {
            this.setNumFluent(n, temp.fluentValue(n));
        }
    }

    void increase_time_by_epsilon ( ) {
        time += 0.1f;
    }

    @Override
    public List getNumFluents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getBoolFluentsSize() {
        return this.boolFluents.length;
    }





}
