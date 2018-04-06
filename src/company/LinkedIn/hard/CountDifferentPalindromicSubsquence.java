package company.LinkedIn.hard;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * LC 730
 Given a string S, find the number of different non-empty palindromic sub sequences in S,
 and return that number modulo 10^9 + 7.

 A subsequence of a string S is obtained by deleting 0 or more characters from S.

 A sequence is palindromic if it is equal to the sequence reversed.

 Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

 Example 1:
 Input:
 S = 'bccb'
 Output: 6
 Explanation:
 The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 Note that 'bcb' is counted only once, even though it occurs twice.
 Example 2:
 Input:
 S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 Output: 104860361
 Explanation:
 There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 Note:

 The length of S will be in the range [1, 1000].
 Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 */
public class CountDifferentPalindromicSubsquence {
    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        char[] chs = s.toCharArray();
        // 先构建长度为1的子问题的解，dp[i][i]表示一个字符
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        // 再构建长度为2 到 n-1 的子问题的解。用短子问题的解构建长子问题的解
        for (int distance = 1; distance < len; distance++) {
            for (int i = 0; i < len - distance; i++) {
                int j = i + distance;
                if (chs[i] == chs[j]) {
                    int low = i + 1;
                    int high = j - 1;
                    while (low <= high && chs[low] != chs[j])   low++;
                    while (low <= high && chs[high] != chs[j])  high--;
                    if (low > high) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    }else if (low == high) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1];
                    }
                }else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        String s = "bccb";
        CountDifferentPalindromicSubsquence cdps = new CountDifferentPalindromicSubsquence();
        System.out.println(cdps.countPalindromicSubsequences(s));
    }
}
