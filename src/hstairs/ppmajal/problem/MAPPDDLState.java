package com.hstairs.ppmajal.problem;

import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import java.util.HashMap;
import java.util.Map;

public class MAPPDDLState extends PDDLState {


    final HashMap<String, Boolean> predicateValue;
    final HashMap<String, PDDLNumber> numFluentValue;
    
    public MAPPDDLState (Map<NumFluent, PDDLNumber> numFluentReference, Map<BoolPredicate, Boolean> initBoolFluentsValues) {
        numFluentValue = new HashMap<>();
        predicateValue = new HashMap<>();
        for (NumFluent nf : numFluentReference.keySet()) {
            numFluentValue.put(nf.toString(), numFluentReference.get(nf));
        }
        for (BoolPredicate p : initBoolFluentsValues.keySet()) {
            predicateValue.put(p.toString(), initBoolFluentsValues.get(p));
        }
    }

    @Override
    public double fluentValue (NumFluent f) {
        PDDLNumber pddlNumber = numFluentValue.get(f.toString());
        if (pddlNumber == null)
            return Double.NaN;
        return pddlNumber.getNumber();
    }

    @Override
    public boolean holds (BoolPredicate p) {
        return predicateValue.get(p.toString());
    }
}
