/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers.CPLEXSingleAction;
import com.hstairs.ppmajal.pddl.heuristics.advanced.lpsolvers.LPSolverSingleAction;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.Arrays;
import java.util.Set;
import org.jgrapht.util.FibonacciHeap;

/**
 *
 * @author enrico
 */
public class HGen extends H1 {
    
    final private LPSolverSingleAction[] lps; 
    private int lpInvokations;
    private boolean greedy;
    private final IntArraySet[] activatedActions;
    public HGen(PDDLProblem problem) {
        super(problem, false);
        lps = new CPLEXSingleAction[cp.numActions()]; 
        activatedActions = new IntArraySet[cp.numActions()];
        greedy = false; //TODO Check the semantics of this
    }

    @Override
    public float computeEstimate(State gs) {
         PDDLState s = (PDDLState) gs;
        //PriorityQueue<ConditionsNode> q = new PriorityQueue();
        if (s.satisfy(problem.getGoals())) {
            return 0f;
        }
        
        FibonacciHeap<Integer> q = new FibonacciHeap();
        Arrays.fill(getActionHCost(), Float.MAX_VALUE);
        Arrays.fill(getClosed(), false);
        for (int v : allActions) {
            Condition c = cp.preconditionFunction()[v];
            if (c == null || s.satisfy(c)) {
                actionHCost[v] = 0f;
                addActionsInPriority(v, q, 0f);
                closed[v] = true;
            } else {
                lps[v] = getLP(v,gs);
            }
        }

        IntArraySet activeActions = new IntArraySet();
        IntArraySet temporaryConditions = new IntArraySet();
        
        while (!q.isEmpty()) {
            float first = -1;
            while (!q.isEmpty()) {//take all the elements with equal distance from the initial state
                
                if (this.additive && !this.reachability) {
                    if (actionHCost[cp.goal()] != Float.MAX_VALUE) {
                        return actionHCost[cp.goal()] ;
                    }
                }       
                int actionId = q.removeMin().getData();

                if (first == -1) {
                    first = actionHCost[actionId] ;
                } else if (first < actionHCost[actionId] ) {//put back and stop
                    addActionsInPriority(actionId, q, actionHCost[actionId]);
                    first = -1;
                    break;//exit from this inner loop and compute cost for new conditons that can be achieved
                }

                closed[actionId] = true;;//this is the best cost so far; no need to reopen this condition again

                if (actionId == cp.goal() && !this.reachability) {
                    if (actionHCost[actionId] == Float.MAX_VALUE) {
                        System.out.println("Anomaly");
                    }
                    return Math.max(actionHCost[actionId], 1f);
                }
                activeActions.add(actionId);
                if (this.reachability) {
                    this.reachableTransitions.add(actionId);
                }
                temporaryConditions.addAll(getActionsAchievableThroughThisAction(actionId));

            }
            for (int actionId : temporaryConditions) {
                if (!closed[actionId]) {
                    Float currentCost = null;
                    currentCost = lps[actionId].solve(s, activeActions);
                    lpInvokations++;
                    if (currentCost != Float.MAX_VALUE) {
                        if (this.greedy && actionId == cp.goal() && !this.reachability) {
                            return Math.max(currentCost, 1f);
                        }
                        if (currentCost < getActionHCost()[actionId]) {
                            if (getActionHCost()[actionId] == Float.MAX_VALUE) {
                                actionHCost[actionId] = currentCost;
                                addActionsInPriority(actionId, q, actionHCost[actionId]);
                            } else {
                                actionHCost[actionId] = currentCost;
                                q.decreaseKey(nodeOf[actionId], currentCost);
                            }
                        }
                    }
                }
            }
        }
        return Math.max(actionHCost[cp.goal()], 1f);
        
    }

    private IntCollection getActionsAchievableThroughThisAction(int actionId) {
        final IntArraySet ret = new IntArraySet();
        IntSet conditionsAchievableById = getInfluencedConditions(actionId);
        for (int v : allActions) {
            IntArraySet necessarySet = Utils.getNecessarySet(cp.preconditionFunction()[v]);
            for (int c : conditionsAchievableById) {
                if (necessarySet.contains(c)) {
                    ret.add(v);
                    break;
                }
            }

        }
        return ret;
    }       

    private IntSet getInfluencedConditions(int actionId) {
        if (activatedActions[actionId] == null){
            final IntArraySet achievableTerms = new IntArraySet();
            for (final int t : getAllComparisons()) {
                final float v = this.numericContribution(actionId, (Comparison) Terminal.getTerminal(t));
                if (v > 0 || v == UNKNOWNEFFECT || v < 0) {
                    achievableTerms.add(t);
                }
            }
            Sets.SetView<Integer> intersection = Sets.intersection(getAllConditions(), (Set<Integer>)cp.propEffectFunction()[actionId]);
            achievableTerms.addAll(intersection);
            for (final int o : intersection) {
                updateAchievers(o, actionId);
            }
            activatedActions[actionId] = achievableTerms;
        }
        
        return activatedActions[actionId];
    
    }

    private LPSolverSingleAction getLP(int actionId, State gs) {
        if (lps[actionId] == null){
            final CPLEXSingleAction solver = new CPLEXSingleAction(Utils.getNecessarySet(cp.preconditionFunction()[actionId]), allActions, this, actionId);
            solver.initLp(gs);
            lps[actionId] = solver;
        }
        return lps[actionId];
    }


    public IntArraySet getActionsAchievingAtLeastATerminalOfActionIdPreconditions(int actionId) {
        final IntArraySet ret = new IntArraySet();
        final IntArraySet necessarySet = Utils.getNecessarySet(cp.preconditionFunction()[actionId]);
        for (int a: allActions){
            if (a != actionId){
                for (int c: necessarySet){
                    final Terminal t = Terminal.getTerminal(c);
                    if (t instanceof Comparison comp){
                        if (this.numericContribution(a, comp) > 0) {
                            ret.add(a);
                        }
                    }else{
                        if (cp.propEffectFunction()[a].contains(c)){
                            ret.add(a);
                        }
                    }
                }
            }
        }
        return ret;
    }
}
