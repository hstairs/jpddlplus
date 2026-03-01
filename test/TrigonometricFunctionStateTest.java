import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.TrigonometricFunction;
import junit.framework.TestCase;

import java.util.BitSet;
import java.util.HashMap;

public class TrigonometricFunctionStateTest extends TestCase {

    private static TrigonometricFunction trig(String operator, com.hstairs.ppmajal.expressions.Expression arg) {
        TrigonometricFunction function = new TrigonometricFunction();
        function.setOperator(operator);
        function.setArg(arg);
        return function;
    }

    public void testTrigonometricFunctionsAreEvaluatedCorrectlyOnState() {
        NumFluent angle30 = NumFluent.numericFluent("angle30_test_trig");
        NumFluent angle45 = NumFluent.numericFluent("angle45_test_trig");
        NumFluent half = NumFluent.numericFluent("half_test_trig");
        NumFluent one = NumFluent.numericFluent("one_test_trig");

        HashMap<Integer, Double> values = new HashMap<>();
        values.put(angle30.getId(), Math.PI / 6.0);
        values.put(angle45.getId(), Math.PI / 4.0);
        values.put(half.getId(), 0.5);
        values.put(one.getId(), 1.0);

        PDDLState state = new PDDLState(values, new BitSet(), null);

        assertEquals(0.5, trig("sin", angle30).eval(state), 1e-9);
        assertEquals(Math.sqrt(3.0) / 2.0, trig("cos", angle30).eval(state), 1e-9);
        assertEquals(1.0, trig("tan", angle45).eval(state), 1e-9);

        assertEquals(Math.PI / 6.0, trig("asin", half).eval(state), 1e-9);
        assertEquals(Math.PI / 3.0, trig("acos", half).eval(state), 1e-9);
        assertEquals(Math.PI / 4.0, trig("atan", one).eval(state), 1e-9);

        double asinPlusAcos = trig("asin", half).eval(state) + trig("acos", half).eval(state);
        assertEquals(Math.PI / 2.0, asinPlusAcos, 1e-9);
    }
}
