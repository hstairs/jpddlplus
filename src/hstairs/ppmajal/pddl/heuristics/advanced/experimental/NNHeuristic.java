/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hstairs.ppmajal.pddl.heuristics.advanced.experimental;

import com.hstairs.ppmajal.pddl.heuristics.BlindHeuristic;
import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.problem.State;
//import org.deeplearning4j.nn.graph.ComputationGraph;
//import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
//import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
//import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
//import org.nd4j.common.io.ClassPathResource;

/**
 *
 * @author enrico
 */
public class NNHeuristic extends BlindHeuristic{
    public NNHeuristic(PDDLProblem problem, String modelFile){
        super(problem);
//        try {
//            String fullModel = new ClassPathResource("full_model.h5").getFile().getPath();
//            ComputationGraph model = KerasModelImport.importKerasModelAndWeights(fullModel);
//        } catch (Exception ex) {
//            Logger.getLogger(NNHeuristic.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public float computeEstimate(State gs) {

        return 0;

    }

  

    
    
}
