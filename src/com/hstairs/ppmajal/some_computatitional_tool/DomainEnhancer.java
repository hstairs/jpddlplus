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
package com.hstairs.ppmajal.some_computatitional_tool;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Predicate;
import com.hstairs.ppmajal.domain.ActionSchema;
import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.domain.PddlDomain;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.GroundAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * @author Enrico Scala
 */
public class DomainEnhancer {

    private String domainEnhancedFileName;
    private ParametersAsTerms constantsFound;
    private HashSet<String> condition_to_reformulation;
    private HashSet<String> goalCondition_Reformulation;
    private int j;

    public void addMacroOperator (String domainFile, GroundAction gr) throws CloneNotSupportedException, IOException {

        String constants = "(costants: " + gr.getParameters().pddlPrint() + ")";
        GroundAction temp = (GroundAction) gr.clone();
        temp.setName("Macro");
        String action = temp.toPDDL();

        Writer f;
        f = new BufferedWriter(new FileWriter("enh" + domainFile));
//        f.write()
        //todo!!!

    }

    public void addMacroOperator (PddlDomain domain, GroundAction gr) throws IOException {

        String constants = "(:constants " + gr.getParameters().pddlPrint() + ")\n";
        //GroundAction temp = (GroundAction)gr.clone();
        gr.setName("macro");
        //String action = temp.toPDDL(); 

        Writer f;
        setEnhancedDomainFileName(domain.getPddlFilRef() + "enh");
        f = new BufferedWriter(new FileWriter(getDomainEnhancedFileName()));

        f.write("(define (domain " + domain.getName() + ")\n");
        if (domain.requirements != null) {
            f.write("(:requirements " + Utils.toPDDLSet(domain.requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (!domain.getPredicates().isEmpty()) {
            f.write(constants);
        }
        f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        if (!domain.functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(domain.functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(gr.toPDDL());
        f.write("\n)");
        f.close();
    }

    public Map addMacroActions (PddlDomain domain, List macroOps) throws IOException {

        Writer f;
        Map ret = new HashMap();
        setEnhancedDomainFileName(domain.getPddlFilRef() + "enh");

        f = new BufferedWriter(new FileWriter(domain.getPddlFilRef() + "enh"));
        ParametersAsTerms constants = new ParametersAsTerms();
        String actions = "\n";
        int i = 0;
        for (Object o : macroOps) {

            GroundAction gr = (GroundAction) o;
            constants.addALLNewObjects(gr.getParameters());
            gr.setName("macro" + i);
            ret.put(gr.getName(), gr.getPrimitives());

            actions += gr.toPDDL() + "\n";
            i++;
        }
        f.write("(define (domain " + domain.getName() + ")\n");
        if (domain.requirements != null && !domain.requirements.isEmpty()) {
            f.write("(:requirements " + Utils.toPDDLSet(domain.requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (!domain.getPredicates().isEmpty()) {
            f.write("(:constants " + constants.pddlPrint() + ")\n");
        }
        f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        if (!domain.functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(domain.functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
        f.close();
        return ret;
    }

    public Map addMacroActions (PddlDomain domain, List macroOps, SimplePlan sp) throws IOException {

        Writer f;
        Map ret = new HashMap();
        setEnhancedDomainFileName(domain.getPddlFilRef() + "enh");

        f = new BufferedWriter(new FileWriter(domain.getPddlFilRef() + "enh"));
        ParametersAsTerms constants = new ParametersAsTerms();
        constants.addAll(domain.getConstants());
        String actions = "\n";
        int i = 0;
        for (Object o : macroOps) {

            GroundAction gr = (GroundAction) o;
            constants.addALLNewObjects(gr.getParameters());
            int first = sp.indexOf(gr.getPrimitives().get(0));
            int last = sp.indexOf(gr.getPrimitives().get(gr.getPrimitives().size() - 1));

            gr.setName("macro_" + first + "-" + last);
            ret.put(gr.getName(), gr.getPrimitives());

            actions += gr.toPDDL() + "\n";
            i++;
        }

        setConstantsFound(constants);

        f.write("(define (domain " + domain.getName() + ")\n");
        if (domain.requirements != null && !domain.requirements.isEmpty()) {
            f.write("(:requirements " + Utils.toPDDLSet(domain.requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (!constants.isEmpty()) {
            f.write("(:constants " + constants.pddlPrint() + ")\n");
        }
        if (domain.getPredicates() != null) {
            f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        }
        if (!domain.functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(domain.functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
        f.close();
        return ret;
    }

    public Map addMacroActionsNoContiguous (PddlDomain domain, List macroOps, SimplePlan sp) throws IOException {

        Writer f;
        Map ret = new HashMap();
        setEnhancedDomainFileName(domain.getPddlFilRef() + "enh");

        f = new BufferedWriter(new FileWriter(domain.getPddlFilRef() + "enh"));
        ParametersAsTerms constants = new ParametersAsTerms();
        String actions = "\n";
        int i = 0;
        for (Object o : macroOps) {

            GroundAction gr = (GroundAction) o;
            constants.addALLNewObjects(gr.getParameters());
            int first = sp.indexOf(gr.getPrimitives().get(0));
            int last = sp.indexOf(gr.getPrimitives().get(gr.getPrimitives().size() - 1));
            String nameMacro = "macro";
            for (Object temp : gr.getPrimitives()) {
                GroundAction primitive = (GroundAction) temp;
                nameMacro += "_" + sp.indexOf(primitive);
            }
            gr.setName(nameMacro);
            ret.put(gr.getName(), gr.getPrimitives());

            actions += gr.toPDDL() + "\n";
            i++;
        }

        setConstantsFound(constants);

        f.write("(define (domain " + domain.getName() + ")\n");
        if (domain.requirements != null && !domain.requirements.isEmpty()) {
            f.write("(:requirements " + Utils.toPDDLSet(domain.requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (!domain.getPredicates().isEmpty()) {
            f.write("(:constants " + constants.pddlPrint() + ")\n");
        }
        f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        if (!domain.functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(domain.functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
        f.close();
        return ret;
    }

    /**
     * @param domain
     * @param macroOps
     * @param sp
     * @return
     * @throws IOException
     */
    public Map addMacroOperators2 (PddlDomain domain, List<ActionSchema> macroOps, SimplePlan sp) throws IOException {

        Writer f;
        Map ret = new HashMap();
        setEnhancedDomainFileName(domain.getPddlFilRef() + "enh");

        f = new BufferedWriter(new FileWriter(domain.getPddlFilRef() + "enh"));
        ParametersAsTerms constants = new ParametersAsTerms();
        String actions = "\n";
        int i = 0;
        for (Object o : macroOps) {

            ActionSchema as = (ActionSchema) o;
            //constants.addALLNewObjects(gr.getParameters());

            actions += as + "\n";
            i++;
        }
        f.write("(define (domain " + domain.getName() + ")\n");
        if (domain.requirements != null) {
            f.write("(:requirements " + Utils.toPDDLSet(domain.requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (!domain.getPredicates().isEmpty()) // f.write("(:constants "+constants.pddlPrint()+")\n");
        {
            f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        }
        if (!domain.functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(domain.functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
        f.close();
        return ret;
    }

    public void addEntanglementsByInit (PddlDomain domain, HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_init) {

        HashMap<Object, Boolean> invariantFluent = domain.generateInvariant();
        setCondition_to_reformulation(new HashSet());
        j = 0;
        for (ActionSchema s : domain.getActionsSchema()) {
            Set<Condition> entanglementsByInit = action_to_entaglement_by_init.get(s);
            for (Condition ent : entanglementsByInit) {
                if (ent instanceof Predicate) {
                    Predicate entP = (Predicate) ent;
                    
                    Predicate predDefinition = domain.getPredicates().findAssociated(entP);
                    if (invariantFluent.get(predDefinition) != null) {
                        String name;
                        ArrayList variables = new ArrayList();
                        name = entP.getPredicateName() + j;
                        j++;
                        variables = entP.getTerms();
                        Predicate temp = Predicate.createPredicate(name, variables);
                        getCondition_to_reformulation().add(entP.pddlPrint(true) + "->" + temp.getPredicateName());
                        domain.getPredicates().add(temp);
                        AndCond precondition = (AndCond) s.getPreconditions();
                        precondition.addConditions(temp);
                    }
                    
                } else if (ent instanceof Comparison) {
                    Comparison entP = (Comparison) ent;
                    Comparison temp = (Comparison) entP.clone();
                    ArrayList<NumFluent> toAddInDefinition = temp.susbtFluentsWithTheirInvariants(invariantFluent, j);
                    j++;
                    //System.out.println(toAddInDefinition);
                    if (!toAddInDefinition.isEmpty()) {

                        //                     domain.functions.addAll(toAddInDefinition);
                        AndCond precondition = (AndCond) s.getPreconditions();
                        precondition.addConditions(temp);
                    }
                    for (NumFluent nf : toAddInDefinition) {
                        throw new RuntimeException("To be fixed");
//                        if (!nf.getBeforeReformulation().equals("same")) {
//                            domain.functions.add(nf);
//                            getCondition_to_reformulation().add(nf.getBeforeReformulation() + "->" + nf.getName());
//                        }
                    }

                }
                //System.out.println(getCondition_to_reformulation());

            }
        }
    }

    public Map addMacroOperators (PddlDomain domain, List<GroundAction> macroOps, SimplePlan sp) throws IOException {

        Writer f;
        Map ret = new HashMap();
        setEnhancedDomainFileName(domain.getPddlFilRef() + "enh");

        f = new BufferedWriter(new FileWriter(domain.getPddlFilRef() + "enh"));
        ParametersAsTerms constants = new ParametersAsTerms();
        String actions = "\n";
        int i = 0;
        for (Object o : macroOps) {

            GroundAction as = (GroundAction) o;
            //constants.addALLNewObjects(gr.getParameters());

            actions += as + "\n";
            i++;
        }
        f.write("(define (domain " + domain.getName() + ")\n");
        if (domain.requirements != null) {
            f.write("(:requirements " + Utils.toPDDLSet(domain.requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (!domain.getPredicates().isEmpty()) // f.write("(:constants "+constants.pddlPrint()+")\n");
        {
            f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        }
        if (!domain.functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(domain.functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
        f.close();
        return ret;
    }

    /**
     * @return the domainEnhancedFileName
     */
    public String getDomainEnhancedFileName ( ) {
        return domainEnhancedFileName;
    }

    /**
     * @param domainEnhancedFileName the domainEnhancedFileName to set
     */
    public void setEnhancedDomainFileName (String domainEnhancedFileName) {
        this.domainEnhancedFileName = domainEnhancedFileName;
    }

    /**
     * @return the constantsFound
     */
    public ParametersAsTerms getConstantsFound ( ) {
        return constantsFound;
    }

    /**
     * @param constantsFound the constantsFound to set
     */
    public void setConstantsFound (ParametersAsTerms constantsFound) {
        this.constantsFound = constantsFound;
    }

    /**
     * @return the condition_to_reformulation
     */
    public HashSet<String> getCondition_to_reformulation ( ) {
        return condition_to_reformulation;
    }

    /**
     * @param condition_to_reformulation the condition_to_reformulation to set
     */
    public void setCondition_to_reformulation (HashSet<String> condition_to_reformulation) {
        this.condition_to_reformulation = condition_to_reformulation;
    }

    public void addEntanglementsByGoal (PddlDomain domain, HashMap<ActionSchema, Set<Condition>> action_to_entaglement_by_goal) {
        goalCondition_Reformulation = new HashSet();
        for (ActionSchema s : domain.getActionsSchema()) {
            Set<Condition> entanglementsByGoal = action_to_entaglement_by_goal.get(s);
            for (Condition ent : entanglementsByGoal) {
                if (ent instanceof Predicate) {
                    Predicate entP = (Predicate) ent;
                    Predicate predDefinition = domain.getPredicates().findAssociated(entP);
                    String name = entP.getPredicateName() + j;
                    ArrayList variables = predDefinition.getTerms();
                    Predicate temp = Predicate.createPredicate(name, variables);
                    domain.getPredicates().add(temp);
                    getGoalCondition_Reformulation().add(entP.pddlPrint(true) + "->" + temp.getPredicateName());
                    j++;
                }
            }

        }

    }

    /**
     * @return the goalCondition_Reformulation
     */
    public HashSet<String> getGoalCondition_Reformulation ( ) {
        return goalCondition_Reformulation;
    }

    /**
     * @param goalCondition_Reformulation the goalCondition_Reformulation to set
     */
    public void setGoalCondition_Reformulation (HashSet<String> goalCondition_Reformulation) {
        this.goalCondition_Reformulation = goalCondition_Reformulation;
    }

}
