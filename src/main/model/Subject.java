package model;

import java.time.Duration;
import java.util.HashMap;

// Represents a subject (or a project) with it's name, estimated time, and tasks
public class Subject {
    private String name;            // unique: name of the subject
    private Duration estTime;       // estimated time to finish all tasks
    private Duration actTime;       // actual time to finish all tasks
    private HashMap<String, Task> tasks;        // collection of tasks that belongs to the subject

    /*
     * REQUIRES: name is correctly formatted and not empty
     * EFFECTS: initializes the name. Set estimated and actual time to zero, tasks to empty collection
     */
    public Subject(String name) {
        this.name = name;
        this.estTime = Duration.ZERO;
        this.actTime = Duration.ZERO;
        this.tasks = new HashMap<String, Task>();
    }

    // start getters
    public String getName() {
        return name;
    }

    public Duration getEstTime() {
        return estTime;
    }

    public Duration getActTime() {
        return actTime;
    }

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    //end getters
}
