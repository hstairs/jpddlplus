package com.hstairs.enhspgui;

import com.hstairs.enhsp.integration.approx.ApproxPddlTranslator;
import com.hstairs.enhsp.integration.execution.PlanningExecutionController;
import com.hstairs.enhsp.integration.execution.PlanningStoppedException;
import com.hstairs.enhsp.integration.execution.SearchTreeListener;
import com.hstairs.enhsp.integration.format.PlannerOutputParser;
import com.hstairs.enhsp.integration.format.PlanningResultFormatter;
import com.hstairs.enhsp.integration.format.StateTraceFormatter;
import com.hstairs.enhsp.integration.model.PlanActionRef;
import com.hstairs.enhsp.integration.model.PlanTimepointGroup;
import com.hstairs.enhsp.integration.model.PlanningResult;
import com.hstairs.enhsp.integration.model.PlannerCliOptions;
import com.hstairs.enhsp.integration.tree.LazySearchTreeWindow;
import com.hstairs.ppmajal.PDDLProblem.PDDLSolution;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;
import com.hstairs.ppmajal.extraUtils.PlannerExitException;
import enhsp2.ENHSP;
import enhsp2.Planner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.prefs.Preferences;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PlanningWorkbench {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlanningFrame frame = new PlanningFrame();
            frame.setVisible(true);
        });
    }

        private static final class PlanningFrame extends JFrame {
            private static final Object STDOUT_REDIRECT_LOCK = new Object();
            private static final int MAX_RAW_OUTPUT_CHARS = 500_000;
            private static final String VSCODE_INTEGRATION_TITLE = "VS Code Integration";
            private static final String VSCODE_ENABLE_MESSAGE =
                    "Enable 'Edit > VS Code External Editing (Experimental)' first.";
        private final LispSyntaxTextPane domainArea;
        private final LispSyntaxTextPane problemArea;
        private final DefaultListModel<String> planListModel;
        private final JList<String> planList;
        private final JButton viewStateButton;
        private final JButton viewPlanGraphButton;
        private final JTextArea statsArea;
        private final JTextField quickTimeoutField;
        private final JButton runButton;
        private final JButton pauseButton;
        private final JButton stopButton;
        private JMenuItem editDomainInVsCodeMenuItem;
        private JMenuItem editProblemInVsCodeMenuItem;
        private JMenuItem reloadFromVsCodeMenuItem;
        private JMenuItem setTreeWidthLimitMenuItem;
        private JCheckBoxMenuItem vsCodeToggleMenuItem;
        private JCheckBoxMenuItem vsCodeAutoReloadToggleMenuItem;
        private boolean vsCodeIntegrationEnabled = false;
        private boolean vsCodeAutoReloadEnabled = false;
        private final Timer vsCodeReloadTimer;
        private final Timer vsCodePushTimer;
        private Path vsCodeIntegrationDir;
        private Path vsCodeDomainFile;
        private Path vsCodeProblemFile;
        private Path currentDomainFile;
        private Path currentProblemFile;
        private volatile Path lastSearchJsonPath;
        private HttpServer sjrFileServer;
        private int sjrFileServerPort = -1;
        private volatile Path sjrServedPath;
        private long vsCodeDomainLastModified = -1L;
        private long vsCodeProblemLastModified = -1L;
        private boolean suppressEditorDirtyTracking = false;
        private boolean domainDirtySinceVsCodeSync = false;
        private boolean problemDirtySinceVsCodeSync = false;
        private AiAssistantDialog aiAssistantDialog;
        private SwingWorker<PlanningResult, Void> currentWorker;
        private PlanningExecutionController executionController;
        private volatile Thread planningThread;
        private boolean pauseRequested;
        private int editorFontSize = 13;
        private final StringBuilder liveStatsBuffer = new StringBuilder();
        private final StringBuilder rawOutputBuffer = new StringBuilder();
        private PlannerCliOptions plannerOptions = PlannerCliOptions.defaults();
        private PlanningResult latestPlanningResult;
        private LazySearchTreeWindow searchTreeWindow;
        private boolean showSearchTree = false;
        private boolean liveSearchTree = false;
        private boolean debugModeEnabled = false;
        private Path lastLoadedDirectory = Path.of(System.getProperty("user.home"));

        PlanningFrame() {
            super("ENHSP-25 (Expressive Numeric Heuristic Search Planner) GUI");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(1200, 800);
            setLocationRelativeTo(null);
            vsCodeReloadTimer = new Timer(1200, e -> maybeAutoReloadFromVsCode());
            vsCodeReloadTimer.setRepeats(true);
            vsCodeReloadTimer.start();
            vsCodePushTimer = new Timer(450, e -> maybeAutoPushToVsCode());
            vsCodePushTimer.setRepeats(false);

            applyModernTheme();
            JPanel root = new JPanel(new BorderLayout(10, 10));
            root.setBorder(new EmptyBorder(12, 12, 12, 12));
            root.setBackground(new Color(246, 248, 252));
            setContentPane(root);
            setJMenuBar(buildMenuBar());

            JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
            controls.setOpaque(true);
            controls.setBackground(new Color(255, 255, 255));
            controls.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 225, 234)),
                    new EmptyBorder(6, 8, 6, 8)
            ));
            JButton plannerOptionsButton = new JButton("Planner Options...");
            plannerOptionsButton.addActionListener(e -> openPlannerOptionsDialog());
            controls.add(plannerOptionsButton);
            runButton = new JButton("Run Planning");
            runButton.addActionListener(e -> runPlanning());
            styleActionButton(runButton, new Color(20, 145, 60), Color.WHITE);
            controls.add(runButton);
            controls.add(new JLabel("Timeout (s):"));
            quickTimeoutField = new JTextField(6);
            quickTimeoutField.setToolTipText("Empty = infinity");
            controls.add(quickTimeoutField);
            pauseButton = new JButton("Pause");
            pauseButton.addActionListener(e -> togglePausePlanning());
            pauseButton.setEnabled(false);
            styleActionButton(pauseButton, new Color(210, 145, 0), Color.BLACK);
            controls.add(pauseButton);
            stopButton = new JButton("Stop");
            stopButton.addActionListener(e -> stopPlanning());
            stopButton.setEnabled(false);
            styleActionButton(stopButton, new Color(180, 35, 35), Color.WHITE);
            controls.add(stopButton);
            root.add(controls, BorderLayout.NORTH);

            JLabel domainStatus = new JLabel(" ");
            domainArea = new LispSyntaxTextPane(EditorKind.DOMAIN, defaultDomain(), report -> updateStatus(domainStatus, report));

            JLabel problemStatus = new JLabel(" ");
            problemArea = new LispSyntaxTextPane(EditorKind.PROBLEM, defaultProblem(), report -> updateStatus(problemStatus, report));
            domainArea.setAutocompleteEnabled(true);
            problemArea.setAutocompleteEnabled(true);
            installVsCodeDirtyTracking();

            JSplitPane editors = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    wrapped("Domain", editorScrollWithLineNumbers(domainArea), domainStatus),
                    wrapped("Problem", editorScrollWithLineNumbers(problemArea), problemStatus)
            );
            editors.setResizeWeight(0.6);
            styleSplitPane(editors);

            planListModel = new DefaultListModel<>();
            planList = new JList<>(planListModel);
            planList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            planList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            viewStateButton = new JButton("View State For Selected Step");
            viewStateButton.setEnabled(false);
            viewStateButton.addActionListener(e -> showSelectedStepStatePopup());
            viewPlanGraphButton = new JButton("View Plan Graph");
            viewPlanGraphButton.setEnabled(false);
            viewPlanGraphButton.addActionListener(e -> showPlanGraphDialog());
            JPanel planPanel = new JPanel(new BorderLayout());
            planPanel.add(new JScrollPane(planList), BorderLayout.CENTER);
            JPanel planBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
            planBottom.add(viewStateButton);
            planBottom.add(viewPlanGraphButton);
            planPanel.add(planBottom, BorderLayout.SOUTH);
            statsArea = new JTextArea();
            statsArea.setEditable(false);
            statsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

            JSplitPane resultSplit = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    wrapped("Plan", planPanel, null),
                    wrapped("Statistics and raw output", new JScrollPane(statsArea), null)
            );
            resultSplit.setResizeWeight(0.7);
            styleSplitPane(resultSplit);

            JSplitPane vertical = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    editors,
                    resultSplit
            );
            vertical.setResizeWeight(0.66);
            styleSplitPane(vertical);
            root.add(vertical, BorderLayout.CENTER);
            bindZoomShortcuts(root);
        }

        private JMenuBar buildMenuBar() {
            JMenuBar bar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");
            JMenuItem loadDomain = new JMenuItem("Load Domain...");
            loadDomain.addActionListener(e -> loadDomainFile());
            fileMenu.add(loadDomain);
            JMenuItem loadProblem = new JMenuItem("Load Problem...");
            loadProblem.addActionListener(e -> loadProblemFile());
            fileMenu.add(loadProblem);
            JMenuItem saveDomain = new JMenuItem("Save Domain");
            saveDomain.addActionListener(e -> saveDomainFile());
            fileMenu.add(saveDomain);
            JMenuItem saveDomainAs = new JMenuItem("Save Domain As...");
            saveDomainAs.addActionListener(e -> saveDomainFileAs());
            fileMenu.add(saveDomainAs);
            JMenuItem saveProblem = new JMenuItem("Save Problem");
            saveProblem.addActionListener(e -> saveProblemFile());
            fileMenu.add(saveProblem);
            JMenuItem saveProblemAs = new JMenuItem("Save Problem As...");
            saveProblemAs.addActionListener(e -> saveProblemFileAs());
            fileMenu.add(saveProblemAs);
            bar.add(fileMenu);

            JMenu editMenu = new JMenu("Edit");
            JMenuItem formatDomain = new JMenuItem("Format Domain");
            formatDomain.addActionListener(e -> formatDomainEditor());
            editMenu.add(formatDomain);
            JMenuItem formatProblem = new JMenuItem("Format Problem");
            formatProblem.addActionListener(e -> formatProblemEditor());
            editMenu.add(formatProblem);
            editMenu.addSeparator();
            editDomainInVsCodeMenuItem = new JMenuItem("Edit Domain in VS Code (Experimental)");
            editDomainInVsCodeMenuItem.addActionListener(e -> openDomainInVsCode());
            editMenu.add(editDomainInVsCodeMenuItem);
            editProblemInVsCodeMenuItem = new JMenuItem("Edit Problem in VS Code (Experimental)");
            editProblemInVsCodeMenuItem.addActionListener(e -> openProblemInVsCode());
            editMenu.add(editProblemInVsCodeMenuItem);
            reloadFromVsCodeMenuItem = new JMenuItem("Reload From VS Code Files (Experimental)");
            reloadFromVsCodeMenuItem.addActionListener(e -> reloadFromVsCodeFiles());
            editMenu.add(reloadFromVsCodeMenuItem);
            vsCodeToggleMenuItem = new JCheckBoxMenuItem("VS Code External Editing (Experimental)", vsCodeIntegrationEnabled);
            vsCodeToggleMenuItem.addActionListener(e -> {
                setVsCodeIntegrationEnabled(vsCodeToggleMenuItem.isSelected());
            });
            editMenu.add(vsCodeToggleMenuItem);
            vsCodeAutoReloadToggleMenuItem = new JCheckBoxMenuItem("VS Code Auto-reload (Experimental)", vsCodeAutoReloadEnabled);
            vsCodeAutoReloadToggleMenuItem.addActionListener(e -> vsCodeAutoReloadEnabled = vsCodeAutoReloadToggleMenuItem.isSelected());
            editMenu.add(vsCodeAutoReloadToggleMenuItem);
            updateVsCodeMenuState();
            bar.add(editMenu);

            JMenu examplesMenu = new JMenu("Examples");
            JMenuItem countersExample = new JMenuItem("Counters (Instance 8)");
            countersExample.addActionListener(e -> loadExamplePair(
                    Path.of("examples", "gui", "counters", "domain.pddl"),
                    Path.of("examples", "gui", "counters", "problem_instance_8.pddl"),
                    "Counters (Instance 8)"
            ));
            examplesMenu.add(countersExample);
            JMenuItem blocksworldExample = new JMenuItem("Blocksworld (3 Blocks)");
            blocksworldExample.addActionListener(e -> loadExamplePair(
                    Path.of("examples", "gui", "blocksworld", "domain.pddl"),
                    Path.of("examples", "gui", "blocksworld", "problem_3blocks.pddl"),
                    "Blocksworld (3 Blocks)"
            ));
            examplesMenu.add(blocksworldExample);
            JMenuItem sailingExample = new JMenuItem("Sailing (2-3)");
            sailingExample.addActionListener(e -> loadExamplePair(
                    Path.of("examples", "sailing", "domain.pddl"),
                    Path.of("examples", "sailing", "small_instances", "instance_2_3_1229.pddl"),
                    "Sailing (2-3)"
            ));
            examplesMenu.add(sailingExample);
            JMenuItem carExample = new JMenuItem("Car Non-Linear (1)");
            carExample.addActionListener(e -> loadExamplePair(
                    Path.of("examples", "pddl+", "car_non_linear", "domain.pddl"),
                    Path.of("examples", "pddl+", "car_non_linear", "instances", "instance_1_30.0_0.1_10.0.pddl"),
                    "Car Non-Linear (1)"
            ));
            examplesMenu.add(carExample);
            bar.add(examplesMenu);

            JMenu configMenu = new JMenu("Config");
            JCheckBoxMenuItem debugModeToggle = new JCheckBoxMenuItem("Debug Mode", debugModeEnabled);
            debugModeToggle.addActionListener(e -> debugModeEnabled = debugModeToggle.isSelected());
            configMenu.add(debugModeToggle);
            configMenu.addSeparator();
            JMenuItem zoomIn = new JMenuItem("Zoom In");
            zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            zoomIn.addActionListener(e -> zoomEditors(1));
            configMenu.add(zoomIn);
            JMenuItem zoomOut = new JMenuItem("Zoom Out");
            zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            zoomOut.addActionListener(e -> zoomEditors(-1));
            configMenu.add(zoomOut);
            JMenuItem zoomReset = new JMenuItem("Reset Zoom");
            zoomReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            zoomReset.addActionListener(e -> resetEditorZoom());
            configMenu.add(zoomReset);
            configMenu.addSeparator();
            final boolean[] syncingTreeToggles = {false};
            JCheckBoxMenuItem treeView = new JCheckBoxMenuItem("Search Tree", false);
            JCheckBoxMenuItem treeLiveView = new JCheckBoxMenuItem("Search Tree Live (slow)", false);
            treeView.addActionListener(e -> {
                if (syncingTreeToggles[0]) {
                    return;
                }
                showSearchTree = treeView.isSelected();
                if (showSearchTree) {
                    plannerOptions.saveSearchJson = true;
                    LazySearchTreeWindow window = ensureSearchTreeWindow();
                    window.setLiveJsonMode(liveSearchTree);
                    window.showWindow();
                } else {
                    if (liveSearchTree) {
                        syncingTreeToggles[0] = true;
                        liveSearchTree = false;
                        treeLiveView.setSelected(false);
                        syncingTreeToggles[0] = false;
                    }
                    if (searchTreeWindow != null) {
                        searchTreeWindow.hideWindow();
                    }
                }
            });
            configMenu.add(treeView);
            treeLiveView.addActionListener(e -> {
                if (syncingTreeToggles[0]) {
                    return;
                }
                liveSearchTree = treeLiveView.isSelected();
                if (liveSearchTree && !showSearchTree) {
                    syncingTreeToggles[0] = true;
                    showSearchTree = true;
                    treeView.setSelected(true);
                    syncingTreeToggles[0] = false;
                    plannerOptions.saveSearchJson = true;
                    LazySearchTreeWindow window = ensureSearchTreeWindow();
                    window.setLiveJsonMode(true);
                    window.showWindow();
                }
                if (searchTreeWindow != null) {
                    searchTreeWindow.setLiveJsonMode(liveSearchTree);
                }
            });
            configMenu.add(treeLiveView);
            setTreeWidthLimitMenuItem = new JMenuItem("Set Tree Width Limit... (Disabled)");
            setTreeWidthLimitMenuItem.setEnabled(false);
            setTreeWidthLimitMenuItem.addActionListener(e -> {
                JOptionPane.showMessageDialog(
                        this,
                        "Tree width limit is temporarily disabled.",
                        "Search Tree",
                        JOptionPane.INFORMATION_MESSAGE
                );
            });
            configMenu.add(setTreeWidthLimitMenuItem);
            JMenuItem openSjrInBrowser = new JMenuItem("Open Last -sjr Tree in ENHSPTree");
            openSjrInBrowser.addActionListener(e -> openLastSjrTreeInBrowser());
            configMenu.add(openSjrInBrowser);
            bar.add(configMenu);

            JMenu syntaxMenu = new JMenu("Syntax");
            JMenuItem syntaxHelp = new JMenuItem("Help");
            syntaxHelp.addActionListener(e -> showSyntaxHelp());
            syntaxMenu.add(syntaxHelp);
            bar.add(syntaxMenu);

            return bar;
        }

        private void showSyntaxHelp() {
            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            area.setText(
                    "Quick Guide: Modeling in PDDL\n" +
                    "=============================\n\n" +
                    "1) DOMAIN structure\n" +
                    "-------------------\n" +
                    "- (define (domain DOMAIN_NAME))\n" +
                    "- (:requirements ...)\n" +
                    "- (:predicates ...)\n" +
                    "- (:functions ...)   ; if you use numeric fluents\n" +
                    "- (:action ...)\n" +
                    "  :parameters (...)\n" +
                    "  :precondition (and ...)\n" +
                    "  :effect (and ...)\n\n" +
                    "2) PROBLEM structure\n" +
                    "--------------------\n" +
                    "- (define (problem PROBLEM_NAME))\n" +
                    "- (:domain DOMAIN_NAME)\n" +
                    "- (:objects ...)\n" +
                    "- (:init ...)\n" +
                    "- (:goal (and ...))\n" +
                    "- (:metric minimize|maximize (...))   ; optional\n\n" +
                    "3) Modeling best practices\n" +
                    "--------------------------\n" +
                    "- What goes in DOMAIN: reusable world dynamics (actions/processes/events),\n" +
                    "  predicates/functions, and constraints shared across instances.\n" +
                    "- What goes in PROBLEM: concrete objects, initial state, goals, and metric\n" +
                    "  for the specific instance you want to solve.\n" +
                    "- Keep boolean facts (:predicates) separate from numeric quantities (:functions).\n" +
                    "- Put all initial facts and numeric assignments in :init using (= (f ...) val).\n" +
                    "- Keep preconditions as local as possible to each action.\n" +
                    "- In :goal, use conditions that are checkable in the final state.\n\n" +
                    "Friendly syntax (~PDDL) in this GUI\n" +
                    "===================================\n" +
                    "This GUI supports a more natural notation for numeric expressions.\n" +
                    "You can write hybrid PDDL / ~PDDL input: standard PDDL remains unchanged,\n" +
                    "while friendly fragments are automatically translated into pure PDDL.\n\n" +
                    "Main rules\n" +
                    "----------\n" +
                    "1) Infix expressions\n" +
                    "   (x + y * 2), (a - b), (n / d)\n" +
                    "   -> translated to prefix PDDL form.\n\n" +
                    "2) Natural function calls\n" +
                    "   fuel()     -> (fuel)\n" +
                    "   weight(a)  -> (weight a)\n" +
                    "   dist(x,y)  -> (dist x y)\n\n" +
                    "3) Infix comparisons\n" +
                    "   (weight(a) <= grip-limit()) -> (<= (weight a) (grip-limit))\n" +
                    "   (fuel() > 0)                -> (> (fuel) 0)\n\n" +
                    "4) Assignments in :effect\n" +
                    "   (fuel() = fuel() + 2) -> (increase (fuel) 2)\n" +
                    "   (fuel() = fuel() - 1) -> (decrease (fuel) 1)\n" +
                    "   (x() = y())           -> (assign (x) (y))\n\n" +
                    "5) Boolean assignments\n" +
                    "   (ready() = T) -> (ready)\n" +
                    "   (ready() = F) -> (not (ready))\n\n" +
                    "Note\n" +
                    "----\n" +
                    "For complex or deeply nested cases, prefer standard PDDL directly.\n"
            );
            JScrollPane sp = new JScrollPane(area);
            sp.setPreferredSize(new Dimension(820, 560));
            JOptionPane.showMessageDialog(this, sp, "Syntax Help", JOptionPane.INFORMATION_MESSAGE);
        }

        private void styleActionButton(JButton button, Color background, Color foreground) {
            button.setBackground(background);
            button.setForeground(foreground);
            button.setOpaque(true);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(background.darker()),
                    new EmptyBorder(6, 12, 6, 12)
            ));
        }

        private void bindZoomShortcuts(JComponent root) {
            InputMap inputMap = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = root.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "zoom-in");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "zoom-in");
            actionMap.put("zoom-in", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    zoomEditors(1);
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "zoom-out");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "zoom-out");
            actionMap.put("zoom-out", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    zoomEditors(-1);
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "zoom-reset");
            actionMap.put("zoom-reset", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetEditorZoom();
                }
            });
        }

        private void updateStatus(JLabel label, SyntaxReport report) {
            label.setText(report.message);
            label.setForeground(report.ok ? new Color(0, 120, 0) : new Color(180, 30, 30));
        }

        private JPanel wrapped(String title, JComponent center, JLabel footer) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setOpaque(true);
            panel.setBackground(new Color(255, 255, 255));
            panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 225, 234)),
                    new EmptyBorder(8, 8, 8, 8)
            ));
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            titleLabel.setBorder(new EmptyBorder(0, 0, 6, 0));
            titleLabel.setForeground(new Color(45, 60, 85));
            panel.add(titleLabel, BorderLayout.NORTH);
            panel.add(center, BorderLayout.CENTER);
            if (footer != null) {
                footer.setBorder(new EmptyBorder(4, 6, 4, 6));
                panel.add(footer, BorderLayout.SOUTH);
            }
            return panel;
        }

        private JScrollPane editorScrollWithLineNumbers(JTextPane editor) {
            JScrollPane scrollPane = new JScrollPane(editor);
            LineNumberView lineNumbers = new LineNumberView(editor);
            scrollPane.setRowHeaderView(lineNumbers);
            return scrollPane;
        }

        private void installVsCodeDirtyTracking() {
            domainArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    markDirty();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    markDirty();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    markDirty();
                }

                private void markDirty() {
                    if (!suppressEditorDirtyTracking) {
                        domainDirtySinceVsCodeSync = true;
                        scheduleAutoPushToVsCode();
                    }
                }
            });

            problemArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    markDirty();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    markDirty();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    markDirty();
                }

                private void markDirty() {
                    if (!suppressEditorDirtyTracking) {
                        problemDirtySinceVsCodeSync = true;
                        scheduleAutoPushToVsCode();
                    }
                }
            });
        }

        private void scheduleAutoPushToVsCode() {
            if (!vsCodeIntegrationEnabled) {
                return;
            }
            if (suppressEditorDirtyTracking) {
                return;
            }
            if (vsCodePushTimer.isRunning()) {
                vsCodePushTimer.restart();
            } else {
                vsCodePushTimer.start();
            }
        }

        private void maybeAutoPushToVsCode() {
            if (!vsCodeIntegrationEnabled) {
                return;
            }
            if (!hasLocalUnsyncedEditorChanges()) {
                return;
            }
            try {
                syncEditorsToVsCodeFiles();
            } catch (IOException ignored) {
            }
        }

        private boolean hasLocalUnsyncedEditorChanges() {
            return domainDirtySinceVsCodeSync || problemDirtySinceVsCodeSync;
        }

        private void markEditorsSyncedWithVsCode() {
            domainDirtySinceVsCodeSync = false;
            problemDirtySinceVsCodeSync = false;
        }

        private Path loadIntoEditor(JTextPane editor) {
            Path file = chooseOpenPath("Load PDDL file");
            if (file == null) {
                return null;
            }
            try {
                suppressEditorDirtyTracking = true;
                try {
                    editor.setText(Files.readString(file, StandardCharsets.UTF_8));
                } finally {
                    suppressEditorDirtyTracking = false;
                }
                Path parent = file.getParent();
                if (parent != null) {
                    lastLoadedDirectory = parent;
                }
                if (vsCodeIntegrationEnabled) {
                    syncEditorsToVsCodeFiles();
                } else {
                    markEditorsSyncedWithVsCode();
                }
                return file;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Cannot load file:\n" + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        private void styleSplitPane(JSplitPane splitPane) {
            splitPane.setContinuousLayout(true);
            splitPane.setBorder(BorderFactory.createEmptyBorder());
            splitPane.setDividerSize(8);
            splitPane.setBackground(new Color(246, 248, 252));
        }

        private void applyModernTheme() {
            UIManager.put("Panel.background", new Color(246, 248, 252));
            UIManager.put("ScrollPane.border", BorderFactory.createLineBorder(new Color(220, 225, 234)));
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(210, 216, 228)),
                    new EmptyBorder(4, 6, 4, 6)
            ));
        }

        private void loadDomainFile() {
            Path loaded = loadIntoEditor(domainArea);
            if (loaded != null) {
                currentDomainFile = loaded;
            }
        }

        private void loadExamplePair(Path domainPath, Path problemPath, String label) {
            try {
                if (!Files.exists(domainPath) || !Files.exists(problemPath)) {
                    JOptionPane.showMessageDialog(this,
                            "Example files not found:\n" + domainPath + "\n" + problemPath,
                            "Examples",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                suppressEditorDirtyTracking = true;
                try {
                    domainArea.setText(Files.readString(domainPath, StandardCharsets.UTF_8));
                    problemArea.setText(Files.readString(problemPath, StandardCharsets.UTF_8));
                } finally {
                    suppressEditorDirtyTracking = false;
                }
                currentDomainFile = domainPath;
                currentProblemFile = problemPath;
                Path parent = domainPath.getParent();
                if (parent != null) {
                    lastLoadedDirectory = parent;
                }
                markEditorsSyncedWithVsCode();
                if (vsCodeIntegrationEnabled) {
                    syncEditorsToVsCodeFiles();
                }
                JOptionPane.showMessageDialog(this,
                        "Loaded example: " + label,
                        "Examples",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Unable to load example files:\n" + ex.getMessage(),
                        "Examples",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private void loadProblemFile() {
            Path loaded = loadIntoEditor(problemArea);
            if (loaded != null) {
                currentProblemFile = loaded;
            }
        }

        private void saveDomainFile() {
            if (currentDomainFile == null) {
                saveDomainFileAs();
                return;
            }
            saveEditorToFile(domainArea, currentDomainFile, "Save Domain");
        }

        private void saveProblemFile() {
            if (currentProblemFile == null) {
                saveProblemFileAs();
                return;
            }
            saveEditorToFile(problemArea, currentProblemFile, "Save Problem");
        }

        private void saveDomainFileAs() {
            Path target = chooseSavePath("Save Domain As...", currentDomainFile, "domain.pddl");
            if (target == null) {
                return;
            }
            if (saveEditorToFile(domainArea, target, "Save Domain")) {
                currentDomainFile = target;
            }
        }

        private void saveProblemFileAs() {
            Path target = chooseSavePath("Save Problem As...", currentProblemFile, "problem.pddl");
            if (target == null) {
                return;
            }
            if (saveEditorToFile(problemArea, target, "Save Problem")) {
                currentProblemFile = target;
            }
        }

        private Path chooseSavePath(String title, Path currentFile, String fallbackName) {
            if (isMacOs()) {
                return chooseSavePathWithNativeDialog(title, currentFile, fallbackName);
            }
            return chooseSavePathWithSwing(title, currentFile, fallbackName);
        }

        private Path chooseOpenPath(String title) {
            if (isMacOs()) {
                return chooseOpenPathWithNativeDialog(title);
            }
            return chooseOpenPathWithSwing(title);
        }

        private Path chooseOpenPathWithSwing(String title) {
            JFileChooser chooser = lastLoadedDirectory == null
                    ? new JFileChooser()
                    : new JFileChooser(lastLoadedDirectory.toFile());
            chooser.setDialogTitle(title);
            int res = chooser.showOpenDialog(this);
            if (res != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            Path selected = chooser.getSelectedFile().toPath();
            Path parent = selected.getParent();
            if (parent != null) {
                lastLoadedDirectory = parent;
            }
            return selected;
        }

        private Path chooseSavePathWithSwing(String title, Path currentFile, String fallbackName) {
            JFileChooser chooser = lastLoadedDirectory == null
                    ? new JFileChooser()
                    : new JFileChooser(lastLoadedDirectory.toFile());
            chooser.setDialogTitle(title);
            if (currentFile != null) {
                chooser.setSelectedFile(currentFile.toFile());
            } else {
                chooser.setSelectedFile(Path.of(fallbackName).toFile());
            }
            int res = chooser.showSaveDialog(this);
            if (res != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            Path selected = chooser.getSelectedFile().toPath();
            Path parent = selected.getParent();
            if (parent != null) {
                lastLoadedDirectory = parent;
            }
            return selected;
        }

        private Path chooseOpenPathWithNativeDialog(String title) {
            FileDialog dialog = new FileDialog(this, title, FileDialog.LOAD);
            if (lastLoadedDirectory != null) {
                dialog.setDirectory(lastLoadedDirectory.toAbsolutePath().toString());
            }
            dialog.setVisible(true);
            String file = dialog.getFile();
            String directory = dialog.getDirectory();
            if (file == null || directory == null) {
                return null;
            }
            Path selected = Path.of(directory, file);
            Path parent = selected.getParent();
            if (parent != null) {
                lastLoadedDirectory = parent;
            }
            return selected;
        }

        private Path chooseSavePathWithNativeDialog(String title, Path currentFile, String fallbackName) {
            FileDialog dialog = new FileDialog(this, title, FileDialog.SAVE);
            Path initialDir = currentFile != null ? currentFile.getParent() : lastLoadedDirectory;
            if (initialDir != null) {
                dialog.setDirectory(initialDir.toAbsolutePath().toString());
            }
            dialog.setFile(currentFile != null ? currentFile.getFileName().toString() : fallbackName);
            dialog.setVisible(true);
            String file = dialog.getFile();
            String directory = dialog.getDirectory();
            if (file == null || directory == null) {
                return null;
            }
            Path selected = Path.of(directory, file);
            Path parent = selected.getParent();
            if (parent != null) {
                lastLoadedDirectory = parent;
            }
            return selected;
        }

        private boolean isMacOs() {
            return System.getProperty("os.name", "").toLowerCase().contains("mac");
        }

        private boolean saveEditorToFile(JTextPane editor, Path target, String actionName) {
            try {
                Files.writeString(target, editor.getText(), StandardCharsets.UTF_8);
                if (vsCodeIntegrationEnabled) {
                    syncEditorsToVsCodeFiles();
                }
                return true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        actionName + " failed:\n" + e.getMessage(),
                        actionName + " Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        private void formatDomainEditor() {
            domainArea.formatDocument();
        }

        private void formatProblemEditor() {
            problemArea.formatDocument();
        }

        private void setVsCodeIntegrationEnabled(boolean enabled) {
            vsCodeIntegrationEnabled = enabled;
            if (!enabled) {
                vsCodeAutoReloadEnabled = false;
                if (vsCodeAutoReloadToggleMenuItem != null) {
                    vsCodeAutoReloadToggleMenuItem.setSelected(false);
                }
                if (vsCodePushTimer.isRunning()) {
                    vsCodePushTimer.stop();
                }
                updateVsCodeMenuState();
                return;
            }
            try {
                ensureVsCodeFiles();
                syncEditorsToVsCodeFiles();
            } catch (IOException e) {
                vsCodeIntegrationEnabled = false;
                vsCodeAutoReloadEnabled = false;
                if (vsCodeAutoReloadToggleMenuItem != null) {
                    vsCodeAutoReloadToggleMenuItem.setSelected(false);
                }
                JOptionPane.showMessageDialog(this,
                        "Cannot enable VS Code integration:\n" + e.getMessage(),
                        "VS Code Integration Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (vsCodeToggleMenuItem != null && vsCodeToggleMenuItem.isSelected() != vsCodeIntegrationEnabled) {
                vsCodeToggleMenuItem.setSelected(vsCodeIntegrationEnabled);
            }
            updateVsCodeMenuState();
        }

        private void updateVsCodeMenuState() {
            boolean enabled = vsCodeIntegrationEnabled;
            if (editDomainInVsCodeMenuItem != null) {
                editDomainInVsCodeMenuItem.setEnabled(enabled);
            }
            if (editProblemInVsCodeMenuItem != null) {
                editProblemInVsCodeMenuItem.setEnabled(enabled);
            }
            if (reloadFromVsCodeMenuItem != null) {
                reloadFromVsCodeMenuItem.setEnabled(enabled);
            }
            if (vsCodeAutoReloadToggleMenuItem != null) {
                vsCodeAutoReloadToggleMenuItem.setEnabled(enabled);
            }
        }

        private void ensureVsCodeFiles() throws IOException {
            if (vsCodeIntegrationDir == null) {
                vsCodeIntegrationDir = Files.createTempDirectory("jpddlplus_vscode_");
                vsCodeDomainFile = vsCodeIntegrationDir.resolve("domain.pddl");
                vsCodeProblemFile = vsCodeIntegrationDir.resolve("problem.pddl");
                writeVsCodeIntegrationSettings();
            }
            if (vsCodeDomainFile == null || vsCodeProblemFile == null) {
                throw new IOException("Invalid VS Code integration paths.");
            }
            if (!Files.exists(vsCodeDomainFile)) {
                Files.writeString(vsCodeDomainFile, domainArea.getText(), StandardCharsets.UTF_8);
            }
            if (!Files.exists(vsCodeProblemFile)) {
                Files.writeString(vsCodeProblemFile, problemArea.getText(), StandardCharsets.UTF_8);
            }
            updateVsCodeModifiedTimestamps();
        }

        private void writeVsCodeIntegrationSettings() throws IOException {
            if (vsCodeIntegrationDir == null) {
                return;
            }
            Path vscodeDir = vsCodeIntegrationDir.resolve(".vscode");
            Files.createDirectories(vscodeDir);
            Path settings = vscodeDir.resolve("settings.json");
            String json = "{\n"
                    + "  \"files.autoSave\": \"afterDelay\",\n"
                    + "  \"files.autoSaveDelay\": 400\n"
                    + "}\n";
            Files.writeString(settings, json, StandardCharsets.UTF_8);
        }

        private void syncEditorsToVsCodeFiles() throws IOException {
            ensureVsCodeFiles();
            String transpiledDomain;
            String transpiledProblem;
            try {
                transpiledDomain = ApproxPddlTranslator.transpile(domainArea.getText(), true);
                transpiledProblem = ApproxPddlTranslator.transpile(problemArea.getText(), false);
            } catch (Exception e) {
                throw new IOException("Unable to transpile current editors to standard PDDL for VS Code.", e);
            }
            Files.writeString(vsCodeDomainFile, transpiledDomain, StandardCharsets.UTF_8);
            Files.writeString(vsCodeProblemFile, transpiledProblem, StandardCharsets.UTF_8);
            updateVsCodeModifiedTimestamps();
            markEditorsSyncedWithVsCode();
        }

        private void syncFromVsCodeFilesIntoEditors() throws IOException {
            ensureVsCodeFiles();
            String domainText = Files.readString(vsCodeDomainFile, StandardCharsets.UTF_8);
            String problemText = Files.readString(vsCodeProblemFile, StandardCharsets.UTF_8);
            suppressEditorDirtyTracking = true;
            try {
                if (!domainText.equals(domainArea.getText())) {
                    domainArea.setText(domainText);
                }
                if (!problemText.equals(problemArea.getText())) {
                    problemArea.setText(problemText);
                }
            } finally {
                suppressEditorDirtyTracking = false;
            }
            updateVsCodeModifiedTimestamps();
            markEditorsSyncedWithVsCode();
        }

        private void maybeAutoReloadFromVsCode() {
            if (!vsCodeIntegrationEnabled || !vsCodeAutoReloadEnabled) {
                return;
            }
            if (vsCodeDomainFile == null || vsCodeProblemFile == null) {
                return;
            }
            try {
                long domainMTime = Files.exists(vsCodeDomainFile) ? Files.getLastModifiedTime(vsCodeDomainFile).toMillis() : -1L;
                long problemMTime = Files.exists(vsCodeProblemFile) ? Files.getLastModifiedTime(vsCodeProblemFile).toMillis() : -1L;
                boolean changed = domainMTime > vsCodeDomainLastModified || problemMTime > vsCodeProblemLastModified;
                if (changed) {
                    if (hasLocalUnsyncedEditorChanges()) {
                        maybeAutoPushToVsCode();
                        return;
                    }
                    syncFromVsCodeFilesIntoEditors();
                }
            } catch (IOException ignored) {
            }
        }

        private void updateVsCodeModifiedTimestamps() throws IOException {
            vsCodeDomainLastModified = Files.exists(vsCodeDomainFile) ? Files.getLastModifiedTime(vsCodeDomainFile).toMillis() : -1L;
            vsCodeProblemLastModified = Files.exists(vsCodeProblemFile) ? Files.getLastModifiedTime(vsCodeProblemFile).toMillis() : -1L;
        }

        private void openDomainInVsCode() {
            openInVsCode(true);
        }

        private void openProblemInVsCode() {
            openInVsCode(false);
        }

        private void reloadFromVsCodeFiles() {
            if (!vsCodeIntegrationEnabled) {
                showVsCodeIntegrationDisabledMessage();
                return;
            }
            try {
                if (hasLocalUnsyncedEditorChanges()) {
                    int choice = JOptionPane.showConfirmDialog(
                            this,
                            "You have local unsynced edits in GUI.\nReloading from VS Code will overwrite them.\nContinue?",
                            "Reload From VS Code",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (choice != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                syncFromVsCodeFilesIntoEditors();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Unable to reload from VS Code files:\n" + e.getMessage(),
                        "VS Code Integration Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private void openInVsCode(boolean domain) {
            if (!vsCodeIntegrationEnabled) {
                showVsCodeIntegrationDisabledMessage();
                return;
            }
            try {
                syncEditorsToVsCodeFiles();
                Path target = domain ? vsCodeDomainFile : vsCodeProblemFile;
                if (!launchVsCode(target)) {
                    JOptionPane.showMessageDialog(this,
                            "Unable to launch VS Code automatically.\n"
                                    + "Install VS Code and ensure 'code' is available in PATH.",
                            "VS Code Launch Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Unable to launch VS Code. Ensure 'code' is in PATH.\n\n" + e.getMessage(),
                        "VS Code Launch Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private void showVsCodeIntegrationDisabledMessage() {
            JOptionPane.showMessageDialog(
                    this,
                    VSCODE_ENABLE_MESSAGE,
                    VSCODE_INTEGRATION_TITLE,
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        private boolean launchVsCode(Path target) {
            String targetPath = target.toAbsolutePath().toString();
            String folderPath = vsCodeIntegrationDir == null
                    ? target.getParent().toAbsolutePath().toString()
                    : vsCodeIntegrationDir.toAbsolutePath().toString();
            List<List<String>> commands = new ArrayList<>();
            commands.add(Arrays.asList("code", folderPath, "-g", targetPath));
            String os = System.getProperty("os.name", "").toLowerCase();
            if (os.contains("mac")) {
                commands.add(Arrays.asList("open", "-a", "Visual Studio Code", folderPath));
            } else if (os.contains("win")) {
                commands.add(Arrays.asList("cmd", "/c", "code", folderPath, "-g", targetPath));
            }
            for (List<String> cmd : commands) {
                try {
                    Process p = new ProcessBuilder(cmd).start();
                    if (p.isAlive() || p.exitValue() == 0) {
                        return true;
                    }
                } catch (IllegalThreadStateException okStillRunning) {
                    return true;
                } catch (IOException ignored) {
                }
            }
            return false;
        }

        private void zoomEditors(int delta) {
            editorFontSize = Math.max(9, Math.min(42, editorFontSize + delta));
            Font font = new Font(Font.MONOSPACED, Font.PLAIN, editorFontSize);
            domainArea.setFont(font);
            problemArea.setFont(font);
            if (searchTreeWindow != null) {
                searchTreeWindow.setFontSize(editorFontSize);
            }
        }

        private void resetEditorZoom() {
            editorFontSize = 13;
            zoomEditors(0);
        }

        private LazySearchTreeWindow ensureSearchTreeWindow() {
            if (searchTreeWindow == null) {
                searchTreeWindow = new LazySearchTreeWindow();
            }
            return searchTreeWindow;
        }

        private SearchTreeListener createSearchTreeListener(LazySearchTreeWindow treeWindow) {
            if (treeWindow == null) {
                return null;
            }
            return new SearchTreeListener() {
                @Override
                public void onSearchStart() {
                    treeWindow.onSearchStart();
                }

                @Override
                public void onLogEvent(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node,
                                       com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType logType,
                                       boolean isGoal) {
                    treeWindow.onLogEvent(node, logType, isGoal);
                }

                @Override
                public void onSearchEnd() {
                    treeWindow.onSearchEnd();
                }

                @Override
                public void markSolutionNode(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node) {
                    treeWindow.markSolutionNode(node);
                }
            };
        }

        private void openPlannerOptionsDialog() {
            PlannerOptionsDialog dialog = new PlannerOptionsDialog(this, plannerOptions);
            PlannerCliOptions updated = dialog.showDialog();
            if (updated != null) {
                plannerOptions = updated;
            }
        }

        private void openLastSjrTreeInBrowser() {
            Path p = lastSearchJsonPath;
            if (p == null || !Files.exists(p)) {
                JOptionPane.showMessageDialog(
                        this,
                        "No -sjr search tree file available yet.\nRun planning with '-sjr' enabled in Planner Options first.",
                        "ENHSPTree",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }
            String url = buildEnhspTreeUrlWithAutoFile(p);
            openUrlInBrowser(url);
            try {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(p.toAbsolutePath().toString()), null);
            } catch (Exception ignored) {
            }
            JOptionPane.showMessageDialog(
                    this,
                    "ENHSPTree opened in browser.\nAttempted auto-load via URL parameter.\n"
                            + "If the site does not auto-load, use its 'Load sp.log' control.\n"
                            + "File path copied to clipboard:\n" + p.toAbsolutePath(),
                    "ENHSPTree",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        private String buildEnhspTreeUrlWithAutoFile(Path spLogFile) {
            String base = "https://clementchamayou.github.io/enhsptree/";
            try {
                String localUrl = startOrUpdateSjrFileServer(spLogFile);
                String encoded = URLEncoder.encode(localUrl, StandardCharsets.UTF_8);
                return base + "?url=" + encoded;
            } catch (Exception ignored) {
                return base;
            }
        }

        private String startOrUpdateSjrFileServer(Path spLogFile) throws IOException {
            sjrServedPath = spLogFile.toAbsolutePath();
            if (sjrFileServer != null && sjrFileServerPort > 0) {
                return "http://127.0.0.1:" + sjrFileServerPort + "/last.sp_log";
            }
            sjrFileServer = HttpServer.create(new java.net.InetSocketAddress("127.0.0.1", 0), 0);
            sjrFileServer.createContext("/last.sp_log", this::serveLastSpLog);
            sjrFileServer.setExecutor(null);
            sjrFileServer.start();
            sjrFileServerPort = sjrFileServer.getAddress().getPort();
            return "http://127.0.0.1:" + sjrFileServerPort + "/last.sp_log";
        }

        private void serveLastSpLog(HttpExchange exchange) throws IOException {
            try {
                Path p = sjrServedPath;
                if (p == null || !Files.exists(p)) {
                    byte[] msg = "No sp_log available".getBytes(StandardCharsets.UTF_8);
                    exchange.sendResponseHeaders(404, msg.length);
                    exchange.getResponseBody().write(msg);
                    return;
                }
                byte[] bytes = Files.readAllBytes(p);
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);
            } finally {
                exchange.close();
            }
        }

        private void openAiAssistant() {
            String apiKey = OpenAiApiKeyStore.resolveApiKey();
            if (apiKey == null || apiKey.isBlank()) {
                boolean configured = promptAiIntegrationSetup();
                if (!configured) {
                    return;
                }
            }
            if (aiAssistantDialog == null) {
                aiAssistantDialog = new AiAssistantDialog(this, domainArea, problemArea);
            }
            aiAssistantDialog.setVisible(true);
            aiAssistantDialog.toFront();
        }

        private boolean promptAiIntegrationSetup() {
            JTextField keyField = new JTextField(40);
            JPanel panel = new JPanel(new BorderLayout(8, 8));
            panel.add(new JLabel("Insert OpenAI API key (saved locally for next runs):"), BorderLayout.NORTH);
            panel.add(keyField, BorderLayout.CENTER);
            JButton openKeysPage = new JButton("Open API Keys Page");
            openKeysPage.addActionListener(e -> openUrlInBrowser("https://platform.openai.com/api-keys"));
            JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            south.add(openKeysPage);
            panel.add(south, BorderLayout.SOUTH);

            int res = JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Enable AI Integration",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
            if (res != JOptionPane.OK_OPTION) {
                return false;
            }
            String key = keyField.getText() == null ? "" : keyField.getText().trim();
            if (key.isBlank()) {
                int fallback = JOptionPane.showConfirmDialog(
                        this,
                        "No API key provided.\nOpen ChatGPT instead?",
                        "AI Integration",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );
                if (fallback == JOptionPane.YES_OPTION) {
                    openChatGptInBrowser();
                }
                return false;
            }
            OpenAiApiKeyStore.saveApiKey(key);
            return true;
        }

        private void openChatGptInBrowser() {
            openUrlInBrowser("https://chatgpt.com");
        }

        private void openUrlInBrowser(String url) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(URI.create(url));
                    return;
                }
            } catch (Exception ignored) {
            }
            String os = System.getProperty("os.name", "").toLowerCase();
            List<List<String>> commands = new ArrayList<>();
            if (os.contains("mac")) {
                commands.add(Arrays.asList("open", url));
            } else if (os.contains("win")) {
                commands.add(Arrays.asList("cmd", "/c", "start", url));
            } else {
                commands.add(Arrays.asList("xdg-open", url));
            }
            for (List<String> cmd : commands) {
                try {
                    new ProcessBuilder(cmd).start();
                    return;
                } catch (IOException ignored) {
                }
            }
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open browser automatically.\nOpen this URL manually: " + url,
                    "Open URL",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        private void runPlanning() {
            if (currentWorker != null) {
                return;
            }
            final boolean debugMode = debugModeEnabled;
            final String timeoutOverride;
            String quickTimeout = quickTimeoutField.getText() == null ? "" : quickTimeoutField.getText().trim();
            if (quickTimeout.isEmpty()) {
                timeoutOverride = "";
            } else {
                try {
                    long v = Long.parseLong(quickTimeout);
                    if (v <= 0) {
                        throw new NumberFormatException("timeout must be > 0");
                    }
                    timeoutOverride = Long.toString(v);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Timeout must be a positive integer number of seconds.\nLeave empty for infinity.",
                            "Invalid Timeout",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            final String domainTextForPlanning;
            final String problemTextForPlanning;
            try {
                if (vsCodeIntegrationEnabled) {
                    if (hasLocalUnsyncedEditorChanges()) {
                        syncEditorsToVsCodeFiles();
                    } else {
                        syncFromVsCodeFilesIntoEditors();
                    }
                }
                String d = domainArea.getText();
                String p = problemArea.getText();
                d = ApproxPddlTranslator.transpile(d, true);
                p = ApproxPddlTranslator.transpile(p, false);
                if (debugMode) {
                    boolean confirmed = previewGeneratedPddlAndConfirm(d, p);
                    if (!confirmed) {
                        return;
                    }
                }
                domainTextForPlanning = d;
                problemTextForPlanning = p;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unable to prepare PDDL for planning:\n" + ex.getMessage(),
                        "Preparation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            runButton.setEnabled(false);
            pauseButton.setEnabled(true);
            stopButton.setEnabled(true);
            viewStateButton.setEnabled(false);
            viewPlanGraphButton.setEnabled(false);
            pauseRequested = false;
            pauseButton.setText("Pause");
            latestPlanningResult = null;
            setPlanMessages("Planning in progress...");
            statsArea.setText("Planning in progress...\n\nRaw output:\n");
            synchronized (liveStatsBuffer) {
                liveStatsBuffer.setLength(0);
            }
            synchronized (rawOutputBuffer) {
                rawOutputBuffer.setLength(0);
            }
            executionController = new PlanningExecutionController();
            final boolean searchTreeForThisRun = showSearchTree;
            final boolean liveSearchTreeForThisRun = showSearchTree && liveSearchTree;
            if (showSearchTree) {
                LazySearchTreeWindow treeWindow = ensureSearchTreeWindow();
                treeWindow.reset();
                 treeWindow.setLiveJsonMode(liveSearchTreeForThisRun);
                treeWindow.showWindow();
            }
            executionController.setSearchTreeListener(liveSearchTreeForThisRun ? createSearchTreeListener(searchTreeWindow) : null);

            SwingWorker<PlanningResult, Void> worker = new SwingWorker<>() {
                @Override
                protected PlanningResult doInBackground() {
                    Path tmpDir = null;
                    PrintStream originalOut = null;
                    PrintStream originalErr = null;
                    PrintStream redirectedOut = null;
                    PrintStream redirectedErr = null;
                    try {
                        planningThread = Thread.currentThread();
                        synchronized (STDOUT_REDIRECT_LOCK) {
                            originalOut = System.out;
                            originalErr = System.err;
                            LineCaptureOutputStream lineParser = new LineCaptureOutputStream(PlanningFrame.this::handlePlannerOutputLine);
                            redirectedOut = new PrintStream(new TeeOutputStream(originalOut, lineParser), true, StandardCharsets.UTF_8);
                            redirectedErr = new PrintStream(new TeeOutputStream(originalErr, lineParser), true, StandardCharsets.UTF_8);
                            System.setOut(redirectedOut);
                            System.setErr(redirectedErr);

                            tmpDir = Files.createTempDirectory("jpddlplus_gui_");
                            Path domainPath = tmpDir.resolve("domain.pddl");
                            Path problemPath = tmpDir.resolve("problem.pddl");
                            Files.writeString(domainPath, domainTextForPlanning, StandardCharsets.UTF_8);
                            Files.writeString(problemPath, problemTextForPlanning, StandardCharsets.UTF_8);

                            List<String> args = new ArrayList<>();
                            args.add("-o");
                            args.add(domainPath.toAbsolutePath().toString());
                            args.add("-f");
                            args.add(problemPath.toAbsolutePath().toString());
                            PlannerCliOptions effectivePlannerOptions = plannerOptions.copy();
                            effectivePlannerOptions.timeout = timeoutOverride;
                            if (searchTreeForThisRun) {
                                effectivePlannerOptions.saveSearchJson = true;
                            }
                            effectivePlannerOptions.appendArgs(args);

                            ENHSP planner = new ENHSP(false);
                            injectExternalLogger(planner, executionController);
                            String[] cliArgs = args.toArray(new String[0]);
                            planner.parseInput(cliArgs);
                            planner.configurePlanner();
                            executionController.checkStopped();
                            if (!planner.parsingDomainAndProblem(cliArgs)) {
                                return PlanningResult.error("Failed while parsing/grounding domain and problem.", null, null);
                            }
                            executionController.setGoalCondition(planner.getProblem() == null ? null : planner.getProblem().getGoals());
                            executionController.checkStopped();
                            PDDLSolution solution = planner.planAndGetSolution();
                            if (solution != null && solution.lastNode() != null) {
                                executionController.markSolutionNode(solution.lastNode());
                            }
                            persistLatestSearchJsonIfPresent(tmpDir);
                            executionController.checkStopped();
                            return PlanningResultFormatter.formatSolution(solution, planner.getProblem(),
                                    debugMode ? domainTextForPlanning : null,
                                    debugMode ? problemTextForPlanning : null);
                        }
                    } catch (PlanningStoppedException e) {
                        return PlanningResult.error("Planning stopped by user.", null, null);
                    } catch (PlannerExitException e) {
                        if (executionController != null && executionController.isStopped()) {
                            return PlanningResult.error("Planning stopped by user.", null, null);
                        }
                        String message = e.getMessage() == null ? "Planner terminated unexpectedly." : e.getMessage();
                        return PlanningResult.error("Planning aborted by planner (exit code " + e.exitCode() + "):\n" + message, null, null);
                    } catch (Throwable t) {
                        if (executionController != null && executionController.isStopped()) {
                            return PlanningResult.error("Planning stopped by user.", null, null);
                        }
                        return PlanningResult.error("Planning failed:\n" + t, null, null);
                    } finally {
                        planningThread = null;
                        if (redirectedErr != null) {
                            redirectedErr.flush();
                            redirectedErr.close();
                        }
                        if (redirectedOut != null) {
                            redirectedOut.flush();
                            redirectedOut.close();
                        }
                        if (originalErr != null) {
                            System.setErr(originalErr);
                        }
                        if (originalOut != null) {
                            System.setOut(originalOut);
                        }
                        if (tmpDir != null) {
                            try {
                                Files.walk(tmpDir)
                                        .sorted(Comparator.reverseOrder())
                                        .forEach(path -> {
                                            try {
                                                Files.deleteIfExists(path);
                                            } catch (IOException ignored) {
                                            }
                                        });
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }

                @Override
                protected void done() {
                    try {
                        if (isCancelled()) {
                            latestPlanningResult = null;
                            setPlanMessages("Planning stopped by user.");
                            statsArea.setText(buildStatsAndRawOutputText("Planning stopped by user."));
                            viewStateButton.setEnabled(false);
                            viewPlanGraphButton.setEnabled(false);
                        } else {
                            PlanningResult result = get();
                            latestPlanningResult = result;
                            setPlanResult(result);
                            statsArea.setText(buildStatsAndRawOutputText(result.statsText));
                            viewStateButton.setEnabled(result.hasTrace());
                            viewPlanGraphButton.setEnabled(result.hasPlanGraph());
                            if (searchTreeForThisRun) {
                                loadLatestSearchTreeJsonIfPresent();
                            }
                        }
                    } catch (Exception e) {
                        latestPlanningResult = null;
                        setPlanMessages("Planning failed:", String.valueOf(e));
                        statsArea.setText(buildStatsAndRawOutputText("Planning failed:\n" + e));
                        viewStateButton.setEnabled(false);
                        viewPlanGraphButton.setEnabled(false);
                    } finally {
                        runButton.setEnabled(true);
                        pauseButton.setEnabled(false);
                        stopButton.setEnabled(false);
                        pauseButton.setText("Pause");
                        currentWorker = null;
                        executionController = null;
                        pauseRequested = false;
                    }
                }
            };
            currentWorker = worker;
            worker.execute();
        }

        private void persistLatestSearchJsonIfPresent(Path tmpDir) {
            if (tmpDir == null) {
                return;
            }
            try {
                Path found = null;
                try (var stream = Files.list(tmpDir)) {
                    found = stream
                            .filter(p -> {
                                String name = p.getFileName().toString();
                                return name.endsWith(".sp_log") || name.endsWith(".json");
                            })
                            .findFirst()
                            .orElse(null);
                }
                if (found == null) {
                    return;
                }
                Path targetDir = Path.of(System.getProperty("user.home"), ".jpddlplus_gui");
                Files.createDirectories(targetDir);
                Path target = targetDir.resolve("last_search_tree.sp_log");
                Files.copy(found, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                lastSearchJsonPath = target;
            } catch (Exception ignored) {
            }
        }

        private void loadLatestSearchTreeJsonIfPresent() {
            Path p = lastSearchJsonPath;
            if (p == null || !Files.exists(p)) {
                return;
            }
            LazySearchTreeWindow treeWindow = ensureSearchTreeWindow();
            treeWindow.showWindow();
            treeWindow.loadJsonTree(p);
        }

        private boolean previewGeneratedPddlAndConfirm(String generatedDomain, String generatedProblem) {
            JTabbedPane tabs = new JTabbedPane();
            JTextArea d = new JTextArea(generatedDomain);
            d.setEditable(false);
            d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            tabs.add("Generated Domain", new JScrollPane(d));
            JTextArea p = new JTextArea(generatedProblem);
            p.setEditable(false);
            p.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            tabs.add("Generated Problem", new JScrollPane(p));
            tabs.setPreferredSize(new Dimension(980, 700));

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    tabs,
                    "Debug Preview: Confirm Generated PDDL",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
            return choice == JOptionPane.OK_OPTION;
        }

        private void setPlanMessages(String... lines) {
            planListModel.clear();
            for (String l : lines) {
                planListModel.addElement(l);
            }
        }

        private void setPlanResult(PlanningResult result) {
            planListModel.clear();
            for (String row : result.displayLines) {
                planListModel.addElement(row);
            }
            if (!planListModel.isEmpty()) {
                planList.setSelectedIndex(0);
            }
        }

        private String getLiveStatsSnapshot() {
            synchronized (liveStatsBuffer) {
                return liveStatsBuffer.toString();
            }
        }

        private String getRawOutputSnapshot() {
            synchronized (rawOutputBuffer) {
                return rawOutputBuffer.toString();
            }
        }

        private String buildStatsAndRawOutputText(String statsText) {
            StringBuilder builder = new StringBuilder();
            if (statsText != null && !statsText.isBlank()) {
                builder.append(statsText);
            }
            String raw = getRawOutputSnapshot();
            if (!raw.isBlank()) {
                if (!builder.isEmpty()) {
                    builder.append("\n\n");
                }
                builder.append("Raw output:\n").append(raw);
            }
            return builder.toString();
        }

        private void appendRawOutputLine(String line) {
            synchronized (rawOutputBuffer) {
                rawOutputBuffer.append(line).append('\n');
                if (rawOutputBuffer.length() > MAX_RAW_OUTPUT_CHARS) {
                    int overflow = rawOutputBuffer.length() - MAX_RAW_OUTPUT_CHARS;
                    int cut = rawOutputBuffer.indexOf("\n", overflow);
                    if (cut < 0) {
                        cut = overflow;
                    }
                    rawOutputBuffer.delete(0, Math.min(cut + 1, rawOutputBuffer.length()));
                }
            }
        }

        private void handlePlannerOutputLine(String line) {
            appendRawOutputLine(line);
            String interesting = PlannerOutputParser.extractInterestingLiveStat(line);
            if (interesting != null) {
                synchronized (liveStatsBuffer) {
                    liveStatsBuffer.append(interesting).append('\n');
                }
            }
            SwingUtilities.invokeLater(() -> {
                if (currentWorker != null) {
                    statsArea.append(line + "\n");
                    statsArea.setCaretPosition(statsArea.getDocument().getLength());
                }
            });
        }

        private void showSelectedStepStatePopup() {
            if (currentWorker != null) {
                return;
            }
            PlanningResult result = latestPlanningResult;
            if (result == null || !result.hasTrace()) {
                JOptionPane.showMessageDialog(this, "No state trace available for this plan.", "State Trace", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int stepIndex = selectedPlanLineIndex();
            if (stepIndex < 0 || stepIndex >= result.displayToActionStep.size()) {
                JOptionPane.showMessageDialog(this, "Select an action line in the plan first.", "State Trace", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int actionStepIndex = result.displayToActionStep.get(stepIndex);
            if (actionStepIndex < 0 || actionStepIndex >= result.actionLines.size()) {
                JOptionPane.showMessageDialog(this, "Select an action line in the plan first.", "State Trace", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JDialog dialog = new JDialog(this, "State Trace Navigator", false);
            dialog.setLayout(new BorderLayout(10, 10));

            JPanel top = new JPanel(new BorderLayout(8, 8));
            top.setBorder(new EmptyBorder(10, 10, 0, 10));
            JLabel stepLabel = new JLabel();
            stepLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            JTextArea actionArea = new JTextArea();
            actionArea.setEditable(false);
            actionArea.setLineWrap(true);
            actionArea.setWrapStyleWord(true);
            actionArea.setRows(2);
            actionArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
            actionArea.setBackground(new Color(245, 248, 252));
            actionArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(215, 222, 232)),
                    new EmptyBorder(8, 10, 8, 10)
            ));
            top.add(stepLabel, BorderLayout.NORTH);
            top.add(actionArea, BorderLayout.CENTER);

            JTabbedPane tabs = new JTabbedPane();

            JTextArea diffArea = createStateTraceArea();
            tabs.add("Differences", new JScrollPane(diffArea));

            JTextArea fullArea = createStateTraceArea();
            tabs.add("Full States", new JScrollPane(fullArea));

            JTextArea varsArea = createStateTraceArea();
            tabs.add("Variables", new JScrollPane(varsArea));

            JButton prevButton = new JButton("Previous");
            JButton nextButton = new JButton("Next");
            JLabel hintLabel = new JLabel("Use Left/Right arrow keys to move through the plan.");
            hintLabel.setForeground(new Color(90, 100, 120));

            JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
            controls.add(hintLabel);
            controls.add(prevButton);
            controls.add(nextButton);

            dialog.add(top, BorderLayout.NORTH);
            dialog.add(tabs, BorderLayout.CENTER);
            dialog.add(controls, BorderLayout.SOUTH);

            final int[] currentActionIndex = {actionStepIndex};
            Runnable refreshView = () -> {
                int current = currentActionIndex[0];
                String action = result.actionLines.get(current);
                String before = current < result.actionStateBefore.size() ? result.actionStateBefore.get(current) : "N/A";
                String after = current < result.actionStateAfter.size() ? result.actionStateAfter.get(current) : "N/A";

                stepLabel.setText("Action step " + current + " / " + (Math.max(0, result.actionLines.size() - 1)));
                actionArea.setText(action);
                diffArea.setText(StateTraceFormatter.buildStateDiff(before, after));
                fullArea.setText("State before:\n" + before + "\n\nState after:\n" + after);
                varsArea.setText(StateTraceFormatter.buildVariableValuesView(before, after));

                prevButton.setEnabled(current > 0);
                nextButton.setEnabled(current < result.actionLines.size() - 1);

                int displayIndex = findDisplayIndexForActionStep(result, current);
                if (displayIndex >= 0 && displayIndex < planListModel.size()) {
                    planList.setSelectedIndex(displayIndex);
                    planList.ensureIndexIsVisible(displayIndex);
                }

                dialog.setTitle("State Trace - Step " + current);
                diffArea.setCaretPosition(0);
                fullArea.setCaretPosition(0);
                varsArea.setCaretPosition(0);
            };

            Runnable goPrevious = () -> {
                if (currentActionIndex[0] > 0) {
                    currentActionIndex[0]--;
                    refreshView.run();
                }
            };
            Runnable goNext = () -> {
                if (currentActionIndex[0] < result.actionLines.size() - 1) {
                    currentActionIndex[0]++;
                    refreshView.run();
                }
            };

            prevButton.addActionListener(e -> goPrevious.run());
            nextButton.addActionListener(e -> goNext.run());
            installStateTraceNavigationBindings(dialog, goPrevious, goNext, tabs, diffArea, fullArea, varsArea, actionArea);

            dialog.setSize(980, 700);
            dialog.setLocationRelativeTo(this);
            refreshView.run();
            dialog.setVisible(true);
        }

        private JTextArea createStateTraceArea() {
            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            return area;
        }

        private int findDisplayIndexForActionStep(PlanningResult result, int actionStepIndex) {
            for (int i = 0; i < result.displayToActionStep.size(); i++) {
                if (result.displayToActionStep.get(i) == actionStepIndex) {
                    return i;
                }
            }
            return -1;
        }

        private void installStateTraceNavigationBindings(JDialog dialog, Runnable goPrevious, Runnable goNext,
                                                         JComponent... components) {
            Action prevAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goPrevious.run();
                }
            };
            Action nextAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goNext.run();
                }
            };

            bindStateTraceNavigation(dialog.getRootPane(), prevAction, nextAction, true);
            for (JComponent component : components) {
                bindStateTraceNavigation(component, prevAction, nextAction, false);
            }
        }

        private void bindStateTraceNavigation(JComponent component, Action prevAction, Action nextAction, boolean windowScope) {
            int condition = windowScope ? JComponent.WHEN_IN_FOCUSED_WINDOW : JComponent.WHEN_FOCUSED;
            InputMap inputMap = component.getInputMap(condition);
            ActionMap actionMap = component.getActionMap();
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "state-trace-prev");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "state-trace-next");
            actionMap.put("state-trace-prev", prevAction);
            actionMap.put("state-trace-next", nextAction);
        }

        private void showPlanGraphDialog() {
            if (currentWorker != null) {
                return;
            }
            PlanningResult result = latestPlanningResult;
            if (result == null || !result.hasPlanGraph()) {
                JOptionPane.showMessageDialog(this, "No plan available to visualize.", "Plan Graph", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JDialog dialog = new JDialog(this, "Plan Graph", false);
            PlanGraphPanel graphPanel = new PlanGraphPanel(result);
            JScrollPane scrollPane = new JScrollPane(graphPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
            dialog.setLayout(new BorderLayout());
            dialog.add(scrollPane, BorderLayout.CENTER);
            dialog.setSize(1100, 700);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        }

        private int selectedPlanLineIndex() {
            return planList.getSelectedIndex();
        }

        private void injectExternalLogger(ENHSP planner, IExternalLogger logger) throws Exception {
            Field field = ENHSP.class.getDeclaredField("externalLogger");
            field.setAccessible(true);
            field.set(planner, logger);
        }

        private void togglePausePlanning() {
            if (currentWorker == null || executionController == null) {
                return;
            }
            pauseRequested = !pauseRequested;
            if (pauseRequested) {
                executionController.pause();
                pauseButton.setText("Resume");
                statsArea.setText(statsArea.getText() + "\n[Paused]");
            } else {
                executionController.resume();
                pauseButton.setText("Pause");
                statsArea.setText(statsArea.getText() + "\n[Resumed]");
            }
        }

        private void stopPlanning() {
            if (currentWorker == null) {
                return;
            }
            if (executionController != null) {
                executionController.stop();
            }
            Thread t = planningThread;
            if (t != null) {
                t.interrupt();
            }
            currentWorker.cancel(true);
            setPlanMessages("Stopping planning...");
            statsArea.setText("Stopping planning...\n");
        }

        private static String defaultDomain() {
            return "; PDDL sample: simple numeric counter\n"
                    + "(define (domain numeric-counter)\n"
                    + "  (:requirements :strips :fluents)\n"
                    + "  (:predicates (ready))\n"
                    + "  (:functions (x) (step) (limit))\n"
                    + "\n"
                    + "  (:action add-step\n"
                    + "    :parameters ()\n"
                    + "    :precondition (and\n"
                    + "      (ready)\n"
                    + "      (<= (+ (x) (step)) (limit))\n"
                    + "    )\n"
                    + "    :effect (and\n"
                    + "      (increase (x) (step))\n"
                    + "    )\n"
                    + "  )\n"
                    + ")";
        }

        private static String defaultProblem() {
            return "; PDDL numeric goal with prefix expressions\n"
                    + "(define (problem numeric-counter-p1)\n"
                    + "  (:domain numeric-counter)\n"
                    + "  (:init\n"
                    + "    (ready)\n"
                    + "    (= (x) 0)\n"
                    + "    (= (step) 2)\n"
                    + "    (= (limit) 10)\n"
                    + "  )\n"
                    + "  (:goal (and\n"
                    + "    (>= (x) 6)\n"
                    + "  ))\n"
                    + ")";
        }
    }

    private enum EditorKind {
        DOMAIN,
        PROBLEM
    }

    private static final class AiAssistantDialog extends JDialog {
        private final JTextArea conversationArea;
        private final JTextArea inputArea;
        private final JTextField modelField;
        private final JCheckBox includeEditorsCheck;
        private final JButton sendButton;
        private final JButton clearButton;
        private final JTextPane domainEditor;
        private final JTextPane problemEditor;
        private final OpenAiChatClient client;
        private final List<ChatTurn> history = new ArrayList<>();

        AiAssistantDialog(Window owner, JTextPane domainEditor, JTextPane problemEditor) {
            super(owner, "AI Assistant", ModalityType.MODELESS);
            this.domainEditor = domainEditor;
            this.problemEditor = problemEditor;
            this.client = new OpenAiChatClient();

            setSize(760, 560);
            setLocationRelativeTo(owner);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel root = new JPanel(new BorderLayout(8, 8));
            root.setBorder(new EmptyBorder(10, 10, 10, 10));
            setContentPane(root);

            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
            top.add(new JLabel("Model:"));
            modelField = new JTextField("gpt-4.1-mini", 14);
            top.add(modelField);
            includeEditorsCheck = new JCheckBox("Include current domain/problem context", true);
            top.add(includeEditorsCheck);
            root.add(top, BorderLayout.NORTH);

            conversationArea = new JTextArea();
            conversationArea.setEditable(false);
            conversationArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            root.add(new JScrollPane(conversationArea), BorderLayout.CENTER);

            JPanel bottom = new JPanel(new BorderLayout(6, 6));
            inputArea = new JTextArea(5, 40);
            inputArea.setLineWrap(true);
            inputArea.setWrapStyleWord(true);
            bottom.add(new JScrollPane(inputArea), BorderLayout.CENTER);

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
            clearButton = new JButton("Clear");
            clearButton.addActionListener(e -> clearConversation());
            sendButton = new JButton("Send");
            sendButton.addActionListener(e -> sendMessage());
            actions.add(clearButton);
            actions.add(sendButton);
            bottom.add(actions, BorderLayout.SOUTH);

            root.add(bottom, BorderLayout.SOUTH);
        }

        private void clearConversation() {
            history.clear();
            conversationArea.setText("");
        }

        private void sendMessage() {
            String user = inputArea.getText() == null ? "" : inputArea.getText().trim();
            if (user.isEmpty()) {
                return;
            }
            String apiKey = OpenAiApiKeyStore.resolveApiKey();
            if (apiKey == null || apiKey.isBlank()) {
                JOptionPane.showMessageDialog(this,
                        "Missing OpenAI API key.\nUse AI Assistant button to configure integration.",
                        "AI Assistant",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String model = modelField.getText() == null ? "" : modelField.getText().trim();
            if (model.isEmpty()) {
                model = "gpt-4.1-mini";
            }
            final String selectedModel = model;

            appendMessage("You", user);
            inputArea.setText("");
            sendButton.setEnabled(false);

            final String contextBlock;
            if (includeEditorsCheck.isSelected()) {
                contextBlock = "Current Domain:\n" + domainEditor.getText() + "\n\nCurrent Problem:\n" + problemEditor.getText();
            } else {
                contextBlock = null;
            }

            SwingWorker<String, Void> worker = new SwingWorker<>() {
                @Override
                protected String doInBackground() throws Exception {
                    return client.chat(apiKey, selectedModel, history, user, contextBlock);
                }

                @Override
                protected void done() {
                    try {
                        String answer = get();
                        history.add(new ChatTurn("user", user));
                        history.add(new ChatTurn("assistant", answer));
                        appendMessage("Assistant", answer);
                    } catch (Exception e) {
                        appendMessage("Assistant", "Error: " + e.getMessage());
                    } finally {
                        sendButton.setEnabled(true);
                    }
                }
            };
            worker.execute();
        }

        private void appendMessage(String role, String text) {
            conversationArea.append(role + ":\n" + text + "\n\n");
            conversationArea.setCaretPosition(conversationArea.getDocument().getLength());
        }
    }

    private record ChatTurn(String role, String text) {}

    private static final class OpenAiApiKeyStore {
        private static final String PREF_NODE = "com.hstairs.enhspgui.ai";
        private static final String PREF_KEY = "openai_api_key";

        static String resolveApiKey() {
            String env = System.getenv("OPENAI_API_KEY");
            if (env != null && !env.isBlank()) {
                return env.trim();
            }
            Preferences prefs = Preferences.userRoot().node(PREF_NODE);
            String stored = prefs.get(PREF_KEY, "");
            return stored == null || stored.isBlank() ? null : stored.trim();
        }

        static void saveApiKey(String key) {
            Preferences prefs = Preferences.userRoot().node(PREF_NODE);
            prefs.put(PREF_KEY, key.trim());
        }
    }

    private static final class OpenAiChatClient {
        private static final String RESPONSES_API = "https://api.openai.com/v1/responses";
        private final HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        String chat(String apiKey, String model, List<ChatTurn> history, String userMessage, String contextBlock) throws Exception {
            JSONObject body = new JSONObject();
            body.put("model", model);

            JSONArray input = new JSONArray();
            input.add(toInputMessage("system",
                    "You are a coding assistant inside ENHSP GUI. Be concise and practical."));
            for (ChatTurn turn : history) {
                input.add(toInputMessage(turn.role(), turn.text()));
            }
            if (contextBlock != null && !contextBlock.isBlank()) {
                input.add(toInputMessage("system", contextBlock));
            }
            input.add(toInputMessage("user", userMessage));
            body.put("input", input);
            body.put("temperature", 0.2);

            HttpRequest request = HttpRequest.newBuilder(URI.create(RESPONSES_API))
                    .timeout(Duration.ofSeconds(90))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toJSONString(), StandardCharsets.UTF_8))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IOException("OpenAI API error " + response.statusCode() + ": " + response.body());
            }
            return extractText(response.body());
        }

        private static JSONObject toInputMessage(String role, String text) {
            JSONObject msg = new JSONObject();
            msg.put("role", role);
            JSONArray content = new JSONArray();
            JSONObject part = new JSONObject();
            part.put("type", "input_text");
            part.put("text", text == null ? "" : text);
            content.add(part);
            msg.put("content", content);
            return msg;
        }

        private static String extractText(String json) throws Exception {
            Object parsed = new JSONParser().parse(json);
            if (!(parsed instanceof JSONObject obj)) {
                return "No response content.";
            }

            Object outputText = obj.get("output_text");
            if (outputText instanceof String s && !s.isBlank()) {
                return s;
            }

            Object outputObj = obj.get("output");
            if (outputObj instanceof JSONArray outputArray) {
                StringBuilder sb = new StringBuilder();
                for (Object item : outputArray) {
                    if (!(item instanceof JSONObject out)) {
                        continue;
                    }
                    Object contentObj = out.get("content");
                    if (!(contentObj instanceof JSONArray contentArray)) {
                        continue;
                    }
                    for (Object c : contentArray) {
                        if (!(c instanceof JSONObject part)) {
                            continue;
                        }
                        Object type = part.get("type");
                        Object text = part.get("text");
                        if ("output_text".equals(type) && text instanceof String t) {
                            if (!sb.isEmpty()) {
                                sb.append('\n');
                            }
                            sb.append(t);
                        }
                    }
                }
                if (!sb.isEmpty()) {
                    return sb.toString();
                }
            }
            return "No textual response.";
        }
    }

    private static final class PlanGraphPanel extends JPanel {
        private static final int MARGIN = 40;
        private static final int BOX_WIDTH = 190;
        private static final int BOX_HEIGHT = 34;
        private static final int COLUMN_GAP = 80;
        private static final int ROW_GAP = 16;
        private final PlanningResult result;
        private final List<TooltipRegion> tooltipRegions = new ArrayList<>();

        PlanGraphPanel(PlanningResult result) {
            this.result = result;
            setOpaque(true);
            setBackground(Color.WHITE);
            ToolTipManager.sharedInstance().registerComponent(this);
        }

        @Override
        public String getToolTipText(MouseEvent event) {
            if (event == null) {
                return null;
            }
            for (int i = tooltipRegions.size() - 1; i >= 0; i--) {
                TooltipRegion region = tooltipRegions.get(i);
                if (region.bounds.contains(event.getPoint())) {
                    return region.tooltip;
                }
            }
            return null;
        }

        @Override
        public Dimension getPreferredSize() {
            if (result.timedPlan && !result.planTimepointGroups.isEmpty()) {
                int columns = result.planTimepointGroups.size();
                int maxRows = 1;
                for (PlanTimepointGroup group : result.planTimepointGroups) {
                    maxRows = Math.max(maxRows, group.actions.size());
                }
                int width = MARGIN * 2 + columns * BOX_WIDTH + Math.max(0, columns - 1) * COLUMN_GAP + 120;
                int height = MARGIN * 2 + 110 + maxRows * (BOX_HEIGHT + ROW_GAP);
                return new Dimension(Math.max(900, width), Math.max(360, height));
            }
            int steps = Math.max(1, result.actionLines.size());
            int width = 960;
            int height = MARGIN * 2 + 80 + steps * (BOX_HEIGHT + 40);
            return new Dimension(width, Math.max(360, height));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            tooltipRegions.clear();
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (result.timedPlan && !result.planTimepointGroups.isEmpty()) {
                paintTimedPlan(g2);
            } else {
                paintSequentialPlan(g2);
            }
            g2.dispose();
        }

        private void paintSequentialPlan(Graphics2D g2) {
            int width = getPreferredSize().width - (MARGIN * 2);
            int x = MARGIN;
            int y = MARGIN + 30;
            g2.setColor(new Color(60, 66, 80));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
            g2.drawString("Sequential plan", MARGIN, MARGIN - 8);

            for (int i = 0; i < result.actionLines.size(); i++) {
                String label = i + ": " + result.actionLines.get(i);
                Rectangle bounds = drawActionBox(g2, x, y, width, BOX_HEIGHT, label,
                        new Color(226, 239, 252), new Color(55, 95, 155));
                registerTooltip(bounds, buildActionTooltip(i, result.actionLines.get(i)));
                if (i < result.actionLines.size() - 1) {
                    int xMid = x + (width / 2);
                    int yStart = y + BOX_HEIGHT;
                    int yEnd = y + BOX_HEIGHT + 28;
                    drawArrow(g2, xMid, yStart, xMid, yEnd);
                }
                y += BOX_HEIGHT + 40;
            }
        }

        private void paintTimedPlan(Graphics2D g2) {
            List<PlanTimepointGroup> groups = result.planTimepointGroups;
            int startX = MARGIN + 40;
            int axisY = MARGIN + 45;
            int panelBottom = getPreferredSize().height - MARGIN;

            g2.setColor(new Color(60, 66, 80));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
            g2.drawString("Timepoint groups (PDDL+/temporal)", MARGIN, MARGIN - 8);

            for (int i = 0; i < groups.size(); i++) {
                PlanTimepointGroup group = groups.get(i);
                int x = startX + i * (BOX_WIDTH + COLUMN_GAP);
                g2.setColor(new Color(110, 120, 135));
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 13f));
                g2.drawString("t=" + group.timeKey, x, axisY);
                registerTooltip(new Rectangle(x, axisY - 14, BOX_WIDTH, 22), buildTimepointTooltip(group));
                g2.setStroke(new BasicStroke(1.2f));
                g2.setColor(new Color(214, 219, 230));
                g2.drawLine(x + (BOX_WIDTH / 2), axisY + 10, x + (BOX_WIDTH / 2), panelBottom);
                for (int row = 0; row < group.actions.size(); row++) {
                    PlanActionRef actionRef = group.actions.get(row);
                    int y = axisY + 22 + row * (BOX_HEIGHT + ROW_GAP);
                    Rectangle bounds = drawActionBox(g2, x, y, BOX_WIDTH, BOX_HEIGHT, actionRef.action,
                            new Color(229, 247, 234), new Color(44, 120, 70));
                    registerTooltip(bounds, buildActionTooltip(actionRef.actionIndex, actionRef.action));
                }
            }

            g2.setStroke(new BasicStroke(1.0f));
            g2.setColor(new Color(120, 130, 150));
            for (int i = 0; i < groups.size() - 1; i++) {
                PlanTimepointGroup left = groups.get(i);
                PlanTimepointGroup right = groups.get(i + 1);
                int connectors = Math.min(left.actions.size(), right.actions.size());
                int leftX = startX + i * (BOX_WIDTH + COLUMN_GAP);
                int rightX = startX + (i + 1) * (BOX_WIDTH + COLUMN_GAP);
                for (int row = 0; row < connectors; row++) {
                    int y = axisY + 22 + row * (BOX_HEIGHT + ROW_GAP) + (BOX_HEIGHT / 2);
                    drawArrow(g2, leftX + BOX_WIDTH, y, rightX, y);
                }
            }
        }

        private String buildActionTooltip(int actionIndex, String actionText) {
            StringBuilder builder = new StringBuilder("<html><b>Action ");
            builder.append(actionIndex).append("</b>: ").append(escapeHtml(ellipsize(actionText, 120)));
            if (actionIndex >= 0 && actionIndex < result.actionStateBefore.size()) {
                builder.append("<br><br><b>State when action is applied:</b><br>")
                        .append(toHtmlMultiline(result.actionStateBefore.get(actionIndex), 2000));
            } else {
                builder.append("<br><br><i>State trace unavailable for this action.</i>");
            }
            if (result.timedPlan) {
                builder.append("<br><br><i>In PDDL+ this state can be approximate (action-only replay).</i>");
            }
            builder.append("</html>");
            return builder.toString();
        }

        private String buildTimepointTooltip(PlanTimepointGroup group) {
            StringBuilder builder = new StringBuilder("<html><b>t=");
            builder.append(escapeHtml(group.timeKey)).append("</b><br>");
            if (group.onDemandTransitions.isEmpty()) {
                builder.append("No waiting/process/event transitions recorded.");
            } else {
                builder.append("<b>Waiting/process/event transitions:</b><br>");
                int shown = 0;
                for (String transition : group.onDemandTransitions) {
                    builder.append("&bull; ").append(escapeHtml(ellipsize(transition, 140))).append("<br>");
                    shown++;
                    if (shown >= 20) {
                        builder.append("...").append("<br>");
                        break;
                    }
                }
            }
            builder.append("</html>");
            return builder.toString();
        }

        private void registerTooltip(Rectangle bounds, String tooltip) {
            if (bounds == null || tooltip == null || tooltip.isBlank()) {
                return;
            }
            tooltipRegions.add(new TooltipRegion(bounds, tooltip));
        }

        private static Rectangle drawActionBox(Graphics2D g2, int x, int y, int width, int height,
                                               String label, Color fill, Color border) {
            g2.setColor(fill);
            g2.fillRoundRect(x, y, width, height, 16, 16);
            g2.setColor(border);
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawRoundRect(x, y, width, height, 16, 16);
            g2.setColor(new Color(28, 32, 40));
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12f));
            g2.drawString(ellipsize(label, 72), x + 10, y + 22);
            return new Rectangle(x, y, width, height);
        }

        private static void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
            g2.drawLine(x1, y1, x2, y2);
            double angle = Math.atan2(y2 - y1, x2 - x1);
            int arrowSize = 7;
            int ax1 = (int) Math.round(x2 - arrowSize * Math.cos(angle - Math.PI / 6));
            int ay1 = (int) Math.round(y2 - arrowSize * Math.sin(angle - Math.PI / 6));
            int ax2 = (int) Math.round(x2 - arrowSize * Math.cos(angle + Math.PI / 6));
            int ay2 = (int) Math.round(y2 - arrowSize * Math.sin(angle + Math.PI / 6));
            g2.drawLine(x2, y2, ax1, ay1);
            g2.drawLine(x2, y2, ax2, ay2);
        }

        private static String ellipsize(String text, int maxChars) {
            if (text == null) {
                return "";
            }
            if (text.length() <= maxChars) {
                return text;
            }
            if (maxChars <= 3) {
                return "...";
            }
            return text.substring(0, maxChars - 3) + "...";
        }

        private static String toHtmlMultiline(String text, int maxChars) {
            String content = text == null ? "" : text;
            if (content.length() > maxChars) {
                content = content.substring(0, maxChars) + "...";
            }
            return escapeHtml(content).replace("\n", "<br>");
        }

        private static String escapeHtml(String text) {
            if (text == null) {
                return "";
            }
            String escaped = text;
            escaped = escaped.replace("&", "&amp;");
            escaped = escaped.replace("<", "&lt;");
            escaped = escaped.replace(">", "&gt;");
            escaped = escaped.replace("\"", "&quot;");
            return escaped.replace("'", "&#39;");
        }

        private static final class TooltipRegion {
            final Rectangle bounds;
            final String tooltip;

            TooltipRegion(Rectangle bounds, String tooltip) {
                this.bounds = bounds;
                this.tooltip = tooltip;
            }
        }
    }

    private static final class SyntaxReport {
        final boolean ok;
        final String message;

        SyntaxReport(boolean ok, String message) {
            this.ok = ok;
            this.message = message;
        }
    }

    private static final class PlannerOptionsDialog extends JDialog {
        private PlannerCliOptions result;
        private final PlannerCliOptions working;
        private final List<JComponent> presetOverriddenComponents = new ArrayList<>();
        private final List<JLabel> presetOverriddenLabels = new ArrayList<>();
        private final List<JCheckBox> presetOverriddenFlags = new ArrayList<>();
        private final JLabel plannerPresetInfo;

        private final JComboBox<String> planner;
        private final JComboBox<String> heuristic;
        private final JComboBox<String> search;
        private final JComboBox<String> novelty;
        private final JTextField kNov;
        private final JComboBox<String> ties;
        private final JComboBox<String> red;
        private final JComboBox<String> grounding;
        private final JComboBox<String> sdac;
        private final JTextField wh;
        private final JTextField dp;
        private final JTextField de;
        private final JTextField dh;
        private final JTextField dv;
        private final JTextField d;
        private final JTextField dl;
        private final JTextField timeout;
        private final JTextField k;
        private final JTextField tolerance;
        private final JTextField inputPlan;
        private final JTextField savePlan;
        private final JTextField posthoc;
        private final JTextField ea;
        private final JTextArea customArgs;

        private final JCheckBox ha;
        private final JCheckBox ht;
        private final JCheckBox pe;
        private final JCheckBox pt;
        private final JCheckBox im;
        private final JCheckBox dap;
        private final JCheckBox stopgro;
        private final JCheckBox ival;
        private final JCheckBox onlyplan;
        private final JCheckBox printActions;
        private final JCheckBox silent;
        private final JCheckBox autoanytime;
        private final JCheckBox anytime;
        private final JCheckBox uch;
        private final JCheckBox npm;
        private final JCheckBox pai;
        private final JCheckBox bbqs;
        private final JCheckBox tun;
        private final JCheckBox sjr;

        PlannerOptionsDialog(Window owner, PlannerCliOptions current) {
            super(owner, "Planner Options", ModalityType.APPLICATION_MODAL);
            this.working = current.copy();

            planner = combo(plannerPresetValues());
            heuristic = combo("hadd", "blind", "hmax", "hmrp", "aibr", "hradd", "hrmax", "hrmaxssnp", "hlm-lp");
            search = combo("gbfs", "wastar", "ehs", "ida", "lazygbfs", "lazywastar");
            novelty = combo("", "aqb", "aw", "iqb", "iw");
            kNov = new JTextField();
            ties = combo("arbitrary", "smaller_g", "larger_g");
            red = combo("no", "brute", "smart");
            grounding = combo("internal", "naive", "fd", "metricff", "fdi");
            sdac = combo("disabled", "rhs", "condition");
            wh = new JTextField();
            dp = new JTextField();
            de = new JTextField();
            dh = new JTextField();
            dv = new JTextField();
            d = new JTextField();
            dl = new JTextField();
            timeout = new JTextField();
            k = new JTextField();
            tolerance = new JTextField();
            inputPlan = new JTextField();
            savePlan = new JTextField();
            posthoc = new JTextField();
            ea = new JTextField();
            customArgs = new JTextArea(4, 24);
            customArgs.setLineWrap(true);
            customArgs.setWrapStyleWord(true);

            ha = new JCheckBox("Helpful actions");
            ht = new JCheckBox("Helpful transitions");
            pe = new JCheckBox("Print events");
            pt = new JCheckBox("Print trace");
            im = new JCheckBox("Ignore metric");
            dap = new JCheckBox("Disable AIBR preprocessing");
            stopgro = new JCheckBox("Stop after grounding");
            ival = new JCheckBox("Internal validation");
            onlyplan = new JCheckBox("Only plan");
            printActions = new JCheckBox("Print actions");
            silent = new JCheckBox("Silent mode");
            autoanytime = new JCheckBox("Auto anytime");
            anytime = new JCheckBox("Anytime");
            uch = new JCheckBox("Unit-cost heuristic");
            npm = new JCheckBox("No makespan print");
            pai = new JCheckBox("Print all info");
            bbqs = new JCheckBox("Bucket-based queue search");
            tun = new JCheckBox("Tunnelling");
            sjr = new JCheckBox("Save search JSON");
            plannerPresetInfo = new JLabel(" ");

            loadFromWorking();
            buildUi();
        }

        PlannerCliOptions showDialog() {
            pack();
            setSize(900, 620);
            setLocationRelativeTo(getOwner());
            setVisible(true);
            return result;
        }

        private void buildUi() {
            JPanel root = new JPanel(new BorderLayout(8, 8));
            root.setBorder(new EmptyBorder(10, 10, 10, 10));
            setContentPane(root);

            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Core", buildCorePanel());
            tabs.add("Advanced", buildAdvancedPanel());
            tabs.add("Flags", buildFlagsPanel());
            root.add(tabs, BorderLayout.CENTER);

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton defaults = new JButton("Defaults");
            defaults.addActionListener(e -> {
                PlannerCliOptions d = PlannerCliOptions.defaults();
                applyToFields(d);
            });
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(e -> dispose());
            JButton ok = new JButton("Apply");
            ok.addActionListener(e -> {
                result = readFromFields();
                dispose();
            });
            actions.add(defaults);
            actions.add(cancel);
            actions.add(ok);
            root.add(actions, BorderLayout.SOUTH);

            planner.addActionListener(e -> updatePresetOverrideVisuals());
            updatePresetOverrideVisuals();
        }

        private JPanel buildCorePanel() {
            JPanel p = new JPanel(new GridLayout(0, 2, 8, 6));
            addField(p, "Planner preset", planner);
            addField(p, "Preset info", plannerPresetInfo);
            markPresetOverridden(addField(p, "Heuristic (-h)", heuristic), heuristic);
            markPresetOverridden(addField(p, "Search (-s)", search), search);
            markPresetOverridden(addField(p, "Tie-breaking", ties), ties);
            addField(p, "Novelty", novelty);
            addField(p, "k-novelty", kNov);
            addField(p, "Helpful weight (wh)", wh);
            markPresetOverridden(addField(p, "Redundant constraints", red), red);
            addField(p, "Grounding", grounding);
            addField(p, "SDAC", sdac);
            return wrappedPanel(p);
        }

        private JPanel buildAdvancedPanel() {
            JPanel p = new JPanel(new GridLayout(0, 2, 8, 6));
            addField(p, "Delta planning (dp)", dp);
            addField(p, "Delta execution (de)", de);
            addField(p, "Delta heuristic (dh)", dh);
            addField(p, "Delta validation (dv)", dv);
            addField(p, "Delta override (d)", d);
            addField(p, "Depth limit (dl)", dl);
            addField(p, "Timeout sec", timeout);
            addField(p, "Subdomains (k)", k);
            addField(p, "Tolerance", tolerance);
            addField(p, "Input plan", inputPlan);
            addField(p, "Save plan path", savePlan);
            addField(p, "Posthoc logger file", posthoc);
            addField(p, "Effect abstraction (ea)", ea);
            addField(p, "Custom CLI args (override)", new JScrollPane(customArgs));
            return wrappedPanel(p);
        }

        private JPanel buildFlagsPanel() {
            JPanel p = new JPanel(new GridLayout(0, 2, 8, 4));
            markPresetOverridden(ha); p.add(ha);
            markPresetOverridden(ht); p.add(ht);
            p.add(pe); p.add(pt);
            p.add(im); markPresetOverridden(dap); p.add(dap);
            p.add(stopgro); p.add(ival);
            p.add(onlyplan); p.add(printActions);
            p.add(silent); p.add(autoanytime);
            p.add(anytime); p.add(uch);
            p.add(npm); p.add(pai);
            p.add(bbqs); p.add(tun);
            p.add(sjr); p.add(new JLabel(""));
            return wrappedPanel(p);
        }

        private JPanel wrappedPanel(JPanel inner) {
            JPanel wrapper = new JPanel(new BorderLayout());
            JScrollPane sp = new JScrollPane(inner);
            sp.setBorder(BorderFactory.createEmptyBorder());
            wrapper.add(sp, BorderLayout.CENTER);
            return wrapper;
        }

        private static JLabel addField(JPanel panel, String label, JComponent comp) {
            JLabel jLabel = new JLabel(label);
            panel.add(jLabel);
            panel.add(comp);
            return jLabel;
        }

        private static JComboBox<String> combo(String... values) {
            return new JComboBox<>(values);
        }

        private static String[] plannerPresetValues() {
            String[] values = new String[Planner.values().length + 1];
            values[0] = "";
            int i = 1;
            for (Planner p : Planner.values()) {
                values[i++] = p.name().toLowerCase().replace('_', '-');
            }
            return values;
        }

        private void loadFromWorking() {
            applyToFields(working);
        }

        private void applyToFields(PlannerCliOptions o) {
            planner.setSelectedItem(o.planner == null ? "" : o.planner);
            heuristic.setSelectedItem(safeOr(o.heuristic, "hadd"));
            search.setSelectedItem(safeOr(o.search, "gbfs"));
            novelty.setSelectedItem(safe(o.novelty));
            kNov.setText(safe(o.kNov));
            ties.setSelectedItem(safeOr(o.tieBreaking, "arbitrary"));
            red.setSelectedItem(safeOr(o.redundantConstraints, "no"));
            grounding.setSelectedItem(safeOr(o.grounding, "internal"));
            sdac.setSelectedItem(safeOr(o.sdac, "disabled"));
            wh.setText(safe(o.wh));
            dp.setText(safe(o.deltaPlanning));
            de.setText(safe(o.deltaExecution));
            dh.setText(safe(o.deltaHeuristic));
            dv.setText(safe(o.deltaValidation));
            d.setText(safe(o.delta));
            dl.setText(safe(o.depthLimit));
            timeout.setText(safe(o.timeout));
            k.setText(safe(o.kSubdomains));
            tolerance.setText(safe(o.tolerance));
            inputPlan.setText(safe(o.inputPlan));
            savePlan.setText(safe(o.savePlan));
            posthoc.setText(safe(o.posthocLogger));
            ea.setText(safe(o.effectAbstraction));
            customArgs.setText(safe(o.customArgs));

            ha.setSelected(o.helpfulActions);
            ht.setSelected(o.helpfulTransitions);
            pe.setSelected(o.printEvents);
            pt.setSelected(o.printTrace);
            im.setSelected(o.ignoreMetric);
            dap.setSelected(o.disableAibrPreprocessing);
            stopgro.setSelected(o.stopAfterGrounding);
            ival.setSelected(o.internalValidation);
            onlyplan.setSelected(o.onlyPlan);
            printActions.setSelected(o.printActions);
            silent.setSelected(o.silent);
            autoanytime.setSelected(o.autoAnytime);
            anytime.setSelected(o.anytime);
            uch.setSelected(o.unitCostHeuristic);
            npm.setSelected(o.noPrintMakespan);
            pai.setSelected(o.printAllInfo);
            bbqs.setSelected(o.bucketBasedQueueSearch);
            tun.setSelected(o.tunnelling);
            sjr.setSelected(o.saveSearchJson);
            updatePresetOverrideVisuals();
        }

        private PlannerCliOptions readFromFields() {
            PlannerCliOptions o = PlannerCliOptions.defaults();
            o.planner = selected(planner);
            o.heuristic = selected(heuristic);
            o.search = selected(search);
            o.novelty = selected(novelty);
            o.kNov = kNov.getText().trim();
            o.tieBreaking = selected(ties);
            o.redundantConstraints = selected(red);
            o.grounding = selected(grounding);
            o.sdac = selected(sdac);
            o.wh = wh.getText().trim();
            o.deltaPlanning = dp.getText().trim();
            o.deltaExecution = de.getText().trim();
            o.deltaHeuristic = dh.getText().trim();
            o.deltaValidation = dv.getText().trim();
            o.delta = d.getText().trim();
            o.depthLimit = dl.getText().trim();
            o.timeout = timeout.getText().trim();
            o.kSubdomains = k.getText().trim();
            o.tolerance = tolerance.getText().trim();
            o.inputPlan = inputPlan.getText().trim();
            o.savePlan = savePlan.getText().trim();
            o.posthocLogger = posthoc.getText().trim();
            o.effectAbstraction = ea.getText().trim();
            o.customArgs = customArgs.getText().trim();

            o.helpfulActions = ha.isSelected();
            o.helpfulTransitions = ht.isSelected();
            o.printEvents = pe.isSelected();
            o.printTrace = pt.isSelected();
            o.ignoreMetric = im.isSelected();
            o.disableAibrPreprocessing = dap.isSelected();
            o.stopAfterGrounding = stopgro.isSelected();
            o.internalValidation = ival.isSelected();
            o.onlyPlan = onlyplan.isSelected();
            o.printActions = printActions.isSelected();
            o.silent = silent.isSelected();
            o.autoAnytime = autoanytime.isSelected();
            o.anytime = anytime.isSelected();
            o.unitCostHeuristic = uch.isSelected();
            o.noPrintMakespan = npm.isSelected();
            o.printAllInfo = pai.isSelected();
            o.bucketBasedQueueSearch = bbqs.isSelected();
            o.tunnelling = tun.isSelected();
            o.saveSearchJson = sjr.isSelected();
            return o;
        }

        private static String selected(JComboBox<String> combo) {
            Object item = combo.getSelectedItem();
            return item == null ? "" : item.toString();
        }

        private static String safe(String v) {
            return v == null ? "" : v;
        }

        private static String safeOr(String v, String def) {
            return (v == null || v.isBlank()) ? def : v;
        }

        private void markPresetOverridden(JLabel label, JComponent component) {
            presetOverriddenLabels.add(label);
            presetOverriddenComponents.add(component);
        }

        private void markPresetOverridden(JCheckBox checkBox) {
            presetOverriddenFlags.add(checkBox);
        }

        private void updatePresetOverrideVisuals() {
            boolean presetActive = !selected(planner).isBlank();
            Color activeColor = UIManager.getColor("Label.foreground");
            Color dimColor = UIManager.getColor("Label.disabledForeground");
            if (activeColor == null) {
                activeColor = Color.BLACK;
            }
            if (dimColor == null) {
                dimColor = Color.GRAY;
            }
            Color overriddenColor = darken(activeColor, 0.42f);
            String tooltip = presetActive ? "Sovrascritto dal planner preset selezionato" : null;
            plannerPresetInfo.setText(selectedPlannerDescription(selected(planner)));
            plannerPresetInfo.setToolTipText(selectedPlannerTooltip(selected(planner)));
            for (int i = 0; i < presetOverriddenComponents.size(); i++) {
                JComponent component = presetOverriddenComponents.get(i);
                JLabel label = presetOverriddenLabels.get(i);
                component.setEnabled(!presetActive);
                component.setToolTipText(tooltip);
                component.setForeground(presetActive ? overriddenColor : activeColor);
                label.setForeground(presetActive ? overriddenColor : activeColor);
                label.setToolTipText(tooltip);
            }
            for (JCheckBox checkBox : presetOverriddenFlags) {
                checkBox.setEnabled(!presetActive);
                checkBox.setForeground(presetActive ? overriddenColor : activeColor);
                checkBox.setToolTipText(tooltip);
            }
            plannerPresetInfo.setForeground(presetActive ? activeColor : dimColor);
        }

        private static String selectedPlannerDescription(String plannerValue) {
            if (plannerValue == null || plannerValue.isBlank()) {
                return "Nessun preset: campi manuali attivi";
            }
            try {
                Planner preset = Planner.valueOf(plannerValue.toUpperCase().replace('-', '_'));
                String desc = preset.getDescription();
                return (desc == null || desc.isBlank()) ? "Preset attivo" : desc;
            } catch (IllegalArgumentException ex) {
                return "Preset non riconosciuto";
            }
        }

        private static String selectedPlannerTooltip(String plannerValue) {
            if (plannerValue == null || plannerValue.isBlank()) {
                return null;
            }
            String description = selectedPlannerDescription(plannerValue);
            return plannerValue + " - " + description;
        }

        private static Color darken(Color c, float factor) {
            if (c == null) {
                return Color.DARK_GRAY;
            }
            float f = Math.max(0f, Math.min(factor, 1f));
            return new Color(
                    Math.max(0, Math.round(c.getRed() * (1f - f))),
                    Math.max(0, Math.round(c.getGreen() * (1f - f))),
                    Math.max(0, Math.round(c.getBlue() * (1f - f)))
            );
        }
    }

    private static final class LineNumberView extends JTextArea {
        private final JTextPane editor;

        LineNumberView(JTextPane editor) {
            this.editor = editor;
            setEditable(false);
            setFocusable(false);
            setBackground(new Color(245, 245, 245));
            setForeground(new Color(120, 120, 120));
            setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            setMargin(new Insets(3, 6, 0, 6));
            setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));

            editor.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    refresh();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    refresh();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    refresh();
                }
            });
            refresh();
        }

        private void refresh() {
            String text = editor.getText();
            int lines = 1;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    lines++;
                }
            }
            StringBuilder sb = new StringBuilder(lines * 3);
            for (int i = 1; i <= lines; i++) {
                sb.append(i).append('\n');
            }
            setText(sb.toString());
            int digits = Math.max(3, String.valueOf(lines).length() + 1);
            setColumns(digits);
        }
    }

    private static final class TeeOutputStream extends OutputStream {
        private final OutputStream first;
        private final OutputStream second;

        TeeOutputStream(OutputStream first, OutputStream second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public void write(int b) throws IOException {
            first.write(b);
            second.write(b);
        }

        @Override
        public void flush() throws IOException {
            first.flush();
            second.flush();
        }

        @Override
        public void close() throws IOException {
            flush();
        }
    }

    private static final class LineCaptureOutputStream extends OutputStream {
        private final StringBuilder buffer = new StringBuilder();
        private final Consumer<String> consumer;

        LineCaptureOutputStream(Consumer<String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void write(int b) {
            char c = (char) (b & 0xff);
            if (c == '\n') {
                flushLine();
            } else if (c != '\r') {
                buffer.append(c);
            }
        }

        @Override
        public void flush() {
            flushLine();
        }

        @Override
        public void close() {
            flushLine();
        }

        private void flushLine() {
            if (buffer.isEmpty()) {
                return;
            }
            String line = buffer.toString();
            buffer.setLength(0);
            consumer.accept(line);
        }
    }

    private static final class LispSyntaxTextPane extends JTextPane {
        private static final Set<String> KEYWORDS = new HashSet<>();
        private static final List<AutocompleteItem> COMMON_COMPLETIONS = new ArrayList<>();
        private static final List<AutocompleteItem> DOMAIN_COMPLETIONS = new ArrayList<>();
        private static final List<AutocompleteItem> PROBLEM_COMPLETIONS = new ArrayList<>();

        static {
            String[] words = {
                    "define", "domain", "problem", ":requirements", ":types", ":objects", ":predicates", ":functions",
                    ":action", ":parameters", ":precondition", ":effect", ":init", ":goal", ":metric", ":domain",
                    "and", "or", "not", "when", "forall", "exists", "increase", "decrease", "assign", "scale-up", "scale-down"
            };
            for (String w : words) {
                KEYWORDS.add(w);
            }

            addCompletion(COMMON_COMPLETIONS, "(define ...)", "(define |)");
            addCompletion(COMMON_COMPLETIONS, "(and ...)", "(and |)");
            addCompletion(COMMON_COMPLETIONS, "(or ...)", "(or |)");
            addCompletion(COMMON_COMPLETIONS, "(not ...)", "(not |)");
            addCompletion(COMMON_COMPLETIONS, "(when ...)", "(when |)");
            addCompletion(COMMON_COMPLETIONS, "(forall ...)", "(forall (|) )");
            addCompletion(COMMON_COMPLETIONS, "(exists ...)", "(exists (|) )");
            addCompletion(COMMON_COMPLETIONS, ":requirements", ":requirements |");
            addCompletion(COMMON_COMPLETIONS, ":types", ":types |");
            addCompletion(COMMON_COMPLETIONS, ":predicates", ":predicates\n  (|)\n");
            addCompletion(COMMON_COMPLETIONS, ":functions", ":functions\n  (|)\n");
            addCompletion(COMMON_COMPLETIONS, ":parameters", ":parameters (|)");
            addCompletion(COMMON_COMPLETIONS, ":precondition", ":precondition\n  (and\n    |\n  )");
            addCompletion(COMMON_COMPLETIONS, ":effect", ":effect\n  (and\n    |\n  )");
            addCompletion(COMMON_COMPLETIONS, ":objects", ":objects\n  |");
            addCompletion(COMMON_COMPLETIONS, ":init", ":init\n  |\n");
            addCompletion(COMMON_COMPLETIONS, ":goal", ":goal\n  (and\n    |\n  )");
            addCompletion(COMMON_COMPLETIONS, ":metric", ":metric minimize (|)");
            addCompletion(COMMON_COMPLETIONS, "(increase ...)", "(increase |)");
            addCompletion(COMMON_COMPLETIONS, "(decrease ...)", "(decrease |)");
            addCompletion(COMMON_COMPLETIONS, "(assign ...)", "(assign |)");
            addCompletion(COMMON_COMPLETIONS, "(scale-up ...)", "(scale-up |)");
            addCompletion(COMMON_COMPLETIONS, "(scale-down ...)", "(scale-down |)");

            addTemplateCompletion(DOMAIN_COMPLETIONS, "template: full domain",
                    "domain define requirements predicates functions action",
                    "(define (domain DOMAIN_NAME)\n" +
                            "  (:requirements :strips :typing :fluents)\n" +
                            "  (:predicates\n" +
                            "    (predicate-name ?x - TYPE)\n" +
                            "  )\n" +
                            "  (:functions\n" +
                            "    (function-name ?x - TYPE)\n" +
                            "  )\n" +
                            "\n" +
                            "  |\n" +
                            ")\n");
            addTemplateCompletion(DOMAIN_COMPLETIONS, "template: action",
                    "domain operator action precondition effect parameters",
                    "(:action ACTION_NAME\n" +
                            "  :parameters (?x - TYPE)\n" +
                            "  :precondition (and\n" +
                            "    |\n" +
                            "  )\n" +
                            "  :effect (and\n" +
                            "    \n" +
                            "  )\n" +
                            ")\n");
            addTemplateCompletion(DOMAIN_COMPLETIONS, "template: predicates block",
                    "domain predicates facts relations",
                    "(:predicates\n" +
                            "  (|)\n" +
                            ")\n");
            addTemplateCompletion(DOMAIN_COMPLETIONS, "template: functions block",
                    "domain functions numeric fluents",
                    "(:functions\n" +
                            "  (|)\n" +
                            ")\n");
            addTemplateCompletion(DOMAIN_COMPLETIONS, "template: requirements block",
                    "domain requirements",
                    "(:requirements :strips :typing :fluents |)\n");
            addCompletion(DOMAIN_COMPLETIONS, "(domain ...)", "(domain |)");

            addTemplateCompletion(PROBLEM_COMPLETIONS, "template: full problem",
                    "problem define objects init goal metric",
                    "(define (problem PROBLEM_NAME)\n" +
                            "  (:domain DOMAIN_NAME)\n" +
                            "  (:objects\n" +
                            "    |obj1 - TYPE\n" +
                            "  )\n" +
                            "  (:init\n" +
                            "    \n" +
                            "  )\n" +
                            "  (:goal\n" +
                            "    (and\n" +
                            "      \n" +
                            "    )\n" +
                            "  )\n" +
                            ")\n");
            addTemplateCompletion(PROBLEM_COMPLETIONS, "template: init block",
                    "problem init initial state",
                    "(:init\n" +
                            "  |\n" +
                            ")\n");
            addTemplateCompletion(PROBLEM_COMPLETIONS, "template: goal block",
                    "problem goal target",
                    "(:goal\n" +
                            "  (and\n" +
                            "    |\n" +
                            "  )\n" +
                            ")\n");
            addTemplateCompletion(PROBLEM_COMPLETIONS, "template: metric block",
                    "problem metric minimize maximize cost",
                    "(:metric minimize (|))\n");
            addTemplateCompletion(PROBLEM_COMPLETIONS, "template: objects block",
                    "problem objects constants instances",
                    "(:objects\n" +
                            "  |obj1 - TYPE\n" +
                            ")\n");
            addCompletion(PROBLEM_COMPLETIONS, "(problem ...)", "(problem |)");
            addCompletion(PROBLEM_COMPLETIONS, "(:domain ...)", "(:domain |)");
        }

        private final EditorKind kind;
        private final Consumer<SyntaxReport> reportConsumer;
        private final Timer repaintTimer;
        private final UndoManager undoManager = new UndoManager();
        private final JPopupMenu autocompletePopup = new JPopupMenu();
        private final JList<AutocompleteItem> autocompleteList = new JList<>();

        private final SimpleAttributeSet normalStyle = style(new Color(30, 30, 30), false, false, null);
        private final SimpleAttributeSet keywordStyle = style(new Color(10, 70, 180), true, false, null);
        private final SimpleAttributeSet numberStyle = style(new Color(138, 84, 0), false, false, null);
        private final SimpleAttributeSet stringStyle = style(new Color(170, 35, 35), false, false, null);
        private final SimpleAttributeSet commentStyle = style(new Color(40, 130, 40), false, true, null);
        private final SimpleAttributeSet parenStyle = style(new Color(95, 95, 95), true, false, null);
        private final SimpleAttributeSet errorStyle = style(new Color(170, 0, 0), true, false, new Color(255, 220, 220));

        private boolean applyingStyles = false;
        private boolean applyingCompletion = false;
        private boolean autocompleteEnabled = false;

        LispSyntaxTextPane(EditorKind kind, String initialText, Consumer<SyntaxReport> reportConsumer) {
            this.kind = kind;
            this.reportConsumer = reportConsumer;
            setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            setText(initialText);
            initAutocompleteUi();
            installEditorActions();
            getDocument().addUndoableEditListener(e -> {
                if (!applyingStyles) {
                    undoManager.addEdit(e.getEdit());
                }
            });

            repaintTimer = new Timer(120, e -> applyHighlightingAndChecks());
            repaintTimer.setRepeats(false);

            getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    schedule();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    schedule();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    schedule();
                }

                private void schedule() {
                    if (!applyingStyles) {
                        if (!applyingCompletion && autocompleteEnabled) {
                            SwingUtilities.invokeLater(() -> autocompletePopup.setVisible(false));
                        }
                        repaintTimer.restart();
                    }
                }
            });

            SwingUtilities.invokeLater(this::applyHighlightingAndChecks);
        }

        private void installEditorActions() {
            InputMap inputMap = getInputMap();
            InputMap ancestorInputMap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            ActionMap actionMap = getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "lisp-auto-indent-enter");
            ancestorInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "lisp-auto-indent-enter");
            actionMap.put("lisp-auto-indent-enter", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (acceptSelectedAutocomplete()) {
                        return;
                    }
                    insertAutoIndentedNewline();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "lisp-format-buffer");
            ancestorInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "lisp-format-buffer");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | KeyEvent.SHIFT_DOWN_MASK), "lisp-format-buffer");
            actionMap.put("lisp-format-buffer", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (acceptSelectedAutocomplete()) {
                        return;
                    }
                    formatDocument();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_J,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "lisp-autocomplete-show");
            actionMap.put("lisp-autocomplete-show", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!autocompleteEnabled) {
                        return;
                    }
                    showAutocomplete(true);
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "lisp-autocomplete-next");
            ancestorInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "lisp-autocomplete-next");
            actionMap.put("lisp-autocomplete-next", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (autocompletePopup.isVisible()) {
                        moveAutocompleteSelection(1);
                        return;
                    }
                    Action delegate = getActionMap().get(DefaultEditorKit.downAction);
                    if (delegate != null) {
                        delegate.actionPerformed(e);
                    }
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "lisp-autocomplete-prev");
            ancestorInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "lisp-autocomplete-prev");
            actionMap.put("lisp-autocomplete-prev", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (autocompletePopup.isVisible()) {
                        moveAutocompleteSelection(-1);
                        return;
                    }
                    Action delegate = getActionMap().get(DefaultEditorKit.upAction);
                    if (delegate != null) {
                        delegate.actionPerformed(e);
                    }
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "lisp-autocomplete-hide");
            ancestorInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "lisp-autocomplete-hide");
            actionMap.put("lisp-autocomplete-hide", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (autocompletePopup.isVisible()) {
                        autocompletePopup.setVisible(false);
                    }
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "editor-undo");
            actionMap.put("editor-undo", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (undoManager.canUndo()) {
                            undoManager.undo();
                        }
                    } catch (CannotUndoException ignored) {
                    }
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | KeyEvent.SHIFT_DOWN_MASK), "editor-redo");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "editor-redo");
            actionMap.put("editor-redo", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (undoManager.canRedo()) {
                            undoManager.redo();
                        }
                    } catch (CannotRedoException ignored) {
                    }
                }
            });
        }

        private void initAutocompleteUi() {
            autocompleteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            autocompleteList.setVisibleRowCount(8);
            autocompleteList.setFont(getFont());
            autocompleteList.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "accept-completion");
            autocompleteList.getActionMap().put("accept-completion", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    acceptSelectedAutocomplete();
                }
            });
            autocompleteList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        acceptSelectedAutocomplete();
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(autocompleteList);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setPreferredSize(new Dimension(340, 160));
            autocompletePopup.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 210)));
            autocompletePopup.add(scrollPane);
        }

        private static void addCompletions(List<AutocompleteItem> target, String... values) {
            for (String value : values) {
                target.add(new AutocompleteItem(value, value, value, 10));
            }
        }

        private static void addCompletion(List<AutocompleteItem> target, String label, String insertion) {
            target.add(new AutocompleteItem(label, insertion, label, 10));
        }

        private static void addTemplateCompletion(List<AutocompleteItem> target, String label, String searchText, String insertion) {
            target.add(new AutocompleteItem(label, insertion, searchText + " " + label, 0));
        }

        private void showAutocomplete(boolean forced) {
            if (!autocompleteEnabled) {
                autocompletePopup.setVisible(false);
                return;
            }
            if (!isFocusOwner()) {
                autocompletePopup.setVisible(false);
                return;
            }

            String prefix = currentTokenPrefix();
            if (!forced && prefix.isBlank()) {
                autocompletePopup.setVisible(false);
                return;
            }

            List<AutocompleteItem> suggestions = collectCompletions(prefix, forced);
            if (suggestions.isEmpty()) {
                autocompletePopup.setVisible(false);
                return;
            }

            autocompleteList.setListData(suggestions.toArray(new AutocompleteItem[0]));
            autocompleteList.setSelectedIndex(0);

            try {
                Rectangle r = modelToView(getCaretPosition());
                int x = (r != null) ? r.x : 0;
                int y = (r != null) ? (r.y + r.height + 2) : 0;
                autocompletePopup.show(this, x, y);
            } catch (BadLocationException ex) {
                autocompletePopup.setVisible(false);
            }
        }

        private List<AutocompleteItem> collectCompletions(String prefix, boolean forced) {
            String p = prefix.toLowerCase();
            List<AutocompleteItem> source = new ArrayList<>(COMMON_COMPLETIONS.size() + 16);
            source.addAll(COMMON_COMPLETIONS);
            if (kind == EditorKind.DOMAIN) {
                source.addAll(DOMAIN_COMPLETIONS);
            } else {
                source.addAll(PROBLEM_COMPLETIONS);
            }

            List<AutocompleteItem> filtered = new ArrayList<>();
            for (AutocompleteItem item : source) {
                String key = item.label.toLowerCase();
                String searchText = item.searchText.toLowerCase();
                String normalizedKey = normalizeCompletionKey(item.label);
                String normalizedPrefix = normalizeCompletionKey(prefix);
                if (p.isBlank()) {
                    if (forced) {
                        filtered.add(item);
                    }
                    continue;
                }
                if (key.startsWith(p) || key.contains(p)
                        || searchText.startsWith(p) || searchText.contains(p)
                        || normalizedKey.startsWith(normalizedPrefix)
                        || normalizedKey.contains(normalizedPrefix)) {
                    filtered.add(item);
                }
            }

            filtered.sort(Comparator
                    .comparingInt((AutocompleteItem i) -> i.priority)
                    .thenComparing((AutocompleteItem i) -> !i.label.toLowerCase().startsWith(p))
                    .thenComparingInt(i -> i.label.length())
                    .thenComparing(i -> i.label));
            if (filtered.size() > 30) {
                return filtered.subList(0, 30);
            }
            return filtered;
        }

        private static String normalizeCompletionKey(String value) {
            return value.toLowerCase()
                    .replace("(", "")
                    .replace(")", "")
                    .replace(":", "")
                    .replace("...", "")
                    .trim();
        }

        private boolean acceptSelectedAutocomplete() {
            if (!autocompleteEnabled) {
                return false;
            }
            if (!autocompletePopup.isVisible()) {
                return false;
            }

            AutocompleteItem selected = autocompleteList.getSelectedValue();
            if (selected == null) {
                if (autocompleteList.getModel().getSize() > 0) {
                    selected = autocompleteList.getModel().getElementAt(0);
                } else {
                    autocompletePopup.setVisible(false);
                    return false;
                }
            }

            return acceptAutocompleteItem(selected);
        }

        private boolean acceptAutocompleteItem(AutocompleteItem selected) {
            int caret = getCaretPosition();
            String text = getText();
            int tokenStart = tokenStartOffset(text, caret);
            int replacementStart = tokenStart;
            if (replacementStart > 0
                    && text.charAt(replacementStart - 1) == '('
                    && selected.insertion.startsWith("(")) {
                replacementStart--;
            }
            String insertion = selected.insertion;
            int marker = insertion.indexOf('|');
            if (marker >= 0) {
                insertion = insertion.substring(0, marker) + insertion.substring(marker + 1);
            }

            applyingCompletion = true;
            try {
                getDocument().remove(replacementStart, caret - replacementStart);
                getDocument().insertString(replacementStart, insertion, null);
            } catch (BadLocationException ignored) {
                autocompletePopup.setVisible(false);
                return false;
            } finally {
                applyingCompletion = false;
            }

            int newCaret = replacementStart + ((marker >= 0) ? marker : insertion.length());
            setCaretPosition(Math.max(0, Math.min(newCaret, getDocument().getLength())));
            autocompletePopup.setVisible(false);
            return true;
        }

        void setAutocompleteEnabled(boolean enabled) {
            autocompleteEnabled = enabled;
            if (!enabled) {
                autocompletePopup.setVisible(false);
            }
        }

        private void moveAutocompleteSelection(int delta) {
            int size = autocompleteList.getModel().getSize();
            if (size <= 0) {
                return;
            }
            int current = Math.max(0, autocompleteList.getSelectedIndex());
            int next = (current + delta + size) % size;
            autocompleteList.setSelectedIndex(next);
            autocompleteList.ensureIndexIsVisible(next);
        }

        private String currentTokenPrefix() {
            int caret = getCaretPosition();
            String text = getText();
            int start = tokenStartOffset(text, caret);
            if (start >= caret || start < 0 || caret > text.length()) {
                return "";
            }
            return text.substring(start, caret).trim();
        }

        private static int tokenStartOffset(String text, int caret) {
            int i = Math.max(0, Math.min(caret, text.length()));
            while (i > 0 && isTokenChar(text.charAt(i - 1))) {
                i--;
            }
            return i;
        }

        private static boolean isTokenChar(char c) {
            return !Character.isWhitespace(c) && c != '(' && c != ')' && c != '"' && c != ';';
        }

        private void insertAutoIndentedNewline() {
            String text = getText();
            int caret = getCaretPosition();
            int currentLineStart = lineStartOffset(text, caret);
            String linePrefix = text.substring(currentLineStart, caret);
            int baseIndent = countLeadingSpaces(linePrefix);

            int openParens = countUnclosedParensIgnoringStringsAndComments(text, 0, caret);
            int indent = Math.max(baseIndent, openParens * 2);

            int next = skipSpaces(text, caret);
            if (next < text.length() && text.charAt(next) == ')' && indent >= 2) {
                indent -= 2;
            }

            String insertion = "\n" + " ".repeat(Math.max(0, indent));
            replaceSelection(insertion);
        }

        void formatDocument() {
            int oldCaret = getCaretPosition();
            String formatted = formatLisp(getText());
            setText(formatted);
            setCaretPosition(Math.min(oldCaret, formatted.length()));
            applyHighlightingAndChecks();
        }

        private static String formatLisp(String text) {
            StringBuilder out = new StringBuilder(text.length() + 64);
            int indentLevel = 0;
            boolean inString = false;
            boolean inComment = false;
            boolean atLineStart = true;

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);

                if (inComment) {
                    out.append(c);
                    if (c == '\n') {
                        inComment = false;
                        atLineStart = true;
                    }
                    continue;
                }

                if (inString) {
                    out.append(c);
                    if (c == '"' && !isEscaped(text, i)) {
                        inString = false;
                    }
                    if (c == '\n') {
                        atLineStart = true;
                    } else {
                        atLineStart = false;
                    }
                    continue;
                }

                if (c == ';') {
                    if (atLineStart) {
                        out.append(" ".repeat(Math.max(0, indentLevel * 2)));
                    }
                    out.append(c);
                    inComment = true;
                    atLineStart = false;
                    continue;
                }

                if (c == '"') {
                    if (atLineStart) {
                        out.append(" ".repeat(Math.max(0, indentLevel * 2)));
                        atLineStart = false;
                    }
                    out.append(c);
                    inString = true;
                    continue;
                }

                if (c == '\n' || c == '\r') {
                    if (out.length() > 0 && out.charAt(out.length() - 1) == ' ') {
                        out.deleteCharAt(out.length() - 1);
                    }
                    out.append('\n');
                    atLineStart = true;
                    continue;
                }

                if (Character.isWhitespace(c)) {
                    if (!atLineStart && out.length() > 0 && out.charAt(out.length() - 1) != ' ' && out.charAt(out.length() - 1) != '\n') {
                        out.append(' ');
                    }
                    continue;
                }

                if (c == ')') {
                    if (atLineStart) {
                        int dedented = Math.max(0, indentLevel - 1);
                        out.append(" ".repeat(dedented * 2));
                        atLineStart = false;
                    }
                    out.append(c);
                    indentLevel = Math.max(0, indentLevel - 1);
                    continue;
                }

                if (c == '(') {
                    if (atLineStart) {
                        out.append(" ".repeat(Math.max(0, indentLevel * 2)));
                        atLineStart = false;
                    }
                    out.append(c);
                    indentLevel++;
                    continue;
                }

                if (atLineStart) {
                    out.append(" ".repeat(Math.max(0, indentLevel * 2)));
                    atLineStart = false;
                }
                out.append(c);
            }

            return out.toString().replaceAll("\\n{3,}", "\n\n");
        }

        private static int countUnclosedParensIgnoringStringsAndComments(String text, int from, int to) {
            boolean inString = false;
            boolean inComment = false;
            int depth = 0;
            for (int i = from; i < to && i < text.length(); i++) {
                char c = text.charAt(i);
                if (inComment) {
                    if (c == '\n') {
                        inComment = false;
                    }
                    continue;
                }
                if (inString) {
                    if (c == '"' && !isEscaped(text, i)) {
                        inString = false;
                    }
                    continue;
                }
                if (c == ';') {
                    inComment = true;
                    continue;
                }
                if (c == '"') {
                    inString = true;
                    continue;
                }
                if (c == '(') {
                    depth++;
                } else if (c == ')' && depth > 0) {
                    depth--;
                }
            }
            return depth;
        }

        private static int lineStartOffset(String text, int offset) {
            int i = Math.max(0, Math.min(offset, text.length()));
            while (i > 0 && text.charAt(i - 1) != '\n') {
                i--;
            }
            return i;
        }

        private static int skipSpaces(String text, int from) {
            int i = from;
            while (i < text.length() && (text.charAt(i) == ' ' || text.charAt(i) == '\t')) {
                i++;
            }
            return i;
        }

        private static int countLeadingSpaces(String lineText) {
            int count = 0;
            while (count < lineText.length() && lineText.charAt(count) == ' ') {
                count++;
            }
            return count;
        }

        private static SimpleAttributeSet style(Color fg, boolean bold, boolean italic, Color bg) {
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setForeground(set, fg);
            StyleConstants.setBold(set, bold);
            StyleConstants.setItalic(set, italic);
            if (bg != null) {
                StyleConstants.setBackground(set, bg);
            }
            return set;
        }

        private void applyHighlightingAndChecks() {
            if (applyingStyles) {
                return;
            }

            applyingStyles = true;
            try {
                StyledDocument doc = getStyledDocument();
                String text = getText();
                int n = text.length();

                doc.setCharacterAttributes(0, n, normalStyle, true);

                boolean inString = false;
                boolean inComment = false;
                int tokenStart = -1;
                ArrayDeque<Integer> openParens = new ArrayDeque<>();
                List<String> diagnostics = new ArrayList<>();
                List<Integer> errorOffsets = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    char c = text.charAt(i);

                    if (inComment) {
                        if (c == '\n') {
                            doc.setCharacterAttributes(tokenStart, i - tokenStart, commentStyle, true);
                            inComment = false;
                            tokenStart = -1;
                        }
                        continue;
                    }

                    if (inString) {
                        if (c == '"' && !isEscaped(text, i)) {
                            doc.setCharacterAttributes(tokenStart, i - tokenStart + 1, stringStyle, true);
                            inString = false;
                            tokenStart = -1;
                        }
                        continue;
                    }

                    if (c == ';') {
                        inComment = true;
                        tokenStart = i;
                        continue;
                    }

                    if (c == '"') {
                        inString = true;
                        tokenStart = i;
                        continue;
                    }

                    if (c == '(') {
                        openParens.push(i);
                        doc.setCharacterAttributes(i, 1, parenStyle, true);
                        continue;
                    }

                    if (c == ')') {
                        if (openParens.isEmpty()) {
                            errorOffsets.add(i);
                            diagnostics.add("Unexpected ')' at " + lineCol(text, i));
                        } else {
                            openParens.pop();
                        }
                        doc.setCharacterAttributes(i, 1, parenStyle, true);
                        continue;
                    }

                    if (Character.isWhitespace(c)) {
                        continue;
                    }

                    int end = i;
                    while (end < n) {
                        char t = text.charAt(end);
                        if (Character.isWhitespace(t) || t == '(' || t == ')' || t == '"' || t == ';') {
                            break;
                        }
                        end++;
                    }

                    String token = text.substring(i, end);
                    String lower = token.toLowerCase();
                    if (KEYWORDS.contains(lower)) {
                        doc.setCharacterAttributes(i, end - i, keywordStyle, true);
                    } else if (isNumberToken(token)) {
                        doc.setCharacterAttributes(i, end - i, numberStyle, true);
                    }

                    i = end - 1;
                }

                if (inComment && tokenStart >= 0) {
                    doc.setCharacterAttributes(tokenStart, n - tokenStart, commentStyle, true);
                }

                if (inString && tokenStart >= 0) {
                    doc.setCharacterAttributes(tokenStart, n - tokenStart, stringStyle, true);
                    errorOffsets.add(tokenStart);
                    diagnostics.add("Unclosed string starting at " + lineCol(text, tokenStart));
                }

                while (!openParens.isEmpty()) {
                    int off = openParens.pop();
                    errorOffsets.add(off);
                    diagnostics.add("Unclosed '(' at " + lineCol(text, off));
                }

                String lowered = text.toLowerCase();
                if (!lowered.contains("(define")) {
                    diagnostics.add("Missing top-level '(define ...)'.");
                }
                if (kind == EditorKind.DOMAIN && !lowered.contains("(domain")) {
                    diagnostics.add("Domain editor: missing '(domain ... )'.");
                }
                if (kind == EditorKind.PROBLEM && !lowered.contains("(:domain")) {
                    diagnostics.add("Problem editor: missing '(:domain ...)'.");
                }

                for (Integer off : errorOffsets) {
                    if (off >= 0 && off < n) {
                        doc.setCharacterAttributes(off, 1, errorStyle, true);
                    }
                }

                if (diagnostics.isEmpty()) {
                    reportConsumer.accept(new SyntaxReport(true, "Syntax check: OK"));
                } else {
                    reportConsumer.accept(new SyntaxReport(false, "Syntax check: " + diagnostics.get(0)));
                }
            } catch (RuntimeException e) {
                reportConsumer.accept(new SyntaxReport(false, "Syntax highlighter internal error: " + e.getMessage()));
            } finally {
                applyingStyles = false;
            }
        }

        private static boolean isEscaped(String text, int quoteIndex) {
            int count = 0;
            for (int i = quoteIndex - 1; i >= 0 && text.charAt(i) == '\\'; i--) {
                count++;
            }
            return (count % 2) == 1;
        }

        private static boolean isNumberToken(String token) {
            return token.matches("[+-]?\\d+(\\.\\d+)?");
        }

        private static String lineCol(String text, int offset) {
            int line = 1;
            int col = 1;
            for (int i = 0; i < offset && i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    line++;
                    col = 1;
                } else {
                    col++;
                }
            }
            return "line " + line + ", col " + col;
        }

        private static final class AutocompleteItem {
            private final String label;
            private final String insertion;
            private final String searchText;
            private final int priority;

            private AutocompleteItem(String label, String insertion, String searchText, int priority) {
                this.label = label;
                this.insertion = insertion;
                this.searchText = searchText;
                this.priority = priority;
            }

            @Override
            public String toString() {
                return label;
            }
        }
    }
}
