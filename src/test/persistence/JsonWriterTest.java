package persistence;

import model.Call;
import model.CallHistory;
import model.Date;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            CallHistory callHistory = new CallHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCallHistory() {
        try {
            CallHistory callHistory = new CallHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCallHistory.json");
            writer.open();
            writer.write(callHistory);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCallHistory.json");
            callHistory = reader.read();
            assertEquals(0, callHistory.getCallHistory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            CallHistory ch = new CallHistory();
            Date date1 = new Date(2000, "June", 1);
            Call call2 = returnCall(); // Used to lower line limit for checkstyle
            ch.addCall(new Call(date1, "1212", "Receiving: Accepted"));
            ch.addCall(call2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCallHistory.json");
            writer.open();
            writer.write(ch);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCallHistory.json");
            ch = reader.read();
            List<Call> calls = ch.getCallHistory();
            assertEquals(2, calls.size());
            checkCall(2000, "June", 1, "1212", "Receiving: Accepted", "unknown name",
                        "No Title", "", calls.get(0));
            checkCall(2001, "July", 2, "2323", "Outgoing: Accepted", "Jeremy",
                        "Hello World", "...", calls.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
