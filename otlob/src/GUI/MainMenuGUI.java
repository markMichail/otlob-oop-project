/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.*;
import javax.swing.*;
import otlob.admin;
import otlob.customer;
import otlob.waiter;
import otlob.restaurant;
import static otlob.OTLOB.*;

/**
 *
 * @author mark
 */
public class MainMenuGUI extends JFrame {
    
    private Container cp = getContentPane();
    private JButton[] AdminButtons = new JButton[11];

    // Main Menu Of The Admin
    public MainMenuGUI(admin a) {
        setSize(400, 700);
        setTitle("Main Menu");
        AdminButtons[0] = new JButton("Add Restaurant");
        AdminButtons[1] = new JButton("Add deal");
        AdminButtons[2] = new JButton("Edit Restaurant ");
        AdminButtons[3] = new JButton("Remove Customer");
        AdminButtons[4] = new JButton("make Item Ads");
        AdminButtons[5] = new JButton("Update Your data");
        AdminButtons[6] = new JButton("Remove Waiter");
        AdminButtons[7] = new JButton("Remove Deal");
        AdminButtons[8] = new JButton("Change Password");
        AdminButtons[9] = new JButton("Signout");
        AdminButtons[10] = new JButton("Add place");
        cp.setLayout(new GridLayout(AdminButtons.length, 1));
        for (int i = 0; i < AdminButtons.length; i++) {
            cp.add(AdminButtons[i]);
            AdminButtons[i].addActionListener(new ButtonListener());
        }
        addWindowListener(new ButtonListener());
    }
    
    private JButton[] CustomerButtons = new JButton[10];

    // Main Menu Of The Customer
    public MainMenuGUI(customer c) {
        setSize(400, 700);
        setTitle("Main Menu");
        CustomerButtons[0] = new JButton("search for a restaurant");
        CustomerButtons[1] = new JButton("view near restaurant");
        CustomerButtons[2] = new JButton("view deal");
        CustomerButtons[3] = new JButton("view Recent Orders");
        CustomerButtons[5] = new JButton("update your data");
        CustomerButtons[4] = new JButton("make review");
        CustomerButtons[6] = new JButton("Change Password");
        CustomerButtons[7] = new JButton("Contact Customer Service");
        CustomerButtons[8] = new JButton("Signout");
        CustomerButtons[9] = new JButton("Statistics");
        cp.setLayout(new GridLayout(CustomerButtons.length, 1));
        for (int i = 0; i < CustomerButtons.length; i++) {
            cp.add(CustomerButtons[i]);
            CustomerButtons[i].addActionListener(new ButtonListener());
        }
        addWindowListener(new ButtonListener());
    }
    private JButton[] WaiterButtons = new JButton[4];

