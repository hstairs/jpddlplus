import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class LmCutDominanceCounterexampleTest extends TestCase {

    private static final String BASE =
            "tests/fixtures/pddl/lmcut_dominance_counterexample";

    private PDDLProblem loadProblem() throws Exception {
        final PDDLDomain domain = new PDDLDomain(path("domain.pddl"));
        final PDDLProblem problem = new PDDLProblem(
                path("problem.pddl"),
                domain.constants,
                domain.getTypes(),
                domain,
                System.out,
                "internal",
                Sdac.disabled,
                false,
                BigDecimal.ONE,
                BigDecimal.ONE
        );
        domain.substituteEqualityConditions();
        assertTrue(problem.prepareForSearch(true));
        return problem;
    }

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

    private float estimate(
            PDDLProblem problem,
            String heuristicName,
            boolean hybrid
    ) {
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
                H1.CrdMode.NONE
        );
        return heuristic.computeEstimate(problem.getInit());
    }

    public void testLmCutDoesNotDominateWithoutActivationFloor() throws Exception {
        final PDDLProblem problem = loadProblem();

        assertStrictSeparation(problem, false);
    }

    public void testActivationFloorDoesNotCauseTheSeparation() throws Exception {
        final PDDLProblem problem = loadProblem();

        final float lmCutWithoutFloor = estimate(problem, "lmcut", false);
        final float hrmaxWithoutFloor = estimate(problem, "hrmax", false);
        final float cpZeroCutWithoutFloor = estimate(problem, "cpzerocut", false);

        assertStrictSeparation(problem, true);
        assertEquals(lmCutWithoutFloor, estimate(problem, "lmcut", true), 0.00001f);
        assertEquals(hrmaxWithoutFloor, estimate(problem, "hrmax", true), 0.00001f);
        assertEquals(cpZeroCutWithoutFloor, estimate(problem, "cpzerocut", true), 0.00001f);
    }

    private void assertStrictSeparation(PDDLProblem problem, boolean hybrid) {
        final float lmCut = estimate(problem, "lmcut", hybrid);
        final float hrmax = estimate(problem, "hrmax", hybrid);
        final float cpZeroCut = estimate(problem, "cpzerocut", hybrid);

        assertEquals(31.6f, lmCut, 0.00001f);
        assertEquals(40f, hrmax, 0.00001f);
        assertEquals(40f, cpZeroCut, 0.00001f);
        assertTrue(lmCut < hrmax);
        assertTrue(lmCut < cpZeroCut);
    }
}
