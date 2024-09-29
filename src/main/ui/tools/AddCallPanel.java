package ui.tools;

import model.Call;
import model.Date;
import ui.MainUIHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/*
 * AddCallPanel is a custom JPanel that provides an interface for adding new calls to the call history.
 * It contains fields for year, month, day, phone number, name, title, status, and summary of the call.
 * It also has buttons to submit the new call and return to the main menu.
 */
public class AddCallPanel extends JPanel {
    private JTextField yearField; 
    private JTextField monthField;
    private JTextField dayField;
    private JTextField phoneField;
    private JTextField nameField;
    private JTextField titleField;
    private JTextField statusField;
    private JTextArea summaryArea;
    private JButton submitButton;
    private JButton mainMenuButton;
    private JLabel messageLabel;
    private MainUIHandler handler;

    // MODIFIES: this
    // EFFECTS: constructs an AddCallPanel with fields and buttons
    public AddCallPanel(MainUIHandler handler) {
        this.handler = handler;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponents(gbc);
        addActionListeners();
    }

    // MODIFIES: this
    // EFFECTS: adds all components to the panel
    private void addComponents(GridBagConstraints gbc) {
        addHeader(gbc);
        addYearField(gbc);
        addMonthField(gbc);
        addDayField(gbc);
        addPhoneField(gbc);
        addNameField(gbc);
        addTitleField(gbc);
        addStatusField(gbc);
        addSummaryField(gbc);
        addButtons(gbc);
        addMessageLabel(gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds the header label to the panel
    private void addHeader(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Add Call"), gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds the year field to the panel
    private void addYearField(GridBagConstraints gbc) {
        addLabel("Add Year:", gbc, 1);
        yearField = new JTextField(10);
        addField(yearField, gbc, 1);
    }

    // MODIFIES: this
    // EFFECTS: adds the month field to the panel
    private void addMonthField(GridBagConstraints gbc) {
        addLabel("Add Month:", gbc, 2);
        monthField = new JTextField(10);
        addField(monthField, gbc, 2);
    }

    // MODIFIES: this
    // EFFECTS: adds the day field to the panel
    private void addDayField(GridBagConstraints gbc) {
        addLabel("Add Day (1-31):", gbc, 3);
        dayField = new JTextField(10);
        addField(dayField, gbc, 3);
    }

    // MODIFIES: this
    // EFFECTS: adds the phone number field to the panel
    private void addPhoneField(GridBagConstraints gbc) {
        addLabel("Add Phone Number:", gbc, 4);
        phoneField = new JTextField(20);
        addField(phoneField, gbc, 4);
    }

    // 
    // MODIFIES: this
    // EFFECTS: adds the name field to the panel
    private void addNameField(GridBagConstraints gbc) {
        addLabel("Add Name:", gbc, 5);
        nameField = new JTextField(20);
        addField(nameField, gbc, 5);
    }

    // MODIFIES: this
    // EFFECTS: adds the title field to the panel
    private void addTitleField(GridBagConstraints gbc) {
        addLabel("Add Title (Max 5 words):", gbc, 6);
        titleField = new JTextField(20);
        addField(titleField, gbc, 6);
    }


    // MODIFIES: this
    // EFFECTS: adds the status field to the panel
    private void addStatusField(GridBagConstraints gbc) {
        addLabel(
                "<html>Add Status (Must be one of the following):<br>"
                + "[\"Receiving: Accepted\", \"Receiving: Rejected\",<br>"
                + "\"Receiving: Missed\", \"Outgoing: Accepted\",<br>"
                + "\"Outgoing: Rejected\", \"Outgoing: Missed\"]:</html>", 
                gbc, 7);
        statusField = new JTextField(20);
        addField(statusField, gbc, 7);
    }

    // MODIFIES: this
    // EFFECTS: adds the summary field to the panel
    private void addSummaryField(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Add Summary (Max 100 words):"), gbc);
        summaryArea = new JTextArea(5, 20);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
        gbc.gridy = 9;
        add(summaryScrollPane, gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds main menu button and 
    //          Submit button
    private void addButtons(GridBagConstraints gbc) {
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        mainMenuButton = new JButton("Main Menu");
        add(mainMenuButton, gbc);

        gbc.gridx = 1;
        submitButton = new JButton("Submit");
        add(submitButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds the message label to the panel
    private void addMessageLabel(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds a label to the panel at the specified position
    private void addLabel(String text, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel(text), gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds a text field to the panel at the specified position
    private void addField(JTextField field, GridBagConstraints gbc, int y) {
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(field, gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds action listeners to the buttons
    private void addActionListeners() {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.showMainMenu();
                resetFields();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitAction();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handles the submit button action
    private void handleSubmitAction() {
        if (validateFields()) {
            Call newCall = createCall();
            handler.addCall(newCall);
            handler.showMainMenu();
            resetFields();
        } else {
            messageLabel.setText("Fields are incorrect, please try again.");
        }
    }

    // REQUIRES: Requires user to input valid year, month, day
    //           phoneNumber, status
    // MODIFIES: this
    // EFFECTS: creates a new Call object with the inputted fields
    private Call createCall() {
        int year = Integer.parseInt(yearField.getText());
        String month = monthField.getText();
        int day = Integer.parseInt(dayField.getText());
        Date date = new Date(year, month, day);
        String phoneNumber = phoneField.getText();
        String status = statusField.getText();

        Call newCall = new Call(date, phoneNumber, status);
        setOptionalFields(newCall);
        return newCall;
    }

    // REQUIRES: Requires user to input valid summary, 
    //           name and title. 
    // MODIFIES: Call
    // EFFECTS: sets the optional fields of the call if they are not empty
    private void setOptionalFields(Call newCall) {
        if (!summaryArea.getText().isEmpty()) {
            newCall.setSummary(summaryArea.getText());
        }
        if (!nameField.getText().isEmpty()) {
            newCall.setName(nameField.getText());
        }
        if (!titleField.getText().isEmpty()) {
            newCall.setTitle(titleField.getText());
        }
    }

    // MODIFIES: this
    // EFFECTS: resets all input fields to empty, after a submit
    //          or a main menu, we reset all the fields always
    public void resetFields() {
        yearField.setText("");
        monthField.setText("");
        dayField.setText("");
        phoneField.setText("");
        nameField.setText("");
        titleField.setText("");
        statusField.setText("");
        summaryArea.setText("");
        messageLabel.setText("");
    }

    // MODIFIES: none
    // EFFECTS: validates the status field only, shows error message if invalid
    //          invalid means not one of the choices presented as a valid status
    private boolean validateFields() {
        String status = statusField.getText();
        String[] validStatuses = {"Receiving: Accepted", "Receiving: Rejected", "Receiving: Missed", 
                                  "Outgoing: Accepted", "Outgoing: Rejected", "Outgoing: Missed"};
        boolean statusValid = Arrays.asList(validStatuses).contains(status);
        if (!statusValid) {
            JOptionPane.showMessageDialog(this, 
                                            "Status field is invalid. Please use one of the specified values.", 
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}






