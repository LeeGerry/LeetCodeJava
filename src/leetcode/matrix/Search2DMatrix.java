package leetcode.matrix;
/**
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

	Integers in each row are sorted from left to right.
	The first integer of each row is greater than the last integer of the previous row.
	For example,
	
	Consider the following matrix:
	
	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
	Given target = 3, return true.
	数组按行递增，按列递增，并且下一行的头比上一行的尾大，所以二维数组可以当做一维数组来分析。
	利用二分法来搜索排序数组，时间复杂度log(元素个数 = m*n)
 * time: log(n*m) space:1
 */
public class Search2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target){
		if(matrix == null || matrix.length == 0)return false;
		int row = matrix.length;
		int col = matrix[0].length;
		int begin = 0, end = row * col - 1; // 第一个和最后一个元素索引
		while(begin <= end){
			int mid = (end - begin) / 2 + begin;
			int value = matrix[mid/col][mid%col]; 
			if(value == target)		return true;
			else if(value < target)	begin = mid+1;
			else					end = mid-1;
		}
		return false;
	}
}
