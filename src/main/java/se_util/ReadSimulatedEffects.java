/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se_util;

import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.expressions.NumFluent;
import java.util.List;
import java.util.Set;

/**
 *
 * @author valerik
 */
public class ReadSimulatedEffects {
    
    static private String domain;
    static private List<NumFluent> numFluents;
 
    
    public static String readEffectName(String name){
        String tmp = domain.split(name)[1];
        tmp = tmp.split("end_action")[0];
        tmp = tmp.split("@")[1];
        tmp = tmp.split("\\|")[0];
        tmp = tmp.replaceAll(" ", "");
        return tmp;
    }
    
    public static boolean hasSimulatedEffects(String name){
        String tmp = domain.split(name)[1];
        tmp = tmp.split("end_action")[0];
        return tmp.contains("@");
    }
    
    public static int[] getVariables(String name){
        String tmp = domain.split(name)[1];
        tmp = tmp.split("end_action")[0];
        tmp = tmp.split("@")[1];
        tmp = tmp.split("\\|")[1];
        String[] var = tmp.split(",");
        int[] variables = new int[var.length];
        
        for(int i = 0; i<variables.length; i++){
            variables[i] = Integer.parseInt(var[i].split("\\.")[1].trim());
        }
        return variables;
    }
    
    public static int[] getToUpdate(String name){
        
        String tmp = domain.split(name)[1];
        tmp = tmp.split("end_action")[0];
        tmp = tmp.split("@")[1];
        tmp = tmp.split("\\|")[2];
        String[] var = tmp.split(",");
        int[] variables = new int[var.length];
        
        for(int i = 0; i<variables.length; i++){
            variables[i] = Integer.parseInt(var[i].split("\\.")[1].trim());
        }
        return variables;
    }
    
    public static String[] getVariableNames(String name){
         String tmp = domain.split(name)[1];
        tmp = tmp.split("end_action")[0];
        tmp = tmp.split("@")[1];
        tmp = tmp.split("\\|")[1];
        String[] var = tmp.split(",");
        String[] variables = new String[var.length];
        
        for(int i = 0; i<variables.length; i++){
            variables[i] =  var[i].split("\\.")[0].trim();
        }
        return variables;
    }
    
    public static String[] getToUpdateNames(String name){
         String tmp = domain.split(name)[1];
        tmp = tmp.split("end_action")[0];
        tmp = tmp.split("@")[1];
        tmp = tmp.split("\\|")[2];
        String[] var = tmp.split(",");
        String[] variables = new String[var.length];
        
        for(int i = 0; i<variables.length; i++){
            variables[i] =  var[i].split("\\.")[0].trim();
        }
        return variables;
    }
        
    public static Object getFluentFromPddlObject(String name, PDDLObject param){
        
        for(int i = 0; i< numFluents.size();i++){
            if(numFluents.get(i).getName().equals(name) && numFluents.get(i).getTerms().contains(param)){
                return numFluents.get(i);
            }
        }
        return null;
    }
    
    public static void setDomain(String domain){
        ReadSimulatedEffects.domain = domain;
    }
    public static String getDomain(){
        return ReadSimulatedEffects.domain;
    }
    public static void setNumFluents(List<NumFluent> numFluents){
        ReadSimulatedEffects.numFluents = numFluents;
    }
}
