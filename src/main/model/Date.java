package model;

import org.json.JSONObject;

import persistence.Writable;

/* 
* The Date Class provides a way to represent the date of the call.
* Consists of three fields; year, month, day.
* Only have getter methods to access the private fields of the date.
*/
public class Date implements Writable {
    private int year;
    private String month;
    private int day;

    // EFFECTS: Initializes a Date object with the given parameters
    public Date(int year, String month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("year", year);
        json.put("month", month);
        json.put("day", day);
        return json;
    }
}
