import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Campeonato {
    private Jogador[] jogadores;
    private int qtdJogadores;
     private File arq = new File("jogoGeneral.dat");

    public void incluirJogador() {
    }

    public void removerJogador() {
    }

    public void iniciarCampeonato() {
    }

    public void mostrarCartela() {
    }

     //grava em arquivo .dat a rodada executada
    public void gravarEmArquivo() {
        try {
            FileOutputStream fout = new FileOutputStream(arq);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            // gravando o vetor de pessoas no arquivo
            oos.writeObject(jogadores);
            oos.flush();
            oos.close();
            fout.close();

            System.out.println("Dados gravados com sucesso!\n");
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

    }

    //le os dados do arquvo .dat
    public void lerDoArquivo() {
        try {
            FileInputStream fin = new FileInputStream(arq);
            ObjectInputStream oin = new ObjectInputStream(fin);

            // Lendo os objetos de um arquivo e fazendo a coercao de tipos

            Jogador[] jogaArq = (Jogador[]) oin.readObject();
            oin.close();
            fin.close();
            jogadores = jogaArq;
            qtdJogadores = 0;
            
            for(Jogador j : jogadores) {
                if(j != null) {
                    qtdJogadores++;
                }
            }

            System.out.println("Dados lidos com sucesso!\n");
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

    }

}
