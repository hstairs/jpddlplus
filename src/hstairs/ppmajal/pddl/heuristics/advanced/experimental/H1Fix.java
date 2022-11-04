/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hstairs.ppmajal.pddl.heuristics.advanced.experimental;

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.expressions.Expression;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import static java.lang.Math.ceil;
import java.util.*;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.opt.graph.fastutil.FastutilFastLookupIntVertexGSS;

/**
 *
 * @author enrico
 */
public class H1Fix extends H1 {
        //Plan Fixing Data Structures;
    final IntArraySet[] prec ;
    private boolean planFixing;
    private boolean superFix = false;
    final private boolean saferVersion;
    final boolean[] visitedActions;
    private final boolean hmrpEstimate;

    public H1Fix(PDDLProblem problem, boolean saferVersion,  boolean maxHelpfulTransitions, String redConstraints, 
            boolean helpfulActionsComputation, boolean reachability, boolean helpfulTransitions, boolean conjunctionsMax, boolean hmprpEstimate) {
        super(problem, true, true, maxHelpfulTransitions, redConstraints, helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax);
        this.prec = new IntArraySet[cp.numActions()];
        this.saferVersion = saferVersion;
        visitedActions = new boolean[cp.numActions()];
        this.hmrpEstimate = hmprpEstimate;

    }

    @Override
    public float computeEstimate(State gs) {
        float computeEstimate = super.computeEstimate(gs); //To change body of generated methods, choose Tools | Templates.
        if (computeEstimate == Float.MAX_VALUE)
            return Float.MAX_VALUE;
        if (hmrpEstimate){
            fixPlan(gs);
            return computeEstimate;
        }else{
            return fixPlan(gs);
        }
    }
    
