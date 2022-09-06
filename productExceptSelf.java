package HOOT100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
请不要使用除法，且在 O(n) 时间复杂度内完成此题。

示例 1:
输入: nums = [1,2,3,4]
输出: [24,12,8,6]

示例 2:
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
 
提示：
2 <= nums.length <= 105
-30 <= nums[i] <= 30
保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 
进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

来源：力扣（LeetCode）

 */
public class productExceptSelf {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,4,6};
        int[] result = getProductExceptSelf2(nums);
        for(int num : result){
            System.out.print(num + " ");
        }
    }

    //会超时~~~
    public static int[] getProductExceptSelf1(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for(int num : nums){
            list.add(num);
        }
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(list.indexOf(nums[i]) > -1){
                list.remove(list.lastIndexOf(nums[i]));
                int curResult = 1;
                for(int curNum : list){
                    if(curNum == 0){
                        curResult = 0;
                    }
                    curResult *= curNum;
                }
                result[i] = curResult;
                list.add(nums[i]);
            }
        }
        return result;
    }

    /**
     * 解题思路：声明一个等长数组用res[]于返回
     * 1.第一次循环在res[]数组中填充当前元素左边的累乘（res[i]表示第i元素左边所有元素的乘积）
     * 2.第二次循环在res[]数组中填充当前元素右边的累乘（res[i]表示第i元素左边所有元素的乘积，再把第i元素右边元素累乘就是最后的结果）
     * @param nums
     * @return
     */
    public static int[] getProductExceptSelf2(int[] nums) {
        //声明一个等长数组
        int[] res = new int[nums.length];
        //p表示当前元素左边所有元素的乘积（第一个元素左边没有值，因此初始化为1）
        //q表示当前元素右边所有元素的乘积（最后一个元素左边没有值，因此初始化为1）
        int p = 1, q = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = p;
            p *= nums[i];
        }
        for (int i = nums.length - 1; i > 0 ; i--) {
            q *= nums[i];
            res[i - 1] *= q;
        }
        return res;
    }
}
