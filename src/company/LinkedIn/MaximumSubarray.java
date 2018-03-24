package company.LinkedIn;

/**
 * LC 205
 * Find the contiguous subarray within an array (containing at least one number)which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.

 给定一个长度为n的序列，求其最大子序列和。
 算法思路：使用动态规划求解，dp[i]表示在尾数在位置i上的最大子序列和，那么dp[i]可以表示为
     dp[i] = max(dp[i-1] + nums[i],nums[i])
     dp[0] = nums[0]
     其中dp[0]表示初值。
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int res = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        //int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {1,2};
        System.out.println(new MaximumSubarray().maxSubArray(nums));
    }
}
