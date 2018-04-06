package company.LinkedIn.medium;

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
        res[0] = searchLeft(nums, target);
        res[1] = searchRight(nums, target);
        return res;
    }

    // 搜索右边界
    public int searchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 因为要找最右边的目标，目标大于等于mid都要移动左指针
            if (target >= nums[mid])    left = mid;
            else                        right = mid;
        }
        // 循环结束后先判断右边是否等于目标
        if (nums[right] == target)      return right;
        // 再判断左边是否等于目标
        if (nums[left] == target)       return left;
        // 都不等，没找到
        return -1;
    }
    // 搜索左边界
    public int searchLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 因为要找最左边的目标，目标小于等于mid都要移动右指针
            if (target <= nums[mid])    right = mid;
            else                        left = mid;
        }
        // 循环结束后先判断左边是否等于目标
        if (nums[left] == target)      return left;
        // 再判断右边是否等于目标
        if (nums[right] == target)       return right;
        // 都不等，没找到
        return -1;
    }
    // 搜索右边界
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
    // 搜索左边界
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
