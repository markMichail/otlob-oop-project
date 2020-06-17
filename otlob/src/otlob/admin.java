package otlob;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static otlob.OTLOB.*;

public class admin
        extends Human
        implements IupdateData, Ilogin, IsignUp, Serializable {

    public admin() {
        fullName = new name();
        phoneNumber = new number();
    }

    public String addDeal(deal D) {
        DealHashmap.put(D.code, D);
        return "deal added succesfully";
    }

    public boolean removeDeal(String s) {
        if (DealHashmap.remove(s) != null) {
            return true;
        } else {
            return false;
        }
    }

    public String addrestaurant(restaurant R) {
        Allrest.add(R);
        return "restaurant added succesfully !!";
    }

    public String editRestaurant(offers o, int id) {
        Allrest.get(id).myOffers = o;
        return "edited succesfully ";
    }

    public String editRestaurant(menu m, int id, int id2) {
        Allrest.get(id).Menu.set(id2, m);
        return "edited succesfully ";
    }

    public boolean removeCustomer(String uName) {
        if (CustomerHashmap.remove(uName) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeWaiter(String uName) {
        if (WaiterHashmap.remove(uName) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String updatedata() {
        AdminHashmap.put(Admin.UserName, Admin);
        return "Data Is Updated";
    }

    @Override
    public int login(String username, String password) {
        if (AdminHashmap.get(username) == null) {
            return -2;
        } else {
            admin x = AdminHashmap.get(username);
            if (x.pass.equals(password)) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String signUp() {
        ObjectInputStream ObjIn = null;
        try {
            ObjIn = new ObjectInputStream(
                    new FileInputStream(filepaths.adminsHashmap));
            AdminHashmap = (HashMap<String, admin>) ObjIn.readObject();
            ObjIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //check if this userName Already taken or Not
        if (AdminHashmap.get(Admin.UserName) != null) {
            return "username Already Taken";
        }
        AdminHashmap.put(Admin.UserName, Admin);
        return "signUp successful";
    
    }
        }


