/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author ASUS
 */
public class ConnectionThread extends Thread {

    private Socket s;
    private InputStream is;
    private OutputStream os;
    private BufferedReader fromclient;
    private PrintWriter toclient;
    private JLabel Message;

    public ConnectionThread(Socket s, JLabel Msg) throws IOException {
        this.s = s;
        this.Message = Msg;
        is = s.getInputStream();
        os = s.getOutputStream();
    }

    public void run() {
        fromclient = new BufferedReader(new InputStreamReader(is));
        toclient = new PrintWriter(os, true);
        while (true) {
            try {
                String NewMessage = fromclient.readLine();
                Message.setText(Message.getText() + "<br>" + NewMessage);
                Thread.sleep(100);
            } catch (IOException ex) {
                Message.setText(Message.getText() + "<br>" + "Disconected");
                break;
            } catch (InterruptedException ex) {
                Message.setText(Message.getText() + "<br>" + "Disconected");
                break;
            }

        }

    }

}
