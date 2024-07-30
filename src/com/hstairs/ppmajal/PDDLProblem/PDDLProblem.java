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
package com.hstairs.ppmajal.PDDLProblem;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.domain.SchemaGlobalConstraint;
import com.hstairs.ppmajal.domain.Type;
import com.hstairs.ppmajal.expressions.*;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.parser.PddlLexer;
import com.hstairs.ppmajal.parser.PddlParser;
import com.hstairs.ppmajal.pddl.heuristics.advanced.Aibr;
import com.hstairs.ppmajal.problem.*;
import com.hstairs.ppmajal.propositionalFactory.*;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.SearchProblem;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.transition.TransitionSchema;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jgrapht.alg.util.Pair;

/**
 * @author enrico
 */
public class PDDLProblem implements SearchProblem {

    private boolean relevantUndefinedVariablesPresent;
    private boolean[] subgoalRelevantFluents;
    private boolean[] costRelevantFluents;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the liftedGoals
     */
    public Condition getLiftedGoals() {
        return liftedGoals;
    }

    final private boolean ignoreMetric;
    private boolean cleanUp = false;
    final public BigDecimal executionDelta;
    final public BigDecimal planningDelta;

    public PDDLProblem(PDDLDomain pddlDomain) {
        this(pddlDomain, "internal", System.out, false, false);
    }

    public PDDLProblem(String arg, PDDLDomain d) {
        this(arg, d.constants, d.getTypes(), d, System.out, "internal", false, false, new BigDecimal(1.0), new BigDecimal(1.0));
    }


    /**
     * @return the readyForSearch
     */
    public boolean isReadyForSearch() {
        return readyForSearch;
    }

    static public HashSet<BoolPredicate> booleanFluents;

    private PDDLObjects objects;
    public State init;
    private Condition liftedGoals;
    private Condition groundGoals;
    public Collection<TransitionGround> actions;
    public Condition belief;
    public Collection<BoolPredicate> unknonw_predicates;
    public Collection<OneOf> one_of_s;
    public Collection<OrCond> or_s;
    public Set<Type> types;
    private String name;
    protected Integer indexObject;
    protected Integer indexInit;
    protected Integer indexGoals;
    protected Metric metric;
    protected String pddlFilRef;
    protected String domainName;
    protected long propositionalTime;
    protected RelState possStates;
    protected boolean simplifyActions;
    private boolean action_cost_from_metric = true;
    protected Set actualFluents;
    //This maps the string representation of a predicate (which uniquely defines it, into an integer)
    final private Map<NumFluent, PDDLNumber> initNumFluentsValues;
    final private Map<BoolPredicate, Boolean> initBoolFluentsValues;
    final PDDLDomain linkedDomain;
    private FactoryConditions fc;


    public HashSet<GlobalConstraint> globalConstraintSet;
    public AndCond globalConstraints;
    private Collection<TransitionGround> processesSet;
    private Collection<TransitionGround> eventsSet;
    private PDDLState pureInit;
    private HashMap<String, Terminal> terminalReference;
    private HashMap<String, NumFluent> numFluentReference;
    private boolean smallExpensive = false;
    private boolean debug;
    private boolean cacheComparison = false;
    private int totNumberOfBoolVariables;
    private int totNumberOfNumVariables;
    final public PrintStream out;
    final private String groundingMethod;
    private long groundingTime;
    private boolean sdac;
    private boolean readyForSearch;


    public PDDLProblem(PDDLDomain domain, String groundingMethod, PrintStream out, boolean sdac, boolean ignoreMetric) {
        this(domain, groundingMethod, out, sdac, ignoreMetric, new BigDecimal(1.0), new BigDecimal(1.0));
    }


    public PDDLProblem(PDDLDomain domain, String groundingMethod, PrintStream out, boolean sdac, boolean ignoreMetric, BigDecimal planningDelta, BigDecimal executionDelta) {
        indexInit = 0;
        indexGoals = 0;
        objects = new PDDLObjects();
        metric = new Metric("NO", null);
        actions = new LinkedHashSet();
        simplifyActions = true;
        possStates = null;
        globalConstraintSet = new LinkedHashSet();
        eventsSet = new LinkedHashSet();
        globalConstraints = new AndCond(Collections.EMPTY_SET);
        this.out = out;
        this.groundingMethod = groundingMethod;
        this.sdac = sdac;
        linkedDomain = domain;
        initBoolFluentsValues = new HashMap();
        initNumFluentsValues = new HashMap();
        readyForSearch = false;
        PDDLState.fastTransitionTable = null;
        this.ignoreMetric = ignoreMetric;
        this.planningDelta = planningDelta;
        this.executionDelta = executionDelta;

    }


