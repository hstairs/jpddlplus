package com.hstairs.enhspgui;

import com.hstairs.enhspgui.approx.ApproxPddlTranslator;
import com.hstairs.enhspgui.tree.LazySearchTreeWindow;
import com.hstairs.ppmajal.PDDLProblem.PDDLSolution;
import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;
import enhsp2.ENHSP;
import org.apache.commons.lang3.tuple.ImmutablePair;

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
import java.math.BigDecimal;
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
        private final LispSyntaxTextPane domainArea;
        private final LispSyntaxTextPane problemArea;
        private final DefaultListModel<String> planListModel;
        private final JList<String> planList;
        private final JButton viewStateButton;
        private final JTextArea statsArea;
        private final JTextField quickTimeoutField;
        private final JButton runButton;
        private final JButton pauseButton;
        private final JButton stopButton;
        private boolean autocompleteEnabled = false;
        private boolean vsCodeIntegrationEnabled = true;
        private boolean vsCodeAutoReloadEnabled = true;
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
        private PlannerExecutionController executionController;
        private volatile Thread planningThread;
        private boolean pauseRequested;
        private int editorFontSize = 13;
        private final StringBuilder liveStatsBuffer = new StringBuilder();
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
            setAutocompleteEnabled(autocompleteEnabled);
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
            JPanel planPanel = new JPanel(new BorderLayout());
            planPanel.add(new JScrollPane(planList), BorderLayout.CENTER);
            JPanel planBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
            planBottom.add(viewStateButton);
            planPanel.add(planBottom, BorderLayout.SOUTH);
            statsArea = new JTextArea();
            statsArea.setEditable(false);
            statsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

            JSplitPane resultSplit = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    wrapped("Plan", planPanel, null),
                    wrapped("Statistics", new JScrollPane(statsArea), null)
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
            fileMenu.addSeparator();
            JMenuItem editDomainInVsCode = new JMenuItem("Edit Domain in VS Code");
            editDomainInVsCode.addActionListener(e -> openDomainInVsCode());
            fileMenu.add(editDomainInVsCode);
            JMenuItem editProblemInVsCode = new JMenuItem("Edit Problem in VS Code");
            editProblemInVsCode.addActionListener(e -> openProblemInVsCode());
            fileMenu.add(editProblemInVsCode);
            JMenuItem reloadFromVsCode = new JMenuItem("Reload From VS Code Files");
            reloadFromVsCode.addActionListener(e -> reloadFromVsCodeFiles());
            fileMenu.add(reloadFromVsCode);
            bar.add(fileMenu);

            JMenu editMenu = new JMenu("Edit");
            JMenuItem formatDomain = new JMenuItem("Format Domain");
            formatDomain.addActionListener(e -> formatDomainEditor());
            editMenu.add(formatDomain);
            JMenuItem formatProblem = new JMenuItem("Format Problem");
            formatProblem.addActionListener(e -> formatProblemEditor());
            editMenu.add(formatProblem);
            editMenu.addSeparator();
            JCheckBoxMenuItem autocompleteToggle = new JCheckBoxMenuItem("Auto-completion", autocompleteEnabled);
            autocompleteToggle.addActionListener(e -> setAutocompleteEnabled(autocompleteToggle.isSelected()));
            editMenu.add(autocompleteToggle);
            JCheckBoxMenuItem vsCodeToggle = new JCheckBoxMenuItem("VS Code External Editing", vsCodeIntegrationEnabled);
            vsCodeToggle.addActionListener(e -> setVsCodeIntegrationEnabled(vsCodeToggle.isSelected()));
            editMenu.add(vsCodeToggle);
            JCheckBoxMenuItem vsCodeAutoReloadToggle = new JCheckBoxMenuItem("VS Code Auto-reload", vsCodeAutoReloadEnabled);
            vsCodeAutoReloadToggle.addActionListener(e -> vsCodeAutoReloadEnabled = vsCodeAutoReloadToggle.isSelected());
            editMenu.add(vsCodeAutoReloadToggle);
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
            JMenuItem setActiveNodes = new JMenuItem("Set Active Nodes Limit...");
            setActiveNodes.addActionListener(e -> {
                LazySearchTreeWindow w = ensureSearchTreeWindow();
                String input = JOptionPane.showInputDialog(this, "Active nodes limit:", String.valueOf(w.getActiveNodeLimit()));
                if (input == null) {
                    return;
                }
                try {
                    int v = Integer.parseInt(input.trim());
                    w.setActiveNodeLimit(v);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please insert a valid integer.", "Invalid number", JOptionPane.ERROR_MESSAGE);
                }
            });
            configMenu.add(setActiveNodes);
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
                    "Guida Rapida: Modellare in PDDL\n" +
                    "===============================\n\n" +
                    "1) Struttura del DOMAIN\n" +
                    "-----------------------\n" +
                    "- (define (domain NOME))\n" +
                    "- (:requirements ...)\n" +
                    "- (:predicates ...)\n" +
                    "- (:functions ...)   ; se usi variabili numeriche\n" +
                    "- (:action ...)\n" +
                    "  :parameters (...)\n" +
                    "  :precondition (and ...)\n" +
                    "  :effect (and ...)\n\n" +
                    "2) Struttura del PROBLEM\n" +
                    "------------------------\n" +
                    "- (define (problem NOME-PROBLEM))\n" +
                    "- (:domain NOME-DOMAIN)\n" +
                    "- (:objects ...)\n" +
                    "- (:init ...)\n" +
                    "- (:goal (and ...))\n" +
                    "- (:metric minimize|maximize (...))   ; opzionale\n\n" +
                    "3) Buone pratiche di modellazione\n" +
                    "---------------------------------\n" +
                    "- Tieni separati fatti booleani (:predicates) e quantità numeriche (:functions).\n" +
                    "- Metti in :init tutti i fatti iniziali e i valori numerici con (= (f ...) val).\n" +
                    "- Scrivi precondizioni il più possibile locali all'azione.\n" +
                    "- In :goal usa condizioni verificabili sullo stato finale.\n\n" +
                    "Sintassi Friendly (~PDDL) di questa GUI\n" +
                    "=======================================\n" +
                    "Questa GUI supporta una forma più naturale per le espressioni numeriche.\n" +
                    "Puoi scrivere input ibrido PDDL / ~PDDL: la parte standard PDDL resta invariata,\n" +
                    "la parte friendly viene tradotta automaticamente in PDDL puro.\n\n" +
                    "Regole principali\n" +
                    "-----------------\n" +
                    "1) Espressioni infisse\n" +
                    "   (x + y * 2), (a - b), (n / d)\n" +
                    "   -> convertite in forma prefissa PDDL.\n\n" +
                    "2) Chiamate funzione naturali\n" +
                    "   fuel()     -> (fuel)\n" +
                    "   weight(a)  -> (weight a)\n" +
                    "   dist(x,y)  -> (dist x y)\n\n" +
                    "3) Confronti infissi\n" +
                    "   (weight(a) <= grip-limit()) -> (<= (weight a) (grip-limit))\n" +
                    "   (fuel() > 0)                -> (> (fuel) 0)\n\n" +
                    "4) Assegnamenti in :effect\n" +
                    "   (fuel() = fuel() + 2) -> (increase (fuel) 2)\n" +
                    "   (fuel() = fuel() - 1) -> (decrease (fuel) 1)\n" +
                    "   (x() = y())           -> (assign (x) (y))\n\n" +
                    "5) Assegnamenti booleani\n" +
                    "   (ready() = T) -> (ready)\n" +
                    "   (ready() = F) -> (not (ready))\n\n" +
                    "Nota\n" +
                    "----\n" +
                    "Per casi complessi/annidati conviene usare direttamente la forma PDDL standard.\n"
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
            JFileChooser chooser = lastLoadedDirectory == null
                    ? new JFileChooser()
                    : new JFileChooser(lastLoadedDirectory.toFile());
            chooser.setDialogTitle("Load PDDL file");
            int res = chooser.showOpenDialog(this);
            if (res != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            Path file = chooser.getSelectedFile().toPath();
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
            Path target = chooser.getSelectedFile().toPath();
            Path parent = target.getParent();
            if (parent != null) {
                lastLoadedDirectory = parent;
            }
            return target;
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

        private void setAutocompleteEnabled(boolean enabled) {
            autocompleteEnabled = enabled;
            domainArea.setAutocompleteEnabled(enabled);
            problemArea.setAutocompleteEnabled(enabled);
        }

        private void setVsCodeIntegrationEnabled(boolean enabled) {
            vsCodeIntegrationEnabled = enabled;
            if (!enabled) {
                if (vsCodePushTimer.isRunning()) {
                    vsCodePushTimer.stop();
                }
                return;
            }
            try {
                ensureVsCodeFiles();
                syncEditorsToVsCodeFiles();
            } catch (IOException e) {
                vsCodeIntegrationEnabled = false;
                JOptionPane.showMessageDialog(this,
                        "Cannot enable VS Code integration:\n" + e.getMessage(),
                        "VS Code Integration Error",
                        JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this,
                        "Enable 'Edit > VS Code External Editing' first.",
                        "VS Code Integration",
                        JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(this,
                        "Enable 'Edit > VS Code External Editing' first.",
                        "VS Code Integration",
                        JOptionPane.INFORMATION_MESSAGE);
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
            pauseRequested = false;
            pauseButton.setText("Pause");
            latestPlanningResult = null;
            setPlanMessages("Planning in progress...");
            statsArea.setText("Planning in progress...\n\nLive search trace:\n");
            synchronized (liveStatsBuffer) {
                liveStatsBuffer.setLength(0);
            }
            executionController = new PlannerExecutionController();
            final boolean searchTreeForThisRun = showSearchTree;
            final boolean liveSearchTreeForThisRun = showSearchTree && liveSearchTree;
            if (showSearchTree) {
                LazySearchTreeWindow treeWindow = ensureSearchTreeWindow();
                treeWindow.reset();
                 treeWindow.setLiveJsonMode(liveSearchTreeForThisRun);
                treeWindow.showWindow();
            }
            executionController.setSearchTreeWindow(liveSearchTreeForThisRun ? searchTreeWindow : null);

            SwingWorker<PlanningResult, Void> worker = new SwingWorker<>() {
                @Override
                protected PlanningResult doInBackground() {
                    Path tmpDir = null;
                    PrintStream originalOut = null;
                    PrintStream redirectedOut = null;
                    try {
                        planningThread = Thread.currentThread();
                        synchronized (STDOUT_REDIRECT_LOCK) {
                            originalOut = System.out;
                            LineCaptureOutputStream liveParser = new LineCaptureOutputStream(PlanningFrame.this::handleStdoutLine);
                            redirectedOut = new PrintStream(new TeeOutputStream(originalOut, liveParser), true, StandardCharsets.UTF_8);
                            System.setOut(redirectedOut);

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
                            return formatSolution(solution, planner.getProblem(), debugMode ? domainTextForPlanning : null, debugMode ? problemTextForPlanning : null);
                        }
                    } catch (PlanningStoppedException e) {
                        return PlanningResult.error("Planning stopped by user.", null, null);
                    } catch (Throwable t) {
                        if (executionController != null && executionController.isStopped()) {
                            return PlanningResult.error("Planning stopped by user.", null, null);
                        }
                        return PlanningResult.error("Planning failed:\n" + t, null, null);
                    } finally {
                        planningThread = null;
                        if (redirectedOut != null) {
                            redirectedOut.flush();
                            redirectedOut.close();
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
                            statsArea.setText("Planning stopped by user.\n\nLive search trace:\n" + getLiveStatsSnapshot());
                            viewStateButton.setEnabled(false);
                        } else {
                            PlanningResult result = get();
                            latestPlanningResult = result;
                            setPlanResult(result);
                            statsArea.setText(result.statsText + "\n\nLive search trace:\n" + getLiveStatsSnapshot());
                            viewStateButton.setEnabled(result.hasTrace());
                            if (searchTreeForThisRun) {
                                loadLatestSearchTreeJsonIfPresent();
                            }
                        }
                    } catch (Exception e) {
                        latestPlanningResult = null;
                        setPlanMessages("Planning failed:", String.valueOf(e));
                        statsArea.setText("Planning failed:\n" + e);
                        viewStateButton.setEnabled(false);
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

        private void handleStdoutLine(String line) {
            String interesting = extractInterestingLiveStat(line);
            if (interesting == null) {
                return;
            }
            synchronized (liveStatsBuffer) {
                liveStatsBuffer.append(interesting).append('\n');
            }
            SwingUtilities.invokeLater(() -> {
                if (currentWorker != null) {
                    statsArea.append(interesting + "\n");
                    statsArea.setCaretPosition(statsArea.getDocument().getLength());
                }
            });
        }

        private String extractInterestingLiveStat(String rawLine) {
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
            String action = result.actionLines.get(actionStepIndex);
            String before = actionStepIndex < result.actionStateBefore.size() ? result.actionStateBefore.get(actionStepIndex) : "N/A";
            String after = actionStepIndex < result.actionStateAfter.size() ? result.actionStateAfter.get(actionStepIndex) : "N/A";

            JTabbedPane tabs = new JTabbedPane();

            JTextArea diffArea = new JTextArea();
            diffArea.setEditable(false);
            diffArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            diffArea.setText("Action: " + action + "\n\n" + buildStateDiff(before, after));
            tabs.add("Differences", new JScrollPane(diffArea));

            JTextArea fullArea = new JTextArea();
            fullArea.setEditable(false);
            fullArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            fullArea.setText("Action: " + action + "\n\nState before:\n" + before + "\n\nState after:\n" + after);
            tabs.add("Full States", new JScrollPane(fullArea));

            JTextArea varsArea = new JTextArea();
            varsArea.setEditable(false);
            varsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            varsArea.setText("Action: " + action + "\n\n" + buildVariableValuesView(before, after));
            tabs.add("Variables", new JScrollPane(varsArea));

            tabs.setPreferredSize(new Dimension(950, 620));
            JOptionPane.showMessageDialog(this, tabs, "State Trace - Step " + actionStepIndex, JOptionPane.INFORMATION_MESSAGE);
        }

        private String buildStateDiff(String before, String after) {
            java.util.Map<String, String> b = parseStateAssignments(before);
            java.util.Map<String, String> a = parseStateAssignments(after);
            java.util.Set<String> keys = new java.util.TreeSet<>();
            keys.addAll(b.keySet());
            keys.addAll(a.keySet());

            StringBuilder sb = new StringBuilder();
            int changes = 0;
            for (String k : keys) {
                String bv = b.get(k);
                String av = a.get(k);
                if (!java.util.Objects.equals(bv, av)) {
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

        private String buildVariableValuesView(String before, String after) {
            java.util.Map<String, String> b = parseStateAssignments(before);
            java.util.Map<String, String> a = parseStateAssignments(after);
            java.util.Set<String> keys = new java.util.TreeSet<>();
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
                if (!java.util.Objects.equals(bv, av)) {
                    sb.append("   *");
                }
                sb.append('\n');
            }
            return sb.toString();
        }

        private java.util.Map<String, String> parseStateAssignments(String stateText) {
            java.util.Map<String, String> map = new java.util.HashMap<>();
            if (stateText == null) {
                return map;
            }
            java.util.regex.Matcher valueMatcher = java.util.regex.Pattern
                    .compile("(\\([^\\)]*\\)|[^\\s=]+)=([^\\s]+)")
                    .matcher(stateText);
            while (valueMatcher.find()) {
                map.put(valueMatcher.group(1), valueMatcher.group(2));
            }
            java.util.regex.Matcher atomMatcher = java.util.regex.Pattern
                    .compile("(\\([^\\)]*\\))")
                    .matcher(stateText);
            while (atomMatcher.find()) {
                String atom = atomMatcher.group(1);
                map.putIfAbsent(atom, "true");
            }
            return map;
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

        private static PlanningResult formatSolution(PDDLSolution solution, com.hstairs.ppmajal.PDDLProblem.PDDLProblem problem,
                                                     String generatedDomainPddl, String generatedProblemPddl) {
            if (solution == null || solution.rawPlan() == null) {
                return PlanningResult.error("Problem unsolvable or no plan returned.", generatedDomainPddl, generatedProblemPddl);
            }
            StringBuilder statsBuilder = new StringBuilder();
            List<String> actionLines = new ArrayList<>();
            List<String> displayLines = new ArrayList<>();
            List<Integer> displayToActionStep = new ArrayList<>();
            statsBuilder.append("Plan found.\n");
            statsBuilder.append("Plan length: ").append(solution.rawPlan().size()).append('\n');
            statsBuilder.append("Search metric: ").append(solution.gValueAtTheEnd()).append('\n');
            statsBuilder.append("Expanded nodes: ").append(solution.stats().nodesExpanded()).append('\n');
            statsBuilder.append("Evaluated states: ").append(solution.stats().nodesEvaluated()).append('\n');
            statsBuilder.append("Dead ends: ").append(solution.stats().deadEnds()).append('\n');
            statsBuilder.append("Duplicates: ").append(solution.stats().duplicates()).append('\n');
            List<ActionDisplayEntry> entries = new ArrayList<>();
            List<Integer> rawStepToActionIndex = new ArrayList<>();
            boolean hasNonActionTransitions = false;
            int stepIndex = 0;
            int actionIndex = 0;
            for (ImmutablePair<BigDecimal, com.hstairs.ppmajal.transition.TransitionGround> planStep : solution.rawPlan()) {
                String action = planStep.getRight().toString();
                String timeKey = normalizeTimeKey(planStep.getLeft(), stepIndex);
                boolean isAction = planStep.getRight() != null
                        && planStep.getRight().getSemantics().equals(com.hstairs.ppmajal.transition.Transition.Semantics.ACTION);
                if (!isAction) {
                    hasNonActionTransitions = true;
                }
                int mappedActionIndex = -1;
                if (isAction) {
                    mappedActionIndex = actionIndex;
                    actionLines.add(action);
                    actionIndex++;
                }
                rawStepToActionIndex.add(mappedActionIndex);
                entries.add(new ActionDisplayEntry(stepIndex, mappedActionIndex, timeKey, action));
                stepIndex++;
            }

            if (!hasNonActionTransitions) {
                int index = 0;
                for (ActionDisplayEntry e : entries) {
                    if (e.actionIndex < 0 || isWaitingActionText(e.action)) {
                        continue;
                    }
                    displayLines.add(index + ": " + e.action);
                    displayToActionStep.add(e.actionIndex);
                    index++;
                }
            } else {
                int pos = 0;
                List<TimeGroup> groups = new ArrayList<>();
                while (pos < entries.size()) {
                    String currentTime = entries.get(pos).timeKey;
                    List<ActionDisplayEntry> group = new ArrayList<>();
                    while (pos < entries.size() && entries.get(pos).timeKey.equals(currentTime)) {
                        group.add(entries.get(pos));
                        pos++;
                    }

                    int waitingCount = 0;
                    List<ActionDisplayEntry> nonWaiting = new ArrayList<>();
                    for (ActionDisplayEntry e : group) {
                        if (isWaitingActionText(e.action)) {
                            waitingCount++;
                            continue;
                        }
                        nonWaiting.add(e);
                    }
                    groups.add(new TimeGroup(currentTime, nonWaiting, waitingCount));
                }

                for (int i = 0; i < groups.size(); i++) {
                    TimeGroup g = groups.get(i);
                    if (g.nonWaitingActions.isEmpty()) {
                        continue;
                    }
                    for (ActionDisplayEntry e : g.nonWaitingActions) {
                        displayLines.add(g.timeKey + ": " + e.action);
                        displayToActionStep.add(e.actionIndex);
                    }

                    String nextActionTime = null;
                    int waitingBetween = 0;
                    for (int j = i + 1; j < groups.size(); j++) {
                        TimeGroup next = groups.get(j);
                        waitingBetween += next.waitingCount;
                        if (!next.nonWaitingActions.isEmpty()) {
                            nextActionTime = next.timeKey;
                            break;
                        }
                    }
                    if (waitingBetween > 0 && nextActionTime != null) {
                        displayLines.add(g.timeKey + ": -----waiting---- [" + nextActionTime + "]");
                        displayToActionStep.add(-1);
                    }
                }
            }

            List<String> actionStateBefore = new ArrayList<>();
            List<String> actionStateAfter = new ArrayList<>();
            try {
                com.hstairs.ppmajal.problem.State current = problem.getInit().clone();
                int rawIndex = 0;
                for (ImmutablePair<BigDecimal, com.hstairs.ppmajal.transition.TransitionGround> step : solution.rawPlan()) {
                    com.hstairs.ppmajal.transition.TransitionGround action = step.getRight();
                    int mappedActionIndex = rawStepToActionIndex.get(rawIndex);
                    if (mappedActionIndex >= 0 && action != null
                            && action.getSemantics().equals(com.hstairs.ppmajal.transition.Transition.Semantics.ACTION)) {
                        com.hstairs.ppmajal.problem.State before = current.clone();
                        com.hstairs.ppmajal.problem.State prevForApply = current.clone();
                        current.apply(action, prevForApply);
                        com.hstairs.ppmajal.problem.State after = current.clone();
                        actionStateBefore.add(String.valueOf(before));
                        actionStateAfter.add(String.valueOf(after));
                    }
                    rawIndex++;
                }
                statsBuilder.append("Trace states available: ").append(actionStateBefore.size()).append('\n');
            } catch (Exception e) {
                statsBuilder.append("Trace states unavailable: ").append(e.getMessage()).append('\n');
            }
            return new PlanningResult(
                    String.join("\n", displayLines),
                    statsBuilder.toString(),
                    actionLines,
                    actionStateBefore,
                    actionStateAfter,
                    displayLines,
                    displayToActionStep,
                    generatedDomainPddl,
                    generatedProblemPddl
            );
        }

        private static String normalizeTimeKey(BigDecimal time, int stepIndex) {
            if (time == null) {
                return "step " + stepIndex;
            }
            BigDecimal normalized = time.stripTrailingZeros();
            if (normalized.compareTo(BigDecimal.ZERO) == 0) {
                return "0";
            }
            return normalized.toPlainString();
        }

        private static boolean isWaitingActionText(String action) {
            if (action == null) {
                return false;
            }
            String a = action.trim().toLowerCase();
            return a.equals("(waiting)") || a.contains("waiting");
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

    private static final class SyntaxReport {
        final boolean ok;
        final String message;

        SyntaxReport(boolean ok, String message) {
            this.ok = ok;
            this.message = message;
        }
    }

    private static final class PlanningResult {
        final String planText;
        final String statsText;
        final List<String> actionLines;
        final List<String> actionStateBefore;
        final List<String> actionStateAfter;
        final List<String> displayLines;
        final List<Integer> displayToActionStep;
        final String generatedDomainPddl;
        final String generatedProblemPddl;

        PlanningResult(String planText, String statsText, List<String> actionLines, List<String> actionStateBefore, List<String> actionStateAfter,
                       List<String> displayLines, List<Integer> displayToActionStep,
                       String generatedDomainPddl, String generatedProblemPddl) {
            this.planText = planText;
            this.statsText = statsText;
            this.actionLines = actionLines;
            this.actionStateBefore = actionStateBefore;
            this.actionStateAfter = actionStateAfter;
            this.displayLines = displayLines;
            this.displayToActionStep = displayToActionStep;
            this.generatedDomainPddl = generatedDomainPddl;
            this.generatedProblemPddl = generatedProblemPddl;
        }

        static PlanningResult error(String message, String generatedDomainPddl, String generatedProblemPddl) {
            return new PlanningResult(message, message, List.of(), List.of(), List.of(), List.of(message), List.of(-1),
                    generatedDomainPddl, generatedProblemPddl);
        }

        boolean hasTrace() {
            return !actionLines.isEmpty()
                    && actionStateBefore.size() == actionLines.size()
                    && actionStateAfter.size() == actionLines.size();
        }

        boolean hasGeneratedPddl() {
            return generatedDomainPddl != null && generatedProblemPddl != null;
        }
    }

    private static final class ActionDisplayEntry {
        final int originalStepIndex;
        final int actionIndex;
        final String timeKey;
        final String action;

        ActionDisplayEntry(int originalStepIndex, int actionIndex, String timeKey, String action) {
            this.originalStepIndex = originalStepIndex;
            this.actionIndex = actionIndex;
            this.timeKey = timeKey;
            this.action = action;
        }
    }

    private static final class TimeGroup {
        final String timeKey;
        final List<ActionDisplayEntry> nonWaitingActions;
        final int waitingCount;

        TimeGroup(String timeKey, List<ActionDisplayEntry> nonWaitingActions, int waitingCount) {
            this.timeKey = timeKey;
            this.nonWaitingActions = nonWaitingActions;
            this.waitingCount = waitingCount;
        }
    }

    private static final class PlanningStoppedException extends RuntimeException {
        PlanningStoppedException() {
            super("Planning stopped");
        }
    }

    private static final class PlannerCliOptions {
        String planner = "";
        String heuristic = "hadd";
        String search = "gbfs";
        String novelty = "";
        String kNov = "";
        String tieBreaking = "arbitrary";
        String redundantConstraints = "no";
        String grounding = "internal";
        String sdac = "disabled";
        String wh = "";
        String deltaPlanning = "";
        String deltaExecution = "";
        String deltaHeuristic = "";
        String deltaValidation = "";
        String delta = "";
        String depthLimit = "";
        String timeout = "";
        String kSubdomains = "";
        String tolerance = "";
        String inputPlan = "";
        String savePlan = "";
        String posthocLogger = "";
        String effectAbstraction = "";
        String customArgs = "";

        boolean helpfulActions;
        boolean helpfulTransitions;
        boolean printEvents;
        boolean printTrace;
        boolean ignoreMetric;
        boolean disableAibrPreprocessing;
        boolean stopAfterGrounding;
        boolean internalValidation;
        boolean onlyPlan;
        boolean printActions;
        boolean silent;
        boolean autoAnytime;
        boolean anytime;
        boolean unitCostHeuristic;
        boolean noPrintMakespan;
        boolean printAllInfo;
        boolean bucketBasedQueueSearch;
        boolean tunnelling;
        boolean saveSearchJson;

        static PlannerCliOptions defaults() {
            return new PlannerCliOptions();
        }

        PlannerCliOptions copy() {
            PlannerCliOptions c = new PlannerCliOptions();
            c.planner = planner;
            c.heuristic = heuristic;
            c.search = search;
            c.novelty = novelty;
            c.kNov = kNov;
            c.tieBreaking = tieBreaking;
            c.redundantConstraints = redundantConstraints;
            c.grounding = grounding;
            c.sdac = sdac;
            c.wh = wh;
            c.deltaPlanning = deltaPlanning;
            c.deltaExecution = deltaExecution;
            c.deltaHeuristic = deltaHeuristic;
            c.deltaValidation = deltaValidation;
            c.delta = delta;
            c.depthLimit = depthLimit;
            c.timeout = timeout;
            c.kSubdomains = kSubdomains;
            c.tolerance = tolerance;
            c.inputPlan = inputPlan;
            c.savePlan = savePlan;
            c.posthocLogger = posthocLogger;
            c.effectAbstraction = effectAbstraction;
            c.customArgs = customArgs;

            c.helpfulActions = helpfulActions;
            c.helpfulTransitions = helpfulTransitions;
            c.printEvents = printEvents;
            c.printTrace = printTrace;
            c.ignoreMetric = ignoreMetric;
            c.disableAibrPreprocessing = disableAibrPreprocessing;
            c.stopAfterGrounding = stopAfterGrounding;
            c.internalValidation = internalValidation;
            c.onlyPlan = onlyPlan;
            c.printActions = printActions;
            c.silent = silent;
            c.autoAnytime = autoAnytime;
            c.anytime = anytime;
            c.unitCostHeuristic = unitCostHeuristic;
            c.noPrintMakespan = noPrintMakespan;
            c.printAllInfo = printAllInfo;
            c.bucketBasedQueueSearch = bucketBasedQueueSearch;
            c.tunnelling = tunnelling;
            c.saveSearchJson = saveSearchJson;
            return c;
        }

        void appendArgs(List<String> args) {
            addArgWithValue(args, "-planner", planner);
            addArgWithValue(args, "-h", heuristic);
            addArgWithValue(args, "-s", search);
            addArgWithValue(args, "-nov", novelty);
            addArgWithValue(args, "-knov", kNov);
            addArgWithValue(args, "-ties", tieBreaking);
            addArgWithValue(args, "-red", redundantConstraints);
            addArgWithValue(args, "-gro", grounding);
            addArgWithValue(args, "-sdac", sdac);
            addArgWithValue(args, "-wh", wh);
            addArgWithValue(args, "-dp", deltaPlanning);
            addArgWithValue(args, "-de", deltaExecution);
            addArgWithValue(args, "-dh", deltaHeuristic);
            addArgWithValue(args, "-dv", deltaValidation);
            addArgWithValue(args, "-d", delta);
            addArgWithValue(args, "-dl", depthLimit);
            addArgWithValue(args, "-timeout", timeout);
            addArgWithValue(args, "-k", kSubdomains);
            addArgWithValue(args, "-tolerance", tolerance);
            addArgWithValue(args, "-inputplan", inputPlan);
            addArgWithValue(args, "-sp", savePlan);
            addArgWithValue(args, "-with_posthoc_logger", posthocLogger);
            addArgWithValue(args, "-ea", effectAbstraction);

            addFlag(args, "-ha", helpfulActions);
            addFlag(args, "-ht", helpfulTransitions);
            addPlainFlag(args, "-pe", printEvents);
            addPlainFlag(args, "-pt", printTrace);
            addPlainFlag(args, "-im", ignoreMetric);
            addPlainFlag(args, "-dap", disableAibrPreprocessing);
            addPlainFlag(args, "-stopgro", stopAfterGrounding);
            addPlainFlag(args, "-ival", internalValidation);
            addPlainFlag(args, "-onlyplan", onlyPlan);
            addPlainFlag(args, "-print_actions", printActions);
            addPlainFlag(args, "-silent", silent);
            addPlainFlag(args, "-autoanytime", autoAnytime);
            addPlainFlag(args, "-anytime", anytime);
            addPlainFlag(args, "-uch", unitCostHeuristic);
            addPlainFlag(args, "-npm", noPrintMakespan);
            addPlainFlag(args, "-pai", printAllInfo);
            addPlainFlag(args, "-bbqs", bucketBasedQueueSearch);
            addPlainFlag(args, "-tun", tunnelling);
            addPlainFlag(args, "-sjr", saveSearchJson);

            // Must be appended last so custom args override presets/UI fields.
            args.addAll(tokenizeCliArgs(customArgs));
        }

        private static void addPlainFlag(List<String> args, String flag, boolean enabled) {
            if (enabled) {
                args.add(flag);
            }
        }

        private static void addFlag(List<String> args, String flag, boolean enabled) {
            if (enabled) {
                args.add(flag);
                args.add("true");
            }
        }

        private static void addArgWithValue(List<String> args, String flag, String value) {
            if (value != null && !value.isBlank()) {
                args.add(flag);
                args.add(value.trim());
            }
        }

        private static List<String> tokenizeCliArgs(String raw) {
            List<String> out = new ArrayList<>();
            if (raw == null || raw.isBlank()) {
                return out;
            }
            StringBuilder current = new StringBuilder();
            boolean inSingle = false;
            boolean inDouble = false;
            boolean escaped = false;
            for (int i = 0; i < raw.length(); i++) {
                char c = raw.charAt(i);
                if (escaped) {
                    current.append(c);
                    escaped = false;
                    continue;
                }
                if (c == '\\') {
                    escaped = true;
                    continue;
                }
                if (c == '\'' && !inDouble) {
                    inSingle = !inSingle;
                    continue;
                }
                if (c == '"' && !inSingle) {
                    inDouble = !inDouble;
                    continue;
                }
                if (Character.isWhitespace(c) && !inSingle && !inDouble) {
                    if (current.length() > 0) {
                        out.add(current.toString());
                        current.setLength(0);
                    }
                    continue;
                }
                current.append(c);
            }
            if (current.length() > 0) {
                out.add(current.toString());
            }
            return out;
        }
    }

    private static final class PlannerOptionsDialog extends JDialog {
        private PlannerCliOptions result;
        private final PlannerCliOptions working;

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

            planner = combo("", "sat-hmrp", "sat-hmrph", "sat-hmrphj", "sat-hmrpff", "sat-hadd", "sat-aibr", "sat-hradd", "opt-hmax", "opt-hlm", "opt-hlmrd", "opt-hrmax", "opt-blind");
            heuristic = combo("hadd", "blind", "hmax", "hmrp", "aibr", "hradd", "hrmax", "hlm-lp");
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
        }

        private JPanel buildCorePanel() {
            JPanel p = new JPanel(new GridLayout(0, 2, 8, 6));
            addField(p, "Planner preset", planner);
            addField(p, "Heuristic (-h)", heuristic);
            addField(p, "Search (-s)", search);
            addField(p, "Tie-breaking", ties);
            addField(p, "Novelty", novelty);
            addField(p, "k-novelty", kNov);
            addField(p, "Helpful weight (wh)", wh);
            addField(p, "Redundant constraints", red);
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
            p.add(ha); p.add(ht);
            p.add(pe); p.add(pt);
            p.add(im); p.add(dap);
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

        private static void addField(JPanel panel, String label, JComponent comp) {
            panel.add(new JLabel(label));
            panel.add(comp);
        }

        private static JComboBox<String> combo(String... values) {
            return new JComboBox<>(values);
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
    }

    private static final class PlannerExecutionController implements IExternalLogger {
        private final Object lock = new Object();
        private volatile boolean paused = false;
        private volatile boolean stopped = false;
        private volatile LazySearchTreeWindow searchTreeWindow;
        private volatile com.hstairs.ppmajal.conditions.Condition goalCondition;

        void setSearchTreeWindow(LazySearchTreeWindow searchTreeWindow) {
            this.searchTreeWindow = searchTreeWindow;
        }

        void setGoalCondition(com.hstairs.ppmajal.conditions.Condition goalCondition) {
            this.goalCondition = goalCondition;
        }

        void markSolutionNode(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node) {
            if (searchTreeWindow != null && node != null) {
                searchTreeWindow.markSolutionNode(node);
            }
        }

        @Override
        public void beforeExecution() {
            if (searchTreeWindow != null) {
                searchTreeWindow.onSearchStart();
            }
            waitIfPausedAndCheckStop();
        }

        @Override
        public void log(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node, ExternalLoggerLogType logType) {
            if (searchTreeWindow != null) {
                boolean isGoal = false;
                if (goalCondition != null && node != null && node.s != null) {
                    try {
                        isGoal = node.s.satisfy(goalCondition);
                    } catch (Exception ignored) {
                    }
                }
                searchTreeWindow.onLogEvent(node, logType, isGoal);
            }
            waitIfPausedAndCheckStop();
        }

        @Override
        public void afterExecution() {
            if (searchTreeWindow != null) {
                searchTreeWindow.onSearchEnd();
            }
            waitIfPausedAndCheckStop();
        }

        void pause() {
            paused = true;
        }

        void resume() {
            synchronized (lock) {
                paused = false;
                lock.notifyAll();
            }
        }

        void stop() {
            synchronized (lock) {
                stopped = true;
                paused = false;
                lock.notifyAll();
            }
        }

        boolean isStopped() {
            return stopped;
        }

        void checkStopped() {
            if (stopped || Thread.currentThread().isInterrupted()) {
                throw new PlanningStoppedException();
            }
        }

        private void waitIfPausedAndCheckStop() {
            synchronized (lock) {
                while (paused && !stopped) {
                    try {
                        lock.wait(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new PlanningStoppedException();
                    }
                }
            }
            if (stopped || Thread.currentThread().isInterrupted()) {
                throw new PlanningStoppedException();
            }
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
            addCompletion(COMMON_COMPLETIONS, ":predicates", ":predicates (|)");
            addCompletion(COMMON_COMPLETIONS, ":functions", ":functions (|)");
            addCompletion(COMMON_COMPLETIONS, ":action", "(:action ACTION_NAME\n :parameters (|)\n :precondition (and )\n :effect (and ))");
            addCompletion(COMMON_COMPLETIONS, ":parameters", ":parameters (|)");
            addCompletion(COMMON_COMPLETIONS, ":precondition", ":precondition (and |)");
            addCompletion(COMMON_COMPLETIONS, ":effect", ":effect (and |)");
            addCompletion(COMMON_COMPLETIONS, ":objects", ":objects |");
            addCompletion(COMMON_COMPLETIONS, ":init", ":init (|)");
            addCompletion(COMMON_COMPLETIONS, ":goal", ":goal (and |)");
            addCompletion(COMMON_COMPLETIONS, ":metric", ":metric minimize (|)");
            addCompletion(COMMON_COMPLETIONS, "(increase ...)", "(increase |)");
            addCompletion(COMMON_COMPLETIONS, "(decrease ...)", "(decrease |)");
            addCompletion(COMMON_COMPLETIONS, "(assign ...)", "(assign |)");
            addCompletion(COMMON_COMPLETIONS, "(scale-up ...)", "(scale-up |)");
            addCompletion(COMMON_COMPLETIONS, "(scale-down ...)", "(scale-down |)");

            addCompletion(DOMAIN_COMPLETIONS, "domain skeleton",
                    "(define (domain DOMAIN_NAME)\n  (:requirements :strips :typing)\n  (:predicates\n    (p)\n  )\n  |\n)\n");
            addCompletion(DOMAIN_COMPLETIONS, "action skeleton",
                    "(:action ACTION_NAME\n :parameters (|)\n :precondition (and )\n :effect (and ))");
            addCompletion(DOMAIN_COMPLETIONS, "(domain ...)", "(domain |)");
            addCompletion(DOMAIN_COMPLETIONS, ":requirements", ":requirements |");
            addCompletion(DOMAIN_COMPLETIONS, ":predicates", ":predicates (|)");
            addCompletion(DOMAIN_COMPLETIONS, ":functions", ":functions (|)");

            addCompletion(PROBLEM_COMPLETIONS, "problem skeleton",
                    "(define (problem PROBLEM_NAME)\n  (:domain DOMAIN_NAME)\n  (:objects |)\n  (:init )\n  (:goal (and ))\n)\n");
            addCompletion(PROBLEM_COMPLETIONS, "(problem ...)", "(problem |)");
            addCompletion(PROBLEM_COMPLETIONS, "(:domain ...)", "(:domain |)");
            addCompletion(PROBLEM_COMPLETIONS, ":objects", ":objects |");
            addCompletion(PROBLEM_COMPLETIONS, ":init", ":init (|)");
            addCompletion(PROBLEM_COMPLETIONS, ":goal", ":goal (and |)");
            addCompletion(PROBLEM_COMPLETIONS, ":metric", ":metric minimize (|)");
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
                    if (acceptAutocompleteFromKeyboard()) {
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

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,
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
                target.add(new AutocompleteItem(value, value));
            }
        }

        private static void addCompletion(List<AutocompleteItem> target, String label, String insertion) {
            target.add(new AutocompleteItem(label, insertion));
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
                String normalizedKey = normalizeCompletionKey(item.label);
                String normalizedPrefix = normalizeCompletionKey(prefix);
                if (p.isBlank()) {
                    if (forced) {
                        filtered.add(item);
                    }
                    continue;
                }
                if (key.startsWith(p) || key.contains(p)
                        || normalizedKey.startsWith(normalizedPrefix)
                        || normalizedKey.contains(normalizedPrefix)) {
                    filtered.add(item);
                }
            }

            filtered.sort(Comparator
                    .comparing((AutocompleteItem i) -> !i.label.toLowerCase().startsWith(p))
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

        private boolean acceptAutocompleteFromKeyboard() {
            if (!autocompleteEnabled) {
                return false;
            }
            if (autocompletePopup.isVisible()) {
                return acceptSelectedAutocomplete();
            }

            String prefix = currentTokenPrefix();
            if (prefix.isBlank()) {
                return false;
            }

            List<AutocompleteItem> suggestions = collectCompletions(prefix, false);
            if (suggestions.isEmpty()) {
                return false;
            }
            return acceptAutocompleteItem(suggestions.get(0));
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

            private AutocompleteItem(String label, String insertion) {
                this.label = label;
                this.insertion = insertion;
            }

            @Override
            public String toString() {
                return label;
            }
        }
    }
}
