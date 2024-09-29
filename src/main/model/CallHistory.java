package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/*
 * Represents a list of calls with complex methods
 * that will be helpful for certain features in the ui.
 */
public class CallHistory implements Writable {
    private List<Call> callHistory;

    // EFFECTS: Initilizes the CallHistory object with a new empty ArrayList
    //          that keeps track of the calls
    public CallHistory() {
        callHistory = new ArrayList<Call>();
    }

    // I ran into issues with Exceptions with this Event Log, but doesn't actually hurt my program?
    public List<Call> getCallHistory() {
        // EventLog.getInstance().logEvent(new Event("Displaying the entire Call History!"));
        return callHistory;
    }

    // When loading, this method gets called, so the event log will show a lot of calls added to the 
    // Call History
    // MODIFIES: this
    // EFFECTS: Adds a call to callHistory
    public void addCall(Call c) {
        EventLog.getInstance().logEvent(new Event("Added a call to the Call History: Name is "
                                                    + c.getName() + ", Date is " + c.getDate().getYear() 
                                                    + "-" + c.getDate().getMonth() + "-" + c.getDate().getDay()));
        callHistory.add(c);
    }

    // MODIFIES: this
    // EFFECTS: Removes a call in callHistory
    public void removeCall(Call c) {
        EventLog.getInstance().logEvent(new Event("Removed a call from Call History: Name is "
                                                    + c.getName() + ", Date is " + c.getDate().getYear() 
                                                    + "-" + c.getDate().getMonth() + "-" + c.getDate().getDay()));
        callHistory.remove(c);
    }

    // MODIFIES: this
    // EFFECTS: Removes a call in callHistory by index
    // This method is never used, but I just have it here lol
    public void removeCall(int index) {
        callHistory.remove(index);
    }

    // EFFECTS: Returns a new list that only has calls
    //          that has the given name for the contact
    public List<Call> filterByName(String name) {
        List<Call> filteredList = new ArrayList<Call>();
        for (Call currentCall : callHistory) {
            if (name.equals(currentCall.getName())) {
                filteredList.add(currentCall);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered the Call History by name: " + name
                                                    + ", and got this many matching results: " 
                                                    + filteredList.size()));
        return filteredList;
    }

    @Override
    // Effects: Turns Call History into a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Call call : callHistory) {
            jsonArray.put(call.toJson());
        }
        json.put("calls", jsonArray);
        return json;
    }

}
