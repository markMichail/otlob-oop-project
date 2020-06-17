/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Container;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import static otlob.OTLOB.*;
import otlob.Order;


/**
 *
 * @author mark
 */
public class RecentOrdersGUI extends JFrame {

    private JButton back = new JButton("Back");
    public RecentOrdersGUI() {
        setSize(400, 700);
        setTitle("Recent Orders");
        Container cp = getContentPane();
        if (ordersList == null) {
            cp.setLayout(new GridLayout(1, 1));
            JLabel NoRecentOrders = new JLabel("No Recent Orders");
            cp.add(NoRecentOrders);
        } else {
            int count = ordersList.size();
            for (Order a : ordersList) {
                count += 2;
                count += a.c.itemsOfCart.size();
                count += 4;
            }
            for (Order a : ordersList) {
                cp.setLayout(new GridLayout(count, 1));
                JLabel orderID = new JLabel("Order Id: " + a.orderID);
                cp.add(orderID);
                JLabel RestName = new JLabel("Restaurant Name: " + a.RestName);
                cp.add(RestName);
                for (int d = 0; d < a.c.itemsOfCart.size(); d++) {
                    JLabel itemLabel = new JLabel("Item "
                            + (d + 1) + ": "
                            + a.c.itemsOfCart.get(d).productName + " ->> Price: "
                            + a.c.itemsOfCart.get(d).price + " LE");
                    cp.add(itemLabel);
                }

                JLabel priceLabel = new JLabel("Total Price: " + a.c.totalPrice);
                cp.add(priceLabel);
                JLabel VoucherLabel = new JLabel("Voucher: " + a.c.voucher);
                cp.add(VoucherLabel);
                JLabel CommentLabel = new JLabel("Comment: " + a.c.comment);
                cp.add(CommentLabel);
                JLabel Empty = new JLabel(" ");
                cp.add(Empty);
            }
            cp.add(back);
        }
        back.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    private class ButtonListener extends WindowAdapter implements ActionListener{

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
            MainMenuGUI mainMenuGUI = new MainMenuGUI(Customer);
            mainMenuGUI.setVisible(true);
            setVisible(false);
        }
    }
}
