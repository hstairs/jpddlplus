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
package com.hstairs.ppmajal.problem;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.domain.*;
import com.hstairs.ppmajal.expressions.*;
import com.hstairs.ppmajal.plan.SimplePlan;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.tuple.Pair;

public class GroundAction extends PDDLGenericAction {

    final protected int id;
    public boolean numeric_effect_undefined;
    public boolean normalized;
    public int hiddenParametersNumber;
    public boolean infinite_constant_effect = false;
    protected ParametersAsTerms parameters_as_terms;
    private ArrayList primitives;
    private boolean isMacro;
    private boolean reacheable = false;
    private Object2DoubleOpenHashMap<NumFluent> coefficientAffected;
    private HashMap<Predicate, Boolean> achieve;
    private LinkedHashSet<NumEffect> list_of_numeric_fluents_affected;
    private List<Pair<Condition, Float>> sdac;

    public GroundAction(String name, int id) {
        super();
        this.name = name;
        numericFluentAffected = null;
        this.parameters_as_terms = new ParametersAsTerms();
        this.preconditions = new AndCond();
        this.numericEffects = new AndCond();
        this.addList = new AndCond();
        this.delList = new AndCond();
        this.cond_effects = new AndCond();
        //numericFluentAffected = new HashMap();
        achieve = new HashMap();
        this.id = id;
    }

    public GroundAction(GroundAction gr, int id) {
        super();
        this.name = gr.name;
        numericFluentAffected = gr.numericFluentAffected;
        this.parameters_as_terms = gr.parameters_as_terms;
        this.preconditions = gr.preconditions;
        this.numericEffects = gr.numericEffects;
        this.addList = gr.addList;
        this.delList = gr.delList;
        this.cond_effects = gr.cond_effects;
        //numericFluentAffected = new HashMap();
        achieve = gr.achieve;
        this.forall = gr.forall;
        this.coefficientAffected = gr.coefficientAffected;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GroundAction ret = new GroundAction(name, id);
        if (this.addList != null) {
            ret.addList = (AndCond) this.addList.clone();
        }
        if (this.delList != null) {
            ret.delList = (AndCond) this.delList.clone();
        }
        ret.normalized = this.normalized;
        if (this.numericEffects != null) {
            ret.numericEffects = (AndCond) this.numericEffects.clone();
        }
        if (this.cond_effects != null) {
            ret.cond_effects = (AndCond) this.cond_effects.clone();
        }

        if (this.numericFluentAffected != null) {
            ret.numericFluentAffected = new Object2BooleanOpenHashMap(this.numericFluentAffected);
        }
        if (this.parameters_as_terms != null) {
            ret.parameters_as_terms = (ParametersAsTerms) this.parameters_as_terms.clone();
        }
        if (this.preconditions != null) {
            ret.preconditions = (ComplexCondition) this.preconditions.clone();
        }

        return ret;

    }

    @Override
    public String toString() {
        String parameters = "";
        for (Object o : getParameters()) {
            parameters = parameters.concat(o.toString()).concat(" ");
        }
        return "\n\nAction Name:" + this.name + " Parameters: " + parameters + "\nPre: " + this.preconditions + "\nEffetti positivi: " 
                + this.getAddList() + "\nEffetti negativi: " + this.getDelList() + "\nNumeric Effects:  " + this.getNumericEffects()+ "\n Conditional Effects:"+this.cond_effects;

    }

    public String toEcoString() {
        return toEcoString(false);
    }
    public String toEcoString(boolean timeInfo) {
        String parameters = "";
        if (getParameters() != null) {
            for (Object o : getParameters()) {
                parameters = parameters.concat(o.toString()).concat(" ");
            }
        }
        if (!timeInfo) {
            return "" + this.name + " Parameters: " + parameters;
        } else {
            return "(" + String.format("%.5f", time) + ") " + this.name + " Parameters: " + parameters;
        }

    }
    
    public String toFileCompliant() {
        return toFileCompliant(false);
    }

    public String toFileCompliant(boolean timeInfo) {
        
        StringBuilder parametri = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (Object o : getParameters()) {
            PDDLObject obj = (PDDLObject) o;
            parametri = parametri.append(obj.getName().concat(" "));
        }
        if (!timeInfo) {
            res.append("(").append(this.name).append(" ").append(parametri).append(")");
            return res.toString();
        } else {
            return String.format("%.5f", time) + ": (" + this.name + " " + parametri + ")";
        }

    }

