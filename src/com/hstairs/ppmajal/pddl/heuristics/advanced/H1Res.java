/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Predicate;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.problem.EPddlProblem;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author enrico
 */
public class H1Res extends H1 {

    private float[][] localCost;
    final private boolean[] seen;
    final private IntArraySet[] terminalConditions;
    final boolean twolevel;
    private float[] currentLocalCost;

    public H1Res(EPddlProblem p, String red, boolean twolevel) {
        super(p, false, false, false, red, false, false, false, false);
        
        localCost = new float[heuristicNumberOfActions][totNumberOfTerms];
        terminalConditions = new IntArraySet[heuristicNumberOfActions];
        seen = new boolean[heuristicNumberOfActions];
        this.twolevel = twolevel;
//        currentLocalCost =  new  float[heuristicNumberOfActions];
        }

    @Override
    protected void updateAchievers(int conditionId, int actionId) {
        getAchievers(conditionId).add(actionId);
    }
//    
//    @Override
//    protected void updateActions(final int c, final FibonacciHeap p, boolean init) {
//        final IntArraySet actions = conditionToAction[c];
//        if (actions != null) {
//            for (final int i : actions) {
//                if (!closed[i]) {
//                    final float v = estimateCost(preconditionFunction[i]);
//                    if (init && v == 0) {
//                        actionInit[i] = true;
//                    }
//                    if (v < Float.MAX_VALUE) {
//                        if (v < actionHCost[i]) {
//                            if (actionHCost[i] == Float.MAX_VALUE) {
//                                actionHCost[i] = v;
//                                addActionsInPriority(i, p, v);
//                            } else {
//                                actionHCost[i] = v;
//                                p.decreaseKey(nodeOf[i], v);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    
    private void updateSeen(Condition id) {
        if (id instanceof Terminal){
            Terminal term1 = (Terminal)id;
            
            final IntArraySet ach = this.getAchievers(term1.getId());
            final IntArrayList q = new IntArrayList();
            q.addAll(ach);
            while (!q.isEmpty()) {
                int popInt = q.popInt();
                if (!seen[popInt] && actionHCost[popInt] != Float.MAX_VALUE) {
                    seen[popInt] = true;
//                    System.out.println("seen-------"+TransitionGround.getTransition(popInt));
                        //Then add also those coming before
                    if (terminalConditions[popInt] == null){
                        Condition name = preconditionFunction[popInt];
                        terminalConditions[popInt] = new IntArraySet();
                        for (final var v: name.getTerminalConditions()){
                            Terminal t =(Terminal)v;
                            terminalConditions[popInt].add(t.getId());
                        }
                    }
                    for (var t : terminalConditions[popInt]) {
                        if (!conditionInit[t]){
                            for (var v : getAchievers(t)) {
                                if (!seen[v] && actionHCost[popInt] != Float.MAX_VALUE)
                                    q.add((int) v);
                            }
                        }
                    }
                    
                }
            }
        }else{
            throw new UnsupportedOperationException();
        }
        
    }

    @Override
    protected void cacheValue(float rep, int a, Terminal t) {
        localCost[a][t.getId()] = rep;
//        currentLocalCost[t.getId()] = rep;
    }
    private float easyHeuristic(Condition ele) {
        if (ele instanceof Comparison) {
            final Terminal t = (Terminal) ele;
            float minA = Float.MAX_VALUE;
            float minP = Float.MAX_VALUE;
            for (var v : getAchievers(t.getId())) {
//                System.out.println("Under Analysis---"+TransitionGround.getTransition(v));
                if (actionHCost[v] != Float.MAX_VALUE){
                    if (seen[v]) {
//                        System.out.println("Already done"+TransitionGround.getTransition(v));
                        minA = 0;
                        minP = 0;
                        break;
                    }else{
                        if (localCost[v][t.getId()]< minA){
//                            System.out.println("Trying---"+TransitionGround.getTransition(v));
                            minA = localCost[v][t.getId()];
                        }
                    }
                    if (twolevel && !actionInit[v]){
                        minP = Math.min(minP, costPre(v));
                    }else{
                        minP = 0;
                    }
                }
            }
//            if (minP != 0){
//                System.out.println("ALE");
//            }
            if (minA==Float.MAX_VALUE || minP == Float.MAX_VALUE){
                throw new RuntimeException();
            }
            return minA+minP;

        }else if (ele instanceof Predicate){
             final Terminal t = (Terminal) ele;
            float minA = Float.MAX_VALUE;
            for (var v : getAchievers(t.getId())) {
//                System.out.println("Under Analysis---"+TransitionGround.getTransition(v));
                if (actionHCost[v] != Float.MAX_VALUE){
                    if (seen[v]) {
                        minA = 0;
                        break;
                    }else{
                        if (localCost[v][t.getId()]< minA){
//                            System.out.println("Trying---"+TransitionGround.getTransition(v));
                            minA = localCost[v][t.getId()];
                        }
                    }
                    if (twolevel && !actionInit[v]){
                        minA += costPre(v);
                    }else{
                        minA = 0;
                    }
                }
            }
            if (minA==Float.MAX_VALUE){
                throw new RuntimeException();
            }
            return minA;
        }{
            throw new UnsupportedOperationException();
        }

    }
    protected boolean update(boolean update, int actionId) {
        return update;
    }

