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
    private String subjectName;    // subject this task belongs to, derived from path
    private String name;        // name of this task, derived from path
    private String parentPath;  // path of this task's parent, derive from path, null if none

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

        String[] subjectAndPath = path.split("//");
        this.subjectName = subjectAndPath[0];
        String pathNoSubj = subjectAndPath[1];
        int lastDelim = pathNoSubj.lastIndexOf("/"); // the / that separates out task name
        if (lastDelim != -1) {  // have parents eg: project/proj1
            this.name = pathNoSubj.substring(lastDelim + 1); 
            this.parentPath = subjectName + "//" + pathNoSubj.substring(0, lastDelim);
        } else {  // no parents eg: register
            this.name = pathNoSubj;
            this.parentPath = null;
        }
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

    public String getSubjectName() {
        return subjectName;
    }

    public String getName() {
        return name;
    }

    public String getParentPath() {
        return parentPath;
    }
    
    // end getters
}
