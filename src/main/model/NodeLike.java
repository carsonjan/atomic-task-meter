package model;

import java.time.Duration;
import java.util.HashMap;

// Represents an Node-Like objects (Subject, Task, Agenda)
public class NodeLike {
    protected Duration estTime;   // estimated time (hours) to finish the task OR all subtasks if Tasks not empty
    protected Duration actTime;   // actual time (hours) to finish the task OR all subtasks if Tasks not empty
    protected HashMap<String, Task> tasks;        // collection of tasks that belongs to the Node, path as key

    /*
     * EFFECTS: initializes the Node. Set estimated and actual time to zero, tasks to empty collection
     */
    public NodeLike() {
        this.estTime = Duration.ZERO;
        this.actTime = Duration.ZERO;
        this.tasks = new HashMap<String, Task>();
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
}
