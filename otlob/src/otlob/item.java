package otlob;

import java.io.Serializable;

public class item implements Serializable {

    public String productName;
    public int price;
    public int quantity;

  public  item(String n, int price, int quan) {
        this.price = price;
        this.productName = n;
        this.quantity = quan;
    }

  public  item(String n, int price) {
        this.price = price;
        this.productName = n;
    }

    public item() {
        this.productName = "";
        this.price = 0;
        this.quantity = 1;
    }
}
