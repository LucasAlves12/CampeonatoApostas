import java.io.Serializable;

public abstract class JogoDados implements Estatistica,Serializable{
    
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

    public Dado getDado(int i) {return dados[i];}
        
    

    public int numeroDado(Dado dado) {
        return dado.getSideUp();
    }


    public boolean executarRegrasJogo() {
        return true;
    }

     public int executarRegrasJogoG(int jogada) {
        return 0;
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
    
    @Override
    public int somarFacesSorteadas(Dado[] dados) {
        int soma = 0;
        for (int i = 0; i < dados.length; i++) {
            soma += dados[i].getSideUp();
        }
        return soma;
    }

    public abstract boolean jogarJogos(char tipoJog);
    public abstract void extrato();
    public abstract void salvarEstatisticas(int a);
    public abstract int getEstatisticas(int i);
   
        
    
}
