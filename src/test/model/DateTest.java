package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateTest {
    private Date date1;

    @BeforeEach
    void runBefore() {
        date1 = new Date(2022, "July", 12);
    }

    @Test
    void testConstructor() {
        assertEquals(2022, date1.getYear());
        assertEquals("July", date1.getMonth());
        assertEquals(12, date1.getDay());
    }

    // When I'm testing for the Constructor, I'm also testing for all the getter methods
    // As such, I don't create tests for getters, as it's just repetitive.

    // @Test
    // void testGetters() {
    //     assertEquals(2022, date1.getYear());
    //     assertEquals("July", date1.getMonth());
    //     assertEquals(12, date1.getDay());
    // }
}

