package otlob;

import GUI.CartGUI;
import GUI.CheckoutGUI;
import GUI.HomeGUI;
import GUI.MenusGUI;
import GUI.RestGUI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mark
 */
public class OTLOB implements Serializable {

    // definitions of static
    public static ArrayList<restaurant> Allrest = new ArrayList<restaurant>();
    public static ArrayList<restaurant> nRArr = new ArrayList<>();
    public static ArrayList<Order> ordersList = new ArrayList<>();
    public static ArrayList<String> Places = new ArrayList<>();
    public static HashMap<String, customer> CustomerHashmap = new HashMap<String, customer>();
    public static HashMap<String, admin> AdminHashmap = new HashMap<String, admin>();
    public static HashMap<String, waiter> WaiterHashmap = new HashMap<String, waiter>();
    public static HashMap<String, deal> DealHashmap = new HashMap<>();
    public static HashMap<Integer, Boolean> AllPorts = new HashMap<>();
    public static CheckoutGUI checkoutGUI;
    public static CartGUI cartGUI;
    public static RestGUI restGUI;
    public static MenusGUI menusGUI;
    public static customer Customer = new customer();
    public static admin Admin = new admin();
    public static waiter Waiter = new waiter();
    public static restaurant Restaurant = new restaurant();
    public static cart c = new cart();
    public static ads Ads = new ads();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
  
        ObjectInputStream ObjIn;
        ObjectOutputStream ObjOut;
        
        // try to fill places arrayList
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.placesArray));
            Places = (ArrayList< String>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.placesArray));
            ObjOut.writeObject(Places);
            ObjOut.close();
        }

        // try to fill restaurants arrayList
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.restsArray));
            Allrest = (ArrayList< restaurant>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.restsArray));
            ObjOut.writeObject(Allrest);
            ObjOut.close();
        }

        // try to fill deal hashmap
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.dealsHashmap));
            DealHashmap = (HashMap<String, deal>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.dealsHashmap));
            ObjOut.writeObject(DealHashmap);
            ObjOut.close();
        }

        // try to fill ads object
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.ad));
            Ads = (ads) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.ad));
            ObjOut.writeObject(Ads);
            ObjOut.close();
        }

        // try to fill admins hashmap
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.adminsHashmap));
            AdminHashmap = (HashMap<String, admin>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.adminsHashmap));
            ObjOut.writeObject(AdminHashmap);
            ObjOut.close();
        }

        // try to fill customers hashmap
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.customersHashmap));
            CustomerHashmap = (HashMap<String, customer>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.customersHashmap));
            ObjOut.writeObject(CustomerHashmap);
            ObjOut.close();
        }

        // try to fill waiter hashmap
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.waitersHashmap));
            WaiterHashmap = (HashMap<String, waiter>) ObjIn.readObject();
            ObjIn.close();
        } catch (ClassNotFoundException | IOException ex) {
            ObjOut = new ObjectOutputStream(
                    new FileOutputStream(filepaths.waitersHashmap));
            ObjOut.writeObject(WaiterHashmap);
            ObjOut.close();
        }
        
        HomeGUI homeGUI = new HomeGUI();
        homeGUI.setVisible(true);
    }
}
