import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.pddl.heuristics.advanced.CPZeroCut;
import com.hstairs.ppmajal.problem.State;
import java.util.BitSet;
import junit.framework.Assert;

/** Checks incremental Q against the original exhaustive minimum at every read. */
final class CrdMinimumCheckingHeuristic extends CPZeroCut {
    CrdMinimumCheckingHeuristic(PDDLProblem problem, CrdMode crdMode,
                               boolean floor, ZeroingMode mode) {
        super(problem, "brute", false, -1, crdMode, floor, mode);
    }

    @Override
    protected float computeCausalPreconditionCost(
            int conditionId, int actionId, float numericEffectCost, State state
    ) {
        float expected = getActionHCost()[actionId];
        final BitSet interfering = interferingAchievers(actionId, conditionId, state);
        for (int supporter = interfering.nextSetBit(0); supporter >= 0;
             supporter = interfering.nextSetBit(supporter + 1)) {
            expected = Math.min(expected, getActionHCost()[supporter]);
        }
        final float actual = super.computeCausalPreconditionCost(
                conditionId, actionId, numericEffectCost, state);
        Assert.assertEquals("Q for action=" + actionId + " condition=" + conditionId,
                expected, actual, 0f);
        return actual;
    }
}
