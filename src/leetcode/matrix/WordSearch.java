package leetcode.matrix;

/**
 * LeetCode 79
 * https://leetcode.com/problems/word-search/description/
 * Given a 2D board and a word, find if the word exists in the grid.
	The word can be constructed from letters of sequentially adjacent cell, 
	where "adjacent" cells are those horizontally or vertically neighboring. 
	The same letter cell may not be used more than once.
	
	For example,
	Given board =
			[
			  ['A','B','C','E'],
			  ['S','F','C','S'],
			  ['A','D','E','E']
			]
			word = "ABCCED", -> returns true,
			word = "SEE",    -> returns true,
			word = "ABCB",   -> returns false.
			从二维数组字母表中找到给出的单词串。找的时候数组中的元素每次上下左右移动一下找，不能倒回去。比如ABABC，不能返回true。
			
 */
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[0].length; j++){
				if(exist(board, i, j, word, 0))	return true;
			}
		}
		return false;
	}

	private boolean exist(char[][] board, int i, int j, String word, int start) {
		if(start >= word.length())	return true; // 如果最后一个字符已经匹配，则找到，返回true
		if(i<0 || i>= board.length || j<0 || j >= board[0].length)	return false; // 边界非法返回false
		if(board[i][j] == word.charAt(start++)){ // 找到匹配，start索引加一
			char c = board[i][j];	// 保存当前字符
			board[i][j] = '#';		// 更改当前字符，防止深搜倒回去
			// 上下左右四个方向深搜
			boolean result = exist(board, i+1, j, word, start)
							|| exist(board, i-1, j, word, start)
							|| exist(board, i, j+1, word, start)
							|| exist(board, i, j-1, word, start);
			board[i][j] = c; // 还原字符
			return result;
		}
		return false;
	}
}
