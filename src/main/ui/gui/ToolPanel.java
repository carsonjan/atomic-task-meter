package ui.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import model.ATM;



public class ToolPanel extends JPanel {
    
    private ATM data;
    private JButton rmButton;
    private JTextField itemName;

    public ToolPanel(ATM data) {
        this.data = data;
        
        rmButton = new JButton("🗑️");
        rmButton.addActionListener(new RmListener());

        ImageIcon imgIcon = new ImageIcon("data/plusImage.png");
        imgIcon = (ImageIcon) resizeIcon(imgIcon, 15, 15);
        JButton addButton = new JButton(imgIcon);

        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand("add");
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        itemName = new JTextField(10);
        itemName.addActionListener(addListener);
        itemName.getDocument().addDocumentListener(addListener);
        // String name = listModel.getElementAt(
        //                       list.getSelectedIndex()).toString();

        add(rmButton);
        add(itemName);
        add(addButton);

    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }

    public MyPanel getMyParent() {
        return (MyPanel) getParent();
    }

    public JButton getRmButton() {
        return rmButton;
    }

    class RmListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            MyScrollPane itemPane = getMyParent().getItemPane();
            JList list = itemPane.getList();
            DefaultListModel listModel = itemPane.getListModel();
            int index = list.getSelectedIndex();
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
    }

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = itemName.getText();
            MyScrollPane itemPane = getMyParent().getItemPane();
            JList list = itemPane.getList();
            DefaultListModel listModel = itemPane.getListModel();

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
            // data.makeProject(itemName.getText()); TODO this can add project, but add task also add project
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            itemName.requestFocusInWindow();
            itemName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            MyScrollPane itemPane = getMyParent().getItemPane();
            DefaultListModel listModel = itemPane.getListModel();
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

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
