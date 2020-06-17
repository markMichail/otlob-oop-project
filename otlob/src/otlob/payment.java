package otlob;

import java.io.Serializable;

public class payment implements Serializable{

    public visa v;

    public Boolean chooseVorC(int x) {
        if (x == 1)
            return true;
        return false;
    }
}
