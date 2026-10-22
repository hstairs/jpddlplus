import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.CPZeroCut;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class CPZeroCutTest extends TestCase {

    private static final String BASE = "tests/fixtures/pddl/ssnp_aware_twist";
    private static final String STATE_BASE = "tests/fixtures/pddl/ssnp_state_interference";
    private static final String STATE_RESET_BASE = "tests/fixtures/pddl/h1dec_ssnp_state_reset";
    private static final String SELECTIVE_BASE = "tests/fixtures/pddl/cpzero_selective";
    private static final String NUMERIC_SELECTIVE_BASE =
            "tests/fixtures/pddl/cpzero_selective_numeric";
    private static final String NUMERIC_INTERFERENCE_BASE =
            "tests/fixtures/pddl/cpzero_selective_numeric_interference";
    private static final String NUMERIC_REPETITION_COUNTEREXAMPLE_BASE =
            "tests/fixtures/pddl/cpzero_numfrac_repetition_counterexample";
    private static final String AND_PCF_BASE =
            "tests/fixtures/pddl/cpzero_and_pcf";
    private static final String HYPERGRAPH_INTERFERENCE_BASE =
            "tests/fixtures/pddl/cpzero_hypergraph_interference";

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

    private PDDLProblem loadProblem(String domainFileName, String problemFileName) throws Exception {
        PDDLDomain domain = new PDDLDomain(domainFileName);
        PDDLProblem problem = new PDDLProblem(
                problemFileName,
                domain.constants,
                domain.getTypes(),
                domain,
                System.out,
                "internal",
                Sdac.disabled,
                false,
                new BigDecimal(1.0),
                new BigDecimal(1.0)
        );
        domain.substituteEqualityConditions();
        assertTrue(problem.prepareForSearch(true));
        return problem;
    }

    public void testZeroSupportClosureVariantComputesEstimate() throws Exception {
        PDDLProblem problem = loadProblem(path("d.pddl"), path("p.pddl"));
        SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                "h1dec",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                false,
                H1.CrdMode.NONE
        );

        assertTrue(heuristic instanceof CPZeroCut);
        float estimate = heuristic.computeEstimate(problem.getInit());

        assertTrue(Float.isFinite(estimate));
        assertTrue(estimate >= 0f);
    }

    public void testZeroSupportClosureUsesOnlineCrdNumericAchieverCost() throws Exception {
        PDDLProblem problem = loadProblem(
                new File(STATE_BASE, "domain.pddl").getAbsolutePath(),
                new File(STATE_BASE, "problem.pddl").getAbsolutePath()
        );
        SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                false,
                H1.CrdMode.ONLINE
        );

        float firstEstimate = heuristic.computeEstimate(problem.getInit());
        float secondEstimate = heuristic.computeEstimate(problem.getInit());

        assertEquals(15f, firstEstimate, 0.00001f);
        assertEquals(firstEstimate, secondEstimate, 0.00001f);
    }

    public void testCrdReuseAcrossDifferentStatesMatchesFreshEvaluations() throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(STATE_BASE, "domain.pddl").getAbsolutePath(),
                new File(STATE_BASE, "problem.pddl").getAbsolutePath()
        );
        final PDDLState initial = (PDDLState) problem.getInit();
        final PDDLState supported = initial.clone();
        supported.setPropFluent(BoolPredicate.BoolFluent("gate"), true);
        supported.setPropFluent(BoolPredicate.BoolFluent("needed-support"), true);
        supported.setNumFluent(NumFluent.numericFluent("x"), 1d);
        final PDDLState nearGoal = supported.clone();
        nearGoal.setNumFluent(NumFluent.numericFluent("x"), 99d);

        for (final H1.CrdMode crd : new H1.CrdMode[] {H1.CrdMode.STATIC, H1.CrdMode.ONLINE}) {
            for (final CPZeroCut.ZeroingMode mode : CPZeroCut.ZeroingMode.values()) {
                for (final boolean floor : new boolean[] {false, true}) {
                    if (!floor && mode != CPZeroCut.ZeroingMode.BASE) {
                        continue;
                    }
                    final CPZeroCut reused = new CrdMinimumCheckingHeuristic(problem,
                            crd, floor, mode);
                    for (final PDDLState state : new PDDLState[] {
                            initial, supported, nearGoal, supported, initial
                    }) {
                        // Prime each reference from the same initial reachability relation.
                        final CPZeroCut fresh = new CPZeroCut(problem, "brute", false, -1,
                                crd, floor, mode);
                        fresh.computeEstimate(initial);
                        assertEquals("crd=" + crd + " mode=" + mode + " floor=" + floor,
                                fresh.computeEstimate(state), reused.computeEstimate(state), 0.00001f);
                    }
                }
            }
        }
    }

    public void testZeroSupportClosureOnlineCrdComposesWithActivationFloor() throws Exception {
        PDDLProblem problem = loadProblem(path("d2.pddl"), path("p_fractional.pddl"));
        SearchHeuristic stateBased = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                false,
                H1.CrdMode.ONLINE
        );
        SearchHeuristic hybrid = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                true,
                H1.CrdMode.ONLINE
        );

        assertEquals(0.5f, stateBased.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(1f, hybrid.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testCrdPrepassDoesNotHideInitiallyApplicableActions() throws Exception {
        PDDLProblem problem = loadProblem(
                new File(STATE_RESET_BASE, "domain.pddl").getAbsolutePath(),
                new File(STATE_RESET_BASE, "problem.pddl").getAbsolutePath()
        );
        SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                false,
                H1.CrdMode.ONLINE
        );

        assertEquals(1f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testConjunctivePcfSelectsOneMaximumAtATime() throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(AND_PCF_BASE, "domain.pddl").getAbsolutePath(),
                new File(AND_PCF_BASE, "problem.pddl").getAbsolutePath()
        );
        final SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                false,
                H1.CrdMode.NONE
        );

        assertEquals(2f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testHyperedgeFiltersNumericSupportersWithCrd() throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(HYPERGRAPH_INTERFERENCE_BASE, "domain.pddl").getAbsolutePath(),
                new File(HYPERGRAPH_INTERFERENCE_BASE, "problem.pddl").getAbsolutePath()
        );
        final SearchHeuristic withoutInterference = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.NONE, CPZeroCut.ZeroingMode.FLOOR
        );
        final SearchHeuristic withInterference = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.STATIC, CPZeroCut.ZeroingMode.FLOOR
        );

        // This fixture has metric action costs 3, 2 and 1. Its conditions are
        // propositional, so CRD does not filter numeric achievers here.
        assertEquals(6f, withoutInterference.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(6f, withInterference.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(6f, withInterference.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testFactoryExposesAllZeroSupportClosureCostVariants() throws Exception {
        final String[] heuristicNames = {
                "cpzerocut"
        };

        for (final String heuristicName : heuristicNames) {
            for (final H1.CrdMode crdMode : H1.CrdMode.values()) {
                for (final boolean hybrid : new boolean[] {false, true}) {
                    final PDDLProblem problem = loadProblem(path("d.pddl"), path("p.pddl"));
                    final SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                            heuristicName,
                            problem,
                            "brute",
                            false,
                            false,
                            false,
                            -1,
                            false,
                            hybrid,
                            crdMode
                    );

                    final String variant = heuristicName + " hybrid=" + hybrid
                            + " crd=" + crdMode;
                    assertTrue(variant, heuristic instanceof CPZeroCut);
                    final float firstEstimate = heuristic.computeEstimate(problem.getInit());
                    final float secondEstimate = heuristic.computeEstimate(problem.getInit());
                    assertTrue(variant, Float.isFinite(firstEstimate));
                    assertEquals(variant, firstEstimate, secondEstimate, 0.00001f);
                }
            }
        }
    }

    public void testSelectiveZeroingPreservesCostsBeyondTheThreshold() throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(SELECTIVE_BASE, "domain.pddl").getAbsolutePath(),
                new File(SELECTIVE_BASE, "problem.pddl").getAbsolutePath()
        );
        final SearchHeuristic complete = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                true,
                H1.CrdMode.NONE,
                false
        );
        final SearchHeuristic selective = PDDLHeuristic.getHeuristic(
                "cpzerocut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                true,
                H1.CrdMode.NONE,
                true
        );

        assertEquals(11f, complete.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(16f, selective.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(16f, selective.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testSelectiveZeroingRequiresActivationFloor() throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(SELECTIVE_BASE, "domain.pddl").getAbsolutePath(),
                new File(SELECTIVE_BASE, "problem.pddl").getAbsolutePath()
        );

        try {
            PDDLHeuristic.getHeuristic(
                    "cpzerocut",
                    problem,
                    "brute",
                    false,
                    false,
                    false,
                    -1,
                    false,
                    false,
                    H1.CrdMode.NONE,
                    true
            );
            fail("Selective zeroing must require the activation floor");
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage().contains("activation floor"));
        }
    }

    public void testNumericSelectiveZeroingAlsoExploresActivationFloor() throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(NUMERIC_SELECTIVE_BASE, "domain.pddl").getAbsolutePath(),
                new File(NUMERIC_SELECTIVE_BASE, "problem.pddl").getAbsolutePath()
        );
        final SearchHeuristic base = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.ONLINE, CPZeroCut.ZeroingMode.BASE
        );
        final SearchHeuristic floor = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.ONLINE, CPZeroCut.ZeroingMode.FLOOR
        );
        final SearchHeuristic numeric = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.ONLINE, CPZeroCut.ZeroingMode.NUM
        );

        assertEquals(10f, base.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(10f, floor.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(10f, numeric.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(10f, numeric.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testNumericSelectiveZeroingUsesFloorBranchWithoutCrd()
            throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(NUMERIC_SELECTIVE_BASE, "domain.pddl").getAbsolutePath(),
                new File(NUMERIC_SELECTIVE_BASE, "problem.pddl").getAbsolutePath()
        );
        final SearchHeuristic floor = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.NONE, CPZeroCut.ZeroingMode.FLOOR
        );
        final SearchHeuristic numeric = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.NONE, CPZeroCut.ZeroingMode.NUM
        );

        assertEquals(10f, floor.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(10f, numeric.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testNumericFractionalMinimalCounterexampleIsAdmissible()
            throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(NUMERIC_REPETITION_COUNTEREXAMPLE_BASE, "domain.pddl")
                        .getAbsolutePath(),
                new File(NUMERIC_REPETITION_COUNTEREXAMPLE_BASE, "problem.pddl")
                        .getAbsolutePath()
        );
        final SearchHeuristic fractional = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.NONE, CPZeroCut.ZeroingMode.NUM_FRACTIONAL
        );

        // The only valid plan costs 3. The activation-floor branch for
        // cheap-step must follow its own PCF (ready -> bootstrap), instead of
        // charging two repetitions of cheap-step and losing that provenance.
        assertEquals(3f, fractional.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testNumericSelectiveZeroingFallsBackToFloorWithInterference()
            throws Exception {
        final PDDLProblem problem = loadProblem(
                new File(NUMERIC_INTERFERENCE_BASE, "domain.pddl").getAbsolutePath(),
                new File(NUMERIC_INTERFERENCE_BASE, "problem.pddl").getAbsolutePath()
        );
        final SearchHeuristic floor = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.ONLINE, CPZeroCut.ZeroingMode.FLOOR
        );
        final SearchHeuristic numeric = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.ONLINE, CPZeroCut.ZeroingMode.NUM
        );

        final float floorEstimate = floor.computeEstimate(problem.getInit());
        assertEquals(11f, floorEstimate, 0.00001f);
        assertEquals(floorEstimate, numeric.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(floorEstimate, numeric.computeEstimate(problem.getInit()), 0.00001f);
    }

}
