/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.OrCond;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.problem.RelState;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.expressions.BinaryOp;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.collections4.multimap.HashSetValuedHashMap;


// Angel

import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.Expression;
import java.util.Arrays;

/**
 *
 * @author enrico
 */
public class ProblemTransfomer {

    private static Condition[] preconditionFunction;
    private static Collection<Integer>[] propEffectFunction;
    private static Collection<NumEffect>[] numericEffectFunction;
    private static float[] actionCost;
    private static PDDLProblem p;
    private static Map<AndCond, Collection<IntArraySet>> redundantMap;
    private static Collection[] transition2cptransition;
    private static int[] cptransition2transition;
    private static boolean conditionalEffectsSensitive = true;
    private static int linearEffectsAbstraction = -1;
    private static int totIntervals = 0;
    private static int compiledEffects = 0;
    private static ArrayList<RelState> relaxedStates = new ArrayList<>();
    private static int pseudoGoal;
    private static Int2ObjectOpenHashMap preconditionFunctionMap;
    private static Int2ObjectOpenHashMap propEffectFunctionMap;
    private static Int2ObjectOpenHashMap numericEffectFunctionMap;
    private static Int2ObjectOpenHashMap transition2cptransitionMap;
    private static Int2IntOpenHashMap cptransition2transitionMap;
    private static Set<NumFluent> metricVars = new HashSet<>();

    // Angel
    private static Condition allProblemConditions;

    public static CompactPDDLProblem generateCompactProblem(PDDLProblem problem, String redConstraints,
                                                            boolean unitaryCost, int linearEffectsAbstraction) {
        int nTransitions = Transition.totNumberOfTransitions + 1;
        pseudoGoal = nTransitions - 1;
        p = problem;

        if (p.getMetric() != null){
            System.out.println("Metric: " + p.getMetric().getMetExpr().getInvolvedNumericFluents());
            metricVars = p.getMetric().getMetExpr().getInvolvedNumericFluents();
        }

        ProblemTransfomer.linearEffectsAbstraction = linearEffectsAbstraction;

        if (linearEffectsAbstraction >= 0) {
            System.out.printf("Activated Linear Effect Abstraction.\n");

            if (linearEffectsAbstraction == 0){
                System.out.printf("Using 2 intervals (-inf, 0) and (0, +inf)\n");
            }

            if (linearEffectsAbstraction > 0){
                if (linearEffectsAbstraction < Integer.MAX_VALUE){
                    System.out.printf("Keeping %s positive and negative intervals\n", linearEffectsAbstraction);
                }
                Aibr haibr = new Aibr(problem);
                haibr.computeEstimate(problem.getInit(), relaxedStates);
            }
        }

        if (conditionalEffectsSensitive) {
            preconditionFunctionMap = new Int2ObjectOpenHashMap();
            propEffectFunctionMap = new Int2ObjectOpenHashMap();
            numericEffectFunctionMap = new Int2ObjectOpenHashMap();
            transition2cptransitionMap = new Int2ObjectOpenHashMap();
            cptransition2transitionMap = new Int2IntOpenHashMap();
        } else {
            preconditionFunction = new Condition[nTransitions];
            propEffectFunction = new Collection[nTransitions];
            numericEffectFunction = new Collection[nTransitions];
            actionCost = new float[nTransitions];
            transition2cptransition = new Collection[nTransitions];
            cptransition2transition = new int[nTransitions];
        }
        var v = fillPreEff(0, redConstraints, new LinkedHashSet(p.actions));
        v = fillPreEff(v, redConstraints, new LinkedHashSet(p.getEventsSet()));
        v = fillPreEff(v, redConstraints, new LinkedHashSet(p.getProcessesSet()));

        if (linearEffectsAbstraction >= 0) {

            System.out.printf("Average increase per effect: %f\n", (float) totIntervals / compiledEffects);
            System.out.printf("Number of compiled effects: %s\n", compiledEffects);

        }
        if (conditionalEffectsSensitive) {
            pseudoGoal = v;
            preconditionFunction = new Condition[v + 1];
            propEffectFunction = new Collection[v + 1];
            numericEffectFunction = new Collection[v + 1];
            transition2cptransition = new Collection[nTransitions];
            cptransition2transition = new int[v + 1];

            actionCost = new float[v + 1];
            for (int v1 : preconditionFunctionMap.keySet()) {
                preconditionFunction[v1] = (Condition) preconditionFunctionMap.get(v1);
                propEffectFunction[v1] = (IntArraySet) propEffectFunctionMap.get(v1);
                numericEffectFunction[v1] = (Collection) numericEffectFunctionMap.get(v1);
                final TransitionGround t = (TransitionGround) Transition.getTransition(cptransition2transitionMap.get(v1));
                if (unitaryCost){
                    actionCost[v1] = 1;
                }else {
                    actionCost[v1] = t.getActionCost(p.getInit(), p.getMetric(), p.isSdac());
                }
            }
            for (int v1 : transition2cptransitionMap.keySet()) {
                transition2cptransition[v1] = (Collection) transition2cptransitionMap.get(v1);

            }
            for (int v1 : cptransition2transitionMap.keySet()) {
                cptransition2transition[v1] = cptransition2transitionMap.get(v1);
            }

            nTransitions = v + 1;
        }
        preconditionFunction[pseudoGoal] = normalizeAndTighthenCondition(p.getGoals(), redConstraints);
        //Angel
        propEffectFunction[pseudoGoal] = new IntArraySet();
        numericEffectFunction[pseudoGoal] = new LinkedHashSet<>();
        transition2cptransition[pseudoGoal] = Collections.singleton(pseudoGoal);

        System.out.println("DPEX relaxation: "+ p.getRelaxationDPEX());

        //System.out.println("Total transitions in the compact problem: " + (cptransition2transition.length-1));

        return new CompactPDDLProblem(preconditionFunction,
                propEffectFunction, numericEffectFunction, actionCost,
                nTransitions, pseudoGoal, transition2cptransition, cptransition2transition);
    }

