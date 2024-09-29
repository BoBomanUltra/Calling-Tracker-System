package ui.tools;

import model.Call;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/*
 * CallTableModel is a custom table model for the JTable that represents the call history.
 * It provides functionality to display and manage the call data in a tabular format.
 */
public class CallTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Date", "Name", "Number", "Status", "Title", "Summary", "Delete"};
    private final List<Call> calls;

    // MODIFIES: this
    // EFFECTS: constructs a CallTableModel with the given list of calls
    public CallTableModel(List<Call> calls) {
        this.calls = calls;
    }

    // EFFECTS: returns the number of columns in the table
    public int getColumnCount() {
        return columnNames.length;
    }

    // EFFECTS: returns the number of rows in the table
    public int getRowCount() {
        return calls.size();
    }
    
    // REQUIRES: row and col are valid indices
    // MODIFIES: none
    // EFFECTS: returns the value at the specified row and column
    public Object getValueAt(int row, int col) {
        Call call = calls.get(row);
        switch (col) {
            case 0:
                return  call.getDate().getYear() + "-" + call.getDate().getMonth() + "-" + call.getDate().getDay();
            case 1:
                return call.getName();
            case 2:
                return call.getPhoneNumber();
            case 3:
                return call.getStatus();
            case 4:
                return call.getTitle();
            case 5:
                return call.getSummary();
            case 6:
                return "Delete";
            default:
                return null;
        }
    }

    // REQUIRES: col is a valid index
    // EFFECTS: returns the name of the column at the specified index
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // REQUIRES: row and col are valid indices
    // EFFECTS: returns true if the cell at the specified row and column is editable
    public boolean isCellEditable(int row, int col) {
        return col == 6;
    }

    // REQUIRES: row is a valid index
    // EFFECTS: returns the Call object at the specified row
    public Call getCallAt(int row) {
        return calls.get(row);
    }

    // REQUIRES: row is a valid index
    // EFFECTS: removes the Call object at the specified row and updates the table
    public void removeCallAt(int row) {
        calls.remove(row);
        fireTableRowsDeleted(row, row);
    }
}



