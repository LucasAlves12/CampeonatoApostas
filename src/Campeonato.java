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
    private File arq = new File("CampeonatoApostas.dat");
    private Scanner teclado = new Scanner(System.in);

    public Campeonato() {
        jogadores = new Jogador[10];
        qtdJogadores = 0;
        maxJogadores = 10;
    }

    // incluir jogador ao campeonato
    public void incluirJogador() {
        char p;

        System.out.println("Insira seu nome: ");
        String nome = teclado.nextLine();
        for (int i = 0; i < qtdJogadores; i++) {
            if (jogadores[i].getNome().equals(nome)) {
                System.out.println("Jogador já cadastrado");
                return;
            }
        }

        do {
            System.out.println("Tipo de jogador (H - humano ou M - maquina):");
            p = teclado.nextLine().toUpperCase().charAt(0);
        } while (p != 'H' && p != 'M');

        if (qtdJogadores < maxJogadores) {

            if (p == 'H') {

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
                cpf = teclado.nextLine();
                System.out.println("Jogador inserido com sucesso!\n");
            } else {
                jogadores[qtdJogadores] = new Maquina(nome);
                qtdJogadores++;
                System.out.println("Jogador inserido com sucesso!\n");
            }
        } else {
            System.out.println("Não é possivel inserir mais jogadores !!!");
        }
    }

    // remover jogador do campeonato
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

    // inicia o campeonato
    public void iniciarCampeonato() {
        Float valorAposta;
        char opcao = ' ';

        if (qtdJogadores == 0) {
            System.out.println("Não há jogadores suficientes para iniciar o campeonato");
            return;
        }
        // se não houver jogadores
        // suficientes

        for (int rodada = 0; rodada < 10; rodada++) {
            for (int i = 0; i < qtdJogadores; i++) {

                if (jogadores[i].getSaldo() <= 0) {
                    System.out
                            .println("O jogador " + jogadores[i].getNome() + " não possui saldo suficiente para jogar");
                    break;
                }

                if(jogadores[i].getnJogos() == 10) {
                    System.out.println("O jogador " + jogadores[i].getNome() + " já jogou 10 vezes, este é o limite");
                    break;
                }

                if (jogadores[i] instanceof Humano) {// humano

                    System.out.println("Jogador: " + jogadores[i].getNome());

                    valorAposta = jogadores[i].escolherAposta();
                    opcao = jogadores[i].escolherJogo();

                    if (opcao == 'G') {
                        jogadores[i].jogarGeneral(rodada, valorAposta);
                    }

                    else if (opcao == 'A') {
                        jogadores[i].jogarAzar(rodada, valorAposta);

                    }

                } else if (jogadores[i] instanceof Maquina) {// maquina

                    System.out.println("Jogador: " + jogadores[i].getNome());

                    valorAposta = jogadores[i].escolherAposta();
                    int opcaoMaquina = (int) Math.floor((Math.random() * 2));

                    if (opcaoMaquina == 0)
                        jogadores[i].jogarGeneral(rodada, valorAposta);
                    else
                        jogadores[i].jogarAzar(rodada, valorAposta);

                }
            }
        }

    }

    // imprime o saldo dos jogadores
    public void imprimirSaldo() {

        int opcao;

        do {
            System.out.println("Imprimir saldo somente de humanos (1)");
            System.out.println("Imprimir saldo somente de maquinas (2)");
            System.out.println("Imprimir saldo de todos (3)");
            opcao = teclado.nextInt();
        } while (opcao < 1 || opcao > 3);

        switch (opcao) {
            case 1:
                for (int i = 0; i < qtdJogadores; i++) {
                    if (jogadores[i] instanceof Humano) {
                        System.out.println("Nome: " + jogadores[i].getNome() + " Saldo: " + jogadores[i].getSaldo());
                    }
                }
                break;

            case 2:
                for (int i = 0; i < qtdJogadores; i++) {
                    if (jogadores[i] instanceof Maquina) {
                        System.out.println("Nome: " + jogadores[i].getNome() + " Saldo: " + jogadores[i].getSaldo());
                    }
                }
                break;

            case 3:
                if (qtdJogadores == 0)
                    System.out.println("Não há jogadores cadastrados");
                for (int i = 0; i < qtdJogadores; i++) {
                    System.out.println("Nome: " + jogadores[i].getNome() + " Saldo: " + jogadores[i].getSaldo());
                }
                break;
        }

    }

    // imprime o extrato dos resultados
    public void extrato() {
        int opcaoJogador;
        int opcaoJogo;

        do {
            System.out.println("Imprimir extrato somente de humanos (1)");
            System.out.println("Imprimir extrato somente de maquinas (2)");
            System.out.println("Imprimir extrato de todos (3)");
            opcaoJogador = teclado.nextInt();
        } while (opcaoJogador < 1 || opcaoJogador > 3);

        do {
            System.out.println("Imprimir extrato de jogo do azar (1)");
            System.out.println("Imprimir extrato de jogo general (2)");
            System.out.println("Imprimir extrato de todos (3)");
            opcaoJogo = teclado.nextInt();
        } while (opcaoJogo < 1 || opcaoJogo > 3);

        if (opcaoJogador == 1 && opcaoJogo == 1)
            for (int j = 0; j < qtdJogadores; j++)
                if (jogadores[j] instanceof Humano) {
                    System.out.println("\nNome: " + jogadores[j].getNome());
                    for (int i = 0; i < jogadores[j].getnJogos(); i++)
                        if (jogadores[j].getJogo()[i] instanceof JogoAzar)
                            jogadores[j].getJogo()[i].extrato();
                }
        if (opcaoJogador == 1 && opcaoJogo == 2)
            for (int j = 0; j < qtdJogadores; j++)
                if (jogadores[j] instanceof Humano) {
                    System.out.println("\nNome: " + jogadores[j].getNome());
                    for (int i = 0; i < jogadores[j].getnJogos(); i++)
                        if (jogadores[j].getJogo()[i] instanceof JogoGeneral)
                            jogadores[j].getJogo()[i].extrato();
                }
        if (opcaoJogador == 1 && opcaoJogo == 3)
            for (int j = 0; j < qtdJogadores; j++)
                if (jogadores[j] instanceof Humano) {
                    System.out.println("\nNome: " + jogadores[j].getNome());
                    for (int i = 0; i < jogadores[j].getnJogos(); i++)
                        jogadores[j].getJogo()[i].extrato();
                }

        if (opcaoJogador == 2 && opcaoJogo == 1)
            for (int j = 0; j < qtdJogadores; j++)
                if (jogadores[j] instanceof Maquina) {
                    System.out.println("\nNome: " + jogadores[j].getNome());
                    for (int i = 0; i < jogadores[j].getnJogos(); i++)
                        if (jogadores[j].getJogo()[i] instanceof JogoAzar)
                            jogadores[j].getJogo()[i].extrato();
                }

        if (opcaoJogador == 2 && opcaoJogo == 2)
            for (int j = 0; j < qtdJogadores; j++)
                if (jogadores[j] instanceof Maquina) {
                    System.out.println("\nNome: " + jogadores[j].getNome());
                    for (int i = 0; i < jogadores[j].getnJogos(); i++)
                        if (jogadores[j].getJogo()[i] instanceof JogoGeneral)
                            jogadores[j].getJogo()[i].extrato();
                }

        if (opcaoJogador == 2 && opcaoJogo == 3)
            for (int j = 0; j < qtdJogadores; j++)
                if (jogadores[j] instanceof Maquina) {
                    System.out.println("\nNome: " + jogadores[j].getNome());
                    for (int i = 0; i < jogadores[j].getnJogos(); i++)
                        jogadores[j].getJogo()[i].extrato();
                }

        if (opcaoJogador == 3 && opcaoJogo == 1)
            for (int j = 0; j < qtdJogadores; j++) {
                System.out.println("\nNome: " + jogadores[j].getNome());
                for (int i = 0; i < jogadores[j].getnJogos(); i++)
                    if (jogadores[j].getJogo()[i] instanceof JogoAzar)
                        jogadores[j].getJogo()[i].extrato();
            }

        if (opcaoJogador == 3 && opcaoJogo == 2)
            for (int j = 0; j < qtdJogadores; j++) {
                System.out.println("\nNome: " + jogadores[j].getNome());
                for (int i = 0; i < jogadores[j].getnJogos(); i++)
                    if (jogadores[j].getJogo()[i] instanceof JogoGeneral)
                        jogadores[j].getJogo()[i].extrato();
            }

        if (opcaoJogador == 3 && opcaoJogo == 3)
            for (int j = 0; j < qtdJogadores; j++) {
                System.out.println("\nNome: " + jogadores[j].getNome());
                for (int i = 0; i < jogadores[j].getnJogos(); i++) {
                    jogadores[j].getJogo()[i].extrato();
                }
            }

    }

    // imprime as estatisticas sobre os dados
    public void estatisticas() {
        int opcao1;
        int[] Estatistica = new int[6];
        int Estatisticaaux;

        for (int i = 0; i < 5; i++) {
            Estatistica[i] = 0;
        }

        do {
            // Solicitar qual estatistica mostrar
            System.out.println("Escolha pelo numero qual tipo de estatisticas voce deseja ver:");
            System.out.println("1  Por tipo de jogador");
            System.out.println("2  Por tipo de jogo escolhido por jogador");
            System.out.println("3  total por jogos General ou azar");
            System.out.println("4  Total do Campeonato");

            opcao1 = teclado.nextInt();
        } while (opcao1 < 1 || opcao1 > 4);

        switch (opcao1) {
            case 1:

                do {
                    System.out.println("1 - Humano");
                    System.out.println("2 - Maquina");
                    opcao1 = teclado.nextInt();

                } while (opcao1 < 1 || opcao1 > 2);

                if (opcao1 == 1) {
                    for (int i = 0; i < qtdJogadores; i++) {
                        if (jogadores[i] instanceof Humano) {
                            System.out.println("Estatisticas do jogador " + jogadores[i].getNome());
                            for (int j = 0; j < jogadores[i].getnJogos(); j++) {
                                for (int k = 0; k < 6; k++) {
                                    Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                    Estatistica[k] += Estatisticaaux;
                                }
                            }
                        }
                    }

                } else if (opcao1 == 2) {
                    for (int i = 0; i < qtdJogadores; i++) {
                        if (jogadores[i] instanceof Maquina) {
                            for (int j = 0; j < jogadores[i].getnJogos(); j++) {
                                for (int k = 0; k < 6; k++) {
                                    Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                    Estatistica[k] += Estatisticaaux;
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < 6; i++) {
                    System.out.println("Numero " + (i + 1) + " numero de vezes que saiu: " + Estatistica[i]);
                }

                break;

            case 2:

                do {
                    System.out.println("1 - jogo de Azar");
                    System.out.println("2 - jogo General");
                    opcao1 = teclado.nextInt();

                } while (opcao1 < 1 || opcao1 > 2);

                System.out.println("----- Jogadores -----");
                for (int i = 0; i < qtdJogadores; i++) {
                    System.out.println(jogadores[i].getNome() + "\t");
                }

                System.out.println("Informe o nome do jogador:");
                teclado.nextLine();
                String nomeAux = teclado.nextLine();

                for (int i = 0; i < qtdJogadores; i++) {
                    if (jogadores[i].getNome().equals(nomeAux)) {
                        System.out.println("Estatisticas do jogador " + jogadores[i].getNome() + " neste jogo");
                        for (int j = 0; j < jogadores[i].getnJogos(); j++) {
                            if (opcao1 == 1 && jogadores[i].getJogo()[j] instanceof JogoAzar) {
                                for (int k = 0; k < 6; k++) {
                                    Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                    Estatistica[k] += Estatisticaaux;
                                }
                            } else if (opcao1 == 2 && jogadores[i].getJogo()[j] instanceof JogoGeneral) {
                                for (int k = 0; k < 6; k++) {
                                    Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                    Estatistica[k] += Estatisticaaux;
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < 6; i++) {
                    System.out.println("Numero " + (i + 1) + " numero de vezes que saiu: " + Estatistica[i]);
                }

                break;

            case 3:

                do {
                    System.out.println("1 - jogo de Azar");
                    System.out.println("2 - jogo General");
                    opcao1 = teclado.nextInt();

                } while (opcao1 < 1 || opcao1 > 2);

                for (int i = 0; i < qtdJogadores; i++) {

                    for (int j = 0; j < jogadores[i].getnJogos(); j++) {
                        if (opcao1 == 1 && jogadores[i].getJogo()[j] instanceof JogoAzar) {
                            for (int k = 0; k < 6; k++) {
                                Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                Estatistica[k] += Estatisticaaux;
                            }
                        } else if (opcao1 == 2 && jogadores[i].getJogo()[j] instanceof JogoGeneral) {
                            for (int k = 0; k < 6; k++) {
                                Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                Estatistica[k] += Estatisticaaux;
                            }
                        }
                    }

                }

                for (int i = 0; i < 6; i++) {
                    System.out.println("Numero " + (i + 1) + " numero de vezes que saiu: " + Estatistica[i]);
                }

            case 4:
                
                for (int i = 0; i < qtdJogadores; i++) {

                    for (int j = 0; j < jogadores[i].getnJogos(); j++) {
                    
                            for (int k = 0; k < 6; k++) {
                                Estatisticaaux = jogadores[i].getJogo()[j].getEstatisticas(k);
                                Estatistica[k] += Estatisticaaux;
                            }
                        
                    }
                }

                for (int i = 0; i < 6; i++) {
                    System.out.println("Numero " + (i + 1) + " numero de vezes que saiu: " + Estatistica[i]);
                }
            
            break;


            default:
                break;
        }
    }

    // grava em arquivo .dat a rodada executada
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

    // le os dados do arquvo .dat
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

            for (Jogador j : jogadores) {
                if (j != null) {
                    qtdJogadores++;
                }
            }

            System.out.println("Dados lidos com sucesso!\n");
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

    }

}
