package leetcode.matrix;

import java.util.PriorityQueue;
/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * 
 *Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

	Note that it is the kth smallest element in the sorted order, not the kth distinct element.
	Example:
		matrix = [
		   [ 1,  5,  9],
		   [10, 11, 13],
		   [12, 13, 15]
		],
		k = 8, return 13.
	给定二维数组，行递增，列递增，返回第K小的值
	两种方法：1.用优先级队列PriorityQueue
			2.用Binary Search做，
 */
class Tuple{
	int x;
	int y;
	int val;
	public Tuple(int x, int y, int val){
		this.x = x;
		this.y = y;
		this.val = val;
	}
}

public class KthSmallestElement {
	// time: O(n * log n)-->把第0行元素加入队列for循环中有优先级队列的插入操作，for是n,优先级队列插入是logn
	// space:O(n)--> 优先级队列大小为列数
	public int kthSmallest(int[][] matrix, int k){
		// 优先级队列大小为列数，比较器设置为小顶堆
		PriorityQueue<Tuple> pq = new PriorityQueue<>(matrix[0].length, (a,b) -> (a.val-b.val));
		// 把第0行的各元素加入队列
		for(int i=0; i<matrix[0].length; i++)	pq.offer(new Tuple(0, i, matrix[0][i]));
		// 从优先级队列中依次poll k-1 次，最后再poll的就是第k小的
		for(int i=0; i<k-1; i++){
			Tuple t = pq.poll(); // poll出一个，记为t
			if(t.x == matrix.length - 1)	continue; // 如果t已经是该列最后一行的元素，则该列完毕，continue
			pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y])); // 把t下面的那个元素加入队列
		}
		return pq.poll().val;
	}

	// time: n* log(max - min) 	space: 1
	public int kthSmallest2(int[][] matrix, int k){
		int n = matrix.length;
		int left = matrix[0][0];
		int right = matrix[n-1][n-1];
		while(left + 1 < right){
			int mid = left + (right - left) / 2;
			int num = count(matrix, mid);
			if(num >= k)	right = mid;
			else			left = mid;
		}
		if(count(matrix, right) <= k-1)	return right;
		return left;
	}

	private int count(int[][] matrix, int target) {
		int n = matrix.length;
		int res = 0;
		int i = n-1, j= 0;
		while(i>=0 && j<n){
			if(matrix[i][j] < target){
				res += i+1;
				j++;
			}else{
				i--;
			}
		}
		return res;
	}
}
