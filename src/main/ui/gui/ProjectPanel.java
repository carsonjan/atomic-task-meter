package ui.gui;

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
        // TODO Auto-generated method stub
        return new ProjectPane();
    }

    @Override
    protected ToolPanel initToolPanel() {
        // TODO Auto-generated method stub
        return new ProjectToolPanel();
    }

    class ProjectPane extends MyScrollPane {

        protected ProjectPane() {
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

    class ProjectToolPanel extends ToolPanel {

        protected ProjectToolPanel() {
            super();
        }

        @Override
        protected AddListener initAddListener(JButton addButton) {
            // TODO Auto-generated method stub
            return new ProjectAddListener();
        }

        @Override
        protected RmListener initRmListener() {
            // TODO Auto-generated method stub
            return new ProjectRmListener();
        }
    }

    class ProjectRmListener extends RmListener {

        protected ProjectRmListener() {
            super();
        }

        @Override
        protected void rmDataAction() {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'rmDataAction'");
        }
    }

    class ProjectAddListener extends AddListener {

        protected ProjectAddListener() {
            super(addButton);
        }

        @Override
        protected void addItemDataAction() {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'addItemDataAction'");
        }
    }
}
