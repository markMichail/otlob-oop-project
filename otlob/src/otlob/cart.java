package otlob;

import java.io.Serializable;
import java.util.ArrayList;

public class cart implements Serializable
{

    public ArrayList<item> itemsOfCart;

    public float totalPrice;

    public String comment;

    public String voucher;

    public cart() {
        voucher = new String();
        comment = new String();
        itemsOfCart=new ArrayList<item>();
        totalPrice = 0;
        comment = "";
    }

    public float addtaxesAndDel(int delFees) {
        return (float) (totalPrice + (totalPrice * 0.14) + delFees);
    }
    
    public float addtaxes() {
        return (float) (totalPrice + (totalPrice * 0.14));
    }

}
