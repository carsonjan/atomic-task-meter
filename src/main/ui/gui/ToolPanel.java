package ui.gui;

import java.awt.Color;
import java.io.File;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ATM;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ToolPanel extends JPanel {
    
    private MyPanel parent;
    private ATM data;

    public ToolPanel(MyPanel parent, ATM data) {
        this.parent = parent;
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

        JButton rmButton = new JButton("🗑️");
        // more ...

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
}
