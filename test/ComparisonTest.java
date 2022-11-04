/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import junit.framework.TestCase;

/**
 *
 * @author enrico
 */
public class ComparisonTest extends TestCase {
    
    public ComparisonTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testComparison() {
        Comparison g = Comparison.comparison(">", new PDDLNumber(0.0), new PDDLNumber(0.0), true);
        Comparison l = Comparison.comparison("<", new PDDLNumber(0.0), new PDDLNumber(0.0), true);

        Comparison ge = Comparison.comparison(">=", new PDDLNumber(0.0), new PDDLNumber(0.0), true);

        Comparison le = Comparison.comparison("<=", new PDDLNumber(0.0), new PDDLNumber(0.0), true);
        Comparison eq = Comparison.comparison("=", new PDDLNumber(0.0), new PDDLNumber(0.0), true);

        assertEquals(false, g.isSatisfied(0.0, 0.0));
        assertEquals(true, g.isSatisfied(0.001, 0.0));

        assertEquals(false, l.isSatisfied(0.0, 0.0));
        assertEquals(true, l.isSatisfied(0.0, 0.0001));

        assertEquals(false, ge.isSatisfied(0.0, 0.0001));
        assertEquals(true, ge.isSatisfied(0.0, 0.0));
        
        assertEquals(false, le.isSatisfied(0.0001, 0.0));
        assertEquals(true, le.isSatisfied(0.0, 0.0));

        assertEquals(false, eq.isSatisfied(0.0, 0.0001));
        assertEquals(true, eq.isSatisfied(0.0, 0.0));
        
        assertEquals(true, eq.isSatisfied(68.0,68.00000101327896));
    }
}
