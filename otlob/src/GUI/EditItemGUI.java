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
import javax.swing.JOptionPane;
import otlob.OTLOB;
import static otlob.OTLOB.AdminHashmap;
import static otlob.OTLOB.Ads;
import static otlob.OTLOB.Allrest;
import static otlob.OTLOB.CustomerHashmap;
import static otlob.OTLOB.DealHashmap;
import static otlob.OTLOB.WaiterHashmap;
import otlob.item;

/**
 *
 * @author Fady Bassel
 */
public class EditItemGUI extends JFrame {

    private JButton[] menuButtons = new JButton[0];
    private JButton backtodashboard = new JButton("Back");
    int indexR, indexM;

    public EditItemGUI(int indexofRest, int indexofMenu) {
        setSize(400, 700);
        setTitle(Allrest.get(indexofRest).Menu.get(indexofMenu).name);
        Container cp = getContentPane();
        indexR = indexofRest;
        indexM = indexofMenu;
        int bool;
        bool = JOptionPane.showConfirmDialog(null, "add new item?");
        while (bool == 0) {
            item r = new item();
            r.productName = JOptionPane.showInputDialog("Enter Item Name");
            r.price = Integer.valueOf(JOptionPane.showInputDialog("Enter Item Price"));
            bool = JOptionPane.showConfirmDialog(null, "Enter anoter item?");
            OTLOB.Restaurant.Menu.get(indexM).items.add(r);
        }
        menuButtons = new JButton[Allrest.get(indexofRest).Menu.get(indexofMenu).items.size()];
        cp.setLayout(new GridLayout(Allrest.get(indexofRest).Menu.get(indexofMenu).items.size() + 2, 1));
        for (int j = 0; j < Allrest.get(indexofRest).Menu.get(indexofMenu).items.size(); j++) {
            menuButtons[j] = new JButton(
                    Allrest.get(indexofRest).Menu.get(indexofMenu).items.get(j).productName
                    + "  "
                    + Allrest.get(indexofRest).Menu.get(indexofMenu).items.get(j).price
                    + "L.E");
            menuButtons[j].addActionListener(new ButtonListener());
            cp.add(menuButtons[j]);
        }
        cp.add(backtodashboard);
        backtodashboard.addActionListener(new ButtonListener());
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
             if (e.getSource().equals(backtodashboard)) {
                RestGUI restGUI = new RestGUI("s", "s");
                setVisible(false);
                restGUI.setVisible(true);
            }
            if (menuButtons.length != 0) {
                for (int i = 0; i < menuButtons.length; i++) {
                    if (e.getSource().equals(menuButtons[i])) {
                        String[] options = {"edit",
                            "delete"};
                        int n = JOptionPane.showOptionDialog(null,
                                "Would do you want to do to item? ",
                                "...",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if (n == 0) {
                            try {
                                int price = Integer.valueOf(JOptionPane.showInputDialog("Enter Item Price"));
                                OTLOB.Restaurant.Menu.get(indexM).items.get(i).price = price;
                                JOptionPane.showMessageDialog(null, "changed successfully");
                            } catch (Exception b) {
                                JOptionPane.showMessageDialog(null, "price Must Be numbers");
                            }

                        }
                        if (n == 1) {
                            OTLOB.Restaurant.Menu.get(indexM).items.remove(i);
                            JOptionPane.showMessageDialog(null, "item deleted successfuly");
                        }
                    }
                }
            }
           

        }
    }
}
