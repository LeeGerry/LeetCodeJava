package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.spi.CalendarNameProvider;

/**
 * LeetCode 377
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.

 Example:
 nums = [1, 2, 3]
 target = 4
 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)
 给定一个正整数数组（元素无重复），给定目标target，找出组合的个数，使得组合中元素的和等于target。数组元素可以重复使用.
 Note that different sequences are counted as different combinations.
 Therefore the output is 7.
 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?
 */
public class CombinationSum4 {
    /**
     * DP: 例如，给定[a,b,c],找出和为5的组合的个数。
     * 假设函数为f(target)表示：用给定的数组构造出和为target的数组的个数。
       那么 和为5的数组的个数 = 和为(5-a)的组合的个数 + 和为(5-b)的组合的个数 + 和为(5-c)的组合的个数
     即 f(5) = f(5-a) + f(5-b) + f(5-c);
     得到状态转移方程：
     f(target) = f(target - nums[0]) + f(target-nums[1]) + f(target-nums[2]) + ... + f(target - nums[nums.length - 1]);
     这时就要判断 target - nums[i]的合法性 需要>= 0

     DP[0] : 和为0的组合的个数，只有1种情况，什么都不选。所以DP[0] = 1
     */
    public int combinationSum4(int[] nums, int target){
        if (target == 0)    return 1;
        if (nums == null || nums.length == 0)   return 0;
        // dp 存放每一个中间状态的结果
        int[] dp = new int[target + 1];
        dp[0] = 1; //和为0的组合，什么都不选，只有这一种情况
        // 从1-target构造DP数组
        for (int t = 1; t <= target; t++){
            for (int curValue: nums) {
                int remainder = t - curValue;
                if (remainder >= 0) {
                    dp[t] += dp[remainder];
                }
            }
        }
        return dp[target];
    }

    int result;
    public int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0)   return 0;
        helper(new ArrayList<>(), nums, target, 0);
        return result;
    }

    private void helper(List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0)    return;
        if (target == 0) {
            result++;
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] numbers = {1,2,3};
        System.out.println(new CombinationSum4().combinationSum4(numbers, 5));
    }
}
