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
    private static int[] code = new int[256];
    private static int[] buf = new int[256];
    private static int n=0;

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

        System.out.println("-------------------------");
        for (int w:code) {
            System.out.println(w);
        }
        bitToOutput(argss);
        System.out.println(argss[0]);
        System.out.println(new File(argss[0]).getAbsolutePath());
        System.out.println("Done Encode!!......>\n");

    }


    private static void inputScanner(String[] args) throws FileNotFoundException {
        fileInputStream = new FileInputStream(args[0]);
        bitInputStream = new BitInputStream(fileInputStream);
        fileOutputStream = new FileOutputStream(args[1]);
        bitOutputStream =new BitOutputStream(fileOutputStream);

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
            n++;
        }

    }

    private static void huffmand(){

        for(int i =0; i <n-1; i++ ){
            Element[] z = new Element[2];
            // z.left
            Element l = pq.extractMin();
            z[0] = l;
            // z.right
            Element r = pq.extractMin();
            z[1] = r;
            int freq = (z[0].getKey()+z[1].getKey());
            pq.insert(new Element(freq,z));

        }
        int count =0;
        find(pq.extractMin(), count);
    }
    public static void find(Element e, int count){
        if(e == null){
            System.out.println("Last rot");
        }


        if((e.getData() instanceof Element[])){
            System.out.println("is right node");
            buf[count]=0;
            find(((Element[])e.getData())[0], count+1);
            buf[count]=1;
            find(((Element[])e.getData())[1],count+1);


        }else{

            System.out.println("is left node");
            System.out.println(e);
            String keyword = "";
            for(int i =0; i<count;i++ ){
                keyword+= buf[i];
            }
            try{
                System.out.println("keyword : "+keyword);
                code[(int) e.getData()]=Integer.parseInt(keyword);
            } catch(NumberFormatException ex){
                System.out.println(ex);
            }
        }
    }

    public static void bitToOutput(String[] args) throws IOException
    {
        fileInputStream = new FileInputStream(args[0]);
        fileOutputStream = new FileOutputStream(args[1]);
        int content;
        String strBit;
        String cont;

        for(int i = 0; i < bitsArray.length; i++ )
        {
            int x = bitsArray[i];
            bitOutputStream.writeInt(x);
        }


//		write file into bytes.
        while ((content = fileInputStream.read()) != -1)
        {
            cont = String.valueOf(code[content]);
            for(int i = 0; i < cont.length(); i++)
            {
                strBit = Character.toString(cont.charAt(i));
                int intBit = Integer.parseInt(strBit);
                bitOutputStream.writeBit(intBit);
            }
        }
        bitOutputStream.writeBit(0);
        bitOutputStream.writeBit(1);
    }

}
