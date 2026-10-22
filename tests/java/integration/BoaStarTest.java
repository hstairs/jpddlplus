import com.hstairs.ppmajal.PDDLProblem.PDDLSolution;
import enhsp2.ENHSP;
import junit.framework.TestCase;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

public class BoaStarTest extends TestCase {
    public void testTwoNonDominatedPlansAndMultiMetricParsing() {
        String[] args = {
                "-o", "tests/fixtures/pddl/boastar/domain.pddl",
                "-f", "tests/fixtures/pddl/boastar/problem.pddl",
                "-s", "boa",
                "-h", "blind"
        };
        ENHSP planner = new ENHSP(false);
        planner.parseInput(args);
        planner.configurePlanner();
        assertTrue(planner.parsingDomainAndProblem(args));
        assertNotNull(planner.getProblem().multiMetrics);
        assertEquals(2, planner.getProblem().multiMetrics.length);

        PDDLSolution solution = planner.planAndGetSolution();
        assertNotNull(solution);
        assertEquals(2, solution.paretoPlans().size());
        assertEquals(2, solution.paretoCosts().size());
        assertEquals(2, solution.paretoTimes().size());
        assertEquals(Set.of(Pair.of(1.0f, 5.0f), Pair.of(5.0f, 1.0f)),
                new HashSet<>(solution.paretoCosts()));
        for (var plan : solution.paretoPlans()) {
            assertEquals(1, plan.size());
        }
    }
}
