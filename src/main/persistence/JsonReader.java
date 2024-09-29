package persistence;

import model.Call;
import model.CallHistory;
import model.Date;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
// Templating basse on Workroom Project JsonReader
// Represents a reader that reads JSON representation of Call History from file

public class JsonReader {
    private String source;

    // From Workroom project
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // From Workroom Project
    // IOException is an imported, so there is no Exception class I make for this
    // EFFECTS: reads call history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CallHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCallHistory(jsonObject);
    }

    // From Workroom Project
    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // From Workroom Project
    // EFFECTS: parses Call History from JSON object and returns it
    private CallHistory parseCallHistory(JSONObject jsonObject) {
        CallHistory callHistory = new CallHistory();
        addCalls(callHistory, jsonObject);
        return callHistory;
    }

    // From Workroom Project
    // MODIFIES: callHistory
    // EFFECTS: parses calls from JSON object and adds them to the Call History
    private void addCalls(CallHistory callHistory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("calls");
        for (Object json : jsonArray) {
            JSONObject nextCall = (JSONObject) json;
            addCall(callHistory, nextCall);
        }
    }

    // From Workroom Project
    // MODIFIES: callHistory
    // EFFECTS: parses call from JSON object and adds it to call history
    private void addCall(CallHistory callHistory, JSONObject jsonObject) {
        JSONObject dateJson = jsonObject.getJSONObject("date");
        Date date = new Date(dateJson.getInt("year"), dateJson.getString("month"), dateJson.getInt("day"));
        String phoneNumber = jsonObject.getString("phoneNumber");
        String status = jsonObject.getString("status");
        
        Call call = new Call(date, phoneNumber, status);

        call.setName(jsonObject.getString("name"));
        call.setTitle(jsonObject.getString("title"));
        call.setSummary(jsonObject.getString("summary"));

        callHistory.addCall(call);
    }
}