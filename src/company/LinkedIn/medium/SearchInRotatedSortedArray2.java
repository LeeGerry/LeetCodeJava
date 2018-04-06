package company.LinkedIn.medium;

import java.util.Map;
import java.util.Stack;

/**
 * LC 81
 Follow up for "Search in Rotated Sorted Array":
 What if duplicates are allowed?

 Would this affect the run-time complexity? How and why?
 考点是能否想到最坏情况比如序列 {1，1，...1，1}中有1个0寻找target=0，
 此时复杂度退化到O(n)

 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Write a function to determine if a given target is in the array.

 The array may contain duplicates.
 */
public class SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)   return false;

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)    return true;
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target <= nums[mid])  right = mid;
                else    left = mid;
            } else if (nums[left] > nums[mid]) {
                if (nums[mid] <= target && target <= nums[right])   left = mid;
                else    right = mid;
            } else {
                left++;
            }
        }

        if (nums[left] == target)   return true;
        if (nums[right] == target)  return true;
        return false;
    }
}
