package HOOT100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。
注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
示例 1：
输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

示例 2：
输入：root = [0,null,1]
输出：[1,null,1]

示例 3：
输入：root = [1,0,2]
输出：[3,3,2]

示例 4：
输入：root = [3,2,4,1]
输出：[7,9,4,10]

提示：
树中的节点数介于 0 和 104 之间。
每个节点的值介于 -104 和 104 之间。
树中的所有值 互不相同 。
给定的树为二叉搜索树。

来源：力扣（LeetCode）

 */
public class convertBST {
    public static void main(String[] args) {
        // TreeNode root = new TreeNode(4);
        // root.left = new TreeNode(1);
        // root.right = new TreeNode(6);
        // root.left.left = new TreeNode(0);
        // root.left.right = new TreeNode(2);
        // root.right.left = new TreeNode(5);
        // root.right.right = new TreeNode(7);
        // root.left.right.right = new TreeNode(3);
        // root.right.right.right = new TreeNode(8);

        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(1);

        TreeNode result = getConvertBST(root);
        printTree(result);
    }

    public static void printTree(TreeNode root) {
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
    
    public static TreeNode getConvertBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        addTreeToList(root,list);
        int totalSum = 0;
        for(int i = 0; i < list.size(); i++){
            map.put(list.get(i), 0);
            totalSum += list.get(i);
        }
        map.put(list.get(0), totalSum);
        for(int i = 1; i < list.size(); i++){
            map.put(list.get(i),map.get(i-1) - list.get(i-1));
        }
        return buildNew(root, map);
    }

    public static void addTreeToList(TreeNode root,List<Integer> list) {
        if(root == null){
            return;
        }
        addTreeToList(root.left,list);
        list.add(root.val);
        addTreeToList(root.right,list);
    }

    public static TreeNode buildNew(TreeNode root, Map<Integer,Integer> map) {
        if(root == null){
            return null;
        }
        TreeNode result = new TreeNode(map.get(root.val));
        result.left = buildNew(root.left,map);
        result.right =buildNew(root.right,map);
        return result;
    }
}
