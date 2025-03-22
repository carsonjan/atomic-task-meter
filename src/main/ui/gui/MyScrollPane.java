package ui.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import model.ATM;

public class MyScrollPane extends JScrollPane implements ListSelectionListener {

    private ATM data;
    private JList list;
    private DefaultListModel listModel;

    public MyScrollPane(ATM data) {

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

    public MyPanel getMyParent() {
        return (MyPanel) getParent();
    }

    public JList getList() {
        return list;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            JButton rmButton = getMyParent().getToolPanel().getRmButton();

            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                rmButton.setEnabled(false);

            } else {
            //Selection, enable the fire button.
                rmButton.setEnabled(true);
            }
        }
    }
}
