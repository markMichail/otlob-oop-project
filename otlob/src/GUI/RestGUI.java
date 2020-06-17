/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
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
import javax.swing.BoxLayout;
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
import otlob.restaurant;
import otlob.workingHours;

/**
 *
 * @author mark
 */
public class RestGUI extends JFrame {

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
    private JLabel n = new JLabel("number of menu categories ::  ");
    private JCheckBox[] check = new JCheckBox[OTLOB.Places.size()];
    private JButton Back = new JButton("Back");
    private JButton BackForAdmin = new JButton("Back");
    private JButton[] restButtons = new JButton[0];
    private JButton[] edit = new JButton[Allrest.size()];
    private JButton[] delete = new JButton[Allrest.size()];

    public RestGUI(int x) {
        setSize(400, 700);
        setTitle("Near Restaurants");
        int i = 0;
        Container cp = getContentPane();
        restButtons = new JButton[OTLOB.nRArr.size()];
        cp.setLayout(new GridLayout(OTLOB.nRArr.size() + 1, 2));
        for (restaurant r : OTLOB.nRArr) {
            restButtons[i] = new JButton(r.name.toUpperCase());
            restButtons[i].addActionListener(new ButtonListener());
            cp.add(restButtons[i]);
            i++;
        }
        Back.addActionListener(new ButtonListener());
        cp.add(Back);
        addWindowListener(new ButtonListener());
    }

    private JButton[] allrestButtons = new JButton[0];

