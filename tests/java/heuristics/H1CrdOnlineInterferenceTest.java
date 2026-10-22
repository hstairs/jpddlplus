import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class H1CrdOnlineInterferenceTest extends TestCase {

    private static final String BASE = "tests/fixtures/pddl/ssnp_state_interference";

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

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

    public void testOnlineCrdDropsTheSatisfiedPreconditionClosure() throws Exception {
        final PDDLProblem problem = loadProblem();

        final float staticEstimate = createHeuristic(
                problem,
                false,
                H1.CrdMode.STATIC
        )
                .computeEstimate(problem.getInit());
        final float stateEstimate = createHeuristic(
                problem,
                false,
                H1.CrdMode.ONLINE
        )
                .computeEstimate(problem.getInit());

        assertEquals(10f, staticEstimate, 0.00001f);
        assertEquals(15f, stateEstimate, 0.00001f);
    }

    public void testOnlineCrdComposesWithActivationFloor() throws Exception {
        final PDDLProblem problem = loadProblem();

        final float stateEstimate = createHeuristic(
                problem,
                false,
                H1.CrdMode.ONLINE
        )
                .computeEstimate(problem.getInit());
        final float hybridEstimate = createHeuristic(
                problem,
                true,
                H1.CrdMode.ONLINE
        )
                .computeEstimate(problem.getInit());

        assertEquals(15f, stateEstimate, 0.00001f);
        assertEquals(stateEstimate, hybridEstimate, 0.00001f);
    }

    public void testStateBasedClosureIsReusableAcrossEvaluations() throws Exception {
        final PDDLProblem problem = loadProblem();
        final SearchHeuristic heuristic = createHeuristic(
                problem,
                false,
                H1.CrdMode.ONLINE
        );

        final float firstEstimate = heuristic.computeEstimate(problem.getInit());
        final float secondEstimate = heuristic.computeEstimate(problem.getInit());

        assertEquals(15f, firstEstimate, 0.00001f);
        assertEquals(firstEstimate, secondEstimate, 0.00001f);
    }
}
