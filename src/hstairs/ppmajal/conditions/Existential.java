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
package com.hstairs.ppmajal.conditions;

import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.domain.SchemaParameters;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.propositionalFactory.Grounder;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author enrico
 */
public class Existential extends ComplexCondition {

    final private SchemaParameters parameters;

    public Existential (Collection input ) {
        super(input);
        this.parameters = new SchemaParameters();
    }
    public Existential (SchemaParameters p, Collection input ) {
        super(input);
        this.parameters = p;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Existential)) {
            return false;
        }

        final Existential other = (Existential) obj;
        return other.sons.equals(this.sons) && this.parameters.equals(other.parameters);
    }

    @Override
    public int hashCode ( ) {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.parameters);
        hash = 83 * hash + Objects.hashCode(this.sons);
        return hash;
    }


    @Override
    public boolean eval (State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toSmtVariableString (int k, TransitionGround gr, String var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSatisfied (State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void changeVar (Map substitution) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public String pddlPrintWithExtraObject ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition clone ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBeTrue (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBeFalse (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition normalize ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String toSmtVariableString (int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        Set<NumFluent> ret = new HashSet();
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
    public ComplexCondition transformEquality ( ) {
        Collection res = new HashSet();
        HashSet<Condition> temp = new HashSet<>();
        for (final var c : sons) {
            temp.add(((Condition)c).transformEquality());
        }
        addAll(temp,sons);
        return new Existential(parameters,res);
    }


    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        System.out.println("(exist " + this.parameters.toString() + " " + this.sons.toString());
    }



    @Override
    public ComplexCondition and (Condition precondition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Condition pushNotToTerminals( ) {
        if (this.sons == null) {
            return this;
        }
        Collection res = new HashSet();
        for (final var c :  this.sons) {
            Condition c1 = ((Condition)c).pushNotToTerminals();
            if (c1 instanceof OrCond) {
                addAll(res,((OrCond) c1).sons);
            } else {
                res.add(c1);
            }
        }
        return new Existential(parameters,res);
    }

    @Override
    public boolean isSatisfied (RelState rs, ArrayList<Integer> dist, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition introduce_red_constraints ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addParameter (Variable variable) {
        this.getParameters().add(variable);
    }

    /**
     * @return the parameters
     */
    public SchemaParameters getParameters ( ) {
        return parameters;
    }


    @Override
    public Condition ground (Map<Variable, PDDLObject> substitution, PDDLObjects objects) {
        try {
            Grounder g = new Grounder();
            Set<ParametersAsTerms> combo = g.Substitutions(this.parameters, objects);
            Collection res = new HashSet();
            for (ParametersAsTerms ele : combo) {
                Map sub = g.obtain_sub_from_instance(parameters, ele);
                sub.putAll(substitution);
                Condition son = (Condition) this.sons[0];
                res.add(son.ground(sub, objects));
            }
            return new OrCond(res);

        } catch (Exception ex) {
            Logger.getLogger(Existential.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Condition weakEval (PDDLProblem s, Set invF) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Condition pushNegationDemorgan ( ) {
        Collection res = new HashSet();

        for (final var c : this.sons) {
            NotCond nc = NotCond.createNotCond((Condition) c);
            res.add(nc);
        }
        return new ForAll(parameters,res);
    }

    @Override
    public Condition unifyVariablesReferences(PDDLProblem p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
