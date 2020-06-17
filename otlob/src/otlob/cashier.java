package otlob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class cashier extends Human {

    private String reciptTxt = filepaths.recipt;

    public String PrintRecipt(cart c) throws IOException {
        Order o = new Order();
        o.c = c;
        o.c.totalPrice = o.c.addtaxes();
        BufferedWriter w;
        w = new BufferedWriter(new FileWriter(
                reciptTxt));
        w.write(o.orderID + " ");

        for (int i = 0; i < o.c.itemsOfCart.size();i++) {
            w.write(o.c.itemsOfCart.get(i).productName + " "
                    + o.c.itemsOfCart.get(i).price + " ");

        }
        w.write(o.c.totalPrice + " ");
        w.close();

        return "Your Order Sent To Cachier";
    }
}
