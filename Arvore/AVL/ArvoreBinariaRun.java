//import java.util.ArrayList;
import java.util.Iterator;
public class ArvoreBinariaRun{
private static ArvoreBinaria arvore;

    public static void main (String[] args){
        arvore = new ArvoreBinaria(10);
        int altura;
        int quantos = 1;
        int tempalt;
        int tempqua;
        String space = " ";
        String sasa = "";

        //Add
        arvore.addChild(5);
        arvore.addChild(2);
        arvore.addChild(8);
        arvore.addChild(15);
        arvore.addChild(22);

        //print
        arvore.elementos();
        altura = arvore.altura()-1;

        Iterator<Object> it = arvore.elementos();
        while(it.hasNext()==true){
            tempalt = altura;
            tempqua = quantos;

            while(tempalt>=0){
                space = " ".repeat(altura);
                tempalt--;
            }

            while(tempqua>0 && it.hasNext()==true){
                sasa = sasa + space + it.next() + space;
                tempqua--;
            }

            System.out.println(sasa);
            altura--;
            quantos = quantos*2;
            sasa = "";
        }

        //Add
        arvore.addChild(25);

        //print
        quantos = 1;
        arvore.elementos();
        altura = arvore.altura()-1;

        it = arvore.elementos();
        while(it.hasNext()==true){
            tempalt = altura;
            tempqua = quantos;

            while(tempalt>=0){
                space = " ".repeat(altura);
                tempalt--;
            }

            while(tempqua>0 && it.hasNext()==true){
                sasa = sasa + space + it.next() + space;
                tempqua--;
            }

            System.out.println(sasa);
            altura--;
            quantos = quantos*2;
            sasa = "";
        }

        //remove
        arvore.remove(25);

        //print
        quantos = 1;
        arvore.elementos();
        altura = arvore.altura()-1;

        it = arvore.elementos();
        while(it.hasNext()==true){
            tempalt = altura;
            tempqua = quantos;

            while(tempalt>=0){
                space = " ".repeat(altura);
                tempalt--;
            }

            while(tempqua>0 && it.hasNext()==true){
                sasa = sasa + space + it.next() + space;
                tempqua--;
            }

            System.out.println(sasa);
            altura--;
            quantos = quantos*2;
            sasa = "";
        }
    }
}