    public PDDLProblem(String problemFile, PDDLObjects constants, Set<Type> types,
                       PDDLDomain domain, PrintStream out, String groundingMethod, boolean sdac, boolean ignoreMetric, BigDecimal planningDelta, BigDecimal executionDelta) {

        this(domain, groundingMethod, out, sdac, ignoreMetric, planningDelta, executionDelta);
        try {
            objects.addAll(constants);
            this.types = types;

            this.parseProblem(problemFile);
        } catch (IOException ex) {
            Logger.getLogger(PDDLProblem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.antlr.runtime.RecognitionException ex) {
            Logger.getLogger(PDDLProblem.class.getName()).log(Level.SEVERE, null, ex);
        }
        indexObject = 0;
    }

    /**
     * @return the sdac
     */
    public boolean isSdac() {
        return sdac;
    }

    /**
     * @param sdac the sdac to set
     */
    public void setSdac(boolean sdac) {
        this.sdac = sdac;
    }

    public long getGroundingTime() {
        return groundingTime;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        //EPddlProblem cloned = new PDDLProblem(this.pddlFilRef, this.objects, this.types, linkedDomain);
        PDDLProblem cloned = new PDDLProblem(pddlFilRef, getObjects(), types, linkedDomain, out, groundingMethod, sdac, ignoreMetric, planningDelta, executionDelta);

        cloned.processesSet = new LinkedHashSet();
        for (TransitionGround gr : this.actions) {
            throw new UnsupportedOperationException();
        }
        for (TransitionGround pr : this.getProcessesSet()) {
            throw new UnsupportedOperationException();
        }
        for (GlobalConstraint constr : this.globalConstraintSet) {
            cloned.globalConstraintSet.add((GlobalConstraint) constr.clone());
        }
        return this;

    }

    private HashMap<String, NumFluent> getNumericFluentReference() {
        if (this.numFluentReference == null) {
            numFluentReference = new HashMap<>();
        }
        return this.numFluentReference;
    }

    private void generateTransitions() {
        long start = System.currentTimeMillis();
        if (!"internal".equals(groundingMethod) && !"naive".equals(groundingMethod)) {
            System.out.println("Generate Transitions using " + groundingMethod);
            ExternalGrounder mff = null;
            switch (groundingMethod) {
                case "metricff":
                    mff = new MetricFFGrounder(this, this.linkedDomain.getPddlFilRef(), this.pddlFilRef);
                    break;
                case "fd":
                    mff = new FDGrounder(this, this.linkedDomain.getPddlFilRef(), this.pddlFilRef);
                    break;
                case "fdi":
                    mff = new FDGrounderInstantiate(this, this.linkedDomain.getPddlFilRef(), this.pddlFilRef);
                    break;
            }
            groundingTime = System.currentTimeMillis();
            Collection<TransitionGround> transitions = mff.doGrounding();
            groundingTime = System.currentTimeMillis() - groundingTime;
            for (var act : transitions) {
                switch (act.getSemantics()) {
                    case ACTION:
                        getActions().add(act);
                        break;
                    case EVENT:
                        getEventsSet().add(act);
                        break;
                    case PROCESS:
                        getProcessesSet().add(act);
                        break;
                }
            }
        } else {
            Grounder af = new Grounder(belief == null && !"naive".equals(groundingMethod));
//        Grounder af = new Grounder(false);

            ArrayList<TransitionSchema> transitions = new ArrayList<>();
            transitions.addAll(linkedDomain.getProcessesSchema());
            transitions.addAll(linkedDomain.getActionsSchema());
            transitions.addAll(linkedDomain.getEventsSchema());
            groundingTime = System.currentTimeMillis();

            for (var act : transitions) {
//                if (act.toString().contains("make-movable-and-stop")){
//                    System.out.println(act);
//                }
                Collection<TransitionGround> propositionalize = af.Propositionalize(act, getObjects(), this, getInitBoolFluentsValues(), linkedDomain);
                switch (act.getSemantics()) {
                    case ACTION:
                        getActions().addAll(propositionalize);
                        break;
                    case EVENT:
                        getEventsSet().addAll(propositionalize);
                        break;
                    case PROCESS:
                        getProcessesSet().addAll(propositionalize);
                        break;
                }
            }
            groundingTime = System.currentTimeMillis() - groundingTime;
        }
//        for (var v: getActions()){
//                    if (v.toString().contains("make-movable-and-stop")){
//                        System.out.println(v);
//                    }       
//                }

    }


    public boolean prepareForSearch(boolean aibrPreprocessing) throws Exception {
        return this.prepareForSearch(aibrPreprocessing, false);
    }

    public boolean prepareForSearch(boolean aibrPreprocessing, boolean stopAfterGrounding) throws Exception {

        //simplification decoupled from the grounding
        this.groundingActionProcessesConstraints();
        this.genActualFluentsAndCleanTransitions();
        out.println("Grounding Time: " + this.getGroundingTime());
        if (stopAfterGrounding) {
            return true;
        }
        if (!this.simplifyAndSetupInit(aibrPreprocessing))
            return false;
        groundGoals = generate_inequalities(getGoals());
        readyForSearch = true;
        return true;
    }

    protected Condition generate_inequalities(Condition con) {
        return (Condition) con.transformEquality();
    }

    protected Set getActualFluents() {
        if (actualFluents == null) {
            actualFluents = new LinkedHashSet();
            Sets.SetView<TransitionGround> transitions = Sets.union(Sets.union(new HashSet(this.actions), new HashSet(this.getEventsSet())), new HashSet(this.getProcessesSet()));

            for (TransitionGround gr : transitions) {
                gr.updateInvariantFluents(actualFluents);
            }
        }
//        System.out.println(actualFluents);
        return actualFluents;
    }

    public boolean isSubgoalsRelevant(NumFluent nf){
        return subgoalRelevantFluents != null && subgoalRelevantFluents[nf.getId()];
    }

    public boolean isCostRelevant(NumFluent nf){
        return costRelevantFluents != null && costRelevantFluents[nf.getId()];
    }

    public void setSubgoalRelevant(NumFluent nf){
        if (subgoalRelevantFluents == null){
            subgoalRelevantFluents = new boolean[NumFluent.numFluentsBank.size()];
            Arrays.fill(subgoalRelevantFluents,false);
        }
        subgoalRelevantFluents[nf.getId()]= true;
    }

    public void setCostRelevant(NumFluent nf){
        if (costRelevantFluents == null){
            costRelevantFluents = new boolean[NumFluent.numFluentsBank.size()];
            Arrays.fill(costRelevantFluents,false);
        }
        costRelevantFluents[nf.getId()]= true;
    }

    private void generateConstraints() throws Exception {
        Grounder af = new Grounder();
        if (linkedDomain.getSchemaGlobalConstraints() != null) {
            for (SchemaGlobalConstraint constr : linkedDomain.getSchemaGlobalConstraints()) {
//                af.Propositionalize(act, objects);

                if (!constr.parameters.isEmpty()) {
                    globalConstraintSet.addAll(af.Propositionalize(constr, getObjects()));
                } else {
                    GlobalConstraint gr = constr.ground();
                    globalConstraintSet.add(gr);
                }
            }
        }
    }

    public void groundingActionProcessesConstraints() throws Exception {
        long start = System.currentTimeMillis();

        actualFluents = null;
        this.createGroundGoals();
        this.generateTransitions();
        this.generateConstraints();
        setPropositionalTime(this.getPropositionalTime() + (System.currentTimeMillis() - start));
//        syncAllVariablesAndUpdateCollections(this);

    }

    public void genActualFluentsAndCleanTransitions() {
        this.getActualFluents();
        if (this.metric != null && this.metric.getMetExpr() != null) {
            this.metric = new Metric(this.metric.getOptimization(), this.metric.getMetExpr().normalize());
        } else {
            this.metric = null;
        }
    }


    private Iterable cleanEasyUnreachableTransitions(Iterable toWorkOut) {
        ArrayList arrayList = new ArrayList((Collection) toWorkOut);
        Collection<TransitionGround> res = new LinkedHashSet<>();
        ListIterator it = arrayList.listIterator();
        while (it.hasNext()) {
            TransitionGround act = (TransitionGround) it.next();
            boolean keep = true;
            for (final NumEffect effect : act.getAllNumericEffects()) {
                if (true) {
                    if (effect.weakEval(this, this.getActualFluents()) != null) {
                        effect.normalize();
                    } else {
                        keep = false;
                    }
                } else {
                    effect.normalize();

                }
            }
            if (isSimplifyActions() && keep) {
                try {
                    Set invariantFluents = this.getActualFluents();
                    Condition preconditions = act.getPreconditions();
                    final Condition condition = preconditions.weakEval(this, invariantFluents).normalize();
                    if (condition != null && !condition.isUnsatisfiable()) {
                        ConditionalEffects conditionalNumericEffects = act.getConditionalNumericEffects();
                        ConditionalEffects conditionalPropositionalEffects = act.getConditionalPropositionalEffects();
                        res.add(new TransitionGround(act.getName(), act.getSemantics(), act.getParameters(), condition,
                                conditionalPropositionalEffects.weakEval(this, invariantFluents),
                                conditionalNumericEffects.weakEval(this, invariantFluents)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
        return new ArrayList(res);
    }

    protected boolean easyClexanUp() {
        return this.easyCleanUp(false);
    }

    protected boolean easyCleanUp(boolean aibrPreprocessing) {
        //out.println("prova");
//        this.saveInitInit();
        if (!sweepStructuresForUnreachableStatements())
            return false;

        debug = false;
        if (debug) {
            out.print("This is the universe of numeric fluent:");
            for (NumFluent nf : NumFluent.numFluentsBank.values()) {
                out.println("ID:" + nf.getId() + "->" + nf);
            }
            out.print("This is the universe of propositional fluent:");
            for (BoolPredicate pred : BoolPredicate.getPredicatesDB().values()) {
                out.println("ID:" + pred.getId() + "->" + pred);
            }
        }
        this.makeInit();
//        System.out.println(this.getActualFluents());
//        h1.computeEstimate(this.init);
//        final Collection<TransitionGround> transitions = h1.getTransitions(false);
        if (aibrPreprocessing) {
            System.out.println("Aibr Preprocessing");
            final Aibr heuristic = new Aibr(this, true);
            final float v = heuristic.computeEstimate(this.init);
            if (v != Float.MAX_VALUE) {
                final Collection<TransitionGround> transitions = heuristic.getAllTransitions();
                actions = new ArrayList<>();
                processesSet = new ArrayList<>();
                eventsSet = new ArrayList<>();
                for (final TransitionGround t : transitions) {
                    switch (t.getSemantics()) {
                        case ACTION:
                            actions.add(t);
                            break;
                        case PROCESS:
                            processesSet.add(t);
                            break;
                        case EVENT:
                            eventsSet.add(t);
                            break;
                    }
                }
            }else {
                out.println("Problem Detected as Unsolvable by AIBR during preprocessing");
                return false;
            }
            if (!sweepStructuresForUnreachableStatements())
                return false;

        }
        return true;
//        this.makePddlState(); //remake init so as to account for only reachable actions
    }

    protected boolean sweepStructuresForUnreachableStatements() {
        this.actualFluents = null;
        //the following just remove actions/processes/events over false and static predicates
        actions = (Collection<TransitionGround>) cleanEasyUnreachableTransitions(actions);
//        this.staticFluents = null;
        processesSet = (Collection<TransitionGround>) cleanEasyUnreachableTransitions(processesSet);
//        this.staticFluents = null;
        eventsSet = (Collection<TransitionGround>) cleanEasyUnreachableTransitions(eventsSet);
//        this.staticFluents = null;
        cleanIrrelevantConstraints(globalConstraintSet);

        groundGoals = (Condition) getGoals().weakEval(this, this.getActualFluents());
        groundGoals = (Condition) getGoals().normalize();
        if (getGoals().isUnsatisfiable()) {
            return false;
        }
        globalConstraints = (AndCond) globalConstraints.weakEval(this, this.getActualFluents());
        globalConstraints = (AndCond) globalConstraints.normalize();
        if (globalConstraints.isUnsatisfiable()) {
            return false;
        }

        if (this.metric != null && this.metric.getMetExpr() != null) {
            this.metric = new Metric(this.metric.getOptimization(), this.metric.getMetExpr().weakEval(this, this.getActualFluents()));
            if (this.metric.getMetExpr() == null) {
                this.metric = null;
            } else {
                this.metric = new Metric(this.metric.getOptimization(), this.metric.getMetExpr().normalize());
            }
        } else {
            this.metric = null;
        }
        return true;
    }

    public boolean simplifyAndSetupInit() throws Exception {
        return simplifyAndSetupInit(true);
    }

    public boolean simplifyAndSetupInit(boolean aibrPreprocessing) throws Exception {

        long start = System.currentTimeMillis();

        boolean relSolvable = easyCleanUp(aibrPreprocessing);

        globalConstraints = (AndCond) globalConstraints.normalize();
        makeInit();
        out.println("UFX:" + relevantUndefinedVariablesPresent);
        out.println("|F|:" + totNumberOfBoolVariables);
        out.println("|X|:" + totNumberOfNumVariables);

        return relSolvable;
    }

    public void printAllInfo(){
        out.println("Numeric fluents deemed relevant for search");
        for (NumFluent nf : NumFluent.numFluentsBank.values()) {
            if ((this.getActualFluents().contains(nf) && this.isSubgoalsRelevant(nf))) {
                out.println(nf);
            }
        }
        out.println("Boolean fluents deemed relevant for search");
        for (BoolPredicate p : BoolPredicate.getPredicatesDB().values()) {
            if (this.getActualFluents().contains(p) ) {
                out.println(p);
            }

        }
        out.println("Actions deemed relevant for search");
        for (var action : this.actions){
            out.println(action);
        }

        out.println("Processes deemed relevant for search");
        for (var action : this.processesSet){
            out.println(action);
        }

        out.println("Events deemed relevant for search");
        for (var action : this.eventsSet){
            out.println(action);
        }
    }

//    private void idifyConditionsAndTransitions (Collection<GroundAction> reachableActions, ComplexCondition liftedGoals, AndCond globalConstraints) {
//        HashMap<Integer, GroundAction> actionIds = new HashMap<>();
//        HashMap<Integer, Condition> conditionsIds = new HashMap<>();
//        int actionID = 0;
//        int conditionID = 0;
//        for (GroundAction gr: reachableActions){
//            if (!actionIds.values().contains(gr)){
//                actionIds.put(actionID,gr);
//                Set<Condition> terminalConditions = gr.getPreconditions().getTerminalConditions();
//                Set<Condition> addListTerminalConditions = gr.getAddList().getTerminalConditions();
//                Set<Condition> delListTerminalConditions = gr.getDelList().getTerminalConditions();
//            }
//            //preconditions
//        }
//    }

    private void cleanIrrelevantConstraints(HashSet<GlobalConstraint> globalConstraintSet) {
        Iterator<GlobalConstraint> it = globalConstraintSet.iterator();
        final Collection temp = new HashSet();
        while (it.hasNext()) {
            GlobalConstraint constr = it.next();
            boolean keep = constr.simplifyModelWithControllableVariablesSem(linkedDomain, this);

            if (!keep) {
                it.remove();
            } else {
                temp.add(constr.condition);
            }

        }
        globalConstraints = new AndCond(temp);
    }


    public void setDeltaTimeVariable(String delta_t) {
        this.getInitNumFluentsValues().put(NumFluent.getNumFluent("#t", new ArrayList()), new PDDLNumber(Double.parseDouble(delta_t)));
    }


    public Sets.SetView<TransitionGround> getTransitions() {
        return Sets.union(Sets.union(new HashSet(actions), new HashSet<>(getEventsSet())), new HashSet(getProcessesSet()));

    }

    private void fixNecessaryFluents() {

        Set<NumFluent> involved_fluents = new LinkedHashSet();


        for (Transition a : getTransitions()) {
            involved_fluents.addAll(a.getPreconditions().getInvolvedFluents());
            ConditionalEffects<NumEffect> conditionalNumericEffects = a.getConditionalNumericEffects();
            Map<Condition, Collection> allConditionalEffects = conditionalNumericEffects.getAllConditionalEffects();
            for (final Condition c : allConditionalEffects.keySet()) {
                involved_fluents.addAll(c.getInvolvedFluents());
            }
            allConditionalEffects = a.getConditionalPropositionalEffects().getAllConditionalEffects();
            for (final Condition c : allConditionalEffects.keySet()) {
                involved_fluents.addAll(c.getInvolvedFluents());
            }

        }


        for (SchemaGlobalConstraint a : this.linkedDomain.getSchemaGlobalConstraints()) {
            involved_fluents.addAll(a.condition.getInvolvedFluents());
        }
        involved_fluents.addAll(getGoals().getInvolvedFluents());

        for (var ele: involved_fluents){
            setSubgoalRelevant(ele);
        }
        Metric metric = this.getMetric();
        if (metric != null){
            for (var v: metric.getMetExpr().getInvolvedNumericFluents()){
                setCostRelevant(v);
            }
        }
        for (var t: getTransitions()){
            for (NumEffect e: t.getConditionalNumericEffects().getAllEffects()){
                if (this.isSubgoalsRelevant(e.getFluentAffected())) {
                    for (var v: e.getRight().getInvolvedNumericFluents()){
                        this.setSubgoalRelevant(v);
                    }
                }
            }
        }



    }



    public Set getAllFluents() {
        Set res = new HashSet();
        if (NumFluent.numFluentsBank != null) {
            for (NumFluent nf : NumFluent.numFluentsBank.values()) {
                if (this.getActualFluents().contains(nf) && this.isSubgoalsRelevant(nf)) {
                    res.add(nf);
                }
            }
        }
        booleanFluents = new HashSet();
        if (BoolPredicate.getPredicatesDB() != null) {
            for (BoolPredicate p : BoolPredicate.getPredicatesDB().values()) {
                if (this.getActualFluents().contains(p)) {
                    res.add(p);
                }

            }
        }
        return res;
    }

    private PDDLState makePddlState() {
        return this.makePddlState(true);
    }

    private PDDLState makePddlState(boolean invAnalysis) {
        //ensure compactness
        //removeStaticPart();
        PDDLState.optimised = (this.processesSet == null || this.processesSet.isEmpty()) && (this.eventsSet == null || this.eventsSet.isEmpty());
        fixNecessaryFluents();

        HashMap<Integer, Double> numFluents = new HashMap();
        totNumberOfNumVariables = 0;
        totNumberOfBoolVariables = 0;
        relevantUndefinedVariablesPresent = false;

        if (NumFluent.numFluentsBank != null) {
            for (NumFluent nf : NumFluent.numFluentsBank.values()) {
                if ((this.getActualFluents().contains(nf) && this.isSubgoalsRelevant(nf)) || !invAnalysis) {

                    if (nf.isGrounded()) {
                        PDDLNumber number = this.getInitNumFluentsValues().get(nf);
                        if (number == null) {
                            numFluents.put(nf.getId(), Double.NaN);
                            relevantUndefinedVariablesPresent = true;
                        } else {
                            numFluents.put(nf.getId(), number.getNumber().doubleValue());
                        }
                        totNumberOfNumVariables++;
                    }
                }
            }
        }
        booleanFluents = new HashSet();
        BitSet boolFluents = new BitSet();
        if (BoolPredicate.getPredicatesDB() != null) {
            for (BoolPredicate p : BoolPredicate.getPredicatesDB().values()) {
                if (this.getActualFluents().contains(p) || !invAnalysis) {
                    if (p.isGrounded()) {
                        Boolean r = this.getInitBoolFluentsValues().get(p);
                        if (r == null || !r) {
                            //boolFluents.set(p.getId(), false);
                        } else {
                            boolFluents.set(p.getId(), true);
                        }
                        booleanFluents.add(p);
                        totNumberOfBoolVariables++;
                    }
                }

            }
        }
        PDDLState pddlState = null;
        if (cacheComparison) {
            pddlState = new PDDLStateWithCache(numFluents, boolFluents, this);
        } else {
            if (smallExpensive) {
                pddlState = new PDDLStateWithInt2Double(numFluents, boolFluents);
            } else {
                pddlState = new PDDLState(numFluents, boolFluents, this);
            }
        }

//        out.println(Printer.stringBuilderPddlPrintWithDummyTrue(this, pddlState));
        return pddlState;

    }


    public void makeInit(boolean invAnalysis) {
        this.init = makePddlState(invAnalysis);
        addTimeFluentToInit();
    }

    public void makeInit() {
        this.init = makePddlState();
        addTimeFluentToInit();
    }


    public Boolean goalSatisfied(State s) {
        return s.satisfy(this.getGoals());
    }

    private void createGroundGoals() {
        groundGoals = (Condition) getLiftedGoals().pushNotToTerminals();
        groundGoals = (Condition) groundGoals.ground(new HashMap(), getObjects());
    }


    protected HashMap<String, Terminal> getTerminalReference() {
        if (terminalReference == null) {
            terminalReference = new HashMap<>();
        }
        return terminalReference;
    }


//    private void syncAllVariablesAndUpdateCollections (PDDLProblem inputProblem) {
//
//        if (inputProblem == null) {
//            inputProblem = this;
//        }
////        HashMap<Predicate, Boolean> tempInitBool = new HashMap();
////        for (Predicate p : this.getInitBoolFluentsValues().keySet()) {
////            Boolean value = this.getInitBoolFluentsValues().get(p);
////            Predicate newP = (Predicate) p.unifyVariablesReferences(inputProblem);
////            tempInitBool.put(newP, value);
////
////        }
////        setInitBoolFluentsValues(tempInitBool);
////        HashMap<NumFluent, PDDLNumber> searchInitFluent = new HashMap();
////        for (NumFluent nf : this.getInitNumFluentsValues().keySet()) {
////            PDDLNumber pddlNumber = getInitNumFluentsValues().get(nf);
////            NumFluent numFluent = (NumFluent) nf.unifyVariablesReferences(inputProblem);
////            searchInitFluent.put(numFluent, pddlNumber);
////            this.getNumericFluentReference().put(nf.toString(), nf);
////        }
////        this.setInitNumFluentsValues(searchInitFluent);
//
//        groundGoals = (Condition) getGoals().unifyVariablesReferences(inputProblem);
//
//        Iterator<GlobalConstraint> it = this.globalConstraintSet.iterator();
//        while (it.hasNext()) {
//            GlobalConstraint gc = it.next();
//            gc.condition = gc.condition.unifyVariablesReferences(inputProblem);
//        }
//
//        globalConstraints = (AndCond) globalConstraints.unifyVariablesReferences(inputProblem);
//        if (metric != null) {
//            metric = metric.unifyVariablesReferences(inputProblem);
//        }
//        if (belief != null) {
//            belief = belief.unifyVariablesReferences(inputProblem);
//        }
//        if (this.unknonw_predicates != null){
//            for (Predicate p: this.unknonw_predicates){
//                p = (Predicate) p.unifyVariablesReferences(inputProblem);
//            }
//        }
//    }


    private void addTimeFluentToInit() {
        ((PDDLState) this.init).time = BigDecimal.ZERO;
    }

    public NumFluent getNumfluentReference(String stringRepresentation) {
        return getNumericFluentReference().get(stringRepresentation);
    }

    @Override
    public Iterator<Pair<State, Object>> getSuccessors(State s, Object[] acts) {
        return new stateIterator(s, acts);
    }

    @Override
    public boolean milestoneReached(Float d, Float current_value, State temp) {
        return d < current_value;
    }

    private ArrayList<TransitionGround> eventsApplication(State s, float delta1, Collection<TransitionGround> events) throws CloneNotSupportedException {
        ArrayList<TransitionGround> ret = new ArrayList<>();
        while (true) {
            boolean at_least_one = false;
            for (TransitionGround ev : events) {
                if (ev.isApplicable(s,this.relevantUndefinedVariablesPresent, this)) {
                    at_least_one = true;
                    s.apply(ev, s.clone());
                    ret.add(ev);
                }
            }
            if (!at_least_one) {
                return ret;
            }
        }

    }

    public Collection<TransitionGround> getEventsSet() {
        if (eventsSet == null) {
            eventsSet = new LinkedHashSet<>();
        }
        return eventsSet;
    }

    public Collection<TransitionGround> getProcessesSet() {
        if (processesSet == null) {
            processesSet = new LinkedHashSet<>();
        }
        return processesSet;
    }

    private State saveInitInit() {
        if (this.pureInit == null) {
            this.pureInit = new MAPPDDLState(this.getInitNumFluentsValues(), getInitBoolFluentsValues());
        }
        return pureInit;
    }


    public void putNumFluentReference(NumFluent t) {
        getNumericFluentReference().put(t.toString(), t);
    }

    @Override
    public boolean satisfyGlobalConstraints(State temp) {
        return temp.satisfy(globalConstraints);
    }

    private void groundViaMetricFF() {

        MetricFFGrounder mff = new MetricFFGrounder(this, this.linkedDomain.getPddlFilRef(), this.pddlFilRef);
        mff.doGrounding();

    }

    public TransitionGround getActionsByName(String actionName) {
        for (var v : this.actions) {
            final String name1 = v.toString();
            if (actionName.equals(name1)) {
                return v;
            }
        }
        return null;
    }

    /**
     * @return the simplifyActions
     */
    public boolean isSimplifyActions() {
        return simplifyActions;
    }

    public PDDLDomain getLinkedDomain() {
        return linkedDomain;
    }


    /**
     * Get the value of domainName
     *
     * @return the value of domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Set the value of domainName
     *
     * @param domainName new value of domainName
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Get the value of pddlFilRef
     *
     * @return the value of pddlFilRef
     */
    public String getPddlFileReference() {
        return pddlFilRef;
    }

    /**
     * Set the value of pddlFilRef
     *
     * @param pddlFilRef new value of pddlFilRef
     */
    public void setPddlFilRef(String pddlFilRef) {
        this.pddlFilRef = pddlFilRef;
    }

    public void saveProblem(String pddlNewFile) throws IOException {
        pddlFilRef = pddlNewFile;
        final StringBuilder str = new StringBuilder("");
        str.append("(define (problem ").append(getName()).append(") ");
        str.append("(:domain ").append(this.getDomainName()).append(") \n");
        str.append(this.getObjects().pddlPrint());
        str.append("(:init ");
        for (var v : initBoolFluentsValues.entrySet()) {
            if (v.getValue()) {
                str.append(v.getKey()).append("\n");
            }
        }
        for (var v : this.initNumFluentsValues.entrySet()) {
            str.append("(= (").append(v.getKey()).append(") ").append(v.getValue().pddlPrint(false)).append(")\n");
        }
        str.append(")\n");
        str.append("(:goal ");
        str.append(this.getLiftedGoals().pddlPrint(false));
        str.append(" )\n");
        if (this.metric != null) {
            str.append(this.metric.pddlPrint());
        }
        str.append("\n )");


        final Writer file = new BufferedWriter(new FileWriter(pddlNewFile));
        file.append(str);
        file.close();
    }

    public void saveProblemWithObjectInterpretation(String pddlNewFile) throws IOException {
        pddlFilRef = pddlNewFile;
        //        final StringBuilder toWrite = new StringBuilder().append(this.metric.pddlPrint()).append("\n"
        //                + ")");
        //
        Writer file = new BufferedWriter(new FileWriter(pddlNewFile));
        StringBuilder builder = new StringBuilder();
        builder.append(this.getDomainName()).append(")");
        file.write("(define (problem temp)");
        file.write("(:domain ");
        file.write(builder.toString());
        file.write(this.getObjects().pddlPrint());
        file.write(Printer.stringBuilderPddlPrintWithDummyTrue(this, (PDDLState) init).toString());
        file.write("(:goal (forall (?interpr - interpretation)");
        file.write(this.getGoals().pddlPrintWithExtraObject() + ")))");
        file.close();
    }

    /**
     * @param file - the pathfile representing the pddl problem
     * @throws IOException
     * @throws org.antlr.runtime.RecognitionException
     */
    public void parseProblem(String file) throws IOException, RecognitionException {
        pddlFilRef = file;
        ANTLRInputStream in;
        in = new ANTLRInputStream(new FileInputStream(file));
        PddlLexer lexer = new PddlLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PddlParser parser = new PddlParser(tokens);
        PddlParser.pddlDoc_return root = parser.pddlDoc();
        if (parser.invalidGrammar()) {
            System.out.println("Grammar is violated");
        }
        //System.out.println("Problem Parsed, building data structure now");
        CommonTree t = (CommonTree) root.getTree();
        //        System.out.println("tree:" + t.toStringTree());
        //        exploreTree(t);
        this.one_of_s = new LinkedHashSet();
        this.or_s = new LinkedHashSet();
        //        System.out.println(this.objects);
        fc = new FactoryConditions(null, (LinkedHashSet<Type>) types, this.getObjects());
        if (this.unknonw_predicates == null) {
            this.unknonw_predicates = new LinkedHashSet();
        }
        for (int i = 0; i < t.getChildCount(); i++) {
            Tree child = t.getChild(i);
            //System.out.println(child.getChild(0).getText());
            //            System.out.println(fc.constants);
            switch (child.getType()) {
                case PddlParser.PROBLEM_DOMAIN:
                    this.setDomainName(child.getChild(0).getText());
                    break;
                case PddlParser.PROBLEM_NAME:
                    name = child.getChild(0).getText();
                    break;
                case PddlParser.OBJECTS:
                    addObjects(child);
                    break;
                case PddlParser.INIT:
                    addInitFacts(child);
                    break;
                case PddlParser.FORMULAINIT:
                    Tree andCondition = child.getChild(0).getChild(0);
                    if (child.getChild(0).getChildCount() > 1) {
                        for (int j = 1; j < child.getChild(0).getChildCount(); j++) {
                            this.unknonw_predicates.add((BoolPredicate) addUnknown(child.getChild(0).getChild(j)));
                        }
                    }
                    this.belief = fc.createGoals(andCondition);
                    //                    this.belief = fc.createCondition(child.getChild(0), null);
                    break;
                case PddlParser.GOAL:
                    liftedGoals = null;
                    Condition con = fc.createCondition(child.getChild(0), null);
                    if (!(con instanceof ComplexCondition)) {
                        liftedGoals = new AndCond(Collections.singleton(con));
                    } else {
                        liftedGoals = (ComplexCondition) con;
                    }
                    break;
                case PddlParser.PROBLEM_METRIC:
                    addMetric(child);
                    break;
            }
        }
        for (PDDLObject object : this.getObjects()) {
            final ArrayList object1 = new ArrayList<>(List.of(object, object));
            this.getInitBoolFluentsValues().put(BoolPredicate.getPredicate("=", object1), true);
        }
        //System.out.println("Total number of Numeric Fluents:"+this.counterNumericFluents);
    }

    protected void addObjects(Tree c) {
        for (int i = 0; i < c.getChildCount(); i++) {
            if (this.linkedDomain != null) {
                String typeName;
                if (c.getChild(i).getChild(0) == null) {
                    typeName = "object";
                } else {
                    typeName = c.getChild(i).getChild(0).getText();
                }
                Type t = linkedDomain.getTypeByName(typeName);
                if (t == null) {
                    System.out.println(c.getChild(i).getChild(0).getText() + " not found");
                    System.exit(-1);
                }
                this.getObjects().add(PDDLObject.object(c.getChild(i).getText(), t));
            } else {
                throw new RuntimeException("Need to link the domain first");
            }
        }
    }

    protected Expression createExpression(Tree t) {
        int test = t.getType();
        switch (t.getType()) {
            case PddlParser.BINARY_OP: {
                BinaryOp ret = new BinaryOp();
                ret.setOperator(t.getChild(0).getText());
                ret.setLhs(createExpression(t.getChild(1)));
                ret.setRhs(createExpression(t.getChild(2)));
                ret.grounded = true;
                return ret;
            }
            case PddlParser.NUMBER: {
                //Float.
                PDDLNumber ret = new PDDLNumber(Float.valueOf(t.getText()));
                return ret;
            }
            case PddlParser.FUNC_HEAD: {
                String name = t.getChild(0).getText();
                ArrayList variables = new ArrayList();
                for (int i = 1; i < t.getChildCount(); i++) {
                    variables.add(this.getObjectByName(t.getChild(i).getText()));
                }
                return NumFluent.createNumFluent(name, variables, true);
            }
            case PddlParser.UNARY_MINUS:
                return new MinusUnary(createExpression(t.getChild(0)));
            case PddlParser.MULTI_OP: {
                MultiOp ret = new MultiOp(t.getChild(0).getText());
                for (int i = 1; i < t.getChildCount(); i++) {
                    //System.out.println("Figlio di + o * " + createExpression(t.getChild(i)));
                    ret.addExpression(createExpression(t.getChild(i)));
                }
                ret.grounded = true;
                return ret;
            }
            default:
                break;
        }
        return null;
    }

    protected void addInitFacts(Tree child) {
//        this.setInitNumFluentsValues(new HashMap());
//        this.setInitBoolFluentsValues(new HashMap());
        for (int i = 0; i < child.getChildCount(); i++) {
            Tree c = child.getChild(i);
            switch (c.getType()) {
                case PddlParser.PRED_INST:
                    getInitBoolFluentsValues().put(fc.buildPredicate(c, null), true);
                    break;
                case PddlParser.INIT_EQ:
                    this.getInitNumFluentsValues().put((NumFluent) createExpression(c.getChild(0)), (PDDLNumber) createExpression(c.getChild(1)));
                    break;
                case PddlParser.UNKNOWN:
                    this.unknonw_predicates.add((BoolPredicate) addUnknown(c));
                    break;
                case PddlParser.ONEOF:
                    this.one_of_s.add((OneOf) fc.createCondition(c, null));
                    break;
                case PddlParser.OR_GD:
                    this.or_s.add((OrCond) fc.createCondition(c, null));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * A pretty representation for the pddl problem
     */
    public void prettyPrint() {
        System.out.println("\ninit: (Bool Fluents)" + initBoolFluentsValues + "(Num Fluents)" + initNumFluentsValues + "\nObjects: " + getProblemObjects() + "\nGoals:" + getLiftedGoals() + "\n");
//        if (metric.getMetExpr() instanceof MultiOp) {
//            MultiOp temp = (MultiOp) metric.getMetExpr();
//            System.out.println("\n Metric:" + temp.getExpr().size());
//        }
        out.println(initBoolFluentsValues);
        out.println(initNumFluentsValues);
        out.println("Actions: " + actions);
    }

    protected void exploreTree(Tree t) {
        if (t == null) {
            return;
        }
        if (t.getChildCount() == 0) {
            System.out.println("Foglia:" + t.getText() + "Tipo:" + t.getType());
            return;
        }
        System.out.println("Nodo intermedio: " + t.getText() + "Tipo:" + t.getType());
        for (int i = 0; i < t.getChildCount(); i++) {
            exploreTree(t.getChild(i));
        }
        return;
    }

    /**
     * @return the objects - the objects of the pddl problem
     */
    public PDDLObjects getProblemObjects() {
        return getObjects();
    }

    /**
     * This is to be used only after prepare for search has run
     *
     * @return the init - the initial status of the problem
     */
    @Override
    public State getInit() {
        return init;
    }

    /**
     * @return the liftedGoals - the goal set
     */
    public Condition getGoals() {
        return groundGoals;
    }

    protected void addMetric(Tree t) {
        //System.out.println(t.toStringTree());
        metric = new Metric(t.getChild(0).getText(), createExpression(t.getChild(1)));

    }

    /**
     * @return the metric
     */
    public Metric getMetric() {
        return metric;
    }


    /**
     * @param string - the name of the object we want
     * @return the term representing the object
     */
    public PDDLObject getObjectByName(String string) {
        return Utils.getObjectByName(this.getObjects(), string);
    }

    public double getInitFunctionValue(NumFluent f) {
        return ((PDDLState) init).fluentValue(f);
    }

    public NumFluent getNumFluent(String string, ArrayList terms) {
        for (NumFluent fAssign : this.getInitNumFluentsValues().keySet()) {
            if (fAssign.getName().equals(string)) {
                if (fAssign.getTerms().equals(terms)) {
                    return fAssign;
                }
            }
        }
        return null;
    }

    public ArrayList getNumFluents() {
        return new ArrayList(this.getInitNumFluentsValues().keySet());
    }

    /**
     * @return the propositionalTime
     */
    public long getPropositionalTime() {
        return propositionalTime;
    }

    /**
     * @param propositionalTime the propositionalTime to set
     */
    public void setPropositionalTime(long propositionalTime) {
        this.propositionalTime = propositionalTime;
    }

    /**
     * @return the actions
     */
    public Collection getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(Set actions) {
        this.actions = actions;
    }

    public void parseProblem(String string, PDDLObjects constants) throws IOException, RecognitionException {
        this.getObjects().addAll(constants);
        parseProblem(string);
    }

    /**
     * @return the possStates
     */
    public RelState getPossStates() {
        return possStates;
    }

    /**
     * @param possStates the possStates to set
     */
    public void setPossStates(RelState possStates) {
        this.possStates = possStates;
    }

    public void removeObjects(ParametersAsTerms constantsFound) {
        for (Object c : constantsFound) {
            this.getObjects().remove(c);
        }
    }

    /**
     * @return the objects
     */
    public PDDLObjects getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(PDDLObjects objects) {
        this.objects = objects;
    }

    private Condition addUnknown(Tree infoAction) {
        if (infoAction == null) {
            return null;
        }
        if (infoAction.getType() == PddlParser.PRED_INST) {
            //estrapola tutti i predicati e ritornali come set di predicati
            //            AndCond and = new AndCond();
            //            and.addConditions();
            return fc.buildPredicate(infoAction, null);
        } else if (infoAction.getType() == PddlParser.UNKNOWN) {
            return addUnknown(infoAction.getChild(0));
        } else {
            System.out.println("Some serious error:" + infoAction);
            return null;
        }
    }

    public Condition getPredicate(BoolPredicate aThis) {
        for (BoolPredicate p : this.getInitBoolFluentsValues().keySet()) {
            if (p.equals(aThis)) {
                return p;
            }
        }
        return aThis;
    }

    public PDDLNumber getNumFluentInitialValue(NumFluent aThis) {
        PDDLNumber nf = this.getInitNumFluentsValues().get(aThis);
        if (nf == null) {
            return null;
        }
        return nf;
    }

    public Iterable<NumFluent> getNumFluentsInvolvedInInit() {
        if (this.getInitNumFluentsValues() == null) {
            return Collections.emptyList();
        }
        return this.getInitNumFluentsValues().keySet();
    }

    public boolean getInitBoolFluentValue(BoolPredicate aThis) {
        Boolean b = this.getInitBoolFluentsValues().get(aThis);
        return b != null && b;
    }

    public Iterable getPredicatesInvolvedInInit() {
        if (this.getInitBoolFluentsValues() == null) {
            return Collections.emptyList();
        }
        return this.getInitBoolFluentsValues().keySet();
    }

    /**
     * @return the initNumFluentsValues
     */
    public Map<NumFluent, PDDLNumber> getInitNumFluentsValues() {
        return initNumFluentsValues;
    }


    /**
     * @return the initBoolFluentsValues
     */
    public Map<BoolPredicate, Boolean> getInitBoolFluentsValues() {
        return initBoolFluentsValues;
    }


    public void addFactValue(BoolPredicate predicate, boolean b) {
        initBoolFluentsValues.put(predicate, b);
    }

    public void addNumValue(NumFluent var, float value) {
        initNumFluentsValues.put(var, new PDDLNumber(value));
    }

    public void addAction(TransitionGround action) {
        this.actions.add(action);
    }


    @Override
    public Float gValue(State s, Object act, State temp, float gValue) {
        Metric m = this.getMetric();
        if (act instanceof Transition) {
            TransitionGround gr = (TransitionGround) act;
            if (gr == null) {
                return gValue;
            }
            return getTransitionCost(s, gr, gValue, ignoreMetric, m);
        } else if (act instanceof Integer) { //this was just a waiting action
            return gValue + 1;
        }
        {
            final ImmutablePair<TransitionGround, Integer> res = (ImmutablePair<TransitionGround, Integer>) act;

            return getTransitionCost(s, res.left, gValue, ignoreMetric, m, res.right);
        }
    }

    float getTransitionCost(State s, TransitionGround gr, Float previousG, boolean ignoreCost, Metric m) {
        return this.getTransitionCost(s, gr, previousG, ignoreCost, m, 1);
    }

    float getTransitionCost(State s, TransitionGround gr, Float previousG, boolean ignoreCost, Metric m, final int right) {
        if (ignoreCost) {
            return previousG + 1 * right;
        }
        if (m != null) {
            return previousG + gr.getActionCost(s, m, isSdac()) * right;
        } else {
            return previousG + 1 * right;
        }
    }

    public void prepareForSearch() throws Exception {
        PDDLProblem.this.prepareForSearch(true);
    }

    public void setGoals(Condition con) {
        liftedGoals = con;
    }

    protected class stateIterator implements ObjectIterator<Pair<State, Object>> {
        protected final State source;
        final private Object[] actionsSet;
        protected Object current;
        protected State newState;
        private int i;
        private boolean processDone;
        private boolean eventsPriority = false;

        public stateIterator(State source, Object[] actionsSet) {
            this.source = source;
            this.actionsSet = actionsSet;
            i = 0;
            processDone = false;
        }

        @Override
        public boolean hasNext() {
            if (!processesSet.isEmpty()) {
                if (!processDone) {
                    processDone = true;
                    final ImmutablePair<State, Integer> intelligentSimulation = intelligentSimulation(source, planningDelta, executionDelta, true);
                    if (intelligentSimulation != null) {
                        newState = intelligentSimulation.getLeft();
                        current = intelligentSimulation.getRight();
                        return true;
                    }
                }
            }
            while (i < actionsSet.length) {
                current = actionsSet[i];
                i++;

                if (current instanceof TransitionGround transitionGround) {

                    if (transitionGround.isApplicable(source,relevantUndefinedVariablesPresent, PDDLProblem.this)) {
                        newState = source.clone();
                        newState.apply(transitionGround, source);
                        if (newState.satisfy(globalConstraints)) {
                            if (eventsPriority) {
                                applyAllEvents(newState);
                            }
                            return true;
                        }
                    }
                } else if (current instanceof Pair) {
                    final Pair<TransitionGround, Integer> tempVar = (Pair<TransitionGround, Integer>) this.current;
                    final int b = applyActionMTimes(tempVar.getFirst(), tempVar.getSecond());
                    if (b > 1) {
                        current = new ImmutablePair(((Pair<TransitionGround, Integer>) this.current).getFirst(), b);
                        return true;
                    }
                }
            }
            return false;
        }

        public int applyActionMTimes(final TransitionGround act, int counter) {
            final State prev = source.clone();
            int i = 0;
            while (i < counter) {
                if (act.isApplicable(prev,relevantUndefinedVariablesPresent, PDDLProblem.this) && prev.satisfy(globalConstraints)) {
                    prev.apply((act), prev.clone());
                    i++;
                } else {
                    break;
                }
            }
            newState = prev;
            return i;
        }

        @Override
        public Pair<State, Object> next() {
            return new Pair(newState, current);
        }
    }


    protected void printInfo(PrintStream out) {
        //out.println("Reachable actions and processes: |A U P U E|:" + TransitionGround.totNumberOfTransitions);
    }

    public boolean validate(List<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> userPlan, BigDecimal execDelta, BigDecimal stepSize) throws CloneNotSupportedException {
        return validate(userPlan, execDelta, stepSize, null);
    }

    public boolean validate(List<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> internalPlanRepresentation, BigDecimal execDelta, BigDecimal stepSize, String planTrace) throws CloneNotSupportedException {
        BigDecimal previous = BigDecimal.ZERO;
        State current = (PDDLState) this.getInit();
        System.out.println("Plan under Validation/Simulation: " + internalPlanRepresentation);
        StringBuilder planTraceString = null;
        if (planTrace != null) {
            planTraceString = new StringBuilder();
            planTraceString.append(current.toString()).append("\n");
        }

        for (org.apache.commons.lang3.tuple.Pair<BigDecimal, Object> ele : internalPlanRepresentation) {
            TransitionGround right = (TransitionGround) ele.getRight();
            if (right.getSemantics().equals(Transition.Semantics.PROCESS)) {
                final ImmutablePair<State, Integer> stateCollectionPair
                        = simulation(current, execDelta, stepSize, false, planTraceString);
                if (stateCollectionPair == null) {
                    System.out.println("Constraint violated");
                    return false;
                } else {
                    current = stateCollectionPair.getLeft();
                }
            }
            previous = ele.getKey();
            if (ele.getRight() != null && right.getSemantics().equals(Transition.Semantics.ACTION)) {
                current.apply(right, current.clone());
                if (planTrace != null) {
                    planTraceString.append(current.toString()).append("\n");
                }
            }
        }
        if (planTrace != null) {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(planTrace));
                bf.append(planTraceString);
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(current);
        return current.satisfy(this.getGoals());
    }

    public boolean validateRefactored(List<org.apache.commons.lang3.tuple.Pair<BigDecimal, TransitionGround>> internalPlanRepresentation, BigDecimal execDelta, BigDecimal stepSize, String planTrace) throws CloneNotSupportedException {
        BigDecimal previous = BigDecimal.ZERO;
        State current = (PDDLState) this.getInit();
        System.out.println("Plan under Validation/Simulation: " + internalPlanRepresentation);
        StringBuilder planTraceString = null;
        if (planTrace != null) {
            planTraceString = new StringBuilder();
            planTraceString.append(current.toString()).append("\n");
        }

        for (org.apache.commons.lang3.tuple.Pair<BigDecimal, TransitionGround> ele : internalPlanRepresentation) {
            TransitionGround right = (TransitionGround) ele.getRight();
            if (right.getSemantics().equals(Transition.Semantics.PROCESS)) {
                final ImmutablePair<State, Integer> stateCollectionPair
                        = simulation(current, execDelta, stepSize, false, planTraceString);
                if (stateCollectionPair == null) {
                    System.out.println("Constraint violated");
                    return false;
                } else {
                    current = stateCollectionPair.getLeft();
                }
            }
            previous = ele.getKey();
            if (ele.getRight() != null && right.getSemantics().equals(Transition.Semantics.ACTION)) {
                current.apply(right, current.clone());
                if (planTrace != null) {
                    planTraceString.append(current.toString()).append("\n");
                }
            }
        }
        if (planTrace != null) {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(planTrace));
                bf.append(planTraceString);
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(current);
        return current.satisfy(this.getGoals());
    }

//    public ArrayList<Pair<BigDecimal,TransitionGround>> constructPlan(List<org.apache.commons.lang3.tuple.Pair<BigDecimal,Object>> internalPlanRepresentation,BigDecimal execDelta, BigDecimal stepSize, boolean events) throws CloneNotSupportedException {
//        BigDecimal previous = BigDecimal.ZERO;
//        State current = (PDDLState) this.getInit();
//        System.out.println("Internal Plan: "+internalPlanRepresentation);
//        ArrayList<Pair<BigDecimal,TransitionGround>> explicitPlan = new ArrayList();
//        org.apache.commons.lang3.tuple.Pair<BigDecimal, Object> lastEle = null;
//        
//        for (org.apache.commons.lang3.tuple.Pair<BigDecimal, Object> ele : internalPlanRepresentation) {
//            TransitionGround right = (TransitionGround) ele.getRight();
//            if (right.getSemantics().equals(Transition.Semantics.PROCESS)) {
//                final org.jgrapht.alg.util.Pair<State, Integer> stateCollectionPair
//                        = simulation(current,  execDelta, stepSize, false, null);
//                if (stateCollectionPair == null) {
//                    System.out.println("Constraint violated");
//                    return null;
//                } else {
//                    current = stateCollectionPair.getFirst();
//                }
//                for (var v: stateCollectionPair.getSecond()){
//                    previous.add(stepSize);
//                    if (v.getSemantics().equals(Transition.Semantics.EVENT)){
//                            explicitPlan.add(Pair.of(previous, v));
//                    }
//                }
//                lastEle = ele;
//            }else{
//                
//            }
//            previous = ele.getKey();
//            if (ele.getRight() != null && right.getSemantics().equals(Transition.Semantics.ACTION)) {
//                current.apply(right, current.clone());
//            }
//        }
////        System.out.println(current);
//        if (current.satisfy(this.getGoals()))
//            return explicitPlan;
//        return null;
//                  
//    }


    protected void advanceTime(Object frontier, SearchNode current_node, SearchProblem generingProblem, Object2FloatMap<State> g) {

        final ImmutablePair<State, Integer> stateCollectionPair = intelligentSimulation(current_node.s, planningDelta, executionDelta, true);

    }

    protected ImmutablePair<State, Integer> intelligentSimulation(State s, BigDecimal horizon, BigDecimal executionDelta, boolean intelligent) {
        return simulation(s, horizon, executionDelta, intelligent, null);
    }

    protected ImmutablePair<State, Integer> simulation(State s, BigDecimal horizon, BigDecimal executionDelta, boolean intelligent, StringBuilder traceString) {
        return simulation(s, horizon, executionDelta, intelligent, traceString, null);
    }

    protected ImmutablePair<State, Integer> simulation(State s, BigDecimal horizon, BigDecimal executionDelta,
                                                       boolean intelligent, StringBuilder traceString, ArrayList<TransitionGround> events) {
        final PDDLState next = (PDDLState) s.clone();
        if (horizon.compareTo(executionDelta) == -1) {
            System.out.println("Horizon: " + horizon + " Execution Delta: " + executionDelta);
            throw new RuntimeException("Planning Delta should be higher than delta execution");
        }
        if (horizon.remainder(executionDelta).compareTo(BigDecimal.ZERO) != 0) {
            System.out.println(horizon.remainder(executionDelta));
            System.out.println("Horizon: " + horizon + " Execution Delta: " + executionDelta);
            System.out.println("WARNING: Planning delta should be a multiple of delta execution");
        }
        final int iterations = horizon.divideToIntegralValue(executionDelta).intValue();

        applyAllEvents(next, events);
        for (int i = 0; i < iterations; i++) {
            PDDLState previousNext = next.clone();

            boolean atLeastOne = false;
            ArrayList<NumEffect> numEffect = new ArrayList();
            for (final TransitionGround act : this.getProcessesSet()) {
                if (act.getSemantics() == Transition.Semantics.PROCESS) {
                    final TransitionGround gp = (TransitionGround) act;
                    if (gp.isApplicable(next,relevantUndefinedVariablesPresent, this)) {
                        atLeastOne = true;
                        for (final NumEffect eff : (Collection<NumEffect>) gp.getConditionalNumericEffects().getAllEffects()) {
                            numEffect.add(eff);
                        }
                    }
                } else {
                    throw new RuntimeException("This shouldn't happen, " + act.getName() + " is not a process?");
                }
            }
            if (!atLeastOne) {
                numEffect.clear();
                if (i == 0) {
                    return null;
                }
                return ImmutablePair.of(previousNext, i);
            }
            //execute
            for (var v : numEffect) {
                next.apply(v, previousNext);
            }

            //next.apply(waiting, next.clone());
            next.time = next.time.add(executionDelta);
            if (!next.satisfy(this.globalConstraints)) {
                if (i == 0 || !intelligent) {
                    return null;
                }
                return ImmutablePair.of(previousNext, i);
            }
            if (events != null) {
                events.add(TransitionGround.waitingAction());
            }
            applyAllEvents(next, events);
            if (traceString != null) {
                traceString.append(next.toString()).append("\n");
            }
            if (intelligent && next.satisfy(this.getGoals())) {
                return ImmutablePair.of(next, i + 1);
            }
        }
        return ImmutablePair.of(next, iterations);
    }

    private boolean notDiv(double horizon, double executionDelta) {
        final double v = Math.IEEEremainder(horizon, executionDelta);
        return v >= Double.MIN_VALUE;
    }

    private void applyAllEvents(State s) {
        applyAllEvents(s, null);
    }

    private void applyAllEvents(final State s, final ArrayList<TransitionGround> ret) {
        while (true) {
            boolean at_least_one = false;
            for (final TransitionGround ev : this.getEventsSet()) {
                if (ev.isApplicable(s,relevantUndefinedVariablesPresent, this)) {
                    at_least_one = true;
                    s.apply(ev, s.clone());
                    if (ret != null)
                        ret.add(ev);
                }
            }
            if (!at_least_one) {
                return;
            }
        }

    }


    public List<PDDLState> simulate(List<org.apache.commons.lang3.tuple.Pair<BigDecimal, TransitionGround>> timedPlan, String delta, PDDLState s, PDDLProblem problem, boolean fullStates) {
        BigDecimal previous = BigDecimal.ZERO;
        final BigDecimal deltaDecimal = new BigDecimal(delta);
        final ArrayList<PDDLState> res = new ArrayList();
        res.add(s.clone());
        int planSize = timedPlan.size();
        for (final var v : timedPlan) {
            final BigDecimal timeAction = v.getKey();
            final BigDecimal subtract = timeAction.subtract(previous);
            if (subtract.compareTo(BigDecimal.ZERO) > 0) {
                if (fullStates) {
                    final int intValue = subtract.divideToIntegralValue(deltaDecimal).intValue();
                    for (int i = 0; i < intValue; i++) {
                        final ImmutablePair<State, Integer> simulatedState = this.simulation(s, deltaDecimal, deltaDecimal, false, null);
                        if (simulatedState == null) {
                            throw new RuntimeException("Global constraints are not satisfied starting from " + s);
                        }
                        s = (PDDLState) simulatedState.getLeft();
                        res.add(s.clone());
                    }
                } else {
                    final ImmutablePair<State, Integer> simulatedState = this.simulation(s, subtract, deltaDecimal, false, null);
                    if (simulatedState == null) {
                        throw new RuntimeException("Global constraints are not satisfied starting from " + s);
                    }
                    s = (PDDLState) simulatedState.getLeft();
                    res.add(s.clone());
                }
            }
            previous = timeAction;
            if (planSize > 1) {
                if (v.getValue().isApplicable(s,relevantUndefinedVariablesPresent, this)) {
                    s.apply(v.getValue(), s);
                    res.add(s.clone());
                } else {
                    throw new RuntimeException(v.getValue() + " is not applicable at time " + timeAction);
                }
            }
            planSize--;
        }
        return res;
    }


}
