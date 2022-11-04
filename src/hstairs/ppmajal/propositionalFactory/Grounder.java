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
package com.hstairs.ppmajal.propositionalFactory;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.FactoryConditions;
import com.hstairs.ppmajal.conditions.ForAll;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.*;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.GlobalConstraint;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.transition.TransitionSchema;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import java.util.*;
import org.apache.commons.lang3.tuple.Pair;


public class Grounder {

    private final boolean smartPruning;

    public Grounder ( ) {
        this(false);
    }
    
    public Grounder (boolean smartPruning ) {
        super();
        this.smartPruning = smartPruning;
    }

    public static Set substitutions (ArrayList<Variable> input, PDDLObjects po) {

        int n_parametri = input.size();

        Set<HashMap<Variable, PDDLObject>> ret = new LinkedHashSet();
        if (n_parametri == 0) {
            ret.add(new HashMap());
            return ret;
        }

        Set<ArrayList<PDDLObject>> sub_ret = sub(new SchemaParameters(input), n_parametri, po, null);

        for (ArrayList<PDDLObject> ele : sub_ret) {
            int i = 0;

            HashMap<Variable, PDDLObject> substitution = new HashMap();
            for (Variable el : input) {
                substitution.put(el, ele.get(i));
                PDDLObject t = substitution.get(el);
                i++;
            }
            ret.add(substitution);

        }
        return ret;

    }

    private static boolean incVettore (Integer[] v, int n, Integer[] max) {

        if (n < 0) {
            return false;
        } else if (v[n] >= max[n]) {
            boolean temp = incVettore(v, n - 1, max);
            if (temp) {
                v[n] = 0;
                return true;
            } else {
                return false;
            }
        } else {
            v[n]++;
            return true;
        }
    }

    public static Set<HashMap<Variable, PDDLObject>> create_combos_ext (Set<Variable> variables, Set<PDDLObject> objects) {

        //assign integer
        HashMap<Integer, Variable> int_to_var = new HashMap();
        Integer var_counter = 0;
        for (Variable v : variables) {
            int_to_var.put(var_counter, v);
            var_counter++;
        }

        HashMap<Integer, PDDLObject> int_to_obj = new HashMap();
        Integer obj_counter = 0;
        for (PDDLObject v : objects) {
            int_to_obj.put(obj_counter, v);
            obj_counter++;
        }
        return null;
    }

    public static Set<PDDLObject[]> create_combos (PDDLObject[][] poss, int i) {
        if (i >= poss.length) {
            return new LinkedHashSet();
        }
        if (i == poss.length - 1) {
            Set<PDDLObject[]> ret = new LinkedHashSet();
            for (int k = 0; k < poss[i].length; k++) {
                if (poss[k].length == 0) {
                    return ret;
                }
            }
            for (int k = 0; k < poss[i].length; k++) {
                PDDLObject[] ele = new PDDLObject[poss.length];
                for (int j = 0; j < poss.length - 1; j++) {
                    ele[j] = poss[j][0];
                }
                ele[i] = poss[i][k];
                ret.add(ele);
            }
            return ret;
        } else {
            Set<PDDLObject[]> ret = create_combos(poss, i + 1);
            Set<PDDLObject[]> ret2 = new LinkedHashSet();
            for (int k = 1; k < poss[i].length; k++) {
                for (PDDLObject[] ele : ret) {
                    PDDLObject[] ele2 = ele.clone();
                    ele2[i] = poss[i][k];
                    ret2.add(ele2);
                }
            }
            ret.addAll(ret2);
            return ret;
        }

    }

