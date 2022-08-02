package HOOT100;
/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
说明：你不能倾斜容器。

示例 1：

输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例 2：
输入：height = [1,1]
输出：1

提示：
n == height.length
2 <= n <= 105
0 <= height[i] <= 104

来源：力扣（LeetCode）

 */
public class maxArea {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int result = findMaxArea(height);
        System.out.println("The Result Is : " + result);
    }
    //水槽的宽度为 0 ~ height.length  水槽的高度为 length[i]。
    //改变水槽宽度找出水槽能容纳水的最大面积
    public static int findMaxArea(int[] heigth) {
        //声明两个指针，分别指向水槽宽度的开始和结尾
        int left = 0, right = heigth.length -1;
        //声明变量记录面积
        int maxArea = 0;
        //遍历条件：左指针小于右指针
        while (left < right) {
            //高度低的一端决定容纳水的面积，如果移动高度高的一端时水槽宽度减一，水槽面积还是由低端决定，因此哪边低就移动哪边能找到可能的最大面积
            maxArea = heigth[left] < heigth[right] ?
                      Math.max(maxArea , (right - left) * heigth[left++]) :
                      Math.max(maxArea , (right - left) * heigth[right--]);
        }
        return maxArea;
    }
}
