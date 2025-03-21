package ui.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MyPanel extends JPanel {
    public MyPanel(String title) {
        super(new BorderLayout());
        
        JLabel titleLabel = new JLabel(title);
        add(titleLabel, BorderLayout.NORTH);
        
        JScrollPane itemPane = new MyScrollPane();
        add(itemPane, BorderLayout.CENTER);

        JPanel toolPanel = new ToolPanel();
        add(toolPanel, BorderLayout.SOUTH);
    }

}
