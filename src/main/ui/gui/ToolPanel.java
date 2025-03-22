package ui.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import model.ATM;



public class ToolPanel extends JPanel {
    
    private ATM data;
    private JButton rmButton;

    public ToolPanel(ATM data) {
        this.data = data;

        ImageIcon imgIcon = new ImageIcon("data/plusImage.png");
        imgIcon = (ImageIcon) resizeIcon(imgIcon, 15, 15);
        JButton addButton = new JButton(imgIcon);

        // try {
        //     // Image img = (Image) new File("../../../../data/plusImage.png");
        //     addButton.setIcon(new ImageIcon(img));
        // } catch (Exception ex) {
        //     System.out.println(ex);
        // }
        // HireListener hireListener = new HireListener(hireButton);
        // addButton.setActionCommand(hireString);
        // addButton.addActionListener(hireListener);
        // addButton.setEnabled(false);

        rmButton = new JButton("🗑️");
        rmButton.addActionListener(new RmListener());

        JTextField itemName = new JTextField(10);
        // itemName.addActionListener(hireListener);
        // itemName.getDocument().addDocumentListener(hireListener);
        // String name = listModel.getElementAt(
        //                       list.getSelectedIndex()).toString();

        add(rmButton);
        add(itemName);
        add(addButton);

        // stub
        // JLabel testLabel = new JLabel("This is a tool panel");
        // testLabel.setBackground(Color.RED);
        // testLabel.setOpaque(true);
        // add(testLabel);
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
}
