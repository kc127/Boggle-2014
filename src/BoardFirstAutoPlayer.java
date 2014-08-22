import java.util.ArrayList;
import java.util.List;

/*
 * Kanchan Chauhan <kc127>
 */

public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    /*
     * this class generates potential words from the board
     * use recursive helper method to find all words starting at a specified row/col
     */
	
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
    	clear();
    	List<BoardCell> list = new ArrayList<BoardCell>();
    	StringBuilder string = new StringBuilder();
	    // TODO: you write this code
    	for(int r = 0; r<board.size(); r++){
    		for(int c = 0; c<board.size(); c++){
    			helper(board, lex, r,c, list, string, minLength);
    		}
    	}
    }
    
    /*
     * Recursive helper method, ILexicon lexicons could be any of the given 3 implementations
     */
    	private void helper(BoggleBoard board, ILexicon lex, int row, int col, List<BoardCell> list, StringBuilder stringSoFar, int minLength){
    		BoardCell cell = new BoardCell(row, col);
    		// if either row or col is out of bounds
    		if( row >= board.size() || col >= board.size()){
    			return;
    		}
    		if(row < 0 || col < 0){
    			return;
    		}
    		
    		// if the list does not contain the cell value, add it to the list
    		if(!list.contains(cell)) {
    			list.add(cell);
    			// legal and not used, so add the cell value to the stringbuilder
    			stringSoFar.append(board.getFace(row,col));
    			// now checktosee if the search so far is word, prefix or not_word
    			LexStatus checkStatus = lex.wordStatus(stringSoFar);
    			//if WORD then add it to the collection
    			if(checkStatus.equals(LexStatus.WORD) || checkStatus.equals(LexStatus.PREFIX)){
    				if(checkStatus.equals(LexStatus.WORD)){
    					if(stringSoFar.length() >=minLength)
    						add(stringSoFar.toString());
    				}
    				// if a PREFIX then	
    				// do the recursion now to continue the search by calling helper method 
    				// for each adjacent cube with the string built so far
    				int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1}; 
    				int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
    				for(int i=0; i < rdelta.length; i++){ 
    					helper(board, lex, row+rdelta[i],col+cdelta[i],list, stringSoFar, minLength);
    				}
    				list.remove(cell);
    				if((board.getFace(row,col)).length()>1) {
    					stringSoFar.deleteCharAt(stringSoFar.length()-1);
    					stringSoFar.deleteCharAt(stringSoFar.length()-1);
    				}
    				else {
    					stringSoFar.deleteCharAt(stringSoFar.length()-1);
    				}
    			}
    			
    			
    			
    			// if not a WORD or PREFIX then 
    			else{	
    				list.remove(cell);
    				if((board.getFace(row, col)).length()>1) {
    					// note, removing the last char
    					stringSoFar.deleteCharAt(stringSoFar.length()-1);
    					// again
    					stringSoFar.deleteCharAt(stringSoFar.length()-1);
    				}
    				else {
    					// then, make it empty
    					stringSoFar.deleteCharAt(stringSoFar.length()-1);
    				}
    			}
    		}
    	}
    }

		



