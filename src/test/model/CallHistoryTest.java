package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CallHistoryTest {
    private CallHistory testingHistory;
    private Call call1;
    private Date date1;
    private Call call2;
    private List<Call> testingList;

    @BeforeEach
    void runBefore() {
        testingHistory = new CallHistory();
        date1 = new Date(2022, "July", 12);
        call1 = new Call(date1, "7782366046", "Receiving: Missed");
        call2 = new Call(date1, "1234567890", "Outgoing: Accepted");
        testingList = new ArrayList<Call>();
    }

    @Test
    void testGetCallHistory() {
        assertEquals(0, testingHistory.getCallHistory().size());
        assertEquals(testingList, testingHistory.getCallHistory());
    }

    @Test 
    void testAddCall() {
        assertEquals(0, testingHistory.getCallHistory().size());
        testingHistory.addCall(call1);
        assertEquals(1, testingHistory.getCallHistory().size());
        assertEquals(call1, testingHistory.getCallHistory().get(0));
        testingHistory.addCall(call2);
        assertEquals(2, testingHistory.getCallHistory().size());
        assertEquals(call1, testingHistory.getCallHistory().get(0));
        assertEquals(call2, testingHistory.getCallHistory().get(1));
    }

    @Test
    void testRemoveCall() {
        assertEquals(0, testingHistory.getCallHistory().size());
        testingHistory.removeCall(call1);
        assertEquals(0, testingHistory.getCallHistory().size());
        testingHistory.addCall(call1);
        assertEquals(1, testingHistory.getCallHistory().size());
        assertEquals(call1, testingHistory.getCallHistory().get(0));
        testingHistory.removeCall(call1);
        assertEquals(0, testingHistory.getCallHistory().size());
        assertEquals(testingList, testingHistory.getCallHistory());
    }

    @Test
    void testRemoveCall2() {
        assertEquals(0, testingHistory.getCallHistory().size());
        testingHistory.addCall(call1);
        assertEquals(1, testingHistory.getCallHistory().size());
        assertEquals(call1, testingHistory.getCallHistory().get(0));
        testingHistory.removeCall(0);
        assertEquals(0, testingHistory.getCallHistory().size());
        assertEquals(testingList, testingHistory.getCallHistory());
    }

    @Test
    void testFilterByName() {
        call1.setName("Jeremy");
        call2.setName("Jonny");
        assertEquals(0, testingHistory.filterByName("Jeremy").size());
        testingHistory.addCall(call1);
        assertEquals(1, testingHistory.filterByName("Jeremy").size());
        assertEquals(call1, testingHistory.filterByName("Jeremy").get(0));
        assertEquals(0, testingHistory.filterByName("Jeremiah").size());
        assertEquals(testingList, testingHistory.filterByName("Jeremiah"));
        testingHistory.addCall(call2);
        assertEquals(0, testingHistory.filterByName("Jeremiah").size());
        assertEquals(testingList, testingHistory.filterByName("Jeremiah"));
        assertEquals(1, testingHistory.filterByName("Jonny").size());
        assertEquals(call2, testingHistory.filterByName("Jonny").get(0));
        call2.setName("Jeremy");
        assertEquals(2, testingHistory.filterByName("Jeremy").size());
        assertEquals(call1, testingHistory.filterByName("Jeremy").get(0));
        assertEquals(call2, testingHistory.filterByName("Jeremy").get(1));

    }

}
