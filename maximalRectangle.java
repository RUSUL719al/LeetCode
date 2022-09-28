package HOOT100;

import java.util.Stack;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
示例 1：
输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：6
解释：最大矩形如上图所示。

示例 2：
输入：matrix = []
输出：0

示例 3：
输入：matrix = [["0"]]
输出：0

示例 4：
输入：matrix = [["1"]]
输出：1

示例 5：
输入：matrix = [["0","0"]]
输出：0
 
提示：
rows == matrix.length
cols == matrix[0].length
1 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）

 */
public class maximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int result = getMaximalRectangle(matrix);
        System.out.println("The Result Is : " + result);
    }

    public static int getMaximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int rows = matrix.length; int cols = matrix[0].length;int maxArea = 0;
        int[] heights = new int[cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == '0'){
                    heights[j] = 0;
                }else{
                    heights[j] += 1;
                }
            }
            maxArea = Math.max(maxArea, getLargestReactangleArea2(heights));
        }
        return maxArea;
    }

    public static int getLargestReactangleArea2(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        int len = newHeights.length; int maxArea = 0;
        for(int i = 1; i < len - 1; i++){
            newHeights[i] = heights[i-1];
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int curIndex = stack.pop();
                int curHeight = newHeights[curIndex];
                int curWidth = i - stack.peek() - 1;
                int curMaxArea = curHeight * curWidth;
                maxArea = Math.max(maxArea, curMaxArea);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
