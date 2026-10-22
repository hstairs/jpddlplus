import com.hstairs.ppmajal.PDDLProblem.PDDLIterativeOptimizationSupport;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.search.IterativeMetricSearch;
import com.hstairs.ppmajal.search.SearchEngine;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.search.SearchProblem;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.transition.Sdac;
import enhsp2.ENHSP;
import junit.framework.TestCase;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IterativeOptimizationSupportTest extends TestCase {

    public void testMaximizationFindsOptimumInTwoSuccessfulIterations() throws Exception {
        Path tempDir = Files.createTempDirectory("iterative-optimization-support-test");
        Path domainPath = tempDir.resolve("domain.pddl");
        Path problemPath = tempDir.resolve("problem.pddl");

        Files.writeString(domainPath, """
                (define (domain iterative-support-max)
                  (:requirements :strips :numeric-fluents)
                  (:predicates (done))
                  (:functions (score))

                  (:action finish_small
                    :parameters ()
                    :precondition (not (done))
                    :effect (and
                      (done)
                      (increase (score) 5)
                    )
                  )

                  (:action finish_large
                    :parameters ()
                    :precondition (not (done))
                    :effect (and
                      (done)
                      (increase (score) 7)
                    )
                  )
                )
                """);
        Files.writeString(problemPath, """
                (define (problem iterative-support-max-problem)
                  (:domain iterative-support-max)
                  (:init
                    (= (score) 0)
                  )
                  (:goal
                    (and
                      (done)
                      (>= (score) 0)
                    )
                  )
                  (:metric maximize (score))
                )
                """);

        PDDLDomain domain = new PDDLDomain(domainPath.toString());
        PDDLProblem problem = new PDDLProblem(
                problemPath.toString(),
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
        assertTrue(problem.prepareForSearch(true));

        ScriptedImprovingSearch baseSearch = new ScriptedImprovingSearch(problem);
        IterativeMetricSearch iterative = new IterativeMetricSearch(
                baseSearch,
                new PDDLIterativeOptimizationSupport(problem, null),
                10
        );

        SimpleSearchNode solution = iterative.search(problem, new ConstantHeuristic(problem), System.out);

        assertNotNull(solution);
        NumFluent score = NumFluent.numericFluent("score");
        assertEquals(7.0, score.eval(solution.s), 0.0001);
        assertEquals(List.of(5.0f, 7.0f), baseSearch.returnedScores);
        assertEquals(3, baseSearch.calls);
        assertNotNull(iterative.getStats().iterativeMetricSummary());
        assertEquals(1, iterative.getStats().iterativeMetricSummary().successfulImprovements());
        assertEquals(2, iterative.getIncumbentSolutions().size());
    }

    public void testMissingMetricFallsBackToPlanLength() throws Exception {
        Path tempDir = Files.createTempDirectory("iterative-optimization-plan-length-test");
        Path domainPath = tempDir.resolve("domain.pddl");
        Path problemPath = tempDir.resolve("problem.pddl");

        Files.writeString(domainPath, """
                (define (domain iterative-support-length)
                  (:requirements :strips)
                  (:predicates (done))

                  (:action finish
                    :parameters ()
                    :precondition (not (done))
                    :effect (done)
                  )
                )
                """);
        Files.writeString(problemPath, """
                (define (problem iterative-support-length-problem)
                  (:domain iterative-support-length)
                  (:init)
                  (:goal (done))
                )
                """);

        PDDLDomain domain = new PDDLDomain(domainPath.toString());
        PDDLProblem problem = new PDDLProblem(
                problemPath.toString(),
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
        assertTrue(problem.prepareForSearch(true));

        ScriptedPlanLengthSearch baseSearch = new ScriptedPlanLengthSearch(problem);
        IterativeMetricSearch iterative = new IterativeMetricSearch(
                baseSearch,
                new PDDLIterativeOptimizationSupport(problem, null),
                10
        );

        SimpleSearchNode solution = iterative.search(problem, new ConstantHeuristic(problem), System.out);

        assertNotNull(solution);
        assertEquals(1.0, iterative.getStats().iterativeMetricSummary().bestObjectiveValue(), 0.0001);
        assertEquals("no_improvement", iterative.getStats().iterativeMetricSummary().stopReason());
        assertEquals(List.of(2.9999f, 0.9999f), baseSearch.bounds);
    }

    public void testEnhspSavesIndexedPlansForIterativeMetricSearch() throws Exception {
        Path planPrefix = Files.createTempDirectory("iterative-save-plan-test").resolve("sol");
        ENHSP planner = new ENHSP(false);
        planner.parseInput(new String[]{
                "-o", "tests/fixtures/pddl/iterative_optimization/domain.pddl",
                "-f", "tests/fixtures/pddl/iterative_optimization/instance_1.pddl",
                "-planner", "sat-aibr",
                "-iopt",
                "-sp", planPrefix.toString()
        });
        assertTrue(planner.parsingDomainAndProblem(new String[0]));
        planner.configurePlanner();

        assertNotNull(planner.planAndGetSolution());

        Path firstPlan = Path.of(planPrefix + "_0");
        Path secondPlan = Path.of(planPrefix + "_1");
        assertTrue(Files.exists(firstPlan));
        assertTrue(Files.exists(secondPlan));
        assertFalse(Files.exists(planPrefix));
        assertFalse(Files.readAllLines(firstPlan).isEmpty());
        assertTrue(Files.readAllLines(secondPlan).size() >= Files.readAllLines(firstPlan).size());
    }

    private static final class ConstantHeuristic implements SearchHeuristic {
        private final PDDLProblem problem;

        private ConstantHeuristic(PDDLProblem problem) {
            this.problem = problem;
        }

        @Override
        public float computeEstimate(com.hstairs.ppmajal.problem.State s) {
            return 0;
        }

        @Override
        public Object[] getTransitions(boolean onlyHelpful) {
            return new Object[0];
        }

        @Override
        public java.util.Collection<com.hstairs.ppmajal.transition.TransitionGround> getAllTransitions() {
            return problem.getTransitions();
        }
    }

    private static final class ScriptedImprovingSearch extends SearchEngine {
        private final PDDLProblem problem;
        private final List<Float> candidateScores = List.of(5.0f, 7.0f);
        private final NumFluent score = NumFluent.numericFluent("score");
        private final BoolPredicate done = BoolPredicate.BoolFluent("done");
        private final SearchStats stats = new SearchStats(0, 0, 0, 0, 0, 0);
        private final List<Float> returnedScores = new ArrayList<>();
        private int calls = 0;

        private ScriptedImprovingSearch(PDDLProblem problem) {
            super(false);
            this.problem = problem;
        }

        @Override
        public SearchStats getStats() {
            return stats;
        }

        @Override
        public SimpleSearchNode search(SearchProblem p, SearchHeuristic h, PrintStream out) {
            calls++;
            for (float candidateScore : candidateScores) {
                PDDLState candidate = ((PDDLState) problem.getInit()).clone();
                candidate.setNumFluent(score, (double) candidateScore);
                candidate.setPropFluent(done, true);
                if (problem.goalSatisfied(candidate)) {
                    returnedScores.add(candidateScore);
                    return new SimpleSearchNode(candidate, null, null, 0, false);
                }
            }
            return null;
        }
    }

    private static final class ScriptedPlanLengthSearch extends SearchEngine {
        private final PDDLProblem problem;
        private final SearchStats stats = new SearchStats(0, 0, 0, 0, 0, 0);
        private final List<Float> bounds = new ArrayList<>();
        private float currentBound = Float.POSITIVE_INFINITY;

        private ScriptedPlanLengthSearch(PDDLProblem problem) {
            super(false);
            this.problem = problem;
        }

        @Override
        public SearchStats getStats() {
            return stats;
        }

        @Override
        public void setGBound(float gBound) {
            currentBound = gBound;
            bounds.add(gBound);
        }

        @Override
        public SimpleSearchNode search(SearchProblem p, SearchHeuristic h, PrintStream out) {
            PDDLState goalState = ((PDDLState) problem.getInit()).clone();
            goalState.setPropFluent(BoolPredicate.BoolFluent("done"), true);
            if (currentBound >= 3f) {
                SimpleSearchNode root = new SimpleSearchNode(problem.getInit(), null, null, 0, false);
                SimpleSearchNode step1 = new SimpleSearchNode(goalState.clone(), "a", root, 1, false);
                SimpleSearchNode step2 = new SimpleSearchNode(goalState.clone(), "b", step1, 2, false);
                return new SimpleSearchNode(goalState, "c", step2, 3, false);
            }
            SimpleSearchNode root = new SimpleSearchNode(problem.getInit(), null, null, 0, false);
            return new SimpleSearchNode(goalState, "a", root, 1, false);
        }
    }
}
