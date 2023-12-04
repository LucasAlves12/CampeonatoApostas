import java.io.Serializable;

public abstract class Jogador implements Serializable {

    private String nome;
    private JogoDados[] jogo;
    private int nJogos;
    private float saldo;

    //construtor
    public Jogador(String nome) {
        this.nome = nome;
        jogo = new JogoDados[10];
        nJogos = 0;
        saldo = 100;
    }
    //construtor sobrecarga
    public Jogador() {
        jogo = new JogoDados[10];  
    }

    //metodos abstratos
    abstract void jogarGeneral(int rodada, float valorAposta);
    abstract void jogarAzar(int rodada, float valorAposta);
    public abstract float escolherAposta();

    //metodo para settar saldo do jogador
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    //metodo para pegar o numero de jogos
    public int getnJogos() {
        return nJogos;
    }

    //metodo para jogar os dados
    public void jogarDados() {
        for (int i = 0; i < nJogos; i++) {
            jogo[i].rolarDados();
        }
    }

    //metodo para mostrar as jogadas executadas
    public void mostrarJogadasExecutadas() {
        for (int i = 0; i < nJogos; i++) {
            System.out.println(jogo[i].toString());
        }
    }

    //metodo para mostrar o tipo de jogador
    public String getTipoJogador() {
        String tipo;

        if (this instanceof Humano)
            tipo = "H";
        else
            tipo = "M";

        return tipo;
    }

    //metodo para pegar o jogo
    public JogoDados[] getJogo() {
        return jogo;
    }

    //metodo para pegar o nome do jogador
    public String getNome() {
        return this.nome;
    }

    //metodo para pegar o saldo do jogador
    public Float getSaldo() {
        return saldo;
    }
    
    //metodo para escolher o jogo
    public char escolherJogo() {
        return 0;
    }

    //usado para fazer a aposta da maquina
    public int ApostaMaquina() {
        return 0;
    }

    //metodo que executa o jogo general e atualiza o saldo conforme o resultado
    public void execGeneral(int rodada, float valorAposta, char tipoJogador){

        nJogos++;
        System.out.println("Jogo numero: " + nJogos);
        jogo[rodada] = new JogoGeneral(valorAposta,saldo);
        boolean s = jogo[rodada].jogarJogos(tipoJogador);

        if(s) setSaldo(saldo + valorAposta*2);
        System.out.println("Saldo: " + (saldo));
    }

    //metodo que executa o jogo azar e atualiza o saldo conforme o resultado
    public void execAzar(int rodada, float valorAposta, char tipoJogador){
        nJogos++;
        System.out.println("Jogo numero: " + nJogos);
        jogo[rodada] = new JogoAzar(valorAposta,saldo);
        boolean s = jogo[rodada].jogarJogos(tipoJogador);
        if(s) setSaldo(saldo + valorAposta*2);
        System.out.println("Saldo: " + (saldo));
    }

}
