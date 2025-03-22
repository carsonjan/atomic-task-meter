package ui.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import model.ATM;

public class MyPanel extends JPanel {

    private ATM data;
    private MySplitPane parent;

    public MyPanel(MySplitPane parent, String title, ATM data) {
        super(new BorderLayout());
        this.parent = parent;
        this.data = data;
        
        JLabel titleLabel = new JLabel(title);
        add(titleLabel, BorderLayout.NORTH);
        
        JScrollPane itemPane = new MyScrollPane(this, data);
        add(itemPane, BorderLayout.CENTER);

        JPanel toolPanel = new ToolPanel(this, data);
        add(toolPanel, BorderLayout.SOUTH);
    }

}
