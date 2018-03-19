package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 90
 * Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,2], a solution is:

 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]
 */
public class Subset2 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)   return res;
        Arrays.sort(nums); // 排序是为了去重复
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private static void helper(List<List<Integer>> res, ArrayList<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            helper(res, list, nums, i+1);
            list.remove(list.size() - 1);
        }
    }

    public static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)   return res;
        Arrays.sort(nums); // 排序是为了去重复
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private static void dfs(List<List<Integer>> res, ArrayList<Integer> list, int[] nums, int pos) {
        res.add(new ArrayList<>(list));
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        System.out.println(Subset2.subsets(nums));
        System.out.println(Subset2.solution(nums));
    }
}
