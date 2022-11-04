/*
 * Copyright (C) 2020 enrico.
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
package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import java.util.Iterator;
import org.jgrapht.alg.util.Pair;

/**
 *
 * @author enrico
 */
public interface SearchProblem {

    public  State getInit();

    public  Boolean goalSatisfied(State current);

    public  Iterator<Pair<State, Object>> getSuccessors(State s, Object[] actionsToSearch);

    public  boolean satisfyGlobalConstraints(State temp);

    public  Float gValue(State s, Object act, State temp, float gValue);

    public  boolean milestoneReached(Float d, Float current_value, State temp);
    
}
