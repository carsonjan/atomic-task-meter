package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTask {

    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    void runBefore() {
        task1 = new Task("MATH-100//tests-study/MT1-study", 3);
        task2 = new Task("CHEM-123//homework/ch1/pre-read", 1);
        task3 = new Task("CHEM-123//register", 0.25);
    }

    @Test
    void testTask() {
        assertEquals("MATH-100//tests-study/MT1-study", task1.getPath());
        assertEquals(Duration.ofHours((long) 3), task1.getEstTime());
        assertEquals(Duration.ZERO, task1.getActTime());
        assertFalse(task1.isDone());
        assertEquals("MT1-study", task1.getName());
        assertEquals("MATH-100//tests-study", task1.getParentPath());
        assertEquals("MATH-100", task1.getSubjectName());

        assertEquals("CHEM-123//homework/ch1/pre-read", task2.getPath());
        assertEquals(Duration.ofHours((long) 1), task2.getEstTime());
        assertEquals(Duration.ZERO, task2.getActTime());
        assertFalse(task2.isDone());
        assertEquals("pre-read", task2.getName());
        assertEquals("CHEM-123//homework/ch1", task2.getParentPath());
        assertEquals("CHEM-123", task2.getSubjectName());

        assertEquals("CHEM-123//register", task3.getPath());
        assertEquals(Duration.ofHours((long) 0.25), task3.getEstTime());
        assertEquals(Duration.ZERO, task3.getActTime());
        assertFalse(task3.isDone());
        assertEquals("register", task3.getName());
        assertEquals(null, task3.getParentPath());
        assertEquals("CHEM-123", task3.getSubjectName());
    }
}