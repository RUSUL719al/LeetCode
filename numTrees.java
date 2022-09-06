package HOOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

示例 1：
输入：n = 3
输出：5

示例 2：
输入：n = 1
输出：1
 

提示：
1 <= n <= 19

来源：力扣（LeetCode）

 */
public class numTrees {
    public static void main(String[] args) {
        int num = 3;
        int result = findBST(num);
        System.out.println("The Result Is :" + result);
    }
    /**
     * 实现思路：
     * 1.搜索二叉树的特点是整个二叉树的左节点值小于根节点值，根节点值小于右节点值，进而搜索二叉树的中序遍历是递增的数组
     * 2.因此可以把问题转换成一个有序数组[1,2,3]能延伸出多少种搜索二叉树。
     * 3.每一个数都有可能作为数的根节点，此时该数作为根节点时左子树的数量和右子树的数量的乘积就是当前数当做根节点时所能形成的所有搜索二叉树的数量
     *   例：数组长度为 n ，当前 i 数（非下标）当做跟节点时 树的总数 BFS(i)=BFS(i-1)*BFS(n-i)
     * @param num
     * @return
     */

    //为了省去重读计算声明一个全局的HashMap（不能放在方法体内，因为方法体本身就是递归函数，会重复创建）
     static Map<Integer,Integer> map = new HashMap<>();
    public static int findBST(int num) {
        //当n=0 和 n=1 时 只有1中情况（为了便于计算人为默认空树也算1中结果）
        if(num == 0 || num == 1){
            return 1;
        }
        if(map.containsKey(num)){
            return map.get(num);
        }
        int count = 0;
        for(int i = 1 ; i <= num; i++){
            int leftCount = findBST(i-1);
            int rightCount = findBST(num-i);
            count += leftCount * rightCount;
        }
        map.put(num,count);
        return count;
    }
}
