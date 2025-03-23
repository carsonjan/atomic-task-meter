package ui.gui;

import javax.swing.JFrame;

import model.ATM;

public class MainFrame extends JFrame {

    private JFrame frame;
    private ATM data;
    private MySplitPane myContentPane;
    private MyMenuBar myMenuBar;
    
    public MainFrame() {
        frame = new JFrame("Atomic Task Meter (GUI)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        data = new ATM();
        myContentPane = new MySplitPane(data);
        myContentPane.setOpaque(true);
        frame.setContentPane(myContentPane);
        myMenuBar = new MyMenuBar(data, this);
        frame.setJMenuBar(myMenuBar);

        frame.pack();
        frame.setVisible(true);
    }

    public ATM getData() {
        return data;
    }

    public MySplitPane getMyContentPane() {
        return myContentPane;
    }

    public MyMenuBar getMyMenuBar() {
        return myMenuBar;
    }

    public void updateData(ATM data) {
        this.data = data;
        myContentPane.updateData(data);
        myMenuBar.updateData(data);
    }
}
