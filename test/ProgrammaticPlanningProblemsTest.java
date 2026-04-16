import com.hstairs.ppmajal.PDDLProblem.PDDLPlanner;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLSolution;
import com.hstairs.ppmajal.conditions.BoolPredicate;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.Sdac;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionSchema;
import junit.framework.TestCase;

import java.util.ArrayList;

import static com.hstairs.ppmajal.conditions.BoolPredicate.BoolFluent;
import static com.hstairs.ppmajal.conditions.Comparison.comparison;
import static com.hstairs.ppmajal.expressions.NumEffect.easyNumEffect;
import static com.hstairs.ppmajal.expressions.NumFluent.numericFluent;
import static com.hstairs.ppmajal.transition.ConditionalEffects.numEffects;
import static com.hstairs.ppmajal.transition.ConditionalEffects.stripsEffects;

public class ProgrammaticPlanningProblemsTest extends TestCase {

    private PDDLSolution runPlanningPipeline(PDDLProblem problem) throws Exception {
        assertFalse(problem.isReadyForSearch());
        assertTrue(problem.prepareForSearch(true));
        assertTrue(problem.isReadyForSearch());
        assertTrue(problem.getTransitions().size() > 0);

        H1 heuristic = new H1(problem);
        PDDLPlanner planner = new PDDLPlanner();
        PDDLSolution solution = planner.plan(problem, heuristic, System.out);

        assertNotNull(solution);
        assertNotNull(solution.rawPlan());
        assertNotNull(solution.lastNode());
        return solution;
    }

    public void testProgrammaticClassicalProblemCanBePlanned() throws Exception {
        PDDLDomain domain = new PDDLDomain();
        domain.addBoolPredicate("at_start_prog_classical");
        domain.addBoolPredicate("goal_prog_classical");

        TransitionSchema moveToGoal = new TransitionSchema(
                "move_to_goal_prog_classical",
                Transition.Semantics.ACTION,
                null,
                BoolFluent("at_start_prog_classical"),
                stripsEffects(BoolFluent("goal_prog_classical")),
                null
        );
        domain.addAction(moveToGoal);

        PDDLProblem problem = new PDDLProblem(domain, "internal",
                System.out, Sdac.disabled, false);
        problem.addFactValue(BoolFluent("at_start_prog_classical"), true);
        problem.setGoals(BoolFluent("goal_prog_classical"));

        PDDLSolution solution = runPlanningPipeline(problem);

        assertEquals(1, solution.rawPlan().size());
        assertTrue(problem.getGoals().isSatisfied(solution.lastState()));
    }

    public void testProgrammaticNumericProblemCanBePlanned() throws Exception {
        PDDLDomain domain = new PDDLDomain();
        domain.addFunction("counter_prog_numeric", new ArrayList());

        NumFluent counter = numericFluent("counter_prog_numeric");
        TransitionSchema increaseCounter = new TransitionSchema(
                "increase_counter_prog_numeric",
                Transition.Semantics.ACTION,
                null,
                null,
                new ConditionalEffects<>(),
                numEffects(easyNumEffect("increase", counter, 2))
        );
        domain.addAction(increaseCounter);

        PDDLProblem problem = new PDDLProblem(domain, "internal", System.out, Sdac.disabled, false);
        problem.addNumValue(counter, 0);
        problem.setGoals(comparison(Comparison.Comparator.GE, counter, new PDDLNumber(6), false));

        PDDLSolution solution = runPlanningPipeline(problem);

        assertTrue(solution.rawPlan().size() >= 3);
        assertTrue(problem.getGoals().isSatisfied(solution.lastState()));
        assertTrue(counter.eval(solution.lastState()) >= 6.0);
    }
}
