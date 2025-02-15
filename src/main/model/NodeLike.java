package model;

import java.time.Duration;
import java.util.HashMap;

// Abstract class that Represents a Node-Like object (Project, Agenda)
public abstract class NodeLike {
    protected Duration totalEstTime;   // total estimate time to finish all tasks
    protected Duration totalActTime;   // total actual time to finish all tasks
    protected HashMap<String, Task> tasks;   // collection of tasks that belongs to the Node, path as key

    /*
     * EFFECTS: initializes the Node. Set estimated and actual time to zero, tasks to empty collection
     */
    public NodeLike() {
        this.totalEstTime = Duration.ZERO;
        this.totalActTime = Duration.ZERO;
        this.tasks = new HashMap<String, Task>();
    }

    public Duration getTotalEstTime() {
        return totalEstTime;
    }

    public Duration getTotalActTime() {
        return totalActTime;
    }

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    /*
     * REQUIRES: task not already in tasks
     * MODIFIES: this
     * EFFECTS: add task into tasks
     */
    public void addTask(Task task) {
        // stub
    }

    /*
     * REQUIRES: task exists in tasks
     * MODIFIES: this
     * EFFECTS: remove task from tasks
     */
    public void removeTask(Task task) {
        // stub
    }

    /*
     * EFFECTS: return task with path from tasks
     *          throw if path not exist in task's keys
     */
    public Task findTask(String taskPath) {
        return null; // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: totalEstTime = sum of all tasks' estTime
     */
    public void updateTotalEstTime() {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: totalActTime = sum of all tasks' actTime
     */
    public void updateTotalActTime() {
        // stub
    }
}
