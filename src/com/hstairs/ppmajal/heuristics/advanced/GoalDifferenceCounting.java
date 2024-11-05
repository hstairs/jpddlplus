/*
 * Copyright (C) 2019 enrico.
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
package jpddlplus.heuristics.advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jpddlplus.conditions.AndCond;
import jpddlplus.conditions.BoolPredicate;
import jpddlplus.conditions.Comparison;
import jpddlplus.conditions.Condition;
import jpddlplus.extraUtils.Pair;
import jpddlplus.problem.PDDLProblem;
import jpddlplus.problem.State;
import jpddlplus.search.SearchHeuristic;
import jpddlplus.transition.TransitionGround;
import org.apache.commons.lang3.NotImplementedException;

/**
 * @author enrico
 */
public class GoalDifferenceCounting extends SearchHeuristic {

  final private PDDLProblem problem;

  public GoalDifferenceCounting(PDDLProblem problem) {
    this.problem = problem;
  }

  @Override
  protected float computeEstimateForStore(State s) {
    float h = 0;
    Condition goals = this.problem.getGoals();
    if (goals instanceof AndCond) {
      for (Object c1 : ((AndCond) goals).sons) {
        Condition con = (Condition) c1;

        if (con instanceof Comparison) {
          h += (float) ((Comparison) con).distanceUntilSatisfied(s);
        } else {
          // choose 1 with the motivation that boolean can be viewed as num assignment of 0 or 1
          h += Boolean.compare(!s.satisfy(con), false);  // convert boolean to int
        }
      }
    }
    return h;
  }

  @Override
  public Object[] getTransitions(boolean helpful) {
    return problem.getActions().toArray();
//    Object[] actions = problem.getActions().toArray();
//    if (!helpful) {
//      return actions;
//    } else {
//      List<Pair<TransitionGround, Integer>> greedyTransitions = new ArrayList<>();
//
//      for (Object action : actions) {
//        TransitionGround a = (TransitionGround) action;
//
//
//      }
//
//      return greedyTransitions.toArray();
//    }
  }

  @Override
  public Collection<TransitionGround> getAllTransitions() {
    return problem.getTransitions();
  }

}
