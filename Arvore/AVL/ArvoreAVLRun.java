import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAVLRun{
private static ArvoreAVL arvore;

    public static void main (String[] args){
        arvore = new ArvoreAVL(10);
        int altura;
        int quantos = 1;
        int tempalt;
        int tempqua;

        //Add
        arvore.addChild(5);
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");
        arvore.addChild(15);
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");
        arvore.addChild(2);
        arvore.addChild(8);
        arvore.addChild(22);

        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");

        arvore.addChild(25);

        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");

        arvore.removeChild(5);

        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");
    }


    public static void imprimirArvore(NoAVL raiz) {
        if (raiz == null) return;
        Queue<NoAVL> fila = new LinkedList<>();
        fila.add(raiz);
        int nivel = 0;

        while(!fila.isEmpty()){
            int tamanho = fila.size();
            StringBuilder linha = new StringBuilder();
            int gap = Math.max(4, 12 - nivel * 3);

            for (int i=0; i<tamanho; i++) {
                NoAVL no=fila.poll();
                if (no != null) {
                    linha.append(String.format("%d(%d)", (int)no.getElemento(), (int)no.getFB()));
                    fila.add(no.getEsquerda());
                    fila.add(no.getDireita());
                } else {
                    linha.append("-");
                }

                if(i<tamanho-1){
                    linha.append(" ".repeat(gap));
                }
            }

            String prefixo;
            if (nivel == 0) {
                prefixo = " ".repeat(gap + 2);
            } else {
                prefixo = " ".repeat(5);
            }
            System.out.println(prefixo + linha.toString().replaceAll("\\s+$", ""));
            nivel++;

            boolean temValor = false;
            for (NoAVL n : fila) {
                if (n != null) {
                    temValor = true;
                    break;
                }
            }
            if (!temValor) break;
        }
    }
}