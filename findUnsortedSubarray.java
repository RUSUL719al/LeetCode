package HOOT100;
/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
请你找出符合题意的 最短 子数组，并输出它的长度。

示例 1：
输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。

示例 2：
输入：nums = [1,2,3,4]
输出：0

示例 3：
输入：nums = [1]
输出：0

提示：
1 <= nums.length <= 104
-105 <= nums[i] <= 105

进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
来源：力扣（LeetCode）

 */
public class findUnsortedSubarray {
    public static void main(String[] args) {
        //int[] nums = {2,6,4,8,10,9,15};
        int[] nums = {1,2,3,4};
        int result = findUnsortedSubarray1(nums);
        System.out.println("THe Result Is : " + result);
    }
     public static int findUnsortedSubarray1(int[] nums) {
        int len = nums.length;int start = 0;int end = 0;
        if(len <= 2){
            return len == 1 ? 0 : (nums[0] > nums[1] ? 2 : 0);
        }
        for(int left = 0,right = 1; right < len;){
            if(right < len && nums[right] > nums[left]){
                right++;left++;
            }else if(right == len - 1){
                return 0;
            }else{
                start = left;
                left = right;
                right++;
                if(nums[right] > nums[left]){
                    right++;left++;
                }
                end = right;
            }
        }
        return end - start + 1;
     }
}
