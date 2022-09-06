package HOOT100;
/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5

示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
 
提示：
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104

来源：力扣（LeetCode）

 */
public class findKthLargest {
    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        //int[] nums = {4,3};
        int k = 4;
        //int k = 2;
        int result = findKthLargestNum(nums, k);
        System.out.println("The Kth Largest Num Is : " + result);
    }

    public static int findKthLargestNum(int[] nums, int k) {
        if(nums.length == 0 || k == 0){
            return 0;
        }
        int indexK = nums.length - k;
        //return bubbleSelect(nums, k);
        quickSelect(nums, 0, nums.length-1, indexK);
        return nums[indexK];
    }

    /**
     * 利用冒泡排序的特性（冒泡排序遍历一趟只能归位当前最大的一个数字），因此只需要遍历k趟就行
     * K值相对大时会超时（不推荐）
     * @param nums
     * @param k
     * @return
     */
    public static int bubbleSelect(int[] nums, int k) {
        for(int i = 0; i < k; i++){
            for(int j = 0; j < nums.length - 1 - i; j++){
                if(nums[j] > nums[j+1]){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }
        return nums[nums.length - k];
    }

    public static void quickSelect(int[] nums, int start, int end, int indexK) {
        //随机获取start ~ end 之间一个下标
        int random = (int)(start + Math.random()*(end - start + 1));
        //交换随机元素和最后的元素
        swap(nums, random, end);
        //获取partation结果等于区域的下标范围（小于区域在左边等于区域在中间大于区域在右边）
        int[] equlaLen = partation(nums, start, end);
        if(indexK >= equlaLen[0] && indexK <= equlaLen[1]){
            indexK = equlaLen[0];
            return;
        }else if(indexK < equlaLen[0]){
            quickSelect(nums, start, equlaLen[0] - 1, indexK);
        }else{
            quickSelect(nums, equlaLen[1] + 1, end, indexK);
        }
    }

    public static int[] partation(int[] nums, int start, int end) {
        int smallRight = start - 1; int bigLeft = end;
        while (start < bigLeft) {
            if(nums[start] < nums[end]){
                swap(nums, ++smallRight, start++);
            }else if(nums[start] > nums[end]){
                swap(nums, --bigLeft, start);
            }else{
                start++;
            }
        }
        swap(nums, bigLeft, end);
        return new int[]{smallRight+1,bigLeft};
    }

    //交换数组中的两个数
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
