package leetcode.stackandpq;

import java.util.Stack;

/**
 * LeetCode 150
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

	Valid operators are +, -, *, /. Each operand may be an integer or another expression.
	
	Some examples:
	  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateReversePolishNotation {
	public static int evalRPN(String[] tokens){
		Stack<String> stack = new Stack<>();
		for(String s: tokens){
			if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")){
				stack.push(s);
			}else{
				int first = Integer.parseInt(stack.pop());
				int second = Integer.parseInt(stack.pop());
				if(s.equals("+"))	stack.push(second+first+"");
				if(s.equals("-"))	stack.push(second-first+"");
				if(s.equals("*"))	stack.push(second*first+"");
				if(s.equals("/"))	stack.push(second/first+"");
			}
		}
		return Integer.parseInt(stack.pop());
	}
	public static void main(String[] args) {
		String[] strs = {"2", "1", "+", "3", "*"};
		System.out.println(evalRPN(strs));
		
	}
}
