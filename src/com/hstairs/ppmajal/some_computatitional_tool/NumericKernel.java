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
import com.hstairs.ppmajal.conditions.ComplexCondition;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.RelState;

import java.util.*;

/**
 * @author Enrico Scala
 */
public class NumericKernel extends HashMap {

    private HashSet involvedFluents;

    /**
     *
     */
    public NumericKernel ( ) {
        super();
    }

    /**
     * @param pianoClonato
     * @param g
     */
    public void construct (SimplePlan sp, Condition g) {

        SimplePlan pianoClonato = (SimplePlan) sp.clone();
        Condition goal = g.clone();
        //HashMap kerns = new HashMap();
        this.put(pianoClonato.size(), goal.clone());

        for (int i = pianoClonato.size() - 1; i >= 0; i--) {
            GroundAction a = pianoClonato.get(i);
            goal = a.regress(goal);//TODO to verify...

            this.put(i, goal.clone());
        }
        for (Object o : this.values()) {
            Condition con = (Condition) o;
            con.normalize();
        }

    }

    public void constructAndComputeMaxDist (SimplePlan sp, ComplexCondition g, RelState numericFleuntsBoundaries) {

        SimplePlan pianoClonato = (SimplePlan) sp.clone();
        ComplexCondition goal = (ComplexCondition) g.clone();

        //HashMap kerns = new HashMap();
        this.put(pianoClonato.size(), goal);

        for (int i = pianoClonato.size() - 1; i >= 0; i--) {
            GroundAction a = pianoClonato.get(i);

            goal = (ComplexCondition) a.regressAndStoreFatherPointer(goal);
            this.put(i, goal);
        }
        for (Object o : this.values()) {
            Condition con = (Condition) o;
            con.normalize();

        }

        for (int i = pianoClonato.size(); i >= 0; i--) {
            ComplexCondition con = (ComplexCondition) this.get(i);
            //System.out.println(con);

            if (con instanceof AndCond) {
                for (Object o : con.sons) {
                    if (o instanceof Comparison) {
                        Comparison comp = (Comparison) o;

                        if ((comp.getRight() instanceof ExtendedNormExpression) && (comp.getLeft() instanceof ExtendedNormExpression)) {
                            ExtendedNormExpression lExpr = (ExtendedNormExpression) comp.getLeft();

                            Float num = new Float(0.0);
                            Float den = new Float(0.0);
                            if (comp.fatherFromRegression == null) {

                                comp.maxDist = maximizationBound(lExpr, numericFleuntsBoundaries);
//                                System.out.println(comp);
//                                System.out.println(comp.maxDist);
//
//                                for (ExtendedAddendum a : lExpr.summations) {
//                                    if (a.f == null) {
//                                        num += Math.abs(a.n.getNumber());
//                                    } else {
//                                        num += Math.max(Math.abs(a.n.getNumber() * numericFleuntsBoundaries.functionInfValue(a.f).getNumber()), Math.abs(a.n.getNumber() * numericFleuntsBoundaries.functionSupValue(a.f).getNumber()));
//                                        den += (float)Math.pow(a.n.getNumber(), 2);
//                                    }
//                                }
//                                System.out.println("Senza massimizzazione"+num);;///(float)Math.sqrt(den);
                                //System.out.println("Computing Maxdist done");
                            } else {
                                //System.out.println("UNO CHE HA IL PADRE C'E'");
                                comp.maxDist = comp.fatherFromRegression.maxDist;
                            }
                        } else {
                            System.out.println("Something is not normalized!!");
                            System.exit(-1);
                        }
                    }
                }

            }
        }

        for (int i = pianoClonato.size(); i >= 0; i--) {
            ComplexCondition con = (ComplexCondition) this.get(i);
            if (con instanceof AndCond) {
                for (Object o : con.sons) {
                    if (o instanceof Comparison) {
                        Comparison comp = (Comparison) o;
                        if (comp.maxDist == null) {
                            System.out.println(comp + " has no maxdist setted");
                            System.out.println(comp.fatherFromRegression + " : the father");
                            System.exit(-1);
                        }
                    }
                }
            }
        }
        //System.exit(-1);
    }