    private static <T> void addCondeff(HashSetValuedHashMap<Condition, T> condeffs, Condition condition, T eff) {
        if (condeffs.containsKey(condition)){
            condeffs.get(condition).add(eff);
        } else {
            condeffs.put(condition, eff);
        }
    }

    private static <T> void fillSimpleCondeff(HashSetValuedHashMap<Condition, T> condeffs, Condition condition, T effect){
        Condition res = new AndCond(new HashSet<>());
        if (!condition.isValid()) {res = res.and(condition);}
        addCondeff(condeffs, condition, effect);
    }

    private static void addElement(ArrayList<HomeMadeRealInterval> incrementalIntervalSequence, HomeMadeRealInterval interval, double min, double max) {

        if (interval.lo() < min) {
            if (min > 0 && interval.lo() < 0){
                incrementalIntervalSequence.add(0, new HomeMadeRealInterval(0, min));
                incrementalIntervalSequence.add(0, new HomeMadeRealInterval(interval.lo(), 0));
            } else {
                incrementalIntervalSequence.add(0, new HomeMadeRealInterval(interval.lo(), min));
            }
        }

        if (interval.hi() > max) {
            if (max < 0  && interval.hi() > 0){
                incrementalIntervalSequence.add(new HomeMadeRealInterval(max, 0));
                incrementalIntervalSequence.add(new HomeMadeRealInterval(0, interval.hi()));
            } else {
                incrementalIntervalSequence.add(new HomeMadeRealInterval(max, interval.hi()));
            }
        }

    }


