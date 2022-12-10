/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package huffman;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Beste Su
 */
public class Huffman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String keys[] = new String[7];

        HashMap<String, String> huffman = new HashMap<String, String>();
        keys[0] = "a";
        keys[1] = "b";
        keys[2] = "c";
        keys[3] = "d";


        Scanner scn = new Scanner(System.in);

        System.out.print("Sıkıştırılacak stringi giriniz ('a','b','c','d' içeren): ");
        String input = scn.next();
        
        int values[] = new int[7];
        int s = 4;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                values[0]++;
            } else if (input.charAt(i) == 'b') {
                values[1]++;
            } else if (input.charAt(i) == 'c') {
                values[2]++;
            } else if (input.charAt(i) == 'd') {
                values[3]++;
            } else {
                System.out.println("Alfabede olmayan bir karakter kullandınız");
                System.exit(0);

            }
        }
        String tempstr;
        int tempint;
        for (int i = 0; i < s - 1; i++) {
            for (int j = 0; j < s - 1; j++) {
                if (values[j] > values[j + 1]) {
                    tempint = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = tempint;
                    tempstr = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = tempstr;
                }
            }
        }

        int i;
        int tempsayı;
        String tempstring;
        for (i = 0; i < values.length - 1; i = i + 2) {
            tempsayı = values[i] + values[i + 1];
            tempstring = keys[i] + keys[i + 1];

            int index = indexbulma(values, s, tempsayı);

            values = sıralavalues(values, index, tempsayı, s);
            keys = sıralakeys(keys, index, tempstring, s);
            s++;

        }

        for (String key : keys) {
            huffman.put(key, "");
        }
        int x = 0;
        for (int j = keys.length - 2; j >= 0; j--) {

            String temp = "";
            if (x % 2 == 0) {
                temp = "0";
            } else if (x % 2 == 1) {
                temp = "1";
            }
            if (keys[j].length() == 1) {

                String tempo = huffman.get(keys[j]) + temp;

                huffman.put(keys[j], tempo);
                x++;
            } else {
                for (int m = 0; m <= keys.length - 1; m++) {

                    if (keys[j].indexOf(keys[m]) != -1) {
                        String tempo = huffman.get(keys[j]) + temp;
                        huffman.put(keys[m], tempo);
                        x++;
                    }
                }
            }
        }

        //yazdırma kısmı
        String çıktı = "";
        char m;
        for (int j = 0; j < input.length(); j++) {
            m = input.charAt(j);
            çıktı = çıktı + huffman.get(String.valueOf(m)) + " ";

        }
        System.out.println("girdiniz:  " + input);
        System.out.println();

        for (int j = 0; j < keys.length; j++) {
            System.out.println("Node: " + keys[j] + "  frekansı: " + values[j]);
        }
        System.out.println();
        for (String j : huffman.keySet()) {
            System.out.println("key: " + j + "   value: " + huffman.get(j));
        }
        System.out.println();
        System.out.println("Sıkıştırılmış hali: " + çıktı);

    }

    public static String[] sıralakeys(String dizi[], int index, String x, int uzunluk) {

        String temp[] = new String[uzunluk + 1 - index];
        int j = 0;
        for (int i = index; i < uzunluk; i++) {
            temp[j] = dizi[i];

            j++;

        }
        dizi[index] = x;
        j = 0;
        for (int i = index + 1; i < uzunluk + 1; i++) {
            dizi[i] = temp[j];
            j++;
        }

        return dizi;
    }

    public static int[] sıralavalues(int dizi[], int index, int sayı, int uzunluk) {

        int temp[] = new int[uzunluk + 1 - index];
        int j = 0;
        for (int i = index; i < uzunluk; i++) {
            temp[j] = dizi[i];

            j++;

        }
        dizi[index] = sayı;
        j = 0;
        for (int i = index + 1; i < uzunluk + 1; i++) {
            dizi[i] = temp[j];
            j++;
        }

        return dizi;
    }

    public static int indexbulma(int dizi[], int uzunluk, int eklenecek) {

        int i = 0;
        for (int j = 0; j < uzunluk; j++) {
            if (dizi[j] < eklenecek) {
                i++;

            }
        }
        return i;
    }

}
