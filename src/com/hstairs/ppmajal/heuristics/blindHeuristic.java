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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.heuristics;

import com.hstairs.ppmajal.heuristics.advanced.h1;
import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.State;
import java.util.Collection;

import java.util.LinkedHashSet;

/**
 * @author enrico
 */
public class blindHeuristic extends Aibr {

    Collection<GroundAction> reachable;
    
    public blindHeuristic (EPddlProblem problem){
        super(problem);
    }

    @Override
    public Float setup(State s_0){
        reachable = new LinkedHashSet();
        reachable.addAll(problem.actions);
        reachable.addAll(problem.getProcessesSet());
        reachable.addAll(problem.getEventsSet());
        return computeEstimate(s_0);
    }
    @Override
    public Float computeEstimate (State s_0) {
        if (reachability){
            return super.computeEstimate(s_0);
        }
        if (s_0.satisfy(this.G)) {
            return 0f;
        } else {
            return 1f;
        }
    }

    @Override
    public Collection<GroundAction> getReachableTransitions() {
        return reachable; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
