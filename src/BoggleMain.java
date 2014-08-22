import java.util.Random;
import java.io.InputStream;


/**
 * Play a game of Boggle. To play code must instantiate the
 * BoggleGui gui object with a working IWordOnBoardFinder and
 * a working ILexicon, as well as a working IAutoPlayer.
 */

public class BoggleMain {

    public static void main(String[] args) {
        
        ILexicon lexicon = new TrieLexicon();
        IWordOnBoardFinder finder = new GoodWordOnBoardFinder();
        
        InputStream is = lexicon.getClass().getResourceAsStream("/bogwords.txt");      
        IAutoPlayer compPlayer = new BoardFirstAutoPlayer();
        BoggleGUI bgui = new BoggleGUI(lexicon,finder,is, compPlayer);
    }

}
