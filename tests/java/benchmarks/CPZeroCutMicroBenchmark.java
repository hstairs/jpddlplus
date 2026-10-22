import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.pddl.heuristics.advanced.CPZeroCut;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.Sdac;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;

/** Command-line benchmark for a single initial-state CPZeroCut evaluation. */
public final class CPZeroCutMicroBenchmark {
    private static final int WARMUPS = 2;
    private static final int RUNS = 5;

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("usage: domain.pddl problem.pddl");
        }
        final PDDLProblem problem = load(args[0], args[1]);
        final State initial = problem.getInit();
        for (final H1.CrdMode crd : H1.CrdMode.values()) {
            benchmark(problem, initial, crd, CPZeroCut.ZeroingMode.BASE, false);
            benchmark(problem, initial, crd, CPZeroCut.ZeroingMode.FLOOR, true);
        }
    }

    private static void benchmark(
            PDDLProblem problem, State initial, H1.CrdMode crd,
            CPZeroCut.ZeroingMode zeroing, boolean floor
    ) {
        for (int i = 0; i < WARMUPS; i++) {
            estimate(problem, initial, crd, zeroing, floor);
        }
        final long[] nanos = new long[RUNS];
        float value = Float.NaN;
        for (int i = 0; i < RUNS; i++) {
            final long start = System.nanoTime();
            final float current = estimate(problem, initial, crd, zeroing, floor);
            nanos[i] = System.nanoTime() - start;
            if (i > 0 && Float.compare(value, current) != 0) {
                throw new AssertionError("non-deterministic h: " + value + " vs " + current);
            }
            value = current;
        }
        Arrays.sort(nanos);
        System.out.printf("%s,%s,h=%.4f,median_ms=%.3f,min_ms=%.3f%n",
                crd, zeroing, value, nanos[RUNS / 2] / 1_000_000d,
                nanos[0] / 1_000_000d);
    }

    private static float estimate(
            PDDLProblem problem, State initial, H1.CrdMode crd,
            CPZeroCut.ZeroingMode zeroing, boolean floor
    ) {
        return new CPZeroCut(problem, "brute", false, -1, crd, floor, zeroing)
                .computeEstimate(initial);
    }

    private static PDDLProblem load(String domainFile, String problemFile) throws Exception {
        final PDDLDomain domain = new PDDLDomain(domainFile);
        final PDDLProblem problem = new PDDLProblem(
                problemFile, domain.constants, domain.getTypes(), domain,
                new PrintStream(OutputStream.nullOutputStream()), "internal",
                Sdac.disabled, false, BigDecimal.ONE, BigDecimal.ONE
        );
        domain.substituteEqualityConditions();
        if (!problem.prepareForSearch(true)) {
            throw new IllegalStateException("cannot prepare problem");
        }
        return problem;
    }
}
