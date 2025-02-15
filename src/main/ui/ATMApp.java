package ui;

import model.ATM;
import model.Project;
import model.Task;

import java.util.Scanner;

public class ATMApp {
    private ATM atm; // the atm model
    private Scanner scanner;
    private String input;
    private boolean quit;

    // EFFECTS: init and run the app
    public ATMApp() {
        quit = false;
        scanner = new Scanner(System.in);

        while (!quit) {
            showMenu();
            processMenu();
        }
    }

    // EFFECT: show the menu (quit, projects, agendas)
    private void showMenu() {
        System.out.println("\tp: show all projects");
        System.out.println("\ta: show all agendas");
    }

    // EFFECTS: process user input in menu stage
    private void processMenu() {
        input = scanner.nextLine();
        switch (input) {
            case "q": // quit
                quit = true;
            case "p": // all projects
                showProjects();
                processProjects();
            case "a": // all agendas
                showAgendas();
                processAgendas();
            default:
                System.out.println("- invalid command -\n- type p, a, or q -");
        }
    }

    // EFFECTS: show all the projects
    private void showProjects() {
        System.out.println("Project Name\tEstimated Time/Actual Time (h)\n");
        for (Project p : atm.getProjects().values()) {
            String line = p.getName() + "\t(" + p.getTotalEstTime() + "/" + p.getTotalActTime() + ")";
            System.out.println(line);
        }
        System.out.println("Type //new to make new project");
    }

    // EFFECTS: process user input in the projects stage
    private void processProjects(){
        System.out.println("\nType project name to select:");
        String projectName = scanner.nextLine();
        if (projectName == "//new") {
            makeProject();
        } else {
            Project project = atm.findProject(projectName);
            if (project == null) {
                System.out.println("- project not found -");
                processProjects();
            } else {
                showProject(project);
                processProject(project);
            }
        }
    }

    // EFFECTS: make a new project
    private void makeProject() {
        System.out.println("\nProject name: \n");
        String projectName = scanner.nextLine();
        if (projectName.contains("//")) {
            System.out.println("- Project name cannot contain // -");
            makeProject();
        } else {
            atm.makeProject(projectName);
            System.out.println("made project " + projectName);
        }
    }

    // EFFECTS: show all agendas
    private void showAgendas() {
        // stub
    }

    // EFFECTS: process user input in the agenda stage
    private void processAgendas() {
        String dateString = scanner.nextLine();
        showAgenda(dateString);
        processAgenda(dateString);
        // stub
    }

    // EFFECTS: show all task in a project
    private void showProject(Project project) {
        System.out.println("Done?\tTask Name\tEstimated Time/Actual Time (h)\n");
        String done = "";
        for (Task t : project.getTasks().values()) {
            if (t.isDone()) {
                done = "[x] ";
            } else {
                done = "[ ] ";
            }
            String line = done + t.getName() + "\t(" + t.getEstTime() + "/" + t.getActTime() + ")";
            System.out.println(line);
        }
        System.out.println("\nType //new to make new task");
        
    }

    // EFFECTS: process user input in a project stage
    private void processProject(Project project) {
        String taskName = scanner.nextLine();
        boolean back = false;
        if (taskName == "//new") {
            makeTask(project.getName());
            back = true;
        } else {
            Task task = atm.findTask(project.getName(), taskName);
            if (task == null) {
                System.out.println("- task not found -");
                processProject(project);
            } else {
                showTask();
                back = processTask(task);
            }
        }
        if (back) {
            processProject(project);
        }
    }

    // EFFECTS: make a new task
    private void makeTask(String projectName) {
        System.out.println("\nTask name: \n");
        String taskName = scanner.nextLine();
        if (taskName.contains("//")) {
            System.out.println("- Task name cannot contain // -");
            makeTask(projectName);
        } else {
            System.out.println("\nEstimated time (h): \n");
            double estTime = scanner.nextDouble();
            atm.makeTask(projectName, taskName, estTime);
            System.out.println("made task " + taskName + " in " + projectName + "\t" + estTime);
        }
    }

    // EFFECTS: show all tasks in an agenda
    private void showAgenda(String dateString) {
        // stub
    }

    // EFFECTS: process user input in an agenda stage
    private void processAgenda(String dateString){
        // stub
    }

    // EFFECTS: show actions for a task
    private void showTask() {
        System.out.println("\t1: mark task done");
        System.out.println("\t2: mark task undone");
        System.out.println("\t3: start timing task");
        System.out.println("\t4: stop timing task");
        System.out.println("\tdelete: delete task");
        System.out.println("\tb: back to project");
        // stub
    }

    // EFFECTS: process user input in a task stage, return if to go back
    private boolean processTask(Task task) {
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                task.markDone();
                return true;
            case "2":
                task.markUndone();
                return true;
            case "3":
                String current = atm.startTask(task);
                if (current == null) {
                    System.out.println("start timing: " + task.getName());
                } else {
                    System.out.println("- currently timing " + current + " -");
                    System.out.println("- cannot time two tasks simultaneously -");
                }
                return true;
            case "4":
                Double addTime = atm.stopTask(task);
                if (addTime == null) {
                    System.out.println("- no task currently timing -");
                } else {
                    System.out.println("stopped timing: " + task.getName());
                    System.out.println("Accumulated an extra" + addTime + "hours");
                }
                return true;
            case "delete":
                atm.removeTask(task.getProjectName(), task.getName());
                return true;
            case "b":
                return true;
            default:
                System.out.println("- action not found -");
                processTask(task);
                return false;
        }
    }
}
