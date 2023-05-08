import java.io.*;
import java.util.Scanner;

public class Controllo {
    public boolean isCorretto(ArrayList<String> pizze){
        File file1;
        File file2;
        Scanner b1,b2;
        file1=new File(path1);
        file2=new File(path2);
        try {
            b1=new Scanner(file1);
            b2=new Scanner(file2);
            //acquisisco prima riga del primo file
            String rowF1=b1.nextLine();
            //acquisisco prima riga del secondo file
            String rowF2=b2.nextLine();
            //cotrollo che abbiano la stessa dimensione
            if(rowF2.length() != rowF1.length()){
                return false;
            }
            //inserisco la stringa degli ingredienti in un array di stringhe
            String[] word=rowF1.split(" ");
            //verifico se ci sono tutti gli ingredienti
            int i=0;
            while(i< word.length){
                if(rowF2.contains(word[i])==false){
                    return false;
                }
                i++;
            }
        } catch (IOException e) {
            ;
        }
        return true;
    }
    public static void main(String[] args) {
        Controllo test=new Controllo("src/Gestione/ordine.txt","src/Gestione/IngredientiPizza.txt");
        System.out.println(test.isCorretto());
    }
}




