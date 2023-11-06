
public abstract class Jogador {

    private String nome;
    private JogoDados[] jogo;
    private int nJogos;

    public Jogador(String nome, JogoDados jogo, int n) {
        this.nome = nome;
        this.jogo = new JogoDados[n];
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




}
