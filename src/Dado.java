import java.util.Random;
import java.io.Serializable;

public class Dado implements Serializable{
    private int sideUp;
    
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
        sideUp = random.nextInt(6) + 1;
    }

    //metodo para mostrar o dado
    public String toString() {
        return "Dado: " + sideUp;
    }
}
