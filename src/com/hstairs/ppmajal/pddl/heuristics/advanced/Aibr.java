package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import static com.google.common.collect.Range.closedOpen;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.expressions.BinaryOp;
import com.hstairs.ppmajal.expressions.Expression;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.problem.RelState;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import it.unimi.dsi.fastutil.ints.*;
import java.io.PrintStream;
import java.util.*;

public final class Aibr implements SearchHeuristic {

    private final PDDLProblem problem;
    private final int numberOfSupporters;
    private final boolean reachability;
    private final int[] supporter2transition;
    private final Collection<Terminal>[] supporter2propeffect;
    private final NumEffect[] supporter2numeffect;
    private final Condition[] supporter2aymptoticeffects;
    private final PrintStream out;
    //The following are built around supporters
    private final Int2ObjectMap<String> names = new Int2ObjectArrayMap();
    private Collection<TransitionGround> reachableTransitions = null;
    private boolean DEBUG = false;
    private AibrLogger logger;

    public Aibr(PDDLProblem problem) {
        this(problem, false, false);
    }

    public Aibr(PDDLProblem problem, boolean reachability, boolean aibrDebug) {
        final Int2ObjectMap<Collection<Terminal>> propEffectMap = new Int2ObjectArrayMap();
        final Int2IntArrayMap supporter2transitionMap = new Int2IntArrayMap();
        final Int2ObjectMap<Condition> asymptoticPreconditionFunctionMap = new Int2ObjectArrayMap<>();
        final Int2ObjectMap<NumEffect> numEffectMap = new Int2ObjectArrayMap<>();
        out = problem.out;
        this.problem = problem;
        this.DEBUG = aibrDebug;
        ArrayList<TransitionGround> array = new ArrayList<>(problem.getTransitions());
        for (final TransitionGround tr : array) {           
            generateNumericSupporters(tr, supporter2transitionMap, asymptoticPreconditionFunctionMap, numEffectMap);
            generatePropositionalAction(tr, supporter2transitionMap, propEffectMap);
        }
        numberOfSupporters = supporter2transitionMap.keySet().size();
        //This maps action to their precondition
        supporter2transition = new int[numberOfSupporters];
       
        supporter2transitionMap.forEach((integer, integer1) -> supporter2transition[integer] = integer1);

        supporter2propeffect = new Collection[numberOfSupporters];
        propEffectMap.forEach((integer, terminals) -> supporter2propeffect[integer] = terminals);
        supporter2aymptoticeffects = new Condition[numberOfSupporters];
        asymptoticPreconditionFunctionMap.forEach((integer, condition) -> supporter2aymptoticeffects[integer] = condition);
        supporter2numeffect = new NumEffect[numberOfSupporters];
        numEffectMap.forEach((integer, numEffect) -> supporter2numeffect[integer] = numEffect);

        if (true) {
            for (int i = 0; i < numberOfSupporters; i++) {
                Collection<Terminal> propEffects = supporter2propeffect[i];
                NumEffect numEffect = supporter2numeffect[i];
                if (propEffects != null && numEffect != null) {
                    throw new RuntimeException("Bug in the function");
                } else {
                }
            }
        }

        this.reachability = reachability;
        if (DEBUG) {
            this.logger = new AibrLogger("aibr_log.json");
        }

//
//        out.println("AIBR :: Number of Supporters = " + numberOfSupporters);

    }

    public void setDebug(boolean debug) {
        this.DEBUG = debug;
        if (debug && logger == null) {
            this.logger = new AibrLogger("aibr_log.json");
        }
    }

    void generatePropositionalAction(TransitionGround tr, Int2IntArrayMap supporter2transitionMap, Int2ObjectMap<Collection<Terminal>> propEffectMap) {
        Collection<Terminal> allAchievableLiterals = tr.getAllAchievableLiterals();
        if (!allAchievableLiterals.isEmpty()) {
            propEffectMap.put(supporter2transitionMap.keySet().size(), allAchievableLiterals);
            names.put(supporter2transitionMap.keySet().size(), tr.getName() + "-Propositional");
            supporter2transitionMap.put(supporter2transitionMap.keySet().size(), tr.getId());
        }
    }

