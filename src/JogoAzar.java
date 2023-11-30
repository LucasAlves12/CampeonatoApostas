public class JogoAzar extends JogoDados {
    private float valorAposta;
    private Integer valorBuscado = null;

    public JogoAzar(float valorAposta) {
        super(2, "Jogo do Azar", 100);
        this.valorAposta = valorAposta;
    }

    @Override
    public boolean executarRegrasJogo(){
        int lancamento = 1;
        int i=0;

            int soma = super.getDados()[0].getSideUp() + super.getDados()[1].getSideUp();
            System.out.println("Lançamento " + lancamento + ": " + super.getDados()[0].getSideUp() + " + " + super.getDados()[1].getSideUp() + " = " + soma); // print the dice roll
            
            if((soma == 7 || soma == 11)){
                super.setSaldo(super.getSaldo() + valorAposta);
                return true;
            }
            else if(soma == 2 || soma == 3 || soma == 12){
                super.setSaldo(super.getSaldo() - valorAposta);
                
                return false;
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
                        super.setSaldo(super.getSaldo() - valorAposta);
                        return false;
                    }
                    else if(soma == valorBuscado){
                        super.setSaldo(super.getSaldo() + valorAposta);
                        
                        return true;
                    }
                    
                }while(valorBuscado != soma);
                return false;
                
            }
            
    }

}