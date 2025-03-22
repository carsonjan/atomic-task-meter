package ui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.ATM;
import ui.gui.MySplitPane;
import ui.gui.MenuBar;

public class MainGui {

    public static void main(String[] args) {
        init();
    }

    // init the frame
    private static void init() {
        JFrame frame = new JFrame("Atomic Task Meter (GUI)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ATM data = new ATM();
        JComponent newContentPane = new MySplitPane(data);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        JMenuBar menuBar = new MenuBar(data);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

}
