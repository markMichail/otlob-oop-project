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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static otlob.OTLOB.*;
import otlob.Order;
import otlob.deal;
import otlob.visa;

/**
 *
 * @author mark
 */
public class VisaGUI extends JFrame {

    private Container cp = getContentPane();
    private JLabel CVV = new JLabel("CVV");
    private JTextField CVVTextField = new JTextField(15);
    private JLabel CardNumber = new JLabel("Card Number");
    private JTextField CardNumberTextField = new JTextField(15);
    private JLabel Month = new JLabel("Exp Month");
    private JTextField MonthTextField = new JTextField(10);
    private JLabel Year = new JLabel("Exp Year");
    private JTextField YearTextField = new JTextField(20);
    private JButton PlaceOrder = new JButton("place Order");
    private JButton Back = new JButton("Back");
    int rIndex;

    public VisaGUI(int Rindex) {
        rIndex = Rindex;
        JPanel j = new JPanel();
        j.setLayout(new GridLayout(10, 1));
        setSize(400, 700);
        setTitle("Visa Data");
        cp.setLayout(new FlowLayout());
        j.add(CVV);
        j.add(CVVTextField);
        j.add(CardNumber);
        j.add(CardNumberTextField);
        j.add(Month);
        j.add(MonthTextField);
        j.add(Year);
        j.add(YearTextField);
        j.add(PlaceOrder);
        j.add(Back);
        cp.add(j);
        Back.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
        PlaceOrder.addActionListener(new ButtonListener());
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
            boolean allvalide = true;
            if (e.getSource().equals(PlaceOrder)) {
                if (CVVTextField.getText().isEmpty()
                        || CardNumberTextField.getText().isEmpty()
                        || MonthTextField.getText().isEmpty()
                        || YearTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                    allvalide = false;
                } else if (CVVTextField.getText().length() != 3) {
                    JOptionPane.showMessageDialog(null, "CVV Must be 3 Numbers");
                    allvalide = false;
                } else if (CardNumberTextField.getText().length() != 14) {
                    JOptionPane.showMessageDialog(null, "CardNumber Must be 14 Numbers");
                    allvalide = false;
                } else if (MonthTextField.getText().length() > 2) {
                    JOptionPane.showMessageDialog(null, "Month Must be smaller than 2 Numbers");
                    allvalide = false;
                } else if (YearTextField.getText().length() != 4) {
                    JOptionPane.showMessageDialog(null, "Year Must be 4 Numbers");
                    allvalide = false;
                } else {
                    visa v = new visa();
                    try {
                        v.CVV = Integer.parseInt(CVVTextField.getText());
                    } catch (NumberFormatException i) {
                        JOptionPane.showMessageDialog(null, "CVV Must Be Numbers Only");
                        allvalide = false;
                    }
                    if (allvalide) {
                        try {
                            Long.parseLong(CardNumberTextField.getText());
                            v.cardNum = CardNumberTextField.getText();
                        } catch (NumberFormatException i) {
                            JOptionPane.showMessageDialog(null, "CardNumber Must Be Numbers Only");
                            allvalide = false;
                        }
                    }
                    if (allvalide) {
                        try {
                            v.expDate.month = Integer.parseInt(MonthTextField.getText());
                        } catch (NumberFormatException i) {
                            JOptionPane.showMessageDialog(null, "Month Must Be Numbers Only");
                            allvalide = false;
                        }
                    }
                    if (allvalide) {
                        try {
                            v.expDate.year = Integer.parseInt(YearTextField.getText());
                        } catch (NumberFormatException i) {
                            JOptionPane.showMessageDialog(null, "Year Must Be Numbers Only");
                            allvalide = false;
                        }
                    }

                }
                if (allvalide) {
                    try {
                        Order o = new Order();
                        o.RestName = nRArr.get(rIndex).name;
                        o.c = c;
                        BufferedReader BR;
                        BufferedWriter BW;
                        BR = new BufferedReader(new FileReader(
                                otlob.filepaths.ordersIDs));
                        o.orderID = Integer.parseInt(BR.readLine());
                        BR.close();
                        BW = new BufferedWriter(new FileWriter(
                                otlob.filepaths.ordersIDs));
                        BW.write(Integer.toString(o.orderID + 1));
                        BW.close();
                        JOptionPane.showMessageDialog(null, Customer.AddToOrdersFile(o));
                    } catch (IOException ex) {
                    }
                }
            } else if (e.getSource().equals(Back)) {
                checkoutGUI.setVisible(true);
                setVisible(false);
            }
        }
    }
}
