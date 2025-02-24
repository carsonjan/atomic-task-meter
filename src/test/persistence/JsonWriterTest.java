package persistence;

import org.junit.jupiter.api.Test;

import model.*;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

// source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            new ATM();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ATM atm = new ATM();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAtm.json");
            writer.open();
            writer.write(atm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAtm.json");
            atm = reader.read();
            assertTrue(atm.getProjects().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneralWorkroom() {
        try {
            ATM atm = new ATM();
            atm.makeProject("cpsc 210");
            atm.makeProject("dsci 100");
            atm.makeTask("cpsc 210", "ch1 study", 2);
            atm.makeTask("cpsc 210", "ch2 study", 3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAtm.json");
            writer.open();
            writer.write(atm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAtm.json");
            atm = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}