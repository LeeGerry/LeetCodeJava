package leetcode.backt;

import java.util.Arrays;

/**
 * Leetcode 31
 * Implement next permutation,
  which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible,
 it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples.
 Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int index = len - 1;
        while (index > 0 && nums[index] <= nums[index - 1]) index--;
        if (index == 0) {
            Arrays.sort(nums);
            return;
        }
        int pos = -1;
        for (int i = len - 1; i >= index; i--) {
            if (nums[i] > nums[index - 1]) {
                pos = i;
                break;
            }
        }
        if (pos >= 0){
            int temp = nums[index - 1];
            nums[index - 1] = nums[pos];
            nums[pos] = temp;
            reverse(nums, index, nums.length-1);
        }
    }
    void swap(int[] a, int i, int j) {
        int t = a[i];a[i] = a[j];a[j] = t;
    }
    void reverse(int[] a, int i, int j) {
        for (; i < j; i ++, j --) swap(a, i, j);
    }

    public static void main(String[] args) {
        int[] nums = {6,5,4,8,7,5,1};
        int[] nums1 = {5,1,1};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));
    }
}