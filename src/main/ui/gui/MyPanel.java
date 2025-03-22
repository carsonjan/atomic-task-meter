package ui.gui;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import model.ATM;

public class MyPanel extends JPanel {

    private ATM data;
    private MyScrollPane itemPane;
    private ToolPanel toolPanel;

    
    public MyPanel(String title, ATM data) {
        super(new BorderLayout());
        this.data = data;
        
        JLabel titleLabel = new JLabel(title);
        add(titleLabel, BorderLayout.NORTH);
        
        itemPane = new MyScrollPane(data);
        add(itemPane, BorderLayout.CENTER);

        toolPanel = new ToolPanel(data);
        add(toolPanel, BorderLayout.SOUTH);
    }

    public MySplitPane getMyParent() {
        return (MySplitPane) getParent();
    }

    public MyScrollPane getItemPane() {
        return itemPane;
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public void updateElements(Collection oList) {
        itemPane.updateElements(oList);
    }

}
