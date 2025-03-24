package ui.gui;

import javax.swing.*;
import model.*;

// The main frame
public class MainFrame extends JFrame {
    private ATM data;
    private MySplitPane splitPane;
    private MyMenuBar menuBar;

    // EFFECTS: constructs the frame
    public MainFrame() {
        super("Atomic Task Meter (GUI)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        data = new ATM();
        splitPane = new MySplitPane(data);
        splitPane.setOpaque(true);
        setContentPane(splitPane);
        menuBar = new MyMenuBar(data, this);
        setJMenuBar(menuBar);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: update this and its subs data to input
    public void updateData(ATM data) {
        this.data = data;
        splitPane.updateData(data);
        menuBar.updateData(data);
    }

    public MySplitPane getSplitPane() {
        return splitPane;
    }
}
