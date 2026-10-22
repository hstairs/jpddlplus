import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

/** Documents why a disjunctive PCF must retain every disjunct. */
public class DisjunctivePcfCounterexampleTest extends TestCase {

    private static final String BASE =
            "tests/fixtures/pddl/disjunctive_pcf_inadmissibility";

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

    private float estimate(PDDLProblem problem, String heuristicName) {
        final SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                heuristicName,
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                true,
                H1.CrdMode.NONE
        );
        return heuristic.computeEstimate(problem.getInit());
    }

    public void testSetPcfProtectsAllDisjunctsInCPZeroCut() throws Exception {
        final PDDLProblem problem = loadProblem();

        // make-r followed by finish is a valid plan of cost 3.
        final float validPlanCost = 3f;
        assertEquals(2f, estimate(problem, "hmax"), 0.00001f);

        final float lmCut = estimate(problem, "lmcut");
        final float zeroSet = estimate(problem, "cpzerocut");

        assertEquals(4f, lmCut, 0.00001f);
        assertEquals(2f, zeroSet, 0.00001f);
        assertTrue(lmCut > validPlanCost);
        assertTrue(zeroSet <= validPlanCost);
    }
}
