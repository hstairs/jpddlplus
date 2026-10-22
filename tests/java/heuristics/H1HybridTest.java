import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.File;
import java.math.BigDecimal;

public class H1HybridTest extends TestCase {

    private static final String BASE = "tests/fixtures/pddl/counterexamplehmax";

    private String path(String fileName) {
        return new File(BASE, fileName).getAbsolutePath();
    }

    private PDDLProblem loadProblem() throws Exception {
        final PDDLDomain domain = new PDDLDomain(path("d.pddl"));
        final PDDLProblem problem = new PDDLProblem(
                path("p.pddl"),
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

    private int numericGoalConditionId(H1 heuristic) {
        for (final Condition condition : heuristic.getGoalFormulation().getTerminalConditions()) {
            if (condition instanceof Comparison comparison) {
                return comparison.getId();
            }
        }
        fail("The test problem has no numeric goal condition");
        return -1;
    }

    public void testWitnessLocalFloorPreventsLateFastAchieverFromLoweringCondition() throws Exception {
        final PDDLProblem problem = loadProblem();
        final H1 heuristic = new H1(
                problem,
                false,
                false,
                false,
                "brute",
                false,
                false,
                false,
                false,
                null,
                false,
                -1,
                true,
                true
        );

        assertEquals(8f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(
                10f,
                heuristic.getConditionCost()[numericGoalConditionId(heuristic)],
                0.00001f
        );
    }

    public void testFactoryEnablesHybridForStaticCrd() throws Exception {
        final PDDLProblem problem = loadProblem();
        final SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
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

        assertEquals(H1.class, heuristic.getClass());
        assertEquals(8f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
    }

    public void testFactoryEnablesHybridByDefaultForNoCrd() throws Exception {
        final PDDLProblem problem = loadProblem();
        final H1 heuristic = (H1) PDDLHeuristic.getHeuristic(
                "hrmax",
                problem,
                "brute",
                false,
                false,
                false,
                -1,
                false
        );

        assertEquals(8f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(
                10f,
                heuristic.getConditionCost()[numericGoalConditionId(heuristic)],
                0.00001f
        );
    }

    public void testFactoryComposesOnlineCrdWithActivationFloor() throws Exception {
        final PDDLProblem problem = loadProblem();
        final H1 stateBased = (H1) PDDLHeuristic.getHeuristic(
                "hrmax",
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
        final H1 hybrid = (H1) PDDLHeuristic.getHeuristic(
                "hrmax",
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

        stateBased.computeEstimate(problem.getInit());
        hybrid.computeEstimate(problem.getInit());

        final int numericGoal = numericGoalConditionId(hybrid);
        assertEquals(5f, stateBased.getConditionCost()[numericGoal], 0.00001f);
        assertEquals(10f, hybrid.getConditionCost()[numericGoal], 0.00001f);
    }

    public void testDisabledFloorKeepsLegacyEstimate() throws Exception {
        final PDDLProblem problem = loadProblem();
        final H1 heuristic = new H1(
                problem,
                false,
                false,
                false,
                "brute",
                false,
                false,
                false,
                false,
                null,
                false,
                -1,
                true,
                false
        );

        assertEquals(8f, heuristic.computeEstimate(problem.getInit()), 0.00001f);
        assertEquals(
                5f,
                heuristic.getConditionCost()[numericGoalConditionId(heuristic)],
                0.00001f
        );
    }
}
