package com.hstairs.ppmajal.PDDLProblem;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.hstairs.ppmajal.conditions.Condition;
public class DecActionRepresentation {
    final IntArraySet[] necTerminals;
    final IntArraySet[] actionsOfNecTerminal;

    final Set<Condition>[] necNotTerminals;
    final IntArraySet universeOfTerminals;
    final IntArraySet actions;
    private final int trueTerminal;



    public DecActionRepresentation(IntArraySet actions){
        this.actions = actions;
        necTerminals = new IntArraySet[TransitionGround.totNumberOfTransitions];
        trueTerminal = Terminal.getTotCounter();
        actionsOfNecTerminal = new IntArraySet[Terminal.getTotCounter()+1];
        universeOfTerminals = new IntArraySet();

        universeOfTerminals.add(trueTerminal);
        necNotTerminals = new HashSet[TransitionGround.totNumberOfTransitions];
        for (var action: this.actions){
            TransitionGround act = (TransitionGround) TransitionGround.getTransition(action);
            IntArraySet terminals = new IntArraySet();
            Set<Condition> notTerminals = new HashSet<>();

            if (act.getPreconditions() instanceof AndCond){
                for (Object c: ((AndCond) act.getPreconditions()).sons){
                    if (c instanceof Terminal){
                        updateTerminalRelantion(((Terminal) c).getId(), terminals, action);
                    }else{
                        notTerminals.add((Condition) c);
                    }
                }
            }else if (act.getPreconditions() instanceof Terminal){
                updateTerminalRelantion(((Terminal) act.getPreconditions()).getId(), terminals, action);
            }else{
                notTerminals.add((Condition) act.getPreconditions());
            }
            if (terminals.isEmpty()){
                updateTerminalRelantion(trueTerminal,terminals,action);
            }
            necTerminals[action] = terminals;
            necNotTerminals[action] = notTerminals;
        }

    }

    public boolean isTrueTerminal(int input){
        return input == trueTerminal;
    }

    private void updateTerminalRelantion(int c, IntArraySet terminals, int action) {
        terminals.add(c);
        universeOfTerminals.add(c);
        if (actionsOfNecTerminal[c] == null)
            actionsOfNecTerminal[c] = new IntArraySet();
        actionsOfNecTerminal[c].add(action);


    }

    private void print(IntArraySet[] actionsOfNecTerminal) {
        for (int i=0; i< actionsOfNecTerminal.length;i++){
            System.out.println(actionsOfNecTerminal[i]);
        }
    }

    public void prettyPrint(){
        System.out.println("Relations");


        for (var i: actions){
            System.out.println("Action ("+i+"):"+ TransitionGround.getTransition(i));
            System.out.println("Necessary Terminals:");
            for (var t: necTerminals[i]){
                if (!isTrueTerminal(t))
                    System.out.println("Terminal("+t+"):"+Terminal.getTerminal(t));
            }
            System.out.println("Necessary Conditions:"+necNotTerminals[i]);
        }
        System.out.println("Universe of conditions");
        for (var i: this.universeOfTerminals){
            if (i == trueTerminal)
                continue;
            System.out.println("Terminal("+i+"):"+Terminal.getTerminal(i));
            System.out.println("Actions affected:"+actionsOfNecTerminal[i]);
            for (var act: actionsOfNecTerminal[i]){
                System.out.println(TransitionGround.getTransition(act));
            }

        }
    }
}
