package said.steffen.Del3;

import java.io.File;
import java.io.IOException;

import said.steffen.Del1.Element;
import said.steffen.Del1.PQ;
import said.steffen.Del1.PQHeap;

import java.io.*;

public class Encode {
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static BitInputStream bitInputStream;
    private static BitOutputStream bitOutputStream;
    private static PQ pq;
    private static int[] bitsArray = new int[256];
    private static int[][] table;
    private static int[] code = new int[256];

    private static int n=0;

    public static void main(String[] args) throws IOException {

        System.out.println("Staring Encode!!......>\n");
        // Scanner skal slettes inde aflevering
        //Scanner sc = new Scanner(System.in);

        String[] argss = new String[2];
        System.out.println("Enter input file");
        argss[0]="src/said/steffen/Del3/"+"hej.txt";
        System.out.println("Enter output file");
        argss[1]= "src/said/steffen/Del3/"+"test";
        inputScanner(argss);
        createTableFrequrency();
        pq = InsertPQ();
        Huffman huffman = new Huffman(bitInputStream, bitOutputStream, fileInputStream, fileOutputStream , bitsArray);
        huffman.huffmand(n, code, pq, true);

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

    private static PQ InsertPQ(){
        PQ pq = new PQHeap();
        for(int i=0; i<256; i++){
            pq.insert(new Element(bitsArray[i],i));
            n++;
        }
        return pq;

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

        fileInputStream.close();
        fileOutputStream.close();
    }


}
