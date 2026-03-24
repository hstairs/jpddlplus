package com.hstairs.enhsp.integration.execution;

public final class PlanningStoppedException extends RuntimeException {
    public PlanningStoppedException() {
        super("Planning stopped");
    }
}
