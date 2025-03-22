package ui.gui;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.ATM;

public class MyScrollPane extends JScrollPane {

    private MyPanel parent;
    private ATM data;
    private JList list;
    private DefaultListModel listModel;

    public MyScrollPane(MyPanel parent, ATM data) {

        this.parent = parent;
        this.data = data;
        
        // stub
        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        // list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        setViewportView(list);
    }
}
