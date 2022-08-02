package HOOT100;
/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。

示例 1：
输入：n = 2
输出：[0,1,1]
解释：
0 --> 0
1 --> 1
2 --> 10

示例 2：
输入：n = 5
输出：[0,1,1,2,1,2]
解释：
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

提示：
0 <= n <= 105

来源：力扣（LeetCode）

 */
public class countBits {
    public static void main(String[] args) {
        int num = 5;
        int[] result = countAllBits(num);
        for(int re : result){
            System.out.print(" " + re);
        }
        
    }

    public static int[] countAllBits(int num) {
        int[] result = new int[num + 1];
        for(int i=1;i<=num;i++){
            if((i & 1) == 0){
                result[i] = result[i >> 1];
            }else{
                result[i] = result[i -1] +1;
            }
        }
        return result;
    }
}
