package com.hstairs.enhsp.integration.model;

import java.util.ArrayList;
import java.util.List;

public final class PlannerCliOptions {
    public String planner = "";
    public String heuristic = "hadd";
    public String search = "gbfs";
    public String novelty = "";
    public String kNov = "";
    public String tieBreaking = "arbitrary";
    public String redundantConstraints = "no";
    public String grounding = "internal";
    public String sdac = "disabled";
    public String wh = "";
    public String deltaPlanning = "";
    public String deltaExecution = "";
    public String deltaHeuristic = "";
    public String deltaValidation = "";
    public String delta = "";
    public String depthLimit = "";
    public String timeout = "";
    public String kSubdomains = "";
    public String tolerance = "";
    public String inputPlan = "";
    public String savePlan = "";
    public String posthocLogger = "";
    public String effectAbstraction = "";
    public String customArgs = "";

    public boolean helpfulActions;
    public boolean helpfulTransitions;
    public boolean printEvents;
    public boolean printTrace;
    public boolean ignoreMetric;
    public boolean disableAibrPreprocessing;
    public boolean stopAfterGrounding;
    public boolean internalValidation;
    public boolean onlyPlan;
    public boolean printActions;
    public boolean silent;
    public boolean autoAnytime;
    public boolean anytime;
    public boolean unitCostHeuristic;
    public boolean noPrintMakespan;
    public boolean printAllInfo;
    public boolean bucketBasedQueueSearch;
    public boolean tunnelling;
    public boolean saveSearchJson;

    public static PlannerCliOptions defaults() {
        return new PlannerCliOptions();
    }

    public PlannerCliOptions copy() {
        PlannerCliOptions c = new PlannerCliOptions();
        c.planner = planner;
        c.heuristic = heuristic;
        c.search = search;
        c.novelty = novelty;
        c.kNov = kNov;
        c.tieBreaking = tieBreaking;
        c.redundantConstraints = redundantConstraints;
        c.grounding = grounding;
        c.sdac = sdac;
        c.wh = wh;
        c.deltaPlanning = deltaPlanning;
        c.deltaExecution = deltaExecution;
        c.deltaHeuristic = deltaHeuristic;
        c.deltaValidation = deltaValidation;
        c.delta = delta;
        c.depthLimit = depthLimit;
        c.timeout = timeout;
        c.kSubdomains = kSubdomains;
        c.tolerance = tolerance;
        c.inputPlan = inputPlan;
        c.savePlan = savePlan;
        c.posthocLogger = posthocLogger;
        c.effectAbstraction = effectAbstraction;
        c.customArgs = customArgs;

        c.helpfulActions = helpfulActions;
        c.helpfulTransitions = helpfulTransitions;
        c.printEvents = printEvents;
        c.printTrace = printTrace;
        c.ignoreMetric = ignoreMetric;
        c.disableAibrPreprocessing = disableAibrPreprocessing;
        c.stopAfterGrounding = stopAfterGrounding;
        c.internalValidation = internalValidation;
        c.onlyPlan = onlyPlan;
        c.printActions = printActions;
        c.silent = silent;
        c.autoAnytime = autoAnytime;
        c.anytime = anytime;
        c.unitCostHeuristic = unitCostHeuristic;
        c.noPrintMakespan = noPrintMakespan;
        c.printAllInfo = printAllInfo;
        c.bucketBasedQueueSearch = bucketBasedQueueSearch;
        c.tunnelling = tunnelling;
        c.saveSearchJson = saveSearchJson;
        return c;
    }

    public void appendArgs(List<String> args) {
        addArgWithValue(args, "-planner", planner);
        addArgWithValue(args, "-h", heuristic);
        addArgWithValue(args, "-s", search);
        addArgWithValue(args, "-nov", novelty);
        addArgWithValue(args, "-knov", kNov);
        addArgWithValue(args, "-ties", tieBreaking);
        addArgWithValue(args, "-red", redundantConstraints);
        addArgWithValue(args, "-gro", grounding);
        addArgWithValue(args, "-sdac", sdac);
        addArgWithValue(args, "-wh", wh);
        addArgWithValue(args, "-dp", deltaPlanning);
        addArgWithValue(args, "-de", deltaExecution);
        addArgWithValue(args, "-dh", deltaHeuristic);
        addArgWithValue(args, "-dv", deltaValidation);
        addArgWithValue(args, "-d", delta);
        addArgWithValue(args, "-dl", depthLimit);
        addArgWithValue(args, "-timeout", timeout);
        addArgWithValue(args, "-k", kSubdomains);
        addArgWithValue(args, "-tolerance", tolerance);
        addArgWithValue(args, "-inputplan", inputPlan);
        addArgWithValue(args, "-sp", savePlan);
        addArgWithValue(args, "-with_posthoc_logger", posthocLogger);
        addArgWithValue(args, "-ea", effectAbstraction);

        addFlag(args, "-ha", helpfulActions);
        addFlag(args, "-ht", helpfulTransitions);
        addPlainFlag(args, "-pe", printEvents);
        addPlainFlag(args, "-pt", printTrace);
        addPlainFlag(args, "-im", ignoreMetric);
        addPlainFlag(args, "-dap", disableAibrPreprocessing);
        addPlainFlag(args, "-stopgro", stopAfterGrounding);
        addPlainFlag(args, "-ival", internalValidation);
        addPlainFlag(args, "-onlyplan", onlyPlan);
        addPlainFlag(args, "-print_actions", printActions);
        addPlainFlag(args, "-silent", silent);
        addPlainFlag(args, "-autoanytime", autoAnytime);
        addPlainFlag(args, "-anytime", anytime);
        addPlainFlag(args, "-uch", unitCostHeuristic);
        addPlainFlag(args, "-npm", noPrintMakespan);
        addPlainFlag(args, "-pai", printAllInfo);
        addPlainFlag(args, "-bbqs", bucketBasedQueueSearch);
        addPlainFlag(args, "-tun", tunnelling);
        addPlainFlag(args, "-sjr", saveSearchJson);

        args.addAll(tokenizeCliArgs(customArgs));
    }

    private static void addPlainFlag(List<String> args, String flag, boolean enabled) {
        if (enabled) {
            args.add(flag);
        }
    }

    private static void addFlag(List<String> args, String flag, boolean enabled) {
        if (enabled) {
            args.add(flag);
            args.add("true");
        }
    }

    private static void addArgWithValue(List<String> args, String flag, String value) {
        if (value != null && !value.isBlank()) {
            args.add(flag);
            args.add(value.trim());
        }
    }

    private static List<String> tokenizeCliArgs(String raw) {
        List<String> out = new ArrayList<>();
        if (raw == null || raw.isBlank()) {
            return out;
        }
        StringBuilder current = new StringBuilder();
        boolean inSingle = false;
        boolean inDouble = false;
        boolean escaped = false;
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (escaped) {
                current.append(c);
                escaped = false;
                continue;
            }
            if (c == '\\') {
                escaped = true;
                continue;
            }
            if (c == '\'' && !inDouble) {
                inSingle = !inSingle;
                continue;
            }
            if (c == '"' && !inSingle) {
                inDouble = !inDouble;
                continue;
            }
            if (Character.isWhitespace(c) && !inSingle && !inDouble) {
                if (current.length() > 0) {
                    out.add(current.toString());
                    current.setLength(0);
                }
                continue;
            }
            current.append(c);
        }
        if (current.length() > 0) {
            out.add(current.toString());
        }
        return out;
    }
}
