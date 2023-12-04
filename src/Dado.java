import java.util.Random;
import java.io.Serializable;

public class Dado implements Serializable{
    private int sideUp;
    
    public Dado() {
        sideUp = -1;
    }

    public int getSideUp() {
        return sideUp;
    }

    public void roll() {
        Random random = new Random();
        sideUp = random.nextInt(6) + 1;
    }

    public String toString() {
        return "Dado: " + sideUp;
    }
}
