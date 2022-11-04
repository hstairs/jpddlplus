/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers;

import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;

/**
 *
 * @author enrico
 */
abstract public class LPSolver {

    protected final H1 h;
    protected final IntArraySet universe;
    public LPSolver(H1 h){
        this(h,h.getAllConditions());
    }
    public LPSolver(H1 h, IntArraySet universe){
        this.h = h;
        this.universe = universe;
    }
    abstract public void initLp(State s);
    
    abstract public float solve(State s, IntSet lms);

}
