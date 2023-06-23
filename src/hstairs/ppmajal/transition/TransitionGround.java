package com.hstairs.ppmajal.transition;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.problem.Metric;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;


public class TransitionGround extends Transition {
    final protected List<PDDLObject> parameters;
    private ArrayList sdac;

    
    public TransitionGround(String name, Semantics semantics, List<PDDLObject> parameters, Condition preconditions, ConditionalEffects conditionalPropositionalEffects, ConditionalEffects conditionalNumericEffects) {
        super(name, conditionalPropositionalEffects, conditionalNumericEffects, preconditions, semantics);
        this.parameters = parameters;
    }

    public static TransitionGround createEmptyAction(){
        return new TransitionGround(null,Semantics.PROCESS,null,null,null,null);
    }
    public TransitionGround(ArrayList<NumEffect> numEffect) {
        this(                "waiting", Transition.Semantics.PROCESS, null, null,
                new ConditionalEffects(),
                new ConditionalEffects());
        this.getConditionalNumericEffects().forceUnconditionalEffect(numEffect);
    }

    public List<PDDLObject> getParameters() {
        return parameters;
    }

    public boolean isApplicable(State s) {
        return (this.preconditions.isSatisfied(s));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 15 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.parameters);
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
        final TransitionGround other = (TransitionGround) obj;
        if (!Objects.equals(this.parameters, other.parameters)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder res= new StringBuilder();
        res.append("(").append(name);
        if (this.parameters != null) {
            for (final PDDLObject obj : this.parameters) {
                res.append(" ").append(obj.getName());
            }
        }
        res.append(")");
        return res.toString();
    }
    
    public String toStringAllModel() {
        StringBuilder res= new StringBuilder();
        res.append("(").append(name);
        if (this.parameters != null) {
            for (final PDDLObject obj : this.parameters) {
                res.append(" ").append(obj.getName());
            }
        }
        res.append("Precondition");
        res.append(this.getPreconditions());
        res.append("numeric effect");
        res.append(this.conditionalNumericEffects);
        res.append("propositional effect");
        res.append(this.conditionalPropositionalEffects);
        res.append(")");
        return res.toString();
    }

    private Float getExprImpact(PDDLState s_0, NumEffect nEff, NumFluent f) {
        if (nEff.getFluentAffected().equals(f)) {
            ExtendedNormExpression right = (ExtendedNormExpression) nEff.getRight();
            if (nEff.getOperator().equals("increase")) {
                return right.eval_apart_from_f(f, s_0);
            } else if (nEff.getOperator().equals("decrease")) {
                return right.eval_apart_from_f(f, s_0) * -1.0f;

            }
            return right.eval_apart_from_f(f, s_0);
        } else {
            return 0f;
        }
    }
    
    private List<Pair<Condition, Float>> getSdac(PDDLState init, Metric metric) {
        return getSdac(init,metric,false);
    }
    
    private float getImpact(Float n, String opt){
        if (opt.equals("maximize")){
            return -1 * n;
        }else{
            return n;
        }
    }
    private List<Pair<Condition, Float>> getSdac(PDDLState init, Metric metric, boolean sdacEnabled) {
        if (this.sdac == null) {
            this.sdac = new ArrayList<>();
            if (metric != null && metric.getMetExpr() != null) {
                if (!sdacEnabled){
                    ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                    //first numeric effect normal
                    Float exprImpact = 0f;
                    for (NumEffect effNum :  this.getConditionalNumericEffects().getAllEffects()) {
                        for (ExtendedAddendum ad : expr.summations) {
                            if (ad.f != null) {
                                exprImpact += ad.n.floatValue() * this.getExprImpact(init, effNum, ad.f);
                            }
                        }
                    }
                        
                    if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                            || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                        BoolPredicate truePredicate = BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE);
                        this.sdac.add(Pair.of(truePredicate, getImpact(exprImpact,metric.getOptimization())));
                    }
                }else{
                    final ConditionalEffects<NumEffect> conditionalNumericEffects1 = this.getConditionalNumericEffects();
                    final Map<Condition, Collection<NumEffect>> actualConditionalEffects = conditionalNumericEffects1.getActualConditionalEffects();
                    for (Map.Entry<Condition,Collection<NumEffect>> ele: actualConditionalEffects.entrySet()) {
                        final ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                        Float exprImpact = 0f;
                        for (NumEffect effNum :  ele.getValue()) {
                            for (ExtendedAddendum ad : expr.summations) {
                                if (ad.f != null) {
                                    exprImpact += ad.n.floatValue() * this.getExprImpact(init, effNum, ad.f);
                                }
                            }
                        }
                        
                        if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                                || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                            this.sdac.add(Pair.of(ele.getKey(), getImpact(exprImpact,metric.getOptimization())));
                        }
                    }
                    final ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                    Float exprImpact = 0f;
                    for (NumEffect effNum :  conditionalNumericEffects1.getUnconditionalEffect()) {
                        for (ExtendedAddendum ad : expr.summations) {
                            if (ad.f != null) {
                                exprImpact += ad.n.floatValue() * this.getExprImpact(init, effNum, ad.f);
                            }
                        }
                    }

                    if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                            || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                        this.sdac.add(Pair.of(BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE), getImpact(exprImpact,metric.getOptimization())));
                    }
                    
                }
            }
        }
        return this.sdac;
    }
    public Float getActionCost(State s, Metric m){
        return getActionCost(s, m, false);
    }
    public Float getActionCost(State s, Metric m, boolean sdac){
        if (m == null || m.getMetExpr() == null ){
            return 1f;
        }
//        List<Pair<Condition, Float>> sdac1 = this.getSdac((PDDLState) s, m);
//        if (sdac1 == null){
//            return 1f;
//        }

        List<Pair<Condition, Float>> sdac1 = this.getSdac((PDDLState) s, m, sdac);
        float impact = 0f;
        for (Pair<Condition,Float> ele : sdac1){
            if (ele.getLeft().isSatisfied(s)){
                impact += ele.getRight();
            }
        }
        return impact;
    }

    private static TransitionGround waiting;
    public static TransitionGround waitingAction() {
       if (waiting == null){
           waiting  = new TransitionGround("------>waiting", 
                    Transition.Semantics.PROCESS, new ArrayList<>(), 
                    null,
                    new ConditionalEffects(),new ConditionalEffects());
       }
       return waiting;
    }
    public boolean isWaiting(){
        return parameters == null;
    }

    public Map<Condition, Collection> getAllConditionalEffects() {
        final Map<Condition, Collection> allConditionalNumericEffects = this.conditionalNumericEffects.getAllConditionalEffects();
        final Map<Condition, Collection> allConditionaPropositionalEffects = this.conditionalPropositionalEffects.getAllConditionalEffects();

        final Set<Condition> keySet = allConditionaPropositionalEffects.keySet();
        final Set<Condition> keySet1 = allConditionalNumericEffects.keySet();
        final Sets.SetView<Condition> union = Sets.union(keySet, keySet1);
        final Map ret = new HashMap();
        for (final Condition c : union) {
            final Collection ele = new HashSet();
            Collection get = allConditionaPropositionalEffects.get(c);
            if (get != null) {
                ele.addAll(get);
            }
            get = allConditionalNumericEffects.get(c);
            if (get != null) {
                ele.addAll(get);
            }
            ret.put(c, ele);

        }

        return ret;
    }
    
    
}
