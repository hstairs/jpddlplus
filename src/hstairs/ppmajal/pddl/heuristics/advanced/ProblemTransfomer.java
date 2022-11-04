/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.OrCond;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import hstairs.ppmajal.pddl.heuristics.advanced.CompactPDDLProblem;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

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
    private static int pseudoGoal;
    private static Int2ObjectOpenHashMap preconditionFunctionMap;
    private static Int2ObjectOpenHashMap propEffectFunctionMap;
    private static Int2ObjectOpenHashMap numericEffectFunctionMap;
    private static Int2ObjectOpenHashMap transition2cptransitionMap;
    private static Int2IntOpenHashMap cptransition2transitionMap;

    public static CompactPDDLProblem generateCompactProblem(PDDLProblem problem, String redConstraints) {
        int nTransitions = Transition.totNumberOfTransitions + 1;
        pseudoGoal = nTransitions - 1;
        p = problem;

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
                actionCost[v1] = t.getActionCost(p.getInit(), p.getMetric(), p.isSdac());
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

    private static int fillPreEff(int offset, String redConstraints, Collection<TransitionGround> transitions) {

        int i = offset;

        if (conditionalEffectsSensitive) {

            for (final TransitionGround b : transitions) {
                for (var v : b.getAllConditionalEffects().entrySet()) {
                    Condition c = null;
                    if (v.getKey().isValid()) {
                        c = b.getPreconditions();
                    } else {
                        c = b.getPreconditions().and(v.getKey());
                    }
                    preconditionFunctionMap.put(i, normalizeAndTighthenCondition(c, redConstraints));
                    final IntArraySet propositional = new IntArraySet();
                    final Collection numEffect = new LinkedHashSet();
                    for (var t : v.getValue()) {
                        if (t instanceof Terminal term) {
                            propositional.add(term.getId());
                        }
                        if (t instanceof NumEffect neff) {
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
                return preconditions.transformEquality().introduce_red_constraints();
            default:
                return preconditions.transformEquality();
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