    /**
     * @param sp Simple Plan
     * @param g  the Goal condition
     */
    @Deprecated
    public void construct_old (SimplePlan sp, Condition g) throws Exception {

        Condition goal = g.clone();
        //HashMap kerns = new HashMap();
        this.put(sp.size(), goal.clone());

        for (int i = sp.size() - 1; i >= 0; i--) {
            GroundAction a = sp.get(i);
            oplus(a, (AndCond) goal);
            this.put(i, goal.clone());
        }

    }

    /**
     * @param a
     * @param con
     * @return
     */
    public AndCond oplus (GroundAction a, AndCond con) throws Exception {

        //AndCond result = (AndCond)con.clone();
        if (!a.normalized) {
            System.out.println("Action must be normalized");
            return null;
        } else {
            if (!((a.getPreconditions() instanceof AndCond))) {
                System.out.println("Only AND precondition at the moment");
                return null;
            }

            for (Object o1 : a.getAddList().sons) {
                con.sons.remove(o1);
            }

            Iterator it = con.sons.iterator();

            ArrayList toAdd = new ArrayList();
            while (it.hasNext()) {

                Object o = it.next();
                if (o instanceof Comparison) {
                    Comparison c = (Comparison) o;
                    if (c != null) {
                        ExtendedNormExpression left = (ExtendedNormExpression) c.getLeft();
                        ExtendedNormExpression right = (ExtendedNormExpression) c.getRight();
                        toAdd.add(Comparison.createComparison(c.getComparator(), left.subst(a.getNumericEffects()), right.subst(a.getNumericEffects()),false));
                    }
                    it.remove();

                }
            }
            for (Object o : toAdd) {
                con.sons.add(o);
            }

            for (Object o1 : a.getPreconditions().sons) {
                Condition c = (Condition) o1;
                con.sons.add(c);
            }
//            if(!verifyConditions(con))
//                System.out.println("Regression of: "+a.toEcoString()+" causes a not satisfiable precondition");

        }
        return con;
    }

    protected Float maximizationBound (ExtendedNormExpression lExpr, RelState numericFleuntsBoundaries) {

        Double b = 0d;
        ArrayList<ExtendedAddendum> variables = new ArrayList();
        for (Object o : lExpr.summations) {
            ExtendedAddendum add = (ExtendedAddendum) o;
            if (add.f == null) {
                b = add.n;
            } else {
                variables.add(add);
            }
        }

        if (variables.size() == 0) {
            return (float) 0.000000000000000000000001;
        }

        Float max = -Float.MIN_VALUE;
        int i = 0;
        while (i < Math.pow(2, variables.size())) {
            Double temp = 0d;
            String bit = Integer.toBinaryString(i);
            //System.out.println(bit);

            while (variables.size() > bit.length()) {
                bit = '0' + bit;
            }

//            for (int j = 0; j < bit.length();j++){
//                System.out.print("pos["+j+"]");
//                System.out.println(bit.charAt(j));
//            }
            System.out.println("Candidate:");
            for (int j = 0; j < variables.size(); j++) {
                if (bit.length() > j) {
                    if (bit.charAt(j) == '0') {
                        temp += variables.get(j).n * numericFleuntsBoundaries.functionInfValue(variables.get(j).f).getNumber();
                        //System.out.println("Inf:"+j);
                    } else {
                        temp += variables.get(j).n * numericFleuntsBoundaries.functionSupValue(variables.get(j).f).getNumber();
                        //System.out.println("sup:"+j);
                    }
                } else {
//                    System.out.println("inf:"+j);
                    temp += variables.get(j).n * numericFleuntsBoundaries.functionInfValue(variables.get(j).f).getNumber();
                }
            }
            if (Math.abs(temp + b) > max) {
                max = (float) Math.abs(temp + b);
            }
            i++;

        }
        return max;

    }

