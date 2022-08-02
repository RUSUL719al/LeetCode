package HOOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
输入: [2,2,1]
输出: 1

示例 2:
输入: [4,1,2,1,2]
输出: 4

来源：力扣（LeetCode）

 */
public class singleNumber {
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,2,5,6,6,5};
        int result = findSingleNumber2(nums);
        System.out.println("The Result Is : " + result);
    }

    //声明一个HashMap，遍历时判断Map中是否存在与当前元素相同的元素，有则删除无则新增。遍历完时Map中剩下的元素一定是目标元素（若返回值为0说明不止一个目标值）
    // 题目要求不能有额外的空间开辟，并且具有线性复杂度（遍历一遍就能找到目标值）
    public static int findSingleNumber1(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else{
                map.put(nums[i], i);
            }
        }
        return map.size()==1?(int) map.keySet().toArray()[0]:0;
    }
    //根据题目意思：只有一个数字只出现一次，其余数字都出现两次。使用异或运算。异或运算规则（二进制中只有同位元素相同结果为0，否则为1）即：相同数字取异或后结果为0，任何数字对0取异或结果为数字本身
    public static int findSingleNumber2(int[] nums) {
        int num = nums[0];
        if(nums.length>1){
            for(int i=1;i<nums.length;i++){
                num = num ^ nums[i];
            }
        }
        return num;
    }
}
