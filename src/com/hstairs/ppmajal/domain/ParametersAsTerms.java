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
package com.hstairs.ppmajal.domain;

import com.hstairs.ppmajal.conditions.PDDLObject;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author enrico
 */
public class ParametersAsTerms<T> extends ArrayList {

    private Integer cached_hash;

    public ParametersAsTerms(){
        super();
    }
    
    public ParametersAsTerms(ArrayList input) {
        this.addAll(input);
    }
    
    

    public PDDLObject containsTerm (PDDLObject o) {
        Integer ret_val = -1;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            PDDLObject t = (PDDLObject) it.next();
            if (t.getName() == null ? o.getName() == null : t.getName().equalsIgnoreCase(o.getName())) {
                return t;
            }
        }
        return null;
    }

    /**
     * @param o
     * @return
     */
//    @Override
//    public boolean equals(Object o){
//        ParametersAsTerms obj = (ParametersAsTerms)o;
//        for (PDDLObject po:(Collection<PDDLObject>)this){
//            for (PDDLObject po1:(Collection<PDDLObject>)obj){
//                if (!po.equals(po1))
//                    return false;
//            }
//        }
//        return true;
//    }
    public void addALLNewObjects (ParametersAsTerms a) {
        for (Object o : a) {
            PDDLObject obj = (PDDLObject) o;
            if (this.containsTerm(obj) == null) {
                this.add(obj);
            }
        }

    }

    public String pddlPrint ( ) {
        String ret = "";

        for (Object o : this) {
            PDDLObject t = (PDDLObject) o;
            ret += t.pddlPrint(true);
        }

        return ret;
    }

//    @Override
//    public int hashCode() {
//        if (cached_hash==null){
//            cached_hash = 3;
//            for (Object o: this){
//                cached_hash = 7 + o.hashCode();
//            }
//        }
//        return cached_hash;
//    }


}
