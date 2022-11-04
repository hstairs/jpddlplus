/*
 * Copyright (C) 2018 enrico.
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

import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import java.util.Map;

/**
 * @author enrico
 */
public class Printer {

    static public StringBuilder stringBuilderPddlPrintWithDummyTrue (PDDLProblem p, PDDLState s) {
        final StringBuilder ret = new StringBuilder("(:init (true)\n");

        for (BoolPredicate a : p.getInitBoolFluentsValues().keySet()) {
            if (s.holds(a)) {
                ret.append("  (").append(a.getName());
                for (Object o1 : a.getTerms()) {
                    PDDLObject obj = (PDDLObject) o1;
                    ret.append(" ").append(obj.getName());
                }
                ret.append(")\n");
            }
        }
        for (NumFluent nf : p.getInitNumFluentsValues().keySet()) {
            if (s.fluentValue(nf) != Double.NaN) {
                ret.append("  ( = (").append(nf.getName());
                for (Object o1 : nf.getTerms()) {
                    PDDLObject obj = (PDDLObject) o1;
                    ret.append(" ").append(obj.getName());
                }
                ret.append(") ").append(s.getNumFluents().get(nf.getId())).append(")\n");
            }
        }

        ret.append(")");
        return ret;
    }

    static public String pddlPrintWithDummyTrue (PDDLProblem p, PDDLState s) {
        return Printer.pddlPrintWithDummyTrue(p, s);
    }

    static public String pddlPrint (PDDLProblem p, PDDLState s) {
        return Printer.pddlPrintWithDummyTrue(p, s);
    }

    public static void pddlPrint(StringBuilder ret, ConditionalEffects conditionalNumericEffects) {
        for (var v: conditionalNumericEffects.getUnconditionalEffect()){
            ret.append("\t\t\t");
            ret.append(v.toString());
        }
        for (var v: conditionalNumericEffects.getActualConditionalEffects().entrySet()){
            Map.Entry entry = (Map.Entry)v;
            ret.append("(when ").append(entry.getKey().toString());
            ret.append(entry.getValue());
        }

    }

}
