package enhsp2;

import java.util.ArrayList;

import com.hstairs.ppmajal.extraUtils.IExternalLogger;

public class ENHSPBuilder {
    private final ENHSP enhsp;

    public ENHSPBuilder(boolean copyProblem) {
        this.enhsp = new ENHSP(copyProblem);
    }

    public ENHSPBuilder setDomainFile(String domainFile) {
        enhsp.domainFile = domainFile;
        return this;
    }

    public ENHSPBuilder setProblemFile(String problemFile) {
        enhsp.problemFile = problemFile;
        return this;
    }

    public ENHSPBuilder setPlanner(String planner) {
        enhsp.planner = planner;
        return this;
    }

    public ENHSPBuilder setHeuristic(String heuristic) {
        enhsp.heuristic = heuristic;
        return this;
    }

    public ENHSPBuilder setSearchEngine(String searchEngine) {
        enhsp.searchEngineString = searchEngine;
        return this;
    }

    public ENHSPBuilder setTieBreaking(String tieBreaking) {
        enhsp.tieBreaking = tieBreaking;
        return this;
    }

    public ENHSPBuilder setDeltaPlanning(String deltaPlanning) {
        enhsp.deltaPlanning = deltaPlanning;
        return this;
    }

    public ENHSPBuilder setDeltaExecution(String deltaExecution) {
        enhsp.deltaExecution = deltaExecution;
        return this;
    }

    public ENHSPBuilder setDeltaHeuristic(String deltaHeuristic) {
        enhsp.deltaHeuristic = deltaHeuristic;
        return this;
    }

    public ENHSPBuilder setDeltaValidation(String deltaValidation) {
        enhsp.deltaValidation = deltaValidation;
        return this;
    }

    public ENHSPBuilder setDepthLimit(float depthLimit) {
        enhsp.depthLimit = depthLimit;
        return this;
    }

    public ENHSPBuilder setTimeout(long timeoutInSeconds) {
        enhsp.timeOut = timeoutInSeconds * 1000;
        return this;
    }

    public ENHSPBuilder setNumSubdomains(int num) {
        enhsp.numSubdomains = num;
        return this;
    }

    public ENHSPBuilder setGroundingType(String groundingType) {
        enhsp.groundingType = groundingType;
        return this;
    }

    public ENHSPBuilder setRedundantConstraints(String mode) {
        enhsp.redundantConstraints = mode;
        return this;
    }

    public ENHSPBuilder setExternalLogger(IExternalLogger externalLogger) {
        enhsp.externalLogger = externalLogger;
        return this;
    }

    public ENHSPBuilder enableHelpActions(boolean enabled) {
        enhsp.helpfulActions = enabled;
        return this;
    }

    public ENHSPBuilder enableHelpfulTransitions(boolean enabled) {
        enhsp.helpfulTransitions = enabled;
        return this;
    }

    public ENHSPBuilder enableSavingSearchSpaceJson(boolean enabled) {
        enhsp.savingSearchSpaceJson = enabled;
        return this;
    }

    public ENHSPBuilder enablePrintTrace(boolean enabled) {
        enhsp.printTrace = enabled;
        return this;
    }

    public ENHSPBuilder enableStopAfterGrounding(boolean enabled) {
        enhsp.stopAfterGrounding = enabled;
        return this;
    }

    public ENHSPBuilder enableInternalValidation(boolean enabled) {
        enhsp.internalValidation = enabled;
        return this;
    }

    public ENHSPBuilder enableOnlyPlan(boolean enabled) {
        enhsp.onlyPlan = enabled;
        return this;
    }

    public ENHSPBuilder enableAutoAnytime(boolean enabled) {
        enhsp.autoAnytime = enabled;
        return this;
    }

    public ENHSPBuilder enableAnytime(boolean enabled) {
        enhsp.anyTime = enabled;
        return this;
    }

    public ENHSPBuilder enableSdac(boolean enabled) {
        enhsp.sdac = enabled;
        return this;
    }

    public ENHSPBuilder enablePrintActions(boolean enabled) {
        enhsp.printActions = enabled;
        return this;
    }

    public ENHSPBuilder setSavePlan(String savePlan) {
        enhsp.savePlan = savePlan;
        return this;
    }

    public ENHSPBuilder setInputPlan(String inputPlan) {
        enhsp.inputPlan = inputPlan;
        return this;
    }

    public ENHSPBuilder enableUnitCostHeuristic(boolean enabled) {
        enhsp.unitCostHeuristic = enabled;
        return this;
    }

    public ENHSPBuilder enableAibrPreprocessing(boolean enabled) {
        enhsp.aibrPreprocessing = enabled;
        return this;
    }

    public ENHSPBuilder enablePrintEvents(boolean enabled) {
        enhsp.printEvents = enabled;
        return this;
    }

    public ENHSPBuilder enableIgnoreMetric(boolean enabled) {
        enhsp.ignoreMetric = enabled;
        return this;
    }

    public ENHSPBuilder setWeightH(String wh) {
        enhsp.wh = wh;
        return this;
    }

    public ENHSPBuilder setWeightG(String gw) {
        enhsp.gw = gw;
        return this;
    }

    public ENHSP buildAndInitialize() {
        enhsp.parsingDomainAndProblem(null);
        enhsp.configurePlanner();
        return enhsp;
    }

    public ENHSP buildWithoutParsing() {
        return enhsp;
    }

    public ENHSPBuilder defaultBuilder() {
        this
            .setPlanner(null)
            .setHeuristic("hadd")
            .setSearchEngine("gbfs")
            .setTieBreaking(null)
            .setDeltaPlanning("1.0")
            .setRedundantConstraints("no")
            .setGroundingType("internal")
            .enableInternalValidation(false)
            .enableUnitCostHeuristic(false)
            .setDeltaExecution("1.0")
            .setDeltaHeuristic("1.0")
            .setDeltaValidation("1")
            .setDepthLimit(-1f)
            .setTimeout(Long.MAX_VALUE)
            .setInputPlan(null)
            .setNumSubdomains(2)
            .setWeightG(null)
            .setWeightH(null)
            .enableSavingSearchSpaceJson(false)
            .enableSdac(false)
            .enableHelpActions(false)
            .enableAutoAnytime(false)
            .enablePrintEvents(false)
            .enablePrintTrace(false)
            .setSavePlan(null)
            .enableOnlyPlan(false)
            .enableAnytime(false)
            .enableAibrPreprocessing(true)
            .enableStopAfterGrounding(false)
            .enableHelpfulTransitions(false)
            .enableIgnoreMetric(false)
            .enablePrintActions(false)
            .setExternalLogger(null);

        return this;   
    }

    public ArrayList<String> getAvailableHeuristics() {
        return enhsp.getAvailableHeuristics();
    }

    public ArrayList<String> getAvailableSearchEngines() {
        return enhsp.getAvailableSearchEngines();
    }

    public ArrayList<String> getAvailableTieBreakers() {
        return enhsp.getAvailableTieBreakers();
    }
}
