package com.hstairs.enhsp.integration.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StateTraceFormatter {
    private static final Pattern VALUE_PATTERN = Pattern.compile("(\\([^\\)]*\\)|[^\\s=]+)=([^\\s]+)");
    private static final Pattern ATOM_PATTERN = Pattern.compile("(\\([^\\)]*\\))");

    private StateTraceFormatter() {
    }

    public static String buildStateDiff(String before, String after) {
        Map<String, String> b = parseStateAssignments(before);
        Map<String, String> a = parseStateAssignments(after);
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(b.keySet());
        keys.addAll(a.keySet());

        StringBuilder sb = new StringBuilder();
        int changes = 0;
        for (String k : keys) {
            String bv = b.get(k);
            String av = a.get(k);
            if (!Objects.equals(bv, av)) {
                sb.append(k).append(": ").append(bv == null ? "<unset>" : bv)
                        .append(" -> ").append(av == null ? "<unset>" : av).append('\n');
                changes++;
            }
        }
        if (changes == 0) {
            return "No state differences detected for this step.";
        }
        return sb.toString();
    }

    public static String buildVariableValuesView(String before, String after) {
        Map<String, String> b = parseStateAssignments(before);
        Map<String, String> a = parseStateAssignments(after);
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(b.keySet());
        keys.addAll(a.keySet());
        if (keys.isEmpty()) {
            return "No explicit variable values parsed from state text.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Before -> After\n");
        sb.append("----------------\n");
        for (String k : keys) {
            String bv = b.get(k);
            String av = a.get(k);
            sb.append(k)
                    .append(": ")
                    .append(bv == null ? "<unset>" : bv)
                    .append(" -> ")
                    .append(av == null ? "<unset>" : av);
            if (!Objects.equals(bv, av)) {
                sb.append("   *");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static Map<String, String> parseStateAssignments(String stateText) {
        Map<String, String> map = new HashMap<>();
        if (stateText == null) {
            return map;
        }
        Matcher valueMatcher = VALUE_PATTERN.matcher(stateText);
        while (valueMatcher.find()) {
            map.put(valueMatcher.group(1), valueMatcher.group(2));
        }
        Matcher atomMatcher = ATOM_PATTERN.matcher(stateText);
        while (atomMatcher.find()) {
            String atom = atomMatcher.group(1);
            map.putIfAbsent(atom, "true");
        }
        return map;
    }
}
