package leetcode.array;

/**
 * LeetCode 27
 Given an array and a value, remove all instances of that value in-place and return the new length.

 Do not allocate extra space for another array,
 you must do this by modifying the input array in-place with O(1) extra memory.

 The order of elements can be changed. It doesn't matter what you leave beyond the new length.

 Example:
 Given nums = [3,2,2,3], val = 3,
 Your function should return length = 2, with the first two elements of nums being 2.
 给定一个数组和一个特定的值val，删除数组中等于val的元素，返回修改后数组中的元素个数。不能使用额外的一个数组空间。

 在原数组上操作，设置一个index=0指向第一个位置，遍历数组，遇到不是val的元素，放入index的位置，index++，
 最终返回index即可
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
