public class JogoDados {
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado[] dados;

    JogoDados(int nDados, String nomeJogo, float saldo) {
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.saldo = saldo;
        dados = new Dado[nDados];
        for (int i = 0; i < nDados; i++) {
            dados[i] = new Dado();
        }
    }

    public void rolarDados() {
        for (int i = 0; i < nDados; i++) {
            dados[i].roll();
        }
    }

    public Dado[] getDados() {
        return dados;
    }

    public void setDados(Dado[] dados) {
        this.dados = dados;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
}
