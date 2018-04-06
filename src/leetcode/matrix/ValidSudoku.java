package leetcode.matrix;

import java.util.HashSet;

/**
 * Determine if a Sudoku is valid.
The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * https://leetcode.com/problems/valid-sudoku/description/
 * https://www.youtube.com/watch?v=4-SF0-p98NM&t=3s
 * 
 * 	每一行必须是数字1~9且不重复
	每一列必须是数字1~9且不重复
	每一个小九宫格（互不交叉，总共九个小九宫格）必须是数字1~9且不重复


依次检查每行，每列，每个子九宫格是否出现重复元素，如果出现返回false，否则返回true.
难点在于表示第i个九宫格每个格点的坐标。

观察行号规律：
第0个九宫格：000111222; 第1个九宫格：000111222; 第2个九宫格：000111222;
第3个九宫格：333444555; 第4个九宫格：333444555; 第5个九宫格：333444555;
第6个九宫格：666777888; 第7个九宫格：666777888; 第8个九宫格：666777888;

可见对于每三个九宫格行号增3；对于单个九宫格，每三个格点行号增1。
因此第i个九宫格的第j个格点的行号可表示为i/3*3+j/3（每个小九宫格j都是从0~9递增）

观察列号规律：
第0个九宫格：012012012; 第1个九宫格：345345345; 第2个九宫格：678678678;
第3个九宫格：012012012; 第4个九宫格：345345345; 第5个九宫格：678678678;
第6个九宫格：012012012; 第7个九宫格：345345345; 第8个九宫格：678678678;

可见对于下个九宫格列号增3，循环周期为3；对于单个九宫格，每个格点行号增1，周期也为3。
周期的数学表示就是取模运算mod。
因此第i个九宫格的第j个格点的列号可表示为i%3*3+j%3（每个小九宫格j都是从0~9递增）


细节分析题
（1）检查行
（2）检查列
（3）检查9个子宫格
使用HashSet

 */
public class ValidSudoku {
	// Time: O(m*n)
	public boolean isValidSudoku1(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			HashSet<Character> rows = new HashSet<>();
			HashSet<Character> cols = new HashSet<>();
			HashSet<Character> cube = new HashSet<>();

			// 通过i来计算第i小正方形的第一个位置[0,0]点
			int rowIndex = 3 * (i / 3);
			int colIndex = 3 * (i % 3);
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.' && !rows.add(board[i][j]))
					return false;
				if (board[j][i] != '.' && !cols.add(board[j][i]))
					return false;
				// 通过j来计算实际在大正方形中的行列号
				int r = rowIndex + j / 3;
				int c = colIndex + j % 3;
				if (board[r][c] != '.' && !cube.add(board[r][c]))
					return false;
			}
		}
		return true;
	}

	public boolean isValidSudoku2(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.')
					continue;
				if (!valid(board, i, j))
					return false;
			}
		}
		return true;
	}

	private boolean valid(char[][] board, int i, int j) {
		for (int row = 0; row < board.length; row++) {
			if (row == i)
				continue;
			if (board[row][j] == board[i][j])
				return false;
		}
		for (int col = 0; col < board[0].length; col++) {
			if (col == i)
				continue;
			if (board[i][col] == board[i][j])
				return false;
		}
		for (int row = (i / 3) * 3; row < (i / 3 + 1) * 3; row++) {
			for (int col = (j / 3) * 3; col < (j / 3 + 1) * 3; col++) {
				if (row == i && col == j)
					continue;
				if (board[row][col] == board[i][j])
					return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		char[][] arr = {
				{'4','6','3','7','2','8','9','5','1'},
				{'2','5','9','4','6','1','7','3','8'},
				{'7','8','1','3','5','9','6','4','2'},
				{'5','3','2','1','9','7','4','8','6'},
				{'9','1','4','6','8','2','5','7','3'},
				{'6','7','8','5','4','3','1','2','9'},
				{'8','2','6','9','7','5','3','1','4'},
				{'1','4','7','2','3','6','8','9','5'},
				{'3','9','5','8','1','4','2','6','7'}
		};
		System.out.println(new ValidSudoku().isValidSudoku1(arr));
	}
}
