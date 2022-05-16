package said.steffen.Del2;
import java.util.Random;
import java.util.Scanner;
/**
 * ****************************** *
 * @author: Said Shaban           *
 * @mail: sshab15@student.sdu.dk  *
 * ****************************** *
 * @author: Steffen Vitten        *
 * @mail stped18@student.sdu.dk   *
 * ****************************** *
 */
public class main {

  public static void main(String[] args) {
    DictBinTree tree = new DictBinTree();
    System.out.println("Starting Del 2");
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter ny int value: ");
    while (sc.hasNextInt()) {
      tree.insert(sc.nextInt());
      System.out.print("Enter ny int value: ");
    }
    System.out.println("_______________________________");
    System.out.println("Sortet Tree : "+tree.orderedTraversal());
  }


}
