package HOOT100;
/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
示例 1：
输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：4

示例 2：
输入：matrix = [["0","1"],["1","0"]]
输出：1

示例 3：
输入：matrix = [["0"]]
输出：0
 
提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）

 */
public class maximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int result = findMaxSizeSquare(matrix);
        System.out.println("The Result Is : " + result);
    }

    public static int findMaxSizeSquare(char[][] matrix) {
        //矩阵行数
        int rowLen = matrix.length;
        //矩阵列数
        int colLen = matrix[0].length;
        //只包含1的最大正方形边长初始化
        int maxSize = 0;
        //声明一个二位矩阵dp, dp[i][j]表示以第i行第j列元素作为右下角的正方形的最大边长
        int[][] dp = new int[rowLen][colLen];
        if(rowLen == 0 || colLen == 0){
            return maxSize;
        }

        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(matrix[i][j] == '1'){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1]) + 1;
                    }
                    maxSize = Math.max(dp[i][j], maxSize);
                }
            }
        }
        return maxSize*maxSize;
    }
}
