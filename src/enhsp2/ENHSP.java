package enhsp2;

import java.util.HashMap;
import java.util.Map;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.heuristics.HeuristicFactory;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.PDDLSearchEngine;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.search.SearchEngine;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Copyright (C) 2016-2017 Enrico Scala. Email enricos83@gmail.com.
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

/**
 * @author enrico
 */
public class ENHSP {

  private String domainFile;
  private String problemFile;
  private String searchEngineString;
  private boolean lazySearch;

  private String heuristic;
  private boolean helpfulActionsPruning;
  private boolean helpfulTransitions;
  private Map<Integer, Integer> reuseHeuristic;

  private List<String> heuristicList;
  private List<Boolean> helpfulActionsPruningList;
  private List<Boolean> helpfulTransitionsList;

  private String wh;
  private String gw;
  private String deltaExecution;
  private float depthLimit;
  private String savePlan;
  private boolean printTrace;
  private String tieBreaking;
  private String planner;
  private String deltaHeuristic;
  private String deltaPlanning;
  private String deltaValidation;
  private PDDLProblem problem;
  private boolean pddlPlus;
  private PDDLDomain domain;
  private PDDLDomain domainHeuristic;
  private PDDLProblem heuristicProblem;
  private long overallStart;
  private boolean anyTime;
  private long timeOut;
  private boolean aibrPreprocessing;
  private SearchHeuristic h;
  private List<SearchHeuristic> hs;
  private long overallPlanningTime;
  private float endGValue;
  private boolean internalValidation = false;
  private String redundantConstraints;
  private String groundingType;
  private boolean stopAfterGrounding;
  private boolean printEvents;
  private boolean sdac;
  private boolean onlyPlan;
  private boolean ignoreMetric;

  private boolean verboseHeuristic;

  public ENHSP() {
  }

