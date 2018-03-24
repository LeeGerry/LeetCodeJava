package leetcode.backt;

import java.util.HashMap;

/**
 * LeetCode 290
 * Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match,
 such that there is a bijection between a letter in pattern and a non-empty word in str.

 Examples:
 pattern = "abba", str = "dog cat cat dog" should return true.
 pattern = "abba", str = "dog cat cat fish" should return false.
 pattern = "aaaa", str = "dog cat cat dog" should return false.
 pattern = "abba", str = "dog dog dog dog" should return false.
 Notes:
 You may assume pattern contains only lowercase letters,
 and str contains lowercase letters separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> c_string = new HashMap<>();
        HashMap<String, Character> string_c = new HashMap<>();
        String[] strings = str.split(" ");
        if (pattern.length() != strings.length)     return false;
        for (int i = 0; i < strings.length; i++) {
            char ch = pattern.charAt(i);
            String currentString = strings[i];
            if (c_string.containsKey(ch) && !c_string.get(ch).equals(currentString))
                return false;
            else if (string_c.containsKey(currentString) && string_c.get(currentString) != ch)
                return false;
            else if (!c_string.containsKey(ch) && !string_c.containsKey(currentString)) {
                c_string.put(ch, currentString);
                string_c.put(currentString, ch);
            }else{}
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat fish";
        System.out.println(new WordPattern().wordPattern(pattern, str));
    }
}
