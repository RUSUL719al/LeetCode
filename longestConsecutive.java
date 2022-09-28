package HOOT100;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

示例 2：
输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9

提示：
0 <= nums.length <= 105
-109 <= nums[i] <= 109

来源：力扣（LeetCode）

 */
public class longestConsecutive {
    public static void main(String[] args) {
        int[] nums = {108,104,109,-109,-108,-107,105,106,107};
        int result = findLoggestConsecutive(nums);
        System.out.println("The Result Is : " + result);
    }

    public static int findLoggestConsecutive(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        //先把数组放入集合中
        for(int num : nums){
            hash.add(num);
        }
        int maxLen = 0;
        for(int num : hash){
            //找出集合中连续数组中的最小数字
            if(!hash.contains(num-1)){
                int y = num;
                //依次枚举是否有比当前数大1的数字
                while (hash.contains(y+1)) {
                    y++;
                }
                //跳出循环说明不再连续了，最后一位减第一位就是连续数组的中间距离
                maxLen = Math.max(maxLen, y - num + 1);
            }
        }
        return maxLen;
    }
}
