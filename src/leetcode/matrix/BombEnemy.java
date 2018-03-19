package leetcode.matrix;

/**
 * LeetCode 361 https://www.youtube.com/watch?v=ykOJqf4TWmU
 * Given a 2D grid, each cell is either a wall 'Y', an enemy 'X' or empty '0' (the number zero), 
   return the maximum enemies you can kill using one bomb.
   
   The bomb kills all the enemies in the same row and column from the planted point 
   until it hits the wall since the wall is too strong to be destroyed.
	
   Note that you can only put the bomb at an empty cell.

Example:

	For the given grid
		{'W', '0', 'E', '0', '0'},
		{'E', 'E', '0', 'W', 'E'},
		{'0', '0', 'E', '0', '0'},
		{'0', '0', 'E', '0', '0'}
	return 3. (Placing a bomb at (1,1) kills 3 enemies)
	需要按行按列双for循环遍历各个元素，对于上面的例子，在[2,2]处投放炸弹能炸掉5个E，是最大的值。
	思路：rowCount保存当前行的E个数，colCount[n]为一维列数组，colCount[j]保存扫描到的位置当前列能炸的E个数。
		每次扫描到一个位置就：对应的行E个数（能有效炸掉） + 对应的列的E个数（能有效炸掉）  得到一个值，和result比较取大的。
 */
public class BombEnemy {
	public int maxKilledEnemies(char[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int m = grid.length;
		int n = grid[0].length;
		int rowCount = 0;
		int[] colCounts = new int[n];
		int result = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j == 0 || grid[i][j - 1] == 'W') { // 如果扫描到最左端需要rowCount清零，或者左边那个位置是墙壁，也需要清零
					rowCount = 0;
					for (int k = j; k < n && grid[i][k] != 'W'; k++) { // 这种情况下清零后，要从当前列号往后，重新计算有多少个E
						rowCount += grid[i][k] == 'E' ? 1 : 0;
					}
				}
				if (i == 0 || grid[i - 1][j] == 'W') { // 如果扫描到的是第0行的元素，或者上面那个位置是墙壁，需要把当前列的E个数清零
					colCounts[j] = 0;
					for (int k = i; k < m && grid[k][j] != 'W'; k++) {// 这种情况下，要从当前行号往下，重新计算有多少个E，放入colCount[j]中
						colCounts[j] += grid[k][j] == 'E' ? 1 : 0;
					}
				}
				if (grid[i][j] == '0') { // 如果碰到可以放炸弹的地方，就把对应的列号的数组中的值（当前列能炸掉的E的个数）+ rowCount 计算一下和结果比较去较大值
					result = Math.max(result, colCounts[j] + rowCount);
				}
			}
		}

		return result;
	}

	public int solution(char[][] matrix){
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)	return 0;
		int res = 0, row = 0, m = matrix.length, n = matrix[0].length;
		int[] col = new int[n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 最左边 || 左边是’w’, row清零，重新计算
				if (j == 0 || matrix[i][j - 1] == 'W') {
					row = 0;
					for (int k = j; k < n && matrix[i][k] != 'W'; k++) {
						row += matrix[i][k] == 'E' ? 1 : 0;
					}
				}
				// 最上边 || 上边是’w’, col[j]清零，重新计算
				if (i == 0 || matrix[i - 1][j] == 'W') {
					col[j] = 0;
					for (int k = i; k < m && matrix[k][j] != 'W'; k++) {
						col[j] += matrix[k][j] == 'E' ? 1 : 0;
					}
				}
				// 是0，计算一下row + col[j] 和res 比较 取最大
				if (matrix[i][j] == '0')	res = Math.max(res, row + col[j]);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		char[][] chars = {
				{'W', '0', 'E', '0', '0'},
				{'E', 'E', '0', 'W', 'E'},
				{'0', '0', 'E', '0', '0'},
				{'0', '0', 'E', '0', '0'}
		};
		char[][] chars1 = {
				{'E'}
		};
		BombEnemy be = new BombEnemy();
		System.out.println(be.maxKilledEnemies(chars1));
		System.out.println(be.solution(chars1));

	}
}
