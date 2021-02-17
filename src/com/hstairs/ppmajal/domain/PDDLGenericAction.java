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
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.EPddlProblem;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;

import java.util.*;

public abstract class PDDLGenericAction {

    public AndCond cond_effects;
    public Float time = null;
    public AndCond forall;
    protected String name;
    protected AndCond addList;
    protected AndCond delList;
    protected AndCond numericEffects;
    protected ComplexCondition preconditions;
    protected Object2BooleanMap numericFluentAffected;
    protected SchemaParameters parameters;

    /**
     * @return the addList
     */
    public AndCond getAddList ( ) {
        return addList;
    }

    /**
     * @param addList the addList to set
     */
    public void setAddList (AndCond addList) {
        this.addList = addList;
    }

    public SchemaParameters getPar ( ) {
        return parameters;
    }

    /**
     * @return the delList
     */
    public AndCond getDelList ( ) {
        return delList;
    }

    /**
     * @param delList the delList to set
     */
    public void setDelList (AndCond delList) {
        this.delList = delList;
    }

    public String getName ( ) {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * @return the numericEffects
     */
    public AndCond getNumericEffects ( ) {
        return numericEffects;
    }

    /**
     * @param numericEffects the numericEffects to set
     */
    public void setNumericEffects (AndCond numericEffects) {
        this.numericEffects = numericEffects;
    }

    /**
     * @return the preconditions
     */
    public ComplexCondition getPreconditions ( ) {
        return preconditions;
    }

    /**
     * @param preconditions the preconditions to set
     */
    public void setPreconditions (ComplexCondition preconditions) {
        this.preconditions = preconditions;
    }

    @Override
    public String toString ( ) {
        return "PDDLGenericAction{" + "name=" + name + ", parameters=" + parameters + ", time=" + time + '}';
    }


    /**
     * @return the parameters
     */
    /**
     * @param parameters the parameters to set
     */
    public void setParameters (SchemaParameters parameters) {
        this.parameters = parameters;
    }

    protected void push_not_to_terminals ( ) {
        ComplexCondition c = (ComplexCondition) this.getPreconditions().push_not_to_terminals();
        if (!(c instanceof AndCond)) {
            AndCond and = new AndCond();
            and.addConditions(c);
            this.setPreconditions(and);
        } else {
            this.setPreconditions(c);
        }
        if (this.cond_effects != null) {
            this.cond_effects.push_not_to_terminals();
        }

    }

    protected Collection<Predicate> getPropositionAffected ( ) {
        LinkedHashSet ret = new LinkedHashSet();
        if (this.addList != null) {
            ret.addAll(this.addList.getInvolvedPredicates());
        }
        if (this.cond_effects != null) {
            AndCond temp2 = this.cond_effects;
            for (Condition cEff : (Collection<Condition>) temp2.sons) {
                if (cEff instanceof ConditionalEffect) {
                    ConditionalEffect conditional = (ConditionalEffect) cEff;
                    Collection<Predicate> temp = ((AndCond) conditional.effect).getInvolvedPredicates();
                    for (Condition c : temp) {
                        if (c instanceof NotCond) {
                            NotCond nc = (NotCond) c;
                            ret.add(nc.getSon());
                        } else if (c instanceof Predicate) {
                            ret.add(c);
                        } else {
                            throw new RuntimeException("A conditional effect terminal conditions function does not work");
                        }
                    }
                } else {
                    throw new RuntimeException("Needs to fix the conditional effect");
                }
            }
        }

        if (this.delList != null) {
            if (this.delList instanceof ComplexCondition) {

                for (Condition c : (Collection<Condition>) this.delList.sons) {
                    if (c instanceof NotCond) {
                        NotCond nc = (NotCond) c;
                        ret.add(nc.getSon());
                    }
                }
            }
        }
        return ret;
    }

    public HashMap update_invariant_fluents (HashMap invariantFluents) {
        for (Object nf : this.getNumericFluentAffected()) {
            invariantFluents.put(nf, Boolean.FALSE);
        }
        for (Predicate p : this.getPropositionAffected()) {
            invariantFluents.put(p, Boolean.FALSE);
        }
        return invariantFluents;
    }


    public Collection<NumFluent> getNumericFluentAffected ( ) {
        this.generateAffectedNumFluents();
        return this.numericFluentAffected.keySet();
    }
    public Object2BooleanMap getNumericFluentAffectedAsMap ( ) {
        this.generateAffectedNumFluents();
        return this.numericFluentAffected;
    }

    public void forcedGenerateAffectedNumFluents ( ) {

        numericFluentAffected = new Object2BooleanOpenHashMap();
        AndCond num = this.getNumericEffects();
        if (num != null) {
            for (Object o : num.sons) {
                if (o instanceof NumEffect) {
                    NumEffect e = (NumEffect) o;
                    this.numericFluentAffected.put(e.getFluentAffected(), Boolean.TRUE);
                }
            }
        }
        if (this.cond_effects != null) {
            for (ConditionalEffect c_eff : (Collection<ConditionalEffect>) this.cond_effects.sons) {
                for (NumFluent nf : c_eff.affectedNumericFluents()) {
                    this.numericFluentAffected.put(nf, Boolean.TRUE);
                }
            }
        }
    }

    public void generateAffectedNumFluents ( ) {
        if (numericFluentAffected != null) {
            return;
        }
        numericFluentAffected = new Object2BooleanOpenHashMap();
        AndCond num = this.getNumericEffects();
        if (num != null) {
            for (Object o : num.sons) {
                if (o instanceof NumEffect) {
                    NumEffect e = (NumEffect) o;
                    this.numericFluentAffected.put(e.getFluentAffected(), true);
                }
            }
        }

        if (this.cond_effects != null) {
            for (ConditionalEffect c_eff : (Collection<ConditionalEffect>) this.cond_effects.sons) {
                for (NumFluent nf : c_eff.affectedNumericFluents()) {
                    this.numericFluentAffected.put(nf, true);
                }
            }
        }

    }

    public Collection<? extends NumFluent> getNumFluentsNecessaryForExecution ( ) {
        Set<NumFluent> ret = new HashSet();
        for (NumEffect neff : (Collection<NumEffect>) this.getNumericEffects().sons) {
            ret.addAll(neff.getRight().getInvolvedNumericFluents());
        }
        return ret;
    }

    public void create_effects_by_cases (PostCondition res) {
        if (res instanceof AndCond) {
            AndCond pc = (AndCond) res;
            for (Object o : pc.sons) {
                if (o instanceof Predicate) {
                    this.addList.sons.add(o);
                } else if (o instanceof NotCond) {
                    this.delList.sons.add(o);
                } else if (o instanceof NumEffect) {
                    this.numericEffects.sons.add(o);
                } else if (o instanceof ConditionalEffect) {
                    this.cond_effects.sons.add(o);
                } else if (o instanceof ForAll) {
                    this.forall.sons.add(o);
                }
            }
        } else if (res instanceof Predicate) {
            this.addList.sons.add(res);
        } else if (res instanceof NotCond) {
            this.delList.sons.add(res);
        } else if (res instanceof NumEffect) {
            this.numericEffects.sons.add(res);
        } else if (res instanceof ConditionalEffect) {
            this.cond_effects.sons.add(res);
        } else if (res instanceof ForAll) {
            this.forall.sons.add(res);
//            this.forall.sons.add(res);
        }
    }

    public Collection<NumFluent> getInvolvedNumFluents ( ) {
        Collection<NumFluent> ret = this.preconditions.getInvolvedFluents();
        ret.addAll(this.numericEffects.getInvolvedFluents());
        return ret;
    }

    public Collection<Predicate> getInvolvedPredicates ( ) {
        Collection<Predicate> ret = new LinkedHashSet();
        if (this.preconditions != null) {
            ret.addAll(this.preconditions.getInvolvedPredicates());
        }
        if (this.addList != null) {
            ret.addAll(this.addList.getInvolvedPredicates());
        }
        if (this.delList != null) {
            ret.addAll(this.delList.getInvolvedPredicates());
        }
        if (this.cond_effects != null) {
            ret.addAll(this.cond_effects.getInvolvedPredicates());
        }
        return ret;
    }

    public void unifyVariablesReferences (EPddlProblem p) {

        preconditions = (ComplexCondition) preconditions.unifyVariablesReferences(p);
        addList = (AndCond) addList.unifyVariablesReferences(p);
        delList = (AndCond) delList.unifyVariablesReferences(p);
        numericEffects = (AndCond) numericEffects.unifyVariablesReferences(p);
        cond_effects = (AndCond) cond_effects.unifyVariablesReferences(p);
    }

    @Override
    public int hashCode ( ) {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.parameters);
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
        final PDDLGenericAction other = (PDDLGenericAction) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.parameters, other.parameters)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }


}