    private static void fillNewCondeffs(HashSetValuedHashMap<Condition, Object> condeffs, Condition condition, NumEffect neff, List<HomeMadeRealInterval> intervals){
        Condition res = new AndCond(new HashSet<>());
        if (!condition.isValid()) {res = res.and(condition);}

        if (intervals == null) {
            ArrayList<HomeMadeRealInterval> incrementalIntervalSequence = new ArrayList<>();
            int i = 0;
            double min = Double.MAX_VALUE;
            double max = -Double.MAX_VALUE;
            for (RelState s: relaxedStates){
                HomeMadeRealInterval interval = neff.getRight().eval(s);
                interval.setSup(Math.round(interval.hi()));
                interval.setInf(Math.round(interval.lo()));
                if (i == 0) {
                    incrementalIntervalSequence.add(interval);
                } else {
                    addElement(incrementalIntervalSequence, interval, min, max);
                }
                if (interval.lo() < min){
                    min = interval.lo();
                }
                if (interval.hi() > max) {
                    max = interval.hi();
                }
                i++;
            }
            intervals = sample_intervals(incrementalIntervalSequence);
        }

        if (totIntervals == 0) {
            System.out.println("Example Interval Set:");
            System.out.println(intervals);
        }

        int i = 0;
        //System.out.println(intervals);
        totIntervals += intervals.size();
        compiledEffects++;
        for (HomeMadeRealInterval interval: intervals){
            boolean skip = false;
            Condition cond_interval = null;
            double constant_effect = 0;
            // Do some checks on the interval here!
//            if (interval.lo() < -1e9){
//                cond_interval = res.and(Comparison.comparison("<", neff.getRight(), new PDDLNumber(interval.hi()), false));
//                assert interval.hi() <= 0;
//                constant_effect = interval.hi() -1;
//            } else if (interval.hi() > 1e9){
//                assert interval.lo() >= 0;
//                cond_interval = res.and(Comparison.comparison(">", neff.getRight(), new PDDLNumber(interval.lo()), false));
//                constant_effect = interval.lo() + 1;
//            } else {
            if (interval.lo() < -1e9) { cond_interval = res.and(Comparison.comparison(Comparison.Comparator.LT, neff.getRight(), new PDDLNumber(interval.hi()), false)); assert interval.hi() <= 0; constant_effect = interval.hi() - 1; }
            else if (interval.hi() > 1e9) { assert interval.lo() >= 0; cond_interval = res.and(Comparison.comparison(Comparison.Comparator.GT, neff.getRight(), new PDDLNumber(interval.lo()), false)); constant_effect = interval.lo() + 1; }
            else {
                // TEST THIS CASE
                // TODO: make this a parameter
                constant_effect = (interval.lo() + interval.hi()) / 2.0;
//                if ((interval.lo() + interval.hi() / 2.0) > 0){
//                    constant_effect = interval.hi();
//                } else {
//                    constant_effect = interval.lo();
//                }

                if (interval.hi() > 0) {
                    if (interval.lo() == interval.hi() && i < (intervals.size() - 1)) {// If this is not the last interval, then we can skip.
//                        cond_interval = res.and(Comparison.comparison(">=", neff.getRight(), new PDDLNumber(interval.lo()), false));
//                        if (i < (intervals.size() - 1) && intervals.get(i+1).lo() == interval.hi()){
//                            skip = true;
//                        }
                        skip = true;
                    }

                    cond_interval = res.and(Comparison.comparison(Comparison.Comparator.GT, neff.getRight(), new PDDLNumber(interval.lo()), false));

                } else {
                    if (interval.lo() == interval.hi() && i > 0) {// If this is not the first interval, then we can skip.
                        skip = true;
                    }
                    cond_interval = res.and(Comparison.comparison(Comparison.Comparator.LT, neff.getRight(), new PDDLNumber(interval.hi()), false));
                }
            }

            if (!skip) {
                NumEffect new_eff = new NumEffect(neff.operator, neff.getFluentAffected(), new ExtendedNormExpression(constant_effect));
                addCondeff(condeffs, cond_interval, new_eff);
            }
            i++;
        }
    }

    private static List<HomeMadeRealInterval> sample_intervals(List<HomeMadeRealInterval> intervals) {
        // Keep the first X intervals where X is the parameter provided
        if (linearEffectsAbstraction > 0) {
            ArrayList<HomeMadeRealInterval> neg_intervals = new ArrayList<>();
            ArrayList<HomeMadeRealInterval> pos_intervals = new ArrayList<>();
            for (HomeMadeRealInterval interval: intervals){
                if (interval.lo() < 0) {
                    neg_intervals.add(0, interval);
                } else if (interval.hi() > 0) {
                    pos_intervals.add(interval);
                }
            }
            if (neg_intervals.size() > linearEffectsAbstraction) {
                neg_intervals = new ArrayList<>(neg_intervals.subList(0, linearEffectsAbstraction));
            }
            if (pos_intervals.size() > linearEffectsAbstraction) {
                pos_intervals = new ArrayList<>(pos_intervals.subList(0, linearEffectsAbstraction));
            }
            Collections.reverse(neg_intervals);

            ArrayList<HomeMadeRealInterval> new_intervals = new ArrayList<>();
            new_intervals.addAll(neg_intervals);
            new_intervals.addAll(pos_intervals);
            return new_intervals;
        }

        return intervals;
    }

