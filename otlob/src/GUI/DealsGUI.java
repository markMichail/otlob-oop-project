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
import otlob.deal;

/**
 *
 * @author mark
 */
public class DealsGUI extends JFrame {

    private Container cp = getContentPane();
    private JButton Back = new JButton("Back");

    public DealsGUI() {
        setSize(400, 700);
        setTitle("Deals");
        if (DealHashmap.size() == 0) {
            cp.setLayout(new GridLayout(2, 1));
            JLabel Empty = new JLabel("No Deals Today");
            cp.add(Empty);
        } else {
            cp.setLayout(new GridLayout(DealHashmap.size() + 1, 1));
            JLabel[] DEALS = new JLabel[DealHashmap.size()];
            int i = 0;
            for (deal x : DealHashmap.values()) {
                DEALS[i] = new JLabel(x.description);
                cp.add(DEALS[i]);
                i++;
            }
        }
        cp.add(Back);
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
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Customer);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            }
        }
    }
}
