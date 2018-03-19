package leetcode.stackandpq;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * LeetCode 341
 * https://leetcode.com/problems/flatten-nested-list-iterator/description/
 * Given a nested list of integers, implement an iterator to flatten it.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Example 1:
     Given the list [[1,1],2,[1,1]],

     By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

     Example 2:
     Given the list [1,[4,[6]]]
    By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


 */

public class FlattenNestedListIterator {
    class NestedInteger{
        private int value;
        private List<NestedInteger> list;
        private boolean isInteger;
        public NestedInteger() {

        }

        public NestedInteger(int value, List<NestedInteger> list) {
            this.value = value;
            this.list = list;
        }

        public boolean isInteger(){
            return isInteger;
        }
        public void setInteger(int v){
            this.value = v;
            this.isInteger = true;
        }
        public Integer getInteger(){
            if (isInteger)
                return this.value;
            else return null;
        }
        public List<NestedInteger> getList(){
            return this.list;
        }
        public void setList(List<NestedInteger> l){
            this.list = l;
        }
        public void add(NestedInteger ni){
            if (this.list == null)  this.list = new ArrayList<>();
            this.list.add(ni);
        }
    }
    class NestedIterator{
        Stack<NestedInteger> stack;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            for (int i = nestedList.size() - 1; i>=0; i--){
                stack.push(nestedList.get(i));
            }

        }


        public Integer next() {
            return stack.pop().getInteger();
        }


        public boolean hasNext() {
            while (!stack.isEmpty()){
                NestedInteger current = stack.peek();
                if (current.isInteger())    return true;
                stack.pop();
                for (int i= current.getList().size() - 1; i>=0; i--){
                    stack.push(current.getList().get(i));
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
