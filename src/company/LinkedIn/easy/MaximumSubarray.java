package company.LinkedIn.easy;

/**
 * LC 53
 * Find the contiguous subarray within an array (containing at least one number)which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.

 给定一个长度为n的序列，求其最大子序列和。

 */
public class MaximumSubarray {
    /**
     * 算法思路：使用DP求解，dp[i]表示在尾数在位置i上的最大子序列和，那么dp[i]可以表示为
        两种情况：1. dp[i] = nums[i], 此时记为value1.表示dp[i-1]<0,不累加的情况
                2. dp[i] = dp[i-1] + nums[i], 此时记为value2. 表示dp[i-1]>=0,累加的情况

                取较大者作为结果，放入 dp[i] = max(value1, value2)
            初始时：dp[0] = nums[0].
     time: O(n) space: O(n)
     */
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

    /**
       同样DP， 空间用两个滚动常量代替数组，降低空间复杂度
     * time: O(n) space: O(1)
     */
    public int maxSubArray1(int[] nums) {
        int localMax = Integer.MIN_VALUE, globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (localMax <= 0) { // localMax非正数，说明这时候不能累加，因为累加后只会变小
                localMax = nums[i]; // 需要置为当前元素值
            } else { // localMax是正数，说明此时要累加，累加后才能让当前序列和更大
                localMax += nums[i];
            }
            globalMax = Math.max(globalMax, localMax); // 更新globalMax
        }
        return globalMax;
    }
    public static void main(String[] args) {
        //int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {1,2};
        System.out.println(new MaximumSubarray().maxSubArray(nums));
    }
}
