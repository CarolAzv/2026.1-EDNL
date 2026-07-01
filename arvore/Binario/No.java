public class No extends Object{
    private Object elemento;
    private No pai;
    private No esquerda;
    private No direita;

    //No para arvore Rubro-Negra
    public No(Object o){
        elemento = o;
        pai = null;
        esquerda = null;
        direita = null;
    }


    //elemento
    public void setElemento(Object o){
        this.elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

  
    //pai
    public void setPai(No o){
        this.pai = o;  
    }

    public No getPai(){
        if(pai==null)
            return null;
        return pai;
    }


    //filho esquerda
    public void setEsquerda(No o){
        this.esquerda = o;  
    }

    public No getEsquerda(){
        if(esquerda==null)
            return null;
        return esquerda;
    }

  
    //filho direira
    public void setDireita(No o){
        this.direita = o;  
    }

    public No getDireita(){
        if(direita==null)
            return null;
        return direita;
    }

}