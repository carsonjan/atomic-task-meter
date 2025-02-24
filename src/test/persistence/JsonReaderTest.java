package persistence;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

// source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAtm() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAtm.json");
        try {
            ATM atm = reader.read();
            assertEquals(0, atm.getProjects().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAtm.json");
        try {
            ATM atm = reader.read();
            assertEquals(2, atm.getProjects().size());
            assertEquals(2, atm.getProjects().get("cpsc 210").getTasks().size());
            assertEquals(0, atm.getProjects().get("dsci 100").getTasks().size());
            Task ch1 = atm.findTask("cpsc 210", "ch1 study");
            assertEquals("cpsc 210//ch1 study", ch1.getPath());
            assertEquals(Duration.ofHours(2), ch1.getEstTime());
            assertEquals(false, ch1.getIsDone());
            assertEquals(Duration.ZERO, ch1.getActTime());
            assertEquals("ch1 study", ch1.getName());
            assertEquals("cpsc 210", ch1.getProjectName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}