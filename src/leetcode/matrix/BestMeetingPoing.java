package leetcode.matrix;

import java.util.ArrayList;
/**
 * LeetCode 296 Best Meeting Point
 * https://www.youtube.com/watch?v=vCRnwe0L0sg&index=12&list=PLvyIyKZVcfAkMmWl2SIreK_2UINBDq-U7
 * 
A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, 
where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
给定一个二维数组，1代表城市，0代表路，找到一个点求最小距离，满足从这个点到所有的城市处的曼哈顿距离最小，返回这个距离。
两点距离计算方法是distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|
解释：降维。把二维将为一维。http://www.chenguanghe.com/best-meeting-point/
	计算一下x轴上有多少个1的投影，y轴上有多少个1的投影，分别计算一遍。
 * 
 */
public class BestMeetingPoing {
	//time: O(m*n) space: O(max(m,n))
	public int minTotalDistance(int[][] grid){
		int m = grid.length;
		int n = grid[0].length;
		ArrayList<Integer> I = new ArrayList<>();
		ArrayList<Integer> J = new ArrayList<>();
		for(int i = 0; i<m;i++){
			for(int j = 0;j<n;j++){
				if(grid[i][j] == 1)
					I.add(i);
			}
		}
		System.out.println(I.toString());
		for(int j=0;j<n; j++){
			for(int i=0;i<m;i++){
				if(grid[i][j] == 1)
					J.add(j);
			}
		}
		System.out.println(J.toString());
		return min(I) + min(J);
	}

	private int min(ArrayList<Integer> list) {
		int i = 0, j = list.size() - 1;
		int sum = 0;
		while(i<j){
			sum += list.get(j--) - list.get(i++);
		}
		return sum;
	}
	public static void main(String[] args) {
		int[][] array = {
				{1,0,0,0,1,1,0,1},
				{0,0,0,0,0,0,0,1},
				{0,0,1,0,0,0,1,0},
				{1,0,0,1,1,0,0,0},
		};
		System.out.println(new BestMeetingPoing().minTotalDistance(array));
	}
}
