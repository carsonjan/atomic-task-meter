package model;

import java.time.Duration;
import java.util.HashMap;
import exceptions.*;

// Abstract class that Represents a Node-Like objects (Subject, Task, Agenda)
public abstract class NodeLike {
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

    /*
     * MODIFIES: this
     * EFFECTS: if tasks is empty, return self.estTime. 
     *          if tasks not empty, make self.estTime the sum of tasks' estTime and return the sum
     */
    public Duration updateEstTime() {
        return null;// stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: if tasks is empty, return self.actTime. 
     *          if tasks not empty, make self.actTime the sum of tasks' actTime and return the sum
     */
    public Duration updateActTime() {
        return null; // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: add task into tasks, throw if path already exist in tasks' keys
     */
    public void addTask(Task task) throws DuplicatePathException {
        String taskPath = task.getPath();
        if (tasks.containsKey(taskPath)) {
            throw new DuplicatePathException();
        }
        tasks.put(taskPath, task);
    }

    /*
     * MODIFIES: this
     * EFFECTS: remove task with key=path from tasks, return the task removed,
     *          throw if path not exist in task's keys
     */
    public Task removeTask(String path) throws PathNotExistException {
        Task task = tasks.remove(path);
        if (task == null) {
            throw new PathNotExistException();
        }
        return task;
    }

    /*
     * MODIFIES: this
     * EFFECTS: return task with key=path from tasks
     *          throw if path not exist in task's keys
     */
    public Task findTask(String path) throws PathNotExistException {
        Task task = task.get(path);
        if (task == null) {
            throw new PathNotExistException();
        }
        return task;
    }
// TODO: debug + need recursive find?
}
