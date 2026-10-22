import com.hstairs.ppmajal.PDDLProblem.PDDLPlanner;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLSolution;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.BlindHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.CPZeroCut;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.Sdac;
import junit.framework.TestCase;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
 * Deterministic differential test: UCS provides the exact metric cost and is
 * compared with every CPZeroCut configuration on small monotonic STRIPS tasks.
 */
public class CPZeroCutAdmissibilityFuzzTest extends TestCase {

    private static final int INSTANCES = 128;
    private static final float EPSILON = 0.00001f;

    public void testAllConfigurationsAgainstUcsOnPropositionalTasks()
            throws Exception {
        final Random random = new Random(0xC0FFEE);
        for (int instance = 0; instance < INSTANCES; instance++) {
            final String domain = domain(instance, random);
            final PDDLProblem problem = load(domain);
            final float optimum = optimum(problem);
            assertTrue("generated task must be solvable", Float.isFinite(optimum));

            for (final H1.CrdMode crdMode : H1.CrdMode.values()) {
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.BASE, false, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.BASE, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.FLOOR, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.NUM, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.NUM_FRACTIONAL, true, crdMode);
            }
        }
    }

    public void testAllConfigurationsAgainstUcsOnNumericTasks() throws Exception {
        final Random random = new Random(0xBAD5EED);
        for (int instance = 0; instance < INSTANCES; instance++) {
            final String domain = numericDomain(instance, random);
            final PDDLProblem problem = load(domain, """
                    (define (problem p)
                      (:domain d)
                      (:init (p0) (= (x) 0))
                      (:goal (and (p1) (>= (x) 7)))
                      (:metric minimize (cost))
                    )
                    """);
            final float optimum = optimum(problem);
            assertTrue("generated numeric task must be solvable", Float.isFinite(optimum));

            for (final H1.CrdMode crdMode : H1.CrdMode.values()) {
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.BASE, false, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.BASE, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.FLOOR, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.NUM, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.NUM_FRACTIONAL, true, crdMode);
            }
        }
    }

    public void testAllConfigurationsAgainstUcsOnTwoNumericGoals() throws Exception {
        final Random random = new Random(0x1DEA5);
        for (int instance = 0; instance < INSTANCES; instance++) {
            final String domain = twoNumericDomain(instance, random);
            final PDDLProblem problem = load(domain, """
                    (define (problem p)
                      (:domain d)
                      (:init (p0) (= (x) 0) (= (y) 0))
                      (:goal (and (p1) (>= (x) 7) (>= (y) 7)))
                      (:metric minimize (cost))
                    )
                    """);
            final float optimum = optimum(problem);
            assertTrue("generated two-numeric task must be solvable", Float.isFinite(optimum));

            for (final H1.CrdMode crdMode : H1.CrdMode.values()) {
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.BASE, false, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.BASE, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.FLOOR, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.NUM, true, crdMode);
                check(problem, optimum, domain,
                        CPZeroCut.ZeroingMode.NUM_FRACTIONAL, true, crdMode);
            }
        }
    }

    public void testBootstrapContributionRemainsAdmissible() throws Exception {
        final String domain = """
                (define (domain d)
                  (:predicates (ready))
                  (:functions (x) (cost))
                  (:action bootstrap
                    :parameters ()
                    :precondition (and)
                    :effect (and (ready) (increase (x) 7) (increase (cost) 10)))
                  (:action cheap-increase
                    :parameters ()
                    :precondition (ready)
                    :effect (and (increase (x) 7) (increase (cost) 1)))
                )
                """;
        final PDDLProblem problem = load(domain, """
                (define (problem p)
                  (:domain d)
                  (:init (= (x) 0))
                  (:goal (>= (x) 14))
                  (:metric minimize (cost))
                )
                """);
        final float optimum = optimum(problem);
        assertEquals(11f, optimum, EPSILON);

        for (final H1.CrdMode crdMode : H1.CrdMode.values()) {
            for (final CPZeroCut.ZeroingMode mode : CPZeroCut.ZeroingMode.values()) {
                final boolean floor = mode != CPZeroCut.ZeroingMode.BASE;
                final SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                        "cpzerocut", problem, "brute", false, false, false, -1,
                        false, floor, crdMode, mode
                );
                final float estimate = heuristic.computeEstimate(problem.getInit());
                assertTrue("mode=" + mode + ", crd=" + crdMode + ", h=" + estimate,
                        estimate <= optimum + EPSILON);
            }
        }
    }

    public void testNumFractionalCanDifferFromNum() throws Exception {
        final String domain = """
                (define (domain d)
                  (:predicates (p0) (p1))
                  (:functions (x) (cost))
                  (:action make-p1
                    :parameters ()
                    :precondition (p0)
                    :effect (and (p1) (increase (cost) 1)))
                  (:action increase-x
                    :parameters ()
                    :precondition (p0)
                    :effect (and (increase (x) 4) (increase (cost) 1)))
                  (:action numeric-0
                    :parameters ()
                    :precondition (p1)
                    :effect (and (increase (x) 6) (increase (cost) 2)))
                  (:action numeric-1
                    :parameters ()
                    :precondition (p0)
                    :effect (and (increase (x) 6) (increase (cost) 4)))
                  (:action numeric-2
                    :parameters ()
                    :precondition (p0)
                    :effect (and (p1) (increase (x) 6) (increase (cost) 5)))
                  (:action numeric-3
                    :parameters ()
                    :precondition (p0)
                    :effect (and (increase (x) 1) (increase (cost) 5)))
                  (:action numeric-4
                    :parameters ()
                    :precondition (p0)
                    :effect (and (increase (x) 4) (increase (cost) 3))))
                """;
        final PDDLProblem problem = load(domain, """
                (define (problem p)
                  (:domain d)
                  (:init (p0) (= (x) 0))
                  (:goal (and (p1) (>= (x) 7)))
                  (:metric minimize (cost)))
                """);
        final float num = estimate(problem, CPZeroCut.ZeroingMode.NUM);
        final float fractional = estimate(problem, CPZeroCut.ZeroingMode.NUM_FRACTIONAL);
        assertEquals(1.75f, num, EPSILON);
        assertEquals(2.75f, fractional, EPSILON);
        assertTrue(fractional > num);
    }

    private float estimate(PDDLProblem problem, CPZeroCut.ZeroingMode mode) {
        final SearchHeuristic heuristic = PDDLHeuristic.getHeuristic(
                "cpzerocut", problem, "brute", false, false, false, -1,
                false, true, H1.CrdMode.NONE, mode
        );
        return heuristic.computeEstimate(problem.getInit());
    }

    private void check(
            PDDLProblem problem,
            float optimum,
            String domain,
            CPZeroCut.ZeroingMode mode,
            boolean floor,
            H1.CrdMode crdMode
    ) {
        final SearchHeuristic heuristic = new CrdMinimumCheckingHeuristic(
                problem, crdMode, floor, mode);
        final float estimate = heuristic.computeEstimate(problem.getInit());
        assertTrue(
                "inadmissible CPZeroCut: mode=" + mode
                        + ", floor=" + floor
                        + ", crd=" + crdMode
                        + ", h=" + estimate
                        + ", optimal=" + optimum
                        + "\n" + domain,
                estimate <= optimum + EPSILON
        );
    }

    private PDDLProblem load(String domainText) throws Exception {
        return load(domainText, """
                (define (problem p)
                  (:domain d)
                  (:init (p0))
                  (:goal (and (p2) (p3)))
                  (:metric minimize (cost))
                )
                """);
    }

    private PDDLProblem load(String domainText, String problemText) throws Exception {
        final Path directory = Files.createTempDirectory("cpzerocut-admissibility-");
        final Path domainFile = directory.resolve("domain.pddl");
        final Path problemFile = directory.resolve("problem.pddl");
        Files.writeString(domainFile, domainText);
        Files.writeString(problemFile, problemText);
        final PDDLDomain domain = new PDDLDomain(domainFile.toString());
        final PDDLProblem problem = new PDDLProblem(
                problemFile.toString(), domain.constants, domain.getTypes(), domain,
                new PrintStream(OutputStream.nullOutputStream()), "internal",
                Sdac.disabled, false, BigDecimal.ONE, BigDecimal.ONE
        );
        domain.substituteEqualityConditions();
        assertTrue(problem.prepareForSearch(true));
        return problem;
    }

    private float optimum(PDDLProblem problem) {
        final PDDLPlanner planner = new PDDLPlanner(
                "wastar", "brute", false, false, 1f,
                BigDecimal.ONE, BigDecimal.ONE, "larger_g", false,
                Float.POSITIVE_INFINITY, false, false, null
        );
        final PDDLSolution solution = planner.plan(
                problem,
                new BlindHeuristic(problem),
                new PrintStream(OutputStream.nullOutputStream())
        );
        return solution.gValueAtTheEnd();
    }

    private String domain(int instance, Random random) {
        final StringBuilder pddl = new StringBuilder("""
                (define (domain d)
                  (:predicates (p0) (p1) (p2) (p3))
                  (:functions (cost))
                """);
        appendAction(pddl, "make-p1", "(p0)", "(p1)", 1 + random.nextInt(4));
        appendAction(pddl, "make-p2", "(p1)", "(p2)", 1 + random.nextInt(4));
        appendAction(pddl, "make-p3", "(p2)", "(p3)", 1 + random.nextInt(4));
        for (int action = 0; action < 5; action++) {
            final int firstPrecondition = random.nextInt(4);
            final int secondPrecondition = random.nextInt(4);
            final int firstEffect = random.nextInt(4);
            final int secondEffect = random.nextInt(4);
            final String precondition = random.nextBoolean()
                    ? "(p" + firstPrecondition + ")"
                    : "(and (p" + firstPrecondition + ") (p" + secondPrecondition + "))";
            final String effect = random.nextBoolean()
                    ? "(p" + firstEffect + ")"
                    : "(p" + firstEffect + ") (p" + secondEffect + ")";
            appendAction(pddl, "random-" + instance + "-" + action, precondition,
                    effect, 1 + random.nextInt(6));
        }
        return pddl.append(")\n").toString();
    }

    private String numericDomain(int instance, Random random) {
        final StringBuilder pddl = new StringBuilder("""
                (define (domain d)
                  (:predicates (p0) (p1))
                  (:functions (x) (cost))
                """);
        appendAction(pddl, "make-p1", "(p0)", "(p1)", 1 + random.nextInt(5));
        appendNumericAction(pddl, "increase-x", "(p0)", null,
                1 + random.nextInt(4), 1 + random.nextInt(4));
        for (int action = 0; action < 5; action++) {
            final boolean requiresP1 = random.nextBoolean();
            final boolean establishesP1 = random.nextBoolean();
            appendNumericAction(
                    pddl,
                    "numeric-" + instance + "-" + action,
                    requiresP1 ? "(p1)" : "(p0)",
                    establishesP1 ? "(p1)" : null,
                    1 + random.nextInt(6),
                    1 + random.nextInt(6)
            );
        }
        return pddl.append(")\n").toString();
    }

    private String twoNumericDomain(int instance, Random random) {
        final StringBuilder pddl = new StringBuilder("""
                (define (domain d)
                  (:predicates (p0) (p1))
                  (:functions (x) (y) (cost))
                """);
        appendAction(pddl, "make-p1", "(p0)", "(p1)", 1 + random.nextInt(5));
        appendTwoNumericAction(pddl, "increase-both", "(p0)", null,
                1 + random.nextInt(4), 1 + random.nextInt(4),
                1 + random.nextInt(5));
        for (int action = 0; action < 6; action++) {
            final boolean requiresP1 = random.nextBoolean();
            final boolean establishesP1 = random.nextBoolean();
            final int xIncrement = random.nextInt(7);
            final int yIncrement = random.nextInt(7);
            appendTwoNumericAction(
                    pddl,
                    "numeric-two-" + instance + "-" + action,
                    requiresP1 ? "(p1)" : "(p0)",
                    establishesP1 ? "(p1)" : null,
                    xIncrement == 0 && yIncrement == 0 ? 1 : xIncrement,
                    yIncrement,
                    1 + random.nextInt(6)
            );
        }
        return pddl.append(")\n").toString();
    }

    private void appendAction(
            StringBuilder pddl,
            String name,
            String precondition,
            String effect,
            int cost
    ) {
        pddl.append("  (:action ").append(name).append("\n")
                .append("    :parameters ()\n")
                .append("    :precondition ").append(precondition).append("\n")
                .append("    :effect (and ").append(effect)
                .append(" (increase (cost) ").append(cost).append("))\n")
                .append("  )\n");
    }

    private void appendNumericAction(
            StringBuilder pddl,
            String name,
            String precondition,
            String propositionEffect,
            int increment,
            int cost
    ) {
        pddl.append("  (:action ").append(name).append("\n")
                .append("    :parameters ()\n")
                .append("    :precondition ").append(precondition).append("\n")
                .append("    :effect (and ");
        if (propositionEffect != null) {
            pddl.append(propositionEffect).append(" ");
        }
        pddl.append("(increase (x) ").append(increment).append(") ")
                .append("(increase (cost) ").append(cost).append("))\n")
                .append("  )\n");
    }

    private void appendTwoNumericAction(
            StringBuilder pddl,
            String name,
            String precondition,
            String propositionEffect,
            int xIncrement,
            int yIncrement,
            int cost
    ) {
        pddl.append("  (:action ").append(name).append("\n")
                .append("    :parameters ()\n")
                .append("    :precondition ").append(precondition).append("\n")
                .append("    :effect (and ");
        if (propositionEffect != null) {
            pddl.append(propositionEffect).append(" ");
        }
        if (xIncrement > 0) {
            pddl.append("(increase (x) ").append(xIncrement).append(") ");
        }
        if (yIncrement > 0) {
            pddl.append("(increase (y) ").append(yIncrement).append(") ");
        }
        pddl.append("(increase (cost) ").append(cost).append("))\n")
                .append("  )\n");
    }
}
