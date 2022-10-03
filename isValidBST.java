package HOOT100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
有效 二叉搜索树定义如下：
节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
 
示例 1：
输入：root = [2,1,3]
输出：true

示例 2：
输入：root = [5,1,4,null,null,3,6]
输出：false
解释：根节点的值是 5 ，但是右子节点的值是 4 。
 
提示：
树中节点数目范围在[1, 104] 内
-231 <= Node.val <= 231 - 1

来源：力扣（LeetCode）

 */
public class isValidBST {
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        int[] nums = {2,1,3};
        TreeNode node = CreateTree.createTree(nums, 0);
        Boolean result = IsValidBST2(node);
        System.out.println("The Result Is :" + result);
    }
    /**
     * 中序遍历搜索二叉树  搜索二叉树的中序遍历一定是自增的
     * 循环中序遍历，只要有第i元素小于等于第i-1元素说明该树不是搜索二叉树
     */
    public static boolean IsValidBST1(TreeNode head) {
        DFS(head);
        for(int i=1; i< list.size(); i++){
            if(list.get(i) < list.get(i-1)){
                return false;
            }
        }
        return true;
    }
    public static void DFS(TreeNode head) {
        if(head != null){
            DFS(head.left);
            list.add(head.val);
            DFS(head.right);
        }
    }
    /**
     * 省去中序遍历存放的集合所消耗的空间和时间
     * @param head
     * @return
     */
    public static boolean IsValidBST2(TreeNode head) {
//        int pre = Integer.MIN_VALUE;  //不能用整数的数据范围，因为案例中给出元素大小可能小于整数最小值
        long pre = Long.MIN_VALUE;
        if(head == null){
            return true;
        }
        //访问左子树
        if(!IsValidBST2(head.left)){
            return false;
        }
        //判断当前元素（即中序遍历的第一个元素开始比较）
        if(head.val < pre){
            return false;
        }
        pre = head.val;
        return IsValidBST2(head.right);
    }
}
