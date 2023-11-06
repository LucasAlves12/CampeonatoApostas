public class Maquina extends Jogador implements JogarComoMaquina {

    public Maquina(String nome, JogoDados jogo, int n) {
        super(nome,jogo,n);
    }

    public int aplicarEstrategia(){
        return 0;
    }//necessita implementar
}
