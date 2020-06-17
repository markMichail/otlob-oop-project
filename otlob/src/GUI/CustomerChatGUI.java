/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import otlob.OTLOB;
import static otlob.OTLOB.*;

/**
 *
 * @author ASUS
 */
public class CustomerChatGUI extends JFrame {

    private JLabel AllMsg;
    private JTextField MessageTextField = new JTextField(20);
    private JButton Back = new JButton("Back");
    private JButton Send = new JButton("Send");
    private Container cp = getContentPane();
    private PrintWriter pw;
    InputStream is;
    OutputStream os;
    Socket toFromserver = null;
    String clientName;
    int port;

    public CustomerChatGUI(String Name, int port) throws IOException {

        this.clientName = Name;
        setSize(400, 700);
        setTitle("Chat");
        AllMsg = new JLabel("<html>Recived Message");
        setLayout(new BorderLayout());
        this.port = port;
        JPanel j = new JPanel();
        j.setLayout(new FlowLayout());
        cp.add(AllMsg, BorderLayout.CENTER);
        j.add(MessageTextField);
        j.add(Send);
        j.add(Back);
        Back.addActionListener(new ButtonListener());
        Send.addActionListener(new ButtonListener());
        cp.add(j, BorderLayout.SOUTH);
        addWindowListener(new ButtonListener());
        try {
            toFromserver = new Socket(otlob.filepaths.customerChatIP, port);
            is = toFromserver.getInputStream();
            os = toFromserver.getOutputStream();
            updategui t = new updategui();
            t.start();

        } catch (IOException ex) {

        }

    }

    private class ButtonListener extends WindowAdapter implements ActionListener {

        @Override
        public void windowClosing(WindowEvent e) {
            try {
                ObjectOutputStream ObjOut;
                AllPorts.remove(port);
                AllPorts.put(port, true);
                ObjOut = new ObjectOutputStream(
                        new FileOutputStream(otlob.filepaths.ports));
                ObjOut.writeObject(AllPorts);
                ObjOut.close();

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
            if (e.getSource().equals(Send)) {
                pw = new PrintWriter(os, true);
                pw.println(clientName + ": " + MessageTextField.getText());
                AllMsg.setText(AllMsg.getText() + "<br>" + "You: " + MessageTextField.getText());
                MessageTextField.setText(null);

            } else if (e.getSource().equals(Back)) {
                dispose();
                System.out.println(port);
                AllPorts.remove(port);
                AllPorts.put(port, true);
                try {
                    ObjectOutputStream ObjOut = new ObjectOutputStream(
                            new FileOutputStream(otlob.filepaths.ports));
                    ObjOut.writeObject(AllPorts);
                    ObjOut.close();
                } catch (Exception z) {
                }
                MainMenuGUI mainMenuGUI = new MainMenuGUI(OTLOB.Customer);
                setVisible(false);
                mainMenuGUI.setVisible(true);
            }
        }

    }

    private class updategui extends Thread {

        public void run() {
            while (true) {
                System.out.println(toFromserver.isConnected());
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                String Message = null;
                try {
                    Message = bf.readLine();
                    AllMsg.setText(AllMsg.getText() + "<br>" + "Customer Service: " + Message);
                    Thread.sleep(100);
                } catch (IOException ex) {
                    AllMsg.setText(AllMsg.getText() + "<br>" + "Customer Service: Disconected");
                    break;
                } catch (InterruptedException ex) {
                    Logger.getLogger(CustomerChatGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            }
        }
    }
}
