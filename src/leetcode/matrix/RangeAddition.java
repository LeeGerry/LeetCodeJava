package leetcode.matrix;

import java.util.Arrays;

/**
 * LeetCode 370 Range Addition
 * Given:
 * 		length = 5,
 *  	updates = [
 *      	[1,  3,  2],
 *      	[2,  4,  3],
 *      	[0,  2, -2]
 *   	]
 * Output:
 * 		[-2, 0, 3, 5, 3]
 * length 5代表要产生的一维数组长度，初始化都是0: [0 0 0 0 0 ]
 * updates二维数组代表增量数组：每一行有三个元素，第一个元素是起始坐标，第二个是终止坐标，第三个是增量。具体操作如下：
 * 										[ 0 0 0 0 0 ]
 * 第一行：[1 3 2]	从1到3，元素加2		[ 0 2 2 2 0 ]
 * 第二行：[2 4 3]	从2到4，元素加3		[ 0 2 5 5 3 ]
 * 第三行：[0 2 -2]	从0到2，元素加-2		[-2 0 3 5 3 ]
 * 最终返回结果[-2, 0, 3, 5, 3]
 * 
 * 暴力解法需要两个循环，这里用一个巧妙的解法：
 *  																			[ 0 0 0 0 0  ]
 *  									  								  		  0 1 2 3 4
 * 第一行：[1 3 2]	从1到3，元素加2，我们只操作index=1处+2和(index=3+1 = 4)处-2		[ 0 2 0 0 -2 ]
 * 第二行：[2 4 3]	从2到4，元素加3，我们只操作index=2处+3和(index=4+1 = 5)处-3		[ 0 2 3 0 -2 ]，因为5越界了，不用操作
 * 第三行：[0 2 -2]	从0到2，元素加-2，  只操作index=0处-2和(index=2+1 = 3)处-(-2)=2 	[-2 2 3 2 -2 ]
 * 
 * 再从result的第1个位置进行更新。每个元素值=当前值+前面的元素值。如下
 * 		[-2 2 3 2 -2]
 * 		[-2 0 3 5  3]
 * 这样只是用了两遍循环，第一个循环中取出updates中的每一行元素进行解析，修改result中的两个位置
 * 					 第二个循环中更新一遍result的值即可。
 */
public class RangeAddition {
	// time: O(k+n) space:O(n)
	public int[] getModifiedArray(int length, int[][] updates){
		int[] result = new int[length];
		for(int[] update: updates){
			int value = update[2];
			int start = update[0];
			int end = update[1];
			result[start] += value;
			if(end + 1 < length)	
				result[end + 1] -= value;
		}
		for(int i = 1; i<length; i++)
			result[i] += result[i-1];
		return result;
	}

	public static void main(String[] args) {
		int[][] a = {{1,2,5},{3,4,7},{2,4,3},{0,2,-1}};
		RangeAddition ra = new RangeAddition();
		System.out.println(Arrays.toString(ra.getModifiedArray(5,a)));
	}
}
