/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers;

import com.hstairs.ppmajal.pddl.heuristics.advanced.HGen;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArraySet;

/**
 *
 * @author enrico
 */
abstract public class LPSolverSingleAction {

    protected final IntArraySet conditions;
    protected final IntArraySet allActions;
    protected final HGen h;
    protected final int actionId;

    public LPSolverSingleAction(IntArraySet conditions, IntArraySet allActions, HGen h, int actionId) {
        this.conditions = conditions;
        this.allActions = allActions;
        this.h = h;
        this.actionId = actionId;
    }
    
    abstract public void initLp(State s);
    
    abstract public float solve(State s, IntArraySet activeActions);

}
