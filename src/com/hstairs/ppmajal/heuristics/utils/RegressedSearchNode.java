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
package com.hstairs.ppmajal.heuristics.utils;

import com.hstairs.ppmajal.problem.GroundAction;

/**
 * @author enrico
 */
public class RegressedSearchNode implements Comparable {

    public GroundAction action;

    public RegressedSearchNode father;
    public int action_cost_to_get_here;

    public RegressedSearchNode (GroundAction action, int action_cost_to_get_here) {
        this.action = action;
        this.father = father;
        this.action_cost_to_get_here = action_cost_to_get_here;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegressedSearchNode other = (RegressedSearchNode) obj;

        return this.action.equals(other.action);
    }

    @Override
    public int hashCode ( ) {
        int hash = 5;
        hash = 29 * hash + (this.action != null ? this.action.hashCode() : 0);
        hash = 29 * hash + (this.father != null ? this.father.hashCode() : 0);
        hash = 29 * hash + this.action_cost_to_get_here;
        return hash;
    }

    //    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 43 * hash + (this.s != null ? this.s.hashCode() : 0);
//        hash = 43 * hash + (this.action != null ? this.action.hashCode() : 0);
//        hash = 43 * hash + this.goal_distance;
//        hash = 43 * hash + (this.father != null ? this.father.hashCode() : 0);
//        hash = 43 * hash + this.action_cost_to_get_here;
//        return hash;
//    }
    @Override
    public int compareTo (Object o) {
        final RegressedSearchNode other = (RegressedSearchNode) o;
        if ((this.action_cost_to_get_here) == (other.action_cost_to_get_here)) {
            return 0;
        }
        if ((this.action_cost_to_get_here) < (other.action_cost_to_get_here)) {
            return -1;
        } else {
            return +1;
        }
    }

}
