public class JogoAzar extends JogoDados {
    private float valorAposta;
    private Integer valorBuscado = null;

    public JogoAzar(float valorAposta) {
        super(2, "Jogo do Azar", 100);
        this.valorAposta = valorAposta;
    }

    public boolean executarRegrasJogo(){
        boolean retorno = false;
        int lancamento = 1;
    
        while (!retorno) {
            int soma = super.getDados()[0].getSideUp() + super.getDados()[1].getSideUp();
            System.out.println("Lançamento " + lancamento + ": " + super.getDados()[0].getSideUp() + " + " + super.getDados()[1].getSideUp() + " = " + soma); // print the dice roll
            if(valorBuscado == null){
                valorBuscado = soma;
            }
            if((soma == 7 || soma == 11) && valorBuscado == null){
                super.setSaldo(super.getSaldo() + valorAposta);
                retorno = true;
            }
            else if(soma == 2 || soma == 3 || soma == 12){
                super.setSaldo(super.getSaldo() - valorAposta);
                retorno = false;
            }
            else if(soma == valorBuscado){
                super.setSaldo(super.getSaldo() + valorAposta);
                retorno = true;
            }
            lancamento++;
        }
        return retorno;
    }

}