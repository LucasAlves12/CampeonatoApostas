import java.io.Serializable;
import java.util.Scanner;

public class JogoGeneral extends JogoDados implements Serializable {

    private int[] jogadas;
    private float valorAposta;

    public JogoGeneral(float valorAposta,float saldo) {

        super(5, "General", saldo);
        this.valorAposta = valorAposta;
        this.jogadas = new int[13];
        for (int i = 0; i < 13; i++) {
            jogadas[i] = -1;
        }

    }

    // getter da classe
    public int getJogoGeneral(int x) {
        return jogadas[x];
    }

  
    public float jogarJogos (char tipoJogador) {
        Scanner sc = new Scanner(System.in);
        int aux=0;
        if (tipoJogador == 'H' || tipoJogador == 'h') {
            for (int i = 0; i < 13; i++) {
                int escolha = 0;
                super.rolarDados();
                System.out.println("Dados:");
                for (int j = 0; j < 5; j++) {
                    System.out.print(super.getDado(j).getSideUp() + " ");
                }
                System.out.println("\n1\t2\t3\t4\t5\t6\t7\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n");
                for (int k = 0; k < 13; k++) {
                    aux = jogadas[k];
                    if (aux == -1)
                        System.out.print("- \t");
                    else
                        System.out.print(aux + "\t");
                }
                do{
                    System.out.println("\nEscolha uma jogada (1-13): ");
                    escolha = sc.nextInt();
                }while(escolha < 1 || escolha > 13 || jogadas[escolha-1] != -1);

                if(jogadas[escolha-1] == -1){
                    pontuarJogada(escolha, executarRegrasJogoG(escolha));
                }
                else{
                    while(jogadas[escolha-1] != -1 && (escolha < 1 || escolha >13)){
                        System.out.println("Jogada já realizada, escolha outra: ");
                        escolha = sc.nextInt();
                    }
                    pontuarJogada(escolha, executarRegrasJogoG(escolha));
                }
            }
            int total = 0;
            for (int i = 0; i < 12; i++) {
                total += jogadas[i];
            }
            float saldo = getSaldo();
            if(total > (jogadas[12]*2)){
                saldo += valorAposta;
                System.out.println("Você ganhou!!! Seu novo saldo : " + saldo);
                return (valorAposta*2);//ganhou
            }
            else{
                System.out.println("Você perdeu!!! Seu novo saldo : " + saldo);
                return 0;//perdeu
            }
        }
        /*else{//maquina
            for (int i = 0; i < 13; i++) {
                int escolha = 0;
                super.rolarDados();
                System.out.println("Dados:");
                for (int j = 0; j < 5; j++) {
                    System.out.print(super.getDado(j).getSideUp() + " ");
                }
        } */
        return 0;
    }

    // lógica do calculo de pontos na jogada
    public int executarRegrasJogoG(int x) {
        int cont[] = new int[6];// vetor que contem quantos vezes cada numero caiu nos dados na rodada
        int soma = 0;// soma de todos os dados

        for (int i = 0; i < 5; i++) {
            cont[super.getDado(i).getSideUp() - 1]++;// conta quantas vezes cada numero caiu nos dados

        }

        soma += super.somarFacesSorteadas(super.getDados());
        // boolean valida = false;

        switch (x) {// fazer a logica de return direto de cada pontuação de cada tipo de jogada
            // 13 cases, 13 tipos de jogada
            case 1:
                return cont[0]; // jogada de 1

            case 2:
                return cont[1] * 2; // jogada de 2

            case 3:
                return cont[2] * 3; // jogada de 3

            case 4:
                return cont[3] * 4; // jogada de 4

            case 5:
                return cont[4] * 5; // jogada de 5

            case 6:
                return cont[5] * 6; // jogada de 6

            case 7: // trinca
                for (int i = 0; i < 6; i++)
                    if (cont[i] >= 3)
                        return soma;

                return 0;// não tem trinca

            case 8: // Quarteto
                for (int i = 0; i < 6; i++)
                    if (cont[i] >= 4)
                        return soma;

                return 0;// não tem quarteto

            case 9:// Full-hand: trinca e par, vale 25
                for (int i = 0; i < 6; i++)
                    if (cont[i] == 1 || cont[i] == 5)
                        return 0;

                return 25;

            case 10:// sequencia alta
                for (int i = 1; i < 6; i++)
                    if (cont[i] == 0)
                        return 0;

                return 30;

            case 11:// sequencia baixa
                for (int i = 0; i < 5; i++)// começa do numero 1 e vai até o 5
                    if (cont[i] == 0)
                        return 0;

                return 40;

            case 12: // general
                for (int i = 0; i < 6; i++)
                    if (cont[i] == 5)
                        return 50;

                return 0;

            case 13:
                return soma;// jogada aleatória

        }
        return 0;

    }

    public void pontuarJogada(int pos, int pont) {
        jogadas[pos - 1] = pont;
    }


    public String toString() {
        String s = "";
        for (int i = 0; i < 5; i++) {
            s += super.getDado(i).getSideUp() + " ";
        }
        return s;
    }

}
