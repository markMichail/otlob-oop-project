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
import javax.swing.JPasswordField;
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
public class SignUpGUI extends JFrame {

    private String SignupCheck = "";
    private Container cp = getContentPane();
    private JLabel FirstnameLabel = new JLabel("     First Name");
    private JTextField firstName = new JTextField(20);
    private JLabel LastnameLabel = new JLabel("    Last Name");
    private JTextField lastName = new JTextField(20);
    private JLabel userNameLabel = new JLabel("     UserName");
    private JTextField userName = new JTextField(20);
    private JLabel passWordLabel = new JLabel("     Password");
    private JPasswordField password = new JPasswordField(20);
    private JLabel phonenumberLabel = new JLabel("Phone Number");
    private JTextField phoneNumber = new JTextField(19);
    private JLabel phonenumberLabel2 = new JLabel("Backup Number");
    private JTextField phoneNumber2 = new JTextField(19);
    private JLabel EmailLabel = new JLabel("           E-Mail");
    private JTextField email = new JTextField(25);
    private JButton createAdmin = new JButton("Create account");
    private JButton Back = new JButton("Back");

    public SignUpGUI(admin Adminn) {
        setSize(400, 700);
        setTitle("SignUp");
        cp.setLayout(new FlowLayout());
        cp.add(FirstnameLabel);
        cp.add(firstName);
        cp.add(LastnameLabel);
        cp.add(lastName);
        cp.add(userNameLabel);
        cp.add(userName);
        cp.add(passWordLabel);
        cp.add(password);
        cp.add(phonenumberLabel);
        cp.add(phoneNumber);
        cp.add(phonenumberLabel2);
        cp.add(phoneNumber2);
        cp.add(EmailLabel);
        cp.add(email);
        cp.add(createAdmin);
        cp.add(Back);
        addWindowListener(new ButtonListener());
        createAdmin.addActionListener(new ButtonListener());
        Back.addActionListener(new ButtonListener());
    }

    private JLabel street = new JLabel("Enter street name    ");
    private JTextField streetName = new JTextField(11);
    private JLabel buildingNo = new JLabel("Enter building number   ");
    private JTextField b = new JTextField(2);
    private JLabel floor = new JLabel("Enter floor number   ");
    private JTextField f = new JTextField(2);
    private JLabel app = new JLabel("Enter appartment no.    ");
    private JLabel area = new JLabel("choose your area ");
    private JTextField p = new JTextField(2);
    private JComboBox areas = new JComboBox();
    private JButton createCustomer = new JButton("create account");

    public SignUpGUI(customer Customerr) {
        setSize(400, 700);
        setTitle("SignUp");
        for (int i = 0; i < OTLOB.Places.size(); i++) {
            areas.addItem(OTLOB.Places.get(i));
        }
        cp.setLayout(new FlowLayout());
        cp.add(FirstnameLabel);
        cp.add(firstName);
        cp.add(LastnameLabel);
        cp.add(lastName);
        cp.add(userNameLabel);
        cp.add(userName);
        cp.add(passWordLabel);
        cp.add(password);
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
        cp.add(createCustomer);
        cp.add(Back);
        addWindowListener(new ButtonListener());
        createCustomer.addActionListener(new ButtonListener());
        Back.addActionListener(new ButtonListener());
    }

    private JLabel restaurant = new JLabel("choose your restaurant ");
    private JComboBox<String> r = new JComboBox();
    private JButton createWaiter = new JButton("create account");

