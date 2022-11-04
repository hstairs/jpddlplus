/*
 * Copyright (C) 2017 Enrico Scala.
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
package com.hstairs.ppmajal.conditions;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.problem.PDDLProblem;
import java.util.*;

/**
 * @author Enrico Scala
 */
public abstract class ComplexCondition extends Condition {
    final public Object[] sons; //used by formula conditions as AndCond and OrCond. Each son is another condition involved in the formula

    public ComplexCondition (Collection input ) {
        sons = input.toArray();
    }
    
    protected ComplexCondition(Object[] input){
        this.sons = input;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.sons);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComplexCondition other = (ComplexCondition) obj;
        if (!Objects.equals(this.sons, other.sons)) {
            return false;
        }
        return true;
    }


    @Override
    public final Collection<BoolPredicate> getInvolvedPredicates ( ) {
        Collection<BoolPredicate> ret = new LinkedHashSet();
        //from here it can only be an AndCond or a Or. Other cases are not instance of this
        if (this.sons != null) {
            for (Object c : this.sons) {
                if (c instanceof Condition) {
                    ret.addAll(((Condition) c).getInvolvedPredicates());
                }
            }
        }
        return ret;
    }

    @Override
    abstract public Condition unifyVariablesReferences (PDDLProblem p);
    
    protected Collection __unifyVariablesReferences(PDDLProblem p){
        Collection ret = new LinkedHashSet();
        for (Object c : this.sons) {
            if (c instanceof NumEffect) {
                NumEffect neff = (NumEffect) c;
                ret.add(neff.unifyVariablesReferences(p));
            } else {
                Condition cond = (Condition) c;
                ret.add(cond.unifyVariablesReferences(p));
            }
        }
        return ret;
    }



//    /**
//     * @param substitution
//     */
//    @Override
//    public void changeVar (Map substitution) {
//        for (Object o : sons) {
//            if (o instanceof NumEffect) {
//                NumEffect el = (NumEffect) o;
//                el.changeVar(substitution);
//            } else {
//                Condition el = (Condition) o;
//                el.changeVar(substitution);
//            }
//        }
//    }

    @Override
    public void extendTerms (Variable v) {
        if (this.sons == null) {
            return;
        }
        for (final var c : sons) {
            ((Condition)c).extendTerms(v);
        }
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        Set<NumFluent> ret = new LinkedHashSet();
        if (this.sons != null) {
            for (Object o : this.sons) {
                if (o instanceof NumFluent) {
                    ret.add((NumFluent) o);
                } else if (o instanceof Condition) {
                    Condition c = (Condition) o;
                    if (c.getInvolvedFluents() != null) {
                        ret.addAll(c.getInvolvedFluents());
                    }
                } else if (o instanceof NumEffect) {
                    NumEffect c = (NumEffect) o;
                    if (c.getInvolvedFluents() != null) {
                        ret.addAll(c.getInvolvedFluents());
                    }
                } else {
                    System.out.println("Error in getting involved fluents");
                }
            }
        }
        return ret;
    }


    @Override
    public Set<Condition> getTerminalConditions ( ) {
        Set ret;
        if (this.sons == null) {
            return new LinkedHashSet();
        }
        ret = Sets.newLinkedHashSetWithExpectedSize(this.sons.length);
        for (Object c : this.sons) {
            ret.addAll(((Condition)c).getTerminalConditions());
        }
        return ret;
    }


    @Override
    public List<Condition> getTerminalConditionsInArray ( ) {
        ArrayList ret = new ArrayList();
        if (this.sons == null) {
            return Collections.emptyList();
        }
        for (var c : this.sons) {
            ret.addAll(((Condition)c).getTerminalConditionsInArray());
        }
        return ret;
    }

    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        Collection ret = new HashSet();
        //System.out.println(this.toString());
        for (Object o : sons) {
            final Object groundedO;
            if (o instanceof NumEffect) {
                NumEffect el = (NumEffect) o;
                groundedO = el.ground(substitution, po);
            } else {
                Condition el = (Condition) o;
                //System.out.println(el);
                groundedO = el.ground(substitution, po);
            }
            if (groundedO instanceof AndCond) {
                addAll(ret,((AndCond) groundedO).sons);
            } else {
                ret.add(groundedO);
            }
        }
        return new AndCond(ret);
    }


    /**
     * @return the freeVarSemantic
     */
    public boolean isFreeVarSemantic ( ) {
        return freeVarSemantic;
    }


    protected void sonHasIncorrectType (Object son) {
        System.out.println("Effect " + son + " is not valid. Its class is" + son.getClass() + ".  Please revise your action model.");
        System.exit(-1);
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        if (this.sons != null) {
            for (Object o : this.sons) {
                if (o instanceof Condition) {
                    Condition c = (Condition) o;
                    c.storeInvolvedVariables(vars);
                } else if (o instanceof NumEffect) {
                    NumEffect c = (NumEffect) o;
                    if (c.getInvolvedVariables() != null) {
                        c.storeInvolvedVariables(vars);
                    }
                } else {
                    System.out.println("Error in getting involved variables");
                }
            }
        }
    }



    @Override
    public boolean involve (Condition c) {
        for (Object c1 :  this.sons) {
            if (((Condition)c1).involve(c)) {
                return true;
            }
        }
        return false;
    }

    
    protected Collection cloneSons() {
        Collection ret = new LinkedHashSet();

        for (Object o : this.sons) {
            if (o instanceof AndCond) {
                AndCond a = (AndCond) o;
                ret.add(a.clone());
            } else if (o instanceof NotCond) {
                NotCond a = (NotCond) o;
                ret.add(a.clone());
            } else if (o instanceof OrCond) {
                OrCond a = (OrCond) o;
                ret.add(a.clone());
            } else if (o instanceof BoolPredicate) {
                BoolPredicate a = (BoolPredicate) o;
                ret.add(a.clone());
            } else if (o instanceof Comparison) {
                Comparison a = (Comparison) o;
                ret.add(a.clone());
            } else if (o instanceof NumEffect) {
                NumEffect a = (NumEffect) o;
                ret.add(a.clone());
            } else if (o instanceof ConditionalEffect) {
                ConditionalEffect a = (ConditionalEffect) o;
                ret.add(a.clone());
            } else if (o instanceof ForAll) {
                ForAll a = (ForAll) o;
                ret.add(a.clone());
            }
        }
        return ret;
    }


}
