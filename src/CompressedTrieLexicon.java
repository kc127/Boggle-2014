import java.util.ArrayList;

/*
 * Kanchan Chauhan <kc127>
 */
public class CompressedTrieLexicon extends TrieLexicon {
     // to compress trie I need to find every leaf 
	private ArrayList<Node> myLeaves = new ArrayList<Node>();
	
	// create constructor for compressedTrieLexicon
	public CompressedTrieLexicon(){
		myLeaves = new ArrayList<Node>();
	}
	

      @Override
      public void load(ArrayList<String> list) {
          super.load(list);
          compress();
      }
      
      // Write a method compress to perform one child compression

  }