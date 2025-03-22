package ui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.ATM;
import ui.gui.MySplitPane;
import ui.gui.MainFrame;
import ui.gui.MyMenuBar;

public class MainGui {

    public static void main(String[] args) {
        init();
    }

    // init the frame
    private static void init() {
        new MainFrame();
    }

}
