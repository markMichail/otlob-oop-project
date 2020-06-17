package otlob;

import java.io.Serializable;
import java.util.ArrayList;

public class menu implements Serializable {

    public ArrayList<item> items;
    public String name;

    public menu(String n, ArrayList<item> i) {
        this.name = n;
        this.items = i;
    }

    public menu() {
        items = new ArrayList<>();
    }

}
