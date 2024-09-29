package ui.tools;

import model.Call;
import ui.MainUIHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/*
 * CallHistoryPanel is a custom JPanel that displays the call history in a table format.
 * It provides functionality to view the summary of a call and delete calls from the history.
 */
public class CallHistoryPanel extends JPanel {
    private JTable callTable;
    private JButton mainMenuButton;
    private MainUIHandler handler;

    // MODIFIES: this
    // EFFECTS: constructs a CallHistoryPanel with a table and a main menu button
    public CallHistoryPanel(MainUIHandler handler) {
        this.handler = handler;
        setLayout(new BorderLayout());
        initializeComponents();
        addComponents();
        addTableMouseListener();
    }

    // MODIFIES: this
    // EFFECTS: initializes the components of the panel
    private void initializeComponents() {
        callTable = new JTable();
        callTable.setFillsViewportHeight(true);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> handler.showMainMenu());
    }

    // MODIFIES: this
    // EFFECTS: adds the components to the panel
    private void addComponents() {
        JScrollPane scrollPane = new JScrollPane(callTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(mainMenuButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds a mouse listener to the table for double-click actions
    private void addTableMouseListener() {
        callTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = callTable.getSelectedRow();
                    int column = callTable.getSelectedColumn();
                    if (column == 5) { // Summary column
                        String summary = (String) callTable.getValueAt(row, column);
                        showSummaryDialog(summary);
                    }
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: displays a dialog with the full summary of the call
    private void showSummaryDialog(String summary) {
        JTextArea textArea = new JTextArea(summary);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: updates the call history table with the provided list of calls
    public void updateCallHistory(List<Call> calls) {
        CallTableModel model = new CallTableModel(calls);
        callTable.setModel(model);
        callTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        callTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), handler));
        // Can just delete this, if I ever wanted to get rid of SummaryCellEditor
        // or SummaryCellRenderer
        callTable.getColumn("Summary").setCellRenderer(new SummaryCellRenderer());
        callTable.getColumn("Summary").setCellEditor(new SummaryCellEditor());
    }
}




















