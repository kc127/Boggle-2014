/*
 * Kanchan Chauhan <kc127>
 */

import java.util.*;

public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    /*
     * StringBuilder is mutable, therefore use stringBuilder instead of string ( meaning could be changed)
     */
    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    /*
     * use BinarySearch. If the given string is not a word in sorted
     * lexicon then, BinarySearch should tell you where the word would be. 
     * Once you find you the location, check for prefix.
     */
    public LexStatus wordStatus(String s) {
    	// binarySearch for s in myWords, should return index of the search key
    	int index = Collections.binarySearch(myWords,s);
    	// if the word is in the list, return word
    	if(index>=0){
    		return LexStatus.WORD;
    	}
    	// if not in the list, then determine the insertion point
    	if(index<0){
    		int i = Math.abs(index);
    		if((i-1) >= myWords.size()){
    			return LexStatus.NOT_WORD;
    		// once you find the location, check to see if it's the prefix of the word 
    		// previously located in Math.abs(index)-1
    		}if(myWords.get(i-1).startsWith(s)){
    			return LexStatus.PREFIX;
    		}
    	}
        
        return LexStatus.NOT_WORD;
    }

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
