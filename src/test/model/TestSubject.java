package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSubject {

    private Project subject1;
    private Project subject2;
    
    @BeforeEach
    void runBefore() {
        subject1 = new Project("MATH-100");
        subject2 = new Project("CHEM-123");
    }

    @Test
    void testSubject() {
        assertEquals("MATH-100", subject1.getName());
        assertEquals(Duration.ZERO, subject1.getTotalEstTime());
        assertEquals(Duration.ZERO, subject1.getTotalActTime());
        assertTrue(subject1.getTasks().isEmpty());

        assertEquals("CHEM-123", subject2.getName());
        assertEquals(Duration.ZERO, subject2.getTotalEstTime());
        assertEquals(Duration.ZERO, subject2.getTotalActTime());
        assertTrue(subject2.getTasks().isEmpty());
    }
}
