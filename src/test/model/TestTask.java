package model;

import static org.junit.jupiter.api.Assertions.*;
import exceptions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTask {

    private Task task1;
    private Task task2;
    private Task task3;
    private Task task0;
    private Task task4;
    private Task task5;
    private Task task6;

    @BeforeEach
    void runBefore() {
        task0 = new Task("MATH-100//tests-study", 6);
        task1 = new Task("MATH-100//tests-study/MT1-study", 3);
        task5 = new Task("MATH-100//tests-study/MT1-study/ch1", 1);
        task6 = new Task("MATH-100//tests-study/MT1-study/ch2", 1);
        task4 = new Task("MATH-100//tests-study/MT1-PP", 2);
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
        assertEquals(Duration.ofMinutes((long) 15), task3.getEstTime());
        assertEquals(Duration.ZERO, task3.getActTime());
        assertFalse(task3.isDone());
        assertEquals("register", task3.getName());
        assertEquals(null, task3.getParentPath());
        assertEquals("CHEM-123", task3.getSubjectName());
    }

    @Test
    void testMarkDone() {
        assertFalse(task1.isDone()); //init
        assertTrue(task1.markDone()); //first mark done
        assertTrue(task1.isDone());
        assertFalse(task1.markDone()); //second mark done
        assertTrue(task1.isDone());
        assertTrue(task1.markUndone()); // first mark undone
        assertFalse(task1.isDone());
        assertFalse(task1.markUndone()); //second mark undone
        assertFalse(task1.isDone());
        assertTrue(task1.markDone()); //third mark done
        assertTrue(task1.isDone());
    }

    @Test
    void testAddActTime() {
        assertEquals(Duration.ZERO, task1.getActTime()); // init
        task1.addActTime(Duration.ofHours(1)); // first add
        assertEquals(Duration.ofMinutes(60), task1.getActTime());
        task1.addActTime(Duration.ofHours(2)); // second add
        assertEquals(Duration.ofMinutes(180), task1.getActTime());
        task1.addActTime(Duration.ofMinutes(15)); // third add
        assertEquals(Duration.ofMinutes(195), task1.getActTime());
    }

    // test NodeLike
    @Test
    void testAddTask() {
        assertTrue(task0.getTasks().isEmpty());
        assertTrue(task4.getTasks().isEmpty());
        assertTrue(task1.getTasks().isEmpty());
        try {
            task0.addTask(task4);
            assertEquals(1, task0.getTasks().size());
            task1.addTask(task5); 
            task1.addTask(task6);
            assertEquals(2, task1.getTasks().size());
            task0.addTask(task1); 
            assertEquals(2, task0.getTasks().size()); // task0 has task 4 and 1
            try { // add duplicates
                task0.addTask(task1);
                fail();
            } catch (DuplicatePathException e) {
                // pass
            }
        } catch (Exception e) { // catch add task 
            fail();
        }
    }

    @Test
    void testFindTask() {
        try {
            task0.addTask(task1);
            task0.addTask(task4);
            task1.addTask(task5);
            task1.addTask(task6);
            assertEquals(task1, task0.findTask(task1.getPath()));
            assertEquals(task5, task0.findTask(task5.getPath()));
        } catch (Exception e) {
            fail();
        }
        try {
            task1.findTask(task0.getPath());
        } catch (PathNotExistException e) {
            // pass
        } catch (Exception e) {
            fail();
        }     
    }

    @Test
    void testRemoveTask() {
        try {
            task0.addTask(task1);
            task0.addTask(task4);
            task1.addTask(task5);
            task1.addTask(task6);
            assertEquals(2, task0.getTasks().size());
            assertTrue(task0.getTasks().containsValue(task1));    
        } catch (Exception e) {
            fail();
        }
        try {
            task0.removeTask(task1.getPath());
            assertFalse(task0.getTasks().containsValue(task1));
        } catch (Exception e) {
            fail();
        }
        try {
            task0.removeTask(task1.getPath());
            fail();
        } catch (PathNotExistException e) {
            // pass
        }
    }

    @Test
    void testUpdateEstTime() {
        assertEquals(Duration.ofHours(6), task0.getEstTime()); // init
        task0.updateEstTime(); // no child update
        assertEquals(Duration.ofHours(6), task0.getEstTime());
        try {
            task0.addTask(task1); // add task 1 into 0
            task0.updateEstTime(); // have child update
            assertEquals(Duration.ofHours(3), task0.getEstTime());
            task0.addTask(task4); // add task 4 into 0
            task0.updateEstTime(); // have child update
            assertEquals(Duration.ofHours(5), task0.getEstTime());
            task1.addTask(task5); // task 1 get subtask
            assertEquals(Duration.ofHours(1), task1.getEstTime());
            assertEquals(Duration.ofHours(4), task0.getEstTime());
            task1.addTask(task6); // task 1 get another subtask
            assertEquals(Duration.ofHours(2), task1.getEstTime());
            assertEquals(Duration.ofHours(5), task0.getEstTime());

        } catch (Exception e) { // catch addTask
            fail();
        }
        
    }

    @SuppressWarnings("methodlength")
    @Test
    void testUpdateActTime() {
        assertEquals(Duration.ofHours(0), task0.getActTime()); // init
        task0.updateActTime(); // no child update
        assertEquals(Duration.ofHours(0), task0.getActTime());
        try {
            task0.addTask(task1); // add task 1 into 0
            task1.addActTime(Duration.ofMinutes(25)); // time task 1
            task0.updateActTime(); // have child update
            assertEquals(Duration.ofMinutes(25), task0.getActTime());
            task4.addActTime(Duration.ofMinutes(100)); // time task 4
            task0.addTask(task4); // add task 4 into 0
            task0.updateActTime(); // have child update
            assertEquals(Duration.ofMinutes(125), task0.getActTime());

            task1.addTask(task5); // task 1 get subtask
            assertEquals(Duration.ofMinutes(0), task1.getActTime()); // because subtask as time=0 
            assertEquals(Duration.ofMinutes(100), task0.getActTime());
            task5.addActTime(Duration.ofMinutes(20)); // time task 5
            assertEquals(Duration.ofMinutes(20), task1.getActTime()); 
            assertEquals(Duration.ofMinutes(120), task0.getActTime());
            task1.addTask(task6); // task 1 get another subtask
            assertEquals(Duration.ofMinutes(20), task1.getActTime()); // time stays
            assertEquals(Duration.ofMinutes(120), task0.getActTime());
            task6.addActTime(Duration.ofMinutes(50)); // time task 6
            assertEquals(Duration.ofMinutes(70), task1.getActTime()); 
            assertEquals(Duration.ofMinutes(170), task0.getActTime());

        } catch (Exception e) { // catch addTask
            fail();
        }
        
    }
}