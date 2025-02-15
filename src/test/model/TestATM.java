package model;

import static org.junit.jupiter.api.Assertions.*;

// import java.time.LocalDate;
import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import exceptions.DuplicateNameException;
// import exceptions.NameNotExistException;

public class TestATM {
    private ATM atm1;

    @BeforeEach
    void runBefore() {
        atm1 = new ATM();
    }

    @Test
    void constructorTest() {
        assertNull(atm1.getCurrentTask());
        assertNull(atm1.getLastStartTime());
        assertTrue(atm1.getProjects().isEmpty());
    }

    @Test
    void makeProjectTest() {
        atm1.makeProject("cs 123");
        assertEquals(1, atm1.getProjects().size());
        assertNotNull(atm1.getProjects().get("cs 123"));
    }

    @Test 
    void findProjectTest() {
        atm1.makeProject("cs 123");
        assertNotNull(atm1.findProject("cs 123"));
        assertNull(atm1.findProject("cs 456"));
    }

    @Test
    void removeProjectTest() {
        atm1.makeProject("cs 123");
        assertNotNull(atm1.removeProject("cs 123"));
        assertTrue(atm1.getProjects().isEmpty());
        assertNull(atm1.removeProject("cs 123"));
        assertTrue(atm1.getProjects().isEmpty());
    }

    @Test
    void makeTaskTest() {
        atm1.makeProject("cs 123");
        assertTrue(atm1.findProject("cs 123").getTasks().isEmpty());
        atm1.makeTask("cs 123", "ch1", 3);
        assertEquals(1, atm1.findProject("cs 123").getTasks().size());
    }

    @Test
    void findTaskTest() {
        atm1.makeProject("cs 123");
        atm1.makeTask("cs 123", "ch1", 3);
        assertEquals(Duration.ofHours(3), atm1.findTask("cs 123", "ch1").getEstTime());
    }

    @Test
    void removeTaskTest() {
        atm1.makeProject("cs 123");
        atm1.makeTask("cs 123", "ch1", 3);
        Task result = atm1.removeTask("cs 123", "ch1");
        assertEquals(Duration.ofHours(3), result.getEstTime());
        assertTrue(atm1.findProject("cs 123").getTasks().isEmpty());
    }

    @Test
    void startTaskTest() {
        atm1.makeProject("cs 123");
        atm1.makeTask("cs 123", "ch1", 3);
        Task task = atm1.findTask("cs 123", "ch1");
        assertNull(atm1.startTask(task));
        assertNotNull(atm1.startTask(task));
        assertEquals(task, atm1.getCurrentTask());
        assertNotNull(atm1.getLastStartTime());
    }

    @Test
    void stopTaskTest() {
        atm1.makeProject("cs 123");
        atm1.makeTask("cs 123", "ch1", 3);
        Task task = atm1.findTask("cs 123", "ch1");
        assertNull(atm1.stopTask(task));
        atm1.startTask(task);
        atm1.setLastStartTime(LocalDateTime.now().minusMinutes(15));
        Double result = atm1.stopTask(task);
        assertNull(atm1.getCurrentTask());
        assertNull(atm1.getLastStartTime());
        assertEquals(0.25, result);
    }

    @Test
    void updateProjectTimeTest() {
        atm1.makeProject("cs 123");
        Project project1 = atm1.findProject("cs 123");
        atm1.makeTask("cs 123", "ch1", 3);
        atm1.makeTask("cs 123", "ch2", 2);
        Task task1 = atm1.findTask("cs 123", "ch1");
        Task task2 = atm1.findTask("cs 123", "ch2");

        task1.addActTime(Duration.ofMinutes(15));
        task2.addActTime(Duration.ofMinutes(20));

        atm1.updateProjectTime("cs 123");
        assertEquals(Duration.ofHours(5), project1.getTotalEstTime());
        assertEquals(Duration.ofMinutes(35), project1.getTotalActTime());
    }

    @Test 
    void formatTimeTest() {
        assertEquals(0.25, atm1.formatTime(Duration.ofMinutes(15)));
    }
}
