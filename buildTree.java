package HOOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点
  
  示例 1:
    输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    输出: [3,9,20,null,null,15,7]
   
  示例 2:
    输入: preorder = [-1], inorder = [-1]
    输出: [-1]
 
  提示:
    1 <= preorder.length <= 3000
    inorder.length == preorder.length
    -3000 <= preorder[i], inorder[i] <= 3000
    preorder 和 inorder 均 无重复 元素
    inorder 均出现在 preorder
    preorder 保证 为二叉树的前序遍历序列
    inorder 保证 为二叉树的中序遍历序列

来源：力扣（LeetCode）
 */
public class buildTree {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode node = buildTree1(preorder, inorder);
        System.out.println("The Resualt Is : " + node.toString());
    }

     public static TreeNode buildTree1(int[] preorder, int[] inorder) {
         if(preorder == null || inorder == null || preorder.length != inorder.length){
            return null;
         }
         //遍历中序遍历并记录下标
         Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length -1,  inorder, 0, inorder.length - 1, map);
     }

    /**
     * 创建一个函数，左子树及范围，右子树及范围，先序遍历的hashMap作为参数，返回该树的头结点
     * @param pre  左子树
     * @param L1   左子树开始下标
     * @param R1   左子树结束下标
     * @param in   右子树
     * @param L2   右子树开始下标
     * @param R2   右子树结束下标
     * @param map  先序遍历的hashMap（用于定位当前头结点）
     * @return
     */
     public static TreeNode buildTreeHelper(int[] pre, int L1, int R1, int[] in, int L2, int R2, Map<Integer,Integer> map) {
         if(L1 > R1){
            return null;
         }
        //先序遍历的第一个元素为整个树的头结点
        TreeNode head = new TreeNode(pre[L1]);
        if(L1 == R1){
            return head;
        }
        //在中序遍历中找出整个树的头结点
        int headIndex = map.get(pre[L1]);
        //递归的方式找出整个树的左子树和右子树的头结点
        head.left = buildTreeHelper(pre, L1 + 1, L1 + headIndex - L2 , in, L2, headIndex - 1, map);
        head.right = buildTreeHelper(pre, L1 + headIndex - L2 + 1, R1, in, headIndex + 1, R2, map);
        return head;
     }
    
}
