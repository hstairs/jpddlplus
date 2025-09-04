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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author enrico
 */
public class SchemaParameters extends ArrayList {

    public static SchemaParameters createPar(Variable... input){
        return new SchemaParameters(Arrays.asList(input));
    }
    

    public SchemaParameters ( ) {
        super();
    }

    public SchemaParameters (SchemaParameters po) {
        super(po);
    }
    public SchemaParameters (List po) {
        super(po);
    }

    public Variable getVar (Variable o) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            Variable v = (Variable) it.next();
            if (v.getName() == null ? o.getName() == null : v.getName().equalsIgnoreCase(o.getName())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString ( ) {
        String ret = "(";
        for (Object o : this) {
            Variable v = (Variable) o;
            //System.out.println(v.getName());
            ret += v.pddlPrint();

        }
        return ret + ")";

    }


    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append("(");
        for (Object o : this) {
            Variable v = (Variable) o;
            //System.out.println(v.getName());
            v.pddlPrint(typeInformation, bui);

        }
        bui.append(")");

    }


}
