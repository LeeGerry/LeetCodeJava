package leetcode.matrix;

import com.sun.org.apache.xml.internal.security.Init;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 317: https://www.youtube.com/watch?v=L3R0Yb4ET8k&t=756s
 * Time: O(m^2 * n^2) Space: O(m*n)
 * 				 {1,0,2,0,1},
				 {0,0,0,0,0},
				 {0,0,1,0,0}

	对二维数组，0表示通路，1表示城市，2表示障碍物，找到一个0的位置，使到每个城市的距离最短，只能上下左右走，一步表示1，返回结果是最小距离。
	如图，[1,2]处是0点，到三个城市的距离和最短，分别是3+3+1=7，返回7.
	
	例2：		{1,0,0,0,1},
				{0,0,0,2,2},
				{0,0,1,0,0}
	[0,2]处到三个城市距离和最短，2+2+2 = 6，返回6
	
	解法: 两层循环遍历每个点，如果是城市，就进行广搜。
		buildNum表示总共的城市数目
		dist[i][j]表示[i][j]处的0点到所有城市的距离和，每次都在已有的基础上进行累加。
		nums[i][j]表示[i][j]处的0点到所有城市的城市个数，每次多一个城市并且能到该城市，就自增
		BFS以后，从dist数组中找出最小值（满足是0点，距离!=0,该点到达城市的数目==buildNum）
 */
public class ShortestDistanceFromBuildings {
	int[][] grid;
	int m;
	int n;
	int[][] dist;    // dist[i][j]表示[i][j]处的0点到所有城市的距离和，每次都在已有的基础上进行累加
	int[][] nums;	 // nums[i][j]表示[i][j]处的0点到所有城市的城市个数，每次多一个城市并且能到该城市，就自增
	int buildingNum; // 城市总数
	int res;
	public void init(){
		m = grid.length;
		n = grid[0].length;
		dist = new int[m][n];
		nums = new int[m][n];
		buildingNum = 0;
		res = Integer.MAX_VALUE;
	}
	public int shortestDistance(int[][] grid) {
		this.grid = grid;
		if (grid == null | grid.length == 0) return -1;
		init();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) { // 如果是城市，就进行广搜
					buildingNum++; 	   // 城市数目++
					bfs(grid, i, j);   // 进行广搜
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 如果 是可作为meet地点的0点 && 该地点到能到达的城市和不为0 && 该地点能到的城市的个数为总城市数
				if (grid[i][j] == 0 && dist[i][j] != 0 && nums[i][j] == buildingNum) {
					res = Math.min(res, dist[i][j]); // 找到最小的那个
				}
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}
	// 广搜
	private void bfs(int[][] grid, int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { row, col });
		int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 上右下左四个方向
		boolean[][] visited = new boolean[m][n];
		int level = 0; // 广搜层次
		while (!queue.isEmpty()) {
			level++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				for (int k = 0; k < dirs.length; k++) { // (上右下左)四次
					int x = cur[0] + dirs[k][0];
					int y = cur[1] + dirs[k][1]; // 计算要搜索的方向
					// 边界越界，访问过，不是0点，都不用进行
					if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 0) {
						visited[x][y] = true; // 标记为访问过
						dist[x][y] += level;  // dist数组该点处累加层数，也就是距离
						nums[x][y]++;		  // 该点可到达的城市数加一
						queue.offer(new int[] { x, y }); // 入队
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		int[][] grid = {
				{1,0,0,0,1},
				{0,0,0,2,2},
				{0,0,1,0,0}
		};
		int[][] grid1 = {
				{1,0,1,0,1},
				{0,0,0,0,0},
				{0,0,0,0,0}
		};
		System.out.println(new ShortestDistanceFromBuildings().shortestDistance(grid));
	}
}
