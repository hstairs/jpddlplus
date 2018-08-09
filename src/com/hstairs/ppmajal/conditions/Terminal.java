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
package com.hstairs.ppmajal.conditions;

import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.RelState;

import java.util.ArrayList;

/**
 * @author enrico
 */
public abstract class Terminal extends Condition {

    protected boolean isUnique;
    private int id;

    @Override
    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        int current_dist = dist.get(this.getHeuristicId());
        if (current_dist < i) {
            return true;
        } else if (this.can_be_true(rs)) {
            dist.set(this.getHeuristicId(), i);
            return true;
        }
        return false;
    }

    @Override
    public Condition introduce_red_constraints ( ) {
        return this;
    }


    public Condition unifyVariablesReferences(EPddlProblem p) {
        Terminal t = p.getTerminalReference(this.toString());
        if (t == null) {
            id = p.getNextTerminalReferenceId();
            this.isUnique = true;
            p.putTerminalReference(this);
            return this;
        }
        return t;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Terminal)) return false;
        Terminal terminal = (Terminal) o;
        return getId() == terminal.getId();
    }

    @Override
    public int hashCode ( ) {
        return getId();
    }

    public int getId ( ) {
        return id;
    }

}