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
import com.hstairs.ppmajal.problem.GroundProcess;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.problem.PddlProblem;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author enrico
 */
public class ProcessSchema extends PDDLGenericAction {

    //private SchemaParameters parameters;
    //private HashSet numericFluentAffected;
    public ProcessSchema ( ) {
        super();
        parameters = new SchemaParameters();
        this.addList = new AndCond();
        this.delList = new AndCond();
        this.numericEffects = new AndCond();
        this.preconditions = new AndCond();

    }

//
//    public Action ground(ArrayList terms){
//        
//        
//        substitution = new HashMap();
//        
//        
//    }

    /**
     * @return the parameters
     */
    public SchemaParameters getParameters ( ) {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters (SchemaParameters parameters) {
        this.parameters = parameters;
    }

    public void addParameter (Variable o) {

        parameters.add(o);

    }

    public GroundProcess ground (Map substitution, PDDLObjects po, PddlProblem pp) {
        GroundProcess ret = new GroundProcess(this.name,pp.getFreshActionId());
        ParametersAsTerms input = new ParametersAsTerms();
        for (Object o : parameters) {
            Variable el = (Variable) o;
            PDDLObject t = (PDDLObject) substitution.get(el);
            input.add(t);
        }
        ret.setParameters(input);

        ret.setNumericEffects((AndCond) this.numericEffects.ground(substitution, po));
        ret.setAddList((AndCond) this.addList.ground(substitution, po));
        ret.setDelList((AndCond) this.delList.ground(substitution, po));
        ret.setPreconditions((ComplexCondition) this.preconditions.ground(substitution, po));

        return ret;

    }

    public GroundProcess ground (Map substitution, int c, PddlProblem pp) {
        GroundProcess ret = new GroundProcess(this.name,pp.getFreshActionId());
        ParametersAsTerms input = new ParametersAsTerms();
        for (Object o : parameters) {
            Variable el = (Variable) o;
            PDDLObject t = (PDDLObject) substitution.get(el);
            input.add(t);
        }
        ret.setParameters(input);

        ret.setNumericEffects((AndCond) this.numericEffects.ground(substitution, c++));
        ret.setAddList((AndCond) this.addList.ground(substitution, c++));
        ret.setDelList((AndCond) this.delList.ground(substitution, c++));
        ret.setPreconditions((ComplexCondition) this.preconditions.ground(substitution, c++));
        return ret;
    }

    public GroundProcess ground (ParametersAsTerms par, PDDLObjects po, PddlProblem pp) {
        GroundProcess ret = new GroundProcess(this.name,pp.getFreshActionId());
        ParametersAsTerms input = new ParametersAsTerms();
        int i = 0;

        Map substitution = new HashMap();
        for (Object o : parameters) {
            Variable el = (Variable) o;
            substitution.put(el, par.get(i));
            PDDLObject t = (PDDLObject) substitution.get(el);
            i++;
        }
        ret.setParameters(par);

        //System.out.println(this);
        if (numericEffects != null || !numericEffects.sons.isEmpty()) {
            //System.out.println(this);
            ret.setNumericEffects((AndCond) this.numericEffects.ground(substitution, po));
        }
        if (addList != null) {
            ret.setAddList((AndCond) this.addList.ground(substitution, po));
        }
        if (delList != null) {
            ret.setDelList((AndCond) this.delList.ground(substitution, po));
        }
        if (preconditions != null) {
            ret.setPreconditions((ComplexCondition) this.preconditions.ground(substitution, po));
        }

        return ret;
    }

    public GroundProcess ground (  PddlProblem pp) {
        GroundProcess ret = new GroundProcess(this.name, pp.getFreshActionId());
        ParametersAsTerms input = new ParametersAsTerms();

        ret.setParameters(input);
        ret.setPreconditions(preconditions);
        ret.setAddList(addList);
        ret.setDelList(delList);
        ret.setNumericEffects(numericEffects);

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

        String ret = "(:process " + this.name + "\n";

        ret += ":parameters " + this.getParameters() + "\n";
        ret += ":precondition " + this.getPreconditions().pddlPrint(false) + "\n";
        ret += ":effect " + this.pddlEffects();

        return ret + ")";
    }

    private String pddlEffects ( ) {
        String ret = "(and ";
        if (this.getAddList() != null) {
            for (Object o : this.getAddList().sons) {
                Predicate p = (Predicate) o;
                ret += p.pddlPrint(false);
            }
        }
        if (this.getDelList() != null) {
            for (Object o : this.getDelList().sons) {
                NotCond p = (NotCond) o;
                ret += p.pddlPrint(false);
            }
        }
        if (this.getNumericEffects() != null) {
            for (Object o : this.getNumericEffects().sons) {
                NumEffect nE = (NumEffect) o;
                ret += nE.pddlPrint(false);

            }
        }

        return ret + ")";
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
    public ProcessSchema append (ProcessSchema as2, PddlDomain domain, PddlProblem problem) throws CloneNotSupportedException {

        if (this.name == null) {
            return (ProcessSchema) as2.clone();
        }
        ProcessSchema a = this;

        ProcessSchema ab = new ProcessSchema();
        ab.name = this.name + "_" + as2.name;

        ab.setParameters((SchemaParameters) a.getParameters().clone());
        ab.getParameters().mergeParameters(as2.getParameters());

        ab.setPreconditions((ComplexCondition) this.regress(as2, a));
        this.progress(a, as2, ab);
        //ab.setIsMacro(true);
        //System.out.println("Da dentro l'azione..."+ab);
        //ab.simplifyModel(pd, pp);
        //ab.computeDistance(pd,pp,consideringNumericInformationInDistance);
        return ab;
    }

    private void progress (ProcessSchema a, ProcessSchema b, ProcessSchema ab) {
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
    private Condition regress (ProcessSchema b, ProcessSchema a) {
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


}