    public static Set sub (SchemaParameters param, int n_parametri, PDDLObjects po, Map<Variable, Collection<PDDLObject>> varMap) {
        HashSet combo = new HashSet();
        ArrayList<PDDLObject>[] sub = new ArrayList[n_parametri];

        int i = 0;

        if (po.isEmpty()) {
            return combo;
        }

        if (param.isEmpty()) {
            //combo.add(new PDDLObject("null"));
            return combo;
        }

        //for each parameter in the action schema, look for all the possible substitutions.
        for (Object el1 : param) {
            sub[i] = new ArrayList();
            //System.out.println("Variable" + el1);
            Collection<PDDLObject> temp = po;
            if (varMap != null){
                Collection<PDDLObject> o1 = varMap.get(el1);
                if (o1 != null){
                    temp = o1;
                }
            }
            boolean at_least_one = false;
            for (Object el : temp) {
                PDDLObject t = (PDDLObject) el;
                Variable v = (Variable) el1;
                if ((v.getType().isAncestorOf(t.getType())) || (t.getType().equals(v.getType()))) {
                    sub[i].add(t);
                    at_least_one = true;
                }
            }
            if (!at_least_one) {
                return combo;
            }
            //System.out.println("Number of Substitution captured:"+sub[i].keySet().size());
            i++;

        }

        //This keeps track of the size of each set of objects, for each variable within the action schema
        Integer[] sizes = new Integer[n_parametri];
        for (i = 0; i < n_parametri; i++) {
            sizes[i] = sub[i].size() - 1;
            //System.out.println(sizes[i]);
        }

        Integer[] counter = new Integer[n_parametri];
        for (i = 0; i < n_parametri; i++) {
            counter[i] = 0;
        }

        do {
//                System.out.print("Combo: ");
//                for(i=0; i<n_parametri;i++)
//                    System.out.print(counter[i]);
//                 System.out.println("");
////                 System.out.println(sub[0]);
////                 System.out.println(a);
            ParametersAsTerms toAdd = new ParametersAsTerms();
            for (int z = 0; z < n_parametri; z++) {
//                System.out.println("z: "+z);
//                System.out.println("counter[z]: "+counter[z]);
//                System.out.println(sub[z].keySet());
                //if (sub[z].size()<counter[z])
                toAdd.add(sub[z].get(counter[z]));
                //else
                //   break;
            }
            combo.add(toAdd);

        } while (incVettore(counter, n_parametri - 1, sizes));

        return combo;
    }

//    public Set Substitutions (ActionSchema a, PDDLObjects po) {
//        HashSet ret = new HashSet();
//        Set combo = new HashSet();
//        SchemaParameters param = a.getPar();
//        int n_parametri = a.getPar().size();
//        return sub(param, n_parametri, po);
//
//    }
    
    public Set Substitutions (TransitionSchema a, PDDLObjects po) {
        return(this.Substitutions(a, po, null));
    }

    public Set Substitutions (TransitionSchema a, PDDLObjects po, Map<Variable,Collection<PDDLObject>> varMap) {
        SchemaParameters param = a.getParameters();
        int n_parametri = a.getParameters().size();
        return sub(param, n_parametri, po,varMap);
    }

    public Set Substitutions (SchemaGlobalConstraint constr, PDDLObjects po) {
        SchemaParameters param = constr.parameters;
        int n_parametri = constr.parameters.size();
        return sub(param, n_parametri, po);
    }

    public Set Substitutions (SchemaParameters param, PDDLObjects po) {
        return sub(param, param.size(), po);

    }

    private List creaCombinazione (PDDLObjects O, SchemaParameters aP, int index) {
        List ret = new ArrayList();
        if (index == aP.size() - 1) {
            for (Object el : O) {
                PDDLObject t = (PDDLObject) el;
                Variable v = (Variable) aP.get(index);
                if (t.getType().equals(v.getType())) {
                    ret.add(t);
                }
            }
        } else {
            List ret2 = creaCombinazione(O, aP, index + 1);
            for (Object el : O) {
                PDDLObject t = (PDDLObject) el;
                Variable v = (Variable) aP.get(index);
                if (t.getType().equals(v.getType())) {
                }
            }
        }
        return ret;
    }

    public Collection Propositionalize(TransitionSchema a, PDDLObjects po, PDDLProblem problem) throws Exception {
        Collection combo = null;
        if (a.getParameters().isEmpty()) {
            combo.add(new ParametersAsTerms());
        } else {
            combo = Substitutions(a, po);
        }
        return this.Propositionalize(a, combo, po, problem);

    }

