package ui.tools;

import ui.MainUIHandler;
import model.Call;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * ButtonEditor is a custom cell editor for the JTable that provides button functionality.
 */
public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private MainUIHandler handler;
    private JTable table;

    // MODIFIES: this
    // EFFECTS: constructs a ButtonEditor with a button and action listener
    public ButtonEditor(JCheckBox checkBox, MainUIHandler handler) {
        super(checkBox);
        this.handler = handler;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: returns the button component to be edited
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        this.table = table;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    // MODIFIES: this
    // EFFECTS: returns the value contained in the editor, deletes the call if button is pushed
    public Object getCellEditorValue() {
        if (isPushed) {
            Call call = ((CallTableModel) table.getModel()).getCallAt(row);
            handler.deleteCall(call);
            ((CallTableModel) table.getModel()).removeCallAt(row);
        }
        isPushed = false;
        return label;
    }

    // MODIFIES: this
    // EFFECTS: stops cell editing and returns true
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    // MODIFIES: this
    // EFFECTS: notifies that editing has stopped
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}









