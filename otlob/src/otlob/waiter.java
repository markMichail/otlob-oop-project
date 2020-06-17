package otlob;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static otlob.OTLOB.*;

public class waiter extends Human implements Ilogin, IsignUp, IupdateData {

    String wData = filepaths.waitersDataFolder;
    String waiter = filepaths.waitersTxt;
    String orderid = filepaths.ordersIDs;
    String wNames = filepaths.waitersNames;
    public restaurant r;
    public String Rname;

    public waiter() {
        fullName = new name();
        phoneNumber = new number();
    }

    @Override
    public int login(String username, String password) {
        if (OTLOB.WaiterHashmap.get(username) == null) {
            return -2;
        } else {
            waiter x = OTLOB.WaiterHashmap.get(username);
            if (x.pass.equals(password)) {
                return 0;
            }
        }

        return -1;
    }

    @Override
    public String updatedata() {
        OTLOB.WaiterHashmap.put(Waiter.UserName, Waiter);
        return "Data Is Updated";
    }

    @Override
    public String signUp() {
        try {
            ObjectInputStream ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.waitersHashmap));
            WaiterHashmap = (HashMap<String, waiter>) ObjIn.readObject();
            ObjIn.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(waiter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(waiter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(waiter.class.getName()).log(Level.SEVERE, null, ex);
        }
        //check if this userName Already taken or Not
        if (OTLOB.WaiterHashmap.get(Waiter.UserName) != null) {
            return "username Already Taken";
        }
        OTLOB.WaiterHashmap.put(Waiter.UserName, Waiter);
        return "signUp successful";
    }

    public String sendToCachier(cart c) throws IOException {
        cashier Cashier = new cashier();
        return Cashier.PrintRecipt(c);
    }

    public String sendToChef(cart c) throws IOException {
        cusine Cusine = new cusine();
        return Cusine.printToScreen(c);
    }

    public String addItemToCart(item i, cart c, int quantity) {
        i.quantity = quantity;
        for (int j = 0; j < i.quantity; j++) {
            c.itemsOfCart.add(i);
            c.totalPrice += i.price;
        }
        return "item is added !! ";
    }
}
