/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.OrCond;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.State;
import ilog.concert.IloLinearNumExpr;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.Arrays;
import java.util.Collection;


/**
 *
 * @author enrico
 */
public class LM extends H1 {

    final private boolean[] reachedConditions;
    final private boolean[] reachedActions;
    final private IntOpenHashSet[] lmC;
    final private IntOpenHashSet[] lmA;
    private final String mode;
    final private LPInterface lpSolver;

    final  IntArraySet[] reachableAchievers;
    protected IloLinearNumExpr objectiveFunction;
            

    public LM(EPddlProblem problem) {
        this(problem, "lmCount","no","cplex");
    }

    public LM(EPddlProblem problem, String mode, String redundantConstraints, String solver) {
        super(problem, true, true, false, redundantConstraints, false, false, false, false, null, false);
        reachedConditions = new boolean[totNumberOfTerms];
        reachedActions = new boolean[heuristicNumberOfActions];
        lmC = new IntOpenHashSet[totNumberOfTerms];
        lmA = new IntOpenHashSet[heuristicNumberOfActions];
        this.mode = mode;
        if ("cplex".equals(solver)) {
            lpSolver = new CPLEX(this);
        } else {
            throw new RuntimeException(solver+" is not supported");
//            lpSolver = new GUROBI(this);
        }
        reachableAchievers = new IntArraySet[totNumberOfTerms];
    }


    @Override
    public float computeEstimate(State s) {

        final IntArrayList q = quickReset(s);

        while (!q.isEmpty()) {
            final int a = q.popInt();
            if (a != pseudoGoal) {
                expand(a, q);
            }
        }

        if ("lmCount".equals(mode)) {
            return countMissing();
        } else if ("lp".equals(mode)) {
            return lpSolver.solve(s, lmA[pseudoGoal]);
        }
        return 0f;
    }

    boolean checkReached(final Condition c) {
        if (c instanceof AndCond) {
            final AndCond and = (AndCond) c;
            if (and.sons == null) {
                return true;
            }
            for (final Condition son : (Collection<Condition>) and.sons) {
                if (!checkReached(son)) {
                    return false;
                }
            }
            return true;

        } else if (c instanceof OrCond) {
            final OrCond and = (OrCond) c;
            if (and.sons == null) {
                return true;
            }
            for (final Condition son : (Collection<Condition>) and.sons) {
                if (checkReached(son)) {
                    return true;
                }
            }
            return false;
        } else if (c instanceof Terminal) {
            final Terminal t = (Terminal) c;
            return reachedConditions[t.getId()];
        } else {
            return true;
        }
    }

    private void updateActions(int i, IntArrayList q, int a) {
        final IntArraySet actions = conditionToAction[i];
        for (final int a1 : actions) {
            if (a == a1) {
                continue;
            }
            final Condition name = preconditionFunction[a1];
            if (reachedActions[a1] || checkReached(name)) {
                reachedActions[a1] = true;
                lmA[a1] = new IntOpenHashSet();
                if (name instanceof AndCond) {
                    for (final Object t : ((AndCond) name).sons) {
                        if (t instanceof Terminal) {
                            lmA[a1].addAll(lmC[((Terminal) t).getId()]);
                            if (!conditionInit[((Terminal) t).getId()]) {
                                lmA[a1].add(((Terminal) t).getId());
                            }
                        }
                    }
                } else {
                    throw new UnsupportedOperationException("Only And Condition supported");
                }
                q.add(a1);
            }
        }
    }

    private boolean updateCondition(int p, int a) {
        if (!reachedConditions[p]) {
            reachedConditions[p] = true;
            lmC[p] = new IntOpenHashSet(lmA[a]);
            return true;
        } else {
            boolean changed = false;
            final IntOpenHashSet newSet = new IntOpenHashSet();
            for (final int sg : lmC[p]) {
                if (!lmA[a].contains(sg)) {
                    changed = true;
                } else {
                    if (changed) {
                        newSet.add(sg);
                    }
                }
            }
            if (changed) {
                lmC[p] = newSet;
            }
            return changed;
        }
    }

    private void expand(int a, IntArrayList q) {
        final Collection<Integer> conditions = getConditionsAchievableById(a);
        for (final int p : conditions) {
            if (!conditionInit[p]) {
                reachableAchievers[p].add(a);
                final boolean changed = updateCondition(p, a);
                if (changed) {
                    updateActions(p, q, a);
                }
            }
        }
    }

    private void printLandmarks() {
        AndCond goal = (AndCond) problem.goals;
        System.out.println("Landmarks");
        for (int sg : lmA[pseudoGoal]) {
            System.out.println(Terminal.getTerminal(sg));
        }
    }

    private IntArrayList quickReset(State s) {
        final IntArrayList q = new IntArrayList();
        Arrays.fill(conditionInit, false);
        Arrays.fill(reachedConditions, false);
        Arrays.fill(reachedActions, false);
        
        for (final int i : allConditions) {
            reachableAchievers[i] = new IntArraySet();
            if (s.satisfy(Terminal.getTerminal(i))) {
                conditionInit[i] = true;
                reachedConditions[i] = true;
                lmC[i] = new IntOpenHashSet();
                updateActions(i, q, -1);
            }
        }
        for (final int a : freePreconditionActions) {
            q.add(a);
            lmA[a] = new IntOpenHashSet();
        }
        return q;
    }

    private int countMissing() {
        int lmCount = 0;
        for (int lm : lmA[pseudoGoal]) {
            if (!conditionInit[lm]) {
                lmCount++;
            }
        }
        return lmCount;
    }
}
