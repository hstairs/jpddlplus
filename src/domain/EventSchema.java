/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import conditions.PDDLObject;
import java.util.HashMap;
import java.util.Map;
import problem.GroundAction;
import problem.GroundEvent;

/**
 *
 * @author enrico
 */
public class EventSchema extends ActionSchema{
    
    public GroundEvent ground() {
        GroundEvent ret = new GroundEvent(this.name);
        ParametersAsTerms input = new ParametersAsTerms();

        ret.setParameters(input);
        ret.setPreconditions(preconditions);
        ret.setAddList(addList);
        ret.setDelList(delList);
        ret.setNumericEffects(numericEffects);
        ret.cond_effects = cond_effects;
        return ret;
    }
    
        @Override
    public String toString() {
//        String parametri = "";
//        for (Object o : parameters) {
//            parametri = parametri.concat(o.toString()).concat(" ");
//        }
//        return "\n\nAction Name:" + this.name + " Parameters: " + parametri + "\nPre: " + this.preconditions + "\nEffetti positivi: " + this.getAddList() + "\nEffetti negativi: " + this.getDelList() + "\nNumeric Effects:  " + this.getNumericEffects();
//    }

        String ret = "(:action " + this.name + "\n";

        ret += ":parameters " + this.parameters + "\n";
        ret += ":precondition " + this.getPreconditions().pddlPrint(false) + "\n";
        //ret += ":effect " + this.pddlEffectsWithExtraObject();
        ret += ":effect " + this.pddlEffects()+"\n";

        return ret + ")";
    }
    
    public GroundEvent ground(ParametersAsTerms par) {
        GroundEvent ret = new GroundEvent(this.name);
        int i = 0;

        Map substitution = new HashMap();
        for (Object o : parameters) {
            Variable el = (Variable) o;
            substitution.put(el, par.get(i));
            PDDLObject t = (PDDLObject) substitution.get(el);
            i++;
        }
        ret.setParameters(par);

//        System.out.println(this);
        if (numericEffects!= null || !numericEffects.sons.isEmpty()){
            //System.out.println(this);
            ret.setNumericEffects(this.numericEffects.ground(substitution));
        }if (addList != null) {
            ret.setAddList(this.addList.ground(substitution));
        }
        if (delList != null) {
            ret.setDelList(this.delList.ground(substitution));
        }
        if (preconditions != null) {
            ret.setPreconditions(this.preconditions.ground(substitution));
        }
        if (cond_effects != null) {
//            System.out.println("DEBUG: Before:"+cond_effects);
            ret.cond_effects = this.cond_effects.ground(substitution);
//            System.out.println("DEBUG: after:"+cond_effects);
       
        }

        return ret;
    }
}