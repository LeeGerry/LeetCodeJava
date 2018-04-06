package company.LinkedIn.hard;

/**
 * LC 174
 The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 The dungeon consists of M x N rooms laid out in a 2D grid.
 Our valiant knight (K) was initially positioned in the top-left room
 and must fight his way through the dungeon to rescue the princess.

 The knight has an initial health point represented by a positive integer.
 If at any point his health point drops to 0 or below, he dies immediately.
 Some of the rooms are guarded by demons,
 so the knight loses health (negative integers) upon entering these rooms;
 other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 In order to reach the princess as quickly as possible,
 the knight decides to move only rightward or downward in each step.

 Write a function to determine the knight's minimum initial health
    so that he is able to rescue the princess.

 For example, given the dungeon below,
    the initial health of the knight must be at least 7
    if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 -2 (K)     -3      3
 -5         -10     1
 10         30      -5 (P)

 Notes:
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups,
    even the first room the knight enters and the bottom-right room where the princess is imprisoned.

 公主被恶龙关在地牢右下角，骑士救公主。每次骑士向下移动或者向右移动。骑士最少需要多少血量才能成功把公主救出。
 数组代表地牢，骑士在左上角，公主在右下角，每个格子代表增加或者减少的血量：-2代表骑士在这里要减少2血量，3代表骑士可以获得3个血量。
 注意：骑士血量无上限；每个小格子都有增加或者减少血量，不管是骑士的起始格子还是公主所在的格子。
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null ||dungeon.length == 0 || dungeon[0].length == 0)	return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;
        //DP二维数组表示血量
        int[][] dp = new int[m][n]; // dp[i][j]表示从(i,j)到目的地(m-1, j-1)需要的最小生命值

        //初始化右下角的血量格子中的值
        dp[m-1][n-1] = Math.max(1-dungeon[m-1][n-1], 1);
        //逆推最后一列的血量
        for(int i = m-2; i>=0; i--){
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1);
        }
        print(dp);
        //逆推最后一行的血量
        for(int i = n-2; i>=0; i--){
            dp[m-1][i] = Math.max(dp[m-1][i+1] - dungeon[m-1][i], 1);
        }
        print(dp);
        //对于每个节点，从下方和右方逆推出来
        for(int i = m-2; i>=0; i--){
            for(int j = n-2; j>=0; j--){
                int down = Math.max(dp[i+1][j] - dungeon[i][j], 1);//从下方逆推出来的值
                int right = Math.max(dp[i][j+1] - dungeon[i][j], 1);//从右方逆推出来的值
                dp[i][j] = Math.min(down, right);//选择最小血量放入当前血量格子
            }
        }
        print(dp);
        return dp[0][0];//返回骑士起始所在格子对应的血量
    }

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2,  -3,  3},
                {-5, -10,  1},
                {10,   3, -5}
        };
        DungeonGame game = new DungeonGame();
        System.out.println(game.calculateMinimumHP(dungeon));
    }
    private void print(int[][] arr) {
        for (int[] line: arr) {
            for (int n: line) {
                System.out.print(n + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
