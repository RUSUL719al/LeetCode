package HOOT100;
/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
 
示例 1：
输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]

示例 2：
输入：root = []
输出：[]

示例 3：
输入：root = [0]
输出：[0]
 
提示：
树中结点数在范围 [0, 2000] 内
-100 <= Node.val <= 100
 
进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？

来源：力扣（LeetCode）

 */
public class flatten {
        static int[] nums = {1,2,3,4,5,6,7};
        static TreeNode root = CreateTree.createTree(nums, 0);
    public static void main(String[] args) {
        flattenTreeNode(root);
        while (root!=null) {
            System.out.print( root.val + " ");
            root = root.right;
        }
    }
    /**
     * 解题思路：
     *  1.如果当前头结点（即当前层）有左子树，把右子树接到左子树的最右子树节点
     *  2.把左子树调到右子树的位置
     *  3.把左子树置空
     *  4.循环下一层
     * @param root
     * @return
     */
    public static void flattenTreeNode(TreeNode root) {
        //如果为空树返回空
        if(root == null){
            return;
        }
        //从第一个头节点开始遍历
        while (root != null) {
            //当前头结点如果没有左子树，则跳到下一个右子树节点
            if(root.left == null){
                root = root.right;
            } else {
                // 获取左子树
                TreeNode tmp = root.left;
                // 遍历到最右子树
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                // 把右子树接到左子树的最右子树节点
                tmp.right = root.right;
                // 把左子树调到右子树的位置
                root.right = root.left;
                // 把左子树置空
                root.left = null;
                // 循环下一层
                root = root.right;
            }
        }
    }
}
