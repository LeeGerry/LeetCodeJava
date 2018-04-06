package company.LinkedIn.phoneinterview;

/**
 * LC 10
 * Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true
 */
public class RegularExressionMatch {
    public boolean isMatch(String s, String p) {
        // * cannot be the head of P. otherwise, index overflow
        if (s == null || p == null) return false;
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        // no chars chosen from either of the string, should match
        match[0][0] = true;
        // deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                match[0][i] = match[0][i - 2];
            }
        }

        for (int si = 1; si <= s.length(); si++) {
            for (int pi = 1; pi <= p.length(); pi++) {
                if (p.charAt(pi - 1) == '.' || p.charAt(pi - 1) == s.charAt(si - 1)) {
                    match[si][pi] = match[si - 1][pi - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    match[si][pi] = match[si][pi - 2];
                    if (p.charAt(pi - 2) == '.' || p.charAt(pi - 2) == s.charAt(si - 1)){
                        match[si][pi] = match[si][pi] | match[si - 1][pi];
                    }
                } else {
                    match[si][pi] = false;
                }
            }
        }
        return match[s.length()][p.length()];
    }

    public static void main(String[] args) {
        String s = "ably"; String p = "a*b.*y";
        RegularExressionMatch rem = new RegularExressionMatch();
        System.out.println(rem.isMatch(s, p));
    }
}
