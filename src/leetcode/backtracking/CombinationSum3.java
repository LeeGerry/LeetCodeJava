package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 216
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used
   and each combination should be a unique set of numbers.

 Example 1:
 Input: k = 3, n = 7
 Output:
 [[1,2,4]]

 Example 2:
 Input: k = 3, n = 9
 Output:
 [[1,2,6], [1,3,5], [2,3,4]]

 给定K和N，从1--9中这几个9个数字组合出来K个数，其和为N。1-9不能重复使用.
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    private void helper(List<List<Integer>> res, ArrayList<Integer> list, int k, int target, int start) {
        if (target < 0) return; // 目标已经减为负数，就直接return
        if (k == 0 && target == 0){ // 目标为0 且需要的个数也为0，就说明找到了一个结果，加入结果集，返回
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9; i++) {
            // 如果要加的数字已经超过剩余，就不用往下进行了
            if (i > target) break;
            list.add(i);
            helper(res, list, k - 1, target - i, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum3().combinationSum3(3, 19));
    }
}
