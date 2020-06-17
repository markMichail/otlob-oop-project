package otlob;

import java.io.Serializable;

public class offers implements Serializable {

    public menu popularItems;

    public offers() {
        popularItems = new menu();
        popularItems.name = "popular items";
    }

    public offers(menu p) {

        this.popularItems = p;
    }
}
