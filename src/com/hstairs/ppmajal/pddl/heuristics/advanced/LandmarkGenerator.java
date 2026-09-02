package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.transition.Transition;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * @brief This class exists such that users (e.g. from Python via JPype) can
 * access the landmarks directly, rather than just the landmark heuristic value.
 * This class should work even when CPLEX is not installed.
 * 
 * @author ryan
 */
public class LandmarkGenerator extends LM{

    /**
     * Construct a landmark generator.
     * @param problem 
     */
    public LandmarkGenerator(PDDLProblem problem) {
        super(problem);
    }
    
    /**
     * Wrapper to pass easily to Python an action landmark. Consists of the
     * actions (as strings for Python), each of their contributions, and the
     * target of the additive landmark.
     */
    public record ActionLandmark(
        ArrayList<String> actions, 
        ArrayList<Double> contributions, 
        Double target) {
        public ActionLandmark {
            if (actions.size() != contributions.size()) {
                throw new IllegalArgumentException(
                        "Action and contribution sizes do not match.");
            }
        }
    }
    
    /**
     * Return the list of action landmarks.
     * @param s state to compute action landmarks.
     * @return list of action landmarks.
     */
    public List<ActionLandmark> getActionLandmarks(State s) {
        final IntOpenHashSet landmarks = getLandmarks(s);
        ArrayList<ActionLandmark> actionLandmarks = new ArrayList<>();
        
        for (var lm : landmarks) {
            if (!getConditionInit()[lm]) {
                final Terminal t = Terminal.getTerminal(lm);
                
                double targetValue = 1f;
                if (t instanceof Comparison) {
                    targetValue = -1d * ((Comparison) t).getLeft().eval(s);    
                }
                
                ArrayList<String> achievers = new ArrayList<>();
                ArrayList<Double> contributions = new ArrayList<>();
                
                for (int a : getOrCreateAchievers(lm)) {
                    double c = 1f;
                    if (t instanceof Comparison) {
                        c = getNumericContribution(a, lm);
                    }
                    
                    Transition transition = 
                            Transition.getTransition(cp.cpTr2TrMap()[a]);
                    achievers.add(transition.toString());
                    contributions.add(c);
                }
                
                actionLandmarks.add(new ActionLandmark(
                        achievers, contributions, targetValue));
            }
        }
        
        return actionLandmarks;
    }
}
