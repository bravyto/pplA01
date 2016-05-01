package ppla01.foodo;

import java.io.Serializable;

/**
 * Created by Gema Raditya on 4/30/2016.
 */
public class Calories implements Serializable{
    public int cal=0;

   /* public Calories(int cal) {
        this.cal = cal;
    } */

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal += cal;
    }
}
