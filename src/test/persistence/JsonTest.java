package persistence;

import model.Call;
import model.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// From Worker Project
// Holds a very helpful method for testing our JsonReader and JsonWriter classes
// Therefore, acts as a superclass
public class JsonTest {
    // EFFECTS: Checks if the selected call from the call history that was read 
    //          from the JsonObject is correct
    protected void checkCall(int year, String month, int day, String phoneNumber,
                             String status, String name, String title, 
                             String summary, Call call) {
        assertEquals(year, call.getDate().getYear());
        assertEquals(month, call.getDate().getMonth());
        assertEquals(day, call.getDate().getDay());
        assertEquals(phoneNumber, call.getPhoneNumber());
        assertEquals(status, call.getStatus());
        assertEquals(name, call.getName());
        assertEquals(title, call.getTitle());
        assertEquals(summary, call.getSummary());
        // assertEquals(, thingy.getCategory());
    }

    // EFFECTS: returns a specified call
    //          Mainly used to lower the line limit of one of my testing methods
    protected Call returnCall() {
        Date date2 = new Date(2001, "July", 2);
        Call call2 = new Call(date2, "2323", "Outgoing: Accepted");
        call2.setName("Jeremy");
        call2.setTitle("Hello World");
        call2.setSummary("...");
        return call2;
    }
}