    public void computeMaxDistViaPlanBounds (SimplePlan sp, Condition g, HashMap higherNFValues, HashMap lowerNFValues) {

        for (int i = sp.size(); i >= 0; i--) {
            ComplexCondition con = (ComplexCondition) this.get(i);
            //System.out.println(con);

            if (con instanceof AndCond) {
                for (Object o : con.sons) {
                    if (o instanceof Comparison) {
                        Comparison comp = (Comparison) o;

                        if ((comp.getRight() instanceof ExtendedNormExpression) && (comp.getLeft() instanceof ExtendedNormExpression)) {
                            ExtendedNormExpression lExpr = (ExtendedNormExpression) comp.getLeft();

                            Float num = new Float(0.0);
                            Float den = new Float(0.0);
                            if (comp.fatherFromRegression == null) {

                                //System.out.println(lExpr);
                                comp.maxDist = maximizationBoundViaPlanBounds(lExpr, higherNFValues, lowerNFValues);
//                                System.out.println(comp);
//                                System.out.println(comp.maxDist);
//
//                                for (ExtendedAddendum a : lExpr.summations) {
//                                    if (a.f == null) {
//                                        num += Math.abs(a.n.getNumber());
//                                    } else {
//                                        num += Math.max(Math.abs(a.n.getNumber() * numericFleuntsBoundaries.functionInfValue(a.f).getNumber()), Math.abs(a.n.getNumber() * numericFleuntsBoundaries.functionSupValue(a.f).getNumber()));
//                                        den += (float)Math.pow(a.n.getNumber(), 2);
//                                    }
//                                }
//                                System.out.println("Senza massimizzazione"+num);;///(float)Math.sqrt(den);
                                //System.out.println("Computing Maxdist done");
                            } else {
                                //System.out.println("UNO CHE HA IL PADRE C'E'");
                                comp.maxDist = comp.fatherFromRegression.maxDist;
                            }
                        } else {
                            System.out.println("Something is not normalized!!");
                            System.exit(-1);
                        }
                    }
                }

            }
        }

        for (int i = sp.size(); i >= 0; i--) {
            ComplexCondition con = (ComplexCondition) this.get(i);
            if (con instanceof AndCond) {
                for (Object o : con.sons) {
                    if (o instanceof Comparison) {
                        Comparison comp = (Comparison) o;
                        if (comp.maxDist == null) {
                            System.out.println(comp + " has no maxdist setted");
                            System.out.println(comp.fatherFromRegression + " : the father");
                            System.exit(-1);
                        }
                    }
                }
            }
        }
    }

    protected Float maximizationBoundViaPlanBounds (ExtendedNormExpression lExpr, HashMap higherNFValues, HashMap lowerNFValues) {
        Double b = 0d;
        ArrayList<ExtendedAddendum> variables = new ArrayList();
        for (Object o : lExpr.summations) {
            ExtendedAddendum add = (ExtendedAddendum) o;
            if (add.f == null) {
                b = add.n;
            } else {
                variables.add(add);
            }
        }

        if (variables.size() == 0) {
            return (float) 0.000000000000000000000001;
        }

        Float max = -Float.MIN_VALUE;
        int i = 0;
        while (i < Math.pow(2, variables.size())) {
            Double temp = 0d;
            String bit = Integer.toBinaryString(i);
            //System.out.println(bit);

            while (variables.size() > bit.length()) {
                bit = '0' + bit;
            }

//            for (int j = 0; j < bit.length();j++){
//                System.out.print("pos["+j+"]");
//                System.out.println(bit.charAt(j));
//            }
//            System.out.println("Candidate:");
            for (int j = 0; j < variables.size(); j++) {
                if (bit.length() > j) {
                    if (bit.charAt(j) == '0') {
                        //System.out.println((Float)lowerNFValues.get(variables.get(j).f));

                        temp += variables.get(j).n * (Double) lowerNFValues.get(variables.get(j).f);
//                        System.out.println("Inf:"+j);
                    } else {
                        temp += variables.get(j).n * (Double) higherNFValues.get(variables.get(j).f);
//                        System.out.println("sup:"+j);
                    }
                } else {
//                    System.out.println("inf:"+j);
                    temp += variables.get(j).n * (Float) lowerNFValues.get(variables.get(j).f);
                }
            }
            if (Math.abs(temp + b) > max) {
                max = (float) Math.abs(temp + b);
            }
            i++;

        }
        return max;

    }

    public Set getInvolvedFluents ( ) {

        if (involvedFluents != null) {
            return involvedFluents;
        }

        involvedFluents = new HashSet();

        for (Object o : this.values()) {
            //System.out.println(o.getClass());
            AndCond kernel = (AndCond) o;
            for (Object o1 : kernel.sons) {
                if (o1 instanceof Comparison) {
                    Comparison c = (Comparison) o1;
                    involvedFluents.addAll(c.getLeft().getInvolvedNumericFluents());
                    involvedFluents.addAll(c.getRight().getInvolvedNumericFluents());
                }
            }
        }
        return involvedFluents;
    }

}
