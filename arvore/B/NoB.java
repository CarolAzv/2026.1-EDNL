public class NoB{
    private Object[] chaves; // Array de chaves
    private int ordem; // (t) Grau ou Ordem
    private NoB[] filhos; // Array de filhos
    private int numChaves; // Número atual de chaves

    public NoB(int t){
    this.ordem = t; // Grau ou Ordem
    this.chaves = new Object[2 * t - 1]; //2t – 1 chaves
    this.filhos = new NoB[2 * t]; //2t filhos
    this.numChaves = 0; // Inicialmente sem chaves
    }

    // chaves
    public void addChave(Object o){
        chaves.add(o);
        numChaves++;
    }

    public Object getChaves(){
        return chaves;
    }


    // filhos
    public void addFilho(Object o){
        filhos.add(o);
        numChaves++;
    }

    public Object getFilho(){
        return filhos;
    }


    // ordem
    public void setOrdem(int o){
        this.ordem = o;
    }

    public int getOrdem(){
        return ordem;
    }


    //numChaves
    public void setNumChaves(int o){
        this.numChaves = o;
    }

    public int getNumChaves(){
        return numChaves;
    }
}