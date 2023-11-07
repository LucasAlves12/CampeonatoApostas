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

    public int escolherJogo() {
        return 0;
    }

    public int escolherJogada(JogoGeneral jogo) {
        return 0;
    }
}
