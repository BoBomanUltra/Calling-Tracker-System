package persistence;

import model.CallHistory;
import org.json.JSONObject;

import java.io.*;
// Templating based on Workroom Project Json Writer
// Represents a writer that writes JSON representation of Call History to file

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // From Workroom Project
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }
    
    // From Workroom Project
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // From Workroom Project
    // MODIFIES: this
    // EFFECTS: writes JSON representation of call history to file
    public void write(CallHistory callHistory) {
        JSONObject json = callHistory.toJson();
        saveToFile(json.toString(TAB));
    }

    // From Workroom Project
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // From Workroom Project
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
