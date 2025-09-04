package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.expressions.NumEffect;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.IntSet;

import java.util.Map;
import java.util.Collection;

public class LoggingManager {
    private final StringBuilder jsonBuilder;
    private boolean isFirstStep = true;
    private boolean hasLoggedSupporters = false;
    private boolean hasLoggedUnsupported = false;

    public LoggingManager() {
        this.jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
    }

    public void logSupporters(int numberOfSupporters, Map<Integer, String> names, 
                            Condition[] preconditions, Collection<Terminal>[] propEffects, 
                            NumEffect[] numEffects, int[] originalActions) {
        if (!hasLoggedSupporters) {
            jsonBuilder.append("  \"supporters\": [\n");
            for (int i = 0; i < numberOfSupporters; i++) {
                if (i > 0) {
                    jsonBuilder.append(",\n");
                }
                jsonBuilder.append("    {\n");
                jsonBuilder.append("      \"id\": ").append(i).append(",\n");
                jsonBuilder.append("      \"name\": \"").append(names.get(i)).append("\",\n");
                jsonBuilder.append("      \"precondition\": \"").append(preconditions[i] != null ? preconditions[i] : "none").append("\",\n");
                
                // Add effects
                jsonBuilder.append("      \"effects\": {\n");
                if (propEffects[i] != null && !propEffects[i].isEmpty()) {
                    jsonBuilder.append("        \"propositional\": [\n");
                    boolean isFirstEffect = true;
                    for (Terminal effect : propEffects[i]) {
                        if (!isFirstEffect) {
                            jsonBuilder.append(",\n");
                        }
                        isFirstEffect = false;
                        jsonBuilder.append("          \"").append(effect.toString()).append("\"");
                    }
                    jsonBuilder.append("\n        ]");
                }
                if (numEffects[i] != null) {
                    if (propEffects[i] != null && !propEffects[i].isEmpty()) {
                        jsonBuilder.append(",\n");
                    }
                    jsonBuilder.append("        \"numeric\": \"").append(numEffects[i].toString()).append("\"");
                }
                jsonBuilder.append("\n      },\n");
                jsonBuilder.append("      \"original_action_id\": ").append(originalActions[i]).append("\n");
                jsonBuilder.append("    }");
            }
            jsonBuilder.append("\n  ],\n");
            hasLoggedSupporters = true;
        }
    }

    public void logUnsupportedSupporters(IntSet unsupportedSupporters, Map<Integer, String> names) {
        if (!hasLoggedUnsupported) {
            jsonBuilder.append("  \"unsupported_supporters\": [\n");
            boolean isFirst = true;
            for (int supporterId : unsupportedSupporters) {
                if (!isFirst) {
                    jsonBuilder.append(",\n");
                }
                isFirst = false;
                jsonBuilder.append("    {\n");
                jsonBuilder.append("      \"id\": ").append(supporterId).append(",\n");
                jsonBuilder.append("      \"name\": \"").append(names.get(supporterId)).append("\"\n");
                jsonBuilder.append("    }");
            }
            jsonBuilder.append("\n  ],\n");
            hasLoggedUnsupported = true;
        }
    }

    public void addStep(int stepNumber, String actionName, Int2ObjectArrayMap<HomeMadeRealInterval> intervals, 
                       int supporterId, String supporterName, Condition precondition, 
                       Collection<Terminal> propEffects, NumEffect numEffect) {
        if (!isFirstStep) {
            jsonBuilder.append(",\n");
        }
        isFirstStep = false;
        
        jsonBuilder.append("    {\n");
        jsonBuilder.append("      \"step\": ").append(stepNumber).append(",\n");
        jsonBuilder.append("      \"action\": \"").append(actionName).append("\",\n");
        
        // Add supporter details
        jsonBuilder.append("      \"supporter\": {\n");
        jsonBuilder.append("        \"id\": ").append(supporterId).append(",\n");
        jsonBuilder.append("        \"name\": \"").append(supporterName).append("\",\n");
        
        // Add precondition
        jsonBuilder.append("        \"precondition\": \"").append(precondition != null ? precondition.toString() : "none").append("\",\n");
        
        // Add effects
        jsonBuilder.append("        \"effects\": {\n");
        if (propEffects != null && !propEffects.isEmpty()) {
            jsonBuilder.append("          \"propositional\": [\n");
            boolean isFirstEffect = true;
            for (Terminal effect : propEffects) {
                if (!isFirstEffect) {
                    jsonBuilder.append(",\n");
                }
                isFirstEffect = false;
                jsonBuilder.append("            \"").append(effect.toString()).append("\"");
            }
            jsonBuilder.append("\n          ]");
        }
        if (numEffect != null) {
            if (propEffects != null && !propEffects.isEmpty()) {
                jsonBuilder.append(",\n");
            }
            jsonBuilder.append("          \"numeric\": \"").append(numEffect.toString()).append("\"");
        }
        jsonBuilder.append("\n        }\n");
        jsonBuilder.append("      },\n");
        
        // Add intervals
        jsonBuilder.append("      \"intervals\": {\n");
        boolean isFirstInterval = true;
        for (Map.Entry<Integer, HomeMadeRealInterval> entry : intervals.entrySet()) {
            if (!isFirstInterval) {
                jsonBuilder.append(",\n");
            }
            isFirstInterval = false;
            
            HomeMadeRealInterval interval = entry.getValue();
            NumFluent nf = NumFluent.fromIdToNumFluents.get(entry.getKey());
            jsonBuilder.append("        \"").append(nf.toString()).append("\": {\n");
            jsonBuilder.append("          \"lower\": ").append(interval.lo()).append(",\n");
            jsonBuilder.append("          \"upper\": ").append(interval.hi()).append("\n");
            jsonBuilder.append("        }");
        }
        
        jsonBuilder.append("\n      }\n");
        jsonBuilder.append("    }");
    }

    public String getJsonString() {
        return jsonBuilder.toString() + "\n  ]\n}";
    }
} 