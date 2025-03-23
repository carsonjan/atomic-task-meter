package ui.gui;

import javax.swing.*;
import model.*;

public class TaskPanel extends MyPanel {
    
    private final String titleStringNoProject = "Tasks in: <please first select a project...>";

    public TaskPanel(ATM data) {
        super(data);
    }

    @Override
    protected JLabel initTitleLabel() {
        titleLabel = new JLabel(titleStringNoProject, SwingConstants.CENTER);
        return titleLabel;
    }

    // paint all tasks given the currently selected project name
    public void paintTasks(String currentProjectName) {
        // TODO
    }

    // change title label when a project is selected
    public void changedTitleLabelProject(String projectName) {
        // TODO
        titleLabel.setText("Tasks in: " + projectName);
    }

    // change title label when a project is selected
    public void changedTitleLabelNull() {
        // TODO
        titleLabel.setText(titleStringNoProject);
    }

    @Override
    protected MyScrollPane initItemPane() {
        // TODO Auto-generated method stub
        return new TaskPane();
    }

    @Override
    protected ToolPanel initToolPanel() {
        // TODO Auto-generated method stub
        return new TaskToolPanel();
    }

    class TaskPane extends MyScrollPane {

        protected TaskPane() {
            super();
        }

        @Override
        protected void selectionChangedAction() {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'selectionChangedAction'");
        }

        @Override
        protected void selectionNoneAction() {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'selectionNoneAction'");
        }

    }

    class TaskToolPanel extends ToolPanel {

        protected TaskToolPanel() {
            super();
        }

        @Override
        protected AddListener initAddListener(JButton addButton) {
            // TODO Auto-generated method stub
            return new TaskAddListener();
        }

        @Override
        protected RmListener initRmListener() {
            // TODO Auto-generated method stub
            return new TaskRmListener();
        }
    }

    class TaskRmListener extends RmListener {

        protected TaskRmListener() {
            super();
        }

        @Override
        protected void rmDataAction() {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'rmDataAction'");
        }
    }

    class TaskAddListener extends AddListener {

        protected TaskAddListener() {
            super(addButton);
        }

        @Override
        protected void addItemDataAction() {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'addItemDataAction'");
        }
    }
}
