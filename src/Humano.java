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

        }while(valorAposta > getSaldo() || valorAposta < 0);

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
        
        Scanner sc = new Scanner(System.in);
        int PontosJogada;
        int treze = 0; 
        int total = 0;

        if (c == 'A'){
            getJogo()[i] = new JogoAzar(v);
            getJogo()[i].rolarDados();
            if( getJogo()[i].executarRegrasJogo() == true){ 
                WinSaldo(v);
                
                System.out.println("Você ganhou!!! Seu novo saldo : " + getSaldo());
                return true;
            }
            LoseSaldo(v);
             System.out.println("Você perdeu!!! Seu novo saldo : " + getSaldo());
             return false;
        }

        else{
            getJogo()[i] = new JogoGeneral(v);
            for(i = 0;i < 13; i++){
                getJogo()[i].rolarDados();     
                
                getJogo()[i].JogadasDisponiveis();
                
                System.out.println("Que jogada deseja fazer? (1-13): ");
                int jogada = sc.nextInt();
                PontosJogada = getJogo()[i].executarRegrasJogoG(jogada);

                if(jogada == 13) treze = PontosJogada;
                else total += PontosJogada;
            
            }
            if(treze*2 < total) return true;
            else return false;
        }
        
    }

}
