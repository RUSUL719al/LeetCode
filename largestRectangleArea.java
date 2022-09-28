package HOOT100;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

示例 1:
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10

示例 2：
输入： heights = [2,4]
输出： 4
 
提示：
1 <= heights.length <=105
0 <= heights[i] <= 104

来源：力扣（LeetCode）
 */
public class largestRectangleArea {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        //int result = getLargestReactangleArea1(heights);
        int result = getLargestReactangleArea2(heights);
        System.out.println("The Result Is : " + result);
    }
    /**
     * 暴力解法：(会超时)
     *          1.遍历每个元素，
     *          2.查找当前元素左边最多能向左延伸多长，找到大于等于当前柱形高度的最左边元素的下标；
                3.查找当前元素右边最多能向右延伸多长；找到大于等于当前柱形高度的最右边元素的下标。
                4.得到一个矩形面积，求出它们的最大值
     * @param heights
     * @return
     */
    public static int getLargestReactangleArea1(int[] heights) {
        int len = heights.length; int maxArea = 0;
        for(int i = 0; i < len; i++){
            int curHeight = heights[i];
            int left = i;
            while (left-1 >= 0 && heights[left-1] >= curHeight) {
                left --;
            }
            int right = i;
            while (right+1 < len && heights[right+1] >= curHeight) {
                right ++;
            }
            int curArea = curHeight * (right - left + 1);
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }

    /**
     * 利用单调栈结构特性
     * @param heights
     * @return
     */
    public static int getLargestReactangleArea2(int[] heights) {
        //为了能处理第一位数字和最后一位数字，数组两边增加 0 
        int[] newHeights = new int[heights.length + 2];
        for(int i = 1; i < newHeights.length - 1; i++){
            newHeights[i] = heights[i - 1];
        }
        int len = newHeights.length; int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int curIndex = stack.pop();
                int curHeight = newHeights[curIndex];
                int curWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, curHeight * curWidth);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
