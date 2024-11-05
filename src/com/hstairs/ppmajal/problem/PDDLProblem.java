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
package jpddlplus.problem;

import com.google.common.collect.Sets;
import jpddlplus.conditions.*;
import jpddlplus.domain.PDDLDomain;
import jpddlplus.domain.SchemaGlobalConstraint;
import jpddlplus.domain.Type;
import jpddlplus.expressions.*;
import jpddlplus.extraUtils.Utils;
import jpddlplus.parser.PddlLexer;
import jpddlplus.parser.PddlParser;
import jpddlplus.heuristics.advanced.Aibr;
import jpddlplus.grounding.*;
import jpddlplus.search.SearchProblem;
import jpddlplus.transition.ConditionalEffects;
import jpddlplus.transition.Transition;
import jpddlplus.transition.TransitionGround;
import jpddlplus.transition.TransitionSchema;
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

  final private boolean ignoreMetric;
  final public BigDecimal executionDelta;
  final public BigDecimal planningDelta;

  private PDDLObjects objects;
  public State init;
  private Condition liftedGoals;
  private Condition groundGoals;
  public Collection<TransitionGround> actions;
  public Condition belief;
  public Collection<BoolPredicate> unknownPredicates;
  public Collection<OneOf> one_of_s;
  public Collection<OrCond> or_s;
  public Set<Type> types;
  private String name;
  protected Metric metric;
  protected String pddlFilRef;
  protected String domainName;
  protected long propositionalTime;
  protected RelState possStates;
  protected boolean simplifyActions;
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
  private HashMap<String, NumFluent> numFluentReference;
  private int totNumberOfBoolVariables;
  private int totNumberOfNumVariables;
  final public PrintStream out;
  final private String groundingMethod;
  private long groundingTime;
  private final boolean sdac;
  private boolean readyForSearch;

  public PDDLProblem(PDDLDomain pddlDomain) {
    this(pddlDomain, "internal", System.out, false, false);
  }

  public PDDLProblem(String arg, PDDLDomain d) {
    this(arg, d.constants, d.getTypes(), d, System.out, "internal", false, false,
        new BigDecimal("1.0"), new BigDecimal("1.0"));
  }

  public PDDLProblem(PDDLDomain domain, String groundingMethod, PrintStream out, boolean sdac,
      boolean ignoreMetric) {
    this(domain, groundingMethod, out, sdac, ignoreMetric, new BigDecimal("1.0"),
        new BigDecimal("1.0"));
  }

  public PDDLProblem(PDDLDomain domain, String groundingMethod, PrintStream out, boolean sdac,
      boolean ignoreMetric, BigDecimal planningDelta, BigDecimal executionDelta) {
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

  public boolean isReadyForSearch() {
    return readyForSearch;
  }

  static public HashSet<BoolPredicate> booleanFluents;

  public String getName() {
    return name;
  }

  public Condition getLiftedGoals() {
    return liftedGoals;
  }

  public PDDLProblem(String problemFile, PDDLObjects constants, Set<Type> types,
      PDDLDomain domain, PrintStream out, String groundingMethod, boolean sdac,
      boolean ignoreMetric, BigDecimal planningDelta, BigDecimal executionDelta) {

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
  }

  public int getTotNumberOfBoolVariables() {
    return totNumberOfBoolVariables;
  }

  public int getTotNumberOfNumVariables() {
    return totNumberOfNumVariables;
  }

  /**
   * @return the sdac
   */
  public boolean isSdac() {
    return sdac;
  }

  public long getGroundingTime() {
    return groundingTime;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {

    //EPddlProblem cloned = new PDDLProblem(this.pddlFilRef, this.objects, this.types, linkedDomain);
    PDDLProblem cloned = new PDDLProblem(pddlFilRef, getObjects(), types, linkedDomain, out,
        groundingMethod, sdac, ignoreMetric, planningDelta, executionDelta);

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
        Collection<TransitionGround> propositionalize = af.Propositionalize(act, getObjects(), this,
            getInitBoolFluentsValues(), linkedDomain);
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

  public void prepareForSearch(boolean aibrPreprocessing, boolean stopAfterGrounding)
      throws Exception {

    //simplification decoupled from the grounding
    this.groundingActionProcessesConstraints();
    this.genActualFluentsAndCleanTransitions();
    out.println("Grounding Time (msec): " + this.getGroundingTime());
    if (stopAfterGrounding) {
      return;
    }
    this.simplifyAndSetupInit(aibrPreprocessing);
    groundGoals = generate_inequalities(getGoals());
    readyForSearch = true;

  }

  protected Condition generate_inequalities(Condition con) {
    return con.transformEquality();
  }

  protected Set getActualFluents() {
    if (actualFluents == null) {
      actualFluents = new LinkedHashSet();
      Sets.SetView<TransitionGround> transitions = Sets.union(
          Sets.union(new HashSet(this.actions), new HashSet(this.getEventsSet())),
          new HashSet(this.getProcessesSet()));

      for (TransitionGround gr : transitions) {
        gr.updateInvariantFluents(actualFluents);

      }
    }
//        System.out.println(actualFluents);
    return actualFluents;
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
            res.add(new TransitionGround(act.getName(), act.getSemantics(), act.getParameters(),
                condition,
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

  protected void easyCleanUp(boolean aibrPreprocessing) {
    //out.println("prova");
//        this.saveInitInit();
    sweepStructuresForUnreachableStatements();

    boolean debug = false;
    if (debug) {
      out.print("This is the universe of numeric fluent: ");
      for (NumFluent nf : NumFluent.numFluentsBank.values()) {
        out.println("ID: " + nf.getId() + "->" + nf);
      }
      out.print("This is the universe of propositional fluent: ");
      for (BoolPredicate pred : BoolPredicate.getPredicatesDB().values()) {
        out.println("ID: " + pred.getId() + "->" + pred);
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
      if (v == Float.MAX_VALUE) {
        out.println("Problem Detected as Unsolvable");
        System.exit(-1);
      }
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
      sweepStructuresForUnreachableStatements();
    }

//        this.makePddlState(); //remake init so as to account for only reachable actions
  }

  protected void sweepStructuresForUnreachableStatements() {
    this.actualFluents = null;
    //the following just remove actions/processes/events over false and static predicates
    actions = (Collection<TransitionGround>) cleanEasyUnreachableTransitions(actions);
//        this.staticFluents = null;
    processesSet = (Collection<TransitionGround>) cleanEasyUnreachableTransitions(processesSet);
//        this.staticFluents = null;
    eventsSet = (Collection<TransitionGround>) cleanEasyUnreachableTransitions(eventsSet);
//        this.staticFluents = null;
    cleanIrrelevantConstraints(globalConstraintSet);

    groundGoals = getGoals().weakEval(this, this.getActualFluents());
    groundGoals = getGoals().normalize();
    if (getGoals().isUnsatisfiable()) {
      throw new RuntimeException("Goal is not reachable");
    }
    globalConstraints = (AndCond) globalConstraints.weakEval(this, this.getActualFluents());
    globalConstraints = (AndCond) globalConstraints.normalize();
    if (globalConstraints.isUnsatisfiable()) {
      throw new RuntimeException("Goal is not reachable");
    }

    if (this.metric != null && this.metric.getMetExpr() != null) {
      this.metric = new Metric(this.metric.getOptimization(),
          this.metric.getMetExpr().weakEval(this, this.getActualFluents()));
      if (this.metric.getMetExpr() == null) {
        this.metric = null;
      } else {
        this.metric = new Metric(this.metric.getOptimization(),
            this.metric.getMetExpr().normalize());
      }
    } else {
      this.metric = null;
    }

  }

  public void simplifyAndSetupInit(boolean aibrPreprocessing) throws Exception {

    long start = System.currentTimeMillis();

    easyCleanUp(aibrPreprocessing);

    globalConstraints = (AndCond) globalConstraints.normalize();
    makeInit();
    out.println("|F|: " + totNumberOfBoolVariables);
    out.println("|X|: " + totNumberOfNumVariables);

  }

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
    this.getInitNumFluentsValues().put(NumFluent.getNumFluent("#t", new ArrayList()),
        new PDDLNumber(Double.parseDouble(delta_t)));
  }

  public Sets.SetView<TransitionGround> getTransitions() {
    return Sets.union(Sets.union(new HashSet(actions), new HashSet<>(getEventsSet())),
        new HashSet(getProcessesSet()));

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
      involved_fluents.addAll(a.getNumFluentsNecessaryForExecution());
    }

    for (SchemaGlobalConstraint a : this.linkedDomain.getSchemaGlobalConstraints()) {
      involved_fluents.addAll(a.condition.getInvolvedFluents());
    }
    involved_fluents.addAll(getGoals().getInvolvedFluents());

    if (NumFluent.numFluentsBank != null) {
      Iterator<NumFluent> it = NumFluent.numFluentsBank.values().iterator();
      while (it.hasNext()) {
        NumFluent nf2 = it.next();
        boolean keep_it = false;
        for (NumFluent nf : involved_fluents) {
          if (nf.getName().equals(nf2.getName())) {
            keep_it = true;
            break;
          }
        }
        //                    it.remove();
        nf2.needsTrackingInState(keep_it);
      }
    }
  }

  private PDDLState makePddlState() {
    return this.makePddlState(true);
  }

  private PDDLState makePddlState(boolean invAnalysis) {
    PDDLState.optimised =
        (this.processesSet == null || this.processesSet.isEmpty()) && (this.eventsSet == null
            || this.eventsSet.isEmpty());
    fixNecessaryFluents();

    HashMap<Integer, Double> numFluents = new HashMap();
    totNumberOfNumVariables = 0;
    totNumberOfBoolVariables = 0;
    if (NumFluent.numFluentsBank != null) {
      for (NumFluent nf : NumFluent.numFluentsBank.values()) {
        if ((this.getActualFluents().contains(nf) && nf.has_to_be_tracked()) || !invAnalysis) {
          if (nf.isGrounded()) {
            PDDLNumber number = this.getInitNumFluentsValues().get(nf);
            if (number == null) {
              numFluents.put(nf.getId(), Double.NaN);
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
    PDDLState pddlState = new PDDLState(numFluents, boolFluents);
    return pddlState;

  }

  public void makeInit() {
    this.init = makePddlState();
    addTimeFluentToInit();
  }

  public Boolean goalSatisfied(State s) {
    return s.satisfy(this.getGoals());
  }

  private void createGroundGoals() {
    groundGoals = getLiftedGoals().pushNotToTerminals();
    groundGoals = groundGoals.ground(new HashMap(), getObjects());
  }

  private void addTimeFluentToInit() {
    ((PDDLState) this.init).time = BigDecimal.ZERO;
  }

  @Override
  public Iterator<Pair<State, Object>> getSuccessors(State s, Object[] acts) {
    return new stateIterator(s, acts);
  }

  @Override
  public boolean milestoneReached(Float d, Float current_value, State temp) {
    return d < current_value;
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

  public void putNumFluentReference(NumFluent t) {
    getNumericFluentReference().put(t.toString(), t);
  }

  @Override
  public boolean satisfyGlobalConstraints(State temp) {
    return temp.satisfy(globalConstraints);
  }

  public boolean isSimplifyActions() {
    return simplifyActions;
  }

  public PDDLDomain getLinkedDomain() {
    return linkedDomain;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public String getPddlFileReference() {
    return pddlFilRef;
  }

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
    //        System.out.println("tree: " + t.toStringTree());
    //        exploreTree(t);
    this.one_of_s = new LinkedHashSet();
    this.or_s = new LinkedHashSet();
    //        System.out.println(this.objects);
    fc = new FactoryConditions(null, (LinkedHashSet<Type>) types, this.getObjects());
    if (this.unknownPredicates == null) {
      this.unknownPredicates = new LinkedHashSet();
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
              this.unknownPredicates.add(
                  (BoolPredicate) addUnknown(child.getChild(0).getChild(j)));
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
            liftedGoals = con;
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
    //System.out.println("Total number of Numeric Fluents: "+this.counterNumericFluents);
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
          this.getInitNumFluentsValues().put((NumFluent) createExpression(c.getChild(0)),
              (PDDLNumber) createExpression(c.getChild(1)));
          break;
        case PddlParser.UNKNOWN:
          this.unknownPredicates.add((BoolPredicate) addUnknown(c));
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

  @Override
  public State getInit() {
    return init;
  }

  public Condition getGoals() {
    return groundGoals;
  }

  protected void addMetric(Tree t) {
    //System.out.println(t.toStringTree());
    metric = new Metric(t.getChild(0).getText(), createExpression(t.getChild(1)));

  }

  public Metric getMetric() {
    return metric;
  }

  public PDDLObject getObjectByName(String string) {
    return Utils.getObjectByName(this.getObjects(), string);
  }

  public long getPropositionalTime() {
    return propositionalTime;
  }

  public void setPropositionalTime(long propositionalTime) {
    this.propositionalTime = propositionalTime;
  }

  public Collection getActions() {
    return actions;
  }

  public void setActions(Set actions) {
    this.actions = actions;
  }

  public PDDLObjects getObjects() {
    return objects;
  }

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
      System.out.println("Some serious error: " + infoAction);
      return null;
    }
  }

  public PDDLNumber getNumFluentInitialValue(NumFluent aThis) {
    PDDLNumber nf = this.getInitNumFluentsValues().get(aThis);
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

  public Map<NumFluent, PDDLNumber> getInitNumFluentsValues() {
    return initNumFluentsValues;
  }

  public Map<BoolPredicate, Boolean> getInitBoolFluentsValues() {
    return initBoolFluentsValues;
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

  float getTransitionCost(State s, TransitionGround gr, Float previousG, boolean ignoreCost,
      Metric m) {
    return this.getTransitionCost(s, gr, previousG, ignoreCost, m, 1);
  }

  float getTransitionCost(State s, TransitionGround gr, Float previousG, boolean ignoreCost,
      Metric m, final int right) {
    if (ignoreCost) {
      return previousG + right;
    }
    if (m != null) {
      return previousG + gr.getActionCost(s, m, isSdac()) * right;
    } else {
      return previousG + right;
    }
  }

  protected class stateIterator implements ObjectIterator<Pair<State, Object>> {

    protected final State source;
    final private Object[] actionsSet;
    protected Object current;
    protected State newState;
    private int i;
    private boolean processDone;
    private final boolean eventsPriority = false;

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
          final ImmutablePair<State, Integer> intelligentSimulation = intelligentSimulation(source,
              planningDelta, executionDelta, true);
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

          if (transitionGround.isApplicable(source)) {
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
            current = new ImmutablePair(((Pair<TransitionGround, Integer>) this.current).getFirst(),
                b);
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
        i++;
        prev.apply((act), source);
        if (!act.isApplicable(newState) || !newState.satisfy(globalConstraints)) {
          return i;
        }
        newState = prev;
      }
      return i;
    }

    @Override
    public Pair<State, Object> next() {
      return new Pair(newState, current);
    }
  }

  public boolean validate(
      List<org.apache.commons.lang3.tuple.Pair<BigDecimal, Object>> internalPlanRepresentation,
      BigDecimal execDelta, BigDecimal stepSize, String planTrace) {
    BigDecimal previous = BigDecimal.ZERO;
    State current = this.getInit();
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
          planTraceString.append(current).append("\n");
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
    return current.satisfy(this.getGoals());
  }

  protected ImmutablePair<State, Integer> intelligentSimulation(State s, BigDecimal horizon,
      BigDecimal executionDelta, boolean intelligent) {
    return simulation(s, horizon, executionDelta, intelligent, null);
  }

  protected ImmutablePair<State, Integer> simulation(State s, BigDecimal horizon,
      BigDecimal executionDelta, boolean intelligent, StringBuilder traceString) {
    return simulation(s, horizon, executionDelta, intelligent, traceString, null);
  }

  protected ImmutablePair<State, Integer> simulation(State s, BigDecimal horizon,
      BigDecimal executionDelta,
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
          final TransitionGround gp = act;
          if (gp.isApplicable(next)) {
            atLeastOne = true;
            for (final NumEffect eff : gp.getConditionalNumericEffects().getAllEffects()) {
              numEffect.add(eff);
            }
          }
        } else {
          throw new RuntimeException(
              "This shouldn't happen, " + act.getName() + " is not a process?");
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
        traceString.append(next).append("\n");
      }
      if (intelligent && next.satisfy(this.getGoals())) {
        return ImmutablePair.of(next, i + 1);
      }
    }
    return ImmutablePair.of(next, iterations);
  }

  private void applyAllEvents(State s) {
    applyAllEvents(s, null);
  }

  private void applyAllEvents(final State s, final ArrayList<TransitionGround> ret) {
    while (true) {
      boolean at_least_one = false;
      for (final TransitionGround ev : this.getEventsSet()) {
        if (ev.isApplicable(s)) {
          at_least_one = true;
          s.apply(ev, s.clone());
            if (ret != null) {
                ret.add(ev);
            }
        }
      }
      if (!at_least_one) {
        return;
      }
    }

  }
}
