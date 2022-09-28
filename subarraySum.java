package HOOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
示例 1：
输入：nums = [1,1,1], k = 2
输出：2

示例 2：
输入：nums = [1,2,3], k = 3
输出：2

提示：
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107

来源：力扣（LeetCode）

 */
public class subarraySum {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int k = 3;
        int result = getSubArraySum2(nums, k);
        System.out.println("The Result Is :" + result);
    }
    /*
    解题思路:
    1.先求出每个元素的前缀和
    2.双层循环让每一个前缀和互减
    3.如果有一个结果等于k则增加一个符合题意的解
    利用前缀和perSum[]的思路:
    1. perSum[i] = nums[0] + nums[1] + ··· nums[i]
    2. perSum[i] - perSum[i-1] = nums[i]
    3. nums[i] + nums[i+1] + ··· + nums[j] = (perSum[j] - perSum[j-1]) + (perSum[j-1] - perSum[j-2]) + ··· +(perSum[i+1] - perSum[i]) + (perSum[i] - perSum[i-1])
    4. 根据公式3可得 
    nums[i] + ··· + nums[j] = perSum[j] - perSum[i-1]
    5. 根据题意,要求出有多少个nums[i] + ··· + nums[j]的结果正好等于k(包括nums[i]本身等于k的情况)
    6. 综上所述，此题只要求出有多少个前缀和的差等于k
    */
    public static int getSubArraySum1(int[] nums, int k) {
        //用于返回
        int count = 0;
        //数组长度
        int len = nums.length;
        //声明前缀和数组(长度加1是因为公式计算中考虑要用到第一个元素之前的前缀和)
        int[] perSum = new int[len+1];
        //第一个元素之前的前缀和人为置零
        perSum[0] = 0;
        for(int i = 0; i < len; i++){
            perSum[i+1] = perSum[i] + nums[i];
        }
        //循环让每一个前缀和互减
        for(int i = 0; i < len; i++){
            for(int j = i; j < len; j++){
                if(perSum[j+1] - perSum[i] == k){
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * 此方法2基于方法1上优化
     * 1.方法1中已得知 nums[i] + ··· + nums[j] = perSum[j] - perSum[i-1]
     *   且题意要求找出有多少个nums[i] + ··· + nums[j] = k
     *   方法1是求出所有元素的前缀和，然后互减
     * 2.方法2中不需要提前求出全部前缀和，用Map存储前缀和 及 该前缀和的次数
     *   求出当前元素的前缀和后，判断map中是否存在满足 perSum[i] - k 的历史前缀和
     *   若存在 则让 （perSum[i] - k）的 value值与当前累计的count相加
     *   若不存在 则把perSum[i]更新到map中（注意考虑map中存在perSum[i]，所以value值要累加更新）
     * @param nums
     * @param k
     * @return
     */
    public static int getSubArraySum2(int[] nums, int k) {
        //用于返回
        int count = 0;
        //前缀和
        int perSum = 0;
        //用Map记录前缀和 及 该前缀和出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        //此步可以理解为在开始计算前缀和时不取任何数的前缀和为0的情况有1个
        map.put(0, 1);
        for(int num : nums){
            //当前前缀和
            perSum += num;
            //如果满足：当前前缀和 减 k 等于 等于历史前缀和 则 等价于 当前前缀和 减 历史前缀和 等于 k
            if(map.containsKey(perSum - k)){
                count += map.get(perSum - k);
            }
            //更新当前前缀和到map中
            map.put(perSum, map.getOrDefault(perSum,0) + 1);
        }
        return count;
    }
}
