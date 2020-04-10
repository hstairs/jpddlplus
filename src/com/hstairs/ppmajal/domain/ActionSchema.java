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
package com.hstairs.ppmajal.domain;

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.problem.PddlProblem;
import com.hstairs.ppmajal.propositionalFactory.Grounder;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author enrico
 */
public class ActionSchema extends PDDLGenericAction {

    public ActionSchema ( ) {
        super();
        parameters = new SchemaParameters();
//        numericFluentAffected = new HashSet();
        this.addList = new AndCond();
        this.delList = new AndCond();
        this.numericEffects = new AndCond();
        this.preconditions = new AndCond();
        this.cond_effects = new AndCond();
        this.forall = new AndCond();

    }

    public void addParameter (Variable o) {

        parameters.add(o);

    }

    public String pddlPrintWithExtraObject ( ) {
        String ret = "(:action " + this.name + "\n";

        ret += ":parameters " + this.parameters + "\n";
        if (this.getPreconditions() != null && !this.getPreconditions().sons.isEmpty()) {
            ret += ":precondition (forall (?interpr - interpretation)" + this.getPreconditions().pddlPrintWithExtraObject() + ")\n";
        }
        ret += ":effect " + this.pddlEffectsWithExtraObject();

        return ret + ")";
    }

    public GroundAction ground (Map substitution, PDDLObjects po, PddlProblem pp) {
        GroundAction ret = new GroundAction(this.name,pp.getFreshActionId());
        ParametersAsTerms input = new ParametersAsTerms();
        for (Object o : parameters) {
            Variable el = (Variable) o;
            PDDLObject t = (PDDLObject) substitution.get(el);
            input.add(t);
        }
        ret.setParameters(input);

        if (ret.forall != null) {//Kind of special case for now
            AndCond temp = (AndCond) ret.forall.ground(substitution, po);
            this.create_effects_by_cases(temp);
        }

        ret.setNumericEffects((AndCond) this.numericEffects.ground(substitution, po));
        ret.setAddList((AndCond) this.addList.ground(substitution, po));
        ret.setDelList((AndCond) this.delList.ground(substitution, po));
        ret.setPreconditions((ComplexCondition) this.preconditions.ground(substitution, po));
        ret.cond_effects = (AndCond) this.cond_effects.ground(substitution, po);

        return ret;

    }


    public GroundAction ground (ParametersAsTerms par, PDDLObjects po, PddlProblem pp) {
        GroundAction ret = new GroundAction(this.name, pp.getFreshActionId());
        int i = 0;
        Grounder g = new Grounder();
        Map substitution = g.obtain_sub_from_instance(parameters, par);
        ret.setParameters(par);

        if (this.forall != null) {//Kind of special case for now
            AndCond temp = (AndCond) this.forall.ground(substitution, po);
            if (po != null) {
                ret.create_effects_by_cases(temp);
            } else {
                ret.forall = temp;
            }

        }

//        System.out.println(this);
        if (numericEffects != null || !numericEffects.sons.isEmpty()) {
            //System.out.println(this);
            ret.numericEffects.sons.addAll(((AndCond) this.numericEffects.ground(substitution, po)).sons);
//            ret.setNumericEffects(this.numericEffects.ground(substitution, po));
        }
        if (addList != null) {
            ret.addList.sons.addAll(((AndCond) this.addList.ground(substitution, po)).sons);
//            ret.setAddList(this.addList.ground(substitution, po));
        }
        if (delList != null) {
            ret.delList.sons.addAll(((AndCond) this.delList.ground(substitution, po)).sons);

//            ret.setDelList(this.delList.ground(substitution, po));
        }
        if (preconditions != null) {
            ret.setPreconditions((ComplexCondition) this.preconditions.ground(substitution, po));
        }
        if (cond_effects != null) {
            ret.cond_effects.sons.addAll(((ComplexCondition) this.cond_effects.ground(substitution, po)).sons);
        }
        return ret;
    }

    public GroundAction fakeGround (PddlProblem pp) {
        GroundAction ret = new GroundAction(this.name,pp.getFreshActionId());
        ParametersAsTerms input = new ParametersAsTerms();

        ret.setParameters(input);
        ret.setPreconditions(preconditions);
        ret.setAddList(addList);
        ret.setDelList(delList);
        ret.setNumericEffects(numericEffects);
        ret.cond_effects = cond_effects;
        return ret;
    }

    @Override
    public String toString ( ) {
//        String parametri = "";
//        for (Object o : parameters) {
//            parametri = parametri.concat(o.toString()).concat(" ");
//        }
//        return "\n\nAction Name:" + this.name + " Parameters: " + parametri + "\nPre: " + this.preconditions + "\nEffetti positivi: " + this.getAddList() + "\nEffetti negativi: " + this.getDelList() + "\nNumeric Effects:  " + this.getNumericEffects();
//    }

        StringBuilder ret = new StringBuilder("(:action " + this.name + "\n");

        ret.append(":parameters " + this.parameters + "\n");
        ret.append(":precondition ");
        this.getPreconditions().pddlPrint(false, ret);
        ret.append("\n");
        //ret += ":effect " + this.pddlEffectsWithExtraObject();
        ret.append(":effect ");
        this.pddlEffects(ret);
        ret.append("\n)");
        return ret.toString();
    }


