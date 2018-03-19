package leetcode.DFSAndBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 51
 * https://leetcode.com/problems/n-queens/description/
 * Given an integer n, return all distinct solutions to the n-queens puzzle.

    Each solution contains a distinct board configuration of the n-queens' placement,
    where 'Q' and '.' both indicate a queen and an empty space respectively.

     For example,
     There exist two distinct solutions to the 4-queens puzzle:

     [
     [". Q . .",  // Solution 1
      ". . . Q",
      "Q . . .",
      ". . Q ."],

    [". . Q .",  // Solution 2
     "Q . . .",
     ". . . Q",
     ". Q . ."]
     ]
    time: O(n*n) space O(n)
    例如，4皇后，每行每列，每条对角线都只能有一个皇后
    queues[1,0,2,3]数组代表 当前4个皇后所在坐标点为：[[0,1],[1,0],[2,2],[3,3]]
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        helper(res, new int[n], 0);
        return res;
    }
    private void helper(List<List<String>> res, int[] queues, int pos){
        if (pos == queues.length) {
            addSolution(res, queues);
            return;
        }
        for (int i= 0; i<queues.length; i++){
            queues[pos] = i;
            if (isValid(queues, pos))   helper(res, queues, pos + 1);
        }
    }
    private boolean isValid(int[] queues, int pos){
        for (int i=0; i<pos; i++){
            if (queues[i] == queues[pos])   // 在同一列
                return false;
            else if (Math.abs(queues[i] - queues[pos]) == Math.abs(i - pos))//在对角线
                return false;
        }
        return true;
    }
    private void addSolution(List<List<String>> res, int[] queues){
        List<String> list = new ArrayList<>();
        for (int i = 0; i<queues.length; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j<queues.length; j++){
                if (queues[i] == j) sb.append('Q');
                else    sb.append('.');
            }
            list.add(sb.toString());
        }
        res.add(list);
    }

    public List<List<String>> solution(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                grid[i][j] = '.';
            }
        }
        dfs(grid, 0, res);
        return res;
    }

    private void dfs(char[][] grid, int col, List<List<String>> res){
        if (col == grid.length) {
            res.add(create(grid));
            return;
        }
        for (int i = 0; i < grid.length; i++) {
            if (valid(grid, i, col)) {
                grid[i][col] = 'Q';
                dfs(grid, col + 1, res);
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

    private List<String> create(char[][] grid) {
        List<String> list = new LinkedList<>();
        for (char[] chars: grid){
            String s = new String(chars);
            list.add(s);
        }
        return list;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        System.out.println(nq.solveNQueens(4));
        System.out.println(nq.solution(4));
    }

}
