package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTask {

    private Task task1;

    @BeforeEach
    void runBefore() {
        task1 = new Task("cs 123//ch1", 2.5);
    }

    @Test
    void constructorTest() {
        assertEquals("cs 123//ch1", task1.getPath());
        assertFalse(task1.getIsDone());
        assertEquals(Duration.ofMinutes(150), task1.getEstTime());
        assertEquals(Duration.ZERO, task1.getActTime());
        assertEquals("cs 123", task1.getProjectName());
        assertEquals("ch1", task1.getName());
    }

    @Test
    void markDoneTest() {
        assertTrue(task1.markDone());
        assertTrue(task1.getIsDone());
        assertFalse(task1.markDone());
        assertTrue(task1.getIsDone());
    }

    @Test
    void markUndoneTest() {
        task1.markDone();
        assertTrue(task1.markUndone());
        assertFalse(task1.getIsDone());
        assertFalse(task1.markUndone());
        assertFalse(task1.getIsDone());
    }

    @Test 
    void addActTimeTest() {
        task1.addActTime(Duration.ofMinutes(15));
        task1.addActTime(Duration.ofMinutes(100));
        assertEquals(Duration.ofMinutes(115), task1.getActTime());
    }
}