    boolean generateNumericSupporters(TransitionGround tr, Int2IntArrayMap supporter2transitionMap, Int2ObjectMap<Condition> asymptoticPreconditionFunctionMap, Int2ObjectMap<NumEffect> numEffectMap) {
        for (NumEffect effect : tr.getAllNumericEffects()) {
            if (effect == null) {
                return false;
            }
        }
        for (NumEffect effect : tr.getAllNumericEffects()) {
            
            //TO FIX. The assignment to an undefined value is not tried!!
            
            effect.additive_relaxation = true;
            if ("assign".equals(effect.getOperator()) && effect.getRight().getInvolvedNumericFluents().isEmpty()) {
                NumEffect assign = new NumEffect("assign");
                assign.setFluentAffected(effect.getFluentAffected());
                assign.setRight(effect.getRight());
                names.put(supporter2transitionMap.keySet().size(), tr.getName() + "-assign");
                numEffectMap.put(supporter2transitionMap.keySet().size(), assign);
                supporter2transitionMap.put(supporter2transitionMap.keySet().size(), tr.getId());
            } else {
                final boolean empty = effect.getRight().getInvolvedNumericFluents().isEmpty();
                if (empty) {
                    final double right = effect.getRight().eval(problem.getInit());
                    if (right > 0 && effect.getOperator().equals("increase")
                            || right < 0 && effect.getOperator().equals("decrease")) {
                        generateInfSupporter(effect, supporter2transitionMap, supporter2transitionMap.keySet().size(), "+", asymptoticPreconditionFunctionMap, numEffectMap, tr);
                    } else {
                        generateInfSupporter(effect, supporter2transitionMap, supporter2transitionMap.keySet().size(), "-", asymptoticPreconditionFunctionMap, numEffectMap, tr);
                    }
                } else {
                    generateInfSupporter(effect, supporter2transitionMap, supporter2transitionMap.keySet().size(), "+", asymptoticPreconditionFunctionMap, numEffectMap, tr);
                    generateInfSupporter(effect, supporter2transitionMap, supporter2transitionMap.keySet().size(), "-", asymptoticPreconditionFunctionMap, numEffectMap, tr);
                }
            }
        }
        return false;
    }

    private void generateInfSupporter(NumEffect effect, Int2IntArrayMap supporter2transitionMap, int idx, String s, Int2ObjectMap<Condition> asymptoticPreconditionFunctionMap, Int2ObjectMap<NumEffect> numEffectMap, TransitionGround tr) {
        String inequality = "";
        Float asymptote = Float.MAX_VALUE;
        if ("+".equals(s)) {
            switch (effect.getOperator()) {
                case "increase":
                    inequality = ">";
                    break;
                case "decrease":
                    inequality = "<";
                    break;
                case "assign":
                    inequality = ">";
                    break;
            }
        } else {
            asymptote = -Float.MAX_VALUE;
            switch (effect.getOperator()) {
                case "increase":
                    inequality = "<";
                    break;
                case "decrease":
                    inequality = ">";
                    break;
                case "assign":
                    inequality = "<";
                    break;
            }
        }
        generateSupporter(effect, idx, inequality, asymptote, asymptoticPreconditionFunctionMap, numEffectMap);
        names.put(idx, tr.getName().concat(s + "Inf"));
        supporter2transitionMap.put(idx, tr.getId());
    }

    private void generateSupporter(NumEffect effect, int idx, String inequality, Float asymptote, Int2ObjectMap<Condition> asymptoticPreconditionFunctionMap, Int2ObjectMap<NumEffect> numEffectMap) {

        final Comparison indirectPrecondition;
        final Expression left;
        if (effect.getOperator().equals("assign")) {
            left = new BinaryOp(effect.getRight(), "-", effect.getFluentAffected(), true);
        } else {
            left = effect.getRight();
        }
        indirectPrecondition = (Comparison) Comparison.comparison(Comparison.Comparator.fromSymbol(inequality), left, new PDDLNumber(0), false).normalize();
        asymptoticPreconditionFunctionMap.put(idx, indirectPrecondition);
        NumEffect eff = new NumEffect("assign");
        eff.setFluentAffected(effect.getFluentAffected());
        eff.setRight(new PDDLNumber(asymptote));
        numEffectMap.put(idx, eff);
    }

    @Override
    public float computeEstimate(State s0) {
        // Call the helper method with a null StateWrapper
        return computeEstimateInternal(s0, null);
    }

