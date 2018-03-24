package leetcode.backt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 46
 * Given a collection of distinct numbers, return all possible permutations.

 For example,
 [1,2,3] have the following permutations:
 [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
 ]

 */
public class Permutations {
    // time: O(n!) space: O(n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)   return res;
        dfs(nums, res, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, ArrayList<Integer> list) {
        if (list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i]))  continue;
            list.add(nums[i]);
            dfs(nums, res, list);
            list.remove(list.size() - 1);
        }
    }

    // time: O(n!) space: O(n)
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0)   return res;
        helper(res, nums, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> list = new LinkedList<>();
            for (int n: nums)   list.add(n);
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            helper(res, nums, start + 1);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int start, int i) {
        int temp = nums[start];
        nums[start] = nums[i];
        nums[i] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Permutations p = new Permutations();
        System.out.println(p.permute(nums));
        System.out.println(p.permute2(nums));
    }
}
