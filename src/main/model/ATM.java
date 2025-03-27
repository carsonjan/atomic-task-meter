package model;

import java.time.*;
import java.util.HashMap;

import org.json.JSONObject;

import persistence.Writable;


// an Atomic Task Meter object
public class ATM implements Writable {
    private HashMap<String, Project> projects; // project with its name as key
    // private HashMap<LocalDate, Agenda> agendas; // agenda with its date as key TODO
    private Task currentTask; // the task currently timing
    private LocalDateTime lastStartTime; // the time when the task started

    // EFFECTS: create an ATM object all empty fields
    public ATM() {
        projects = new HashMap<String, Project>();
        // agendas = new HashMap<LocalDate, Agenda>(); TODO
        currentTask = null;
        lastStartTime = null;
    }

    public HashMap<String, Project> getProjects() {
        return projects;
    }

    // public HashMap<LocalDate, Agenda> getAgendas() {
    //     return agendas;
    // }

    public Task getCurrentTask() {
        return currentTask;
    }

    public LocalDateTime getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(LocalDateTime lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    /*
     * REQUIRES: name does not consist forward slashes
     *           name not already exist in projects
     * MODIFIES: this
     * EFFECTS: make a empty project with name, and add into projects
     */
    public void makeProject(String name) {
        Project project = new Project(name);
        addProject(project);
    }

    // MODIFIES: this
    // EFFECTS: add a project into projects
    public void addProject(Project project) {
        String name = project.getName();
        projects.put(name, project);
        logEvent("Project " + name + " added");
    }

    // /*
    //  * REQUIRES: date not already exist in agendas
    //  * MODIFIES: this
    //  * EFFECTS: make a empty agenda with Date= date from dateString, and add into agendas
    //  */
    // public void makeAgenda(String dateString) {
    //     // stub
    // }

    /*
     * EFFECTS:return project with name from projects,
     *         return null if not founded
     */
    public Project findProject(String name) {
        return projects.get(name);
    }

    // /*
    //  * EFFECTS:return agenda with date= date from dateString from agenda,
    //  *         return null if not founded
    //  */
    // public Agenda findAgenda(String dateString) {
    //     return null; // stub
    // }

    /*
     * MODIFIES: this, project
     * EFFECTS:remove and return project with name from projects,
     *         !! also remove all it's tasks from agendas (TODO)
     *         return null if not founded
     */
    public Project removeProject(String name) {
        logEvent("Project " + name + " removed");
        return projects.remove(name);
    }

    // /*
    //  * MODIFIES: this
    //  * EFFECTS:remove and return agenda with date= date from dateString from agenda,
    //  *         return null if not founded
    //  */
    // public Agenda removeAgenda(String dateString) {
    //     return null;
    // }

    /*
     * REQUIRES: projectName exists in projects,
     *           taskName not exist in project
     * MODIFIES: this, project
     * EFFECTS: make a task and add it into project
     */
    public void makeTask(String projectName, String taskName, double estTime) {
        Task task = new Task(projectName + "//" + taskName, estTime);
        findProject(projectName).addTask(task);
        logEvent("Task " + taskName + " added in project " + projectName);
    }

    /*
     * REQUIRES: projectName exists in projects
     * MODIFIES: this, project, agenda
     * EFFECTS: return a task with taskName from project with projectName
     *          returns null if task not found
     */
    public Task findTask(String projectName, String taskName) {
        return findProject(projectName).findTask(projectName + "//" + taskName);
    }

    /*
     * REQUIRES: projectName exists in projects
     * MODIFIES: this, project, agenda
     * EFFECTS: remove and return a task with taskName from project with projectName
     *          !! Also remove task from all agendas (TODO)
     *          returns null if task not found
     */
    public Task removeTask(String projectName, String taskName) {
        Project project = findProject(projectName);
        Task task = project.findTask(projectName + "//" + taskName);
        project.removeTask(projectName + "//" + taskName);
        logEvent("Task " + taskName + " removed from project " + projectName);
        return task;
    }

    /*
     * MODIFIES: this, task
     * EFFECTS: start timing a task
     *          make currentTask to task, and last task time to now
     *          !! returns subject//taskName if a task is already timing, else null
     */
    public String startTask(Task task) {
        if (currentTask == null) {
            currentTask = task;
            lastStartTime = LocalDateTime.now();
            return null;
        } else {
            return currentTask.getPath();
        }
    }

    /*
     * MODIFIES: this, task
     * EFFECTS: stop timing a task, add delta time into task actTime
     *          make currentTask and last task time to null
     *          !! returns null if no task is timing, else the added time
     */
    public Double stopTask(Task task) {
        if (currentTask == null) {
            return null;
        }
        Duration delta = Duration.between(lastStartTime, LocalDateTime.now());
        task.addActTime(delta);
        double deltaH = delta.toMinutes() / 60.0;
        currentTask = null;
        lastStartTime = null;
        return Math.round(deltaH * 100) / 100.0;
    }

    // /*
    //  * REQUIRES: all arguments exists
    //  * MODIFIES: agenda
    //  * EFFECTS: add a task to an agenda
    //  */
    // public void addTask(String projectName, String taskName, String dateString) {
    //     // stub
    // }

    // /*
    //  * REQUIRES: all arguments exists
    //  * MODIFIES: agenda
    //  * EFFECTS: remove a task to an agenda
    //  */
    // public void dropTask(String projectName, String taskName, String dateString) {
    //     // stub
    // }

    /*
     * REQUIRES: projectName exists
     * MODIFIES: project
     * EFFECTS: update estTime and actTime for the project with projectName
     */
    public void updateProjectTime(String projectName) {
        findProject(projectName).updateTotalEstTime();
        findProject(projectName).updateTotalActTime();
    }

    // /*
    //  * REQUIRES: agendaDate exists
    //  * MODIFIES: agenda
    //  * EFFECTS: update estTime and actTime for the agenda with AgendaDate
    //  */
    // public void updateAgendaTime(LocalDate agendaDate) {
    //     // stub
    // }

    /*
     * EFFECTS: format duration into hours, with max 2 decimals
     */
    public double formatTime(Duration duration) {
        double hours = duration.toMinutes() / 60.0;
        return Math.floor(hours * 100) / 100;
    }

    // EFFECTS: save projects field to the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("projects", projects);
        return json;
    }

    private void logEvent(String msg) {
        EventLog.getInstance().logEvent(new Event(msg));
    }
    

}
