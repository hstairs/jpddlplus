package com.hstairs.enhspgui.tree;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class LazySearchTreeWindow {
    private static final int NODE_D = 28;
    private static final int H_GAP = 120;
    private static final int V_GAP = 28;
    private static final int MARGIN = 24;
    private static final double MIN_ZOOM = 0.2;
    private static final double MAX_ZOOM = 2.5;
    private static final double DEFAULT_ZOOM = 1.0;

    private final JFrame frame;
    private final GraphCanvas canvas;
    private final JScrollPane canvasScrollPane;
    private final JTextArea info;
    private final Timer renderTimer;

    private final Queue<LogEvent> pendingLogEvents = new ArrayDeque<>();
    private volatile int epoch = 0;
    private boolean logDrainScheduled = false;
    private boolean refreshScheduled = false;
    private boolean infoDirty = false;
    private boolean visibleDirty = true;

    private final Map<String, GraphNode> nodes = new LinkedHashMap<>();

    private Set<String> visibleCache = Set.of();
    private int activeNodeLimit = 120;
    private String rootKey = null;
    private String selectedKey = null;
    private String hoveredKey = null;
    private int nextStateIndex = 0;
    private Set<String> highlightedPathKeys = Set.of();
    private Set<String> highlightedPathEdgeKeys = Set.of();
    private boolean showAllNodes = false;
    private boolean showGeneratedNodes = false;
    private boolean verticalLayout = false;
    private boolean liveJsonMode = false;
    private int liveJsonEventCounter = 0;
    private String latestLiveJsonSnapshot = null;
    private boolean liveJsonRefreshScheduled = false;

    private int generated = 0;
    private int expanded = 0;
    private int closed = 0;
    private String jsonSourcePath = null;

    public LazySearchTreeWindow() {
        frame = new JFrame("Search Tree");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(860, 620);
        frame.setLocationByPlatform(true);

        canvas = new GraphCanvas();
        canvasScrollPane = new JScrollPane(canvas);
        canvasScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        canvasScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        info = new JTextArea();
        info.setEditable(false);
        info.setRows(5);
        info.setBorder(new EmptyBorder(6, 6, 6, 6));
        updateInfoNow();

        renderTimer = new Timer(30, e -> {
            refreshScheduled = false;
            if (infoDirty) {
                updateInfoNow();
                infoDirty = false;
            }
            canvas.repaint();
        });
        renderTimer.setRepeats(false);

        JPanel controls = createControlsPanel();

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, canvasScrollPane, new JScrollPane(info));
        split.setResizeWeight(0.9);

        JPanel root = new JPanel(new BorderLayout());
        root.add(controls, BorderLayout.NORTH);
        root.add(split, BorderLayout.CENTER);
        frame.setContentPane(root);
    }

    private JPanel createControlsPanel() {
        JButton showPathButton = new JButton("Show Path");
        showPathButton.addActionListener(e -> highlightSelectedPath());

        JButton showGlobalPathButton = new JButton("Show Global Path");
        showGlobalPathButton.addActionListener(e -> highlightGlobalPath());

        JButton clearPathButton = new JButton("Clear Path");
        clearPathButton.addActionListener(e -> clearPathHighlight());

        JButton fitButton = new JButton("Fit Visible");
        fitButton.addActionListener(e -> fitVisible(false));

        JButton fitAllButton = new JButton("Fit All");
        fitAllButton.addActionListener(e -> fitVisible(true));

        JCheckBox showGeneratedToggle = new JCheckBox("Show Generated");
        showGeneratedToggle.setSelected(showGeneratedNodes);
        showGeneratedToggle.addActionListener(e -> setShowGeneratedNodes(showGeneratedToggle.isSelected()));

        JCheckBox verticalLayoutToggle = new JCheckBox("Vertical layout");
        verticalLayoutToggle.setSelected(verticalLayout);
        verticalLayoutToggle.addActionListener(e -> setVerticalLayout(verticalLayoutToggle.isSelected()));

        JButton saveJpegButton = new JButton("Save JPEG");
        saveJpegButton.addActionListener(e -> saveCurrentViewAsJpeg());

        JButton saveSvgButton = new JButton("Save SVG (Batik)");
        saveSvgButton.addActionListener(e -> saveCurrentViewAsSvgBatik());

        JButton zoomInButton = new JButton("Zoom +");
        zoomInButton.addActionListener(e -> applyZoomFactor(1.15));

        JButton zoomOutButton = new JButton("Zoom -");
        zoomOutButton.addActionListener(e -> applyZoomFactor(1.0 / 1.15));

        JButton zoomResetButton = new JButton("Zoom 1:1");
        zoomResetButton.addActionListener(e -> resetZoom());

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        controls.add(showPathButton);
        controls.add(showGlobalPathButton);
        controls.add(clearPathButton);
        controls.add(showGeneratedToggle);
        controls.add(verticalLayoutToggle);
        controls.add(saveJpegButton);
        controls.add(saveSvgButton);
        controls.add(fitButton);
        controls.add(fitAllButton);
        controls.add(zoomInButton);
        controls.add(zoomOutButton);
        controls.add(zoomResetButton);
        return controls;
    }

    private void clearPathHighlight() {
        highlightedPathKeys = Set.of();
        highlightedPathEdgeKeys = Set.of();
        showAllNodes = false;
        markVisibleDirty();
        scheduleRefresh(true);
    }

    private void setShowGeneratedNodes(boolean enabled) {
        showGeneratedNodes = enabled;
        markVisibleDirty();
        scheduleRefresh(true);
    }

    private void setVerticalLayout(boolean enabled) {
        verticalLayout = enabled;
        markVisibleDirty();
        scheduleRefresh(true);
        fitVisible(false);
    }

    private void saveCurrentViewAsJpeg() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save Search View");
            FileNameExtensionFilter jpegFilter = new FileNameExtensionFilter("JPEG (*.jpg, *.jpeg)", "jpg", "jpeg");
            FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG lossless (*.png)", "png");
            chooser.addChoosableFileFilter(jpegFilter);
            chooser.addChoosableFileFilter(pngFilter);
            chooser.setFileFilter(jpegFilter);
            chooser.setSelectedFile(new File("search_tree.jpg"));
            int res = chooser.showSaveDialog(frame);
            if (res != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File out = chooser.getSelectedFile();
            boolean savePng = chooser.getFileFilter() == pngFilter;
            String expectedExt = savePng ? ".png" : ".jpg";
            String name = out.getName().toLowerCase(Locale.ROOT);
            if (!(savePng ? name.endsWith(".png") : (name.endsWith(".jpg") || name.endsWith(".jpeg")))) {
                out = new File(out.getParentFile(), out.getName() + expectedExt);
            }
            try {
                int baseW = Math.max(1, canvas.getWidth());
                int baseH = Math.max(1, canvas.getHeight());
                int scale = 3;
                int maxDim = 12000;
                if (baseW * scale > maxDim || baseH * scale > maxDim) {
                    scale = Math.max(1, Math.min(maxDim / baseW, maxDim / baseH));
                }
                int w = Math.max(1, baseW * scale);
                int h = Math.max(1, baseH * scale);
                BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = image.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, w, h);
                setHighQualityRenderingHints(g2);
                g2.scale(scale, scale);
                canvas.paint(g2);
                g2.dispose();
                if (savePng) {
                    ImageIO.write(image, "png", out);
                } else {
                    writeJpegWithQuality(image, out, 1.0f);
                }
                JOptionPane.showMessageDialog(frame,
                        "Saved image:\n" + out.getAbsolutePath(),
                        "Search Tree",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Unable to save image:\n" + ex.getMessage(),
                        "Search Tree",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void setHighQualityRenderingHints(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private static void writeJpegWithQuality(BufferedImage image, File output, float quality) throws IOException {
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(output)) {
            writer.setOutput(ios);
            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(Math.max(0f, Math.min(1f, quality)));
            }
            writer.write(null, new IIOImage(image, null, null), param);
        } finally {
            writer.dispose();
        }
    }

    private void saveCurrentViewAsSvgBatik() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save Search View as SVG (Batik)");
            chooser.setFileFilter(new FileNameExtensionFilter("SVG (*.svg)", "svg"));
            chooser.setSelectedFile(new File("search_tree.svg"));
            int res = chooser.showSaveDialog(frame);
            if (res != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File out = chooser.getSelectedFile();
            String name = out.getName().toLowerCase(Locale.ROOT);
            if (!name.endsWith(".svg")) {
                out = new File(out.getParentFile(), out.getName() + ".svg");
            }
            try {
                writeSvgWithBatik(out);
                JOptionPane.showMessageDialog(frame,
                        "Saved SVG:\n" + out.getAbsolutePath(),
                        "Search Tree",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Batik libraries not found in classpath.\n"
                                + "Add Batik jars under jar_dependencies (e.g. batik-svggen + batik-dom + dependencies) "
                                + "and restart.\n\nDetails: " + ex.getMessage(),
                        "Save SVG (Batik)",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Unable to save SVG:\n" + ex.getMessage(),
                        "Save SVG (Batik)",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void writeSvgWithBatik(File outputFile) throws Exception {
        Class<?> genericDomImplClass = Class.forName("org.apache.batik.dom.GenericDOMImplementation");
        Method getDomImpl = genericDomImplClass.getMethod("getDOMImplementation");
        Object domImpl = getDomImpl.invoke(null);

        Class<?> domImplClass = Class.forName("org.w3c.dom.DOMImplementation");
        Method createDocument = domImplClass.getMethod("createDocument", String.class, String.class, Class.forName("org.w3c.dom.DocumentType"));
        Object document = createDocument.invoke(domImpl, "http://www.w3.org/2000/svg", "svg", null);

        Class<?> svgGraphics2DClass = Class.forName("org.apache.batik.svggen.SVGGraphics2D");
        Constructor<?> ctor = svgGraphics2DClass.getConstructor(Class.forName("org.w3c.dom.Document"));
        Object svgGraphics = ctor.newInstance(document);

        Method setSVGCanvasSize = svgGraphics2DClass.getMethod("setSVGCanvasSize", Dimension.class);
        setSVGCanvasSize.invoke(svgGraphics, new Dimension(Math.max(1, canvas.getWidth()), Math.max(1, canvas.getHeight())));

        Graphics2D g2 = (Graphics2D) svgGraphics;
        setHighQualityRenderingHints(g2);
        canvas.paint(g2);
        g2.dispose();

        Method stream = svgGraphics2DClass.getMethod("stream", Writer.class, boolean.class);
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            stream.invoke(svgGraphics, writer, true);
        }
    }

    private void applyZoomFactor(double factor) {
        canvas.setZoom(canvas.getZoom() * factor);
        canvas.repaint();
    }

    private void resetZoom() {
        canvas.setZoom(DEFAULT_ZOOM);
        canvasScrollPane.getHorizontalScrollBar().setValue(0);
        canvasScrollPane.getVerticalScrollBar().setValue(0);
        canvas.repaint();
    }

    public void showWindow() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            fitVisible(false);
        });
    }

    public void hideWindow() {
        SwingUtilities.invokeLater(() -> frame.setVisible(false));
    }

    public void setFontSize(int size) {
        SwingUtilities.invokeLater(() -> {
            Font f = new Font(Font.MONOSPACED, Font.PLAIN, size);
            canvas.setFont(f);
            info.setFont(f);
            scheduleRefresh(true);
        });
    }

    public void reset() {
        if (SwingUtilities.isEventDispatchThread()) {
            resetNow();
            return;
        }
        SwingUtilities.invokeLater(this::resetNow);
    }

    public void onSearchStart() {
        reset();
    }

    public void onSearchEnd() {
        SwingUtilities.invokeLater(() -> {
            info.append("\nSearch completed.");
            fitVisible(false);
        });
    }

    public void loadJsonTree(Path jsonPath) {
        if (jsonPath == null) {
            return;
        }
        if (SwingUtilities.isEventDispatchThread()) {
            loadJsonTreeNow(jsonPath);
            return;
        }
        SwingUtilities.invokeLater(() -> loadJsonTreeNow(jsonPath));
    }

    public void setActiveNodeLimit(int activeNodeLimit) {
        this.activeNodeLimit = Math.max(10, activeNodeLimit);
        this.showAllNodes = false;
        markVisibleDirty();
        scheduleRefresh(true);
    }

    public void setLiveJsonMode(boolean enabled) {
        liveJsonMode = enabled;
    }

    public int getActiveNodeLimit() {
        return activeNodeLimit;
    }

    public void onLogEvent(SimpleSearchNode node, ExternalLoggerLogType type, boolean isGoal) {
        if (liveJsonMode && node instanceof SearchNode sn && sn.jsonRepresentation != null) {
            onLiveJsonEvent(sn, type);
            return;
        }
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

    private void onLiveJsonEvent(SearchNode node, ExternalLoggerLogType type) {
        // Throttle snapshots: full in-memory JSON parsing is intentionally expensive.
        if (type == ExternalLoggerLogType.Generating && (liveJsonEventCounter++ % 40 != 0)) {
            return;
        }
        SearchNode root = node;
        while (root.father instanceof SearchNode parent) {
            root = parent;
        }
        String snapshot = root.jsonRepresentation.toJSONString();
        synchronized (pendingLogEvents) {
            latestLiveJsonSnapshot = snapshot;
            if (liveJsonRefreshScheduled) {
                return;
            }
            liveJsonRefreshScheduled = true;
        }
        SwingUtilities.invokeLater(this::drainLiveJsonSnapshot);
    }

    private void drainLiveJsonSnapshot() {
        String snapshot;
        synchronized (pendingLogEvents) {
            snapshot = latestLiveJsonSnapshot;
            latestLiveJsonSnapshot = null;
            liveJsonRefreshScheduled = false;
        }
        if (snapshot == null || snapshot.isBlank()) {
            return;
        }
        loadJsonTreeFromStringNow(snapshot, "(live jsonRepresentation)");
    }

    public void markSolutionNode(SimpleSearchNode node) {
        SwingUtilities.invokeLater(() -> {
            GraphNode n = ensureNode(node);
            n.isSolution = true;
            selectedKey = n.key;
            applyPathHighlightFromNode(n, true);
        });
    }

    private void highlightSelectedPath() {
        SwingUtilities.invokeLater(() -> {
            if ((selectedKey == null || !nodes.containsKey(selectedKey)) && rootKey != null && nodes.containsKey(rootKey)) {
                selectedKey = rootKey;
            }
            if (selectedKey == null || !nodes.containsKey(selectedKey)) {
                JOptionPane.showMessageDialog(frame, "Select a node first.", "Path", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            applyPathHighlightFromNode(nodes.get(selectedKey), true);
        });
    }

    private void highlightGlobalPath() {
        SwingUtilities.invokeLater(() -> {
            GraphNode target = pickGlobalPathTarget();
            if (target == null) {
                JOptionPane.showMessageDialog(frame, "No solution path available yet.", "Path", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            selectedKey = target.key;
            applyPathHighlightFromNode(target, true);
        });
    }

    private GraphNode pickGlobalPathTarget() {
        GraphNode bestSolution = null;
        GraphNode bestGoal = null;
        GraphNode bestVisitedLeaf = null;
        GraphNode deepest = null;

        for (GraphNode n : nodes.values()) {
            if (n == null) {
                continue;
            }
            if (deepest == null || n.depth > deepest.depth) {
                deepest = n;
            }
            if (n.isSolution && (bestSolution == null || n.depth > bestSolution.depth)) {
                bestSolution = n;
            }
            if (n.isGoal && (bestGoal == null || n.depth > bestGoal.depth)) {
                bestGoal = n;
            }
            if (n.status == ExternalLoggerLogType.Closing
                    && n.children.isEmpty()
                    && (bestVisitedLeaf == null || n.depth > bestVisitedLeaf.depth)) {
                bestVisitedLeaf = n;
            }
        }

        if (bestSolution != null) {
            return bestSolution;
        }
        if (bestGoal != null) {
            return bestGoal;
        }
        if (bestVisitedLeaf != null) {
            return bestVisitedLeaf;
        }
        return deepest;
    }

    private void applyPathHighlightFromNode(GraphNode target, boolean revealAllNodes) {
        if (target == null) {
            return;
        }
        LinkedHashSet<String> path = new LinkedHashSet<>();
        LinkedHashSet<String> pathEdges = new LinkedHashSet<>();
        GraphNode cur = target;
        while (cur != null) {
            path.add(cur.key);
            if (cur.parent != null) {
                pathEdges.add(edgeKey(cur.parent, cur));
            }
            cur.expanded = true;
            cur = cur.parent;
        }
        highlightedPathKeys = path;
        highlightedPathEdgeKeys = pathEdges;
        showAllNodes = revealAllNodes;
        markVisibleDirty();
        scheduleRefresh(true);
        fitVisible(false);
    }

    private void fitVisible(boolean all) {
        SwingUtilities.invokeLater(() -> {
            if (all) {
                showAllNodes = true;
                markVisibleDirty();
            }
            Rectangle logicalBounds = computeVisibleBounds();
            if (logicalBounds != null) {
                canvas.fitToBounds(logicalBounds, canvasScrollPane.getViewport());
            }
        });
    }

    private void drainPendingLogEvents() {
        int processed = 0;
        while (processed < 3000) {
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
        GraphNode n = ensureNode(ev.node);
        n.status = ev.type;
        n.isGoal = n.isGoal || ev.isGoal;
        n.gValue = ev.node.gValue;
        if (ev.node instanceof SearchNode sn) {
            n.fValue = sn.f;
        }

        if (ev.type == ExternalLoggerLogType.Generating) {
            generated++;
        } else if (ev.type == ExternalLoggerLogType.Expanding) {
            expanded++;
            n.expanded = true;
            if (n.parent != null) {
                n.parent.expanded = true;
            }
        } else if (ev.type == ExternalLoggerLogType.Closing) {
            closed++;
            n.expanded = true;
        }

        markVisibleDirty();
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

        GraphNode created = new GraphNode(key, nextStateIndex++, actionText(node), depth, node.gValue, stateText(node));
        created.parent = parent;
        if (node instanceof SearchNode sn) {
            created.fValue = sn.f;
        }
        if (parent == null) {
            created.isStart = true;
            created.expanded = true;
        } else {
            parent.children.add(created);
        }

        nodes.put(key, created);
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

    private Set<String> computeVisibleNodeKeys() {
        if (!visibleDirty) {
            return visibleCache;
        }

        LinkedHashSet<String> visible = new LinkedHashSet<>();
        if (nodes.isEmpty()) {
            return cacheVisibleNodes(visible);
        }
        if (showAllNodes) {
            visible.addAll(nodes.keySet());
            return cacheVisibleNodes(visible);
        }

        GraphNode rootNode = resolveRootNode();
        addAnchoredNodesToVisible(visible, rootNode);
        bfsExpandVisibleNodes(visible, rootNode);
        return cacheVisibleNodes(visible);
    }

    private GraphNode resolveRootNode() {
        String root = rootKey != null ? rootKey : nodes.keySet().iterator().next();
        return nodes.get(root);
    }

    private void addAnchoredNodesToVisible(Set<String> visible, GraphNode rootNode) {
        if (rootNode != null) {
            visible.add(rootNode.key);
        }
        if (selectedKey != null && nodes.containsKey(selectedKey)) {
            GraphNode cur = nodes.get(selectedKey);
            while (cur != null) {
                visible.add(cur.key);
                cur = cur.parent;
            }
        }
        visible.addAll(highlightedPathKeys);
    }

    private void bfsExpandVisibleNodes(Set<String> visible, GraphNode rootNode) {
        if (rootNode == null || visible.size() >= activeNodeLimit) {
            return;
        }
        ArrayDeque<GraphNode> q = new ArrayDeque<>();
        q.add(rootNode);

        while (!q.isEmpty() && visible.size() < activeNodeLimit) {
            GraphNode node = q.poll();
            if (!shouldExpandNode(node)) {
                continue;
            }
            for (GraphNode child : node.children) {
                if (visible.size() >= activeNodeLimit) {
                    break;
                }
                if (isHiddenGeneratedLeaf(child)) {
                    continue;
                }
                if (visible.add(child.key)) {
                    q.add(child);
                }
            }
        }
    }

    private boolean shouldExpandNode(GraphNode node) {
        return node.expanded || node.isStart || highlightedPathKeys.contains(node.key);
    }

    private boolean isHiddenGeneratedLeaf(GraphNode node) {
        return node.status == ExternalLoggerLogType.Generating
                && !showGeneratedNodes
                && !node.expanded
                && !highlightedPathKeys.contains(node.key)
                && !node.key.equals(selectedKey);
    }

    private Set<String> cacheVisibleNodes(Set<String> visible) {
        visibleCache = visible;
        visibleDirty = false;
        return visibleCache;
    }

    private Rectangle computeVisibleBounds() {
        Set<String> visible = computeVisibleNodeKeys();
        if (visible.isEmpty()) {
            return null;
        }
        RenderData renderData = buildRenderData(visible);
        if (renderData.visibleNodes.isEmpty()) {
            return null;
        }
        canvas.layoutVisibleNodes(renderData.visibleNodes);

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (String key : renderData.visibleNodes) {
            GraphNode n = nodes.get(key);
            if (n == null) {
                continue;
            }
            minX = Math.min(minX, n.x);
            minY = Math.min(minY, n.y);
            maxX = Math.max(maxX, n.x + NODE_D);
            maxY = Math.max(maxY, n.y + NODE_D);
        }
        if (minX == Integer.MAX_VALUE) {
            return null;
        }
        int pad = 30;
        return new Rectangle(
                Math.max(0, minX - pad),
                Math.max(0, minY - pad),
                (maxX - minX) + pad * 2,
                (maxY - minY) + pad * 2
        );
    }

    private void updateInfoNow() {
        Set<String> visible = computeVisibleNodeKeys();
        int aggregatedGenerated = countAggregatedGenerated(visible);
        String focusKey = hoveredKey != null && nodes.containsKey(hoveredKey) ? hoveredKey : selectedKey;
        StringBuilder sb = new StringBuilder();
        sb.append("Generated: ").append(generated)
          .append("\nExpanded: ").append(expanded)
          .append("\nExpanded/Closed: ").append(closed)
          .append("\nNodes: ").append(nodes.size())
          .append("  Visible: ").append(visible.size())
          .append("  Limit: ").append(showAllNodes ? "all" : activeNodeLimit)
          .append("\nGenerated clustered: ").append(aggregatedGenerated);
        if (jsonSourcePath != null) {
            sb.append("\nSource JSON: ").append(jsonSourcePath);
        }

        if (focusKey != null) {
            GraphNode n = nodes.get(focusKey);
            if (n != null) {
                sb.append("\n\n").append(focusKey.equals(hoveredKey) ? "Hover: " : "Selected: ")
                  .append("s").append(n.index)
                  .append(" depth=").append(n.depth)
                  .append(" children=").append(n.children.size())
                  .append("\nStatus: ").append(n.status == null ? "-" : statusLabel(n.status))
                  .append("  g=").append(n.gValue)
                  .append(Float.isNaN(n.fValue) ? "" : "  f=" + n.fValue)
                  .append("\nAction: ").append(n.action)
                  .append("\n\nState values:\n").append(n.stateValues);
            }
        }
        info.setText(sb.toString());
    }

    private void resetNow() {
        epoch++;
        nodes.clear();
        synchronized (pendingLogEvents) {
            pendingLogEvents.clear();
        }

        rootKey = null;
        selectedKey = null;
        hoveredKey = null;
        nextStateIndex = 0;
        highlightedPathKeys = Set.of();
        highlightedPathEdgeKeys = Set.of();
        showAllNodes = false;
        generated = expanded = closed = 0;
        jsonSourcePath = null;
        latestLiveJsonSnapshot = null;
        liveJsonRefreshScheduled = false;
        liveJsonEventCounter = 0;

        logDrainScheduled = false;
        refreshScheduled = false;
        infoDirty = true;
        visibleDirty = true;
        visibleCache = Set.of();

        canvas.setPreferredSize(new Dimension(1200, 900));
        canvas.setZoom(DEFAULT_ZOOM);
        canvas.revalidate();
        canvas.repaint();
        canvasScrollPane.getHorizontalScrollBar().setValue(0);
        canvasScrollPane.getVerticalScrollBar().setValue(0);

        scheduleRefresh(true);
    }

    private void loadJsonTreeNow(Path jsonPath) {
        try {
            String raw = Files.readString(jsonPath, StandardCharsets.UTF_8);
            loadJsonTreeFromStringNow(raw, jsonPath.toAbsolutePath().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Unable to load JSON search tree:\n" + ex.getMessage(),
                    "Search Tree JSON",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadJsonTreeFromStringNow(String rawJson, String source) {
        try {
            Object parsed = new JSONParser().parse(rawJson);
            if (!(parsed instanceof JSONObject root)) {
                throw new IllegalArgumentException("Root JSON node is not an object");
            }
            resetNow();
            generated = expanded = closed = 0;
            jsonSourcePath = source;
            importJsonNode(root, null, 0, new IdentityHashMap<>());
            GraphNode inferredTarget = pickGlobalPathTarget();
            if (inferredTarget != null && !hasExplicitSolutionNode()) {
                inferredTarget.isSolution = true;
            }
            if (rootKey != null) {
                selectedKey = rootKey;
            }
            showAllNodes = true;
            markVisibleDirty();
            scheduleRefresh(true);
        } catch (Exception ex) {
            // Ignore transient parse errors in live mode; next snapshot will likely fix it.
        }
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

    private static String stateText(SimpleSearchNode n) {
        if (n == null || n.s == null) {
            return "(state unavailable)";
        }
        String txt = String.valueOf(n.s);
        return txt == null || txt.isBlank() ? "(state unavailable)" : txt;
    }

    private static String statusLabel(ExternalLoggerLogType type) {
        return switch (type) {
            case Generating -> "generated";
            case Expanding -> "expanded";
            case Closing -> "expanded/closed";
        };
    }

    private static String edgeKey(GraphNode from, GraphNode to) {
        return from.key + "->" + to.key;
    }

    private GraphNode importJsonNode(Object rawNode, GraphNode parent, int depth, IdentityHashMap<Object, GraphNode> seen) {
        if (!(rawNode instanceof JSONObject obj)) {
            return null;
        }
        GraphNode cached = seen.get(obj);
        if (cached != null) {
            return cached;
        }

        String key = jsonNodeKey(obj, nextStateIndex);
        String action = jsonAction(obj);
        String state = jsonState(obj);
        float g = jsonFloat(obj, "action_cost_to_get_here", "g", "gValue");

        GraphNode node = new GraphNode(key, nextStateIndex++, action, depth, g, state);
        seen.put(obj, node);
        nodes.put(key, node);
        generated++;

        node.parent = parent;
        if (parent == null) {
            node.isStart = true;
            node.expanded = true;
            rootKey = node.key;
        } else {
            parent.children.add(node);
        }

        node.fValue = jsonFloat(obj, "f", "fValue");
        boolean visited = jsonBoolean(obj, "visited", "expanded", "closed");
        node.isGoal = jsonBoolean(obj, "goal", "is_goal");
        node.isSolution = jsonBoolean(obj, "solution", "is_solution", "in_solution");

        List<Object> children = jsonChildren(obj);
        boolean hasChildren = !children.isEmpty();
        node.expanded = node.expanded || visited || hasChildren;
        if (visited) {
            node.status = ExternalLoggerLogType.Closing;
            expanded++;
            closed++;
        } else if (hasChildren) {
            node.status = ExternalLoggerLogType.Expanding;
            expanded++;
        } else {
            node.status = ExternalLoggerLogType.Generating;
        }

        for (Object child : children) {
            importJsonNode(child, node, depth + 1, seen);
        }
        return node;
    }

    private static String jsonNodeKey(JSONObject node, int fallbackIndex) {
        Object step = firstPresent(node, "visit_step", "visited_step", "id");
        if (step != null) {
            return "v" + step + "_" + fallbackIndex;
        }
        return "json_" + fallbackIndex;
    }

    private static String jsonAction(JSONObject node) {
        Object action = firstPresent(node, "action", "operator", "name", "label");
        if (action == null) {
            return "init/wait";
        }
        String value = String.valueOf(action).trim();
        return value.isEmpty() ? "init/wait" : value;
    }

    private static String jsonState(JSONObject node) {
        Object state = firstPresent(node, "state", "values");
        if (state == null) {
            return "(state unavailable)";
        }
        if (state instanceof JSONObject json) {
            return json.toJSONString();
        }
        if (state instanceof JSONArray json) {
            return json.toJSONString();
        }
        String txt = String.valueOf(state).trim();
        return txt.isEmpty() ? "(state unavailable)" : txt;
    }

    private static List<Object> jsonChildren(JSONObject node) {
        Object children = firstPresent(node, "descendants", "children", "nodes");
        if (children instanceof JSONArray array) {
            return new ArrayList<>(array);
        }
        return List.of();
    }

    private static float jsonFloat(JSONObject node, String... keys) {
        Object value = firstPresent(node, keys);
        if (value == null) {
            return Float.NaN;
        }
        if (value instanceof Number n) {
            return n.floatValue();
        }
        try {
            return Float.parseFloat(String.valueOf(value));
        } catch (NumberFormatException ex) {
            return Float.NaN;
        }
    }

    private static boolean jsonBoolean(JSONObject node, String... keys) {
        Object value = firstPresent(node, keys);
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean b) {
            return b;
        }
        String s = String.valueOf(value).trim().toLowerCase(Locale.ROOT);
        return s.equals("true") || s.equals("1") || s.equals("yes");
    }

    private static Object firstPresent(JSONObject node, String... keys) {
        for (String key : keys) {
            if (node.containsKey(key)) {
                return node.get(key);
            }
        }
        return null;
    }

    private boolean hasExplicitSolutionNode() {
        for (GraphNode n : nodes.values()) {
            if (n != null && n.isSolution) {
                return true;
            }
        }
        return false;
    }

    private RenderData buildRenderData(Set<String> visible) {
        LinkedHashSet<String> renderNodes = new LinkedHashSet<>();
        HashMap<String, Integer> aggregatedByParent = new HashMap<>();
        for (String key : visible) {
            GraphNode n = nodes.get(key);
            if (n == null) {
                continue;
            }
            if (isAggregatableGenerated(n)) {
                if (n.parent != null) {
                    aggregatedByParent.merge(n.parent.key, 1, Integer::sum);
                }
                continue;
            }
            renderNodes.add(key);
        }
        return new RenderData(renderNodes, aggregatedByParent);
    }

    private int countAggregatedGenerated(Set<String> visible) {
        int count = 0;
        for (String key : visible) {
            GraphNode n = nodes.get(key);
            if (n != null && isAggregatableGenerated(n)) {
                count++;
            }
        }
        return count;
    }

    private boolean isAggregatableGenerated(GraphNode n) {
        if (showGeneratedNodes) {
            return false;
        }
        return n != null
                && n.parent != null
                && n.status == ExternalLoggerLogType.Generating
                && !n.expanded
                && !n.isGoal
                && !n.isSolution
                && !highlightedPathKeys.contains(n.key)
                && !n.key.equals(selectedKey);
    }

    private static final class RenderData {
        final Set<String> visibleNodes;
        final Map<String, Integer> aggregatedGeneratedByParent;

        RenderData(Set<String> visibleNodes, Map<String, Integer> aggregatedGeneratedByParent) {
            this.visibleNodes = visibleNodes;
            this.aggregatedGeneratedByParent = aggregatedGeneratedByParent;
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

    private static final class GraphNode {
        final String key;
        final int index;
        final String action;
        final String stateValues;
        final int depth;
        final List<GraphNode> children = new ArrayList<>();

        GraphNode parent;
        ExternalLoggerLogType status;
        float gValue;
        float fValue = Float.NaN;

        boolean expanded = false;
        boolean isStart = false;
        boolean isGoal = false;
        boolean isSolution = false;

        int x = 0;
        int y = 0;

        GraphNode(String key, int index, String action, int depth, float gValue, String stateValues) {
            this.key = key;
            this.index = index;
            this.action = action;
            this.stateValues = stateValues;
            this.depth = depth;
            this.gValue = gValue;
        }
    }

    private final class GraphCanvas extends JPanel {
        private double zoom = DEFAULT_ZOOM;
        private int logicalWidth = 1200;
        private int logicalHeight = 900;

        GraphCanvas() {
            setPreferredSize(new Dimension(1200, 900));
            setBackground(new Color(250, 252, 255));

            MouseAdapter mouse = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() != MouseEvent.BUTTON1) {
                        return;
                    }
                    GraphNode hit = findNodeAt(e.getPoint());
                    if (hit == null) {
                        return;
                    }
                    selectedKey = hit.key;
                    // Never collapse on click: keep descendants visible while navigating.
                    hit.expanded = true;
                    markVisibleDirty();
                    scheduleRefresh(true);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    GraphNode hit = findNodeAt(e.getPoint());
                    String hk = hit == null ? null : hit.key;
                    if ((hoveredKey == null && hk == null) || (hoveredKey != null && hoveredKey.equals(hk))) {
                        return;
                    }
                    hoveredKey = hk;
                    scheduleRefresh(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (hoveredKey != null) {
                        hoveredKey = null;
                        scheduleRefresh(true);
                    }
                }
            };
            addMouseListener(mouse);
            addMouseMotionListener(mouse);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.scale(zoom, zoom);

            Set<String> visible = computeVisibleNodeKeys();
            RenderData renderData = buildRenderData(visible);
            layoutVisibleNodes(renderData.visibleNodes);

            for (String key : renderData.visibleNodes) {
                GraphNode n = nodes.get(key);
                if (n == null || n.parent == null || !renderData.visibleNodes.contains(n.parent.key)) {
                    continue;
                }
                drawEdge(g2, n.parent, n);
            }
            for (String key : renderData.visibleNodes) {
                GraphNode n = nodes.get(key);
                if (n != null) {
                    drawNode(g2, n, renderData.aggregatedGeneratedByParent.getOrDefault(n.key, 0));
                }
            }
            g2.dispose();
        }

        void layoutVisibleNodes(Set<String> visible) {
            Map<Integer, List<GraphNode>> byDepth = new LinkedHashMap<>();
            for (String key : visible) {
                GraphNode n = nodes.get(key);
                if (n != null) {
                    byDepth.computeIfAbsent(n.depth, ignored -> new ArrayList<>()).add(n);
                }
            }
            int maxX = 0;
            int maxY = 0;
            int maxDepth = 0;
            int maxLayerSize = 0;
            for (List<GraphNode> layer : byDepth.values()) {
                layer.sort(Comparator.comparingInt(a -> a.index));
                maxLayerSize = Math.max(maxLayerSize, layer.size());
            }
            for (Integer depth : byDepth.keySet()) {
                if (depth != null) {
                    maxDepth = Math.max(maxDepth, depth);
                }
            }

            final int depthStep = NODE_D + H_GAP;
            final int layerStep = NODE_D + V_GAP;
            final int halfLayerSpan = Math.max(0, (maxLayerSize - 1) * layerStep / 2);

            if (!verticalLayout) {
                final int rootX = MARGIN;
                final int centerY = MARGIN + halfLayerSpan;
                for (Map.Entry<Integer, List<GraphNode>> e : byDepth.entrySet()) {
                    int depth = e.getKey();
                    List<GraphNode> layer = e.getValue();
                    int layerHalf = (layer.size() - 1) * layerStep / 2;
                    for (int i = 0; i < layer.size(); i++) {
                        GraphNode n = layer.get(i);
                        n.x = rootX + depth * depthStep;
                        n.y = centerY - layerHalf + i * layerStep;
                        maxX = Math.max(maxX, n.x + NODE_D);
                        maxY = Math.max(maxY, n.y + NODE_D);
                    }
                }
            } else {
                final int centerX = MARGIN + halfLayerSpan;
                final int rootY = MARGIN;
                for (Map.Entry<Integer, List<GraphNode>> e : byDepth.entrySet()) {
                    int depth = e.getKey();
                    List<GraphNode> layer = e.getValue();
                    int layerHalf = (layer.size() - 1) * layerStep / 2;
                    for (int i = 0; i < layer.size(); i++) {
                        GraphNode n = layer.get(i);
                        n.x = centerX - layerHalf + i * layerStep;
                        n.y = rootY + depth * depthStep;
                        maxX = Math.max(maxX, n.x + NODE_D);
                        maxY = Math.max(maxY, n.y + NODE_D);
                    }
                }
            }
            int wantedW = Math.max(1200, maxX + MARGIN);
            int wantedH = Math.max(900, maxY + MARGIN);
            logicalWidth = wantedW;
            logicalHeight = wantedH;
            int scaledW = Math.max(1, (int) Math.ceil(logicalWidth * zoom));
            int scaledH = Math.max(1, (int) Math.ceil(logicalHeight * zoom));
            Dimension cur = getPreferredSize();
            if (cur.width != scaledW || cur.height != scaledH) {
                setPreferredSize(new Dimension(scaledW, scaledH));
                revalidate();
            }
        }

        double getZoom() {
            return zoom;
        }

        void setZoom(double newZoom) {
            double clamped = Math.max(MIN_ZOOM, Math.min(MAX_ZOOM, newZoom));
            if (Math.abs(clamped - zoom) < 1e-6) {
                return;
            }
            zoom = clamped;
            int scaledW = Math.max(1, (int) Math.ceil(logicalWidth * zoom));
            int scaledH = Math.max(1, (int) Math.ceil(logicalHeight * zoom));
            setPreferredSize(new Dimension(scaledW, scaledH));
            revalidate();
        }

        void fitToBounds(Rectangle logicalBounds, JViewport viewport) {
            if (logicalBounds == null || viewport == null) {
                return;
            }
            Dimension extent = viewport.getExtentSize();
            if (extent.width <= 4 || extent.height <= 4) {
                return;
            }
            double zx = extent.width / (double) Math.max(1, logicalBounds.width);
            double zy = extent.height / (double) Math.max(1, logicalBounds.height);
            double fitZoom = Math.min(zx, zy) * 0.92;
            setZoom(fitZoom);

            int cx = (int) Math.round((logicalBounds.x + logicalBounds.width / 2.0) * zoom);
            int cy = (int) Math.round((logicalBounds.y + logicalBounds.height / 2.0) * zoom);
            int tx = Math.max(0, cx - extent.width / 2);
            int ty = Math.max(0, cy - extent.height / 2);
            scrollRectToVisible(new Rectangle(tx, ty, extent.width, extent.height));
            repaint();
        }

        private void drawEdge(Graphics2D g2, GraphNode from, GraphNode to) {
            final int x1;
            final int y1;
            final int x2;
            final int y2;
            if (!verticalLayout) {
                x1 = from.x + NODE_D;
                y1 = from.y + NODE_D / 2;
                x2 = to.x;
                y2 = to.y + NODE_D / 2;
            } else {
                x1 = from.x + NODE_D / 2;
                y1 = from.y + NODE_D;
                x2 = to.x + NODE_D / 2;
                y2 = to.y;
            }

            boolean onPath = highlightedPathEdgeKeys.contains(edgeKey(from, to));
            boolean pathMode = !highlightedPathKeys.isEmpty();
            g2.setColor(onPath ? new Color(22, 163, 74) : (pathMode ? new Color(190, 200, 214) : new Color(100, 130, 170)));
            g2.setStroke(new BasicStroke(onPath ? 4.2f : 1.1f));
            g2.draw(new Line2D.Double(x1, y1, x2, y2));

            if (onPath) {
                g2.setColor(new Color(16, 120, 54, 170));
                g2.setStroke(new BasicStroke(7.0f));
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
                g2.setColor(new Color(22, 163, 74));
                g2.setStroke(new BasicStroke(3.4f));
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }

            String edgeAction = to.action == null ? "" : to.action.trim();
            if (!edgeAction.isEmpty() && !edgeAction.equals("init/wait")) {
                int availablePx = !verticalLayout
                        ? Math.max(18, x2 - x1 - 14)
                        : Math.max(18, Math.abs(y2 - y1) - 14);
                int baseFontSize = g2.getFont().getSize();
                int fontSize = baseFontSize;
                String label = compactActionKeepingParams(edgeAction);
                Font originalFont = g2.getFont();
                Font labelFont = originalFont.deriveFont((float) fontSize);
                g2.setFont(labelFont);
                FontMetrics fm = g2.getFontMetrics();
                while (fm.stringWidth(label) > availablePx && fontSize > 8) {
                    fontSize--;
                    labelFont = originalFont.deriveFont((float) fontSize);
                    g2.setFont(labelFont);
                    fm = g2.getFontMetrics();
                }
                int tw = fm.stringWidth(label);
                if (tw > availablePx) {
                    label = forceCompactOperator(label);
                    fm = g2.getFontMetrics();
                    tw = fm.stringWidth(label);
                }

                int mx = (x1 + x2) / 2;
                int my = (y1 + y2) / 2;
                int th = fm.getHeight();
                int px;
                int py;
                if (!verticalLayout) {
                    int minLabelX = x1 + 7;
                    int maxLabelX = Math.max(minLabelX, x2 - 7 - tw);
                    int rawPx = mx - tw / 2 - 4;
                    px = Math.max(minLabelX - 4, Math.min(rawPx, maxLabelX - 4));
                    py = my - th / 2 - 2;
                } else {
                    int minLabelY = Math.min(y1, y2) + 7;
                    int maxLabelY = Math.max(y1, y2) - 7 - th;
                    int rawPy = my - th / 2;
                    py = Math.max(minLabelY, Math.min(rawPy, maxLabelY));
                    px = mx - tw / 2 - 4;
                }
                g2.setColor(new Color(255, 255, 255, 220));
                g2.fillRoundRect(px, py, tw + 8, th, 8, 8);
                g2.setColor(pathMode && !onPath ? new Color(170, 170, 170) : new Color(120, 120, 120));
                g2.setStroke(new BasicStroke(0.9f));
                g2.drawRoundRect(px, py, tw + 8, th, 8, 8);
                g2.setColor(onPath ? new Color(14, 100, 44) : (pathMode ? new Color(145, 145, 145) : new Color(55, 55, 55)));
                g2.drawString(label, px + 4, py + fm.getAscent());
                g2.setFont(originalFont);
            }
        }

        private void drawNode(Graphics2D g2, GraphNode n, int aggregatedGeneratedChildren) {
            java.awt.geom.Ellipse2D shape = new java.awt.geom.Ellipse2D.Double(n.x, n.y, NODE_D, NODE_D);
            boolean onPath = highlightedPathKeys.contains(n.key);
            boolean pathMode = !highlightedPathKeys.isEmpty();
            g2.setColor(fillColor(n, onPath, pathMode));
            g2.fill(shape);

            boolean selected = n.key.equals(selectedKey);
            boolean hovered = n.key.equals(hoveredKey);
            boolean isCoreSearchNode = n.status == ExternalLoggerLogType.Expanding || n.status == ExternalLoggerLogType.Closing || n.isStart || n.isGoal || n.isSolution;
            g2.setColor(selected
                    ? new Color(20, 20, 20)
                    : (onPath
                    ? new Color(14, 110, 44)
                    : (pathMode ? new Color(170, 178, 191) : (isCoreSearchNode ? new Color(30, 90, 45) : new Color(95, 95, 95)))));
            g2.setStroke(new BasicStroke(selected ? 2.4f : (hovered ? 2.0f : (onPath ? 2.8f : (isCoreSearchNode ? 1.6f : 0.9f)))));
            g2.draw(shape);

            if (onPath) {
                java.awt.geom.Ellipse2D halo = new java.awt.geom.Ellipse2D.Double(n.x - 2, n.y - 2, NODE_D + 4, NODE_D + 4);
                g2.setColor(new Color(22, 163, 74, 120));
                g2.setStroke(new BasicStroke(3.0f));
                g2.draw(halo);
            }

            String label = "s" + n.index;
            Font oldFont = g2.getFont();
            Font nodeFont = oldFont.deriveFont(Math.max(9f, oldFont.getSize2D() - 2f));
            g2.setFont(nodeFont);
            FontMetrics fm = g2.getFontMetrics();
            int lx = n.x + (NODE_D - fm.stringWidth(label)) / 2;
            int ly = n.y + ((NODE_D - fm.getHeight()) / 2) + fm.getAscent();
            g2.setColor(pathMode && !onPath ? new Color(135, 144, 158) : new Color(20, 20, 20));
            g2.drawString(label, lx, ly);
            g2.setFont(oldFont);

            if (aggregatedGeneratedChildren > 0) {
                int badgeD = 14;
                int bx = n.x + NODE_D - badgeD - 1;
                int by = n.y - 1;
                g2.setColor(new Color(245, 158, 66));
                g2.fillOval(bx, by, badgeD, badgeD);
                g2.setColor(new Color(120, 60, 10));
                g2.setStroke(new BasicStroke(1.1f));
                g2.drawOval(bx, by, badgeD, badgeD);
                g2.setColor(Color.BLACK);
                String txt = "+" + Math.min(99, aggregatedGeneratedChildren);
                Font old = g2.getFont();
                Font badgeFont = old.deriveFont(Math.max(8f, old.getSize2D() - 3f));
                g2.setFont(badgeFont);
                FontMetrics badgeFm = g2.getFontMetrics();
                int tx = bx + (badgeD - badgeFm.stringWidth(txt)) / 2;
                int ty = by + ((badgeD - badgeFm.getHeight()) / 2) + badgeFm.getAscent();
                g2.drawString(txt, tx, ty);
                g2.setFont(old);
            }
        }

        private Color fillColor(GraphNode n, boolean onPath, boolean pathMode) {
            if (onPath) {
                return new Color(209, 250, 229);
            }
            if (pathMode) {
                return new Color(242, 244, 248);
            }
            if (n.isSolution) {
                return new Color(255, 236, 179);
            }
            if (n.isGoal) {
                return new Color(206, 241, 210);
            }
            if (n.isStart) {
                return new Color(209, 228, 255);
            }
            if (n.status == ExternalLoggerLogType.Expanding) {
                return new Color(194, 237, 204);
            }
            if (n.status == ExternalLoggerLogType.Closing) {
                return new Color(174, 223, 188);
            }
            return new Color(237, 242, 250);
        }

        private GraphNode findNodeAt(Point p) {
            int lx = (int) Math.floor(p.x / zoom);
            int ly = (int) Math.floor(p.y / zoom);
            Set<String> visible = computeVisibleNodeKeys();
            for (String key : visible) {
                GraphNode n = nodes.get(key);
                if (n == null) {
                    continue;
                }
                if (lx >= n.x && lx <= n.x + NODE_D && ly >= n.y && ly <= n.y + NODE_D) {
                    return n;
                }
            }
            return null;
        }
    }

    private static String compactActionKeepingParams(String rawAction) {
        if (rawAction == null) {
            return "";
        }
        String action = rawAction.trim();
        if (action.startsWith("(") && action.endsWith(")") && action.length() > 2) {
            String inside = action.substring(1, action.length() - 1).trim();
            int split = inside.indexOf(' ');
            if (split < 0) {
                return action;
            }
            String op = inside.substring(0, split);
            String params = inside.substring(split + 1).trim();
            String compactOp = abbreviateWord(op);
            return "(" + compactOp + (params.isEmpty() ? "" : " " + params) + ")";
        }
        return action;
    }

    private static String forceCompactOperator(String compactAction) {
        if (compactAction == null || compactAction.isBlank()) {
            return "";
        }
        String action = compactAction.trim();
        if (action.startsWith("(") && action.endsWith(")") && action.length() > 2) {
            String inside = action.substring(1, action.length() - 1).trim();
            int split = inside.indexOf(' ');
            if (split < 0) {
                return action;
            }
            String op = inside.substring(0, split);
            String params = inside.substring(split + 1).trim();
            String tiny = op.length() <= 2 ? op : op.substring(0, Math.min(2, op.length())) + ".";
            return "(" + tiny + (params.isEmpty() ? "" : " " + params) + ")";
        }
        return action;
    }

    private static String abbreviateWord(String word) {
        if (word == null || word.isBlank() || word.length() <= 6) {
            return word;
        }
        StringBuilder out = new StringBuilder();
        out.append(word.charAt(0));
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            boolean vowel = "aeiouAEIOU".indexOf(c) >= 0;
            if (!vowel) {
                out.append(c);
            }
        }
        if (out.length() > 6) {
            out.setLength(6);
        }
        out.append('.');
        return out.toString();
    }
}
