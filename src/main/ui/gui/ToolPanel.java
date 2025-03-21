package ui.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class ToolPanel extends JPanel {
    public ToolPanel() {

        // stub
        JLabel testLabel = new JLabel("This is a tool panel");
        testLabel.setBackground(Color.RED);
        testLabel.setOpaque(true);
        add(testLabel);
    }
}
