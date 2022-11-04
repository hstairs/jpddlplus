/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */

import enhsp2.ENHSP;
import junit.framework.TestCase;

/**
 *
 * @author enrico
 */
public class planningTest extends TestCase {
    
    public planningTest(String testName) {
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

    public void run(String[] args, int planLengthExpected) {
        ENHSP p = new ENHSP(false);
        p.parseInput(args);
        p.configurePlanner();
        p.parsingDomainAndProblem(args);
        p.planning();
        assertEquals(p.getPlanLength(), planLengthExpected);
    }

    public void testPDDLPlanning() {
        //car
        String enhspPath = "/Users/enrico/Dropbox/plan_exec_2_0/jpddlplus/";
        String input = "-o " + enhspPath + "examples/pddl+/car_non_linear/domain.pddl -f "
                + "" + enhspPath + "examples/pddl+/car_non_linear/instances/instance_1_30.0_0.1_10.0.pddl "
                + "-planner sat-aibr";
        System.out.println(enhspPath);
        String[] args = input.split(" ");
        run(args,20);
        
        //car
         input = "-o " + enhspPath + "examples/pddl+/car_non_linear/domain.pddl -f "
                + "" + enhspPath + "examples/pddl+/car_non_linear/instances/instance_1_30.0_0.1_10.0.pddl "
                + "-planner sat-hadd";
        args = input.split(" ");
        run(args,193);

        //counter
        input = "-o " + enhspPath + "examples/pddl2_1/counters/domain.pddl -f "
                +enhspPath+ "examples/pddl2_1/counters/instance_4.pddl -planner sat-aibr";
        args = input.split(" ");
        run(args,6);
        
         //counter
        input = "-o " + enhspPath + "examples/pddl2_1/counters/domain.pddl -f "
                +enhspPath+ "examples/pddl2_1/counters/instance_4.pddl -h hmrp -s gbfs -red true";
        args = input.split(" ");
        run(args,6);
        
        //counter
        input = "-o " + enhspPath + "examples/pddl2_1/counters/domain.pddl -f "
                +enhspPath+ "examples/pddl2_1/counters/instance_4.pddl -planner sat-hadd";
        args = input.split(" ");
        run(args,6);
        
                //counter
        input = "-o " + enhspPath + "examples/pddl2_1/counters/domain.pddl -f "
                +enhspPath+ "examples/pddl2_1/counters/instance_4.pddl -planner sat-hmrp";
        args = input.split(" ");
        run(args,6);
        
                //counter
        input = "-o " + enhspPath + "examples/pddl2_1/counters/domain.pddl -f "
                +enhspPath+ "examples/pddl2_1/counters/instance_4.pddl -planner opt-hrmax";
        args = input.split(" ");
        run(args,6);
        
         input = "-o " + enhspPath + "examples/pddl2_1/sandrino_domain/d.pddl -f "
                + "" + enhspPath + "examples/pddl2_1/sandrino_domain/p.pddl "
                + "-h aibr";
        args = input.split(" ");
        run(args,2);
        
//        input = "-o " + enhspPath + "/home/enrico/Dropbox/plan_exec_2_0/jpddlplus/unit_test_instances/ground_instance/d.pddl -f "
//                + "" + enhspPath + "/home/enrico/Dropbox/plan_exec_2_0/jpddlplus/unit_test_instances/ground_instance/p.pddl "
//                + "-h hmrp";
//        args = input.split(" ");
//        run(args,137);
    }
}
