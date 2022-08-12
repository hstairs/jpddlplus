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

	public Object applyEffect(Object[] values) {
		Object out = null;
		if(function.equals("f1")) {
			out = applyNum(values);
		}else if(function.equals("increment_se")){
                    out = increase_se(values);
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
