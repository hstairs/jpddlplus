package enhsp2;

class PlannerConfig {
    String heuristic;
    String searchEngineString;
    String tieBreaking;
    boolean helpfulActions = false;
    boolean helpfulTransitions = false;
    boolean aibrPreprocessing = true;
    String redundantConstraints = null;

    PlannerConfig(String heuristic, String searchEngineString, String tieBreaking) {
        this.heuristic = heuristic;
        this.searchEngineString = searchEngineString;
        this.tieBreaking = tieBreaking;
    }

    PlannerConfig withHelpfulActions() {
        this.helpfulActions = true;
        return this;
    }

    PlannerConfig withHelpfulTransitions() {
        this.helpfulTransitions = true;
        return this;
    }

    PlannerConfig withRedundantConstraints(String rc) {
        this.redundantConstraints = rc;
        return this;
    }

    PlannerConfig withoutAibrPreprocessing() {
        this.aibrPreprocessing = false;
        return this;
    }
}