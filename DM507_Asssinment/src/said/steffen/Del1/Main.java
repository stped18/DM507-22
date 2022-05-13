package said.steffen.Del1;

import said.steffen.Del1.Element;
import said.steffen.Del1.PQ;
import said.steffen.Del1.PQHeap;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * author: Said Shaban and Steffen Vitten
 */
public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/decreasing.txt");
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/empty.txt");
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/increasing.txt");
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/mixed.txt");
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/negative.txt");
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/positive.txt");
        System.out.println("*********************************");
       run("src/said/steffen/Del1/Testfiler/same.txt");
        System.out.println("*********************************");
    }

    /**
     * @param stringpath
     * @throws IOException
     */
    public static void run(String stringpath) throws IOException {
        Path path = Paths.get(stringpath);
        Path fileName = path.getFileName();
        System.out.println("*\t\t\t"+fileName+"\t\t\t*");
        PQ pq = new PQHeap();
        int n = 0;
        File file = new File(stringpath);
        BufferedReader br= new BufferedReader(new FileReader(file));
        String st;
        long start = System.nanoTime();
      while ((st = br.readLine()) != null){
            n++;
            pq.insert(new Element(Integer.parseInt(st),null));
        }
        while (n > 0){
            System.out.println("*\t\t\t\t"+pq.extractMin().getKey()+"\t\t\t\t*");
            n--;
        }
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println("*\tTime : "+elapsedTime+" Nanosecond\t*");
    }
}
