package HOOT100;
/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
必须在不使用库的sort函数的情况下解决这个问题。

示例 1：
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]

示例 2：
输入：nums = [2,0,1]
输出：[0,1,2]
 
提示：
n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
 
进阶：
你可以不使用代码库中的排序函数来解决这道题吗？
你能想出一个仅使用常数空间的一趟扫描算法吗？

来源：力扣（LeetCode）

 */
public class sortColors {
    public static void main(String[] args) {
        int[] nums = {1,2,0,0,1,2,2,0};
        int[] result = sortColors2(nums);
        for(int num : result){
            System.out.print(" " + num);
        }
    }

    public static int[] sortColors1(int[] nums) {
        int prt = 0; int R = nums.length;
        for (int i = 0; i < R; i++) {
            if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[prt];
                nums[prt] = temp;
                prt++;
            }
        }

        for (int i = prt; i < R; i++) {
            if(nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[prt];
                nums[prt] = temp;
                prt++;
            }
        }
        return nums;
    }

    public static int[] sortColors2(int[] nums) {
        int p = 0; int q = nums.length - 1;
        for(int i = 0; i <= q; i++){
            if(nums[i] == 0){
                nums[i] = nums[p];
                nums[p] = 0;
                p++;
            }
            if(nums[i] == 2){
                nums[i] = nums[q];
                nums[q] = 2;
                q--;
                if(nums[i] != 1){
                    i--;
                }
            }
        }
        return nums;
    }
}
