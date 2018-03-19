package leetcode.stackandpq;

import java.util.Stack;

/**
 * LeetCode 232
 * Implement the following operations of a queue using stacks.

	push(x) -- Push element x to the back of queue.
	pop() -- Removes the element from in front of queue.
	peek() -- Get the front element.
	empty() -- Return whether the queue is empty.
	Notes:
	You must use only standard operations of a stack 
		-- which means only push to top, peek/pop from top, size, and is empty operations are valid.
	
	Depending on your language, stack may not be supported natively. 
		You may simulate a stack by using a list or deque (double-ended queue), 
		as long as you use only standard operations of a stack.
	
	You may assume that all operations are valid 
		(for example, no pop or peek operations will be called on an empty queue).
 *
 */
public class ImplementQueueUsingStack {

}
class MyQueue {
	private Stack<Integer> stack;
	private Stack<Integer> temp;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        temp = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while(!stack.isEmpty())	temp.push(stack.pop());
        temp.push(x);
        while(!temp.isEmpty())	stack.push(temp.pop());
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return this.stack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
