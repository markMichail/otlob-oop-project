package otlob;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static otlob.OTLOB.*;

public class customer
        extends Human
        implements IupdateData, Ilogin, IsignUp, Serializable {

    public address Address;

    public customer() {
        fullName = new name();
        phoneNumber = new number();
        Address = new address();
    }

    public boolean checkVoucher(String code, restaurant r) {
        deal D;
        if (DealHashmap.get(code) == null) {
            return false;
        }
        D = DealHashmap.get(code);
        return r.name.equals(D.rname);

    }

    public void applyVoucher(String code) {
        deal D;
        D = DealHashmap.get(code);
        System.out.println(code);
        System.out.println(D.rname);
        c.totalPrice = (float) (c.totalPrice - (c.totalPrice * (D.discountPercent / 100.0)));
    }

    public int makereview(restaurant Mac, int NumOfStars) {
        Mac.NoOfPeopleRating++;
        Mac.NoOfTotalStars += NumOfStars;
        Mac.rating = Mac.NoOfTotalStars / Mac.NoOfPeopleRating;
        return Mac.rating;
    }

    public ArrayList<Order> viewRecentOrders() throws IOException {
        ObjectInputStream f;
        try {
            f = new ObjectInputStream(new FileInputStream(
                    filepaths.customersOrdersFolder + UserName + ".bin"));
            try {
                return (ArrayList< Order>) f.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return null;

    }

    public restaurant searchRestaurant(ArrayList<restaurant> r, String name) {
        int index = 0;
        Iterator<restaurant> itr2 = r.iterator();
        int j = 0;
        while (itr2.hasNext()) {
            if (r.get(j).name.toLowerCase().equals(name.toLowerCase())) {
                index = j;
                break;
            }
            j++;
        }
        return r.get(index);
    }

    public boolean checkSearch(ArrayList<restaurant> r, String name) {
        boolean first = false;
        boolean second = false;
        int j = 0;
        int index = 0;
        for (restaurant x : OTLOB.Allrest) {
            if (r.get(j).name.equals(name)) {
                first = true;
                index = j;
                break;
            }
            j++;
        }

        for (int c = 0; c < r.get(index).location.size(); c++) {

            if (Address.area.toLowerCase().equals(r.get(index).location.get(c).toLowerCase())) {
                second = true;
                break;
            }
        }

        if (first == true && second == true) {
            return true;
        } else {
            return false;
        }
    }

    public String savaVisa(visa v) throws IOException {
        BufferedWriter BW = new BufferedWriter(new FileWriter(
                filepaths.visaDataFolder + UserName + ".txt"));
        BW.write(v.cardNum + "~" + v.CVV + "~" + v.expDate.month + "~" + v.expDate.year + "~");
        return "Visa Data Is Saved";
    }

    public String AddToOrdersFile(Order o) throws IOException {
        ObjectOutputStream ObjOut;
        OTLOB.ordersList.add(o);
        ObjOut = new ObjectOutputStream(
                new FileOutputStream(filepaths.customersOrdersFolder + UserName + ".bin"));
        ObjOut.writeObject(ordersList);
        ObjOut.close();
        return "Ordered SuccessFully";
    }

    public void viewNearRestaurants() {

        for (int i = 0; i < OTLOB.Allrest.size(); i++) {
            for (int j = 0; j < OTLOB.Allrest.get(i).location.size(); j++) {
                if (OTLOB.Allrest.get(i).location.get(j).toLowerCase().equals(Address.area.toLowerCase())) {
                    OTLOB.nRArr.add(OTLOB.Allrest.get(i));
                }
            }
        }
    }

    public String addItemToCart(item i, cart c, int quantity) {
        try {
            i.quantity = quantity;
            for (int j = 0; j < i.quantity; j++) {
                c.itemsOfCart.add(i);
                c.totalPrice += i.price;
            }
            System.out.println(c.totalPrice);
        } catch (NumberFormatException f) {
            JOptionPane.showMessageDialog(null, "wrong input!");

        }

        return "item is added !! ";
    }

    @Override
    public String updatedata() {
        OTLOB.CustomerHashmap.put(Customer.UserName, Customer);
        return "Data Is Updated";
    }

    @Override
    public int login(String username, String password) {
        if (OTLOB.CustomerHashmap.get(username) == null) {
            return -2;
        } else {
            customer x = OTLOB.CustomerHashmap.get(username);
            if (x.pass.equals(password)) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String signUp() {
        //check if this userName Already taken or Not
        if (OTLOB.CustomerHashmap.get(Customer.UserName) != null) {
            return "username Already Taken";
        }
        OTLOB.CustomerHashmap.put(Customer.UserName, Customer);
        return "signUp successful";
    }

}
