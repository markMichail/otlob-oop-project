/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import static otlob.OTLOB.*;

/**
 *
 * @author mark
 */
public class ItemsGUI extends JFrame {

    private JButton[] menuButtons = new JButton[0];
    private JButton[] menuButtons1 = new JButton[0];
    private JButton[] WaiterPopularItemButtons = new JButton[0];
    private JButton[] WaiterMenuButtons = new JButton[0];
    private JButton[] AdsItemButtons = new JButton[0];
    private JButton Back = new JButton("Back");
    int indexR, indexM;

    public ItemsGUI(int indexofRest, int indexofMenu) {
        setSize(400, 700);
        Container cp = getContentPane();
        indexR = indexofRest;
        indexM = indexofMenu;
        if (indexofMenu == nRArr.get(indexofRest).Menu.size()) {
            setTitle(nRArr.get(indexofRest).myOffers.popularItems.name);
            menuButtons1 = new JButton[nRArr.get(indexofRest).myOffers.popularItems.items.size()];
            cp.setLayout(new GridLayout(nRArr.get(indexofRest).myOffers.popularItems.items.size() + 1, 1));
            for (int j = 0; j < nRArr.get(indexofRest).myOffers.popularItems.items.size(); j++) {
                menuButtons1[j] = new JButton(
                        nRArr.get(indexofRest).myOffers.popularItems.items.get(j).productName
                        + "  "
                        + nRArr.get(indexofRest).myOffers.popularItems.items.get(j).price
                        + "L.E");
                menuButtons1[j].addActionListener(new ButtonListener());
                cp.add(menuButtons1[j]);
            }
        } else {
            setTitle(nRArr.get(indexofRest).Menu.get(indexofMenu).name);
            menuButtons = new JButton[nRArr.get(indexofRest).Menu.get(indexofMenu).items.size()];
            cp.setLayout(new GridLayout(nRArr.get(indexofRest).Menu.get(indexofMenu).items.size() + 1, 1));
            for (int j = 0; j < nRArr.get(indexofRest).Menu.get(indexofMenu).items.size(); j++) {
                menuButtons[j] = new JButton(
                        nRArr.get(indexofRest).Menu.get(indexofMenu).items.get(j).productName
                        + "  "
                        + nRArr.get(indexofRest).Menu.get(indexofMenu).items.get(j).price
                        + "L.E");
                menuButtons[j].addActionListener(new ButtonListener());
                cp.add(menuButtons[j]);
            }

        }
        cp.add(Back);
        Back.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    public ItemsGUI(int indexofRest, boolean a, int indexofMenu) {
        setSize(400, 700);
        Container cp = getContentPane();
        indexR = indexofRest;
        indexM = indexofMenu;
        if (indexofMenu == nRArr.get(indexofRest).Menu.size()) {
            setTitle(nRArr.get(indexofRest).myOffers.popularItems.name);
            WaiterPopularItemButtons = new JButton[nRArr.get(indexofRest).myOffers.popularItems.items.size()];
            cp.setLayout(new GridLayout(nRArr.get(indexofRest).myOffers.popularItems.items.size() + 1, 1));
            for (int j = 0; j < nRArr.get(indexofRest).myOffers.popularItems.items.size(); j++) {
                WaiterPopularItemButtons[j] = new JButton(
                        nRArr.get(indexofRest).myOffers.popularItems.items.get(j).productName
                        + "  "
                        + nRArr.get(indexofRest).myOffers.popularItems.items.get(j).price
                        + "L.E");
                WaiterPopularItemButtons[j].addActionListener(new ButtonListener());
                cp.add(WaiterPopularItemButtons[j]);
            }
        } else {
            setTitle(nRArr.get(indexofRest).Menu.get(indexofMenu).name);
            WaiterMenuButtons = new JButton[nRArr.get(indexofRest).Menu.get(indexofMenu).items.size()];
            cp.setLayout(new GridLayout(nRArr.get(indexofRest).Menu.get(indexofMenu).items.size() + 1, 1));
            for (int j = 0; j < nRArr.get(indexofRest).Menu.get(indexofMenu).items.size(); j++) {
                WaiterMenuButtons[j] = new JButton(
                        nRArr.get(indexofRest).Menu.get(indexofMenu).items.get(j).productName
                        + "  "
                        + nRArr.get(indexofRest).Menu.get(indexofMenu).items.get(j).price
                        + "L.E");
                WaiterMenuButtons[j].addActionListener(new ButtonListener());
                cp.add(WaiterMenuButtons[j]);
            }

        }
        cp.add(Back);
        Back.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    public ItemsGUI(int indexofRest, int indexofMenu, boolean a) {
        setSize(400, 700);
        Container cp = getContentPane();
        indexR = indexofRest;
        indexM = indexofMenu;
        if (indexofMenu == Allrest.get(indexofRest).Menu.size()) {
            setTitle(Allrest.get(indexofRest).myOffers.popularItems.name);
            menuButtons1 = new JButton[Allrest.get(indexofRest).myOffers.popularItems.items.size()];
            cp.setLayout(new GridLayout(Allrest.get(indexofRest).myOffers.popularItems.items.size(), 1));
            for (int j = 0; j < Allrest.get(indexofRest).myOffers.popularItems.items.size(); j++) {
                menuButtons1[j] = new JButton(
                        Allrest.get(indexofRest).myOffers.popularItems.items.get(j).productName
                        + "  "
                        + Allrest.get(indexofRest).myOffers.popularItems.items.get(j).price
                        + "L.E");
                menuButtons1[j].addActionListener(new ButtonListener());
                cp.add(menuButtons1[j]);
            }
        } else {
            setTitle(Allrest.get(indexofRest).Menu.get(indexofMenu).name);
            menuButtons = new JButton[Allrest.get(indexofRest).Menu.get(indexofMenu).items.size()];
            cp.setLayout(new GridLayout(Allrest.get(indexofRest).Menu.get(indexofMenu).items.size(), 1));
            for (int j = 0; j < Allrest.get(indexofRest).Menu.get(indexofMenu).items.size(); j++) {
                menuButtons[j] = new JButton(
                        Allrest.get(indexofRest).Menu.get(indexofMenu).items.get(j).productName
                        + "  "
                        + Allrest.get(indexofRest).Menu.get(indexofMenu).items.get(j).price
                        + "L.E");
                menuButtons[j].addActionListener(new ButtonListener());
                cp.add(menuButtons[j]);
            }
        }
        addWindowListener(new ButtonListener());
    }

    public ItemsGUI(int indexofRest, int indexofMenu, boolean a, boolean b) {
        setSize(400, 700);
        Container cp = getContentPane();
        indexR = indexofRest;
        indexM = indexofMenu;
        if (indexofMenu == Allrest.get(indexofRest).Menu.size()) {
            setTitle(Allrest.get(indexofRest).myOffers.popularItems.name);
            AdsItemButtons = new JButton[Allrest.get(indexofRest).myOffers.popularItems.items.size()];
            cp.setLayout(new GridLayout(Allrest.get(indexofRest).myOffers.popularItems.items.size(), 1));
            for (int j = 0; j < Allrest.get(indexofRest).myOffers.popularItems.items.size(); j++) {
                AdsItemButtons[j] = new JButton(
                        Allrest.get(indexofRest).myOffers.popularItems.items.get(j).productName
                        + "  "
                        + Allrest.get(indexofRest).myOffers.popularItems.items.get(j).price
                        + "L.E");
                AdsItemButtons[j].addActionListener(new ButtonListener());
                cp.add(AdsItemButtons[j]);
            }
            cp.add(Back);
        } else {
            setTitle(Allrest.get(indexofRest).Menu.get(indexofMenu).name);
            AdsItemButtons = new JButton[Allrest.get(indexofRest).Menu.get(indexofMenu).items.size()];
            cp.setLayout(new GridLayout(Allrest.get(indexofRest).Menu.get(indexofMenu).items.size(), 1));
            for (int j = 0; j < Allrest.get(indexofRest).Menu.get(indexofMenu).items.size(); j++) {
                AdsItemButtons[j] = new JButton(
                        Allrest.get(indexofRest).Menu.get(indexofMenu).items.get(j).productName
                        + "  "
                        + Allrest.get(indexofRest).Menu.get(indexofMenu).items.get(j).price
                        + "L.E");
                AdsItemButtons[j].addActionListener(new ButtonListener());
                cp.add(AdsItemButtons[j]);
            }
            cp.add(Back);

        }
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

            if (menuButtons.length != 0) {
                for (int i = 0; i < menuButtons.length; i++) {
                    if (e.getSource().equals(menuButtons[i])) {
                        AddItemGUI itemsGUI = new AddItemGUI(indexR, indexM, i);
                        setVisible(false);
                        itemsGUI.setVisible(true);
                    }
                }
            }

            for (int i = 0; i < WaiterMenuButtons.length; i++) {
                if (e.getSource().equals(WaiterMenuButtons[i])) {
                    AddItemGUI itemsGUI = new AddItemGUI(indexR, indexM, i,true);
                    setVisible(false);
                    itemsGUI.setVisible(true);
                }
            }

            for (int i = 0; i < WaiterPopularItemButtons.length; i++) {
                if (e.getSource().equals(WaiterPopularItemButtons[i])) {
                    AddItemGUI itemsGUI = new AddItemGUI(indexR, i , true);
                    setVisible(false);
                    itemsGUI.setVisible(true);
                }
            }

            if (menuButtons1.length != 0) {
                for (int i = 0; i < menuButtons1.length; i++) {
                    if (e.getSource().equals(menuButtons1[i])) {
                        AddItemGUI itemsGUI = new AddItemGUI(indexR, i);
                        setVisible(false);
                        itemsGUI.setVisible(true);
                    }
                }
            }

            if (AdsItemButtons.length != 0) {
                for (int i = 0; i < AdsItemButtons.length; i++) {
                    if (e.getSource().equals(AdsItemButtons[i])) {
                        Ads.itm.productName = Allrest.get(indexR).Menu.get(indexM).items.get(i).productName;
                        Ads.itm.price = Allrest.get(indexR).Menu.get(indexM).items.get(i).price;
                        Ads.RName = Allrest.get(indexR).name;
                        JOptionPane.showMessageDialog(null, "Item Added to Be Ad");
                    }
                }
            }

            if (e.getSource().equals(Back)) {
                setVisible(false);
                menusGUI.setVisible(true);
            }
        }
    }
}
