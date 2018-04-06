package company.LinkedIn.easy;

import leetcode.DFSAndBFS.NestedInteger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 364
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

 Example 2:
 Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
public class NestedListWeightSum2 {
    public int depthSumInverse(List<NestedInteger> nestedList){
        if (nestedList == null)     return 0;
        return helper(nestedList, 0);
    }

    private int helper(List<NestedInteger> list, int res){
        List<NestedInteger> nextList = new LinkedList<>();
        for (NestedInteger ni: list){
            if (ni.isInteger())     res += ni.getInteger();
            else                    nextList.addAll(ni.getList());
        }
        res += nextList.isEmpty() ? 0 : helper(nextList, res);
        return res;
    }

    public int depthSumInverse2(List<NestedInteger> nestedList) {
        int weighted = 0, unweighted = 0;
        while (!nestedList.isEmpty()) {
            ArrayList<NestedInteger> next = new ArrayList<>();
            for (NestedInteger ni: nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    next.addAll(ni.getList());
            }
            weighted += unweighted;
            nestedList = next;
        }
        return weighted;
    }

    public static void main(String[] args) {
        //[1,[4,[6]]]
        NestedInteger t1 = new NestedInteger();
        t1.setInteger(1);

        NestedInteger t2 = new NestedInteger();
        t2.setInteger(4);

        NestedInteger t3 = new NestedInteger();
        t3.setInteger(6);

        NestedInteger ni = new NestedInteger();
        ni.add(t3);
        NestedInteger ni2 = new NestedInteger();
        ni2.add(t2); ni2.add(ni);

        NestedInteger ni3 = new NestedInteger();
        ni3.add(t1); ni3.add(ni2);
        List<NestedInteger> list = new ArrayList<>();
        list.add(t1); list.add(ni2);
        System.out.println(list.toString());
        NestedListWeightSum2 sum = new NestedListWeightSum2();

        System.out.println(sum.depthSumInverse(list));
        System.out.println(sum.depthSumInverse2(list));
    }
}
