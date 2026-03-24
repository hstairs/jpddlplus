package com.hstairs.enhsp.integration.model;

import java.util.List;

public final class PlanningResult {
    public final String planText;
    public final String statsText;
    public final List<String> actionLines;
    public final List<String> actionStateBefore;
    public final List<String> actionStateAfter;
    public final List<String> displayLines;
    public final List<Integer> displayToActionStep;
    public final boolean timedPlan;
    public final List<PlanTimepointGroup> planTimepointGroups;
    public final String generatedDomainPddl;
    public final String generatedProblemPddl;

    public PlanningResult(String planText, String statsText, List<String> actionLines,
                          List<String> actionStateBefore, List<String> actionStateAfter,
                          List<String> displayLines, List<Integer> displayToActionStep,
                          boolean timedPlan, List<PlanTimepointGroup> planTimepointGroups,
                          String generatedDomainPddl, String generatedProblemPddl) {
        this.planText = planText;
        this.statsText = statsText;
        this.actionLines = actionLines;
        this.actionStateBefore = actionStateBefore;
        this.actionStateAfter = actionStateAfter;
        this.displayLines = displayLines;
        this.displayToActionStep = displayToActionStep;
        this.timedPlan = timedPlan;
        this.planTimepointGroups = planTimepointGroups;
        this.generatedDomainPddl = generatedDomainPddl;
        this.generatedProblemPddl = generatedProblemPddl;
    }

    public static PlanningResult error(String message, String generatedDomainPddl, String generatedProblemPddl) {
        return new PlanningResult(message, message, List.of(), List.of(), List.of(), List.of(message), List.of(-1),
                false, List.of(), generatedDomainPddl, generatedProblemPddl);
    }

    public boolean hasTrace() {
        return !actionLines.isEmpty()
                && actionStateBefore.size() == actionLines.size()
                && actionStateAfter.size() == actionLines.size();
    }

    public boolean hasGeneratedPddl() {
        return generatedDomainPddl != null && generatedProblemPddl != null;
    }

    public boolean hasPlanGraph() {
        return !actionLines.isEmpty() || !planTimepointGroups.isEmpty();
    }
}
