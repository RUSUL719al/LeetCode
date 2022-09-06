package HOOT100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]

示例 2：
输入：root = [1]
输出：[[1]]

示例 3：
输入：root = []
输出：[]
 

提示：
树中节点数目在范围 [0, 2000] 内
-1000 <= Node.val <= 1000

来源：力扣（LeetCode）

 */
public class levelOrder {
    static List<List<Integer>> bigList = new ArrayList<>();
    static List<Integer> smallList;
    public static void main(String[] args) {
        int[] nums = {3,9,20,0,0,15,7};
        TreeNode node = CreateTree.createTree(nums, 0);
        List<List<Integer>> result = LevelOrderTree(node);
        System.out.print("[");
        for(List<Integer> list : result){
            System.out.print("[");
            for(Integer num : list){
                System.out.print( num + " ");
            }
            System.out.print("]");
        }
        System.out.print("]");
    }

    public static List<List<Integer>> LevelOrderTree(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(head == null){
            return bigList;
        }
        queue.offer(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            smallList = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                smallList.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size --;
            }
            bigList.add(smallList);
        }
        return bigList;
    }
}
