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
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Predicate;
import com.hstairs.ppmajal.domain.ActionSchema;
import com.hstairs.ppmajal.domain.PddlDomain;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.PddlProblem;
import com.hstairs.ppmajal.some_computatitional_tool.DomainEnhancer;
import com.hstairs.ppmajal.wrapped_planners.metricFFWrapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
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
public class produceEntaglements {

    private static String domainFile;
    private static String problemFile;
    private static String planFile;
    private static HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_init;
    private static HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_goal;
    private static String enhancedDomainFile;
    private static String multipleFilesFileName;
    private static String flawRatio;
    private static SimplePlan sp;
    private static boolean numericEntanglements;
    private static boolean propEntanglements;

    public static void parseInput (String[] args) {

        domainFile = Utils.searchParameterValue(args, "-d");
        problemFile = Utils.searchParameterValue(args, "-p");
        planFile = Utils.searchParameterValue(args, "-P");
        enhancedDomainFile = Utils.searchParameterValue(args, "-D");
        multipleFilesFileName = Utils.searchParameterValue(args, "-M");
        flawRatio = Utils.searchParameterValue(args, "-f");
        numericEntanglements = Utils.searchParameter(args, "-NE");
        propEntanglements = Utils.searchParameter(args, "-PE");
        //System.out.println("input"+args);
        //System.out.println(domainFile);
        if (domainFile == null || (problemFile == null && multipleFilesFileName == null)) {
            System.out.println("Usage: java -jar <executable_name> -d domain_file "
                    + "\n                              -p problem_file "
                    + "\n                              -P plan_file (Optional) "
                    + "\n                              -D enhanced_domain_file (Optional, domain with entanglements; default is domain.pddlenh)"
                    + "\n                              -M multiple_problems_file"
                    + "\n                              -f flaw-ratio");
            System.exit(0);
        }
        if (enhancedDomainFile == null) {
            enhancedDomainFile = domainFile + "enh";
        }
        if (numericEntanglements) {
            System.out.println("Learning Numeric Entanglements");
        }
        if (propEntanglements) {
            System.out.println("Learning Propositional Entanglements");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws Exception {

        parseInput(args);

        PddlDomain dom = new PddlDomain(domainFile);

        if (multipleFilesFileName == null) {

            learn_entanglements_with_problem_given_in_input(dom);
            //System.out.println(action_to_entaglement_by_init);
        } else {
            learn_entanglements_via_many_problems(dom);

        }

    }

    private static void learn_entanglements_with_problem_given_in_input (PddlDomain dom) throws Exception {
        PddlProblem prob = new PddlProblem();
        prob.parseProblem(problemFile);
        //System.out.println(dom.getActionsSchema());
        planJustInCase(dom, prob);
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
    }

    private static void learn_entanglements_via_many_problems (PddlDomain dom) throws Exception {

        Scanner scanner = new Scanner(new File(multipleFilesFileName));
        scanner.useDelimiter(",");

        //this structures point out the entanglement associated with the actions
        //for now just ent by init (prop and num)
        action_to_entaglement_by_init = new HashMap();
        action_to_entaglement_by_goal = new HashMap();
        HashMap<Object, Integer> init_condition_holding_number = new HashMap();
        HashMap<Object, Integer> operator_occurence_number = new HashMap();
        HashMap<Object, Integer> goal_condition_holding_number = new HashMap();

        //HashMap<Object,Integer> operator_occurence_number = new HashMap();
        while (scanner.hasNext()) {
            //reading problem and plan file from file
            problemFile = scanner.next();
            planFile = scanner.next();
            PddlProblem prob = new PddlProblem();
            prob.parseProblem(problemFile);
            //System.out.println(dom.getActionsSchema());

            planJustInCase(dom, prob);
            //System.out.println(sp);
            for (ActionSchema as : dom.getActionsSchema()) {
                incCounter(operator_occurence_number, as, sp.countOccurenceOf(as.getName()));
            }

            for (ActionSchema as : dom.getActionsSchema()) {
                AndCond c = (AndCond) as.getPreconditions();
                for (Object o : c.sons) {
                    //System.out.println("Testing: "+o);
                    int holdingCount = sp.entangledByInitCounter(as.getName(), (PDDLState) prob.getInit(), (Condition) o);
                    incCounter(init_condition_holding_number, o.toString() + as.getName(), holdingCount);
                }

            }
            for (ActionSchema as : dom.getActionsSchema()) {
                AndCond c = as.getAddList();
                for (Object o : c.sons) {
                    //System.out.println("Testing: "+o);
                    int holdingCount = sp.entangledByGoalCounter(as.getName(), prob.getGoals(), (Condition) o);
                    incCounter(goal_condition_holding_number, o.toString() + as.getName(), holdingCount);
                }

            }

        }

        for (ActionSchema as : dom.getActionsSchema()) {
            Set<Condition> entanglementsByInit = new HashSet();
            Set<Condition> entanglementsByGoal = new HashSet();
            //System.out.println(as.getName());
            AndCond c = (AndCond) as.getPreconditions();
            for (Object o : c.sons) {
                //System.out.println("Testing: "+o);
                int holdingCount = init_condition_holding_number.get(o.toString() + as.getName());
                int n = operator_occurence_number.get(as);
                //System.out.println("Condition "+o+" : "+holdingCount+" Over "+n+" operators instances");
                //System.out.println("Flaw-ratio:"+(float) holdingCount / (float)n);
                if ((float) holdingCount / (float) n >= (1.0 - Float.parseFloat(flawRatio))) {
                    if ((numericEntanglements && o instanceof Comparison) || (o instanceof Predicate && propEntanglements)) {
                        entanglementsByInit.add((Condition) o);
                    }
                }
            }
            c = as.getAddList();
            for (Object o : c.sons) {
                //System.out.println("Testing: "+o);
                int holdingCount = goal_condition_holding_number.get(o.toString() + as.getName());
                int n = operator_occurence_number.get(as);
                //System.out.println("Condition "+o+" : "+holdingCount+" Over "+n+" operators instances");
                //System.out.println("Flaw-ratio:"+(float) holdingCount / (float)n);
                if ((float) holdingCount / (float) n >= (1.0 - Float.parseFloat(flawRatio))) {
                    if (propEntanglements) {
                        entanglementsByGoal.add((Condition) o);
                    }
                }
            }
            action_to_entaglement_by_init.put(as, entanglementsByInit);
            action_to_entaglement_by_goal.put(as, entanglementsByGoal);
            System.out.print(as.getName() + " ent_init -> ");
            for (Condition ent : entanglementsByInit) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();

            System.out.print(as.getName() + " ent_goal -> ");
            for (Condition ent : entanglementsByGoal) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();

        }

        scanner.close();
        DomainEnhancer dEnh = new DomainEnhancer();
        dEnh.addEntanglementsByInit(dom, action_to_entaglement_by_init);
        dEnh.addEntanglementsByGoal(dom, action_to_entaglement_by_goal);
        dom.saveDomain(enhancedDomainFile);

        BufferedWriter f = new BufferedWriter(new FileWriter(domainFile + "RefMap", false));
        for (String s : dEnh.getCondition_to_reformulation()) {
            f.write(s + "\n");
        }
        f.close();

        f = new BufferedWriter(new FileWriter(domainFile + "RefGoalMap", false));
        for (String s : dEnh.getGoalCondition_Reformulation()) {
            f.write(s + "\n");
        }
        f.close();

    }

    private static void incCounter (HashMap<Object, Integer> counter_of_entanglement_by_init, Object o, int n) {
        if (counter_of_entanglement_by_init.get(o) == null) {
            counter_of_entanglement_by_init.put(o, n);
        } else {
            counter_of_entanglement_by_init.put(o, counter_of_entanglement_by_init.get(o) + n);
        }

    }

    private static void planJustInCase (PddlDomain dom, PddlProblem prob) throws Exception {
        sp = new SimplePlan(dom, prob, false);
        metricFFWrapper p = new metricFFWrapper();
        p.setTimeout(100000);
        if (planFile.contains("null")) {
            sp.parseSolution(p.plan(domainFile, problemFile));
            sp.savePlan("plan.pddl");
        } else {

            String piano = "";
            int i = 0;
            while (i < planFile.length()) {
                if (planFile.charAt(i) != '\n') {
                    piano += planFile.charAt(i);
                } else {
                    break;
                }
                i++;
                //System.out.println(piano);
            }
            sp.parseSolution(piano);
        }
        //System.out.println(sp);
    }

}
