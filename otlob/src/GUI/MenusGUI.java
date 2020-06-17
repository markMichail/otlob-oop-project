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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static otlob.OTLOB.*;
import otlob.cart;
import otlob.restaurant;
import otlob.item;
import otlob.menu;

/**
 *
 * @author mark
 */
public class MenusGUI extends JFrame {

    private JButton[] menuButtons = new JButton[0];
    private JButton[] AdsMenuButtons = new JButton[0];
    private JButton[] WaiterMenuButtons = new JButton[0];
    private JButton[] EmenuButtons = new JButton[0];
    private JTextField title1 = new JTextField(12);
    private JButton AddItem = new JButton("add item");
    private JButton AddNewMenu = new JButton("add new menu");
    private JButton AddPopularItems = new JButton("add popular items");
    private JButton Finish = new JButton("finish");
    private JButton BackFromCustomer = new JButton("back");
    private JButton BackFromSearch = new JButton("back");
    private JButton BackFromWaiter = new JButton("back");
    private JButton BackFromAdmin = new JButton("back");
    private JButton BackToDashBoard = new JButton("back");
    private int indexOfRest;
    public static int count;

    // For Customer
    public MenusGUI(int index) {
        setSize(400, 700);
        setTitle(nRArr.get(index).name);
        indexOfRest = index;
        JPanel j = new JPanel(new GridLayout(2, 2));
        BackFromCustomer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel delivery = new JLabel("Delivery: " + nRArr.get(index).delivery.deliveryFee + " LE");
        j.add(delivery);
        JLabel time = new JLabel("Estimeted time: " + nRArr.get(index).delivery.timeEstimated + " Min");
        j.add(time);
        JLabel workHours = new JLabel("WorkHours F: " + nRArr.get(index).timeWork.day.from + " T: "
                + nRArr.get(index).timeWork.day.to);
        j.add(workHours);
        JLabel Rating = new JLabel("Rating : " + nRArr.get(index).rating);
        j.add(Rating);
        JPanel j2 = new JPanel(new GridLayout(nRArr.get(index).Menu.size() + 1, 1));
        menuButtons = new JButton[nRArr.get(index).Menu.size() + 1];
        for (int i = 0; i < nRArr.get(index).Menu.size(); i++) {
            menuButtons[i] = new JButton(nRArr.get(index).Menu.get(i).name);
            j2.add(menuButtons[i]);
            menuButtons[i].addActionListener(new ButtonListener());
        }
        menuButtons[nRArr.get(index).Menu.size()] = new JButton("populer Items");
        menuButtons[nRArr.get(index).Menu.size()].addActionListener(new ButtonListener());
        j2.add(menuButtons[nRArr.get(index).Menu.size()]);
        Container cp = getContentPane();
        cp.add(j, BorderLayout.PAGE_START);
        cp.add(j2, BorderLayout.CENTER);
        cp.add(BackFromCustomer, BorderLayout.SOUTH);
        BackFromCustomer.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    // for waiter
    public MenusGUI(int index, boolean a) {
        setSize(400, 700);
        setTitle(nRArr.get(index).name);
        indexOfRest = index;
        JPanel j = new JPanel(new GridLayout(2, 2));
        BackFromCustomer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel delivery = new JLabel("Delivery: " + nRArr.get(index).delivery.deliveryFee + " LE");
        j.add(delivery);
        JLabel time = new JLabel("Estimeted time: " + nRArr.get(index).delivery.timeEstimated + " Min");
        j.add(time);
        JLabel workHours = new JLabel("WorkHours F: " + nRArr.get(index).timeWork.day.from + " T: "
                + nRArr.get(index).timeWork.day.to);
        j.add(workHours);
        JLabel Rating = new JLabel("Raaaaating : " + nRArr.get(index).rating);
        j.add(Rating);
        JPanel j2 = new JPanel(new GridLayout(nRArr.get(index).Menu.size() + 1, 1));
        WaiterMenuButtons = new JButton[nRArr.get(index).Menu.size() + 1];
        for (int i = 0; i < nRArr.get(index).Menu.size(); i++) {
            WaiterMenuButtons[i] = new JButton(nRArr.get(index).Menu.get(i).name);
            j2.add(WaiterMenuButtons[i]);
            WaiterMenuButtons[i].addActionListener(new ButtonListener());
        }
        WaiterMenuButtons[nRArr.get(index).Menu.size()] = new JButton("populer Items");
        WaiterMenuButtons[nRArr.get(index).Menu.size()].addActionListener(new ButtonListener());
        j2.add(WaiterMenuButtons[nRArr.get(index).Menu.size()]);
        Container cp = getContentPane();
        cp.add(j, BorderLayout.PAGE_START);
        cp.add(j2, BorderLayout.CENTER);
        cp.add(BackFromWaiter, BorderLayout.SOUTH);
        BackFromWaiter.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    // For Admin
    public MenusGUI(int index, int a) {
        setSize(400, 700);
        setTitle(Allrest.get(index).name);
        indexOfRest = index;
        JPanel j = new JPanel(new GridLayout(2, 2));
        BackFromCustomer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel delivery = new JLabel("Delivery: " + Allrest.get(index).delivery.deliveryFee + " LE");
        j.add(delivery);
        JLabel time = new JLabel("Estimeted time: " + Allrest.get(index).delivery.timeEstimated + " Min");
        j.add(time);
        JLabel workHours = new JLabel("WorkHours F: " + Allrest.get(index).timeWork.day.from + " T: "
                + Allrest.get(index).timeWork.day.to);
        j.add(workHours);
        JLabel Rating = new JLabel("Rating : " + Allrest.get(index).rating);
        j.add(Rating);
        JPanel j2 = new JPanel(new GridLayout(Allrest.get(index).Menu.size() + 1, 1));
        AdsMenuButtons = new JButton[Allrest.get(index).Menu.size() + 1];
        for (int i = 0; i < Allrest.get(index).Menu.size(); i++) {
            AdsMenuButtons[i] = new JButton(Allrest.get(index).Menu.get(i).name);
            j2.add(AdsMenuButtons[i]);
            AdsMenuButtons[i].addActionListener(new ButtonListener());
        }
        AdsMenuButtons[Allrest.get(index).Menu.size()] = new JButton("populer Items");
        AdsMenuButtons[Allrest.get(index).Menu.size()].addActionListener(new ButtonListener());
        j2.add(AdsMenuButtons[Allrest.get(index).Menu.size()]);
        Container cp = getContentPane();
        cp.add(j, BorderLayout.PAGE_START);
        cp.add(j2, BorderLayout.CENTER);
        cp.add(BackFromAdmin, BorderLayout.SOUTH);
        BackFromAdmin.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    // For Search
    public MenusGUI(restaurant r) {
        setSize(400, 700);
        setTitle(r.name);
        for (int i = 0; i < Allrest.size(); i++) {
            if (Allrest.get(i).name.equals(r.name)) {
                indexOfRest = i;
            }
        }
        JPanel j = new JPanel(new GridLayout(2, 2));
        JLabel delivery = new JLabel("Delivery: " + r.delivery.deliveryFee + " LE");
        JLabel time = new JLabel("Estimeted time: " + r.delivery.timeEstimated + " Min");
        JLabel workHours = new JLabel("WorkHours F: " + r.timeWork.day.from + " T: "
                + r.timeWork.day.to);
        JLabel Rating = new JLabel("Rating : " + r.rating);
        j.add(delivery);
        j.add(time);
        j.add(workHours);
        j.add(Rating);
        JPanel j2 = new JPanel(new GridLayout((r.Menu.size() + 1), 1));
        menuButtons = new JButton[r.Menu.size() + 1];
        for (int i = 0; i < r.Menu.size(); i++) {
            menuButtons[i] = new JButton(r.Menu.get(i).name);
            j2.add(menuButtons[i]);
            menuButtons[i].addActionListener(new ButtonListener());
        }
        menuButtons[r.Menu.size()] = new JButton("Popular Items");
        j2.add(menuButtons[r.Menu.size()]);
        menuButtons[r.Menu.size()].addActionListener(new ButtonListener());
        Container cp = getContentPane();
        cp.add(j, BorderLayout.PAGE_START);
        cp.add(j2, BorderLayout.CENTER);
        cp.add(BackFromSearch, BorderLayout.SOUTH);
        BackFromSearch.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    // For Edit Menu
    public MenusGUI(restaurant r, int y) {
        setSize(400, 700);
        setTitle(r.name);
        for (int i = 0; i < Allrest.size(); i++) {
            if (Allrest.get(i).name.equals(r.name)) {
                indexOfRest = i;
            }
        }
        JPanel j2 = new JPanel(new GridLayout((r.Menu.size() + 1), 1));
        EmenuButtons = new JButton[r.Menu.size() + 1];
        for (int i = 0; i < r.Menu.size(); i++) {
            EmenuButtons[i] = new JButton(r.Menu.get(i).name);
            j2.add(EmenuButtons[i]);
            EmenuButtons[i].addActionListener(new ButtonListener());
        }
        Container cp = getContentPane();
        cp.add(j2, BorderLayout.CENTER);
        cp.add(BackToDashBoard, BorderLayout.SOUTH);
        BackToDashBoard.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    // For Admin (Add Restaurant)
    public MenusGUI(String x) {

        setSize(400, 700);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel title = new JLabel("Enter menu title: ");
        BackFromAdmin.setLayout(new FlowLayout(FlowLayout.LEFT));
        AddItem.addActionListener(new ButtonListener());
        AddNewMenu.addActionListener(new ButtonListener());
        Finish.addActionListener(new ButtonListener());
        AddPopularItems.addActionListener(new ButtonListener());
        BackFromAdmin.addActionListener(new ButtonListener());
        cp.add(title);
        cp.add(title1);
        cp.add(AddItem);
        cp.add(AddNewMenu);
        cp.add(Finish);
        cp.add(AddPopularItems);
        cp.add(BackFromAdmin, BorderLayout.SOUTH);
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

            for (int i = 0; i < menuButtons.length; i++) {
                if (e.getSource().equals(menuButtons[i])) {
                    ItemsGUI itemsGUI = new ItemsGUI(indexOfRest, i);
                    setVisible(false);
                    itemsGUI.setVisible(true);
                }
            }
            for (int i = 0; i < WaiterMenuButtons.length; i++) {
                if (e.getSource().equals(WaiterMenuButtons[i])) {
                    ItemsGUI itemsGUI = new ItemsGUI(indexOfRest,true, i);
                    setVisible(false);
                    itemsGUI.setVisible(true);
                }
            }

            for (int i = 0; i < AdsMenuButtons.length; i++) {
                if (e.getSource().equals(AdsMenuButtons[i])) {
                    ItemsGUI itemsGUI = new ItemsGUI(indexOfRest, i, true, true);
                    setVisible(false);
                    itemsGUI.setVisible(true);
                }
            }

            for (int i = 0; i < EmenuButtons.length; i++) {
                if (e.getSource().equals(EmenuButtons[i])) {

                    String[] options = {"edit",
                        "delete"};
                    int n = JOptionPane.showOptionDialog(null,
                            "Would do you want to do to menu? ",
                            "...",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    // edit
                    if (n == 0) {
                        EditItemGUI itemsGUI = new EditItemGUI(indexOfRest, i);
                        setVisible(false);
                        itemsGUI.setVisible(true);
                    } // delete
                    else if (n == 1) {
                        int bool = JOptionPane.showConfirmDialog(null, "are you sure? ");
                        if (bool == 0) {
                            Allrest.get(indexOfRest).Menu.remove(i);
                            MenusGUI menusGUI = new MenusGUI(Allrest.get(indexOfRest), 4);
                            setVisible(false);
                            menusGUI.setVisible(true);
                        }
                    }
                }
            }
            if (e.getSource().equals(AddItem)) {
                menu m = new menu();
                m.name = title1.getText();
                int bool = 0;
                Restaurant.Menu.add(m);
                while (bool == 0) {
                    item r = new item();
                    r.productName = JOptionPane.showInputDialog("Enter Item Name");
                    try {
                        int price = Integer.parseInt(JOptionPane.showInputDialog("Enter Item Price"));
                        r.price = price;
                        Restaurant.Menu.get(Restaurant.Menu.size() - 1).items.add(r);
                    } catch (NumberFormatException f) {
                        JOptionPane.showMessageDialog(null, "wrong input!");

                    }
                    bool = JOptionPane.showConfirmDialog(null, "Enter anoter item?");

                }
            } else if (e.getSource().equals(AddNewMenu)) {
                MenusGUI menusGUI = new MenusGUI("a");
                setVisible(false);
                menusGUI.setVisible(true);
            } else if (e.getSource().equals(Finish)) {
                if (Restaurant.myOffers.popularItems.items.size() == 0) {
                    JOptionPane.showMessageDialog(null, "popular items is empty please fill it");
                } else if (Restaurant.Menu.size() == 0) {
                    JOptionPane.showMessageDialog(null, "you haven't added any menus");
                } else {
                    JOptionPane.showMessageDialog(null, Admin.addrestaurant(Restaurant));
                }
            } else if (e.getSource().equals(AddPopularItems)) {
                int bool = 0;
                while (bool == 0) {
                    item r = new item();
                    String name = JOptionPane.showInputDialog("Enter Item Name");
                    if (name != null) {
                        r.productName = name;
                        try {
                            r.price = Integer.valueOf(JOptionPane.showInputDialog("Enter Item Price"));
                        } catch (Exception g) {
                            JOptionPane.showMessageDialog(null, "Price Must be numbers only");
                        }
                    }
                    bool = JOptionPane.showConfirmDialog(null, "Enter anoter item?");
                    Restaurant.myOffers.popularItems.items.add(r);
                }
            } else if (e.getSource()
                    .equals(BackFromCustomer)) {
                c = new cart();
                setVisible(false);
                restGUI.setVisible(true);
            } else if (e.getSource()
                    .equals(BackFromSearch)) {
                c = new cart();
                setVisible(false);
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Customer);
                mainMenuGUI.setVisible(true);
            } else if (e.getSource()
                    .equals(BackFromWaiter)) {
                c = new cart();
                nRArr.clear();
                setVisible(false);
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Waiter);
                mainMenuGUI.setVisible(true);
            } else if (e.getSource()
                    .equals(BackFromAdmin)) {
                int bol = 0;
                if (Restaurant.myOffers.popularItems.items.size() == 0 || Restaurant.Menu.size() == 0) {
                    bol = JOptionPane.showConfirmDialog(null, "Are yoou sure want to exit");
                }

                if (bol == 0) {
                    setVisible(false);
                    MainMenuGUI mainMenuGUI = new MainMenuGUI(Admin);
                    mainMenuGUI.setVisible(true);
                }
            } else if (e.getSource()
                    .equals(BackToDashBoard)) {
                RestGUI restGUI = new RestGUI("s", "s");
                setVisible(false);
                restGUI.setVisible(true);
            }
        }
    }
}
