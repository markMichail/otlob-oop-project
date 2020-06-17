package otlob;

import java.io.Serializable;

public class flight implements Serializable{

    public int deliveryFee;

    public int timeEstimated;
    
    public flight(){
        
    }
    public flight(int f ,int time){
        this.deliveryFee=f;
        this.timeEstimated=time;
    }
}
