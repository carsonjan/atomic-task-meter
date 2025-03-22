package ui.gui;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;

import model.ATM;

public class MenuBar extends JMenuBar implements ActionListener {
    
    private ATM data;

    // make menu bar
    public MenuBar(ATM data) {
        super();
        this.data = data;

        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(this);
        file.add(save);
        file.add(load);
        add(file);

        JMenu about = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        about.add(aboutItem);
        add(about);
        
        setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        switch (source.getText()) {
            case "Save":
                System.out.println("save!"); // stub
                break;
            case "Load":
                System.out.println("load!"); //stub
                break;
            case "About":
                System.out.println("about!"); //stub
                break;
            default:
                // pass (should not reach, do nothing)
        }
    }
}
