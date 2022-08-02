package HOOT100;

/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
给你两个整数 x 和 y，计算并返回它们之间的汉明距离。

示例 1：
输入：x = 1, y = 4
输出：2
解释：
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
上面的箭头指出了对应二进制位不同的位置。

示例 2：
输入：x = 3, y = 1
输出：1
 
来源：力扣（LeetCode）

 */
public class hammingDistance {
    public static void main(String[] args) {
        int x = 93 , y = 73;
        int result = findHanmingDistance2(x, y);
        System.out.println("The Result Is :" + result);
    }
    //使用异或运算的特点（相同为0，否则为1），再用整数类型的内置的计算二进制表达中 1 的数量的函数
    public static int findHanmingDistance1(int x, int y) {
        int num = x ^ y;
        int re = Integer.bitCount(num);
        return re;
    }

    public static int findHanmingDistance2(int x, int y) {
        int num = x ^ y; int re = 0;
        while (num != 0) {
            re += num & 1;
            num >>= 1; 
        }
        return re;
    }
}