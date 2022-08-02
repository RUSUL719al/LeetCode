package HOOT100;
/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

示例 :
给定二叉树

          1
         / \
        2   3
       / \     
      4   5    
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示。

来源：力扣（LeetCode）

 */
public class diameterOfBinaryTree {
    //最大直径（全局变量-减少传参）
    static int maxDiam = 0;
    public static void main(String[] args) {
        int[] nodes = {3,9,20,15,7};
        TreeNode node = CreateTree.createTree(nodes, 0);
        int result = findDiameterOfBinaryTree(node);
        System.out.println("The Result Is  : " + result);
    }

    public static int findDiameterOfBinaryTree(TreeNode node) {
        dp(node);
        return maxDiam;
    }

    public static int dp(TreeNode head) {
        if(head == null){
            return 0;
        }
        int left = dp(head.left);
        int right = dp(head.right);
        maxDiam = Math.max(left+right, maxDiam);
        return Math.max(left, right) + 1;
    }
}