    /**
     * @return the parameters
     */
    public ParametersAsTerms getParameters() {
        return parameters_as_terms;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(ParametersAsTerms parameters) {
        this.parameters_as_terms = parameters;
    }

    protected State apply(State s) {

//        System.out.println(this.getClass());
//        System.out.println(s.getClass());
//        if (!this.isApplicable(ret)){
//            //System.out.println("Action: " + this + "  is not applicable");
//            return null;
//        }
        HashMap subst = new HashMap();
        if (delList != null) {
            delList.apply(s, subst);
        }
        if (addList != null) {
            addList.apply(s, subst);
        }

        this.getNumericEffects().apply(s, subst);

        if (this.cond_effects != null) {
            final AndCond c_eff = this.cond_effects;
            c_eff.apply(s, subst);
        }

        for (Object o : subst.keySet()) {
            if (o instanceof NumFluent) {
                NumFluent nf = (NumFluent) o;
                if (nf.has_to_be_tracked()) {
                    ((PDDLState) s).setNumFluent(nf, (Double) subst.get(o));
                }
            } else {
                Boolean newval = (Boolean) subst.get(o);
                if (!newval) {
                    ((PDDLState) s).setPropFluent((Predicate) o, false);
                } else {
                    ((PDDLState) s).setPropFluent((Predicate) o, true);
//                    s.initPred.put((Predicate)o, (Boolean)subst.get(o));

                }
            }
        }
//        System.out.println("DEBUG: Subst within action application:"+subst);
        return s;
    }

    public Collection<NumEffect> getNumericEffectsAsCollection() {
        if (this.list_of_numeric_fluents_affected == null) {
            AndCond num = this.getNumericEffects();
            list_of_numeric_fluents_affected = new LinkedHashSet();
            this.numericFluentAffected = new Object2BooleanOpenHashMap();
            if (num != null) {
                for (Object o : num.sons) {
                    if (o instanceof NumEffect) {
                        NumEffect e = (NumEffect) o;
                        this.numericFluentAffected.put(e.getFluentAffected(), true);
                        list_of_numeric_fluents_affected.add(e);
                    }
                }
            }
            if (this.cond_effects != null) {
                for (ConditionalEffect ceff : (Collection<ConditionalEffect>) this.cond_effects.sons) {
                    AndCond effect = (AndCond) ceff.effect;
                    for (Object o : effect.sons) {
                        if (o instanceof NumEffect) {
                            NumEffect e = (NumEffect) o;
                            this.numericFluentAffected.put(e.getFluentAffected(), true);
                            list_of_numeric_fluents_affected.add(e);
                        }
                    }
                }
            }
        }
        return list_of_numeric_fluents_affected;
    }

    public void normalize() {

        if (normalized) {
            return;
        }

        if (this.getPreconditions() != null) {
            this.setPreconditions((ComplexCondition) this.getPreconditions().normalize());
            if (this.getPreconditions().isUnsatisfiable()) {
                this.setReacheable(true);
            }
        }

        AndCond num = this.getNumericEffects();
        if (num != null) {
            for (Object o : num.sons) {
                if (o instanceof NumEffect) {
                    NumEffect e = (NumEffect) o;
                    //System.out.println(e);
                    e.setRight(e.getRight().normalize());
                }
            }
        }
        this.cond_effects = (AndCond) this.cond_effects.normalize();
        this.normalized = true;
    }

    /**
     * @param numericFluentAffected the numericFluentAffected to set
     */
    public void setNumericFluentAffected(Object2BooleanMap numericFluentAffected) {
        this.numericFluentAffected = numericFluentAffected;
    }

    public RelState apply(RelState s) {
        return s.apply(this);
    }

    public boolean isApplicable(RelState current) {

        if (numericEffectUndefined(current)) {
            return false;
        }

        if (this.getPreconditions() == null) {
            return true;
        }

        return this.getPreconditions().isSatisfied(current);
    }

    public boolean isApplicable(State s) {
        if (this.getPreconditions() == null) {
            return true;
        }
        PDDLState current = (PDDLState) s;
        return current.satisfy(this.getPreconditions());
    }

    /**
     * @return the numericFluentAffected
     */
    private Condition regress(GroundAction b, GroundAction a) {
        /*Propositional Part first*/

        AndCond result = (AndCond) b.getPreconditions().clone();

        /*probably something more efficient can be done here*/
        for (Object o1 : a.getAddList().sons) {
            result.sons.remove(o1);
        }
        ReferenceLinkedOpenHashSet sons2 = new ReferenceLinkedOpenHashSet();
        for (Object o1 : result.sons) {

            //Numeric part. Substitution of variables
            if (o1 instanceof Comparison) {
                Comparison c = (Comparison) o1;
                sons2.add(Comparison.createComparison(c.getComparator(), c.getLeft().subst(a.getNumericEffects()), c.getRight().subst(a.getNumericEffects()), false));
            } else if (a.getDelList() != null) {
                if (a.getDelList().sons.contains(o1)) {
                    System.out.println("Error, " + a.name + " cannot be followed by " + b.name);
                    return null;
                } else {
                    sons2.add(o1);
                }
            } else {
                sons2.add(o1);
            }

        }

        result.sons = sons2;
        result.sons.addAll(a.getPreconditions().sons);

        //AndCond numericCondition = 
        return result;

    }

    public Comparison regressComparison(Comparison cond) {
        return Comparison.createComparison(name, cond.getLeft().subst(this.getNumericEffects()), cond.getRight().subst(this.getNumericEffects()), false);
    }

    public Condition regress(Condition cond) {

        if (cond instanceof Comparison) {
            Comparison c = (Comparison) cond;
            return Comparison.createComparison(name, c.getLeft().subst(this.getNumericEffects()), c.getRight().subst(this.getNumericEffects()), false);
        }

        /*Propositional Part first*/
        AndCond result = (AndCond) cond;


        /*probably something more efficient can be done here*/
        if (this.getAddList() != null) {
            for (Object o1 : this.getAddList().sons) {
                result.sons.remove(o1);
            }
        }
        ReferenceLinkedOpenHashSet sons2 = new ReferenceLinkedOpenHashSet();

        for (Object o1 : result.sons) {

            //Numeric part. Substitution of variables
            if (o1 instanceof Comparison) {
                Comparison c = (Comparison) o1;
                sons2.add(Comparison.createComparison(c.getComparator(), c.getLeft().subst(this.getNumericEffects()), c.getRight().subst(this.getNumericEffects()), false));
            } else if (this.getDelList() != null) {
                if (this.getDelList().sons.contains(o1)) {
                    System.out.println("Error, " + this.name + " cannot weakAchiever " + cond.toString());
                    return null;
                } else {
                    sons2.add(o1);
                }
            } else {
                sons2.add(o1);
            }

        }

        result.sons = sons2;

        if (this.getPreconditions() != null) {
            result.sons.addAll(this.getPreconditions().sons);
        }

        //AndCond numericCondition = 
        return result;
    }

    public Condition regress_formula(Condition input) {
        AndCond ret = new AndCond();
        Condition con = input.regress(this);
        if (con instanceof AndCond) {
            ret.sons.addAll(((AndCond) con).sons);
        } else {
            ret.addConditions(con);
        }
        return ret;
    }

    public Condition regress_formula_old(Condition input) {
        AndCond ret = new AndCond();
        if (this.getPreconditions() != null && !this.getPreconditions().sons.isEmpty()) {
            ret.addConditions(this.getPreconditions());
        }
        Condition con = input.regress(this);
        if (con instanceof AndCond) {
            ret.sons.addAll(((AndCond) con).sons);
        } else {
            ret.addConditions(con);
        }
        return ret;
    }

    private void progress(GroundAction a, GroundAction b, GroundAction ab) {

        /*Starting from what action a weakAchiever*/
        AndCond localAddList = (AndCond) a.addList.clone();

        /*remove those atoms which will be deleted afterwards*/
        localAddList.subtractElements(b.getDelList());

        /*atoms achieved by b*/
        //System.out.println(b);
        //System.out.println(b.getAddList());
        if (b.getAddList() != null) {
            localAddList.sons.addAll(b.getAddList().sons);
        }

        /*Starting from what action a deletes*/
        AndCond localDelList = null;
        if (a.delList != null) {
            localDelList = (AndCond) a.delList.clone();
            /*remove those atoms which will be added by b*/
            localDelList.subtractNegation(b.getAddList());
        } else {
            localDelList = new AndCond();
        }

        /*add the remaining atoms to be removed because of b*/
        if (b.getDelList() != null) {
            localDelList.sons.addAll(b.getDelList().sons);
        }


        /*update the macro operator model*/
        ab.setAddList(localAddList);
        ab.setDelList(localDelList);

        AndCond numEff = new AndCond();
        if (b.getNumericEffects() != null) {
            for (Object o : b.getNumericEffects().sons) {
                NumEffect nf = (NumEffect) o;
                numEff.sons.add(nf.subst(a.getNumericEffects()));
                ab.addNumericFluentAffected(nf.getFluentAffected());
            }
        }
        if (a.getNumericEffects() != null) {
            for (Object o : a.getNumericEffects().sons) {
                NumEffect nf = (NumEffect) o;
                //nf.getFluentAffected();
                if (ab.getNumericFluentAffected().contains(nf.getFluentAffected())) {
                    numEff.sons.add(o);
                    ab.addNumericFluentAffected(nf.getFluentAffected());
                }
            }
        }

        ab.setNumericEffects(numEff);

    }

    public String toPDDL() {

        String ret = "(:action " + this.name + "\n";

        ret += ":parameters ()\n";
        ret += ":precondition " + this.getPreconditions().pddlPrint(false) + "\n";
        ret += ":effect " + this.pddlEffects();

        return ret + ")";

    }
//    
//    (:action debark
// :parameters (?p - person ?a - aircraft ?c - city)
// :precondition (and (in ?p ?a)
//                 (located ?a ?c))
// :effect (and (not (in ?p ?a))
//              (located ?p ?c)
//		(decrease (onboard ?a) 1)))

    protected String pddlEffects() {
        String ret = "(and ";
        if (this.getAddList() != null && this.getAddList().sons != null) {

            for (Object o : this.getAddList().sons) {
                Predicate p = (Predicate) o;
                ret += p.pddlPrint(true);
            }
        }
        if (this.getDelList() != null && this.getDelList().sons != null) {
            for (Object o : this.getDelList().sons) {
                NotCond p = (NotCond) o;
                ret += p.pddlPrint(true);
            }
        }
        if (this.getNumericEffects() != null && this.getNumericEffects().sons != null) {
            for (Object o : this.getNumericEffects().sons) {
                NumEffect nE = (NumEffect) o;
                ret += nE.pddlPrint(true);
            }
        }
        if (this.cond_effects != null) {
            for (Object o : this.cond_effects.sons) {
                ConditionalEffect nE = (ConditionalEffect) o;
                ret += nE.pddlPrint(true);

            }
        }

        return ret + ")";
    }

    public boolean simplifyModel(PddlDomain domain, PddlProblem problem) throws Exception {

        HashMap invariantFluents = problem.getActualFluents();
        //add invariantFluents because free variable
        for (NumFluent nf : domain.get_derived_variables()) {
            invariantFluents.put(nf.getName(), Boolean.FALSE);
        }

        GroundAction a = this;

        Condition con = a.getPreconditions();

        if (con != null) {

            con = con.weakEval(problem, invariantFluents);

            if (con == null) {
                //System.out.println("A precondition is never satisfiable");
                return false;
            }
        }

        Condition eff = a.getNumericEffects();

        //System.out.println(abstractInvariantFluents);
        eff = eff.weakEval(problem, invariantFluents);

        if (eff == null) {
            return false;
        }
        //System.out.println(a.toPDDL());
        a.normalize();
//        if (a.isUnsatisfiable())
//            return false;
        //System.out.println(a.toPDDL());
        return true;
    }

    /**
     * @return the isMacro
     */
    public boolean isIsMacro() {
        return isMacro;
    }

    /**
     * @param isMacro the isMacro to set
     */
    public void setIsMacro(boolean isMacro) {
        this.isMacro = isMacro;
    }

    /**
     * @return the primitives
     */
    public ArrayList getPrimitives() {
        if (primitives == null) {
            primitives = new ArrayList();
        }
        return primitives;
    }

    public void setPrimitives(ArrayList primitives) {
        setIsMacro(true);
        this.primitives = primitives;
    }

    public int getParametersFusionNumber() {
        hiddenParametersNumber = 0;
        if (this.isMacro) {

            for (GroundAction gr : (ArrayList<GroundAction>) this.primitives) {
                hiddenParametersNumber += gr.getParameters().size();

            }
        }
        return hiddenParametersNumber - this.getParameters().size();
    }

    public boolean threatenConditions(ComplexCondition goal, SimplePlan sp, PDDLState current) {
        boolean threatened = false;
        for (Object o : goal.sons) {
            if (o instanceof Predicate) {
                Predicate p = (Predicate) o;
                if (this.addList instanceof AndCond) {
                    if (!this.addList.sons.contains(o)) {
                        threatened = true;
                    }
                }
            } else if (o instanceof Comparison) {
                System.out.println("Minacce su goal comparison da fare!");
            }
        }

        return threatened;

    }

    public boolean threatGoalConditions(ComplexCondition goal, SimplePlan sp, int j, PDDLState current) {
        boolean threatened = false;

        Set threatenedAtoms = new HashSet();

        if (delList instanceof AndCond) {
            for (NotCond o : (Collection<NotCond>) delList.sons) {
                //System.out.println(o);
                for (Object o1 : goal.sons) {
                    //System.out.println(o1);
                    //System.out.println(o.son.iterator().next());
                    Predicate a = (Predicate) o.getSon();
                    Predicate b = (Predicate) o1;
                    if (a.toString().equalsIgnoreCase(b.toString())) {
                        //System.out.println("Scoperto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!<=======================================");
                        //System.out.println("Per azione:" + j);
                        threatenedAtoms.add(o1);
                    }
                }
            }
        }
        if (threatenedAtoms.isEmpty()) {
            return false;
        }
        PDDLState end = current.clone();
        for (int i = j; i < sp.size(); i++) {
            end = (PDDLState) sp.get(i).apply(end);
        }
        for (Object o : threatenedAtoms) {
            if (!end.holds((Predicate) o)) {
                System.out.println("=================Found a goal weakThreat=============");
                return true;
            }

        }

        return false;
    }

    public Condition regressAndStoreFatherPointer(ComplexCondition cond) {
        /*Propositional Part first*/

        AndCond result = new AndCond();


        /*probably something more efficient can be done here*/
        if (this.getAddList() != null) {
            for (Object o1 : cond.sons) {
                if (o1 instanceof Predicate) {
                    if (!this.getAddList().sons.contains(o1)) {
                        result.sons.add(o1);
                    }
                }
            }

        }
        for (Object o1 : cond.sons) {

            //Numeric part. Substitution of variables
            if (o1 instanceof Comparison) {
                Comparison temp = (Comparison) o1;
                Comparison c = (Comparison) temp.clone();
                Comparison cond2 = Comparison.createComparison(c.getComparator(), c.getLeft().subst(this.getNumericEffects()), c.getRight().subst(this.getNumericEffects()), false);
                cond2.fatherFromRegression = temp;
                result.sons.add(cond2);
            } else if (this.getDelList() != null) {
                if (this.getDelList().sons.contains(o1)) {
                    System.out.println("Error, " + this.name + " cannot weakAchiever " + cond.toString());
                    return null;
                }
            }

        }

        result.sons.addAll(this.getPreconditions().sons);

        //AndCond numericCondition = 
        return result;
    }

    public ActionSchema unGround() {
        ActionSchema result = new ActionSchema();
        result.setName(this.name);

        Map asbstractionOf = this.abstractParameters();
        for (Object o : this.getParameters()) {
            PDDLObject obj = (PDDLObject) o;
            result.addParameter((Variable) asbstractionOf.get(obj.getName()));
        }
        //System.out.println("Macro Action: " + result.getName());
        //System.out.println(asbstractionOf);
        result.setPreconditions((ComplexCondition) preconditions.unGround(asbstractionOf));
        result.setAddList((AndCond) addList.unGround(asbstractionOf));
        result.setDelList((AndCond) delList.unGround(asbstractionOf));
        result.setNumericEffects((AndCond) this.numericEffects.unGround(asbstractionOf));

        return result;
    }

    private Map abstractParameters() {

        HashMap result = new HashMap();
        for (Object o : this.getParameters()) {
            PDDLObject po = (PDDLObject) o;
            Variable absPo = new Variable("?" + po.getName(), po.getType());
            result.put(po.getName(), absPo);
        }
        return result;
    }

    public boolean weakAchiever(Predicate p) {

        if (this.achieve.get(p) == null) {
            if (this.getAddList() != null) {
                if (this.getAddList().getInvolvedPredicates().contains(p)) {
                    this.achieve.put(p, true);
                    return true;
                }
            }
            if (this.cond_effects != null) {
                for (ConditionalEffect ceff : (Collection<ConditionalEffect>) this.cond_effects.sons) {
                    if (ceff.effect.achieve(p) != null) {
                        this.achieve.put(p, true);
                        return true;
                    }
                }
            }
            this.achieve.put(p, false);
            return false;
        }

        return this.achieve.get(p);
    }

    public boolean delete(Predicate p) {
        AndCond add = this.getDelList();
        //System.out.println(this.toPDDL());
        //System.out.println(this.getDelList());
        if (add != null && add.sons != null) {
            for (Object o : this.getDelList().sons) {
                NotCond nc = (NotCond) o;
                if (nc.getSon().equals(p)) {
                    return true;
                }
            }
        }
        if (this.cond_effects != null) {
            for (ConditionalEffect ceff : (Collection<ConditionalEffect>) this.cond_effects.sons) {
                if (ceff.effect.delete(p) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public PDDLState transformInState() {
        PDDLState ret = new PDDLState();
        ret = (PDDLState) this.apply(ret);
        return ret;
    }

    public boolean mayInfluence(Comparison aThis) {

        this.generateAffectedNumFluents();
        if (aThis.isNormalized()) {
            return aThis.getLeft().involve(numericFluentAffected.keySet()) || aThis.getRight().involve((Collection<NumFluent>) this.numericFluentAffected.keySet());
        } else {
            return aThis.getLeft().involve(this.numericFluentAffected.keySet()) || aThis.getRight().involve(this.numericFluentAffected.keySet());
        }

    }

    public boolean influence(HashMap<NumFluent, HashSet<NumFluent>> dependsOn) {

        for (NumFluent nf : dependsOn.keySet()) {
//            if (numericFluentAffected.contains(nf)) {
//                    return true;
//                }
            for (NumFluent nf1 : dependsOn.get(nf)) {
                if (numericFluentAffected.get(nf1) != null) {
                    //System.out.println(nf+" <-"+nf1);
                    return true;
                }
            }
        }

        return false;

    }

    public boolean influence(NumFluent nf) {
        if (this.numericFluentAffected == null) {
            this.generateAffectedNumFluents();
        }

        return numericFluentAffected.get(nf) != null;
    }

    public Set influencedBy(NumFluent nf) {
        HashSet<NumFluent> ret = new HashSet();
        AndCond c = this.getNumericEffects();
        if (c != null) {
            if (c instanceof AndCond) {
                for (Object o : c.sons) {
                    NumEffect eff = (NumEffect) o;
                    if (eff.getFluentAffected().equals(nf)) {
                        return eff.getRight().getInvolvedNumericFluents();
                    }

                }
            }

        }
        return null;
    }

    //    public String to_smtlib_with_repetition() {
//        String parametri = "";
//        for (Object o : getParameters()) {
//            parametri = parametri.concat(o.toString()).concat("");
//        }
//        return ("ACTION" + this.name + "" + parametri).replaceAll("\\s+", "");
//    }
    public boolean weakThreat(AndCond andCond) {
        if (this.delList == null) {
            return false;
        }
        if (this.delList.sons == null) {
            return false;
        }
        if (andCond.sons != null) {

            for (Object o : this.delList.sons) {
                NotCond nc = (NotCond) o;
                Predicate p = (Predicate) nc.getSon();
                for (Object o2 : andCond.sons) {
                    if (o2 instanceof Predicate) {
                        Predicate p1 = (Predicate) o2;
                        if (p1.equals(p)) {
                            //System.out.println("Found: "+p1+p);
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    //    public HashMap threats(AndCond andCond) {
//        if (this.delList == null) {
//            return false;
//        }
//        if (this.delList.sons == null) {
//            return false;
//        }
//        if (andCond.sons != null) {
//
//            for (Object o : this.delList.sons) {
//                NotCond nc = (NotCond) o;
//                Predicate p = (Predicate) nc.son.iterator().next();
//                for (Object o2 : andCond.sons) {
//                    if (o2 instanceof Predicate) {
//                        Predicate p1 = (Predicate) o2;
//                        if (p1.equals(p)) {
//                            //System.out.println("Found: "+p1+p);
//                            return true;
//                        }
//                    }
//
//                }
//            }
//        }
//        return false;
//    }
//    
    public String toSmtVariableString(int i) {
        String parametri = "";
        for (Object o : getParameters()) {
            PDDLObject po = (PDDLObject) o;
            parametri += "@" + po.getName();
            //parametri = parametri.concat(po.getName()).concat("_");
        }
        return ("ACTION" + this.name + parametri + "@-" + i).replaceAll("\\s+", "");
    }

    public String toVariableString() {
        String parametri = "";
        for (Object o : getParameters()) {
            PDDLObject po = (PDDLObject) o;
            parametri += "@" + po.getName();
            //parametri = parametri.concat(po.getName()).concat("_");
        }
        return ("ACTION" + this.name + parametri + "@").replaceAll("\\s+", "");
    }

//    //This function regresses the cond passed as input according to the model of the action. The value of the parameter will be modified. So if you want to generate a new condition please clone before using the function
//    public Condition regressNew (Condition cond) {
//
//        AndCond result = null;
//
//        /*Propositional Part first*/
//        if (cond instanceof AndCond) {
//            result = (AndCond) cond;
//        } else {
//            result = new AndCond();
//            result.addConditions(cond);
//        }
//
//
//        /*probably something more efficient can be done here*/
//        if (this.getAddList() != null) {
//            for (Object o1 : this.getAddList().sons) {
//                //System.out.println(result);
//                result.sons.remove(o1);
//                //System.out.println(result);
//            }
//
//        }
//        for (Object o1 : result.sons) {
//
//            //Numeric part. Substitution of variables
//            if (o1 instanceof Comparison) {
//                Comparison c = (Comparison) o1;
//                c.setLeft(c.getLeft().subst(this.getNumericEffects()));
//                c.setRight(c.getRight().subst(this.getNumericEffects()));
//
//            } else if (this.getDelList() != null) {
//                if (this.getDelList().sons.contains(o1)) {
//                    System.out.println("Error, " + this.name + " cannot weakAchiever " + cond.toString());
//                    return null;
//                }
//            }
//
//        }
//
//        //System.out.println(result);
//        if (this.getPreconditions() != null) {
//            result.sons.addAll(this.getPreconditions().sons);
//        }
//
//        //System.out.println(result);
//        //AndCond numericCondition = 
//        return result;
//
//    }
    public boolean improve(Comparison t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean numericEffectUndefined(RelState current) {

        //if (numeric_effect_undefined == null) {//there is no action that can make a numeric fluent undefined. So this can be considered an invariant
        if (this.numericEffects == null) {
            return false;
        }
        if (this.numericEffects.sons.isEmpty()) {
            return false;
        }
        numeric_effect_undefined = this.numericEffects.sons.stream().anyMatch(new java.util.function.Predicate<NumEffect>() {
            @Override
            public boolean test(NumEffect e) {
                return e.getRight().eval(current).getInf().getNumber().isNaN();
            }
        });
        //}
        return numeric_effect_undefined;
//            

    }

    public boolean simplifyModelWithControllableVariablesSem(PddlDomain domain, EPddlProblem problem) throws Exception {

        HashMap invariantFluents = problem.getActualFluents();
        //add invariantFluents because free variable
        for (NumFluent nf : domain.get_derived_variables()) {
            invariantFluents.put(nf.getName(), Boolean.FALSE);
        }

        GroundAction a = this;
        //a.normalizeAndCopy();
        boolean free_var_semantics = !domain.get_derived_variables().isEmpty();
        Condition con = a.getPreconditions();
        if (con != null) {
            con.setFreeVarSemantic(free_var_semantics);
            con = con.weakEval(problem, invariantFluents);
            if (con == null || con.isUnsatisfiable()) {
                return false;
            }
            if (con.isValid()) {
                a.setPreconditions(null);
            }
        } else {
            return false;
        }

        Condition eff = a.getNumericEffects();
        eff.setFreeVarSemantic(free_var_semantics);
        eff = eff.weakEval(problem, invariantFluents);

        a.cond_effects = (AndCond) a.cond_effects.weakEval(problem, invariantFluents);
        if (eff == null) {
//            System.out.println("Pruning because of the effect");
            return false;
        }
        //System.out.println(a.toPDDL());
        a.normalize();
//        if (a.isUnsatisfiable())
//            return false;
        //System.out.println(a.toPDDL());
        return true;
    }

    public void addPreconditions(Condition c) {
        if (this.getPreconditions() != null) {
            if (this.getPreconditions().sons != null) {
                this.getPreconditions().sons.add(c);
            } else {
                this.preconditions.sons = new ReferenceLinkedOpenHashSet();
                this.getPreconditions().sons.add(c);
            }
        } else {
            this.preconditions = new AndCond();
            this.preconditions.sons = new ReferenceLinkedOpenHashSet();
            this.getPreconditions().sons.add(c);
        }
    }

    private boolean effects_might_be_influenced() throws Exception {
        boolean ret = true;
        for (Object o : this.getNumericEffects().sons) {
            NumEffect nf = (NumEffect) o;
            //System.out.println("nf:"+nf.getRhs().getClass());
            if (nf.getRight() instanceof ExtendedNormExpression) {
                ExtendedNormExpression ne = (ExtendedNormExpression) nf.getRight();
                //System.out.println(ne);
                if (!ne.isNumber()) {
                    return true;
                }
            } else if (!(nf.getRight() instanceof PDDLNumber)) {
                //System.out.println(nf.getRhs());
                return true;
            }
        }

        return false;
    }

    //this type of influence is on the rhs, that is it reasons on just complex dependencies
    public boolean is_complexly_influenced_by(GroundAction a) {
        for (Object o : this.getNumericEffects().sons) {
            NumEffect nf = (NumEffect) o;
            //System.out.println(nf);
            if (nf.getRight().involve(a.getNumericFluentAffected())) {
                return true;
            }
//            if ((nf.getOperator().equals("increase"))|| (nf.getOperator().equals("increase")) && (a.getNumericFluentAffected().contains(nf.getFluentAffected())))
//                return true;

        }
        return false;
    }

    /**
     * @return the reacheable
     */
    public boolean isReacheable() {
        return reacheable;
    }

    /**
     * @param reacheable the reacheable to set
     */
    public void setReacheable(boolean reacheable) {
        this.reacheable = reacheable;
    }

    public boolean preconditioned_on(Condition c) {
        if (this.getPreconditions() == null) {
            return false;
        }
        if (this.getPreconditions().sons == null)//assuming action preconditions are given as set of atoms
        {
            return false;
        }
        for (Condition cond : (Collection<Condition>) this.getPreconditions().sons) {
            if (cond.getHeuristicId() == c.getHeuristicId()) {
                return true;
            }
        }
        return false;

    }

    public Float getNumberOfExecutionInt(PDDLState s_0, Comparison comp) {
        float a1;
        float b;

//        if (!this.interact_with(comp)){
//            return Integer.MAX_VALUE;
//        }
        if (!comp.involve(this.getNumericFluentAffected())) {
            return Float.MAX_VALUE;
        }

//        if (comp.eval_to_null(s_0)){
//            Comparison reg = this.regressComparison(comp);
//            if (s_0.satisfy(comp)){
//                return 1;
//            }
//        }
        a1 = comp.eval_not_affected(s_0, this);
        b = comp.eval_affected(s_0, this);
        if (b < 0.0) {
//            System.out.println(a1);
//            System.out.println(b);
//            //System.out.println("DEBUG:"+s_0);
//            System.out.println("DEBUG: "+this.toEcoString()+" is considered negative for:"+comp);
            return Float.MAX_VALUE;//the action contributes negatively
        }

        //Assumption: comparisons are normalized!
        if (comp.getComparator().equals("=")) {
            int m1 = (int) (-a1 / b);
            if (m1 < 0 || a1 % b != 0) {
                return Float.MAX_VALUE;
            } else {
                return (float) m1;
            }
        } else {//it is >= or >
            float m1 = -a1 / b;
            if (m1 >= 0) {
                if (comp.getComparator().equals(">")) {
                    m1 += 1;
                }
                return (float) Math.ceil(m1);
            } else {
                return Float.MAX_VALUE;
            }
        }
    }

    public Float getNumberOfExecution(PDDLState s_0, Comparison comp) {
        final Float a1;
        final Float b;

//        if (!this.interact_with(comp)){
//            return Float.MAX_VALUE;
//        }
        if (!comp.interactWith(this)) {
//        if (!comp.involve(this.getNumericFluentAffected())) {
            return Float.MAX_VALUE;
        }

//        if (comp.eval_to_null(s_0)){
//            Comparison reg = this.regressComparison(comp);
//            if (s_0.satisfy(comp)){
//                return 1;
//            }
//        }
        a1 = comp.eval_not_affected(s_0, this);
        if (a1 == Float.NaN) {
            return Float.MAX_VALUE;
        }
        b = comp.eval_affected(s_0, this);
        if (b == Float.NaN) {
            return Float.MAX_VALUE;
        }
        if (a1 == null || b == null) {
            return Float.MAX_VALUE;
        }
        if (b < 0.0) {
//            System.out.println(a1);
//            System.out.println(b);
//            //System.out.println("DEBUG:"+s_0);
//            System.out.println("DEBUG: "+this.toEcoString()+" is considered negative for:"+comp);
            return Float.MAX_VALUE;//the action contributes negatively
        }

        //Assumption: comparisons are normalized!
        if (comp.getComparator().equals("=")) {
            Float m1 = (-a1 / b);
            if (m1 < 0 || a1 % b != 0) {
                return Float.MAX_VALUE;
            } else {
                return m1;
            }
        } else {//it is >= or >
            float m1 = -a1 / b;
            if (m1 >= 0) {
                if (comp.getComparator().equals(">")) {
                    m1 += 0.00001;//epsilon
                }
                return m1;
            } else {
                return Float.MAX_VALUE;
            }
        }
    }

    public int getNumberOfExecutionWithoutCache(PDDLState s_0, Comparison comp) {
        float a1;
        float b;

//        if (!this.interact_with(comp)){
//            return Integer.MAX_VALUE;
//        }
        if (!comp.involve(this.getNumericFluentAffected())) {
            return Integer.MAX_VALUE;
        }

//        if (comp.eval_to_null(s_0)){
//            Comparison reg = this.regressComparison(comp);
//            if (s_0.satisfy(comp)){
//                return 1;
//            }
//        }
        a1 = comp.eval_not_affected(s_0, this);
        b = comp.eval_affected(s_0, this);
        if (b < 0.0) {
//            System.out.println(a1);
//            System.out.println(b);
//            //System.out.println("DEBUG:"+s_0);
//            System.out.println("DEBUG: "+this.toEcoString()+" is considered negative for:"+comp);
            return Integer.MAX_VALUE;//the action contributes negatively
        }

        //Assumption: comparisons are normalized!
        if (comp.getComparator().equals("=")) {
            int m1 = (int) (-a1 / b);
            if (m1 < 0 || a1 % b != 0) {
                return Integer.MAX_VALUE;
            } else {
                return m1;
            }
        } else {//it is >= or >
            float m1 = -a1 / b;
            if (m1 >= 0) {
                if (comp.getComparator().equals(">")) {
                    m1 += 1;
                }
                return (int) Math.ceil(m1);
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    public int getBoundOnTheNumberOfExecution(PDDLState s_0, Comparison comp) {
        float a1;
        float b;

        if (!comp.involve(this.getNumericFluentAffected())) {
            return Integer.MAX_VALUE;
        }
//        if (comp.eval_to_null(s_0)){
//            Comparison reg = this.regressComparison(comp);
//            if (s_0.satisfy(comp)){
//                return 1;
//            }
//        }
        a1 = comp.eval_not_affected(s_0, this);
        b = comp.eval_affected(s_0, this);
//        System.out.println(a1);
//        System.out.println(b);
        //Assumption: comparisons are normalized!
        if (comp.getComparator().equals("=")) {
            int m1 = (int) (-a1 / b);
            if (m1 < 0 || a1 % b != 0) {
                return Integer.MAX_VALUE;
            } else {
                return m1;
            }
        } else {//it is >= or >
            float m1 = -a1 / b;
            if (m1 >= 0) {
                if (comp.getComparator().equals(">")) {
                    m1 += 1;
                }
                return (int) Math.ceil(m1);
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    public Double getCoefficientAffected(NumFluent f) {
        this.generateCoefficientsAffected();
        return this.coefficientAffected.get(f);
    }

    private void generateCoefficientsAffected() {

        if (coefficientAffected != null) {
            return;
        }
        if (this.getNumericEffects() == null) {
            return;
        }

        coefficientAffected = new Object2DoubleOpenHashMap();
        for (Object c : this.getNumericEffects().sons) {
            NumEffect nEff = (NumEffect) c;
            ExtendedNormExpression right = (ExtendedNormExpression) nEff.getRight();
            if (nEff.getOperator().equals("increase") || nEff.getOperator().equals("decrease")) {
                coefficientAffected.put(nEff.getFluentAffected(), 1 + right.getCoefficient(nEff.getFluentAffected()));
            } else {
                coefficientAffected.put(nEff.getFluentAffected(), right.getCoefficient(nEff.getFluentAffected()));
            }
        }

    }

    public Pair<NumEffect, Boolean> expressionToBeEvaluated(NumFluent f, PDDLState s_0) {
        for (Object c : this.getNumericEffects().sons) {
            NumEffect nEff = (NumEffect) c;
            if (nEff.getFluentAffected().equals(f)) {
                return Pair.of(nEff, false);
            }
        }
        return null;
    }

    private Float getExprImpact(PDDLState s_0, NumEffect nEff, NumFluent f) {
        if (nEff.getFluentAffected().equals(f)) {
            ExtendedNormExpression right = (ExtendedNormExpression) nEff.getRight();
            if (nEff.getOperator().equals("increase")) {
                return right.eval_apart_from_f(f, s_0);
            } else if (nEff.getOperator().equals("decrease")) {
                return right.eval_apart_from_f(f, s_0) * -1.0f;

            }
            return right.eval_apart_from_f(f, s_0);
        } else {
            return 0f;
        }
    }

    public Float getValueOfRightExpApartFromAffected(NumFluent f, PDDLState s_0) {
        for (Object c : this.getNumericEffects().sons) {
            NumEffect nEff = (NumEffect) c;
            if (nEff.getFluentAffected().equals(f)) {
                ExtendedNormExpression right = (ExtendedNormExpression) nEff.getRight();
                if (nEff.getOperator().equals("increase")) {
                    return right.eval_apart_from_f(f, s_0);
                } else if (nEff.getOperator().equals("decrease")) {
                    return right.eval_apart_from_f(f, s_0) * -1.0f;

                }
                return right.eval_apart_from_f(f, s_0);
            }

        }
        throw new RuntimeException("Likely, you have state dependent numeric effects, which aren't supported at the moment");
    }

    public NumEffect getAffectorOf(NumFluent f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addNumericFluentAffected(NumFluent fluentAffected) {
        if (this.numericFluentAffected == null) {
            this.numericFluentAffected = new Object2BooleanOpenHashMap();
        }
        this.numericFluentAffected.put(fluentAffected, true);
    }

    public boolean depends_on(GroundAction ele) {
        if (this.getNumericEffects() == null) {
            return false;
        }
        for (NumEffect ne : (Collection<NumEffect>) this.getNumericEffects().sons) {

            ele.generateAffectedNumFluents();
            if (ne.getRight().involve(ele.numericFluentAffected.keySet())) {
                return true;
            }
        }
        return false;
    }

    public boolean is_possible_achiever_of(Comparison comp) {
        float positiveness = 0;
        if (this.getNumericEffectsAsCollection().isEmpty()) {
            return false;
        }
        if (comp.getLeft() instanceof ExtendedNormExpression) {
            ExtendedNormExpression left = (ExtendedNormExpression) comp.getLeft();
            for (ExtendedAddendum ad : left.summations) {
                if (ad.f != null) {
                    for (NumEffect ne : this.getNumericEffectsAsCollection()) {
                        if (!ne.getFluentAffected().equals(ad.f)) {
                            continue;
                        }
//                        if (ne.isPseudo_num_effect()) {
//                            return true;
//                        }
                        if (ne.getInvolvedNumericFluents().isEmpty()) {
                            ExtendedNormExpression rhs = (ExtendedNormExpression) ne.getRight();

                            if (!rhs.linear) {
                                return false;
                            }
                            if (ne.getOperator().equals("increase")) {
                                try {
                                    positiveness += rhs.getNumber() * ad.n;
                                } catch (Exception ex) {
                                    Logger.getLogger(GroundAction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (ne.getOperator().equals("decrease")) {
                                try {
                                    positiveness += (-1) * rhs.getNumber() * ad.n;
                                } catch (Exception ex) {
                                    Logger.getLogger(GroundAction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (ne.getOperator().equals("assign")) {
                                try {
                                    double contribution = rhs.getNumber() * ad.n;
                                    if (contribution < 0) {
                                        positiveness = -1;
                                    } else {
                                        positiveness = 1;
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(GroundAction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        } else {
                            return false;
                        }

                    }
                }
            }
            return positiveness > 0;

        } else {
            throw new RuntimeException("At the moment only normalized expressions are considered " + comp);
        }
    }

    public RelState apply_with_generalized_interval_based_relaxation_copy(RelState s) {
        RelState s_copied = null;
        s_copied = s.clone();
        return apply_with_generalized_interval_based_relaxation(s_copied);
    }

    public RelState apply_with_generalized_interval_based_relaxation(RelState s) {
        return s.apply_with_generalized_interval_based_relaxation(this);
    }

    public boolean has_complex_preconditions() {

        if (this.getPreconditions() == null || this.getPreconditions().sons.isEmpty()) {
            return false;
        }

        for (Condition c : (Collection<Condition>) this.getPreconditions().sons) {
            if (c instanceof Comparison) {
                Comparison comp = (Comparison) c;
                if (comp.getLeft() instanceof ExtendedNormExpression) {
                    ExtendedNormExpression a = (ExtendedNormExpression) comp.getLeft();
                    for (ExtendedAddendum ad : a.summations) {
                        if (ad.bin != null) {
                            return true;
                        }
                    }

                }
                if (comp.getRight() instanceof ExtendedNormExpression) {
                    ExtendedNormExpression a = (ExtendedNormExpression) comp.getRight();
                    for (ExtendedAddendum ad : a.summations) {
                        if (ad.bin != null) {
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    public boolean has_exponential_or_nl_effects_asymptotic_effects() {
        if (this.getNumericEffects() == null || this.getNumericEffects().sons.isEmpty()) {
            return false;
        }
        for (NumEffect neff : this.getNumericEffectsAsCollection()) {
            NumFluent nf = neff.getFluentAffected();

            if (neff.getRight() instanceof ExtendedNormExpression) {
                ExtendedNormExpression right = (ExtendedNormExpression) neff.getRight();
                for (ExtendedAddendum ad : right.summations) {
                    if (ad.bin != null) {
                        return true;
                    }
                    if (ad.f != null) {
                        if (ad.f.equals(nf)) {
                            if (ad.n != 1.0f || ad.n != .0f) {
                                return true;
                            }
                        }
                    }

                }
            }

        }
        return false;
    }

    public boolean has_additive_effects() {
        if (this.getNumericEffects() == null || this.getNumericEffects().sons.isEmpty()) {
            return false;
        }
        for (NumEffect neff : this.getNumericEffectsAsCollection()) {
            if (neff.getOperator().equals("increase") || neff.getOperator().equals("decrease")) {
                return true;
            }

        }
        return false;

    }

    public boolean delete_own_preconditions() {//to do

//        AndCond
        return false;
    }

    public PostCondition getAdder(Predicate aThis) {
        for (PostCondition eff : (Collection<PostCondition>) this.addList.sons) {
            if (eff.equals(aThis)) {
                return eff;
            }
        }
        for (PostCondition eff : (Collection<PostCondition>) this.cond_effects.sons) {
            ConditionalEffect c_eff = (ConditionalEffect) eff;
//            Predicate p = (Predicate)c_eff.effect;
            if (c_eff.effect instanceof AndCond) {
                AndCond and = (AndCond) c_eff.effect;
                if (and.getInvolvedPredicates().contains(aThis)) {
                    return c_eff;
                }
            }
            if (c_eff.effect.equals(aThis)) {
                return c_eff;
            }
        }
        return null;
    }

    public PostCondition getDeleter(Predicate aThis) {
        for (PostCondition eff : (Collection<PostCondition>) this.delList.sons) {
            NotCond n_eff = (NotCond) eff;
            Predicate p = (Predicate) n_eff.getSon();
            if (p.equals(aThis)) {
                return p;
            }
        }
        for (PostCondition eff : (Collection<PostCondition>) this.cond_effects.sons) {
            ConditionalEffect c_eff = (ConditionalEffect) eff;
            //for now single condition effect. Extend to andcond
            if (c_eff.effect instanceof AndCond) {
                AndCond and = (AndCond) c_eff.effect;
                for (Condition c : (Collection<Condition>) and.sons) {
                    if (c instanceof NotCond) {
                        NotCond n_eff = (NotCond) c;
                        Predicate p = (Predicate) n_eff.getSon();
//                System.out.println("DEBUG: Comparing: "+p+" with "+aThis);
                        if (p.equals(aThis)) {
//                    System.out.println("DEBUG: Equal");
                            return c_eff;
                        }
                    }
                }
            }
            if (c_eff.effect instanceof NotCond) {
                NotCond n_eff = (NotCond) c_eff.effect;
                Predicate p = (Predicate) n_eff.getSon();
//                System.out.println("DEBUG: Comparing: "+p+" with "+aThis);
                if (p.equals(aThis)) {
//                    System.out.println("DEBUG: Equal");
                    return c_eff;
                }
            }

        }
        return null;
    }

    void subst_predicate(Predicate p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OrCond getAdders(Predicate aThis) {
        OrCond or = new OrCond();
        or.addConditions(Predicate.createPredicate(Predicate.trueFalse.FALSE));

        if (this.addList instanceof AndCond) {
            AndCond and = this.addList;
            Condition c = and.achieve(aThis);
            if (c != null) {
                or.addConditions(c);
            }
        }
        for (ConditionalEffect c : (Collection<ConditionalEffect>) this.cond_effects.sons) {
            Condition c1 = c.achieve(aThis);
            if (c1 != null) {
                or.addConditions(c1);
            }
        }
        return or;
    }

    public OrCond getDels(Predicate aThis) {
        OrCond or = new OrCond();
        or.addConditions(Predicate.createPredicate(Predicate.trueFalse.FALSE));
        if (this.delList instanceof AndCond) {
            AndCond and = this.delList;
            Condition c = and.delete(aThis);
            if (c != null) {
                or.addConditions(c);
            }
        }
        for (ConditionalEffect c : (Collection<ConditionalEffect>) this.cond_effects.sons) {
            Condition c1 = c.delete(aThis);
            if (c1 != null) {
                or.addConditions(c1);
            }
        }
        return or;
    }

    public Double getStaticContribution(PDDLState s_0, Condition c) {

        if (c instanceof Predicate) {
            if (this.weakAchiever((Predicate) c)) {
                return 1d;
            } else {
                return 0d;
            }
        } else {
            return this.getStaticContribution((Comparison) c);
        }

    }

    public Float getContribution(PDDLState s_0, Comparison comp) {

        Float b = comp.eval_affected(s_0, this);
        if (b <= 0) {
            return Float.MAX_VALUE; //the action is not a possible achiever
        }
        return b;
    }

    public Double getStaticContribution(Comparison comp) {
        Double positiveness = 0d;

        if (comp.getLeft() instanceof ExtendedNormExpression) {
            ExtendedNormExpression left = (ExtendedNormExpression) comp.getLeft();
            for (ExtendedAddendum ad : left.summations) {
                if (ad.f != null) {
                    for (NumEffect ne : this.getNumericEffectsAsCollection()) {
                        if (!ne.getFluentAffected().equals(ad.f)) {
                            continue;
                        }

                        if (ne.getInvolvedNumericFluents().isEmpty()) {
                            ExtendedNormExpression rhs = (ExtendedNormExpression) ne.getRight();

                            if (!rhs.linear) {
                                return Double.MAX_VALUE;
                            }
                            if (ne.getOperator().equals("increase")) {
                                try {
                                    positiveness += rhs.getNumber() * ad.n;
                                } catch (Exception ex) {
                                    Logger.getLogger(GroundAction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (ne.getOperator().equals("decrease")) {
                                try {
                                    positiveness += (-1) * rhs.getNumber() * ad.n;
                                } catch (Exception ex) {
                                    Logger.getLogger(GroundAction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (ne.getOperator().equals("assign")) {
                                try {
                                    positiveness += rhs.getNumber() * ad.n;//Treat this as it was an increase effect
                                } catch (Exception ex) {
                                    Logger.getLogger(GroundAction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        } else {
                            return Double.MAX_VALUE;
                        }

                    }
                }
            }
            return positiveness;
        }
        return positiveness;

    }

    public boolean hasApplicableEffects(PDDLState s) {
        for (NumEffect e : this.getNumericEffectsAsCollection()) {
            if (e.getOperator().equalsIgnoreCase("increase") || e.getOperator().equalsIgnoreCase("decrease")) {

                if (s.fluentValue(e.getFluentAffected()) == Double.NaN) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Pair<Condition, Float>> getSdac(PDDLState init, Metric metric) {
        if (this.sdac == null) {
            this.sdac = new ArrayList<>();
            if (metric != null && metric.getMetExpr() != null) {
                this.forcedGenerateAffectedNumFluents();
                ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                //first numeric effect normal
                Float exprImpact = 0f;
                if (this.numericEffects != null) {
                    AndCond numericEffects1 = this.numericEffects;

                    for (NumEffect effNum : (Collection<NumEffect>) numericEffects1.sons) {
                        for (ExtendedAddendum ad : expr.summations) {
                            if (ad.f != null) {
                                exprImpact += ad.n.floatValue() * this.getExprImpact(init, effNum, ad.f);
                            }
                        }
                    }
                }
                if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                        || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                    Predicate truePredicate = Predicate.createPredicate(Predicate.trueFalse.TRUE);
                    this.sdac.add(Pair.of(truePredicate, exprImpact));
                }
                for (ConditionalEffect cond : (Collection<ConditionalEffect>) this.cond_effects.sons) {
                    AndCond effect = (AndCond) cond.effect;
                    exprImpact = 0f;
                    for (Object innerCondition : (Collection<Object>) effect.sons) {
                        if (innerCondition instanceof NumEffect) {
                            NumEffect effNum = (NumEffect) innerCondition;
                            for (ExtendedAddendum ad : expr.summations) {
                                if (ad.f != null) {
                                    exprImpact += ad.n.floatValue() * this.getExprImpact(init, effNum, ad.f);
                                }
                            }
                        }
                    }
                    if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                            || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                        this.sdac.add(Pair.of(cond.activation_condition, exprImpact));
                    }
                }

            }
        }
        return this.sdac;
    }

    public boolean has_pseudo_numeric_effect_on(Condition c) {
        if (c instanceof Predicate) {
            return false;
        }
        Comparison comp = (Comparison) c;
        for (NumEffect eff : this.getNumericEffectsAsCollection()) {
            if (eff.isPseudo_num_effect()) {
                if (comp.getLeft().involve(eff.getFluentAffected())) {
                    return true;
                }
            }

        }
        return false;

    }

    public GroundAction buildMacroInProgression (GroundAction b, PddlDomain pd, PddlProblem pp) throws Exception {
        if (this.name == null) {

            return (GroundAction) b.clone();
        }
        GroundAction a = this;

        GroundAction ab = new GroundAction(a.name + "_" + b.name, pp.actions.size());
        if (a.getPrimitives().isEmpty()) {
            a.getPrimitives().add(a);
        }
        ab.setPrimitives((ArrayList) a.getPrimitives().clone());
        if (b.isMacro) {
            ab.getPrimitives().addAll(b.getPrimitives());
        }

        ab.getPrimitives().add(b);

        ab.setParameters((ParametersAsTerms) a.getParameters().clone());
        ab.getParameters().addALLNewObjects(b.getParameters());

        //System.out.println(b.pddlEffects());
        //      System.out.println(a.pddlEffects());
        ab.setPreconditions((ComplexCondition) regress(b, a));
        progress(a, b, ab);
        ab.setIsMacro(true);
//        System.out.println("Da dentro l'azione..."+ab);
        ab.simplifyModel(pd, pp);

        if (ab.getName() == null) {
            System.out.println(a + " " + b + " created a null action");
        }

        pp.actions.add(ab);

        return ab;
    }

    public float getActionCost (State s, Metric m) {
        if (m == null || m.getMetExpr() == null ){
            return 1f;
        }
//        List<Pair<Condition, Float>> sdac1 = this.getSdac((PDDLState) s, m);
//        if (sdac1 == null){
//            return 1f;
//        }
        List<Pair<Condition, Float>> sdac1 = this.getSdac((PDDLState) s, m);
        float impact = 0f;
        for (Pair<Condition,Float> ele : sdac1){
            if (ele.getLeft().isSatisfied(s)){
                impact+=ele.getRight();
            }
        }
        return impact;
    }

    @Override
        public int hashCode ( ) {
        if (id != -1)
            return id;
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.parameters_as_terms);
        hash = 97 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
        public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final GroundAction other = (GroundAction) obj;

        if (id != -1 && other.getId() != -1) {
            return id == other.getId();
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.parameters_as_terms, other.parameters_as_terms)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    public boolean equalsNoId (Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final GroundAction other = (GroundAction) obj;

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.parameters_as_terms, other.parameters_as_terms)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }


    public Boolean weakAchiever (Terminal t) {
        if (t.isValid())
            return true;
        if (t.isUnsatisfiable())
            return false;
        if (t instanceof Comparison) {
            Set<NumFluent> involvedFluents = t.getInvolvedFluents();
            for (NumFluent nf : this.getNumericFluentAffected()) {
                if (involvedFluents.contains(nf))
                    return null;
            }
            return false;
        }
        if (t instanceof Predicate) {
            return this.addList.getTerminalConditions().contains(t);
        }
        if (t instanceof NotCond) {
            return this.delete((Predicate) ((NotCond) t).getSon());
        }
        throw new RuntimeException("Unsupported instance of terminal: " + t.getClass());
    }


    //Here Assumption on calling this method only over Terminal conditions
    public boolean weakThreat (Condition c) {
        if (!(c instanceof Terminal)) {
            throw new RuntimeException("Only terminals supported for threat detection");
        }
        if (c instanceof Predicate) {
            if (this.delete((Predicate) c))
                return true;
        } else if (c instanceof NotCond) {
            Predicate son = (Predicate) ((NotCond) c).getSon();
            if (this.weakAchiever(son)) {
                return true;
            }
        } else if (c instanceof Comparison) {
            Set<NumFluent> involvedFluents = c.getInvolvedFluents();
            for (NumFluent nf : this.getNumericFluentAffected()) {
                if (involvedFluents.contains(nf))
                    return true;
            }

        }
        return false;
    }

    public void setActionCost(int i) {
        this.sdac = Collections.singletonList(Pair.of(Predicate.createPredicate(Predicate.trueFalse.TRUE), (float)i));
    }


}
