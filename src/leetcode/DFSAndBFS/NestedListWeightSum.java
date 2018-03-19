package leetcode.DFSAndBFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 339
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Example 1:
     Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

     Example 2:
     Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)


 */
public class NestedListWeightSum {
    // 方法1:深搜
    public int depthSum(List<NestedInteger> nestedList){
        if (nestedList == null)     return 0;
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth){
        int result = 0;
        for (NestedInteger ni: nestedList){
            if (ni.isInteger()) result += ni.getInteger() * depth;
            else    result += dfs(ni.getList(), depth + 1);
        }
        return result;
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
        NestedListWeightSum sum = new NestedListWeightSum();

        System.out.println(sum.depthSum(list));
    }
}
