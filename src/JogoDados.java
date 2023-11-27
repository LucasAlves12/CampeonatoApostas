
public abstract class JogoDados implements Estatistica {
    
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

    public Dado getDado(int i) {
        return dados[i];
    }


    public int numeroDado(Dado dado) {
        return dado.getSideUp();
    }

    public int validarJogada(int x) {
        return 0;
    }

    public boolean executarRegrasJogo() {
        return false;
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

    public int getPontuacao(int x){
        return 0;
    }
    
    @Override
    public int somarFacesSorteadas(Dado[] dados) {
        int soma = 0;
        for (int i = 0; i < dados.length; i++) {
            soma += dados[i].getSideUp();
        }
        return soma;
    }

    
}
