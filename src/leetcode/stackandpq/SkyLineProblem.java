package leetcode.stackandpq;

import java.util.*;

/** LeetCode 218
 * https://leetcode.com/problems/the-skyline-problem/description/
 * https://www.youtube.com/watch?v=7AE-VCGEhtI
 * 
 */
public class SkyLineProblem {
	// time: O(n Log n) space: O(n)
	public List<int[]> getSkyline(int[][] buildings){
		List<int[]> res = new ArrayList<>();
		List<int[]> heights = new ArrayList<>();
		for(int[] b: buildings){
			heights.add(new int[]{b[0], -b[2]});
			heights.add(new int[]{b[1], b[2]});
		}
		Collections.sort(heights, (a,b)->(a[0] == b[0] ? a[1]-b[1]:a[0]-b[0]));
		TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
		map.put(0, 1);
		int prev = 0;
		for(int[] h: heights){
			if(h[1] < 0){
				map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
			}else{
				int count = map.get(h[1]);
				if(count == 1)	map.remove(h[1]);
				else			map.put(h[1], count - 1);
			}
			int current = map.firstKey();
			if(prev != current){
				res.add(new int[]{h[0], current});
				prev = current;
			}
		}
		return res;
	}

	class Line{
		int x;
		int height;
		boolean isLeft;
		public Line(int x, int h, boolean left){
			this.x = x; 	this.height = h; 	this.isLeft = left;
		}
	}
	public List<int[]> getSkyline2(int[][] buildings) {
		List<int[]> buildingList = new ArrayList<>();
		for (int[] b: buildings) {
			buildingList.add(new int[]{b[0], -b[2]});
			buildingList.add(new int[]{b[1], b[2]});
		}
		Collections.sort(buildingList, (a, b) -> {
			if (a[0] != b[0])	return a[0] - b[0];
			else return a[1] - b[1];
		});
		List<int[]> res = new ArrayList<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		maxHeap.offer(0);
		int preMax = 0;
		for (int[] b: buildingList) {
			if (b[1] < 0) 	maxHeap.offer(-b[1]);
			else 			maxHeap.remove(b[1]);
			int currentHeight = maxHeap.peek();
			if (currentHeight != preMax) {
				res.add(new int[]{b[0], currentHeight});
				preMax = currentHeight;
			}
		}
		return res;
	}
	private static void printArray(List<int[]> array){
		for (int[] arr: array) {
			System.out.print(Arrays.toString(arr));
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
	//		int[][] buildings = {{1, 3, 7}, {2, 3, 5}};
		SkyLineProblem sp = new SkyLineProblem();
		printArray(sp.getSkyline(buildings));

		printArray(sp.getSkyline2(buildings));
	}
}
