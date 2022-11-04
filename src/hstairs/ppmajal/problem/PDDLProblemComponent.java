/*
 * Copyright (C) 2017 Enrico Scala.
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

import com.hstairs.ppmajal.conditions.Condition;
import java.util.Collection;

/**
 * @author Enrico Scala
 */
public abstract class PDDLProblemComponent {


    public abstract Condition unifyVariablesReferences (PDDLProblem p);
    protected void addAll(Collection res, Object[] sons) {
        for (final Object o : sons) {
            res.add(o);
        }
    }
}
