package ui.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.ATM;

public class MySplitPane extends JPanel {
    private ATM data;
    private JSplitPane splitPane;
    private JPanel projectPanel;
    private JPanel taskPanel;

    public MySplitPane(ATM data) {
        this.data = data;
        projectPanel = new MyPanel(this, "Projects", data);
        taskPanel = new MyTaskPanel(this, "Tasks for <Project Name>", data);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   projectPanel, taskPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        splitPane.setPreferredSize(new Dimension(700, 400));
        add(splitPane);
    }
}
