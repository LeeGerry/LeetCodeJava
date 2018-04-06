package company.LinkedIn.medium;

/**
 * LC 152
  Find the contiguous sub-array within an array (containing at least one number)
    which has the largest product.

  For example, given the array [2,3,-2,4],
  the contiguous subarray [2,3] has the largest product = 6.

 从数组（至少包含一个数字）中找出一个连续的子数组，该子数组的乘积最大。

 解题思路:DP
 　　特殊情况，负数和负数相乘：
 　　如果前面得到一个较小的负数，和后面一个较大的负数相乘，得到的反而是一个较大的数，如{2，-3，-7}，
 　　所以，我们在处理乘法的时候，除了需要维护一个局部最大值，同时还要维护一个局部最小值
 　　len大于0时
 　　max(0)=num[0]
 　　min(0)=num[0]
 　　max(n+1) = MAX(MAX(num[n+1]*max(n), num[n+1]), num[n+1]*min(n)) // 最大值
 　　min(n+1) = MIN(MIN(num[n+1]*max(n), num[n+1]), num[n+1]*min(n)) // 最小值，为下一个新计算做准备


 */
public class MaximumProductSubArray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)   return -1;
        if (nums.length == 1)                   return nums[0];
        int result = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = nums[i] * max;
            int b = nums[i] * min;
            max = Math.max(Math.max(a, nums[i]), b);
            min = Math.min(Math.min(a, nums[i]), b);
            result = Math.max(result, max);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,0,-2, -8};
        System.out.println(new MaximumProductSubArray().maxProduct(nums));
    }
}
