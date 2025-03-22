package ui.gui;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.ATM;

public class MySplitPane extends JPanel {
    private ATM data;
    private JSplitPane splitPane;
    private MyPanel projectPanel;
    private MyTaskPanel taskPanel;

    public MySplitPane(ATM data) {
        this.data = data;
        projectPanel = new MyPanel("Projects", data);
        taskPanel = new MyTaskPanel("Tasks for <Project Name>", data);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   projectPanel, taskPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        splitPane.setPreferredSize(new Dimension(700, 400));
        add(splitPane);
    }

    public MyPanel getProjectPanel() {
        return projectPanel;
    }

    public MyTaskPanel getTaskPanel() {
        return taskPanel;
    }

    // paint all projects from data to list in project panel
    public void paintProjects(ATM data) {
        this.data = data;
        Collection elements = data.getProjects().keySet();
        projectPanel.updateElements(elements);
    }
}
