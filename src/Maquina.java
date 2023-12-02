public class Maquina extends Jogador implements JogarComoMaquina {

    public Maquina(String nome) {
        super(nome);
    }

    public float escolherAposta() {
        float valorAposta = 0;
        if (getSaldo() > 0) {
            if (getSaldo() > 20) valorAposta =  20f;
            else if (getSaldo() > 10) valorAposta = 10f;
            else if (getSaldo() > 5) valorAposta =  5f;
            else valorAposta = 1f;
        }
        return valorAposta;
    }

    @Override
    void jogarGeneral(int rodada, float valorAposta) {
        super.setSaldo(super.getSaldo() - valorAposta);

        super.execGeneral(rodada, valorAposta,'M');
    }

    @Override
    void jogarAzar(int rodada, float valorAposta) {
        super.setSaldo(super.getSaldo() - valorAposta);

        super.execAzar(rodada, valorAposta,'M');
        System.out.println("Saldo: " + (getSaldo()));
    }
}
