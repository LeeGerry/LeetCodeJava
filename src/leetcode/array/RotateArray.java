package leetcode.array;

import java.util.Arrays;

/**
 * LeetCode 189
 Rotate an array of n elements to the right by k steps.

 For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

 Note:
 Try to come up as many solutions as you can,
 there are at least 3 different ways to solve this problem.


 */
public class RotateArray {
    //
    public void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return;
        k = k % nums.length;
        reverse(0, nums.length - 1, nums);
        reverse(0, k - 1, nums);
        reverse(k, nums.length - 1, nums);
    }

    private void reverse(int start, int end, int[] nums) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length <= 1)   return;
        if (k > nums.length)  k %= nums.length;
        int[] temp = new int[nums.length];
        int index = 0;
        for (int i = nums.length - k; i < nums.length; i++) {
            temp[index++] = nums[i];
        }
        for (int i = 0; i <= nums.length - 1 - k; i++) {
            temp[index++] = nums[i];
        }
        for (int i = 0; i < temp.length; i++) {
            nums[i] = temp[i];
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        new RotateArray().rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
