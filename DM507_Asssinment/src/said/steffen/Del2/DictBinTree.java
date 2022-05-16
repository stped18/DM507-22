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
public class DictBinTree implements Dict {

  private BinNode root;
  public DictBinTree() {
    this.root=null;
  }
  /**
   * @param k
   * @return boolean
   * search kalde funktionen Treesearch da tager og kigger fra roden af træet.
   */
  @Override
  public boolean search(int k) {
    return TreeSearch(this.root, k);
  }
  /**
   * @param parrent_node
   * @param k
   * @return boolean
   * TreeSearch er en recursive funktion i det den kalder sig selv indtil indtil den kan finde en null eller k værdien,
   * den spøger en node om den værdi er mindre eller støre end k
   * hvis den er mindre eller støre end k vil den går mode højere eller vester afhængigt af k
   * hvis værdien i noden er tilsvarende k retunere den true
   * hvis værdien ikke eksistere "null" vil den retunere false
   */
  private boolean TreeSearch(BinNode parrent_node, int k){
    if (parrent_node == null){
      return false;
    }
    if (k==parrent_node.getKey()){
      return  true;
    }
    if(k<parrent_node.getKey()){
      return TreeSearch(parrent_node.getLeft_child(), k);
    }else{
      return TreeSearch(parrent_node.getRight_child(),k);
    }
  }
  /**
   * @param k
   * finde den rette plads for ny værdig i binær træet
   * insert virker meget som search
   * vi spøger roden hvis den eksister.
   * Er du forældre til en node der er mindre eller støre end k værdien
   * hvis den er støre går vi mod den højersiden af træet ellers venstre,
   * så spøger vi dens node har du et barn som er støre eller mindre en k,
   * og så køre vi indtil vi finder den rette plads eller en null værdi for k værdien og laver en ny node med den værdi,
   * Der er to typer at træer enten er de balanceret ellers er de ikke balanceret
   */
  @Override
  public void insert(int k) {
    BinNode y =null;
    BinNode x = this.root;
    // så længe roden ikke er null kan vi finde en knude til den.
    while (x!=null){
      y=x;
        // valider om k høre til end af parrentes børn
       if (x.getKey()>k){
          x=x.getLeft_child();
       }else{
         x=x.getRight_child();
       }
    }
    // laver en ny node afhængigt dens k værdig i form af hvilket barn parrenten skal have
    if (y==null){
      this.root =new BinNode(k);
    }else if(k<y.getKey()){
      y.setLeft_child(new BinNode(k));
    }else{
      y.setRight_child(new BinNode(k));
    }
  }
  /**
   * @return list at nodes værdier
   * Den instansiere en ny Arraylist.
   * og derefter kalder den travel for at gemme key i arraylisten
   */
  @Override
  public ArrayList<Integer> orderedTraversal() {
    ArrayList<Integer> data = new ArrayList<>();
    travel(this.root, data);
    return data;
  }
  /**
   * @param parrent_node
   * Er en recursive funktion der kalder sig selv til den bliver null
   *                *
   *          *   *   *   *
   *         * * * * * * * *
   * Den rejser gennem nodens børns børn og gemmer værdierne i this.data.
   * Den går left left left til den finder en nul værdi og så hør den right left til den finder en ny værdi at gemme i data.
   */
  private void travel(BinNode parrent_node,ArrayList<Integer> data ){
    if (parrent_node !=null){
      travel(parrent_node.getLeft_child(), data);
      data.add(parrent_node.getKey());
      travel(parrent_node.getRight_child(), data);
    }
  }
}
