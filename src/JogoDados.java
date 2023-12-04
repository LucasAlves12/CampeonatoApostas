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

    //metodo para rolar os dados
    public void rolarDados() {
        for (int i = 0; i < nDados; i++) {
            dados[i].roll();
        }
    }
  
    //metodo para mostrar os dados
    public Dado[] getDados() {
        return dados;
    }

    //metodo para mostrar o vetor de dados
    public Dado getDado(int i) {
        return dados[i];
    }

    //metodo para mostrar o numero sorteado no dado
    public int numeroDado(Dado dado) {
        return dado.getSideUp();
    }

    //metodo para executar as regras do jogo azar
    public boolean executarRegrasJogo() {
        return true;
    }

    //metodo para executar as regras do jogo general
     public int executarRegrasJogoG(int jogada) {
        return 0;
    }

    //metodo setter dos dados
    public void setDados(Dado[] dados) {
        this.dados = dados;
    }

    //metodo para pegar o saldo do jogo
    public float getSaldo() {
        return saldo;
    }

    //metodo para settar o saldo do jogo
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    //metodo para somar as faces sorteadas do dado
    @Override
    public int somarFacesSorteadas(Dado[] dados) {
        int soma = 0;
        for (int i = 0; i < dados.length; i++) {
            soma += dados[i].getSideUp();
        }
        return soma;
    }

    //metodos abstratos
    public abstract boolean jogarJogos(char tipoJog);
    public abstract void extrato();
    public abstract void salvarEstatisticas(int a);
    public abstract int getEstatisticas(int i);
   
        
    
}
