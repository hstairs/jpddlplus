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

import com.hstairs.ppmajal.domain.ActionParameter;
import com.hstairs.ppmajal.domain.Type;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.*;

/**
 * @author enrico
 */
public class PDDLObject extends Terminal implements ActionParameter {

    final private String name;
    private Type type;

    public static HashMap<String,PDDLObject> objectsDB;
    
    public static PDDLObject object(String name, Type type){
        if (objectsDB == null){
            objectsDB = new HashMap();
        }
        PDDLObject obj = objectsDB.get(name);
        if (obj == null){
            obj = new PDDLObject(name,type);
            objectsDB.put(name, obj);
        }else{
            if (obj.type == null){
                obj.type = type;
            }
        }
        
        
        return obj;
            
    }

    private PDDLObject (String n, Type atype) {
        super();
        name = n;
        type = atype;
    }


    /**
     * @return the type
     */
    public Type getType ( ) {
        return type;
    }


    @Override
    public String toString ( ) {
        String ret_val = null;

        ret_val = getName() + " " + type;

        return ret_val;
    }

    /**
     * @return the name
     */
    public String getName ( ) {
        return name;
    }


    @Override
    public PDDLObject ground (Map<Variable, PDDLObject> substitution, PDDLObjects po) {
        return this; // TODO: Why not return this?
    }

    @Override
    public boolean eval (State s) {
        return true;
    }

    @Override
    public boolean isSatisfied (State s) {
        return true;
    }

    @Override
    public boolean canBeTrue (RelState s) {
        return true;
    }

//    @Override
//    public void changeVar (Map substitution) {
//        return;
//    }

    @Override
    public Condition clone ( ) {
//        PDDLObject ret = new PDDLObject(name,this.getType());
//        ret.grounded = this.grounded;
//        return ret;
        return this;
    }

    @Override
    public Condition normalize ( ) {
        return this;
    }

    @Override
    public String toSmtVariableString (int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<NumFluent> getInvolvedFluents ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition weakEval (PDDLProblem s, Set invF) {
        return this;
    }

    @Override
    public String toSmtVariableString (int k, TransitionGround gr, String var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition transformEquality ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String pddlPrintWithExtraObject ( ) {
        return this.getName();
    }

    @Override
    public boolean canBeFalse (RelState aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pddlPrint (boolean typeInformation, StringBuilder bui) {
        bui.append(getName());
        if (typeInformation) {
            bui.append(" ").append(getType());
        }
    }

    @Override
    public void storeInvolvedVariables (Collection<Variable> vars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Condition> getTerminalConditions ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public ComplexCondition and (Condition precondition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Condition pushNotToTerminals( ) {
        return this;
    }

    @Override
    public PDDLObject ground (Map<Variable, PDDLObject> substitution) {
        return this; // TODO: Why not return this?
    }

    @Override
    public void extendTerms (Variable v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<BoolPredicate> getInvolvedPredicates ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition unifyVariablesReferences (PDDLProblem p) {
        return this;
    }


    
    
}