     private float fixPlan(State gs) {
        
        //Compute DG
        final Condition goal = cp.preconditionFunction()[cp.goal()];

        final IntArraySet V = new IntArraySet();

        final DirectedAcyclicGraph DG = new DirectedAcyclicGraph(FastutilFastLookupIntVertexGSS.class);
//        final FastutilMapIntVertexGraph<Integer> DG = new FastutilMapIntVertexGraph<>(
//         SupplierUtil.createIntegerSupplier(), 
//         SupplierUtil.createIntegerSupplier(),
//         DefaultGraphType.dag(), true);
//        new DirectedAcyclicGraph();
        
        Arrays.fill(prec, null);
        Arrays.fill(visitedActions, false);
        Arrays.fill(maxNumRepetition, 0);
//        Arrays.fill(minNumRepetition, Integer.MAX_VALUE);
        
        final int[] repetitionToConsider = maxNumRepetition;
        
        //Construct graph;
        final LinkedList<Integer> stack = new LinkedList();
        stack.push(cp.goal());
        while (!stack.isEmpty()) {
            int a = stack.pollLast();
            if (!visitedActions[a]){                
                V.add(a);
                DG.addVertex(a);
                visitedActions[a] = true;
                for (final int conditionId : (Collection<Integer>)getActivatingConditions(cp.preconditionFunction()[a]).getFirst()) {
                        if (!getConditionInit()[conditionId]) {
                            final int b = establishedAchiever[conditionId];
                            maxNumRepetition[b] = (int) Math.max(maxNumRepetition[b],ceil(numRepetition[conditionId]));
//                            minNumRepetition[b] = (int) Math.min(minNumRepetition[b],ceil(numRepetition[conditionId]));
                            if (prec[a] == null){
                                prec[a] = new IntArraySet();
                            }
                            prec[a].add(b);
                            if (!visitedActions[b]){
                                DG.addVertex(b);
                            }
                            DG.addEdge(b,a);
                            stack.push(b);
                            
                        }
                }
            }
            
        }
        
        //Exploit Graph for mitigating the effect of the action preconditions
        Iterator topo = DG.iterator();
        
        int[] orderedActions = new int[DG.vertexSet().size()];
        int idx = 0;
        while (topo.hasNext()){
            orderedActions[idx] = (int) topo.next();
            idx++;
        }
        
        
        int extraCost =0;
        final IntArraySet[] descendants = new IntArraySet[cp.numActions()];
        Arrays.fill(descendants, null);
        
        computeDescendants(cp.goal(), prec, descendants);
        
        for (idx = DG.vertexSet().size()-1; idx >= 0; idx--){
            int a = orderedActions[idx];
            for (final int conditionId : (Collection<Integer>)getActivatingConditions(cp.preconditionFunction()[a]).getFirst()) {
                final Terminal terminal = Terminal.getTerminal(conditionId);
                if (terminal instanceof Comparison){
                    final Comparison c = (Comparison)terminal;
                    float cum = 0;
//                        for (int j=0; j < idx ; j++){
//                            int b = orderedActions[j];
                        for (int b : descendants[a]){
//                            System.out.println(printTransition(b));
                            float numericContribution1 = numericContribution(b, c);
                            if (numericContribution1 != 0f || numericContribution1 != Float.MAX_VALUE){
                                cum += numericContribution1 * repetitionToConsider[b];
                            }
                        }
                        if (a != cp.goal() && repetitionToConsider[a]>1){
                            float numericContribution1 = numericContribution(a, c);
                            if (numericContribution1 != 0f || numericContribution1 != Float.MAX_VALUE){
                                cum += numericContribution1*(repetitionToConsider[a]-1);
                            }
                        }
                        final Expression left = c.getLeft();
                        final double eval = left.eval(gs);
                        final float T = (float) (eval + cum);
                        if (T < 0) {
                        if (saferVersion) {
                            if (false){
                                IntArraySet achieverSet = getAllAchievers()[conditionId];
                                if (achieverSet!= null && !achieverSet.isEmpty()) {
                                    int min = Integer.MAX_VALUE;
                                    int best = -1;

                                    for (var i : achieverSet) {
                                        int ceil = (int) ceil(-1f * (T / numericContribution(i, c)));
                                        if (ceil < min) {
                                            min = ceil;
                                            best = i;
                                        }
                                    }
                                    extraCost += getActionCost()[best];
                                }else{
                                    extraCost += 1;
                                }
                            }else{
                                extraCost += 1;
                            }
                        } else {

                                int achiever = establishedAchiever[conditionId];
                                if (achiever != -1) { //this is the case where there is an achiever already in the plan
                                    int repetition = (int) ceil(-1f * (T / numericContribution(achiever, c)));
                                    if (repetitionsInThePlan[achiever].size() == 1){
                                        repetitionsInThePlan[achiever] = new IntArraySet(Collections.singleton(maxNumRepetition[achiever] + repetition));
                                    }
                                    maxNumRepetition[achiever] = maxNumRepetition[achiever] + repetition;
                                } else {
                                    if (superFix) {//mmmmmmm
                                        IntArraySet achieverSet = getAllAchievers()[conditionId];
                                        if (!achieverSet.isEmpty()) {
                                            int min = Integer.MAX_VALUE;
                                            int best = -1;
                                            for (var i : achieverSet) {
                                                int ceil = (int) ceil(-1f * (T / numericContribution(i, c)));
                                                if (ceil < min) {
                                                    min = ceil;
                                                    best = i;
                                                }
                                            }
                                            if (getActionHCost()[best] != Float.MAX_VALUE) {
                                                extraCost += getActionHCost()[best];
                                            } else {
                                                extraCost += 1;
                                            }
                                            extraCost += min;
                                        }
                                    }
                                }
                            }
                        
                    }
                }
            }
        }
        float estimate = 0f;
        for (int aId : V){
            estimate += maxNumRepetition[aId] * getActionCost()[aId];
        }
        return estimate+extraCost;
        
    }

    
    private IntArraySet computeDescendants(int actionId, IntArraySet[] prec, IntArraySet[] descendants) {
        
        if (descendants[actionId] != null){
            return descendants[actionId];
        }
        final IntArraySet res = new IntArraySet();
        if (prec[actionId] != null) {
            for (int b : prec[actionId]) {
                res.add(b);
                res.addAll(computeDescendants(b, prec, descendants));
            }
        }
        descendants[actionId] = res;
        return res;  
        
    }
    
}
