package said.steffen.Del3;





import said.steffen.Del1.Element;
import said.steffen.Del1.PQ;
import said.steffen.Del1.PQHeap;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Encode {

  private static FileInputStream fileInputStream;
  private static FileOutputStream fileOutputStream;
  private static BitInputStream bitInputStream;
  private static BitOutputStream bitOutputStream;
  private static PQ pq;
  private static String[] codeList = new String[256];

  private static int[][] table;



  public static void main(String[] args) throws IOException {

        System.out.println("Staring Encode!!......>\n");
        // Scanner skal slettes inde aflevering
        Scanner sc = new Scanner(System.in);

        String[] argss = new String[2];
        System.out.println("Enter input file");
        argss[0]="src/said/steffen/Del3/"+sc.nextLine();
        System.out.println("Enter output file");
        argss[1]= "src/said/steffen/Del3/"+sc.nextLine();

        System.out.println(argss[0]);
        System.out.println(new File(argss[0]).getAbsolutePath());
        readFile(argss);
        createTabel();
        huffman();
        bitToOutput(argss);
        System.out.println("Done Encode!!......>\n");
  }

  private static void readFile(String args[]) throws IOException {

      fileInputStream = new FileInputStream(args[0]);
      bitInputStream = new BitInputStream(fileInputStream);
  }

  private static void createTabel() throws IOException {
      table = new int[256][8];

      int bite;
      int colum=0;
      int row=0;
      while ((bite = fileInputStream.read())!= -1){
        table[row][colum]=bite;
        colum++;
        if(colum==8){
            row++;
            colum=0;
        }
      }
      createElements(table);
    }
  private static void createElements(int[][] table ){
      pq = new PQHeap();
      int count =0;
      while(count<256){
          pq.insert(new Element(count, table[count]));
          count++;
      }
  }
    public static void huffman()
    {
        int n=0;
        for(int i = 0; i < n-1; i++)
        {
            Element[] elementNode = new Element[2];
            elementNode[0] = pq.extractMin();
            elementNode[1] = pq.extractMin();

            int freq = elementNode[0].getKey() + elementNode[1].getKey();

            pq.insert(new Element(freq, elementNode));
        }

        int[] arr = new int[256];
        search(pq.extractMin(), arr, 0);
    }

    public static void search(Element x, int arr[], int count)
    {
        if (x == null)
        {
            return;
        }

        if (IsLeafNode(x))
        {
            StringBuilder strNum = new StringBuilder();
            String bitValue;
            for(int i = 0; i < count; i++ )
            {
                strNum.append(arr[i]);
            }

            bitValue = strNum.toString();

            codeList[x.getKey()] = bitValue;
            System.out.println(codeList.toString());
            return;
        }
        else
        {
            arr[count] = 0;
            search(((Element[])x.getData())[0], arr, count+1);

            arr[count] = 1;
            search(((Element[])x.getData())[1], arr, count+1);
        }
    }

    public static boolean IsLeafNode(Element x)
    {
        return !(x.getData() instanceof Element[]);
    }

    public static void bitToOutput(String args[] ) throws IOException
    {
        fileInputStream = new FileInputStream(args[0]);
        fileOutputStream = new FileOutputStream(args[1]);
        bitOutputStream= new BitOutputStream(fileOutputStream);
        int content;
        String strBit;
        String cont="";

        for(int i = 0; i < table.length; i++ )
        {
            int[] x = table[i];
            for (int j:x){
                bitOutputStream.writeInt(j);
            }

        }
        while ((content = fileInputStream.read()) != -1)
        {
            cont = codeList[content];
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
        fileInputStream.close();
    }
}