    public Collection<Condition> getNecessaryConditions(TransitionSchema action,PDDLDomain domain){
       Collection<Condition> collection = new ArrayList<>();
        final Condition preconditions = action.getPreconditions();
        if (preconditions instanceof AndCond){
            for (final Object son : ((AndCond) preconditions).sons) {
                if (son instanceof BoolPredicate){
                    final BoolPredicate predicateAction = (BoolPredicate) son;
                    if (domain.getDynamicPredicateMap().get(predicateAction.getName()) == null) {
                        if (!collection.contains(predicateAction)){
                            collection.add(predicateAction);
                        }
                    }
                }
            }
        }
        return collection;
    }


    public Collection PropositionalizeNew(TransitionSchema action, PDDLObjects po, PDDLProblem problem, HashMap<BoolPredicate, Boolean> initBooleanState, PDDLDomain domain) {

        Collection combo;
        if (action.getParameters().isEmpty()) {
            combo = Collections.singletonList(new ParametersAsTerms());
        } else {
            HashMap<String, Boolean> dynamicPredicateMap = domain.getDynamicPredicateMap();
            combo = new LinkedHashSet();
            final ArrayList<Variable> varst = action.getParameters();
            final Variable[] vars = new Variable[varst.size()];
            for (int i = 0; i < varst.size(); i++) {
                vars[i] = varst.get(i);
            }
            final ArrayList<PDDLObject> planningObjectst = new ArrayList<>(problem.getObjects());
            final PDDLObject[] planningObjects = new PDDLObject[planningObjectst.size()];
            for (int i = 0; i < planningObjectst.size(); i++) {
                planningObjects[i] = planningObjectst.get(i);
            }


            BitSet[] map = new BitSet[vars.length];
            Int2IntMap varId = new Int2IntArrayMap();
            for (int i = 0; i < vars.length; i++) {
                map[i] = new BitSet();
                for (int j = 0; j < planningObjects.length; j++) {
                    final Variable var = vars[i];
                    final Type type = var.getType();
                    final Type type1 = planningObjects[j].getType();
                    if (type.equals(type1) || type1.isAncestorOf(type)){
                        map[i].set(planningObjects[j].getId(), true);
                        varId.put(var.getId(),i);
                    }
                }
            }
            final Collection<Condition> necessaryConditions = getNecessaryConditions(action, domain);
            for (final Condition necessaryCondition : necessaryConditions) {
                if (necessaryCondition instanceof BoolPredicate) {
                    BoolPredicate predicateAction = ((BoolPredicate) necessaryCondition);
                    BitSet[] s = getUnifyingObjects(initBooleanState,predicateAction, map.length,varId);
                    final List terms = predicateAction.getTerms();
                    //Debug
                    for (PDDLObject object : problem.getObjects()) {
                        System.out.println(s[0].get(object.getId()));
                    }
                    for (Object term : terms) {
                        if (term instanceof Variable){
                            final Variable v = (Variable)term;
                            final int id = varId.get(v.getId());
                            map[id].and(s[id]);//This is the intersection....
                        }
                    }
                }
            }
            for (int i = 0; i < vars.length; i++) {
                HashMap<Variable,Collection<PDDLObject>> temp = new HashMap<>();
                final Variable v = vars[i];
                Collection<PDDLObject> objects = new ArrayList<>();
                for (PDDLObject object : problem.getObjects()) {
                    if (map[i].get(object.getId())){
                        objects.add(object);
                    }
                }
                temp.put(v,objects);
                combo.add(Substitutions(action, po, temp));
            }
//            for (HashMap<Variable, Set<PDDLObject>> temp : S) {
//            }
    //            combo = Substitutions(action, po);
            Collection res = new LinkedHashSet();
            for (Object o1 : combo) {
                res.addAll(this.Propositionalize(action, (Collection)o1, po, problem));
            }
            return res;

        }
        return this.Propositionalize(action,combo, po, problem);
    }

