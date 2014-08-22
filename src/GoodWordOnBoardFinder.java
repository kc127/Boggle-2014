/*
 * Kanchan Chauhan <kc127>
 */

import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder {
	
	
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		for(int r=0; r < board.size(); r++){ 
			for(int c=0; c < board.size(); c++){ 
				if (helper(word,0,r,c,board,list)){ 
					 return list;
				}
			}
		}
		list.clear();
		return list;
	}
	
	private boolean helper(String word, int index, int row, int col,BoggleBoard board, List<BoardCell> list){
		BoardCell cell = new BoardCell(row, col);
	
		// if the index is past the end of the string or if the word has been found
		if( index >= word.length()){
			return true;
		}
		// if either row or col is out of bounds
		if( row >= board.size() || col >= board.size()){
			return false;
		}
		if(row < 0 || col < 0){
			return false;
		}
		
		String partOfWord;
		if(index+1 != word.length() && word.substring(index,index+1).equals("Q")){
			 partOfWord = word.substring(index, index+2);
		}else{
			partOfWord = word.substring(index,index+1);
		}
		
		//check if first letter of the substring starting at index matches the boardcell
		// check to see if the boardcell appears in the list
		if(board.getFace(row, col).equals(partOfWord)) {
			// if the alphabet is not in the list, add it
			if(!list.contains(cell)) {
				list.add(cell);
				// do the recursion for the possible directions the cell could take
				int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1}; 
				int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
				for(int k=0; k < rdelta.length; k++){ 
					// Q condition
					if(index+1!=word.length() && word.substring(index,index+1).equals("Q")) {
						if (helper(word, index+2, row+rdelta[k], col+cdelta[k], board,list)){ 
							return true;
						}
					}
					else {
						if (helper(word, index+1, row+rdelta[k], col+cdelta[k], board,list)) {
							return true; 
					}
					}
				} 
				list.remove(cell);
			}
		}
		return false;
	}

}
	