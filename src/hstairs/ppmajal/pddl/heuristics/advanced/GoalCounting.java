/*
 * Copyright (C) 2019 enrico.
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
package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.Collection;

/**
 *
 * @author enrico
 */
public class GoalCounting implements SearchHeuristic{

    final private PDDLProblem problem;
    public GoalCounting(PDDLProblem problem){
        this.problem = problem;
    }
    @Override
    public float computeEstimate(State s) {
        int c = 0;
        Condition goals = this.problem.getGoals();
        if (goals instanceof AndCond){
            for (Object c1: ((AndCond) goals).sons){
                Condition con = (Condition)c1;
                if (!s.satisfy(con)){
                    c++;
                }
               
            }
        }
        return (float)c;
    }

    @Override
    public Object[] getTransitions(boolean helpful) {
        return problem.getActions().toArray();
    }
    @Override
    public Collection<TransitionGround> getAllTransitions() {
        return problem.getTransitions();
    }

}
