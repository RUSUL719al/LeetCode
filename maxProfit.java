package HOOT100;
/**
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入: prices = [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

示例 2:
输入: prices = [1]
输出: 0
 
提示：
1 <= prices.length <= 5000
0 <= prices[i] <= 1000

来源：力扣（LeetCode）
 */
public class maxProfit {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        int result = getMaxProfit(prices);
        System.out.println("The Result Is : " + result);
    }

    /**
     * 声明一个二维矩阵dp[i][j]表示该股票在第i天在j的状态下收益最大值
     * 股票状态j：0：今天持有  1：因为没买未持有  2：今天买入又卖出未持有
     * 第一天持有的收益：  dp[0][0] = 当日股价取负数； 
     * 第一天未持有的收益：dp[0][1] = 0;(没买入)
     * 第一天未持有的收益：dp[0][2] = 0;(买入又卖出)
     * @param prices
     * @return
     */
    public static int getMaxProfit(int[] prices) {
        //记录天数
        int days = prices.length;
        //声明一个二位矩阵dp[i][j]并初始化(i表示天数，j表示股票状态)
        int[][] dp = new int[days][4];
        //第一天
        dp[0][0] = -prices[0];//持股且没卖出
        dp[0][1] = 0;//未持股且未卖出
        dp[0][2] = 0;//未持股且已卖出
        for(int i = 1; i < days; i++){
            //第i天持股：（1）前一天就有持股，今天继续继承  （2）前一天未持股且没卖出，今天才买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            //第i天未持股且未卖出：（1）前一天未持股也未卖出（一直未持股） （2）前一天未持股且已卖出（就是今天是冷冻期）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            //第i天未持股且已卖出：（1）前一天持股且未卖出，今天才卖出 （2）前一天未持股且未卖出（今天能卖说明前一天不能卖）
            dp[i][2] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][0]);
        
        }
        //最后一天如果要最大化，那最后一天不能持股，所以返回持股的两个状态中返回最大的
        return Math.max(dp[days - 1][1], dp[days - 1][2]);
    }
}
