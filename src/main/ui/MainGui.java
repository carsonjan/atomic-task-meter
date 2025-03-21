package ui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ui.gui.MySplitPane;

public class MainGui {
    public static void main(String[] args) {
        init();
    }

    // init the frame
    private static void init() {
        JFrame frame = new JFrame("Atomic Task Meter (GUI)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new MySplitPane();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setJMenuBar(makeMenuBar());

        frame.pack();
        frame.setVisible(true);
    }

    // make menu bar
    private static JMenuBar makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        file.add(save);
        file.add(load);
        menuBar.add(file);

        JMenu about = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About");
        about.add(aboutItem);
        menuBar.add(about);
        
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        return menuBar;
    }
}
