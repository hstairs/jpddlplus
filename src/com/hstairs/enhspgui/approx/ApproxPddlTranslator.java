package com.hstairs.enhspgui.approx;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ApproxPddlTranslator {

    private ApproxPddlTranslator() {
    }

    private enum Section { NONE, PRECONDITION, EFFECT, INIT, GOAL }

    public static String transpile(String text, boolean domainFile) {
        String[] lines = text.split("\\R", -1);
        StringBuilder out = new StringBuilder(text.length() + 128);
        Section section = Section.NONE;
        for (String line : lines) {
            String trimmed = line.trim().toLowerCase();
            if (startsSection(trimmed, "precondition")) {
                section = Section.PRECONDITION;
            } else if (startsSection(trimmed, "effect")) {
                section = Section.EFFECT;
            } else if (!domainFile && startsSection(trimmed, "init")) {
                section = Section.INIT;
            } else if (!domainFile && startsSection(trimmed, "goal")) {
                section = Section.GOAL;
            } else if (startsSection(trimmed, "action")) {
                section = Section.NONE;
            }
            out.append(rewriteLine(line, section)).append('\n');
        }
        return out.toString();
    }

    private static boolean startsSection(String trimmedLowerLine, String sectionName) {
        String t = trimmedLowerLine;
        if (t.startsWith("(")) {
            t = t.substring(1).trim();
        }
        return t.startsWith(":" + sectionName);
    }

    private static String rewriteLine(String line, Section section) {
        int commentIdx = line.indexOf(';');
        String code = commentIdx >= 0 ? line.substring(0, commentIdx) : line;
        String comment = commentIdx >= 0 ? line.substring(commentIdx) : "";
        String rewritten = rewriteParenthesizedClauses(code, section);
        rewritten = rewriteBareInfixLine(rewritten, section);
        return rewritten + comment;
    }

    private static String rewriteBareInfixLine(String code, Section section) {
        if (section == Section.NONE) {
            return code;
        }
        int start = 0;
        while (start < code.length() && Character.isWhitespace(code.charAt(start))) {
            start++;
        }
        int end = code.length();
        while (end > start && Character.isWhitespace(code.charAt(end - 1))) {
            end--;
        }
        if (start >= end) {
            return code;
        }
        String core = code.substring(start, end);
        if (core.startsWith("(") || core.startsWith(":") || core.equals(")") || core.equals("))")) {
            return code;
        }
        ComparisonSplit cmp = findTopLevelComparison(core);
        if (cmp == null) {
            return code;
        }
        String converted = convertClause(core, section);
        if (converted == null) {
            return code;
        }
        return code.substring(0, start) + converted + code.substring(end);
    }

    private static String rewriteParenthesizedClauses(String code, Section section) {
        StringBuilder out = new StringBuilder(code.length() + 16);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> pairs = new java.util.HashMap<>();
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')' && !stack.isEmpty()) {
                pairs.put(stack.pop(), i);
            }
        }
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '(' && pairs.containsKey(i)) {
                int end = pairs.get(i);
                String inner = code.substring(i + 1, end).trim();
                if (isInfixCandidate(inner)) {
                    String converted = convertClause(inner, section);
                    if (converted != null) {
                        out.append(converted);
                        i = end;
                        continue;
                    }
                }
            }
            out.append(code.charAt(i));
        }
        return out.toString();
    }

    private static boolean isInfixCandidate(String inner) {
        String low = inner.toLowerCase().trim();
        if (isAlreadyPddlClause(low)) {
            return false;
        }
        return findTopLevelComparison(inner) != null;
    }

    private static boolean isAlreadyPddlClause(String low) {
        if (low.isEmpty()) {
            return true;
        }
        String first = firstToken(low);
        Set<String> pddlHeads = Set.of(
                "and", "or", "not", "when", "forall", "exists",
                "assign", "increase", "decrease", "scale-up", "scale-down",
                "=", "<", ">", "<=", ">=", "+", "-", "*", "/"
        );
        return first.startsWith(":") || pddlHeads.contains(first);
    }

    private static String firstToken(String s) {
        int i = 0;
        while (i < s.length() && !Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        return s.substring(0, i);
    }

    private static String convertClause(String inner, Section section) {
        ComparisonSplit cmp = findTopLevelComparison(inner);
        if (cmp == null) {
            return null;
        }
        String lhs = toPddlExpression(cmp.left);
        String rhs = toPddlExpression(cmp.right);
        if (lhs == null || rhs == null) {
            return null;
        }
        if ("=".equals(cmp.op)) {
            Boolean boolRhs = parseBooleanLiteral(cmp.right);
            if (boolRhs != null) {
                String predicate = ensurePredicateLike(lhs);
                return boolRhs ? predicate : "(not " + predicate + ")";
            }
        }
        if ("=".equals(cmp.op) && section == Section.EFFECT) {
            return toNumericEffect(ensureFunctionLike(lhs), rhs);
        }
        return "(" + cmp.op + " " + lhs + " " + rhs + ")";
    }

    private static Boolean parseBooleanLiteral(String rhsRaw) {
        if (rhsRaw == null) return null;
        String t = rhsRaw.trim();
        if (t.equalsIgnoreCase("t") || t.equalsIgnoreCase("true")) return Boolean.TRUE;
        if (t.equalsIgnoreCase("f") || t.equalsIgnoreCase("false")) return Boolean.FALSE;
        return null;
    }

    private static String toNumericEffect(String lhs, String rhs) {
        String increase = detectIncrease(lhs, rhs);
        if (increase != null) return "(increase " + lhs + " " + increase + ")";
        String decrease = detectDecrease(lhs, rhs);
        if (decrease != null) return "(decrease " + lhs + " " + decrease + ")";
        return "(assign " + lhs + " " + rhs + ")";
    }

    private static String detectIncrease(String lhs, String rhs) {
        String[] parts = splitBinaryPrefix(rhs, "+");
        if (parts == null) return null;
        if (sameExpr(parts[0], lhs) && !containsExpr(parts[1], lhs)) return parts[1];
        if (sameExpr(parts[1], lhs) && !containsExpr(parts[0], lhs)) return parts[0];
        return null;
    }

    private static String detectDecrease(String lhs, String rhs) {
        String[] parts = splitBinaryPrefix(rhs, "-");
        if (parts == null) return null;
        if (sameExpr(parts[0], lhs) && !containsExpr(parts[1], lhs)) return parts[1];
        return null;
    }

    private static String[] splitBinaryPrefix(String expr, String op) {
        String s = expr == null ? "" : expr.trim();
        if (!s.startsWith("(") || !s.endsWith(")")) return null;
        s = s.substring(1, s.length() - 1).trim();
        if (!s.startsWith(op + " ")) return null;
        String body = s.substring((op + " ").length()).trim();
        List<String> args = splitTopLevelTerms(body);
        if (args.size() != 2) return null;
        return new String[]{args.get(0), args.get(1)};
    }

    private static List<String> splitTopLevelTerms(String body) {
        List<String> parts = new ArrayList<>();
        int depth = 0;
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < body.length(); i++) {
            char c = body.charAt(i);
            if (c == '(') depth++;
            if (c == ')') depth = Math.max(0, depth - 1);
            if (Character.isWhitespace(c) && depth == 0) {
                if (cur.length() > 0) {
                    parts.add(cur.toString().trim());
                    cur.setLength(0);
                }
            } else {
                cur.append(c);
            }
        }
        if (cur.length() > 0) parts.add(cur.toString().trim());
        return parts;
    }

    private static boolean sameExpr(String a, String b) {
        return normalizeExpr(a).equals(normalizeExpr(b));
    }

    private static boolean containsExpr(String expr, String target) {
        return normalizeExpr(expr).contains(normalizeExpr(target));
    }

    private static String normalizeExpr(String expr) {
        return (expr == null ? "" : expr).replaceAll("\\s+", "").trim();
    }

    private static String ensureFunctionLike(String lhs) {
        if (lhs.startsWith("(")) return lhs;
        return "(" + lhs + ")";
    }

    private static String ensurePredicateLike(String lhs) {
        return ensureFunctionLike(lhs);
    }

    private static final class ComparisonSplit {
        final String left;
        final String op;
        final String right;
        ComparisonSplit(String left, String op, String right) {
            this.left = left.trim();
            this.op = op;
            this.right = right.trim();
        }
    }

    private static ComparisonSplit findTopLevelComparison(String s) {
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') depth++;
            else if (c == ')') depth = Math.max(0, depth - 1);
            if (depth != 0) continue;
            if (i + 1 < s.length()) {
                String two = s.substring(i, i + 2);
                if ("<=".equals(two) || ">=".equals(two)) {
                    return new ComparisonSplit(s.substring(0, i), two, s.substring(i + 2));
                }
            }
            if (c == '=' || c == '<' || c == '>') {
                return new ComparisonSplit(s.substring(0, i), String.valueOf(c), s.substring(i + 1));
            }
        }
        return null;
    }

    private static String toPddlExpression(String expr) {
        String raw = expr == null ? "" : expr.trim();
        if (raw.isEmpty()) return null;
        if (raw.matches("^\\(\\s*\\?[A-Za-z0-9_\\-]+\\s*\\)$")) {
            return raw.replaceAll("[()\\s]", "");
        }
        if (raw.startsWith("(") && raw.endsWith(")")
                && isAlreadyPddlClause(raw.substring(1, raw.length() - 1).trim().toLowerCase())) {
            return raw;
        }
        try {
            return new InfixExpressionParser(raw).parse();
        } catch (RuntimeException ex) {
            return fallbackNaturalExpression(raw);
        }
    }

    private static String fallbackNaturalExpression(String expr) {
        String e = expr.trim();
        if (e.matches("[+-]?\\d+(\\.\\d+)?")) return e;
        if (e.matches("\\?[A-Za-z0-9_\\-]+")) return e;
        if (e.matches("[A-Za-z_][A-Za-z0-9_\\-]*")) return e;
        java.util.regex.Matcher fn = java.util.regex.Pattern
                .compile("^([A-Za-z_\\?][A-Za-z0-9_\\-]*)\\s*\\((.*)\\)$")
                .matcher(e);
        if (fn.matches()) {
            String name = fn.group(1);
            String argBody = fn.group(2).trim();
            if (argBody.isEmpty()) return "(" + name + ")";
            List<String> args = splitArgs(argBody);
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(name);
            for (String a : args) {
                String parsed = toPddlExpression(a);
                if (parsed == null) parsed = a.trim();
                sb.append(" ").append(parsed);
            }
            sb.append(")");
            return sb.toString();
        }
        return null;
    }

    private static List<String> splitArgs(String argBody) {
        List<String> args = new ArrayList<>();
        int depth = 0;
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < argBody.length(); i++) {
            char c = argBody.charAt(i);
            if (c == '(') depth++;
            if (c == ')') depth = Math.max(0, depth - 1);
            boolean sep = (c == ',' && depth == 0) || (Character.isWhitespace(c) && depth == 0);
            if (sep) {
                if (cur.length() > 0) {
                    args.add(cur.toString().trim());
                    cur.setLength(0);
                }
            } else {
                cur.append(c);
            }
        }
        if (cur.length() > 0) args.add(cur.toString().trim());
        return args;
    }

    private static final class InfixExpressionParser {
        private final String s;
        private int pos = 0;
        InfixExpressionParser(String s) { this.s = s == null ? "" : s.trim(); }

        String parse() {
            String r = parseExpr();
            skipWs();
            if (pos != s.length()) throw new RuntimeException("Unexpected token");
            return r;
        }

        private String parseExpr() {
            String left = parseTerm();
            while (true) {
                skipWs();
                if (match('+')) left = "(+ " + left + " " + parseTerm() + ")";
                else if (match('-')) left = "(- " + left + " " + parseTerm() + ")";
                else return left;
            }
        }

        private String parseTerm() {
            String left = parseFactor();
            while (true) {
                skipWs();
                if (match('*')) left = "(* " + left + " " + parseFactor() + ")";
                else if (match('/')) left = "(/ " + left + " " + parseFactor() + ")";
                else return left;
            }
        }

        private String parseFactor() {
            skipWs();
            if (match('-')) return "(- 0 " + parseFactor() + ")";
            if (match('(')) {
                String x = parseExpr();
                expect(')');
                return x;
            }
            String id = parseIdentifierOrNumber();
            skipWs();
            if (id == null) throw new RuntimeException("Missing factor");
            if (match('(')) {
                List<String> args = new ArrayList<>();
                skipWs();
                if (!peek(')')) {
                    while (true) {
                        args.add(parseExpr());
                        skipWs();
                        if (match(',')) continue;
                        break;
                    }
                }
                expect(')');
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(id);
                for (String a : args) sb.append(" ").append(a);
                sb.append(")");
                return sb.toString();
            }
            return id;
        }

        private String parseIdentifierOrNumber() {
            skipWs();
            int start = pos;
            while (pos < s.length()) {
                char c = s.charAt(pos);
                if (Character.isLetterOrDigit(c) || c == '_' || c == '-' || c == '?' || c == '.') pos++;
                else break;
            }
            if (start == pos) return null;
            return s.substring(start, pos);
        }

        private void skipWs() { while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) pos++; }
        private boolean match(char c) {
            skipWs();
            if (pos < s.length() && s.charAt(pos) == c) { pos++; return true; }
            return false;
        }
        private boolean peek(char c) {
            skipWs();
            return pos < s.length() && s.charAt(pos) == c;
        }
        private void expect(char c) {
            if (!match(c)) throw new RuntimeException("Expected " + c);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1 || args.length > 3) {
            System.err.println("Usage:");
            System.err.println("  java com.hstairs.enhspgui.approx.ApproxPddlTranslator <input.pddl> [output.pddl] [domain|problem]");
            System.exit(2);
        }
        Path input = Path.of(args[0]);
        Path output = args.length >= 2 ? Path.of(args[1]) : null;
        boolean domainFile = true;
        if (args.length == 3) {
            domainFile = !"problem".equalsIgnoreCase(args[2]);
        }
        String in = Files.readString(input, StandardCharsets.UTF_8);
        String out = transpile(in, domainFile);
        if (output != null) {
            Files.writeString(output, out, StandardCharsets.UTF_8);
        } else {
            System.out.print(out);
        }
    }
}