    public RestGUI() {
        setSize(400, 700);
        setTitle("All Restaurants");
        int i = 0;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(OTLOB.Allrest.size() + 1, 2));
        allrestButtons = new JButton[OTLOB.Allrest.size()];
        for (restaurant r : OTLOB.Allrest) {
            allrestButtons[i] = new JButton(r.name.toUpperCase());
            allrestButtons[i].addActionListener(new ButtonListener());
            cp.add(allrestButtons[i]);
            i++;
        }
        BackForAdmin.addActionListener(new ButtonListener());
        cp.add(BackForAdmin);
        addWindowListener(new ButtonListener());
    }

    private JButton[] reviewButtons = new JButton[0];
    public JButton AddMenu = new JButton("add menu");

    // Make A Review
    public RestGUI(boolean A) {
        setSize(400, 700);
        setTitle("Review");
        int i = 0;
        Container cp = getContentPane();
        reviewButtons = new JButton[nRArr.size()];
        cp.setLayout(new GridLayout((nRArr.size() + 1), 2));
        for (restaurant r : nRArr) {
            reviewButtons[i] = new JButton(r.name.toUpperCase());
            reviewButtons[i].addActionListener(new ButtonListener());
            cp.add(reviewButtons[i]);
            i++;
        }
        Back.addActionListener(new ButtonListener());
        cp.add(Back);
        addWindowListener(new ButtonListener());
    }

    public RestGUI(String s) {
        setSize(400, 700);
        setTitle("Add new restaurant");
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        JPanel j = new JPanel(new GridLayout());
        for (int i = 0; i < Places.size(); i++) {
            check[i] = new JCheckBox(OTLOB.Places.get(i));
            check[i].setSelected(false);
            j.add(check[i]);

        }
        AddMenu.addActionListener(new ButtonListener());
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
        cp.add(n);
        cp.add(AddMenu);
        cp.add(BackForAdmin);
        BackForAdmin.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    public RestGUI(String s, String S) {
        setSize(400, 700);
        setTitle("Restaurant dashboard");
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        JPanel j = new JPanel(new FlowLayout());
        for (int i = 0; i < Allrest.size(); i++) {
            name = new JLabel(i + 1 + "-" + Allrest.get(i).name + "                      ");
            edit[i] = new JButton("edit");
            delete[i]=new JButton("delete");
            delete[i].addActionListener(new ButtonListener());
            edit[i].addActionListener(new ButtonListener());
            j.add(name);
            j.add(edit[i]);

        }
        j.add(BackForAdmin);
        cp.add(j);
        BackForAdmin.addActionListener(new ButtonListener());
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
            } catch (IOException ex) {
                Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < reviewButtons.length; i++) {
                if (e.getSource().equals(reviewButtons[i])) {
                    try {
                        int x = Integer.valueOf(JOptionPane.showInputDialog("Enter No From 1 TO 5"));
                        if (x <= 5 && x >= 1) {
                            nRArr.get(i).rating = Customer.makereview(nRArr.get(i), x);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter Numbers between 1 & 5");
                        }

                    } catch (Exception a) {
                        JOptionPane.showMessageDialog(null, "Please enter Numbers Only");
                    }
                }
            }

            for (int i = 0; i < restButtons.length; i++) {
                if (e.getSource().equals(restButtons[i])) {
                    menusGUI = new MenusGUI(i);
                    setVisible(false);
                    menusGUI.setVisible(true);
                }
            }

            for (int i = 0; i < allrestButtons.length; i++) {
                if (e.getSource().equals(allrestButtons[i])) {
                    menusGUI = new MenusGUI(i, 0);
                    setVisible(false);
                    menusGUI.setVisible(true);
                }
            }
            if (e.getSource().equals(Back)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Customer);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            }
            
            for (int i = 0; i < Allrest.size(); i++) {
                
                if (e.getSource().equals(delete[i])){
                   Allrest.remove(i);
                    setVisible(false);
                    RestGUI g=new RestGUI("k","k");
                    g.setVisible(true);
                }
            }
            for (int i = 0; i < Allrest.size(); i++) {
                if (e.getSource().equals(edit[i])) {
                    Restaurant = Allrest.get(i);
                    String[] options = {"restaurant info",
                        "menu",
                        "popular items"};
                    int n = JOptionPane.showOptionDialog(null,
                            "Would do you want to edit? ",
                            "...",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        EditRestGUI rr = new EditRestGUI(i, "a");
                        setVisible(false);
                        rr.setVisible(true);
                    }
                    if (n == 1) {
                        int b = JOptionPane.showConfirmDialog(null, "add new menu? ");
                        if (b == 0) {
                            EditRestGUI rr = new EditRestGUI(i);
                            setVisible(false);
                            rr.setVisible(true);
                        } else {
                            MenusGUI m = new MenusGUI(Allrest.get(i), 4);
                            setVisible(false);
                            m.setVisible(true);
                        }
                    } else if (n == 2) {
                        Allrest.get(i).myOffers.popularItems.items.clear();
                        int bool = 0;
                        while (bool == 0) {
                            item r = new item();
                            
                           String name = JOptionPane.showInputDialog("Enter Item Name");
                           if (name!=null){
                               r.productName=name;
                            try {
                                int price = Integer.parseInt(JOptionPane.showInputDialog("Enter Item Price"));
                                r.price = price;
                                Allrest.get(i).myOffers.popularItems.items.add(r);
                            } catch (NumberFormatException f) {
                                JOptionPane.showMessageDialog(null, "wrong input!");

                            }}
                            bool = JOptionPane.showConfirmDialog(null, "Enter anoter item?");
                        }
                    }
                }
            }
            if (e.getSource().equals(BackForAdmin)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Admin);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            }
            if (e.getSource().equals(AddMenu)) {
                if (Name.getText().isEmpty()
                        || del.getText().isEmpty()
                        || est.getText().isEmpty()
                        || ewor.getText().isEmpty()
                        || workingHoursT.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                } else {

                    Restaurant.name = restName.getText();
                    Restaurant.delivery = new flight();
                    Restaurant.delivery.deliveryFee = Integer.valueOf(deiveryFee.getText());
                    Restaurant.delivery.timeEstimated = Integer.valueOf(estimatedTime.getText());
                    Restaurant.timeWork = new workingHours();
                    Restaurant.timeWork.day = new days();
                    Restaurant.timeWork.day.from = Integer.valueOf(workingHoursF.getText());
                    Restaurant.timeWork.day.to = Integer.valueOf(workingHoursT.getText());
                    Restaurant.location = new ArrayList<>();
                    for (int i = 0; i < Places.size(); i++) {
                        if (check[i].isSelected()) {
                            Restaurant.location.add(check[i].getText());
                        }
                    }
                    menusGUI = new MenusGUI("a");
                    setVisible(false);
                    menusGUI.setVisible(true);
                }
            }
        }
    }
}