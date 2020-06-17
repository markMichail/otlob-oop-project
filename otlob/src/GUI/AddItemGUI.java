/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import otlob.OTLOB;
import static otlob.OTLOB.*;

/**
 *
 * @author Fady Bassel
 */
public class AddItemGUI extends JFrame {

    public JButton add = new JButton("add to cart");
    public JButton add2 = new JButton("add to cart");
    public JButton add3 = new JButton("add to cart");
    public JButton add4 = new JButton("add to cart");
    public JButton view = new JButton("view cart");
    public JButton back = new JButton("back");
    public int ie, j, k;

    public AddItemGUI(int indexR, int indexM, int i) {
        ie = indexR;
        j = indexM;
        k = i;
        setSize(400, 700);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel name = new JLabel("product name: " + nRArr.get(indexR).Menu.get(indexM).items.get(i).productName + " price: "
                + nRArr.get(indexR).Menu.get(indexM).items.get(i).price);
        name.setForeground(Color.BLUE);
        add.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        view.addActionListener(new ButtonListener());
        back.setBackground(Color.CYAN);
        JPanel o = new JPanel();
        o.setLayout(new FlowLayout());
        cp.add(name);
        o.add(add);
        o.add(view);
        o.add(back);
        cp.add(o);
        addWindowListener(new ButtonListener());
    }

    public AddItemGUI(int indexR, int indexM, int i, boolean a) {
        ie = indexR;
        j = indexM;
        k = i;
        setSize(400, 700);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel name = new JLabel("product name: " + nRArr.get(indexR).Menu.get(indexM).items.get(i).productName + " price: "
                + nRArr.get(indexR).Menu.get(indexM).items.get(i).price);
        name.setForeground(Color.BLUE);
        add3.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        view.addActionListener(new ButtonListener());
        back.setBackground(Color.CYAN);
        JPanel o = new JPanel();
        o.setLayout(new FlowLayout());
        cp.add(name);
        o.add(add3);
        o.add(view);
        o.add(back);
        cp.add(o);
        addWindowListener(new ButtonListener());
    }

    public AddItemGUI(int indexR, int i) {
        ie = indexR;
        k = i;
        setSize(400, 700);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel name = new JLabel("product name: " + OTLOB.nRArr.get(indexR).myOffers.popularItems.items.get(i).productName + " price: " + OTLOB.nRArr.get(indexR).myOffers.popularItems.items.get(i).price);
        name.setForeground(Color.BLUE);
        add2.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        view.addActionListener(new ButtonListener());
        back.setBackground(Color.CYAN);
        JPanel o = new JPanel();
        o.setLayout(new FlowLayout());
        cp.add(name);
        o.add(add2);
        o.add(view);
        o.add(back);
        cp.add(o);
        addWindowListener(new ButtonListener());
    }

    public AddItemGUI(int indexR, int i, boolean a) {
        ie = indexR;
        k = i;
        setSize(400, 700);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel name = new JLabel("product name: " + OTLOB.nRArr.get(indexR).myOffers.popularItems.items.get(i).productName + " price: " + OTLOB.nRArr.get(indexR).myOffers.popularItems.items.get(i).price);
        name.setForeground(Color.BLUE);
        add4.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        view.addActionListener(new ButtonListener());
        back.setBackground(Color.CYAN);
        JPanel o = new JPanel();
        o.setLayout(new FlowLayout());
        cp.add(name);
        o.add(add3);
        o.add(view);
        o.add(back);
        cp.add(o);
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
            if (e.getSource().equals(add)) {
                try {
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog("enter quantity"));
                    OTLOB.Customer.addItemToCart(OTLOB.nRArr.get(ie).Menu.get(j).items.get(k), OTLOB.c, quantity);
                    cartGUI = new CartGUI(ie, j, k);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "wrong input!! ");
                }
            } else if (e.getSource().equals(add2)) {
                try {
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog("enter quantity"));
                    OTLOB.Customer.addItemToCart(OTLOB.nRArr.get(ie).myOffers.popularItems.items.get(k), OTLOB.c, quantity);
                    cartGUI = new CartGUI(ie, j, k);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "wrong input!! ");
                }
            } else if (e.getSource().equals(view)) {
                if (OTLOB.c.itemsOfCart.size() == 0) {
                    JOptionPane.showMessageDialog(null, "your cart is empty");
                } else {
                    setVisible(false);
                    cartGUI.setVisible(true);
                }
            } else if (e.getSource().equals(back)) {
                setVisible(false);
                ItemsGUI x = new ItemsGUI(ie, j);
                x.setVisible(true);
            } else if (e.getSource().equals(add4)) {
                try {
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog("enter quantity"));
                    OTLOB.Customer.addItemToCart(OTLOB.nRArr.get(ie).myOffers.popularItems.items.get(k), OTLOB.c, quantity);
                    cartGUI = new CartGUI(ie, j, k, true);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "wrong input!! ");
                }
            }else if (e.getSource().equals(add3)) {
                try {
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog("enter quantity"));
                    OTLOB.Customer.addItemToCart(OTLOB.nRArr.get(ie).Menu.get(j).items.get(k), OTLOB.c, quantity);
                    cartGUI = new CartGUI(ie, j, k ,true);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "wrong input!! ");
                }
            }

        }
    }

}
