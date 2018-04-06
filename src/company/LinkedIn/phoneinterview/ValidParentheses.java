package company.LinkedIn.phoneinterview;

import java.util.Stack;

/**
 * LC 20
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0)    return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == ')' || current == ']' || current == '}') {
                if (stack.isEmpty())    return false;
                char top = stack.pop();
                if (current == ')' && top != '(')   return false;
                if (current == ']' && top != '[')   return false;
                if (current == '}' && top != '{')   return false;
            } else {
                stack.push(current);
            }
        }
        if (!stack.isEmpty())   return false;
        return true;
    }
}
