package ui.gui;

import javax.swing.*;
import model.*;
import java.awt.event.*;
import java.util.Iterator;

// The main frame
public class MainFrame extends JFrame implements WindowListener {
    private ATM data;
    private MySplitPane splitPane;
    private MyMenuBar menuBar;

    // EFFECTS: constructs the frame
    public MainFrame() {
        super("Atomic Task Meter (GUI)");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);

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

    @Override
    public void windowActivated(WindowEvent arg0) {
        // pass
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        System.out.println("window closed");
    }

    // EFFECTS: print event log and close program
    @Override
    public void windowClosing(WindowEvent arg0) {
        printEventLog();
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // pass
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // pass
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // pass
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // pass
    }

    // EFFECTS: print all eventLog on terminal 
    private void printEventLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }
}