  /**
   * Construct and parse ENHSP arguments. Configures planner settings if --planner not set
   *
   * @param args
   */
  public void parseInput(String[] args) {
    Options options = new Options();
    options.addRequiredOption("o", "domain", true, "PDDL domain file");
    options.addRequiredOption("f", "problem", true, "PDDL problem file");
    options.addOption("planner", true,
        "Fast Preconfgured Planner. For available options look into the code. This overrides all other parameters but domain and problem specs."
    );
    options.addOption("lazy", false, "Lazy evaluation for search");
    options.addOption("h", true, """
        heuristic: options (default is hadd):
        aibr, Additive Interval Based relaxation heuristic
        hadd, Additive version of subgoaling heuristic
        hradd, Additive version of subgoaling heuristic plus redundant constraints
        hmax, Hmax for Numeric Planning
        hrmax, Hmax for Numeric Planning with redundant constraints
        hmrp, heuristic based on MRP extraction
        blcost, goal sensitive heuristic (1 to non goal-states, 0 to goal-states)
        blind, full blind heuristic (0 to all states)
        """);
    options.addOption("s", true, """
        allows to select search strategy (default is gbfs):
        gbfs, Greedy Best First Search (f(n) = h(n))
        WAStar, WA* (f(n) = g(n) + h_w*h(n))
        """);
    options.addOption("ties", true,
        "tie-breaking (default is arbitrary): larger_g, smaller_g, arbitrary"
    );
    options.addOption("dp", "delta_planning", true, "planning decision executionDelta: float");
    options.addOption("de", "delta_execuction", true, "planning execution executionDelta: float");
    options.addOption("dh", "delta_heuristic", true, "planning heuristic executionDelta: float");
    options.addOption("dv", "delta_validation", true, "validation executionDelta: float");
    options.addOption("d", "delta", true,
        "Override other delta_<planning,execuction,validation,heuristic> configurations: float"
    );
    options.addOption("epsilon", true, "epsilon separation: float");
    options.addOption("wg", true, "g-values weight: float");
    options.addOption("wh", true, "h-values weight: float");
    options.addOption("ha", "helpful-actions", true, "activate helpful actions pruning");
    options.addOption("pe", "print-events-plan", false, "activate printing of events");

    options.addOption("ht", "helpful-transitions", true, "activate up-to-macro actions");
    options.addOption("sp", true, "Save plan. Argument is filename");
    options.addOption("pt", false, "print state trajectory (Experimental)");
    options.addOption("dap", false, "Disable Aibr Preprocessing");
    options.addOption("red", "redundant_constraints", true,
        "Choose mechanism for redundant constraints generation among, "
            + "no, brute and smart. No redundant constraints generation is the default"
    );
    options.addOption("gro", "grounding", true,
        "Activate grounding via internal mechanism, fd or metricff or internal or naive (default is internal)"
    );

    options.addOption("dl", true, "bound on plan-cost: float (Experimental)");
    options.addOption("anytime", false,
        "Run in anytime modality. Incrementally tries to find an upper bound. Does not stop until the user decides so"
    );
    options.addOption("timeout", true, "Timeout for anytime modality");
    options.addOption("stopgro", false, "Stop After Grounding");
    options.addOption("ival", false, "Internal Validation");
    options.addOption("sdac", false, "Activate State Dependent Action Cost (Very Experimental!)");
    options.addOption("onlyplan", false, "Print only the plan without waiting");
    options.addOption("vb", "verboseheuristic", false, "Log novelty over time");

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine cmd = parser.parse(options, args);
      domainFile = cmd.getOptionValue("o");
      problemFile = cmd.getOptionValue("f");
      planner = cmd.getOptionValue("planner");
      lazySearch = cmd.hasOption("lazy");
      heuristic = cmd.getOptionValue("h");
      heuristicList = new LinkedList<>();  // set only from planner configurations for now
      if (heuristic == null) {
        heuristic = "aibr";
      }
      searchEngineString = cmd.getOptionValue("s");
      if (searchEngineString == null) {
        searchEngineString = "gbfs";
      }
      tieBreaking = cmd.getOptionValue("ties");
      deltaPlanning = cmd.getOptionValue("dp");
      if (deltaPlanning == null) {
        deltaPlanning = "1.0";
      }
      String optionValue = cmd.getOptionValue("red");
      if (optionValue == null) {
        redundantConstraints = "no";
      } else {
        redundantConstraints = optionValue;
      }
      optionValue = cmd.getOptionValue("gro");
      if (optionValue != null) {
        groundingType = optionValue;
      } else {
        groundingType = "internal";
      }
      internalValidation = cmd.hasOption("ival");

      deltaExecution = cmd.getOptionValue("de");
      if (deltaExecution == null) {
        deltaExecution = "1.0";
      }
      deltaHeuristic = cmd.getOptionValue("dh");
      if (deltaHeuristic == null) {
        deltaHeuristic = "1.0";
      }
      deltaValidation = cmd.getOptionValue("dv");
      if (deltaValidation == null) {
        deltaValidation = "1";
      }
      String temp = cmd.getOptionValue("dl");
      if (temp != null) {
        depthLimit = Float.parseFloat(temp);
      } else {
        depthLimit = Float.NaN;
      }

      String timeOutString = cmd.getOptionValue("timeout");
      if (timeOutString != null) {
        timeOut = Long.parseLong(timeOutString) * 1000;
      } else {
        timeOut = Long.MAX_VALUE;
      }

      String delta = cmd.getOptionValue("delta");
      if (delta != null) {
        deltaHeuristic = delta;
        deltaValidation = delta;
        deltaPlanning = delta;
        deltaExecution = delta;
      }

      gw = cmd.getOptionValue("wg");
      wh = cmd.getOptionValue("wh");
      sdac = cmd.hasOption("sdac");
      helpfulActionsPruning = cmd.getOptionValue("ha") != null && "true".equals(
          cmd.getOptionValue("ha"));
      printEvents = cmd.hasOption("pe");
      printTrace = cmd.hasOption("pt");
      savePlan = cmd.getOptionValue("sp");
      onlyPlan = cmd.hasOption("onlyplan");
      anyTime = cmd.hasOption("anytime");
      aibrPreprocessing = !cmd.hasOption("dap");
      stopAfterGrounding = cmd.hasOption("stopgro");
      helpfulTransitions = cmd.getOptionValue("ht") != null && "true".equals(
          cmd.getOptionValue("ht"));
      ignoreMetric = cmd.hasOption("im");
      verboseHeuristic = cmd.hasOption("verboseheuristic");
    } catch (ParseException exp) {
      System.err.println("Parsing failed.  Reason: " + exp.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("enhsp", options);
      System.exit(1);
    }
  }

