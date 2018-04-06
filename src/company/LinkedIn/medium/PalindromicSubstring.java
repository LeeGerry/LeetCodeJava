package company.LinkedIn.medium;

/**
 * LC 647
 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings
 even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".

 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 Note:
 The input string length won't exceed 1000.

 找出一个字符串中所有可能出现的回文子串的个数
 */
public class PalindromicSubstring {
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);        // odd length
            helper(s, i, i + 1);    // even length
        }
        return count;
    }
    /*
      按位置的统计，使用中心向外拓展的方法：
        1、每个字符自己构成回文，+1
        2、中心拓展，假设当前位置i为回文的中心，那么设置left=i-1 right=i+1，比较left与right位置是否相同，相同就+1，然后各自移动一步，重复直到退出
        3、中心拓展当前的回文长度是偶数的，那么就设置left=i，right=i+1，其他同2一样
     */
    private void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(new PalindromicSubstring().countSubstrings(s));
    }
}