    // Main Menu Of The Waiter
    public MainMenuGUI(waiter w) {
        setSize(400, 700);
        setTitle("Main Menu");
        cp.setLayout(new GridLayout(WaiterButtons.length, 1));
        WaiterButtons[0] = new JButton("update your data");
        WaiterButtons[1] = new JButton("view menu of the restaurant");
        WaiterButtons[2] = new JButton("Change Password");
        WaiterButtons[3] = new JButton("Signout");
        for (int i = 0; i < WaiterButtons.length; i++) {
            cp.add(WaiterButtons[i]);
            WaiterButtons[i].addActionListener(new ButtonListener());
        }
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
            // Update Waiter Data
            if (e.getSource().equals(WaiterButtons[0])) {
                UpdateDataGUI updateDataGUI = new UpdateDataGUI(Waiter);
                setVisible(false);
                updateDataGUI.setVisible(true);
            } // View Restaurant For Waiter
            else if (e.getSource().equals(WaiterButtons[1])) {
                nRArr.add(Waiter.r);
                menusGUI = new MenusGUI(0, true);
                setVisible(false);
                menusGUI.setVisible(true);
            } // Change Waiter Password
            else if (e.getSource().equals(WaiterButtons[2])) {
                JLabel a = new JLabel("Enter The New Password");
                JPasswordField b = new JPasswordField(25);
                JPanel c = new JPanel();
                c.setLayout(new GridLayout(2, 1));
                c.add(a);
                c.add(b);
                int x = JOptionPane.showConfirmDialog(null, c, "Change Password", 0);
                if (x == 0) {
                    if (b.getPassword().length >= 5) {
                        Waiter.pass = String.valueOf(b.getPassword());
                        WaiterHashmap.remove(Waiter.UserName);
                        WaiterHashmap.put(Waiter.UserName, Waiter);
                        JOptionPane.showMessageDialog(null, "Password Changed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 chars");
                    }
                }
            } // signout 
            else if (e.getSource().equals(WaiterButtons[3])
                    || e.getSource().equals(CustomerButtons[8])
                    || e.getSource().equals(AdminButtons[9])) {
                nRArr.clear();
                HomeGUI homeGUI = new HomeGUI();
                setVisible(false);
                homeGUI.setVisible(true);
            } // Update Customer Data
            else if (e.getSource().equals(CustomerButtons[5])) {
                UpdateDataGUI updateDataGUI = new UpdateDataGUI(Customer);
                setVisible(false);
                updateDataGUI.setVisible(true);
            } //Search For A resturant
            else if (e.getSource().equals(CustomerButtons[0])) {
                String s = JOptionPane.showInputDialog("Enter The Name Of the Restuarant");
                if (Customer.checkSearch(Allrest, s) == true) {
                    restaurant sr = Customer.searchRestaurant(Allrest, s);
                    menusGUI = new MenusGUI(sr);
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "This Is Ad GET " + Ads.itm.productName
                            + " For Only " + Ads.itm.price + " L.E Form " + Ads.RName);
                    menusGUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
            }//view Deals for Customer 
            else if (e.getSource().equals(CustomerButtons[2])) {
                DealsGUI dealsGUI = new DealsGUI();
                setVisible(false);
                JOptionPane.showMessageDialog(null, "This Is Ad GET " + Ads.itm.productName
                        + " For Only " + Ads.itm.price + " L.E Form " + Ads.RName);
                dealsGUI.setVisible(true);
            }//make a review 
            else if (e.getSource().equals(CustomerButtons[4])) {
                RestGUI restGUI = new RestGUI(true);
                setVisible(false);
                JOptionPane.showMessageDialog(null, "This Is Ad GET " + Ads.itm.productName
                        + " For Only " + Ads.itm.price + " L.E Form " + Ads.RName);
                restGUI.setVisible(true);
            }//view Near Restaurant and make order 
            else if (e.getSource().equals(CustomerButtons[1])) {
                restGUI = new RestGUI(0);
                setVisible(false);
                JOptionPane.showMessageDialog(null, "This Is Ad GET " + Ads.itm.productName
                        + " For Only " + Ads.itm.price + " L.E Form " + Ads.RName);
                restGUI.setVisible(true);
            }//view Recent Orders 
            else if (e.getSource().equals(CustomerButtons[3])) {
                RecentOrdersGUI recentOrdersGUI = new RecentOrdersGUI();
                setVisible(false);
                JOptionPane.showMessageDialog(null, "This Is Ad GET " + Ads.itm.productName
                        + " For Only " + Ads.itm.price + " L.E Form " + Ads.RName);
                recentOrdersGUI.setVisible(true);
            } // Change customer Password
            else if (e.getSource().equals(CustomerButtons[6])) {
                JLabel a = new JLabel("Enter The New Password");
                JPasswordField b = new JPasswordField(25);
                JPanel c = new JPanel();
                c.setLayout(new GridLayout(2, 1));
                c.add(a);
                c.add(b);
                int x = JOptionPane.showConfirmDialog(null, c, "Change Password", JOptionPane.OK_OPTION);
                if (x == 0) {
                    if (b.getPassword().length >= 5) {
                        Customer.pass = String.valueOf(b.getPassword());
                        CustomerHashmap.remove(Customer.UserName);
                        CustomerHashmap.put(Customer.UserName, Customer);
                        JOptionPane.showMessageDialog(null, "Password Changed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 chars");
                    }
                }
            } else if (e.getSource().equals(CustomerButtons[7])) {
                boolean connected = false;
                try {
                    ObjectInputStream ObjIn = new ObjectInputStream(
                            new FileInputStream(otlob.filepaths.ports));
                    AllPorts = (HashMap<Integer, Boolean>) ObjIn.readObject();
                    ObjIn.close();
                    if (AllPorts.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Avilable Customer Service Now");
                    } else {
                        for (int p : AllPorts.keySet()) {
                            System.out.println(p);
                            System.out.println(AllPorts.get(p));
                            if (AllPorts.get(p)) {
                                AllPorts.remove(p);
                                AllPorts.put(p, false);
                                ObjectOutputStream ObjOut = new ObjectOutputStream(
                                        new FileOutputStream(otlob.filepaths.ports));
                                ObjOut.writeObject(AllPorts);
                                ObjOut.close();
                                connected = true;
                                CustomerChatGUI customerChatGUI = new CustomerChatGUI(Customer.UserName, p);
                                setVisible(false);
                                customerChatGUI.setVisible(true);
                                break;
                            }
                        }
                        if (!connected) {
                            JOptionPane.showMessageDialog(null, "All Customer Service Are busy Now");
                        }
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "No Avilable Customer Service Now");
                }
                
            } else if (e.getSource().equals(CustomerButtons[9])) {
                StatisticsGUI statisticsGUI = new StatisticsGUI();
                setVisible(false);
                
            } // Add Rest By Admin 
            else if (e.getSource().equals(AdminButtons[0])) {
                RestGUI Addrestaur = new RestGUI("a");
                setVisible(false);
                Addrestaur.setVisible(true);
            }// Add Deal By Admin 
            else if (e.getSource().equals(AdminButtons[1])) {
                AddDealGUI addDealGUI = new AddDealGUI();
                setVisible(false);
                addDealGUI.setVisible(true);
            }// Edit Restaurant By Admin 
            else if (e.getSource().equals(AdminButtons[2])) {
                RestGUI restGUI = new RestGUI("s", "s");
                setVisible(false);
                restGUI.setVisible(true);
            } //remove User By Admin 
            else if (e.getSource().equals(AdminButtons[3])) {
                String S = JOptionPane.showInputDialog("Please Enter Customer UserName");
                if (Admin.removeCustomer(S)) {
                    JOptionPane.showMessageDialog(null, "Customer Is removed");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer Not Found");
                }
            }// make ads 
            else if (e.getSource().equals(AdminButtons[4])) {
                RestGUI restGUI = new RestGUI();
                setVisible(false);
                restGUI.setVisible(true);
            } // Update Admin Data
            else if (e.getSource().equals(AdminButtons[5])) {
                UpdateDataGUI updateDataGUI = new UpdateDataGUI(Admin);
                setVisible(false);
                updateDataGUI.setVisible(true);
            } // remove Waiter By Admin
            else if (e.getSource().equals(AdminButtons[6])) {
                String S = JOptionPane.showInputDialog("Please Enter Waiter UserName");
                if (Admin.removeWaiter(S)) {
                    JOptionPane.showMessageDialog(null, "Waiter Is removed");
                } else {
                    JOptionPane.showMessageDialog(null, "Waiter Not Found");
                }
            } // remove Deal By Admin
            else if (e.getSource().equals(AdminButtons[7])) {
                String S = JOptionPane.showInputDialog("Please Enter Deal Code");
                if (Admin.removeDeal(S)) {
                    JOptionPane.showMessageDialog(null, "Deal Is removed");
                } else {
                    JOptionPane.showMessageDialog(null, "Deal Not Found");
                }
            }// Change admin Password
            else if (e.getSource().equals(AdminButtons[8])) {
                JLabel a = new JLabel("Enter The New Password");
                JPasswordField b = new JPasswordField(25);
                JPanel c = new JPanel();
                c.setLayout(new GridLayout(2, 1));
                c.add(a);
                c.add(b);
                int x = JOptionPane.showConfirmDialog(null, c, "Change Password", 0);
                if (x == 0) {
                    if (b.getPassword().length >= 5) {
                        Admin.pass = String.valueOf(b.getPassword());
                        AdminHashmap.remove(Admin.UserName);
                        AdminHashmap.put(Admin.UserName, Admin);
                        JOptionPane.showMessageDialog(null, "Password Changed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 chars");
                    }
                }
            } else if (e.getSource().equals(AdminButtons[10])) {
                ObjectOutputStream ObjOut = null;
                try {
                    String x = JOptionPane.showInputDialog(null, "Enter Place Name");
                    if (!x.equals("")) {
                        Places.add(x);
                        ObjOut = new ObjectOutputStream(
                                new FileOutputStream(otlob.filepaths.placesArray));
                        ObjOut.writeObject(Places);
                        ObjOut.close();
                        JOptionPane.showMessageDialog(null, "Place Added");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
            
        }
    }
    
}
