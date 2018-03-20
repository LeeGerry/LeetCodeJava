package leetcode.array;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Leetcode 220
 * Given an array of integers,
 * find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 */
public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length < 2|| k<0||t<0){
            return false;
        }
        TreeSet<Long> set = new TreeSet<>();
        for(int i=0;i<nums.length ;i++){
            long cur = (long)nums[i];
            long left = cur - t;
            long right = cur + t + 1;
            SortedSet<Long> sub = set.subSet(left, right);
            if(sub.size() > 0)
                return true;
            set.add(cur);
            if(i>=k)
                set.remove((long)(nums[i-k]));
        }
        return false;
    }
}

