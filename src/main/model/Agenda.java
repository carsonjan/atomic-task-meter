package model;

import java.time.LocalDate;
import java.util.HashMap;

// Represents an agenda with its date, events, tasks, estimated time, actual time
public class Agenda extends NodeLike {
    private LocalDate date;         // unique: date
    private HashMap<String, Event> events;      // collection of events linked to with their name as key

    /*
     * REQUIRES: date is properly formatted and not empty
     * EFFECTS: initializes an agenda with its date. Set events and tasks to empty, 
     *          estimated and actual time to zero
     */
    public Agenda(String date) {
        super();
        this.date = LocalDate.parse(date);
        this.events = new HashMap<String, Event>();
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<String, Event> getEvents() {
        return events;
    }

}
