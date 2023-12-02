
public abstract class Jogador {

    private String nome;
    private JogoDados[] jogo;
    private int nJogos;
    private float saldo;

    public Jogador(String nome) {
        this.nome = nome;
        jogo = new JogoDados[10];
        nJogos = 0;
        saldo = 100;
    }

    abstract void jogarGeneral(int rodada, float valorAposta);

    abstract void jogarAzar(int rodada, float valorAposta);

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getnJogos() {
        return nJogos;
    }

    public void jogarDados() {
        for (int i = 0; i < nJogos; i++) {
            jogo[i].rolarDados();
        }
    }

    public void mostrarJogadasExecutadas() {
        for (int i = 0; i < nJogos; i++) {
            System.out.println(jogo[i].toString());
        }
    }

    public String getTipoJogador() {
        String tipo;

        if (this instanceof Humano)
            tipo = "H";
        else
            tipo = "M";

        return tipo;
    }

    public JogoDados[] getJogo() {
        return jogo;
    }

    public String getNome() {
        return this.nome;
    }

    public Float getSaldo() {
        return saldo;
    }
    
    public abstract float escolherAposta();

    public char escolherJogo() {
        return 0;
    }

    public void execGeneral(int rodada, float valorAposta, char tipoJogador){
        jogo[rodada] = new JogoGeneral(valorAposta,saldo);
        saldo = jogo[rodada].jogarJogos(tipoJogador);
        System.out.println("Saldo: " + (saldo));
    }

    public void execAzar(int rodada, float valorAposta, char tipoJogador){
        jogo[rodada] = new JogoAzar(valorAposta,saldo);
        saldo = jogo[rodada].jogarJogos(tipoJogador);
        System.out.println("Saldo: " + (saldo));
    }
}
