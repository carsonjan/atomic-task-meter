package ui;

import javax.swing.JComponent;
import javax.swing.JFrame;

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

        frame.pack();
        frame.setVisible(true);
    }
}
