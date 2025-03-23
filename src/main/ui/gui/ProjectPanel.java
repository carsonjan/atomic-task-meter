package ui.gui;

import java.util.Collection;
import java.util.Set;

import javax.swing.*;
import model.*;

public class ProjectPanel extends MyPanel {

    private TaskPanel taskPanel;
    
    public ProjectPanel(ATM data, TaskPanel taskPanel) {
        super(data);
        this.taskPanel = taskPanel;
    }

    @Override
    protected JLabel initTitleLabel() {
        titleLabel = new JLabel("Projects", SwingConstants.CENTER);
        return titleLabel;
    }

    @Override
    protected MyScrollPane initItemPane() {
        return new ProjectPane();
    }

    @Override
    protected ToolPanel initToolPanel() {
        return new ProjectToolPanel();
    }

    class ProjectPane extends MyScrollPane {

        protected ProjectPane() {
            super();
        }

        @Override
        protected void selectionChangedAction() {
            String currentProjectName = (String) list.getSelectedValue();
            taskPanel.changeTitleLabelProject(currentProjectName);
            taskPanel.setCurrentProjectName(currentProjectName);
            try {
               Collection<Task> tasks = data.findProject(currentProjectName).getTasks().values(); 
               taskPanel.drawTaskList(tasks);
            } catch (NullPointerException e) {
                // project not found
                selectionNoneAction();
                System.out.println("something get wrong: ProjectPanel selectionChangedAction: cannot find project");
            }
            
        }

        @Override
        protected void selectionNoneAction() {
            taskPanel.changeTitleLabelNull();
            taskPanel.clearTaskList();
        }

    }

    class ProjectToolPanel extends ToolPanel {

        protected ProjectToolPanel() {
            super();
        }

        @Override
        protected AddListener initAddListener(JButton addButton) {
            return new ProjectAddListener();
        }

        @Override
        protected RmListener initRmListener() {
            return new ProjectRmListener();
        }
    }

    class ProjectRmListener extends RmListener {

        protected ProjectRmListener() {
            super();
        }

        @Override
        protected void rmDataAction() {
            data.removeProject((String) list.getSelectedValue());
        }
    }

    class ProjectAddListener extends AddListener {

        protected ProjectAddListener() {
            super(addButton);
        }

        @Override
        protected void addItemDataAction() {
            data.makeProject((String) itemName.getText());
        }
    }
}
