/*
 * Copyright (C) 2020 enrico.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.propositionalFactory;

import com.hstairs.ppmajal.domain.ParametersAsTerms;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.transition.Transition;
import com.hstairs.ppmajal.transition.TransitionGround;
import com.hstairs.ppmajal.transition.TransitionSchema;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author enrico
 */
public abstract class ExternalGrounder {
    protected final String domainFile;
    protected final String problemFile;
    protected final PDDLProblem problem;

    public ExternalGrounder(PDDLProblem problem, String domainFile, String problemFile) {
        this.domainFile = domainFile;
        this.problemFile = problemFile;
        this.problem = problem;
    }

    public abstract Collection<TransitionGround> doGrounding();

    protected Collection<TransitionGround> extractActions(HashMap<String, Collection<List<String>>> groundings) {
        final Collection<TransitionGround> actions = new ArrayList();
        ArrayList<Transition> transitionSchema = new ArrayList();
        for (final Transition t : Transition.getId2transition()) {
            if (t instanceof TransitionSchema) {
                transitionSchema.add(t);
            } else {
                actions.add((TransitionGround) t);
//                throw new RuntimeException("We expect only schema here for the moment"+t);
            }
        }
        
        
        for (final Transition t : transitionSchema){
            String name = t.getName();
            TransitionSchema schema;
            if (t instanceof TransitionSchema) {
                schema = (TransitionSchema)t;
                Collection<List<String>> get = groundings.get(name.toLowerCase());
                if (get == null){
                    if (((TransitionSchema) t).getParameters().isEmpty()){
                        get = new ArrayList();
                        get.add(new ArrayList());
                    }
                } else {
                    for (List<String> ele : get) {
                        ParametersAsTerms p = new ParametersAsTerms();
                        for (String obj : ele) {
                            p.add(problem.getObjectByName(obj));
                        }
                        final TransitionGround ground = Grounder.ground(schema, p, problem.getObjects(), problem);
                        actions.add(ground);
                    }
                }
            }
            
        }
        return(actions);
    }
}
