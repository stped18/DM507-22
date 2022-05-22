package said.steffen.Del3;

import said.steffen.Del1.Element;
import said.steffen.Del1.PQ;
import said.steffen.Del1.PQHeap;

import java.io.*;




public class Decode {
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static BitInputStream bitInputStream;
    private static BitOutputStream bitOutputStream;
    private static int[] bitsArray = new int[256];
    private static int n=0;
    private static PQ pq;
    private static int[] code = new int[256];

    public static void main(String[] args) throws IOException {
        System.out.println("Staring Encode!!......>\n");
        // Scanner skal slettes inde aflevering
        //Scanner sc = new Scanner(System.in);

        String[] argss = new String[2];
        System.out.println("Enter input file");
        argss[1]="src/said/steffen/Del3/"+"hej2.txt";
        System.out.println("Enter output file");
        argss[0]= "src/said/steffen/Del3/"+"test";
        fileReader(argss);
        createFrequrency();
        pq = insertPQ();
        Huffman huffman = new Huffman(bitInputStream, bitOutputStream, fileInputStream, fileOutputStream, bitsArray);
        huffman.huffmand(n, code, pq, false);
        System.out.println(argss[0]);
        System.out.println(new File(argss[0]).getAbsolutePath());
        System.out.println("Done Decode!!......>\n");
    }

    private static void fileReader(String[]args) throws FileNotFoundException {
        fileInputStream = new FileInputStream(args[0]);
        fileOutputStream = new FileOutputStream(args[1]);
        bitInputStream = new BitInputStream(fileInputStream);
        bitOutputStream = new BitOutputStream(fileOutputStream);

    }
    private static void createFrequrency() throws IOException {
        for (int i = 0; i<256; i++) {
            bitsArray[i] = bitInputStream.readInt();
        }
    }

    private static PQ insertPQ(){
        PQ pq = new PQHeap();
        for(int i=0; i<256; i++){
            pq.insert(new Element(bitsArray[i],i));
            n++;
        }
        return pq;
    }



}
