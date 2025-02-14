package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSubject {

    private Subject subject1;
    private Subject subject2;
    
    @BeforeEach
    void runBefore() {
        subject1 = new Subject("MATH-100");
        subject2 = new Subject("CHEM-123");
    }

    @Test
    void testSubject() {
        assertEquals("MATH-100", subject1.getName());
        assertEquals(Duration.ZERO, subject1.getEstTime());
        assertEquals(Duration.ZERO, subject1.getActTime());
        assertTrue(subject1.getTasks().isEmpty());

        assertEquals("CHEM-123", subject2.getName());
        assertEquals(Duration.ZERO, subject2.getEstTime());
        assertEquals(Duration.ZERO, subject2.getActTime());
        assertTrue(subject2.getTasks().isEmpty());
    }
}
