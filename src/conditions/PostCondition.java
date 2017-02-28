/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conditions;

import java.util.HashMap;
import java.util.Map;
import problem.RelState;
import problem.State;

/**
 *
 * @author enrico
 */
public interface PostCondition {
    
    /**
     * Applies this post condition to the specified state 
     * and stores the result in the specified map.  
     * 
     * @param s the current state to which the post condition is applied.  
     * @param modifications a map that associates 
     * every variable whose value is assigned by the post condition 
     * with its new value (boolean or float).  
     */
    public abstract void apply(State s, Map modifications);
    
    /**
     * Applies this post condition to the specified rel state 
     * and stores the result in the specified map.  
     * 
     * @param s the current state to which the post condition is applied.  
     * @param modifications a map that associates 
     * every variable whose value is assigned by the post condition 
     * with its new value (boolean or float).  
     */
    public abstract void apply(RelState s, Map modifications);
    
    /**
     * Returns a string representation of this condition in PDDL format.  
     * 
     * @param typeInformation <tt>true</tt> 
     * if the type of the object should be printed as well.  
     * @return a string representation in PDDL format of this condition.  
     */
    public abstract String pddlPrint(boolean typeInformation);
    
    /**
     * Prints this condition in PDDL format in the specified string builder.  
     * 
     * @param typeInformation <tt>true</tt> 
     * if the type of the object should be printed as well.  
     * @param bui the string builder where this condition is printed.  
     */
    public abstract void pddlPrint(boolean typeInformation, StringBuilder bui);
    
    public abstract HashMap<Object,Object> apply(State s);
    public abstract HashMap<Object,Object> apply(RelState s);
    public abstract Object clone();
    public abstract String pddlPrintWithExtraObject();
    public abstract Conditions achieve(Predicate p);
    public abstract Conditions delete(Predicate p);

}