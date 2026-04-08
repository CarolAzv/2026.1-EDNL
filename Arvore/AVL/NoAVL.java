public class NoAVL extends Object{
    private Object elemento;
    private NoAVL pai;
    private NoAVL esquerda;
    private NoAVL direita;
    private int FB;

    //No para arvore AVL
    public NoAVL(Object o){
        elemento = o;
        pai = null;
        esquerda = null;
        direita = null;
        FB = 0;
    }


    //elemento
    public void setElemento(Object o){
        this.elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

  
    //pai
    public void setPai(NoAVL o){
        this.pai = o;  
    }

    public NoAVL getPai(){
        if(pai==null)
            return null;
        return pai;
    }


    //filho esquerda
    public void setEsquerda(NoAVL o){
        this.esquerda = o;  
    }

    public NoAVL getEsquerda(){
        if(esquerda==null)
            return null;
        return esquerda;
    }

  
    //filho direira
    public void setDireita(NoAVL o){
        this.direita = o;  
    }

    public NoAVL getDireita(){
        if(direita==null)
            return null;
        return direita;
    }


    //FB
    public void setFB(int o){
        this.FB = o;
    }

    public Object getFB(){
        return FB;
    }

}