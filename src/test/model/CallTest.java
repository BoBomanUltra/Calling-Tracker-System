package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CallTest {
    private Call call1;
    private Date date1;

    @BeforeEach
    void runBefore() {
        date1 = new Date(2022, "July", 12);
        call1 = new Call(date1, "7782366046", "Receiving: Missed");
    }

    // When I'm testing for the Constructor, I'm also testing for all the getter methods
    // As such, I don't create tests for getters, as it's just repetitive.
    @Test
    void testConstructor() {
        assertEquals(date1, call1.getDate());
        assertEquals("7782366046", call1.getPhoneNumber());
        assertEquals("Receiving: Missed", call1.getStatus());
        assertEquals("unknown name", call1.getName());
        assertEquals("No Title", call1.getTitle());
        assertEquals("", call1.getSummary());
    }

    // When I'm testing for the Constructor, I'm also testing for all the getter methods
    // As such, I don't create tests for getters, as it's just repetitive.
    // @Test
    // void testGetters() {
    //     assertEquals(date1, call1.getDate());
    //     assertEquals("7782366046", call1.getPhoneNumber());
    //     assertEquals("Receiving: Missed", call1.getStatus());
    //     assertEquals("unknown name", call1.getName());
    //     assertEquals("No Title", call1.getTitle());
    //     assertEquals("", call1.getSummary());
    // }

    @Test 
    void testSetName() {
        assertEquals("unknown name", call1.getName());
        call1.setName("Jeremy");
        assertEquals("Jeremy", call1.getName());
    }

    @Test
    void testSetTitle() {
        assertEquals("No Title", call1.getTitle());
        call1.setTitle("Poke Meal Prep");
        assertEquals("Poke Meal Prep", call1.getTitle());
    }

    @Test
    void testSetSummary() {
        assertEquals("", call1.getSummary());
        call1.setSummary("Testing Summary...");
        assertEquals("Testing Summary...", call1.getSummary());
    }

}
