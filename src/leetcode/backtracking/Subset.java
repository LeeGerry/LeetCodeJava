package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 78
 * Given a set of distinct integers, nums,
 * return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
        help(res, 0, nums, new LinkedList<Integer>());
        return res;

    }

    private void help(List<List<Integer>> res, int index, int[] nums, LinkedList<Integer> item) {
        if (index == nums.length) {
            res.add(new LinkedList<>(item));
        } else {
            help(res, index + 1, nums, item);// 不加当前值，递归一下
            item.add(nums[index]); // 加上
            help(res, index + 1, nums, item); // 加上当前值，递归一下
            item.remove(item.size() - 1); // 出来后remove掉，保持递归干净
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, ArrayList<Integer> list, int[] nums, int index) {
        System.out.println(index);
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        Subset ss = new Subset();
        List<List<Integer>> subsets = ss.subsets(nums);
        System.out.println(subsets);
        System.out.println(ss.subsets1(nums));
    }
}
