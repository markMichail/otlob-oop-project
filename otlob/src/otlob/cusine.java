package otlob;

import GUI.CheckoutGUI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cusine {

    public String printToScreen(cart c) throws IOException {
        Socket toScreen;
        try {
            toScreen = new Socket(filepaths.cusineIP, filepaths.cusinePort);
            PrintWriter pw = new PrintWriter(toScreen.getOutputStream(), true);
            for (int i = 0; i < c.itemsOfCart.size(); i++) {
                pw.println(c.itemsOfCart.get(i).productName);
            }
            pw.close();
            toScreen.close();
        } catch (IOException ex) {
            Logger.getLogger(CheckoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Your Order Sent To chef";
    }

}
