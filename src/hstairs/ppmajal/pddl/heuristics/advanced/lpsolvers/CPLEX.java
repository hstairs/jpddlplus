/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers;

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.pddl.heuristics.advanced.LM;
import com.hstairs.ppmajal.problem.State;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrico
 */
public class CPLEX extends LPSolver{

    private IloCplex lp;
    private IloNumVar[] lpvar;
    private IloRange[] lpcond;
    private IloLinearNumExpr objectiveFunction;

    public CPLEX(H1 h, IntArraySet universe) {
        super(h, universe);
    }
    public CPLEX(H1 h) {
        super(h);
    }

    @Override
    public void initLp(State s) {
        if (lp == null) {
            try {
                lpvar = new IloNumVar[h.cp.numActions()];
                lpcond = new IloRange[h.getTotNumberOfTerms()];
                lp = new IloCplex();
                lp.setOut(null);
                objectiveFunction = lp.linearNumExpr();
                for (int p : universe) {
                    final Terminal terminal = Terminal.getTerminal(p);
                    final IloLinearNumExpr expr = lp.linearNumExpr();
                    for (int a : h.getAchievers(p)) {
                        Float numericContribution = 1f;
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
    public float solve(State s, IntSet lms) {

        try {
            initLp(s);
            for (var i : universe) {//Need to be reset; only lm are going to be targeted with a value
                lpcond[i].setLB(0f);
            }
            for (var lm : lms) {//by default they are not sat in the initial state
                if (!h.getConditionInit()[lm]) {
                    final Terminal t = Terminal.getTerminal(lm);
                    final IloRange constraint = lpcond[lm];
                    if (t instanceof Comparison) {
                        double targetValue = -1d * ((Comparison) t).getLeft().eval(s);
                        constraint.setLB(targetValue);
                    } else {
                        constraint.setLB(1f);
                    }
                    for (var a : h.getReachableAchievers()[lm]) { //these are reachable actions achieving lm
                        lpvar[a].setUB(Float.MAX_VALUE);
                    }
                }
            }
            if (lp.solve()) {
                return (float) lp.getObjValue();
            }

        } catch (IloException ex) {
            Logger.getLogger(LM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Float.MAX_VALUE;
    }

    
}
