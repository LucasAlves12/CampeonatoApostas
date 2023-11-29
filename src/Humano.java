import java.util.Scanner;

public class Humano extends Jogador implements JogarComoHumano {
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(String nome,String cpf, String agencia, String conta, int numeroBanco) {
        super(nome);
        this.cpf = cpf;
        this.agencia = agencia;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
    }


    public int escolherJogada(JogoGeneral jogo) {
        return 0;
    }

    public float escolherAposta() {
        Scanner sc = new Scanner(System.in);
         float valorAposta;

        do{

        System.out.println("Digite o valor da aposta: ");
            valorAposta = sc.nextFloat();

        }while(valorAposta > super.getSaldo() || valorAposta < 0);

        return valorAposta;
    }

    public char escolherJogo() {
        Scanner sc = new Scanner(System.in);
        char tipoJogo;

        do{
            System.out.println("Digite o tipo de jogo (A - Jogo do Azar ou G - Jogo General): ");
            tipoJogo = sc.nextLine().toUpperCase().charAt(0);
        }while(tipoJogo != 'A' && tipoJogo != 'G');

        return tipoJogo;
    }

    public boolean addJogo(int i, char c, float v) {
        boolean s = true;

        if (c == 'A'){
            getJogo()[i] = new JogoAzar(v);
            getJogo()[i].rolarDados();
            s = getJogo()[i].executarRegrasJogo();
            
        }

        else
            getJogo()[i] = new JogoGeneral(v);

        return s;
        
    }

}
