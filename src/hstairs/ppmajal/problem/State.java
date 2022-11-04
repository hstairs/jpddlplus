/*
 * Copyright (C) 2018 enrico.
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
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.List;

/**
 * @author enrico
 */
public abstract class State {

    private Iterable<TransitionGround> applicableActions;

    public State ( ) {
        super();
    }

    public abstract void apply (TransitionGround gr, State prev);

    public abstract boolean satisfy (final Condition input);

    @Override
    public abstract State clone ( );

    public boolean isSafeState ( ) {
        return true;
    }

    public Iterable<TransitionGround> getApplicableActions ( ) {
        return applicableActions;
    }

    public void setApplicableActions (Iterable<TransitionGround> applicableActions) {
        this.applicableActions = applicableActions;
    }

    public State getRepresentative ( ) {
        return this;
    }

    public abstract List getNumFluents();

    public abstract int getBoolFluentsSize();

}
