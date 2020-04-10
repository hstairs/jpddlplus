/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.adaptation;

import antlr.RecognitionException;
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.domain.PddlDomain;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.some_computatitional_tool.DomainEnhancer;
import com.hstairs.ppmajal.some_computatitional_tool.NumericKernel;
import com.hstairs.ppmajal.some_computatitional_tool.NumericPlanningGraph;
import com.hstairs.ppmajal.wrapped_planners.metricFFWrapper;
import com.hstairs.ppmajal.wrapped_planners.planningTool;
import org.jgrapht.alg.TransitiveClosure;
import org.jgrapht.graph.AsUndirectedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.DirectedAcyclicGraph;

/**
 * @author enrico
 */
public class PlanAdapter {

    //wrapper for external planning tool
    protected planningTool planner;
    protected int plannerTime;
    protected long macroActionsConstructionTime;
    //The solution container
    protected SimplePlan solution;
    //statistics informations
    private long kernelConstructionTime;
    private long adaptationTime;
    private long heuristicComputationTime;
    //if true, use kernel heuristic repair instead of greedy repair. See AIXIA 2013, Enrico Scala article
    private boolean kernelHeuristicRepair;
    //public boolean debuggingMacrioActionConstructionBench;
    //number of macro actions exploited for performing the repair
    private int employedMacros;

    //upper bound on the number of primitives to use
    private int max_primitives;

    //reacheable States
    private RelState poss_state;
    private int connectedComponentsNumber;
    private int uselessActionPruning;

    /**
     * Constructor for the plan adapter.
     *
     * @param planner: the planner used as black box for performing specific
     *                 local repair tasks
     */
    public PlanAdapter (planningTool planner) {
        this.planner = planner;
        solution = null;
        planner.setTimeout(100000);
        uselessActionPruning = 0;
    }

    /**
     * Get the value of employedMacros
     *
     * @return the value of employedMacros
     */
    public int getEmployedMacros ( ) {
        return employedMacros;
    }

    /**
     * Set the value of employedMacros
     *
     * @param employedMacros new value of employedMacros
     */
    public void setEmployedMacros (int employedMacros) {
        this.employedMacros = employedMacros;
    }

    /**
     * Get the value of solution
     *
     * @return the value of solution
     */
    public SimplePlan getSolution ( ) {
        return solution;
    }

    /**
     * Set the value of solution
     *
     * @param solution new value of solution
     */
    public void setSolution (SimplePlan solution) {
        this.solution = solution;
    }

    /**
     * Get the value of macroActionsConstructionTime
     *
     * @return the value of macroActionsConstructionTime
     */
    public long getMacroActionsConstructionTime ( ) {
        return macroActionsConstructionTime;
    }

    /**
     * Set the value of macroActionsConstructionTime
     *
     * @param macroActionsConstructionTime new value of
     *                                     macroActionsConstructionTime
     */
    public void setMacroActionsConstructionTime (long macroActionsConstructionTime) {
        this.macroActionsConstructionTime = macroActionsConstructionTime;
    }

    /**
     * Adapt the plan by using the Greedy Repair Strategy
     *
     * @param domain     The PDDL domain of reference
     * @param problem    The problem the plan bust be adapted to
     * @param input_plan The plan from which the repair process is started
     * @return the plan repaired or null in case of no solution (for any
     * reasons...the motivation is due to the planning tool)
     * @throws FileNotFoundException
     * @throws Exception
     */
    public SimplePlan adaptViaFirstKernel_GreedyRepair (PddlDomain domain, PddlProblem problem, SimplePlan input_plan) throws FileNotFoundException, Exception {
        this.kernelHeuristicRepair = false;
        return this.adaptKernelBasedMethod(domain, problem, input_plan);
    }

