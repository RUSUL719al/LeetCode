package HOOT100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

来源：力扣（LeetCode）

 */
public class maxDepth {
    public static void main(String[] args) {
        int[] nodes = {3,9,20,15,7};
        TreeNode node = CreateTree.createTree(nodes, 0);
        int result = dp(node);
        //int result = dp2(node);
        System.out.println("The Result Is :" + result);
    }
    //深度优先法则（每个节点深入到最底层的叶子结点。一个节点深入到最底层的节点时因为没有子节点了因此return函数会变成return Math.max(0, 0) + 1 = 1
    //  重点:本体中的递归规则中每个节点的高度受他的子节点的高度；因此以此类推整个过程是从最底层的叶子结点开始算高度  ）
    public static int dp(TreeNode head) {
        if(head == null){
            return 0;
        }
        int leftHigh = dp(head.left);
        int rightHigh = dp(head.right);
        return Math.max(leftHigh, rightHigh) + 1;
    }

    //广度优先法则 (按层遍历。把每一层的节点都依次放入队列，再依次弹出队列。每次队列弹出结束说明当前层的所有节点走了一遍 就层数加一)
    public static int dp2(TreeNode head) {
        if(head == null){
            return 0;
        }
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size>0) {
                TreeNode node = queue.poll();
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                size --;
            }
            result ++;
        }
        return result;
    }
}