    private static NumEffect normalizeAssign(NumEffect neff){
        if (neff.getOperator().equals("assign")){
            BinaryOp new_rhs = new BinaryOp(neff.getRight(), "-", neff.getFluentAffected(), true);
            return new NumEffect("increase", neff.getFluentAffected(), new ExtendedNormExpression(new_rhs));
        } else {
            return neff;
        }
    }

/** Angel
 * Relaja una condición sustituyendo input fluents por sus bounds.
 * Para expresiones normalizadas (todo a la izquierda):
 * - Si coeficiente > 0: usa upper bound
 * - Si coeficiente < 0: usa lower bound
 */
private static Condition relaxConditionInputs(Condition cond) {
    if (cond == null || p == null) {
        return cond;
    }
    
    // ✅ PROCESAR Comparison ANTES que Terminal (porque Comparison extiende Terminal)
    if (cond instanceof Comparison) {
        Comparison comp = (Comparison) cond;
        Expression left = comp.getLeft();
        
        // Solo procesamos la izquierda (normalizado)
        if (left instanceof ExtendedNormExpression) {
            ExtendedNormExpression expr = (ExtendedNormExpression) left;
            ArrayList<ExtendedAddendum> newSummations = new ArrayList<>();
            boolean modified = false;
            
            for (ExtendedAddendum add : expr.summations) {
                if (add.f != null) {
                    double[] bounds = p.getInputBounds(add.f.getName());
                    
                    if (bounds != null) {
                        // Es un input: sustituir por bound según signo
                        double replacementValue = (add.n > 0) ? bounds[1] : bounds[0];
                        ExtendedAddendum newAdd = new ExtendedAddendum();
                        newAdd.n = add.n * replacementValue;
                        newAdd.f = null;
                        newSummations.add(newAdd);
                        modified = true;
                    } else {
                        // No es input: copiar tal cual
                        newSummations.add(add);
                    }
                } else {
                    // Constante: copiar
                    newSummations.add(add);
                }
            }
            
            if (modified) {
                ExtendedNormExpression newLeft = new ExtendedNormExpression();
                newLeft.summations = newSummations;
                return Comparison.comparison(
                    Comparison.Comparator.fromSymbol(comp.getComparator()),
                    newLeft,
                    comp.getRight(),
                    comp.isNormalized()
                );
            }
        }
        return cond;
    }
    
    // DESPUÉS procesar Terminal (más general)
    if (cond instanceof Terminal) {
        return cond;
    }
    
    if (cond instanceof AndCond) {
        Collection<Object> relaxedSons = new HashSet<>();
        for (Object son : ((AndCond) cond).sons) {
            if (son instanceof Condition) {
                relaxedSons.add(relaxConditionInputs((Condition) son));
            } else {
                relaxedSons.add(son);
            }
        }
        return new AndCond(relaxedSons);
    }
    
    if (cond instanceof OrCond) {
        Collection<Object> relaxedSons = new HashSet<>();
        for (Object son : ((OrCond) cond).sons) {
            if (son instanceof Condition) {
                relaxedSons.add(relaxConditionInputs((Condition) son));
            } else {
                relaxedSons.add(son);
            }
        }
        return new OrCond(relaxedSons);
    }
    
    return cond;
}

private static void buildCondeffsMap(Int2ObjectOpenHashMap<HashSetValuedHashMap<Condition, Object>> tr2condeffs, Collection<TransitionGround> transitions, Condition globalConditions) {
    
    // For every transition
    for (final TransitionGround tr : transitions) {
        HashSetValuedHashMap<Condition, Object> condeffs = new HashSetValuedHashMap<>();
        
        var allCondEffects = tr.getAllConditionalEffects();
        
        // This iterates over every conditional effect. In our case the condition is true but is generalizable.
        for (var entry : allCondEffects.entrySet()) {
            final Condition condition = entry.getKey();
            final Collection<Object> effects = entry.getValue();
            
            // If the relaxation is unitary, just add effects regularly
            if ("unitary".equals(p.getRelaxationDPEX())) {                
                for (Object eff : effects) {
                    addCondeff(condeffs, condition, eff);
                }
                continue;
            }

            // If not, we are in the optimistic or signature compilation. 
            // Then, we need to generate the cartesian product of all effect options.
            List<List<Object>> optionsList = new ArrayList<>();
            
            // For signature analysis, we need the full preconditions (for the conditional case, we use the condition on the effect)
            Condition fullPreconditions = tr.getPreconditions().and(condition);
            

            // For every conditional effect under condition
            for (var effect : effects) {
                if (effect instanceof NumEffect) {

                    // We normalize the effect
                    NumEffect neff = normalizeAssign((NumEffect) effect);
                    
                    // We verify whether the right part of the assignment is only an expression of inputs
                    boolean canSplit = (neff.getRight() instanceof ExtendedNormExpression);
                    
                    if (canSplit) {
                        ExtendedNormExpression expr = (ExtendedNormExpression) neff.getRight();
                        
                        // Only add supported right now for the expression :)!
                        for (ExtendedAddendum ad : expr.summations) {
                            if (ad.f != null) {
                                double[] bounds = p.getInputBounds(ad.f.getName());
                                if (bounds == null) {
                                    canSplit = false;
                                    break;
                                }
                            }
                        }
                    }
                    
                    if (!canSplit) {
                        // It cannot be expanded so we just add it as is
                        optionsList.add(Collections.singletonList((Object) neff));
                    } else if ("signature".equals(p.getRelaxationDPEX())) {
                        // SIGNATURE: we only generate the signature variant of the action
                        optionsList.add(generateSignatureVariants(neff, globalConditions));
                    } else {
                        // OPTIMISTIC: we always generate both variants
                        ExtendedNormExpression expr = (ExtendedNormExpression) neff.getRight();
                        double lowerVal = 0.0;
                        double upperVal = 0.0;
                        
                        for (ExtendedAddendum ad : expr.summations) {
                            if (ad.f == null) {
                                double coefficient = (ad.n != null) ? ad.n : 0.0;
                                lowerVal += coefficient;
                                upperVal += coefficient;
                            } else {
                                double[] bounds = p.getInputBounds(ad.f.getName());
                                double coefficient = (ad.n != null) ? ad.n : 1.0;
        
                                if (coefficient > 0) {
                                    lowerVal += coefficient * bounds[0];
                                    upperVal += coefficient * bounds[1];
                                } else {
                                    lowerVal += coefficient * bounds[1];
                                    upperVal += coefficient * bounds[0];
                                }
                            }
                        }
                        
                        NumEffect effLower = new NumEffect(
                            neff.getOperator(),
                            neff.getFluentAffected(),
                            new ExtendedNormExpression(lowerVal)
                        );
                        NumEffect effUpper = new NumEffect(
                            neff.getOperator(),
                            neff.getFluentAffected(),
                            new ExtendedNormExpression(upperVal)
                        );
                        
                        optionsList.add(Arrays.asList((Object) effLower, (Object) effUpper));
                    }
                    
                } else if (effect instanceof Terminal) {
                    optionsList.add(Collections.singletonList(effect));
                } else {
                    optionsList.add(Collections.singletonList(effect));
                }
            }
            
            // Create cartesian product
            List<List<Object>> cartesian = new ArrayList<>();
            cartesian.add(new ArrayList<>());
            
            for (List<Object> options : optionsList) {
                List<List<Object>> newCartesian = new ArrayList<>();
                for (List<Object> prefix : cartesian) {
                    for (Object option : options) {
                        List<Object> combination = new ArrayList<>(prefix);
                        combination.add(option);
                        newCartesian.add(combination);
                    }
                }
                cartesian = newCartesian;
            }
            
            // We create a unique condition for every combination
            for (int combIdx = 0; combIdx < cartesian.size(); combIdx++) {
                List<Object> combination = cartesian.get(combIdx);
                
                Condition uniqueCondition;
                if (cartesian.size() == 1) {
                    uniqueCondition = condition;
                } else {
                    Collection<Object> sons = new HashSet<>();
                    sons.add(condition);
                    uniqueCondition = new AndCond(sons);
                }
                
                for (Object eff : combination) {
                    addCondeff(condeffs, uniqueCondition, eff);
                }
            }
        }
        
        tr2condeffs.put(tr.getId(), condeffs);
    }
}

