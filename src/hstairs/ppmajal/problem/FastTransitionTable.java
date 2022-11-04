/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.problem;

import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.PostCondition;
import java.util.Collection;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author enrico
 */
public class FastTransitionTable {
    final private Pair<Condition,PostCondition[]>[][] effects;

    public FastTransitionTable(int nEff) {
        this.effects = new Pair[nEff][];
    }

    public void addEffect(int id, Collection<Pair<Condition,Collection<PostCondition>>> eff){
        this.effects[id] = new Pair[eff.size()];
        int i = 0;
        for (final var e : eff){
            final PostCondition[] effs = new PostCondition[e.getRight().size()];
            int j = 0;
            for (final var n : e.getRight()){
                effs[j] = n;
                j++;
            }
            this.effects[id][i] = Pair.of(e.getKey(),effs);
            i++;
        }
    }
    
    public boolean done(int id){
        return this.effects[id] != null;
    }
    
    public Pair<Condition,PostCondition[]>[] getEffects(int id){
        return this.effects[id];
    }

}
