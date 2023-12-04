public class JogoAzar extends JogoDados {
    private float valorAposta;
    private Integer valorBuscado = 0;
    private boolean resultado;

    public JogoAzar(float valorAposta, float saldo) {
        super(2, "Jogo do Azar", saldo);
        this.valorAposta = valorAposta;
    }

    @Override
    public boolean jogarJogos(char tipoJog) {
        int lancamento = 1;
        int i = 0;

        do {
            for (i = 0; i < 2; i++)rolarDados(); 
                
            int soma = super.getDados()[0].getSideUp() + super.getDados()[1].getSideUp();
            System.out.println("Lançamento " + lancamento + ": " + super.getDados()[0].getSideUp() + " + "
                    + super.getDados()[1].getSideUp() + " = " + soma); // print the dice roll

            if (((soma == 7 || soma == 11) && lancamento == 1) || (soma == valorBuscado)) {
                super.setSaldo(super.getSaldo() + valorAposta * 2);
                System.out.println("Você ganhou!!!");resultado = true;
                return true;
            } else if (soma == 2 || soma == 3 || soma == 12) 
                {System.out.println("Você perdeu!!!");resultado = false;return false;}
            else if(lancamento == 1)valorBuscado = soma;
             
            lancamento++;
        } while (true);

    }
    public void extrato(){
        System.out.println("Jogo: Jogo azar");
        System.out.println("Valor da aposta: " + valorAposta);
        System.out.println("Resultado: " + resultado);
    }
}