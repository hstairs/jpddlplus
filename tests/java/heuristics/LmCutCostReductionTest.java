import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.CPZeroCut;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.pddl.heuristics.advanced.LmCut;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class LmCutCostReductionTest extends TestCase {

    private static final String BASE = "tests/fixtures/pddl/lmcut_duplicate_action";

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

    private PDDLProblem loadProblem() throws Exception {
        PDDLDomain domain = new PDDLDomain(path("domain.pddl"));
        PDDLProblem problem = new PDDLProblem(
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

    public void testActionCrossingCutTwiceIsChargedOnce() throws Exception {
        final PDDLProblem problem = loadProblem();
        final LmCut heuristic = (LmCut) PDDLHeuristic.getHeuristic(
                "lmcut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false
        );

        assertEquals(1f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
        for (final float residualCost : heuristic.getActionCost()) {
            assertTrue("An action cost was reduced below zero", residualCost >= 0f);
        }
    }

    public void testZeroSupportClosureHandlesSharedSupporterOnce() throws Exception {
        final PDDLProblem problem = loadProblem();
        final CPZeroCut heuristic = new CPZeroCut(
                problem, "brute", false, -1, H1.CrdMode.NONE,
                false, CPZeroCut.ZeroingMode.BASE
        );

        assertEquals(1f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
        for (final float residualCost : heuristic.getActionCost()) {
            assertTrue("An action cost was reduced below zero", residualCost >= 0f);
        }
    }

    public void testCrdFlagDoesNotChangeLmCut() throws Exception {
        final PDDLProblem problem = loadProblem();
        final LmCut none = createLmCut(problem, H1.CrdMode.NONE);
        final LmCut online = createLmCut(problem, H1.CrdMode.ONLINE);

        assertEquals(
                none.computeEstimate(problem.getInit()),
                online.computeEstimate(problem.getInit()),
                0.00001f
        );
    }

    private LmCut createLmCut(PDDLProblem problem, H1.CrdMode crdMode) {
        return (LmCut) PDDLHeuristic.getHeuristic(
                "lmcut",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false,
                true,
                crdMode
        );
    }
}
