package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static otlob.OTLOB.*;
import otlob.Order;
import otlob.cart;
import otlob.restaurant;

/**
 *
 * @author mark
 */
public class HomeGUI extends JFrame {

    private JButton login = new JButton("Login");
    private JButton signUp = new JButton("SignUp");
    private JRadioButton CustomerRButton = new JRadioButton("Customer", true);
    private JRadioButton AdminRButton = new JRadioButton("Admin", false);
    private JRadioButton WaiterRButton = new JRadioButton("Waiter", false);
    private ButtonGroup gr = new ButtonGroup();
    private Container cp = getContentPane();
    private JPanel ChooseUser = new JPanel(new FlowLayout());
    private JPanel ChooseLoginOrSignup = new JPanel(new FlowLayout());
    private JPanel loginPanel = new JPanel(new FlowLayout());
    private JLabel u = new JLabel("UserName");
    private JTextField userName = new JTextField(25);
    private JLabel p = new JLabel("Password");
    private JPasswordField PassWord = new JPasswordField(25);
    private int LoginCheck = 0;
    
    public HomeGUI() {
        setSize(400, 700);
        setTitle("Aklny");
        gr.add(AdminRButton);
        gr.add(CustomerRButton);
        gr.add(WaiterRButton);
        ChooseUser.add(CustomerRButton);
        ChooseUser.add(AdminRButton);
        ChooseUser.add(WaiterRButton);
        cp.add(ChooseUser, BorderLayout.NORTH);
        loginPanel.add(u);
        loginPanel.add(userName);
        loginPanel.add(p);
        loginPanel.add(PassWord);
        cp.add(loginPanel, BorderLayout.CENTER);
        ChooseLoginOrSignup.add(login);
        ChooseLoginOrSignup.add(signUp);
        cp.add(ChooseLoginOrSignup, BorderLayout.SOUTH);
        login.addActionListener(new ButtonListener());
        signUp.addActionListener(new ButtonListener());
        AdminRButton.addActionListener(new ButtonListener());
        WaiterRButton.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
    }

    public class ButtonListener extends WindowAdapter implements ActionListener {

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
            if (e.getSource().equals(AdminRButton)) {
                JLabel a = new JLabel("Enter The Main Password");
                JPasswordField b = new JPasswordField(25);
                JPanel c = new JPanel();
                c.setLayout(new GridLayout(2, 1));
                c.add(a);
                c.add(b);
                JOptionPane.showMessageDialog(null, c, "Main Password", 0);
                String s = String.valueOf(b.getPassword());
                if (s.equals("123")) {
                    AdminRButton.setSelected(true);
                } else {
                    CustomerRButton.setSelected(true);
                }
            } else if (e.getSource().equals(WaiterRButton)) {
                JLabel a = new JLabel("Enter The Main Password");
                JPasswordField b = new JPasswordField(25);
                JPanel c = new JPanel();
                c.setLayout(new GridLayout(2, 1));
                c.add(a);
                c.add(b);
                JOptionPane.showMessageDialog(null, c, "Main Password", 0);
                String s = String.valueOf(b.getPassword());
                if (s.equals("123")) {
                    WaiterRButton.setSelected(true);
                } else {
                    CustomerRButton.setSelected(true);
                }
            }
            if (e.getSource().equals(signUp)) {
                setVisible(false);
                if (AdminRButton.isSelected() == true) {
                    SignUpGUI signUpGUI = new SignUpGUI(Admin);
                    signUpGUI.setVisible(true);
                } else if (WaiterRButton.isSelected() == true) {
                    SignUpGUI signUpGUI = new SignUpGUI(Waiter);
                    signUpGUI.setVisible(true);
                } else {
                    SignUpGUI signUpGUI = new SignUpGUI(Customer);
                    signUpGUI.setVisible(true);
                }

            } else if (e.getSource().equals(login)) {
                String s = userName.getText();
                String s1 = String.valueOf(PassWord.getPassword());
                if (CustomerRButton.isSelected() == true) {
                    if (s.isEmpty() || s1.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all the data");
                    } else if (s.length() < 3) {
                        JOptionPane.showMessageDialog(null, "UserName Must Be Bigger Than 3 Letters");
                    } else if (s1.length() < 5) {
                        JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 chars");
                    } else {
                        LoginCheck = Customer.login(s, s1);
                        if (LoginCheck == -2) {
                            JOptionPane.showMessageDialog(null, "not Already user");
                        } else if (LoginCheck == -1) {
                            JOptionPane.showMessageDialog(null, "WrongPassword");
                        } else {
                            Customer = CustomerHashmap.get(s);
                            Customer.viewNearRestaurants();
                            // try to fill orders ArrayList
                            ObjectInputStream ObjIn;
                            ObjectOutputStream ObjOut;
                            try {
                                ObjIn = new ObjectInputStream(
                                        new FileInputStream(otlob.filepaths.customersOrdersFolder
                                                + Customer.UserName + ".bin"));
                                ordersList = (ArrayList<Order>) ObjIn.readObject();
                                ObjIn.close();
                            } catch (ClassNotFoundException | IOException ex) {
                                try {
                                    ObjOut = new ObjectOutputStream(
                                            new FileOutputStream(otlob.filepaths.customersOrdersFolder + Customer.UserName + ".bin"));
                                    ObjOut.writeObject(ordersList);
                                    ObjOut.close();
                                } catch (IOException ex1) {
                                    Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                            MainMenuGUI mainMenu = new MainMenuGUI(Customer);
                            setVisible(false);
                            mainMenu.setVisible(true);
                        }
                    }
                } else if (AdminRButton.isSelected() == true) {
                    if (s.isEmpty() || s1.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all the data",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else if (s.length() < 3) {
                        JOptionPane.showMessageDialog(null, "UserName Must Be Bigger Than 3 Letters");
                    } else if (s1.length() < 5) {
                        JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 chars");
                    } else {
                        LoginCheck = Admin.login(s, s1);
                        if (LoginCheck == -2) {
                            JOptionPane.showMessageDialog(null, "not Already user");
                        } else if (LoginCheck == -1) {
                            JOptionPane.showMessageDialog(null, "WrongPassword");
                        } else {
                            Admin = AdminHashmap.get(s);
                            MainMenuGUI mainMenu = new MainMenuGUI(Admin);
                            setVisible(false);
                            mainMenu.setVisible(true);
                        }
                    }
                } else if (WaiterRButton.isSelected() == true) {
                    if (s.isEmpty() || s1.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all the data",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else if (s.length() < 3) {
                        JOptionPane.showMessageDialog(null, "UserName Must Be Bigger Than 3 Letters");
                    } else if (s1.length() < 5) {
                        JOptionPane.showMessageDialog(null, "Password Must Be Bigger Than 5 chars");
                    } else {
                        LoginCheck = Waiter.login(s, s1);
                        if (LoginCheck == -2) {
                            JOptionPane.showMessageDialog(null, "not Already user");
                        } else if (LoginCheck == -1) {
                            JOptionPane.showMessageDialog(null, "WrongPassword");
                        } else {
                            Waiter = WaiterHashmap.get(s);
                            for(restaurant x: Allrest)
                            {
                                if (x.name.equals(Waiter.Rname))
                                {
                                    Waiter.r = x;
                                }
                            }
                            
                            MainMenuGUI mainMenu = new MainMenuGUI(Waiter);
                            setVisible(false);
                            mainMenu.setVisible(true);
                        }
                    }
                }
            }
        }

    }
}
