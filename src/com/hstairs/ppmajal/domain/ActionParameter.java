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
 * An <b>action parameter</b> is either a variable (when the action is not
 * grounded) or a PDDL Object (i.e., a constant, when the action is grounded).
 *
 * @author Alban Grastien
 */
public interface ActionParameter {

    /**
     * Grounds this action parameter according to the specified map. Any
     * occurrence of a variable is replaced with the corresponding PDDL object
     * (fails if the variable does not appear in the map). PDDL objects are
     * unchanged.
     *
     * @param substitution the map that indicates what the variables should be
     *                     replaced with.
     * @return the grounding of this action parameter.
     */
    PDDLObject ground (Map<Variable, PDDLObject> substitution);

}
