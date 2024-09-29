package ui.tools;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/*
 * ButtonRenderer is a custom cell renderer for the JTable that provides button functionality.
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    // MODIFIES: this
    // EFFECTS: constructs a ButtonRenderer with opaque set to true
    public ButtonRenderer() {
        setOpaque(true);
    }

    // MODIFIES: this
    // EFFECTS: returns the button component to be rendered in the table cell
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                                                    boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}


