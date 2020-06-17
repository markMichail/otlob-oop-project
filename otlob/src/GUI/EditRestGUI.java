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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import otlob.OTLOB;
import static otlob.OTLOB.*;
import otlob.days;
import otlob.flight;
import otlob.item;
import otlob.menu;
import otlob.workingHours;

/**
 *
 * @author Fady Bassel
 */
public class EditRestGUI extends JFrame {

    private menu m = new menu();
    private JTextField title1 = new JTextField(12);
    private JButton b = new JButton("add item");
    private JButton C = new JButton("add new menu");

    private JButton F = new JButton("finish");
    int indexR;

    private JLabel Name = new JLabel("Enter restaurant name");
    private JLabel name = new JLabel();
    private JTextField restName = new JTextField(15);
    private JLabel del = new JLabel("Enter deivery fee");
    private JTextField deiveryFee = new JTextField(15);
    private JLabel est = new JLabel("Enter estimated time   ");
    private JLabel area = new JLabel("delivery areas:   ");
    private JTextField estimatedTime = new JTextField(15);
    private JLabel ewor = new JLabel("Enter working hours from:  ");
    private JTextField workingHoursF = new JTextField(15);
    private JLabel to = new JLabel("Enter working hours to:  ");
    private JTextField workingHoursT = new JTextField(15);
    private JCheckBox[] check = new JCheckBox[OTLOB.Places.size()];
    private JButton edit = new JButton("edit");
    private JButton delete = new JButton("delete");
    private JButton save = new JButton("save");
    private JButton back = new JButton("back");

    public EditRestGUI(int index) {

        indexR = index;
        setSize(400, 700);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel title = new JLabel("Enter menu title: ");

        b.addActionListener(new ButtonListener());
        C.addActionListener(new ButtonListener());
        F.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        cp.add(title);
        cp.add(title1);
        cp.add(b);
        cp.add(C);
        cp.add(F);
        cp.add(back);
        addWindowListener(new ButtonListener());

    }

    public EditRestGUI(int index, String s) {
        indexR = index;

        setSize(400, 700);
        setTitle("Edit restaurant");
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        JPanel j = new JPanel(new GridLayout());
        for (int i = 0; i < Places.size(); i++) {
            check[i] = new JCheckBox(OTLOB.Places.get(i));
            check[i].setSelected(false);
            j.add(check[i]);

        }
        addWindowListener(new ButtonListener());
        save.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        cp.add(Name);
        cp.add(restName);
        cp.add(del);
        cp.add(deiveryFee);
        cp.add(est);
        cp.add(estimatedTime);
        cp.add(area);
        cp.add(j);
        cp.add(ewor);
        cp.add(workingHoursF);
        cp.add(to);
        cp.add(workingHoursT);
        cp.add(save);
        cp.add(back);

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

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(b)) {

                m.name = title1.getText();
                int bool = 0;

                while (bool == 0) {
                    item r = new item();
                    String name = JOptionPane.showInputDialog("Enter Item Name");
                    if (name != null) {
                        r.productName = name;
                        try {
                            int price = Integer.parseInt(JOptionPane.showInputDialog("Enter Item Price"));
                            r.price = price;
                            m.items.add(r);
                        } catch (NumberFormatException f) {
                            JOptionPane.showMessageDialog(null, "wrong input!");

                        }
                    }

                    bool = JOptionPane.showConfirmDialog(null, "Enter anoter item?");

                }

            } else if (e.getSource().equals(C)) {
                EditRestGUI a = new EditRestGUI(indexR);
                setVisible(false);
                a.setVisible(true);
            } else if (e.getSource().equals(F)) {
                if (m.items.size() == 0) {
                    JOptionPane.showMessageDialog(null, "menu is still empty fill it!");
                } else {
                    Allrest.get(indexR).Menu.add(m);
                }
            } else if (e.getSource().equals(save)) {

                if (Name.getText().isEmpty()
                        || del.getText().isEmpty()
                        || est.getText().isEmpty()
                        || ewor.getText().isEmpty()
                        || workingHoursT.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                } else {
                    Allrest.get(indexR).name = restName.getText();
                    Allrest.get(indexR).delivery = new flight();
                    Allrest.get(indexR).delivery.deliveryFee = Integer.valueOf(deiveryFee.getText());
                    Allrest.get(indexR).delivery.timeEstimated = Integer.valueOf(estimatedTime.getText());
                    Allrest.get(indexR).timeWork = new workingHours();
                    Allrest.get(indexR).timeWork.day = new days();
                    Allrest.get(indexR).timeWork.day.from = Integer.valueOf(workingHoursF.getText());
                    Allrest.get(indexR).timeWork.day.to = Integer.valueOf(workingHoursT.getText());
                    Allrest.get(indexR).location = new ArrayList<>();
                    for (int i = 0; i < OTLOB.Places.size(); i++) {
                        if (check[i].isSelected()) {
                            Allrest.get(indexR).location.add(check[i].getText());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Data Has Been Updated");
                }

            } else if (e.getSource().equals(back)) {
                RestGUI restGUI = new RestGUI("s", "s");
                setVisible(false);
                restGUI.setVisible(true);
            }
        }
    }
}
