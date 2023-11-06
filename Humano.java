public class Humano implements JogarComoHumano {
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(String cpf, String agencia, String conta, int numeroBanco) {
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
