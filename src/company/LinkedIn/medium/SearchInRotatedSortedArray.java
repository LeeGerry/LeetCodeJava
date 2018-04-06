package company.LinkedIn.medium;

/**
 * LC 33
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search.
 If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        // 边界，数组为空或者长度为0，肯定找不到target，返回-1
        if (nums == null || nums.length == 0)   return -1;

        int left = 0, right = nums.length - 1;
        // 二分法标准写法
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid; // 找到
            // 如果前半段数组是递增的
            if (nums[mid] >= nums[left])    {
                // 如果target落在前半段区间，右指针左移
                if (nums[left] <= target && target <= nums[mid]) right = mid;
                // 否则，左指针右移
                else left = mid;
            } else { // 如果前半段数组不是递增的，也就是前半段数组出现的有极大值
                // 此时，若target落在右半段，则左指针右移
                if (nums[mid] <= target && target <= nums[right]) left = mid;
                // 否则，右指针左移
                else right = mid;
            }
        }
        // 退出while循环，此时左右指针相邻
        if (target == nums[left])    return left; //判断左指针位置
        if (target == nums[right])   return right;// 判断右指针位置
        return -1; // 左右指针处都不是目标，说明没找到
    }
}
