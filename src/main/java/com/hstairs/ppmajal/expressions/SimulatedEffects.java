package com.hstairs.ppmajal.expressions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.hstairs.ppmajal.conditions.PostCondition;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.RelState;
import com.hstairs.ppmajal.problem.State;


public class SimulatedEffects  implements PostCondition{
	
	private final String function;
	private final List<Object> variables;
	private final List<Object> toUpdate;

	public SimulatedEffects(String function, List<Object> variables, List<Object> toUpdate) {
		this.function=function;
		this.variables=variables;
		this.toUpdate=toUpdate;
	}

	public Object[] applyEffect(Object[] values) {
		Object[] out = new Object[toUpdate.size()];
            switch (function) {
                case "f1":
                    out[0] = applyNum(values);
                    break;
                case "increment_se":
                    out[0] = increase_se(values);
                    break;
                case "decrement_se":
                    out[0] = decrease_se(values);
                    break;
                case "increment_decrement_se":
                    out[0] = increment_decrement_se(values);
                    break;
                case "max":
                    out[0] = max(values);
                    break;
                case "max2":
                    out = max2(values);
                    break;
                case "max3":
                    out = max3(values);
                    break; 
                case "max4":
                    out = max4(values);
                    break;
                case "max5":
                    out = max5(values);
                    break;
                default:
                    break;
            }
		return out;
	}
	
	
	//this methods calls the function that computes the new value for the left side assignment
	public Double applyNum(Object[] values) {
		Double result = 0.0;
		//do something 
		return result;
	}
        public Double increase_se(Object[] values) {
		Double result = (Double)values[0];
		result += 1.0;
                return result;
	}
        public Double decrease_se(Object[] values) {
		Double result = (Double)values[0];
		result -= 1.0;
                return result;
	}
         public Double[] increment_decrement_se(Object[] values) {
             Double[] result = new Double[2];
		result[0] = (Double)values[0];
		result[0] += 1.0;
                result[1] = (Double)values[1];
		result[1] -= 1.0;
                return result;
	}
         
         public Double max_old(Object[] values){
             Double result;
             if((Double)values[0] >= (Double)values[1]){
                 result = (Double)values[0];
             }else{
                 result = (Double)values[1];
             }
             return result;
         }
         
         public Double max(Object[] values){
             Double result = (Double)values[0];
             for(int i = 1; i < values.length; i++ ){
                 if (result < (Double)values[i]){
                     result = (Double)values[i];
                 }
             }
             return result;
         }
         
          public Double[] max2(Object[] values){
             Double[] result = {(Double)values[0],(Double)values[2]};
             if((Double)values[0] < (Double)values[1]){
                 result[0] = (Double)values[1];
             }
              if((Double)values[2] < (Double)values[3]){
                 result[1] = (Double)values[3];
             }
             return result;
         }
          
         public Double[] max3(Object[] values){
             Double[] result = {(Double)values[0],(Double)values[2],(Double)values[4]};
             if((Double)values[0] < (Double)values[1]){
                 result[0] = (Double)values[1];
             }
              if((Double)values[2] < (Double)values[3]){
                 result[1] = (Double)values[3];
             }
              if((Double)values[4] < (Double)values[5]){
                 result[2] = (Double)values[5];
             }
             return result;
         }
         
         public Double[] max4(Object[] values){
             Double[] result = {(Double)values[0],(Double)values[2],(Double)values[4],(Double)values[6]};
             if((Double)values[0] < (Double)values[1]){
                 result[0] = (Double)values[1];
             }
              if((Double)values[2] < (Double)values[3]){
                 result[1] = (Double)values[3];
             }
              if((Double)values[4] < (Double)values[5]){
                 result[2] = (Double)values[5];
             }
              if((Double)values[6] < (Double)values[7]){
                 result[3] = (Double)values[7];
             }
             return result;
         }
         
         public Double[] max5(Object[] values){
             Double[] result = {(Double)values[0],(Double)values[2],(Double)values[4],(Double)values[6],(Double)values[8]};
             if((Double)values[0] < (Double)values[1]){
                 result[0] = (Double)values[1];
             }
              if((Double)values[2] < (Double)values[3]){
                 result[1] = (Double)values[3];
             }
              if((Double)values[4] < (Double)values[5]){
                 result[2] = (Double)values[5];
             }
              if((Double)values[6] < (Double)values[7]){
                 result[3] = (Double)values[7];
             }
              if((Double)values[8] < (Double)values[9]){
                 result[4] = (Double)values[9];
             }
             return result;
         }
       
	
	public int getVariablesSize() {
		return variables.size();
	}
	public Object getVariable(int i) {
		return variables.get(i);
	}
	public List<Object> getToUpdate(){
		return toUpdate;
	}
        public List<Object> getVariables(){
		return variables;
	}

	/**
     * @return
     */
    @Override
    public SimulatedEffects clone ( ) {
    return this;
    }
    
	@Override
	public void apply(State s, Map modifications) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(RelState s, Map modifications) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void pddlPrint(boolean typeInformation, StringBuilder bui) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<Object, Object> apply(PDDLState s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Object, Object> apply(RelState s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pddlPrintWithExtraObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pddlPrint(boolean typeInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
