package HOOT100;
/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]

示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]

示例 3：
输入：nums = [], target = 0
输出：[-1,-1]
 
提示：
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109

来源：力扣（LeetCode）

 */
public class searchRange {
    
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] result = findRange(nums, target);
        for(int re : result){
            System.out.print(" " + re);
        }
    }

    public static int[] findRange(int[] nums, int target) {
        int leftIndex = search(nums, target);
        int rightIndex = search(nums, target + 1);
        if(leftIndex == nums.length || nums[leftIndex] != target){
            return new int[]{-1,-1};
        }else{
            return new int[]{leftIndex,rightIndex - 1};
        }
    }

    public static int search(int[] nums, int target) {
        int left = 0;int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if(nums[mid] >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }
}
