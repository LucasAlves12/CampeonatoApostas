public class JogoAzar extends JogoDados{
    private float valorAposta;
    private Integer valorBuscado = null;

    public JogoAzar(float valorAposta) {
        super(2, "Jogo do Azar", 100);
        this.valorAposta = valorAposta;
    }
     
    public void executarRegrasJogo(){
        int soma = super.getDados()[0].getSideUp() + super.getDados()[1].getSideUp();
        if(valorBuscado == null){
            valorBuscado = soma;
        }
        if((soma == 7 || soma == 11) && valorBuscado == null){
            super.setSaldo(super.getSaldo() + valorAposta);
        }
        else if(soma == 2 || soma == 3 || soma == 12){
            super.setSaldo(super.getSaldo() - valorAposta);
        }
        else if(soma == valorBuscado){
            super.setSaldo(super.getSaldo() + valorAposta);
        }
        else{
            super.setSaldo(super.getSaldo());
        }
    }
}