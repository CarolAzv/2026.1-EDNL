import java.util.ArrayList;
import java.util.Iterator;
public class ArvoreBinaria{
    No raiz;
    int tamanho;

    public ArvoreBinaria(Object o){
        raiz = new No(o);
    }

    //acesso
    public No root(){
        return this.raiz;
    }
    
    public No parent(No no){
        return no.getPai();
    }

    public Iterator<Object> children(No no){
        ArrayList<Object> child = new ArrayList<>();
        child.add(no.getEsquerda());
        child.add(no.getDireita());
        return child.iterator();
    }


    //informação
    public int size(){
        return tamanho;
    }

    public int altura(){
        ArrayList<Integer> altura = new ArrayList<>();
        No no = raiz;
        int deep = 0;
        alturaCheck(no, deep, altura);
        int maior = 0;
        for(int i=0; i<altura.size(); i++){
            int see = altura.get(i);
            if(see>maior){
                maior = see;
            }
        }
        return maior;
    }

    public void alturaCheck(No no, int deep, ArrayList<Integer> altura){
        if(no.getElemento() == null){
            altura.add(deep);
            return;
        }
        deep++;
        if(no.getEsquerda()!=null){
            alturaCheck(no.getEsquerda(), deep, altura);
        }
        if(no.getDireita()!=null){
            alturaCheck(no.getDireita(), deep, altura);
        }
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public Iterator<Object> elementos(){
        ArrayList<Object> elementos = new ArrayList<>();
        No no = raiz;
        elementosCheck(no, elementos);
        return elementos.iterator();
    }

    public void elementosCheck(No no, ArrayList<Object> elementos){
        if(no.getElemento() == null){ //
            return;
        }
        elementos.add(no.getElemento());
        if(no.getEsquerda()!=null){
            elementosCheck(no.getEsquerda(), elementos);
        }
        if(no.getDireita()!=null){
            elementosCheck(no.getDireita(), elementos);
        }
    }

    public Object nos(){
        // Implementação futura
        return null;
    }


    //consulta
    public boolean isInternal(No no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return false;
        }
        return true;
    }
    
    public boolean isExternal(No no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return true;
        }
        return false;
    }

    public boolean isRoot(No no){
        if(no==raiz){
            return true;
        }
        return false;
    }

    public int depth(No no){
        if(no==raiz){
            return 0;
        }
        return 1+depth(parent(no));
    }


    //addicionar
    public void replace(No no, int o){
        // Implementação futura
    }

    public void addChild(int o){
        No novo = new No(o);
        No seeing = raiz;
        int foi = 0;
        while(foi!=1){
            if((int)seeing.getElemento()>o){
                if(seeing.getEsquerda()==null){
                    novo.setPai(seeing);
                    seeing.setEsquerda(novo);
                    foi++;
                }
                seeing=seeing.getEsquerda();
            }
            else if((int)seeing.getElemento()<o){
                if(seeing.getDireita()==null){
                    novo.setPai(seeing);
                    seeing.setDireita(novo);
                    foi++;
                }
                seeing=seeing.getDireita();
            }
            else if((int)seeing.getElemento()==o){
                System.err.println("Indece já existe!");
                foi++;
            }
        }
    }


    //remover
    public void remove(Object o){
        No seeing = raiz;
        //loop para achar o nó
        while(raiz.getElemento()!=o){
            if((int)seeing.getElemento()>(int)o){
                seeing=seeing.getEsquerda();
            }
            else if((int)seeing.getElemento()<(int)o){
                seeing=seeing.getDireita();
            }
        }

        //caso no não tenha filhos
        if(seeing.getEsquerda()==null && seeing.getDireita()==null){
            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){
                seeing.getPai().setDireita(null);
            }
            else{
                seeing.getPai().setEsquerda(null);
            }
            return;
        }

        //caso tenha apenas um filho
        else if(seeing.getEsquerda()!=null && seeing.getDireita()==null){
            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){
                seeing.getPai().setDireita(seeing.getEsquerda());
            }
            else{
                seeing.getPai().setEsquerda(seeing.getEsquerda());
            }
            return;
        }
        else if(seeing.getEsquerda()==null && seeing.getDireita()!=null){
            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){
                seeing.getPai().setDireita(seeing.getDireita());
            }
            else{
                seeing.getPai().setEsquerda(seeing.getDireita());
            }
            return;
        }

        //caso tenha os dois filhos
        else{
            //not done
            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){ //se o elemento for maior que o pai
                seeing.getPai().setDireita(seeing.getDireita());
                if(seeing.getDireita().getEsquerda()!=null && seeing.getDireita().getDireita()!=null){
                    NoTri au = seeing.getEsquerda();
                    while(seeing.getEsquerda()!=null || seeing.getDireita()!=null){
                        seeing=seeing.getDireita();
                    }
                    if(seeing.getEsquerda()==null){
                        seeing.setEsquerda(au);
                    }else {
                        seeing.setDireita(au);
                    }
                }
            }
            return;
        }
    }
}