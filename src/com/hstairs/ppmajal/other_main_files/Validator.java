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
package com.hstairs.ppmajal.other_main_files;

import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.domain.ActionSchema;
import com.hstairs.ppmajal.domain.PddlDomain;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.PddlProblem;
import com.hstairs.ppmajal.wrapped_planners.metricFFWrapper;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.hstairs.ppmajal.extraUtils.Utils.searchParameterValue;

/**
 * *******************************************************************
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 * <p>
 * *******************************************************************
 * <p>
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 * <p>
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 * <p>
 * *******************************************************************
 * <p>
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 * <p>
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 * <p>
 * *******************************************************************
 */
/**
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 *
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 *
 ********************************************************************
 */

/**
 *
 * @author enrico
 */
public class Validator {

    private static String domainFile;
    private static String problemFile;
    private static String planFile;
    private static HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_init;
    private static HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_goal;
    private static String last_state_file;

    public static void parseInput (String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: executable-name -o domain_name -f problem_name (-p plan_name)  (-s last_state_file)");
            System.exit(-1);
        }
        domainFile = searchParameterValue(args, "-o");
        problemFile = searchParameterValue(args, "-f");
        planFile = searchParameterValue(args, "-p");
        last_state_file = searchParameterValue(args, "-s");
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws Exception {

        parseInput(args);

        PddlDomain dom = new PddlDomain(domainFile);

        PddlProblem prob = new PddlProblem();
        prob.parseProblem(problemFile);
        //System.out.println(dom.getActionsSchema());
        metricFFWrapper p = new metricFFWrapper();
        p.setTimeout(100000);
        SimplePlan sp = new SimplePlan(dom, prob, true);

        if (planFile == null) {
            sp.parseSolution(p.plan(domainFile, problemFile));
            //sp.savePlan(problemFile+".sol");
        } else {
            sp.parseSolution(planFile);
        }

        sp.setInvariantFluents(prob.getActualFluents());
        System.out.println(sp.last_relevant_fluents_last_state(0, (PDDLState) prob.getInit().clone(), prob));
        if (last_state_file != null) {
            save_last_state_to_a_file(last_state_file, sp.last_relevant_fluents_last_state(0, (PDDLState) prob.getInit().clone(), prob));
        }

        System.out.println("Is the plan valid? " + sp.execute((PDDLState) prob.getInit()).satisfy(prob.getGoals()));

        //System.out.println(action_to_entaglement_by_init);
    }

    private static void save_last_state_to_a_file (String last_state_file, String last_relevant_fluents_last_state) {
        try {
            PrintWriter out = new PrintWriter(last_state_file);
            out.println(last_relevant_fluents_last_state);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
