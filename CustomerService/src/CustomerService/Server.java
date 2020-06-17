/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerService;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class Server extends JFrame implements Runnable {

    private ServerSocket Mysocket;
    private Socket clientsocket;
    private JTextField MessageTextField;
    public JLabel allMessage;
    private JButton Send = new JButton("Send");
    private Container cp = getContentPane();
    int p;

    public Server(int port) throws IOException {
        setSize(400, 700);
        setTitle("Customer Service");
        p = port;
        Mysocket = new ServerSocket(port);
        setLayout(new BorderLayout());
        JPanel j = new JPanel();
        j.setLayout(new FlowLayout());
        allMessage = new JLabel("<html>All messages here");
        MessageTextField = new JTextField(25);
        cp.add(allMessage, BorderLayout.CENTER);
        j.add(MessageTextField);
        j.add(Send);
        Send.addActionListener(new ButtonListener());
        addWindowListener(new ButtonListener());
        cp.add(j, BorderLayout.SOUTH);
    }

    public void run() {
        try {
            while (true) {
                clientsocket = Mysocket.accept();
                ConnectionThread ct = new ConnectionThread(clientsocket, allMessage);
                ct.start();
                Thread.sleep(1000);
            }
        } catch (InterruptedException | IOException e) {
        }
    }

    private class ButtonListener extends WindowAdapter implements ActionListener {

        @Override
        public void windowClosing(WindowEvent e) {
            
            try {
                CustomerService.AllPorts.remove(p);
                ObjectOutputStream ObjOut = new ObjectOutputStream(
                        new FileOutputStream(filespaths.ports));
                ObjOut.writeObject(CustomerService.AllPorts);
                ObjOut.close();
                System.exit(0);
            } catch (IOException ex) {

            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource().equals(Send)) {
                    OutputStream os = clientsocket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    pw.println(MessageTextField.getText());
                    allMessage.setText(allMessage.getText() + "<br>" + "You: " + MessageTextField.getText());
                    MessageTextField.setText(null);
                }

            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }
}
