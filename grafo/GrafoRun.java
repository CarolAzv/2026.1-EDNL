import java.util.Scanner;

public class GrafoRun{
    private static Grafo grafo;

    public static void main (String[] args){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Quantas Vertices tera o grafo: ");
        int quanVer = userInput.nextInt();
        userInput.nextLine();

        grafo = new Grafo(quanVer);

        String input;

        do{
            System.out.println("Para adicionar uma aresta use um numero");
            System.out.println("Para ver grafo use 'GRAFO'");
            System.out.println("Para remover uma vertice use 'VERTICE'");
            System.out.println("Para remover uma aresta 'ARESTA'");
            System.out.println("Para sair use 'EXIT'");
            input = userInput.nextLine();

            if(input.equals("GRAFO")){
                imprimirGrafo(grafo);
            }

            else if(input.equals("VERTICE")){
                System.out.println("Escreva qual vertice deseja remover: ");
                int ver = userInput.nextInt();

                grafo.removeVertice(ver);
            }

            else if(input.equals("ARESTA")){
                System.out.println("Escreva os vestices da aresta que deseja remover: ");
                int ver1 = userInput.nextInt();
                int ver2 = userInput.nextInt();

                grafo.removeAresta(ver1, ver2);
            }

            else if(input.equals("EXIT")){
                System.out.println("Saindo...");
            }

            else{
                System.out.println("Adicionar Aresta Direcionada? 'SIM' ou 'NAO'");
                String info = userInput.nextLine();

                System.out.println("Escreva quais vertices a arestra deve conectar: ");
                int ver1 = userInput.nextInt();
                int ver2 = userInput.nextInt();
                userInput.nextLine(); // limpa a quebra de linha, já que você usou nextInt()

                if(info.equals("SIM")){
                    grafo.inserirArestaDirecionada(ver1, ver2);
                }
                else{
                    grafo.inserirAresta(ver1, ver2);
                }
            }
        } while(!input.equals("EXIT"));
    }


    public static void imprimirGrafo(Grafo grafo){
        boolean[][] matrix = grafo.getMatrix();
        int totalVertices = grafo.getVertice();

        for(int i = 0; i < totalVertices; i++){
            for(int j = 0; j < totalVertices; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}