package said.steffen.Del2;

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
interface Dict {
  boolean search(int k);
  void insert(int k);
  ArrayList<Integer> orderedTraversal();
}
