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

import java.util.Map;

/**
 * @author enrico
 */
public class Variable implements ActionParameter {

    final private String Name;
    final private Type type;

    public Variable (String Name, Type type) {
        super();
        this.Name = Name;
        this.type = type;
    }

    public Variable (String text) {
        this(text,null);
    }


    @Override
    public PDDLObject ground (Map<Variable, PDDLObject> substitution) {
        final PDDLObject o = substitution.get(this);

        if (o == null) {
            System.out.println("Substitution Failed for " + o.toString());
            System.exit(-1);
        }

        return o;
    }

    @Override
    public String toString ( ) {

        return this.getName() + " " + this.getType();

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + java.util.Objects.hashCode(this.Name);
        hash = 67 * hash + java.util.Objects.hashCode(this.type);
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
        final Variable other = (Variable) obj;
        if (!java.util.Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!java.util.Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

//    @Override
//    public int hashCode ( ) {
//        int hash = 7;
//        hash = 97 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
//        hash = 97 * hash + (this.getType() != null ? this.getType().hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals (Object o) {
//        if (o instanceof Variable) {
//            Variable o1 = (Variable) o;
//            if ((o1.getName() == null ? this.getName() == null : o1.getName().equalsIgnoreCase(this.getName()))) {
//                return Objects.equal(this.type, o1.type);
//            } else {
//                return false;
//            }
//        }
//        return false;
//
//    }

    /**
     * @return the Name
     */
    public String getName ( ) {
        return Name;
    }


    /**
     * @return the type
     */
    public Type getType ( ) {
        return type;
    }

    public String pddlPrint ( ) {

        return this.getName() + " " + this.getType();
    }

    public String pddlPrint (boolean typeInformation) {
        if (typeInformation) {
            return this.getName() + " " + this.getType();
        } else {
            return this.getName();
        }
    }

    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append(this.getName());
        if (typeInformation) {
            bui.append(" ").append(this.getType());
        }
    }
}
