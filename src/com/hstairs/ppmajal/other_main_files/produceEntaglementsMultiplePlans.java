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

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.domain.ActionSchema;
import com.hstairs.ppmajal.domain.PddlDomain;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.PddlProblem;
import com.hstairs.ppmajal.some_computatitional_tool.DomainEnhancer;
import com.hstairs.ppmajal.wrapped_planners.metricFFWrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
public class produceEntaglementsMultiplePlans {

    private static String domainFile;
    private static String problemFile;
    private static String planFile;
    private static HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_init;
    private static HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_goal;
    private static String enhancedDomainFile;

    public static void parseInput (String[] args) {

        domainFile = Utils.searchParameterValue(args, "-d");
        problemFile = Utils.searchParameterValue(args, "-p");
        planFile = Utils.searchParameterValue(args, "-P");
        enhancedDomainFile = Utils.searchParameterValue(args, "-D");
        //System.out.println("input"+args);
        //System.out.println(domainFile);
        if (domainFile == null || problemFile == null) {
            System.out.println("Usage: java -jar <executable_name> -d domain_file "
                    + "\n                              -p problem_file "
                    + "\n                              -P plan_file (Optional) "
                    + "\n                              -D enhanced_domain_file (Optional, domain with entanglements; default is domain.pddlenh)  ");
            System.exit(0);
        }
        if (enhancedDomainFile == null) {
            enhancedDomainFile = domainFile + "enh";
        }

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
        SimplePlan sp = new SimplePlan(dom, prob, false);

        if (planFile == null) {
            sp.parseSolution(p.plan(domainFile, problemFile));
            sp.savePlan("plan.pddl");
        } else {
            sp.parseSolution(planFile);
        }
        //System.out.println(sp);
        action_to_entaglement_by_init = new HashMap();
        action_to_entaglement_by_goal = new HashMap();
        for (ActionSchema as : dom.getActionsSchema()) {
            Set<Condition> entanglementsByInit = new HashSet();
            //System.out.println(as.getName());
            AndCond c = (AndCond) as.getPreconditions();
            for (Object o : c.sons) {
                //System.out.println("Testing: "+o);
                if (sp.entangledByInit(as.getName(), (PDDLState) prob.getInit(), (Condition) o)) {
                    entanglementsByInit.add((Condition) o);

                }
            }
            action_to_entaglement_by_init.put(as, entanglementsByInit);
            System.out.print(as.getName() + " ent_init -> ");
            for (Condition ent : entanglementsByInit) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();
        }

        for (ActionSchema as : dom.getActionsSchema()) {
            Set<Condition> entanglementsByGoal = new HashSet();
            //System.out.println(as.getName());
            AndCond c = as.getAddList();
            for (Object o : c.sons) {
                if (sp.entangledByGoal(as.getName(), prob.getGoals(), (Condition) o)) {
                    entanglementsByGoal.add((Condition) o);
                }
            }
            action_to_entaglement_by_goal.put(as, entanglementsByGoal);
            System.out.print(as.getName() + " ent_goal -> ");
            for (Condition ent : entanglementsByGoal) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();
        }
        DomainEnhancer dEnh = new DomainEnhancer();
        dEnh.addEntanglementsByInit(dom, action_to_entaglement_by_init);
        dom.saveDomain(enhancedDomainFile);
        //System.out.println(action_to_entaglement_by_init);

    }

}
