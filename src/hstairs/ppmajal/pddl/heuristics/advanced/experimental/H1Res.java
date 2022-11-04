/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hstairs.ppmajal.pddl.heuristics.advanced.experimental;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author enrico
 */
public class H1Res extends H1 {

    private float[][] localCost;
    final private IntArraySet[] terminalConditions;
    final boolean twolevel;
    private BitSet[] depActions;
    final private boolean ordering;
    
    public H1Res(PDDLProblem p, String red, boolean twolevel, boolean ordering) {
        super(p, false, false, false, red, false, false, false, false);
        
        localCost = new float[cp.numActions()][totNumberOfTerms];
        terminalConditions = new IntArraySet[cp.numActions()];
        this.twolevel = twolevel;
//        currentLocalCost =  new  float[heuristicNumberOfActions];
        for (var act: p.actions){
            super.getConditionsAchievableById(act.getId());
        }
        this.ordering = ordering;
        }

    @Override
    protected void updateAchievers(int conditionId, int actionId) {
        getAchievers(conditionId).add(actionId);
    }
    
    protected BitSet getDep(int term1) {
//        System.out.println("-------------------------------------------------");
//        System.out.println(Terminal.getTerminal(conditionId));
        if (depActions[term1] == null) {
            depActions[term1] = new BitSet(cp.numActions());
            for (var actionId : getAchievers(term1)) {
                final BitSet dep = depActions[term1];
    //            System.out.println(TransitionGround.getTransition(actionId));
                if (!dep.get(actionId)) {
                    dep.set(actionId);
                    if (terminalConditions[actionId] == null) {
                        Condition name = cp.preconditionFunction()[actionId];
                        terminalConditions[actionId] = new IntArraySet();
                        for (final var v : name.getTerminalConditions()) {
                            Terminal t = (Terminal) v;
                            terminalConditions[actionId].add(t.getId());
                        }
                    }
                    for (final var t : terminalConditions[actionId]) {
                        if (!getConditionInit()[t]) {
                            dep.or(getDep(t));
                        }
                    }
                }
            }
//                System.out.println("Restart");
        }
        return depActions[term1];
    }


    
  

    
    
