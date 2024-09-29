package ui.tools;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/*
 * SummaryCellRenderer is a custom cell renderer for the JTable that renders text area functionality for summary cells.
 */
public class SummaryCellRenderer extends DefaultTableCellRenderer {

    // MODIFIES: this
    // EFFECTS: returns the text area component to be rendered in the table cell
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                                                    boolean hasFocus, int row, int column) {
        JTextArea textArea = new JTextArea(value.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        if (isSelected) {
            textArea.setBackground(table.getSelectionBackground());
            textArea.setForeground(table.getSelectionForeground());
        } else {
            textArea.setBackground(table.getBackground());
            textArea.setForeground(table.getForeground());
        }

        return textArea;
    }
}



