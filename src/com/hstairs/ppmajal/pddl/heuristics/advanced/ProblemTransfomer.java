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
    private static boolean linearEffectsAbstraction = false;
    private static int pseudoGoal;
    private static Int2ObjectOpenHashMap preconditionFunctionMap;
    private static Int2ObjectOpenHashMap propEffectFunctionMap;
    private static Int2ObjectOpenHashMap numericEffectFunctionMap;
    private static Int2ObjectOpenHashMap transition2cptransitionMap;
    private static Int2IntOpenHashMap cptransition2transitionMap;
    private static Set<NumFluent> metric_vars = new HashSet<>();

    public static CompactPDDLProblem generateCompactProblem(PDDLProblem problem, String redConstraints, boolean unitaryCost, boolean linearEffectsAbstraction) {
        int nTransitions = Transition.totNumberOfTransitions + 1;
        pseudoGoal = nTransitions - 1;
        p = problem;

        if (p.getMetric() != null){
            metric_vars = p.getMetric().getMetExpr().getInvolvedNumericFluents();
        }

        ProblemTransfomer.linearEffectsAbstraction = linearEffectsAbstraction;

        // TODO: Call AIBR Here!

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

    private static void fillNewCondeffs(HashSetValuedHashMap<Condition, Object> condeffs, Condition condition, NumEffect neff, List<HomeMadeRealInterval> intervals){
        Condition res = new AndCond(new HashSet<>());
        if (!condition.isValid()) {res = res.and(condition);}

        for (HomeMadeRealInterval interval: intervals){
            Condition cond_interval = null;
            double constant_effect = 0;
            // Do some checks on the interval here!
            if (interval.lo() == -Double.MAX_VALUE){
                cond_interval = res.and(Comparison.comparison("<", neff.getRight(), new PDDLNumber(interval.hi()), false));
                constant_effect = -1;
            } else if (interval.hi() == Double.MAX_VALUE){
                cond_interval = res.and(Comparison.comparison(">", neff.getRight(), new PDDLNumber(interval.lo()), false));
                constant_effect = 1;
            } else {
                // TEST THIS CASE
                cond_interval = res.and(Comparison.comparison(">", neff.getRight(), new PDDLNumber(interval.lo()), false));
                cond_interval = cond_interval.and(Comparison.comparison("<", neff.getRight(), new PDDLNumber(interval.hi()), false));
                constant_effect = (interval.lo() + interval.hi()) / 2.0;
            }

            NumEffect new_eff = new NumEffect(neff.operator, neff.getFluentAffected(), new ExtendedNormExpression(constant_effect));

            addCondeff(condeffs, cond_interval, new_eff);
        }
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
                        if (neff.getInvolvedNumericFluents().size() > 0 && linearEffectsAbstraction && !metric_vars.contains(neff.getFluentAffected())) {
                            // Non constant effects
                            List<HomeMadeRealInterval> intervals = new ArrayList<>(); // TODO; use AIBR to extract intervals
                            intervals.add(new HomeMadeRealInterval(-Double.MAX_VALUE, 0));
                            intervals.add(new HomeMadeRealInterval(0, Double.MAX_VALUE));
                            fillNewCondeffs(condeffs, condition, neff, intervals);
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
