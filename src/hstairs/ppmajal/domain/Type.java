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

import com.hstairs.ppmajal.extraUtils.DAG;
import java.util.HashMap;
import java.util.Set;

/**
 * @author enrico
 */
public class Type extends Object {

    /**
     * @return the hierarchy
     */
    public static DAG getHierarchy() {
        return hierarchy;
    }


    final private String name;
    final private String fatherType;
    final int id;
    
    private static HashMap<String,Type> typeDB;
    private static DAG hierarchy;
    
    
    public static Type type(String name) {
       
        Type father = Type.getType("object","null");
        if (name.equals("object")){
            return father;
        }
        if (getHierarchy() == null){
            hierarchy = new DAG();
        }
        if (typeDB == null){
            typeDB = new HashMap();
        }
        Type t = typeDB.get(name);
        if (t == null){
            t = new Type(name,father.name,typeDB.size());
            getHierarchy().addVertex(name);
            getHierarchy().addVertex(father.name);
            getHierarchy().addEdge(father.name, name);
            typeDB.put(name,t);
        }
        return t;
    }
    public static Type getType(String name, String father){
        if (getHierarchy() == null){
            hierarchy = new DAG();
        }
        if (typeDB == null){
            typeDB = new HashMap();
        }
        Type t = typeDB.get(name);
        if (t == null){
            t = new Type(name,father,typeDB.size());
            getHierarchy().addVertex(name);
            getHierarchy().addVertex(father);
            getHierarchy().addEdge(father, name);
            typeDB.put(name,t);
        }
        return t;
    }

    private Type(String name, String fatherType, int id) {
        this.name = name;
        this.fatherType = fatherType;
        this.id = id;
    }

    public boolean isAncestorOf (Type anc) {
        Set ancestors = getHierarchy().getAncestors(anc.name);
        if (ancestors.contains(name)){
            return true;
        }
        return false;
    }

    @Override
    public String toString ( ) {
        return " - " + getName() + " ";
    }

    public String pddlString ( ) {
        return this.getName() + " - " + this.getFatherType() + " ";
    }

    public String getName ( ) {
        return name;
    }
    
    public String getFatherType() {
        return fatherType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Type other = (Type) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
