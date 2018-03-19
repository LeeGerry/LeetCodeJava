package leetcode.matrix;
/**
 * https://leetcode.com/problems/rotate-image/
 * Given input matrix = 
		[
		  [1,2,3],
		  [4,5,6],
		  [7,8,9]
		],

rotate the input matrix in-place such that it becomes:
		[
		  [7,4,1],
		  [8,5,2],
		  [9,6,3]
		]
给定一个二维数组，顺时针旋转90度
 */
public class RotateImage {
	
	public void rotate(int[][] matrix) {
		for(int layer = 0; layer < matrix.length/2; layer++){
			int first = layer;
			int last = matrix.length - 1 - layer;
			for(int i = first; i<last; i++){
				int offset = i - first;
				// 存储上边
				int top = matrix[first][i];
				// 左到上
				matrix[first][i] = matrix[last-offset][first];
				// 下到左
				matrix[last-offset][first] = matrix[last][last-offset];
				// 右到下
				matrix[last][last-offset] = matrix[i][last];
				// 上到右
				matrix[i][last] = top;
			}
		}
	}
	
	/** 1 2 3		1 4 7		7 4 1
	 *  4 5 6   ->  2 5 8 ->    8 5 2
	 *  7 8 9		3 6 9       9 6 3
	 */
	/*
	 * 分两次按轴对称交换元素
	 */
	public void rotate2(int[][] matrix) {
		// 第一次以 1 5 9对角线为轴，交换两边元素
		for(int i = 0; i<matrix.length; i++){
			for(int j = i; j<matrix.length;j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		// 第二次以4 5 6 为轴，交换两边元素
		for(int i=0;i<matrix.length;i++){
			for(int j=0; j<matrix.length / 2; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1 - j];
				matrix[i][matrix.length -1-j] = temp;
			}
		}
	}


	/** 1 2 3		1 4 7		7 4 1
	 *  4 5 6   ->  2 5 8 ->    8 5 2
	 *  7 8 9		3 6 9       9 6 3
	 */
	public void rotate3(int[][] matrix){

	}
}