    private BitSet[] getUnifyingObjects(HashMap<BoolPredicate, Boolean> initBooleanState, BoolPredicate predicateAction, int length, Int2IntMap varId) {
        BitSet[] res = new BitSet[length];
        for (Map.Entry<BoolPredicate, Boolean> ele : initBooleanState.entrySet()) {
            final BoolPredicate predicateInit = ele.getKey();
            if (predicateInit.getName().equals(predicateAction.getName())) {
                if (predicateInit.getTerms().size() == predicateAction.getTerms().size()) {
                    ArrayList<Pair<Variable,PDDLObject>> temp = new ArrayList<>();
                    for (int i = 0; i < predicateInit.getTerms().size(); i++) {
                        Object a = predicateAction.getTerms().get(i);
                        Object b = predicateInit.getTerms().get(i);
                        Type aType = null;
                        Type bType = null;
                        if (a instanceof Variable) {
                            aType = ((Variable) a).getType();
                        } else if (a instanceof PDDLObject) {
                            aType = ((PDDLObject) a).getType();
                        }
                        if (b instanceof PDDLObject) {
                            bType = ((PDDLObject) b).getType();
                        }
                        if (aType.equals(bType) || aType.isAncestorOf(bType)) {
                            if (a instanceof Variable) {
                               PDDLObject o = (PDDLObject)b ;
                               final Integer idx = varId.get(((Variable) a).getId());
                                temp.add(Pair.of((Variable)a, o));
                            }else{
                                if (a instanceof PDDLObject){
                                    if (!a.equals(b)){
                                        break;
                                    }
                                }
                            }
                        }else{
                            break;
                        }
                    }

                }
            }
        }

        return res;
    }

    public Collection Propositionalize(TransitionSchema action, PDDLObjects po, PDDLProblem problem, Map<BoolPredicate, Boolean> initBooleanState, PDDLDomain domain) {

        Map<String, Boolean> dynamicPredicateMap = domain.getDynamicPredicateMap();

        
        Collection combo;
        if (action.getParameters() == null || action.getParameters().isEmpty()) {
            combo = Collections.singletonList(new ParametersAsTerms());
//            combo = Collections.singleton(new ParametersAsTerms());
//            combo.add(new ParametersAsTerms());
        } else {

            combo = new LinkedHashSet();
            Condition cond = action.getPreconditions();
            Collection<Map<Variable, Set<PDDLObject>>> S = new ArrayList();
            final Map<Variable, Set<PDDLObject>> t = new Object2ObjectArrayMap();
            for (Object o : action.getParameters()) {
                Variable v = (Variable) o;
                t.put(v, po);
            }
            S.add(t);
            if (cond instanceof AndCond && smartPruning) {
                final AndCond and = (AndCond) cond;
                for (final Object o : and.sons) {
                    if (o instanceof BoolPredicate) {
                        final BoolPredicate predicateAction = (BoolPredicate) o;
                        if (dynamicPredicateMap.get(predicateAction.getName()) == null && predicateAction.getTerms().size() > 0) {
                            final Collection<Map<Variable, Set<PDDLObject>>> S1 = new ArrayList<>();
                            for (final Map.Entry<BoolPredicate, Boolean> ele : initBooleanState.entrySet()) {
                                final Pair<Map<Variable, Set<PDDLObject>>, Boolean> routine = routine(predicateAction.getName(),
                                        ele.getKey().getName(),
                                        predicateAction.getTerms(),
                                        ele.getKey().getTerms(), S1, S);
                                final boolean foundToBeTrue = routine.getRight();
                                final Map<Variable, Set<PDDLObject>> t1 = routine.getLeft();
                                if (foundToBeTrue) {
                                    if (!t1.isEmpty()) {
                                        updateCombo(S, S1, t1);
                                    }
                                }
                            }
                            S = S1;
                        }
                    } 
                    else if (o instanceof Comparison) {
//                        if (false){
                            final Set<NumFluent> involvedFluents = ((Comparison) o).getInvolvedFluents();
                            for (final NumFluent nf : involvedFluents) {
                                if (dynamicPredicateMap.get(nf.getName()) == null && nf.getTerms().size() > 0) {
                                    final Collection<Map<Variable, Set<PDDLObject>>> S1 = new ArrayList<>();
                                    for (final NumFluent nf2 : problem.getNumFluentsInvolvedInInit()) {
                                        final Pair<Map<Variable, Set<PDDLObject>>, Boolean> routine = routine(nf.getName(),
                                                nf2.getName(),
                                                nf.getTerms(),
                                                nf2.getTerms(), S1, S);
                                        final boolean foundToBeNotNull = routine.getRight();
                                        final Map<Variable, Set<PDDLObject>> t1 = routine.getLeft();
                                        if (foundToBeNotNull) {
                                            if (!t1.isEmpty()) {
                                                updateCombo(S, S1, t1);
                                            }
                                        }
                                    }
                                    S = S1;
                                }
                            }
//                        }

                    }
                }
            }
            for (Object temp : S) {
                Map<Variable, Collection<PDDLObject>> temp2 = (Map<Variable, Collection<PDDLObject>> )temp;
                combo.add(Substitutions(action, po, temp2));
            }
//            combo = Substitutions(action, po);
            Collection res = new LinkedHashSet();
            for (Object o1 : combo) {
                res.addAll(this.Propositionalize(action, (Collection)o1, po, problem));
            }
            return res;
        }
        return this.Propositionalize(action,combo, po, problem);

    }

