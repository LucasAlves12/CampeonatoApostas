import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Campeonato {
    private Jogador[] jogadores;
    private int qtdJogadores;
    private int maxJogadores;
    private File arq = new File("jogoGeneral.dat");
    private Scanner teclado = new Scanner(System.in);

     public Campeonato() {
        jogadores = new Jogador[10];
        qtdJogadores = 0;
        maxJogadores = 10;
        

    }

    public void incluirJogador() {
        char p;

        System.out.println("Insira seu nome: ");
        String nome = teclado.nextLine();

        do {
            System.out.println("Tipo de jogador (H - humano ou M - maquina):");
            p = teclado.nextLine().toUpperCase().charAt(0);
        } while (p != 'H' && p != 'M');

        if (qtdJogadores < maxJogadores) {

            
            if(p == 'H'){

                System.out.println("Insira seu CPF: ");
                String cpf = teclado.nextLine();
                teclado.nextLine();
                System.out.println("Insira sua agencia: ");
                String agencia = teclado.nextLine();
                teclado.nextLine();
                System.out.println("Insira sua conta: ");
                String conta = teclado.nextLine();
                teclado.nextLine();
                System.out.println("Insira o numero do banco: ");
                int numeroBanco = teclado.nextInt();




                jogadores[qtdJogadores] = new Humano(nome, cpf, agencia, conta, numeroBanco);
                qtdJogadores++;
            }
            else{
                jogadores[qtdJogadores] = new Maquina(nome);
                qtdJogadores++;
            }
        } else {
            System.out.println("Não é possivel inserir mais jogadores !!!");
        }
    }

    public void removerJogador() {
        int posicaoRemovida = 0;
        String nomeAux; // variavel para remover pelo nome
        int aux;
        int i;

        System.out.println("Informe o nome da pessoa que deseja remover:");
        nomeAux = teclado.nextLine();
        teclado.nextLine();
        for (i = 0; i < qtdJogadores; i++) {
            if (jogadores[i].getNome().equals(nomeAux)) {
                jogadores[i] = null;
                posicaoRemovida = i;
                aux = qtdJogadores;
                for (i = posicaoRemovida; i < aux - 1; i++) {
                    jogadores[i] = jogadores[i + 1];
                }
                qtdJogadores--;

                System.out.println("Jogador removido!\n");
            }
        }

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
