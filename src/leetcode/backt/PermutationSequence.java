package leetcode.backt;

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
            int count = fact[i - 1];
            int index = k / count;
            k = k - index * count;
            sb.append(nums.get(index));
            nums.remove(index);
        }
        return sb.toString();
    }
    /*
    因为只要求1个，所以可以按照全排列的规则，一个个数的求出每个位置的数字，而不需要将所有的全排列字符串列出来。
    对于n个字符组成的字符串{1,2,3,...,n}，取第k个数时，首先可以求出第一个数，即(k-1)/(n-1个数的排列个数)。

    比如n=3，k=4时，全排列组合为：
    1 + {2,3}的全排列
    2 + {1,3}的全排列
    3 + {1,2}的全排列
    我们可以首先求出目标排序的第一个数字，即(k-1)/(两个数的排列数) = (k-1)/2 = 3/2 = 1，下标从0开始，下标1表示的数就是2。
    接下来，就是求出{1,3}全排列中排在第 k-2=2 个位置上的数，方法同3个字母时一样，求出结果后为 231。
    所以，可以一层一层的求出第k个顺序的字符串。
    时间复杂度为O(N)。
    */
    public String getPermutation2(int n, int k) {
        // 构造 1...n 的字符串 ， 例如输入n = 4, 构造"1234"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(i+1);

        // 构造排列数组，0! = 1, 1! = 1, 2! = 2, 3! = 6; fact : [1, 1, 2, 6]
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++)
            fact[i] = i * fact[i - 1];

        StringBuilder result = new StringBuilder();
        while (k > 0 && sb.length() > 0) {
            // 计算 (n-1)的排列个数cnt
            int count = fact[sb.length() - 1];
            // 计算 要选择字符在sb中的位置
            int pos = (k - 1) / count;
            // 把字符加入结果
            result.append(sb.charAt(pos));
            // 从sb中删除已经选择过的字符
            sb.deleteCharAt(pos);
            // 更新剩余个数 k -= pos * count， pos位置说明前面有几组， 乘以每组个数
            k -= pos * count;
        }
        return result.toString();
    }
    public static void main(String[] args) {
        System.out.println(new PermutationSequence().getPermutation2(4,14));
    }
}