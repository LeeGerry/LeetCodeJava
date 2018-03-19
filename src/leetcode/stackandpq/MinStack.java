package leetcode.stackandpq;

import java.util.Stack;

/**
 * LeetCode 155
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
	Example:
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> Returns -3.
	minStack.pop();
	minStack.top();      --> Returns 0.
	minStack.getMin();   --> Returns -2.
 *  用双栈来实现，stack保存正常的数据输入，minStack保存递减序列。
 */
public class MinStack {
	Stack<Integer> stack;
	Stack<Integer> minStack;
	public MinStack(){
		stack = new Stack<>();
		minStack = new Stack<>();
	}
	public void push(int x){
		stack.push(x); 
		// min栈为空或者要放入的元素不大于栈顶，则放入min栈
		if(minStack.isEmpty() || x<=minStack.peek())
			minStack.push(x);
	}
	public void pop(){
		//若正常栈为空，则直接返回
		if(stack.isEmpty())	return;
		// 若正常站和min栈的栈顶一样，则要把min栈pop一下
		int n1 = stack.peek();
		int n2 = minStack.peek();
		// 这里要把Integer中的值取出来搞成int
		if(n1 == n2)	minStack.pop();
		// 在把正常栈pop一下
		stack.pop();
	}
	
	public int top(){
		return stack.peek();
	}
	
	public int getMin(){
		return minStack.peek();
	}
}
