package com.hstairs.enhsp.integration.model;

import java.util.List;

public final class PlanTimepointGroup {
    public final String timeKey;
    public final List<PlanActionRef> actions;
    public final List<String> onDemandTransitions;

    public PlanTimepointGroup(String timeKey, List<PlanActionRef> actions, List<String> onDemandTransitions) {
        this.timeKey = timeKey;
        this.actions = actions;
        this.onDemandTransitions = onDemandTransitions;
    }
}
