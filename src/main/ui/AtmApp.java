package ui;

import model.ATM;
import model.Project;
import model.Task;

import java.util.Scanner;

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
        System.out.println("\n\tp: show all projects");
        System.out.println("\ta: show all agendas");
        System.out.println("\tquit: quit application");
        System.out.println("\nchoose function:");
    }

    // EFFECTS: process user input in menu stage
    private void processMenu() {
        input = scanner.nextLine().trim();
        switch (input) {
            case "quit": // quit
                quit = true;
            case "p": // all projects
                showProjects();
                processProjects();
            case "a": // all agendas
                System.out.println("- function under construction, stayed tuned! -"); // stub
                // showAgendas();
                // processAgendas();
            default:
                System.out.println("- invalid command -\n- type p, a, or q -");
        }
    }

    // EFFECTS: show all the projects
    private void showProjects() {
        System.out.println("\nProject Name\tEstimated Time/Actual Time (h)\n");
        for (Project p : atm.getProjects().values()) {
            String line = p.getName() + "\t(" + p.getTotalEstTime() + "/" + p.getTotalActTime() + ")";
            System.out.println(line);
        }
    }

    // EFFECTS: process user input in the projects stage
    private void processProjects() {
        System.out.println("\nType project name to select\nor //new to make new project");
        String projectName = scanner.nextLine().trim();
        if (projectName.equals("//new")) {
            makeProject();
            showProjects();
            processProjects();
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
        System.out.println("\nProject name:");
        String projectName = scanner.nextLine().trim();
        if (projectName.contains("//")) {
            System.out.println("- Project name cannot contain // -");
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
        System.out.println("\nDone?\tTask Name\tActual Time/Estimated Time (h)\n");
        String done;
        for (Task t : project.getTasks().values()) {
            if (t.getIsDone()) {
                done = "[x] ";
            } else {
                done = "[ ] ";
            }
            String line = done + t.getName() + "\t(" + t.getActTime() + "/" + t.getEstTime() + ")";
            System.out.println(line);
        }
        System.out.println("\nType task name to select\nor //new to make new task");
        
    }

    // EFFECTS: process user input in a project stage
    private void processProject(Project project) {
        String taskName = scanner.nextLine().trim();
        boolean back = false;
        if (taskName.equals("//new")) {
            makeTask(project);
            back = true;
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
        String done;
        if (t.getIsDone()) {
            done = "[x] ";
        } else {
            done = "[ ] ";
        }
        String line = done + t.getName() + "\t(" + t.getActTime() + "/" + t.getEstTime() + ")";
        System.out.println("\n" + line);
        System.out.println("\t1: mark task done");
        System.out.println("\t2: mark task undone");
        System.out.println("\t3: start timing task");
        System.out.println("\t4: stop timing task");
        System.out.println("\tdelete: delete task");
        System.out.println("\tb: back to project");
        // stub
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
            case "3": //TODO atm start task 142 null pointer
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
                atm.removeTask(task.getProjectName(), task.getName()); //TODO not removing
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
