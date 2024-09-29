package model;

import org.json.JSONObject;

import persistence.Writable;

/*
 * The Call class is the arbitrary element X 
 * that can be added to the Call History (the log that keeps track of the calls).
 * The Call Class consists of many private fields:
 * - the date 
 * - the phone number
 * - the name
 * - the status, which can only be one of the following within this list:
 * ["Receiving: Accepted", "Receiving: Rejected", "Receiving: Missed", 
 *  "Outgoing: Accepted", "Outgoing: Rejected", "Outgoing: Missed"]
 * - the title 
 * - the summary
 * The Call class is mainly used as a data object in the console / ui.
 * As such, it holds very simplistic methods (mostly getter or setter), but
 * it does have other public methods, that mainly act as helpers for certain 
 * fields in this Call class.
 */

public class Call implements Writable {
    private Date date;
    private String phoneNumber;
    private String status;
    private String name;
    private String title;
    private String summary;

    // EFFECTS: Initializes a Call object with the given parameters
    //          + Default values for the name, title, summary.
    public Call(Date date, String phoneNumber, String status) {
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.name = "unknown name";
        this.title = "No Title";
        this.summary = "";
    }

    public Date getDate() {
        return date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
    
    public String getSummary() {
        return summary;
    }

    public void setName(String name) {
        this.name = name;
    } 

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }

    // this is somehow covered by tests?
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date.toJson());
        json.put("phoneNumber", phoneNumber);
        json.put("status", status);
        json.put("name", name);
        json.put("title", title);
        json.put("summary", summary);
        return json;
    }
}