    public float computeEstimate(State s0, ArrayList<RelState> relaxedStates) {
        // Call the helper method with the provided StateWrapper
        return computeEstimateInternal(s0, relaxedStates);
    }


    public float computeEstimateInternal(State s0, ArrayList<RelState> relaxedStates) {
        final PDDLState s = (PDDLState) s0;
        final RelState relState = s.relaxState();
        final IntArraySet supporters = new IntArraySet(ContiguousSet.create(closedOpen(0, numberOfSupporters), DiscreteDomain.integers()));
        final IntArrayList reachableActionsThisStage = new IntArrayList();
        boolean goalReached = false;
        final BitSet conditionSatisfied = new BitSet();
        final BitSet actionInserted = new BitSet();
        int stepCount = 0;

        // Print all potential supporters
        if (DEBUG) {
            logSupporters(names, supporter2aymptoticeffects, supporter2propeffect, supporter2numeffect, supporter2transition);
            logComputationStart();
            logUnsupportedSupporters(supporters);
        }

        //while ((reachability && !supporters.isEmpty()) || (!supporters.isEmpty() && !reachability && goalReached)) {
        while (!supporters.isEmpty()){
            stepCount++;
            // Log the current state before applying actions
            if (DEBUG) {
                logger.logStep(stepCount, "Step " + stepCount, relState.getPossNumValues(), 
                             -1, "Initial State", null, null, null);
            }
            
            final IntIterator iterator = supporters.iterator();
            final IntArrayList propAppliers = new IntArrayList();
            final IntArrayList numAppliers = new IntArrayList();
            while (iterator.hasNext()) {
                int current = iterator.nextInt();
                final TransitionGround tr = (TransitionGround) Transition.getTransition(supporter2transition[current]);
                final boolean b = conditionSatisfied.get(current);
                if (!reachability || tr.getConditionalNumericEffects().canBeRelaxedApplied(relState,problem)) {
                    if (b || relState.satisfy(tr.getPreconditions())) {
                        if (!b) {
                            conditionSatisfied.set(current, true);
                        }
                        final int id = tr.getId();
                        if (!actionInserted.get(id)) {
                            reachableActionsThisStage.add(id);
                            actionInserted.set(id, true);
                        }
                        //Prop effect
                        final Collection<Terminal> terminals = supporter2propeffect[current];
                        if (terminals != null && !terminals.isEmpty()) {
                            iterator.remove();
                            propAppliers.add(current);
                        } else {
                            final NumEffect numEffect = supporter2numeffect[current];
                            if (numEffect != null) {
                                final Condition condition2 = supporter2aymptoticeffects[current];

                                if (condition2 == null || relState.satisfy(condition2)) {
                                    iterator.remove();
                                    numAppliers.add(current);
                                } else if (DEBUG) {
                                    // Log why the numeric effect wasn't applied
                                    logger.logStep(stepCount, "Unsupported Numeric Effect", relState.getPossNumValues(),
                                                 current, names.get(current), condition2,
                                                 null, numEffect);
                                }
                            }
                        }
                    } else if (DEBUG) {
                        // Log why the transition wasn't applied
                        logger.logStep(stepCount, "Unsupported Transition", relState.getPossNumValues(),
                                     current, names.get(current), tr.getPreconditions(),
                                     null, null);
                    }
                } else if (DEBUG) {
                    // Log why the transition wasn't reachable
                    logger.logStep(stepCount, "Unreachable Transition", relState.getPossNumValues(),
                                 current, names.get(current), tr.getPreconditions(),
                                 null, null);
                }
            }

            if (numAppliers.isEmpty() && propAppliers.isEmpty() && !relState.satisfy(problem.getGoals())) {
                if (DEBUG) {
                    logUnsupportedSupporters(supporters);
                    logUnsat();
                }
                return Float.MAX_VALUE;
            }
            for (final int current : propAppliers) {
                final Collection<Terminal> terminals = supporter2propeffect[current];
                relState.apply(terminals, relState.clone(),this.problem);
                if (DEBUG) {
                    logger.logStep(stepCount, names.get(current), relState.getPossNumValues(),
                                 current, names.get(current), supporter2aymptoticeffects[current],
                                 terminals, null);
                }
            }
            for (final int current : numAppliers) {
                final NumEffect effect = supporter2numeffect[current];
                relState.apply(effect, relState.clone(),this.problem);
                if (DEBUG) {
                    logger.logStep(stepCount, names.get(current), relState.getPossNumValues(),
                                 current, names.get(current), supporter2aymptoticeffects[current],
                                 null, effect);
                }
            }
            if (relState.satisfy(problem.getGoals())) {
                goalReached = true;
                if (reachableTransitions != null) {
                    break;
                } 
                else {
                    if (numAppliers.isEmpty() && propAppliers.isEmpty()) {
                        break;
                    }
                }
            }
            
            // After applying actions and updating intervals, log the changes
            if (DEBUG && !reachableActionsThisStage.isEmpty()) {
                for (int actionId : reachableActionsThisStage) {
                    String actionName = names.get(actionId);
                    logger.logStep(stepCount, actionName, relState.getPossNumValues(),
                                 actionId, actionName, supporter2aymptoticeffects[actionId],
                                 supporter2propeffect[actionId], supporter2numeffect[actionId]);
                }
            }
        }
        
        // Save the log at the end of computation
        if (DEBUG) {
            logger.saveLog();
        }
        
        if (reachableTransitions == null) {
            reachableTransitions = new LinkedHashSet<>();
            for (final int reacheableAction : reachableActionsThisStage) {
                reachableTransitions.add((TransitionGround) Transition.getTransition(reacheableAction));
            }
            reachableTransitions = new ArrayList<>(reachableTransitions);
            if (reachability) {
                return 0;
            }
        }
        if (goalReached) {
            if (DEBUG){
                System.err.println("Computing actual estimate using the following transitions:"+reachableTransitions);
            }
            float res = fixPointComputation(reachableTransitions, s.relaxState(), relaxedStates);
            if (relaxedStates != null) {
                relaxedStates.add(relState.clone());
            }
            return res;
        }
        return Float.MAX_VALUE;
    }

