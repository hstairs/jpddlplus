package enhsp2;

public enum Planner {
    SAT_HMRP(new PlannerConfig("hmrp", "gbfs", "arbitrary")),
    SAT_HMRPH(new PlannerConfig("hmrp", "gbfs", "arbitrary").withHelpfulActions()),
    SAT_HMRPHJ(new PlannerConfig("hmrp", "gbfs", "arbitrary").withHelpfulActions().withHelpfulTransitions()),
    SAT_HMRPFF(new PlannerConfig("hmrp", "gbfs", "arbitrary").withRedundantConstraints("brute")),
    SAT_HADD(new PlannerConfig("hadd", "gbfs", "smaller_g")),
    SAT_AIBR(new PlannerConfig("aibr", "WAStar", "arbitrary")),
    SAT_HRADD(new PlannerConfig("hradd", "gbfs", "smaller_g")),
    OPT_HMAX(new PlannerConfig("hmax", "WAStar", "larger_g")),
    OPT_HLM(new PlannerConfig("hlm-lp", "WAStar", "larger_g")),
    OPT_HLMRD(new PlannerConfig("hlm-lp", "WAStar", "larger_g").withRedundantConstraints("brute")),
    OPT_HRMAX(new PlannerConfig("hrmax", "WAStar", "larger_g")),
    OPT_BLIND(new PlannerConfig("blind", "WAStar", "larger_g").withoutAibrPreprocessing());

    final PlannerConfig config;

    Planner(PlannerConfig config) {
        this.config = config;
    }
}
