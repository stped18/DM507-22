package said.steffen.Del1;

import java.util.ArrayList;
/**
 * ****************************** *
 * @author: Said Shaban           *
 * @mail: sshab15@student.sdu.dk  *
 * ****************************** *
 * @author: Steffen Vitten        *
 * @mail stped18@student.sdu.dk   *
 * ****************************** *
 */
public interface PQ {

   Element extractMin();
   void insert(Element e);
   ArrayList<Element> getHeap();
}
