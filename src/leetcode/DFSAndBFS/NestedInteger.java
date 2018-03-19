package leetcode.DFSAndBFS;

import java.util.ArrayList;
import java.util.List;

/**LeetCode 339中的
 *  NestedInteger辅助类
 */
public class NestedInteger {
    private boolean isInteger;
    private int i;
    private List<NestedInteger> list;
    // Constructor initializes an empty nested list.
    public NestedInteger() {
        isInteger = false;
        list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.i = value;
        this.isInteger = true;
        this.list = new ArrayList<>();
    }

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger() {
        return isInteger;
    }

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return i;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.i = value;
        this.isInteger = true;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to
    // it.
    public void add(NestedInteger ni) {
        this.list.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a
    // nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        if (isInteger)  return ""+getInteger();
        else return list.toString();
    }

}
