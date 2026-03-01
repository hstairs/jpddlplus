package com.hstairs.enhspgui.tree;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Comparator;

public final class LazySearchTreeWindow {
    private final JFrame frame;
    private final GraphCanvas canvas;
    private final JTextArea info;
    private final Timer renderTimer;
    private final Queue<LogEvent> pendingLogEvents = new ArrayDeque<>();
    private volatile int epoch = 0;
    private boolean logDrainScheduled = false;
    private boolean refreshScheduled = false;
    private boolean infoDirty = false;
    private boolean visibleDirty = true;

    private final Map<String, GraphNode> nodes = new LinkedHashMap<>();
    private final Map<String, List<GraphEdge>> outgoingEdges = new HashMap<>();
    private final Map<Integer, Integer> depthCounts = new HashMap<>();
    private Set<String> visibleCache = Set.of();
    private int activeNodeLimit = 120;
    private String rootKey = null;
    private String selectedKey = null;
    private int nextStateIndex = 0;
    private Set<String> highlightedPathKeys = Set.of();
    private boolean showAllNodes = false;
    private int generated = 0;
    private int expanded = 0;
    private int closed = 0;

    public LazySearchTreeWindow() {
        frame = new JFrame("Search Tree");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(1080, 760);
        frame.setLocationByPlatform(true);

        canvas = new GraphCanvas();
        info = new JTextArea();
        info.setEditable(false);
        info.setRows(4);
        info.setText("Generated: 0\nExpanded: 0\nExpanded/Closed: 0\nNodes: 0  Visible: 0  Limit: " + activeNodeLimit);

        renderTimer = new Timer(33, e -> {
            refreshScheduled = false;
            if (infoDirty) {
                updateInfoNow();
                infoDirty = false;
            }
            canvas.repaint();
        });
        renderTimer.setRepeats(false);

        JButton showPathButton = new JButton("Show Path");
        showPathButton.addActionListener(e -> highlightSelectedPath());
        JButton clearPathButton = new JButton("Clear Path");
        clearPathButton.addActionListener(e -> {
            highlightedPathKeys = Set.of();
            markVisibleDirty();
            scheduleRefresh(true);
        });
        JButton centerFitButton = new JButton("Center/Fit (C)");
        centerFitButton.addActionListener(e -> centerAndFit());
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        controls.add(showPathButton);
        controls.add(clearPathButton);
        controls.add(centerFitButton);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(canvas), new JScrollPane(info));
        split.setResizeWeight(0.88);
        JPanel root = new JPanel(new BorderLayout());
        root.add(controls, BorderLayout.NORTH);
        root.add(split, BorderLayout.CENTER);
        frame.setContentPane(root);
    }

    public void showWindow() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public void hideWindow() {
        SwingUtilities.invokeLater(() -> frame.setVisible(false));
    }

    public void setFontSize(int size) {
        SwingUtilities.invokeLater(() -> {
            Font f = new Font(Font.MONOSPACED, Font.PLAIN, size);
            canvas.setFont(f);
            info.setFont(f);
        });
    }

    public void reset() {
        if (SwingUtilities.isEventDispatchThread()) {
            resetNow();
            return;
        }
        try {
            SwingUtilities.invokeAndWait(this::resetNow);
        } catch (Exception ignored) {
            SwingUtilities.invokeLater(this::resetNow);
        }
    }

    public void onSearchStart() {
        reset();
    }

    public void onSearchEnd() {
        SwingUtilities.invokeLater(() -> info.append("\nSearch completed."));
    }

    public void setActiveNodeLimit(int activeNodeLimit) {
        this.activeNodeLimit = Math.max(10, activeNodeLimit);
        showAllNodes = false;
        markVisibleDirty();
        scheduleRefresh(true);
    }

    public int getActiveNodeLimit() {
        return activeNodeLimit;
    }

    public void onLogEvent(SimpleSearchNode node, ExternalLoggerLogType type, boolean isGoal) {
        int e = epoch;
        synchronized (pendingLogEvents) {
            pendingLogEvents.add(new LogEvent(node, type, isGoal, e));
            if (logDrainScheduled) {
                return;
            }
            logDrainScheduled = true;
        }
        SwingUtilities.invokeLater(this::drainPendingLogEvents);
    }

    public void markSolutionNode(SimpleSearchNode node) {
        SwingUtilities.invokeLater(() -> {
            GraphNode n = ensureNode(node);
            n.isSolution = true;
            selectedKey = n.key;
            // Reveal ancestors so solution is reachable immediately.
            GraphNode cur = n.parent;
            while (cur != null) {
                cur.userExpanded = true;
                cur = cur.parent;
            }
            markVisibleDirty();
            scheduleRefresh(true);
        });
    }

    private void highlightSelectedPath() {
        SwingUtilities.invokeLater(() -> {
            if (selectedKey == null || !nodes.containsKey(selectedKey)) {
                JOptionPane.showMessageDialog(frame, "Select a node first.", "Path", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            LinkedHashSet<String> path = new LinkedHashSet<>();
            GraphNode cur = nodes.get(selectedKey);
            while (cur != null) {
                path.add(cur.key);
                cur = cur.parent;
            }
            highlightedPathKeys = path;
            markVisibleDirty();
            scheduleRefresh(true);
        });
    }

    private void centerAndFit() {
        SwingUtilities.invokeLater(() -> {
            showAllNodes = true;
            markVisibleDirty();
            canvas.fitViewToContent(true);
            scheduleRefresh(true);
        });
    }

    private void drainPendingLogEvents() {
        int processed = 0;
        while (processed < 2500) {
            LogEvent ev;
            synchronized (pendingLogEvents) {
                ev = pendingLogEvents.poll();
                if (ev == null) {
                    logDrainScheduled = false;
                    break;
                }
            }
            processLogEvent(ev);
            processed++;
        }
        scheduleRefresh(true);
        synchronized (pendingLogEvents) {
            if (!pendingLogEvents.isEmpty()) {
                SwingUtilities.invokeLater(this::drainPendingLogEvents);
            } else {
                logDrainScheduled = false;
            }
        }
    }

    private void processLogEvent(LogEvent ev) {
        if (ev.epoch != epoch) {
            return;
        }
        SimpleSearchNode node = ev.node;
        ExternalLoggerLogType type = ev.type;
        GraphNode n = ensureNode(node);
        n.status = type;
        n.gValue = node.gValue;
        n.isGoal = n.isGoal || ev.isGoal;
        if (node instanceof SearchNode sn) {
            n.fValue = sn.f;
        }
        if (type == ExternalLoggerLogType.Generating) generated++;
        else if (type == ExternalLoggerLogType.Expanding) expanded++;
        else if (type == ExternalLoggerLogType.Closing) closed++;
    }

    private GraphNode ensureNode(SimpleSearchNode node) {
        String key = nodeKey(node);
        GraphNode existing = nodes.get(key);
        if (existing != null) {
            return existing;
        }
        GraphNode parent = null;
        int depth = 0;
        if (node.father != null) {
            parent = ensureNode(node.father);
            depth = parent.depth + 1;
        } else if (rootKey == null) {
            rootKey = key;
        }
        GraphNode created = new GraphNode(key, "s" + nextStateIndex++, actionText(node), depth, node.gValue);
        created.parent = parent;
        if (node instanceof SearchNode sn) {
            created.fValue = sn.f;
        }
        if (created.parent == null) {
            created.isStart = true;
            created.userExpanded = false;
        } else {
            String edgeKey = created.parent.key + "->" + created.key;
            List<GraphEdge> outs = outgoingEdges.computeIfAbsent(created.parent.key, ignored -> new ArrayList<>());
            boolean exists = false;
            for (GraphEdge e : outs) {
                if (e.key.equals(edgeKey)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                outs.add(new GraphEdge(edgeKey, created.parent, created));
                created.parent.childCount++;
            }
        }
        nodes.put(key, created);
        depthCounts.merge(depth, 1, Integer::sum);
        markVisibleDirty();
        return created;
    }

    private void markVisibleDirty() {
        visibleDirty = true;
    }

    private void scheduleRefresh(boolean withInfo) {
        infoDirty = infoDirty || withInfo;
        if (!refreshScheduled) {
            refreshScheduled = true;
            renderTimer.restart();
        }
    }

    private void updateInfoNow() {
        int visible = computeVisibleNodeKeys().size();
        StringBuilder depthSummary = new StringBuilder();
        int maxDepth = 0;
        for (Integer d : depthCounts.keySet()) {
            maxDepth = Math.max(maxDepth, d);
        }
        int shownDepths = Math.min(maxDepth, 8);
        for (int d = 0; d <= shownDepths; d++) {
            int c = depthCounts.getOrDefault(d, 0);
            if (c > 0) {
                if (!depthSummary.isEmpty()) {
                    depthSummary.append("  ");
                }
                depthSummary.append("d").append(d).append("=").append(c);
            }
        }
        if (maxDepth > shownDepths) {
            depthSummary.append(" ... d").append(maxDepth);
        }
        StringBuilder selectedInfo = new StringBuilder();
        if (selectedKey != null) {
            GraphNode n = nodes.get(selectedKey);
            if (n != null) {
                selectedInfo.append("\n\nSelected: ").append(n.displayName)
                        .append("  (depth=").append(n.depth).append(")")
                        .append("\nStatus: ").append(n.status == null ? "-" : statusLabel(n.status))
                        .append("  g=").append(n.gValue)
                        .append(Float.isNaN(n.fValue) ? "" : "  f=" + n.fValue)
                        .append("\nAction: ").append(n.action)
                        .append("\nChildren: ").append(n.childCount);
            }
        }
        StringBuilder frontierInfo = new StringBuilder("\n\nFrontier sample (best 5):");
        List<GraphNode> frontier = computeFrontierSample(5);
        if (frontier.isEmpty()) {
            frontierInfo.append("\n- no frontier nodes");
        } else {
            for (GraphNode n : frontier) {
                frontierInfo.append("\n- ").append(n.displayName)
                        .append(" ")
                        .append(Float.isNaN(n.fValue) ? ("g=" + n.gValue) : ("f=" + n.fValue + " g=" + n.gValue))
                        .append("  path: ").append(pathForNode(n, 8));
            }
        }

        info.setText("Generated: " + generated +
                "\nExpanded: " + expanded +
                "\nExpanded/Closed: " + closed +
                "\nNodes: " + nodes.size() + "  Visible: " + visible + "  Limit: " + activeNodeLimit +
                "\nDepths: " + depthSummary +
                selectedInfo +
                frontierInfo);
    }

    private List<GraphNode> computeFrontierSample(int k) {
        List<GraphNode> out = new ArrayList<>();
        for (GraphNode n : nodes.values()) {
            if (n.status == ExternalLoggerLogType.Closing) {
                continue;
            }
            if (n.parent == null && n.status == null) {
                continue;
            }
            out.add(n);
        }
        out.sort(Comparator
                .comparing((GraphNode n) -> Float.isNaN(n.fValue))
                .thenComparing(n -> Float.isNaN(n.fValue) ? n.gValue : n.fValue)
                .thenComparingInt(n -> n.depth));
        if (out.size() > k) {
            return new ArrayList<>(out.subList(0, k));
        }
        return out;
    }

    private String pathForNode(GraphNode n, int maxNodes) {
        ArrayDeque<String> seq = new ArrayDeque<>();
        GraphNode cur = n;
        while (cur != null) {
            seq.addFirst(cur.displayName);
            cur = cur.parent;
        }
        List<String> list = new ArrayList<>(seq);
        if (list.size() <= maxNodes) {
            return String.join("->", list);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0)).append("->").append(list.get(1)).append("->...");
        for (int i = Math.max(2, list.size() - (maxNodes - 2)); i < list.size(); i++) {
            sb.append("->").append(list.get(i));
        }
        return sb.toString();
    }

    private Set<String> computeVisibleNodeKeys() {
        if (!visibleDirty) {
            return visibleCache;
        }
        if (showAllNodes) {
            LinkedHashSet<String> all = new LinkedHashSet<>(nodes.keySet());
            visibleCache = all;
            visibleDirty = false;
            return visibleCache;
        }
        LinkedHashSet<String> visible = new LinkedHashSet<>();
        if (nodes.isEmpty()) {
            visibleCache = visible;
            visibleDirty = false;
            return visibleCache;
        }
        String start = rootKey;
        if (start == null) {
            start = nodes.keySet().iterator().next();
        }
        visible.add(start);

        if (selectedKey != null && nodes.containsKey(selectedKey)) {
            GraphNode cur = nodes.get(selectedKey);
            while (cur != null && visible.size() < activeNodeLimit) {
                visible.add(cur.key);
                cur = cur.parent;
            }
        }
        if (!highlightedPathKeys.isEmpty()) {
            for (String k : highlightedPathKeys) {
                if (visible.size() < activeNodeLimit) {
                    visible.add(k);
                } else {
                    break;
                }
            }
        }

        ArrayDeque<String> q = new ArrayDeque<>(visible);
        while (!q.isEmpty() && visible.size() < activeNodeLimit) {
            String k = q.poll();
            GraphNode n = nodes.get(k);
            if (n == null) {
                continue;
            }
            if (!(n.userExpanded || n.isStart)) {
                continue;
            }
            List<GraphEdge> outs = outgoingEdges.get(k);
            if (outs == null) {
                continue;
            }
            for (GraphEdge e : outs) {
                if (visible.add(e.to.key) && visible.size() < activeNodeLimit) {
                    q.add(e.to.key);
                }
            }
        }
        visibleCache = visible;
        visibleDirty = false;
        return visibleCache;
    }

    private static String nodeKey(SimpleSearchNode n) {
        if (n.id != null) {
            return n.id.toString();
        }
        return Integer.toHexString(System.identityHashCode(n));
    }

    private static String actionText(SimpleSearchNode n) {
        return n.transition == null ? "init/wait" : n.transition.toString();
    }

    private static String statusLabel(ExternalLoggerLogType type) {
        return switch (type) {
            case Generating -> "generated";
            case Expanding -> "expanded";
            case Closing -> "expanded/closed";
        };
    }

    private static final class GraphNode {
        final String key;
        final String displayName;
        final String action;
        final int depth;
        float gValue;
        float fValue = Float.NaN;
        ExternalLoggerLogType status;
        GraphNode parent;
        int childCount = 0;
        boolean userExpanded = false;
        boolean isStart = false;
        boolean isGoal = false;
        boolean isSolution = false;

        GraphNode(String key, String displayName, String action, int depth, float gValue) {
            this.key = key;
            this.displayName = displayName;
            this.action = action;
            this.depth = depth;
            this.gValue = gValue;
        }
    }

    private enum EdgeStatus { TRIED, CHOSEN }

    private static final class GraphEdge {
        final String key;
        final GraphNode from;
        final GraphNode to;
        EdgeStatus status = EdgeStatus.TRIED;

        GraphEdge(String key, GraphNode from, GraphNode to) {
            this.key = key;
            this.from = from;
            this.to = to;
        }
    }

    private static final class LogEvent {
        final SimpleSearchNode node;
        final ExternalLoggerLogType type;
        final boolean isGoal;
        final int epoch;

        LogEvent(SimpleSearchNode node, ExternalLoggerLogType type, boolean isGoal, int epoch) {
            this.node = node;
            this.type = type;
            this.isGoal = isGoal;
            this.epoch = epoch;
        }
    }

    private final class GraphCanvas extends JPanel {
        private static final int NODE_D = 54;
        private static final int X_GAP = 145;
        private static final int Y_GAP = 68;
        private double zoom = 1.0;
        private double panX = 0;
        private double panY = 40;
        private Point dragStart;

        GraphCanvas() {
            setPreferredSize(new Dimension(2200, 1800));
            setBackground(new Color(250, 252, 255));
            MouseAdapter mouse = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        GraphNode hit = findNodeAt(e.getPoint());
                        if (hit != null) {
                            selectedKey = hit.key;
                            if (e.getClickCount() >= 2) {
                                hit.userExpanded = !hit.userExpanded;
                            }
                            markVisibleDirty();
                            scheduleRefresh(true);
                            return;
                        }
                    }
                    dragStart = e.getPoint();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dragStart != null) {
                        panX += (e.getX() - dragStart.x);
                        panY += (e.getY() - dragStart.y);
                        dragStart = e.getPoint();
                        repaint();
                    }
                }

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    // Zoom around mouse cursor to avoid jumpy navigation.
                    double oldZoom = zoom;
                    double rotation = e.getPreciseWheelRotation();
                    double factor = Math.pow(1.12, -rotation);
                    double newZoom = Math.max(0.005, Math.min(3.5, oldZoom * factor));
                    if (Math.abs(newZoom - oldZoom) < 1e-9) {
                        return;
                    }

                    double mx = e.getX();
                    double my = e.getY();
                    double tx = getWidth() / 2.0 + panX;
                    double ty = panY;

                    double worldX = (mx - tx) / oldZoom;
                    double worldY = (my - ty) / oldZoom;

                    zoom = newZoom;
                    panX = (mx - worldX * zoom) - getWidth() / 2.0;
                    panY = my - worldY * zoom;
                    repaint();
                }
            };
            addMouseListener(mouse);
            addMouseMotionListener(mouse);
            addMouseWheelListener(mouse);

            InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();
            im.put(KeyStroke.getKeyStroke('c'), "tree-center-fit");
            im.put(KeyStroke.getKeyStroke('C'), "tree-center-fit");
            am.put("tree-center-fit", new AbstractAction() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    showAllNodes = true;
                    markVisibleDirty();
                    fitViewToContent(true);
                    scheduleRefresh(true);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Set<String> visible = computeVisibleNodeKeys();
            Map<Integer, List<GraphNode>> byDepth = groupVisibleByDepth(visible);

            AffineTransform old = g2.getTransform();
            g2.translate(getWidth() / 2.0 + panX, panY);
            g2.scale(zoom, zoom);

            for (String key : visible) {
                GraphNode from = nodes.get(key);
                if (from == null || !(from.userExpanded || from.isStart)) {
                    continue;
                }
                List<GraphEdge> outs = outgoingEdges.get(key);
                if (outs == null) {
                    continue;
                }
                for (GraphEdge e : outs) {
                    if (visible.contains(e.to.key)) {
                        drawEdge(g2, from, e.to, byDepth);
                    }
                }
            }
            for (List<GraphNode> layer : byDepth.values()) {
                for (GraphNode n : layer) {
                    drawNode(g2, n, byDepth);
                }
            }
            g2.setTransform(old);
            drawOverview(g2, byDepth, visible);
            g2.dispose();
        }

        private Map<Integer, List<GraphNode>> groupVisibleByDepth(Set<String> visible) {
            Map<Integer, List<GraphNode>> byDepth = new HashMap<>();
            for (String k : visible) {
                GraphNode n = nodes.get(k);
                if (n != null) {
                    byDepth.computeIfAbsent(n.depth, ignored -> new ArrayList<>()).add(n);
                }
            }
            for (List<GraphNode> layer : byDepth.values()) {
                layer.sort((a, b) -> a.key.compareTo(b.key));
            }
            return byDepth;
        }

        private Point nodeCenter(GraphNode n, Map<Integer, List<GraphNode>> byDepth) {
            List<GraphNode> layer = byDepth.getOrDefault(n.depth, List.of());
            int idx = 0;
            for (int i = 0; i < layer.size(); i++) {
                if (layer.get(i).key.equals(n.key)) {
                    idx = i;
                    break;
                }
            }
            int count = layer.size();
            int startY = -((count - 1) * Y_GAP) / 2;
            int x = n.depth * X_GAP;
            int y = startY + idx * Y_GAP;
            return new Point(x, y);
        }

        private void drawEdge(Graphics2D g2, GraphNode from, GraphNode to, Map<Integer, List<GraphNode>> byDepth) {
            Point p1 = nodeCenter(from, byDepth);
            Point p2 = nodeCenter(to, byDepth);
            g2.setColor(new Color(88, 124, 170));
            g2.setStroke(new BasicStroke(0.9f));
            int r = NODE_D / 2;
            double dx = p2.x - p1.x;
            double dy = p2.y - p1.y;
            double len = Math.max(1.0, Math.hypot(dx, dy));
            double ux = dx / len;
            double uy = dy / len;
            double x1 = p1.x + ux * r;
            double y1 = p1.y + uy * r;
            double x2 = p2.x - ux * r;
            double y2 = p2.y - uy * r;
            if (highlightedPathKeys.contains(from.key) && highlightedPathKeys.contains(to.key)) {
                g2.setColor(new Color(220, 130, 28));
                g2.setStroke(new BasicStroke(1.6f));
            }
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }

        private void drawNode(Graphics2D g2, GraphNode n, Map<Integer, List<GraphNode>> byDepth) {
            Point c = nodeCenter(n, byDepth);
            int x = c.x - NODE_D / 2;
            int y = c.y - NODE_D / 2;

            Color fill;
            if (n.isSolution) fill = new Color(255, 236, 179);
            else if (n.isGoal) fill = new Color(206, 241, 210);
            else if (n.isStart) fill = new Color(209, 228, 255);
            else fill = new Color(237, 242, 250);

            Ellipse2D rr = new Ellipse2D.Double(x, y, NODE_D, NODE_D);
            g2.setColor(fill);
            g2.fill(rr);
            boolean selected = n.key.equals(selectedKey);
            boolean onPath = highlightedPathKeys.contains(n.key);
            g2.setColor(selected ? new Color(12, 26, 46) : (onPath ? new Color(220, 130, 28) : new Color(80, 80, 80)));
            g2.setStroke(new BasicStroke(selected ? 2.3f : (onPath ? 2.0f : 1.1f)));
            g2.draw(rr);

            g2.setColor(new Color(20, 20, 20));
            FontMetrics fm = g2.getFontMetrics();
            int tw = fm.stringWidth(n.displayName);
            g2.drawString(n.displayName, c.x - tw / 2, c.y + fm.getAscent() / 2 - 2);
        }

        private void drawOverview(Graphics2D g2, Map<Integer, List<GraphNode>> byDepth, Set<String> visible) {
            int panelW = 220;
            int panelH = 140;
            int x = getWidth() - panelW - 16;
            int y = 16;
            g2.setColor(new Color(255, 255, 255, 230));
            g2.fillRoundRect(x, y, panelW, panelH, 14, 14);
            g2.setColor(new Color(90, 90, 90));
            g2.drawRoundRect(x, y, panelW, panelH, 14, 14);
            g2.drawString("Overview", x + 8, y + 16);

            int maxDepth = 0;
            for (Integer d : depthCounts.keySet()) {
                maxDepth = Math.max(maxDepth, d);
            }
            int barTop = y + 26;
            int barBottom = y + panelH - 12;
            int barH = Math.max(1, barBottom - barTop);
            int usableW = panelW - 20;
            int levels = Math.max(1, maxDepth + 1);
            int stepW = Math.max(1, usableW / levels);
            int maxCount = 1;
            for (Integer c : depthCounts.values()) {
                maxCount = Math.max(maxCount, c);
            }
            for (int d = 0; d <= maxDepth; d++) {
                int total = depthCounts.getOrDefault(d, 0);
                if (total <= 0) continue;
                int vis = byDepth.getOrDefault(d, List.of()).size();
                int hTot = (int) ((total / (double) maxCount) * (barH - 2));
                int hVis = (int) ((vis / (double) maxCount) * (barH - 2));
                int bx = x + 10 + d * stepW;
                int bw = Math.max(1, stepW - 1);
                g2.setColor(new Color(200, 210, 230));
                g2.fillRect(bx, barBottom - hTot, bw, hTot);
                g2.setColor(new Color(88, 134, 198));
                g2.fillRect(bx, barBottom - hVis, bw, hVis);
            }
            g2.setColor(new Color(80, 80, 80));
            g2.drawString("visible " + visible.size() + " / total " + nodes.size(), x + 8, y + panelH - 2);
        }

        private GraphNode findNodeAt(Point pScreen) {
            double wx = (pScreen.x - (getWidth() / 2.0 + panX)) / zoom;
            double wy = (pScreen.y - panY) / zoom;
            Set<String> visible = computeVisibleNodeKeys();
            Map<Integer, List<GraphNode>> byDepth = groupVisibleByDepth(visible);
            for (String key : visible) {
                GraphNode n = nodes.get(key);
                if (n == null) {
                    continue;
                }
                Point c = nodeCenter(n, byDepth);
                int x = c.x - NODE_D / 2;
                int y = c.y - NODE_D / 2;
                double dx = wx - (x + NODE_D / 2.0);
                double dy = wy - (y + NODE_D / 2.0);
                double rr = NODE_D / 2.0;
                if ((dx * dx + dy * dy) <= rr * rr) {
                    return n;
                }
            }
            return null;
        }

        private void fitViewToContent(boolean entireTree) {
            Set<String> considered;
            Map<Integer, List<GraphNode>> byDepth;
            if (entireTree) {
                considered = new LinkedHashSet<>(nodes.keySet());
                byDepth = groupVisibleByDepth(considered);
            } else {
                considered = computeVisibleNodeKeys();
                byDepth = groupVisibleByDepth(considered);
            }
            if (considered.isEmpty()) {
                return;
            }
            double minX = Double.POSITIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY;
            double maxX = Double.NEGATIVE_INFINITY;
            double maxY = Double.NEGATIVE_INFINITY;
            double r = NODE_D / 2.0;
            for (String key : considered) {
                GraphNode n = nodes.get(key);
                if (n == null) {
                    continue;
                }
                Point c = nodeCenter(n, byDepth);
                minX = Math.min(minX, c.x - r);
                maxX = Math.max(maxX, c.x + r);
                minY = Math.min(minY, c.y - r);
                maxY = Math.max(maxY, c.y + r);
            }
            if (!Double.isFinite(minX) || !Double.isFinite(minY) || !Double.isFinite(maxX) || !Double.isFinite(maxY)) {
                return;
            }

            double contentW = Math.max(1.0, maxX - minX);
            double contentH = Math.max(1.0, maxY - minY);
            double margin = 24.0;
            double availW = Math.max(1.0, getWidth() - 2 * margin);
            double availH = Math.max(1.0, getHeight() - 2 * margin);
            double fitZoom = Math.min(availW / contentW, availH / contentH);
            zoom = Math.max(0.005, Math.min(3.5, fitZoom));

            double cx = (minX + maxX) / 2.0;
            double cy = (minY + maxY) / 2.0;
            panX = -cx * zoom;
            panY = getHeight() / 2.0 - cy * zoom;
        }
    }

    private void resetNow() {
        epoch++;
        nodes.clear();
        outgoingEdges.clear();
        depthCounts.clear();
        pendingLogEvents.clear();
        rootKey = null;
        selectedKey = null;
        nextStateIndex = 0;
        highlightedPathKeys = Set.of();
        showAllNodes = false;
        generated = expanded = closed = 0;
        logDrainScheduled = false;
        markVisibleDirty();
        scheduleRefresh(true);
    }
}
