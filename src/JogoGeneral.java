import java.io.Serializable;
public class JogoGeneral extends JogoDados implements Serializable{

    private int[] jogadas = new int[13];
    private float valorAposta;

    public JogoGeneral(float valorAposta) {

        super(5, "General", 100);
        this.valorAposta = valorAposta;

    }


    //Dados são sorteados aleatoriamente a cada rodada
    public void rolarDados() { 
        for (int i = 0; i < 5; i++) {
            super.rolarDados();
        }
        System.out.println("Valores obtidos: "+this.toString());
    }

    //getter da classe
    public int getJogoGeneral(int x) {
        return jogadas[x];
    }

    public void JogadasDispoiveis(){
        System.out.println("1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13");
        for(int i = 0;i < 13;i++){
            if(jogadas[i] == 0)System.out.print("- \t");
            else System.out.print(jogadas[i] + "\t");
        }
    }

    //lógica do calculo de pontos na jogada
    public int executarRegrasJogoG(int x) {
        int cont[] = new int[6];//vetor que contem quantos vezes cada numero caiu nos dados na rodada
        int soma = 0;//soma de todos os dados

        

        for (int i = 0; i < 5; i++) {
            cont[super.getDado(i).getSideUp() - 1]++;//conta quantas vezes cada numero caiu nos dados
           
        }

         soma += super.somarFacesSorteadas(super.getDados());
        //boolean valida = false;

        switch (x) {// fazer a logica de return direto de cada pontuação de cada tipo de jogada
            // 13 cases, 13 tipos de jogada
            case 1:     return cont[0]; // jogada de 1
              
            case 2:     return cont[1] * 2; // jogada de 2
                
            case 3:     return cont[2] * 3; // jogada de 3
                
            case 4:     return cont[3] * 4; // jogada de 4
               
            case 5:     return cont[4] * 5; // jogada de 5
               
            case 6:     return cont[5] * 6; // jogada de 6
                

            case 7:        //trinca
                for (int i = 0; i < 6; i++) 
                    if (cont[i] >= 3)return soma;
                             
                 return 0;// não tem trinca    

            case 8:       //Quarteto         
                for (int i = 0; i < 6; i++) 
                    if (cont[i] >= 4)return soma;
                        
                 return 0;// não tem quarteto                                  
                
            case 9://Full-hand: trinca e par, vale 25
                for (int i = 0; i < 6; i++)
                    if(cont[i] == 1 || cont[i] == 5) return 0;
                   
                return 25;
                        
            case 10://sequencia alta
                for (int i = 1; i < 6; i++)
                    if(cont[i] == 0)return 0;                   
                
                return 30;
                 
            case 11://sequencia baixa
                for (int i = 0; i < 5; i++)//começa do numero 1 e vai até o 5
                    if(cont[i] == 0) return 0; 
                                     
                return 40;

            case 12: //general
                for(int i = 0;i < 6;i++)
                    if(cont[i] == 5)return 50;
                        
                 return 0;

            case 13:      return soma;//jogada aleatória

        }
        return 0;
        
    }

    public void pontuarJogada(int pos, int pont) {
        jogadas[pos-1] = pont;
    }

    public String toString(){
        String s = "";
        for(int i = 0;i < 5;i++){
            s += super.getDado(i).getSideUp() + " ";
        }
        return s;
    }


}
