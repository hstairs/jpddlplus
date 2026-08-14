/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.ExtendedAddendum;
import com.hstairs.ppmajal.expressions.ExtendedNormExpression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.ArrayShifter;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.util.*;

import static com.hstairs.ppmajal.transition.Transition.getTransition;

/**
 * @author enrico
 */
public class lmcutOld implements SearchHeuristic  {

    /**
     * @return the heuristicNumberOfActions
     */

    static final boolean DEBUG = false;
    final public boolean extractRelaxedPlan;
    final public boolean maxMRP;

    public final CompactPDDLProblem cp;
    protected final int totNumberOfTerms;
    protected final int totNumberOfTermsRefactored;

    protected final PDDLProblem problem;
    final private boolean helpfulActionsComputation;
    final IntArraySet[] conditionsAchievableBy;
    final IntArraySet[] conditionsDeletableBy;
    final IntArraySet[] conditionToAction;
    final IntArraySet allConditions;
    private final IntArraySet allComparisons;
    protected final FibonacciHeapNode[] nodeOf;
    private final int[] pcf;
    boolean reachability;
    private final boolean conjunctionsMax;

    final float[] actionHCost;
    private final float[] conditionCost;
    protected final boolean[] closed;

    final boolean additive;
    private final boolean[] conditionInit;
    private final boolean[] actionInit;
    private final boolean helpfulTransitions;
    private final boolean hardcoreVersion;
    private final float[][] numericContributionRaw;
    private final Map<Pair<Integer, Integer>, Float> numericContribution;
    protected final ArrayShifter termsArrayShifter;
    protected final ArrayShifter actionsArrayShifter;
    protected final int totNumberOfActionsRefactored;
    IntArraySet[] allAchievers;
    final private IntArraySet[] deleters;
    private List helpfulActions;
    IntArraySet reachableTransitions;
    private Collection<TransitionGround> reachableTransitionsInstances;
    int root;

    final float UNKNOWNEFFECT = Float.NEGATIVE_INFINITY;
    final protected IntArraySet freePreconditionActions;
    private IntArraySet plan;
    final protected IntArraySet[] repetitionsInThePlan;
    protected IntArraySet allActions;

    final boolean useSmartConstraints;

    public boolean[] helpfulTransitionsMap = null;

    public lmcutOld(PDDLProblem problem) {
        this(problem, true, false, false, "no", false, false, false, false, null, false, -1, false);
    }


    public lmcutOld(PDDLProblem problem, boolean additive) {
        this(problem, additive, false, false, "no", false, false, false, false, null, false, -1, false);
    }

