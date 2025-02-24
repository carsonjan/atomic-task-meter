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

    public void setTotalEstTime(Duration duration) {
        this.totalEstTime = duration;
    }

    public void setTotalActTime(Duration duration) {
        this.totalActTime = duration;
    }

    /*
     * REQUIRES: task not already in tasks
     * MODIFIES: this
     * EFFECTS: add task into tasks
     */
    public void addTask(Task task) {
        tasks.put(task.getPath(), task);
    }

    /*
     * REQUIRES: task exists in tasks
     * MODIFIES: this
     * EFFECTS: remove task with path from tasks
     */
    public void removeTask(String taskPath) {
        tasks.remove(taskPath);
    }

    /*
     * EFFECTS: return task with path from tasks
     *          return null if path not exist in task's keys
     */
    public Task findTask(String taskPath) {
        return tasks.get(taskPath);
    }

    /*
     * MODIFIES: this
     * EFFECTS: totalEstTime = sum of all tasks' estTime
     */
    public void updateTotalEstTime() {
        totalEstTime = Duration.ZERO;
        for (Task task : tasks.values()) {
            totalEstTime = totalEstTime.plus(task.getEstTime());
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: totalActTime = sum of all tasks' actTime
     */
    public void updateTotalActTime() {
        totalActTime = Duration.ZERO;
        for (Task task : tasks.values()) {
            totalActTime = totalActTime.plus(task.getActTime());
        }
    }
}
