package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

import model.*;

public class MySplitPane extends JSplitPane {

    private ATM data;
    private ProjectPanel projectPanel;
    private TaskPanel taskPanel;

    public MySplitPane(ATM data) {
        super(JSplitPane.HORIZONTAL_SPLIT);
        this.data = data;

        taskPanel = new TaskPanel(data);
        projectPanel = new ProjectPanel(data, taskPanel);

        setLeftComponent(projectPanel);
        setRightComponent(taskPanel);
        setOneTouchExpandable(true);
        setDividerLocation(250);
        setPreferredSize(new Dimension(700, 400));
    }

    public void updateData(ATM data) {
        this.data = data;
        projectPanel.updateData(data);
        taskPanel.updateData(data);
    }

    // paint all projects from data to list in project panel
    public void paintProjects() {
        Collection elements = data.getProjects().keySet();
        projectPanel.updateElements(elements);
    }

}
