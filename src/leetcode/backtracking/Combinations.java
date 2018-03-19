package leetcode.backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 77
 * Given two integers n and k,
    return all possible combinations of k numbers out of 1 ... n.
 For example,
 If n = 4 and k = 2, a solution is:
 [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]

    给定 n = 4, k = 2, 从[1,2,3,4]中选出2个数字作为组合，返回所有的组合。[1,2]和[2,1]是一种。
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int n, int k, int start){
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            helper(res, list, n, k - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(4, 2));
    }
}
