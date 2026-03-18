package com.hstairs.ppmajal.transition;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.PDDLProblem.Metric;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.problem.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
        return new TransitionGround(null,Semantics.ACTION,null,null,null,null);
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

    public boolean isApplicable(State s, boolean checkEffects, PDDLProblem p) {
        if (checkEffects)
            return (this.preconditions.isSatisfied(s) && this.conditionalNumericEffects.canBeApplied(s,p));
        return (this.preconditions == null || this.preconditions.isSatisfied(s));

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
    
public String toString(Map<String, Double> inputValues) {
    StringBuilder res = new StringBuilder();
    Set<String> usedInputs = getUsedInputs(inputValues);

    if (!usedInputs.isEmpty())    res.append("<(");
    else                            res.append("(");
    
    res.append(name);
    if (this.parameters != null) {
        for (final PDDLObject obj : this.parameters) {
            res.append(" ").append(obj.getName());
        }
    }

    if (!usedInputs.isEmpty())    res.append("), ");
    

    // añadimos solo los inputs que realmente participan en esta acción
    if (!usedInputs.isEmpty()) {
        res.append("{");
        for (String inputName : usedInputs) {
            res.append(inputName)
               .append("=")
               .append(inputValues.get(inputName))
               .append(", ");
        }
        // eliminamos la última coma y espacio si había elementos
        res.setLength(res.length() - 2);
        res.append("}");
    } 

    if (!usedInputs.isEmpty())    res.append(">");
    else                            res.append(")");
    
    return res.toString();
}

    private Set<String> getUsedInputs(Map<String, Double> inputValues) {
        Set<String> usedInputs = new LinkedHashSet<>();
        if (inputValues == null || inputValues.isEmpty()) {
            return usedInputs;
        }

        Set<NumFluent> involvedFluents = new HashSet<>();

        if (this.preconditions != null) {
            involvedFluents.addAll(this.preconditions.getInvolvedFluents());
        }

        for (NumEffect effect : this.getConditionalNumericEffects().getAllEffects()) {
            involvedFluents.addAll(effect.getInvolvedFluents());
        }

        for (Condition condition : this.getConditionalNumericEffects().getActualConditionalEffects().keySet()) {
            involvedFluents.addAll(condition.getInvolvedFluents());
        }

        for (Object conditionObj : this.getConditionalPropositionalEffects().getActualConditionalEffects().keySet()) {
            if (conditionObj instanceof Condition condition) {
                involvedFluents.addAll(condition.getInvolvedFluents());
            }
        }

        for (NumFluent fluent : involvedFluents) {
            String fluentName = fluent.getName();
            if (inputValues.containsKey(fluentName)) {
                usedInputs.add(fluentName);
            }
        }

        return usedInputs;
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
        return getSdac(init,metric,Sdac.disabled);
    }
    
    private float getImpact(Float n, String opt){
        if (opt.equals("maximize")){
            return -1 * n;
        }else{
            return n;
        }
    }
    private List<Pair<Condition, Float>> getSdac(PDDLState state, Metric metric, Sdac sdacConfiguration) {

        if (sdacConfiguration == Sdac.byRHS) {
            if (this.sdac == null){
                this.sdac = new ArrayList();
                final ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                //first numeric effect normal
                for (final NumEffect effNum :  this.getConditionalNumericEffects().getAllEffects()) {
                    for (final ExtendedAddendum ad : expr.summations) {
                        if (ad.f != null) {
                            if (effNum.getFluentAffected().equals(ad.f)){
                                this.sdac.add(effNum);
                            }
                        }
                    }
                }
            }
            Float exprImpact = 0f;
            ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
            for (var eff : this.sdac){
                for (ExtendedAddendum ad : expr.summations){
                    if (ad.f != null){
                        exprImpact += ad.n.floatValue() * this.getExprImpact(state, (NumEffect)eff, ad.f);
                    }
                }
            }
            if ((exprImpact <= 0 && metric.getOptimization().equals("maximize"))
                    || (exprImpact >= 0 && metric.getOptimization().equals("minimize"))) {
                BoolPredicate truePredicate = BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE);
                return java.util.Collections.singletonList(
                        Pair.of(truePredicate, getImpact(exprImpact, metric.getOptimization())));
            }else{
                throw new RuntimeException("Metric not supported in that it induces negative costs");
            }

        }

        if (this.sdac == null) {
            this.sdac = new ArrayList<>();
            if (metric != null && metric.getMetExpr() != null) {
                if (sdacConfiguration == Sdac.disabled){
                    ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                    //first numeric effect normal
                    Float exprImpact = 0f;
                    for (NumEffect effNum :  this.getConditionalNumericEffects().getAllEffects()) {
                        for (ExtendedAddendum ad : expr.summations) {
                            if (ad.f != null) {
                                exprImpact += ad.n.floatValue() * this.getExprImpact(state, effNum, ad.f);
                            }
                        }
                    }
                        
                    if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                            || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                        BoolPredicate truePredicate = BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE);
                        this.sdac.add(Pair.of(truePredicate, getImpact(exprImpact,metric.getOptimization())));
                    }
                }else if (sdacConfiguration == Sdac.byCondition){
                    final ConditionalEffects<NumEffect> conditionalNumericEffects1 = this.getConditionalNumericEffects();
                    final Map<Condition, Collection<NumEffect>> actualConditionalEffects = conditionalNumericEffects1.getActualConditionalEffects();
                    for (Map.Entry<Condition,Collection<NumEffect>> ele: actualConditionalEffects.entrySet()) {
                        final ExtendedNormExpression expr = (ExtendedNormExpression) metric.getMetExpr();
                        Float exprImpact = 0f;
                        for (NumEffect effNum :  ele.getValue()) {
                            for (ExtendedAddendum ad : expr.summations) {
                                if (ad.f != null) {
                                    exprImpact += ad.n.floatValue() * this.getExprImpact(state, effNum, ad.f);
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
                                exprImpact += ad.n.floatValue() * this.getExprImpact(state, effNum, ad.f);
                            }
                        }
                    }

                    if ((exprImpact < 0 && metric.getOptimization().equals("maximize"))
                            || (exprImpact > 0 && metric.getOptimization().equals("minimize"))) {
                        this.sdac.add(Pair.of(BoolPredicate.getPredicate(BoolPredicate.trueFalse.TRUE), getImpact(exprImpact,metric.getOptimization())));
                    }
                    
                } else{
                    throw new UnsupportedOperationException("Sdac option not supported"+ sdacConfiguration);
                }
            }
        }
        return this.sdac;
    }
    public Float getActionCost(State s, Metric m){
        return getActionCost(s, m, Sdac.disabled);
    }
    public Float getActionCost(State s, Metric m, Sdac sdac){
        if (m == null || m.getMetExpr() == null ){
            return 1f;
        }
//        List<Pair<Condition, Float>> sdac1 = this.getSdac((PDDLState) s, m);
//        if (sdac1 == null){
//            return 1f;
//        }

        List<Pair<Condition, Float>> sdac1 = this.getSdac((PDDLState) s, m, sdac);
        float impact = 0f;
        for (final Pair<Condition,Float> ele : sdac1){
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
