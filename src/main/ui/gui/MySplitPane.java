package ui.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MySplitPane extends JPanel {
    private JSplitPane splitPane;
    private JPanel projectPanel;
    private JPanel taskPanel;

    public MySplitPane() {
        projectPanel = new MyPanel("Projects");
        taskPanel = new MyTaskPanel("Tasks for <Project Name>");
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   projectPanel, taskPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        splitPane.setPreferredSize(new Dimension(700, 400));
        add(splitPane);
    }
}