    public lmcutOld(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions, String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                    boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost, int linearEffectsAbstraction) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions,
                redConstraints, helpfulActionsComputation, reachability,
                helpfulTransitions, conjunctionsMax, null, unitaryCost, linearEffectsAbstraction, false);
    }

    public lmcutOld(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions, String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                    boolean helpfulTransitions, boolean conjunctionsMax, boolean unitaryCost) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions,
                redConstraints, helpfulActionsComputation, reachability, helpfulTransitions,
                conjunctionsMax, null, unitaryCost, -1, false);
    }

    public lmcutOld(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions, String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                    boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond,
                    Collection<IntArraySet>> redundantMap, boolean unitaryCost, int compNumericStrategy) {
        this(problem, additive, extractRelaxedPlan, maxHelpfulTransitions, redConstraints,
                helpfulActionsComputation, reachability, helpfulTransitions, conjunctionsMax,
                redundantMap, unitaryCost, compNumericStrategy, false);
    }

    public lmcutOld(PDDLProblem problem, boolean additive, boolean extractRelaxedPlan, boolean maxHelpfulTransitions, String redConstraints, boolean helpfulActionsComputation, boolean reachability,
                    boolean helpfulTransitions, boolean conjunctionsMax, Map<AndCond,
                    Collection<IntArraySet>> redundantMap, boolean unitaryCost, int compNumericStrategy, boolean ssnpAwareVersion) {
        long startSetup = System.currentTimeMillis();
        this.additive = additive;
        this.problem = problem;
        this.reachability = reachability;
        this.helpfulActionsComputation = helpfulActionsComputation;
        this.extractRelaxedPlan = extractRelaxedPlan;
        allComparisons = new IntArraySet();
        freePreconditionActions = new IntArraySet();
        final boolean hardConditionThroughNumError = compNumericStrategy > -2;
        if (hardConditionThroughNumError)
            System.out.println("Numeric Error for Complex Condition Activated");
        cp = ProblemTransfomer.generateCompactProblem(problem, redConstraints, unitaryCost, compNumericStrategy);
        useSmartConstraints = "smart".equals(redConstraints);

        totNumberOfTerms = Terminal.getTotCounter();
        conditionsAchievableBy = new IntArraySet[cp.numActions()];
        conditionToAction = new IntArraySet[totNumberOfTerms];
        allConditions = new IntArraySet();
        allActions = new IntArraySet();

        nodeOf = new FibonacciHeapNode[cp.numActions()];
        fillPreEffFunctions(new LinkedHashSet(problem.actions));
        fillPreEffFunctions(new LinkedHashSet(problem.getEventsSet()));
        fillPreEffFunctions(new LinkedHashSet(problem.getProcessesSet()));

        allActions.add(cp.goal());
        updatePreconditionFunction(cp.goal());

        termsArrayShifter = new ArrayShifter(getAllConditions());
        totNumberOfTermsRefactored = termsArrayShifter.getMaxTid();

        actionsArrayShifter = new ArrayShifter(allActions);
        totNumberOfActionsRefactored = actionsArrayShifter.getMaxTid();

        actionHCost = new float[cp.numActions()];
        conditionCost = new float[totNumberOfTerms];
        closed = new boolean[cp.numActions()];

        root = getTotNumberOfTerms();

        hardcoreVersion = cp.numActions() * totNumberOfTermsRefactored < 1999999999;
        if (hardcoreVersion) {
            numericContributionRaw = new float[totNumberOfActionsRefactored][totNumberOfTermsRefactored];
            for (final float[] row : numericContributionRaw) {
                Arrays.fill(row, Float.MAX_VALUE);
            }
            numericContribution = null;
        } else {
            numericContributionRaw = null;
            numericContribution = new HashMap<>();
        }

        conditionInit = new boolean[totNumberOfTerms];
        actionInit = new boolean[cp.numActions()];
        pcf = new int[cp.numActions()];
        Arrays.fill(pcf, -1);
        if (extractRelaxedPlan || useSmartConstraints || helpfulActionsComputation) {
            allAchievers = new IntArraySet[totNumberOfTerms];
        }
        if (useSmartConstraints) {
            deleters = new IntArraySet[totNumberOfTerms];
            conditionsDeletableBy = new IntArraySet[cp.numActions()];
        } else {
            deleters = null;
            conditionsDeletableBy = null;
        }
        this.helpfulTransitions = helpfulTransitions;

        maxMRP = maxHelpfulTransitions;
        this.conjunctionsMax = conjunctionsMax;
        System.out.println("H1 Setup Time (msec): " + (System.currentTimeMillis() - startSetup));
        repetitionsInThePlan = extractRelaxedPlan || helpfulActionsComputation
                ? new IntArraySet[Transition.totNumberOfTransitions + 1]
                : null;
    }

    private void fillPreEffFunctions(LinkedHashSet<TransitionGround> transitions) {

        for (final TransitionGround b : transitions) {
            for (final int i : cp.tr2CpTrMap()[b.getId()]) {
                allActions.add(i);
                updatePreconditionFunction(i);
            }
        }

    }


    void updatePreconditionFunction(int i) {
        final Collection<Condition> terminalConditions = cp.preconditionFunction()[i].getTerminalConditionsInArray();
        if (terminalConditions.isEmpty()) {
            freePreconditionActions.add(i);
        }
        for (final Condition c : terminalConditions) {
            if (c instanceof Terminal) {
                final Terminal t = (Terminal) c;
                IntArraySet groundActions = getConditionToAction()[((Terminal) c).getId()];
                if (groundActions == null) {
                    groundActions = new IntArraySet();
                }
                groundActions.add(i);
                conditionToAction[t.getId()] = groundActions;
                getAllConditions().add(((Terminal) c).getId());
                if (c instanceof Comparison) {
                    final Comparison normalize = (Comparison) c.normalize();
                    getAllComparisons().add(normalize.getId());
                }
            }
        }
    }


    record Supp(int act, int cond) {
    }

    ;

    private record JGraph(Set<Supp>[] E, Set<Integer>[] ERev) {
    }

    ;

    public Pair<JGraph, Float> constructJG(State gs) {
        Set<Supp>[] E = new HashSet[getTotNumberOfTerms() + 1];
        Set<Integer>[] ERev = new HashSet[getTotNumberOfTerms() + 1];
        E[root] = new HashSet<>();
        ERev[root] = new HashSet<>();

        Arrays.fill(getActionHCost(), Float.MAX_VALUE);
        Arrays.fill(getConditionCost(), Float.MAX_VALUE);
        Arrays.fill(getClosed(), false);
        Arrays.fill(getActionInit(), false);
        Arrays.fill(getConditionInit(), false);
        allAchievers = new IntArraySet[totNumberOfTerms];
        final FibonacciHeap h = new FibonacciHeap();
        for (final int i : getAllConditions()) {
            if (gs.satisfy(Terminal.getTerminal(i))) {
                conditionCost[i] = 0f;
                conditionInit[i] = true;
            }
            E[i] = new HashSet();
            ERev[i] = new HashSet();
        }
        for (final int freePreconditionAction : freePreconditionActions) {
            actionHCost[freePreconditionAction] = 0f;
            actionInit[freePreconditionAction] = true;
            addActionsInPriority(freePreconditionAction, h, 0f);
            pcf[freePreconditionAction] = root;
        }

        for (var a : allActions) {
            Condition condition = cp.preconditionFunction()[a];
            if (gs.satisfy(condition) && !actionInit[a]) {
                addActionsInPriority(a, h, 0f);
                actionHCost[a] = 0f;
                actionInit[a] = true;
                pcf[a] = root;
            }
        }

        while (!h.isEmpty()) {
            final int actionId = (int) h.removeMin().getData();
            if (!closed[actionId]) {
                closed[actionId] = true;
                if (actionId != cp.goal()) {
                    final IntSet conditionsAchievableByAction = getConditionsAchievableById(actionId);
                    for (final int conditionId : conditionsAchievableByAction) {
                        if (!getConditionInit()[conditionId]) {
                            E[this.pcf[actionId]].add(new Supp(actionId, conditionId));
                            ERev[conditionId].add(actionId);

                            float temp;
                            if (getActionCost()[actionId] == 0f) {
                                temp = 0f;
                            } else {
                                temp = computeRepetition(conditionId, actionId, gs);
                            }
                            if (updateIfNeeded(conditionId, temp)) {
                                updateActions(conditionId, h);
                            }
                        }
                    }
                } else if (actionHCost[actionId] == 0f) {
                    break;
                }
            }
        }
        return Pair.of(new JGraph(E, ERev), actionHCost[cp.goal()]);
    }

    float computeRepetition(int conditionId, int actionId, State gs) {
        final Terminal t = Terminal.getTerminal(conditionId);
        if (t instanceof BoolPredicate || t instanceof NotCond) {//affecting a prop variable
            return getActionCost()[actionId] + getActionHCost()[actionId];
        } else {//affecting a num comparison
            final double v = this.numericContribution(actionId, (Comparison) t);
            if (v > 0) {
                float rep = computeRepetition(t, v, gs);
                final float newCost = rep * getActionCost()[actionId];
                return getActionHCost()[actionId] + newCost;
            }
        }
        return -1f;
    }

    float[] tempActionCost;

    record Cut(int actionId, int conditionId) {
    }

    ;

    @Override
    public float computeEstimate(State gs) {
        float cost = 0f;
        boolean firstTime = true;
        tempActionCost = Arrays.copyOf(cp.actionCost(), cp.actionCost().length);
        while (true) {
            boolean goalZone[] = new boolean[totNumberOfTerms + 1];
            Pair<JGraph, Float> jGraphFloatPair = constructJG(gs);
            JGraph JG = jGraphFloatPair.getFirst();
            if (jGraphFloatPair.getSecond() == 0f || jGraphFloatPair.getSecond() == Float.MAX_VALUE) {
                if (firstTime)
                    return jGraphFloatPair.getSecond();
                else
                    return cost;
            }
            firstTime = false;
            markGoalZone(JG, pcf[cp.goal()], goalZone);
            assert(!goalZone[root]);
            Collection<Cut> cuts = computeCuts(JG, goalZone);
            if (cuts.isEmpty()) {
                return cost;
            }
            float min = Float.POSITIVE_INFINITY;
            float[] actionOut = new float[cp.numActions()];
            Arrays.fill(actionOut, Float.POSITIVE_INFINITY);
            for (var v : cuts) {
                float temp = computeRepetition(v.conditionId, v.actionId, gs);
                if (temp < min) {
                    min = temp;
                }
                actionOut[v.actionId] = Math.min(actionOut[v.actionId], temp);
            }

            if (min <= 0.00001f) {
                return cost;
            }
            for (var v : cuts) {
                getActionCost()[v.actionId] -= min / actionOut[v.actionId];
            }
            cost += min;
        }

    }

    private void markGoalZone(JGraph jg, int starting, boolean[] goalZone) {
        goalZone[starting] = true;
        for (var v : jg.ERev[starting]) {
            if (getActionCost()[v] <= 0f)
                markGoalZone(jg, pcf[v], goalZone);
        }
    }

    private Collection<Cut> computeCuts(JGraph JG, boolean goalZone[]) {

        Collection<Cut> cuts = new ArrayList<Cut>();
        IntArrayFIFOQueue q = new IntArrayFIFOQueue();
        q.enqueue(root);
        IntArraySet closed = new IntArraySet();
        while (!q.isEmpty()) {
            int ele = q.dequeueInt();
            closed.add(ele);
            for (final var v : JG.E[ele]) {
                if (goalZone[v.cond]) {
                    cuts.add(new Cut(v.act, v.cond));
                } else {
                    if (!closed.contains(v.cond))
                        q.enqueue(v.cond);
                }
            }
        }
        return cuts;


    }

    void addActionsInPriority(final int i, final FibonacciHeap p, final float v) {
        final FibonacciHeapNode fibonacciHeapNode = new FibonacciHeapNode(i);
        nodeOf[i] = fibonacciHeapNode;
        p.insert(fibonacciHeapNode, v);
    }

    protected void updateActions(final int c, final FibonacciHeap p) {
        final IntArraySet actions = getConditionToAction()[c];
        if (actions != null) {
            for (final int i : actions) {
                if (!closed[i]) {
                    Pair<Float, Integer> justifier = estimateCost(cp.preconditionFunction()[i]);
                    float v = justifier.getFirst();
                    if (v < Float.MAX_VALUE && !actionInit[i]) {
                        if (v < getActionHCost()[i]) {
                            if (getActionHCost()[i] == Float.MAX_VALUE) {
                                actionHCost[i] = v;
                                addActionsInPriority(i, p, v);
                            } else {
                                actionHCost[i] = v;
                                p.decreaseKey(getNodeOf()[i], v);
                            }

                        }
                        if (justifier == null || justifier.getSecond() == null) {
                            pcf[i] = root;
                        } else {
                            pcf[i] = justifier.getSecond();
                        }
                    }
                }
            }
        }
    }

    @Override
    public Collection getAllEstimates() {
        return SearchHeuristic.super.getAllEstimates(); //To change body of generated methods, choose Tools | Templates.
    }


    public IntArraySet getAchievers(int conditionId) {
        final IntArraySet achievers = getAllAchievers()[conditionId];
        if (achievers == null) {
            getAllAchievers()[conditionId] = new IntArraySet();
        }
        return getAllAchievers()[conditionId];
    }

    protected void updateAchievers(int conditionId, int actionId) {
        getAchievers(conditionId).add(actionId);

    }

    protected boolean updateIfNeeded(final int t, final float value) {
        if (getConditionCost()[t] > value) {
            conditionCost[t] = value;
            return true;
        }
        return false;
    }

    @Override
    public boolean[] getHelpfulTransitionMap() {
        return this.helpfulTransitionsMap;
    }


    private Pair<Float, Integer> estimateCost(final Condition c) {
        if (c instanceof AndCond and) {
            if (and.sons == null) {
                return Pair.of(0f, null);
            }
            float ret = 0f;
            Integer best = null;
            for (final var son : and.sons) {
                Pair<Float, Integer> costJustifier = estimateCost((Condition) son);
                if (ret < costJustifier.getFirst()) {
                    ret = costJustifier.getFirst();
                    best = costJustifier.getSecond();
                }

            }
            return Pair.of(ret, best);

        } else if (c instanceof OrCond and) {
            if (and.sons == null) {
                return Pair.of(0f, null);
            }
            float ret = Float.POSITIVE_INFINITY;
            Integer best = null;
            for (final var son : and.sons) {
                Pair<Float, Integer> floatIntegerPair = estimateCost((Condition) son);
                if (ret > floatIntegerPair.getFirst()) {
                    ret = floatIntegerPair.getFirst();
                    best = floatIntegerPair.getSecond();
                }

            }
            return Pair.of(ret, best);
        } else if (c instanceof Terminal t) {
            return Pair.of(getConditionCost()[t.getId()], t.getId());
        } else {
            throw new RuntimeException("This is not supported:" + c);
        }
    }

    void setNumericContribution(int a, int b, float value) {
        if (hardcoreVersion) {
            numericContributionRaw[actionsArrayShifter.getTID(a)][termsArrayShifter.getTID(b)] = value;
        } else {
            numericContribution.put(Pair.of(actionsArrayShifter.getTID(a), termsArrayShifter.getTID(b)), value);
        }
    }

    public Float getNumericContribution(int a, int b) {
        if (hardcoreVersion) {
            return numericContributionRaw[actionsArrayShifter.getTID(a)][termsArrayShifter.getTID(b)];
        }
        return numericContribution.getOrDefault(Pair.of(actionsArrayShifter.getTID(a), termsArrayShifter.getTID(b)), Float.MAX_VALUE);
    }

    //Semantics: UNKNOWEFFECT don't know because comp is hard. > 0 is achiever, 0 no
    protected float numericContribution(int t, Comparison comp) {

        if (cp.numericEffectFunction()[t] == null || cp.numericEffectFunction()[t].isEmpty()) {
            return 0f;
        }

//        Float positiveness = numericContribution[t][comp.getId()];
        Float positiveness = getNumericContribution(t, comp.getId());
        if (positiveness == Float.MAX_VALUE) {
            positiveness = 0f;
            if (cp.numericEffectFunction()[t].isEmpty()) {
                setNumericContribution(t, comp.getId(), 0f);
                return positiveness;
            }
            if (comp.getLeft() instanceof ExtendedNormExpression extendedNormExpression) {
                final ExtendedNormExpression left = extendedNormExpression;
                for (final ExtendedAddendum ad : left.summations) {
                    if (ad.bin != null) {
                        for (final NumEffect ne : cp.numericEffectFunction()[t]) {
                            NumFluent fluentAffected = ne.getFluentAffected();
                            if (ad.bin.getInvolvedNumericFluents().contains(fluentAffected)) {
                                setNumericContribution(t, comp.getId(), UNKNOWNEFFECT);
                                return UNKNOWNEFFECT;
                            }
                        }
                    }
                    if (ad.f != null) {
                        for (final NumEffect ne : cp.numericEffectFunction()[t]) {

                            if (!ne.getFluentAffected().equals(ad.f)) {
                                continue;
                            }

                            if (ne.getInvolvedNumericFluents().isEmpty()) {
                                final ExtendedNormExpression rhs = (ExtendedNormExpression) ne.getRight();
                                if (!rhs.linear || !rhs.isNumber() || ne.getOperator().equals("assign")) {
                                    setNumericContribution(t, comp.getId(), UNKNOWNEFFECT);
                                    return UNKNOWNEFFECT;
                                }
                                if (ne.getOperator().equals("increase")) {
                                    positiveness += rhs.getNumber().floatValue() * ad.n.floatValue();
                                } else if (ne.getOperator().equals("decrease")) {
                                    positiveness += (-1) * rhs.getNumber().floatValue() * ad.n.floatValue();
                                }
                            } else {//The effect is state dependent.
                                setNumericContribution(t, comp.getId(), UNKNOWNEFFECT);
                                return UNKNOWNEFFECT;
                            }
                        }
                    }
                }
                setNumericContribution(t, comp.getId(), positiveness);
                return positiveness;
            } else {
                throw new RuntimeException("At the moment only normalized expressions are considered " + comp);
            }
        }
        return positiveness;
    }

    @Override
    public Object[] getTransitions(final boolean helpful) {
        Collection res;
        if (helpfulActions == null || !helpful) {
            if (reachableTransitionsInstances == null) {
                if (reachableTransitions == null) {
                    res = getProblem().actions;
                } else {
                    reachableTransitionsInstances = new LinkedHashSet<TransitionGround>();
                    for (final int i : reachableTransitions) {
                        Transition transition = getTransition(cp.cpTr2TrMap()[i]);
                        if (transition.getSemantics().equals(Transition.Semantics.ACTION))
                            reachableTransitionsInstances.add((TransitionGround) transition);
                    }
                    reachableTransitionsInstances = new ArrayList<>(reachableTransitionsInstances);
                    res = reachableTransitionsInstances;
                }
            } else {
                res = reachableTransitionsInstances;
            }
        } else {
            res = helpfulActions;
        }
        if (helpfulTransitions) {
            res.addAll(getHelpfulTransitions());
        }
        return res.toArray();
    }


    public Collection<TransitionGround> getPotentialApplicableActions() {
        return this.getAllTransitions();
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() {
        if (reachableTransitionsInstances == null) {
            if (reachableTransitions == null) {
                throw new RuntimeException("The heuristics should be called at least once to be used to get the reached actions");
            }
            reachableTransitionsInstances = new LinkedHashSet<TransitionGround>();
            for (final int i : reachableTransitions) {
                TransitionGround transition = (TransitionGround) getTransition(cp.cpTr2TrMap()[i]);
                if (transition.getSemantics().equals((Transition.Semantics.ACTION)))
                    reachableTransitionsInstances.add((TransitionGround) getTransition(cp.cpTr2TrMap()[i]));
            }
            reachableTransitionsInstances = new ArrayList<>(reachableTransitionsInstances);
            return reachableTransitionsInstances;
        } else {
            return reachableTransitionsInstances;
        }
    }

    public Collection<Pair<TransitionGround, Integer>> getHelpfulTransitions() {
        if (!extractRelaxedPlan && !isHelpfulActionsComputation()) {
            throw new RuntimeException("Helpful Transitions can only be activatated in combination with the relaxed plan extraction");
        }
        Collection<Pair<TransitionGround, Integer>> res = new ArrayList<>();

        for (final int actionTransitionId : plan) {
            int actionId = cp.tr2CpTrMap()[actionTransitionId].iterator().next();//Assume that there is a one-to-one relantioship between actions in the heuristic and actions in the search
            if (getActionInit()[actionId]) {
                final IntArraySet right = repetitionsInThePlan[actionTransitionId];
                if (!right.isEmpty()) {
                    if (maxMRP) {
                        int max = 0;
                        for (int i : right) {
                            if (i > max) {
                                max = i;
                            }
                        }
                        if (max > 1) {
                            res.add(Pair.of((TransitionGround) getTransition(actionTransitionId), max));
                        }
                    } else {
                        int min = Integer.MAX_VALUE;
                        for (int i : right) {
                            if (i < min) {
                                min = i;
                            }
                        }
                        if (min > 1) {
                            res.add(Pair.of((TransitionGround) getTransition(actionTransitionId), min));
                        }
                    }
                }
            }
        }
        return res;
    }


    public void addDeleter(int i, int actId) {
        if (deleters[i] == null) {
            deleters[i] = new IntArraySet();
        }
        deleters[i].add(actId);
    }

    void updateDeleters(int t, int actionId) {
        addDeleter(t, actionId);
    }


    public Condition getGoalFormulation() {
        return cp.preconditionFunction()[cp.goal()];
    }

    protected IntSet getConditionsAchievableById(int actionId) {
        if (getConditionsAchievableBy()[actionId] == null) {
            final IntArraySet achievableTerms = new IntArraySet();
            final IntArraySet deletableTerms = new IntArraySet();
            for (final int t : getAllComparisons()) {
                final float v = this.numericContribution(actionId, (Comparison) Terminal.getTerminal(t));
                if (v > 0 || v == UNKNOWNEFFECT) {
                    achievableTerms.add(t);
                    updateAchievers(t, actionId);
                    if (DEBUG) {
                        System.out.println("Transition: " + getTransition(actionId));
                        System.out.println("Comparison Achievable: " + Terminal.getTerminal(t));
                        System.out.println("Numeric Contribution: " + v);
                    }
                } else {
                    if (v < 0 && useSmartConstraints) {
                        if (DEBUG) {
                            System.out.print(Transition.getTransition(actionId) + " worsens");
                            System.out.println((Comparison) Terminal.getTerminal(t));
                        }
                        updateDeleters(t, actionId);
                        deletableTerms.add(t);
                    }
                }
            }
            Sets.SetView<Integer> intersection = Sets.intersection(getAllConditions(), (Set<Integer>) cp.propEffectFunction()[actionId]);
            achievableTerms.addAll(intersection);
            for (final int o : intersection) {
                updateAchievers(o, actionId);
            }
            conditionsAchievableBy[actionId] = achievableTerms;
            if (useSmartConstraints)
                conditionsDeletableBy[actionId] = deletableTerms;

        }
        return getConditionsAchievableBy()[actionId];
    }


    private float computeRepetition(Terminal t, double v, State s) {
        final double eval = ((Comparison) t).getLeft().eval(s);
        if (Double.isNaN(eval)) {
            return 1.0f;
        }
        if (((Comparison) t).isStrict && this.isAdditive()) {
            return (float) (-1f * eval / v) + Float.MIN_VALUE;
        }
        return (float) (-1f * eval / v);
    }


    /**
     * @return the allAchievers
     */
    public IntArraySet[] getAllAchievers() {
        if (allAchievers == null) {
            allAchievers = new IntArraySet[getTotNumberOfTerms()];
        }
        return allAchievers;
    }

    /**
     * @return the totNumberOfTerms
     */
    public int getTotNumberOfTerms() {
        return totNumberOfTerms;
    }

    /**
     * @return the totNumberOfTermsRefactored
     */
    public int getTotNumberOfTermsRefactored() {
        return totNumberOfTermsRefactored;
    }

    /**
     * @return the problem
     */
    public PDDLProblem getProblem() {
        return problem;
    }

    /**
     * @return the helpfulActionsComputation
     */
    public boolean isHelpfulActionsComputation() {
        return helpfulActionsComputation;
    }

    /**
     * @return the conditionsAchievableBy
     */
    public IntArraySet[] getConditionsAchievableBy() {
        return conditionsAchievableBy;
    }

    /**
     * @return the conditionsDeletableBy
     */
    public IntArraySet[] getConditionsDeletableBy() {
        return conditionsDeletableBy;
    }

    /**
     * @return the conditionToAction
     */
    public IntArraySet[] getConditionToAction() {
        return conditionToAction;
    }

    /**
     * @return the allConditions
     */
    public IntArraySet getAllConditions() {
        return allConditions;
    }

    /**
     * @return the reachableAchievers
     */
    public IntArraySet[] getReachableAchievers() {
        return allAchievers;
    }

    /**
     * @return the allComparisons
     */
    public IntArraySet getAllComparisons() {
        return allComparisons;
    }

    /**
     * @return the nodeOf
     */
    public FibonacciHeapNode[] getNodeOf() {
        return nodeOf;
    }

    /**
     * @return the reachability
     */
    public boolean isReachability() {
        return reachability;
    }

    /**
     * @return the conjunctionsMax
     */
    public boolean isConjunctionsMax() {
        return conjunctionsMax;
    }

    /**
     * @return the actionCost
     */
    public float[] getActionCost() {
        return tempActionCost;
    }

    /**
     * @return the actionHCost
     */
    public float[] getActionHCost() {
        return actionHCost;
    }

    /**
     * @return the conditionCost
     */
    public float[] getConditionCost() {
        return conditionCost;
    }

    /**
     * @return the closed
     */
    public boolean[] getClosed() {
        return closed;
    }

    /**
     * @return the additive
     */
    public boolean isAdditive() {
        return additive;
    }

    /**
     * @return the conditionInit
     */
    public boolean[] getConditionInit() {
        return conditionInit;
    }

    /**
     * @return the actionInit
     */
    public boolean[] getActionInit() {
        return actionInit;
    }

    public void setComputeHelpfulActionsMap() {
        // LM-Cut does not compute a dedicated helpful-actions bitmap.
    }

}
