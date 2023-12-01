
public abstract class Jogador {

    private String nome;
    private JogoDados[] jogo;
    private int nJogos;
    private float saldo;

    public Jogador(String nome) {
        this.nome = nome;
        this.jogo = new JogoDados[10];
        this.nJogos = 0;
        this.saldo = 100;
    }

    public int getnJogos() {
        return nJogos;
    }

    public void jogarDados() {
        for (int i = 0; i < nJogos; i++) {
            jogo[i].rolarDados();
        }
    }
    
    public void mostrarJogadasExecutadas(){
        for (int i = 0; i < nJogos; i++) {
            System.out.println(jogo[i].toString());
        }
    }

    public String getTipoJogador(){
        String tipo;

        if(this instanceof Humano)tipo = "H";
        else  tipo = "M";
       
        return tipo;
    }

    public JogoDados[] getJogo() {
        return jogo;
    }

    public boolean addJogo(int i, char c, float v) {
        if (c == 'A')
            jogo[i] = new JogoAzar(v);
        else
            jogo[i] = new JogoGeneral(v);

        return true;
    }

    public String getNome() {
       return this.nome;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void WinSaldo(Float saldo) {
        this.saldo += saldo;
    }
    public void LoseSaldo(Float saldo) {
        this.saldo -= saldo;
    }

    public float escolherAposta() {
        return 0.0f;
    }

    public char escolherJogo() {
        return 0;
    }
}