    private Collection Propositionalize(TransitionSchema a, Collection combo, PDDLObjects po, PDDLProblem problem) {
        List ret = new ArrayList();
        for (Object o : combo) {
            if (o instanceof ParametersAsTerms) {
                    TransitionGround toAdd = this.ground(a,(ParametersAsTerms) o, po, problem);
                    ret.add(toAdd);
            }
        }
        return ret;
    }


    public Set Propositionalize (SchemaGlobalConstraint constr, PDDLObjects po) throws Exception {
        Set ret = new LinkedHashSet();

        Set combo = Substitutions(constr, po);
        for (Object o : combo) {
            if (o instanceof ParametersAsTerms) {
                GlobalConstraint toAdd = constr.ground((ParametersAsTerms) o, po);
                ret.add(toAdd);
            }
        }

        return ret;

    }

    private Set sub (SchemaParameters param, int n_parametri, PDDLObjects po) {
        HashSet combo = new HashSet();
        ArrayList<PDDLObject>[] sub = new ArrayList[n_parametri];

        int i = 0;

        if (po.isEmpty()) {
            return combo;
        }

        if (param.isEmpty()) {
            combo.add(PDDLObject.object("null",null));
            return combo;
        }

        //for each parameter in the action schema, look for all the possible substitutions.
        for (Object el1 : param) {
            sub[i] = new ArrayList();
            //System.out.println("Variable" + el1);
            boolean at_least_one = false;
            for (Object el : po) {
                PDDLObject t = (PDDLObject) el;
                Variable v = (Variable) el1;
                if ((v.getType().isAncestorOf(t.getType())) || (t.getType().equals(v.getType()))) {
                    sub[i].add(t);
                    at_least_one = true;
                }
            }
            if (!at_least_one) {
                return combo;
            }
            //System.out.println("Number of Substitution captured:"+sub[i].keySet().size());
            i++;

        }

        //This keeps track of the size of each set of objects, for each variable within the action schema
        Integer[] sizes = new Integer[n_parametri];
        for (i = 0; i < n_parametri; i++) {
            sizes[i] = sub[i].size() - 1;
            //System.out.println(sizes[i]);
        }

        Integer[] counter = new Integer[n_parametri];
        for (i = 0; i < n_parametri; i++) {
            counter[i] = 0;
        }

        do {
//                System.out.print("Combo: ");
//                for(i=0; i<n_parametri;i++)
//                    System.out.print(counter[i]);
//                 System.out.println("");
////                 System.out.println(sub[0]);
////                 System.out.println(a);
            ParametersAsTerms toAdd = new ParametersAsTerms();
            for (int z = 0; z < n_parametri; z++) {
//                System.out.println("z: "+z);
//                System.out.println("counter[z]: "+counter[z]);
//                System.out.println(sub[z].keySet());
                //if (sub[z].size()<counter[z])
                toAdd.add(sub[z].get(counter[z]));
                //else
                //   break;
            }
            combo.add(toAdd);

        } while (incVettore(counter, n_parametri - 1, sizes));

        return combo;
    }

    public static Map obtain_sub_from_instance (SchemaParameters parameters, ParametersAsTerms par) {

        int i = 0;
        Map substitution = new Object2ObjectArrayMap();
        if (parameters != null) {
            for (Object o : parameters) {
                Variable el = (Variable) o;
                substitution.put(el, par.get(i));
                i++;
            }
        }
        return substitution;

    }

