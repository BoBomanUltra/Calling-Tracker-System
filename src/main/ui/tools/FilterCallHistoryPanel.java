package ui.tools;

import ui.MainUIHandler;

import javax.swing.*;

import model.EventLog;

import java.awt.*;

/*
 * FilterCallHistoryPanel is a custom JPanel that provides an interface for filtering the call history by name.
 * It contains a search field, search button, message label, and a main menu button.
 */
public class FilterCallHistoryPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JLabel messageLabel;
    private JButton mainMenuButton;
    private MainUIHandler handler;

    // MODIFIES: this
    // EFFECTS: constructs a FilterCallHistoryPanel with search field, buttons, and a message label
    public FilterCallHistoryPanel(MainUIHandler handler) {
        this.handler = handler;
        setLayout(new BorderLayout());
        initializeComponents();
        addComponents();
        addActionListeners();
    }

    // MODIFIES: this
    // EFFECTS: initializes the components of the panel
    private void initializeComponents() {
        searchField = new JTextField(20);
        searchButton = new JButton("Enter");
        messageLabel = new JLabel("", SwingConstants.CENTER);
        mainMenuButton = new JButton("Main Menu");
    }

    // MODIFIES: this
    // EFFECTS: adds the components to the panel
    private void addComponents() {
        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel instructionLabel = new JLabel("Type the name you want to search for: ");

        searchPanel.add(instructionLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.add(mainMenuButton);

        add(searchPanel, BorderLayout.NORTH);
        add(messageLabel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds action listeners to the buttons
    private void addActionListeners() {
        searchButton.addActionListener(e -> handleSearch());
        mainMenuButton.addActionListener(e -> handler.showMainMenu());
    }

    // MODIFIES: this
    // EFFECTS: handles the search button action
    private void handleSearch() {
        String searchText = searchField.getText();
        if (handler.searchCallHistory(searchText)) {
            handler.showFilteredCallHistoryPanel(searchText);
            // EventLog.getInstance().logEvent(new Event("Filtered the Call History by name: " + searchText));
            
        } else {
            messageLabel.setText("Name not found! Please try again.");
            // EventLog.getInstance().logEvent(new Event("Tried Filtering Call History by a name, 
            // but could not find any results"));

        }
    }
    
    // MODIFIES: this
    // EFFECTS: resets the search field and message label
    public void reset() {
        searchField.setText("");
        messageLabel.setText("");
    }
}







