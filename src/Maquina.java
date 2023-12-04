public class Maquina extends Jogador implements JogarComoMaquina{

    public Maquina(String nome) {
        super(nome);
    }

    public int ApostaMaquina() {
        return (int)Math.floor((Math.random() * 2));
    }

    //escolhe o valor a ser apostado
    public float escolherAposta() {
        float valorAposta=0;
        if (getSaldo() > 0) {
            if (getSaldo() > 30) valorAposta =  20f;
            else if (getSaldo() > 10) valorAposta = 10f;
            else if (getSaldo() > 5) valorAposta =  5f;
            else valorAposta = 1f;
        }
        return valorAposta;
    }

    //metodo para chamar o jogo general e atribuir o valor da aposta
    @Override
    void jogarGeneral(int rodada, float valorAposta) {
        super.setSaldo(super.getSaldo() - valorAposta);
        super.execGeneral(rodada, valorAposta,'M');
    }

    //metodo para chamar o jogo azar e atribuir o valor da aposta
    @Override
    void jogarAzar(int rodada, float valorAposta) {
        super.setSaldo(super.getSaldo() - valorAposta);
        super.execAzar(rodada, valorAposta,'M');
        
    }
}
