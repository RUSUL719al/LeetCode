package HOOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：
输入：nums = [3,2,3]
输出：3

示例 2：
输入：nums = [2,2,1,1,1,2,2]
输出：2
 
提示：
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
 
进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

来源：力扣（LeetCode）
 */
public class majorityElement {
    public static void main(String[] args) {
        int[] nums = {3,2,3,3,1,3,4,3};
        int result = findMajorityElement2(nums);
        System.out.println("The Result Is:" + result);
    }

    public static int findMajorityElement1(int[] nums){
        int len = nums.length;
        int half = len >> 1;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i< nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        for(int i=0;i<map.values().size();i++){
            int num = (int)map.values().toArray()[i];
            if(num > half){
                int resIndex = (int)map.keySet().toArray()[i];
                return resIndex;
            }
        }
        return 0;
    }

    /**
     * 摩尔投票法思路
候选人(cand_num)初始化为nums[0]，票数count初始化为1。
当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
当票数count为0时，更换候选人，并将票数count重置为1。
遍历完数组后，cand_num即为最终答案。

为何这行得通呢？
投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。

无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。

     * @param nums
     * @return
     */
    public static int findMajorityElement2(int[] nums) {
        int res = nums[0], count = 1;
        for(int i=1;i<nums.length;i++){
            if(res == nums[i]){
                count ++;
            }else{
                count --;
                if(count == 0){
                    res = nums[i];
                    count = 1;
                }
            } 
        }
        return res;
    }
}
