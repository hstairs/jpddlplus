/*
 * Copyright (C) 2018 enrico.
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
package com.hstairs.ppmajal.problem;

import com.hstairs.ppmajal.search.SearchEngine;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.search.SearchNode;
import com.hstairs.ppmajal.search.SimpleSearchNode;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;



/**
 * @author enrico
 */
public class PDDLSearchEngine extends SearchEngine {

    protected Collection<TransitionGround> reachableProcesses;
    protected Collection<TransitionGround> reachableEvents;
        public BigDecimal executionDelta;
    public BigDecimal planningDelta;
    
    //This is a hack!
    private HashMap<Float,TransitionGround> triggeredEvents;

    public PDDLSearchEngine(PDDLProblem problem, SearchHeuristic h) {
        super(h, problem);
        executionDelta = problem.executionDelta;
        planningDelta = problem.planningDelta;
        if (!problem.isReadyForSearch()){
            throw new RuntimeException("PDDL Problem is not ready for search yet. Bailing out");
        }
    }
    @Override
    protected void printInfo(PrintStream out) {
            //out.println("Reachable actions and processes: |A U P U E|:" + TransitionGround.totNumberOfTransitions);
    }


    @Override
    public LinkedList<Pair<BigDecimal,TransitionGround>> extractPlan (SimpleSearchNode input) {

        LinkedList<Pair<BigDecimal,TransitionGround>> plan = new LinkedList<>();
        lastState = input.s;
        if (!(input instanceof SearchNode)) {
            SimpleSearchNode temp = input;
            while (temp.transition != null) {
                Double time = null;
                plan.addFirst(Pair.of(BigDecimal.ZERO,(TransitionGround)temp.transition));
                temp = temp.father;
            }
            return plan;
        }
        
        SearchNode c = (SearchNode) input;
        if (((PDDLProblem)problem).getProcessesSet().isEmpty()) {
            while ((c.transition != null || c.list_of_actions != null)) {
                BigDecimal time = null;
                if (c.father != null && c.father.s instanceof PDDLState) {
                    time = ((PDDLState) c.father.s).time;
                }
                if (c.transition != null) {//this is an action
                    if (c.transition instanceof ImmutablePair) {
                        final ImmutablePair<TransitionGround, Integer> t = (ImmutablePair<TransitionGround, Integer>) c.transition;
                        for (int i = 0; i < t.right; i++) {
                            plan.addFirst(Pair.of(time, t.left));
                        }
                        System.out.println("JUMP for " + t.left + ":" + t.right);
                    } else {
                        plan.addFirst(Pair.of(time, (TransitionGround) c.transition));
                    }
                }
                c = (SearchNode) c.father;

            }
        }else {
            triggeredEvents = new HashMap();
            System.out.println("Extracting plan with execution delta: "+executionDelta);
            BigDecimal time = ((PDDLState)c.s).time;
            TransitionGround waiting = new TransitionGround("------>waiting", Transition.Semantics.PROCESS, new ArrayList<>(), null,new ConditionalEffects(),new ConditionalEffects());
            while ((c.transition != null || c.list_of_actions != null)) {
                if (c.transition != null){
                    // This is an action
                    plan.addFirst(Pair.of(((PDDLState)c.s).time, (TransitionGround) c.transition));
                }else{
                    //c is the next state actually. Be careful!!
                    ArrayList arrayList = new ArrayList(c.list_of_actions);
                    Collections.reverse(arrayList);
                    for (final Object o: arrayList ){
                        if (o == null){//This is when this is a process
                            time = time.subtract(executionDelta);
                            plan.addFirst(Pair.of(time, waiting));
                        }else{ //This case is when this is an event
                            TransitionGround t = (TransitionGround)o;
                            plan.addFirst(Pair.of(time,t));
                        }
                    }
                }
                c = (SearchNode) c.father;
            }
//            LinkedList<Pair<Float,TransitionGround>> newPlan = new LinkedList<>();
//
//            Pair<Float,TransitionGround> waitingTransition = null;
//            for (Pair<Float, TransitionGround> ele : plan) {
//                final TransitionGround transition = ele.getRight();
//                if (transition.getSemantics() == Transition.Semantics.PROCESS){
//                    time+=ele.getKey();
//                    waitingTransition = Pair.of(time,transition);
//                }else{
//                    if (waitingTransition != null) {
//                        newPlan.add(waitingTransition);
//                    }
//                    newPlan.add(Pair.of(time,ele.getRight()));
//                    waitingTransition = null;
//                }
//            }
//            if (waitingTransition != null){
//                final Pair<Float, TransitionGround> of = Pair.of(endTime, waitingTransition.getRight());
//                newPlan.add(of);
//            }
            return plan;
        }

        return plan;
    }

}
