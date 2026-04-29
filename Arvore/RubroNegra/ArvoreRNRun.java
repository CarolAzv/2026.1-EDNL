import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreRNRun{
private static ArvoreRN arvore;

    public static void main (String[] args){
        arvore = new ArvoreRN(50);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");

        //Arovore 1
        arvore.addChild(30);
        arvore.addChild(70);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");
        
        arvore.addChild(20);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");

        arvore.addChild(40);
        arvore.addChild(60);
        arvore.addChild(80);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");
        
        arvore.addChild(25);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");
        
        arvore.addChild(35);
        arvore.addChild(45);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");

        arvore.removeChild(20);
        System.out.println("Arvore 1:");
        imprimirArvore(arvore.root());
        System.out.println("----------------------------------------");


        ArvoreRN arvore2 = new ArvoreRN(50);
        System.out.println("Arvore 2:");
        imprimirArvore(arvore2.root());
        System.out.println("----------------------------------------");

        arvore2.addChild(10);
        System.out.println("Arvore 2:");
        imprimirArvore(arvore2.root());
        System.out.println("----------------------------------------");

        arvore2.addChild(5);
        arvore2.addChild(15);
        System.out.println("Arvore 2:");
        imprimirArvore(arvore2.root());
        System.out.println("----------------------------------------");

        arvore2.addChild(3);
        arvore2.addChild(7);
        arvore2.addChild(12);
        arvore2.addChild(17);
        System.out.println("Arvore 2:");
        imprimirArvore(arvore2.root());
        System.out.println("----------------------------------------");

        arvore2.addChild(2);
        System.out.println("Arvore 2:");
        imprimirArvore(arvore2.root());
        System.out.println("----------------------------------------");

        arvore2.addChild(4);
        System.out.println("Arvore 2:");
        imprimirArvore(arvore2.root());
        System.out.println("----------------------------------------");


    }


    public static void imprimirArvore(NoRN raiz){
        if(raiz == null) return;
        Queue<NoRN> fila = new LinkedList<>();
        fila.add(raiz);
        int nivel = 0;

        while(!fila.isEmpty()){
            int tamanho = fila.size();
            StringBuilder linha = new StringBuilder();
            int gap = Math.max(4, 12 - nivel * 3);

            for(int i=0; i<tamanho; i++){
                NoRN no=fila.poll();

                if(no != null){
                    linha.append(String.format("%d(%s)", (int)no.getElemento(), (String)no.getCor()));
                    fila.add(no.getEsquerda());
                    fila.add(no.getDireita());
                }else{
                    linha.append("-");
                }
                
                if(i<tamanho-1){
                    linha.append(" ".repeat(gap));
                }
            }

            String prefixo;
            if(nivel == 0){
                prefixo = " ".repeat(gap + 2);
            } 
            else{
                prefixo = " ".repeat(5);
            }

            System.out.println(prefixo + linha.toString().replaceAll("\\s+$", ""));
            nivel++;

            boolean temValor = false;
            for (NoRN n : fila) {
                if (n != null) {
                    temValor = true;
                    break;
                }
            }
            if(!temValor) break;
        }
    }
}