package said.steffen.Del2;

import java.util.ArrayList;
import java.util.Scanner;

public class Treesort {
    public static void main(String[] args) {
        Dict dict = new DictBinTree();
        int k;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            k = sc.nextInt();
            dict.insert(k);
        }
        ArrayList<Integer> sortetList = dict.orderedTraversal();
        for (int i:sortetList) {
            System.out.println(i);
        }

    }
}
