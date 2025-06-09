package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.expressions.HomeMadeRealInterval;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Terminal;
import com.hstairs.ppmajal.expressions.NumEffect;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.IntSet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Collection;

public class AibrLogger {
    private final PrintWriter writer;
    private final LoggingManager loggingManager;

    public AibrLogger(String logFilePath) {
        try {
            this.writer = new PrintWriter(new FileWriter(logFilePath));
            this.loggingManager = new LoggingManager();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create log file: " + e.getMessage());
        }
    }

    public void logSupporters(int numberOfSupporters, Map<Integer, String> names,
                            Condition[] preconditions, Collection<Terminal>[] propEffects,
                            NumEffect[] numEffects, int[] originalActions) {
        loggingManager.logSupporters(numberOfSupporters, names, preconditions, propEffects, numEffects, originalActions);
    }

    public void logUnsupportedSupporters(IntSet unsupportedSupporters, Map<Integer, String> names) {
        loggingManager.logUnsupportedSupporters(unsupportedSupporters, names);
    }

    public void logStep(int stepNumber, String actionName, Int2ObjectArrayMap intervals,
                       int supporterId, String supporterName, Condition precondition,
                       Collection<Terminal> propEffects, NumEffect numEffect) {
        loggingManager.addStep(stepNumber, actionName, intervals, supporterId, supporterName, 
                             precondition, propEffects, numEffect);
    }

    public void saveLog() {
        writer.write(loggingManager.getJsonString());
        writer.close();
    }
} 