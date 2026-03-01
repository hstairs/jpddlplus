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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

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
        private final JTextField searchField;
        private final JTextField heuristicField;
        private final JCheckBox debugModeCheck;
        private final JButton runButton;
        private final JButton pauseButton;
        private final JButton stopButton;
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
        private Path lastLoadedDirectory = Path.of(System.getProperty("user.home"));

        PlanningFrame() {
            super("ENHSP-25 (Expressive Numeric Heuristic Search Planner) GUI");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(1200, 800);
            setLocationRelativeTo(null);

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
            controls.add(new JLabel("Search:"));
            searchField = new JTextField("gbfs", 10);
            controls.add(searchField);
            controls.add(new JLabel("Heuristic:"));
            heuristicField = new JTextField("hadd", 10);
            controls.add(heuristicField);
            debugModeCheck = new JCheckBox("Debug Mode");
            controls.add(debugModeCheck);
            JButton plannerOptionsButton = new JButton("Planner Options...");
            plannerOptionsButton.addActionListener(e -> openPlannerOptionsDialog());
            controls.add(plannerOptionsButton);
            runButton = new JButton("Run Planning");
            runButton.addActionListener(e -> runPlanning());
            styleActionButton(runButton, new Color(20, 145, 60), Color.WHITE);
            controls.add(runButton);
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
            bar.add(fileMenu);

            JMenu editMenu = new JMenu("Edit");
            JMenuItem formatDomain = new JMenuItem("Format Domain");
            formatDomain.addActionListener(e -> formatDomainEditor());
            editMenu.add(formatDomain);
            JMenuItem formatProblem = new JMenuItem("Format Problem");
            formatProblem.addActionListener(e -> formatProblemEditor());
            editMenu.add(formatProblem);
            bar.add(editMenu);

            JMenu viewMenu = new JMenu("View");
            JMenuItem zoomIn = new JMenuItem("Zoom In");
            zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            zoomIn.addActionListener(e -> zoomEditors(1));
            viewMenu.add(zoomIn);
            JMenuItem zoomOut = new JMenuItem("Zoom Out");
            zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            zoomOut.addActionListener(e -> zoomEditors(-1));
            viewMenu.add(zoomOut);
            JMenuItem zoomReset = new JMenuItem("Reset Zoom");
            zoomReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            zoomReset.addActionListener(e -> resetEditorZoom());
            viewMenu.add(zoomReset);
            viewMenu.addSeparator();
            JCheckBoxMenuItem treeView = new JCheckBoxMenuItem("Search Tree", false);
            treeView.addActionListener(e -> {
                showSearchTree = treeView.isSelected();
                if (showSearchTree) {
                    ensureSearchTreeWindow().showWindow();
                } else if (searchTreeWindow != null) {
                    searchTreeWindow.hideWindow();
                }
            });
            viewMenu.add(treeView);
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
            viewMenu.add(setActiveNodes);
            bar.add(viewMenu);

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
                    "Input Syntax (Automatic)\n" +
                    "------------------------\n" +
                    "Il parser GUI riconosce automaticamente input ibridi PDDL / ~PDDL.\n" +
                    "Non c'e piu una selezione di modalita: puoi mischiare forme standard e approximate\n" +
                    "nello stesso file. Le parti gia standard PDDL vengono lasciate invariate.\n\n" +
                    "Regole principali in ~PDDL\n" +
                    "--------------------------\n" +
                    "1) Espressioni infisse supportate\n" +
                    "   (x + y * 2), (a - b), (n / d)\n" +
                    "   Vengono convertite in forma prefissa PDDL.\n\n" +
                    "2) Chiamate funzione stile naturale\n" +
                    "   fuel()          -> (fuel)\n" +
                    "   weight(a)       -> (weight a)\n" +
                    "   dist(x,y)       -> (dist x y)\n\n" +
                    "3) Confronti infissi\n" +
                    "   (weight(a) <= grip-limit())  -> (<= (weight a) (grip-limit))\n" +
                    "   (fuel() > 0)                 -> (> (fuel) 0)\n\n" +
                    "4) Assegnamento in effetti\n" +
                    "   In :effect, '=' viene convertito automaticamente:\n" +
                    "   (fuel() = fuel() + 2)        -> (increase (fuel) 2)\n" +
                    "   (fuel() = fuel() - 1)        -> (decrease (fuel) 1)\n" +
                    "   Altrimenti fallback su assign.\n\n" +
                    "5) Assegnamenti booleani\n" +
                    "   (ready() = T)                -> (ready)\n" +
                    "   (ready() = F)                -> (not (ready))\n\n" +
                    "Limitazioni pratiche\n" +
                    "--------------------\n" +
                    "- La conversione lavora bene su clausole lineari dentro parentesi.\n" +
                    "- Strutture molto annidate o forme miste non standard possono richiedere PDDL standard.\n"
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

        private void loadIntoEditor(JTextPane editor) {
            JFileChooser chooser = lastLoadedDirectory == null
                    ? new JFileChooser()
                    : new JFileChooser(lastLoadedDirectory.toFile());
            chooser.setDialogTitle("Load PDDL file");
            int res = chooser.showOpenDialog(this);
            if (res != JFileChooser.APPROVE_OPTION) {
                return;
            }
            Path file = chooser.getSelectedFile().toPath();
            try {
                editor.setText(Files.readString(file, StandardCharsets.UTF_8));
                Path parent = file.getParent();
                if (parent != null) {
                    lastLoadedDirectory = parent;
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Cannot load file:\n" + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
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
            loadIntoEditor(domainArea);
        }

        private void loadProblemFile() {
            loadIntoEditor(problemArea);
        }

        private void formatDomainEditor() {
            domainArea.formatDocument();
        }

        private void formatProblemEditor() {
            problemArea.formatDocument();
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
                if (plannerOptions.search != null && !plannerOptions.search.isBlank()) {
                    searchField.setText(plannerOptions.search);
                }
                if (plannerOptions.heuristic != null && !plannerOptions.heuristic.isBlank()) {
                    heuristicField.setText(plannerOptions.heuristic);
                }
            }
        }

        private void runPlanning() {
            if (currentWorker != null) {
                return;
            }
            final boolean debugMode = debugModeCheck.isSelected();
            final String domainTextForPlanning;
            final String problemTextForPlanning;
            try {
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
            if (showSearchTree) {
                LazySearchTreeWindow treeWindow = ensureSearchTreeWindow();
                treeWindow.reset();
                treeWindow.showWindow();
                executionController.setSearchTreeWindow(treeWindow);
            } else {
                executionController.setSearchTreeWindow(null);
            }

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
                            plannerOptions.search = searchField.getText().trim().isEmpty() ? "gbfs" : searchField.getText().trim();
                            plannerOptions.heuristic = heuristicField.getText().trim().isEmpty() ? "hadd" : heuristicField.getText().trim();
                            plannerOptions.appendArgs(args);

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

        private java.util.Map<String, String> parseStateAssignments(String stateText) {
            java.util.Map<String, String> map = new java.util.HashMap<>();
            if (stateText == null) {
                return map;
            }
            java.util.regex.Matcher matcher = java.util.regex.Pattern
                    .compile("(\\([^\\)]*\\)|[^\\s=]+)=([^\\s]+)")
                    .matcher(stateText);
            while (matcher.find()) {
                map.put(matcher.group(1), matcher.group(2));
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
            int stepIndex = 0;
            int actionIndex = 0;
            for (ImmutablePair<BigDecimal, com.hstairs.ppmajal.transition.TransitionGround> planStep : solution.rawPlan()) {
                String action = planStep.getRight().toString();
                String timeKey = normalizeTimeKey(planStep.getLeft(), stepIndex);
                boolean isAction = planStep.getRight() != null
                        && planStep.getRight().getSemantics().equals(com.hstairs.ppmajal.transition.Transition.Semantics.ACTION);
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

            int pos = 0;
            while (pos < entries.size()) {
                String currentTime = entries.get(pos).timeKey;
                List<ActionDisplayEntry> group = new ArrayList<>();
                while (pos < entries.size() && entries.get(pos).timeKey.equals(currentTime)) {
                    group.add(entries.get(pos));
                    pos++;
                }

                displayLines.add("t = " + currentTime);
                displayToActionStep.add(-1);
                int waitingCount = 0;
                for (ActionDisplayEntry e : group) {
                    if (isWaitingActionText(e.action)) {
                        waitingCount++;
                        continue;
                    }
                    displayLines.add("  - " + e.action);
                    displayToActionStep.add(e.actionIndex);
                }
                if (waitingCount > 0) {
                    displayLines.add("  - [waiting x" + waitingCount + "]");
                    displayToActionStep.add(-1);
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
            return "; ~PDDL sample: simple numeric counter\n"
                    + "(define (domain numeric-counter-approx)\n"
                    + "  (:requirements :strips :fluents)\n"
                    + "  (:predicates (ready))\n"
                    + "  (:functions (x) (step) (limit))\n"
                    + "\n"
                    + "  (:action add-step\n"
                    + "    :parameters ()\n"
                    + "    :precondition (and\n"
                    + "      (ready)\n"
                    + "      (x() + step() <= limit())\n"
                    + "    )\n"
                    + "    :effect (and\n"
                    + "      (x() = x() + step())\n"
                    + "    )\n"
                    + "  )\n"
                    + ")";
        }

        private static String defaultProblem() {
            return "; ~PDDL numeric goal with infix expression\n"
                    + "(define (problem numeric-counter-approx-p1)\n"
                    + "  (:domain numeric-counter-approx)\n"
                    + "  (:init\n"
                    + "    (ready)\n"
                    + "    (x() = 0)\n"
                    + "    (step() = 2)\n"
                    + "    (limit() = 10)\n"
                    + "  )\n"
                    + "  (:goal (and\n"
                    + "    (x() >= 6)\n"
                    + "  ))\n"
                    + ")";
        }
    }

    private enum EditorKind {
        DOMAIN,
        PROBLEM
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

    private static final class SearchTreeWindow {
        private final JFrame frame;
        private final GraphCanvas canvas;
        private final JTextArea info;
        private final java.util.Map<String, GraphNode> nodes = new java.util.LinkedHashMap<>();
        private final java.util.Map<String, GraphEdge> edges = new java.util.LinkedHashMap<>();
        private final java.util.Map<String, List<GraphEdge>> outgoingEdges = new java.util.HashMap<>();
        private final java.util.Map<Integer, List<GraphNode>> nodesByDepth = new java.util.HashMap<>();
        private final Timer renderTimer;
        private final java.util.Queue<LogEvent> pendingLogEvents = new ArrayDeque<>();
        private int activeNodeLimit = 10;
        private String rootKey = null;
        private String selectedKey = null;
        private int generated = 0;
        private int expanded = 0;
        private int closed = 0;
        private boolean refreshScheduled = false;
        private boolean infoDirty = false;
        private boolean visibleDirty = true;
        private boolean logDrainScheduled = false;
        private Set<String> visibleCache = Set.of();
        private static final int X_GAP = 240;
        private static final int Y_GAP = 160;

        SearchTreeWindow() {
            frame = new JFrame("Search Tree");
            frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            frame.setSize(980, 760);
            frame.setLocationByPlatform(true);

            canvas = new GraphCanvas();

            info = new JTextArea();
            info.setEditable(false);
            info.setRows(4);
            info.setText("Generated: 0\nExpanded: 0\nExpanded/Closed: 0");

            renderTimer = new Timer(33, e -> {
                refreshScheduled = false;
                if (infoDirty) {
                    updateInfoNow();
                    infoDirty = false;
                }
                canvas.repaint();
            });
            renderTimer.setRepeats(false);

            JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(canvas), new JScrollPane(info));
            split.setResizeWeight(0.88);
            frame.setContentPane(split);
        }

        void showWindow() {
            SwingUtilities.invokeLater(() -> frame.setVisible(true));
        }

        void hideWindow() {
            SwingUtilities.invokeLater(() -> frame.setVisible(false));
        }

        void setFontSize(int size) {
            SwingUtilities.invokeLater(() -> {
                Font f = new Font(Font.MONOSPACED, Font.PLAIN, size);
                canvas.setFont(f);
                info.setFont(f);
            });
        }

        void reset() {
            SwingUtilities.invokeLater(() -> {
                nodes.clear();
                edges.clear();
                outgoingEdges.clear();
                nodesByDepth.clear();
                pendingLogEvents.clear();
                rootKey = null;
                selectedKey = null;
                generated = expanded = closed = 0;
                logDrainScheduled = false;
                markVisibleDirty();
                scheduleRefresh(true);
            });
        }

        void onSearchStart() {
            reset();
        }

        void onSearchEnd() {
            SwingUtilities.invokeLater(() -> info.append("\nSearch completed."));
        }

        void setActiveNodeLimit(int activeNodeLimit) {
            this.activeNodeLimit = Math.max(3, activeNodeLimit);
            markVisibleDirty();
            scheduleRefresh(true);
        }

        int getActiveNodeLimit() {
            return activeNodeLimit;
        }

        void onLogEvent(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node, ExternalLoggerLogType type, boolean isGoal) {
            synchronized (pendingLogEvents) {
                pendingLogEvents.add(new LogEvent(node, type, isGoal));
                if (logDrainScheduled) {
                    return;
                }
                logDrainScheduled = true;
            }
            SwingUtilities.invokeLater(this::drainPendingLogEvents);
        }

        void markSolutionNode(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node) {
            SwingUtilities.invokeLater(() -> {
                GraphNode n = ensureNode(node);
                n.isSolution = true;
                selectedKey = n.key;
                markVisibleDirty();
                scheduleRefresh(true);
            });
        }

        private GraphNode ensureNode(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node) {
            String k = nodeKey(node);
            GraphNode existing = nodes.get(k);
            if (existing != null) {
                return existing;
            }
            GraphNode parent = null;
            int depth = 0;
            if (node.father != null) {
                parent = ensureNode(node.father);
                depth = parent.depth + 1;
            } else if (rootKey == null) {
                rootKey = k;
            }
            GraphNode created = new GraphNode(k, formatNode(node, null), actionText(node), depth, node.gValue);
            if (node instanceof com.hstairs.ppmajal.search.searchnodes.SearchNode sn) {
                created.fValue = sn.f;
            }
            created.parent = parent;
            if (created.parent == null) {
                created.expanded = true;
                created.isStart = true;
            }
            nodes.put(k, created);
            nodesByDepth.computeIfAbsent(created.depth, ignored -> new ArrayList<>()).add(created);
            layoutDepth(created.depth);
            markVisibleDirty();
            return created;
        }

        private void layoutDepth(int depth) {
            List<GraphNode> layer = nodesByDepth.getOrDefault(depth, List.of());
            int count = layer.size();
            int startX = -((count - 1) * X_GAP) / 2;
            for (int i = 0; i < count; i++) {
                GraphNode n = layer.get(i);
                n.x = startX + i * X_GAP;
                n.y = depth * Y_GAP;
            }
        }

        private void updateInfoNow() {
            int visible = computeVisibleNodeKeys().size();
            info.setText("Generated: " + generated +
                    "\nExpanded: " + expanded +
                    "\nExpanded/Closed: " + closed +
                    "\nNodes: " + nodes.size() + "  Visible: " + visible + "  Limit: " + activeNodeLimit);
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

        private String nodeKey(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode n) {
            if (n.id != null) {
                return n.id.toString();
            }
            return Integer.toHexString(System.identityHashCode(n));
        }

        private String formatNode(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode n, ExternalLoggerLogType type) {
            String action = n.transition == null ? "init/wait" : n.transition.toString();
            String tag = type == null ? "" : "[" + statusLabel(type) + "] ";
            String g = "g=" + n.gValue;
            String f = "";
            if (n instanceof com.hstairs.ppmajal.search.searchnodes.SearchNode sn) {
                f = " f=" + sn.f;
            }
            return tag + action + " " + g + f;
        }

        private String statusLabel(ExternalLoggerLogType type) {
            return switch (type) {
                case Generating -> "generated";
                case Expanding -> "expanded";
                case Closing -> "expanded/closed";
            };
        }

        private String actionText(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode n) {
            return n.transition == null ? "init/wait" : n.transition.toString();
        }

        private void drainPendingLogEvents() {
            int processed = 0;
            while (processed < 2000) {
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
            com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node = ev.node;
            ExternalLoggerLogType type = ev.type;
            GraphNode n = ensureNode(node);
            n.status = type;
            n.label = formatNode(node, type);
            n.gValue = node.gValue;
            n.isGoal = n.isGoal || ev.isGoal;
            if (node instanceof com.hstairs.ppmajal.search.searchnodes.SearchNode sn) {
                n.fValue = sn.f;
            }

            if (node.father != null) {
                GraphNode p = ensureNode(node.father);
                String ek = p.key + "->" + n.key;
                GraphEdge edge = edges.computeIfAbsent(ek, k -> {
                    GraphEdge created = new GraphEdge(p, n, n.action);
                    outgoingEdges.computeIfAbsent(p.key, ignored -> new ArrayList<>()).add(created);
                    return created;
                });
                if (type == ExternalLoggerLogType.Expanding || type == ExternalLoggerLogType.Closing) {
                    edge.status = EdgeStatus.CHOSEN;
                    if (!p.expanded) {
                        p.expanded = true;
                        markVisibleDirty();
                    }
                } else if (edge.status == EdgeStatus.UNKNOWN) {
                    edge.status = EdgeStatus.TRIED;
                }
            }

            if (type == ExternalLoggerLogType.Generating) generated++;
            else if (type == ExternalLoggerLogType.Expanding) expanded++;
            else if (type == ExternalLoggerLogType.Closing) closed++;
        }

        private enum EdgeStatus { UNKNOWN, TRIED, CHOSEN }

        private static final class GraphNode {
            final String key;
            String label;
            final String action;
            final int depth;
            float gValue;
            float fValue = Float.NaN;
            ExternalLoggerLogType status;
            GraphNode parent;
            int x;
            int y;
            boolean expanded;
            boolean isStart;
            boolean isGoal;
            boolean isSolution;
            GraphNode(String key, String label, String action, int depth, float gValue) {
                this.key = key;
                this.label = label;
                this.action = action;
                this.depth = depth;
                this.gValue = gValue;
                this.expanded = false;
                this.isStart = false;
                this.isGoal = false;
                this.isSolution = false;
            }
        }

        private static final class GraphEdge {
            final GraphNode from;
            final GraphNode to;
            final String label;
            EdgeStatus status = EdgeStatus.UNKNOWN;
            GraphEdge(GraphNode from, GraphNode to, String label) {
                this.from = from;
                this.to = to;
                this.label = label;
            }
        }

        private static final class LogEvent {
            final com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node;
            final ExternalLoggerLogType type;
            final boolean isGoal;

            LogEvent(com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode node, ExternalLoggerLogType type, boolean isGoal) {
                this.node = node;
                this.type = type;
                this.isGoal = isGoal;
            }
        }

        private final class GraphCanvas extends JPanel {
            private double zoom = 1.0;
            private double panX = 0;
            private double panY = 40;
            private Point dragStart;

            GraphCanvas() {
                setPreferredSize(new Dimension(1800, 1400));
                setBackground(new Color(250, 252, 255));
                MouseAdapter mouse = new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            GraphNode hit = findNodeAt(e.getPoint());
                            if (hit != null) {
                                selectedKey = hit.key;
                                markVisibleDirty();
                                if (e.getClickCount() >= 2) {
                                    hit.expanded = !hit.expanded;
                                    markVisibleDirty();
                                }
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
                        double factor = e.getPreciseWheelRotation() < 0 ? 1.1 : 0.9;
                        zoom = Math.max(0.25, Math.min(3.0, zoom * factor));
                        repaint();
                    }
                };
                addMouseListener(mouse);
                addMouseMotionListener(mouse);
                addMouseWheelListener(mouse);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform old = g2.getTransform();
                g2.translate(getWidth() / 2.0 + panX, panY);
                g2.scale(zoom, zoom);

                Set<String> visible = computeVisibleNodeKeys();
                for (String key : visible) {
                    List<GraphEdge> outs = outgoingEdges.get(key);
                    if (outs == null) {
                        continue;
                    }
                    for (GraphEdge e : outs) {
                        if (visible.contains(e.to.key)) {
                            drawEdge(g2, e);
                        }
                    }
                }
                for (String key : visible) {
                    GraphNode n = nodes.get(key);
                    if (n != null) {
                        drawNode(g2, n);
                    }
                }
                g2.setTransform(old);
                g2.dispose();
            }

            private void drawEdge(Graphics2D g2, GraphEdge e) {
                int x1 = e.from.x;
                int y1 = e.from.y + 42;
                int x2 = e.to.x;
                int y2 = e.to.y - 42;
                Color c = switch (e.status) {
                    case CHOSEN -> new Color(22, 142, 78);
                    case TRIED -> new Color(70, 120, 180);
                    default -> new Color(160, 160, 160);
                };
                g2.setColor(c);
                g2.setStroke(new BasicStroke(e.status == EdgeStatus.CHOSEN ? 2.4f : 1.4f));
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }

            private void drawNode(Graphics2D g2, GraphNode n) {
                int w = 220;
                int h = 84;
                int x = n.x - w / 2;
                int y = n.y - h / 2;
                Color fill;
                if (n.isSolution) {
                    fill = new Color(255, 236, 179);
                } else if (n.isGoal) {
                    fill = new Color(206, 241, 210);
                } else if (n.isStart) {
                    fill = new Color(209, 228, 255);
                } else {
                    fill = switch (n.status == null ? ExternalLoggerLogType.Generating : n.status) {
                        case Generating -> new Color(232, 240, 255);
                        case Expanding -> new Color(225, 247, 232);
                        case Closing -> new Color(243, 243, 243);
                    };
                }
                RoundRectangle2D rr = new RoundRectangle2D.Double(x, y, w, h, 20, 20);
                g2.setColor(fill);
                g2.fill(rr);
                boolean selected = n.key.equals(selectedKey);
                g2.setColor(selected ? new Color(20, 20, 20) : new Color(70, 70, 70));
                g2.setStroke(new BasicStroke(selected ? 2.2f : 1.2f));
                g2.draw(rr);

                g2.setColor(new Color(30, 30, 30));
                String idShort = n.key.length() > 8 ? n.key.substring(0, 8) : n.key;
                String marker = n.expanded ? "[-]" : "[+]";
                String semantic = n.isSolution ? "solution" : (n.isGoal ? "goal" : (n.isStart ? "start" : ""));
                String status = n.status == null ? "" : statusLabel(n.status);
                String head = marker + " " + idShort + " " + (status.isBlank() ? semantic : status + (semantic.isBlank() ? "" : ", " + semantic));
                g2.drawString(head, x + 10, y + 20);
                String action = n.action.length() > 28 ? n.action.substring(0, 28) + "..." : n.action;
                g2.drawString(action, x + 10, y + 40);
                String gf = "g=" + n.gValue + (Float.isNaN(n.fValue) ? "" : ("  f=" + n.fValue));
                g2.drawString(gf, x + 10, y + 60);
            }

            private GraphNode findNodeAt(Point pScreen) {
                double wx = (pScreen.x - (getWidth() / 2.0 + panX)) / zoom;
                double wy = (pScreen.y - panY) / zoom;
                Set<String> visible = computeVisibleNodeKeys();
                for (String key : visible) {
                    GraphNode n = nodes.get(key);
                    if (n == null) {
                        continue;
                    }
                    int w = 220;
                    int h = 84;
                    int x = n.x - w / 2;
                    int y = n.y - h / 2;
                    if (wx >= x && wx <= x + w && wy >= y && wy <= y + h) {
                        return n;
                    }
                }
                return null;
            }
        }

        private Set<String> computeVisibleNodeKeys() {
            if (!visibleDirty) {
                return visibleCache;
            }
            Set<String> visible = new java.util.LinkedHashSet<>();
            if (nodes.isEmpty()) {
                visibleCache = visible;
                visibleDirty = false;
                return visible;
            }
            String start = selectedKey != null && nodes.containsKey(selectedKey) ? selectedKey : rootKey;
            if (start == null) {
                start = nodes.keySet().iterator().next();
            }
            java.util.ArrayDeque<String> q = new java.util.ArrayDeque<>();
            q.add(start);
            while (!q.isEmpty() && visible.size() < activeNodeLimit) {
                String k = q.poll();
                if (!visible.add(k)) {
                    continue;
                }
                GraphNode n = nodes.get(k);
                if (n == null) {
                    continue;
                }
                if (n.parent != null && visible.size() < activeNodeLimit) {
                    q.add(n.parent.key);
                }
                if (n.expanded || n.parent == null) {
                    List<GraphEdge> outs = outgoingEdges.get(k);
                    if (outs == null) {
                        continue;
                    }
                    for (GraphEdge e : outs) {
                        if (visible.size() < activeNodeLimit) {
                            q.add(e.to.key);
                        }
                    }
                }
            }
            visibleCache = visible;
            visibleDirty = false;
            return visibleCache;
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

        static {
            String[] words = {
                    "define", "domain", "problem", ":requirements", ":types", ":objects", ":predicates", ":functions",
                    ":action", ":parameters", ":precondition", ":effect", ":init", ":goal", ":metric", ":domain",
                    "and", "or", "not", "when", "forall", "exists", "increase", "decrease", "assign", "scale-up", "scale-down"
            };
            for (String w : words) {
                KEYWORDS.add(w);
            }
        }

        private final EditorKind kind;
        private final Consumer<SyntaxReport> reportConsumer;
        private final Timer repaintTimer;
        private final UndoManager undoManager = new UndoManager();

        private final SimpleAttributeSet normalStyle = style(new Color(30, 30, 30), false, false, null);
        private final SimpleAttributeSet keywordStyle = style(new Color(10, 70, 180), true, false, null);
        private final SimpleAttributeSet numberStyle = style(new Color(138, 84, 0), false, false, null);
        private final SimpleAttributeSet stringStyle = style(new Color(170, 35, 35), false, false, null);
        private final SimpleAttributeSet commentStyle = style(new Color(40, 130, 40), false, true, null);
        private final SimpleAttributeSet parenStyle = style(new Color(95, 95, 95), true, false, null);
        private final SimpleAttributeSet errorStyle = style(new Color(170, 0, 0), true, false, new Color(255, 220, 220));

        private boolean applyingStyles = false;

        LispSyntaxTextPane(EditorKind kind, String initialText, Consumer<SyntaxReport> reportConsumer) {
            this.kind = kind;
            this.reportConsumer = reportConsumer;
            setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            setText(initialText);
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
                        repaintTimer.restart();
                    }
                }
            });

            SwingUtilities.invokeLater(this::applyHighlightingAndChecks);
        }

        private void installEditorActions() {
            InputMap inputMap = getInputMap();
            ActionMap actionMap = getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "lisp-auto-indent-enter");
            actionMap.put("lisp-auto-indent-enter", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insertAutoIndentedNewline();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "lisp-format-buffer");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | KeyEvent.SHIFT_DOWN_MASK), "lisp-format-buffer");
            actionMap.put("lisp-format-buffer", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    formatDocument();
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
    }
}
