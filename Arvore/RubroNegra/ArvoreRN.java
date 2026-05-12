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
        return no != null && no.getPai() == null;
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
         while(seeing!=null && (int)seeing.getElemento()!=(int)o){ // Checando para ver se o no seeing é o desejado
            if((int)seeing.getElemento()>(int)o){
                seeing=seeing.getEsquerda();
            }
            else{
                seeing=seeing.getDireita();
            }
        }
        return seeing; // retorna null se não encontrado
    }

    //-----------------------------------------------------------------------------//
    //----------------------------------|Rotação|----------------------------------//
    //-----------------------------------------------------------------------------//

    //Esquerda Simples - Rotação para esqerda \ para ^
    public void esquerdaSimples(NoRN o){
        if (o == null || o.getDireita() == null) {
            return;
        }

        NoRN seeing = o;
        NoRN pai = seeing.getPai();

        if(pai.getPai() != null){  //checando sé tem pai tem pai
            seeing.setPai(pai.getPai());
            if(pai.getPai().getEsquerda() == pai){ //checando se o pai é a esquerda ou direita do avô
                pai.getPai().setEsquerda(seeing);
            } else {
                pai.getPai().setDireita(seeing);
            }
        }
        
        pai.setPai(seeing);
        seeing.setEsquerda(pai);
        if(isRoot(pai)){
            raiz = seeing;
        }
        checkCor(seeing);

        return;
    }

    //Direita Simples - Rotação para direita / para ^
    public void direitaSimples(NoRN o){
        if (o == null || o.getDireita() == null) {
            return;
        }

        NoRN seeing = o;
        NoRN pai = seeing.getPai();
        
        if(pai.getPai() != null){ //checando sé tem pai tem pai
            seeing.setPai(pai.getPai());
            if(pai.getPai().getEsquerda() == pai){ //checando se o pai é a esquerda ou direita do avô
                pai.getPai().setEsquerda(seeing);
            } else {
                pai.getPai().setDireita(seeing);
            }
        }

        pai.setPai(seeing);
        seeing.setDireita(pai);
        if(isRoot(pai)){
            raiz = seeing;
        }
        checkCor(seeing);
        return;
    }

    //-----------------------------------------------------------------------------//
    //------------------------------------|Cor|------------------------------------//
    //-----------------------------------------------------------------------------//

    public void mudarCor(NoRN no, String cor){
        no.setCor(cor);
    }

    public void checkCor(NoRN no){
        NoRN seeing = no;

        //check se é raiz
        if(isRoot(seeing)){
            mudarCor(seeing, "preto");
            return;
        }

        NoRN pai = seeing.getPai();

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

        //pegando irmão de seeing
        NoRN irmao;
        if((int)seeing.getElemento()<(int)seeing.getPai().getElemento()){
            irmao = seeing.getPai().getDireita();
        } else{
            irmao = seeing.getPai().getEsquerda();
        }

        //caso 2 inserção
        if (tio != null && isRed(tio)) {
            mudarCor(pai, "preto");
            mudarCor(tio, "preto");
            mudarCor(avo, "vermelho");

            checkCor(avo); // Verificando avô
            return;
        }

        //caso 3 inserção
        if (pai == avo.getEsquerda()) {
            // Pai é filho esquerdo do avô
            if(seeing == pai.getDireita()){
                seeing = pai;
                esquerdaSimples(pai);
                pai = seeing.getPai();
            }
            mudarCor(pai, "preto");
            mudarCor(avo, "vermelho");
            direitaSimples(pai);

        } else {
            // Pai é filho direito do avô
            if (seeing == pai.getEsquerda()) {
                seeing = pai;
                direitaSimples(pai);
                pai = seeing.getPai();
            }
            mudarCor(pai, "preto");
            mudarCor(avo, "vermelho");
            esquerdaSimples(avo);
        }

        //situação 3 remover e sucesor rubro
        if(isBlack(seeing)){

            //caso 1
            if(isRed(irmao) && isBlack(seeing.getPai())){
                caso1(seeing);
                checkCor(seeing);
            }

            //caso 2a
            else if(isBlack(irmao) && isBlack(seeing.getPai()) && isBlack(seeing.getEsquerda()) && isBlack(seeing.getDireita())){
                caso2a(seeing); 
                checkCor(seeing);
            }

            //caso 2b
            else if(isBlack(irmao) && isRed(seeing.getPai()) && isBlack(seeing.getEsquerda()) && isBlack(seeing.getDireita())){
                caso2b(seeing);
                return;
            }

            //caso 3
            else if(isBlack(irmao) && (seeing.getPai()!=null) && isRed(irmao.getEsquerda()) && isBlack(irmao.getDireita())){
                caso3(seeing);
                checkCor(seeing);
            }

            //caso 4
            else if(isBlack(irmao) && (seeing.getPai()!=null) && (irmao.getEsquerda()!=null && isRed(irmao.getDireita()))){
                caso4(seeing);
                checkCor(seeing);
                return;
            }
        }

        //situação 4 remover e sucesor rubro
        else if(isBlack(seeing)){
            seeing.setCor("vermelho");
            checkCor(seeing);
            return;
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

    public NoRN sucessoRN(NoRN o){
        NoRN sucesor = o; // Achando o anterior do sucesor

        if(sucesor.getDireita()!=null){ //pegando o sucesor caso tenha direita
            sucesor = sucesor.getDireita();
            while(sucesor.getEsquerda()!=null){ // Procurando um que não tem filho a esquerda
                sucesor = sucesor.getEsquerda();
            }
        } else{ //pegando o sucesor caso não tenha direita
            sucesor = sucesor.getEsquerda();
        }
        
        return sucesor;
    }

    public void removeHub(int o){
        NoRN remover = locate(o);
        NoRN sucesor = null;
        NoRN irmao = null;
        String corRemover = remover.getCor(); //pegando a cor do remover para usar depois

        if(remover == null){
            System.err.println("Elemento não encontrado!");
            return;
        }

        if(remover.getEsquerda()!=null || remover.getDireita()!=null){
            sucesor = sucessoRN(remover);
        }

        removeChild(remover, sucesor);

        if(sucesor == null){
            return;
        }

        //pegando irmão do sucessor
        if(isRoot(sucesor)){
            return;
        } else{
            if((int)sucesor.getElemento()<(int)sucesor.getPai().getElemento()){
                irmao = sucesor.getPai().getEsquerda();
            } else{
                irmao = sucesor.getPai().getDireita();
            }
        }
        

        //situação 2  negro e sucesor rubro
        if(corRemover=="preto" && isRed(sucesor)){
            sucesor.setCor("preto");
            return;
        }

        //situação 3 remover e sucesor negro
        if(corRemover=="preto" && isBlack(sucesor)){

            //caso 1
            if(isRed(irmao) && isBlack(sucesor.getPai())){
                caso1(sucesor);
                checkCor(sucesor);
                return;
            }

            //caso 2a
            else if(isBlack(irmao) && isBlack(sucesor.getPai()) && isBlack(sucesor.getEsquerda()) && isBlack(sucesor.getDireita())){
                caso2a(sucesor);
                return;
            }

            //caso 2b
            else if(isBlack(irmao) && isRed(sucesor.getPai()) && isBlack(sucesor.getEsquerda()) && isBlack(sucesor.getDireita())){
                caso2b(sucesor);
                return;
            }

            //caso 3
            else if(isBlack(irmao) && (sucesor.getPai()!=null) && isRed(irmao.getEsquerda()) && isBlack(irmao.getDireita())){
                caso3(sucesor);
                return;
            }

            //caso 4
            else if(isBlack(irmao) && (sucesor.getPai()!=null) && (irmao.getEsquerda()!=null && isRed(irmao.getDireita()))){
                caso4(sucesor);
                return;
            }
        }

        //situação 4 remover e sucesor rubro
        else if(corRemover=="vermelho" && isBlack(sucesor)){
            sucesor.setCor("vermelho");
            checkCor(sucesor);
            return;
        }

    }


    public void removeChild(NoRN r, NoRN s){
        NoRN remover = r;
        NoRN sucesor = s;
        

        //caso no não tenha filhos
        if(isBlack(remover) && remover.getEsquerda()==null && remover.getDireita()==null){
            if(isRoot(remover)){ // removendo a raiz (árvore com 1 elemento)
                raiz = null;
                return;
            }

            if((int)remover.getElemento() > (int)(remover.getPai()).getElemento()){ //removendo filho a direita
                (remover.getPai()).setDireita(null);
                remover.setPai(null);
            }
            else{
                (remover.getPai()).setEsquerda(null); //removendo filho a esquerda
                remover.setPai(null);
            }
            checkCor(remover.getPai());
            remover.setPai(null);

            return;
        }

        else if(isRed(remover) && remover.getEsquerda()==null && remover.getDireita()==null){
            if((int)remover.getElemento() > (int)(remover.getPai()).getElemento()){ //removendo filho a direita
                (remover.getPai()).setDireita(null);
                remover.setPai(null);
            }
            else{
                (remover.getPai()).setEsquerda(null); //removendo filho a esquerda
                checkCor(remover.getPai());
                remover.setPai(null);
            }
            return;
        }


        //caso tenha apenas um filho
        // tem um filho a esquerda
        else if(remover.getEsquerda()!=null && remover.getDireita()==null){
            if(remover.getPai() != null){
                if((int)remover.getElemento() > (int)(remover.getPai()).getElemento()){ // check se o remover é maior que o pai
                    remover.getPai().setDireita(remover.getEsquerda()); //passa o filho para a direita do pai
                }
                else{
                    remover.getPai().setEsquerda(remover.getEsquerda()); //passa o filho para a esquerda do pai
                }
                remover.getEsquerda().setPai(remover.getPai());
                checkCor(remover.getPai());
            } else {
                // remover é raiz
                raiz = remover.getEsquerda();
                raiz.setPai(null);
                checkCor(raiz);
            }
            remover.setEsquerda(null);
            remover.setPai(null);
            return;
        }

        // tem um filho a direita
        else if(remover.getEsquerda()==null && remover.getDireita()!=null){
            if(remover.getPai() != null){
                if((int)remover.getElemento() > (int)(remover.getPai()).getElemento()){ // check se o remover é maior que o pai
                    remover.getPai().setDireita(remover.getDireita()); //passa o filho para a direita do pai
                }
                else{
                    remover.getPai().setEsquerda(remover.getDireita()); //passa o filho para a esquerda do pai
                }
                remover.getDireita().setPai(remover.getPai());
                checkCor(remover.getPai());
            } else {
                // remover é raiz
                raiz = remover.getDireita();
                raiz.setPai(null);
                checkCor(raiz);
            }
            remover.setDireita(null);
            remover.setPai(null);
            return;
        }


        //caso tenha os dois filhos //TEM QUE CONCERTA AINDA AAAAAAAAAAAAAAAAAAAAAAAAAAA
        else{
            //passando o sucesor para o lugar do remover
            if(remover.getPai()!=null){
                if((int)remover.getPai().getElemento() > (int)remover.getElemento()){ //pai é maior que o remover
                    remover.getPai().setEsquerda(sucesor);

                }else{
                    remover.getPai().setDireita(sucesor); //pai é menor que o remover
                }

                sucesor.getPai().setEsquerda(sucesor.getDireita()); // Passando o filho direito do sucesor para a esquerda do seu pai
                sucesor.setEsquerda(remover.getEsquerda()); // Passando a esquerda do remover para o sucesor
                if (remover.getEsquerda() != null) {
                    remover.getEsquerda().setPai(sucesor); // Passando o pai do remover para a esquerda do sucessor
                }

                sucesor.setPai(remover.getPai()); // Passando o pai do remover para o sucessor
                sucesor.setDireita(remover.getDireita()); // Passando a direita do remover para o sucessor
                if (remover.getDireita() != null) {
                    remover.getDireita().setPai(sucesor);
                }

                remover.setEsquerda(null);
                remover.setDireita(null);
                remover.setPai(null);
                checkCor(sucesor);
            } else {
                // remover é raiz
                raiz = sucesor;
                sucesor.getPai().setEsquerda(sucesor.getDireita());
                if (sucesor.getDireita() != null) {
                    sucesor.getDireita().setPai(sucesor.getPai());
                }
                sucesor.setEsquerda(remover.getEsquerda());
                if (remover.getEsquerda() != null) {
                    remover.getEsquerda().setPai(sucesor);
                }
                sucesor.setDireita(remover.getDireita());
                if (remover.getDireita() != null) {
                    remover.getDireita().setPai(sucesor);
                }
                sucesor.setPai(null);
                remover.setEsquerda(null);
                remover.setDireita(null);
                remover.setPai(null);
                checkCor(sucesor);
            }
            return;
        }
    }



    //isBlack(remover) && isRed(sucesor) && isBlack(irmao) && isBlack(sucesor.getPai())

    public void caso1(NoRN sucesor){
        sucesor.setDuploNegro(true); //sucesor é duplo negro

        esquerdaSimples(sucesor); //rotação simples

        //pegando novo irmão do sucesor
        NoRN irmao = null;
        if((int)sucesor.getElemento()<(int)sucesor.getPai().getElemento()){
            irmao = sucesor.getPai().getEsquerda();
        } else{
            irmao = sucesor.getPai().getDireita();
        }

        irmao.setCor("preto"); //mudando a cor 

        sucesor.getPai().setCor("preto"); //mudando a cor

        checkCor(sucesor);
    }

    //isBlack(remover) && isBlack(sucesor) && isBlack(irmao) && isBlack(sucesor.getPai() && (sucesor.getDireita() == null || isBlack(sucesor.getDireita()) && (sucesor.getEsquerda() == null || isBlack(sucesor.getEsquerda()))

    public void caso2a(NoRN sucesor){
        //pegando novo irmão do sucesor
        NoRN irmao = null;

        if((int)sucesor.getElemento()<(int)sucesor.getPai().getElemento()){
            irmao = sucesor.getPai().getEsquerda();
        } else{
            irmao = sucesor.getPai().getDireita();
        }

        irmao.setCor("vermelho"); //mudando a cor

        //pasando o duplo negro pro pai
        sucesor.setDuploNegro(false);
        sucesor.getPai().setDuploNegro(true);

        checkCor(sucesor);
    }

    //isBlack(remover) && isBlack(sucesor) && isBlack(irmao) && isRed(sucesor.getPai() && (sucesor.getDireita() == null || isBlack(sucesor.getDireita()) && (sucesor.getEsquerda() == null || isBlack(sucesor.getEsquerda()))
    
    public void caso2b(NoRN sucesor){
        //pegando novo irmão do sucesor
        NoRN irmao = null;

        if((int)sucesor.getElemento()<(int)sucesor.getPai().getElemento()){
            irmao = sucesor.getPai().getEsquerda();
        } else{
            irmao = sucesor.getPai().getDireita();
        }

        irmao.setCor("vermelho"); //mudando a cor

        sucesor.getPai().setCor("preto"); //mudando a cor

        //duplo negro é absorvido
        sucesor.setDuploNegro(false);
    }

    //isBlack(remover) && isBlack(sucesor) && isBlack(irmao) && isBlack(sucesor.getDireita() && isRed(sucesor.getEsquerda())

    public void caso3(NoRN sucesor){
        //pegando novo irmão do sucesor
        NoRN irmao = null;
        if((int)sucesor.getElemento()<(int)sucesor.getPai().getElemento()){
            irmao = sucesor.getPai().getEsquerda();
        } else{
            irmao = sucesor.getPai().getDireita();
        }

        direitaSimples(irmao);

        //pasando cor do filho esquerdo para irmao e a cor de irmao para filho esquerdo
        irmao.setCor(irmao.getEsquerda().getCor());
        irmao.getEsquerda().setCor("preto");

        checkCor(sucesor);
    }

    //isBlack(remover) && isBlack(sucesor) && isBlack(irmao) && isBlack(sucesor.getDireita() && isRed(sucesor.getEsquerda())

    public void caso4(NoRN sucesor){
        esquerdaSimples(sucesor);
        NoRN irmao = null;

        //pegando novo irmão do sucesor
        if((int)sucesor.getElemento()>(int)sucesor.getPai().getElemento()){
            irmao = sucesor.getPai().getEsquerda();
        } else{
            irmao = sucesor.getPai().getDireita();
        }

        irmao.setCor(sucesor.getPai().getCor());
        sucesor.getPai().setCor("preto");

        irmao.getDireita().setCor("preto");

        //duplo negro é absorvido
        sucesor.setDuploNegro(false);
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