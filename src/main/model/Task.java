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
     * REQUIRES: path is correctly formatted and not empty. 
     *          estTime is a positive number of hours
     * EFFECTS: initializes a Task with its path, estimated time, sub-tasks
     *          and actual time to zero, is done? to false.
     *          derive path into additional task name, parent path, subject variable
     */
    public Task(String path, double estTime) {
        super();
        this.path = path;
        this.isDone = false;
        this.estTime = Duration.ofMinutes((long) (estTime * 60));
        this.tasks = new HashMap<String, Task>();

        // derive other parameters from path
        String[] subjectAndPath = path.split("//");
        this.subjectName = subjectAndPath[0];
        String pathNoSubj = subjectAndPath[1];
        int lastDelim = pathNoSubj.lastIndexOf("/"); // the / that separates out task name
        if (lastDelim != -1) {  // have parent task eg: project/proj1
            this.name = pathNoSubj.substring(lastDelim + 1); 
            this.parentPath = subjectName + "//" + pathNoSubj.substring(0, lastDelim);
        } else {  // no parent task eg: register
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

    /*
     * MODIFIES: this
     * EFFECTS: mark task as done
     *          return true if operation has effect (task originally not done)
     *          else return false
     */
    public Boolean markDone() {
        boolean result = !isDone; // isDone = True -> result = False
        this.isDone = true;
        return result;
    }

    /*
     * MODIFIES: this
     * EFFECTS: mark task as undone
     *          return true if operation has effect (task originally done)
     *          else return false
     */
    public Boolean markUndone() {
        boolean result = isDone; // isDone = False -> result = False
        this.isDone = false;
        return result;
    }

    /*
     * REQUIRES: duration is positive
     * MODIFIES: this
     * EFFECTS: add duration to the task's actual time
     */
    public void addActTime(Duration duration) {
        this.actTime.plus(duration);
    }
    
}