   /* 
    private static void buildCondeffsMap(Int2ObjectOpenHashMap<HashSetValuedHashMap<Condition, Object>> tr2condeffs, Collection<TransitionGround> transitions) {

        // ASSUMPTION: ALL EFFECTS ARE CONDITIONAL
        for (final TransitionGround tr : transitions) {

            HashSetValuedHashMap<Condition, Object> condeffs = new HashSetValuedHashMap<>();
            // TODO; Check if there is a superclass or an interface for Numeff and Terminal

            for (var v : tr.getAllConditionalEffects().entrySet()) {
                for (var t: v.getValue()){
                    Condition condition = v.getKey();
                    if (t instanceof NumEffect neff) {
                        neff = normalizeAssign(neff);
                        if (neff.getInvolvedNumericFluents().size() > 0 && linearEffectsAbstraction >= 0 && !metricVars.contains(neff.getFluentAffected())) {
                            // Non constant effects
                            if (linearEffectsAbstraction == 0) {
                                List<HomeMadeRealInterval> intervals = new ArrayList<>(); // TODO; use AIBR to extract intervals
                                intervals.add(new HomeMadeRealInterval(-Double.MAX_VALUE, 0));
                                intervals.add(new HomeMadeRealInterval(0, Double.MAX_VALUE));
                                fillNewCondeffs(condeffs, condition, neff, intervals);
                            } else {
                                fillNewCondeffs(condeffs, condition, neff, null);
                            }

                        }
                        else { fillSimpleCondeff(condeffs, condition, neff);}
                    }
                    else { if (t instanceof Terminal term) {fillSimpleCondeff(condeffs, condition, term); }}
                }
            }

            tr2condeffs.put(tr.getId(), condeffs);
        }
    }
    */
    private static int fillPreEff(int offset, String redConstraints, Collection<TransitionGround> transitions) {

        int i = offset;

        Collection<TransitionGround> filteredTransitions = transitions;
        if (!p.getRelaxationDPEX().equals("unitary")) {
            // If the relaxation is not unitary, filter the control transitions
            filteredTransitions = new LinkedHashSet<>();
            for (TransitionGround tr : transitions) {
                String name = tr.getName().toLowerCase();
                if (!name.startsWith("increase_control") && !name.startsWith("decrease_control")) {
                    filteredTransitions.add(tr);
                }
            }
        }


        if (conditionalEffectsSensitive) {

            final Int2ObjectOpenHashMap<HashSetValuedHashMap<Condition, Object>> tr2condeffs = new Int2ObjectOpenHashMap<>();

            // This method has changed
            Condition globalConditions = collectAllProblemConditions(filteredTransitions);
            buildCondeffsMap(tr2condeffs, filteredTransitions, globalConditions);

            for (final TransitionGround b : filteredTransitions) {
                HashSetValuedHashMap<Condition, Object> condeffs = tr2condeffs.get(b.getId());
                for (Condition condition : condeffs.keySet()) {
                    /*System.out.println("DEBUG fillPreEff: Procesando cp-index=" + i + " para transición original=" + b.getId() + " (" + b.getName() + ")");
                    System.out.println("DEBUG fillPreEff: Condición clave: " + condition);
                    System.out.println("DEBUG fillPreEff: Efectos en condeffs para esta condición: " + condeffs.get(condition));*/
                    
                    Condition c = b.getPreconditions().and(condition);

                    // Normalizar
                    Condition normalized = normalizeAndTighthenCondition(c, redConstraints);

                    // Relax preconditions on optimistic and signature compilations
                    if ("optimistic".equals(p.getRelaxationDPEX()) || "signature".equals(p.getRelaxationDPEX())) {
                        normalized = relaxConditionInputs(normalized);
                    }

                    preconditionFunctionMap.put(i, normalized);                    
                    
                    //preconditionFunctionMap.put(i, normalizeAndTighthenCondition(c, redConstraints));
                    final IntArraySet propositional = new IntArraySet();
                    final Collection numEffect = new LinkedHashSet();
                    for (var effect : condeffs.get(condition)) {
                        if (effect instanceof Terminal term) {
                            propositional.add(term.getId());
                        }
                        if (effect instanceof NumEffect neff) {
                            numEffect.add(neff);
                        }
                    }
                    Collection actions = (Collection) transition2cptransitionMap.get(b.getId());
                    if (actions == null) {
                        actions = new IntArraySet();
                    }
                    actions.add(i);
                    transition2cptransitionMap.put(b.getId(), actions);
                    cptransition2transitionMap.put(i, b.getId());
                    propEffectFunctionMap.put(i, propositional);
                    numericEffectFunctionMap.put(i, numEffect);
                    i++;
                }

                
                for (TransitionGround tr : transitions) {
                    String name = tr.getName().toLowerCase();
                    if (name.startsWith("increase_control") || name.startsWith("decrease_control")) {
                        transition2cptransitionMap.put(tr.getId(), new IntArraySet());
                    }
                }

            }
        } else {
            for (final TransitionGround b : transitions) {
                i++;
                transition2cptransition[b.getId()] = Collections.singleton(b.getId());
                cptransition2transition[b.getId()] = b.getId();

                preconditionFunction[b.getId()] = normalizeAndTighthenCondition(b.getPreconditions(), redConstraints);

                final IntArraySet propositional = new IntArraySet();
                for (Terminal t : b.getAllAchievableLiterals()) {
                    propositional.add(t.getId());
                }
                propEffectFunction[b.getId()] = propositional;
                numericEffectFunction[b.getId()] = b.getConditionalNumericEffects().getAllEffects();
                for (final NumEffect neff : numericEffectFunction[b.getId()]) {
                    neff.normalize();
                }
                actionCost[b.getId()] = b.getActionCost(p.getInit(), p.getMetric(), p.isSdac());
            }
        }
        return i;
    }

