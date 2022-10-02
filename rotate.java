package HOOT100;
/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]

示例 2：
输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 
提示：
n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

来源：力扣（LeetCode）

 */
public class rotate {
    public static void main(String[] args) {
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        int[][] result = rotateMatrix(matrix);
        for(int[] re : result){
            for(int r : re){
                System.out.print("  " + r);
            }
            System.out.println(" ");
        }
    }

    public static int[][] rotateMatrix(int[][] matrix) {
        int a = 0,b = 0, c = matrix.length - 1,d = matrix[0].length - 1;
        while (a < c) {
            func(matrix, a++, b++, c--, d--);   
        }
        return matrix;
    }
    /**
     * 创建一个函数旋转矩阵，把矩阵分组（此函数只旋转一层，本题从最外层开始依次循环-像剥洋葱一样）
     * @param matrix 矩阵参数
     * @param a 矩阵左上角元素的横坐标
     * @param b 矩阵左上角元素的纵坐标
     * @param c 矩阵右下角元素的横坐标
     * @param d 矩阵右下角元素的纵坐标
     */
    public static void func(int[][] matrix, int a, int b, int c, int d) {
        //循环次数
        int times = d - b ;
        int temp = 0;
        /**
         * 按照组数依次循环（依次交换元素值  一的值给二，二的值给三，三的值给四，四的值给一）
         * matrix[a][b+i]  第i组第一个元素位置
         * matrix[a+i][d]  第i组第二个元素位置
         * matrix[c][d-i]  第i组第三个元素位置
         * matrix[c-i][b]  第i组第四个元素位置
         */
        for(int i=0; i< times; i++){
            temp = matrix[a][b+i];
            matrix[a][b+i] = matrix[c-i][b];
            matrix[c-i][b] = matrix[c][d-i];
            matrix[c][d-i] = matrix[a+i][d];
            matrix[a+i][d] = temp;
        }
    }
}
