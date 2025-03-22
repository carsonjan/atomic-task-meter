package ui.gui;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.ATM;

public class MenuBar extends JMenuBar {
    
    private ATM data;

    // make menu bar
    public MenuBar(ATM data) {
        super();
        this.data = data;

        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        file.add(save);
        file.add(load);
        add(file);

        JMenu about = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About");
        about.add(aboutItem);
        add(about);
        
        setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    }

    // public void actionPerformed(ActionEvent e) {
    //     JMenuItem source = (JMenuItem)(e.getSource());
    //     switch (source.getText()) {
    //         case "save":
    //             // stub
    //         case "load":
    //             //stub
    //         default:
    //             // pass (do nothing)
    //     }
    // }
}
