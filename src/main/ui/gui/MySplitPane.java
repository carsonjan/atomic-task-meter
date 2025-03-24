package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

import model.*;

// The split pane for projects and tasks panel
public class MySplitPane extends JSplitPane {

    private ATM data;
    private ProjectPanel projectPanel;
    private TaskPanel taskPanel;

    // EFFECTS: constructs the split pane
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

    // MODIFIES: this
    // EFFECTS: update this and its subs data to input
    public void updateData(ATM data) {
        this.data = data;
        projectPanel.updateData(data);
        taskPanel.updateData(data);
    }

    // MODIFIES: this
    // EFFECTS: paint all projects from data to list in project panel
    public void paintProjects() {
        Collection elements = data.getProjects().keySet();
        projectPanel.updateElements(elements);
    }

}
