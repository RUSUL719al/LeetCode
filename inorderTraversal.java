package HOOT100;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 
示例 1：
输入：root = [1,null,2,3]
输出：[1,3,2]

示例 2：
输入：root = []
输出：[]

示例 3：
输入：root = [1]
输出：[1]
 

提示：
树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100

来源：力扣（LeetCode）

 */
public class inorderTraversal {
    public static void main(String[] args) {
        int[] nodes = {2,6,8,4,7,1,3,5,9};
        TreeNode head = CreateTree.createTree(nodes,0);
        //printTreeNode(head);
        //List<Integer> list = inordertraversal1(head);
        List<Integer> list = inordertraversal2(head);
        for(Integer result:list){
            System.out.print(" " + result);
        }
    }

    public static List<Integer> inordertraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        printTreeNode(root,list);
        return list;
    }
    //递归的方式实现
    public static void printTreeNode(TreeNode head,List<Integer> list) {
        if(head == null){
            return ;
        }
        printTreeNode(head.left,list);
        printTreeNode(head.right,list);
        list.add(head.val);
    }

    //通过栈的方式实现递归功能
    public static List<Integer> inordertraversal2(TreeNode head) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        while (stack.size()>0 || head != null) {
            if(head!=null){
                stack.add(head);
                head = head.left;
            }else{
                TreeNode node = stack.pop();
                res.add(node.val);
                head = node.right;
            }
        }
        return res;
        
    }
}
