package ui.gui;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.ATM;
import model.Task;
import persistence.JsonReader;
import persistence.JsonWriter;

public class MyMenuBar extends JMenuBar implements ActionListener {
    
    private ATM data;
    private MainFrame parent;
    private static final String JSON_STORE = "./data/atm.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // make menu bar
    public MyMenuBar(ATM data, MainFrame parent) {
        super();
        this.data = data;
        this.parent = parent;
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);

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
                saveData();
                break;
            case "Load":
                System.out.println("load!"); //stub
                loadData();
                break;
            case "About":
                System.out.println("about!"); //stub
                break;
            default:
                // pass (should not reach, do nothing)
        }
    }

    // EFFECTS: saves the ATM object to file
    private void saveData() {
        Task currentTask = data.getCurrentTask();
        if (currentTask != null) {
            Double addTime = data.stopTask(currentTask);
            System.out.println("> stopped timing: " + currentTask.getName()); // stub
            System.out.println("> Accumulated an extra " + addTime + "hours"); // stub
        }
        try {
            jsonWriter.open();
            jsonWriter.write(data);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE); // stub
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE); // stub
        }
    }

    // MODIFIES: this
    // EFFECTS: loads atm from file
    private void loadData() {
        try {
            data = jsonReader.read();
            parent.getMyContentPane().paintProjects(data); // paint projects
            System.out.println("Loaded " + " from " + JSON_STORE); // stub
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE); // stub
        }
    }
}
