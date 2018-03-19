package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 60
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

     By listing and labeling all of the permutations in order,
     We get the following sequence (ie, for n = 3):

     "123"
     "132"
     "213"
     "231"
     "312"
     "321"

     Given n and k, return the kth permutation sequence.
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++)    nums.add(i);

        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }

        k = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int f = fact[i - 1];
            int index = k / f;
            k = k % f;
            sb.append(nums.get(index));
            nums.remove(index);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new PermutationSequence().getPermutation(4,17));
    }
}