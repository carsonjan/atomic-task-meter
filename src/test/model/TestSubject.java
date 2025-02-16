package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Tests for Subject and NodeLike abstract class
public class TestSubject {

    private Project subject1;
    private Project subject2;
    private Task task1;
    private Task task2;
    
    @BeforeEach
    void runBefore() {
        subject1 = new Project("MATH 100");
        subject2 = new Project("CHEM-123");
        task1 = new Task("MATH 100//ch1", 2);
        task2 = new Task("MATH 100//ch2", 3);
    }

    @Test
    void testSubject() {
        assertEquals("MATH 100", subject1.getName());
        assertEquals(Duration.ZERO, subject1.getTotalEstTime());
        assertEquals(Duration.ZERO, subject1.getTotalActTime());
        assertTrue(subject1.getTasks().isEmpty());

        assertEquals("CHEM-123", subject2.getName());
        assertEquals(Duration.ZERO, subject2.getTotalEstTime());
        assertEquals(Duration.ZERO, subject2.getTotalActTime());
        assertTrue(subject2.getTasks().isEmpty());
    }

    //Test NodeLike
    @Test
    void addTaskTest() {
        subject1.addTask(task1);
        assertEquals(1, subject1.getTasks().size());
        subject1.addTask(task2);
        assertEquals(2, subject1.getTasks().size());
    }

    @Test
    void removeTaskTest() {
        subject1.addTask(task1);
        subject1.addTask(task2);
        subject1.removeTask("MATH 100//ch1");
        assertEquals(1, subject1.getTasks().size());
    }

    @Test
    void findTaskTest() {
        subject1.addTask(task1);
        subject1.addTask(task2);
        assertEquals(Duration.ofHours(2), subject1.findTask("MATH 100//ch1").getEstTime());
        assertEquals(2, subject1.getTasks().size());
    }

    @Test
    void updateTotalEstTimeTest() {
        subject1.addTask(task1);
        subject1.addTask(task2);
        subject1.updateTotalEstTime();
        assertEquals(Duration.ofHours(5), subject1.getTotalEstTime());
    }

    @Test
    void updateTotalActTimeTest() {
        subject1.addTask(task1);
        subject1.addTask(task2);
        task1.addActTime(Duration.ofMinutes(15));
        subject1.updateTotalActTime();
        assertEquals(Duration.ofMinutes(15), subject1.getTotalActTime());
        task1.addActTime(Duration.ofMinutes(30));
        task2.addActTime(Duration.ofMinutes(20));
        subject1.updateTotalActTime();
        assertEquals(Duration.ofMinutes(65), subject1.getTotalActTime());
    }
}
