package leetcode.matrix;
/**
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 * Time n^2
 * Space n^2
 * 给一个n 例如 n=3，产生一个n*n的螺旋数组
 * 		1 2 3 
 * 		8 9	4
 * 		7 6 5
 * 和SpiralMatrix1一样，从左到右，从上到下，从右到左，从下到上
 */
public class SpiralMatrix2 {
	public int[][] generateMatrix(int n){
		int[][] matrix = new int[n][n];
		
		// 标记
		int rowBegin = 0;
		int colBegin = 0;
		int rowEnd = n-1;
		int colEnd = n-1;
		// 当前填充数字
		int num = 1;
		// while循环判断终止条件
		while(rowBegin <= rowEnd && colBegin <= colEnd){
			// 从左向右
			for(int i = colBegin; i<=colEnd; i++)
				matrix[rowBegin][i] = num++;
			rowBegin++;
			
			// 坐上向下
			for(int i = rowBegin; i<=rowEnd; i++)
				matrix[i][colEnd] = num++;
			colEnd--;
			
			// 从右向左
			for(int i = colEnd; i>=colBegin; i--)
				matrix[rowEnd][i] = num++;
			rowEnd--;
			
			// 从下向上
			for(int i = rowEnd; i>=rowBegin; i--)
				matrix[i][colBegin] = num++;
			colBegin++;
		}
		return matrix;
	}
}