    private SimplePlan adaptKernelBasedMethod (PddlDomain dom, PddlProblem prob, SimplePlan sp) throws Exception {

        try {
            //Kernel Construction; mandatory for both strategies
            NumericKernel nk = new NumericKernel();
            /**/
            long start = System.currentTimeMillis();
            nk.construct(sp, prob.getGoals());
            /**/
            setKernelConstructionTime(System.currentTimeMillis() - start);

            //Selecting the best kernel!
            int i = 0;
            if (kernelHeuristicRepair) {
                start = System.currentTimeMillis();
                i = this.findBestKernel(prob, nk, 0);
                this.setHeuristicComputationTime(System.currentTimeMillis() - start);
            }

            //Temporary Problem to emulate a new goal in a PDDL problem description
            PddlProblem tempProblem = new PddlProblem(dom);
            tempProblem.parseProblem(prob.getPddlFileReference());
            tempProblem.setGoals((ComplexCondition) nk.get(i));
            tempProblem.saveProblem("temp.pddl");

            //Computing the patch toward the kernel by means of a general purpose planner
            SimplePlan sp2 = new SimplePlan(dom, tempProblem);
            String solutionString = planner.plan(dom.getPddlFilRef(), "temp.pddl");

            //delete the temporary problem...a kind of garbage collector
            new File("temp.pddl").delete();
            if (solutionString == null) {
                return null;
            }

            //parse the solution patch and after delete it
            sp2.parseSolution(solutionString);
            new File(solutionString).delete();

            setPlannerTime(planner.getPlannerTime());
            if (kernelHeuristicRepair) {
                setAdaptationTime(this.plannerTime + this.kernelConstructionTime + this.heuristicComputationTime);
            } else {
                setAdaptationTime(this.plannerTime + this.kernelConstructionTime);
            }

            //concatenation with the previous solution
            //in case of greedyrepair, the index i=0
            sp2.addAll(sp.subList(i, sp.size()));

            //storing the solution "permanently" within this object
            solution = sp2;
            return sp2;

        } catch (IOException ex) {
            Logger.getLogger(PlanAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RecognitionException ex) {
            Logger.getLogger(PlanAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.antlr.runtime.RecognitionException ex) {
            Logger.getLogger(PlanAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(PlanAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //function to evaluate which is the best kernel. This function exploits a variant of numeric kernel heuristic to support multiple conditions
    private int findBestKernel (PddlProblem problem, NumericKernel kerns, int i) throws CloneNotSupportedException {

        NumericPlanningGraph gr;
        int closer = i;
        int closerDistance = Integer.MAX_VALUE;

        gr = new NumericPlanningGraph();
        //compute a relaxed plan for each kernel starting from the init. Then the function gets a map (order2) 
        //between kernel and the distance given by the size of such plans.
        Map order2 = gr.computeRelaxedPlans((PDDLState) problem.getInit(), kerns, new LinkedHashSet(problem.getActions()), i);
        //System.out.println(order2);
        for (Object o : order2.keySet()) {
            int candidate = (Integer) o;
            int distance = (Integer) order2.get(o);
            if (distance < closerDistance) {
                closer = candidate;
                closerDistance = distance;
            }
            if (distance <= 1) {
                break;
            }
        }
        setHeuristicComputationTime(gr.getCpu_time());
        return closer;
    }

    /**
     * Adapt the plan by using the Heuristic Kernel Repair Strategy
     *
     * @param domain     The PDDL domain of reference
     * @param problem    The problem the plan bust be adapted to
     * @param input_plan The plan from which the repair process is started
     * @return the plan repaired or null in case of no solution (for any
     * reasons...the motivation is due to the planning tool)
     * @throws FileNotFoundException
     * @throws Exception
     */
    public SimplePlan adaptViaBestKernel_HeuristicKernelRepair (PddlDomain domain, PddlProblem problem, SimplePlan input_plan) throws FileNotFoundException, Exception {

        this.kernelHeuristicRepair = true;
        return this.adaptKernelBasedMethod(domain, problem, input_plan);
    }


    /**
     * Get the value of planner
     *
     * @return the value of planner
     */
    public planningTool getPlanner ( ) {
        return planner;
    }

    /**
     * Set the value of planner
     *
     * @param planner new value of planner
     */
    public void setPlanner (planningTool planner) {
        this.planner = planner;
    }

    /**
     * @return the kernelConstructionTime
     */
    public long getKernelConstructionTime ( ) {
        return kernelConstructionTime;
    }

    /**
     * @param kernelConstructionTime the kernelConstructionTime to set
     */
    public void setKernelConstructionTime (long kernelConstructionTime) {
        this.kernelConstructionTime = kernelConstructionTime;
    }

    /**
     * @return the plannerTime
     */
    public int getPlannerTime ( ) {
        return plannerTime;
    }

    /**
     * @param plannerTime the plannerTime to set
     */
    public void setPlannerTime (int plannerTime) {
        this.plannerTime = plannerTime;
    }

    /**
     * @return the adaptationTime
     */
    public long getAdaptationTime ( ) {
        return adaptationTime;
    }

    /**
     * @param adaptationTime the adaptationTime to set
     */
    public void setAdaptationTime (long adaptationTime) {
        this.adaptationTime = adaptationTime;
    }

    /**
     * @return the heuristicComputationTime
     */
    public long getHeuristicComputationTime ( ) {
        return heuristicComputationTime;
    }

    /**
     * @param heuristicComputationTime the heuristicComputationTime to set
     */
    public void setHeuristicComputationTime (long heuristicComputationTime) {
        this.heuristicComputationTime = heuristicComputationTime;
    }

    /**
     * Get the outcome of the plan adaption task
     *
     * @return the outcome of the plan-adaptation process; one of "success",
     * "failure" and "timeout"
     */
    public String getOutcome ( ) {
        if (!this.getPlanner().isFailed() && !this.getPlanner().isTimeoutFail()) {
            return "success";
        } else if (this.getPlanner().isTimeoutFail()) {
            return "timeout";
        } else {
            return "failure";
        }
    }

    /**
     * Adapt the plan by using the LPG-ADAPT subsystem.
     *
     * @param domain
     * @param problem
     * @param input_plan
     * @return the repaired input_plan or null in case of failure (or timeout)
     * @throws Exception
     */
    public SimplePlan adaptViaLPG (PddlDomain domain, PddlProblem problem, SimplePlan input_plan) throws Exception {

        try {

            input_plan.savePlan("plan.sol");
            String solutionString = planner.adapt(domain.getPddlFilRef(), problem.getPddlFileReference(), "plan.sol");
            if (solutionString == null) {
                return null;
            }
            SimplePlan new_plan = new SimplePlan(domain, problem);
            new_plan.parseSolution(solutionString);
            new File(solutionString).delete();
            new File("plan.sol").delete();

            this.setAdaptationTime(planner.getPlannerTime());
            this.setSolution(new_plan);
            return new_plan;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlanAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Replanning from scratch
     *
     * @param domain
     * @param problem
     * @return a new plan in case of success
     * @throws Exception
     */
    public SimplePlan replan (PddlDomain domain, PddlProblem problem) throws Exception {
        try {
            SimplePlan new_plan = new SimplePlan(domain, problem);
            planningTool replanner = this.planner;

            String solutionString = replanner.plan(domain.getPddlFilRef(), problem.getPddlFileReference());
            if (solutionString == null) {
                return null;
            }
            new_plan.parseSolution(solutionString);
            new File(solutionString).delete();

            this.setAdaptationTime(replanner.getPlannerTime());
            this.setSolution(new_plan);
            return new_plan;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlanAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    /**
     * Get the maximum number of actions a macro action consists of
     *
     * @return the max_primitives
     */
    public int getMax_primitives ( ) {
        return max_primitives;
    }

    /**
     * Set the maximum number of actions a macro action consists of
     *
     * @param max_primitives the max_primitives to set
     */
    public void setMax_primitives (int max_primitives) {
        this.max_primitives = max_primitives;
    }

    /**
     * @return the poss_state
     */
    public RelState getPoss_state ( ) {
        return poss_state;
    }

    /**
     * @param poss_state the poss_state to set
     */
    public void setPoss_state (RelState poss_state) {
        this.poss_state = poss_state;
    }

    public SimplePlan adaptViaPop (SimplePlan plan, PddlProblem prob, PddlDomain domain, boolean endlessActionPruning,
                                   boolean biconnectivity, boolean missingServicesCut, boolean goalachievingCut, boolean goalthreatCut) throws Exception {

        System.out.println("Deordering..");
        //HashMap achieveGoal = new HashMap();
        long beforeMacrogeneration = System.currentTimeMillis();
        DirectedAcyclicGraph po = plan.deorder((PDDLState) prob.getInit(), prob.getGoals(), goalachievingCut);
        //System.out.println(po);
        if (endlessActionPruning) {
            removeUselessActions(po, plan.size() - 1);
        }
        //System.out.println(po);
        //System.out.println(plan.toStringWithIndex());
        po = plan.removeInitGoal(po);
        DomainEnhancer dEnh = new DomainEnhancer();
        List c = null;
        c = plan.generateMacrosFromPop(po, plan.getGoalAchiever(), missingServicesCut, biconnectivity, goalthreatCut);

        this.setConnectedComponentsNumber(plan.getConnectedSetBuilder().connectedSets().size());
        //System.out.println(c);
        List macros = new ArrayList();
        macros.addAll(pruneSmallMacros(c, 1000000));

        Map m = dEnh.addMacroActions(domain, macros, plan);

        //System.out.println("time for adding in the file:" + (System.currentTimeMillis() - beforeAddingFile));

        /**/
        macroActionsConstructionTime = System.currentTimeMillis() - beforeMacrogeneration;
        //System.out.println("MacroConstruction Time:" + getMacroActionsConstructionTime());

        //planning using the enhanced domain
        //PddlDomain temp = new PddlDomain(dEnh.getDomainEnhancedFileName());
        SimplePlan new_plan = new SimplePlan(domain, prob, false);

        //PddlProblem temp = new PddlProblem();
        String solutionString = null;
        if (planner instanceof metricFFWrapper) {
            solutionString = planner.plan(dEnh.getDomainEnhancedFileName(), prob.getPddlFileReference());
        } else {
            PDDLObjects temp = (PDDLObjects) prob.getObjects().clone();
            prob.removeObjects(dEnh.getConstantsFound());
            prob.saveProblem("temp");
            solutionString = planner.plan(dEnh.getDomainEnhancedFileName(), "temp");
            prob.setObjects(temp);
            Utils.deleteFile("temp");
        }

        if (solutionString == null) {
            return null;
        }
        new_plan.parseSolutionWithMacro(solutionString, m);
        this.setEmployedMacros(new_plan.getEmployedMacro());
        new File(solutionString).delete();

        setPlannerTime(planner.getPlannerTime());
        this.setAdaptationTime(plannerTime + macroActionsConstructionTime);
        solution = new_plan;
        return new_plan;
    }

    public int adaptViaPopDEBUG (SimplePlan plan, PddlProblem prob, PddlDomain domain, boolean goalPruning, boolean biconnectivity) throws Exception {

        long beforeMacrogeneration = System.currentTimeMillis();

        System.out.println("Deordering..");
        //HashMap achieveGoal = new HashMap();
        DirectedAcyclicGraph po = plan.deorder((PDDLState) prob.getInit(), prob.getGoals(), false);

        if (goalPruning) {
            this.removeUselessActions(po, plan.size() - 1);
        }

        System.out.println(plan.getGoalAchiever());

        po = plan.removeInitGoal(po);
        BiconnectivityInspector bI = new BiconnectivityInspector(new AsUndirectedGraph(po));
        //System.out.println("Number of BiconnectedComponents: "+bI.getCutpoints());

        //System.out.println("Number of macros"+macros.size());
        //long beforeAddingFile = System.currentTimeMillis();
        DomainEnhancer dEnh = new DomainEnhancer();
        List c = null;

        c = plan.generateMacrosFromPop(po, plan.getGoalAchiever(), true, biconnectivity, false);

        setConnectedComponentsNumber(plan.getConnectedSetBuilder().connectedSets().size());

        return 0;
    }

    /**
     * @return the connectedComponentsNumber
     */
    public int getConnectedComponentsNumber ( ) {
        return connectedComponentsNumber;
    }

    /**
     * @param connectedComponentsNumber the connectedComponentsNumber to set
     */
    public void setConnectedComponentsNumber (int connectedComponentsNumber) {
        this.connectedComponentsNumber = connectedComponentsNumber;
    }

    public DirectedAcyclicGraph removeUselessActions (DirectedAcyclicGraph po, int goalIndex) {
        int counter = 0;
        DirectedAcyclicGraph po1 = (DirectedAcyclicGraph) po.clone();
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(po1);

        Set s = new HashSet();
        for (int v = 1; v < goalIndex; v++) {
            if (!po1.containsEdge(v, goalIndex)) {
                System.out.println(v);
                counter++;
                s.add(v);
            }
        }
        System.out.println(s);
        for (Object v : s) {
            po.removeVertex(v);
        }

        System.out.println("actins belonging to endless path:" + counter);

        setUselessActionPruning(counter);

        return po;

    }

    /**
     * @return the uselessActionPruning
     */
    public int getUselessActionPruning ( ) {
        return uselessActionPruning;
    }

    /**
     * @param uselessActionPruning the uselessActionPruning to set
     */
    public void setUselessActionPruning (int uselessActionPruning) {
        this.uselessActionPruning = uselessActionPruning;
    }

    protected TreeSet pruneSmallMacros (List c, int i) {

        TreeSet<GroundAction> ret = new TreeSet(new GroundActionComparator());
        ret.addAll(c);

        while (ret.size() > i) {
            ret.pollFirst();
        }
        return ret;

    }

    public void adaptViaBlockDeordering (SimplePlan sp, PddlProblem problem, PddlDomain domain, String planFile) {

        //Block deordering invocation
        //Build Macro Action
        //Write domain extended
        //Planning with off-the shelf planner on the extended domain representation
    }

    public class GroundActionComparator implements Comparator<GroundAction> {

        @Override
        public int compare (GroundAction x, GroundAction y) {
            if (x.getPrimitives().size() <= y.getPrimitives().size()) {
                return -1;
            }
            if (x.getPrimitives().size() == y.getPrimitives().size()) {
                return 0;
            }
            return 1;  // do your comparison
        }
    }

}
