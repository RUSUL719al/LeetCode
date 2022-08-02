package HOOT100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 
 * 实例1：
    输入：root = [1,2,2,3,4,4,3]
    输出：true

 * 实例2：
    输入：root = [1,2,2,null,3,null,3]
    输出：false
 */
public class isSymmetric {
    public static void main(String[] args) {
        int[] nodes = {1,2,2,3,4,4,3};
        TreeNode node = CreateTree.createTree(nodes, 0);
        boolean result = IsSymmetric(node);
        System.out.println("The Result Is :" + result);
    }

    public static boolean IsSymmetric(TreeNode node) {
        if(node == null ){
            return true;
        }else{
            //return dfs1(node.left, node.right);
           return dfs2(node, node);
        } 
    }

   //递归方式实现
    /**
     * 解题思路：对称二叉树是头节点的左子树与右子树完全对称，左子树某个节点的左节点必须等于右子树对称节点的右节点
     * 递归终止条件：当前左右节点其中任意一个为空或者值不相等
     * @param left
     * @param right
     * @return
     */
    public static boolean dfs1(TreeNode left, TreeNode right) {
        if(left == null && right == null){
            return true;
        }
        if((left == null || right == null) || left.val != right.val){
            return false;
        }
        return dfs1(left.right, right.left) && dfs1(left.left, right.right);
    }

    /**
     * 通过迭代的方式实现
     * 声明一个队列（队列特性：先进先出）
     * 实现思路：往队列中放入左子树的头节点和右子树的头节点，通过循环（循环条件：队列不为空）弹出队列中的节点并判断当前节点是否相等
                若相等说明左子树的头节点和右子树的同一节点是对称的，接着王队列咋红放入左子树的左节点和右子树的右节点  及  左子树的右节点和右子树的左节点
                接着继续循环判断弹出节点（注：连续弹出的节点一样的话说明该节点是对称的）
     * @param left
     * @param right
     * @return
     */
    public static boolean dfs2(TreeNode left,TreeNode right) {
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(left);
        que.offer(right);
        while (!que.isEmpty()) {
            left = que.poll();
            right = que.poll();
            if(left == null && right == null){
                continue;
            }
            if((left == null || right == null) || left.val != right.val){
                return false;
            }

            que.offer(left.left);
            que.offer(right.right);

            que.offer(left.right);
            que.offer(right.left);
        }
        return true;
    }

}
