/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
public class CustomerService {

    /**
     * @param args the command line arguments
     */
    public static HashMap<Integer, Boolean> AllPorts = new HashMap<>();
    public static void main(String[] args) throws IOException {
        try {
            ObjectInputStream ObjIn = new ObjectInputStream(
                    new FileInputStream(filespaths.ports));
            AllPorts = (HashMap<Integer, Boolean>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjectOutputStream ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filespaths.ports));
            ObjOut.writeObject(AllPorts);
            ObjOut.close();
        }
        Random r = new Random();
        int port = r.nextInt(1000) + 2000;
        boolean a = true;
        while (a) {
            a = false;
            for (int temp : AllPorts.keySet()) {
                if (temp == port) {
                    port = r.nextInt(1000) + 2000;
                    a = true;
                    break;
                }
            }
        }
        AllPorts.put(port, true);
        System.out.println(port);
        System.out.println(AllPorts.get(port));
        ObjectOutputStream ObjOut = new ObjectOutputStream(
                new FileOutputStream(filespaths.ports));
        ObjOut.writeObject(AllPorts);
        ObjOut.close();
        Server s = new Server(port);
        s.setVisible(true);
        Thread t = new Thread(s);
        t.start();

    }

}
