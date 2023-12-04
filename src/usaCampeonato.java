import java.util.Scanner;

public class usaCampeonato {
    public static void main(String[] args){//metodo main
        Scanner teclado = new Scanner(System.in);
        char opcao;
        Campeonato c = new Campeonato();
        do {//menu interativo para escolher as opcoes
            System.out.println("\n..:: Menu interativo ::..");
            System.out.println("a - Incluir jogador");
            System.out.println("b - Remover jogador");
            System.out.println("c - Executar rodada de apostas");
            System.out.println("d - Imprimir saldo dos jogadores");
            System.out.println("e - Imprimir extrato dos resultados");
            System.out.println("f - Imprimir estatísticas");
            System.out.println("g - Gravar em arquivo");
            System.out.println("h - Ler de arquivo");
            System.out.println("i - Sair");
            System.out.println("Entre com uma opcao do menu: ");
            opcao = teclado.nextLine().charAt(0);

            switch (opcao) {
                case 'a':
                    c.incluirJogador();
                    break;
                    
                case 'b':
                    c.removerJogador();
                    break;

                case 'c':
                    c.iniciarCampeonato();
                    break;

                case 'd':
                    c.imprimirSaldo();
                    break;

                case 'e':
                    c.extrato();    
                    break;

                case 'f':
                    c.estatisticas();   
                    break;
                case 'g':
                    c.gravarEmArquivo();
                    break;

                case 'h':
                    c.lerDoArquivo();
                    break;

                case 'i':
                    System.out.println("Saindo...");
                break;

                default:
                    System.out.println("Opcao invalida. Tente novamente");
            }
        } while (opcao != 'i');

        teclado.close();
    }
}

