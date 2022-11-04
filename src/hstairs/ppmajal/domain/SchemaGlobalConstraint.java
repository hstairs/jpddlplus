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

import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.problem.GlobalConstraint;
import com.hstairs.ppmajal.problem.PDDLObjects;
import java.util.HashMap;
import java.util.Map;

/**
 * @author enrico
 */
public class SchemaGlobalConstraint {

    public SchemaParameters parameters;
    public Condition condition;
    public String name;

    public SchemaGlobalConstraint (String name) {
        this.name = name;
        parameters = new SchemaParameters();
    }

    @Override
    public String toString ( ) {
        return "SchemaGlobalConstraint{" + "parameters=" + parameters + ", condition=" + condition + ", name=" + name + '}';
    }

    public GlobalConstraint ground (ParametersAsTerms par, PDDLObjects po) {
        GlobalConstraint ret = new GlobalConstraint(this.name);
        int i = 0;

        Map substitution = new HashMap();
        for (Object o : parameters) {
            Variable el = (Variable) o;
            substitution.put(el, par.get(i));
            i++;
        }
        ret.grn_parameters = par;

        if (this.condition != null) {
            ret.condition = this.condition.ground(substitution, po);
        }

        return ret;
    }

    public GlobalConstraint ground ( ) {
        GlobalConstraint ret = new GlobalConstraint(this.name);

        ret.grn_parameters = new ParametersAsTerms();
        ret.condition = this.condition;

        return ret;
    }

}