    protected void pddlEffects (StringBuilder input) {
        input.append("(and ");
        if (this.getAddList() != null) {
            for (Object o : this.getAddList().sons) {
                Predicate p = (Predicate) o;
                p.pddlPrint(false, input);
            }
        }
        if (this.getDelList() != null) {
            for (Object o : this.getDelList().sons) {
                NotCond p = (NotCond) o;
                p.pddlPrint(false, input);
            }
        }
        if (this.getNumericEffects() != null) {
            for (Object o : this.getNumericEffects().sons) {
                NumEffect nE = (NumEffect) o;
                nE.pddlPrint(false, input);

            }
        }
        if (this.cond_effects != null) {
            for (Object o : this.cond_effects.sons) {
                ConditionalEffect nE = (ConditionalEffect) o;
                nE.pddlPrint(false, input);

            }
        }

        if (this.forall != null) {
            for (Object o : this.forall.sons) {
                ForAll nE = (ForAll) o;
                nE.pddlPrint(false, input);
            }
        }
        input.append(")");
    }

    protected String pddlEffects ( ) {
        StringBuilder input = new StringBuilder();
        this.pddlEffects(input);
        return input.toString();
    }

    public Set getAbstractNumericFluentAffected ( ) {
        HashSet anfa = new HashSet();
        for (Object o : this.numericEffects.sons) {
            if (o instanceof AndCond) {
                AndCond a = (AndCond) o;
                for (Object o1 : a.sons) {
                    if (o1 instanceof NumEffect) {
                        NumEffect ne = (NumEffect) o1;
                        anfa.add(ne.getFluentAffected());
                    }
                }

            } else if (o instanceof NumEffect) {
                NumEffect ne = (NumEffect) o;
                anfa.add(ne.getFluentAffected());
            }
        }
        return anfa;
    }

    @Deprecated
    public ActionSchema append (ActionSchema as2, PddlDomain domain, PddlProblem problem) throws CloneNotSupportedException {

        if (this.name == null) {
            return (ActionSchema) as2.clone();
        }
        ActionSchema a = this;

        ActionSchema ab = new ActionSchema();
        ab.name = this.name + "_" + as2.name;

        ab.setParameters((SchemaParameters) a.parameters.clone());
        ab.parameters.mergeParameters(as2.parameters);

        ab.setPreconditions((ComplexCondition) this.regress(as2, a));
        this.progress(a, as2, ab);
        //ab.setIsMacro(true);
        //System.out.println("Da dentro l'azione..."+ab);
        //ab.simplifyModel(pd, pp);
        //ab.computeDistance(pd,pp,consideringNumericInformationInDistance);
        return ab;
    }

    public ActionSchema clone ( ) {
        ActionSchema res = new ActionSchema();
        res.parameters = this.parameters;
        res.preconditions = (ComplexCondition) this.preconditions.clone();
        res.addList = (AndCond) this.addList.clone();
        res.delList = (AndCond) this.delList.clone();
        res.cond_effects = (AndCond) this.cond_effects.clone();
        res.forall = (AndCond) this.forall.clone();
        res.numericEffects = (AndCond) this.numericEffects.clone();
        res.name = this.name;
        return res;

    }

    private void progress (ActionSchema a, ActionSchema b, ActionSchema ab) {
        /*Starting from what action a weakAchiever*/
        AndCond localAddList = (AndCond) a.addList.clone();

        /*remove those atoms which will be deleted afterwards*/
        localAddList.subtractElements(b.getDelList());

        /*atoms achieved by b*/
        localAddList.sons.addAll(b.getAddList().sons);

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
                System.out.println("TODO: redo the numeric affected fluents");
                System.exit(-1);
//                ab.getNumericFluentAffected().add(nf.getFluentAffected());
            }
        }
        if (a.getNumericEffects() != null) {
            for (Object o : a.getNumericEffects().sons) {
                NumEffect nf = (NumEffect) o;
                System.out.println("TODO: redo the numeric affected fluents");
                System.exit(-1);
//                if (!ab.getNumericFluentAffected().contains(nf.getFluentAffected())) {
//                    numEff.sons.add(o);
//                    ab.getNumericFluentAffected().add(nf.getFluentAffected());
//                }
            }
        }

        ab.setNumericEffects(numEff);
    }

    /**
     * @return the numericFluentAffected
     */
    private Condition regress (ActionSchema b, ActionSchema a) {
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
                sons2.add(Comparison.createComparison(c.getComparator(), c.getLeft().subst(a.getNumericEffects()), c.getRight().subst(a.getNumericEffects()),false));
            } else if (a.getDelList() != null) {
                if (a.getDelList().sons.contains(o1)) {
                    System.out.println("Error, " + a.name + " cannot be followed by " + b.name);
                    return null;
                }else{
                    sons2.add(o1);
                }
            }else{
                sons2.add(o1);
            }

        }

        result.sons = sons2;
        result.sons.addAll(a.getPreconditions().sons);

        //AndCond numericCondition = 
        return result;

    }

    private String pddlEffectsWithExtraObject ( ) {
        String ret = "(and ";
        if (this.getAddList() != null) {
            for (Object o : this.getAddList().sons) {
                Predicate p = (Predicate) o;
                ret += "(forall (?interpr - interpretation) " + p.pddlPrintWithExtraObject() + ")";
            }
        }
        if (this.getDelList() != null) {
            for (Object o : this.getDelList().sons) {
                NotCond p = (NotCond) o;
                ret += "(forall (?interpr - interpretation) " + p.pddlPrintWithExtraObject() + ")";
            }
        }
        if (this.getNumericEffects() != null) {
            for (Object o : this.getNumericEffects().sons) {
                NumEffect nE = (NumEffect) o;
                ret += "(forall (?interpr - interpretation) " + nE.pddlPrint(false) + ")";

            }
        }
        if (this.cond_effects != null) {
            for (Object o : this.cond_effects.sons) {
                ConditionalEffect cond_eff = (ConditionalEffect) o;
                ret += "(forall (?interpr - interpretation) " + cond_eff.pddlPrintWithExtraObject() + ")";

            }
        }

        return ret + ")";
    }

}
