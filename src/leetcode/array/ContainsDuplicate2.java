package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 219
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 给定数组nums[] 和 k，能否找到 nums[i] = nums[j]，而且i跟j的距离不能比k大。
 例如:
 nums = [1,2,3,4,1] k=3; nums[0] = nums[4] = 1 , j=4, i=0, 能找到两个相等的值，但是距离为4 > k，返回false
 nums = [1,2,3,4,1] k=4; nums[0] = nums[4] = 1 , j=4, i=0, 距离为4 == k，返回true
 */
public class ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if (map.containsKey(nums[i])) {
                int index1 = map.get(nums[i]);
                if((i - index1) <= k)
                    return true;
                else
                    map.put(nums[i], i);
            }else{
                map.put(nums[i], i);
            }
        }

        return false;
    }
}