    private float costPre(Integer v) {
        Condition name = preconditionFunction[v];
        if (name instanceof AndCond) {
            float max = 0;
            for (var c : ((AndCond) name).sons) {
                float min = Float.MAX_VALUE;
                if (conditionInit[((Terminal) c).getId()]){
                    min = 0;
                }else{
                    for (var a : getAchievers(((Terminal) c).getId())) {
//                        if (actionHCost[a] != Float.MAX_VALUE) {
                            if (seen[a]) {
                                min = 0;
                                break;
                            } else if (localCost[a][((Terminal) c).getId()] < min) {
                                min = localCost[a][((Terminal) c).getId()];
                            }
//                        }
                    }
                }
                if (min == Float.MAX_VALUE)
                    throw new RuntimeException("---------------------------");
                max = Math.max(max, min);
            }
            return max;
        } else {
            return 0;
        }

    }

    class Comp implements Comparator<Pair<Condition,Float>>{
        @Override
        public int compare(Pair<Condition,Float> t, Pair<Condition,Float> t1) {
            return (int) (t1.getRight() - t.getRight());
        }
    }
    
    
    @Override
    protected float estimateCost(Condition c, float previous) {
        if (c instanceof AndCond) {
            
            final AndCond and = (AndCond) c;
            if (and.sons == null) {
                return 0f;
            }
            if (and.sons.length == 1){
                return super.estimateCost(c);
            }

            float max = 0;
            int best = -1;
            final Object[] sons = ((AndCond)and).sons;
            for (int i = 0; i< sons.length; i++ ){
                Condition v = (Condition)sons[i];
                final float estimateCost = estimateCost(v);
                if (estimateCost > max){
                    max = estimateCost;
                    best = i;
                }           
            }

            if (max == Float.MAX_VALUE ){
                return Float.MAX_VALUE;
            }
            if (max == 0 || (previous <= max))
                return max;
            boolean first  = true;
//            System.out.println("-----------------");
//            System.out.println(sons[best]);
//            System.out.println("Cost: "+ max);
            int prev = -1;
            for (int i = 0; i < sons.length;  i++) {
                final Condition v = (Condition) sons[i];
                if (i != best && !conditionInit[((Terminal) v).getId()]) {
                    if (first) {
                        Arrays.fill(seen, false);
//                        System.out.println("Updating seen of: "+(Condition) sons[best]);
                        updateSeen((Condition) sons[best]);
                        first = false;
                    } else {
//                        System.out.println("Updating seen of: "+(Condition) sons[prev]);
                        updateSeen((Condition) sons[prev]);
                    }
                    prev = i;
//                    final float easyHeuristic = easyHeuristic(v);
//                    System.out.println(v);
//                    System.out.println(easyHeuristic);
                    max += easyHeuristic(v);
                }
            }
//            System.out.println("Tot Cost:"+max);
            return max;   
        }else if (c instanceof Terminal){
            return super.estimateCost(c);
        }else{
            throw new UnsupportedOperationException("Condition "+c.getClass()+" not supported");
        }
    }

    
    
    @Override
    public float computeEstimate(State gs) {
        for (final float[] row : localCost) {
            Arrays.fill(row, Float.MAX_VALUE);
        }
//        Arrays.fill(currentLocalCost, Float.MAX_VALUE);
        this.reachability = getAllAchievers() == null;
        
        return super.computeEstimate(gs); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
