package company.LinkedIn.medium;

import java.util.Arrays;

/**
 * LC 238
 * Given an array of n integers where n > 1, nums, return an array output
   such that output[i] is equal to the product of all the elements of nums except nums[i].

 Solve it without division and in O(n).

 For example, given [1,2,3,4], return [24,12,8,6].

 Follow up:
 Could you solve it with constant space complexity?
 (Note: The output array does not count as extra space
 for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        Arrays.fill(result, 1);
        int left = 1, right = 1;
        for(int i = 0; i < len; i++){
            result[i] *= left;
            result[len - i - 1] *= right;
            left *= nums[i];
            right *= nums[len-i-1];
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 0)   return nums;
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++)
            res[i] = res[i - 1] * nums[i - 1];
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
     }
    public static void main(String[] args) {
        int[] nums = {2,3,4,5};
        ProductOfArrayExceptSelf pa = new ProductOfArrayExceptSelf();
        int[] result = pa.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));

        int[] res2 = pa.productExceptSelf2(nums);
        System.out.println(Arrays.toString(res2));

    }
}
