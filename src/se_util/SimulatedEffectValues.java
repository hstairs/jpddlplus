/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se_util;

/**
 *
 * @author valerik
 */
public class SimulatedEffectValues {
    
    private int[] variables;
    private int[] toUpdate;
    private String[] variableNames;
    private String[] toUpdateNames;
    
    public SimulatedEffectValues(int[] variables, int[] toUpdate, String[] variableNames, String[] toUpdateNames){
        this.variables = variables;
        this.toUpdate = toUpdate;
        this.variableNames = variableNames;
        this.toUpdateNames = toUpdateNames;
    }
    
    
    public int[] getVariables() {
        return variables;
    }

    public int[] getToUpdate() {
        return toUpdate;
    }

    public String[] getVariableNames() {
        return variableNames;
    }

    public String[] getToUpdateNames() {
        return toUpdateNames;
    }
  
}
