package otlob;

import java.io.Serializable;

public class deal implements Serializable{

    public int discountPercent;
    public String rname;
    public String code;
    public String description;
    public deal(String n,Integer d,String code,String description){
        this.rname=n.toLowerCase();
        this.discountPercent=d;
        this.code=code;
        this.description=description;
    }
    public deal(){
        
    }
}
