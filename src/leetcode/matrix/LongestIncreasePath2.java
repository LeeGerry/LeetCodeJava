package leetcode.matrix;

import java.util.ArrayList;

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

public class LongestIncreasePath2 {
	public class Result{
		int x;
		int y;
		int maxValue;
		ArrayList<Integer> path;

		public Result() {
			this.x = 0;
			this.y = 0;
			this.maxValue = 1;
			this.path = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "Result{" +
					"x=" + x +
					", y=" + y +
					", maxValue=" + maxValue +
					", path=" + path +
					'}';
		}
	}
	Result result;
	int m, n;
	public Result longestIncreasingPath(int[][] matrix){
		if (matrix == null || matrix.length == 0 | matrix[0].length == 0) return null;
		m = matrix.length;
		n = matrix[0].length;
		int[][] cache = new int[m][n];
		result = new Result();

		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				int len = dfs(matrix, i, j, cache);
				result.maxValue = Math.max(len, result.maxValue);
			}
		}
		result.path.add(matrix[result.x][result.y]);
		int curr = result.maxValue - 1;
		int row = result.x;
		int col = result.y;
		while (curr > 0){
			for (int i = 0; i < 4; i++){
				int newX = row + dx[i];
				int newY = col + dy[i];
				if (newX < 0 || newY < 0 || newX >= m || newY >= n)	continue;
				if (cache[newX][newY] == curr) {
					result.path.add(matrix[newX][newY]);
					curr--;
					row = newX;
					col = newY;
					break;
				}
			}
		}
		System.out.println(result);
		return result;
	}
	int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	private int dfs(int[][] matrix, int i, int j, int[][] cache){
		if (cache[i][j] != 0)	return cache[i][j];
		int max = 1;
		for (int k = 0; k < 4; k++){
			int newX = i + dx[k];
			int newY = j + dy[k];
			if (newX < 0 || newY < 0 || newX >= m || newY >= n || matrix[newX][newY] <= matrix[i][j])	continue;
			int len = 1 + dfs(matrix, newX, newY, cache);
			max = Math.max(max, len);
			if (result.maxValue < len){
				result.maxValue = len;
				result.x = i;
				result.y = j;
			}
		}
		cache[i][j] = max;
		return max;
	}

	public static void main(String[] args) {
		int[][] matrix = {
				{9,9,4},
				{6,6,8},
				{2,1,1}
		};
		LongestIncreasePath2 l2 = new LongestIncreasePath2();
		l2.longestIncreasingPath(matrix);
	}
}
