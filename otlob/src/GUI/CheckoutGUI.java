/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import static otlob.OTLOB.*;
import otlob.Order;

/**
 *
 * @author mark
 */
public class CheckoutGUI extends JFrame {

    private boolean temp = true;
    private JLabel CommentLabel = new JLabel("Comment");
    private JButton PaymentMethod = new JButton("Choose Payment Method");
    private JTextArea Comment = new JTextArea();
    private JButton AddVoucher = new JButton("AddVoucher");
    private JButton Back = new JButton("Back");
    private JLabel TotalNewPriceLabel = new JLabel("");
    private int RestIndex;

    public CheckoutGUI(int Index) {
        setSize(400, 700);
        setTitle("CheckOut");

        TotalNewPriceLabel.setText("Total Price:" + c.totalPrice);
        Comment.setColumns(20);
        Comment.setRows(30);
        RestIndex = Index;
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(CommentLabel);
        cp.add(Comment);
        cp.add(AddVoucher);
        cp.add(TotalNewPriceLabel);
        cp.add(PaymentMethod);
        cp.add(Back);
        AddVoucher.addActionListener(new ButtonListener());
        PaymentMethod.addActionListener(new ButtonListener());
        Back.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
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
            if (e.getSource().equals(Back)) {
                setVisible(false);
                cartGUI.setVisible(true);
            } else if (e.getSource().equals(AddVoucher) && temp) {
                c.voucher = JOptionPane.showInputDialog("Enter Voucher");
                if (Customer.checkVoucher(c.voucher, nRArr.get(RestIndex))) {
                    Customer.applyVoucher(c.voucher);
                    JOptionPane.showMessageDialog(null, "Voucher Is Applyed");
                    TotalNewPriceLabel.setText("The Total new Price is: "
                            + c.totalPrice);
                    temp = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Voucher");
                }
            } else if (e.getSource().equals(PaymentMethod)) {
                c.comment = Comment.getText();
                String[] options = {"Cash",
                    "Visa"};
                int n = JOptionPane.showOptionDialog(null,
                        "Choose Payment Method",
                        "...",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) {
                    String[] option = {"Yes",
                        "No"};
                    int s = JOptionPane.showOptionDialog(null,
                            "Place Order ?",
                            "...",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            option,
                            option[0]);
                    if (s == 0) {
                        try {
                            Order o = new Order();
                            o.RestName = nRArr.get(RestIndex).name;
                            o.c = c;
                            BufferedReader BR;
                            BufferedWriter BW;
                            BR = new BufferedReader(new FileReader(
                                    "D:\\Java\\Ordersid.txt"));
                            o.orderID = Integer.parseInt(BR.readLine());
                            BR.close();
                            BW = new BufferedWriter(new FileWriter(
                                    "D:\\Java\\Ordersid.txt"));
                            BW.write(Integer.toString(o.orderID + 1));
                            BW.close();
                            JOptionPane.showMessageDialog(null, Customer.AddToOrdersFile(o));
                        } catch (IOException ex) {
                            Logger.getLogger(CheckoutGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    VisaGUI visaGUI = new VisaGUI(RestIndex);
                    setVisible(false);
                    visaGUI.setVisible(true);

                }
            }
        }
    }
}
