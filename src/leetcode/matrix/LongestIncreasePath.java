package leetcode.matrix;
/**
 * LeetCode329
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
 * 在给定的二维数组中找最长的递增路径的长度
 * Given an integer matrix, find the length of the longest increasing path.
		From each cell, you can either move to four directions: left, right, up or down. 
		You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
		nums = [
		  [9,9,4],
		  [6,6,8],
		  [2,1,1]
		]
		Return 4. The longest increasing path is [1, 2, 6, 9].

Example 2:
		nums = [
		  [3,4,5],
		  [3,2,6],
		  [2,2,1]
		]
		Return 4. The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * time: m*n	space: m*n
 */
public class LongestIncreasePath {
	public int longestIncreasingPath(int[][] matrix){
		if(matrix == null || matrix.length == 0)	return 0;
		int result = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] cache = new int[m][n]; // 缓存，用来存储计算好的结果，降低时间复杂度
		
		for(int i=0; i<m; i++){
			for(int j = 0;j<n; j++){
				int max = dfs(matrix, Integer.MIN_VALUE, i, j, m, n, cache);
				result = Math.max(result, max);
			}
		}
		return result;
	}

	private int dfs(int[][] matrix, int min, int i, int j, int m, int n, int[][] cache) {
		if(i<0 || j<0 || i>=m|| j>=n || matrix[i][j]<=min)	return 0; // 边界判断
		if(cache[i][j] != 0)	return cache[i][j]; // 如果缓存中有值，直接返回
		min = matrix[i][j];
		// 分别向上下左右各走一步
		int up = dfs(matrix, min, i-1, j, m, n, cache) + 1;
		int down = dfs(matrix, min, i+1, j, m, n, cache) + 1;
		int left = dfs(matrix, min, i, j-1, m, n, cache) + 1;
		int right = dfs(matrix, min, i, j+1, m, n, cache) + 1;
		// 取四个值中的最大值放入缓存中并返回
		int max = Math.max(up, Math.max(down, Math.max(left, right)));
		cache[i][j] = max;
		return max;
	}
}
