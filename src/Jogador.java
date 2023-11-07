
public abstract class Jogador {

    private String nome;
    private JogoDados[] jogo;
    private int nJogos;

    public Jogador(String nome) {
        this.nome = nome;
        this.jogo = new JogoDados[10];
        this.nJogos = 0;
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

    public void addJogo(JogoDados jogo) {
        this.jogo[nJogos] = jogo;
        nJogos++;
        

    }

    public String getNome() {
       return this.nome;
    }

    public float getSaldo(int i) {
        return jogo[i].getSaldo();
    }



}
