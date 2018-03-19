package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 39
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
  find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [2, 3, 6, 7] and target 7,
 A solution set is:
 [
 [7],
 [2, 2, 3]
 ]
 给定一个数组（元素无重复），和一个目标值，找到所有组合，加起来等于目标值。数组中的元素可以重复使用.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0)   return res;
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0)    return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        Arrays.sort(candidates);
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= target; i++) {
            List<List<Integer>> iList = new ArrayList<>();
            for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
                if (i == candidates[j]) iList.add(Arrays.asList(candidates[j]));
                else {
                    for (List<Integer> l: dp.get(i - candidates[j] - 1)) {
                        if (candidates[j] <= l.get(0)) {
                            List c = new ArrayList();
                            c.add(candidates[j]);
                            c.addAll(l);
                            iList.add(c);
                        }
                    }
                }
            }
            dp.add(iList);
        }
        return dp.get(target - 1);
    }
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        int[] candidates2 = {1,2,3};
        int target2 = 4;

        CombinationSum cs = new CombinationSum();
        System.out.println(cs.combinationSum(candidates, target));
        System.out.println(cs.combinationSum2(candidates, target));
        System.out.println(cs.combinationSum(candidates2, target2));

    }
}
