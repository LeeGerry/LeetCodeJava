package leetcode.DFSAndBFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 52
 * Follow up for N-Queens problem.
    Now, instead outputting board configurations, return the total number of distinct solutions.
 */
public class NQueens2 {
    int res = 0;
    public int totalNQueues(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n];
        boolean[] d2 = new boolean[2 * n];
        helper(0, cols, d1, d2, n);
        return res;
    }
    void helper(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n) {
        if (row == n) {
            res++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if (cols[col] || d1[id1] || d2[id2]) continue;
            cols[col] = true;
            d1[id1] = true;
            d2[id2] = true;
            helper(row+1, cols, d1, d2, n);
            cols[col] = false;
            d1[id1] = false;
            d2[id2] = false;
        }
    }
    int total = 0;
    public int solution2(int n) {
        if (n <= 0) return 0;
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                grid[i][j] = '.';
            }
        }
        dfs(grid, 0);
        return total;
    }

    private void dfs(char[][] grid, int col){
        if (col == grid.length) {
            total++;
            return;
        }
        for (int i = 0; i < grid.length; i++) {
            if (valid(grid, i, col)) {
                grid[i][col] = 'Q';
                dfs(grid, col + 1);
                grid[i][col] = '.';
            }
        }
    }

    private boolean valid(char[][] grid, int row,int col) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 'Q' && (row + col == i + j || row + j == col + i || i == row))
                    return false;
            }
        }
        return true;
    }
}
