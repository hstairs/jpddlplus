package com.hstairs.ppmajal.propositionalFactory;

import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.util.Collection;
import java.util.LinkedHashSet;

public class ASPGrounder {
    final protected PDDLProblem problem;
    final protected PDDLDomain domain;
    Collection<TransitionGround> groundedTransitions;


    public ASPGrounder(PDDLProblem problem, PDDLDomain domain) {
        this.problem = problem;
        this.domain = domain;
    }

    public Collection<TransitionGround> ground(){
        if (groundedTransitions == null){
            groundedTransitions = new LinkedHashSet<>();
            String aspString = encode();
            String aspResult = solve(aspString);
            groundedTransitions = decode(aspResult);
        }
        return groundedTransitions;
    }

    private Collection<TransitionGround> decode(String aspResult) {
        return  null;
    }

    private String solve(String aspString) {
        return null;
    }

    private String encode() {

        return null;
    }
}
