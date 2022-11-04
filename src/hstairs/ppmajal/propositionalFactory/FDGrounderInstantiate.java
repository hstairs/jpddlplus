/*
 * Copyright (C) 2020 enrico.
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
package com.hstairs.ppmajal.propositionalFactory;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.ConditionalEffect;
import com.hstairs.ppmajal.conditions.ForAll;
import com.hstairs.ppmajal.conditions.OrCond;
import com.hstairs.ppmajal.conditions.PostCondition;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.domain.PredicateSet;
import com.hstairs.ppmajal.domain.Type;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.transition.TransitionSchema;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.alg.util.Pair;

/**
 *
 * @author enrico
 */
public class FDGrounderInstantiate extends ExternalGrounder{
    
    public FDGrounderInstantiate(PDDLProblem problem, String domainFile, String problemFile) {
        super(problem,domainFile,problemFile);
    }
    
    public Collection<TransitionGround> doGrounding(){
        
        final HashMap<String, Collection<List<String>>> groundings = new HashMap();
        Pair<String, String> dom_prob = abstractNumeric();
        try {
            String fdtranslator = "python3 "+new File(FDGrounderInstantiate.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "/../../downward/translate/instantiate.py";
            String command = fdtranslator + " --dump-task " + dom_prob.getFirst() + " " + dom_prob.getSecond();
            System.out.println(String.format("DEBUG: domain file: %s problem file %s", dom_prob.getFirst(), dom_prob.getSecond()));
            System.out.println("Executing:"+command);
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            boolean read = false;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                if((line.length()>0) && ('(' == line.charAt(0))) {
                    line = line.replace("(", "");
                    line = line.replace(")","");
                    String[] split = line.split(" ");
                    String actionName = split[0];
                    Collection<List<String>> get = groundings.get(actionName);
                    if (get == null) {
                        get = new ArrayList();
                        groundings.put(actionName, get);
                    }
                    ArrayList<String> list = new ArrayList();
                    for (int i = 1; i < split.length; i++) {
                        list.add(split[i]);
                    }    
                    get.add(list);
                }
            }
            process.waitFor();

            process.destroy();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return extractActions(groundings);
//        System.out.println(groundings.keySet());
        
        
               
    }

    private Pair<String,String> abstractNumeric() {
        FileWriter writer = null;
        String newDF=null,newPF=null;
        
        try {
            String domainInput = "(define (domain temporary)\n "
                    + ";types \n (:types %s) \n"
                    + ";predicates \n (:predicates %s)\n"
                    + ";actions \n %s \n )";
            StringBuilder actionString  = new StringBuilder();
            PDDLDomain domain = problem.getLinkedDomain();
            actionString.append(actionString(domain.getActionsSchema()));
            actionString.append(actionString(domain.getEventsSchema()));
            actionString.append(actionString(domain.getProcessesSchema()));
            StringBuilder predicates = new StringBuilder();
            predicates.append(buildPredicateString(domain.getPredicates()));
            predicates.append(buildFunctionsString(domain.getFunctions()));
            StringBuilder types = new StringBuilder();
            types.append(buildTypes(domain.getTypes()));
            String toWrite = String.format(domainInput, types, predicates, actionString);
            File tmpFile = File.createTempFile("domain", ".tmp");
//            File tmpFile = new File("domaincassic.pddl");

            writer = new FileWriter(tmpFile);
            writer.write(toWrite);
            writer.close();
            newDF = tmpFile.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(FDGrounderInstantiate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FDGrounderInstantiate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            String problemInput = "(define (problem temporary)\n (:domain temporary) "
                    + ";objects \n  %s \n"
                    + ";init \n (:init %s)\n"
                    + ";goals \n (:goal %s) \n )";
            StringBuilder objects = new StringBuilder();
            objects.append(problem.getObjects().pddlPrint());
            StringBuilder goals = new StringBuilder();
            goals.append(getPreconditionString(problem.getGoals(),true));
            StringBuilder init = abstractNumericInit(problem);
            String toWrite = String.format(problemInput, objects, init,goals);
            File tmpFile = File.createTempFile("problem", ".tmp");
//            File tmpFile = new File("problemclassical.pddl");

            writer = new FileWriter(tmpFile);
            writer.write(toWrite);
            writer.close();
            newPF = tmpFile.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(FDGrounderInstantiate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FDGrounderInstantiate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Pair.of(newDF, newPF);
    }

    private StringBuilder actionString(Collection<TransitionSchema> actionsSchema) {
        String template = "(:action %s \n\t"
                              + ":parameters (%s) \n\t"
                              + ":precondition %s \n\t"
                              + ":effect %s )\n ";
        StringBuilder str = new StringBuilder();
        for (TransitionSchema t: actionsSchema){
            StringBuilder par = new StringBuilder();
            for (Variable p : (List<Variable>) t.getParameters()) {
                par.append(p.getName()).append(p.getType()).append(" ");
            }
            str.append(String.format(template, t.getName(), par,
                    getPreconditionString(t.getPreconditions(), true),
                    getEffectString(t.getConditionalPropositionalEffects(), t.getConditionalNumericEffects(),t.getForallEffects())));
        }
        return str;

    }

    private StringBuilder getPreconditionString(Condition preconditions, boolean toplevel) {
        if (preconditions instanceof BoolPredicate){
            return new StringBuilder(preconditions.pddlPrint(false));
        }
        if (preconditions instanceof Comparison){
           String template = "(and %s)";
//           if (!toplevel){
//               template = "%s";
//           }
           StringBuilder str = new StringBuilder();
           for (NumFluent nf : preconditions.getInvolvedFluents()){
               nf.pddlPrint(false,str);
           }
           return (new StringBuilder(String.format(template, str.toString())));
        }
        if (preconditions instanceof OrCond) {
            String template = "(or %s)";
            StringBuilder str = new StringBuilder();
            for (var v : ((OrCond) preconditions).sons) {
                str.append(getPreconditionString((Condition) v,false));
            }
            return (new StringBuilder(String.format(template, str.toString())));
        }
        if (preconditions instanceof AndCond) {
            String template = "(and %s)";
            StringBuilder str = new StringBuilder();
            for (var v : ((AndCond) preconditions).sons) {
                if (v instanceof NumEffect){
                    str.append(((NumEffect) v).getFluentAffected().pddlPrint(false));
                } else {
                    str.append(getPreconditionString((Condition) v,false));
                }
            }
            return (new StringBuilder(String.format(template, str.toString())));
        }
        

        return new StringBuilder(preconditions.pddlPrint(false));

    }

    private StringBuilder getEffectString(ConditionalEffects conditionalPropositionalEffects, ConditionalEffects conditionalNumericEffects, Collection<ForAll> forallEffects) {
        StringBuilder str = new StringBuilder("");
        String template0 = "(and %s )";

        StringBuilder effects = new StringBuilder("");
        effects.append(fromConditionalEffects(conditionalPropositionalEffects.getActualConditionalEffects()));
        effects.append(fromConditionalEffects(conditionalNumericEffects.getActualConditionalEffects()));
        effects.append(fromUnconditionalEffects(conditionalPropositionalEffects.getUnconditionalEffect()));
        effects.append(fromUnconditionalEffects(conditionalNumericEffects.getUnconditionalEffect()));
        for (var v: forallEffects){
            fromForAllEffects(v,effects);
        }
        return str.append(String.format(template0, effects));
    }

    private StringBuilder fromConditionalEffects(Map<Condition, Collection> actualConditionalEffects) {
        StringBuilder effects = new StringBuilder("");
        String whenTemplate = "(when %s (and %s ) )";

        Set<Map.Entry<Condition, Collection>> entrySet = actualConditionalEffects.entrySet();
        for (var v : entrySet) {
            StringBuilder preconditionString = getPreconditionString(v.getKey(),true);
            StringBuilder effect = new StringBuilder("");
            for (var v1 : v.getValue()) {
                basicEffects(v1, effect);
            }
            String format = String.format(whenTemplate, preconditionString, effect);
            effects.append(format);
        }
        return effects;
    }

    private StringBuilder fromUnconditionalEffects(Collection unconditionalEffect) {
        StringBuilder effects = new StringBuilder("");
        for (var v : unconditionalEffect) {
            basicEffects(v,effects);
        }
        return effects;
    }
 
    private void basicEffects(Object v,StringBuilder effects) {
        if (v instanceof Condition) {
            effects.append(getPreconditionString((Condition) v,true));
        } else {
            if (v instanceof NumEffect) {
                effects.append(((NumEffect) v).getFluentAffected().pddlPrint(false));
            }
        }
    }

    private StringBuilder buildPredicateString(PredicateSet predicates) {
        StringBuilder str = new StringBuilder();
        for (var v: predicates){
            str.append(v.pddlPrint(true)).append("\n");
        }
        return str;
    }
    

    private StringBuilder buildFunctionsString(Collection functions) {
        StringBuilder str = new StringBuilder();
        for (var v: functions){
            if (v instanceof NumFluent){
                str.append(((NumFluent) v).pddlPrint(true)).append("\n");
            }
        }
        return str;    
    
    }

    private StringBuilder buildTypes(Set<Type> types) {
        StringBuilder str = new StringBuilder();
        for (var v: types){
            str.append(" ").append(v.getName()).append(" -").append(v.getFatherType());
        }
        return str;
    }

    private StringBuilder abstractNumericInit(PDDLProblem init) {
        Iterable<NumFluent> numFluentsInvolvedInInit = init.getNumFluentsInvolvedInInit();
        Iterable<BoolPredicate> predicatesInvolvedInInit = init.getPredicatesInvolvedInInit();
        StringBuilder str = new StringBuilder("");
        for (var v : numFluentsInvolvedInInit) {
            if (!v.getName().contains("#")) {
                str.append(v.pddlPrint(false));
            }
        }
        for (var v : predicatesInvolvedInInit) {
            if (!v.getName().equals("=")) {
                str.append(v.pddlPrint(false));
            }
        }
        return str;   
    }
    private void fromForAllEffects(PostCondition pc, StringBuilder str) {

        if ((pc instanceof BoolPredicate) || (pc instanceof NumEffect)) {
            this.basicEffects(pc, str);
        }else if (pc instanceof ForAll){
            str.append("(forall ");
            ((ForAll) pc).parameters.pddlPrint(true, str);
            
            for (var v: ((ForAll) pc).sons){
                fromForAllEffects((PostCondition) v, str);
            }
            str.append(")");
        }else if (pc instanceof ConditionalEffect){
            pc.pddlPrint(false, str);
        }
    }
    

}
