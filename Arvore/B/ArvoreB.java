import java.util.ArrayList;
import java.util.Iterator;
import Arvore.Binario.ArvoreBinaria;
import Arvore.Binario.No;

public class ArvoreB{
    private NoB raiz;
    private final int ordem; // ordem mínima

    public ArvoreB(int t){
    ordem = t;
    raiz = new NoB(t);
    }

    //regras
    //1. A raiz é uma folha ou tem no mínimo dois filhos;
    //2. Cada nó diferente do raiz e das folhas possui no mínimo t filhos;
    //3. Cada nó tem no máximo 2t filhos;
    //4. Todas as folhas estão no mesmo nível


    //addicionar
    public void addChild(Object o){
        NoB seeing = raiz;

        
    }
}