package ui.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.Collection;
import java.awt.*;
import model.*;

// the abstract class for a itemPanel (project panel OR task panel)
public abstract class MyPanel extends JPanel {

    protected ATM data;
    protected JLabel titleLabel;
    protected MyScrollPane itemPane;
    protected ToolPanel toolPanel;

    protected JList list;
    protected DefaultListModel listModel;
    protected JButton rmButton;
    protected JButton addButton;
    protected JTextField itemName;

    // EFFECTS: constructs the panel
    public MyPanel(ATM data) {
        super(new BorderLayout());
        this.data = data;
        
        titleLabel = initTitleLabel();
        add(titleLabel, BorderLayout.NORTH);
        
        itemPane = initItemPane();
        add(itemPane, BorderLayout.CENTER);

        toolPanel = initToolPanel();
        add(toolPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: return a new title label
    protected abstract JLabel initTitleLabel();
    
    // EFFECTS: return a new item pane
    protected abstract MyScrollPane initItemPane();

    // EFFECTS: return a new tool panel
    protected abstract ToolPanel initToolPanel();

    // MODIFIES: this
    // EFFECTS: update this and its subs data to input
    public void updateData(ATM data) {
        this.data = data;
    }

    // MODIFIES: this
    // EFFECTS: clear current list, put all elements into list
    public void updateElements(Collection objList) {
        listModel.clear();
        for (Object o : objList) {
            listModel.addElement(o);
        }
    }


    // abstract class of a item pane
    abstract class MyScrollPane extends JScrollPane implements ListSelectionListener {

        // EFFECTS: constructs an item pane
        protected MyScrollPane() {
            super();
            listModel = new DefaultListModel();
            list = new JList(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.addListSelectionListener(this);
            list.setVisibleRowCount(5);
            setViewportView(list);
        }

        // MODIFIES: this
        // EFFECTS: update the remove button and perform panel specific action when selection is changed
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {

                if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                    rmButton.setEnabled(false);
                    selectionNoneAction();

                } else {
                //Selection, enable the fire button.
                    rmButton.setEnabled(true);
                    selectionChangedAction();
                }
            }
        }

        // EFFECTS: perform an action when list selection is changed
        protected abstract void selectionChangedAction();

        // EFFECTS: perform an action when there is no selection (init/ removed all items)
        protected abstract void selectionNoneAction();
    }

    // the abstract class for a tool panel
    abstract class ToolPanel extends JPanel {

        // EFFECTS: constructs a tool panel
        protected ToolPanel() {
            super();
            rmButton = new JButton("🗑️");
            RmListener rmListener = initRmListener();
            rmButton.addActionListener(rmListener);
            rmButton.setEnabled(false);

            ImageIcon imgIcon = new ImageIcon("data/plusImage.png");
            imgIcon = (ImageIcon) resizeIcon(imgIcon, 15, 15);
            addButton = new JButton(imgIcon);

            AddListener addListener = initAddListener(addButton);
            addButton.setActionCommand("add");
            addButton.addActionListener(addListener);
            addButton.setEnabled(false);

            itemName = new JTextField(10);
            itemName.addActionListener(addListener);
            itemName.getDocument().addDocumentListener(addListener);

            add(rmButton);
            add(itemName);
            add(addButton);
        }

        // EFFECTS: return a resized copy of the given icon 
        private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
            Image img = icon.getImage();  
            Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
            return new ImageIcon(resizedImage);
        }

        // EFFECTS: return an item add listener
        protected abstract AddListener initAddListener(JButton addButton);

        // EFFECTS: return an item remove listener
        protected abstract RmListener initRmListener();

    }

    // an abstract remove action listener
    abstract class RmListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: handle the remove action
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            String itemName = (String) list.getSelectedValue();
            rmDataAction();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                rmButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }

        // EFFECTS: perform an action to data when item is removed
        protected abstract void rmDataAction();
    }

    //This listener is shared by the text field and the hire button.
    abstract class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: construct the add listener
        public AddListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: this
        // EFFECTS: handle the add action
        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = itemName.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                itemName.requestFocusInWindow();
                itemName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(itemName.getText(), index);
            addItemDataAction();

            //Reset the text field.
            itemName.requestFocusInWindow();
            itemName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // what to do with data when item is added\
        // EFFECTS: perform an action to data when item is added
        protected abstract void addItemDataAction();

        
        // EFFECTS: return if a name is already in list
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        // EFFECTS: enable button when item is inserted
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        // EFFECTS: none
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        // EFFECTS: enable button when item is changed
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: enable button when it is not enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: disable add button when theres no name inputed 
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
        
    }
}
