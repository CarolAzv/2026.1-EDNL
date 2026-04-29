public class NoRN extends Object{
    private Object elemento;
    private NoRN pai;
    private NoRN esquerda;
    private NoRN direita;
    private int qp; // quantos pretos tem no caminho nó até as folhas
    private String cor; // "vermelho" ou "preto"
    private boolean duploNegro;

    //No para arvore Rubro-Negra
    public NoRN(Object o){
        elemento = o;
        pai = null;
        esquerda = null;
        direita = null;
        qp = 0;
        cor = "vermelho"; // Novos nós são sempre vermelhos
        duploNegro = false;
    }


    //elemento
    public void setElemento(Object o){
        this.elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

  
    //pai
    public void setPai(NoRN o){
        this.pai = o;  
    }

    public NoRN getPai(){
        if(pai==null)
            return null;
        return pai;
    }


    //filho esquerda
    public void setEsquerda(NoRN o){
        this.esquerda = o;  
    }

    public NoRN getEsquerda(){
        if(esquerda==null)
            return null;
        return esquerda;
    }

  
    //filho direira
    public void setDireita(NoRN o){
        this.direita = o;  
    }

    public NoRN getDireita(){
        if(direita==null)
            return null;
        return direita;
    }

    //quantos pretos tem no caminho nó até as folhas
    public void setQp(int o){
        this.qp = o;
    }
    public int getQp(){
        return qp;
    }

    //cor
    public void setCor(String o){
        this.cor = o;
    }

    public String getCor(){
        return cor;
    }

    //duploNegro
    public void setDuploNegro(boolean o){
        this.duploNegro = o;
    }

    public boolean getDuploNegro(){
        return duploNegro;
    }

}