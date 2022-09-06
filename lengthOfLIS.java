package HOOT100;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 
进阶：
你能将算法的时间复杂度降低到 O(n log(n)) 吗?

来源：力扣（LeetCode）

 */
public class lengthOfLIS {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int result = getLengthOfLIS1(nums);
        System.out.println("The Result Is : " + result);
    }
    /**
     * 解题思路：声明一个等长数组dp[]用于记录以第i位元素结尾的前缀数组中严格升序的子序列长度
     * @param nums
     * @return
     */
    public static int getLengthOfLIS1(int[] nums) {
        //声明一个等长数组dp[]
        int[] dp = new int[nums.length];
        //第一个元素因为没有前缀元素，以自身作为单个的升序子序列
        dp[0] = 1;
        //从第二位开始遍历
        for(int i = 1; i < nums.length; i++){
            //初始化当前最大升序子序列长度
            int curMax = 0;
            //遍历当前元素之前的所有元素
            for(int j = 0; j < i; j++){
                //如果第j元素小于当前元素取dp[j]和当前最大升序子序列长度最大
                if(nums[j] < nums[i]){
                    curMax = Math.max(dp[j], curMax);
                }
            }
            //把更新后的当前最大升序子序列长度更新到第i位置（+1 是补充当前比较的元素）
            dp[i] = curMax + 1;
        }
        int maxLen = 1;
        //遍历dp[]数组，返回数组中最大的值
        for(int max : dp){
            maxLen = Math.max(maxLen, max);
        }
        return maxLen; 
    }

    /**
     * 解题思路：声明一个二位矩阵dp[i][j]
     * @param nums
     * @return
     */
    public static int getLengthOfLIS2(int[] nums) {
        return 1;
    }
}
