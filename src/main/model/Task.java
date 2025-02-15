package model;

import java.time.Duration;

// Represents a task with its name, estimated time, actual time, is done?
public class Task {
    private String path;        // unique: path to the task
    private boolean isDone;     // if the project is done?
    private Duration estTime; // estimated time to finish task
    private Duration actTime; // actual time to finish task

    private String projectName; // name of the project this task belongs to
    private String name; // name of this task

    /*
     * REQUIRES: path is correctly formatted and not empty. 
     *          estTime is a positive number of hours
     * EFFECTS: initializes a Task with its path, estimated time
     *          and actual time to zero, is done? to false.
     *          derive path into additional subject name, task name
     */
    public Task(String path, double estTime) {
        this.path = path;
        this.isDone = false;
        this.estTime = Duration.ofMinutes(Math.round(estTime * 60.0));
        this.actTime = Duration.ZERO;

        String[] splitPath = path.split("//");
        this.projectName = splitPath[0];
        this.name = splitPath[1];
    }

    public String getPath() {
        return path;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public Duration getEstTime() {
        return estTime;
    }

    public Duration getActTime() {
        return actTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getName() {
        return name;
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
        actTime = actTime.plus(duration);
    }
    
}
