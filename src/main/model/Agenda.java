package model;

import java.time.LocalDate;
import java.time.Duration;
import java.util.HashMap;

import exceptions.*;

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

    /*
     * MODIFIES: this
     * EFFECTS: add event into events, throw if name already exist in events' keys
     */
    public void addEvent(Event event) throws DuplicateNameException {
        String eventName = event.getName();
        if (events.containsKey(eventName)) {
            throw new DuplicateNameException();
        }
        events.put(eventName, event);
    }

    /*
     * MODIFIES: this
     * EFFECTS: remove event with key=name from events, return the event removed,
     *          throw if name not exist in events' keys
     */
    public Event removeEvent(String name) throws NameNotExistException {
        Event event = events.remove(name);
        if (event == null) {
            throw new NameNotExistException();
        }
        return event;
    }

    /*
     * MODIFIES: this
     * EFFECTS: return event with key=name from events
     *          throw if name not exist in events' keys
     */
    public Event findEvent(String name) throws NameNotExistException {
        Event event = events.get(name);
        if (event == null) {
            throw new NameNotExistException();
        }
        return event;
    }

    /*
     * REQUIRES: all task in tasks has correct estTime
     * MODIFIES: this
     * EFFECTS: make self.estTime the sum of tasks' estTime and return the sum
     */
    @Override
    public Duration updateEstTime() {
        Duration sum = Duration.ZERO;
        for (Task task : tasks.values()) {
            sum.plus(task.getEstTime());
        }
        return sum;
    }

    /*
     * REQUIRES: all task in tasks has correct actTime
     * MODIFIES: this
     * EFFECTS: make self.actTime the sum of tasks' actTime and return the sum
     */
    @Override
    public Duration updateActTime() {
        Duration sum = Duration.ZERO;
        for (Task task : tasks.values()) {
            sum.plus(task.getActTime());
        }
        return sum;
    }
}
