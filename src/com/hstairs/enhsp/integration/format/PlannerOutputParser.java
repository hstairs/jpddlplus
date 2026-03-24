package com.hstairs.enhsp.integration.format;

public final class PlannerOutputParser {
    private PlannerOutputParser() {
    }

    public static String extractInterestingLiveStat(String rawLine) {
        String line = rawLine == null ? "" : rawLine.trim();
        if (line.isEmpty()) {
            return null;
        }
        if (line.startsWith("h(I):")) {
            return line;
        }
        if (line.startsWith("f(n) =")) {
            return line;
        }
        if (line.startsWith("g(n)=") && line.contains("h(n)=")) {
            return line;
        }
        if (line.startsWith("Plan-Length:")
                || line.startsWith("Metric (Search):")
                || line.startsWith("Planning Time (msec):")
                || line.startsWith("Expanded Nodes:")
                || line.startsWith("States Evaluated:")) {
            return line;
        }
        return null;
    }
}
