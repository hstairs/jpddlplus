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
package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;


/**
 * @author enrico
 */
public interface SearchHeuristic {

    /**
     *
     * @param s A state of the system
     * @return Distance to the goal
     */
    float computeEstimate(State s);
    
    default Collection getAllEstimates(){
        return Collections.EMPTY_LIST;
    }

    /**
     *
     * @param onlyHelpful
     * @return Return only the helpful actions, if the heuristic is thought to produce them
     */
    Object[] getTransitions(final boolean onlyHelpful);

    /**
     *
     * @return All actions that are deemed reachable by the heuristic model
     */
    Collection<TransitionGround> getAllTransitions();
}
