/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import otlob.OTLOB;
import otlob.admin;
import otlob.customer;
import otlob.waiter;
import static otlob.OTLOB.*;

/**
 *
 * @author mark
 */
public class UpdateDataGUI extends JFrame {

    private Container cp = getContentPane();
    private JLabel FirstnameLabel = new JLabel("Enter Your First Name");
    private JTextField firstName = new JTextField(15);
    private JLabel LastnameLabel = new JLabel("Enter Your Last Name");
    private JTextField lastName = new JTextField(15);
    private JLabel phonenumberLabel = new JLabel("Enter phone number        ");
    private JTextField phoneNumber = new JTextField(11);
    private JLabel phonenumberLabel2 = new JLabel("Enter backup number       ");
    private JTextField phoneNumber2 = new JTextField(11);
    private JLabel EmailLabel = new JLabel("Enter Your Mail");
    private JTextField email = new JTextField(25);
    private JButton updateAdmin = new JButton("Update account");
    private JButton backAdmin = new JButton("Back");
    private boolean allValide = true;

    public UpdateDataGUI(admin Admin) {
        setSize(400, 700);
        setTitle("Update");
        cp.setLayout(new FlowLayout());
        cp.add(FirstnameLabel);
        cp.add(firstName);
        cp.add(LastnameLabel);
        cp.add(lastName);
        cp.add(phonenumberLabel);
        cp.add(phoneNumber);
        cp.add(phonenumberLabel2);
        cp.add(phoneNumber2);
        cp.add(EmailLabel);
        cp.add(email);
        cp.add(updateAdmin);
        cp.add(backAdmin);
        backAdmin.addActionListener(new ButtonListener());
        updateAdmin.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    private JLabel street = new JLabel("Enter street name    ");
    private JTextField streetName = new JTextField(11);
    private JLabel buildingNo = new JLabel("Enter building number   ");
    private JTextField b = new JTextField(2);
    private JLabel floor = new JLabel("Enter floor number   ");
    private JTextField f = new JTextField(2);
    private JLabel app = new JLabel("Enter appartment no.    ");
    private JTextField p = new JTextField(2);
    private JLabel area = new JLabel("choose your area ");
    private JButton create = new JButton("create account");
    private JComboBox areas = new JComboBox();
    private JButton updateCustomer = new JButton("Update account");
    private JButton backCustomer = new JButton("Back");

    public UpdateDataGUI(customer Customer) {
        setSize(400, 700);
        setTitle("Update");
        for (int i = 0; i < OTLOB.Places.size(); i++) {
            areas.addItem(OTLOB.Places.get(i));
        }
        cp.setLayout(new FlowLayout());
        cp.add(FirstnameLabel);
        cp.add(firstName);
        cp.add(LastnameLabel);
        cp.add(lastName);
        cp.add(phonenumberLabel);
        cp.add(phoneNumber);
        cp.add(phonenumberLabel2);
        cp.add(phoneNumber2);
        cp.add(EmailLabel);
        cp.add(email);
        cp.add(street);
        cp.add(streetName);
        cp.add(buildingNo);
        cp.add(b);
        cp.add(floor);
        cp.add(f);
        cp.add(app);
        cp.add(p);
        cp.add(area);
        cp.add(areas);
        cp.add(updateCustomer);
        cp.add(backCustomer);
        backCustomer.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
        updateCustomer.addActionListener(new ButtonListener());
    }

    private JLabel restaurant = new JLabel("choose your restaurant ");
    private JButton updateWaiter = new JButton("create account");
    private JComboBox r = new JComboBox();
    private JButton backWaiter = new JButton("Back");

    public UpdateDataGUI(waiter Waiter) {
        setSize(400, 700);
        setTitle("Update");
        for (int i = 0; i < Allrest.size(); i++) {
            r.addItem(Allrest.get(i).name);
        }
        cp.setLayout(new FlowLayout());
        cp.add(FirstnameLabel);
        cp.add(firstName);
        cp.add(LastnameLabel);
        cp.add(lastName);
        cp.add(phonenumberLabel);
        cp.add(phoneNumber);
        cp.add(phonenumberLabel2);
        cp.add(phoneNumber2);
        cp.add(EmailLabel);
        cp.add(email);
        cp.add(restaurant);
        cp.add(r);
        cp.add(updateWaiter);
        cp.add(backWaiter);
        backWaiter.addActionListener(new ButtonListener());
        updateWaiter.addActionListener(new ButtonListener());
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

            if (e.getSource().equals(updateAdmin)) {
                allValide = true;
                if (firstName.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || phoneNumber.getText().isEmpty()
                        || phoneNumber2.getText().isEmpty()
                        || email.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                    allValide = false;
                } else if (firstName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "firstName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (lastName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "lastName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (phoneNumber.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "phoneNumber Must Be 11 numbers");
                    allValide = false;
                } else if (phoneNumber2.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "backup phoneNumber Must Be 11 numbers");
                    allValide = false;
                } else if (phoneNumber2.getText().equals(phoneNumber.getText())) {
                    JOptionPane.showMessageDialog(null, "phoneNumber & backup phoneNumber must be diffrent");
                    allValide = false;
                } else {
                    try {
                        Integer.parseInt(phoneNumber.getText());

                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null, "phoneNumber Must Be numbers only");
                        allValide = false;
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(phoneNumber2.getText());
                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "backup phoneNumber Must Be numbers only");
                            allValide = false;
                        }
                    }
                    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                    Pattern p = Pattern.compile(ePattern);
                    Matcher m = p.matcher(email.getText());
                    if (!m.matches() && allValide) {
                        JOptionPane.showMessageDialog(null, "Check your email");
                        allValide = false;
                    }
                }
                if (allValide) {

                    Admin.fullName.firstName = firstName.getText();
                    Admin.fullName.lastName = lastName.getText();
                    Admin.phoneNumber.num = phoneNumber.getText();
                    Admin.phoneNumber.backupNum = phoneNumber2.getText();
                    Admin.email = email.getText();
                    JOptionPane.showMessageDialog(null, Admin.updatedata());
                    MainMenuGUI mainMenuGUI = new MainMenuGUI(Admin);
                    setVisible(false);
                    mainMenuGUI.setVisible(true);
                }
            } else if (e.getSource().equals(updateCustomer)) {
                allValide = true;
                if (firstName.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || phoneNumber.getText().isEmpty()
                        || phoneNumber2.getText().isEmpty()
                        || email.getText().isEmpty()
                        || streetName.getText().isEmpty()
                        || b.getText().isEmpty()
                        || f.getText().isEmpty()
                        || p.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                } else if (firstName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "firstName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (lastName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "lastName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (phoneNumber.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "phoneNumber Must Be 11 numbers");
                    allValide = false;
                } else if (phoneNumber2.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "backup phoneNumber Must Be 11 numbers");
                    allValide = false;
                } else if (phoneNumber2.getText().equals(phoneNumber.getText())) {
                    JOptionPane.showMessageDialog(null, "phoneNumber & backup phoneNumber must be diffrent");
                    allValide = false;
                } else {
                    if (allValide) {
                        try {
                            Integer.parseInt(phoneNumber.getText());

                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "phoneNumber Must Be numbers only");
                            allValide = false;
                        }
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(f.getText());

                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "Floor must be numbers only");
                            allValide = false;
                        }
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(p.getText());

                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "App Number Must Be numbers only");
                            allValide = false;
                        }
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(b.getText());

                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "phoneNumber Must Be numbers only");
                            allValide = false;
                        }
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(phoneNumber2.getText());
                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "backup phoneNumber Must Be numbers only");
                            allValide = false;
                        }
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(f.getText());
                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "Floor Must Be numbers only");
                            allValide = false;
                        }
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(p.getText());
                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "App Number Must Be numbers only");
                            allValide = false;
                        }
                    }
                    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                    Pattern pp = Pattern.compile(ePattern);
                    Matcher m = pp.matcher(email.getText());
                    if (!m.matches() && allValide) {
                        JOptionPane.showMessageDialog(null, "Check your email");
                        allValide = false;
                    }
                }

                if (allValide) {
                    Customer.fullName.firstName = firstName.getText();
                    Customer.fullName.lastName = lastName.getText();
                    Customer.phoneNumber.num = phoneNumber.getText();
                    Customer.phoneNumber.backupNum = phoneNumber2.getText();
                    Customer.email = email.getText();
                    Customer.Address.streetName = streetName.getText();
                    Customer.Address.BuildingNum = b.getText();
                    Customer.Address.floor = Integer.valueOf(f.getText());
                    Customer.Address.appNum = Integer.valueOf(p.getText());
                    Customer.Address.area = String.valueOf(areas.getSelectedItem());
                    JOptionPane.showMessageDialog(null, Customer.updatedata());
                    MainMenuGUI mainMenuGUI = new MainMenuGUI(Customer);
                    setVisible(false);
                    mainMenuGUI.setVisible(true);
                }
            } else if (e.getSource().equals(updateWaiter)) {
                allValide = true;
                if (firstName.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || phoneNumber.getText().isEmpty()
                        || phoneNumber2.getText().isEmpty()
                        || email.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                    allValide = false;
                } else if (firstName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "First Name Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (lastName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "Last Name Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (phoneNumber.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "PhoneNumber Must Be 11 numbers");
                    allValide = false;
                } else if (phoneNumber2.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "Backup PhoneNumber Must Be 11 numbers");
                    allValide = false;
                } else if (phoneNumber2.getText().equals(phoneNumber.getText())) {
                    JOptionPane.showMessageDialog(null, "PhoneNumber & Backup PhoneNumber Must Be Diffrent");
                    allValide = false;
                } else {
                    try {
                        Integer.parseInt(phoneNumber.getText());

                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null, "PhoneNumber Must Be Numbers Only");
                        allValide = false;
                    }
                    if (allValide) {
                        try {
                            Integer.parseInt(phoneNumber2.getText());
                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "Backup PhoneNumber Must Be Numbers Only");
                            allValide = false;
                        }
                    }
                    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                    Pattern p = Pattern.compile(ePattern);
                    Matcher m = p.matcher(email.getText());
                    if (!m.matches() && allValide) {
                        JOptionPane.showMessageDialog(null, "Check your email");
                        allValide = false;
                    }
                }
                if (allValide) {
                    Waiter.fullName.firstName = firstName.getText();
                    Waiter.fullName.lastName = lastName.getText();
                    Waiter.phoneNumber.num = phoneNumber.getText();
                    Waiter.phoneNumber.backupNum = phoneNumber2.getText();
                    Waiter.email = email.getText();
                    Waiter.Rname = Allrest.get(r.getSelectedIndex()).name;;
                    JOptionPane.showMessageDialog(null, Waiter.updatedata());
                    MainMenuGUI mainMenuGUI = new MainMenuGUI(Waiter);
                    setVisible(false);
                    mainMenuGUI.setVisible(true);
                }
            } else if (e.getSource().equals(backAdmin)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Admin);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            } else if (e.getSource().equals(backCustomer)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Customer);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            } else if (e.getSource().equals(backWaiter)) {
                MainMenuGUI mainMenuGUI = new MainMenuGUI(Waiter);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            }
        }

    }
}