  public void parsingDomainAndProblem() {
    try {
      overallStart = System.currentTimeMillis();
      Pair<PDDLDomain, PDDLProblem> res = parseDomainProblem(domainFile, problemFile,
          deltaExecution, System.out
      );
      domain = res.getKey();
      problem = res.getRight();
      if (pddlPlus) {
        res = parseDomainProblem(domainFile, problemFile, deltaHeuristic,
            new PrintStream(new OutputStream() {
              public void write(int b) {
              }
            })
        );
        domainHeuristic = res.getKey();
        heuristicProblem = res.getRight();
      } else {
        heuristicProblem = problem;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Pair<PDDLDomain, PDDLProblem> parseDomainProblem(
      String domainFile, String problemFile, String delta, PrintStream out
  ) {
    try {
      final PDDLDomain localDomain = new PDDLDomain(domainFile);
      // domain.substituteEqualityConditions();
      pddlPlus =
          !localDomain.getProcessesSchema().isEmpty() || !localDomain.getEventsSchema().isEmpty();
      out.println("Domain parsed: " + domainFile);
      final PDDLProblem localProblem = new PDDLProblem(problemFile, localDomain.getConstants(),
          localDomain.getTypes(), localDomain, out, groundingType, sdac, ignoreMetric,
          new BigDecimal(deltaPlanning), new BigDecimal(deltaExecution)
      );
      if (!localDomain.getProcessesSchema().isEmpty()) {
        localProblem.setDeltaTimeVariable(delta);
      }
      // this second model is the one used in the heuristic. This can potentially be different from the one used in the execution model. Decoupling it
      // allows us to a have a finer control on the machine
      // the third one is the validation model, where, also in this case we test our plan against a potentially more accurate description
      out.println("Problem parsed: " + problemFile);
      out.println("Grounding..");

      localProblem.prepareForSearch(aibrPreprocessing, stopAfterGrounding);

      if (stopAfterGrounding) {
        System.exit(1);
      }
      return Pair.of(localDomain, localProblem);
    } catch (Exception ex) {
      Logger.getLogger(ENHSP.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * Checks if --planner is set, and if so calls setPlanner to configure planner settings
   */
  public void configurePlanner() {
    if (planner != null) {
      System.out.println("Planner: " + planner);
      setPlanner();
    }

    // log planner configuration
    System.out.println("Search: " + searchEngineString);
    if (heuristicList.isEmpty()) {
      System.out.println("Heuristic: " + heuristic);
    } else {
      for (int i = 0; i < heuristicList.size(); i++) {
        System.out.println("Heuristic_" + i + ": " + heuristicList.get(i));
      }
    }

    System.out.println("Timeout (msec): " + timeOut);
  }

  /**
   * Configures ENHSP solver settings if --planner is set.
   */
  private void setPlanner() {
    helpfulActionsPruning = false;
    helpfulTransitions = false;
    helpfulActionsPruningList = new ArrayList<>();
    helpfulTransitionsList = new ArrayList<>();
    tieBreaking = "arbitrary";
    reuseHeuristic = new HashMap<>();
    List<Boolean> booleans;

    if (planner.contains("-lazy")) {
      lazySearch = true;
      planner = planner.replace("-lazy", "");
    }

    if (planner.startsWith("sat-hw") || planner.startsWith("sat-hqb") || planner.startsWith(
        "sat-hiw") || planner.startsWith("sat-hiqb")) {
      heuristic = planner.replace("sat-", "");
      searchEngineString = "gbfs";
      tieBreaking = "arbitrary";
      if (planner.contains("mrphj")) {
        heuristic = heuristic.replace("mrphj", "mrp");
        helpfulActionsPruning = true;
        helpfulTransitions = true;
      }
      planner = "_completed_";
    }

    switch (planner) {
      //////// new planners ////////
      //
      case "sat-egbfs-hadd":
        heuristic = "hadd";
        searchEngineString = "egbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-egbfs-hradd":
        heuristic = "hradd";
        searchEngineString = "egbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-egbfs-hgc":
        heuristic = "hgc";
        searchEngineString = "egbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-egbfs-hgdc":
        heuristic = "hgdc";
        searchEngineString = "egbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-egbfs-hmrp":
        heuristic = "hmrp";
        searchEngineString = "egbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-egbfs-hiqb2add":
        heuristic = "hiqb2add";
        searchEngineString = "egbfs";
        tieBreaking = "arbitrary";
        break;
      //
      case "sat-jgbfs-hadd":
        heuristic = "hadd";
        searchEngineString = "jgbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-jgbfs-hradd":
        heuristic = "hradd";
        searchEngineString = "jgbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-jgbfs-hgc":
        heuristic = "hgc";
        searchEngineString = "jgbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-jgbfs-hgdc":
        heuristic = "hgdc";
        searchEngineString = "jgbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-jgbfs-hmrp":
        heuristic = "hmrp";
        searchEngineString = "jgbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-jgbfs-hiqb2add":
        heuristic = "hiqb2add";
        searchEngineString = "jgbfs";
        tieBreaking = "arbitrary";
        break;
      //
      case "sat-ngbfs-hadd":
        heuristic = "hadd";
        searchEngineString = "ngbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-ngbfs-hradd":
        heuristic = "hradd";
        searchEngineString = "ngbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-ngbfs-hgc":
        heuristic = "hgc";
        searchEngineString = "ngbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-ngbfs-hgdc":
        heuristic = "hgdc";
        searchEngineString = "ngbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-ngbfs-hmrp":
        heuristic = "hmrp";
        searchEngineString = "ngbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-ngbfs-hiqb2add":
        heuristic = "hiqb2add";
        searchEngineString = "ngbfs";
        tieBreaking = "arbitrary";
        break;
      // multi queue methods
      case "beluga":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hgdc", "hadd", "hiqb2add", "hmrp"}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-4-4iqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(List.of(
            new String[]{"hgc", "hadd", "hradd", "hmrp", "hiqb2gc", "hiqb2add", "hiqb2radd",
                "hiqb2mrp"}));
        booleans = new ArrayList<>(
            List.of(new Boolean[]{false, false, false, true, false, false, false, true}));
        reuseHeuristic.put(4, 0);
        reuseHeuristic.put(5, 1);
        reuseHeuristic.put(6, 2);
        reuseHeuristic.put(7, 3);
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-3d-3diqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hgc", "hadd", "hmrp", "hiqb2gc", "hiqb2add", "hiqb2mrp"}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true, false, false, true}));
        reuseHeuristic.put(3, 0);
        reuseHeuristic.put(4, 1);
        reuseHeuristic.put(5, 2);
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq3h3n":
      case "sat-mq-3e-3eiqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hgdc", "hadd", "hmrp", "hiqb2gdc", "hiqb2add", "hiqb2mrp"}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true, false, false, true}));
        reuseHeuristic.put(3, 0);
        reuseHeuristic.put(4, 1);
        reuseHeuristic.put(5, 2);
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq3h":
      case "sat-mq-3e":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hgdc", "hadd", "hmrp"}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq3n":
      case "sat-mq-3eiqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hiqb2gdc", "hiqb2add", "hiqb2mrp"}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-3a-3aiqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hadd", "hradd", "hmrp", "hiqb2add", "hiqb2radd", "hiqb2mrp"}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true, false, false, true}));
        reuseHeuristic.put(3, 0);
        reuseHeuristic.put(4, 1);
        reuseHeuristic.put(5, 2);
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-4iqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hiqb2gc", "hiqb2add", "hiqb2radd", "hiqb2mrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-3diqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(List.of(new String[]{"hiqb2gc", "hiqb2add", "hiqb2mrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-3aiqb2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hiqb2add", "hiqb2radd", "hiqb2mrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-4w2":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(
            List.of(new String[]{"hw2gc", "hw2add", "hw2radd", "hw2mrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-4":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(List.of(new String[]{"hgc", "hadd", "hradd", "hmrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-3d":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(List.of(new String[]{"hgc", "hadd", "hmrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mq-3a":
        searchEngineString = "mq";
        heuristicList = new ArrayList<>(List.of(new String[]{"hadd", "hradd", "hmrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
      case "sat-mqc-4":
        searchEngineString = "mqc";
        heuristicList = new ArrayList<>(List.of(new String[]{"hgc", "hadd", "hradd", "hmrp",}));
        booleans = new ArrayList<>(List.of(new Boolean[]{false, false, false, true}));
        helpfulActionsPruningList = booleans;
        helpfulTransitionsList = booleans;
        tieBreaking = "arbitrary";
        break;
        // manhatten goal count
        case "sat-hgdc": case "sat-gdc": case "sat-hmd":
        heuristic = "gdc";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
    //////// existing ENHSP planners ////////
        case "sat-hgc": case "sat-gc":
        heuristic = "gc";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-hmrp":
        heuristic = "hmrp";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-hmrph":
        heuristic = "hmrp";
        helpfulActionsPruning = true;
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-hmrphj":
        heuristic = "hmrp";
        helpfulActionsPruning = true;
        helpfulTransitions = true;
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-hmrpff":
        heuristic = "hmrp";
        helpfulActionsPruning = false;
        helpfulTransitions = false;
        redundantConstraints = "brute";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-hadd":
        heuristic = "hadd";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-aibr":
        heuristic = "aibr";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "sat-hradd":
        heuristic = "hradd";
        searchEngineString = "gbfs";
        tieBreaking = "arbitrary";
        break;
      case "opt-hmax":
        heuristic = "hmax";
        searchEngineString = "WAStar";
        tieBreaking = "larger_g";
        break;
      case "opt-hlm":
        heuristic = "hlm-lp";
        searchEngineString = "WAStar";
        tieBreaking = "larger_g";
        break;
      case "opt-hlmrd":
        heuristic = "hlm-lp";
        redundantConstraints = "brute";
        searchEngineString = "WAStar";
        tieBreaking = "larger_g";
        break;
      case "opt-hrmax":
        heuristic = "hrmax";
        searchEngineString = "WAStar";
        tieBreaking = "larger_g";
        break;
      case "opt-blind":
        heuristic = "blind";
        searchEngineString = "WAStar";
        tieBreaking = "larger_g";
        aibrPreprocessing = false;
        break;
      case "_completed_":
        break;
      default:
        throw new UnsupportedOperationException("Error: Unknown planner configuration " + planner);
    }
  }

  /**
   * Main planning driver code
   */
  public void planning() {
    try {
      printStats();
      setHeuristic();
      do {
        if (search() == null) {
          return;
        }
        depthLimit = endGValue;
        if (anyTime) {
          System.out.println(
              "NEW COST ==================================================================================>"
                  + depthLimit);
        }
        System.gc();
      } while (anyTime);
    } catch (Exception ex) {
      Logger.getLogger(ENHSP.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Configures heuristic configurations
   */
  private void setHeuristic() {
    // multi queue methods use multiple heuristics
    hs = new ArrayList<>();
    int n = heuristicList.size();
    if (n != helpfulActionsPruningList.size() && n != helpfulTransitionsList.size()) {
      throw new UnsupportedOperationException(
          "Number of heuristics do not match helpful actions or helpful transitions configs");
    }
    for (int i = 0; i < n; i++) {
      SearchHeuristic h_tmp;
      if (reuseHeuristic.containsKey(i)) {
        SearchHeuristic helperHeuristic = hs.get(reuseHeuristic.get(i));
        h_tmp = HeuristicFactory.getHeuristic(heuristicList.get(i), heuristicProblem,
            redundantConstraints, helpfulActionsPruningList.get(i), helpfulTransitionsList.get(i),
            helperHeuristic
        );
      } else {
        h_tmp = HeuristicFactory.getHeuristic(heuristicList.get(i), heuristicProblem,
            redundantConstraints, helpfulActionsPruningList.get(i), helpfulTransitionsList.get(i)
        );
      }
      hs.add(h_tmp);
      hs.get(i).setVerboseHeuristic(verboseHeuristic);
    }

    // fall back to singleton list if config is the usual single queue search
    if (hs.isEmpty()) {
      h = HeuristicFactory.getHeuristic(heuristic, heuristicProblem, redundantConstraints,
          helpfulActionsPruning, helpfulTransitions
      );
      h.setVerboseHeuristic(verboseHeuristic);
      hs.add(h);
    }
  }

  /**
   * Main search driver code. SearchEngine is called here
   */
  private LinkedList search() throws Exception {

    // raw list of actions returned by the search strategies
    LinkedList rawPlan;

    // manager of the search strategies
    final PDDLSearchEngine searchEngine = new PDDLSearchEngine(problem, hs, verboseHeuristic);

    switch (tieBreaking) {
      case "smaller_g":
        searchEngine.setTieBreaking(SearchEngine.TieBreaking.LOWERG);
        break;
      case "larger_g":
        searchEngine.setTieBreaking(SearchEngine.TieBreaking.HIGHERG);
        break;
      case "arbitrary":
        searchEngine.setTieBreaking(SearchEngine.TieBreaking.ARBITRARY);
        break;
      default:
        System.out.println("Unknown setting for break-ties. Arbitrary tie breaking");
        tieBreaking = "arbitrary";
        searchEngine.setTieBreaking(SearchEngine.TieBreaking.ARBITRARY);
        break;
    }

    searchEngine.setWH(wh != null ? Float.parseFloat(wh) : 1);

    depthLimit = !Float.isNaN(depthLimit) ? depthLimit : Float.POSITIVE_INFINITY;
    searchEngine.setDepthLimit(depthLimit);

    searchEngine.setLazySearch(lazySearch);

    // list version for MQ search set in SearchEngine initialisation
    searchEngine.setHelpfulActionsPruning(helpfulActionsPruning);

    // search algorithms
    switch (searchEngineString) {
      case "WAStar":
        System.out.println("Running WA-STAR");
        rawPlan = searchEngine.WAStar(getProblem(), timeOut);
        break;
      case "ngbfs":
        System.out.println("Running Numeric Greedy Best First Search");
        rawPlan = searchEngine.NumericGBFS(getProblem(), timeOut);
        break;
      case "egbfs":
        System.out.println("Running Numeric Greedy Best First Search");
        rawPlan = searchEngine.EnthusiasticGBFS(getProblem(), timeOut);
        break;
      case "jgbfs":
        System.out.println("Running Jumping Greedy Best First Search");
        rawPlan = searchEngine.JumpingGBFS(getProblem(), timeOut);
        break;
      case "gbfs":
        System.out.println("Running Greedy Best First Search");
        rawPlan = searchEngine.GBFS(getProblem(), timeOut);
        break;
      case "mqc":
        searchEngine.setCompleteSearch(true);
        System.out.println("Running Complete Multi Queue GBFS");
      case "mq":
        System.out.println("Running Multi Queue GBFS");
        rawPlan = searchEngine.AltGBFS(getProblem(), timeOut);
        break;
      default:
        throw new RuntimeException("Unknown search strategy: " + searchEngineString);
    }
    endGValue = searchEngine.currentG;

    overallPlanningTime = (System.currentTimeMillis() - overallStart);
    boolean valid;
    if (printTrace) {
      String fileName =
          getProblem().getPddlFileReference() + "_search_" + searchEngineString + "_h_" + heuristic
              + "_break_ties_" + tieBreaking + ".npt";
      System.out.println("Numeric Plan Trace saved to " + fileName);
    } else if (internalValidation) {
      Pair<PDDLDomain, PDDLProblem> res = parseDomainProblem(domainFile, problemFile,
          deltaValidation, new PrintStream(new OutputStream() {
            public void write(int b) {
            }
          })
      );
      valid = res.getRight().validate(rawPlan, new BigDecimal(this.deltaExecution),
          new BigDecimal(deltaValidation), "/tmp/temp_trace.pddl"
      );
      if (valid) {
        System.out.println("Plan is valid");
      } else {
        System.out.println("Plan is not valid");
      }
    }
    printInfo(rawPlan, searchEngine);
    return rawPlan;
  }

  public void printStats() {
    System.out.println("|A|: " + getProblem().getActions().size());
    System.out.println("|P|: " + getProblem().getProcessesSet().size());
    System.out.println("|E|: " + getProblem().getEventsSet().size());
    if (pddlPlus) {
      System.out.println("Delta time heuristic model: " + deltaHeuristic);
      System.out.println("Delta time planning model: " + deltaPlanning);
      System.out.println("Delta time search-execution model: " + deltaExecution);
      System.out.println("Delta time validation model: " + deltaValidation);
    }
  }

  private void printInfo(LinkedList<Pair<BigDecimal, Object>> sp, PDDLSearchEngine searchEngine) {

    PDDLState s = (PDDLState) searchEngine.getLastState();
    if (sp != null) {
      System.out.println("Problem Solved\n");
      System.out.println("Found Plan: ");
      printPlan(sp, pddlPlus, s, savePlan);
      System.out.println("\nPlan-Length: " + sp.size());
    } else {
      System.out.println("Problem unsolvable");
    }
    if (pddlPlus && sp != null) {
      System.out.println("Elapsed Time: " + s.time);
    }
    System.out.println("Metric (Search): " + searchEngine.currentG);
    System.out.println("Planning Time (msec): " + overallPlanningTime);
    System.out.println("Heuristic Time (msec): " + searchEngine.getHeuristicCpuTime());
    System.out.println("Search Time (msec): " + searchEngine.getOverallSearchTime());
    System.out.println("Expanded Nodes: " + searchEngine.getNodesExpanded());
    System.out.println("States Evaluated: " + searchEngine.getNumberOfEvaluatedStates());
    System.out.println("Fixed constraint violations during search (zero-crossing): "
        + searchEngine.constraintsViolations);
    System.out.println("Dead-Ends detected: " + searchEngine.getDeadEndsDetected());
    System.out.println("Duplicates detected: " + searchEngine.getDuplicatesDetected());
  }

  private void printPlan(
      LinkedList<Pair<BigDecimal, Object>> plan, boolean temporal, PDDLState par, String fileName
  ) {
    float i = 0f;
    Pair<BigDecimal, Object> previous = null;
    List<String> fileContent = new ArrayList<>();
    boolean startProcess = false;
    int size = plan.size();
    int j = 0;
    for (Pair<BigDecimal, Object> ele : plan) {
      j++;
      if (!temporal) {
        System.out.print(i + ": " + ele.getRight() + "\n");
        if (fileName != null) {
          TransitionGround t = (TransitionGround) ele.getRight();
          fileContent.add(t.toString());
        }
        i++;
      } else {
        TransitionGround t = (TransitionGround) ele.getRight();
        if (t.getSemantics() == TransitionGround.Semantics.PROCESS) {
          if (!startProcess) {
            previous = ele;
            startProcess = true;
          }
          if (j == size) {
            if (!onlyPlan) {
              System.out.println(previous.getLeft() + ": -----waiting---- " + "[" + par.time + "]");
            }
          }
        } else {
          if (t.getSemantics() != TransitionGround.Semantics.EVENT || printEvents) {
            if (startProcess) {
              startProcess = false;
              if (!onlyPlan) {
                System.out.println(
                    previous.getLeft() + ": -----waiting---- " + "[" + ele.getLeft() + "]");
              }
            }
            System.out.print(ele.getLeft() + ": " + ele.getRight() + "\n");
            if (fileName != null) {
              fileContent.add(ele.getLeft() + ": " + t);
            }
          } else {
            if (j == size) {
              if (!onlyPlan) {
                assert previous != null;
                System.out.println(
                    previous.getLeft() + ": -----waiting---- " + "[" + ele.getLeft() + "]");
              }
            }
          }
        }
      }
    }

    if (fileName != null) {
      try {
        if (temporal) {
          fileContent.add(par.time + ": @PlanEND ");
        }
        Files.write(Path.of(fileName), fileContent);
      } catch (IOException ex) {
        Logger.getLogger(ENHSP.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public PDDLProblem getProblem() {
    return problem;
  }
}
