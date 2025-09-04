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
package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.Type;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author enrico
 */
public class PDDLObjects extends LinkedHashSet<PDDLObject> {

    public PDDLObjects ( ) {
    }

    public PDDLObjects (Collection<? extends PDDLObject> c) {
        super(c);
    }

    // Returns true if they have the same name (or both ``null''), except for the character case
    private static boolean objectsShareSameName (PDDLObject o1, PDDLObject o2) {
        // TODO: Should we really allow for null names?

        if (o1.getName() == null && o2.getName() == null) {
            return true;
        }

        return o1.getName().equalsIgnoreCase(o2.getName());

    }

    /**
     * Checks that the specified PDDLObject appears in this list of objects.
     *
     * @param t the object whose existence is checked.
     * @return true if the specified PDDL object appears in this list of PDDL
     * objects.
     */
    public boolean validate (PDDLObject t) {
        for (final PDDLObject el : this) {
            if (objectsShareSameName(el, t)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the type of the specified PDDL object. This method is preferable to
     * <code>t.getType()</code> as <code>t</code> may ignore its type.
     *
     * @param t the PDDL object.
     * @return the type of the specified PDDL object if valid, <code>null</code>
     * otherwise.
     */
    public Type getTermType (PDDLObject t) {
        for (final PDDLObject el : this) {
            if (objectsShareSameName(el, t)) {
                return el.getType();
            }
        }
        return null;
    }

    /**
     * Returns the PDDL object equivalent to the specified one. When
     * constructing a PDDLObject, it is always preferable to replace it
     * immediately with the result of the call to this method, as it reduces the
     * memory consumption.
     *
     * @param t the PDDL object.
     * @return the PDDL object equivalent to the specified one that appears in
     * this list of PDDL objects if existing, <code>null</code> otherwise.
     */
    public PDDLObject containsTerm (PDDLObject t) {
        for (final PDDLObject el : this) {
            if (objectsShareSameName(el, t)) {
                return el;
            }
        }
        return null;
    }

    @Override
    public String toString ( ) {
        final StringBuffer result = new StringBuffer();

        for (final PDDLObject el : this) {
            result.append(" ").append(el.toString()).append(" ");
        }

        return result.toString();
    }

    /**
     * Returns a string representation of this list of PDDLObject in PDDL
     * format.
     *
     * @return a PDDL representation of this list of PDDLObject.
     */
    public String pddlPrint ( ) {
        return pddlPrint("objects");
    }

    public String pddlPrint (String head) {
        final StringBuffer result = new StringBuffer();

        result.append("(:").append(head).append(" \n");
        for (final PDDLObject o : this) {
            result.append("   ").append(o.pddlPrint(true)).append("\n");
        }
        result.append(")\n");

        return result.toString();
    }

}
