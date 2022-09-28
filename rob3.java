package HOOT100;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。

示例 1:
输入: root = [3,2,3,null,3,null,1]
输出: 7 
解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7

示例 2:
输入: root = [3,4,5,1,3,null,1]
输出: 9
解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 

提示：
树的节点数在 [1, 104] 范围内
0 <= Node.val <= 104

来源：力扣（LeetCode）

 */
public class rob3 {
    public static void main(String[] args) {
        int[] nums = {3,2,3,0,3,0,1};
        //临时创建特殊二叉树（节点较少）
        TreeNode head = new TreeNode(nums[0]);
        head.left = new TreeNode(nums[1]);
        head.right = new TreeNode(nums[2]);
        //head.left.left = new TreeNode();
        head.left.right = new TreeNode(nums[4]);
        //head.right.left = new TreeNode();
        head.right.right = new TreeNode(nums[6]);
    
        int result = getRob3(head);
        System.out.println("The Result Is : " + result);
    }
    /**
     * 解题思路：每一个节点就只有取和不取两种情况，因此可以声明一个长度为2的数组保存当前节点取和不取的情况下的最大收益
     *          如果取当前节点，则它的子节点都不能取，因此取当前节点的最大收益是：当前节点的值+不取左右子节点的最大收益
     *          如果不取当前节点，则它的左右子节点各有取和不取的情况，因此不取当前节点最大值：当前节点的最大收益+左节点的最大收益+右节点的最大收益
     * @param head
     * @return
     */
    public static int getRob3(TreeNode head) {
        //用长度为2的数组接收dfs结果，返回取和不取的最大值
        int[] result = dfs(head);
        return Math.max(result[0], result[1]);
    }

    public static int[] dfs(TreeNode head) {
        //声明一个长度为2的数组，存放当前节点取和不取的最大收益  0：取  1：不取
        int[] dp = new int[2];
        if(head == null){
            return dp;
        }
        int[] left = dfs(head.left);
        int[] right = dfs(head.right);
        //根据左子节点的值更新当前节点取和不取的情况：
        //当前节点取的情况，那 左子节点不取的值 + 右子节点不取的值 + 当前节点的值 就是最大收益
        dp[0] = left[1] + right[1] + head.val;
        //当前节点不取的情况，那（左子节点的取和不取的最大值） 与 （右子节点的取和不取的最大值）总和作为最大收益 (两边都取 > 只取一遍)
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        return dp;

    }
}
