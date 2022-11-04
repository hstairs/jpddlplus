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
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;

/**
 * @author enrico
 */
public class PDDLStateWithCache extends PDDLState {


    private int[] compSat; //0 is false //1 is true //-1 is unknown

    public PDDLStateWithCache(HashMap<Integer, Double> inputNumFluents, BitSet otherBoolFluents) {
        super(inputNumFluents, otherBoolFluents);
        initCompSat();
    }
    
    private void initCompSat(){
        
        if (Comparison.getComparisonDataBase()!= null){
            compSat = new int[Comparison.getComparisonDataBase().values().size()];
            Arrays.fill(compSat, -1);
        }
    }
    
    private PDDLStateWithCache(DoubleArrayList numFluents, BitSet boolFluents) {
        this.numFluents = numFluents.clone();
        this.boolFluents = (BitSet) boolFluents.clone();
        initCompSat();

    }

    
    @Override
    public PDDLStateWithCache clone ( ) {
        PDDLStateWithCache ret_val = new PDDLStateWithCache(this.numFluents, this.boolFluents);
        ret_val.time = this.time;
        return ret_val;
    }


    @Override
    public boolean satisfy (Condition input) {
        if (input instanceof Comparison){
            final Comparison comp = (Comparison)input;
//            System.out.println(comp.getId());
//            System.out.println(comp.id);
            if (compSat[comp.getId()] == -1){
                final double first = comp.getLeft().eval(this);
                final double second = comp.getRight().eval(this);
                if (comp.isSatisfied(first, second)){
                    compSat[comp.getId()] = 1;
                }else{
                    compSat[comp.getId()] = 0; 
                }   
            }
            return compSat[comp.getId()] == 1;
        }
        return input.isSatisfied(this);

    }

}