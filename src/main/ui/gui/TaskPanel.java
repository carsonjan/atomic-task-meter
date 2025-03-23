package ui.gui;

import javax.swing.*;
import model.*;

public class TaskPanel extends MyPanel {

    public TaskPanel(ATM data) {
        super(data);
    }

    @Override
    protected JLabel initTitleLabel() {
        JLabel label = new JLabel("Projects", SwingConstants.CENTER);
        return label;
    }

    public void updateTaskPanel(String currentProjectName) {
        // TODO
    }
}
