package persistence;

import model.Call;
import model.CallHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CallHistory ch = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCallHistory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCallHistory.json");
        try {
            CallHistory ch = reader.read();
            assertEquals(0, ch.getCallHistory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCallHistory.json");
        try {
            CallHistory ch = reader.read();
            List<Call> calls = ch.getCallHistory();
            assertEquals(2, calls.size());
            checkCall(2000, "June", 1, "1212", "Receiving: Accepted", "unknown name",
                        "No Title", "", calls.get(0));
            checkCall(2001, "July", 2, "2323", "Outgoing: Accepted", "Jeremy",
                        "Hello World", "...", calls.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