    private static Condition normalizeAndTighthenCondition(Condition preconditions, String redConstraints) {
        switch (redConstraints) {
            case "smart":
                if (redundantMap == null || redundantMap.isEmpty()) {
                    return preconditions.transformEquality();
                }
                return addSmartRedundantConstraints(preconditions.transformEquality());
            case "brute":
                return preconditions.transformEquality().normalize().introduce_red_constraints();
            default:
                return preconditions.transformEquality().normalize();
        }
    }

    private static Condition addSmartRedundantConstraints(Condition cond) {
        if (cond instanceof Terminal) {
            return cond;
        }
        if (cond instanceof OrCond) {
            Collection newOr = new HashSet();
            for (var v : ((OrCond) cond).sons) {
                newOr.add(addSmartRedundantConstraints((Condition) v));
            }
            return new OrCond(newOr);
        }
        if (cond instanceof AndCond) {
            Collection and = new HashSet();
            Collection<IntArraySet> get = redundantMap.get((AndCond) cond);
            for (var v : ((AndCond) cond).sons) {
                and.add((Condition) v);
            }
            if (get != null) {
                System.out.println("One Redundant Constraint added");
                for (var v : get) {
                    Comparison previous = null;
                    for (int i : v) {
                        if (previous != null) {
                            previous = AndCond.generateRedConstraints((Comparison) Comparison.getTerminal(i), previous);
                        } else {
                            previous = (Comparison) Comparison.getTerminal(i);
                        }

                    }
                    if (previous != null) {
                        and.add(previous);
                    }
                }
            }
            return new AndCond(and);
        } else {
            throw new RuntimeException("This was unexepected:" + cond);
        }

    }



