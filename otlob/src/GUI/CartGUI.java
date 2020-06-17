/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import otlob.OTLOB;
import static otlob.OTLOB.*;

/**
 *
 * @author Fady Bassel
 */
public class CartGUI extends JFrame {

    private JButton back = new JButton("back");
    private JButton check = new JButton("proceed to check point");
    private JButton placeorder = new JButton("place order");
    private JButton[] x = new JButton[OTLOB.c.itemsOfCart.size()];
    int sum = 0;
    int i1, j, k;
    private float oldPrice;

    public CartGUI(int Rindex, int Mindex, int Iindex) {
        i1 = Rindex;
        j = Mindex;
        k = Iindex;
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        setSize(400, 700);
        setTitle("Your Cart");
        Container cp = getContentPane();
        oldPrice = c.totalPrice;
        c.totalPrice = OTLOB.c.addtaxesAndDel(Allrest.get(Rindex).delivery.deliveryFee);
        JPanel p = new JPanel(new GridLayout());
        for (int i = 0; i < OTLOB.c.itemsOfCart.size(); i++) {
            x[i] = new JButton(OTLOB.c.itemsOfCart.get(i).productName);
            x[i].addActionListener(new ButtonListener());
            sum += OTLOB.c.itemsOfCart.get(i).price;
            x[i].setBorder(border);
            p.add(x[i]);
        }
        cp.setLayout(new FlowLayout());

        cp.add(p);
        JLabel del = new JLabel("delevery fee:: " + Allrest.get(Rindex).delivery.deliveryFee);
        cp.add(del);

        JLabel price = new JLabel("Price After Delivery And Taxes: " + c.totalPrice);
        cp.add(price);
        back.setBackground(Color.CYAN);
        back.addActionListener(new ButtonListener());
        check.addActionListener(new ButtonListener());
        cp.add(back);
        cp.add(check);
        addWindowListener(new ButtonListener());
    }

    public CartGUI(int Rindex, int Mindex, int Iindex, boolean a) {
        i1 = Rindex;
        j = Mindex;
        k = Iindex;
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        setSize(400, 700);
        setTitle("Your Cart");
        Container cp = getContentPane();
        oldPrice = c.totalPrice;
        c.totalPrice = OTLOB.c.addtaxesAndDel(Allrest.get(Rindex).delivery.deliveryFee);
        JPanel p = new JPanel(new GridLayout());
        for (int i = 0; i < OTLOB.c.itemsOfCart.size(); i++) {
            x[i] = new JButton(OTLOB.c.itemsOfCart.get(i).productName);
            x[i].addActionListener(new ButtonListener());
            sum += OTLOB.c.itemsOfCart.get(i).price;
            x[i].setBorder(border);
            p.add(x[i]);
        }
        cp.setLayout(new FlowLayout());
        cp.add(p);
        JLabel price = new JLabel("Price After Delivery And Taxes: " + c.totalPrice);
        cp.add(price);
        back.setBackground(Color.CYAN);
        back.addActionListener(new ButtonListener());
        placeorder.addActionListener(new ButtonListener());
        cp.add(back);
        cp.add(placeorder);
        
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
            if (e.getSource().equals(back)) {
                c.totalPrice = oldPrice;
                setVisible(false);
                AddItemGUI g = new AddItemGUI(i1, j, k);
                g.setVisible(true);
            } else if (e.getSource().equals(check)) {
                checkoutGUI = new CheckoutGUI(i1);
                setVisible(false);
                checkoutGUI.setVisible(true);
            } else if (e.getSource().equals(placeorder)) {
                try {
                    JOptionPane.showMessageDialog(null, Waiter.sendToCachier(c) 
                            + " And " +Waiter.sendToChef(c));
                    
                } catch (IOException ex) {
                    Logger.getLogger(CartGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                for (int i = 0; i < OTLOB.c.itemsOfCart.size(); i++) {
                    if (e.getSource().equals(x[i])) {

                        int bool = JOptionPane.showConfirmDialog(null, "remove item ?");
                        if (bool == 0) {

                            OTLOB.c.totalPrice = (float) (sum - OTLOB.c.itemsOfCart.get(i).price);
                            OTLOB.c.itemsOfCart.remove(i);
                            if (!OTLOB.c.itemsOfCart.isEmpty()) {
                                cartGUI = new CartGUI(i1, j, k);
                                setVisible(false);
                                cartGUI.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "your cart is empty");
                                setVisible(false);
                                AddItemGUI g = new AddItemGUI(i1, j, k);
                                g.setVisible(true);
                            }

                        }
                    }
                }
            }
        }
    }
}
