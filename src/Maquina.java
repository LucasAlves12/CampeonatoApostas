public class Maquina extends Jogador implements JogarComoMaquina {

    public Maquina(String nome) {
        super(nome);
    }

    public int aplicarEstrategia(){
        return 0;
    }//necessita implementar

    @Override
    void jogarGeneral(int rodada, float valorAposta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jogarGeneral'");
    }

    @Override
    void jogarAzar(int rodada, float valorAposta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jogarAzar'");
    }
}
