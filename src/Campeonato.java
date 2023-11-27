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
                System.out.println("Insira sua agencia: ");
                String agencia = teclado.nextLine();
                System.out.println("Insira sua conta: ");
                String conta = teclado.nextLine();
                System.out.println("Insira o numero do banco: ");
                int numeroBanco = teclado.nextInt();



                jogadores[qtdJogadores] = new Humano(nome, cpf, agencia, conta, numeroBanco);
                qtdJogadores++;
                cpf =  teclado.nextLine();
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

        System.out.println("----- Jogadores -----");  
        for (i = 0; i < qtdJogadores; i++) {
            System.out.println(jogadores[i].getNome() + "\t");
        }

        System.out.println("Informe o nome da pessoa que deseja remover:");
        nomeAux = teclado.nextLine();

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
        Float valorAposta;
        char opcao= ' ';
        boolean s = false;

        if (qtdJogadores == 0) {
            System.out.println("Não há jogadores suficientes para iniciar o campeonato"); // se não houver jogadores suficientes
        }else{
            
            for(int i = 0; i < qtdJogadores; i++){

                if(jogadores[i].getSaldo() <= 0){
                    System.out.println("O jogador " + jogadores[i].getNome() + " não possui saldo suficiente para jogar");
                    break;
                }

                else{

                    if(jogadores[i] instanceof Humano){
                    
                        do {
                            System.out.println("Qual o valor da aposta ?");
                            valorAposta = teclado.nextFloat();
                            teclado.nextLine(); // consume the leftover newline
                        } while(valorAposta < 0);
                        
                        do {
                            System.out.println("Qual jogo sera jogado ? (G - General ou A - Azar)");
                            String line = teclado.nextLine().toUpperCase();
                            if (!line.isEmpty()) {
                                opcao = line.charAt(0);
                            }
                        } while (opcao != 'G' && opcao != 'A');
                            
                        

                            if(opcao == 'G'){
                                jogadores[i].addJogo(i, opcao, valorAposta);

                                for(int j = 0; j < 13; j++){


                                    jogadores[i].getJogo()[jogadores[i].getnJogos()].rolarDados();

                                    System.out.println("Qual a jogada ? (1 - 13)");
                                    int jogada = teclado.nextInt();
                                    jogadores[i].getJogo()[jogadores[i].getnJogos()].validarJogada(jogada);





                                }


                            }

                            else if(opcao == 'A'){
                                jogadores[i].addJogo(i, opcao, valorAposta);
                                
                                //do{
                                    jogadores[i].getJogo()[jogadores[i].getnJogos()].rolarDados();
                                    s = jogadores[i].getJogo()[jogadores[i].getnJogos()].executarRegrasJogo();

                                //}while(s == false);
                                    if(s == true){
                                        System.out.println("Você ganhou!");
                                    }
                                    else{
                                        System.out.println("Você perdeu!");
                                    }
                            }
                    
                        
                    }
                    else if( jogadores[i] instanceof Maquina){

                        if (jogadores[i].getSaldo() > 20) {
                            valorAposta = (float)20;
                        }
                        else if (jogadores[i].getSaldo() > 10) {
                            valorAposta = (float)10;
                        }
                        else if(jogadores[i].getSaldo() > 5){
                            valorAposta = (float)5;
                        }
                        else{
                            valorAposta = (float)1;
                        }
                        
                        int opcaoMaquina = (int)(Math.random() * 2);

                        if(opcaoMaquina == 0){
                            jogadores[i].addJogo(i, 'G', valorAposta);

                            for(int j = 0; j < 13; j++){

                                jogadores[i].getJogo()[jogadores[i].getnJogos()-1].rolarDados();
                                //fazer a logica de jogada da maquina

                            }
                        }
                        else{
                            jogadores[i].addJogo(i, 'A', valorAposta);
                            do{
                                jogadores[i].getJogo()[jogadores[i].getnJogos()-1].rolarDados();
                                s = jogadores[i].getJogo()[jogadores[i].getnJogos()-1].executarRegrasJogo();

                            }while(s == false);
                        }
                    }
                }   
            }
        }
    }
    
    

    public void mostrarCartela() {
        int i;

        System.out.println("----- Cartela de Resultados -----\n");
        System.out.printf("%s", "\t");

        for (i = 0; i < qtdJogadores; i++)
            System.out.printf("%s", "\t" + jogadores[i].getNome() + "(" + jogadores[i].getTipoJogador() + ")\t");
        System.out.println();
    }

    public void extrato(){//Analisar este código
        int i;
        System.out.println("----- Extrato de Jogos -----\n");
        System.out.printf("%s", "\t");

        for (i = 0; i < qtdJogadores; i++)
            System.out.printf("%s", "\t" + jogadores[i].getNome() + "(" + jogadores[i].getTipoJogador() + ")\t");
        System.out.println();
    }

    public void estatisticas(){ //Analisar este código
        int i;
        System.out.println("----- Estatisticas -----\n");
        System.out.printf("%s", "\t");

        for (i = 0; i < qtdJogadores; i++)
            System.out.printf("%s", "\t" + jogadores[i].getNome() + "(" + jogadores[i].getTipoJogador() + ")\t");
        System.out.println();
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