    public static TransitionGround ground(TransitionSchema a, ParametersAsTerms parametersAsTerms, PDDLObjects po, PDDLProblem problem) {

        Map substitution = obtain_sub_from_instance(a.getParameters(), parametersAsTerms);
        final ConditionalEffects groundedConditionalPropEffects = a.getConditionalPropositionalEffects().ground(substitution,po);
        final ConditionalEffects groundedConditionalNumericEffects = a.getConditionalNumericEffects().ground(substitution,po);
        final Condition preconditions = a.getPreconditions().ground(substitution,po);
        final Collection<ForAll> forallEffects = a.getForallEffects();
        if (forallEffects != null) {//Kind of special case for now
            for (ForAll forall :forallEffects){
                AndCond temp = (AndCond) forall.ground(substitution, po);
                addEffects(temp,groundedConditionalNumericEffects,groundedConditionalPropEffects);
            }
        }
        return new TransitionGround( a.getName(), a.getSemantics(), parametersAsTerms, preconditions, groundedConditionalPropEffects,groundedConditionalNumericEffects);

    }

    private static void addEffects(AndCond temp, ConditionalEffects groundedConditionalNumericEffects, ConditionalEffects groundedConditionalPropEffects) {
        if (temp == null || temp.sons.length == 0) {
            return;
        }
        FactoryConditions.createEffectsFromPostCondition(temp, groundedConditionalPropEffects, groundedConditionalNumericEffects);
    }

    private Pair<Map<Variable, Set<PDDLObject>>,Boolean> routine(String predicateName, String predicateName1, 
            List terms, List terms1, Collection<Map<Variable, Set<PDDLObject>>> S1, Collection<Map<Variable, Set<PDDLObject>>> S) {
        boolean foundToBeTrue = false;
        final Object2ObjectMap<Variable, Set<PDDLObject>> t1 = new Object2ObjectArrayMap<>();
        if (predicateName.equals(predicateName1)) {
            if (terms.size() == terms1.size()) {
                foundToBeTrue = true;
                for (int i = 0; i < terms.size(); i++) {
                    Object a = terms.get(i);
                    Object b = terms1.get(i);
                    Type aType = null;
                    Type bType = null;
                    if (a instanceof Variable) {
                        aType = ((Variable) a).getType();
                    } else if (a instanceof PDDLObject) {
                        aType = ((PDDLObject) a).getType();
                    }
                    if (b instanceof PDDLObject) {
                        bType = ((PDDLObject) b).getType();
                    }
                    if (aType == null) {
                        throw new RuntimeException("Object " + a + " seems to be not specified as an object of the problem");
                    }
                    if (bType == null) {
                        throw new RuntimeException("Object " + b + " seems to be not specified as an object of the problem");
                    }
                    if (aType.equals(bType) || aType.isAncestorOf(bType)) {
                        if (a instanceof Variable) {
                            t1.put((Variable) a, Collections.singleton((PDDLObject) b));
                        } else {
                            if (a instanceof PDDLObject) {
                                if (!a.equals(b)) {
                                    foundToBeTrue = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        foundToBeTrue = false;
                        break;
                    }

                }
            }
        }
        return Pair.of(t1, foundToBeTrue);
        
    }


    private void updateCombo(Collection<Map<Variable, Set<PDDLObject>>> S, Collection<Map<Variable, Set<PDDLObject>>> S1, Map<Variable, Set<PDDLObject>> t1) {
        for (final Map<Variable, Set<PDDLObject>> ele2 : S) {
            final Map<Variable, Set<PDDLObject>> temp = new Object2ObjectArrayMap();
            for (final Map.Entry<Variable, Set<PDDLObject>> ele3 : ele2.entrySet()) {
                final Set<PDDLObject> temp2 = t1.get(ele3.getKey());
                if (temp2 != null) {
                    final Sets.SetView<PDDLObject> intersection = Sets.intersection(temp2, ele3.getValue());
                    temp.put(ele3.getKey(), intersection);
                } else {//not bound..shouldn't happen this
                    //throw new RuntimeException("Something went wrong when checking static predicates in grounding "+
                    //        action+ " for "+predicateAction);
                    //                                                    System.out.println(ele3.getKey());
                    temp.put(ele3.getKey(), ele3.getValue());
                }
            }
            S1.add(temp);
        }
    }

}
