package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 40
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 给定一个数组（元素可以有重复），和一个目标值，找到所有组合，加起来等于目标值。数组中的元素不能重复使用。
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0)   return res;
        // 排序是为了在递归中排除重复元素
        Arrays.sort(candidates);
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, ArrayList<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            /* 过滤重复元素的方法1
            if (i != start && candidates[i] == candidates[i - 1])   continue;
            */
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
            // 过滤重复元素的方法2
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) i++;
        }
    }

    public static void main(String[] args) {
        int[] candidtes = {1,1,2,5,6,7,10};
        int[] candidtes2 = {1,1,3};
        System.out.println(new CombinationSum2().combinationSum2(candidtes2, 4));
    }
}
