package company.LinkedIn.easy;

/**
 * LC  265
 There are a row of n houses, each house can be painted with one of the k colors.
 The cost of painting each house with a certain color is different.
 You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x k cost matrix.
 For example,
 costs[0][0] is the cost of painting house 0 with color 0;
 costs[1][2] is the cost of painting house 1 with color 2,
 and so on...
 Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Follow up:
 Could you solve it in O(nk) runtime?
 */
public class PaintHouse2 {
        /* 跟上个不同，有一排房子，每个房子可以被刷K种颜色之一，而不是上个题目说的3种颜色之一。
             思路，用DP：时间O(N) 空间O(1)
             和上一个题目的思路一样，不过这里有K个颜色，不能简单的用min方法了。
             只需要把最小和次小的都记录下来，这样，如果和最小的是一个颜色，就加上次小的代价，反之，加上最小的代价。
        */
        public int minCost(int[][] costs){
            if(costs == null || costs.length == 0 || costs[0].length == 0)  return 0;
            int prevMin = 0, preSec = 0, prevIdx = -1;
            for (int i = 0; i<costs.length; i++){
                int currentMin = Integer.MAX_VALUE, currentSecond = Integer.MAX_VALUE, currentIdx = -1;
                for (int j = 0; j<costs[0].length; j++){
                    costs[i][j] = costs[i][j] + (prevIdx == j ? preSec : prevMin);
                    // 找出最小的和次小的，最小的要记录下标，方便下一轮判断
                    if (costs[i][j] < currentMin){
                        currentSecond = currentMin;
                        currentMin = costs[i][j];
                        currentIdx = j;
                    }else if (costs[i][j] < currentSecond){
                        currentSecond = costs[i][j];
                    }
                }
                prevMin = currentMin;
                preSec = currentSecond;
                prevIdx = currentIdx;
            }
            return prevMin;
        }

    public static void main(String[] args) {
        int[][] nums = {
                {3,4,3,1,5},
                {2,1,3,2,3},
                {3,1,2,2,4},
                {1,1,2,2,1},
                {2,2,1,2,1},
        };
        System.out.println(new PaintHouse2().minCost(nums));
    }
}
