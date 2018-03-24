package company.LinkedIn;

import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * LeetCode 34
 * Given an array of integers sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
    Your algorithm's runtime complexity must be in the order of O(log n).
     If the target is not found in the array, return [-1, -1].

     For example,
     Given [5, 7, 7, 8, 8, 10] and target value 8,
     return [3, 4].
 */
public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) return res;
        res[0] = searchLeftIndex(nums, target);
        res[1] = searchRightIndex(nums, target);
        return res;
    }
    public int searchRightIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target >= nums[mid])        left = mid + 1;
            else if (target < nums[mid])    right = mid - 1;
            if (nums[mid] == target)        index = mid;
        }
        return index;
    }
    public int searchLeftIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid])        right = mid - 1;
            else if (target > nums[mid])    left = mid + 1;
            if (nums[mid] == target)        index = mid;
        }
        return index;
    }
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 7, 7, 8, 8, 9};
        SearchForRange sfr = new SearchForRange();
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(sfr.searchRange(nums, 7)));
    }
}
