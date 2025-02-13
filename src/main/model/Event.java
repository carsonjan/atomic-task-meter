package model;

import java.time.LocalDate;

// Represents an event with its name, date
public class Event {
    private String name;        // unique: name of the event
    private LocalDate date;     // date of the event

    /*
     * REQUIRES: name is properly formatted and not empty, date is properly formatted and not empty
     * EFFECTS: initializes an event with its name and date
     */
    public Event(String name, String date) {
        this.name = name;
        this.date = LocalDate.parse(date);
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

}
