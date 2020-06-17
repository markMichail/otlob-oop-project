package otlob;

import java.io.Serializable;
import java.util.ArrayList;

public class restaurant implements Serializable{

    public String name;

    public ArrayList<menu> Menu;

    public ArrayList<String> location;

    public String category;

    public int rating;
    
    public int id;
    
    public int NoOfPeopleRating = 0;
    
    public int NoOfTotalStars = 0;

    public String description;

    public flight delivery;

    public offers myOffers;

    public workingHours timeWork;
    
    public restaurant()
    {
        Menu =  new ArrayList<>();
        myOffers = new offers();
     
    }
}
