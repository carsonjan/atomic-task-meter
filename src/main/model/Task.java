package model;

import java.time.Duration;
import java.util.HashMap;

// Represents a task with its path, estimated time, actual time, is done?, sub-tasks
public class Task extends NodeLike {
    private String path;        // unique: path to the task
    private boolean isDone;     // if the project is done?

    private String subjectName;    // subject this task belongs to, derived from path
    private String name;        // name of this task, derived from path
    private String parentPath;  // path of this task's parent, derive from path, null if none

    /*
     * REQUIRES: path is correctly formatted and not empty. estTime is a positive number of hours  
     * EFFECTS: initializes a Task with its path, estimated time, sub-tasks
     *          and actual time to zero, is done? to false.
     *          derive path into additional task name, parent path, subject variable
     */
    public Task(String path, double estTime) {
        super();
        this.path = path;
        this.isDone = false;
        this.estTime = Duration.ofHours((long) estTime);
        this.tasks = new HashMap<String, Task>();

        // derive other parameters from path
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

    public String getPath() {
        return path;
    }

    public boolean isDone() {
        return isDone;
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
    
}
