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

public class PQHeap implements PQ {
  private final ArrayList<Element> heap;

  public PQHeap() {
    this.heap =new ArrayList<Element>();
  }
  /**
   *
   * @return
   */
  @Override
  public Element extractMin() {
    Element minste = this.heap.get(0);
    int heepSize = this.heap.size()-1;
    Element last = this.heap.get(heepSize);
    this.heap.set(0,last);
    this.minHeepfy(0);
    this.heap.remove(heepSize);
    return minste;
  }

  /**
   *
   * @param i
   * @return
   */
  private  int left (int i) {
    return 2*i+1;

  }

  /**
   *
   * @param i
   * @return
   */
  private  int parent (int i) {
    return (i-1)/2;

  }

  /**
   *
   * @param i
   * @return
   */
  private  int right (int i) {
    return 2*i+2;

  }

  /**
   *
   * @param i
   */
  public void minHeepfy(int i){
    int smallest;
    Element buf;
    int heapSize= this.heap.size()-1;
    if(left(i)<=heapSize && this.heap.get(left(i)).getKey() < this.heap.get(i).getKey()){
      smallest=left(i);
    }else {
      smallest=i;
    }
    if (right(i)<= heapSize && this.heap.get(right(i)).getKey() < this.heap.get(smallest).getKey() ){
      smallest=right(i);
    }
    if (smallest!=i){
      buf=this.heap.get(i);
      this.heap.set(i,this.heap.get(smallest));
      this.heap.set(smallest, buf);
      this.minHeepfy(smallest);
    }
  }

  /**
   *
   * @param e
   */
  @Override
  public void insert(Element e) {
    Element buf;
    int i = this.heap.size();
    this.heap.add(e);
    Element parrent=this.heap.get(parent(i));
    Element lastElement = this.heap.get(i);
    while (i > 0 && parrent.getKey() > lastElement.getKey()){
      buf = this.heap.get(i);
      this.heap.set(i,parrent);
      this.heap.set(parent(i),buf);
      i=parent(i);
    }
  }

   public ArrayList<Element> getHeap(){
      return this.heap;
   }


  @Override
  public String toString() {
    return "PQHeap{" +
            "heap=" + heap +
            '}';
  }
}
