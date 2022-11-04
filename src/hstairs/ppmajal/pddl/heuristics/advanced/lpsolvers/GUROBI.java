package com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.hstairs.ppmajal.pddl.heuristics.advanced;
//
//import com.hstairs.ppmajal.conditions.Comparison;
//import com.hstairs.ppmajal.conditions.Terminal;
//import com.hstairs.ppmajal.problem.State;
//import gurobi.GRB;
//import static gurobi.GRB.DoubleAttr.LB;
//import static gurobi.GRB.DoubleAttr.UB;
//import gurobi.GRBConstr;
//import gurobi.GRBEnv;
//import gurobi.GRBException;
//import gurobi.GRBLinExpr;
//import gurobi.GRBModel;
//import gurobi.GRBVar;
//import ilog.concert.IloException;
//import ilog.concert.IloRange;
//import it.unimi.dsi.fastutil.ints.IntSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author enrico
// */
//public class GUROBI extends LPInterface {
//
//    private GRBModel lp;
//    private GRBLinExpr objectiveFunction;
//    private GRBVar[] lpvar;
//    private GRBConstr[] lpcond;
//
//    GUROBI(LM aThis) {
//        super(aThis);
//
//    }
//
//    private void initLp(State s) {
//        if (lp == null) {
//            try {
//                lpvar = new GRBVar[h.heuristicNumberOfActions];
//                lpcond = new GRBConstr[h.totNumberOfTerms];
//                GRBEnv env = new GRBEnv(true);
//                env.set("logFile", "mip1.log");
//                env.set("OutputFlag", "0");
//                env.start();
//                lp = new GRBModel(env);
//                objectiveFunction = new GRBLinExpr();
//
//                for (int p : h.allConditions) {
//                    final Terminal terminal = Terminal.getTerminal(p);
//                    GRBLinExpr expr = new GRBLinExpr();
//                    for (int a : h.getAchiever(p)) {
//                        Float numericContribution = 1f;
//                        if (terminal instanceof Comparison) {
//                            numericContribution = h.getNumericContribution(a, p);
//                        }
//                        if (lpvar[a] == null) {
//                            lpvar[a] = lp.addVar(0.0, 0.0, 0.0, GRB.CONTINUOUS, Integer.toString(a));
//                            objectiveFunction.addTerm(h.actionCost[a], lpvar[a]);
//                        }
//                        expr.addTerm(numericContribution, lpvar[a]);
//                    }
//                    GRBConstr addConstr = lp.addConstr(expr, GRB.GREATER_EQUAL, 0.0, Integer.toString(p));
//                    lpcond[p] = addConstr;
//                }
//                lp.setObjective(objectiveFunction, GRB.MINIMIZE);
//                lp.write("temp.log");
//            } catch (GRBException e) {
//                System.out.println("Error code: " + e.getErrorCode() + ". "
//                        + e.getMessage());
//            }
//        }
//    }
//
//    @Override
//    public float solve(State s, IntSet lms) {
//
//        try {
//            initLp(s);
//            for (var i : h.allConditions) {//Need to be reset; only lm are going to be targeted with a value
//                lpcond[i].set(GRB.DoubleAttr.RHS, 0);
//            }
//            for (var lm : lms) {//by default they are not sat in the initial state
//                if (!h.conditionInit[lm]) {
//                    final Terminal t = Terminal.getTerminal(lm);
//                    final GRBConstr constraint = lpcond[lm];
//                    if (t instanceof Comparison) {
//                        double targetValue = -1d * ((Comparison) t).getLeft().eval(s);
//                        constraint.set(GRB.DoubleAttr.RHS, targetValue);
//                    } else {
//                        constraint.set(GRB.DoubleAttr.RHS, 1f);
//                    }
//                    for (var a : h.reachableAchievers[lm]) { //these are reachable actions achieving lm
//                        lpvar[a].set(GRB.DoubleAttr.UB,GRB.INFINITY);
//                    }
//                }
//            }
//            
//            lp.optimize();
//            double get = lp.get(GRB.DoubleAttr.ObjVal);
//            return (float) get;
//
//        } catch (GRBException ex) {
//            Logger.getLogger(LM.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Float.MAX_VALUE;
//    }
//}
//
//
