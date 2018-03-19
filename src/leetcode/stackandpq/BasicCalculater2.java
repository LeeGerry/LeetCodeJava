package leetcode.stackandpq;

import java.util.Stack;

/**
 * LeetCode 227
 * Implement a basic calculator to evaluate a simple expression string.

	The expression string contains only non-negative integers, +, -, *, / operators and empty spaces. 
	The integer division should truncate toward zero.
	
	You may assume that the given expression is always valid.
	
	Some examples:
	"3+2*2" = 7
	" 3/2 " = 1
	" 3+5 / 2 " = 5
	Note: Do not use the eval built-in library function.
 */
public class BasicCalculater2 {
	public static int calculate(String s) {
		if(s == null || s.length() == 0)	return 0;
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		char sign = '+';
		int num = 0;
		for(int i=0; i<s.length(); i++){
			if(Character.isDigit(s.charAt(i))){
				num = s.charAt(i) - '0';
				while(i+1 <s.length() && Character.isDigit(s.charAt(i+1))){
					num = num * 10 + s.charAt(i+1) - '0';
					i++;
				}
			}
			if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i==s.length()-1){
				if(sign == '+')	stack.push(num);
				if(sign == '-')	stack.push(-num);
				if(sign == '*') stack.push(stack.pop() * num);
				if(sign == '/') stack.push(stack.pop() / num);
				sign = s.charAt(i);
				num = 0;
			}
		}
		for(int i: stack){
			res += i;
		}
		return res;
	}
	public static void main(String[] args) {
		String s = " 3+5 / 2 ";
		System.out.println(calculate(s));
	}
}
