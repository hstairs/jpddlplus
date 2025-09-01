package enhsp2;

public enum Planner {
    SAT_HMRP(new PlannerConfig("hmrp", "gbfs", "arbitrary"), "satisficing planning"),
    SAT_HMRPH(new PlannerConfig("hmrp", "gbfs", "arbitrary").withHelpfulActions(), ""),
    SAT_HMRPHJ(new PlannerConfig("hmrp", "gbfs", "arbitrary").withHelpfulActions().withHelpfulTransitions(), ""),
    SAT_HMRPFF(new PlannerConfig("hmrp", "gbfs", "arbitrary").withRedundantConstraints("brute"), ""),
    SAT_HADD(new PlannerConfig("hadd", "gbfs", "smaller_g"), ""),
    SAT_AIBR(new PlannerConfig("aibr", "WAStar", "arbitrary"), ""),
    SAT_HRADD(new PlannerConfig("hradd", "gbfs", "smaller_g"), ""),
    OPT_HMAX(new PlannerConfig("hmax", "WAStar", "larger_g"), ""),
    OPT_HLM(new PlannerConfig("hlm-lp", "WAStar", "larger_g"), ""),
    OPT_HLMRD(new PlannerConfig("hlm-lp", "WAStar", "larger_g").withRedundantConstraints("brute"), ""),
    OPT_HRMAX(new PlannerConfig("hrmax", "WAStar", "larger_g"), "optimal planning"),
    OPT_BLIND(new PlannerConfig("blind", "WAStar", "larger_g").withoutAibrPreprocessing(), "blind planning");

    final PlannerConfig config;
    final String description;

    Planner(PlannerConfig config, String description) {
        this.config = config;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getHelp() {
        StringBuilder sb = new StringBuilder("Available planners:\n\n");
        for (Planner p : Planner.values()) {
            sb.append(String.format("  %-12s : %s%n", p.name(), p.getDescription()));
        }
        return sb.toString();
    }
}
