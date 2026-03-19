public class NoTri extends Object{
    private Object elemento;
    private NoTri pai;
    private NoTri esquerda;
    private NoTri direita;

    public NoTri(Object o){
        elemento = o;
        pai = null;
        esquerda = null;
        direita = null;
    }

  
    public void setElemento(Object o){
        this.elemento = o;
    }

    public Object getElemento(){
        return elemento;
    }

  
    public void setEsquerda(NoTri o){
        this.esquerda = o;  
    }

    public NoTri getEsquerda(){
        if(esquerda==null)
            return null;
        return esquerda;
    }

  
  public void setDireita(NoTri o){
        this.direita = o;  
    }

    public NoTri getDireita(){
        if(direita==null)
            return null;
        return direita;
    }

  
    public void setPai(NoTri o){
        this.pai = o;  
    }

    public NoTri getPai(){
        if(pai==null)
            return null;
        return pai;
    }

}
