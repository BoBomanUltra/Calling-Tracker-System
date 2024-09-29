package ui;

import model.Call;
import model.CallHistory;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/*
 * MainUIHandler handles the main UI logic and transitions between different panels.
 */
public class MainUIHandler {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private CallHistory callHistory;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private MainMenuPanel mainMenuPanel;
    private AddCallPanel addCallPanel;
    private CallHistoryPanel callHistoryPanel;
    private FilterCallHistoryPanel filterCallHistoryPanel;
    private SplashScreenPanel splashScreenPanel;

    private static final String JSON_STORE = "./data/callhistory.json";

    // MODIFIES: this
    // EFFECTS: sets up the main frame and panels, initializes data
    public void initialize() {
        setupFrame();
        initializeData();
        initializePanels();
        addPanelsToMainPanel();
        frame.add(mainPanel);
        frame.setVisible(true);
        showSplashScreen();
    }

    // MODIFIES: this
    // EFFECTS: sets up the main frame
    private void setupFrame() {
        frame = new JFrame("Call Management System");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        // When the application closes, we print all the events
        // to the console
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes data components
    private void initializeData() {
        callHistory = new CallHistory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes the panels
    private void initializePanels() {
        mainMenuPanel = new MainMenuPanel(this);
        addCallPanel = new AddCallPanel(this);
        callHistoryPanel = new CallHistoryPanel(this);
        filterCallHistoryPanel = new FilterCallHistoryPanel(this);
        splashScreenPanel = new SplashScreenPanel(this);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to the main panel
    private void addPanelsToMainPanel() {
        mainPanel.add(splashScreenPanel, "SplashScreen");
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(addCallPanel, "AddCall");
        mainPanel.add(callHistoryPanel, "CallHistory");
        mainPanel.add(filterCallHistoryPanel, "FilterCallHistory");
    }

    // MODIFIES: this
    // EFFECTS: displays the splash screen panel for 3 seconds
    private void showSplashScreen() {
        cardLayout.show(mainPanel, "SplashScreen");
        Timer timer = new Timer(3000, e -> showMainMenu());
        timer.setRepeats(false);
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: shows the main menu panel
    public void showMainMenu() {
        cardLayout.show(mainPanel, "MainMenu");
    }

    // MODIFIES: this
    // EFFECTS: shows the add call panel
    public void showAddCallPanel() {
        cardLayout.show(mainPanel, "AddCall");
    }

    // MODIFIES: this
    // EFFECTS: updates and shows the call history panel
    public void showCallHistoryPanel() {
        callHistoryPanel.updateCallHistory(callHistory.getCallHistory());
        cardLayout.show(mainPanel, "CallHistory");
    }

    // MODIFIES: this
    // EFFECTS: shows the filter call history panel
    public void showFilterCallHistoryPanel() {
        filterCallHistoryPanel.reset();
        cardLayout.show(mainPanel, "FilterCallHistory");
    }

    // REQUIRES: name != null
    // MODIFIES: this
    // EFFECTS: updates and shows the call history panel with filtered calls
    public void showFilteredCallHistoryPanel(String name) {
        List<Call> filteredCalls = callHistory.filterByName(name);
        callHistoryPanel.updateCallHistory(filteredCalls);
        cardLayout.show(mainPanel, "CallHistory");
    }

    // REQUIRES: call != null
    // MODIFIES: this
    // EFFECTS: adds the given call to the call history
    public void addCall(Call call) {
        callHistory.addCall(call);
    }

    // REQUIRES: call != null
    // MODIFIES: this
    // EFFECTS: removes the given call from the call history
    public void deleteCall(Call call) {
        callHistory.removeCall(call);
    }

    // MODIFIES: this
    // EFFECTS: writes the call history to the JSON store
    public void saveCallHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(callHistory);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Call history saved successfully.", 
                                            "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to save call history.", "Save", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the call history from the JSON store
    public void loadCallHistory() {
        try {
            callHistory = jsonReader.read();
            JOptionPane.showMessageDialog(frame, "Call history loaded successfully.", 
                                            "Load", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to load call history.", "Load", JOptionPane.ERROR_MESSAGE);
        }
    }

    // REQUIRES: name != null
    // EFFECTS: returns true if calls with the given name are found, false otherwise
    public boolean searchCallHistory(String name) {
        return callHistory.filterByName(name).size() > 0;
    }

    // EFFECTS: prints all events in the event log to the console
    private void printEventLog() {
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event);
        }
    }
}









