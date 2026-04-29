import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class ArvoreRN{
    NoRN raiz;

    public ArvoreRN(Object o){
        raiz = new NoRN(o);
        raiz.setCor("preto");
    }


    //Devolve raiz
    public NoRN root(){
        return this.raiz;
    }

    //------------------------------------------------------------------------------------//
    //----------------------------------|Checks Simples|----------------------------------//
    //------------------------------------------------------------------------------------//

    //É raiz?
    public boolean isRoot(NoRN no){
        if(no==raiz){
            return true;
        }
        return false;
    }

    //É folha?
    public boolean isExternal(NoRN no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return true;
        }
        return false;
    }

    //Não é folha?
    public boolean isInternal(NoRN no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return false;
        }
        return true;
    }

    //É vermelho?
    public boolean isRed(NoRN no){
        if(no.getCor().equals("vermelho")){
            return true;
        }
        return false;
    }

    //É preto?
    public boolean isBlack(NoRN no){
        if(no.getCor().equals("preto")){
            return true;
        }
        return false;
    }

    public NoRN locate(Object o){
        NoRN seeing = raiz;
         while((int)seeing.getElemento()!=(int)o){ // Checando para ver se o no seeing é o desejado
            if((int)seeing.getElemento()>(int)o){
                seeing=seeing.getEsquerda();
            }
            else if((int)seeing.getElemento()<(int)o){
                seeing=seeing.getDireita();
            }
        }
        return seeing;
    }

    //-----------------------------------------------------------------------------//
    //----------------------------------|Rotação|----------------------------------//
    //-----------------------------------------------------------------------------//

    //Esquerda Simples - Rotação para esqerda \ para ^
    public void esquerdaSimples(NoRN o){
        NoRN filho = o.getDireita();
        if(o.getPai() != null){  //checando sé tem pai para atualizar ele também
            NoRN pai = o.getPai();
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

        return;
    }

    //Direita Simples - Rotação para direita / para ^
    public void direitaSimples(NoRN o){
        NoRN filho = o.getEsquerda();
        if(o.getPai() != null){ //checando sé tem pai para atualizar ele também
            NoRN pai = o.getPai();
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

        return;
    }

    //-----------------------------------------------------------------------------//
    //------------------------------------|Cor|------------------------------------//
    //-----------------------------------------------------------------------------//

    public void mudarCor(NoRN no, String cor){
        no.setCor(cor);
    }

    public void checkCor(NoRN no){
        NoRN filho = no;

        //check se é raiz
        if(isRoot(no)){
            mudarCor(no, "preto");
            return;
        }

        NoRN pai = no.getPai();

        //caso1 - check se o pai é preto
        if (pai == null || isBlack(pai)) {
            return;
        }

        //pegando o avô
        NoRN avo = pai.getPai();
        if (avo == null) return; // pai vermelho sem avô: situação inválida

        //pegando o tio
        NoRN tio;
        if(pai.getPai().getEsquerda() == pai){ //checando se o tio é a esquerda ou direita do avô
            tio = pai.getPai().getDireita();
        }else{
            tio = pai.getPai().getEsquerda();
        }

        //caso 2
        if (tio != null && isRed(tio)) {
            mudarCor(pai, "preto");
            mudarCor(tio, "preto");
            mudarCor(avo, "vermelho");

            checkCor(avo); // Verificando avô
            return;
        }

        //caso 3
        if (pai == avo.getEsquerda()) {
            // Pai é filho esquerdo do avô
            if(filho == pai.getDireita()){
                esquerdaSimples(pai);
                filho = pai;
                pai = filho.getPai();
            }
            //caso 3a
            mudarCor(pai, "preto");
        mudarCor(avo, "vermelho");
        direitaSimples(avo);

    } else {
        //caso 3c
        if (filho == pai.getEsquerda()) {
            direitaSimples(pai);
            filho = pai;
            pai = filho.getPai();
        }
        // Caso 3b: Filho é direita do pai → rotação esquerda no avô
        mudarCor(pai, "preto");
        mudarCor(avo, "vermelho");
        esquerdaSimples(avo);
    }
        
    }

    //-------------------------------------------------------------------------------//
    //----------------------------------|Adicionar|----------------------------------//
    //-------------------------------------------------------------------------------//

    public void addChild(int o){
        NoRN novo = new NoRN(o);
        NoRN seeing = raiz;
        int foi = 0; // Marcado para confirma que foi colocado
        while(foi!=1){
            if((int)seeing.getElemento()>o){ // Checando se o elemento do no atual é maior que o novo
                if(seeing.getEsquerda()==null){ //Se sim e o no a esquerda é fazio, coloca o no novo aqui
                    novo.setPai(seeing);
                    seeing.setEsquerda(novo);
                    foi++;
                }
                seeing=seeing.getEsquerda(); // Move o no atual par a esquerda
            }
            else if((int)seeing.getElemento()<o){  // Checando se o elemento do no atual é menor que o novo
                if(seeing.getDireita()==null){ //Se sim e o no a direita é fazio, coloca o no novo aqui
                    novo.setPai(seeing);
                    seeing.setDireita(novo);
                    foi++;
                }
                seeing=seeing.getDireita();  // Move o no atual par a direita
            }
            else if((int)seeing.getElemento()==o){ // Checando se o elemento do no atual é igual ao novo
                System.err.println("Indece já existe!");
                foi++;
            }
        }

        checkCor(novo);
        return;
    }

    //-----------------------------------------------------------------------------//
    //----------------------------------|Remover|----------------------------------//
    //-----------------------------------------------------------------------------//

    public void removeChild(int o){
        NoRN seeing = locate(o);

        //caso no não tenha filhos
        if(seeing.getEsquerda()==null && seeing.getDireita()==null){
            if((int)seeing.getElemento() > (int)(seeing.getPai()).getElemento()){ //removendo filho a direita
                (seeing.getPai()).setDireita(null);
                seeing.setPai(null);
            }
            (seeing.getPai()).setEsquerda(null); //removendo filho a esquerda
            checkCor(seeing.getPai());
            seeing.setPai(null);
            return;
        }


        //caso tenha apenas um filho
        else if(seeing.getEsquerda()!=null && seeing.getDireita()==null){ // Filho a esquerda
            if((int)seeing.getElemento() > (int)(seeing.getPai()).getElemento()){ // Checando se o filho é maior que o pai
                if(this.isBlack(seeing) && this.isRed(seeing.getEsquerda())){ //check se o seeing é preto e o filho é vermelho
                    (seeing.getEsquerda()).setCor("vermelho");
                    
                }
                seeing.getPai().setDireita(seeing.getEsquerda()); // Se filho maior, passa o filho para a direita do pai

            } else {
                if(this.isBlack(seeing) && this.isRed(seeing.getEsquerda())){ //check se o seeing é preto e o filho é vermelho
                    (seeing.getEsquerda()).setCor("vermelho");
                    
                }
                seeing.getPai().setEsquerda(seeing.getEsquerda()); // Se não, passa o filho para a esquerda do pai
            }

            checkCor(seeing.getPai());
            seeing.setEsquerda(null);
            seeing.setPai(null);
            return;
        }

        else if(seeing.getEsquerda()==null && seeing.getDireita()!=null){ // filho a direita
            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){ // Checando se o filho é maior que o pai
                (seeing.getPai()).setDireita(seeing.getDireita()); // Se sim, passa o filho para a direita do pai
            } else {
                (seeing.getPai()).setEsquerda(seeing.getDireita()); // Se não, passa o filho para a esquerda do pai
            }

            checkCor(seeing.getPai());
            seeing.setDireita(null);
            seeing.setPai(null);
            return;
        }


        //caso tenha os dois filhos
        else{
            NoRN sucesor = seeing.getDireita(); // Achando o sucesor
            while(sucesor.getEsquerda()!=null){ // Procurando um que não tem filho a esquerda
                sucesor = sucesor.getEsquerda();
            }
            sucesor.setEsquerda(seeing.getEsquerda()); // Passando o filho a esquerda do seeing para o sucesor
            if(sucesor.getPai() != seeing){ // Checando se o sucesor é filho direto do seeing
                sucesor.getPai().setEsquerda(sucesor.getDireita()); // Se não, passa o filho a direita do sucesor para a esquerda do pai do sucesor
                
                if(sucesor.getDireita() != null){
                    (sucesor.getDireita()).setPai(sucesor.getPai()); // Passando o pai do sucesor para o filho
                }

                sucesor.setDireita(seeing.getDireita()); // Passando o filho a direita do seeing para o sucesor
                (sucesor.getDireita()).setPai(sucesor);
            }

            if((int)seeing.getElemento()>(int)seeing.getPai().getElemento()){ // Checando se o seeing é filho a direita do pai
                seeing.getPai().setDireita(sucesor); // Se sim, passa o sucesor para a direita do pai do seeing
            }
            else{
                seeing.getPai().setEsquerda(sucesor); // Se não, passa o sucesor para a esquerda do pai do seeing
            }

            sucesor.setPai(seeing.getPai()); // Passando o pai do seeing para o sucesor
            seeing.setEsquerda(null);
            seeing.setDireita(null);
            seeing.setPai(null);
            checkCor(sucesor);
            return;
        }
    }

    //------------------------------------------------------------------------------//
    //----------------------------------|ITERADOR|----------------------------------//
    //------------------------------------------------------------------------------//

    //Pega todos os nos (PARECE CERTO)
    public Map.Entry<ArrayList<Object>, Integer> elementos(){
        ArrayList<Object> elementosEcor = new ArrayList<>();
        NoRN no = raiz;
        int altura = 1;
        elementosEcor.add(no.getElemento());
        elementosEcor.add(no.getCor());
        elementosCheck(no, elementosEcor, altura);
        return new AbstractMap.SimpleEntry<>(elementosEcor, altura);
    }

    //DEVERIA pegar os nos e suas cor
    public void elementosCheck(NoRN no, ArrayList<Object> elementosEcor, int altura){
        if(no == null){
            return;
        }
        //pegando info esquerda
        if(no.getEsquerda() != null){
            elementosEcor.add((no.getEsquerda()).getElemento());
            elementosEcor.add(no.getEsquerda().getCor());
        }

        //pegando info direita
        if(no.getDireita() != null){
            elementosEcor.add((no.getDireita()).getElemento());
            elementosEcor.add(no.getDireita().getCor());
        }
        altura = altura+1;

        //indo pegar os proximos numeros
        elementosCheck(no.getEsquerda(), elementosEcor, altura);
        elementosCheck(no.getDireita(), elementosEcor, altura);
        
        return;
    }
}
