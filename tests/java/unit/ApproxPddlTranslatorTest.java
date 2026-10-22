import com.hstairs.enhsp.integration.approx.ApproxPddlTranslator;
import junit.framework.TestCase;

public class ApproxPddlTranslatorTest extends TestCase {

    public void testDomainNamesWithHyphenAreNotRewrittenAsArithmetic() {
        String input = "(define (domain numeric-counter))";

        String output = ApproxPddlTranslator.transpile(input, true);

        assertFalse(output.contains("(- domain"));
        assertTrue(output.contains("numeric-counter"));
    }

    public void testAssignmentIsRewrittenAsIncreaseWhenLhsIsOnLeftSideOfSum() {
        String input = """
                (:effect
                    (x() = x() + step())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(increase (x) (step))"));
    }

    public void testAssignmentIsRewrittenAsIncreaseWhenLhsIsOnRightSideOfSum() {
        String input = """
                (:effect
                    (x() = step() + x())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(increase (x) (step))"));
    }

    public void testAssignmentIsRewrittenAsDecrease() {
        String input = """
                (:effect
                    (x() = x() - step())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output, output.contains("(decrease (x) (step))"));
    }

    public void testCompactIncreaseInsideAndClauseIsPreserved() {
        String input = """
                (:effect
                    (and(increase (x) (step)))
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(and(increase (x) (step)))"));
    }

    public void testPlusEqualsIsTranslatedToIncrease() {
        String input = """
                (:effect
                    (x() += step())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(increase (x) (step))"));
    }

    public void testMinusEqualsIsTranslatedToDecrease() {
        String input = """
                (:effect
                    (x() -= step())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(decrease (x) (step))"));
    }

    public void testTimesEqualsIsTranslatedToScaleUp() {
        String input = """
                (:effect
                    (x() *= factor())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(scale-up (x) (factor))"));
    }

    public void testDivideEqualsIsTranslatedToScaleDown() {
        String input = """
                (:effect
                    (x() /= factor())
                )
                """;

        String output = ApproxPddlTranslator.transpile(input, true);

        assertTrue(output.contains("(scale-down (x) (factor))"));
    }
}
