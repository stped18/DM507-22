package said.steffen.Del3;

import said.steffen.Del1.Element;
import said.steffen.Del1.PQ;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Huffman {
    private  BitInputStream bitInputStream;
    private  BitOutputStream bitOutputStream;
    private  FileInputStream fileInputStream;
    private  FileOutputStream fileOutputStream;
    private  PQ pq=null;
    private int[] bitsArray;
    private  int[] buf = new int[256];

    public Huffman(BitInputStream bitInputStream, BitOutputStream bitOutputStream, FileInputStream fileInputStream, FileOutputStream fileOutputStream, int[] bitsArray) {
        this.bitInputStream = bitInputStream;
        this.bitOutputStream = bitOutputStream;
        this.fileInputStream = fileInputStream;
        this.fileOutputStream = fileOutputStream;
        this.bitsArray = bitsArray;
    }

    public  void huffmand(int n, int[] code, PQ pq, boolean isEncode) throws IOException {
        if (pq == null){
            pq=pq;
        }
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
        if(isEncode){
            find_Encode(pq.extractMin(), count, code);
        }else{
            find_Decode(pq.extractMin());
        }

    }
    public  void find_Encode(Element e, int count, int[] code){
        if(e != null){
            if((e.getData() instanceof Element[])){
                System.out.println("is right node");
                buf[count]=0;
                find_Encode(((Element[])e.getData())[0], count+1, code);
                buf[count]=1;
                find_Encode(((Element[])e.getData())[1],count+1, code);


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
    }
    public  void find_Decode(Element e) throws IOException {
        Element current =e;
        int bit = 0;
        String datatofile = "";
        if(e != null) {
            while(bit != -1){
                bit = bitInputStream.readBit();
                if(!(current.getData() instanceof Element[])){
                    System.out.println("*****************************");
                    fileOutputStream.write((Integer) current.getData());
                    current=e;
                }
                if(bit ==0){
                    System.out.println("LEFT");
                    current = ((Element[])current.getData())[0];
                }
                if(bit==1){
                    System.out.println("RIGHT");
                    current = ((Element[])current.getData())[1];
                }
            }

        }
        System.out.println("----------------------------------------------------------");

    }

}
