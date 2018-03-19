package leetcode.stackandpq;

import java.util.Stack;

/**
 * LeetCode 394
 * Given an encoded string, return it's decoded string.

	The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
	
	You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
	
	Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
	
	Examples:
	
	s = "3[a]2[bc]", return "aaabcbc".
	s = "3[a2[c]]", return "accaccacc".
	s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 */
public class DecodeString {
	public static String decodeString(String s) {
		if(s == null || s.length() == 0)	return s;
		String result = "";
		Stack<Integer> countStack = new Stack<>();
		Stack<String> resStack = new Stack<>();
		int index = 0;
		while(index < s.length()){
			char current = s.charAt(index);
			if(Character.isDigit(current)){
				int count = 0;
				while(Character.isDigit(s.charAt(index))){
					count = count * 10 + (s.charAt(index) - '0');
					index++;
				}
				countStack.push(count);
			}else if(current == '['){
				resStack.push(result);
				result = "";
				index++;
			}else if(current == ']'){
				StringBuilder temp = new StringBuilder(resStack.pop());
				int repeat = countStack.pop();
				for(int i=0; i<repeat; i++){
					temp.append(result);
				}
				result = temp.toString();
				index++;
			}else{
				result += current;
				index++;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String s = "10[a2[cd]]";
		System.out.println(decodeString(s));
	}
}
