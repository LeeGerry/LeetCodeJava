package other.dp;

/**
 * DP:
 *
 */
public class Rob {
    /****************************************************************
     * 递归版本：时间复杂度 2^n
     * 暴力搜索，有大量冗余
     *      不抢n-1, 则后续策略有 抢 n-2, 抢n-3, 抢n-4...
     *      抢 n-1, 则后续策略有 抢 n-3, 抢n-4, 抢n-5...
     *      ........
     *      大量策略重复计算了，例如上面两行抢n-3,n-4...
     *
     *
    ****************************************************************/
    public int rob1(int[] nums){
        return helper(nums.length - 1, nums);
    }
    private int helper(int index, int[] nums) {
        if (index < 0)  return 0;
        // 抢这家店，得到的财富是该店财富 + 跳过该店，从index - 2开始抢的策略 得到的财富
        int taken = nums[index] + helper(index - 2, nums);
        // 不抢这家，得到的财富是从下家开始抢能够的到的财富
        int notTaken = helper(index - 1, nums);
        return Math.max(taken, notTaken);
    }

    /****************************************************************
     * DP自顶向下递归版本，记忆化搜索，又叫备忘录
     *      用DP数组来保存已经计算过的策略，
     *      这样在有重复计算的时候先查备忘录，
     *      如果已经有结果直接返回
     ****************************************************************/
    private int[] dp;
    public int rob2(int[] nums) {
        dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        return helper2(nums.length - 1, nums);
    }
    private int helper2(int index, int[] nums) {
        if (index < 0)      return 0;
        if (dp[index] >= 0) return dp[index];
        int taken = nums[index] + helper2(index - 2, nums);
        int notTaken = helper2(index - 1, nums);
        int max = Math.max(taken, notTaken);
        return max;
    }

    /****************************************************************
     * DP自底向上 递推
     *      用DP数组来从头开始计算策略结果
     ****************************************************************/
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        if (nums.length == 1)                   return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; // 只有一家可以抢
        dp[1] = Math.max(nums[0], nums[1]); // 只有两家可以抢，抢其中财富较大的那个
        for (int i = 2; i < nums.length; i++) {
            int taken = nums[i] + dp[i - 2];
            int notTaken = dp[i - 1];
            dp[i] = Math.max(taken, notTaken);
        }
        return dp[dp.length - 1];
    }
    public static void main(String[] args) {
        int[] nums = {1,2,7,4,5};
        Rob rob = new Rob();
        System.out.println(rob.rob1(nums));
        System.out.println(rob.rob2(nums));
        System.out.println(rob.rob3(nums));
    }
}
