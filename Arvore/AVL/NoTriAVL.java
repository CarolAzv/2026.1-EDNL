public class NoTriAVL extends Object{
    private Object elemento;
    private NoTriAVL pai;
    private NoTriAVL esquerda;
    private NoTriAVL direita;
    private int FB;

    //No para arvore AVL
    public NoTriAVL(Object o){
        elemento = o;
        pai = null;
        esquerda = null;
        direita = null;
        FB = 0;
    }

  
    public void setElemento(Object o){
        this.elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

  
    public void setEsquerda(NoTriAVL o){
        this.esquerda = o;  
    }

    public NoTriAVL getEsquerda(){
        if(esquerda==null)
            return null;
        return esquerda;
    }

  
  public void setDireita(NoTriAVL o){
        this.direita = o;  
    }

    public NoTriAVL getDireita(){
        if(direita==null)
            return null;
        return direita;
    }

  
    public void setPai(NoTriAVL o){
        this.pai = o;  
    }

    public NoTriAVL getPai(){
        if(pai==null)
            return null;
        return pai;
    }

    public void setFB(int o){
        this.FB = o;
    }

    public Object getFB(){
        return FB;
    }

}
