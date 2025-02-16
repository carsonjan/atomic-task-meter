package ui;

import model.ATM;
import model.Project;
import model.Task;

import java.util.Scanner;

// The running application ui
public class AtmApp {
    private ATM atm; // the atm model
    private Scanner scanner;
    private String input;
    private boolean quit;

    // EFFECTS: init and run the app
    public AtmApp() {
        atm = new ATM();
        quit = false;
        scanner = new Scanner(System.in);

        while (!quit) {
            showMenu();
            processMenu();
        }
    }

    // EFFECT: show the menu (quit, projects, agendas)
    private void showMenu() {
        System.out.println("\n------------------");
        System.out.println("\n\tMENU");
        System.out.println("\tp: show all projects");
        System.out.println("\ta: show all agendas");
        System.out.println("\tquit: quit application");
        System.out.println("\nchoose function:");
    }

    // EFFECTS: process user input in menu stage
    private void processMenu() {
        input = scanner.nextLine().trim();
        switch (input) {
            case "quit":
                System.out.println("== Thank you for using! ==");
                quit = true;
                break;
            case "p": // all projects
                showProjects();
                processProjects();
                break;
            case "a": // all agendas
                System.out.println("- function under construction, stayed tuned! -");
                break;
            default:
                System.out.println("- invalid function -\n- type p, a, or q -");
        }
    }

    // EFFECTS: show all the projects
    private void showProjects() {
        System.out.println("\n----------------------");
        System.out.println("\nProject Name\tActual Time/Estimated Time (h)\n");
        for (Project p : atm.getProjects().values()) {
            atm.updateProjectTime(p.getName());
            String line = p.getName() + "\t(" + atm.formatTime(p.getTotalActTime()) + "/" + atm.formatTime(p.getTotalEstTime()) + ")";
            System.out.println(line);
        }
    }

    // EFFECTS: process user input in the projects stage
    private void processProjects() {
        System.out.println("\n\tType project name to select\n\tType //new to make new project\n\tType //b to go back to menu");
        String projectName = scanner.nextLine().trim();
        if (projectName.equals("//new")) {
            makeProject(); 
            showProjects();
            processProjects();
        } else if (projectName.equals("//b")) {
            // pass
        } else {
            Project project = atm.findProject(projectName);
            if (project == null) {
                System.out.println("- project not found -");
                showProjects();
                processProjects();
            } else {
                showProject(project);
                processProject(project);
            }
        }
    }

    // EFFECTS: make a new project
    private void makeProject() {
        System.out.println("\nProject name:");
        String projectName = scanner.nextLine().trim();
        if (projectName.contains("//")) {
            System.out.println("- Project name cannot contain // -");
            makeProject();
        } else if (projectName.isEmpty()) {
            System.out.println("- Project name cannot be empty -");
            makeProject();  
        } else if (atm.getProjects().containsKey(projectName)) {
            System.out.println("- Project name must be unique -");
            makeProject();
        } else {
            atm.makeProject(projectName);
            System.out.println("> made project " + projectName);
        }
    }

    // // EFFECTS: show all agendas
    // private void showAgendas() {
    //     // stub
    // }

    // // EFFECTS: process user input in the agenda stage
    // private void processAgendas() {
    //     String dateString = scanner.nextLine().trim();
    //     showAgenda(dateString);
    //     processAgenda(dateString);
    //     // stub
    // }

    // EFFECTS: show all task in a project
    private void showProject(Project project) {
        System.out.println("\n----------------------");
        atm.updateProjectTime(project.getName());
        String pLine = project.getName() + "\t(" + atm.formatTime(project.getTotalActTime()) + "/" + atm.formatTime(project.getTotalEstTime()) + ")";
        System.out.println("Tasks in:\t" + pLine);
        System.out.println("\nDone?\tTask Name\tActual Time/Estimated Time (h)\n");
        String done;
        for (Task t : project.getTasks().values()) {
            if (t.getIsDone()) {
                done = "[x] ";
            } else {
                done = "[ ] ";
            }
            String line = done + t.getName() + "\t(" + atm.formatTime(t.getActTime()) + "/" + atm.formatTime(t.getEstTime()) + ")";
            System.out.println(line);
        }
        System.out.println("\n\tType task name to select\n\tType //new to make new task\n\tType //b to go back to projects");
        
    }

