import java.util.ArrayList;

public class Grafo{
    private boolean[][] Matrix;
    private int Vertice;

    public Grafo(int Vertice){
        this.Vertice = Vertice;
        Matrix = new boolean[Vertice][Vertice];
    }


    public ArrayList arestasIncidentes(ver){}

    public ArrayList vertices(){}

    public ArrayList arestas(){}

    public ArrayList finalVertices(int are){}

    public aaaaaaaaa oposto(int ver, int are){}

    public boolean éAdjacente(int ver, w){}

    public boolean eDirecionado(e){}

 
//------------------------------------------------------------------------------------//


    public void inserirVertice(int num){
        Vertice = Vertice + num;
        boolean[][] novaMatriz = new int[Vertice][Vertice];

        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[i].length; j++) {
                novaMatriz[i][j] = Matrix[i][j];
            }
        }

        Matrix = novaMatriz;
        novaMatriz.clear();
    }

    public void inserirAresta(int ver1, int ver2){
        Matrix [ver1][ver2] = true;
        Matrix [ver2][ver1] = true;
    }

    public void inserirArestaDirecionada(int ver1, int ver2){
        Matrix [ver1][ver2] = true;
    }


    public void removeVertice(int ver){
        for(int i=ver; i<Vertice-1; i++){
            for(int j=0; j<Vertice; j++){
                Matrix[i][j] = Matrix[i+1][j];
            }
        }

        for(int i=0; i<Vertice-1; i++){
            for(int j=ver; j<Vertice-1; j++){
                Matrix[i][j] = Matrix[i+1][j];
            }
        }

        Vertice--;
    }

    public void removeAresta(int ver1, int ver2){
        Matrix [ver1][ver2] = false;
        Matrix [ver2][ver1] = false;
    }
}