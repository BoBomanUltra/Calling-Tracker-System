package ui.tools;

import javax.swing.*;
import ui.MainUIHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * MainMenuPanel represents the main menu of the application.
 * It contains buttons to navigate to different functionalities of the app,
 * including adding a call, viewing call history, filtering call history,
 * saving call history, and loading call history.
 */
public class MainMenuPanel extends JPanel {
    private JButton addCallButton;
    private JButton viewCallHistoryButton;
    private JButton filterCallHistoryButton;
    private JButton saveCallHistoryButton;
    private JButton loadCallHistoryButton;
    private MainUIHandler handler;

    // MODIFIES: this
    // EFFECTS: constructs a MainMenuPanel with buttons and action listeners
    public MainMenuPanel(MainUIHandler handler) {
        this.handler = handler;
        setLayout(new GridLayout(5, 1));
        initializeButtons();
        addButtonsToPanel();
        addButtonListeners();
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons
    private void initializeButtons() {
        addCallButton = new JButton("Add Call to Call History");
        viewCallHistoryButton = new JButton("View Call History");
        filterCallHistoryButton = new JButton("Get Filtered Call History by Name");
        saveCallHistoryButton = new JButton("Save Call History");
        loadCallHistoryButton = new JButton("Load Call History");
    }

    // MODIFIES: this
    // EFFECTS: adds the buttons to the panel
    private void addButtonsToPanel() {
        add(addCallButton);
        add(viewCallHistoryButton);
        add(filterCallHistoryButton);
        add(saveCallHistoryButton);
        add(loadCallHistoryButton);
    }

    // MODIFIES: this
    // EFFECTS: adds action listeners to the buttons
    private void addButtonListeners() {
        addCallButton.addActionListener(createAddCallListener());
        viewCallHistoryButton.addActionListener(createViewCallHistoryListener());
        filterCallHistoryButton.addActionListener(createFilterCallHistoryListener());
        saveCallHistoryButton.addActionListener(createSaveCallHistoryListener());
        loadCallHistoryButton.addActionListener(createLoadCallHistoryListener());
    }

    // Note, actionPerformed is a method in in the ActionListener interface
    // This method is overridden to specify the action that should be taken when the event occurs.

    // EFFECTS: creates an ActionListener to show the AddCallPanel
    private ActionListener createAddCallListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.showAddCallPanel();
            }
        };
    }

    // EFFECTS: creates an ActionListener to show the CallHistoryPanel
    private ActionListener createViewCallHistoryListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.showCallHistoryPanel();
            }
        };
    }

    // EFFECTS: creates an ActionListener to show the FilterCallHistoryPanel
    private ActionListener createFilterCallHistoryListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.showFilterCallHistoryPanel();
            }
        };
    }

    // EFFECTS: creates an ActionListener to save the call history
    private ActionListener createSaveCallHistoryListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.saveCallHistory();
            }
        };
    }

    // EFFECTS: creates an ActionListener to load the call history
    private ActionListener createLoadCallHistoryListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.loadCallHistory();
            }
        };
    }
}



