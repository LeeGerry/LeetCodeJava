package company.LinkedIn;

/**
 * LeetCode 698
 * Given an array of integers nums and a positive integer k,
 find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 Note:
 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n: nums) sum += n;
        if (k <= 0 || sum % k != 0) return false;

        boolean[] visited = new boolean[nums.length];
        return helper(nums, visited, 0, k, 0, 0, sum / k);
    }

    private boolean helper(int[] nums, boolean[] visited,
                           int start, int k, int curSum, int curNum,int target) {
        if (k == 1) return true;
        if (curSum == target && curNum > 0)
            return helper(nums, visited, 0, k - 1, 0, 0, target);
        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (helper(nums, visited, i + 1, k, curSum + nums[i], curNum++, target))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int target = 4;
        System.out.println(new PartitionToKEqualSumSubsets().canPartitionKSubsets(nums, target));
    }
}
