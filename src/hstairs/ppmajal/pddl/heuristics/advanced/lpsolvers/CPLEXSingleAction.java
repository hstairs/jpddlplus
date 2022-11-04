/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers;

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.pddl.heuristics.advanced.HGen;
import com.hstairs.ppmajal.pddl.heuristics.advanced.LM;
import com.hstairs.ppmajal.problem.State;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrico
 */
public class CPLEXSingleAction extends LPSolverSingleAction {
    private IloCplex lp;
    private IloNumVar[] lpvar;
    private IloRange[] lpcond;
    private IloLinearNumExpr objectiveFunction;
    IntArraySet relevantActions;
    public CPLEXSingleAction(IntArraySet conditions, IntArraySet allActions, HGen h, int actionId) {
        super(conditions, allActions, h, actionId);
    }

    
    @Override
    public void initLp(State s) {
        if (lp == null) {
            try {
                lp = new IloCplex();
                relevantActions = h.getActionsAchievingAtLeastATerminalOfActionIdPreconditions(actionId);
                lpvar = new IloNumVar[h.cp.numActions()];
                lpcond = new IloRange[h.getTotNumberOfTerms()];
                lp.setOut(null);
                objectiveFunction = lp.linearNumExpr();
                for (int p : conditions) {
                    final Terminal terminal = Terminal.getTerminal(p);
                    final IloLinearNumExpr expr = lp.linearNumExpr();
                    for (int a : relevantActions) {
                        float numericContribution = 1f;
                        if (terminal instanceof Comparison) {
                            numericContribution = h.getNumericContribution(a, p);
                        }
                        if (lpvar[a] == null) {
                            lpvar[a] = lp.numVar(0.0, 0.0, IloNumVarType.Float);
                            objectiveFunction.addTerm(lpvar[a], h.getActionCost()[a]);
                        }
                        expr.addTerm(lpvar[a], numericContribution);
                    }
                    final IloRange ilo = lp.addGe(expr, 0f);
                    lpcond[p] = ilo;
                }
                lp.addMinimize(objectiveFunction);
            } catch (IloException ex) {
                Logger.getLogger(LM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public float solve(State s, IntArraySet activeActions) {
        try {
            float preLowerBound = 0f;
            for (var lm : conditions) {//by default they are not sat in the initial state
                    final Terminal t = Terminal.getTerminal(lm);
                    final IloRange constraint = lpcond[lm];
                    if (t instanceof Comparison) {
                        double targetValue = -1d * ((Comparison) t).getLeft().eval(s);
                        constraint.setLB(targetValue);
                    } else {
                        if (!s.satisfy(t)){
                            constraint.setLB(1f);
                        }
                    }
                    for (int a : relevantActions) { //these are reachable actions achieving lm
                        if (activeActions.contains(a)){
                            preLowerBound = Math.min(preLowerBound, h.getActionHCost()[a]);
                            lpvar[a].setUB(Float.MAX_VALUE);
                        }
                    }   
            }
            if (lp.solve()) {
                return (float) lp.getObjValue()+preLowerBound;
            }
        } catch (IloException ex) {
            Logger.getLogger(LM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Float.MAX_VALUE;
    }
    
}
