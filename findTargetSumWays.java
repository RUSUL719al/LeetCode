package HOOT100;


/**
 * 给你一个整数数组 nums 和一个整数 target 。
向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目

实例1：
输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

来源：力扣（LeetCode）

 */
public class findTargetSumWays {
    static int count = 0;
    public static void main(String[] args) {
        int[] arrs = {0,0,0,0,0,0,0,0,1};
        int result = 1;
        //int res = process1(arrs, 0, result);
        int res = process3(arrs, result);
        System.out.println("The Result Is : " + res);
    }
    //递归的方式实现(当index小于数组长度时，一直递归相加当前元素前加 + 和加 - 的两种情况)
    public static int process1(int[] arrs, int index, int result) {
        //当遍历到最后一个元素时，如果result正好等于0.说明有一个组合是符合要求的
        if(index == arrs.length){
            return result ==0?1:0;
        }
        //如果当前元素取正，那下一个递归传的应该是目标值-当前元素，否则相反
        return process1(arrs, index+1, result-arrs[index]) + process1(arrs, index+1, result + arrs[index]);
        
    }


    //回溯的方式实现（index每次到达最后一个元素时判断一下是否等于目标数，若是自增计数。否则给当前元素增加加，减号各走一遍）
    public static int process2(int[] arrs,int result) {
        dp(arrs, result, 0, 0);
        return count;
    }
    public static void dp(int[] arrs, int target, int index, int sum) {
        if(index == arrs.length){
            if(sum == target){
                count ++;
            }
        }else{
            dp(arrs, target, index + 1, sum + arrs[index]);
            dp(arrs, target, index + 1, sum - arrs[index]);
        }
    }


    //使用动态规划的方式
    public static int process3(int[] arrs, int result) {
        /**
         * 例：  arrs = [1,2,3,4,5], result = 5（若题目中标识有正有负，先把所有负数转正，应为加 + 和加 - 不影响数字的正负性质）
         * 一种组合：-1 + 2 + 3 - 4 + 5 = 5
         * 组合中 正数组合P{2,3,5} 负数组合N{1,4} 全员总和 S=15
         * P - N = result   等式两边相加 S 后得 P - N + S = result + S  ->  P - N + P + N = result + P + N  -> 2P = result + S  -> P = (result+ S)/2
         * 由公式可得等式左边 P 是正数组合，等式右边是目标值和全员总和相加的一半。带入例子  P = （5 + 15）/2  -> P = 10  
         * 此结果的意思：在给出的元素中随意组合元素相加结果等于10的可能性有多少个就是本题答案
         * 优化点：优化前题目的数据规模是 -S ~ +S（全员取负到全员取正），优化后数据规模是 0 ~ +S（目标值最多跟S一样，如果大于S本体无解）
         *         把复杂问题简单化（优化后是经典的背包问题-01背包  注：完全背包是同一个元素可无限使用）
         */

        //先求和
        int sum = 0;
        for(int num : arrs){
            sum += num;
        }
        //目标值大于求和    或   求和数 和 目标值的奇偶性不一致的话返回0 ， 否则调用动态规划
        return Math.abs(result) > Math.abs(sum) || ((sum & 1)^(result & 1)) !=0 ? 0 : dp(arrs, (result+sum)>>1);
        
    }

    /**
     * 经典背包问题（动态规划）
     * @param nums   数组
     * @param target 目标数
     * @return       返回方法数
     * 解题思路：
     * 1.二维数组 dp[i][j]表示 从下标为0~i个数字中求和能得到j的方法数。
     * 2.每一行dp[i][j]的结果都依赖上一行即dp[i-1][j]的结果
     *   因为下标为0~i的数字中每个数子都有取和不取的两种情况，
     *   如果不取当前数那 dp[i][j] = dp[i-1][j];如果取当前数那 dp[i][j] = dp[i-1][j - 当前数]  最终得： dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i]]
     * 3.得加条件：如果当前数大于当前目标数 那不需要 dp[i-1][j - 当前数]
     */
    public static int dp(int[] nums, int target) {
        //因为下标是从0开始，因此声明二维数组时列数加一
        int[][] dp = new int[nums.length][target+1];
        //初始nums[0~0]凑出0的方法数为1
        dp[0][0] = 1;
        //nums[0~0]特殊情况处理（如果nums[0]为0，则增加一个方法数）
        for(int i = 0 ; i <= target; i++){
            if(i == nums[0]){
                dp[0][i] +=  1;
            }
        }
        
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j <= target; j++){
                if(nums[i] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i]]; 
                }
            }
        }
        return dp[nums.length-1][target];
    }
}
