package ui.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import model.ATM;

public class MyPanel extends JPanel {

    private ATM data;

    public MyPanel(String title, ATM data) {
        super(new BorderLayout());
        this.data = data;
        
        JLabel titleLabel = new JLabel(title);
        add(titleLabel, BorderLayout.NORTH);
        
        JScrollPane itemPane = new MyScrollPane(data);
        add(itemPane, BorderLayout.CENTER);

        JPanel toolPanel = new ToolPanel(data);
        add(toolPanel, BorderLayout.SOUTH);
    }

}
