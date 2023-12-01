import java.util.Random;

public class Dado {
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
