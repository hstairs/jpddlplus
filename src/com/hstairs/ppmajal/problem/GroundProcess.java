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

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroundProcess extends GroundAction {

    private double delta;

    public GroundProcess (String name, int id) {
        super(name, id);
    }

    public boolean isActive (State s) {
        return this.isApplicable(s);
    }

    @Override
    public Object clone ( ) {
        GroundProcess ret = new GroundProcess(name,this.id);
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

//    
//    
//    public PDDLState apply(PDDLState s_in, int time) {
//
//        PDDLState s = null;
//        s = s_in.clone();
//
//        AndCond del = delList;
//        if (del != null) {
//            del.apply(s);
//        }
//        AndCond add = addList;
//        if (add != null) {
//            add.apply(s);
//        }
//
//        AndCond c = this.getNumericEffects();
//        if (c != null) {
//            ArrayList temporaryMod = new ArrayList();
//            HashMap fun2numb = new HashMap();
//            for (Object o : c.sons) {
//                NumEffect all = (NumEffect) o;
//                NumFluent f = all.getFluentAffected();
//                Double newN = null;
//
//                Double rValue;
//                if (all.getRhs().eval(s) == null) {
//                    newN = null;
//                } else {
//                    rValue = all.getRhs().eval(s);
//                    if (rValue == null) {
//                        System.out.println("Trying to applying an action with invalid effects!!");
//                        System.out.println(this);
//                        System.exit(-1);
//                    }
//                    if (all.getOperator().equals("increase")) {
//                        if (s.fluentValue(f) == null) {
//                            newN = null;
//                        } else {
//                            newN = s.fluentValue(f) + rValue;
//                        }
//                    } else if (all.getOperator().equals("decrease")) {
//                        //                    System.out.print("Valore di " + f);
//                        //                    System.out.println(" :"+ s.functionValue(f).getNumber());
//                        if (s.fluentValue(f) == null) {
//                            newN = null;
//                        } else {
//                            newN = s.fluentValue(f) - rValue;
//                        }
//                    } else if (all.getOperator().equals("assign")) {
//                        //System.out.println("================ASSIGN===========");
//                        newN = rValue;
//                    }
//                }
//                temporaryMod.add(f);
//                fun2numb.put(f, newN);
//            }
//
//            for (Object o : temporaryMod) {
//                NumFluent f = (NumFluent) o;
//                s.setNumFluent(f,(Double) fun2numb.get(f));
//            }
//
//        }
//        return s;
//
//    }

    public void add_numeric_effect (NumEffect eff) {

        Iterator<NumEffect> it = this.numericEffects.sons.iterator();
        Collection<NumEffect> to_add = new LinkedHashSet();
        while (it.hasNext()) {
            NumEffect int_eff = it.next();
            if (int_eff.getFluentAffected().equals(eff.getFluentAffected())) {
                if (eff.getOperator().equals(int_eff.getOperator())) {
                    try {
//                        System.out.println("DEBUG:"+int_eff);
//                        System.out.println("DEBUG:"+int_eff.getRhs());
                        ExtendedNormExpression expr = (ExtendedNormExpression) int_eff.getRight();
                        ExtendedNormExpression res = expr.sum((ExtendedNormExpression) eff.getRight());
                        NumEffect n_effect = new NumEffect(eff.getOperator());
                        n_effect.setFluentAffected(int_eff.getFluentAffected());
                        n_effect.setRight(res);
                        to_add.add(n_effect);
                        it.remove();
                    } catch (Exception ex) {
                        Logger.getLogger(GroundProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (int_eff.getOperator().equals("increase")) {
                    try {
                        ExtendedNormExpression expr = (ExtendedNormExpression) int_eff.getRight();
                        ExtendedNormExpression res = expr.minus((ExtendedNormExpression) eff.getRight());
                        NumEffect n_effect = new NumEffect("increase");
                        n_effect.setFluentAffected(int_eff.getFluentAffected());
                        n_effect.setRight(res);
                        to_add.add(n_effect);
                        it.remove();
                    } catch (Exception ex) {
                        Logger.getLogger(GroundProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        ExtendedNormExpression expr = (ExtendedNormExpression) eff.getRight();
                        ExtendedNormExpression res = expr.minus((ExtendedNormExpression) int_eff.getRight());
                        NumEffect n_effect = new NumEffect("increase");
                        n_effect.setFluentAffected(int_eff.getFluentAffected());
                        n_effect.setRight(res);
                        to_add.add(n_effect);
                        it.remove();
                    } catch (Exception ex) {
                        Logger.getLogger(GroundProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.numericEffects.sons.addAll(to_add);
                return;
            }
        }
        //In case it does not found another effect like that, add just this one (this is not checking for the particular type of the effects, so you can potentially
        //use processes to represent sponteneous, more general evolution of the environment
        this.numericEffects.sons.add(eff);

    }

    public void addDelta (double delta) {
        this.setDelta(delta);
    }

    @Override
    public State apply (State s) {
        s = (PDDLState) super.apply(s);
        ((PDDLState) s).time += getDelta();
        //this.time = ((PDDLState)s).time;
//        System.out.println("DEBUG: Subst within action application:"+subst);
        return s;
    }

    /**
     * @return the executionDelta
     */
    public double getDelta ( ) {
        return delta;
    }

    /**
     * @param delta the executionDelta to set
     */
    public void setDelta (double delta) {
        this.delta = delta;
    }

}
