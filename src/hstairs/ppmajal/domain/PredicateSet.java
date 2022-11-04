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

import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.PDDLObject;
import java.util.HashSet;

/**
 * @author enrico
 */
public class PredicateSet extends HashSet<BoolPredicate> {

    public PredicateSet ( ) {
        super();
    }

    public PredicateSet (PredicateSet predicates) {
        super();
        this.addAll(predicates);
    }

    public boolean validate (BoolPredicate p) {

        for (final BoolPredicate elP : this) {
            if (elP.getName() == null ? p.getName() == null : elP.getName().equalsIgnoreCase(p.getName())) {
                if (elP.getTerms().size() == p.getTerms().size()) {//this is not sufficient. Works just for predicates with different names
                    return true;
                }
            }
        }
        return false;
    }

    public BoolPredicate findAssociated (BoolPredicate p) {

        for (final BoolPredicate elP : this) {
            if (elP.getName() == null ? p.getName() == null : elP.getName().equalsIgnoreCase(p.getName())) {
                if (elP.getTerms().size() == p.getTerms().size()) {//this is not sufficient. Works just for predicates with different names
//                    for (Object o: elP.getTerms())
                    for (int i = 0; i < p.getTerms().size(); i++) {
                        Variable v = (Variable) elP.getTerms().get(i);
                        Variable v1 = (Variable) p.getTerms().get(i);
                        if (!(v.getType().equals(v1.getType())) && !(v.getType().isAncestorOf(v1.getType()))) {
                            System.out.println(v1 + " is neither a valid instance nor a valid subtype for " + v1);
                            return null;
                        }
                    }

                    return elP;
                }
            }
        }
        return null;
    }

    public boolean validateInst (BoolPredicate p) {

        for (final BoolPredicate elP : this) {
            if (elP.getName() == null ? p.getName() == null : elP.getName().equalsIgnoreCase(p.getName())) {
                if (elP.getTerms().size() == p.getTerms().size()) {
                    int i = 0;
                    boolean found = true;
                    for (Object o : elP.getTerms()) {
                        Variable v = (Variable) o;
                        PDDLObject t = (PDDLObject) p.getTerms().get(i);
                        if (!(v.getType().equals(t.getType())) && !(t.getType().isAncestorOf(v.getType()))) {
                            found = false;

                            System.out.println("relazione non valida: " + v.getType() + t.getType());
                            System.out.println((v.getType().isAncestorOf(t.getType())));
                        }
                        i++;
                        if (!found) {
                            break;
                        }
                    }
                    if (found == true) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public String pddlPrint (boolean typeInformation) {
        StringBuilder ret = new StringBuilder("");
        for (final BoolPredicate elP : this) {
            elP.pddlPrint(typeInformation, ret);
//            ret += elP.pddlPrint(typeInformation);
        }
        ret.append(")");
        return ret.toString();
    }

    public String pddlPrintWithExtraObject (boolean typeInformation) {
        String ret = "";
        for (final BoolPredicate elP : this) {
            if (typeInformation) {
                ret += elP.pddlPrintWithTypedExtraObject();
            } else {
                ret += elP.pddlPrintWithExtraObject();
            }
        }
        return ret + ")";
    }

    public void addIfNecessary (BoolPredicate p) {
        boolean found = false;
        for (final BoolPredicate elP : this) {
            if (elP.getName() == null ? p.getName() == null : elP.getName().equalsIgnoreCase(p.getName())) {
                if (elP.getTerms().size() == p.getTerms().size()) {//this is not sufficient. Works just for predicates with different names
                    found = true;
//                    for (Object o: elP.getTerms())
                    for (int i = 0; i < p.getTerms().size(); i++) {
                        Variable v = (Variable) elP.getTerms().get(i);
                        Variable v1 = (Variable) p.getTerms().get(i);
                        if (!(v.getType().equals(v1.getType())) && !(v.getType().isAncestorOf(v1.getType()))) {
                            System.out.println(v1 + " is neither a valid instance nor a valid subtype for " + v);
                            if (!(v1.getType().equals(v.getType())) && !(v1.getType().isAncestorOf(v.getType()))) {
                                System.out.println(v + " is neither a valid instance nor a valid subtype for " + v1);
                                found = false;
                            } else {
                                found = true;
                                v = Variable.variable(v.getName(),v1.getType());
                            }

                        } else {
                            found = true;
                            break;
                        }

                    }

                }
            }
        }
        if (!found) {
            this.add(p);
        }

    }
}
