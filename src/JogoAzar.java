public class JogoAzar extends JogoDados {
    private float valorAposta;
    private Integer valorBuscado = null;

    public JogoAzar(float valorAposta,float saldo) {
        super(2, "Jogo do Azar", saldo);
        this.valorAposta = valorAposta;
    }

    @Override
    public float jogarJogos(char tipoJog) {
        int lancamento = 1;
        int i=0;

            for (i = 0; i < 2; i++) {
                super.rolarDados();
            }

            int soma = super.getDados()[0].getSideUp() + super.getDados()[1].getSideUp();
            System.out.println("Lançamento " + lancamento + ": " + super.getDados()[0].getSideUp() + " + " + super.getDados()[1].getSideUp() + " = " + soma); // print the dice roll
            
            if((soma == 7 || soma == 11)){
                super.setSaldo(super.getSaldo() + valorAposta*2);
                System.out.println("Você ganhou!!!" );
                return (getSaldo());
            }
            else if(soma == 2 || soma == 3 || soma == 12){
                super.setSaldo(super.getSaldo());
                System.out.println("Você perdeu!!!" );
                return getSaldo();
            }
            else{
                valorBuscado = soma;
                lancamento++;

                do{
                    for (i = 0; i < 2; i++) {
                        super.getDados()[i].roll();
                    }
                    soma = super.getDados()[0].getSideUp() + super.getDados()[1].getSideUp();
                    System.out.println("Lançamento " + lancamento + ": " + super.getDados()[0].getSideUp() + " + " + super.getDados()[1].getSideUp() + " = " + soma); // print the dice roll
                    lancamento++;
                    if(soma == 2 || soma == 3 || soma == 12){
                        super.setSaldo(super.getSaldo());
                        System.out.println("Você perdeu!!! ");
                        return getSaldo();
                    }
                    else if(soma == valorBuscado){
                        super.setSaldo(super.getSaldo() + valorAposta*2);
                        System.out.println("Você ganhou!!! " );
                        return (getSaldo());
                    }
                    
                }while(valorBuscado != soma);
                return 0;
                
            }
            
    }

}