    // EFFECTS: process user input in a project stage
    private void processProject(Project project) {
        String taskName = "";
        while (taskName.equals("")) {
            taskName = scanner.nextLine().trim();
        }
        boolean back = false;
        if (taskName.equals("//new")) {
            makeTask(project);
            back = true;
        } else if (taskName.equals("//b")) {
            showProjects();
            processProjects();
        } else {
            Task task = atm.findTask(project.getName(), taskName);
            if (task == null) {
                System.out.println("- task not found -");
                showProject(project);
                processProject(project);
            } else {
                showTask(task);
                back = processTask(task);
            }
        }
        if (back) {
            showProject(project);
            processProject(project);
        }
    }

    // EFFECTS: make a new task
    private void makeTask(Project project) {
        String projectName = project.getName();
        System.out.println("\nTask name:");
        String taskName = scanner.nextLine().trim();
        if (taskName.contains("//")) {
            System.out.println("- Task name cannot contain // -");
            makeTask(project);
        } else if (taskName.isEmpty()) {
            System.out.println("- Task name cannot be empty -");
            makeTask(project);
        } else if (project.getTasks().containsKey(project.getName() + "//" + taskName)) {
            System.out.println("- Task name must be unique -");
            makeTask(project);
        } else {
            System.out.println("\nEstimated time (h):");
            double estTime = scanner.nextDouble();
            atm.makeTask(projectName, taskName, estTime);
            System.out.println("> made task " + taskName + " in " + projectName + "\t(" + estTime + " h)");
        }
    }

    // // EFFECTS: show all tasks in an agenda
    // private void showAgenda(String dateString) {
    //     // stub
    // }

    // // EFFECTS: process user input in an agenda stage
    // private void processAgenda(String dateString){
    //     // stub
    // }

    // EFFECTS: show actions for a task
    private void showTask(Task t) {
        System.out.println("\n----------------------");
        String done;
        if (t.getIsDone()) {
            done = "[x] ";
        } else {
            done = "[ ] ";
        }
        String line = done + t.getName() + "\t(" + atm.formatTime(t.getActTime()) + "/" + atm.formatTime(t.getEstTime()) + ")";
        System.out.println(line + "\n");
        System.out.println("\t1: mark task done");
        System.out.println("\t2: mark task undone");
        System.out.println("\t3: start timing task");
        System.out.println("\t4: stop timing task");
        System.out.println("\tdelete: delete task");
        System.out.println("\tb: back to project");
    }

    // EFFECTS: process user input in a task stage, return if to go back
    @SuppressWarnings("methodlength")
    private boolean processTask(Task task) {
        String action = scanner.nextLine().trim();
        switch (action) {
            case "1":
                boolean doneSuccess = task.markDone();
                if (doneSuccess) {
                    System.out.println("> task marked done");
                } else {
                    System.out.println("> task is already done before marking");
                }
                return true;
            case "2":
                boolean undoneSuccess = task.markUndone();
                if (undoneSuccess) {
                    System.out.println("> task marked undone");
                } else {
                    System.out.println("> task is already undone before marking");
                }
                return true;
            case "3":
                String current = atm.startTask(task);
                if (current == null) {
                    System.out.println("> start timing: " + task.getName());
                } else {
                    System.out.println("- currently timing " + current + " -");
                    System.out.println("- cannot time two tasks simultaneously -");
                }
                return true;
            case "4":
                Double addTime = atm.stopTask(task);
                if (addTime == null) {
                    System.out.println("- task not currently timing -");
                } else {
                    System.out.println("> stopped timing: " + task.getName());
                    System.out.println("> Accumulated an extra" + addTime + "hours");
                }
                return true;
            case "delete":
                Task delResult = atm.removeTask(task.getProjectName(), task.getName());
                System.out.println("> removed task: " + delResult.getName());
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
