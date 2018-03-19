package leetcode.DFSAndBFS;

import javax.sound.midi.Soundbank;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 286
     You are given a m x n 2D grid initialized with these three possible values.

     -1 - A wall or an obstacle.
     0 - A gate.
     INF - Infinity means an empty room.

     We use the value 231 - 1 = 2147483647 to represent INF
        as you may assume that the distance to a gate is less than 2147483647.
     Fill each empty room with the distance to its nearest gate.
     If it is impossible to reach a gate, it should be filled with INF.

     For example, given the 2D grid:

     INF  -1   0  INF
     INF INF INF   -1
     INF  -1 INF   -1
       0  -1 INF  INF
    INF是空房间，-1是障碍物，0是门

    [0,0]处的空房间到[3,0]处的门需要走3步，并且是最近的
    [0,3]处的空房间到[0,2]处的门需要走1步，并且是最近的
    。。。

     After running your function, the 2D grid should be:
    执行完以后，空房间处的数字更改为到最近的门的距离
     3  -1   0   1
     2   2   1  -1
     1  -1   2  -1
     0  -1   3   4

    方法：遍历到门进行深搜或广搜，去填充门到房间的距离。

 */
public class WallsAndGates {
    //方法1：深搜
    public void wallsAndGates(int[][] rooms){
        printArr(rooms);
        for (int i = 0; i<rooms.length;i++){
            for (int j= 0; j<rooms[0].length; j++){
                if (rooms[i][j] == 0){
                    dfs(rooms, i, j, 0);
                }
            }
        }
        printArr(rooms);
    }
    private void dfs(int[][] rooms, int i, int j, int distance){
        if (i<0 || i>=rooms.length || j<0 || j>=rooms[0].length || rooms[i][j] < distance)  return;
        rooms[i][j] = distance;
        dfs(rooms, i-1, j, distance+1);
        dfs(rooms, i+1, j, distance+1);
        dfs(rooms, i, j-1, distance+1);
        dfs(rooms, i, j+1, distance+1);
    }



    // 广搜 time: O(m*n) space: O(m*n)
    public void wallsAndGates2(int[][] rooms){
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0; i<rooms.length; i++){
            for (int j=0; j<rooms[0].length; j++){
                if (rooms[i][j] == 0) queue.offer(new int[]{i,j});
            }
        }
        while (!queue.isEmpty()){
            int[] head = queue.poll();
            int row = head[0], col = head[1];
            //上
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE){
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.offer(new int[]{row - 1, col});
            }
            //下
            if (row < rooms.length-1 && rooms[row + 1][col] == Integer.MAX_VALUE){
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.offer(new int[]{row+1, col});
            }
            //左
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE){
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.offer(new int[]{row, col - 1});
            }
            //右
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE){
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.offer(new int[]{row, col + 1});
            }
        }
        printArr(rooms);
    }



    public void solution(int[][] rooms){
        if (rooms == null || rooms.length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] offset = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0){
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + offset[i][0];
                int newY = y + offset[i][1];
                if(newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] == Integer.MAX_VALUE) {
                    q.offer(new int[]{newX, newY});
                    rooms[newX][newY] = rooms[x][y] + 1;
                }
            }
        }
//        printArr(rooms);
    }

    private void printArr(int[][] arr){
        for (int[] a: arr){
            for (int i: a){
                System.out.print(i==Integer.MAX_VALUE ? "∞":String.format("%2d", i));
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int[][] init(){
        int M = Integer.MAX_VALUE;
        int[][] arr = {
                {0,-1, 0, M},
                {M, M, M, M},
                {M,-1, M,-1},
                {0,-1, M, -1}
        };
        return arr;
    }
    public static void main(String[] args) {
        WallsAndGates wag = new WallsAndGates();
//        wag.wallsAndGates(wag.init());
        wag.solution(wag.init());

    }
}
