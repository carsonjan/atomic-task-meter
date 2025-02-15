// package model;

// import static org.junit.jupiter.api.Assertions.*;

// import java.time.LocalDate;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class TestEvent {

//     private Event event1;
//     private Event event2;
    
//     @BeforeEach
//     void runBefore() {
//         event1 = new Event("MATH-MT1", "2025-02-01");
//         event2 = new Event("CHEM-proj", "2028-08-31");
//     }

//     @Test
//     void testEvent() {
//         assertEquals("MATH-MT1", event1.getName());
//         assertEquals(LocalDate.of(2025, 2, 1), event1.getDate());

//         assertEquals("CHEM-proj", event2.getName());
//         assertEquals(LocalDate.of(2028, 8, 31), event2.getDate());
//     }
// }
