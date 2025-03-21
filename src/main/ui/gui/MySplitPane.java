package ui.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ui.guidraft.OldMyPanel;

public class MySplitPane extends JPanel {
    private JSplitPane splitPane;
    private JPanel projectPanel;
    private JPanel taskPanel;

    public MySplitPane() {
        projectPanel = new MyPanel();
        taskPanel = new MyTaskPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   projectPanel, taskPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        splitPane.setPreferredSize(new Dimension(500, 300));
    }
}
