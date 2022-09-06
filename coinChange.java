package HOOT100;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。

示例 1：
输入：coins = [1, 2, 5], amount = 11
输出：3 
解释：11 = 5 + 5 + 1

示例 2：
输入：coins = [2], amount = 3
输出：-1

示例 3：
输入：coins = [1], amount = 0
输出：0
 
提示：
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104

来源：力扣（LeetCode）

 */
public class coinChange {
    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        int result = getMinCoinChange1(coins, amount);
        System.out.println("The Result Is : " + result);
    }

    /*
    借用经典完全背包问题思路:
    声明一个数组dp[]
    dp[i]表示要凑成金额i的最少硬币数量
    */
    public static int getMinCoinChange1(int[] coins, int amount){
        int[] dp = new int[amount+1];
        //求最少数量就初始化为MAX，求最多就初始化为MIN
        //Arrays.fill(dp, Integer.MAX_VALUE);(此解不能用Integer.MAX_VALUE ，案例 coins：[2] amount:3 会因为最大值加一变成负数返回)
        Arrays.fill(dp, amount + 1);//只要大于所有就行
        //凑成0元的最少数量当然是0
        dp[0] = 0;
        //循环更新dp[i] 外层i循环金额;内层j循环硬币
        //不考虑当前硬币，那当前的最少数量就是dp[i]默认值
        //考虑当前硬币，那从当前金额减去当前硬币面值，让之前的硬币凑成差额，因为考虑了当前硬币所以dp[i-coins[j]]要加上1
       //最后让dp[i] 取这两个情况中的最小
       for(int i = 1; i <= amount; i++){
           for(int j = 0; j < coins.length; j++){
               //如果当前金额够减当前硬币面值才可以让其他硬币去凑差值
               if(i >= coins[j]){
                   dp[i] = Math.min(dp[i], dp[i - coins[j]]+1);
               }
           }
       }
       //更新结束后如果最后一个位置没被更新说明无法凑成，如果能凑成那凑成的最少数量就是最后一个位置的值
       return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int getMinCoinChange2(int[] coins, int amount) {
        // 动态规划 dp[i] 表示组成金额i所需最少的硬币数量，典型的完全背包问题
        if(amount == 0) return 0;
        int[] dp = new int[amount+1]; 
        Arrays.fill(dp, amount+1);  // 也可以直接填充Integer.MAX_VALUE
        dp[0] = 0;
        for(int i = 1; i < amount+1; i++){  // 数量，遍历背包
            for(int j = 0; j < coins.length; j++){  // 金额，遍历物品
                // 最小硬币数 = 最小硬币数与该金额减去一个面值的最小硬币数+1
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
