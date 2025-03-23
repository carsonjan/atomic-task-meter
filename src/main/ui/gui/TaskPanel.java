package ui.gui;

import java.util.Collection;

import javax.swing.*;
import model.*;

public class TaskPanel extends MyPanel {

    private String currentProjectName;
    
    private final String titleStringNoProject = "Tasks in: <please first select a project...>";

    public TaskPanel(ATM data) {
        super(data);
    }

    @Override
    protected JLabel initTitleLabel() {
        titleLabel = new JLabel(titleStringNoProject, SwingConstants.CENTER);
        return titleLabel;
    }

    // // paint all tasks given the currently selected project name
    // public void paintTasks(String currentProjectName) {
    //     // TODO not used, remove
    // }

    public void setCurrentProjectName(String currentProjectName) {
        this.currentProjectName = currentProjectName;
    }

    // change title label when a project is selected
    public void changeTitleLabelProject(String projectName) {
        titleLabel.setText("Tasks in: " + projectName);
    }

    // change title label when a project is selected
    public void changeTitleLabelNull() {
        titleLabel.setText(titleStringNoProject);
    }

    public void drawTaskList(Collection<Task> tasks) {
        clearTaskList();
        for (Task task : tasks) {
            String taskName = task.getName();
            listModel.addElement(taskName);
        }
    }

    public void clearTaskList() {
        listModel.clear();
    }

    @Override
    protected MyScrollPane initItemPane() {
        return new TaskPane();
    }

    @Override
    protected ToolPanel initToolPanel() {
        return new TaskToolPanel();
    }

    class TaskPane extends MyScrollPane {

        protected TaskPane() {
            super();
        }

        @Override
        protected void selectionChangedAction() {
            // DO NOTHING
        }

        @Override
        protected void selectionNoneAction() {
            // DO NOTHING 
        }

    }

    class TaskToolPanel extends ToolPanel {

        protected TaskToolPanel() {
            super();
        }

        @Override
        protected AddListener initAddListener(JButton addButton) {
            return new TaskAddListener();
        }

        @Override
        protected RmListener initRmListener() {
            return new TaskRmListener();
        }
    }

    class TaskRmListener extends RmListener {

        protected TaskRmListener() {
            super();
        }

        @Override
        protected void rmDataAction() {
            try {
                String currentTaskName = (String) list.getSelectedValue();
                data.removeTask(currentProjectName, currentTaskName);
            } catch (NullPointerException e) {
                System.out.println("something get wrong: TaskPanel rmDataAction: cannot find task to remove");
            }
        }
    }

    class TaskAddListener extends AddListener {

        protected TaskAddListener() {
            super(addButton);
        }

        @Override
        protected void addItemDataAction() {
            try {
                data.makeTask(currentProjectName, itemName.getText(), 0); // TODO make time not 0
            } catch (NullPointerException e) {
                System.out.println("something get wrong: TaskPanel addItemDataAction: cannot find project to add");
            }
        }
    }
}
