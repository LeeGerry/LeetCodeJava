package company.LinkedIn.easy;

/**
 * LC  256
 There are a row of n houses,
 each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color
 is represented by a n x 3 cost matrix.
 For example,costs[0][0] is the cost of painting house 0 with color red;
            costs[1][2] is the cost of painting house 1 with color green,
            and so on...
 Find the minimum cost to paint all houses.
 Note:
 All costs are positive integers.
 有一排房子，每个房子可以被刷成红蓝绿三种颜色之一。
 每种颜色刷到每个房子上的花费是不一样的。需要刷出来的房子挨着的颜色不能相同。
 cost[n][3]:
 {  {34,22,17},
    {36,12,11},
    {64,25,10},
    {13,28,98} }
 例如该矩阵，第一行是0号房子刷三种颜色的代价34，22，17
 找到一种最便宜的方法，满足上述条件
 */
public class PaintHouse {
    /*
    思路，用DP：时间O(N) 空间O(1)
    到房子i，最小的代价是 直到房子i-1的最小代价 + 房子i本身的刷颜色代价。
    房子i的涂色方式要根据房子i-1的涂色方式来决定，所以对房子i-1要记录三种颜色分别的不同代价，
    这样在房子i刷颜色的时候，就知道三种颜色各自的最小代价是多少了。
    在原数组上进行修改的话，可以做到不用额外空间
    */
    public int minCost(int[][] costs){
        if(costs == null || costs.length == 0 || costs[0].length == 0)  return 0;
        for (int i = 1; i<costs.length;i++){
            // 刷第一种颜色的话，上一个房子就不能刷第一种颜色，所以要在上一个房子的第二和第三个颜色的最小代价中找最小的那个
            // 然后加上i个说话第一个颜色的本身的代价
            costs[i][0] = costs[i][0] + Math.min(costs[i-1][1], costs[i-1][2]);
            // 同理,刷第二种颜色的话，取上个房子刷第一和第三个的代价的最小值
            costs[i][1] = costs[i][1] + Math.min(costs[i-1][0], costs[i-1][2]);
            // 刷第三种颜色的话，取上个房子刷第一和第二个的代价的最小值
            costs[i][2] = costs[i][2] + Math.min(costs[i-1][0], costs[i-1][1]);
        }
        // 返回三种颜色中代价最小的那个
        return Math.min(costs[costs.length - 1][0],Math.min(costs[costs.length - 1][1],costs[costs.length - 1][2]));
    }
}
