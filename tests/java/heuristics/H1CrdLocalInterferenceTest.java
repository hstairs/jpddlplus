import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class H1CrdLocalInterferenceTest extends TestCase {

    private static final String BASE = "tests/fixtures/pddl/ssnp_local_interference";

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
                new BigDecimal(1.0),
                new BigDecimal(1.0)
        );
        domain.substituteEqualityConditions();
        assertTrue(problem.prepareForSearch(true));
        return problem;
    }

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

    private SearchHeuristic createHeuristic(
            PDDLProblem problem,
            boolean hybrid,
            H1.CrdMode crdMode
    ) {
        return PDDLHeuristic.getHeuristic(
                "hrmax",
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
    }

    public void testStaticCrdUsesOnlyTheCheapestInterferingAchiever() throws Exception {
        final PDDLProblem problem = loadProblem();

        final float estimate = createHeuristic(problem, false, H1.CrdMode.STATIC)
                .computeEstimate(problem.getInit());

        assertEquals(15f, estimate, 0.00001f);
    }

    public void testStaticCrdHybridUsesTheSameLocalInterferenceMinimum() throws Exception {
        final PDDLProblem problem = loadProblem();

        final float estimate = createHeuristic(problem, true, H1.CrdMode.STATIC)
                .computeEstimate(problem.getInit());

        assertEquals(15f, estimate, 0.00001f);
    }

    public void testNoCrdHybridKeepsTheGlobalAchieverMinimum() throws Exception {
        final PDDLProblem problem = loadProblem();

        final float estimate = createHeuristic(problem, true, H1.CrdMode.NONE)
                .computeEstimate(problem.getInit());

        assertEquals(10f, estimate, 0.00001f);
    }

    public void testStaticCausalAncestryKeepsEstimateStable() throws Exception {
        final PDDLProblem problem = loadProblem();
        final SearchHeuristic heuristic = createHeuristic(
                problem,
                true,
                H1.CrdMode.STATIC
        );

        final float firstEstimate = heuristic.computeEstimate(problem.getInit());
        final float secondEstimate = heuristic.computeEstimate(problem.getInit());

        assertEquals(15f, firstEstimate, 0.00001f);
        assertEquals(firstEstimate, secondEstimate, 0.00001f);
    }
}
