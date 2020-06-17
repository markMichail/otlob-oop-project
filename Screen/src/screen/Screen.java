/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Screen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Server s = new Server(4000);
        s.setVisible(true);
        Thread t = new Thread(s);
        t.start();

    }

}
