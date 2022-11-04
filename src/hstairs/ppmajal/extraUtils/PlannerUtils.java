package com.hstairs.ppmajal.extraUtils;

import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.GoalSensitiveHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.Aibr;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.PDDLSearchEngine;
import com.hstairs.ppmajal.search.SearchHeuristic;
import java.math.BigDecimal;
import java.util.LinkedList;
import org.apache.commons.lang3.tuple.Pair;

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

/**
 * @author enrico
 */
public class PlannerUtils {
    PDDLDomain d;
    PDDLProblem p;
    SearchHeuristic h;


    private void setup(String domainFileName, String problemFileName, String heuristic) throws Exception {
        d = new PDDLDomain(domainFileName);
        p = new PDDLProblem(problemFileName, d.constants, d.getTypes(), d, System.out, heuristic, true, false,new BigDecimal(1.0),new BigDecimal(1.0));
        d.substituteEqualityConditions();
        if (!d.getProcessesSchema().isEmpty()) {
            p.setDeltaTimeVariable("1");
        }
        p.prepareForSearch(true);
        h = null;

        switch (heuristic) {
            case "blind":
                h = new GoalSensitiveHeuristic(p);
                break;
            case "aibr":
                h = new Aibr(p);
                break;
            case "hadd":
                h = new H1(p,true);
                break;
            case "hmax":
                h = new H1(p,false);
                break;
            case "hrmax":
                h = new H1(p,false,false,false,"brute",false,false,false,false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + heuristic);
        }
    }
    public int getPlanSize (String domainFileName, String problemFileName, String heuristic) throws Exception {
        return this.getPlanSize(domainFileName, problemFileName, heuristic, 1, 1, Integer.MAX_VALUE);
    }

    public int getPlanSize(String domainFileName, String problemFileName, String heuristic, int wg, int wh, int depthBound) throws Exception {
        setup(domainFileName,problemFileName,heuristic);
        PDDLSearchEngine search = new PDDLSearchEngine(p, h);
        if (!p.getProcessesSet().isEmpty()){
            search.planningDelta = new BigDecimal(1.0f);
            search.processes = true;
            search.executionDelta = new BigDecimal(1.0f);
        }
        final LinkedList<Pair<BigDecimal, Object>> pairs = search.WAStar();
        return pairs.size();
    }

    public int heuristicEstimate (String domainFileName, String problemFileName, String heuristic) throws Exception {
        setup(domainFileName,problemFileName,heuristic);
        final float v = h.computeEstimate(p.getInit());
        return (int)v;
    }

}
