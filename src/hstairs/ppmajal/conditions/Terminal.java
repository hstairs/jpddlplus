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

import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.RelState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author enrico
 */
public abstract class Terminal extends Condition {

    private static int totCounter = 0;
    private static int totComparisonCounter = 0;

    private static ArrayList<Terminal> id2terminal = new ArrayList();
    private final int id;

    public Terminal() {
        this.id = getTotCounter();
        id2terminal.add(this);
        totCounter++;
//        System.out.println(totCounter);
        if (this instanceof Comparison){
            totComparisonCounter++;
        }
    }

    public int getId() {
        return id;
    }

    public static int getTotCounter() {
        return totCounter;
    }

    public static Terminal getTerminal(int t) {
        return id2terminal.get(t);
    }


    @Override
    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        int current_dist = dist.get(this.getId());
        if (current_dist < i) {
            return true;
        } else if (this.canBeTrue(rs)) {
            dist.set(this.getId(), i);
            return true;
        }
        return false;
    }

    @Override
    public Condition introduce_red_constraints ( ) {
        return this;
    }


    @Override
    public Condition unifyVariablesReferences (PDDLProblem p) {
        return this;
    }


    @Override
    public boolean involve (Condition c) {
        return this.equals(c);
    }


    @Override
    public List<Condition> getTerminalConditionsInArray ( ) {
        return Collections.singletonList(this);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Terminal) && ((Terminal) obj).getId() == getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

}
