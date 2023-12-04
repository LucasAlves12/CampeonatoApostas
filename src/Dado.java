import java.util.Random;
import java.io.Serializable;

public class Dado implements Serializable{
    private int sideUp;
    private static final int NUM_SIDES = 6;
    
    //construtor
    public Dado() {
        sideUp = -1;
    }

    //metodo para pegar o lado do dado
    public int getSideUp() {
        return sideUp;
    }

    //metodo para rolar o dado
    public void roll() {
        Random random = new Random();
        sideUp = random.nextInt(NUM_SIDES) + 1;
    }

    //metodo para mostrar o dado
    public String toString() {
        return "Dado: " + sideUp;
    }
}
