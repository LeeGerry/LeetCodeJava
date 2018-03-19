package leetcode.matrix;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 * time: m+n
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
每一行有序，每一列有序，和Search2dMatrix1不一样的地方是：
		该题目没有说上一行最后一个元素小于下一行第一个元素，所以是整体无序，局部有序。
		解法：从右上角开始找，或者从左下角开始找
		
 */
public class Search2DMatrix2 {
	// 从右上角开始找
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0)	return false;
		//从右上角开始找的话while起始处应该为第0行最后一列
		int row = 0; // 第0行
		int col = matrix[0].length - 1; // 最后一列
		
		while(col >= 0 && row <= matrix.length-1){ // 从右上角开始
			if(target == matrix[row][col])		return true;// 找到
			else if(target < matrix[row][col])	col--;		// 目标小于右上角元素，说明右上角元素对应的整列就可以排除，列数往左移
			else								row++;		// 目标大于右上角元素，说明右上角元素左对应的整行可以排除，行数向下移
		}
		return false;//循环结束说明没有找到，返回false
	}
}
