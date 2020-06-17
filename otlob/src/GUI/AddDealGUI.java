/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static otlob.OTLOB.*;
import otlob.deal;

/**
 *
 * @author mark
 */
public class AddDealGUI extends JFrame {

    private Container cp = getContentPane();
    private JComboBox<String> r = new JComboBox();
    private JLabel ChooseRestLabel = new JLabel("Choose restaurant");
    private JLabel DisCountLabel = new JLabel("Enter Discount %");
    private JTextField Discount = new JTextField(2);
    private JLabel CodeLabel = new JLabel("Enter Code");
    private JTextField Code = new JTextField(10);
    private JLabel DescriptionLabel = new JLabel("Enter Discription");
    private JTextField Description = new JTextField(20);
    private JButton Adddeal = new JButton("Add Deal");
    private JButton Back = new JButton("Back");

    public AddDealGUI() {
        JPanel j = new JPanel();
        j.setLayout(new GridLayout(10, 1));
        setSize(400, 700);
        setTitle("Add Deal");
        for (int i = 0; i < Allrest.size(); i++) {
            r.addItem(Allrest.get(i).name);
        }
        cp.setLayout(new FlowLayout());
        j.add(ChooseRestLabel);
        j.add(r);
        j.add(DisCountLabel);
        j.add(Discount);
        j.add(CodeLabel);
        j.add(Code);
        j.add(DescriptionLabel);
        j.add(Description);
        j.add(Adddeal);
        j.add(Back);
        cp.add(j);
        Back.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
        Adddeal.addActionListener(new ButtonListener());
    }

    private class ButtonListener extends WindowAdapter implements ActionListener {

        @Override
        public void windowClosing(WindowEvent e) {
            try {
                
                ObjectOutputStream ObjOut;
                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.restsArray));
                ObjOut.writeObject(Allrest);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.adminsHashmap));
                ObjOut.writeObject(AdminHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.customersHashmap));
                ObjOut.writeObject(CustomerHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.waitersHashmap));
                ObjOut.writeObject(WaiterHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.dealsHashmap));
                ObjOut.writeObject(DealHashmap);
                ObjOut.close();

                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.ad));
                ObjOut.writeObject(Ads);
                ObjOut.close();

            } catch (IOException ex) {
                Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(Adddeal)) {
                if (Discount.getText().isEmpty()
                        || Code.getText().isEmpty()
                        || Description.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                } else {
                    try {
                        Integer.parseInt(Discount.getText());
                        String Rname = r.getSelectedItem().toString();
                        deal D = new deal(Rname,
                                Integer.parseInt(Discount.getText()),
                                Code.getText(), Description.getText());
                        JOptionPane.showMessageDialog(null, Admin.addDeal(D));
                    } catch (Exception b) {
                        JOptionPane.showMessageDialog(null, "Discount Must Be Numbers Only");
                    }
                }
            } else if (e.getSource().equals(Back)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Admin);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            }
        }
    }
}
