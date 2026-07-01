public class Grafo{
    private boolean[][] Matrix;
    private int Vertice;

    public Grafo(int Vertice){
        this.Vertice = Vertice;
        Matrix = new boolean[Vertice][Vertice];
    }


    public boolean[][] getMatrix(){
        return Matrix;
    }

    public int getVertice(){
        return Vertice;
    }

    public Object[] arestasIncidentes(int ver){
        Object[] incidentes = new Object[Matrix.length];

        for(int i = 0; i < Matrix.length; i++){
            incidentes[i] = Matrix[i][ver];
        }

        return incidentes;
    }

    //public Object[] vertices(){}

    //public Object[] arestas(){}

    //public Object[] finalVertices(int are){}

    public boolean éAdjacente(int ver1, int ver2){
        if(Matrix [ver1][ver2] == true){
            return true;
        }
        return false;
    }

    public boolean eDirecionado(int ver1, int ver2){
        if(Matrix [ver1][ver2] == true && Matrix [ver2][ver1] == false){
            return true;
        }
        return false;
    }

 
//------------------------------------------------------------------------------------//


    public void inserirVertice(int num){
        Vertice = Vertice + num;
        boolean[][] novaMatriz = new boolean[Vertice][Vertice];

        for(int i = 0; i < Matrix.length; i++) {
            for(int j = 0; j < Matrix[i].length; j++) {
                novaMatriz[i][j] = Matrix[i][j];
            }
        }

        Matrix = novaMatriz;
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
                Matrix[i][j] = Matrix[i][j+1];
            } 
        }

        Vertice--;
    }

    public void removeAresta(int ver1, int ver2){
        Matrix [ver1][ver2] = false;
        Matrix [ver2][ver1] = false;
    }
}