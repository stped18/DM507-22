package said.steffen.Del2;
/**
 * ****************************** *
 * @author: Said Shaban           *
 * @mail: sshab15@student.sdu.dk  *
 * ****************************** *
 * @author: Steffen Vitten        *
 * @mail stped18@student.sdu.dk   *
 * ****************************** *
 */
public class BinNode {
  private BinNode left_child;
  private BinNode right_child;
  private int key;

  /**
   * @param key
   * Instansiere ny knude/ node
   */
  public BinNode(int key) {
    this.left_child=null;
    this.right_child=null;
    this.key=key;
  }

  public int getKey() {
    return key;
  }
  public BinNode getLeft_child() {
    return left_child;
  }
  public void setLeft_child(BinNode left_child) {
    this.left_child = left_child;
  }
  public BinNode getRight_child() {
    return right_child;
  }
  public void setRight_child(BinNode right_child) {
    this.right_child = right_child;
  }
}
