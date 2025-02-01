package model;

import java.time.Duration;
import java.util.HashMap;

// Represents a task with its path, estimated time, actual time, is done?, sub-tasks
public class Task {
    private String path;        // unique: path to the task
    private Duration estTime;   // estimated time (minutes) to finish the task OR all subtasks if subTasks not empty
    private Duration actTime;   // actual time (minutes) to finish the task OR all subtasks if subTasks not empty
    private boolean isDone;     // if the project is done?
    private HashMap<String, Task> subTasks; // collection of subtasks that belongs to this task, with their path as key
    private Subject subject;    // subject this task belongs to, derived from path
    private String name;        // name of this task, derived from path
    private String parentPath;  // path of this task's parent, derive from path

    /*
     * REQUIRES: path is correctly formatted and not empty. estTime is a positive integer 
     * EFFECTS: initializes a Task with its path, estimated time, sub-tasks
     *          and actual time to zero, is done? to false.
     *          derive path into additional task name, parent path, subject variable
     */
    public Task(String path, int estTime) {
        this.path = path;
        this.estTime = Duration.ofMinutes((long) estTime); // store duration as minutes
        this.actTime = Duration.ZERO;
        this.isDone = false;
        this.subTasks = new HashMap<String, Task>();
        int lastDelim = path.lastIndexOf("/"); // the / that separates out task name
        int subjDelim = path.indexOf("//"); // the // that separates out subject
        this.name = path.substring(lastDelim); 
        this.parentPath = path.substring(0, lastDelim);
        this.subject = new Subject(path.substring(0, subjDelim));
    }

    // start getters
    public String getPath() {
        return path;
    }

    public Duration getEstTime() {
        return estTime;
    }

    public Duration getActTime() {
        return actTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public HashMap<String, Task> getSubTasks() {
        return subTasks;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public String getParentPath() {
        return parentPath;
    }
    
    // end getters
}