    /**
     * Analiza qué variantes (lower/upper) son necesarias para cada input en un efecto numérico,
     * basándose en cómo aparece el fluent afectado en las precondiciones.
     * 
     * @param preconditions Las precondiciones de la acción
     * @param affectedFluentName El nombre del fluent que se ve afectado por el efecto
     * @return Conjunto con "lower", "upper", o ambos, indicando qué variantes son necesarias
     */
    private static Set<String> analyzeNecessaryVariantsForFluent(Condition preconditions, NumFluent affectedFluent, String operator) {
        Set<String> signs = new HashSet<>();
        
        if (preconditions == null) {
            // Sin precondiciones, generamos ambas por seguridad
            signs.add("lower");
            signs.add("upper");
            return signs;
        }
        
        // Buscar comparaciones que involucren el fluent afectado
        collectFluentSigns(preconditions, affectedFluent, signs, operator);
        
        // Si no encontramos el fluent en ninguna precondición, generamos ambas por seguridad
        if (signs.isEmpty()) {
            signs.add("lower");
            signs.add("upper");
        }
        
        return signs;
    }

    /**
     * Método auxiliar recursivo para recolectar los signos de un fluent en las precondiciones.
     */
    private static void collectFluentSigns(Condition cond, NumFluent affectedFluent, Set<String> signs, String operator) {
        if (cond instanceof Comparison) {
            Comparison comp = (Comparison) cond;
            Expression left = comp.getLeft();
            
            // Las precondiciones están normalizadas, así que todo está en el lado izquierdo
            if (left instanceof ExtendedNormExpression) {
                ExtendedNormExpression expr = (ExtendedNormExpression) left;
                
                for (ExtendedAddendum ad : expr.summations) {
                    if (ad.f != null && ad.f.equals(affectedFluent)) {
                        double coefficient = (ad.n != null) ? ad.n : 1.0;
                        boolean isIncrease = "increase".equals(operator);

                        if (coefficient > 0) {
                            signs.add(isIncrease ? "upper" : "lower");
                        } else if (coefficient < 0) {
                            signs.add(isIncrease ? "lower" : "upper");
                        }
                    }
                }
            }
        } else if (cond instanceof AndCond) {
            for (Object son : ((AndCond) cond).sons) {
                if (son instanceof Condition) {
                    collectFluentSigns((Condition) son, affectedFluent, signs, operator);
                }
            }
        } else if (cond instanceof OrCond) {
            // En el caso de OR, necesitamos ambas variantes para cubrir todas las ramas
            for (Object son : ((OrCond) cond).sons) {
                if (son instanceof Condition) {
                    collectFluentSigns((Condition) son, affectedFluent, signs, operator);
                }
            }
        }
    }

