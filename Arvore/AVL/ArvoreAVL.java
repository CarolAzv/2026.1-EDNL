import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class ArvoreAVL{
    NoAVL raiz;

    public ArvoreAVL(Object o){
        raiz = new NoAVL(o);
    }


    //Devolve raiz
    public NoAVL root(){
        return this.raiz;
    }

    //------------------------------------------------------------------------------------//
    //----------------------------------|Checks Simples|----------------------------------//
    //------------------------------------------------------------------------------------//

    //É raiz?
    public boolean isRoot(NoAVL no){
        if(no==raiz){
            return true;
        }
        return false;
    }

    //É folha?
    public boolean isExternal(NoAVL no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return true;
        }
        return false;
    }

    //Não é folha?
    public boolean isInternal(NoAVL no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return false;
        }
        return true;
    }

    //-----------------------------------------------------------------------------//
    //----------------------------------|Rotação|----------------------------------//
    //-----------------------------------------------------------------------------//

    //addicionar FB - Começa de um no arumado e com o pai não feito
    public void addingFB(NoAVL no){ 
        NoAVL seeing = no;
        NoAVL pai = no.getPai();
        while(pai != null && pai.getPai() != null && ((int)(pai.getPai()).getFB()==0 || pai.getElemento()==null)){ // Checando antecessor para parar

            if((int)(pai.getPai()).getFB()==2){ // Checando se esta desbalanceado para a esquerda
                direitaSimples(pai.getPai());
                return;
            }
            if((int)(pai.getPai()).getFB()==-2){ // Checando se esta desbalanceado para a direita
                esquerdaSimples(pai.getPai());
                return;
            }

            if(pai.getDireita()==seeing){ // Checando se o filho direita é o seeing
                pai.setFB((int)pai.getFB()+1);
                seeing = pai;
                pai = pai.getPai();
            }
            pai.setFB((int)pai.getFB()-1); // Caso não seja
            seeing = pai;
            pai = pai.getPai();
        }
        return;
    }

    //remover FB - Começa de um no arumado e com o pai não feito
    public void removingFB(NoAVL no){
        NoAVL seeing = no;
        NoAVL pai = no.getPai();
        while(pai != null && pai.getPai() != null && ((int)(pai.getPai()).getFB()!=0 || pai.getElemento()==null)){ // Checando antecessor para parar

            if((int)(pai.getPai()).getFB()==2){ // Checando se esta desbalanceado para a esquerda
                direitaSimples(pai.getPai());
                return;
            }
            if((int)(pai.getPai()).getFB()==-2){ // Checando se esta desbalanceado para a direita
                esquerdaSimples(pai.getPai());
                return;
            }

            if(pai.getDireita()==seeing){ // Checando se o filho direita é o seeing
                pai.setFB((int)pai.getFB()-1);
                seeing = pai;  // Atualizando o seeing e o pai para o proximo
                pai = pai.getPai();
            }
            pai.setFB((int)pai.getFB()+1); // Caso não seja
            seeing = pai;
            pai = pai.getPai();
        }
        return;
    }

    //Esquerda Simples - Rotação para esqerda \ para ^
    public void esquerdaSimples(NoAVL o){
        NoAVL filho = o.getDireita();
        if(o.getPai() != null){  //checando sé tem pai para atualizar ele também
            NoAVL pai = o.getPai();
            if(pai.getDireita() == o){ //checando pelo filho certo
                pai.setDireita(filho);
            } else {
                pai.setEsquerda(filho);
            }
        }
        filho.setPai(o.getPai());
        o.setPai(filho);
        o.setDireita(filho.getEsquerda());
        filho.setEsquerda(o);

        o.setFB((int)o.getFB() + 1 - min((int)filho.getFB(), 0));
        filho.setFB((int)filho.getFB() + 1 + max((int)o.getFB(), 0));
        addingFB(filho);
        return;
    }

    //Direita Simples - Rotação para direita / para ^
    public void direitaSimples(NoAVL o){
        NoAVL filho = o.getEsquerda();
        if(o.getPai() != null){ //checando sé tem pai para atualizar ele também
            NoAVL pai = o.getPai();
            if(pai.getEsquerda() == o){ //checando pelo filho certo
                pai.setEsquerda(filho);
            } else {
                pai.setDireita(filho);
            }
        }
        filho.setPai(o.getPai());
        o.setPai(filho);
        o.setEsquerda(filho.getDireita());
        filho.setDireita(o);

        o.setFB((int)o.getFB() - 1 - min((int)filho.getFB(), 0));
        filho.setFB((int)filho.getFB() - 1 + max((int)o.getFB(), 0));
        addingFB(filho);
        return;
    }

    //-------------------------------------------------------------------------------//
    //----------------------------------|Adicionar|----------------------------------//
    //-------------------------------------------------------------------------------//

    public void addChild(int o){
        NoAVL novo = new NoAVL(o);
        NoAVL seeing = raiz;
        int foi = 0; // Marcado para confirma que foi colocado
        while(foi!=1){
            if((int)seeing.getElemento()>o){ // Checando se o elemento do no atual é maior que o novo
                if(seeing.getEsquerda()==null){ //Se sim e o no a esquerda é fazio, coloca o no novo aqui
                    novo.setPai(seeing);
                    seeing.setEsquerda(novo);
                    foi++;
                    addingFB(novo);
                }
                seeing=seeing.getEsquerda(); // Move o no atual par a esquerda
            }
            else if((int)seeing.getElemento()<o){  // Checando se o elemento do no atual é menor que o novo
                if(seeing.getDireita()==null){ //Se sim e o no a direita é fazio, coloca o no novo aqui
                    novo.setPai(seeing);
                    seeing.setDireita(novo);
                    foi++;
                    addingFB(novo);
                }
                seeing=seeing.getDireita();  // Move o no atual par a direita
            }
            else if((int)seeing.getElemento()==o){ // Checando se o elemento do no atual é igual ao novo
                System.err.println("Indece já existe!");
                foi++;
            }
        }
        return;
    }

    //-----------------------------------------------------------------------------//
    //----------------------------------|Remover|----------------------------------//
    //-----------------------------------------------------------------------------//

    public void removeChild(int o){
        NoAVL seeing = raiz;
        //loop para achar o nó
        while((int)seeing.getElemento()!=o){ // Checando para ver se o no seeing é o que deseja
            if((int)seeing.getElemento()>o){
                seeing=seeing.getEsquerda();
            }
            else if((int)seeing.getElemento()<o){
                seeing=seeing.getDireita();
            }
        }

        //caso no não tenha filhos
        if(seeing.getEsquerda()==null && seeing.getDireita()==null){
            if((int)seeing.getElemento() > (int)(seeing.getPai()).getElemento()){
                (seeing.getPai()).setDireita(null);
                removingFB(seeing);
                seeing.setPai(null);

            }
            else{
                seeing.getPai().setEsquerda(null);
                removingFB(seeing);
                seeing.setPai(null);
            }
            return;
        }


        //caso tenha apenas um filho
        else if(seeing.getEsquerda()!=null && seeing.getDireita()==null){ // Filho a esquerda
            if((int)seeing.getElemento() > (int)(seeing.getPai()).getElemento()){ // Checando se o filho é maior que o pai
                seeing.getPai().setDireita(seeing.getEsquerda()); // Se sim, passa o filho para a direita do pai
            } else {
                seeing.getPai().setEsquerda(seeing.getEsquerda()); // Se não, passa o filho para a esquerda do pai
            }

            removingFB(seeing);
            seeing.setPai(null);
            seeing.setEsquerda(null);
            return;
        }

        else if(seeing.getEsquerda()==null && seeing.getDireita()!=null){ // filho a direita
            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){ // Checando se o filho é maior que o pai
                seeing.getPai().setDireita(seeing.getDireita()); // Se sim, passa o filho para a direita do pai
            } else {
                seeing.getPai().setEsquerda(seeing.getDireita()); // Se não, passa o filho para a esquerda do pai
            }

            removingFB(seeing);
            seeing.setPai(null);
            seeing.setDireita(null);
            return;
        }


        //caso tenha os dois filhos
        else{
            NoAVL sucesor = seeing.getDireita(); // Achando o sucesor
            while(sucesor.getEsquerda()!=null){ // Procurando um que não tem filho a esquerda
                sucesor = sucesor.getEsquerda();
            }
            seeing.setElemento(sucesor.getElemento()); // Transformando o seeing no sucesor

            (sucesor.getPai()).setEsquerda(null); // Removendo a coneção
            removingFB(sucesor);
            sucesor.setPai(null);
            return;
        }
    }

    //------------------------------------------------------------------------------//
    //----------------------------------|ITERADOR|----------------------------------//
    //------------------------------------------------------------------------------//

    //DEVERIA pegar todos os nos
    public Map.Entry<ArrayList<Object>, Integer> elementos(){
        ArrayList<Object> elementosEfb = new ArrayList<>();
        NoAVL no = raiz;
        int altura = 1;
        elementosEfb.add(no.getElemento());
        elementosEfb.add(no.getFB());
        elementosCheck(no, elementosEfb, altura);
        return new AbstractMap.SimpleEntry<>(elementosEfb, altura);
    }

    //DEVERIA pegar os nos e seus FB por linha
    public void elementosCheck(NoAVL no, ArrayList<Object> elementosEfb, int altura){
        if(no == null){
            return;
        }
        //pegando info esquerda
        if(no.getEsquerda() != null){
            elementosEfb.add((no.getEsquerda()).getElemento());
            elementosEfb.add(no.getEsquerda().getFB());
        }

        //pegando info direita
        if(no.getDireita() != null){
            elementosEfb.add((no.getDireita()).getElemento());
            elementosEfb.add(no.getDireita().getFB());
        }
        altura = altura+1;

        //indo pegar os proximos numeros
        elementosCheck(no.getEsquerda(), elementosEfb, altura);
        elementosCheck(no.getDireita(), elementosEfb, altura);
        
        return;
    }
}