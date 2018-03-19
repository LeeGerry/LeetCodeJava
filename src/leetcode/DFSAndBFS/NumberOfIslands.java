package leetcode.DFSAndBFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 200
 * https://leetcode.com/problems/number-of-islands/description/
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * 找小岛的个数，上下左右的1连成一片属于一个岛
 */
public class NumberOfIslands {

    /**
     * 方法1：深搜，用一个boolean二维数组来记录是否访问过
     * @param grid
     * @return
     */
    public int numberIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int result = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs1(grid, visited, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs1(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
                || visited[i][j]
                || grid[i][j] == '0')
            return;
        visited[i][j] = true;
        dfs1(grid, visited, i - 1, j);
        dfs1(grid, visited, i + 1, j);
        dfs1(grid, visited, i, j - 1);
        dfs1(grid, visited, i, j + 1);
    }

    //方法2: 深搜，直接修改数组中的1位0来代替方法1中的boolean数组
    public int numberIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs2(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    public void dfs2(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs2(grid, i, j + 1);
        dfs2(grid, i, j - 1);
        dfs2(grid, i + 1, j);
        dfs2(grid, i - 1, j);
    }

    //广搜
    public int numberIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void bfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int index = i * n + j;//转换为一维数组坐标
        queue.offer(index);
        while (!queue.isEmpty()) {
            index = queue.poll();
            // 计算一维坐标对应的二维坐标
            int rowIndex = index / n;
            int colIndex = index % n;
            //上
            if (rowIndex > 0 && rowIndex <= m - 1 && grid[rowIndex - 1][colIndex] == '1') {
                queue.offer((rowIndex - 1) * n + colIndex);
                grid[rowIndex - 1][colIndex] = '0';
            }
            //下
            if (rowIndex >= 0 && rowIndex < m - 1 && grid[rowIndex + 1][colIndex] == '1') {
                queue.offer((rowIndex + 1) * n + colIndex);
                grid[rowIndex + 1][colIndex] = '0';
            }
            //左
            if (colIndex > 0 && colIndex <= n - 1 && grid[rowIndex][colIndex - 1] == '1') {
                queue.offer((rowIndex * n) + colIndex - 1);
                grid[rowIndex][colIndex - 1] = '0';
            }
            //右
            if (colIndex >= 0 && colIndex < n - 1 && grid[rowIndex][colIndex + 1] == '1') {
                queue.offer((rowIndex * n) + colIndex + 1);
                grid[rowIndex][colIndex + 1] = '0';
            }
        }
    }


    public static void printArray(char[][] arr){
        for (char[] chs: arr){
            for(char c: chs){
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }




    public static char[][] init(){
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        return grid;
    }
    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println(numberOfIslands.numberIslands1(init()));
        System.out.println(numberOfIslands.numberIslands2(init()));
        System.out.println(numberOfIslands.numberIslands3(init()));
    }
}
