package leetcode.backtracking;

/**
 * LeetCode 351.
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9,
 * count the total number of unlock patterns of the Android lock screen,
 * which consist of minimum of m keys and maximum n keys.

 Rules for a valid pattern:

 Each pattern must connect at least m keys and at most n keys.
 All the keys must be distinct.
 If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 The order of keys used matters.
 这题是给定了一个最小的k个数和最大k个数, 问有多少种合法的手势,
 可以看到9个数字其中1, 3, 7, 9处在角上, 是对称的. 而2, 4, 6, 8处在边中间, 也是对称的.
 5处在最中间, 只有这一个.
 也就是说9个数字只要求出三个位置的数量就行了.
 其中还有一个条件就是如果两个数字经过了一个数字, 那么这个数字必须之前访问过, 因此我们可以设置一个hash表来存储两个数字的中间数字.
 这样当从一个数字去访问下一个数字的时候还要看中间数字是不是被访问过了. 然后剩下的就是正常的DFS了.
 */
public class AndroidUnlockPatterns {
    int res;
    int[][] skip = new int[10][10];
    boolean[] visited = new boolean[10];
    public int numberOfPatterns(int m, int n){
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[9][7] = skip[9][7] = 8;
        skip[1][9] = skip[9][1]
                = skip[3][7] = skip[7][3]
                = skip[7][3] = skip[4][6]
                = skip[2][8] = skip[8][2] = 5;
        for (int i = m; i <= n; i++) {
            res += dfs(1, i-1) * 4; // 1,3,7,9对称， 乘4
            res += dfs(2, i-1) * 4; // 2,4,6,8对称， 乘4
            res += dfs(5, i-1); // 5只有一个
        }
        return res;
    }

    private int dfs(int curr, int remainder) {
        if (remainder < 0)  return 0;
        if (remainder == 0) return 1;
        visited[curr] = true;
        int sum = 0;
        for (int i = 1; i <= 9; i++) {
            // skip[curr][i] == 0 如果不需要经过点; visited[skip[curr][i]]为true说明访问过
            if (visited[i] && (skip[curr][i] == 0 || visited[skip[curr][i]])) {
                sum += dfs(i, remainder - 1);
            }
        }
        visited[curr] = false;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new AndroidUnlockPatterns().numberOfPatterns(2,3));
    }
}
