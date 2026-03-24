package com.hstairs.enhsp.integration.format;

import com.hstairs.enhsp.integration.model.PlanActionRef;
import com.hstairs.enhsp.integration.model.PlanTimepointGroup;
import com.hstairs.enhsp.integration.model.PlanningResult;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLSolution;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class PlanningResultFormatter {
    private PlanningResultFormatter() {
    }

    public static PlanningResult formatSolution(PDDLSolution solution, PDDLProblem problem,
                                                String generatedDomainPddl, String generatedProblemPddl) {
        if (solution == null || solution.rawPlan() == null) {
            return PlanningResult.error("Problem unsolvable or no plan returned.", generatedDomainPddl, generatedProblemPddl);
        }
        StringBuilder statsBuilder = new StringBuilder();
        List<String> actionLines = new ArrayList<>();
        List<String> displayLines = new ArrayList<>();
        List<Integer> displayToActionStep = new ArrayList<>();
        List<PlanTimepointGroup> graphGroups = new ArrayList<>();
        statsBuilder.append("Plan found.\n");
        statsBuilder.append("Plan length: ").append(solution.rawPlan().size()).append('\n');
        statsBuilder.append("Search metric: ").append(solution.gValueAtTheEnd()).append('\n');
        statsBuilder.append("Expanded nodes: ").append(solution.stats().nodesExpanded()).append('\n');
        statsBuilder.append("Evaluated states: ").append(solution.stats().nodesEvaluated()).append('\n');
        statsBuilder.append("Dead ends: ").append(solution.stats().deadEnds()).append('\n');
        statsBuilder.append("Duplicates: ").append(solution.stats().duplicates()).append('\n');
        List<ActionDisplayEntry> entries = new ArrayList<>();
        List<Integer> rawStepToActionIndex = new ArrayList<>();
        boolean hasNonActionTransitions = false;
        boolean hasActionWithExplicitTime = false;
        boolean hasNonZeroActionTime = false;
        boolean hasConcurrentActionsAtSameTime = false;
        Set<String> explicitActionTimes = new LinkedHashSet<>();
        int stepIndex = 0;
        int actionIndex = 0;
        for (ImmutablePair<BigDecimal, TransitionGround> planStep : solution.rawPlan()) {
            TransitionGround transition = planStep.getRight();
            String action = transition == null ? "<null transition>" : transition.toString();
            BigDecimal timeValue = planStep.getLeft();
            boolean explicitTime = timeValue != null;
            String timeKey = normalizeTimeKey(timeValue, stepIndex);
            boolean isAction = transition != null && transition.getSemantics().equals(Transition.Semantics.ACTION);
            boolean waiting = isWaitingActionText(action);
            if (!isAction) {
                hasNonActionTransitions = true;
            }
            int mappedActionIndex = -1;
            if (isAction) {
                mappedActionIndex = actionIndex;
                actionLines.add(action);
                actionIndex++;
                if (explicitTime) {
                    hasActionWithExplicitTime = true;
                    if (normalizeTimeValue(timeValue).compareTo(BigDecimal.ZERO) != 0) {
                        hasNonZeroActionTime = true;
                    }
                    if (!explicitActionTimes.add(timeKey)) {
                        hasConcurrentActionsAtSameTime = true;
                    }
                }
            }
            rawStepToActionIndex.add(mappedActionIndex);
            entries.add(new ActionDisplayEntry(stepIndex, mappedActionIndex, timeKey, action, isAction, waiting));
            stepIndex++;
        }

        boolean hasMultipleActionTimepoints = explicitActionTimes.size() > 1;
        boolean pddlPlusProblem = problem != null
                && ((problem.getProcessesSet() != null && !problem.getProcessesSet().isEmpty())
                || (problem.getEventsSet() != null && !problem.getEventsSet().isEmpty()));
        boolean timedPlanCandidate = hasNonActionTransitions
                || hasConcurrentActionsAtSameTime
                || (hasActionWithExplicitTime && (hasMultipleActionTimepoints || hasNonZeroActionTime));
        boolean timedPlan = pddlPlusProblem && timedPlanCandidate;
        statsBuilder.append("Plan layout: ").append(timedPlan ? "timepoint groups" : "sequential").append('\n');

        if (!timedPlan) {
            int index = 0;
            for (ActionDisplayEntry e : entries) {
                if (!e.actionTransition || e.waitingTransition) {
                    continue;
                }
                displayLines.add(index + ": " + e.action);
                displayToActionStep.add(e.actionIndex);
                graphGroups.add(new PlanTimepointGroup(
                        String.valueOf(index),
                        List.of(new PlanActionRef(e.actionIndex, e.action)),
                        List.of()
                ));
                index++;
            }
        } else {
            LinkedHashMap<String, List<ActionDisplayEntry>> groupedByTime = new LinkedHashMap<>();
            for (ActionDisplayEntry entry : entries) {
                groupedByTime.computeIfAbsent(entry.timeKey, ignored -> new ArrayList<>()).add(entry);
            }
            List<PlanTimepointGroup> compactGroups = new ArrayList<>();
            LinkedHashSet<String> carriedOnDemandTransitions = new LinkedHashSet<>();
            for (var groupEntry : groupedByTime.entrySet()) {
                String timeKey = groupEntry.getKey();
                List<ActionDisplayEntry> atTime = groupEntry.getValue();
                List<PlanActionRef> actionsAtTime = new ArrayList<>();
                LinkedHashSet<String> onDemandTransitionsAtTime = new LinkedHashSet<>();
                for (ActionDisplayEntry entry : atTime) {
                    if (entry.actionTransition && !entry.waitingTransition) {
                        actionsAtTime.add(new PlanActionRef(entry.actionIndex, entry.action));
                    } else if (entry.action != null && !entry.action.isBlank()) {
                        onDemandTransitionsAtTime.add(entry.action);
                    }
                }
                if (actionsAtTime.isEmpty()) {
                    carriedOnDemandTransitions.addAll(onDemandTransitionsAtTime);
                    continue;
                }
                LinkedHashSet<String> mergedOnDemandTransitions = new LinkedHashSet<>(carriedOnDemandTransitions);
                mergedOnDemandTransitions.addAll(onDemandTransitionsAtTime);
                carriedOnDemandTransitions.clear();
                compactGroups.add(new PlanTimepointGroup(timeKey, actionsAtTime, new ArrayList<>(mergedOnDemandTransitions)));
            }
            if (!carriedOnDemandTransitions.isEmpty()) {
                if (!compactGroups.isEmpty()) {
                    PlanTimepointGroup last = compactGroups.get(compactGroups.size() - 1);
                    LinkedHashSet<String> mergedTail = new LinkedHashSet<>(last.onDemandTransitions);
                    mergedTail.addAll(carriedOnDemandTransitions);
                    last.onDemandTransitions.clear();
                    last.onDemandTransitions.addAll(mergedTail);
                } else if (!groupedByTime.isEmpty()) {
                    String fallbackTime = groupedByTime.keySet().iterator().next();
                    compactGroups.add(new PlanTimepointGroup(fallbackTime, new ArrayList<>(), new ArrayList<>(carriedOnDemandTransitions)));
                }
            }
            for (PlanTimepointGroup group : compactGroups) {
                String header = "[t=" + group.timeKey + "] " + group.actions.size() + " action(s)";
                displayLines.add(header);
                displayToActionStep.add(-1);
                for (PlanActionRef actionRef : group.actions) {
                    displayLines.add("  - " + actionRef.action);
                    displayToActionStep.add(actionRef.actionIndex);
                }
                graphGroups.add(group);
            }
            statsBuilder.append("Timepoints shown: ").append(graphGroups.size()).append('\n');
        }

        List<String> actionStateBefore = new ArrayList<>();
        List<String> actionStateAfter = new ArrayList<>();
        try {
            if (pddlPlusProblem) {
                buildValidatedActionTrace(problem, solution.rawPlan(), rawStepToActionIndex, actionStateBefore, actionStateAfter);
            } else {
                buildSequentialActionTrace(problem, solution.rawPlan(), rawStepToActionIndex, actionStateBefore, actionStateAfter);
            }
            statsBuilder.append("Trace states available: ").append(actionStateBefore.size()).append('\n');
        } catch (Exception e) {
            statsBuilder.append("Trace states unavailable: ").append(e.getMessage()).append('\n');
        }
        return new PlanningResult(
                String.join("\n", displayLines),
                statsBuilder.toString(),
                actionLines,
                actionStateBefore,
                actionStateAfter,
                displayLines,
                displayToActionStep,
                timedPlan,
                graphGroups,
                generatedDomainPddl,
                generatedProblemPddl
        );
    }

    private static void buildSequentialActionTrace(PDDLProblem problem,
                                                   List<ImmutablePair<BigDecimal, TransitionGround>> rawPlan,
                                                   List<Integer> rawStepToActionIndex,
                                                   List<String> actionStateBefore,
                                                   List<String> actionStateAfter) throws Exception {
        State current = problem.getInit().clone();
        int rawIndex = 0;
        for (ImmutablePair<BigDecimal, TransitionGround> step : rawPlan) {
            TransitionGround action = step.getRight();
            int mappedActionIndex = rawStepToActionIndex.get(rawIndex);
            if (mappedActionIndex >= 0 && action != null
                    && action.getSemantics().equals(Transition.Semantics.ACTION)) {
                State before = current.clone();
                State prevForApply = current.clone();
                current.apply(action, prevForApply);
                State after = current.clone();
                actionStateBefore.add(String.valueOf(before));
                actionStateAfter.add(String.valueOf(after));
            }
            rawIndex++;
        }
    }

    private static void buildValidatedActionTrace(PDDLProblem problem,
                                                  List<ImmutablePair<BigDecimal, TransitionGround>> rawPlan,
                                                  List<Integer> rawStepToActionIndex,
                                                  List<String> actionStateBefore,
                                                  List<String> actionStateAfter) throws Exception {
        List<Pair<BigDecimal, TransitionGround>> traceInput = new ArrayList<>(rawPlan.size());
        traceInput.addAll(rawPlan);
        List<State> validatedTrace = problem.getTrace(traceInput, problem.executionDelta, problem.executionDelta);
        if (validatedTrace == null || validatedTrace.isEmpty()) {
            throw new IllegalStateException("Validated trace is empty.");
        }

        State current = validatedTrace.get(0);
        int traceIndex = 0;
        int rawIndex = 0;
        for (ImmutablePair<BigDecimal, TransitionGround> step : rawPlan) {
            TransitionGround transition = step.getRight();
            if (transition == null) {
                rawIndex++;
                continue;
            }

            if (transition.getSemantics().equals(Transition.Semantics.PROCESS)) {
                traceIndex = Math.min(traceIndex + 1, validatedTrace.size() - 1);
                current = validatedTrace.get(traceIndex);
                rawIndex++;
                continue;
            }

            int mappedActionIndex = rawStepToActionIndex.get(rawIndex);
            if (mappedActionIndex >= 0 && transition.getSemantics().equals(Transition.Semantics.ACTION)) {
                State before = current;
                traceIndex = Math.min(traceIndex + 1, validatedTrace.size() - 1);
                State after = validatedTrace.get(traceIndex);
                actionStateBefore.add(String.valueOf(before));
                actionStateAfter.add(String.valueOf(after));
                current = after;
            }
            rawIndex++;
        }
    }

    private static BigDecimal normalizeTimeValue(BigDecimal time) {
        if (time == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal normalized = time.stripTrailingZeros();
        if (normalized.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return normalized;
    }

    private static String normalizeTimeKey(BigDecimal time, int stepIndex) {
        if (time == null) {
            return "step " + stepIndex;
        }
        BigDecimal normalized = normalizeTimeValue(time);
        if (normalized.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        return normalized.toPlainString();
    }

    private static boolean isWaitingActionText(String action) {
        if (action == null) {
            return false;
        }
        String a = action.trim().toLowerCase();
        return a.equals("(waiting)") || a.contains("waiting");
    }

    private static final class ActionDisplayEntry {
        final int actionIndex;
        final String timeKey;
        final String action;
        final boolean actionTransition;
        final boolean waitingTransition;

        ActionDisplayEntry(int originalStepIndex, int actionIndex, String timeKey, String action,
                           boolean actionTransition, boolean waitingTransition) {
            this.actionIndex = actionIndex;
            this.timeKey = timeKey;
            this.action = action;
            this.actionTransition = actionTransition;
            this.waitingTransition = waitingTransition;
        }
    }
}
