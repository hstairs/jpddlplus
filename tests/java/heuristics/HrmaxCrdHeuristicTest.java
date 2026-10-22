import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class HrmaxCrdHeuristicTest extends TestCase {

    private static final String BASE = "tests/fixtures/pddl/ssnp_aware_twist";

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

    private int heuristicEstimate(String domainFileName, String problemFileName) throws Exception {
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
        SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                "hrmax",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                true,
                H1.CrdMode.STATIC
        );
        return (int) heuristic.computeEstimate(problem.getInit());
    }

    public void testHrmaxStaticCrdReturnsThreeWithSharedSupporter() throws Exception {
        assertEquals(3, heuristicEstimate(path("d.pddl"), path("p.pddl")));
    }

    public void testHrmaxStaticCrdCouplesTheNonInterferingWitnessPrecondition() throws Exception {
        assertEquals(6, heuristicEstimate(path("d2.pddl"), path("p.pddl")));
    }
}