    public SignUpGUI(waiter Waiterr) {
        setSize(400, 700);
        setTitle("SignUp");
        for (int i = 0; i < Allrest.size(); i++) {
            r.addItem(Allrest.get(i).name);
        }
        cp.setLayout(new FlowLayout());
        cp.add(FirstnameLabel);
        cp.add(firstName);
        cp.add(LastnameLabel);
        cp.add(lastName);
        cp.add(userNameLabel);
        cp.add(userName);
        cp.add(passWordLabel);
        cp.add(password);
        cp.add(phonenumberLabel);
        cp.add(phoneNumber);
        cp.add(phonenumberLabel2);
        cp.add(phoneNumber2);
        cp.add(EmailLabel);
        cp.add(email);
        cp.add(restaurant);
        cp.add(r);
        cp.add(createWaiter);
        cp.add(Back);
        addWindowListener(new ButtonListener());
        createWaiter.addActionListener(new ButtonListener());
        Back.addActionListener(new ButtonListener());
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

            boolean allValide = true;
            if (e.getSource().equals(createAdmin)) {
                if (firstName.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || userName.getText().isEmpty()
                        || String.valueOf(password.getPassword()).isEmpty()
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
                } else if (userName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "userName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (String.valueOf(password.getPassword()).length() < 5) {
                    JOptionPane.showMessageDialog(null, "password Must Be Bigger Than 5 Letters");
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
                    Admin.UserName = userName.getText();
                    Admin.pass = String.valueOf(password.getPassword());
                    Admin.phoneNumber.num = phoneNumber.getText();
                    Admin.phoneNumber.backupNum = phoneNumber2.getText();
                    Admin.email = email.getText();
                    SignupCheck = Admin.signUp();
                    if (SignupCheck.equals("signUp successful")) {
                        JOptionPane.showMessageDialog(null, SignupCheck);
                        HomeGUI homeGUI = new HomeGUI();
                        setVisible(false);
                        homeGUI.setVisible(true);
                    } else {
                        // wrong password
                        JOptionPane.showMessageDialog(null, SignupCheck);
                    }
                }
            } else if (e.getSource().equals(createCustomer)) {

                if (firstName.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || userName.getText().isEmpty()
                        || String.valueOf(password.getPassword()).isEmpty()
                        || phoneNumber.getText().isEmpty()
                        || phoneNumber2.getText().isEmpty()
                        || email.getText().isEmpty()
                        || streetName.getText().isEmpty()
                        || b.getText().isEmpty()
                        || f.getText().isEmpty()
                        || p.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill All the data");
                    allValide = false;
                } else if (firstName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "firstName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (lastName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "lastName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (userName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "userName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (String.valueOf(password.getPassword()).length() < 5) {
                    JOptionPane.showMessageDialog(null, "password Must Be Bigger Than 5 Letters");
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
                } if (allValide){
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
                    Pattern p = Pattern.compile(ePattern);
                    Matcher m = p.matcher(email.getText());
                    if (!m.matches() && allValide) {
                        JOptionPane.showMessageDialog(null, "Check your email");
                        allValide = false;
                    }
                }
                if (allValide) {
                    Customer.fullName.firstName = firstName.getText();
                    Customer.fullName.lastName = lastName.getText();
                    Customer.UserName = userName.getText();
                    Customer.pass = String.valueOf(password.getPassword());
                    Customer.phoneNumber.num = phoneNumber.getText();
                    Customer.phoneNumber.backupNum = phoneNumber2.getText();
                    Customer.email = email.getText();
                    Customer.Address.streetName = streetName.getText();
                    Customer.Address.BuildingNum = b.getText();
                    Customer.Address.floor = Integer.valueOf(f.getText());
                    Customer.Address.appNum = Integer.valueOf(p.getText());
                    Customer.Address.area = String.valueOf(areas.getSelectedItem());
                    SignupCheck = Customer.signUp();
                    if (SignupCheck.equals("signUp successful")) {
                        JOptionPane.showMessageDialog(null, SignupCheck);
                        HomeGUI homeGUI = new HomeGUI();
                        setVisible(false);
                        homeGUI.setVisible(true);
                    } else {
                        // wrong password
                        JOptionPane.showMessageDialog(null, SignupCheck);
                    }
                }
            } else if (e.getSource().equals(createWaiter)) {
                if (firstName.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || userName.getText().isEmpty()
                        || String.valueOf(password.getPassword()).isEmpty()
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
                } else if (userName.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "UserName Must Be Bigger Than 3 Letters");
                    allValide = false;
                } else if (String.valueOf(password.getPassword()).length() < 5) {
                    JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 Letters");
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
                    Waiter.UserName = userName.getText();
                    Waiter.pass = String.valueOf(password.getPassword());
                    Waiter.phoneNumber.num = phoneNumber.getText();
                    Waiter.phoneNumber.backupNum = phoneNumber2.getText();
                    Waiter.email = email.getText();
                    Waiter.Rname = Allrest.get(r.getSelectedIndex()).name;
                    SignupCheck = Waiter.signUp();
                    if (SignupCheck.equals("signUp successful")) {
                        JOptionPane.showMessageDialog(null, SignupCheck);
                        HomeGUI homeGUI = new HomeGUI();
                        setVisible(false);
                        homeGUI.setVisible(true);
                    } else {
                        // wrong password
                        JOptionPane.showMessageDialog(null, SignupCheck);
                    }
                }
            } else if (e.getSource().equals(Back)) {
                HomeGUI homeGUI = new HomeGUI();
                setVisible(false);
                homeGUI.setVisible(true);
            }
        }

    }
}