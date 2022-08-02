package HOOT100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]

示例 2：
输入：nums = []
输出：[]

示例 3：
输入：nums = [0]
输出：[]
 
提示：
0 <= nums.length <= 3000
-105 <= nums[i] <= 105

来源：力扣（LeetCode）
 */
public class threeSum {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> result = findThreeSum(nums);
        System.out.print("[");
        for(int i=0;i<result.size();i++){
            System.out.print("[");
            for(Integer num : result.get(i)){
                System.out.print(" " + num);
            }
            System.out.print("]");
        }
        System.out.print("]");
    }

    public static List<List<Integer>> findThreeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int len =  nums.length;
        if(nums == null || len < 3){
            return null;
        }
        Arrays.sort(nums);
        for(int i = 0; i < len ; i++){
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] == nums[i+1]){
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    list.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left ++ ;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right --;
                    }
                    left ++;right--;
                }else if(sum < 0){
                    left ++ ;
                }else{
                    right -- ;
                }
            }
        }
        return list;
    }
}
