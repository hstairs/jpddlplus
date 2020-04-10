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

import java.util.HashMap;
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

/**
 * @author enrico
 */
public class Type extends Object {


    final private String name;
    final private String fatherType;
    final int id;
    
    private static HashMap<String,Type> typeDB;
    private static DirectedAcyclicGraph hierarchy;
    
    
    public static Type createType(String text) {
        if (hierarchy == null || typeDB == null || typeDB.get(text)==null){
            throw new RuntimeException("This type ("+text+") has not been defined before");
        }
        return typeDB.get(text);
    }
    public static Type createType(String name, String father){
        if (hierarchy == null){
            hierarchy = new DirectedAcyclicGraph(DefaultEdge.class);
        }
        if (typeDB == null){
            typeDB = new HashMap();
        }
        Type t = typeDB.get(name);
        if (t == null){
            t = new Type(name,father,typeDB.size());
            hierarchy.addVertex(name);
            hierarchy.addVertex(father);
            hierarchy.addEdge(father, name);
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
        Set ancestors = hierarchy.getAncestors(anc.name);
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
