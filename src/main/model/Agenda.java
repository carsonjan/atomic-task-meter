package model;

import java.time.LocalDate;
import java.time.Duration;
import java.util.HashMap;

// Represents an agenda with its date, events, tasks, estimated time, actual time
public class Agenda {
    private LocalDate date;         // unique: date
    private HashMap<String, Event> events;      // collection of events linked to with their name as key
    private HashMap<String, Task> tasks;        // collection of tasks linked to with their path as key
    private Duration estTime;       // estimated time to finish all tasks
    private Duration actTime;       // actual time to finish all tasks

    /*
     * REQUIRES: date is properly formatted and not empty
     * EFFECTS: initializes an agenda with its date. Set events and tasks to empty, 
     *          estimated and actual time to zero
     */
    public Agenda(String date) {
        this.date = LocalDate.parse(date);
        this.events = new HashMap<String, Event>();
        this.tasks = new HashMap<String, Task>();
        this.estTime = Duration.ZERO;
        this.actTime = Duration.ZERO;
    }

    // start getters
    public LocalDate getDate() {
        return date;
    }

    public HashMap<String, Event> getEvents() {
        return events;
    }

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    public Duration getEstTime() {
        return estTime;
    }

    public Duration getActTime() {
        return actTime;
    }
    
    // end getters
}
