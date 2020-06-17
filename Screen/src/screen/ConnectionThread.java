/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JLabel;

/**
 *
 * @author ASUS
 */
public class ConnectionThread extends Thread {

    private Socket s;
    private InputStream is;
    private BufferedReader fromclient;
    private PrintWriter toclient;
    private JLabel Message;

    public ConnectionThread(Socket s, JLabel Msg) throws IOException {
        this.s = s;
        this.Message = Msg;
        is = s.getInputStream();
    }

    public void run() {
        fromclient = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                String NewMessage = fromclient.readLine();
                if (NewMessage == null) {
                    Message.setText(Message.getText() + "<br>" + "EndOfOrder <br>");
                    break;
                }
                Message.setText(Message.getText() + "<br>" + NewMessage);
                Thread.sleep(100);
            } catch (IOException | InterruptedException ex) {
                Message.setText(Message.getText() + "<br>" + "EndOfOrder <br>");
                break;
            }
        }

    }

}
