package entanglements;


import computation.DomainEnhancer;
import conditions.AndCond;
import conditions.Comparison;
import conditions.Conditions;
import conditions.Predicate;
import domain.ActionSchema;
import domain.PddlDomain;
import extraUtils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.antlr.runtime.RecognitionException;
import plan.SimplePlan;
import planners.metricFFWrapper;
import problem.PddlProblem;

/**
 * *******************************************************************
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 ********************************************************************
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
    private static HashMap<ActionSchema, Set<Conditions>> action_to_entaglement_by_init;
    private static HashMap<ActionSchema, Set<Conditions>> action_to_entaglement_by_goal;
    private static String enhancedDomainFile;
    private static String multipleFilesFileName;
    private static String flawRatio;
    private static SimplePlan sp;
    private static boolean numericEntanglements;
    private static boolean propEntanglements;

    public static void parseInput(String[] args) {

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
        if (numericEntanglements)
            System.out.println("Learning Numeric Entanglements");
        if (propEntanglements)
            System.out.println("Learning Propositional Entanglements");
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, RecognitionException, antlr.RecognitionException, Exception {

        parseInput(args);

        PddlDomain dom = new PddlDomain();
        dom.parseDomain(domainFile);
        if (multipleFilesFileName == null) {

            learn_entanglements_with_problem_given_in_input(dom);
            //System.out.println(action_to_entaglement_by_init);
        } else {
            learn_entanglements_via_many_problems(dom);

        }

    }

    private static void learn_entanglements_with_problem_given_in_input(PddlDomain dom) throws IOException, antlr.RecognitionException, RecognitionException, Exception {
        PddlProblem prob = new PddlProblem();
        prob.setDomain(dom);
        prob.parseProblem(problemFile);
        dom.validate(prob);
        //System.out.println(dom.getActionsSchema());
        planJustInCase(dom,prob);
        //System.out.println(sp);
        action_to_entaglement_by_init = new HashMap();
        action_to_entaglement_by_goal = new HashMap();
        for (ActionSchema as : dom.getActionsSchema()) {
            Set<Conditions> entanglementsByInit = new HashSet();
            //System.out.println(as.getName());
            AndCond c = (AndCond) as.getPreconditions();
            for (Object o : c.sons) {
                //System.out.println("Testing: "+o);
                if (sp.entangledByInit(as.getName(), prob.getInit(), (Conditions) o)) {
                    entanglementsByInit.add((Conditions) o);

                }
            }
            action_to_entaglement_by_init.put(as, entanglementsByInit);
            System.out.print(as.getName() + " ent_init -> ");
            for (Conditions ent : entanglementsByInit) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();
        }

        for (ActionSchema as : dom.getActionsSchema()) {
            Set<Conditions> entanglementsByGoal = new HashSet();
            //System.out.println(as.getName());
            AndCond c = (AndCond) as.getAddList();
            for (Object o : c.sons) {
                if (sp.entangledByGoal(as.getName(), prob.getGoals(), (Conditions) o)) {
                    entanglementsByGoal.add((Conditions) o);
                }
            }
            action_to_entaglement_by_goal.put(as, entanglementsByGoal);
            System.out.print(as.getName() + " ent_goal -> ");
            for (Conditions ent : entanglementsByGoal) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();
        }
        DomainEnhancer dEnh = new DomainEnhancer();
        dEnh.addEntanglementsByInit(dom, action_to_entaglement_by_init);
        dom.saveDomain(enhancedDomainFile);
    }

    private static void learn_entanglements_via_many_problems(PddlDomain dom) throws FileNotFoundException, IOException, antlr.RecognitionException, RecognitionException, Exception {

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
            prob.setDomain(dom);
            prob.parseProblem(problemFile);
            dom.validate(prob);
            //System.out.println(dom.getActionsSchema());

            planJustInCase(dom,prob);
            //System.out.println(sp);
            for (ActionSchema as : dom.getActionsSchema()) {
                incCounter(operator_occurence_number, as, sp.countOccurenceOf(as.getName()));
            }

            for (ActionSchema as : dom.getActionsSchema()) {
                AndCond c = (AndCond) as.getPreconditions();
                for (Object o : c.sons) {
                    //System.out.println("Testing: "+o);
                    int holdingCount = sp.entangledByInitCounter(as.getName(), prob.getInit(), (Conditions) o);
                    incCounter(init_condition_holding_number, o.toString()+as.getName(), holdingCount);
                }

            }
            for (ActionSchema as : dom.getActionsSchema()) {
                AndCond c = (AndCond) as.getAddList();
                for (Object o : c.sons) {
                    //System.out.println("Testing: "+o);
                    int holdingCount = sp.entangledByGoalCounter(as.getName(), prob.getGoals(), (Conditions) o);
                    incCounter(goal_condition_holding_number, o.toString()+as.getName(), holdingCount);
                }

            }

        }

        
        
        for (ActionSchema as : dom.getActionsSchema()) {
            Set<Conditions> entanglementsByInit = new HashSet();
            Set<Conditions> entanglementsByGoal = new HashSet();
            //System.out.println(as.getName());
            AndCond c = (AndCond) as.getPreconditions();
            for (Object o : c.sons) {
                //System.out.println("Testing: "+o);
                int holdingCount = init_condition_holding_number.get(o.toString()+as.getName());
                int n = operator_occurence_number.get(as);
                //System.out.println("Condition "+o+" : "+holdingCount+" Over "+n+" operators instances");
                //System.out.println("Flaw-ratio:"+(float) holdingCount / (float)n);
                if ((float) holdingCount / (float)n >= (1.0 - Float.parseFloat(flawRatio))) {
                    if ((numericEntanglements && o instanceof Comparison) || (o instanceof Predicate && propEntanglements))
                        entanglementsByInit.add((Conditions) o);
                }
            }
            c =(AndCond) as.getAddList();
            for (Object o : c.sons) {
                //System.out.println("Testing: "+o);
                int holdingCount = goal_condition_holding_number.get(o.toString()+as.getName());
                int n = operator_occurence_number.get(as);
                //System.out.println("Condition "+o+" : "+holdingCount+" Over "+n+" operators instances");
                //System.out.println("Flaw-ratio:"+(float) holdingCount / (float)n);
                if ((float) holdingCount / (float)n >= (1.0 - Float.parseFloat(flawRatio))) {
                    if (propEntanglements)
                         entanglementsByGoal.add((Conditions) o);
                }
            }
            action_to_entaglement_by_init.put(as, entanglementsByInit);
            action_to_entaglement_by_goal.put(as, entanglementsByGoal);
            System.out.print(as.getName() + " ent_init -> ");
            for (Conditions ent : entanglementsByInit) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();

            System.out.print(as.getName() + " ent_goal -> ");
            for (Conditions ent : entanglementsByGoal) {
                System.out.print(ent.pddlPrint(false) + ", ");
            }
            System.out.println();

        }
        

        
        
        
        scanner.close();
        DomainEnhancer dEnh = new DomainEnhancer();
        dEnh.addEntanglementsByInit(dom, action_to_entaglement_by_init);
        dEnh.addEntanglementsByGoal(dom, action_to_entaglement_by_goal);
        dom.saveDomain(enhancedDomainFile);
        
        
        BufferedWriter f = new BufferedWriter(new FileWriter(domainFile+"RefMap", false));
        for (String s: dEnh.getCondition_to_reformulation()){
            f.write(s+"\n");
        }
        f.close();
        
        f = new BufferedWriter(new FileWriter(domainFile+"RefGoalMap", false));
        for (String s: dEnh.getGoalCondition_Reformulation()){
            f.write(s+"\n");
        }
        f.close();
        

        

    }

    private static void incCounter(HashMap<Object, Integer> counter_of_entanglement_by_init, Object o, int n) {
        if (counter_of_entanglement_by_init.get(o) == null) {
            counter_of_entanglement_by_init.put((Object) o, n);
        } else {
            counter_of_entanglement_by_init.put((Object) o, counter_of_entanglement_by_init.get(o) + n);
        }

    }

    private static void planJustInCase(PddlDomain dom, PddlProblem prob) throws Exception {
        sp = new SimplePlan(dom, prob, false);
        metricFFWrapper p = new metricFFWrapper();
        p.setTimeout(100000);
        if (planFile.contains("null")) {
            sp.parseSolution(p.plan(domainFile, problemFile));
            sp.savePlan("plan.pddl");
        } else {
            
            String piano = "";
            int i=0;
            while (i<planFile.length()){
                if (planFile.charAt(i)!='\n')
                    piano+=planFile.charAt(i);
                else
                    break;
                i++;
                //System.out.println(piano);
            }
            sp.parseSolution(piano);
        }
        //System.out.println(sp);
    }

}