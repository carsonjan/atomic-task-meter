package ui.gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.event.*;
import javax.swing.*;

import model.*;
import persistence.*;

// the menu bar
public class MyMenuBar extends JMenuBar implements ActionListener {
    
    private ATM data;
    private MainFrame parent;
    private static final String JSON_STORE = "./data/atm.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs menu bar
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

    // EFFECTS: handle click actions
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        switch (source.getText()) {
            case "Save":
                saveData();
                break;
            case "Load":
                loadData();
                break;
            case "About":
                break;
            default:
                // pass (should not reach, do nothing)
        }
    }

    // EFFECTS: saves the ATM object to file
    private void saveData() {
        Task currentTask = data.getCurrentTask();
        if (currentTask != null) {
            data.stopTask(currentTask);
        }
        try {
            jsonWriter.open();
            jsonWriter.write(data);
            jsonWriter.close();
            data.logSave();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: data
    // EFFECTS: loads atm from file
    private void loadData() {
        try {
            data = jsonReader.read();
            parent.updateData(data);
            data.logLoad();
            parent.getSplitPane().paintProjects(); // paint projects
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: update this and its subs data to input
    public void updateData(ATM data) {
        this.data = data;
    } 
}
