/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author ASUS
 */
public class Server extends JFrame implements Runnable {

    private ServerSocket Mysocket;
    private Socket clientsocket;
    public JLabel allMessage;
    private Container cp = getContentPane();
    int p;

    public Server(int port) throws IOException {
        setSize(400, 700);
        setTitle("Screen");
        p = port;
        Mysocket = new ServerSocket(port);
        cp.setLayout(new FlowLayout());
        allMessage = new JLabel("<html>All Orders here");
        cp.add(allMessage);
        addWindowListener(new ButtonListener());

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
            dispose();
            System.exit(0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
          
        }

    }
}
