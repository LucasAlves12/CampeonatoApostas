public class JogoAzar extends JogoDados {
    private float valorAposta;
    private Integer valorBuscado = 0;
    private boolean resultado;
    private int[] estats;

    public JogoAzar(float valorAposta, float saldo) {
        super(2, "Jogo do Azar", saldo);
        this.valorAposta = valorAposta;
        estats = new int[numFaces];
        for (int i = 0; i < 6; i++) {
            estats[i] = 0;
        }
    }

    //metodo para jogar o jogo azar retornado boolean
    @Override
    public boolean jogarJogos(char tipoJog) {
        int lancamento = 1;
        int i = 0;

        do {
            for (i = 0; i < 2; i++){rolarDados(); 
                salvarEstatisticas( getDados()[i].getSideUp());
            }
                
            int soma = getDados()[0].getSideUp() + getDados()[1].getSideUp();

            System.out.println("Lançamento " + lancamento + ": " + getDados()[0].getSideUp() + " + "
                    + getDados()[1].getSideUp() + " = " + soma); // print the dice roll

            if (((soma == 7 || soma == 11) && lancamento == 1) || (soma == valorBuscado)) {
                setSaldo(super.getSaldo() + valorAposta * 2);
                System.out.println("Você ganhou!!!");resultado = true;
                return true;
            } else if (soma == 2 || soma == 3 || soma == 12) 
                {System.out.println("Você perdeu!!!");resultado = false;return false;}
            else if(lancamento == 1)valorBuscado = soma;
             
            lancamento++;
        } while (true);

    }

    //metodo para mostrar o extrato do jogo azar
    public void extrato(){
        System.out.println("\nJogo: Jogo azar");
        System.out.println("Valor da aposta: " + valorAposta);
        System.out.println("Resultado ultima jogada de dados: " + getDados()[0].getSideUp() + " + "
                    + getDados()[1].getSideUp() + " = " + (getDados()[0].getSideUp() + getDados()[1].getSideUp()) );
        if(resultado) System.out.println("Resultado: Ganhou" );
        else System.out.println("Resultado: Perdeu" ); 
    }

    //metodo para salvar estatisticas por jogada
    public void salvarEstatisticas(int a) {
        for(int i = 0; i < 6; i++){
            if(a == i+1) estats[i]++;
        }
    }

    //metodo para pegar estatisticas por jogada
    @Override
    public int getEstatisticas(int i) {return estats[i];}
        

    
    
        
    
}