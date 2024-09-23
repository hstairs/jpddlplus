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

    public static CompactPDDLProblem generateCompactProblem(PDDLProblem problem, String redConstraints, boolean unitaryCost, int linearEffectsAbstraction) {
        int nTransitions = Transition.totNumberOfTransitions + 1;
        pseudoGoal = nTransitions - 1;
        p = problem;

        if (p.getMetric() != null){
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
            if (interval.lo() < -1e9){
                cond_interval = res.and(Comparison.comparison("<", neff.getRight(), new PDDLNumber(interval.hi()), false));
                assert interval.hi() <= 0;
                constant_effect = interval.hi() -1 ;
            } else if (interval.hi() > 1e9){
                assert interval.lo() >= 0;
                cond_interval = res.and(Comparison.comparison(">", neff.getRight(), new PDDLNumber(interval.lo()), false));
                constant_effect = interval.lo() + 1;
            } else {
                // TEST THIS CASE
                // TODO: make this a parameter
                constant_effect = (interval.lo() + interval.hi()) / 2.0;
                // constant_effect = interval.hi();
                if (interval.hi() > 0) {
                    if (interval.lo() == interval.hi() && i < (intervals.size() - 1)) {// If this is not the last interval, then we can skip.
//                        cond_interval = res.and(Comparison.comparison(">=", neff.getRight(), new PDDLNumber(interval.lo()), false));
//                        if (i < (intervals.size() - 1) && intervals.get(i+1).lo() == interval.hi()){
//                            skip = true;
//                        }
                        skip = true;
                    }

                    cond_interval = res.and(Comparison.comparison(">", neff.getRight(), new PDDLNumber(interval.lo()), false));

                } else {
                    if (interval.lo() == interval.hi() && i > 0) {// If this is not the first interval, then we can skip.
                        skip = true;
                    }
                    cond_interval = res.and(Comparison.comparison("<", neff.getRight(), new PDDLNumber(interval.lo()), false));
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

    private static int fillPreEff(int offset, String redConstraints, Collection<TransitionGround> transitions) {

        int i = offset;

        if (conditionalEffectsSensitive) {

            final Int2ObjectOpenHashMap<HashSetValuedHashMap<Condition, Object>> tr2condeffs = new Int2ObjectOpenHashMap<>();

            buildCondeffsMap(tr2condeffs, transitions);

            for (final TransitionGround b : transitions) {
                HashSetValuedHashMap<Condition, Object> condeffs = tr2condeffs.get(b.getId());
                for (Condition condition : condeffs.keySet()) {
                    Condition c = b.getPreconditions().and(condition);
                    preconditionFunctionMap.put(i, normalizeAndTighthenCondition(c, redConstraints));
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

}
