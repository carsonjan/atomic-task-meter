package ui.gui;

import javax.swing.*;
import model.*;

public class ProjectPanel extends MyPanel {
    
    public ProjectPanel(ATM data) {
        super(data);
    }

    @Override
    protected JLabel initTitleLabel() {
        JLabel label = new JLabel("Projects", SwingConstants.CENTER);
        return label;
    }
}
