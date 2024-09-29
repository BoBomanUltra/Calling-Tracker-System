package ui.tools;

import javax.swing.*;
import java.awt.*;

/*
 * SummaryCellEditor is a custom cell editor for the JTable that provides text area functionality for summary cells.
 */
public class SummaryCellEditor extends DefaultCellEditor {
    private JTextArea textArea;

    // MODIFIES: this
    // EFFECTS: constructs a SummaryCellEditor with a JTextArea that has line wrap and word wrap enabled
    public SummaryCellEditor() {
        super(new JTextField());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: returns the text area component to be edited in the table cell
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textArea.setText(value.toString());
        return textArea;
    }

    // EFFECTS: returns the value contained in the text area editor
    @Override
    public Object getCellEditorValue() {
        return textArea.getText();
    }
}




