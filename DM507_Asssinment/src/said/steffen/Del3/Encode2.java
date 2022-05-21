package said.steffen.Del3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import said.steffen.Del1.Element;
import said.steffen.Del1.PQ;
import said.steffen.Del1.PQHeap;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;



public class Encode2 {
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static BitInputStream bitInputStream;
    private static BitOutputStream bitOutputStream;
    private static PQ pq = new PQHeap();
    private static int[] bitsArray = new int[256];
    private static int[][] table;

    public static void main(String[] args) throws IOException {

        System.out.println("Staring Encode!!......>\n");
        // Scanner skal slettes inde aflevering
        //Scanner sc = new Scanner(System.in);

        String[] argss = new String[2];
        System.out.println("Enter input file");
        argss[0]="src/said/steffen/Del3/"+"readMe.txt";
        System.out.println("Enter output file");
        argss[1]= "src/said/steffen/Del3/"+"test.txt";
        inputScanner(argss);
        createTableFrequrency();
        InsertPQ();
        huffmand();
        System.out.println(argss[0]);
        System.out.println(new File(argss[0]).getAbsolutePath());
        System.out.println("Done Encode!!......>\n");

    }


    private static void inputScanner(String[] args) throws FileNotFoundException {
        fileInputStream = new FileInputStream(args[0]);
        bitInputStream = new BitInputStream(fileInputStream);

    }


    private static void createTableFrequrency() throws IOException {
        int _byte ;
        while((_byte = fileInputStream.read() )!=-1){
            bitsArray[_byte]=bitsArray[_byte]+1;
        }
    }

    private static void InsertPQ(){
        for(int i=0; i<256; i++){
            pq.insert(new Element(bitsArray[i],i));
        }
    }

    private static void huffmand(){

        for(int i =0; i <255; i++ ){
            Element[] elements = new Element[2];
            // z.left
            elements[0] = pq.extractMin();
            // z.right
            elements[1] = pq.extractMin();
            pq.insert(new Element((elements[0].getKey()+elements[1].getKey()),elements));

        }

        System.out.println(pq.extractMin());
    }
}
