package otlob;

import java.io.Serializable;

public class Order implements Serializable{
    
    public String RestName;

    protected flight f;

    public cart c;

    protected payment p;

    public int orderID;
}
