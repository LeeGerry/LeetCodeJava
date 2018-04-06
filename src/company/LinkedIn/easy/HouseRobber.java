package company.LinkedIn.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * LC 198
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.

 */
public class HouseRobber {
	/* 解题思路DP：
	nums: [2,3,5,1]
	dp  : [_,_,_,_,_]    // dp[i]表示到第i个房间，能取到的最大值
	dp[0] = 0, dp[1] = 2;// 当有0个房间，肯定是0；只有1个房间，只能抢
	初始后 [0,2,_,_,_]
	dp[2]: 到第2个房间，两种策略，抢得3；不抢得2；
	      [0,2,3,_,_]
	dp[3]: 到第3个房间，		  抢得7;不抢得3；
		  [0,2,3,7,_]
	dp[4]: 到第4个房间，		  抢的4；不抢得7；
		  [0,2,3,7,7]

	状态转移方程：
	dp[i] = max(dp[i - 1], dp[i - 2] + num[i - 1])

	*/
	public static int rob(int[] nums) {
		if (nums == null || nums.length == 0)	return 0;
		int n = nums.length;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = nums[0];
		for (int i = 2; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
		}
		return dp[n];
	}
	/*
		用三个变量来替换掉space为O(n)的数组,max, prepre:, pre
	 */
	public static int rob2(int[] nums) {
		if (nums == null || nums.length == 0)	return 0;
		if (nums.length == 1)	return nums[0];
		int max = 0, prepre = 0, pre = nums[0];
		for (int i = 1; i < nums.length; i++) {
			max = Math.max(pre, prepre + nums[i]);
			prepre = pre;
			pre = max;
		}
		return max;
	}
    // 得到抢的房屋序列
    // 比如{ 2, 3, 5, 1 }， 最后能抢到的最大值是7.返回{1,3},因为抢的是第一个和第三个
	static List<Integer> getRob(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		int[] dp = new int[nums.length + 1];
		int[] path = new int[nums.length + 1]; //存储抢的房屋路径，存储的是从哪个抢的房屋过来的
		dp[0] = 0;
		dp[1] = nums[0];
		Arrays.fill(path, Integer.MIN_VALUE);
		for (int i = 2; i <= nums.length; i++) {
			if (dp[i - 2] + nums[i - 1] > dp[i - 1]) {
				path[i] = i - 2; // 抢当前房间，则是从i-2过来的
				dp[i] = dp[i - 2] + nums[i - 1];
			} else {
				path[i] = i - 1; // 不抢，则是从i-1过来的
				dp[i] = dp[i - 1];
			}
		}

		LinkedList<Integer> res = new LinkedList<>();
		int i = nums.length;
		while (i > 0) {
			if (path[i] == i - 1) {
				i--;
			} else {
				res.addFirst(i);
				i = path[i];
			}
		}
        System.out.println(Arrays.toString(path));
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 5, 1, 2, 1, 2 };
		System.out.println(getRob(arr));
		//System.out.println(rob1(arr));
	}
}
