package said.steffen.Del1;

/**
 * author: Said Shaban and Steffen Vitten
 */
public class Element {

  private final int key;
  private final Object data;

  public Element(int i, Object o){
    this.key=i;
    this.data=o;

  }

  /**
   *
   * @return
   */
  public int getKey(){
    return this.key;
  }

  /**
   *
   * @return
   */
  public Object getData(){
    return this.data;
  }

  /**
   *
    * @return
   */
  @Override
  public String toString() {
    return "Element{" +
            "key=" + key +
            ", data=" + data +
            '}';
  }
}