    /**
     * Genera las variantes necesarias de un efecto numérico para el modo "signature".
     * Analiza cada input en el efecto y determina si necesita lower, upper, o ambos bounds.
     * 
     * @param neff El efecto numérico a procesar
     * @param preconditions Las precondiciones completas de la acción
     * @return Lista de efectos numéricos (variantes necesarias)
     */
    private static List<Object> generateSignatureVariants(NumEffect neff, Condition preconditions) {
        ExtendedNormExpression expr = (ExtendedNormExpression) neff.getRight();
        
        // Determinar qué variantes son necesarias según las precondiciones
        NumFluent affectedFluent = neff.getFluentAffected();
        String operator = neff.getOperator();
        Set<String> necessaryVariants = analyzeNecessaryVariantsForFluent(preconditions, affectedFluent,operator);
        
        // Si solo necesitamos una variante, simplificamos
        if (necessaryVariants.size() == 1) {
            String variant = necessaryVariants.iterator().next();
            double value = 0.0;
            
            for (ExtendedAddendum ad : expr.summations) {
                if (ad.f == null) {
                    // Constante
                    value += (ad.n != null) ? ad.n : 0.0;
                } else {
                    // Input fluent
                    double[] bounds = p.getInputBounds(ad.f.getName());
                    double coefficient = (ad.n != null) ? ad.n : 1.0;
                    
                    if ("upper".equals(variant)) {
                        // Queremos maximizar el efecto
                        value += (coefficient > 0) ? coefficient * bounds[1] : coefficient * bounds[0];
                    } else {
                        // Queremos minimizar el efecto
                        value += (coefficient > 0) ? coefficient * bounds[0] : coefficient * bounds[1];
                    }
                }
            }
            
            NumEffect effVariant = new NumEffect(
                neff.getOperator(),
                neff.getFluentAffected(),
                new ExtendedNormExpression(value)
            );
            return Collections.singletonList((Object) effVariant);
        }
        
        // Si necesitamos ambas variantes, generamos lower y upper
        double lowerVal = 0.0;
        double upperVal = 0.0;
        
        for (ExtendedAddendum ad : expr.summations) {
            if (ad.f == null) {
                // Constante
                double coefficient = (ad.n != null) ? ad.n : 0.0;
                lowerVal += coefficient;
                upperVal += coefficient;
            } else {
                // Input fluent
                double[] bounds = p.getInputBounds(ad.f.getName());
                double coefficient = (ad.n != null) ? ad.n : 1.0;
                
                if (coefficient > 0) {
                    lowerVal += coefficient * bounds[0];
                    upperVal += coefficient * bounds[1];
                } else {
                    lowerVal += coefficient * bounds[1];
                    upperVal += coefficient * bounds[0];
                }
            }
        }
        
        NumEffect effLower = new NumEffect(
            neff.getOperator(),
            neff.getFluentAffected(),
            new ExtendedNormExpression(lowerVal)
        );
        NumEffect effUpper = new NumEffect(
            neff.getOperator(),
            neff.getFluentAffected(),
            new ExtendedNormExpression(upperVal)
        );
        
        return Arrays.asList((Object) effLower, (Object) effUpper);
    }

    /**
     * Recopila TODAS las condiciones del problema: goal + precondiciones de todas las acciones.
     * Esto es necesario para la compilación signature, ya que debemos analizar cómo se usa
     * cada fluent en TODO el problema, no solo en la acción actual.
     */
    private static Condition collectAllProblemConditions(Collection<TransitionGround> transitions) {
        List<Condition> allConditions = new ArrayList<>();
        
        // Añadir el goal
        if (p.getGoals() != null) {
            allConditions.add(p.getGoals());
        }
        
        // Añadir las precondiciones de todas las transiciones
        for (TransitionGround tr : transitions) {
            if (tr.getPreconditions() != null) {
                allConditions.add(tr.getPreconditions());
            }
            
            // También añadir las condiciones de los efectos condicionales
            var allCondEffects = tr.getAllConditionalEffects();
            for (var entry : allCondEffects.entrySet()) {
                Condition condition = entry.getKey();
                // Solo agregar condiciones no-null y no-triviales
                if (condition != null && !(condition instanceof Terminal)) {
                    allConditions.add(condition);
                }
            }
        }
        
        // Combinar todas las condiciones con AND
        if (allConditions.isEmpty()) {
            // Condición vacía = "true"
            return new AndCond(new HashSet<>());
        } else if (allConditions.size() == 1) {
            return allConditions.get(0);
        } else {
            return new AndCond(allConditions);
        }
    }

}