    @Override
    protected void cacheValue(float rep, int a, Terminal t) {
        localCost[a][t.getId()] = rep;
//        currentLocalCost[t.getId()] = rep;
    }
    private float easyHeuristic(Condition ele, BitSet allSeen) {
        if (ele instanceof Comparison) {
            final Terminal t = (Terminal) ele;
            float minA = Float.MAX_VALUE;
            float minP = Float.MAX_VALUE;
            for (var v : getAchievers(t.getId())) {
//                System.out.println("Under Analysis---"+TransitionGround.getTransition(v));
                if (getActionHCost()[v] != Float.MAX_VALUE){
//                if (actionHCost[v] <= conditionCost[t.getId()]){
                    
                    if (allSeen.get(v)) {
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
                    if (twolevel && !getActionInit()[v]){
                        minP = Math.min(minP, costPre(v,allSeen));
                    }else{
                        minP = 0;
                    }
                }
            }
            if (minA==Float.MAX_VALUE || minP == Float.MAX_VALUE){
                throw new RuntimeException();
            }
            return minA+minP;

        }else if (ele instanceof BoolPredicate){
             final Terminal t = (Terminal) ele;
            float minA = Float.MAX_VALUE;
            for (var v : getAchievers(t.getId())) {
//                System.out.println("Under Analysis---"+TransitionGround.getTransition(v));
                if (getActionHCost()[v] != Float.MAX_VALUE){
//                if (actionHCost[v] <= conditionCost[t.getId()]){
                    
                    if (allSeen.get(v)) {
                        minA = 0;
                        break;
                    }else{
                        float prec = 0;
                        if (twolevel && !getActionInit()[v]){
                            prec = costPre(v,allSeen);
                        }
                        if (prec != Float.MAX_VALUE){
                            if (localCost[v][t.getId()]+prec< minA){
    //                            System.out.println("Trying---"+TransitionGround.getTransition(v));
                                minA = localCost[v][t.getId()]+prec;
                            }
                        }
                    }
                }
            }
            if (minA==Float.MAX_VALUE){
                throw new RuntimeException("----------------"+ele);
            }
            return minA;
        }{
            throw new UnsupportedOperationException();
        }

    }


    private float costPre(Integer v, BitSet allSeen) {
        Condition name = cp.preconditionFunction()[v];
        if (name instanceof AndCond) {
            float max = 0;
            for (final var c : ((AndCond) name).sons) {
                float min = Float.MAX_VALUE;
                if (getConditionInit()[((Terminal) c).getId()]){
                    min = 0;
                }else{
                    for (var a : getAchievers(((Terminal) c).getId())) {
                        if (getActionHCost()[a] != Float.MAX_VALUE) {
//                        if (actionHCost[a] <= conditionCost[((Terminal) c).getId()]){
                            
                            if (allSeen.get(a)) {
                                min = 0;
                                break;
                            } else if (localCost[a][((Terminal) c).getId()] < min) {
                                min = localCost[a][((Terminal) c).getId()];
                            }
                        }
                    }
                }
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
                return super.estimateCost(c,previous);
            }

            float max = 0;
            int best = -1;
            final Object[] sons = ((AndCond)and).sons;
            for (int i = 0; i< sons.length; i++ ){
                Condition v = (Condition)sons[i];
                final float estimateCost = super.estimateCost(v,previous);
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
//            for (int j= 0; j< heuristicNumberOfActions; j++){
//                if (updateSeen((Condition) sons[best]).get(j)) {
//                    System.out.println("Action:" + TransitionGround.getTransition(j));
//                }
//            }
            int prev = -1;
            Object[] toIterate = sons;
            if (ordering) {
                toIterate = Arrays.copyOf(sons, sons.length);
                Arrays.sort(toIterate, new Comparator() {
                    @Override
                    public int compare(Object t, Object t1) {
//                        int first = getDep(((Terminal) t).getId()).cardinality();
//                        int second = getDep(((Terminal) t1).getId()).cardinality();
//                        return first - second;
                           return (int) (estimateCost((Condition) t1,previous)-estimateCost((Condition) t,previous));
//                        return (int) (estimateCost((Condition) t) - estimateCost((Condition) t1));

                    }
                });
                best = 0;
//                for (int i = 0; i< toIterate.length; i++ ){
//                    Condition v = (Condition)sons[i];
//                    final float estimateCost = super.estimateCost(v);
//                    if (estimateCost > max){
//                        max = estimateCost;
//                        best = i;
//                    }           
//                }
            }
            BitSet allSeen = null;
            for (int i = 0; i < toIterate.length;  i++) {
                final Condition v = (Condition) toIterate[i];
                if (i != best && !getConditionInit()[((Terminal) v).getId()]) {
                    if (first) {
//                        System.out.println("Updating seen of: "+(Condition) sons[best]);
                        allSeen = new BitSet();
                        allSeen.or(getDep(((Terminal) toIterate[best]).getId()));
                        first = false;
                    } else {
//                        System.out.println("Updating seen of: "+(Condition) sons[prev]);
                        allSeen.or(getDep(((Terminal) toIterate[prev]).getId()));
                    }
                    prev = i;
//                    final float easyHeuristic = easyHeuristic(v,allSeen);
//                    System.out.println(v);
//                    System.out.println(easyHeuristic);
//                    for (int j= 0; j< heuristicNumberOfActions; j++){
//                        if (allSeen.get(i))
//                            System.out.println("Action:"+TransitionGround.getTransition(j));
//                    }
//                    System.out.println();
                    max += easyHeuristic(v,allSeen);
                }
            }
            
//            if (allSeen != null)
//                for (int i= 0; i< heuristicNumberOfActions; i++){
//                    if (allSeen.get(i))
//                        System.out.println("Action:"+TransitionGround.getTransition(i));
//                }
//
//            System.out.println("Tot Cost:"+max);
            return max;   
        }else if (c instanceof Terminal){
            return super.estimateCost(c,previous);
        }else{
            throw new UnsupportedOperationException("Condition "+c.getClass()+" not supported");
        }
    }

    
    
    @Override
    public float computeEstimate(State gs) {
        for (final float[] row : localCost) {
            Arrays.fill(row, Float.MAX_VALUE);
        }
        depActions = new BitSet[getTotNumberOfTerms()];
        return super.computeEstimate(gs); //To change body of generated methods, choose Tools | Templates.

    }


    
    
    
}
