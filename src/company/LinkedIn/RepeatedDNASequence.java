package company.LinkedIn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LC 187
 All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 for example: "ACGAATTCCG".
 When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings)
 that occur more than once in a DNA molecule.

 For example,
 Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 Return:
 ["AAAAACCCCC", "CCCCCAAAAA"].
 题目中的例子都是不重叠的重复字串，实际上相互重叠的字串也是要统计进去，
 例如11位的 "AAAAAAAAAA" 就包含两个长度为 10 的"AAAAAAAAAA" 的重复子序列。这一点是题目没有说清楚的。

 明确题目后，实现思路也比较简单：
 将 s 从第一个字符位置开始往后到len-9 截取所有长度为 10 的连续子字符串，放入Set中,
 如果add失败，说明已经出现过该子字符串，则加入结果集。
 */
public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet();
        Set<String> repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        String s2 = "AAAAAAAAAAA";
        System.out.println(new RepeatedDNASequence().findRepeatedDnaSequences(s));
        System.out.println(new RepeatedDNASequence().findRepeatedDnaSequences(s2));
    }
}
