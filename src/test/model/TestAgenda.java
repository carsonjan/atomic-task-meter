package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAgenda {

    private Agenda agenda1;
    private Agenda agenda2;

    @BeforeEach
    void runBefore() {
        agenda1 = new Agenda("2025-02-01");
        agenda2 = new Agenda("2028-08-31");
    }

    @Test
    void testAgenda() {
        assertEquals(LocalDate.of(2025, 2, 1), agenda1.getDate());
        assertTrue(agenda1.getEvents().isEmpty());
        assertTrue(agenda1.getTasks().isEmpty());
        assertEquals(Duration.ZERO, agenda1.getEstTime());
        assertEquals(Duration.ZERO, agenda1.getActTime());

        assertEquals(LocalDate.of(2028, 8, 31), agenda2.getDate());
        assertTrue(agenda2.getEvents().isEmpty());
        assertTrue(agenda2.getTasks().isEmpty());
        assertEquals(Duration.ZERO, agenda2.getEstTime());
        assertEquals(Duration.ZERO, agenda2.getActTime());
    }
}
