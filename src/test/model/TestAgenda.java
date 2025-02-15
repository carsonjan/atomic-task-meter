// package model;

// import static org.junit.jupiter.api.Assertions.*;

// import java.time.LocalDate;
// import java.time.Duration;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import exceptions.DuplicateNameException;
// import exceptions.NameNotExistException;

// public class TestAgenda {

//     private Agenda agenda1;
//     private Agenda agenda2;
//     private Event event1;
//     private Event event2;

//     @BeforeEach
//     void runBefore() {
//         agenda1 = new Agenda("2025-02-01");
//         agenda2 = new Agenda("2028-08-31");
//         event1 = new Event("MATH-100", "2025-02-01");
//         event2 = new Event("CHEM-123", "2025-02-01");
//     }

//     @Test
//     void testAgenda() {
//         assertEquals(LocalDate.of(2025, 2, 1), agenda1.getDate());
//         assertTrue(agenda1.getEvents().isEmpty());
//         assertTrue(agenda1.getTasks().isEmpty());
//         assertEquals(Duration.ZERO, agenda1.getTotalEstTime());
//         assertEquals(Duration.ZERO, agenda1.getTotalActTime());

//         assertEquals(LocalDate.of(2028, 8, 31), agenda2.getDate());
//         assertTrue(agenda2.getEvents().isEmpty());
//         assertTrue(agenda2.getTasks().isEmpty());
//         assertEquals(Duration.ZERO, agenda2.getTotalEstTime());
//         assertEquals(Duration.ZERO, agenda2.getTotalActTime());
//     }

//     @Test
//     void testAddEvent() {
//         try {
//             agenda1.addEvent(event1);
//             assertTrue(agenda1.getEvents().containsValue(event1));
//             agenda1.addEvent(event2);
//             assertTrue(agenda1.getEvents().containsValue(event1));
//             assertTrue(agenda1.getEvents().containsValue(event2));
//         } catch (Exception e) {
//             fail();
//         }
//         try {
//             agenda1.addEvent(event1);
//             fail();
//         } catch (DuplicateNameException e) {
//             // pass
//         }
//     }

//     @Test
//     void testRemoveEvent() {
//         try {
//             agenda1.addEvent(event1);
//             agenda1.addEvent(event2);
//             assertEquals(event1, agenda1.removeEvent(event1.getName()));
//             assertFalse(agenda1.getEvents().containsValue(event1));
//             assertTrue(agenda1.getEvents().containsValue(event2));
//             assertEquals(event2, agenda1.removeEvent(event2.getName()));
//             assertFalse(agenda1.getEvents().containsValue(event1));
//             assertFalse(agenda1.getEvents().containsValue(event2));
//         } catch (Exception e) {
//             fail();
//         }
//         try {
//             agenda1.removeEvent(event1.getName());
//             fail();
//         } catch (NameNotExistException e) {
//             // pass
//         }
//     }

//     @Test
//     void testFindEvent() {
//         try {
//             agenda1.addEvent(event1);
//             assertEquals(event1, agenda1.findEvent(event1.getName()));
//         } catch (Exception e) {
//             fail();
//         }
//         try {
//             agenda1.findEvent(event2.getName());
//             fail();
//         } catch (NameNotExistException e) {
//             // pass
//         }
//     }

//     @Test
//     void testUpdateEstTime() {
//         assertEquals(Duration.ZERO, agenda1.getTotalEstTime());
//         Task task1 = new Task("MATH-100//tests-study/MT1-study", 3);
//         Task task4 = new Task("MATH-100//tests-study/MT1-PP", 2);
//         try {
//             agenda1.addTask(task1);
//             agenda1.updateEstTime();
//             assertEquals(Duration.ofHours(3), agenda1.getTotalEstTime());
//             agenda1.addTask(task4);
//             agenda1.updateEstTime();
//             assertEquals(Duration.ofHours(5), agenda1.getTotalEstTime());
//         } catch (Exception e) {
//             fail();
//         }
//     }

//     @Test
//     void testUpdateActTime() {
//         assertEquals(Duration.ZERO, agenda1.getTotalActTime());
//         Task task1 = new Task("MATH-100//tests-study/MT1-study", 3);
//         Task task4 = new Task("MATH-100//tests-study/MT1-PP", 2);
//         try {
//             agenda1.addTask(task1);
//             agenda1.updateActTime();
//             assertEquals(Duration.ZERO, agenda1.getTotalActTime());
//             task1.addActTime(Duration.ofHours(1));
//             agenda1.updateActTime();
//             assertEquals(Duration.ofHours(1), agenda1.getTotalActTime());
//             agenda1.addTask(task4);
//             agenda1.updateActTime();
//             assertEquals(Duration.ofHours(1), agenda1.getTotalActTime());
//             task4.addActTime(Duration.ofHours(2));
//             agenda1.updateActTime();
//             assertEquals(Duration.ofHours(3), agenda1.getTotalActTime());
//         } catch (Exception e) {
//             fail();
//         }
//     }
// }
