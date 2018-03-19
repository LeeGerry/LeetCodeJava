package leetcode.array;

import java.util.Arrays;

/**
 * Leetcode 80
 * Follow up for "Remove Duplicates":
 What if duplicates are allowed at most twice?
 For example,
 Given sorted array nums = [1,1,1,2,2,3],

 Your function should return length = 5,
 with the first five elements of nums being 1, 1, 2, 2 and 3.
 It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        if (nums.length < 3)    return nums.length;
        int i = 1, j = 2;
        while (j < nums.length) {
            if (nums[j] == nums[i] && nums[j] == nums[i - 1]){
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,3,3,4,4,4,4,5,6};
        System.out.println(new RemoveDuplicatesFromSortedArray2().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