    private float fixPointComputation(Collection<TransitionGround> reachable, RelState s, ArrayList<RelState> relaxedStates) {
        int counter = 0;
        int horizon = Integer.MAX_VALUE;
        if (relaxedStates != null) {
            relaxedStates.add(s.clone());
        }
        BitSet applicable = new BitSet();
                
        while (counter <= horizon) {
            if (s.satisfy(problem.getGoals())) {
                return counter;
            }
            for (var transition : reachable) {
                final boolean b = applicable.get(transition.getId());
                if (b || s.satisfy(transition.getPreconditions())) {
                    if (!b) {
                        applicable.set(transition.getId(), true);
                    }
                    s.apply(transition, (RelState) s.clone(),problem);
                    if (relaxedStates != null) {
                        relaxedStates.add(s.clone());
                    }
                    counter++;
                    if (s.satisfy(problem.getGoals())) {
                        return counter;
                    }

                }
            }
        }
        return Float.MAX_VALUE;
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() {
        if (reachableTransitions == null) {
            throw new RuntimeException("Reachable transitions computed by AIBR is null");
        }
        return reachableTransitions;
    }

    @Override
    public Object[] getTransitions(boolean helpful) {
        return problem.actions.toArray();
    }

    private void logSupporters(Map<Integer, String> names, Condition[] supporter2aymptoticeffects,
                             Collection<Terminal>[] supporter2propEffects, NumEffect[] supporter2numeffect,
                             int[] supporter2transition) {
        logger.logSupporters(names.size(), names, supporter2aymptoticeffects, 
                           supporter2propEffects, supporter2numeffect, supporter2transition);
    }

    private void logComputationStart() {
        logger.logStep(0, "Starting Computation", new Int2ObjectArrayMap<>(), -1, "none", null, null, null);
    }

    private void logUnsat() {
        logger.logStep(-1, "UNSAT", new Int2ObjectArrayMap<>(), -1, "none", null, null, null);
        logger.saveLog();
    }

    private void logUnsupportedSupporters(IntArraySet supporters) {
        if (DEBUG) {
            IntArraySet unsupported = new IntArraySet();
            for (int i = 0; i < numberOfSupporters; i++) {
                if (!supporters.contains(i)) {
                    unsupported.add(i);
                }
            }
            logger.logUnsupportedSupporters(unsupported, names);
        }
    }
}
