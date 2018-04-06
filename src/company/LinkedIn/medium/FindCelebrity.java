package company.LinkedIn.medium;

/**
 * LeetCode 277
 *
 * Find the Celebrity
 Suppose you are at a party with n people (labeled from 0 to n - 1) and among them,
 there may exist one celebrity.
 The definition of a celebrity is that all the other n - 1 people know him/her
 but he/she does not know any of them.

 Now you want to find out who the celebrity is or verify that there is not one.
 The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?"
 to get information of whether A knows B.
 You need to find out the celebrity (or verify there is not one)
 by asking as few questions as possible (in the asymptotic sense).

 You are given a helper function bool knows(a, b)
 which tells you whether A knows B.
 Implement a function int findCelebrity(n),
 your function should minimize the number of calls to knows.

 Note: There will be exactly one celebrity if he/she is in the party.
 Return the celebrity's label if there is a celebrity in the party.
 If there is no celebrity, return -1.
 在数组中找Celebrity。
 Celebrity的条件很特殊，首先所有人都认识Celebrity，Celebrity不认识任何人。
 Brute force，需要O(n2)遍历。要好好利用第一个条件.
 假定candidate = 0，遍历数组，当knows(candidate, i)时， 假定i为candidate。
 这里因为所有人都认识Celebrity，所以遍历完数组以后candidate就是我们要验证的Celebrity。
 还需要第二遍遍历来验证这个candidate是否满足我们的条件。
 */
public class FindCelebrity {
    public boolean knows(int i,int j){return false;}

    public int findCelebrity(int n) {
        int candidate = 0; // 候选人初始为0

        for(int i = 1; i < n; i++) {
            if(knows(candidate, i)) { // 遍历1到n-1所有人，如果候选人知道i，则候选人置为i
                candidate = i;
            }
        }
        // 再验证一遍
        for(int i = 0; i < n; i++) {//对所有人
            // 如果当前i不是候选人，并且候选人认识i，返回-1
            // 或者如果当前i不认识候选人，也返回-1
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }

        return candidate;
    }
}
